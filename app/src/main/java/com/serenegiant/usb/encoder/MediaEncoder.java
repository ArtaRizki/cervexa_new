package com.serenegiant.usb.encoder;

import android.media.MediaCodec;
import android.os.Build;
import android.util.Log;
import com.baidu.trace.model.StatusCodes;
import com.jiangdg.usbcamera.utils.FileUtils;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.serenegiant.media.AbstractAudioEncoder;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MediaEncoder implements Runnable {
    public static final int[] AUDIO_SAMPLING_RATES = {96000, 88200, AbstractAudioEncoder.DEFAULT_BIT_RATE, 48000, 44100, 32000, 24000, 22050, StatusCodes.BOS_UNINITIALIZED, StatusCodes.START_GATHER_REQUEST_FAILED, 11025, IConstant.AUDIO_SAMPLE_RATE_DEFAULT, 7350, -1, -1, -1};
    private static final boolean DEBUG = true;
    protected static final int MSG_FRAME_AVAILABLE = 1;
    protected static final int MSG_STOP_RECORDING = 9;
    private static final String TAG = "MediaEncoder";
    protected static final int TIMEOUT_USEC = 10000;
    public static final int TYPE_AUDIO = 0;
    public static final int TYPE_VIDEO = 1;
    private boolean isExit;
    private long lastPush;
    private MediaCodec.BufferInfo mBufferInfo;
    protected volatile boolean mIsCapturing;
    protected boolean mIsEOS;
    protected final MediaEncoderListener mListener;
    protected MediaCodec mMediaCodec;
    protected boolean mMuxerStarted;
    private int mRequestDrain;
    protected volatile boolean mRequestStop;
    protected int mTrackIndex;
    protected final WeakReference<MediaMuxerWrapper> mWeakMuxer;
    private long millisPerframe;
    protected final Object mSync = new Object();
    ByteBuffer mBuffer = ByteBuffer.allocate(10240);
    private long prevOutputPTSUs = 0;

    public interface MediaEncoderListener {
        void onEncodeResult(byte[] bArr, int i, int i2, long j, int i3);

        void onPrepared(MediaEncoder mediaEncoder);

        void onStopped(MediaEncoder mediaEncoder);
    }

    abstract void prepare() throws IOException;

    public MediaEncoder(MediaMuxerWrapper mediaMuxerWrapper, MediaEncoderListener mediaEncoderListener) {
        if (mediaEncoderListener == null) {
            throw new NullPointerException("MediaEncoderListener is null");
        }
        if (mediaMuxerWrapper == null) {
            throw new NullPointerException("MediaMuxerWrapper is null");
        }
        this.mWeakMuxer = new WeakReference<>(mediaMuxerWrapper);
        mediaMuxerWrapper.addEncoder(this);
        this.mListener = mediaEncoderListener;
        synchronized (this.mSync) {
            this.mBufferInfo = new MediaCodec.BufferInfo();
            new Thread(this, getClass().getSimpleName()).start();
            try {
                this.mSync.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public String getOutputPath() {
        MediaMuxerWrapper mediaMuxerWrapper = this.mWeakMuxer.get();
        if (mediaMuxerWrapper != null) {
            return mediaMuxerWrapper.getOutputPath();
        }
        return null;
    }

    public boolean frameAvailableSoon() {
        synchronized (this.mSync) {
            if (this.mIsCapturing && !this.mRequestStop) {
                this.mRequestDrain++;
                this.mSync.notifyAll();
                return true;
            }
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0064 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mSync
            monitor-enter(r0)
            r1 = 0
            r6.mRequestStop = r1     // Catch: java.lang.Throwable -> L75
            r6.mRequestDrain = r1     // Catch: java.lang.Throwable -> L75
            java.lang.Object r2 = r6.mSync     // Catch: java.lang.Throwable -> L75
            r2.notify()     // Catch: java.lang.Throwable -> L75
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L75
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r2 = r2.getAbsolutePath()
            r0.append(r2)
            java.lang.String r2 = "/test222.h264"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.jiangdg.usbcamera.utils.FileUtils.createfile(r0)
        L2a:
            java.lang.Object r2 = r6.mSync
            monitor-enter(r2)
            boolean r0 = r6.mRequestStop     // Catch: java.lang.Throwable -> L72
            int r3 = r6.mRequestDrain     // Catch: java.lang.Throwable -> L72
            r4 = 1
            if (r3 <= 0) goto L36
            r3 = 1
            goto L37
        L36:
            r3 = 0
        L37:
            if (r3 == 0) goto L3e
            int r5 = r6.mRequestDrain     // Catch: java.lang.Throwable -> L72
            int r5 = r5 - r4
            r6.mRequestDrain = r5     // Catch: java.lang.Throwable -> L72
        L3e:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L72
            if (r0 == 0) goto L4e
            r6.drain()
            r6.signalEndOfInputStream()
            r6.drain()
            r6.release()
            goto L61
        L4e:
            if (r3 == 0) goto L54
            r6.drain()
            goto L2a
        L54:
            java.lang.Object r0 = r6.mSync
            monitor-enter(r0)
            java.lang.Object r2 = r6.mSync     // Catch: java.lang.Throwable -> L5e java.lang.InterruptedException -> L60
            r2.wait()     // Catch: java.lang.Throwable -> L5e java.lang.InterruptedException -> L60
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5e
            goto L2a
        L5e:
            r1 = move-exception
            goto L70
        L60:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5e
        L61:
            java.lang.Object r2 = r6.mSync
            monitor-enter(r2)
            r6.mRequestStop = r4     // Catch: java.lang.Throwable -> L6d
            r6.mIsCapturing = r1     // Catch: java.lang.Throwable -> L6d
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L6d
            com.jiangdg.usbcamera.utils.FileUtils.releaseFile()
            return
        L6d:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L6d
            throw r0
        L70:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5e
            throw r1
        L72:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L72
            throw r0
        L75:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L75
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.serenegiant.usb.encoder.MediaEncoder.run():void");
    }

    void startRecording() {
        Log.v(TAG, "startRecording");
        synchronized (this.mSync) {
            this.mIsCapturing = true;
            this.mRequestStop = false;
            this.isExit = false;
            this.mSync.notifyAll();
        }
    }

    void stopRecording() {
        Log.v(TAG, "stopRecording");
        synchronized (this.mSync) {
            if (this.mIsCapturing && !this.mRequestStop) {
                this.mRequestStop = true;
                this.isExit = true;
                this.mSync.notifyAll();
            }
        }
    }

    protected void release() {
        MediaMuxerWrapper mediaMuxerWrapper;
        Log.d(TAG, "release:");
        try {
            this.mListener.onStopped(this);
        } catch (Exception e) {
            Log.e(TAG, "failed onStopped", e);
        }
        this.mIsCapturing = false;
        MediaCodec mediaCodec = this.mMediaCodec;
        if (mediaCodec != null) {
            try {
                mediaCodec.stop();
                this.mMediaCodec.release();
                this.mMediaCodec = null;
            } catch (Exception e2) {
                Log.e(TAG, "failed releasing MediaCodec", e2);
            }
        }
        if (this.mMuxerStarted && (mediaMuxerWrapper = this.mWeakMuxer.get()) != null) {
            try {
                mediaMuxerWrapper.stop();
            } catch (Exception e3) {
                Log.e(TAG, "failed stopping muxer", e3);
            }
        }
        this.mBufferInfo = null;
    }

    protected void signalEndOfInputStream() {
        Log.d(TAG, "sending EOS to encoder");
        encode((byte[]) null, 0, getPTSUs());
    }

    protected void encode(byte[] bArr, int i, long j) {
        if (this.mIsCapturing) {
            int i2 = 0;
            ByteBuffer[] inputBuffers = this.mMediaCodec.getInputBuffers();
            while (this.mIsCapturing && i2 < i) {
                int iDequeueInputBuffer = this.mMediaCodec.dequeueInputBuffer(10000L);
                if (iDequeueInputBuffer >= 0) {
                    ByteBuffer byteBuffer = inputBuffers[iDequeueInputBuffer];
                    byteBuffer.clear();
                    int iRemaining = byteBuffer.remaining();
                    if (i2 + iRemaining >= i) {
                        iRemaining = i - i2;
                    }
                    int i3 = iRemaining;
                    if (i3 > 0 && bArr != null) {
                        byteBuffer.put(bArr, i2, i3);
                    }
                    i2 += i3;
                    if (i <= 0) {
                        this.mIsEOS = true;
                        Log.i(TAG, "send BUFFER_FLAG_END_OF_STREAM");
                        this.mMediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, 0, j, 4);
                        return;
                    }
                    this.mMediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, i3, j, 0);
                }
            }
        }
    }

    protected void encode(ByteBuffer byteBuffer, int i) {
        int iDequeueInputBuffer;
        ByteBuffer inputBuffer;
        if (this.mIsCapturing) {
            try {
                if (this.lastPush == 0) {
                    this.lastPush = System.currentTimeMillis();
                }
                long jCurrentTimeMillis = System.currentTimeMillis() - this.lastPush;
                if (jCurrentTimeMillis >= 0) {
                    long j = this.millisPerframe - jCurrentTimeMillis;
                    if (j > 0) {
                        Thread.sleep(j / 2);
                    }
                }
                ByteBuffer[] inputBuffers = this.mMediaCodec.getInputBuffers();
                try {
                    iDequeueInputBuffer = this.mMediaCodec.dequeueInputBuffer(0L);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    iDequeueInputBuffer = -1;
                }
                if (iDequeueInputBuffer >= 0) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        inputBuffer = this.mMediaCodec.getInputBuffer(iDequeueInputBuffer);
                    } else {
                        inputBuffer = inputBuffers[iDequeueInputBuffer];
                    }
                    int iCapacity = byteBuffer.capacity();
                    byte[] bArr = new byte[iCapacity];
                    byteBuffer.get(bArr);
                    inputBuffer.clear();
                    inputBuffer.put(bArr);
                    inputBuffer.clear();
                    this.mMediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, iCapacity, System.nanoTime() / 1000, 1);
                }
                this.lastPush = System.currentTimeMillis();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }

    protected void encode(ByteBuffer byteBuffer, int i, long j) {
        if (this.mIsCapturing) {
            int i2 = 0;
            ByteBuffer[] inputBuffers = this.mMediaCodec.getInputBuffers();
            while (this.mIsCapturing && i2 < i) {
                int iDequeueInputBuffer = this.mMediaCodec.dequeueInputBuffer(10000L);
                if (iDequeueInputBuffer >= 0) {
                    ByteBuffer byteBuffer2 = inputBuffers[iDequeueInputBuffer];
                    byteBuffer2.clear();
                    int iRemaining = byteBuffer2.remaining();
                    if (i2 + iRemaining >= i) {
                        iRemaining = i - i2;
                    }
                    int i3 = iRemaining;
                    if (i3 > 0 && byteBuffer != null) {
                        byteBuffer.position(i2 + i3);
                        byteBuffer.flip();
                        byteBuffer2.put(byteBuffer);
                    }
                    i2 += i3;
                    if (i <= 0) {
                        this.mIsEOS = true;
                        Log.i(TAG, "send BUFFER_FLAG_END_OF_STREAM");
                        this.mMediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, 0, j, 4);
                        return;
                    }
                    this.mMediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, i3, j, 0);
                }
            }
        }
    }

    protected void drain() {
        int iDequeueOutputBuffer;
        ByteBuffer outputBuffer;
        byte[] bArr;
        boolean z;
        MediaCodec mediaCodec = this.mMediaCodec;
        if (mediaCodec == null) {
            return;
        }
        ByteBuffer[] outputBuffers = mediaCodec.getOutputBuffers();
        MediaMuxerWrapper mediaMuxerWrapper = this.mWeakMuxer.get();
        if (mediaMuxerWrapper == null) {
            Log.w(TAG, "muxer is unexpectedly null");
            return;
        }
        byte[] bArr2 = new byte[0];
        byte[] bArr3 = new byte[307200];
        while (true) {
            int i = 0;
            while (this.mIsCapturing) {
                iDequeueOutputBuffer = this.mMediaCodec.dequeueOutputBuffer(this.mBufferInfo, 10000L);
                if (iDequeueOutputBuffer == -1) {
                    if (!this.mIsEOS && (i = i + 1) > 5) {
                        return;
                    }
                } else if (iDequeueOutputBuffer == -3) {
                    outputBuffers = this.mMediaCodec.getOutputBuffers();
                } else {
                    if (iDequeueOutputBuffer == -2) {
                        if (this.mMuxerStarted) {
                            throw new RuntimeException("format changed twice");
                        }
                        this.mTrackIndex = mediaMuxerWrapper.addTrack(this.mMediaCodec.getOutputFormat());
                        this.mMuxerStarted = true;
                        if (mediaMuxerWrapper.start()) {
                            continue;
                        } else {
                            synchronized (mediaMuxerWrapper) {
                                while (!mediaMuxerWrapper.isStarted()) {
                                    try {
                                        mediaMuxerWrapper.wait(100L);
                                    } catch (InterruptedException unused) {
                                    }
                                }
                            }
                        }
                    } else if (iDequeueOutputBuffer < 0) {
                        Log.w(TAG, "drain:unexpected result from encoder#dequeueOutputBuffer: " + iDequeueOutputBuffer);
                    } else {
                        if (Build.VERSION.SDK_INT >= 21) {
                            outputBuffer = this.mMediaCodec.getOutputBuffer(iDequeueOutputBuffer);
                        } else {
                            outputBuffer = outputBuffers[iDequeueOutputBuffer];
                        }
                        outputBuffer.position(this.mBufferInfo.offset);
                        outputBuffer.limit(this.mBufferInfo.offset + this.mBufferInfo.size);
                        if (outputBuffer == null) {
                            throw new RuntimeException("encoderOutputBuffer " + iDequeueOutputBuffer + " was null");
                        }
                        if ((this.mBufferInfo.flags & 2) != 0) {
                            Log.d(TAG, "drain:BUFFER_FLAG_CODEC_CONFIG");
                            this.mBufferInfo.size = 0;
                        }
                        if ((this.mBufferInfo.flags & 4) != 0) {
                            this.mIsCapturing = false;
                            this.mMuxerStarted = false;
                            return;
                        }
                        if (this.mBufferInfo.size != 0) {
                            if (!this.mMuxerStarted) {
                                throw new RuntimeException("drain:muxer hasn't started");
                            }
                            this.mBufferInfo.presentationTimeUs = getPTSUs();
                            mediaMuxerWrapper.writeSampleData(this.mTrackIndex, outputBuffer, this.mBufferInfo);
                            this.prevOutputPTSUs = this.mBufferInfo.presentationTimeUs;
                            int i2 = this.mTrackIndex;
                            if (i2 == 0) {
                                outputBuffer.position(this.mBufferInfo.offset);
                                outputBuffer.limit(this.mBufferInfo.offset + this.mBufferInfo.size);
                                if ((this.mBufferInfo.flags & 2) != 0) {
                                    z = (this.mBufferInfo.flags & 1) != 0;
                                    if (!z) {
                                        break;
                                    } else {
                                        bArr = new byte[0];
                                    }
                                } else {
                                    bArr = bArr2;
                                    z = false;
                                }
                                boolean z2 = z | ((this.mBufferInfo.flags & 1) != 0);
                                int length = bArr.length + this.mBufferInfo.size;
                                if (length > bArr3.length) {
                                    bArr3 = new byte[length];
                                }
                                if (z2) {
                                    System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
                                    outputBuffer.get(bArr3, bArr.length, this.mBufferInfo.size);
                                    MediaEncoderListener mediaEncoderListener = this.mListener;
                                    if (mediaEncoderListener != null) {
                                        mediaEncoderListener.onEncodeResult(bArr3, 0, bArr.length + this.mBufferInfo.size, this.mBufferInfo.presentationTimeUs / 1000, 1);
                                    }
                                    FileUtils.putFileStream(bArr3, 0, bArr.length + this.mBufferInfo.size);
                                } else {
                                    outputBuffer.get(bArr3, 0, this.mBufferInfo.size);
                                    MediaEncoderListener mediaEncoderListener2 = this.mListener;
                                    if (mediaEncoderListener2 != null) {
                                        mediaEncoderListener2.onEncodeResult(bArr3, 0, this.mBufferInfo.size, this.mBufferInfo.presentationTimeUs / 1000, 1);
                                    }
                                    FileUtils.putFileStream(bArr3, 0, this.mBufferInfo.size);
                                }
                                bArr2 = bArr;
                            } else if (i2 == 1) {
                                this.mBuffer.clear();
                                outputBuffer.get(this.mBuffer.array(), 7, this.mBufferInfo.size);
                                outputBuffer.clear();
                                this.mBuffer.position(this.mBufferInfo.size + 7);
                                addADTStoPacket(this.mBuffer.array(), this.mBufferInfo.size + 7);
                                this.mBuffer.flip();
                                MediaEncoderListener mediaEncoderListener3 = this.mListener;
                                if (mediaEncoderListener3 != null) {
                                    mediaEncoderListener3.onEncodeResult(this.mBuffer.array(), 0, this.mBufferInfo.size + 7, this.mBufferInfo.presentationTimeUs / 1000, 0);
                                }
                            }
                            i = 0;
                        }
                        this.mMediaCodec.releaseOutputBuffer(iDequeueOutputBuffer, false);
                    }
                }
            }
            return;
            bArr2 = new byte[this.mBufferInfo.size];
            outputBuffer.get(bArr2);
            this.mMediaCodec.releaseOutputBuffer(iDequeueOutputBuffer, false);
        }
    }

    private void addADTStoPacket(byte[] bArr, int i) {
        bArr[0] = -1;
        bArr[1] = -15;
        bArr[2] = (byte) ((getSamplingRateIndex() << 2) + 64 + 0);
        bArr[3] = (byte) ((i >> 11) + 64);
        bArr[4] = (byte) ((i & 2047) >> 3);
        bArr[5] = (byte) (((i & 7) << 5) + 31);
        bArr[6] = -4;
    }

    private int getSamplingRateIndex() {
        int i = 0;
        while (true) {
            int[] iArr = AUDIO_SAMPLING_RATES;
            if (i >= iArr.length) {
                return -1;
            }
            if (iArr[i] == 44100) {
                return i;
            }
            i++;
        }
    }

    protected long getPTSUs() {
        long jNanoTime = System.nanoTime() / 1000;
        long j = this.prevOutputPTSUs;
        return jNanoTime < j ? jNanoTime + (j - jNanoTime) : jNanoTime;
    }
}
