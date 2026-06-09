package com.serenegiant.media;

import android.media.MediaFormat;
import android.os.Bundle;
import com.serenegiant.utils.BuildCheck;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractVideoEncoder extends AbstractEncoder implements IVideoEncoder {
    public static boolean supportsAdaptiveStreaming = BuildCheck.isKitKat();
    protected int mBitRate;
    protected int mFramerate;
    protected int mHeight;
    protected int mIFrameIntervals;
    protected int mWidth;

    @Override // com.serenegiant.media.Encoder
    public final boolean isAudio() {
        return false;
    }

    public AbstractVideoEncoder(String str, IRecorder iRecorder, EncoderListener encoderListener) {
        super(str, iRecorder, encoderListener);
        this.mBitRate = -1;
        this.mFramerate = -1;
        this.mIFrameIntervals = -1;
    }

    @Override // com.serenegiant.media.IVideoEncoder
    public void setVideoSize(int i, int i2) throws IllegalStateException, IllegalArgumentException {
        this.mWidth = i;
        this.mHeight = i2;
        this.mBitRate = VideoConfig.getBitrate(i, i2);
    }

    public void setVideoConfig(int i, int i2, int i3) {
        this.mBitRate = i;
        this.mFramerate = i2;
        this.mIFrameIntervals = i3;
    }

    @Override // com.serenegiant.media.IVideoEncoder
    public int getWidth() {
        return this.mWidth;
    }

    @Override // com.serenegiant.media.IVideoEncoder
    public int getHeight() {
        return this.mHeight;
    }

    public void adjustBitrate(int i) {
        if (!supportsAdaptiveStreaming || this.mMediaCodec == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("video-bitrate", i);
        this.mMediaCodec.setParameters(bundle);
    }

    @Override // com.serenegiant.media.AbstractEncoder
    protected MediaFormat createOutputFormat(byte[] bArr, int i, int i2, int i3, int i4) {
        if (i2 >= 0) {
            MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(this.MIME_TYPE, this.mWidth, this.mHeight);
            int i5 = i3 - i2;
            ByteBuffer byteBufferOrder = ByteBuffer.allocateDirect(i5).order(ByteOrder.nativeOrder());
            byteBufferOrder.put(bArr, i2, i5);
            byteBufferOrder.flip();
            mediaFormatCreateVideoFormat.setByteBuffer("csd-0", byteBufferOrder);
            if (i3 > i2) {
                int i6 = (i - i3) + i2;
                ByteBuffer byteBufferOrder2 = ByteBuffer.allocateDirect(i6).order(ByteOrder.nativeOrder());
                byteBufferOrder2.put(bArr, i3, i6);
                byteBufferOrder2.flip();
                mediaFormatCreateVideoFormat.setByteBuffer("csd-1", byteBufferOrder2);
            }
            return mediaFormatCreateVideoFormat;
        }
        throw new RuntimeException("unexpected csd data came.");
    }
}
