package com.serenegiant.media;

import android.media.AudioRecord;

/* JADX INFO: loaded from: classes2.dex */
public class AudioSampler extends IAudioSampler {
    private static final int AUDIO_FORMAT = 2;
    private static final String TAG = AudioSampler.class.getSimpleName();
    private final int AUDIO_SOURCE;
    private final int BUFFER_SIZE;
    private final int CHANNEL_COUNT;
    private final int SAMPLES_PER_FRAME;
    private final int SAMPLING_RATE;
    private AudioThread mAudioThread;

    @Override // com.serenegiant.media.IAudioSampler
    public int getBitResolution() {
        return 16;
    }

    public AudioSampler(int i, int i2, int i3, int i4, int i5) {
        this.AUDIO_SOURCE = i;
        this.CHANNEL_COUNT = i2;
        this.SAMPLING_RATE = i3;
        this.SAMPLES_PER_FRAME = i4 * i2;
        this.BUFFER_SIZE = getAudioBufferSize(i2, i3, i4, i5);
    }

    @Override // com.serenegiant.media.IAudioSampler
    public int getBufferSize() {
        return this.SAMPLES_PER_FRAME;
    }

    @Override // com.serenegiant.media.IAudioSampler
    public synchronized void start() {
        super.start();
        if (this.mAudioThread == null) {
            init_pool(this.SAMPLES_PER_FRAME);
            AudioThread audioThread = new AudioThread();
            this.mAudioThread = audioThread;
            audioThread.start();
        }
    }

    @Override // com.serenegiant.media.IAudioSampler
    public synchronized void stop() {
        this.mIsCapturing = false;
        this.mAudioThread = null;
        super.stop();
    }

    @Override // com.serenegiant.media.IAudioSampler
    public int getAudioSource() {
        return this.AUDIO_SOURCE;
    }

    protected static final class AudioRecordRec {
        AudioRecord audioRecord;
        int bufferSize;

        protected AudioRecordRec() {
        }
    }

    public static int getAudioBufferSize(int i, int i2, int i3, int i4) {
        int minBufferSize = AudioRecord.getMinBufferSize(i2, i == 1 ? 16 : 12, 2);
        int i5 = i4 * i3;
        return i5 < minBufferSize ? ((minBufferSize / i3) + 1) * i3 * 2 * i : i5;
    }

    private final class AudioThread extends Thread {
        public AudioThread() {
            super("AudioThread");
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x008c, code lost:
        
            r8.presentationTimeUs = r13.this$0.getInputPTSUs();
            r8.size = r10;
            r9.position(r10);
            r9.flip();
            r13.this$0.addMediaData(r8);
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                Method dump skipped, instruction units count: 399
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            return;
        }
    }

    @Override // com.serenegiant.media.IAudioSampler
    public int getChannels() {
        return this.CHANNEL_COUNT;
    }

    @Override // com.serenegiant.media.IAudioSampler
    public int getSamplingFrequency() {
        return this.SAMPLING_RATE;
    }
}

