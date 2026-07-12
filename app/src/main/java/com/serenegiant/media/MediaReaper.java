package com.serenegiant.media;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MediaReaper implements Runnable {
    private static final boolean DEBUG = false;
    public static final int REAPER_AUDIO = 1;
    public static final int REAPER_VIDEO = 0;
    private static final String TAG = MediaReaper.class.getSimpleName();
    public static final int TIMEOUT_USEC = 10000;
    private volatile boolean mIsEOS;
    private volatile boolean mIsRunning;
    private final ReaperListener mListener;
    private final int mReaperType;
    private volatile boolean mRecorderStarted;
    private int mRequestDrain;
    private boolean mRequestStop;
    private final WeakReference<MediaCodec> mWeakEncoder;
    private final Object mSync = new Object();
    private long prevOutputPTSUs = -1;
    private MediaCodec.BufferInfo mBufferInfo = new MediaCodec.BufferInfo();

    public interface ReaperListener {
        void onError(MediaReaper mediaReaper, Exception exc);

        void onOutputFormatChanged(MediaReaper mediaReaper, MediaFormat mediaFormat);

        void onStop(MediaReaper mediaReaper);

        void writeSampleData(MediaReaper mediaReaper, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo);
    }

    protected abstract MediaFormat createOutputFormat(byte[] bArr, int i, int i2, int i3, int i4);

    public static class VideoReaper extends MediaReaper {
        public static final String MIME_AVC = "video/avc";
        private final int mHeight;
        private final int mWidth;

        public VideoReaper(MediaCodec mediaCodec, ReaperListener reaperListener, int i, int i2) {
            super(0, mediaCodec, reaperListener);
            this.mWidth = i;
            this.mHeight = i2;
        }

        @Override // com.serenegiant.media.MediaReaper
        protected MediaFormat createOutputFormat(byte[] bArr, int i, int i2, int i3, int i4) {
            if (i2 >= 0) {
                MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat("video/avc", this.mWidth, this.mHeight);
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
    }

    public static class AudioReaper extends MediaReaper {
        private static final String MIME_TYPE = "audio/mp4a-latm";
        private final int mChannelCount;
        private final int mSampleRate;

        public AudioReaper(MediaCodec mediaCodec, ReaperListener reaperListener, int i, int i2) {
            super(1, mediaCodec, reaperListener);
            this.mSampleRate = i;
            this.mChannelCount = i2;
        }

        @Override // com.serenegiant.media.MediaReaper
        protected MediaFormat createOutputFormat(byte[] bArr, int i, int i2, int i3, int i4) {
            MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", this.mSampleRate, this.mChannelCount);
            ByteBuffer byteBufferOrder = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
            byteBufferOrder.put(bArr, 0, i);
            byteBufferOrder.flip();
            mediaFormatCreateAudioFormat.setByteBuffer("csd-0", byteBufferOrder);
            return mediaFormatCreateAudioFormat;
        }
    }

    public MediaReaper(int i, MediaCodec mediaCodec, ReaperListener reaperListener) {
        this.mWeakEncoder = new WeakReference<>(mediaCodec);
        this.mListener = reaperListener;
        this.mReaperType = i;
        synchronized (this.mSync) {
            new Thread(this, getClass().getSimpleName()).start();
            try {
                this.mSync.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public void release() {
        if (this.mIsRunning && !this.mRequestStop) {
            this.mRequestStop = true;
        }
        synchronized (this.mSync) {
            this.mSync.notifyAll();
        }
    }

    public void frameAvailableSoon() {
        synchronized (this.mSync) {
            if (this.mIsRunning && !this.mRequestStop) {
                this.mRequestDrain++;
                this.mSync.notifyAll();
            }
        }
    }

    public int reaperType() {
        return this.mReaperType;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x005e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r6 = this;
            r0 = -4
            android.os.Process.setThreadPriority(r0)
            java.lang.Object r0 = r6.mSync
            monitor-enter(r0)
            r1 = 1
            r6.mIsRunning = r1     // Catch: java.lang.Throwable -> L67
            r2 = 0
            r6.mRequestStop = r2     // Catch: java.lang.Throwable -> L67
            r6.mRequestDrain = r2     // Catch: java.lang.Throwable -> L67
            java.lang.Object r3 = r6.mSync     // Catch: java.lang.Throwable -> L67
            r3.notify()     // Catch: java.lang.Throwable -> L67
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L67
        L15:
            boolean r0 = r6.mIsRunning
            if (r0 == 0) goto L5b
            java.lang.Object r0 = r6.mSync
            monitor-enter(r0)
            boolean r3 = r6.mRequestStop     // Catch: java.lang.Throwable -> L58
            int r4 = r6.mRequestDrain     // Catch: java.lang.Throwable -> L58
            if (r4 <= 0) goto L24
            r4 = 1
            goto L25
        L24:
            r4 = 0
        L25:
            if (r4 == 0) goto L2c
            int r5 = r6.mRequestDrain     // Catch: java.lang.Throwable -> L58
            int r5 = r5 - r1
            r6.mRequestDrain = r5     // Catch: java.lang.Throwable -> L58
        L2c:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L58
            if (r3 == 0) goto L3a
            r6.drain()     // Catch: java.lang.Exception -> L38 java.lang.IllegalStateException -> L5b
            r6.mIsEOS = r1     // Catch: java.lang.Exception -> L38 java.lang.IllegalStateException -> L5b
            r6.release()     // Catch: java.lang.Exception -> L38 java.lang.IllegalStateException -> L5b
            goto L5b
        L38:
            r0 = move-exception
            goto L52
        L3a:
            if (r4 == 0) goto L40
            r6.drain()     // Catch: java.lang.Exception -> L38 java.lang.IllegalStateException -> L5b
            goto L15
        L40:
            java.lang.Object r0 = r6.mSync     // Catch: java.lang.Exception -> L38 java.lang.IllegalStateException -> L5b
            monitor-enter(r0)     // Catch: java.lang.Exception -> L38 java.lang.IllegalStateException -> L5b
            java.lang.Object r3 = r6.mSync     // Catch: java.lang.Throwable -> L4c java.lang.InterruptedException -> L4e
            r4 = 50
            r3.wait(r4)     // Catch: java.lang.Throwable -> L4c java.lang.InterruptedException -> L4e
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4c
            goto L15
        L4c:
            r3 = move-exception
            goto L50
        L4e:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4c
            goto L5b
        L50:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4c
            throw r3     // Catch: java.lang.Exception -> L38 java.lang.IllegalStateException -> L5b
        L52:
            java.lang.String r3 = com.serenegiant.media.MediaReaper.TAG
            android.util.Log.w(r3, r0)
            goto L15
        L58:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L58
            throw r1
        L5b:
            java.lang.Object r3 = r6.mSync
            monitor-enter(r3)
            r6.mRequestStop = r1     // Catch: java.lang.Throwable -> L64
            r6.mIsRunning = r2     // Catch: java.lang.Throwable -> L64
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L64
            return
        L64:
            r0 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L64
            throw r0
        L67:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L67
            throw r1
        */
        return;
    }

    private final void drain() {
        MediaCodec mediaCodec = this.mWeakEncoder.get();
        if (mediaCodec == null) {
            return;
        }
        try {
            ByteBuffer[] outputBuffers = mediaCodec.getOutputBuffers();
            int i = 0;
            while (this.mIsRunning) {
                int iDequeueOutputBuffer = mediaCodec.dequeueOutputBuffer(this.mBufferInfo, 10000L);
                if (iDequeueOutputBuffer == -1) {
                    if (!this.mIsEOS && (i = i + 1) > 5) {
                        return;
                    }
                } else if (iDequeueOutputBuffer == -3) {
                    outputBuffers = mediaCodec.getOutputBuffers();
                } else if (iDequeueOutputBuffer == -2) {
                    if (this.mRecorderStarted) {
                        throw new RuntimeException("format changed twice");
                    }
                    if (!callOnFormatChanged(mediaCodec.getOutputFormat())) {
                        return;
                    }
                } else if (iDequeueOutputBuffer < 0) {
                    continue;
                } else {
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
                            int iFindStartMarker = MediaCodecHelper.findStartMarker(bArr, 0);
                            int iFindStartMarker2 = MediaCodecHelper.findStartMarker(bArr, iFindStartMarker + 2);
                            if (!callOnFormatChanged(createOutputFormat(bArr, this.mBufferInfo.size, iFindStartMarker, iFindStartMarker2, MediaCodecHelper.findStartMarker(bArr, iFindStartMarker2 + 2)))) {
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
                            this.mListener.writeSampleData(this, byteBuffer, this.mBufferInfo);
                        } catch (TimeoutException e) {
                            callOnError(e);
                        } catch (Exception e2) {
                            callOnError(e2);
                        }
                        i = 0;
                    }
                    mediaCodec.releaseOutputBuffer(iDequeueOutputBuffer, false);
                    if ((this.mBufferInfo.flags & 4) != 0) {
                        callOnStop();
                        return;
                    }
                }
            }
        } catch (IllegalStateException unused) {
        }
    }

    private boolean callOnFormatChanged(MediaFormat mediaFormat) {
        try {
            this.mListener.onOutputFormatChanged(this, mediaFormat);
            this.mRecorderStarted = true;
            return true;
        } catch (Exception e) {
            callOnError(e);
            return false;
        }
    }

    private void callOnStop() {
        try {
            this.mListener.onStop(this);
        } catch (Exception e) {
            callOnError(e);
        }
    }

    private void callOnError(Exception exc) {
        try {
            this.mListener.onError(this, exc);
        } catch (Exception e) {
            Log.w(TAG, e);
        }
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

