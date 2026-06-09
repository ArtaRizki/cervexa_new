package com.serenegiant.media;

import com.serenegiant.media.IAudioSampler;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes2.dex */
public class AudioSamplerEncoder extends AbstractAudioEncoder {
    private int frame_count;
    private final Runnable mAudioTask;
    private final IAudioSampler mSampler;
    private final boolean mSamplerCreated;
    private final IAudioSampler.SoundSamplerCallback mSoundSamplerCallback;

    static /* synthetic */ int access$008(AudioSamplerEncoder audioSamplerEncoder) {
        int i = audioSamplerEncoder.frame_count;
        audioSamplerEncoder.frame_count = i + 1;
        return i;
    }

    public AudioSamplerEncoder(IRecorder iRecorder, EncoderListener encoderListener, int i, IAudioSampler iAudioSampler) {
        super(iRecorder, encoderListener, i, iAudioSampler != null ? iAudioSampler.getChannels() : 1, iAudioSampler != null ? iAudioSampler.getSamplingFrequency() : 44100, AbstractAudioEncoder.DEFAULT_BIT_RATE);
        this.frame_count = 0;
        this.mSoundSamplerCallback = new IAudioSampler.SoundSamplerCallback() { // from class: com.serenegiant.media.AudioSamplerEncoder.1
            @Override // com.serenegiant.media.IAudioSampler.SoundSamplerCallback
            public void onError(Exception exc) {
            }

            @Override // com.serenegiant.media.IAudioSampler.SoundSamplerCallback
            public void onData(ByteBuffer byteBuffer, int i2, long j) {
                synchronized (AudioSamplerEncoder.this.mSync) {
                    if (AudioSamplerEncoder.this.mIsCapturing && !AudioSamplerEncoder.this.mRequestStop && !AudioSamplerEncoder.this.mIsEOS) {
                        if (i2 > 0) {
                            AudioSamplerEncoder.this.frameAvailableSoon();
                            AudioSamplerEncoder.this.encode(byteBuffer, i2, j);
                            AudioSamplerEncoder.access$008(AudioSamplerEncoder.this);
                        }
                    }
                }
            }
        };
        this.mAudioTask = new Runnable() { // from class: com.serenegiant.media.AudioSamplerEncoder.2
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    synchronized (AudioSamplerEncoder.this.mSync) {
                        if (!AudioSamplerEncoder.this.mIsCapturing || AudioSamplerEncoder.this.mRequestStop || AudioSamplerEncoder.this.mIsEOS) {
                            break;
                        } else {
                            try {
                                AudioSamplerEncoder.this.mSync.wait();
                            } catch (InterruptedException unused) {
                            }
                        }
                    }
                }
                if (AudioSamplerEncoder.this.frame_count == 0) {
                    ByteBuffer byteBufferOrder = ByteBuffer.allocateDirect(1024).order(ByteOrder.nativeOrder());
                    for (int i2 = 0; AudioSamplerEncoder.this.mIsCapturing && i2 < 5; i2++) {
                        byteBufferOrder.clear();
                        byteBufferOrder.position(1024);
                        byteBufferOrder.flip();
                        AudioSamplerEncoder audioSamplerEncoder = AudioSamplerEncoder.this;
                        audioSamplerEncoder.encode(byteBufferOrder, 1024, audioSamplerEncoder.getInputPTSUs());
                        AudioSamplerEncoder.this.frameAvailableSoon();
                        synchronized (this) {
                            try {
                                wait(50L);
                            } catch (InterruptedException unused2) {
                            }
                        }
                    }
                }
            }
        };
        if (iAudioSampler != null) {
            this.mSamplerCreated = false;
        } else {
            if (i < 0 || i > 7) {
                throw new IllegalArgumentException("invalid audio source:" + i);
            }
            iAudioSampler = new AudioSampler(i, 1, 44100, 1024, 25);
            this.mSamplerCreated = true;
        }
        this.mSampler = iAudioSampler;
    }

    @Override // com.serenegiant.media.AbstractEncoder, com.serenegiant.media.Encoder
    public void start() {
        super.start();
        this.mSampler.addCallback(this.mSoundSamplerCallback);
        if (this.mSamplerCreated) {
            this.mSampler.start();
        }
        new Thread(this.mAudioTask, "AudioTask").start();
    }

    @Override // com.serenegiant.media.AbstractEncoder, com.serenegiant.media.Encoder
    public void stop() {
        this.mSampler.removeCallback(this.mSoundSamplerCallback);
        if (this.mSamplerCreated) {
            this.mSampler.stop();
        }
        super.stop();
    }

    @Override // com.serenegiant.media.AbstractEncoder, com.serenegiant.media.Encoder
    public void release() {
        if (this.mSamplerCreated) {
            this.mSampler.release();
        }
        super.release();
    }
}
