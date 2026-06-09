package com.baidu.platform.comapi.commonutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: renamed from: com.baidu.platform.comapi.commonutils.a */
/* JADX INFO: loaded from: classes.dex */
public class C0724a {

    /* JADX INFO: renamed from: a */
    private static final boolean f799a;

    static {
        f799a = Build.VERSION.SDK_INT >= 8;
    }

    /* JADX INFO: renamed from: a */
    public static Bitmap m535a(String str, Context context) {
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            if (inputStreamOpen != null) {
                return BitmapFactory.decodeStream(inputStreamOpen);
            }
            return null;
        } catch (Exception unused) {
            return BitmapFactory.decodeFile(m538b("assets/" + str, str, context));
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m536a(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    fileOutputStream.flush();
                    try {
                        inputStream.close();
                        fileOutputStream.close();
                        return;
                    } catch (IOException unused) {
                        return;
                    }
                }
                fileOutputStream.write(bArr, 0, i);
            } catch (Throwable th) {
                try {
                    inputStream.close();
                    fileOutputStream.close();
                    throw th;
                } catch (IOException unused2) {
                    return;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0097 A[Catch: IOException -> 0x0093, TRY_LEAVE, TryCatch #2 {IOException -> 0x0093, blocks: (B:37:0x008f, B:41:0x0097), top: B:46:0x008f }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m537a(java.lang.String r6, java.lang.String r7, android.content.Context r8) throws java.lang.Throwable {
        /*
            r0 = 0
            android.content.res.AssetManager r1 = r8.getAssets()     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6c
            java.io.InputStream r1 = r1.open(r6)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6c
            if (r1 == 0) goto L57
            int r2 = r1.available()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            r1.read(r2)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            r4.<init>()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            java.io.File r5 = r8.getFilesDir()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            java.lang.String r5 = r5.getAbsolutePath()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            r4.append(r5)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            java.lang.String r5 = "/"
            r4.append(r5)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            r4.append(r7)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            boolean r4 = r3.exists()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            if (r4 == 0) goto L3e
            r3.delete()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
        L3e:
            r3.createNewFile()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L54
            r4.write(r2)     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L55
            r4.close()     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L55
            r0 = r4
            goto L57
        L4e:
            r6 = move-exception
            goto L52
        L50:
            r6 = move-exception
            r4 = r0
        L52:
            r0 = r1
            goto L8d
        L54:
            r4 = r0
        L55:
            r0 = r1
            goto L6d
        L57:
            if (r1 == 0) goto L5f
            r1.close()     // Catch: java.io.IOException -> L5d
            goto L5f
        L5d:
            r6 = move-exception
            goto L65
        L5f:
            if (r0 == 0) goto L8b
            r0.close()     // Catch: java.io.IOException -> L5d
            goto L8b
        L65:
            r6.printStackTrace()
            goto L8b
        L69:
            r6 = move-exception
            r4 = r0
            goto L8d
        L6c:
            r4 = r0
        L6d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8c
            r1.<init>()     // Catch: java.lang.Throwable -> L8c
            java.lang.String r2 = "assets/"
            r1.append(r2)     // Catch: java.lang.Throwable -> L8c
            r1.append(r6)     // Catch: java.lang.Throwable -> L8c
            java.lang.String r6 = r1.toString()     // Catch: java.lang.Throwable -> L8c
            m538b(r6, r7, r8)     // Catch: java.lang.Throwable -> L8c
            if (r0 == 0) goto L86
            r0.close()     // Catch: java.io.IOException -> L5d
        L86:
            if (r4 == 0) goto L8b
            r4.close()     // Catch: java.io.IOException -> L5d
        L8b:
            return
        L8c:
            r6 = move-exception
        L8d:
            if (r0 == 0) goto L95
            r0.close()     // Catch: java.io.IOException -> L93
            goto L95
        L93:
            r7 = move-exception
            goto L9b
        L95:
            if (r4 == 0) goto L9e
            r4.close()     // Catch: java.io.IOException -> L93
            goto L9e
        L9b:
            r7.printStackTrace()
        L9e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.commonutils.C0724a.m537a(java.lang.String, java.lang.String, android.content.Context):void");
    }

    /* JADX INFO: renamed from: b */
    private static String m538b(String str, String str2, Context context) throws Throwable {
        File file;
        File file2;
        StringBuilder sb = new StringBuilder(context.getFilesDir().getAbsolutePath());
        ZipFile zipFile = null;
        try {
            try {
                try {
                    ZipFile zipFile2 = new ZipFile(f799a ? context.getPackageCodePath() : "");
                    try {
                        int iLastIndexOf = str2.lastIndexOf("/");
                        if (iLastIndexOf > 0) {
                            file = new File(context.getFilesDir().getAbsolutePath());
                            String strSubstring = str2.substring(0, iLastIndexOf);
                            file2 = new File(file.getAbsolutePath() + "/" + strSubstring, str2.substring(iLastIndexOf + 1, str2.length()));
                        } else {
                            file = new File(context.getFilesDir(), "assets");
                            file2 = new File(file.getAbsolutePath(), str2);
                        }
                        file.mkdirs();
                        ZipEntry entry = zipFile2.getEntry(str);
                        if (entry == null) {
                            try {
                                zipFile2.close();
                            } catch (IOException unused) {
                            }
                            return null;
                        }
                        m536a(zipFile2.getInputStream(entry), new FileOutputStream(file2));
                        sb.append("/");
                        sb.append(str);
                        zipFile2.close();
                    } catch (Exception e) {
                        e = e;
                        zipFile = zipFile2;
                        Log.e(C0724a.class.getSimpleName(), "copyAssetsError", e);
                        if (zipFile != null) {
                            zipFile.close();
                        }
                        return sb.toString();
                    } catch (Throwable th) {
                        th = th;
                        zipFile = zipFile2;
                        if (zipFile != null) {
                            try {
                                zipFile.close();
                            } catch (IOException unused2) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (IOException unused3) {
        }
        return sb.toString();
    }
}
