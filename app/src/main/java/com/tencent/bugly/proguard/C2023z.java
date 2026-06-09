package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import com.bumptech.glide.load.Key;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import kotlin.UByte;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.z */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class C2023z {

    /* JADX INFO: renamed from: a */
    private static Map<String, String> f2934a;

    /* JADX INFO: renamed from: a */
    public static String m1902a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            if (C2021x.m1867a(th2)) {
                return "fail";
            }
            th2.printStackTrace();
            return "fail";
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m1897a() {
        return m1898a(System.currentTimeMillis());
    }

    /* JADX INFO: renamed from: a */
    public static String m1898a(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(j));
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m1903a(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m1916a(File file, String str, String str2) {
        ZipOutputStream zipOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        if (str == null || str.length() == 0) {
            return null;
        }
        C2021x.m1871c("rqdp{  ZF start}", new Object[0]);
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes(Key.STRING_CHARSET_NAME));
            byteArrayOutputStream = new ByteArrayOutputStream();
            zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        } catch (Throwable th) {
            th = th;
            zipOutputStream = null;
        }
        try {
            zipOutputStream.setMethod(8);
            zipOutputStream.putNextEntry(new ZipEntry(str2));
            byte[] bArr = new byte[1024];
            while (true) {
                int i = byteArrayInputStream.read(bArr);
                if (i <= 0) {
                    break;
                }
                zipOutputStream.write(bArr, 0, i);
            }
            zipOutputStream.closeEntry();
            zipOutputStream.flush();
            zipOutputStream.finish();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                zipOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            C2021x.m1871c("rqdp{  ZF end}", new Object[0]);
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
            try {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
                if (zipOutputStream != null) {
                    try {
                        zipOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                C2021x.m1871c("rqdp{  ZF end}", new Object[0]);
                return null;
            } catch (Throwable th3) {
                if (zipOutputStream != null) {
                    try {
                        zipOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                C2021x.m1871c("rqdp{  ZF end}", new Object[0]);
                throw th3;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m1917a(byte[] bArr, int i) {
        if (bArr == null) {
            return bArr;
        }
        C2021x.m1871c("[Util] Zip %d bytes data with type %s", Integer.valueOf(bArr.length), "Gzip");
        try {
            InterfaceC1985ae interfaceC1985aeM1713a = C1984ad.m1713a(2);
            if (interfaceC1985aeM1713a == null) {
                return null;
            }
            return interfaceC1985aeM1713a.mo1714a(bArr);
        } catch (Throwable th) {
            if (!C2021x.m1867a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    public static byte[] m1927b(byte[] bArr, int i) {
        if (bArr == null) {
            return bArr;
        }
        C2021x.m1871c("[Util] Unzip %d bytes data with type %s", Integer.valueOf(bArr.length), "Gzip");
        try {
            InterfaceC1985ae interfaceC1985aeM1713a = C1984ad.m1713a(2);
            if (interfaceC1985aeM1713a == null) {
                return null;
            }
            return interfaceC1985aeM1713a.mo1715b(bArr);
        } catch (Throwable th) {
            if (th.getMessage() != null && th.getMessage().contains("Not in GZIP format")) {
                C2021x.m1872d(th.getMessage(), new Object[0]);
            } else if (!C2021x.m1867a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    public static long m1918b() {
        try {
            return (((System.currentTimeMillis() + ((long) TimeZone.getDefault().getRawOffset())) / 86400000) * 86400000) - ((long) TimeZone.getDefault().getRawOffset());
        } catch (Throwable th) {
            if (C2021x.m1867a(th)) {
                return -1L;
            }
            th.printStackTrace();
            return -1L;
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m1904a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "NULL";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(bArr);
            byte[] bArrDigest = messageDigest.digest();
            if (bArrDigest == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bArrDigest) {
                String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
                if (hexString.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString().toUpperCase();
        } catch (Throwable th) {
            if (C2021x.m1867a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m1912a(File file, File file2, int i) {
        ZipOutputStream zipOutputStream;
        C2021x.m1871c("rqdp{  ZF start}", new Object[0]);
        if (file == null || file2 == null || file.equals(file2)) {
            C2021x.m1872d("rqdp{  err ZF 1R!}", new Object[0]);
            return false;
        }
        if (!file.exists() || !file.canRead()) {
            C2021x.m1872d("rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}", new Object[0]);
            return false;
        }
        try {
            if (file2.getParentFile() != null && !file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
        } catch (Throwable th) {
            if (!C2021x.m1867a(th)) {
                th.printStackTrace();
            }
        }
        if (!file2.exists() || !file2.canRead()) {
            return false;
        }
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file2)));
                try {
                    zipOutputStream.setMethod(8);
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    byte[] bArr = new byte[5000];
                    while (true) {
                        int i2 = fileInputStream2.read(bArr);
                        if (i2 <= 0) {
                            break;
                        }
                        zipOutputStream.write(bArr, 0, i2);
                    }
                    zipOutputStream.flush();
                    zipOutputStream.closeEntry();
                    try {
                        fileInputStream2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        zipOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    C2021x.m1871c("rqdp{  ZF end}", new Object[0]);
                    return true;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = fileInputStream2;
                    try {
                        if (!C2021x.m1867a(th)) {
                            th.printStackTrace();
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (zipOutputStream != null) {
                            try {
                                zipOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        C2021x.m1871c("rqdp{  ZF end}", new Object[0]);
                        return false;
                    } catch (Throwable th3) {
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        if (zipOutputStream != null) {
                            try {
                                zipOutputStream.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        C2021x.m1871c("rqdp{  ZF end}", new Object[0]);
                        throw th3;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                zipOutputStream = null;
            }
        } catch (Throwable th5) {
            th = th5;
            zipOutputStream = null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static ArrayList<String> m1906a(Context context, String[] strArr) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        Process processExec;
        if (AppInfo.m1469e(context)) {
            return new ArrayList<>(Arrays.asList("unknown(low memory)"));
        }
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            processExec = Runtime.getRuntime().exec(strArr);
            bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
        } catch (Throwable th) {
            th = th;
            bufferedReader = null;
            bufferedReader2 = null;
        }
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                arrayList.add(line);
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = null;
            }
            try {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return null;
            } finally {
            }
        }
        bufferedReader2 = new BufferedReader(new InputStreamReader(processExec.getErrorStream()));
        while (true) {
            try {
                String line2 = bufferedReader2.readLine();
                if (line2 != null) {
                    arrayList.add(line2);
                } else {
                    try {
                        break;
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        bufferedReader.close();
        try {
            bufferedReader2.close();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: a */
    public static String m1900a(Context context, String str) {
        if (str == null || str.trim().equals("")) {
            return "";
        }
        if (f2934a == null) {
            f2934a = new HashMap();
            ArrayList<String> arrayListM1906a = m1906a(context, new String[]{(new File("/system/bin/sh").exists() && new File("/system/bin/sh").canExecute()) ? "/system/bin/sh" : "sh", "-c", "getprop"});
            if (arrayListM1906a != null && arrayListM1906a.size() > 0) {
                C2021x.m1868b(C2023z.class, "Successfully get 'getprop' list.", new Object[0]);
                Pattern patternCompile = Pattern.compile("\\[(.+)\\]: \\[(.*)\\]");
                Iterator<String> it = arrayListM1906a.iterator();
                while (it.hasNext()) {
                    Matcher matcher = patternCompile.matcher(it.next());
                    if (matcher.find()) {
                        f2934a.put(matcher.group(1), matcher.group(2));
                    }
                }
                C2021x.m1868b(C2023z.class, "Systems properties number: %d.", Integer.valueOf(f2934a.size()));
            }
        }
        return f2934a.containsKey(str) ? f2934a.get(str) : "fail";
    }

    /* JADX INFO: renamed from: b */
    public static void m1922b(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m1914a(String str) {
        return str == null || str.trim().length() <= 0;
    }

    /* JADX INFO: renamed from: b */
    public static void m1924b(String str) {
        if (str == null) {
            return;
        }
        File file = new File(str);
        if (file.isFile() && file.exists() && file.canWrite()) {
            file.delete();
        }
    }

    /* JADX INFO: renamed from: c */
    public static byte[] m1930c(long j) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(j);
            return sb.toString().getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    public static long m1919b(byte[] bArr) {
        if (bArr == null) {
            return -1L;
        }
        try {
            return Long.parseLong(new String(bArr, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    /* JADX INFO: renamed from: a */
    public static Context m1891a(Context context) {
        Context applicationContext;
        return (context == null || (applicationContext = context.getApplicationContext()) == null) ? context : applicationContext;
    }

    /* JADX INFO: renamed from: b */
    public static String m1920b(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    /* JADX INFO: renamed from: a */
    public static void m1910a(Class<?> cls, String str, Object obj, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(null, obj);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public static Object m1895a(String str, String str2, Object obj, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1909a(Parcel parcel, Map<String, PlugInBean> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Map.Entry<String, PlugInBean> entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pluginNum", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            bundle.putString("pluginKey" + i, (String) arrayList.get(i));
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            bundle.putString("pluginVal" + i2 + "plugInId", ((PlugInBean) arrayList2.get(i2)).f2353a);
            bundle.putString("pluginVal" + i2 + "plugInUUID", ((PlugInBean) arrayList2.get(i2)).f2355c);
            bundle.putString("pluginVal" + i2 + "plugInVersion", ((PlugInBean) arrayList2.get(i2)).f2354b);
        }
        parcel.writeBundle(bundle);
    }

    /* JADX INFO: renamed from: a */
    public static Map<String, PlugInBean> m1908a(Parcel parcel) {
        Bundle bundle = parcel.readBundle();
        HashMap map = null;
        if (bundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int iIntValue = ((Integer) bundle.get("pluginNum")).intValue();
        for (int i = 0; i < iIntValue; i++) {
            arrayList.add(bundle.getString("pluginKey" + i));
        }
        for (int i2 = 0; i2 < iIntValue; i2++) {
            arrayList2.add(new PlugInBean(bundle.getString("pluginVal" + i2 + "plugInId"), bundle.getString("pluginVal" + i2 + "plugInVersion"), bundle.getString("pluginVal" + i2 + "plugInUUID")));
        }
        if (arrayList.size() == arrayList2.size()) {
            map = new HashMap(arrayList.size());
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                map.put(arrayList.get(i3), PlugInBean.class.cast(arrayList2.get(i3)));
            }
        } else {
            C2021x.m1873e("map plugin parcel error!", new Object[0]);
        }
        return map;
    }

    /* JADX INFO: renamed from: b */
    public static void m1923b(Parcel parcel, Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList<String> arrayList = new ArrayList<>(size);
        ArrayList<String> arrayList2 = new ArrayList<>(size);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("keys", arrayList);
        bundle.putStringArrayList("values", arrayList2);
        parcel.writeBundle(bundle);
    }

    /* JADX INFO: renamed from: b */
    public static Map<String, String> m1921b(Parcel parcel) {
        Bundle bundle = parcel.readBundle();
        HashMap map = null;
        if (bundle == null) {
            return null;
        }
        ArrayList<String> stringArrayList = bundle.getStringArrayList("keys");
        ArrayList<String> stringArrayList2 = bundle.getStringArrayList("values");
        if (stringArrayList != null && stringArrayList2 != null && stringArrayList.size() == stringArrayList2.size()) {
            map = new HashMap(stringArrayList.size());
            for (int i = 0; i < stringArrayList.size(); i++) {
                map.put(stringArrayList.get(i), stringArrayList2.get(i));
            }
        } else {
            C2021x.m1873e("map parcel error!", new Object[0]);
        }
        return map;
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m1915a(Parcelable parcelable) {
        Parcel parcelObtain = Parcel.obtain();
        parcelable.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        return bArrMarshall;
    }

    /* JADX INFO: renamed from: a */
    public static <T> T m1896a(byte[] bArr, Parcelable.Creator<T> creator) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, 0, bArr.length);
        parcelObtain.setDataPosition(0);
        try {
            return creator.createFromParcel(parcelObtain);
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                if (parcelObtain == null) {
                    return null;
                }
                parcelObtain.recycle();
                return null;
            } finally {
                if (parcelObtain != null) {
                    parcelObtain.recycle();
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m1899a(Context context, int i, String str) {
        Process processExec = null;
        if (!AppInfo.m1465a(context, "android.permission.READ_LOGS")) {
            C2021x.m1872d("no read_log permission!", new Object[0]);
            return null;
        }
        String[] strArr = str == null ? new String[]{"logcat", "-d", "-v", "threadtime"} : new String[]{"logcat", "-d", "-v", "threadtime", "-s", str};
        StringBuilder sb = new StringBuilder();
        try {
            processExec = Runtime.getRuntime().exec(strArr);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
                sb.append("\n");
                if (i > 0 && sb.length() > i) {
                    sb.delete(0, sb.length() - i);
                }
            }
            return sb.toString();
        } catch (Throwable th) {
            try {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
                sb.append("\n[error:" + th.toString() + "]");
                String string = sb.toString();
                if (processExec != null) {
                    try {
                        processExec.getOutputStream().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        processExec.getInputStream().close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        processExec.getErrorStream().close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return string;
            } finally {
                if (processExec != null) {
                    try {
                        processExec.getOutputStream().close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    try {
                        processExec.getInputStream().close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    try {
                        processExec.getErrorStream().close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static Map<String, String> m1907a(int i, boolean z) {
        HashMap map = new HashMap(12);
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        Thread thread = Looper.getMainLooper().getThread();
        if (!allStackTraces.containsKey(thread)) {
            allStackTraces.put(thread, thread.getStackTrace());
        }
        Thread.currentThread().getId();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            int i2 = 0;
            sb.setLength(0);
            if (entry.getValue() != null && entry.getValue().length != 0) {
                StackTraceElement[] value = entry.getValue();
                int length = value.length;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = value[i2];
                    if (i > 0 && sb.length() >= i) {
                        sb.append("\n[Stack over limit size :" + i + " , has been cut!]");
                        break;
                    }
                    sb.append(stackTraceElement.toString());
                    sb.append("\n");
                    i2++;
                }
                map.put(entry.getKey().getName() + "(" + entry.getKey().getId() + ")", sb.toString());
            }
        }
        return map;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m1911a(Context context, String str, long j) {
        C2021x.m1871c("[Util] Try to lock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (file.exists()) {
                if (System.currentTimeMillis() - file.lastModified() < 10000) {
                    return false;
                }
                C2021x.m1871c("[Util] Lock file (%s) is expired, unlock it.", str);
                m1926b(context, str);
            }
            if (file.createNewFile()) {
                C2021x.m1871c("[Util] Successfully locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                return true;
            }
            C2021x.m1871c("[Util] Failed to locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return false;
        } catch (Throwable th) {
            C2021x.m1867a(th);
            return false;
        }
    }

    /* JADX INFO: renamed from: b */
    public static boolean m1926b(Context context, String str) {
        C2021x.m1871c("[Util] Try to unlock file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (!file.exists()) {
                return true;
            }
            if (!file.delete()) {
                return false;
            }
            C2021x.m1871c("[Util] Successfully unlocked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            C2021x.m1867a(th);
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m1901a(File file, int i, boolean z) {
        BufferedReader bufferedReader;
        if (file == null || !file.exists() || !file.canRead()) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                    sb.append("\n");
                    if (i > 0 && sb.length() > i) {
                        if (z) {
                            sb.delete(i, sb.length());
                            break;
                        }
                        sb.delete(0, sb.length() - i);
                    }
                } catch (Throwable th) {
                    th = th;
                    try {
                        C2021x.m1867a(th);
                        return null;
                    } finally {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e) {
                                C2021x.m1867a(e);
                            }
                        }
                    }
                }
            }
            String string = sb.toString();
            try {
                bufferedReader.close();
            } catch (Exception e2) {
                C2021x.m1867a(e2);
            }
            return string;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static BufferedReader m1893a(File file) {
        if (file != null && file.exists() && file.canRead()) {
            try {
                return new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            } catch (Throwable th) {
                C2021x.m1867a(th);
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    public static BufferedReader m1894a(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            File file = new File(str, str2);
            if (file.exists() && file.canRead()) {
                return m1893a(file);
            }
            return null;
        } catch (NullPointerException e) {
            C2021x.m1867a(e);
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static Thread m1905a(Runnable runnable, String str) {
        try {
            Thread thread = new Thread(runnable);
            thread.setName(str);
            thread.start();
            return thread;
        } catch (Throwable th) {
            C2021x.m1873e("[Util] Failed to start a thread to execute task with message: %s", th.getMessage());
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m1913a(Runnable runnable) {
        if (runnable == null) {
            return false;
        }
        C2020w c2020wM1858a = C2020w.m1858a();
        if (c2020wM1858a != null) {
            return c2020wM1858a.m1860a(runnable);
        }
        String[] strArrSplit = runnable.getClass().getName().split("\\.");
        return m1905a(runnable, strArrSplit[strArrSplit.length - 1]) != null;
    }

    /* JADX INFO: renamed from: c */
    public static boolean m1929c(String str) {
        if (str == null || str.trim().length() <= 0) {
            return false;
        }
        if (str.length() > 255) {
            C2021x.m1866a("URL(%s)'s length is larger than 255.", str);
            return false;
        }
        if (str.toLowerCase().startsWith("http")) {
            return true;
        }
        C2021x.m1866a("URL(%s) is not start with \"http\".", str);
        return false;
    }

    /* JADX INFO: renamed from: a */
    public static SharedPreferences m1892a(String str, Context context) {
        if (context != null) {
            return context.getSharedPreferences(str, 0);
        }
        return null;
    }

    /* JADX INFO: renamed from: b */
    public static void m1925b(String str, String str2) {
        if (C1958a.m1472b() == null || C1958a.m1472b().f2362F == null) {
            return;
        }
        C1958a.m1472b().f2362F.edit().putString(str, str2).apply();
    }

    /* JADX INFO: renamed from: c */
    public static String m1928c(String str, String str2) {
        return (C1958a.m1472b() == null || C1958a.m1472b().f2362F == null) ? "" : C1958a.m1472b().f2362F.getString(str, str2);
    }
}
