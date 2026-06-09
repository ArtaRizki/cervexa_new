package com.serenegiant.media;

import android.media.MediaFormat;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes2.dex */
public class FakeVideoEncoder extends AbstractFakeEncoder implements IVideoEncoder {
    public static final String MIME_AVC = "video/avc";
    private static final String TAG = FakeVideoEncoder.class.getSimpleName();
    protected int mHeight;
    protected int mWidth;

    @Override // com.serenegiant.media.Encoder
    @Deprecated
    public boolean isAudio() {
        return false;
    }

    public FakeVideoEncoder(IRecorder iRecorder, EncoderListener encoderListener) {
        super("video/avc", iRecorder, encoderListener);
    }

    public FakeVideoEncoder(IRecorder iRecorder, EncoderListener encoderListener, int i) {
        super("video/avc", iRecorder, encoderListener, i);
    }

    public FakeVideoEncoder(IRecorder iRecorder, EncoderListener encoderListener, int i, int i2, int i3) {
        super("video/avc", iRecorder, encoderListener, i, i2, i3);
    }

    public FakeVideoEncoder(String str, IRecorder iRecorder, EncoderListener encoderListener) {
        super(str, iRecorder, encoderListener);
    }

    public FakeVideoEncoder(String str, IRecorder iRecorder, EncoderListener encoderListener, int i) {
        super(str, iRecorder, encoderListener, i);
    }

    public FakeVideoEncoder(String str, IRecorder iRecorder, EncoderListener encoderListener, int i, int i2, int i3) {
        super(str, iRecorder, encoderListener, i, i2, i3);
    }

    @Override // com.serenegiant.media.AbstractFakeEncoder
    protected MediaFormat createOutputFormat(String str, byte[] bArr, int i, int i2, int i3, int i4) {
        if (i2 >= 0) {
            MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(str, this.mWidth, this.mHeight);
            int i5 = i3 - i2;
            ByteBuffer byteBufferOrder = ByteBuffer.allocateDirect(i5).order(ByteOrder.nativeOrder());
            byteBufferOrder.put(bArr, i2, i5);
            byteBufferOrder.flip();
            mediaFormatCreateVideoFormat.setByteBuffer("csd-0", byteBufferOrder);
            if (i3 > i2) {
                int i6 = i4 > i3 ? i4 - i3 : i - i3;
                ByteBuffer byteBufferOrder2 = ByteBuffer.allocateDirect(i6).order(ByteOrder.nativeOrder());
                byteBufferOrder2.put(bArr, i3, i6);
                byteBufferOrder2.flip();
                mediaFormatCreateVideoFormat.setByteBuffer("csd-1", byteBufferOrder2);
            }
            return mediaFormatCreateVideoFormat;
        }
        throw new RuntimeException("unexpected csd data came.");
    }

    @Override // com.serenegiant.media.IVideoEncoder
    public void setVideoSize(int i, int i2) throws IllegalStateException, IllegalArgumentException {
        this.mWidth = i;
        this.mHeight = i2;
    }

    @Override // com.serenegiant.media.IVideoEncoder
    public int getWidth() {
        return this.mWidth;
    }

    @Override // com.serenegiant.media.IVideoEncoder
    public int getHeight() {
        return this.mHeight;
    }
}
