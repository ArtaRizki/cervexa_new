package com.jieli.lib.p015dv.control.projection.tools;

import com.jieli.lib.p015dv.control.projection.beans.StreamData;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class Utils {
    public static boolean byte2File(byte[] bArr, String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            File file = new File(str);
            if (!file.exists() && file.mkdirs()) {
                System.out.printf("create dir ok!", new Object[0]);
            }
            fileOutputStream = new FileOutputStream(new File(str + File.separator + str2));
            try {
                try {
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream);
                    try {
                        bufferedOutputStream2.write(bArr);
                        try {
                            bufferedOutputStream2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            fileOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        return true;
                    } catch (Exception e3) {
                        e = e3;
                        bufferedOutputStream = bufferedOutputStream2;
                        e.printStackTrace();
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream = bufferedOutputStream2;
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                                throw th;
                            } catch (IOException e7) {
                                e7.printStackTrace();
                                throw th;
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e8) {
                e = e8;
            }
        } catch (Exception e9) {
            e = e9;
            fileOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0043 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] file2Bytes(java.lang.String r5) throws java.lang.Throwable {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r5)
            boolean r5 = r0.exists()
            r1 = 0
            if (r5 == 0) goto L4c
            long r2 = r0.length()
            int r5 = (int) r2
            byte[] r2 = new byte[r5]
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L2f
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L2f
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L2f
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L2f
            r0 = 0
            r3.read(r2, r0, r5)     // Catch: java.io.IOException -> L2b java.lang.Throwable -> L3f
            r3.close()     // Catch: java.io.IOException -> L25
            goto L29
        L25:
            r5 = move-exception
            r5.printStackTrace()
        L29:
            r1 = r2
            goto L3e
        L2b:
            r5 = move-exception
            goto L31
        L2d:
            r5 = move-exception
            goto L41
        L2f:
            r5 = move-exception
            r3 = r1
        L31:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L3f
            if (r3 == 0) goto L3e
            r3.close()     // Catch: java.io.IOException -> L3a
            goto L3e
        L3a:
            r5 = move-exception
            r5.printStackTrace()
        L3e:
            return r1
        L3f:
            r5 = move-exception
            r1 = r3
        L41:
            if (r1 == 0) goto L4b
            r1.close()     // Catch: java.io.IOException -> L47
            goto L4b
        L47:
            r0 = move-exception
            r0.printStackTrace()
        L4b:
            throw r5
        L4c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.lib.p015dv.control.projection.tools.Utils.file2Bytes(java.lang.String):byte[]");
    }

    public static byte[] mergeDataPacket(StreamData streamData) {
        byte[] payload;
        int payloadLen;
        if (streamData == null || (payload = streamData.getPayload()) == null || (payloadLen = streamData.getPayloadLen()) < 0) {
            return null;
        }
        byte[] bArr = new byte[payloadLen + 20];
        byte[] bArrContructIntTo2B = FormatHex.contructIntTo2B(payloadLen);
        byte[] bArrIntToByteArray = FormatHex.intToByteArray(streamData.getSeq());
        byte[] bArrIntToByteArray2 = FormatHex.intToByteArray(streamData.getFrameSize());
        byte[] bArrIntToByteArray3 = FormatHex.intToByteArray(streamData.getOffset());
        byte[] bArrContructIntTo1B = FormatHex.contructIntTo1B(streamData.getType());
        byte[] bArrContructIntTo1B2 = FormatHex.contructIntTo1B(streamData.getSave());
        byte[] bArrIntToByteArray4 = FormatHex.intToByteArray(streamData.getDateFlag());
        System.arraycopy(bArrContructIntTo1B, 0, bArr, 0, 1);
        System.arraycopy(bArrContructIntTo1B2, 0, bArr, 1, 1);
        System.arraycopy(bArrContructIntTo2B, 0, bArr, 2, 2);
        System.arraycopy(bArrIntToByteArray, 0, bArr, 4, 4);
        System.arraycopy(bArrIntToByteArray2, 0, bArr, 8, 4);
        System.arraycopy(bArrIntToByteArray3, 0, bArr, 12, 4);
        System.arraycopy(bArrIntToByteArray4, 0, bArr, 16, 4);
        System.arraycopy(payload, 0, bArr, 20, payloadLen);
        return bArr;
    }
}
