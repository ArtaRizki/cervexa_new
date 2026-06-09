package com.serenegiant.media;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.util.Log;
import com.serenegiant.utils.BufferHelper;
import com.serenegiant.utils.Time;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractEncoder implements Encoder {
    private static final String TAG = AbstractEncoder.class.getSimpleName();
    public static final int TIMEOUT_USEC = 10000;
    protected final String MIME_TYPE;
    private MediaCodec.BufferInfo mBufferInfo;
    protected volatile boolean mIsCapturing;
    protected volatile boolean mIsEOS;
    private final EncoderListener mListener;
    protected MediaCodec mMediaCodec;
    private IRecorder mRecorder;
    protected volatile boolean mRecorderStarted;
    private volatile int mRequestDrain;
    protected volatile boolean mRequestStop;
    protected int mTrackIndex;
    protected final Object mSync = new Object();
    private final Runnable mDrainTask = new Runnable() { // from class: com.serenegiant.media.AbstractEncoder.1
        /* JADX WARN: Can't wrap try/catch for region: R(17:0|2|8|6|(4:10|28|15|7)|92|98|(6:22|48|(1:82)(2:(3:71|37|95)(5:93|44|95|48|97)|94)|52|a8|56)|32|77|33|86|34|35|52|a8|(1:(0))) */
        /* JADX WARN: Removed duplicated region for block: B:84:0x00a9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:89:0x0049 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r7 = this;
                r0 = -4
                android.os.Process.setThreadPriority(r0)
                com.serenegiant.media.AbstractEncoder r0 = com.serenegiant.media.AbstractEncoder.this
                java.lang.Object r0 = r0.mSync
                monitor-enter(r0)
                com.serenegiant.media.AbstractEncoder r1 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Throwable -> Lc2
                r2 = 0
                r1.mRequestStop = r2     // Catch: java.lang.Throwable -> Lc2
                com.serenegiant.media.AbstractEncoder r1 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Throwable -> Lc2
                com.serenegiant.media.AbstractEncoder.access$002(r1, r2)     // Catch: java.lang.Throwable -> Lc2
                com.serenegiant.media.AbstractEncoder r1 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Throwable -> Lc2
                java.lang.Object r1 = r1.mSync     // Catch: java.lang.Throwable -> Lc2
                r1.notify()     // Catch: java.lang.Throwable -> Lc2
                monitor-exit(r0)     // Catch: java.lang.Throwable -> Lc2
                r0 = 0
            L1c:
                com.serenegiant.media.AbstractEncoder r1 = com.serenegiant.media.AbstractEncoder.this
                boolean r1 = r1.mIsCapturing
                if (r1 != 0) goto L44
                if (r0 != 0) goto L44
                com.serenegiant.media.AbstractEncoder r0 = com.serenegiant.media.AbstractEncoder.this
                java.lang.Object r0 = r0.mSync
                monitor-enter(r0)
                com.serenegiant.media.AbstractEncoder r1 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Throwable -> L3e java.lang.InterruptedException -> L40
                java.lang.Object r1 = r1.mSync     // Catch: java.lang.Throwable -> L3e java.lang.InterruptedException -> L40
                r3 = 10
                r1.wait(r3)     // Catch: java.lang.Throwable -> L3e java.lang.InterruptedException -> L40
                com.serenegiant.media.AbstractEncoder r1 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Throwable -> L3e
                boolean r1 = r1.mRequestStop     // Catch: java.lang.Throwable -> L3e
                com.serenegiant.media.AbstractEncoder r3 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Throwable -> L3e
                com.serenegiant.media.AbstractEncoder.access$002(r3, r2)     // Catch: java.lang.Throwable -> L3e
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L3e
                r0 = r1
                goto L1c
            L3e:
                r1 = move-exception
                goto L42
            L40:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L3e
                goto L44
            L42:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L3e
                throw r1
            L44:
                com.serenegiant.media.AbstractEncoder r0 = com.serenegiant.media.AbstractEncoder.this
                java.lang.Object r1 = r0.mSync
                monitor-enter(r1)
                com.serenegiant.media.AbstractEncoder r0 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Throwable -> Lbf
                boolean r0 = r0.mRequestStop     // Catch: java.lang.Throwable -> Lbf
                com.serenegiant.media.AbstractEncoder r3 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Throwable -> Lbf
                int r3 = com.serenegiant.media.AbstractEncoder.access$000(r3)     // Catch: java.lang.Throwable -> Lbf
                r4 = 1
                if (r3 <= 0) goto L58
                r3 = 1
                goto L59
            L58:
                r3 = 0
            L59:
                if (r3 == 0) goto L60
                com.serenegiant.media.AbstractEncoder r5 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Throwable -> Lbf
                com.serenegiant.media.AbstractEncoder.access$010(r5)     // Catch: java.lang.Throwable -> Lbf
            L60:
                monitor-exit(r1)     // Catch: java.lang.Throwable -> Lbf
                if (r0 == 0) goto L78
                com.serenegiant.media.AbstractEncoder r0 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Exception -> L68
                com.serenegiant.media.AbstractEncoder.access$100(r0)     // Catch: java.lang.Exception -> L68
            L68:
                com.serenegiant.media.AbstractEncoder r0 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Exception -> L6d
                r0.signalEndOfInputStream()     // Catch: java.lang.Exception -> L6d
            L6d:
                com.serenegiant.media.AbstractEncoder r0 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Exception -> L72
                com.serenegiant.media.AbstractEncoder.access$100(r0)     // Catch: java.lang.Exception -> L72
            L72:
                com.serenegiant.media.AbstractEncoder r0 = com.serenegiant.media.AbstractEncoder.this
                r0.release()
                goto La4
            L78:
                if (r3 == 0) goto L91
                com.serenegiant.media.AbstractEncoder r0 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Exception -> L80
                com.serenegiant.media.AbstractEncoder.access$100(r0)     // Catch: java.lang.Exception -> L80
                goto L44
            L80:
                r0 = move-exception
                com.serenegiant.media.AbstractEncoder r1 = com.serenegiant.media.AbstractEncoder.this
                boolean r1 = r1.mRequestStop
                if (r1 != 0) goto L8c
                com.serenegiant.media.AbstractEncoder r1 = com.serenegiant.media.AbstractEncoder.this
                r1.callOnError(r0)
            L8c:
                com.serenegiant.media.AbstractEncoder r0 = com.serenegiant.media.AbstractEncoder.this
                r0.mRequestStop = r4
                goto L44
            L91:
                com.serenegiant.media.AbstractEncoder r0 = com.serenegiant.media.AbstractEncoder.this
                java.lang.Object r0 = r0.mSync
                monitor-enter(r0)
                com.serenegiant.media.AbstractEncoder r1 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Throwable -> La1 java.lang.InterruptedException -> La3
                java.lang.Object r1 = r1.mSync     // Catch: java.lang.Throwable -> La1 java.lang.InterruptedException -> La3
                r5 = 30
                r1.wait(r5)     // Catch: java.lang.Throwable -> La1 java.lang.InterruptedException -> La3
                monitor-exit(r0)     // Catch: java.lang.Throwable -> La1
                goto L44
            La1:
                r1 = move-exception
                goto Lbd
            La3:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> La1
            La4:
                com.serenegiant.media.AbstractEncoder r0 = com.serenegiant.media.AbstractEncoder.this
                java.lang.Object r1 = r0.mSync
                monitor-enter(r1)
                com.serenegiant.media.AbstractEncoder r0 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Throwable -> Lba
                r0.mRequestStop = r4     // Catch: java.lang.Throwable -> Lba
                com.serenegiant.media.AbstractEncoder r0 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Throwable -> Lba
                r0.mIsCapturing = r2     // Catch: java.lang.Throwable -> Lba
                com.serenegiant.media.AbstractEncoder r0 = com.serenegiant.media.AbstractEncoder.this     // Catch: java.lang.Throwable -> Lba
                java.lang.Object r0 = r0.mSync     // Catch: java.lang.Throwable -> Lba
                r0.notifyAll()     // Catch: java.lang.Throwable -> Lba
                monitor-exit(r1)     // Catch: java.lang.Throwable -> Lba
                return
            Lba:
                r0 = move-exception
                monitor-exit(r1)     // Catch: java.lang.Throwable -> Lba
                throw r0
            Lbd:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> La1
                throw r1
            Lbf:
                r0 = move-exception
                monitor-exit(r1)     // Catch: java.lang.Throwable -> Lbf
                throw r0
            Lc2:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> Lc2
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.serenegiant.media.AbstractEncoder.RunnableC18471.run():void");
        }
    };
    private long prevOutputPTSUs = -1;
    private long prevInputPTSUs = -1;

    protected abstract MediaFormat createOutputFormat(byte[] bArr, int i, int i2, int i3, int i4);

    @Override // com.serenegiant.media.Encoder
    public void encode(ByteBuffer byteBuffer) {
    }

    public int getCaptureFormat() {
        return -1;
    }

    protected abstract boolean internalPrepare() throws Exception;

    static /* synthetic */ int access$010(AbstractEncoder abstractEncoder) {
        int i = abstractEncoder.mRequestDrain;
        abstractEncoder.mRequestDrain = i - 1;
        return i;
    }

    public AbstractEncoder(String str, IRecorder iRecorder, EncoderListener encoderListener) {
        if (encoderListener == null) {
            throw new NullPointerException("EncodeListener is null");
        }
        if (iRecorder == null) {
            throw new NullPointerException("IMuxer is null");
        }
        this.MIME_TYPE = str;
        this.mRecorder = iRecorder;
        this.mListener = encoderListener;
        iRecorder.addEncoder(this);
        this.mBufferInfo = new MediaCodec.BufferInfo();
    }

    public IRecorder getRecorder() {
        return this.mRecorder;
    }

    @Override // com.serenegiant.media.Encoder
    public String getOutputPath() {
        IRecorder iRecorder = this.mRecorder;
        if (iRecorder != null) {
            return iRecorder.getOutputPath();
        }
        return null;
    }

    protected void finalize() throws Throwable {
        this.mRecorder = null;
        release();
        super.finalize();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.serenegiant.media.Encoder
    public final void prepare() throws Exception {
        try {
            this.mListener.onStartEncode(this, this instanceof ISurfaceEncoder ? ((ISurfaceEncoder) this).getInputSurface() : null, getCaptureFormat(), internalPrepare());
        } catch (Exception e) {
            Log.w(TAG, e);
        }
        synchronized (this.mSync) {
            new Thread(this.mDrainTask, getClass().getSimpleName()).start();
            try {
                this.mSync.wait(1000L);
            } catch (InterruptedException unused) {
            }
        }
    }

    protected void callOnError(Exception exc) {
        try {
            this.mListener.onError(exc);
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    @Override // com.serenegiant.media.Encoder
    public void start() {
        synchronized (this.mSync) {
            this.mIsCapturing = true;
            this.mRequestStop = false;
            this.mRequestDrain = 0;
        }
    }

    @Override // com.serenegiant.media.Encoder
    public void stop() {
        synchronized (this.mSync) {
            if (this.mRequestStop) {
                return;
            }
            this.mRequestStop = true;
            this.mSync.notifyAll();
        }
    }

    @Override // com.serenegiant.media.Encoder
    public void frameAvailableSoon() {
        synchronized (this.mSync) {
            if (this.mIsCapturing && !this.mRequestStop) {
                this.mRequestDrain++;
                this.mSync.notifyAll();
            }
        }
    }

    @Override // com.serenegiant.media.Encoder
    public void release() {
        if (this.mIsCapturing) {
            try {
                this.mListener.onStopEncode(this);
            } catch (Exception unused) {
            }
        }
        this.mIsCapturing = false;
        MediaCodec mediaCodec = this.mMediaCodec;
        if (mediaCodec != null) {
            try {
                mediaCodec.stop();
                this.mMediaCodec.release();
                this.mMediaCodec = null;
            } catch (Exception unused2) {
            }
        }
        if (this.mRecorderStarted) {
            this.mRecorderStarted = false;
            IRecorder iRecorder = this.mRecorder;
            if (iRecorder != null) {
                try {
                    iRecorder.stop(this);
                } catch (Exception unused3) {
                }
            }
        }
        try {
            this.mListener.onDestroy(this);
        } catch (Exception unused4) {
        }
        this.mBufferInfo = null;
        this.mRecorder = null;
    }

    @Override // com.serenegiant.media.Encoder
    public void signalEndOfInputStream() {
        encode(null, 0, getInputPTSUs());
    }

    @Override // com.serenegiant.media.Encoder
    public boolean isCapturing() {
        boolean z;
        synchronized (this.mSync) {
            z = this.mIsCapturing;
        }
        return z;
    }

    @Override // com.serenegiant.media.Encoder
    public void encode(ByteBuffer byteBuffer, int i, long j) {
        synchronized (this.mSync) {
            if (this.mIsCapturing && !this.mRequestStop) {
                if (this.mMediaCodec == null) {
                    return;
                }
                ByteBuffer[] inputBuffers = this.mMediaCodec.getInputBuffers();
                while (this.mIsCapturing) {
                    int iDequeueInputBuffer = this.mMediaCodec.dequeueInputBuffer(10000L);
                    if (iDequeueInputBuffer >= 0) {
                        ByteBuffer byteBuffer2 = inputBuffers[iDequeueInputBuffer];
                        byteBuffer2.clear();
                        if (byteBuffer != null && i > 0) {
                            byteBuffer.clear();
                            byteBuffer.position(i);
                            byteBuffer.flip();
                            byteBuffer2.put(byteBuffer);
                        }
                        if (i <= 0) {
                            this.mIsEOS = true;
                            this.mMediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, 0, j, 4);
                            return;
                        } else {
                            this.mMediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, i, j, 0);
                            return;
                        }
                    }
                    if (iDequeueInputBuffer == -1) {
                        frameAvailableSoon();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void drain() {
        MediaCodec mediaCodec = this.mMediaCodec;
        if (mediaCodec == null) {
            return;
        }
        try {
            ByteBuffer[] outputBuffers = mediaCodec.getOutputBuffers();
            IRecorder iRecorder = this.mRecorder;
            if (iRecorder == null) {
                return;
            }
            int i = 0;
            while (this.mIsCapturing) {
                int iDequeueOutputBuffer = this.mMediaCodec.dequeueOutputBuffer(this.mBufferInfo, 10000L);
                if (iDequeueOutputBuffer == -1) {
                    if (!this.mIsEOS && (i = i + 1) > 5) {
                        return;
                    }
                } else if (iDequeueOutputBuffer == -3) {
                    outputBuffers = this.mMediaCodec.getOutputBuffers();
                } else if (iDequeueOutputBuffer == -2) {
                    if (this.mRecorderStarted) {
                        throw new RuntimeException("format changed twice");
                    }
                    if (!startRecorder(iRecorder, this.mMediaCodec.getOutputFormat())) {
                        return;
                    }
                } else if (iDequeueOutputBuffer >= 0) {
                    ByteBuffer byteBuffer = outputBuffers[iDequeueOutputBuffer];
                    if (byteBuffer == null) {
                        throw new RuntimeException("encoderOutputBuffer " + iDequeueOutputBuffer + " was null");
                    }
                    if ((this.mBufferInfo.flags & 2) != 0) {
                        if (!this.mRecorderStarted) {
                            byte[] bArr = new byte[this.mBufferInfo.size];
                            byteBuffer.position(0);
                            byteBuffer.get(bArr, this.mBufferInfo.offset, this.mBufferInfo.size);
                            byteBuffer.position(0);
                            int iByteComp = BufferHelper.byteComp(bArr, 0, BufferHelper.ANNEXB_START_MARK, BufferHelper.ANNEXB_START_MARK.length);
                            int iByteComp2 = BufferHelper.byteComp(bArr, iByteComp + 2, BufferHelper.ANNEXB_START_MARK, BufferHelper.ANNEXB_START_MARK.length);
                            if (!startRecorder(iRecorder, createOutputFormat(bArr, this.mBufferInfo.size, iByteComp, iByteComp2, BufferHelper.byteComp(bArr, iByteComp2 + 2, BufferHelper.ANNEXB_START_MARK, BufferHelper.ANNEXB_START_MARK.length)))) {
                                return;
                            }
                        }
                        this.mBufferInfo.size = 0;
                    }
                    if (this.mBufferInfo.size != 0) {
                        if (!this.mRecorderStarted) {
                            throw new RuntimeException("drain:muxer hasn't started");
                        }
                        try {
                            this.mBufferInfo.presentationTimeUs = getNextOutputPTSUs(this.mBufferInfo.presentationTimeUs);
                            iRecorder.writeSampleData(this.mTrackIndex, byteBuffer, this.mBufferInfo);
                        } catch (TimeoutException unused) {
                            iRecorder.stopRecording();
                        } catch (Exception unused2) {
                            iRecorder.stopRecording();
                        }
                        i = 0;
                    }
                    this.mMediaCodec.releaseOutputBuffer(iDequeueOutputBuffer, false);
                    if ((this.mBufferInfo.flags & 4) != 0) {
                        stopRecorder(iRecorder);
                        return;
                    }
                } else {
                    continue;
                }
            }
        } catch (IllegalStateException unused3) {
        }
    }

    protected boolean startRecorder(IRecorder iRecorder, MediaFormat mediaFormat) {
        if (iRecorder.getState() != 3) {
            for (int i = 0; i < 10 && iRecorder.getState() != 3; i++) {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException unused) {
                }
            }
        }
        if (iRecorder.getState() == 3) {
            int iAddTrack = iRecorder.addTrack(this, mediaFormat);
            this.mTrackIndex = iAddTrack;
            if (iAddTrack >= 0) {
                this.mRecorderStarted = true;
                iRecorder.start(this);
            } else {
                iRecorder.removeEncoder(this);
            }
        }
        return iRecorder.isStarted();
    }

    protected void stopRecorder(IRecorder iRecorder) {
        this.mIsCapturing = false;
        this.mRecorderStarted = false;
    }

    protected long getInputPTSUs() {
        long jNanoTime = Time.nanoTime() / 1000;
        long j = this.prevInputPTSUs;
        if (jNanoTime <= j) {
            jNanoTime = 9643 + j;
        }
        this.prevInputPTSUs = jNanoTime;
        return jNanoTime;
    }

    protected long getNextOutputPTSUs(long j) {
        long j2 = this.prevOutputPTSUs;
        if (j <= j2) {
            j = 9643 + j2;
        }
        this.prevOutputPTSUs = j;
        return j;
    }
}
