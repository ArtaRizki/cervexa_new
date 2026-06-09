package com.serenegiant.usb.encoder;

import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Process;
import android.util.Log;
import android.view.Surface;
import com.serenegiant.usb.encoder.MediaEncoder;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes2.dex */
public class MediaAudioEncoder extends MediaEncoder implements IAudioEncoder {
    private static final int[] AUDIO_SOURCES = {0, 1, 5};
    private static final int BIT_RATE = 64000;
    private static final boolean DEBUG = true;
    public static final int FRAMES_PER_BUFFER = 25;
    private static final String MIME_TYPE = "audio/mp4a-latm";
    public static final int SAMPLES_PER_FRAME = 1024;
    public static final int SAMPLE_RATE = 44100;
    private static final String TAG = "MediaAudioEncoder";
    private AudioThread mAudioThread;

    public MediaAudioEncoder(MediaMuxerWrapper mediaMuxerWrapper, MediaEncoder.MediaEncoderListener mediaEncoderListener) {
        super(mediaMuxerWrapper, mediaEncoderListener);
        this.mAudioThread = null;
    }

    @Override // com.serenegiant.usb.encoder.MediaEncoder
    protected void prepare() throws IOException {
        Log.v(TAG, "prepare:");
        this.mTrackIndex = -1;
        this.mIsEOS = false;
        this.mMuxerStarted = false;
        MediaCodecInfo mediaCodecInfoSelectAudioCodec = selectAudioCodec("audio/mp4a-latm");
        if (mediaCodecInfoSelectAudioCodec == null) {
            Log.e(TAG, "Unable to find an appropriate codec for audio/mp4a-latm");
            return;
        }
        Log.i(TAG, "selected codec: " + mediaCodecInfoSelectAudioCodec.getName());
        MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", 44100, 1);
        mediaFormatCreateAudioFormat.setInteger("aac-profile", 2);
        mediaFormatCreateAudioFormat.setInteger("channel-mask", 16);
        mediaFormatCreateAudioFormat.setInteger(IjkMediaMeta.IJKM_KEY_BITRATE, 64000);
        mediaFormatCreateAudioFormat.setInteger("channel-count", 1);
        Log.i(TAG, "format: " + mediaFormatCreateAudioFormat);
        this.mMediaCodec = MediaCodec.createEncoderByType("audio/mp4a-latm");
        this.mMediaCodec.configure(mediaFormatCreateAudioFormat, (Surface) null, (MediaCrypto) null, 1);
        this.mMediaCodec.start();
        Log.i(TAG, "prepare finishing");
        if (this.mListener != null) {
            try {
                this.mListener.onPrepared(this);
            } catch (Exception e) {
                Log.e(TAG, "prepare:", e);
            }
        }
    }

    @Override // com.serenegiant.usb.encoder.MediaEncoder
    protected void startRecording() {
        super.startRecording();
        if (this.mAudioThread == null) {
            AudioThread audioThread = new AudioThread();
            this.mAudioThread = audioThread;
            audioThread.start();
        }
    }

    @Override // com.serenegiant.usb.encoder.MediaEncoder
    protected void release() {
        this.mAudioThread = null;
        super.release();
    }

    private class AudioThread extends Thread {
        private AudioThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int i;
            Process.setThreadPriority(-16);
            int minBufferSize = AudioRecord.getMinBufferSize(44100, 16, 2);
            int i2 = 25600 < minBufferSize ? ((minBufferSize / 1024) + 1) * 1024 * 2 : 25600;
            ByteBuffer byteBufferOrder = ByteBuffer.allocateDirect(1024).order(ByteOrder.nativeOrder());
            AudioRecord audioRecord = null;
            for (int i3 : MediaAudioEncoder.AUDIO_SOURCES) {
                try {
                    AudioRecord audioRecord2 = new AudioRecord(i3, 44100, 16, 2, i2);
                    if (audioRecord2.getState() != 1) {
                        audioRecord2.release();
                        audioRecord2 = null;
                    }
                    audioRecord = audioRecord2;
                } catch (Exception unused) {
                    audioRecord = null;
                }
                if (audioRecord != null) {
                    break;
                }
            }
            if (audioRecord != null) {
                try {
                    try {
                        if (MediaAudioEncoder.this.mIsCapturing) {
                            Log.v(MediaAudioEncoder.TAG, "AudioThread:start audio recording");
                            audioRecord.startRecording();
                            i = 0;
                            while (MediaAudioEncoder.this.mIsCapturing && !MediaAudioEncoder.this.mRequestStop && !MediaAudioEncoder.this.mIsEOS) {
                                try {
                                    try {
                                        byteBufferOrder.clear();
                                        try {
                                            int i4 = audioRecord.read(byteBufferOrder, 1024);
                                            if (i4 > 0) {
                                                byteBufferOrder.position(i4);
                                                byteBufferOrder.flip();
                                                MediaAudioEncoder.this.encode(byteBufferOrder, i4, MediaAudioEncoder.this.getPTSUs());
                                                MediaAudioEncoder.this.frameAvailableSoon();
                                                i++;
                                            }
                                        } catch (Exception unused2) {
                                        }
                                    } catch (Exception e) {
                                        e = e;
                                        Log.e(MediaAudioEncoder.TAG, "AudioThread#run", e);
                                    }
                                } catch (Throwable th) {
                                    audioRecord.stop();
                                    throw th;
                                }
                            }
                            if (i > 0) {
                                MediaAudioEncoder.this.frameAvailableSoon();
                            }
                            audioRecord.stop();
                        } else {
                            i = 0;
                        }
                    } finally {
                        audioRecord.release();
                    }
                } catch (Exception e2) {
                    e = e2;
                    i = 0;
                }
            } else {
                i = 0;
            }
            if (i == 0) {
                for (int i5 = 0; MediaAudioEncoder.this.mIsCapturing && i5 < 5; i5++) {
                    byteBufferOrder.position(1024);
                    byteBufferOrder.flip();
                    try {
                        MediaAudioEncoder.this.encode(byteBufferOrder, 1024, MediaAudioEncoder.this.getPTSUs());
                        MediaAudioEncoder.this.frameAvailableSoon();
                        synchronized (this) {
                            try {
                                wait(50L);
                            } catch (InterruptedException unused3) {
                            }
                        }
                    } catch (Exception unused4) {
                    }
                }
            }
            Log.v(MediaAudioEncoder.TAG, "AudioThread:finished");
        }
    }

    private static final MediaCodecInfo selectAudioCodec(String str) {
        Log.v(TAG, "selectAudioCodec:");
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                for (int i2 = 0; i2 < supportedTypes.length; i2++) {
                    Log.i(TAG, "supportedType:" + codecInfoAt.getName() + ",MIME=" + supportedTypes[i2]);
                    if (supportedTypes[i2].equalsIgnoreCase(str)) {
                        return codecInfoAt;
                    }
                }
            }
        }
        return null;
    }
}
