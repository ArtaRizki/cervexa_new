package com.gizthon.camera.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.util.Log;
import android.view.Surface;
import android.view.View;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ViewRecorder {
    private static final String TAG = "ViewRecorder";
    private View mView;
    private String mOutputPath;
    
    private MediaCodec mEncoder;
    private MediaMuxer mMuxer;
    private int mTrackIndex = -1;
    private boolean mMuxerStarted = false;
    private Surface mInputSurface;
    private boolean mIsRecording = false;
    
    private Thread mRecordThread;
    
    public ViewRecorder(View view, String outputPath) {
        mView = view;
        mOutputPath = outputPath;
    }
    
    public String getOutputPath() {
        return mOutputPath;
    }
    
    public void start() throws IOException {
        int width = mView.getWidth();
        int height = mView.getHeight();
        if (width == 0 || height == 0) {
            width = 1280; height = 720;
        }
        // Pastikan genap untuk video encoder
        width = width % 2 == 0 ? width : width - 1;
        height = height % 2 == 0 ? height : height - 1;
        
        MediaFormat format = MediaFormat.createVideoFormat(MediaFormat.MIMETYPE_VIDEO_AVC, width, height);
        format.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
        format.setInteger(MediaFormat.KEY_BIT_RATE, 3000000);
        format.setInteger(MediaFormat.KEY_FRAME_RATE, 15);
        format.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 1);
        
        mEncoder = MediaCodec.createEncoderByType(MediaFormat.MIMETYPE_VIDEO_AVC);
        mEncoder.configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
        mInputSurface = mEncoder.createInputSurface();
        mEncoder.start();
        
        mMuxer = new MediaMuxer(mOutputPath, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
        
        mIsRecording = true;
        mRecordThread = new Thread(new Runnable() {
            @Override
            public void run() {
                recordLoop();
            }
        });
        mRecordThread.start();
    }
    
    private void recordLoop() {
        final long frameTimeNs = 1000000000L / 15; // 15 fps
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        
        while (mIsRecording) {
            long loopStart = System.nanoTime();
            
            // Ambil bitmap langsung jika berupa IjkVideoView (TextureView)
            Bitmap bmp = null;
            try {
                if (mView instanceof com.jieli.stream.p016dv.running2.p017ui.widget.media.IjkVideoView) {
                    bmp = ((com.jieli.stream.p016dv.running2.p017ui.widget.media.IjkVideoView) mView).getBitmap();
                } else {
                    mView.setDrawingCacheEnabled(true);
                    mView.buildDrawingCache();
                    Bitmap cache = mView.getDrawingCache();
                    if (cache != null) {
                        bmp = Bitmap.createBitmap(cache);
                    }
                    mView.setDrawingCacheEnabled(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bmp != null) {
                try {
                    Canvas canvas = mInputSurface.lockCanvas(null);
                    if (canvas != null) {
                        canvas.drawBitmap(bmp, 0, 0, null);
                        mInputSurface.unlockCanvasAndPost(canvas);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Error drawing to surface: " + e.getMessage());
                }
                bmp.recycle();
            }
            
            drainEncoder(false, bufferInfo);
            
            long loopTimeNs = System.nanoTime() - loopStart;
            long sleepTimeMs = (frameTimeNs - loopTimeNs) / 1000000L;
            if (sleepTimeMs > 0) {
                try {
                    Thread.sleep(sleepTimeMs);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
        drainEncoder(true, bufferInfo);
        release();
    }
    
    private void drainEncoder(boolean endOfStream, MediaCodec.BufferInfo bufferInfo) {
        if (endOfStream) {
            try {
                mEncoder.signalEndOfInputStream();
            } catch (Exception e) {}
        }
        
        while (true) {
            int encoderStatus = -1;
            try {
                encoderStatus = mEncoder.dequeueOutputBuffer(bufferInfo, 10000);
            } catch (Exception e) { break; }
            
            if (encoderStatus == MediaCodec.INFO_TRY_AGAIN_LATER) {
                if (!endOfStream) break;
            } else if (encoderStatus == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                if (mMuxerStarted) throw new RuntimeException("format changed twice");
                MediaFormat newFormat = mEncoder.getOutputFormat();
                mTrackIndex = mMuxer.addTrack(newFormat);
                mMuxer.start();
                mMuxerStarted = true;
            } else if (encoderStatus >= 0) {
                ByteBuffer encodedData = mEncoder.getOutputBuffer(encoderStatus);
                if ((bufferInfo.flags & MediaCodec.BUFFER_FLAG_CODEC_CONFIG) != 0) {
                    bufferInfo.size = 0;
                }
                if (bufferInfo.size != 0) {
                    if (!mMuxerStarted) throw new RuntimeException("muxer hasn't started");
                    encodedData.position(bufferInfo.offset);
                    encodedData.limit(bufferInfo.offset + bufferInfo.size);
                    // Time assignment
                    bufferInfo.presentationTimeUs = System.nanoTime() / 1000;
                    mMuxer.writeSampleData(mTrackIndex, encodedData, bufferInfo);
                }
                mEncoder.releaseOutputBuffer(encoderStatus, false);
                if ((bufferInfo.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {
                    break;
                }
            }
        }
    }
    
    public void stop() {
        mIsRecording = false;
        if (mRecordThread != null) {
            try {
                mRecordThread.join();
            } catch (InterruptedException e) {}
            mRecordThread = null;
        }
    }
    
    private void release() {
        if (mEncoder != null) {
            try { mEncoder.stop(); mEncoder.release(); } catch (Exception e) {}
            mEncoder = null;
        }
        if (mMuxer != null) {
            if (mMuxerStarted) {
                try { mMuxer.stop(); } catch (Exception e) {}
            }
            try { mMuxer.release(); } catch (Exception e) {}
            mMuxer = null;
        }
        if (mInputSurface != null) {
            try { mInputSurface.release(); } catch (Exception e) {}
            mInputSurface = null;
        }
    }
}
