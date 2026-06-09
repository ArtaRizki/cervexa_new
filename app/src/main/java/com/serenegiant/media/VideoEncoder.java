package com.serenegiant.media;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import com.serenegiant.common.BuildConfig;
import java.nio.ByteBuffer;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes2.dex */
public final class VideoEncoder extends AbstractVideoEncoder {
    private static final String TAG = VideoEncoder.class.getSimpleName();
    private static boolean isLoaded;
    private int BUF_SIZE;
    private final boolean mAlign16;
    private int mColorFormat;
    private byte[] mEncodeBytes;
    protected long mNativePtr;

    private final native long nativeCreate();

    private final native void nativeDestroy(long j);

    private static final native int nativePrepare(long j, int i, int i2, int i3);

    private static final native int nativeResize(long j, int i, int i2, int i3);

    private static final native int nativeStop(long j);

    @Override // com.serenegiant.media.AbstractEncoder
    public int getCaptureFormat() {
        return -1;
    }

    static {
        isLoaded = false;
        if (0 == 0) {
            System.loadLibrary(BuildConfig.STL_NAME);
            System.loadLibrary("jpeg-turbo2000");
            System.loadLibrary("png16");
            System.loadLibrary("common");
            System.loadLibrary("mediaencoder");
            isLoaded = true;
        }
    }

    public VideoEncoder(Recorder recorder, EncoderListener encoderListener, boolean z) {
        super("video/avc", recorder, encoderListener);
        int iCeil = ((((this.mWidth * ((int) Math.ceil(this.mHeight / 16.0f))) * 16) * 2) * 3) / 4;
        this.BUF_SIZE = iCeil;
        this.mEncodeBytes = new byte[iCeil];
        this.mAlign16 = z;
        this.mNativePtr = nativeCreate();
    }

    @Override // com.serenegiant.media.AbstractEncoder
    protected boolean internalPrepare() throws Exception {
        this.mRecorderStarted = false;
        this.mIsCapturing = true;
        this.mIsEOS = false;
        MediaCodecInfo mediaCodecInfoSelectVideoEncoder = MediaCodecHelper.selectVideoEncoder("video/avc");
        if (mediaCodecInfoSelectVideoEncoder == null) {
            Log.e(TAG, "Unable to find an appropriate codec for video/avc");
            return true;
        }
        boolean z = this.mWidth >= 1000 || this.mHeight >= 1000;
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat("video/avc", this.mWidth, this.mHeight);
        int iSelectColorFormat = MediaCodecHelper.selectColorFormat(mediaCodecInfoSelectVideoEncoder, "video/avc");
        this.mColorFormat = iSelectColorFormat;
        mediaFormatCreateVideoFormat.setInteger("color-format", iSelectColorFormat);
        mediaFormatCreateVideoFormat.setInteger(IjkMediaMeta.IJKM_KEY_BITRATE, this.mBitRate > 0 ? this.mBitRate : VideoConfig.getBitrate(this.mWidth, this.mHeight));
        mediaFormatCreateVideoFormat.setInteger("frame-rate", this.mFramerate > 0 ? this.mFramerate : VideoConfig.getCaptureFps());
        mediaFormatCreateVideoFormat.setInteger("i-frame-interval", this.mIFrameIntervals > 0 ? this.mIFrameIntervals : VideoConfig.getIFrame());
        Log.d(TAG, "format: " + mediaFormatCreateVideoFormat);
        this.mMediaCodec = MediaCodec.createEncoderByType("video/avc");
        this.mMediaCodec.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        this.mMediaCodec.start();
        if (this.mAlign16) {
            if ((this.mWidth / 16) * 16 != this.mWidth) {
                this.mWidth = ((this.mWidth / 16) + 1) * 16;
            }
            if ((this.mHeight / 16) * 16 != this.mHeight) {
                this.mHeight = ((this.mHeight / 16) + 1) * 16;
            }
        }
        nativePrepare(this.mNativePtr, this.mWidth, this.mHeight, MediaCodecHelper.selectColorFormat(mediaCodecInfoSelectVideoEncoder, "video/avc"));
        return z;
    }

    @Override // com.serenegiant.media.AbstractEncoder, com.serenegiant.media.Encoder
    public void stop() {
        long j = this.mNativePtr;
        if (j != 0) {
            nativeStop(j);
        }
        super.stop();
    }

    @Override // com.serenegiant.media.AbstractEncoder, com.serenegiant.media.Encoder
    public void release() {
        stop();
        long j = this.mNativePtr;
        if (j != 0) {
            nativeDestroy(j);
            this.mNativePtr = 0L;
        }
        super.release();
    }

    @Override // com.serenegiant.media.AbstractEncoder
    protected synchronized boolean startRecorder(IRecorder iRecorder, MediaFormat mediaFormat) {
        int integer;
        int integer2;
        try {
            integer = mediaFormat.getInteger("width");
        } catch (Exception unused) {
            integer = this.mWidth;
        }
        try {
            integer2 = mediaFormat.getInteger("height");
        } catch (Exception unused2) {
            integer2 = this.mHeight;
        }
        nativeResize(this.mNativePtr, integer, integer2, this.mColorFormat);
        int i = (((integer * integer2) * 2) * 3) / 4;
        if (i != this.BUF_SIZE) {
            this.BUF_SIZE = i;
            this.mEncodeBytes = new byte[i];
        }
        return super.startRecorder(iRecorder, mediaFormat);
    }

    @Override // com.serenegiant.media.AbstractEncoder
    protected void stopRecorder(IRecorder iRecorder) {
        if (this.mRecorderStarted) {
            nativeStop(this.mNativePtr);
        }
        super.stopRecorder(iRecorder);
    }

    @Override // com.serenegiant.media.AbstractEncoder, com.serenegiant.media.Encoder
    public void encode(ByteBuffer byteBuffer) {
        synchronized (this.mSync) {
            if (this.mIsCapturing && !this.mRequestStop) {
                try {
                    byteBuffer.rewind();
                    encode(byteBuffer, byteBuffer.limit(), getInputPTSUs());
                } catch (Exception e) {
                    callOnError(e);
                }
            }
        }
    }
}
