package com.serenegiant.p020io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class ReadableInputStreamWrapper extends InputStream implements IReadable {
    private final InputStream mParent;

    public ReadableInputStreamWrapper(InputStream inputStream) {
        if (inputStream == null) {
            throw null;
        }
        this.mParent = inputStream;
    }

    @Override // com.serenegiant.p020io.IReadable
    public int read(ByteBuffer byteBuffer) throws IOException {
        byte[] bArr = new byte[byteBuffer.remaining()];
        int i = this.mParent.read(bArr);
        byteBuffer.put(bArr, 0, i);
        return i;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.mParent.read();
    }

    @Override // java.io.InputStream, com.serenegiant.p020io.IReadable
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.mParent.read(bArr, i, i2);
    }

    @Override // java.io.InputStream, com.serenegiant.p020io.IReadable
    public long skip(long j) throws IOException {
        return this.mParent.skip(j);
    }

    @Override // java.io.InputStream, com.serenegiant.p020io.IReadable
    public int available() throws IOException {
        return this.mParent.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mParent.close();
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        this.mParent.mark(i);
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.mParent.reset();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.mParent.markSupported();
    }
}
