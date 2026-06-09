package com.serenegiant.media;

import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Process;
import android.util.Log;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes2.dex */
public class AudioEncoder extends AbstractEncoder implements IAudioEncoder {
    private static final String TAG = AudioEncoder.class.getSimpleName();
    protected final int mAudioSource;
    private AudioThread mAudioThread;
    protected final int mChannelCount;
    protected final int mSampleRate;

    @Override // com.serenegiant.media.Encoder
    public boolean isAudio() {
        return true;
    }

    public AudioEncoder(IRecorder iRecorder, EncoderListener encoderListener, int i, int i2) {
        super(MediaCodecHelper.MIME_AUDIO_AAC, iRecorder, encoderListener);
        this.mAudioThread = null;
        this.mAudioSource = i;
        this.mSampleRate = 44100;
        this.mChannelCount = i2;
        if (i < 0 || i > 7) {
            throw new IllegalArgumentException("invalid audio source:" + i);
        }
    }

    @Override // com.serenegiant.media.AbstractEncoder
    protected boolean internalPrepare() throws Exception {
        this.mTrackIndex = -1;
        this.mIsEOS = false;
        this.mRecorderStarted = false;
        if (MediaCodecHelper.selectAudioEncoder(this.MIME_TYPE) == null) {
            return true;
        }
        MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat(this.MIME_TYPE, this.mSampleRate, this.mChannelCount);
        mediaFormatCreateAudioFormat.setInteger("aac-profile", 2);
        mediaFormatCreateAudioFormat.setInteger("channel-mask", this.mChannelCount == 1 ? 16 : 12);
        mediaFormatCreateAudioFormat.setInteger(IjkMediaMeta.IJKM_KEY_BITRATE, AbstractAudioEncoder.DEFAULT_BIT_RATE);
        mediaFormatCreateAudioFormat.setInteger("channel-count", 1);
        this.mMediaCodec = MediaCodec.createEncoderByType(this.MIME_TYPE);
        this.mMediaCodec.configure(mediaFormatCreateAudioFormat, (Surface) null, (MediaCrypto) null, 1);
        this.mMediaCodec.start();
        return false;
    }

    @Override // com.serenegiant.media.AbstractEncoder, com.serenegiant.media.Encoder
    public void start() {
        super.start();
        if (this.mAudioThread == null) {
            AudioThread audioThread = new AudioThread();
            this.mAudioThread = audioThread;
            audioThread.start();
        }
    }

    @Override // com.serenegiant.media.AbstractEncoder, com.serenegiant.media.Encoder
    public void release() {
        this.mAudioThread = null;
        super.release();
    }

    private final class AudioThread extends Thread {
        public AudioThread() {
            super("AudioThread");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            Process.setThreadPriority(-16);
            int audioBufferSize = AudioSampler.getAudioBufferSize(AudioEncoder.this.mChannelCount, AudioEncoder.this.mSampleRate, 1024, 25);
            int i = 2;
            AudioRecord audioRecordCreateAudioRecord = IAudioSampler.createAudioRecord(AudioEncoder.this.mAudioSource, AudioEncoder.this.mSampleRate, AudioEncoder.this.mChannelCount, 2, audioBufferSize);
            ByteBuffer byteBufferOrder = ByteBuffer.allocateDirect(audioBufferSize).order(ByteOrder.nativeOrder());
            if (audioRecordCreateAudioRecord != null) {
                try {
                    if (AudioEncoder.this.mIsCapturing) {
                        try {
                            audioRecordCreateAudioRecord.startRecording();
                            try {
                                int i2 = AudioEncoder.this.mChannelCount * 1024;
                                i = 0;
                                loop1: while (true) {
                                    int i3 = 0;
                                    while (AudioEncoder.this.mIsCapturing && !AudioEncoder.this.mRequestStop && !AudioEncoder.this.mIsEOS) {
                                        try {
                                            byteBufferOrder.clear();
                                            try {
                                                int i4 = audioRecordCreateAudioRecord.read(byteBufferOrder, i2);
                                                if (i4 > 0) {
                                                    i++;
                                                    byteBufferOrder.position(i4);
                                                    byteBufferOrder.flip();
                                                    AudioEncoder.this.encode(byteBufferOrder, i4, AudioEncoder.this.getInputPTSUs());
                                                    AudioEncoder.this.frameAvailableSoon();
                                                    i3 = 0;
                                                } else {
                                                    if (i4 == 0) {
                                                        break;
                                                    }
                                                    if (i4 == -1) {
                                                        if (i3 == 0) {
                                                            Log.e(AudioEncoder.TAG, "Read error ERROR");
                                                        }
                                                    } else if (i4 == -2) {
                                                        if (i3 == 0) {
                                                            Log.e(AudioEncoder.TAG, "Read error ERROR_BAD_VALUE");
                                                        }
                                                    } else if (i4 == -3) {
                                                        if (i3 == 0) {
                                                            Log.e(AudioEncoder.TAG, "Read error ERROR_INVALID_OPERATION");
                                                        }
                                                    } else if (i4 == -6) {
                                                        if (i3 == 0) {
                                                            Log.e(AudioEncoder.TAG, "Read error ERROR_DEAD_OBJECT");
                                                        }
                                                    } else if (i4 < 0) {
                                                        if (i3 == 0) {
                                                            Log.e(AudioEncoder.TAG, "Read returned unknown err " + i4);
                                                        }
                                                    }
                                                    i3++;
                                                }
                                                if (i3 > 10) {
                                                    break loop1;
                                                }
                                            } catch (Exception unused) {
                                            }
                                        } catch (Throwable th) {
                                            th = th;
                                            audioRecordCreateAudioRecord.stop();
                                            throw th;
                                        }
                                    }
                                }
                                if (i > 0) {
                                    AudioEncoder.this.frameAvailableSoon();
                                }
                                audioRecordCreateAudioRecord.stop();
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (Exception unused2) {
                        }
                    } else {
                        i = 0;
                    }
                } finally {
                    audioRecordCreateAudioRecord.release();
                }
            } else {
                i = 0;
            }
            if (i == 0) {
                for (int i5 = 0; AudioEncoder.this.mIsCapturing && i5 < 5; i5++) {
                    byteBufferOrder.position(1024);
                    byteBufferOrder.flip();
                    AudioEncoder audioEncoder = AudioEncoder.this;
                    audioEncoder.encode(byteBufferOrder, 1024, audioEncoder.getInputPTSUs());
                    AudioEncoder.this.frameAvailableSoon();
                    synchronized (this) {
                        try {
                            wait(50L);
                        } catch (InterruptedException unused3) {
                        }
                    }
                }
            }
        }
    }

    @Override // com.serenegiant.media.AbstractEncoder
    protected MediaFormat createOutputFormat(byte[] bArr, int i, int i2, int i3, int i4) {
        MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat(this.MIME_TYPE, this.mSampleRate, this.mChannelCount);
        ByteBuffer byteBufferOrder = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
        byteBufferOrder.put(bArr, 0, i);
        byteBufferOrder.flip();
        mediaFormatCreateAudioFormat.setByteBuffer("csd-0", byteBufferOrder);
        return mediaFormatCreateAudioFormat;
    }
}
