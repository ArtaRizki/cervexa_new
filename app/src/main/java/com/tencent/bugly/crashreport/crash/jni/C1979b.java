package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2023z;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.tencent.bugly.crashreport.crash.jni.b */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C1979b {

    /* JADX INFO: renamed from: a */
    private static List<File> f2660a = new ArrayList();

    /* JADX INFO: renamed from: d */
    private static Map<String, Integer> m1681d(String str) {
        if (str == null) {
            return null;
        }
        try {
            HashMap map = new HashMap();
            for (String str2 : str.split(",")) {
                String[] strArrSplit = str2.split(":");
                if (strArrSplit.length != 2) {
                    C2021x.m1873e("error format at %s", str2);
                    return null;
                }
                map.put(strArrSplit[0], Integer.valueOf(Integer.parseInt(strArrSplit[1])));
            }
            return map;
        } catch (Exception e) {
            C2021x.m1873e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    protected static String m1673a(String str) {
        if (str == null) {
            return "";
        }
        String[] strArrSplit = str.split("\n");
        if (strArrSplit == null || strArrSplit.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArrSplit) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                sb.append(str2);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    private static CrashDetailBean m1671a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        String str;
        String str2;
        HashMap map2;
        if (map == null) {
            return null;
        }
        if (C1958a.m1471a(context) == null) {
            C2021x.m1873e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str3 = map.get("intStateStr");
        if (str3 == null || str3.trim().length() <= 0) {
            C2021x.m1873e("no intStateStr", new Object[0]);
            return null;
        }
        Map<String, Integer> mapM1681d = m1681d(str3);
        if (mapM1681d == null) {
            C2021x.m1873e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            mapM1681d.get("sino").intValue();
            mapM1681d.get("sud").intValue();
            String str4 = map.get("soVersion");
            if (TextUtils.isEmpty(str4)) {
                C2021x.m1873e("error format at version", new Object[0]);
                return null;
            }
            String str5 = map.get("errorAddr");
            String str6 = "unknown";
            String str7 = str5 == null ? "unknown" : str5;
            String str8 = map.get("codeMsg");
            if (str8 == null) {
                str8 = "unknown";
            }
            String str9 = map.get("tombPath");
            String str10 = str9 == null ? "unknown" : str9;
            String str11 = map.get("signalName");
            if (str11 == null) {
                str11 = "unknown";
            }
            map.get("errnoMsg");
            String str12 = map.get("stack");
            if (str12 == null) {
                str12 = "unknown";
            }
            String str13 = map.get("jstack");
            if (str13 != null) {
                str12 = str12 + "java:\n" + str13;
            }
            Integer num = mapM1681d.get("sico");
            if (num == null || num.intValue() <= 0) {
                str = str8;
                str2 = str11;
            } else {
                str2 = str11 + "(" + str8 + ")";
                str = "KERNEL";
            }
            String str14 = map.get("nativeLog");
            byte[] bArrM1916a = (str14 == null || str14.isEmpty()) ? null : C2023z.m1916a((File) null, str14, "BuglyNativeLog.txt");
            String str15 = map.get("sendingProcess");
            if (str15 == null) {
                str15 = "unknown";
            }
            Integer num2 = mapM1681d.get("spd");
            if (num2 != null) {
                str15 = str15 + "(" + num2 + ")";
            }
            String str16 = str15;
            String str17 = map.get("threadName");
            if (str17 == null) {
                str17 = "unknown";
            }
            Integer num3 = mapM1681d.get("et");
            if (num3 != null) {
                str17 = str17 + "(" + num3 + ")";
            }
            String str18 = str17;
            String str19 = map.get("processName");
            if (str19 != null) {
                str6 = str19;
            }
            Integer num4 = mapM1681d.get("ep");
            if (num4 != null) {
                str6 = str6 + "(" + num4 + ")";
            }
            String str20 = str6;
            String str21 = map.get("key-value");
            if (str21 != null) {
                HashMap map3 = new HashMap();
                String[] strArrSplit = str21.split("\n");
                int length = strArrSplit.length;
                int i = 0;
                while (i < length) {
                    String[] strArrSplit2 = strArrSplit[i].split("=");
                    String[] strArr = strArrSplit;
                    if (strArrSplit2.length == 2) {
                        map3.put(strArrSplit2[0], strArrSplit2[1]);
                    }
                    i++;
                    strArrSplit = strArr;
                }
                map2 = map3;
            } else {
                map2 = null;
            }
            CrashDetailBean crashDetailBeanPackageCrashDatas = nativeExceptionHandler.packageCrashDatas(str20, str18, (((long) mapM1681d.get("etms").intValue()) / 1000) + (((long) mapM1681d.get("ets").intValue()) * 1000), str2, str7, m1673a(str12), str, str16, str10, map.get("sysLogPath"), map.get("jniLogPath"), str4, bArrM1916a, map2, false, false);
            if (crashDetailBeanPackageCrashDatas != null) {
                String str22 = map.get("userId");
                if (str22 != null) {
                    C2021x.m1871c("[Native record info] userId: %s", str22);
                    crashDetailBeanPackageCrashDatas.f2500m = str22;
                }
                String str23 = map.get("sysLog");
                if (str23 != null) {
                    crashDetailBeanPackageCrashDatas.f2510w = str23;
                }
                String str24 = map.get("appVersion");
                if (str24 != null) {
                    C2021x.m1871c("[Native record info] appVersion: %s", str24);
                    crashDetailBeanPackageCrashDatas.f2493f = str24;
                }
                String str25 = map.get("isAppForeground");
                if (str25 != null) {
                    C2021x.m1871c("[Native record info] isAppForeground: %s", str25);
                    crashDetailBeanPackageCrashDatas.f2477N = str25.equalsIgnoreCase("true");
                }
                String str26 = map.get("launchTime");
                if (str26 != null) {
                    C2021x.m1871c("[Native record info] launchTime: %s", str26);
                    try {
                        crashDetailBeanPackageCrashDatas.f2476M = Long.parseLong(str26);
                    } catch (NumberFormatException e) {
                        if (!C2021x.m1867a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
                crashDetailBeanPackageCrashDatas.f2513z = null;
                crashDetailBeanPackageCrashDatas.f2498k = true;
            }
            return crashDetailBeanPackageCrashDatas;
        } catch (Throwable th) {
            C2021x.m1873e("error format", new Object[0]);
            th.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static String m1672a(BufferedInputStream bufferedInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        if (bufferedInputStream == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(1024);
            while (true) {
                try {
                    int i = bufferedInputStream.read();
                    if (i == -1) {
                        byteArrayOutputStream.close();
                        break;
                    }
                    if (i == 0) {
                        String str = new String(byteArrayOutputStream.toByteArray(), "UTf-8");
                        byteArrayOutputStream.close();
                        return str;
                    }
                    byteArrayOutputStream.write(i);
                } catch (Throwable th) {
                    th = th;
                    try {
                        C2021x.m1867a(th);
                        return null;
                    } finally {
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r7v3, types: [boolean] */
    /* JADX INFO: renamed from: a */
    public static CrashDetailBean m1670a(Context context, String str, NativeExceptionHandler nativeExceptionHandler) throws Throwable {
        BufferedInputStream bufferedInputStream;
        String str2;
        String strM1672a;
        ?? r1 = 0;
        if (context == null || str == null || nativeExceptionHandler == null) {
            C2021x.m1873e("get eup record file args error", new Object[0]);
            return null;
        }
        File file = new File(str, "rqd_record.eup");
        if (file.exists()) {
            ?? CanRead = file.canRead();
            try {
                if (CanRead != 0) {
                    try {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    } catch (IOException e) {
                        e = e;
                        bufferedInputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        if (r1 != 0) {
                            try {
                                r1.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                    try {
                        String strM1672a2 = m1672a(bufferedInputStream);
                        if (strM1672a2 != null && strM1672a2.equals("NATIVE_RQD_REPORT")) {
                            HashMap map = new HashMap();
                            loop0: while (true) {
                                str2 = null;
                                while (true) {
                                    strM1672a = m1672a(bufferedInputStream);
                                    if (strM1672a == null) {
                                        break loop0;
                                    }
                                    if (str2 == null) {
                                        str2 = strM1672a;
                                    }
                                }
                                map.put(str2, strM1672a);
                            }
                            if (str2 != null) {
                                C2021x.m1873e("record not pair! drop! %s", str2);
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                                return null;
                            }
                            CrashDetailBean crashDetailBeanM1671a = m1671a(context, map, nativeExceptionHandler);
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                            return crashDetailBeanM1671a;
                        }
                        C2021x.m1873e("record read fail! %s", strM1672a2);
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                        return null;
                    } catch (IOException e6) {
                        e = e6;
                        e.printStackTrace();
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        return null;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                r1 = CanRead;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: b */
    private static String m1678b(String str, String str2) {
        BufferedReader bufferedReaderM1894a = C2023z.m1894a(str, "reg_record.txt");
        if (bufferedReaderM1894a == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line = bufferedReaderM1894a.readLine();
            if (line != null && line.startsWith(str2)) {
                int i = 18;
                int i2 = 0;
                int length = 0;
                while (true) {
                    String line2 = bufferedReaderM1894a.readLine();
                    if (line2 == null) {
                        break;
                    }
                    if (i2 % 4 == 0) {
                        if (i2 > 0) {
                            sb.append("\n");
                        }
                        sb.append("  ");
                    } else {
                        if (line2.length() > 16) {
                            i = 28;
                        }
                        sb.append("                ".substring(0, i - length));
                    }
                    length = line2.length();
                    sb.append(line2);
                    i2++;
                }
                sb.append("\n");
                return sb.toString();
            }
            if (bufferedReaderM1894a != null) {
                try {
                    bufferedReaderM1894a.close();
                } catch (Exception e) {
                    C2021x.m1867a(e);
                }
            }
            return null;
        } catch (Throwable th) {
            try {
                C2021x.m1867a(th);
                if (bufferedReaderM1894a != null) {
                    try {
                        bufferedReaderM1894a.close();
                    } catch (Exception e2) {
                        C2021x.m1867a(e2);
                    }
                }
                return null;
            } finally {
                if (bufferedReaderM1894a != null) {
                    try {
                        bufferedReaderM1894a.close();
                    } catch (Exception e3) {
                        C2021x.m1867a(e3);
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: c */
    private static String m1679c(String str, String str2) {
        BufferedReader bufferedReaderM1894a = C2023z.m1894a(str, "map_record.txt");
        if (bufferedReaderM1894a == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line = bufferedReaderM1894a.readLine();
            if (line != null && line.startsWith(str2)) {
                while (true) {
                    String line2 = bufferedReaderM1894a.readLine();
                    if (line2 == null) {
                        break;
                    }
                    sb.append("  ");
                    sb.append(line2);
                    sb.append("\n");
                }
                return sb.toString();
            }
            if (bufferedReaderM1894a != null) {
                try {
                    bufferedReaderM1894a.close();
                } catch (Exception e) {
                    C2021x.m1867a(e);
                }
            }
            return null;
        } catch (Throwable th) {
            try {
                C2021x.m1867a(th);
                if (bufferedReaderM1894a != null) {
                    try {
                        bufferedReaderM1894a.close();
                    } catch (Exception e2) {
                        C2021x.m1867a(e2);
                    }
                }
                return null;
            } finally {
                if (bufferedReaderM1894a != null) {
                    try {
                        bufferedReaderM1894a.close();
                    } catch (Exception e3) {
                        C2021x.m1867a(e3);
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m1675a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String strM1678b = m1678b(str, str2);
        if (strM1678b != null && !strM1678b.isEmpty()) {
            sb.append("Register infos:\n");
            sb.append(strM1678b);
        }
        String strM1679c = m1679c(str, str2);
        if (strM1679c != null && !strM1679c.isEmpty()) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append("System SO infos:\n");
            sb.append(strM1679c);
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: b */
    public static String m1677b(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str, "backup_record.txt");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    /* JADX INFO: renamed from: c */
    public static void m1680c(String str) {
        File[] fileArrListFiles;
        if (str == null) {
            return;
        }
        try {
            File file = new File(str);
            if (file.canRead() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                for (File file2 : fileArrListFiles) {
                    if (file2.canRead() && file2.canWrite() && file2.length() == 0) {
                        file2.delete();
                        C2021x.m1871c("Delete empty record file %s", file2.getAbsoluteFile());
                    }
                }
            }
        } catch (Throwable th) {
            C2021x.m1867a(th);
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1676a(boolean z, String str) {
        if (str != null) {
            f2660a.add(new File(str, "rqd_record.eup"));
            f2660a.add(new File(str, "reg_record.txt"));
            f2660a.add(new File(str, "map_record.txt"));
            f2660a.add(new File(str, "backup_record.txt"));
            if (z) {
                m1680c(str);
            }
        }
        List<File> list = f2660a;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (File file : f2660a) {
            if (file.exists() && file.canWrite()) {
                file.delete();
                C2021x.m1871c("Delete record file %s", file.getAbsoluteFile());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.String] */
    /* JADX INFO: renamed from: a */
    public static String m1674a(String str, int i, String str2, boolean z) {
        BufferedReader bufferedReader = null;
        if (str != null && i > 0) {
            File file = new File(str);
            if (file.exists() && file.canRead()) {
                C2021x.m1866a("Read system log from native record file(length: %s bytes): %s", Long.valueOf(file.length()), file.getAbsolutePath());
                f2660a.add(file);
                C2021x.m1871c("Add this record file to list for cleaning lastly.", new Object[0]);
                if (str2 == null) {
                    return C2023z.m1901a(new File(str), i, z);
                }
                String sb = new StringBuilder();
                try {
                    try {
                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                        while (true) {
                            try {
                                String line = bufferedReader2.readLine();
                                if (line == null) {
                                    break;
                                }
                                if (Pattern.compile(str2 + "[ ]*:").matcher(line).find()) {
                                    sb.append(line);
                                    sb.append("\n");
                                }
                                if (i > 0 && sb.length() > i) {
                                    if (z) {
                                        sb.delete(i, sb.length());
                                        break;
                                    }
                                    sb.delete(0, sb.length() - i);
                                }
                            } catch (Throwable th) {
                                th = th;
                                bufferedReader = bufferedReader2;
                                try {
                                    C2021x.m1867a(th);
                                    sb.append("\n[error:" + th.toString() + "]");
                                    String string = sb.toString();
                                    if (bufferedReader == null) {
                                        return string;
                                    }
                                    bufferedReader.close();
                                    sb = string;
                                } catch (Throwable th2) {
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (Exception e) {
                                            C2021x.m1867a(e);
                                        }
                                    }
                                    throw th2;
                                }
                            }
                        }
                        String string2 = sb.toString();
                        bufferedReader2.close();
                        sb = string2;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                    return sb;
                } catch (Exception e2) {
                    C2021x.m1867a(e2);
                    return sb;
                }
            }
        }
        return null;
    }
}
