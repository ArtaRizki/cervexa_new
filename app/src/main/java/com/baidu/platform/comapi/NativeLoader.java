package com.baidu.platform.comapi;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class NativeLoader {

    /* JADX INFO: renamed from: a */
    private static Context f772a;

    /* JADX INFO: renamed from: d */
    private static NativeLoader f775d;

    /* JADX INFO: renamed from: b */
    private static final Set<String> f773b = new HashSet();

    /* JADX INFO: renamed from: c */
    private static final Set<String> f774c = new HashSet();

    /* JADX INFO: renamed from: e */
    private static EnumC0719a f776e = EnumC0719a.ARMEABI;

    /* JADX INFO: renamed from: com.baidu.platform.comapi.NativeLoader$1 */
    /* synthetic */ class C07181 {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f777a;

        static {
            int[] iArr = new int[EnumC0719a.values().length];
            f777a = iArr;
            try {
                iArr[EnumC0719a.ARM64.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f777a[EnumC0719a.ARMV7.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f777a[EnumC0719a.ARMEABI.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f777a[EnumC0719a.X86_64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f777a[EnumC0719a.X86.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: renamed from: com.baidu.platform.comapi.NativeLoader$a */
    private enum EnumC0719a {
        ARMEABI("armeabi"),
        ARMV7("armeabi-v7a"),
        ARM64("arm64-v8a"),
        X86("x86"),
        X86_64("x86_64");


        /* JADX INFO: renamed from: f */
        private String f784f;

        EnumC0719a(String str) {
            this.f784f = str;
        }

        /* JADX INFO: renamed from: a */
        public String m520a() {
            return this.f784f;
        }
    }

    private NativeLoader() {
    }

    /* JADX INFO: renamed from: a */
    private static EnumC0719a m511a() {
        String str = Build.VERSION.SDK_INT < 21 ? Build.CPU_ABI : Build.SUPPORTED_ABIS[0];
        if (str == null) {
            return EnumC0719a.ARMEABI;
        }
        if (str.contains("arm") && str.contains("v7")) {
            f776e = EnumC0719a.ARMV7;
        }
        if (str.contains("arm") && str.contains("64")) {
            f776e = EnumC0719a.ARM64;
        }
        if (str.contains("x86")) {
            f776e = str.contains("64") ? EnumC0719a.X86_64 : EnumC0719a.X86;
        }
        return f776e;
    }

    /* JADX INFO: renamed from: a */
    private String m512a(EnumC0719a enumC0719a) {
        return "lib/" + enumC0719a.m520a() + "/";
    }

    /* JADX INFO: renamed from: a */
    private void m513a(Throwable th) {
        Log.e(NativeLoader.class.getSimpleName(), "loadException", th);
        for (String str : f774c) {
            Log.e(NativeLoader.class.getSimpleName(), str + " Failed to load.");
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m514a(String str, String str2) {
        return !copyNativeLibrary(str2, EnumC0719a.ARMV7) ? m515b(str, str2) : m519f(str2, str);
    }

    /* JADX INFO: renamed from: b */
    private boolean m515b(String str, String str2) {
        if (copyNativeLibrary(str2, EnumC0719a.ARMEABI)) {
            return m519f(str2, str);
        }
        Log.e(NativeLoader.class.getSimpleName(), "found lib" + str + ".so error");
        return false;
    }

    /* JADX INFO: renamed from: c */
    private boolean m516c(String str, String str2) {
        return !copyNativeLibrary(str2, EnumC0719a.ARM64) ? m514a(str, str2) : m519f(str2, str);
    }

    /* JADX INFO: renamed from: d */
    private boolean m517d(String str, String str2) {
        return !copyNativeLibrary(str2, EnumC0719a.X86) ? m514a(str, str2) : m519f(str2, str);
    }

    /* JADX INFO: renamed from: e */
    private boolean m518e(String str, String str2) {
        return !copyNativeLibrary(str2, EnumC0719a.X86_64) ? m517d(str, str2) : m519f(str2, str);
    }

    /* JADX INFO: renamed from: f */
    private boolean m519f(String str, String str2) {
        try {
            System.load(new File(getCustomizeNativePath(), str).getAbsolutePath());
            synchronized (f773b) {
                f773b.add(str2);
            }
            return true;
        } catch (Throwable th) {
            synchronized (f774c) {
                f774c.add(str2);
                m513a(th);
                return false;
            }
        }
    }

    public static synchronized NativeLoader getInstance() {
        if (f775d == null) {
            f775d = new NativeLoader();
            f776e = m511a();
        }
        return f775d;
    }

    public static void setContext(Context context) {
        f772a = context;
    }

    protected boolean copyNativeLibrary(String str, EnumC0719a enumC0719a) throws Throwable {
        String str2 = m512a(enumC0719a) + str;
        ZipFile zipFile = null;
        try {
            try {
                ZipFile zipFile2 = new ZipFile(getCodePath());
                try {
                    File file = new File(getCustomizeNativePath(), str);
                    ZipEntry entry = zipFile2.getEntry(str2);
                    if (entry == null) {
                        try {
                            zipFile2.close();
                        } catch (IOException unused) {
                        }
                        return false;
                    }
                    copyStream(zipFile2.getInputStream(entry), new FileOutputStream(file));
                    try {
                        zipFile2.close();
                        return true;
                    } catch (IOException unused2) {
                        return false;
                    }
                } catch (Exception e) {
                    e = e;
                    zipFile = zipFile2;
                    Log.e(NativeLoader.class.getSimpleName(), "copyError", e);
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException unused3) {
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    zipFile = zipFile2;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException unused4) {
                            return false;
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
    }

    protected final void copyStream(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException {
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

    protected String getCodePath() {
        return 8 <= Build.VERSION.SDK_INT ? f772a.getPackageCodePath() : "";
    }

    protected String getCustomizeNativePath() {
        File file = new File(f772a.getFilesDir(), "libs");
        file.mkdirs();
        return file.getAbsolutePath();
    }

    protected boolean loadCustomizeNativeLibrary(String str) {
        String strMapLibraryName = System.mapLibraryName(str);
        int i = C07181.f777a[f776e.ordinal()];
        if (i == 1) {
            return m516c(str, strMapLibraryName);
        }
        if (i == 2) {
            return m514a(str, strMapLibraryName);
        }
        if (i == 3) {
            return m515b(str, strMapLibraryName);
        }
        if (i == 4) {
            return m518e(str, strMapLibraryName);
        }
        if (i != 5) {
            return false;
        }
        return m517d(str, strMapLibraryName);
    }

    public synchronized boolean loadLibrary(String str) {
        try {
            synchronized (f773b) {
                if (f773b.contains(str)) {
                    return true;
                }
                System.loadLibrary(str);
                synchronized (f773b) {
                    f773b.add(str);
                }
                return true;
            }
        } catch (Throwable unused) {
            return loadCustomizeNativeLibrary(str);
        }
    }
}
