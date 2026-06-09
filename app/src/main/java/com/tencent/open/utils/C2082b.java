package com.tencent.open.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import com.jiangdg.usbcamera.UVCCameraHelper;
import com.tencent.open.p026a.C2061f;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: renamed from: com.tencent.open.utils.b */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2082b {

    /* JADX INFO: renamed from: c */
    private static String f3262c;

    /* JADX INFO: renamed from: a */
    private String f3263a;

    /* JADX INFO: renamed from: b */
    private InterfaceC2083c f3264b;

    /* JADX INFO: renamed from: d */
    private long f3265d;

    /* JADX INFO: renamed from: e */
    private Handler f3266e;

    /* JADX INFO: renamed from: f */
    private Runnable f3267f = new Runnable() { // from class: com.tencent.open.utils.b.2
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            boolean zM2214a;
            C2061f.m2127a("AsynLoadImg", "saveFileRunnable:");
            String str = "share_qq_" + C2089i.m2275f(C2082b.this.f3263a) + UVCCameraHelper.SUFFIX_JPEG;
            String str2 = C2082b.f3262c + str;
            File file = new File(str2);
            Message messageObtainMessage = C2082b.this.f3266e.obtainMessage();
            if (!file.exists()) {
                Bitmap bitmapM2207a = C2082b.m2207a(C2082b.this.f3263a);
                if (bitmapM2207a != null) {
                    zM2214a = C2082b.this.m2214a(bitmapM2207a, str);
                } else {
                    C2061f.m2127a("AsynLoadImg", "saveFileRunnable:get bmp fail---");
                    zM2214a = false;
                }
                if (zM2214a) {
                    messageObtainMessage.arg1 = 0;
                    messageObtainMessage.obj = str2;
                } else {
                    messageObtainMessage.arg1 = 1;
                }
                C2061f.m2127a("AsynLoadImg", "file not exists: download time:" + (System.currentTimeMillis() - C2082b.this.f3265d));
            } else {
                messageObtainMessage.arg1 = 0;
                messageObtainMessage.obj = str2;
                C2061f.m2127a("AsynLoadImg", "file exists: time:" + (System.currentTimeMillis() - C2082b.this.f3265d));
            }
            C2082b.this.f3266e.sendMessage(messageObtainMessage);
        }
    };

    public C2082b(Activity activity) {
        this.f3266e = new Handler(activity.getMainLooper()) { // from class: com.tencent.open.utils.b.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                C2061f.m2127a("AsynLoadImg", "handleMessage:" + message.arg1);
                if (message.arg1 == 0) {
                    C2082b.this.f3264b.mo2021a(message.arg1, (String) message.obj);
                } else {
                    C2082b.this.f3264b.mo2021a(message.arg1, (String) null);
                }
            }
        };
    }

    /* JADX INFO: renamed from: a */
    public void m2213a(String str, InterfaceC2083c interfaceC2083c) {
        C2061f.m2127a("AsynLoadImg", "--save---");
        if (str == null || str.equals("")) {
            interfaceC2083c.mo2021a(1, (String) null);
            return;
        }
        if (!C2089i.m2265b()) {
            interfaceC2083c.mo2021a(2, (String) null);
            return;
        }
        f3262c = Environment.getExternalStorageDirectory() + "/tmp/";
        this.f3265d = System.currentTimeMillis();
        this.f3263a = str;
        this.f3264b = interfaceC2083c;
        new Thread(this.f3267f).start();
    }

    /* JADX INFO: renamed from: a */
    public boolean m2214a(Bitmap bitmap, String str) throws Throwable {
        String str2 = f3262c;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                File file = new File(str2);
                if (!file.exists()) {
                    file.mkdir();
                }
                C2061f.m2127a("AsynLoadImg", "saveFile:" + str);
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(new File(str2 + str)));
                try {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bufferedOutputStream2);
                    bufferedOutputStream2.flush();
                    try {
                        bufferedOutputStream2.close();
                        return true;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return true;
                    }
                } catch (IOException e2) {
                    e = e2;
                    bufferedOutputStream = bufferedOutputStream2;
                    e.printStackTrace();
                    C2061f.m2131b("AsynLoadImg", "saveFile bmp fail---", e);
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream = bufferedOutputStream2;
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e5) {
            e = e5;
        }
    }

    /* JADX INFO: renamed from: a */
    public static Bitmap m2207a(String str) {
        C2061f.m2127a("AsynLoadImg", "getbitmap:" + str);
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            C2061f.m2127a("AsynLoadImg", "image download finished." + str);
            return bitmapDecodeStream;
        } catch (IOException e) {
            e.printStackTrace();
            C2061f.m2127a("AsynLoadImg", "getbitmap bmp fail---");
            return null;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            C2061f.m2127a("AsynLoadImg", "getbitmap bmp fail---");
            return null;
        }
    }
}
