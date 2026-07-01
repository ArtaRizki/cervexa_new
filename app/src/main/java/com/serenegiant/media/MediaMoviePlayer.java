package com.serenegiant.media;

import android.content.res.AssetFileDescriptor;
import android.media.AudioTrack;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import com.serenegiant.utils.BuildCheck;
import com.serenegiant.utils.Time;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

/* JADX INFO: loaded from: classes2.dex */
public class MediaMoviePlayer {
    private static final boolean DEBUG = false;
    private static final int REQ_NON = 0;
    private static final int REQ_PAUSE = 5;
    private static final int REQ_PREPARE = 1;
    private static final int REQ_QUIT = 9;
    private static final int REQ_RESUME = 6;
    private static final int REQ_SEEK = 3;
    private static final int REQ_START = 2;
    private static final int REQ_STOP = 4;
    private static final int STATE_PAUSED = 3;
    private static final int STATE_PLAYING = 2;
    private static final int STATE_PREPARED = 1;
    private static final int STATE_STOP = 0;
    private static final String TAG_STATIC = "MediaMoviePlayer:";
    private static final int TIMEOUT_USEC = 10000;
    private MediaCodec.BufferInfo mAudioBufferInfo;
    private int mAudioChannels;
    private final boolean mAudioEnabled;
    private int mAudioInputBufSize;
    private ByteBuffer[] mAudioInputBuffers;
    private boolean mAudioInputDone;
    private MediaCodec mAudioMediaCodec;
    protected MediaExtractor mAudioMediaExtractor;
    private byte[] mAudioOutTempBuf;
    private ByteBuffer[] mAudioOutputBuffers;
    private boolean mAudioOutputDone;
    private int mAudioSampleRate;
    private long mAudioStartTime;
    private AudioTrack mAudioTrack;
    private volatile int mAudioTrackIndex;
    private int mBitrate;
    private final IFrameCallback mCallback;
    private long mDuration;
    private float mFrameRate;
    private boolean mHasAudio;
    private volatile boolean mIsRunning;
    protected MediaMetadataRetriever mMetadata;
    private final Surface mOutputSurface;
    private int mRequest;
    private long mRequestTime;
    private int mRotation;
    private Object mSource;
    private int mState;
    private MediaCodec.BufferInfo mVideoBufferInfo;
    private int mVideoHeight;
    private ByteBuffer[] mVideoInputBuffers;
    private boolean mVideoInputDone;
    private MediaCodec mVideoMediaCodec;
    protected MediaExtractor mVideoMediaExtractor;
    private ByteBuffer[] mVideoOutputBuffers;
    private boolean mVideoOutputDone;
    private long mVideoStartTime;
    private volatile int mVideoTrackIndex;
    private int mVideoWidth;
    private final String TAG = TAG_STATIC + getClass().getSimpleName();
    private final Object mSync = new Object();
    private final Object mVideoSync = new Object();
    private long previousVideoPresentationTimeUs = -1;
    private final Object mAudioSync = new Object();
    private long previousAudioPresentationTimeUs = -1;
    private final Runnable mMoviePlayerTask = new Runnable() { // from class: com.serenegiant.media.MediaMoviePlayer.1
        @Override // java.lang.Runnable
        public final void run() {
            boolean z;
            int i;
            boolean zProcessStop = false;
            try {
                synchronized (MediaMoviePlayer.this.mSync) {
                    z = MediaMoviePlayer.this.mIsRunning = true;
                    MediaMoviePlayer.this.mState = 0;
                    MediaMoviePlayer.this.mRequest = 0;
                    MediaMoviePlayer.this.mRequestTime = -1L;
                    MediaMoviePlayer.this.mSync.notifyAll();
                }
                while (z) {
                    try {
                        synchronized (MediaMoviePlayer.this.mSync) {
                            z = MediaMoviePlayer.this.mIsRunning;
                            i = MediaMoviePlayer.this.mRequest;
                            MediaMoviePlayer.this.mRequest = 0;
                        }
                        int i2 = MediaMoviePlayer.this.mState;
                        if (i2 == 0) {
                            zProcessStop = MediaMoviePlayer.this.processStop(i);
                        } else if (i2 == 1) {
                            zProcessStop = MediaMoviePlayer.this.processPrepared(i);
                        } else if (i2 == 2) {
                            zProcessStop = MediaMoviePlayer.this.processPlaying(i);
                        } else if (i2 == 3) {
                            zProcessStop = MediaMoviePlayer.this.processPaused(i);
                        }
                        z = zProcessStop;
                    } catch (InterruptedException unused) {
                    } catch (Exception e) {
                        Log.e(MediaMoviePlayer.this.TAG, "MoviePlayerTask:", e);
                    }
                }
            } finally {
                try {
                    MediaMoviePlayer.this.handleStop();
                } catch (java.io.IOException e) {
                    Log.e(MediaMoviePlayer.this.TAG, "handleStop failed", e);
                }
            }
        }
    };
    private final Runnable mVideoTask = new Runnable() { // from class: com.serenegiant.media.MediaMoviePlayer.2
        @Override // java.lang.Runnable
        public void run() {
            while (MediaMoviePlayer.this.mIsRunning && !MediaMoviePlayer.this.mVideoInputDone && !MediaMoviePlayer.this.mVideoOutputDone) {
                try {
                    if (!MediaMoviePlayer.this.mVideoInputDone) {
                        MediaMoviePlayer.this.handleInputVideo();
                    }
                    if (!MediaMoviePlayer.this.mVideoOutputDone) {
                        MediaMoviePlayer.this.handleOutputVideo(MediaMoviePlayer.this.mCallback);
                    }
                } catch (Exception e) {
                    Log.e(MediaMoviePlayer.this.TAG, "VideoTask:", e);
                }
            }
            synchronized (MediaMoviePlayer.this.mSync) {
                MediaMoviePlayer.this.mVideoInputDone = MediaMoviePlayer.this.mVideoOutputDone = true;
                MediaMoviePlayer.this.mSync.notifyAll();
            }
        }
    };
    private final Runnable mAudioTask = new Runnable() { // from class: com.serenegiant.media.MediaMoviePlayer.3
        @Override // java.lang.Runnable
        public void run() {
            while (MediaMoviePlayer.this.mIsRunning && !MediaMoviePlayer.this.mAudioInputDone && !MediaMoviePlayer.this.mAudioOutputDone) {
                try {
                    if (!MediaMoviePlayer.this.mAudioInputDone) {
                        MediaMoviePlayer.this.handleInputAudio();
                    }
                    if (!MediaMoviePlayer.this.mAudioOutputDone) {
                        MediaMoviePlayer.this.handleOutputAudio(MediaMoviePlayer.this.mCallback);
                    }
                } catch (Exception e) {
                    Log.e(MediaMoviePlayer.this.TAG, "VideoTask:", e);
                }
            }
            synchronized (MediaMoviePlayer.this.mSync) {
                MediaMoviePlayer.this.mAudioInputDone = MediaMoviePlayer.this.mAudioOutputDone = true;
                MediaMoviePlayer.this.mSync.notifyAll();
            }
        }
    };

    private final void handlePause() {
    }

    private final void handleResume() {
    }

    protected void internal_stop_video() {
    }

    protected boolean internal_write_video(ByteBuffer byteBuffer, int i, int i2, long j) {
        return false;
    }

    public MediaMoviePlayer(Surface surface, IFrameCallback iFrameCallback, boolean z) throws NullPointerException {
        if (surface == null || iFrameCallback == null) {
            throw new NullPointerException("outputSurface and callback should not be null");
        }
        this.mOutputSurface = surface;
        this.mCallback = iFrameCallback;
        this.mAudioEnabled = z;
        new Thread(this.mMoviePlayerTask, this.TAG).start();
        synchronized (this.mSync) {
            try {
                if (!this.mIsRunning) {
                    this.mSync.wait();
                }
            } catch (InterruptedException unused) {
            }
        }
    }

    public final int getWidth() {
        return this.mVideoWidth;
    }

    public final int getHeight() {
        return this.mVideoHeight;
    }

    public final int getBitRate() {
        return this.mBitrate;
    }

    public final float getFramerate() {
        return this.mFrameRate;
    }

    public final int getRotation() {
        return this.mRotation;
    }

    public final long getDurationUs() {
        return this.mDuration;
    }

    public final int getSampleRate() {
        return this.mAudioSampleRate;
    }

    public final boolean hasAudio() {
        return this.mHasAudio;
    }

    public final void prepare(String str) {
        synchronized (this.mSync) {
            this.mSource = str;
            this.mRequest = 1;
            this.mSync.notifyAll();
        }
    }

    public final void prepare(AssetFileDescriptor assetFileDescriptor) {
        synchronized (this.mSync) {
            this.mSource = assetFileDescriptor;
            this.mRequest = 1;
            this.mSync.notifyAll();
        }
    }

    public final void play() {
        synchronized (this.mSync) {
            if (this.mState == 2) {
                return;
            }
            this.mRequest = 2;
            this.mSync.notifyAll();
        }
    }

    public final void seek(long j) {
        synchronized (this.mSync) {
            this.mRequest = 3;
            this.mRequestTime = j;
            this.mSync.notifyAll();
        }
    }

    public final void stop() {
        synchronized (this.mSync) {
            if (this.mState != 0) {
                this.mRequest = 4;
                this.mSync.notifyAll();
                try {
                    this.mSync.wait(50L);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    public final void pause() {
        synchronized (this.mSync) {
            this.mRequest = 5;
            this.mSync.notifyAll();
        }
    }

    public final void resume() {
        synchronized (this.mSync) {
            this.mRequest = 6;
            this.mSync.notifyAll();
        }
    }

    public final void release() {
        stop();
        synchronized (this.mSync) {
            this.mRequest = 9;
            this.mSync.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean processStop(int i) throws InterruptedException, IOException {
        boolean z;
        boolean z2 = true;
        if (i == 1) {
            handlePrepare(this.mSource);
        } else {
            if (i == 2 || i == 5 || i == 6) {
                throw new IllegalStateException("invalid state:" + this.mState);
            }
            if (i != 9) {
                synchronized (this.mSync) {
                    this.mSync.wait();
                }
            } else {
                z2 = false;
            }
        }
        synchronized (this.mSync) {
            z = z2 & this.mIsRunning;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0043 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean processPrepared(int r3) throws java.lang.InterruptedException, java.io.IOException {
        /*
            r2 = this;
            r0 = 2
            if (r3 == r0) goto L3c
            r0 = 9
            if (r3 == r0) goto L3a
            r0 = 4
            if (r3 == r0) goto L36
            r0 = 5
            if (r3 == r0) goto L1d
            r0 = 6
            if (r3 == r0) goto L1d
            java.lang.Object r3 = r2.mSync
            monitor-enter(r3)
            java.lang.Object r0 = r2.mSync     // Catch: java.lang.Throwable -> L1a
            r0.wait()     // Catch: java.lang.Throwable -> L1a
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L1a
            goto L3f
        L1a:
            r0 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L1a
            throw r0
        L1d:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "invalid state:"
            r0.append(r1)
            int r1 = r2.mState
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3.<init>(r0)
            throw r3
        L36:
            r2.handleStop()
            goto L3f
        L3a:
            r3 = 0
            goto L40
        L3c:
            r2.handleStart()
        L3f:
            r3 = 1
        L40:
            java.lang.Object r0 = r2.mSync
            monitor-enter(r0)
            boolean r1 = r2.mIsRunning     // Catch: java.lang.Throwable -> L48
            r3 = r3 & r1
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L48
            return r3
        L48:
            r3 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L48
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.serenegiant.media.MediaMoviePlayer.processPrepared(int):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean processPlaying(int i) throws IOException {
        boolean z;
        boolean z2;
        if (i != 9) {
            switch (i) {
                case 1:
                case 2:
                case 6:
                    throw new IllegalStateException("invalid state:" + this.mState);
                case 3:
                    handleSeek(this.mRequestTime);
                    break;
                case 4:
                    handleStop();
                    break;
                case 5:
                    handlePause();
                    break;
                default:
                    handleLoop(this.mCallback);
                    break;
            }
            z = true;
        } else {
            z = false;
        }
        synchronized (this.mSync) {
            z2 = z & this.mIsRunning;
        }
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean processPaused(int i) throws InterruptedException, IOException {
        boolean z;
        boolean z2 = true;
        if (i == 1 || i == 2) {
            throw new IllegalStateException("invalid state:" + this.mState);
        }
        if (i == 3) {
            handleSeek(this.mRequestTime);
        } else if (i == 4) {
            handleStop();
        } else if (i == 6) {
            handleResume();
        } else if (i != 9) {
            synchronized (this.mSync) {
                this.mSync.wait();
            }
        } else {
            z2 = false;
        }
        synchronized (this.mSync) {
            z = z2 & this.mIsRunning;
        }
        return z;
    }

    private final void handlePrepare(Object obj) throws IOException {
        synchronized (this.mSync) {
            if (this.mState != 0) {
                throw new RuntimeException("invalid state:" + this.mState);
            }
        }
        this.mAudioTrackIndex = -1;
        this.mVideoTrackIndex = -1;
        if (obj instanceof String) {
            String str = (String) obj;
            File file = new File(str);
            if (TextUtils.isEmpty(str) || !file.canRead()) {
                throw new FileNotFoundException("Unable to read " + obj);
            }
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            this.mMetadata = mediaMetadataRetriever;
            mediaMetadataRetriever.setDataSource(str);
        } else if (obj instanceof AssetFileDescriptor) {
            MediaMetadataRetriever mediaMetadataRetriever2 = new MediaMetadataRetriever();
            this.mMetadata = mediaMetadataRetriever2;
            mediaMetadataRetriever2.setDataSource(((AssetFileDescriptor) obj).getFileDescriptor());
        } else {
            throw new IllegalArgumentException("unknown source type:source=" + obj);
        }
        updateMovieInfo();
        this.mVideoTrackIndex = internal_prepare_video(obj);
        if (this.mAudioEnabled) {
            this.mAudioTrackIndex = internal_prepare_audio(obj);
        }
        this.mHasAudio = this.mAudioTrackIndex >= 0;
        if (this.mVideoTrackIndex < 0 && this.mAudioTrackIndex < 0) {
            throw new RuntimeException("No video and audio track found in " + obj);
        }
        synchronized (this.mSync) {
            this.mState = 1;
        }
        this.mCallback.onPrepared();
    }

    protected int internal_prepare_video(Object obj) {
        MediaExtractor mediaExtractor = new MediaExtractor();
        this.mVideoMediaExtractor = mediaExtractor;
        int iSelectTrack = -1;
        try {
            if (obj instanceof String) {
                mediaExtractor.setDataSource((String) obj);
            } else if (obj instanceof AssetFileDescriptor) {
                if (BuildCheck.isAndroid7()) {
                    this.mVideoMediaExtractor.setDataSource((AssetFileDescriptor) obj);
                } else {
                    this.mVideoMediaExtractor.setDataSource(((AssetFileDescriptor) obj).getFileDescriptor());
                }
            } else {
                throw new IllegalArgumentException("unknown source type:source=" + obj);
            }
            iSelectTrack = selectTrack(this.mVideoMediaExtractor, "video/");
            if (iSelectTrack >= 0) {
                this.mVideoMediaExtractor.selectTrack(iSelectTrack);
                MediaFormat trackFormat = this.mVideoMediaExtractor.getTrackFormat(iSelectTrack);
                this.mVideoWidth = trackFormat.getInteger("width");
                this.mVideoHeight = trackFormat.getInteger("height");
                this.mDuration = trackFormat.getLong("durationUs");
            }
        } catch (IOException unused) {
        }
        return iSelectTrack;
    }

    protected int internal_prepare_audio(Object obj) {
        MediaExtractor mediaExtractor = new MediaExtractor();
        this.mAudioMediaExtractor = mediaExtractor;
        int iSelectTrack = -1;
        try {
            if (obj instanceof String) {
                mediaExtractor.setDataSource((String) obj);
            } else if (obj instanceof AssetFileDescriptor) {
                if (BuildCheck.isAndroid7()) {
                    this.mVideoMediaExtractor.setDataSource((AssetFileDescriptor) obj);
                } else {
                    this.mVideoMediaExtractor.setDataSource(((AssetFileDescriptor) obj).getFileDescriptor());
                }
            } else {
                throw new IllegalArgumentException("unknown source type:source=" + obj);
            }
            iSelectTrack = selectTrack(this.mAudioMediaExtractor, "audio/");
            if (iSelectTrack >= 0) {
                this.mAudioMediaExtractor.selectTrack(iSelectTrack);
                MediaFormat trackFormat = this.mAudioMediaExtractor.getTrackFormat(iSelectTrack);
                this.mAudioChannels = trackFormat.getInteger("channel-count");
                int integer = trackFormat.getInteger("sample-rate");
                this.mAudioSampleRate = integer;
                int minBufferSize = AudioTrack.getMinBufferSize(integer, this.mAudioChannels == 1 ? 4 : 12, 2);
                int integer2 = trackFormat.getInteger("max-input-size");
                int i = minBufferSize > 0 ? minBufferSize * 4 : integer2;
                this.mAudioInputBufSize = i;
                if (i > integer2) {
                    this.mAudioInputBufSize = integer2;
                }
                int i2 = this.mAudioChannels * 2;
                this.mAudioInputBufSize = (this.mAudioInputBufSize / i2) * i2;
                AudioTrack audioTrack = new AudioTrack(3, this.mAudioSampleRate, this.mAudioChannels == 1 ? 4 : 12, 2, this.mAudioInputBufSize, 1);
                this.mAudioTrack = audioTrack;
                try {
                    audioTrack.play();
                } catch (Exception e) {
                    Log.e(this.TAG, "failed to start audio track playing", e);
                    this.mAudioTrack.release();
                    this.mAudioTrack = null;
                }
            }
        } catch (IOException unused) {
        }
        return iSelectTrack;
    }

    protected void updateMovieInfo() {
        this.mBitrate = 0;
        this.mRotation = 0;
        this.mVideoHeight = 0;
        this.mVideoWidth = 0;
        this.mDuration = 0L;
        this.mFrameRate = 0.0f;
        String strExtractMetadata = this.mMetadata.extractMetadata(18);
        if (!TextUtils.isEmpty(strExtractMetadata)) {
            this.mVideoWidth = Integer.parseInt(strExtractMetadata);
        }
        String strExtractMetadata2 = this.mMetadata.extractMetadata(19);
        if (!TextUtils.isEmpty(strExtractMetadata2)) {
            this.mVideoHeight = Integer.parseInt(strExtractMetadata2);
        }
        String strExtractMetadata3 = this.mMetadata.extractMetadata(24);
        if (!TextUtils.isEmpty(strExtractMetadata3)) {
            this.mRotation = Integer.parseInt(strExtractMetadata3);
        }
        String strExtractMetadata4 = this.mMetadata.extractMetadata(20);
        if (!TextUtils.isEmpty(strExtractMetadata4)) {
            this.mBitrate = Integer.parseInt(strExtractMetadata4);
        }
        String strExtractMetadata5 = this.mMetadata.extractMetadata(9);
        if (TextUtils.isEmpty(strExtractMetadata5)) {
            return;
        }
        this.mDuration = Long.parseLong(strExtractMetadata5) * 1000;
    }

    private final void handleStart() {
        Thread thread;
        synchronized (this.mSync) {
            if (this.mState != 1) {
                throw new RuntimeException("invalid state:" + this.mState);
            }
            this.mState = 2;
        }
        long j = this.mRequestTime;
        if (j > 0) {
            handleSeek(j);
        }
        this.previousAudioPresentationTimeUs = -1L;
        this.previousVideoPresentationTimeUs = -1L;
        this.mVideoOutputDone = true;
        this.mVideoInputDone = true;
        Thread thread2 = null;
        if (this.mVideoTrackIndex >= 0) {
            MediaCodec mediaCodecInternal_start_video = internal_start_video(this.mVideoMediaExtractor, this.mVideoTrackIndex);
            if (mediaCodecInternal_start_video != null) {
                this.mVideoMediaCodec = mediaCodecInternal_start_video;
                this.mVideoBufferInfo = new MediaCodec.BufferInfo();
                this.mVideoInputBuffers = mediaCodecInternal_start_video.getInputBuffers();
                this.mVideoOutputBuffers = mediaCodecInternal_start_video.getOutputBuffers();
            }
            this.mVideoOutputDone = false;
            this.mVideoInputDone = false;
            thread = new Thread(this.mVideoTask, "VideoTask");
        } else {
            thread = null;
        }
        this.mAudioOutputDone = true;
        this.mAudioInputDone = true;
        if (this.mAudioTrackIndex >= 0) {
            MediaCodec mediaCodecInternal_start_audio = internal_start_audio(this.mAudioMediaExtractor, this.mAudioTrackIndex);
            if (mediaCodecInternal_start_audio != null) {
                this.mAudioMediaCodec = mediaCodecInternal_start_audio;
                this.mAudioBufferInfo = new MediaCodec.BufferInfo();
                this.mAudioInputBuffers = mediaCodecInternal_start_audio.getInputBuffers();
                this.mAudioOutputBuffers = mediaCodecInternal_start_audio.getOutputBuffers();
            }
            this.mAudioOutputDone = false;
            this.mAudioInputDone = false;
            thread2 = new Thread(this.mAudioTask, "AudioTask");
        }
        if (thread != null) {
            thread.start();
        }
        if (thread2 != null) {
            thread2.start();
        }
    }

    protected MediaCodec internal_start_video(MediaExtractor mediaExtractor, int i) {
        MediaCodec mediaCodec = null;
        if (i < 0) {
            return null;
        }
        MediaFormat trackFormat = mediaExtractor.getTrackFormat(i);
        try {
            MediaCodec mediaCodecCreateDecoderByType = MediaCodec.createDecoderByType(trackFormat.getString(IMediaFormat.KEY_MIME));
            try {
                mediaCodecCreateDecoderByType.configure(trackFormat, this.mOutputSurface, (MediaCrypto) null, 0);
                mediaCodecCreateDecoderByType.start();
                return mediaCodecCreateDecoderByType;
            } catch (Exception e) {
                mediaCodec = mediaCodecCreateDecoderByType;
                Log.w(this.TAG, e);
                return mediaCodec;
            }
        } catch (IOException e2) {
            Log.w(this.TAG, e2);
            return mediaCodec;
        }
    }

    protected MediaCodec internal_start_audio(MediaExtractor mediaExtractor, int i) {
        MediaCodec mediaCodec = null;
        if (i < 0) {
            return null;
        }
        MediaFormat trackFormat = mediaExtractor.getTrackFormat(i);
        try {
            MediaCodec mediaCodecCreateDecoderByType = MediaCodec.createDecoderByType(trackFormat.getString(IMediaFormat.KEY_MIME));
            try {
                mediaCodecCreateDecoderByType.configure(trackFormat, (Surface) null, (MediaCrypto) null, 0);
                mediaCodecCreateDecoderByType.start();
                int iCapacity = mediaCodecCreateDecoderByType.getOutputBuffers()[0].capacity();
                if (iCapacity <= 0) {
                    iCapacity = this.mAudioInputBufSize;
                }
                this.mAudioOutTempBuf = new byte[iCapacity];
                return mediaCodecCreateDecoderByType;
            } catch (Exception e) {
                mediaCodec = mediaCodecCreateDecoderByType;
                Log.w(this.TAG, e);
                return mediaCodec;
            }
        } catch (IOException e2) {
            Log.w(this.TAG, e2);
            return mediaCodec;
        }
    }

    private final void handleSeek(long j) {
        if (j < 0) {
            return;
        }
        if (this.mVideoTrackIndex >= 0) {
            this.mVideoMediaExtractor.seekTo(j, 2);
            this.mVideoMediaExtractor.advance();
        }
        if (this.mAudioTrackIndex >= 0) {
            this.mAudioMediaExtractor.seekTo(j, 2);
            this.mAudioMediaExtractor.advance();
        }
        this.mRequestTime = -1L;
    }

    private final void handleLoop(IFrameCallback iFrameCallback) throws IOException {
        synchronized (this.mSync) {
            try {
                this.mSync.wait();
            } catch (InterruptedException unused) {
            }
        }
        if (this.mVideoInputDone && this.mVideoOutputDone && this.mAudioInputDone && this.mAudioOutputDone) {
            handleStop();
        }
    }

    protected boolean internal_process_input(MediaCodec mediaCodec, MediaExtractor mediaExtractor, ByteBuffer[] byteBufferArr, long j, boolean z) {
        int iDequeueInputBuffer;
        while (this.mIsRunning && (iDequeueInputBuffer = mediaCodec.dequeueInputBuffer(10000L)) != -1) {
            if (iDequeueInputBuffer >= 0) {
                int sampleData = mediaExtractor.readSampleData(byteBufferArr[iDequeueInputBuffer], 0);
                if (sampleData > 0) {
                    mediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, sampleData, j, 0);
                }
                return mediaExtractor.advance();
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleInputVideo() {
        if (internal_process_input(this.mVideoMediaCodec, this.mVideoMediaExtractor, this.mVideoInputBuffers, this.mVideoMediaExtractor.getSampleTime(), false)) {
            return;
        }
        while (true) {
            if (!this.mIsRunning) {
                break;
            }
            int iDequeueInputBuffer = this.mVideoMediaCodec.dequeueInputBuffer(10000L);
            if (iDequeueInputBuffer >= 0) {
                this.mVideoMediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, 0, 0L, 4);
                break;
            }
        }
        synchronized (this.mSync) {
            this.mVideoInputDone = true;
            this.mSync.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleOutputVideo(IFrameCallback iFrameCallback) {
        int iDequeueOutputBuffer;
        while (this.mIsRunning && !this.mVideoOutputDone && (iDequeueOutputBuffer = this.mVideoMediaCodec.dequeueOutputBuffer(this.mVideoBufferInfo, 10000L)) != -1) {
            if (iDequeueOutputBuffer == -3) {
                this.mVideoOutputBuffers = this.mVideoMediaCodec.getOutputBuffers();
            } else if (iDequeueOutputBuffer == -2) {
                this.mVideoMediaCodec.getOutputFormat();
            } else {
                if (iDequeueOutputBuffer < 0) {
                    throw new RuntimeException("unexpected result from video decoder.dequeueOutputBuffer: " + iDequeueOutputBuffer);
                }
                boolean z = false;
                if (this.mVideoBufferInfo.size > 0) {
                    if (this.mVideoBufferInfo.size != 0 && !internal_write_video(this.mVideoOutputBuffers[iDequeueOutputBuffer], 0, this.mVideoBufferInfo.size, this.mVideoBufferInfo.presentationTimeUs)) {
                        z = true;
                    }
                    if (z && !iFrameCallback.onFrameAvailable(this.mVideoBufferInfo.presentationTimeUs)) {
                        this.mVideoStartTime = adjustPresentationTime(this.mVideoSync, this.mVideoStartTime, this.mVideoBufferInfo.presentationTimeUs);
                    }
                }
                this.mVideoMediaCodec.releaseOutputBuffer(iDequeueOutputBuffer, z);
                if ((this.mVideoBufferInfo.flags & 4) != 0) {
                    synchronized (this.mSync) {
                        this.mVideoOutputDone = true;
                        this.mSync.notifyAll();
                    }
                } else {
                    continue;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleInputAudio() {
        if (internal_process_input(this.mAudioMediaCodec, this.mAudioMediaExtractor, this.mAudioInputBuffers, this.mAudioMediaExtractor.getSampleTime(), true)) {
            return;
        }
        while (true) {
            if (!this.mIsRunning) {
                break;
            }
            int iDequeueInputBuffer = this.mAudioMediaCodec.dequeueInputBuffer(10000L);
            if (iDequeueInputBuffer >= 0) {
                this.mAudioMediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, 0, 0L, 4);
                break;
            }
        }
        synchronized (this.mSync) {
            this.mAudioInputDone = true;
            this.mSync.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleOutputAudio(IFrameCallback iFrameCallback) {
        int iDequeueOutputBuffer;
        while (this.mIsRunning && !this.mAudioOutputDone && (iDequeueOutputBuffer = this.mAudioMediaCodec.dequeueOutputBuffer(this.mAudioBufferInfo, 10000L)) != -1) {
            if (iDequeueOutputBuffer == -3) {
                this.mAudioOutputBuffers = this.mAudioMediaCodec.getOutputBuffers();
            } else if (iDequeueOutputBuffer == -2) {
                this.mAudioMediaCodec.getOutputFormat();
            } else {
                if (iDequeueOutputBuffer < 0) {
                    throw new RuntimeException("unexpected result from audio decoder.dequeueOutputBuffer: " + iDequeueOutputBuffer);
                }
                if (this.mAudioBufferInfo.size > 0) {
                    internal_write_audio(this.mAudioOutputBuffers[iDequeueOutputBuffer], 0, this.mAudioBufferInfo.size, this.mAudioBufferInfo.presentationTimeUs);
                    if (!iFrameCallback.onFrameAvailable(this.mAudioBufferInfo.presentationTimeUs)) {
                        this.mAudioStartTime = adjustPresentationTime(this.mAudioSync, this.mAudioStartTime, this.mAudioBufferInfo.presentationTimeUs);
                    }
                }
                this.mAudioMediaCodec.releaseOutputBuffer(iDequeueOutputBuffer, false);
                if ((this.mAudioBufferInfo.flags & 4) != 0) {
                    synchronized (this.mSync) {
                        this.mAudioOutputDone = true;
                        this.mSync.notifyAll();
                    }
                } else {
                    continue;
                }
            }
        }
    }

    protected boolean internal_write_audio(ByteBuffer byteBuffer, int i, int i2, long j) {
        if (this.mAudioOutTempBuf.length < i2) {
            this.mAudioOutTempBuf = new byte[i2];
        }
        byteBuffer.position(i);
        byteBuffer.get(this.mAudioOutTempBuf, 0, i2);
        byteBuffer.clear();
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack == null) {
            return true;
        }
        audioTrack.write(this.mAudioOutTempBuf, 0, i2);
        return true;
    }

    protected long adjustPresentationTime(Object obj, long j, long j2) {
        if (j > 0) {
            long jNanoTime = Time.nanoTime();
            while (true) {
                long j3 = j2 - ((jNanoTime / 1000) - j);
                if (j3 <= 0) {
                    break;
                }
                synchronized (obj) {
                    try {
                        obj.wait(j3 / 1000, (int) ((j3 % 1000) * 1000));
                    } catch (InterruptedException unused) {
                    }
                    if (this.mState == 4 || this.mState == 9) {
                        break;
                    }
                }
                jNanoTime = Time.nanoTime();
            }
            return j;
        }
        return Time.nanoTime() / 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleStop() throws IOException {
        synchronized (this.mVideoTask) {
            internal_stop_video();
            this.mVideoTrackIndex = -1;
        }
        synchronized (this.mAudioTask) {
            internal_stop_audio();
            this.mAudioTrackIndex = -1;
        }
        MediaCodec mediaCodec = this.mVideoMediaCodec;
        if (mediaCodec != null) {
            mediaCodec.stop();
            this.mVideoMediaCodec.release();
            this.mVideoMediaCodec = null;
        }
        MediaCodec mediaCodec2 = this.mAudioMediaCodec;
        if (mediaCodec2 != null) {
            mediaCodec2.stop();
            this.mAudioMediaCodec.release();
            this.mAudioMediaCodec = null;
        }
        MediaExtractor mediaExtractor = this.mVideoMediaExtractor;
        if (mediaExtractor != null) {
            mediaExtractor.release();
            this.mVideoMediaExtractor = null;
        }
        MediaExtractor mediaExtractor2 = this.mAudioMediaExtractor;
        if (mediaExtractor2 != null) {
            mediaExtractor2.release();
            this.mAudioMediaExtractor = null;
        }
        this.mAudioBufferInfo = null;
        this.mVideoBufferInfo = null;
        this.mVideoOutputBuffers = null;
        this.mVideoInputBuffers = null;
        this.mAudioOutputBuffers = null;
        this.mAudioInputBuffers = null;
        MediaMetadataRetriever mediaMetadataRetriever = this.mMetadata;
        if (mediaMetadataRetriever != null) {
            mediaMetadataRetriever.release();
            this.mMetadata = null;
        }
        synchronized (this.mSync) {
            this.mAudioInputDone = true;
            this.mAudioOutputDone = true;
            this.mVideoInputDone = true;
            this.mVideoOutputDone = true;
            this.mState = 0;
        }
        this.mCallback.onFinished();
    }

    protected void internal_stop_audio() {
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack != null) {
            if (audioTrack.getState() != 0) {
                this.mAudioTrack.stop();
            }
            this.mAudioTrack.release();
            this.mAudioTrack = null;
        }
        this.mAudioOutTempBuf = null;
    }

    protected static final int selectTrack(MediaExtractor mediaExtractor, String str) {
        int trackCount = mediaExtractor.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            if (mediaExtractor.getTrackFormat(i).getString(IMediaFormat.KEY_MIME).startsWith(str)) {
                return i;
            }
        }
        return -1;
    }
}
