package com.baidu.p002a.p003a.p004a.p005a.p007b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.system.ErrnoException;
import android.system.Os;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.p002a.p003a.p004a.p005a.p006a.C0630a;
import com.baidu.p002a.p003a.p004a.p005a.p006a.C0631b;
import com.baidu.p002a.p003a.p004a.p005a.p006a.C0632c;
import com.baidu.p002a.p003a.p004a.p005a.p006a.C0633d;
import com.jieli.lib.p015dv.control.utils.TopicKey;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import javax.crypto.Cipher;
import kotlin.UByte;
import org.apache.commons.net.nntp.NNTPReply;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.a.a.a.a.b.c */
/* JADX INFO: loaded from: classes.dex */
public final class C0636c {

    /* JADX INFO: renamed from: a */
    private static final String f84a = new String(C0631b.m24a(new byte[]{77, 122, 65, 121, 77, 84, 73, 120, 77, 68, 73, 61})) + new String(C0631b.m24a(new byte[]{90, 71, 108, 106, 100, 87, 82, 112, 89, 87, 73, 61}));

    /* JADX INFO: renamed from: e */
    private static b f85e;

    /* JADX INFO: renamed from: b */
    private final Context f86b;

    /* JADX INFO: renamed from: c */
    private int f87c = 0;

    /* JADX INFO: renamed from: d */
    private PublicKey f88d;

    /* JADX INFO: renamed from: com.baidu.a.a.a.a.b.c$a */
    static class a {

        /* JADX INFO: renamed from: a */
        public ApplicationInfo f89a;

        /* JADX INFO: renamed from: b */
        public int f90b;

        /* JADX INFO: renamed from: c */
        public boolean f91c;

        /* JADX INFO: renamed from: d */
        public boolean f92d;

        private a() {
            this.f90b = 0;
            this.f91c = false;
            this.f92d = false;
        }

        /* synthetic */ a(C0637d c0637d) {
            this();
        }
    }

    /* JADX INFO: renamed from: com.baidu.a.a.a.a.b.c$b */
    static class b {

        /* JADX INFO: renamed from: a */
        public String f93a;

        /* JADX INFO: renamed from: b */
        public String f94b;

        /* JADX INFO: renamed from: c */
        public int f95c;

        private b() {
            this.f95c = 2;
        }

        /* synthetic */ b(C0637d c0637d) {
            this();
        }

        /* JADX INFO: renamed from: a */
        public static b m62a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("deviceid");
                String string2 = jSONObject.getString("imei");
                int i = jSONObject.getInt(TopicKey.VERSION);
                if (!TextUtils.isEmpty(string) && string2 != null) {
                    b bVar = new b();
                    bVar.f93a = string;
                    bVar.f94b = string2;
                    bVar.f95c = i;
                    return bVar;
                }
            } catch (JSONException e) {
                C0636c.m38a(e);
            }
            return null;
        }

        /* JADX INFO: renamed from: a */
        public String m63a() {
            try {
                return new JSONObject().put("deviceid", this.f93a).put("imei", this.f94b).put(TopicKey.VERSION, this.f95c).toString();
            } catch (JSONException e) {
                C0636c.m38a(e);
                return null;
            }
        }

        /* JADX INFO: renamed from: b */
        public String m64b() {
            String str = this.f94b;
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            return this.f93a + "|" + new StringBuffer(str).reverse().toString();
        }
    }

    /* JADX INFO: renamed from: com.baidu.a.a.a.a.b.c$c */
    static class c {
        /* JADX INFO: renamed from: a */
        static boolean m65a(String str, int i) {
            try {
                Os.chmod(str, i);
                return true;
            } catch (ErrnoException e) {
                C0636c.m38a(e);
                return false;
            }
        }
    }

    private C0636c(Context context) throws Throwable {
        this.f86b = context.getApplicationContext();
        m37a();
    }

    /* JADX INFO: renamed from: a */
    public static String m33a(Context context) {
        return m56e(context).m64b();
    }

    /* JADX INFO: renamed from: a */
    private static String m34a(File file) throws Throwable {
        FileReader fileReader;
        char[] cArr;
        CharArrayWriter charArrayWriter;
        FileReader fileReader2 = null;
        try {
            fileReader = new FileReader(file);
        } catch (Exception e) {
            e = e;
            fileReader = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            try {
                cArr = new char[8192];
                charArrayWriter = new CharArrayWriter();
            } catch (Throwable th2) {
                th = th2;
                fileReader2 = fileReader;
            }
            while (true) {
                int i = fileReader.read(cArr);
                if (i <= 0) {
                    break;
                }
                charArrayWriter.write(cArr, 0, i);
                th = th2;
                fileReader2 = fileReader;
                if (fileReader2 != null) {
                    try {
                        fileReader2.close();
                    } catch (Exception e2) {
                        m48b(e2);
                    }
                }
                throw th;
            }
            String string = charArrayWriter.toString();
            try {
                fileReader.close();
            } catch (Exception e3) {
                m48b(e3);
            }
            return string;
        } catch (Exception e4) {
            e = e4;
            m48b(e);
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e5) {
                    m48b(e5);
                }
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static String m35a(byte[] bArr) {
        StringBuilder sb;
        if (bArr == null) {
            throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
        }
        String string = "";
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                sb = new StringBuilder();
                sb.append(string);
                string = "0";
            } else {
                sb = new StringBuilder();
            }
            sb.append(string);
            sb.append(hexString);
            string = sb.toString();
        }
        return string.toLowerCase();
    }

    /* JADX INFO: renamed from: a */
    private List m36a(Intent intent, boolean z) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = this.f86b.getPackageManager();
        List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (listQueryBroadcastReceivers != null) {
            for (ResolveInfo resolveInfo : listQueryBroadcastReceivers) {
                if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.applicationInfo != null) {
                    try {
                        Bundle bundle = packageManager.getReceiverInfo(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name), 128).metaData;
                        if (bundle != null) {
                            String string = bundle.getString("galaxy_data");
                            if (!TextUtils.isEmpty(string)) {
                                byte[] bArrM24a = C0631b.m24a(string.getBytes("utf-8"));
                                JSONObject jSONObject = new JSONObject(new String(bArrM24a));
                                a aVar = new a(null);
                                aVar.f90b = jSONObject.getInt("priority");
                                aVar.f89a = resolveInfo.activityInfo.applicationInfo;
                                if (this.f86b.getPackageName().equals(resolveInfo.activityInfo.applicationInfo.packageName)) {
                                    aVar.f92d = true;
                                }
                                if (z) {
                                    String string2 = bundle.getString("galaxy_sf");
                                    if (!TextUtils.isEmpty(string2)) {
                                        PackageInfo packageInfo = packageManager.getPackageInfo(resolveInfo.activityInfo.applicationInfo.packageName, 64);
                                        JSONArray jSONArray = jSONObject.getJSONArray("sigs");
                                        int length = jSONArray.length();
                                        String[] strArr = new String[length];
                                        for (int i = 0; i < length; i++) {
                                            strArr[i] = jSONArray.getString(i);
                                        }
                                        if (m41a(strArr, m43a(packageInfo.signatures))) {
                                            byte[] bArrM42a = m42a(C0631b.m24a(string2.getBytes()), this.f88d);
                                            if (bArrM42a != null && Arrays.equals(bArrM42a, C0633d.m28a(bArrM24a))) {
                                                aVar.f91c = true;
                                            }
                                        }
                                    }
                                }
                                arrayList.add(aVar);
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        Collections.sort(arrayList, new C0637d(this));
        return arrayList;
    }

    /* JADX INFO: renamed from: a */
    private void m37a() throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(C0635b.m32a());
        } catch (Exception unused) {
        } catch (Throwable th2) {
            byteArrayInputStream = null;
            th = th2;
        }
        try {
            this.f88d = CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream).getPublicKey();
            try {
                byteArrayInputStream.close();
            } catch (Exception e) {
                m48b(e);
            }
        } catch (Exception unused2) {
            byteArrayInputStream2 = byteArrayInputStream;
            if (byteArrayInputStream2 != null) {
                try {
                    byteArrayInputStream2.close();
                } catch (Exception e2) {
                    m48b(e2);
                }
            }
        } catch (Throwable th3) {
            th = th3;
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (Exception e3) {
                    m48b(e3);
                }
            }
            throw th;
        }
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m38a(Throwable th) {
    }

    /* JADX INFO: renamed from: a */
    private boolean m39a(String str) {
        int i = Build.VERSION.SDK_INT >= 24 ? 0 : 1;
        FileOutputStream fileOutputStreamOpenFileOutput = null;
        try {
            fileOutputStreamOpenFileOutput = this.f86b.openFileOutput("libcuid.so", i);
            fileOutputStreamOpenFileOutput.write(str.getBytes());
            fileOutputStreamOpenFileOutput.flush();
            if (fileOutputStreamOpenFileOutput != null) {
                try {
                    fileOutputStreamOpenFileOutput.close();
                } catch (Exception unused) {
                }
            }
            if (i == 0) {
                return c.m65a(new File(this.f86b.getFilesDir(), "libcuid.so").getAbsolutePath(), NNTPReply.TRANSFER_FAILED);
            }
            return true;
        } catch (Exception unused2) {
            if (fileOutputStreamOpenFileOutput != null) {
                try {
                    fileOutputStreamOpenFileOutput.close();
                } catch (Exception unused3) {
                }
            }
            return false;
        } catch (Throwable th) {
            if (fileOutputStreamOpenFileOutput != null) {
                try {
                    fileOutputStreamOpenFileOutput.close();
                } catch (Exception unused4) {
                }
            }
            throw th;
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m40a(String str, String str2) {
        try {
            return Settings.System.putString(this.f86b.getContentResolver(), str, str2);
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m41a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            hashSet.add(str);
        }
        HashSet hashSet2 = new HashSet();
        for (String str2 : strArr2) {
            hashSet2.add(str2);
        }
        return hashSet.equals(hashSet2);
    }

    /* JADX INFO: renamed from: a */
    private static byte[] m42a(byte[] bArr, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, publicKey);
        return cipher.doFinal(bArr);
    }

    /* JADX INFO: renamed from: a */
    private String[] m43a(Signature[] signatureArr) {
        int length = signatureArr.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = m35a(C0633d.m28a(signatureArr[i].toByteArray()));
        }
        return strArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01b8  */
    /* JADX WARN: Type inference failed for: r11v0, types: [com.baidu.a.a.a.a.b.c] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.baidu.a.a.a.a.b.d] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX INFO: renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.baidu.p002a.p003a.p004a.p005a.p007b.C0636c.b m44b() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 577
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p002a.p003a.p004a.p005a.p007b.C0636c.m44b():com.baidu.a.a.a.a.b.c$b");
    }

    /* JADX INFO: renamed from: b */
    public static String m45b(Context context) {
        return m56e(context).f93a;
    }

    /* JADX INFO: renamed from: b */
    private String m46b(String str) {
        try {
            return Settings.System.getString(this.f86b.getContentResolver(), str);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m47b(String str, String str2) {
        File file;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file2 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
        File file3 = new File(file2, ".cuid");
        try {
            if (file2.exists() && !file2.isDirectory()) {
                Random random = new Random();
                File parentFile = file2.getParentFile();
                String name = file2.getName();
                do {
                    file = new File(parentFile, name + random.nextInt() + ".tmp");
                } while (file.exists());
                file2.renameTo(file);
                file.delete();
            }
            file2.mkdirs();
            FileWriter fileWriter = new FileWriter(file3, false);
            fileWriter.write(C0631b.m23a(C0630a.m21a(f84a, f84a, (str + "=" + str2).getBytes()), "utf-8"));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException | Exception unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m48b(Throwable th) {
    }

    /* JADX INFO: renamed from: c */
    public static String m49c(Context context) {
        return m56e(context).f94b;
    }

    /* JADX INFO: renamed from: c */
    private boolean m50c() {
        return m51c("android.permission.WRITE_SETTINGS");
    }

    /* JADX INFO: renamed from: c */
    private boolean m51c(String str) {
        return this.f86b.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    /* JADX INFO: renamed from: d */
    private b m52d() {
        String strM46b = m46b("com.baidu.deviceid");
        String strM46b2 = m46b("bd_setting_i");
        if (TextUtils.isEmpty(strM46b2)) {
            strM46b2 = m60h("");
            if (!TextUtils.isEmpty(strM46b2)) {
                m40a("bd_setting_i", strM46b2);
            }
        }
        if (TextUtils.isEmpty(strM46b)) {
            strM46b = m46b(C0632c.m27a(("com.baidu" + strM46b2 + m54d(this.f86b)).getBytes(), true));
        }
        C0637d c0637d = null;
        if (TextUtils.isEmpty(strM46b)) {
            return null;
        }
        b bVar = new b(c0637d);
        bVar.f93a = strM46b;
        bVar.f94b = strM46b2;
        return bVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a9 A[RETURN] */
    /* JADX INFO: renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.baidu.p002a.p003a.p004a.p005a.p007b.C0636c.b m53d(java.lang.String r11) {
        /*
            r10 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 1
            r3 = 23
            if (r0 >= r3) goto La
            r0 = 1
            goto Lb
        La:
            r0 = 0
        Lb:
            r3 = 0
            if (r0 == 0) goto L15
            boolean r4 = android.text.TextUtils.isEmpty(r11)
            if (r4 == 0) goto L15
            return r3
        L15:
            java.lang.String r4 = ""
            java.io.File r5 = new java.io.File
            java.io.File r6 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r7 = "baidu/.cuid"
            r5.<init>(r6, r7)
            boolean r6 = r5.exists()
            if (r6 != 0) goto L35
            java.io.File r5 = new java.io.File
            java.io.File r6 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r7 = "backups/.SystemConfig/.cuid"
            r5.<init>(r6, r7)
            r6 = 1
            goto L36
        L35:
            r6 = 0
        L36:
            java.io.FileReader r7 = new java.io.FileReader     // Catch: java.lang.Throwable -> L98
            r7.<init>(r5)     // Catch: java.lang.Throwable -> L98
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L98
            r5.<init>(r7)     // Catch: java.lang.Throwable -> L98
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L98
            r7.<init>()     // Catch: java.lang.Throwable -> L98
        L45:
            java.lang.String r8 = r5.readLine()     // Catch: java.lang.Throwable -> L98
            if (r8 == 0) goto L54
            r7.append(r8)     // Catch: java.lang.Throwable -> L98
            java.lang.String r8 = "\r\n"
            r7.append(r8)     // Catch: java.lang.Throwable -> L98
            goto L45
        L54:
            r5.close()     // Catch: java.lang.Throwable -> L98
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Throwable -> L98
            java.lang.String r8 = com.baidu.p002a.p003a.p004a.p005a.p007b.C0636c.f84a     // Catch: java.lang.Throwable -> L98
            java.lang.String r9 = com.baidu.p002a.p003a.p004a.p005a.p007b.C0636c.f84a     // Catch: java.lang.Throwable -> L98
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L98
            byte[] r7 = r7.getBytes()     // Catch: java.lang.Throwable -> L98
            byte[] r7 = com.baidu.p002a.p003a.p004a.p005a.p006a.C0631b.m24a(r7)     // Catch: java.lang.Throwable -> L98
            byte[] r7 = com.baidu.p002a.p003a.p004a.p005a.p006a.C0630a.m22b(r8, r9, r7)     // Catch: java.lang.Throwable -> L98
            r5.<init>(r7)     // Catch: java.lang.Throwable -> L98
            java.lang.String r7 = "="
            java.lang.String[] r5 = r5.split(r7)     // Catch: java.lang.Throwable -> L98
            if (r5 == 0) goto L92
            int r7 = r5.length     // Catch: java.lang.Throwable -> L98
            r8 = 2
            if (r7 != r8) goto L92
            if (r0 == 0) goto L86
            r1 = r5[r1]     // Catch: java.lang.Throwable -> L98
            boolean r1 = r11.equals(r1)     // Catch: java.lang.Throwable -> L98
            if (r1 != 0) goto L90
        L86:
            if (r0 != 0) goto L92
            boolean r0 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Throwable -> L98
            if (r0 == 0) goto L90
            r11 = r5[r2]     // Catch: java.lang.Throwable -> L98
        L90:
            r4 = r5[r2]     // Catch: java.lang.Throwable -> L98
        L92:
            if (r6 != 0) goto L99
            m47b(r11, r4)     // Catch: java.lang.Throwable -> L98
            goto L99
        L98:
        L99:
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 != 0) goto La9
            com.baidu.a.a.a.a.b.c$b r0 = new com.baidu.a.a.a.a.b.c$b
            r0.<init>(r3)
            r0.f93a = r4
            r0.f94b = r11
            return r0
        La9:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p002a.p003a.p004a.p005a.p007b.C0636c.m53d(java.lang.String):com.baidu.a.a.a.a.b.c$b");
    }

    /* JADX INFO: renamed from: d */
    public static String m54d(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return TextUtils.isEmpty(string) ? "" : string;
    }

    /* JADX INFO: renamed from: e */
    private b m55e() throws Throwable {
        File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
        if (!file.exists()) {
            return null;
        }
        String strM34a = m34a(file);
        if (TextUtils.isEmpty(strM34a)) {
            return null;
        }
        try {
            return b.m62a(new String(C0630a.m22b(f84a, f84a, C0631b.m24a(strM34a.getBytes()))));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: renamed from: e */
    private static b m56e(Context context) {
        if (f85e == null) {
            synchronized (b.class) {
                if (f85e == null) {
                    SystemClock.uptimeMillis();
                    f85e = new C0636c(context).m44b();
                    SystemClock.uptimeMillis();
                }
            }
        }
        return f85e;
    }

    /* JADX INFO: renamed from: e */
    private static String m57e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return C0631b.m23a(C0630a.m21a(f84a, f84a, str.getBytes()), "utf-8");
        } catch (UnsupportedEncodingException | Exception unused) {
            return "";
        }
    }

    /* JADX INFO: renamed from: f */
    private static String m58f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new String(C0630a.m22b(f84a, f84a, C0631b.m24a(str.getBytes())));
        } catch (Exception e) {
            m48b(e);
            return "";
        }
    }

    /* JADX INFO: renamed from: g */
    private static void m59g(String str) {
        File file;
        File file2 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
        File file3 = new File(file2, ".cuid2");
        try {
            if (file2.exists() && !file2.isDirectory()) {
                Random random = new Random();
                File parentFile = file2.getParentFile();
                String name = file2.getName();
                do {
                    file = new File(parentFile, name + random.nextInt() + ".tmp");
                } while (file.exists());
                file2.renameTo(file);
                file.delete();
            }
            file2.mkdirs();
            FileWriter fileWriter = new FileWriter(file3, false);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException | Exception unused) {
        }
    }

    /* JADX INFO: renamed from: h */
    private String m60h(String str) {
        String deviceId = null;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.f86b.getSystemService("phone");
            if (telephonyManager != null) {
                deviceId = telephonyManager.getDeviceId();
            }
        } catch (Exception e) {
            Log.e("DeviceId", "Read IMEI failed", e);
        }
        String strM61i = m61i(deviceId);
        return TextUtils.isEmpty(strM61i) ? str : strM61i;
    }

    /* JADX INFO: renamed from: i */
    private static String m61i(String str) {
        return (str == null || !str.contains(":")) ? str : "";
    }
}
