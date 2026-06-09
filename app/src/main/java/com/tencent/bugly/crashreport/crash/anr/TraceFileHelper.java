package com.tencent.bugly.crashreport.crash.anr;

import com.tencent.bugly.proguard.C2021x;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class TraceFileHelper {

    /* JADX INFO: renamed from: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$a */
    /* JADX INFO: compiled from: BUGLY */
    public static class C1967a {

        /* JADX INFO: renamed from: a */
        public long f2525a;

        /* JADX INFO: renamed from: b */
        public String f2526b;

        /* JADX INFO: renamed from: c */
        public long f2527c;

        /* JADX INFO: renamed from: d */
        public Map<String, String[]> f2528d;
    }

    /* JADX INFO: renamed from: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$b */
    /* JADX INFO: compiled from: BUGLY */
    public interface InterfaceC1968b {
        /* JADX INFO: renamed from: a */
        boolean mo1562a(long j);

        /* JADX INFO: renamed from: a */
        boolean mo1563a(long j, long j2, String str);

        /* JADX INFO: renamed from: a */
        boolean mo1564a(String str, int i, String str2, String str3);
    }

    public static C1967a readTargetDumpInfo(final String str, String str2, final boolean z) throws Throwable {
        if (str != null && str2 != null) {
            final C1967a c1967a = new C1967a();
            readTraceFile(str2, new InterfaceC1968b() { // from class: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.1
                @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC1968b
                /* JADX INFO: renamed from: a */
                public final boolean mo1564a(String str3, int i, String str4, String str5) {
                    C2021x.m1871c("new thread %s", str3);
                    if (c1967a.f2525a > 0 && c1967a.f2527c > 0 && c1967a.f2526b != null) {
                        if (c1967a.f2528d == null) {
                            c1967a.f2528d = new HashMap();
                        }
                        Map<String, String[]> map = c1967a.f2528d;
                        StringBuilder sb = new StringBuilder();
                        sb.append(i);
                        map.put(str3, new String[]{str4, str5, sb.toString()});
                    }
                    return true;
                }

                @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC1968b
                /* JADX INFO: renamed from: a */
                public final boolean mo1563a(long j, long j2, String str3) {
                    C2021x.m1871c("new process %s", str3);
                    if (!str3.equals(str)) {
                        return true;
                    }
                    c1967a.f2525a = j;
                    c1967a.f2526b = str3;
                    c1967a.f2527c = j2;
                    return z;
                }

                @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC1968b
                /* JADX INFO: renamed from: a */
                public final boolean mo1562a(long j) {
                    C2021x.m1871c("process end %d", Long.valueOf(j));
                    return c1967a.f2525a <= 0 || c1967a.f2527c <= 0 || c1967a.f2526b == null;
                }
            });
            if (c1967a.f2525a > 0 && c1967a.f2527c > 0 && c1967a.f2526b != null) {
                return c1967a;
            }
        }
        return null;
    }

    public static C1967a readFirstDumpInfo(String str, final boolean z) throws Throwable {
        if (str == null) {
            C2021x.m1873e("path:%s", str);
            return null;
        }
        final C1967a c1967a = new C1967a();
        readTraceFile(str, new InterfaceC1968b() { // from class: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.2
            @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC1968b
            /* JADX INFO: renamed from: a */
            public final boolean mo1564a(String str2, int i, String str3, String str4) {
                C2021x.m1871c("new thread %s", str2);
                if (c1967a.f2528d == null) {
                    c1967a.f2528d = new HashMap();
                }
                Map<String, String[]> map = c1967a.f2528d;
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                map.put(str2, new String[]{str3, str4, sb.toString()});
                return true;
            }

            @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC1968b
            /* JADX INFO: renamed from: a */
            public final boolean mo1563a(long j, long j2, String str2) {
                C2021x.m1871c("new process %s", str2);
                c1967a.f2525a = j;
                c1967a.f2526b = str2;
                c1967a.f2527c = j2;
                return z;
            }

            @Override // com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.InterfaceC1968b
            /* JADX INFO: renamed from: a */
            public final boolean mo1562a(long j) {
                C2021x.m1871c("process end %d", Long.valueOf(j));
                return false;
            }
        });
        if (c1967a.f2525a > 0 && c1967a.f2527c > 0 && c1967a.f2526b != null) {
            return c1967a;
        }
        C2021x.m1873e("first dump error %s", c1967a.f2525a + " " + c1967a.f2527c + " " + c1967a.f2526b);
        return null;
    }

    public static void readTraceFile(String str, InterfaceC1968b interfaceC1968b) throws Throwable {
        Throwable th;
        if (str == null || interfaceC1968b == null) {
            return;
        }
        File file = new File(str);
        if (!file.exists()) {
            return;
        }
        file.lastModified();
        file.length();
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                try {
                    Pattern patternCompile = Pattern.compile("-{5}\\spid\\s\\d+\\sat\\s\\d+-\\d+-\\d+\\s\\d{2}:\\d{2}:\\d{2}\\s-{5}");
                    Pattern patternCompile2 = Pattern.compile("-{5}\\send\\s\\d+\\s-{5}");
                    Pattern patternCompile3 = Pattern.compile("Cmd\\sline:\\s(\\S+)");
                    Pattern patternCompile4 = Pattern.compile("\".+\"\\s(daemon\\s){0,1}prio=\\d+\\stid=\\d+\\s.*");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                    while (true) {
                        Object[] objArrM1560a = m1560a(bufferedReader2, patternCompile);
                        if (objArrM1560a == null) {
                            try {
                                bufferedReader2.close();
                                return;
                            } catch (IOException e) {
                                if (C2021x.m1867a(e)) {
                                    return;
                                }
                                e.printStackTrace();
                                return;
                            }
                        }
                        String[] strArrSplit = objArrM1560a[1].toString().split("\\s");
                        long j = Long.parseLong(strArrSplit[2]);
                        long time = simpleDateFormat.parse(strArrSplit[4] + " " + strArrSplit[5]).getTime();
                        Object[] objArrM1560a2 = m1560a(bufferedReader2, patternCompile3);
                        if (objArrM1560a2 == null) {
                            try {
                                bufferedReader2.close();
                                return;
                            } catch (IOException e2) {
                                if (C2021x.m1867a(e2)) {
                                    return;
                                }
                                e2.printStackTrace();
                                return;
                            }
                        }
                        Matcher matcher = patternCompile3.matcher(objArrM1560a2[1].toString());
                        matcher.find();
                        matcher.group(1);
                        SimpleDateFormat simpleDateFormat2 = simpleDateFormat;
                        if (!interfaceC1968b.mo1563a(j, time, matcher.group(1))) {
                            try {
                                bufferedReader2.close();
                                return;
                            } catch (IOException e3) {
                                if (C2021x.m1867a(e3)) {
                                    return;
                                }
                                e3.printStackTrace();
                                return;
                            }
                        }
                        while (true) {
                            Object[] objArrM1560a3 = m1560a(bufferedReader2, patternCompile4, patternCompile2);
                            if (objArrM1560a3 == null) {
                                break;
                            }
                            if (objArrM1560a3[0] == patternCompile4) {
                                String string = objArrM1560a3[1].toString();
                                Matcher matcher2 = Pattern.compile("\".+\"").matcher(string);
                                matcher2.find();
                                String strGroup = matcher2.group();
                                String strSubstring = strGroup.substring(1, strGroup.length() - 1);
                                string.contains("NATIVE");
                                Matcher matcher3 = Pattern.compile("tid=\\d+").matcher(string);
                                matcher3.find();
                                String strGroup2 = matcher3.group();
                                interfaceC1968b.mo1564a(strSubstring, Integer.parseInt(strGroup2.substring(strGroup2.indexOf("=") + 1)), m1559a(bufferedReader2), m1561b(bufferedReader2));
                            } else if (!interfaceC1968b.mo1562a(Long.parseLong(objArrM1560a3[1].toString().split("\\s")[2]))) {
                                try {
                                    bufferedReader2.close();
                                    return;
                                } catch (IOException e4) {
                                    if (C2021x.m1867a(e4)) {
                                        return;
                                    }
                                    e4.printStackTrace();
                                    return;
                                }
                            }
                        }
                        simpleDateFormat = simpleDateFormat2;
                    }
                } catch (Exception e5) {
                    e = e5;
                    bufferedReader = bufferedReader2;
                    if (!C2021x.m1867a(e)) {
                        e.printStackTrace();
                    }
                    C2021x.m1872d("trace open fail:%s : %s", e.getClass().getName(), e.getMessage());
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e6) {
                            if (C2021x.m1867a(e6)) {
                                return;
                            }
                            e6.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader == null) {
                        throw th;
                    }
                    try {
                        bufferedReader.close();
                        throw th;
                    } catch (IOException e7) {
                        if (C2021x.m1867a(e7)) {
                            throw th;
                        }
                        e7.printStackTrace();
                        throw th;
                    }
                }
            } catch (Exception e8) {
                e = e8;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX INFO: renamed from: a */
    private static Object[] m1560a(BufferedReader bufferedReader, Pattern... patternArr) throws IOException {
        if (bufferedReader != null && patternArr != null) {
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                for (Pattern pattern : patternArr) {
                    if (pattern.matcher(line).matches()) {
                        return new Object[]{pattern, line};
                    }
                }
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private static String m1559a(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            String line = bufferedReader.readLine();
            if (line == null) {
                return null;
            }
            stringBuffer.append(line + "\n");
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: renamed from: b */
    private static String m1561b(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null || line.trim().length() <= 0) {
                break;
            }
            stringBuffer.append(line + "\n");
        }
        return stringBuffer.toString();
    }
}
