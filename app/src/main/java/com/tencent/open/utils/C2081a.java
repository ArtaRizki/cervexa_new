package com.tencent.open.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Properties;
import java.util.zip.ZipException;

/* JADX INFO: renamed from: com.tencent.open.utils.a */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public final class C2081a {

    /* JADX INFO: renamed from: a */
    private static final C2090j f3258a = new C2090j(101010256);

    /* JADX INFO: renamed from: b */
    private static final C2091k f3259b = new C2091k(38651);

    /* JADX INFO: renamed from: com.tencent.open.utils.a$a */
    /* JADX INFO: compiled from: ProGuard */
    private static class a {

        /* JADX INFO: renamed from: a */
        Properties f3260a;

        /* JADX INFO: renamed from: b */
        byte[] f3261b;

        private a() {
            this.f3260a = new Properties();
        }

        /* JADX INFO: renamed from: a */
        void m2206a(byte[] bArr) throws IOException {
            if (bArr == null) {
                return;
            }
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            int length = C2081a.f3259b.m2283a().length;
            byte[] bArr2 = new byte[length];
            byteBufferWrap.get(bArr2);
            if (!C2081a.f3259b.equals(new C2091k(bArr2))) {
                throw new ProtocolException("unknow protocl [" + Arrays.toString(bArr) + "]");
            }
            if (bArr.length - length <= 2) {
                return;
            }
            byte[] bArr3 = new byte[2];
            byteBufferWrap.get(bArr3);
            int iM2284b = new C2091k(bArr3).m2284b();
            if ((bArr.length - length) - 2 < iM2284b) {
                return;
            }
            byte[] bArr4 = new byte[iM2284b];
            byteBufferWrap.get(bArr4);
            this.f3260a.load(new ByteArrayInputStream(bArr4));
            int length2 = ((bArr.length - length) - iM2284b) - 2;
            if (length2 > 0) {
                byte[] bArr5 = new byte[length2];
                this.f3261b = bArr5;
                byteBufferWrap.get(bArr5);
            }
        }

        public String toString() {
            return "ApkExternalInfo [p=" + this.f3260a + ", otherData=" + Arrays.toString(this.f3261b) + "]";
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m2204a(File file, String str) throws Throwable {
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2 = null;
        byte b = 0;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArrM2205a = m2205a(randomAccessFile);
            if (bArrM2205a != null) {
                a aVar = new a();
                aVar.m2206a(bArrM2205a);
                String property = aVar.f3260a.getProperty(str);
                randomAccessFile.close();
                return property;
            }
            randomAccessFile.close();
            return null;
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile2 = randomAccessFile;
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
            throw th;
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m2203a(File file) throws IOException {
        return m2204a(file, "channelNo");
    }

    /* JADX INFO: renamed from: a */
    private static byte[] m2205a(RandomAccessFile randomAccessFile) throws IOException {
        boolean z;
        long length = randomAccessFile.length() - 22;
        randomAccessFile.seek(length);
        byte[] bArrM2281a = f3258a.m2281a();
        int i = randomAccessFile.read();
        while (true) {
            z = true;
            if (i == -1) {
                z = false;
                break;
            }
            if (i == bArrM2281a[0] && randomAccessFile.read() == bArrM2281a[1] && randomAccessFile.read() == bArrM2281a[2] && randomAccessFile.read() == bArrM2281a[3]) {
                break;
            }
            length--;
            randomAccessFile.seek(length);
            i = randomAccessFile.read();
        }
        if (!z) {
            throw new ZipException("archive is not a ZIP archive");
        }
        randomAccessFile.seek(length + 16 + 4);
        byte[] bArr = new byte[2];
        randomAccessFile.readFully(bArr);
        int iM2284b = new C2091k(bArr).m2284b();
        if (iM2284b == 0) {
            return null;
        }
        byte[] bArr2 = new byte[iM2284b];
        randomAccessFile.read(bArr2);
        return bArr2;
    }
}
