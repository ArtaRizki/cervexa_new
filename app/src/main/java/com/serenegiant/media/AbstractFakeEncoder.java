package com.serenegiant.media;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import com.serenegiant.utils.BufferHelper;
import com.serenegiant.utils.BuildCheck;
import com.serenegiant.utils.Time;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractFakeEncoder implements Encoder {
    public static final int BUFFER_FLAG_KEY_FRAME;
    private static final int DEFAULT_FRAME_SZ = 1024;
    private static final int DEFAULT_MAX_POOL_SZ = 8;
    private static final int DEFAULT_MAX_QUEUE_SZ = 6;
    private static final long MAX_WAIT_FRAME_MS = 100;
    private static final String TAG = AbstractFakeEncoder.class.getSimpleName();
    private final int FRAME_SZ;
    private final int MAX_POOL_SZ;
    private final String MIME_TYPE;
    private int cnt;
    private final MediaCodec.BufferInfo mBufferInfo;
    private final Runnable mDrainTask;
    private Thread mDrainThread;
    private final LinkedBlockingQueue<MediaData> mFrameQueue;
    private volatile boolean mIsCapturing;
    private boolean mIsEOS;
    private final EncoderListener mListener;
    private final List<MediaData> mPool;
    private IRecorder mRecorder;
    private volatile boolean mRecorderStarted;
    private volatile boolean mRequestStop;
    private final Object mSync;
    private int mTrackIndex;
    private volatile boolean mWaitingKeyFrame;
    private long prevInputPTSUs;
    private long prevOutputPTSUs;

    protected abstract MediaFormat createOutputFormat(String str, byte[] bArr, int i, int i2, int i3, int i4);

    static {
        BuildCheck.isLollipop();
        BUFFER_FLAG_KEY_FRAME = 1;
    }

    public AbstractFakeEncoder(String str, IRecorder iRecorder, EncoderListener encoderListener) {
        this(str, iRecorder, encoderListener, 1024, 8, 6);
    }

    public AbstractFakeEncoder(String str, IRecorder iRecorder, EncoderListener encoderListener, int i) {
        this(str, iRecorder, encoderListener, i, 8, 6);
    }

    public AbstractFakeEncoder(String str, IRecorder iRecorder, EncoderListener encoderListener, int i, int i2, int i3) {
        this.mSync = new Object();
        this.mPool = new ArrayList();
        this.mBufferInfo = new MediaCodec.BufferInfo();
        this.cnt = 0;
        this.mDrainTask = new Runnable() { // from class: com.serenegiant.media.AbstractFakeEncoder.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (AbstractFakeEncoder.this.mSync) {
                    AbstractFakeEncoder.this.mRequestStop = false;
                    AbstractFakeEncoder.this.mSync.notify();
                }
                while (AbstractFakeEncoder.this.mIsCapturing) {
                    MediaData mediaDataWaitFrame = AbstractFakeEncoder.this.waitFrame(100L);
                    if (mediaDataWaitFrame != null) {
                        try {
                            if (AbstractFakeEncoder.this.mIsCapturing) {
                                AbstractFakeEncoder.this.handleFrame(mediaDataWaitFrame);
                            }
                        } finally {
                            AbstractFakeEncoder.this.recycle(mediaDataWaitFrame);
                        }
                    }
                }
                synchronized (AbstractFakeEncoder.this.mSync) {
                    AbstractFakeEncoder.this.mRequestStop = true;
                    AbstractFakeEncoder.this.mIsCapturing = false;
                }
                AbstractFakeEncoder.this.mDrainThread = null;
            }
        };
        this.prevInputPTSUs = -1L;
        this.prevOutputPTSUs = -1L;
        this.MIME_TYPE = str;
        this.FRAME_SZ = i;
        this.MAX_POOL_SZ = i2;
        this.mRecorder = iRecorder;
        this.mListener = encoderListener;
        this.mFrameQueue = new LinkedBlockingQueue<>(i3);
        iRecorder.addEncoder(this);
    }

    protected void finalize() throws Throwable {
        release();
        super.finalize();
    }

    public IRecorder getRecorder() {
        return this.mRecorder;
    }

    public boolean isRecorderStarted() {
        return this.mRecorderStarted;
    }

    @Override // com.serenegiant.media.Encoder
    public String getOutputPath() {
        IRecorder iRecorder = this.mRecorder;
        if (iRecorder != null) {
            return iRecorder.getOutputPath();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001a A[Catch: all -> 0x000b, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0005, B:11:0x0013, B:13:0x001a, B:10:0x000e), top: B:18:0x0001, inners: #0 }] */
    @Override // com.serenegiant.media.Encoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void release() {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.Thread r0 = r2.mDrainThread     // Catch: java.lang.Throwable -> Lb java.lang.Exception -> Ld
            if (r0 == 0) goto L13
            java.lang.Thread r0 = r2.mDrainThread     // Catch: java.lang.Throwable -> Lb java.lang.Exception -> Ld
            r0.interrupt()     // Catch: java.lang.Throwable -> Lb java.lang.Exception -> Ld
            goto L13
        Lb:
            r0 = move-exception
            goto L1f
        Ld:
            r0 = move-exception
            java.lang.String r1 = com.serenegiant.media.AbstractFakeEncoder.TAG     // Catch: java.lang.Throwable -> Lb
            android.util.Log.w(r1, r0)     // Catch: java.lang.Throwable -> Lb
        L13:
            r0 = 0
            r2.mDrainThread = r0     // Catch: java.lang.Throwable -> Lb
            com.serenegiant.media.IRecorder r0 = r2.mRecorder     // Catch: java.lang.Throwable -> Lb
            if (r0 == 0) goto L1d
            r2.internalRelease()     // Catch: java.lang.Throwable -> Lb
        L1d:
            monitor-exit(r2)
            return
        L1f:
            monitor-exit(r2)
            throw r0
        */
        return;
    }

    @Override // com.serenegiant.media.Encoder
    public void signalEndOfInputStream() {
        MediaData mediaDataObtain = obtain(0);
        mediaDataObtain.set(null, 0, 0, getInputPTSUs(), 4);
        offer(mediaDataObtain);
    }

    @Override // com.serenegiant.media.Encoder
    public void encode(ByteBuffer byteBuffer) {
        throw new UnsupportedOperationException("can not call encode");
    }

    @Override // com.serenegiant.media.Encoder
    public void encode(ByteBuffer byteBuffer, int i, long j) {
        throw new UnsupportedOperationException("can not call encode");
    }

    public boolean queueFrame(ByteBuffer byteBuffer, int i, int i2, long j, int i3) throws IllegalStateException {
        if (!this.mIsCapturing) {
            throw new IllegalStateException();
        }
        if (this.mRequestStop) {
            return false;
        }
        MediaData mediaDataObtain = obtain(i2);
        mediaDataObtain.set(byteBuffer, i, i2, j, i3);
        return offer(mediaDataObtain);
    }

    @Override // com.serenegiant.media.Encoder
    public boolean isCapturing() {
        return this.mIsCapturing;
    }

    @Override // com.serenegiant.media.Encoder
    public void prepare() throws Exception {
        this.mTrackIndex = -1;
        this.mRecorderStarted = false;
        this.mWaitingKeyFrame = true;
        this.mIsCapturing = true;
        this.mIsEOS = false;
        this.mRequestStop = false;
        callOnStartEncode(null, -1, false);
    }

    @Override // com.serenegiant.media.Encoder
    public void start() {
        synchronized (this.mSync) {
            if (this.mIsCapturing && !this.mRequestStop) {
                initPool();
                Thread thread = new Thread(this.mDrainTask, getClass().getSimpleName());
                this.mDrainThread = thread;
                thread.start();
                try {
                    this.mSync.wait();
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    @Override // com.serenegiant.media.Encoder
    public void stop() {
        synchronized (this.mSync) {
            if (this.mRequestStop) {
                return;
            }
            this.mRequestStop = true;
            signalEndOfInputStream();
            this.mSync.notifyAll();
        }
    }

    @Override // com.serenegiant.media.Encoder
    public void frameAvailableSoon() {
        synchronized (this.mSync) {
            if (this.mIsCapturing && !this.mRequestStop) {
                this.mSync.notifyAll();
            }
        }
    }

    protected void callOnStartEncode(Surface surface, int i, boolean z) {
        try {
            this.mListener.onStartEncode(this, surface, i, z);
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    protected void callOnError(Exception exc) {
        try {
            this.mListener.onError(exc);
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    protected void initPool() {
        this.mFrameQueue.clear();
        synchronized (this.mPool) {
            this.mPool.clear();
            for (int i = 0; i < this.MAX_POOL_SZ; i++) {
                this.mPool.add(new MediaData(this.FRAME_SZ));
            }
        }
    }

    protected void clearFrames() {
        synchronized (this.mPool) {
            this.mPool.clear();
        }
        this.mFrameQueue.clear();
        this.cnt = 0;
    }

    protected MediaData obtain(int i) {
        MediaData mediaData;
        synchronized (this.mPool) {
            if (this.mPool.isEmpty()) {
                this.cnt++;
                mediaData = new MediaData(this.FRAME_SZ);
            } else {
                MediaData mediaDataRemove = this.mPool.remove(this.mPool.size() - 1);
                mediaDataRemove.resize(i);
                mediaData = mediaDataRemove;
            }
        }
        return mediaData;
    }

    protected boolean offer(MediaData mediaData) {
        boolean zOffer = this.mFrameQueue.offer(mediaData);
        if (zOffer) {
            return zOffer;
        }
        MediaData mediaDataPoll = this.mFrameQueue.poll();
        boolean zOffer2 = this.mFrameQueue.offer(mediaData);
        if (mediaDataPoll != null) {
            recycle(mediaDataPoll);
        }
        return zOffer2;
    }

    protected MediaData waitFrame(long j) {
        try {
            return this.mFrameQueue.poll(j, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            return null;
        }
    }

    protected void recycle(MediaData mediaData) {
        synchronized (this.mPool) {
            if (this.mPool.size() < this.MAX_POOL_SZ) {
                this.mPool.add(mediaData);
            } else {
                this.cnt--;
            }
        }
    }

    protected void handleFrame(MediaData mediaData) {
        IRecorder iRecorder = this.mRecorder;
        if (iRecorder == null) {
            Log.w(TAG, "muxer is unexpectedly null");
            return;
        }
        this.mBufferInfo.set(0, mediaData.size, mediaData.presentationTimeUs, mediaData.flags);
        int i = this.mBufferInfo.flags;
        int i2 = BUFFER_FLAG_KEY_FRAME;
        boolean z = (i & i2) == i2;
        if (!this.mRecorderStarted && (z || (this.mBufferInfo.flags & 2) != 0)) {
            byte[] bArr = new byte[this.mBufferInfo.size];
            ByteBuffer byteBufferDuplicate = mediaData.mBuffer.duplicate();
            byteBufferDuplicate.clear();
            byteBufferDuplicate.get(bArr, 0, this.mBufferInfo.size);
            int iFindAnnexB = BufferHelper.findAnnexB(bArr, 0);
            int iFindAnnexB2 = BufferHelper.findAnnexB(bArr, iFindAnnexB + 2);
            try {
                if (!startRecorder(iRecorder, createOutputFormat(this.MIME_TYPE, bArr, this.mBufferInfo.size, iFindAnnexB, iFindAnnexB2, BufferHelper.findAnnexB(bArr, iFindAnnexB2 + 2)))) {
                    Log.w(TAG, "handleFrame:failed to start recorder");
                    return;
                }
            } catch (Exception unused) {
                return;
            }
        }
        if ((this.mBufferInfo.flags & 2) != 0) {
            this.mBufferInfo.size = 0;
        }
        if (this.mRecorderStarted && this.mBufferInfo.size != 0 && (z || !this.mWaitingKeyFrame)) {
            this.mWaitingKeyFrame = false;
            try {
                this.mBufferInfo.presentationTimeUs = getNextOutputPTSUs(this.mBufferInfo.presentationTimeUs);
                iRecorder.writeSampleData(this.mTrackIndex, mediaData.mBuffer, this.mBufferInfo);
            } catch (TimeoutException unused2) {
                iRecorder.stopRecording();
            } catch (Exception unused3) {
                iRecorder.stopRecording();
            }
        }
        if ((this.mBufferInfo.flags & 4) != 0) {
            stopRecorder(iRecorder);
        }
    }

    protected boolean startRecorder(IRecorder iRecorder, MediaFormat mediaFormat) {
        int iAddTrack = iRecorder.addTrack(this, mediaFormat);
        this.mTrackIndex = iAddTrack;
        if (iAddTrack >= 0) {
            this.mRecorderStarted = true;
            if (!iRecorder.start(this)) {
                Log.e(TAG, "failed to start muxer mTrackIndex=" + this.mTrackIndex);
            }
        } else {
            Log.e(TAG, "failed to addTrack: mTrackIndex=" + this.mTrackIndex);
            iRecorder.removeEncoder(this);
        }
        return iRecorder.isStarted();
    }

    protected void stopRecorder(IRecorder iRecorder) {
        if (this.mRecorder != null) {
            internalRelease();
        }
    }

    private void internalRelease() {
        this.mIsEOS = true;
        if (this.mIsCapturing) {
            this.mIsCapturing = false;
            try {
                this.mListener.onStopEncode(this);
            } catch (Exception e) {
                Log.e(TAG, "failed onStopped", e);
            }
        }
        if (this.mRecorderStarted) {
            this.mRecorderStarted = false;
            IRecorder iRecorder = this.mRecorder;
            if (iRecorder != null) {
                try {
                    iRecorder.stop(this);
                } catch (Exception e2) {
                    Log.e(TAG, "failed stopping muxer", e2);
                }
            }
        }
        try {
            this.mListener.onDestroy(this);
        } catch (Exception e3) {
            Log.e(TAG, "destroy:", e3);
        }
        this.mRecorder = null;
        clearFrames();
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

