package com.baidu.android.bbalbs.common.util;

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
import com.baidu.android.bbalbs.common.p008a.C0638a;
import com.baidu.android.bbalbs.common.p008a.C0639b;
import com.baidu.android.bbalbs.common.p008a.C0640c;
import com.baidu.android.bbalbs.common.p008a.C0641d;
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

/* JADX INFO: renamed from: com.baidu.android.bbalbs.common.util.b */
/* JADX INFO: loaded from: classes.dex */
public final class C0643b {

    /* JADX INFO: renamed from: a */
    private static final String f99a = new String(C0639b.m70a(new byte[]{77, 122, 65, 121, 77, 84, 73, 120, 77, 68, 73, 61})) + new String(C0639b.m70a(new byte[]{90, 71, 108, 106, 100, 87, 82, 112, 89, 87, 73, 61}));

    /* JADX INFO: renamed from: e */
    private static b f100e;

    /* JADX INFO: renamed from: b */
    private final Context f101b;

    /* JADX INFO: renamed from: c */
    private int f102c = 0;

    /* JADX INFO: renamed from: d */
    private PublicKey f103d;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.baidu.android.bbalbs.common.util.b$a */
    static class a {

        /* JADX INFO: renamed from: a */
        public ApplicationInfo f104a;

        /* JADX INFO: renamed from: b */
        public int f105b;

        /* JADX INFO: renamed from: c */
        public boolean f106c;

        /* JADX INFO: renamed from: d */
        public boolean f107d;

        private a() {
            this.f105b = 0;
            this.f106c = false;
            this.f107d = false;
        }

        /* synthetic */ a(C0644c c0644c) {
            this();
        }
    }

    /* JADX INFO: renamed from: com.baidu.android.bbalbs.common.util.b$b */
    private static class b {

        /* JADX INFO: renamed from: a */
        public String f108a;

        /* JADX INFO: renamed from: b */
        public String f109b;

        /* JADX INFO: renamed from: c */
        public int f110c;

        private b() {
            this.f110c = 2;
        }

        /* synthetic */ b(C0644c c0644c) {
            this();
        }

        /* JADX INFO: renamed from: a */
        public static b m104a(String str) {
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
                    bVar.f108a = string;
                    bVar.f109b = string2;
                    bVar.f110c = i;
                    return bVar;
                }
            } catch (JSONException e) {
                C0643b.m92b(e);
            }
            return null;
        }

        /* JADX INFO: renamed from: a */
        public String m105a() {
            try {
                return new JSONObject().put("deviceid", this.f108a).put("imei", this.f109b).put(TopicKey.VERSION, this.f110c).toString();
            } catch (JSONException e) {
                C0643b.m92b(e);
                return null;
            }
        }

        /* JADX INFO: renamed from: b */
        public String m106b() {
            String str = this.f109b;
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            return this.f108a + "|" + new StringBuffer(str).reverse().toString();
        }
    }

    /* JADX INFO: renamed from: com.baidu.android.bbalbs.common.util.b$c */
    static class c {
        /* JADX INFO: renamed from: a */
        static boolean m107a(String str, int i) {
            try {
                Os.chmod(str, i);
                return true;
            } catch (ErrnoException e) {
                C0643b.m92b(e);
                return false;
            }
        }
    }

    private C0643b(Context context) throws Throwable {
        this.f101b = context.getApplicationContext();
        m81a();
    }

    /* JADX INFO: renamed from: a */
    public static String m77a(Context context) {
        return m93c(context).m106b();
    }

    /* JADX INFO: renamed from: a */
    private static String m78a(File file) throws Throwable {
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
                        m92b(e2);
                    }
                }
                throw th;
            }
            String string = charArrayWriter.toString();
            try {
                fileReader.close();
            } catch (Exception e3) {
                m92b(e3);
            }
            return string;
        } catch (Exception e4) {
            e = e4;
            m92b(e);
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e5) {
                    m92b(e5);
                }
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static String m79a(byte[] bArr) {
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
    private List<a> m80a(Intent intent, boolean z) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = this.f101b.getPackageManager();
        List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (listQueryBroadcastReceivers != null) {
            for (ResolveInfo resolveInfo : listQueryBroadcastReceivers) {
                if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.applicationInfo != null) {
                    try {
                        Bundle bundle = packageManager.getReceiverInfo(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name), 128).metaData;
                        if (bundle != null) {
                            String string = bundle.getString("galaxy_data");
                            if (!TextUtils.isEmpty(string)) {
                                byte[] bArrM70a = C0639b.m70a(string.getBytes("utf-8"));
                                JSONObject jSONObject = new JSONObject(new String(bArrM70a));
                                a aVar = new a(null);
                                aVar.f105b = jSONObject.getInt("priority");
                                aVar.f104a = resolveInfo.activityInfo.applicationInfo;
                                if (this.f101b.getPackageName().equals(resolveInfo.activityInfo.applicationInfo.packageName)) {
                                    aVar.f107d = true;
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
                                        if (m85a(strArr, m87a(packageInfo.signatures))) {
                                            byte[] bArrM86a = m86a(C0639b.m70a(string2.getBytes()), this.f103d);
                                            if (bArrM86a != null && Arrays.equals(bArrM86a, C0641d.m74a(bArrM70a))) {
                                                aVar.f106c = true;
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
        Collections.sort(arrayList, new C0644c(this));
        return arrayList;
    }

    /* JADX INFO: renamed from: a */
    private void m81a() throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            try {
                byteArrayInputStream = new ByteArrayInputStream(C0642a.m76a());
            } catch (Exception unused) {
            } catch (Throwable th2) {
                byteArrayInputStream = null;
                th = th2;
            }
            try {
                this.f103d = CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream).getPublicKey();
                byteArrayInputStream.close();
            } catch (Exception unused2) {
                byteArrayInputStream2 = byteArrayInputStream;
                if (byteArrayInputStream2 == null) {
                } else {
                    byteArrayInputStream2.close();
                }
            } catch (Throwable th3) {
                th = th3;
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Exception e) {
                        m92b(e);
                    }
                }
                throw th;
            }
        } catch (Exception e2) {
            m92b(e2);
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m83a(String str) {
        int i = Build.VERSION.SDK_INT >= 24 ? 0 : 1;
        FileOutputStream fileOutputStreamOpenFileOutput = null;
        try {
            try {
                fileOutputStreamOpenFileOutput = this.f101b.openFileOutput("libcuid.so", i);
                fileOutputStreamOpenFileOutput.write(str.getBytes());
                fileOutputStreamOpenFileOutput.flush();
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (Exception e) {
                        m92b(e);
                    }
                }
                if (i == 0) {
                    return c.m107a(new File(this.f101b.getFilesDir(), "libcuid.so").getAbsolutePath(), NNTPReply.TRANSFER_FAILED);
                }
                return true;
            } catch (Exception e2) {
                m92b(e2);
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (Exception e3) {
                        m92b(e3);
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (fileOutputStreamOpenFileOutput != null) {
                try {
                    fileOutputStreamOpenFileOutput.close();
                } catch (Exception e4) {
                    m92b(e4);
                }
            }
            throw th;
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m84a(String str, String str2) {
        try {
            return Settings.System.putString(this.f101b.getContentResolver(), str, str2);
        } catch (Exception e) {
            m92b(e);
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m85a(String[] strArr, String[] strArr2) {
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
    private static byte[] m86a(byte[] bArr, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, publicKey);
        return cipher.doFinal(bArr);
    }

    /* JADX INFO: renamed from: a */
    private String[] m87a(Signature[] signatureArr) {
        int length = signatureArr.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = m79a(C0641d.m74a(signatureArr[i].toByteArray()));
        }
        return strArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01be  */
    /* JADX WARN: Type inference failed for: r11v0, types: [com.baidu.android.bbalbs.common.util.b] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.baidu.android.bbalbs.common.util.c] */
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
    private com.baidu.android.bbalbs.common.util.C0643b.b m88b() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 583
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.bbalbs.common.util.C0643b.m88b():com.baidu.android.bbalbs.common.util.b$b");
    }

    /* JADX INFO: renamed from: b */
    public static String m89b(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return TextUtils.isEmpty(string) ? "" : string;
    }

    /* JADX INFO: renamed from: b */
    private String m90b(String str) {
        try {
            return Settings.System.getString(this.f101b.getContentResolver(), str);
        } catch (Exception e) {
            m92b(e);
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m91b(String str, String str2) {
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
            fileWriter.write(C0639b.m69a(C0638a.m67a(f99a, f99a, (str + "=" + str2).getBytes()), "utf-8"));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException | Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public static void m92b(Throwable th) {
    }

    /* JADX INFO: renamed from: c */
    private static b m93c(Context context) {
        if (f100e == null) {
            synchronized (b.class) {
                if (f100e == null) {
                    SystemClock.uptimeMillis();
                    f100e = new C0643b(context).m88b();
                    SystemClock.uptimeMillis();
                }
            }
        }
        return f100e;
    }

    /* JADX INFO: renamed from: c */
    private boolean m94c() {
        return m95c("android.permission.WRITE_SETTINGS");
    }

    /* JADX INFO: renamed from: c */
    private boolean m95c(String str) {
        return this.f101b.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    /* JADX INFO: renamed from: d */
    private b m96d() {
        String strM90b = m90b("com.baidu.deviceid");
        String strM90b2 = m90b("bd_setting_i");
        if (TextUtils.isEmpty(strM90b2)) {
            strM90b2 = m102h("");
            if (!TextUtils.isEmpty(strM90b2)) {
                m84a("bd_setting_i", strM90b2);
            }
        }
        if (TextUtils.isEmpty(strM90b)) {
            strM90b = m90b(C0640c.m73a(("com.baidu" + strM90b2 + m89b(this.f101b)).getBytes(), true));
        }
        C0644c c0644c = null;
        if (TextUtils.isEmpty(strM90b)) {
            return null;
        }
        b bVar = new b(c0644c);
        bVar.f108a = strM90b;
        bVar.f109b = strM90b2;
        return bVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ad A[RETURN] */
    /* JADX INFO: renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.baidu.android.bbalbs.common.util.C0643b.b m97d(java.lang.String r11) {
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
            if (r6 == 0) goto L2a
            r6 = 0
            goto L36
        L2a:
            java.io.File r5 = new java.io.File
            java.io.File r6 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r7 = "backups/.SystemConfig/.cuid"
            r5.<init>(r6, r7)
            r6 = 1
        L36:
            java.io.FileReader r7 = new java.io.FileReader     // Catch: java.lang.Throwable -> L9c
            r7.<init>(r5)     // Catch: java.lang.Throwable -> L9c
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L9c
            r5.<init>(r7)     // Catch: java.lang.Throwable -> L9c
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9c
            r7.<init>()     // Catch: java.lang.Throwable -> L9c
        L45:
            java.lang.String r8 = r5.readLine()     // Catch: java.lang.Throwable -> L9c
            if (r8 == 0) goto L54
            r7.append(r8)     // Catch: java.lang.Throwable -> L9c
            java.lang.String r8 = "\r\n"
            r7.append(r8)     // Catch: java.lang.Throwable -> L9c
            goto L45
        L54:
            r5.close()     // Catch: java.lang.Throwable -> L9c
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Throwable -> L9c
            java.lang.String r8 = com.baidu.android.bbalbs.common.util.C0643b.f99a     // Catch: java.lang.Throwable -> L9c
            java.lang.String r9 = com.baidu.android.bbalbs.common.util.C0643b.f99a     // Catch: java.lang.Throwable -> L9c
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L9c
            byte[] r7 = r7.getBytes()     // Catch: java.lang.Throwable -> L9c
            byte[] r7 = com.baidu.android.bbalbs.common.p008a.C0639b.m70a(r7)     // Catch: java.lang.Throwable -> L9c
            byte[] r7 = com.baidu.android.bbalbs.common.p008a.C0638a.m68b(r8, r9, r7)     // Catch: java.lang.Throwable -> L9c
            r5.<init>(r7)     // Catch: java.lang.Throwable -> L9c
            java.lang.String r7 = "="
            java.lang.String[] r5 = r5.split(r7)     // Catch: java.lang.Throwable -> L9c
            if (r5 == 0) goto L96
            int r7 = r5.length     // Catch: java.lang.Throwable -> L9c
            r8 = 2
            if (r7 != r8) goto L96
            if (r0 == 0) goto L89
            r1 = r5[r1]     // Catch: java.lang.Throwable -> L9c
            boolean r1 = r11.equals(r1)     // Catch: java.lang.Throwable -> L9c
            if (r1 == 0) goto L89
            r0 = r5[r2]     // Catch: java.lang.Throwable -> L9c
            goto L95
        L89:
            if (r0 != 0) goto L96
            boolean r0 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Throwable -> L9c
            if (r0 == 0) goto L93
            r11 = r5[r2]     // Catch: java.lang.Throwable -> L9c
        L93:
            r0 = r5[r2]     // Catch: java.lang.Throwable -> L9c
        L95:
            r4 = r0
        L96:
            if (r6 != 0) goto L9d
            m91b(r11, r4)     // Catch: java.lang.Throwable -> L9c
            goto L9d
        L9c:
        L9d:
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 != 0) goto Lad
            com.baidu.android.bbalbs.common.util.b$b r0 = new com.baidu.android.bbalbs.common.util.b$b
            r0.<init>(r3)
            r0.f108a = r4
            r0.f109b = r11
            return r0
        Lad:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.bbalbs.common.util.C0643b.m97d(java.lang.String):com.baidu.android.bbalbs.common.util.b$b");
    }

    /* JADX INFO: renamed from: e */
    private b m98e() throws Throwable {
        File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
        if (!file.exists()) {
            return null;
        }
        String strM78a = m78a(file);
        if (TextUtils.isEmpty(strM78a)) {
            return null;
        }
        try {
            return b.m104a(new String(C0638a.m68b(f99a, f99a, C0639b.m70a(strM78a.getBytes()))));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: renamed from: e */
    private static String m99e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return C0639b.m69a(C0638a.m67a(f99a, f99a, str.getBytes()), "utf-8");
        } catch (UnsupportedEncodingException | Exception e) {
            m92b(e);
            return "";
        }
    }

    /* JADX INFO: renamed from: f */
    private static String m100f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new String(C0638a.m68b(f99a, f99a, C0639b.m70a(str.getBytes())));
        } catch (Exception e) {
            m92b(e);
            return "";
        }
    }

    /* JADX INFO: renamed from: g */
    private static void m101g(String str) {
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
    private String m102h(String str) {
        String deviceId = null;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.f101b.getSystemService("phone");
            if (telephonyManager != null) {
                deviceId = telephonyManager.getDeviceId();
            }
        } catch (Exception e) {
            Log.e("DeviceId", "Read IMEI failed", e);
        }
        String strM103i = m103i(deviceId);
        return TextUtils.isEmpty(strM103i) ? str : strM103i;
    }

    /* JADX INFO: renamed from: i */
    private static String m103i(String str) {
        return (str == null || !str.contains(":")) ? str : "";
    }
}
