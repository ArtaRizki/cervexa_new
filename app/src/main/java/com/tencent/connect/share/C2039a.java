package com.tencent.connect.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.jiangdg.usbcamera.UVCCameraHelper;
import com.serenegiant.usb.UVCCamera;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.utils.C2089i;
import com.tencent.open.utils.InterfaceC2083c;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.tencent.connect.share.a */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2039a {
    /* JADX INFO: renamed from: a */
    public static final void m2031a(Context context, final String str, final InterfaceC2083c interfaceC2083c) {
        C2061f.m2130b("openSDK_LOG.AsynScaleCompressImage", "scaleCompressImage");
        if (TextUtils.isEmpty(str)) {
            interfaceC2083c.mo2021a(1, (String) null);
        } else if (!C2089i.m2265b()) {
            interfaceC2083c.mo2021a(2, (String) null);
        } else {
            final Handler handler = new Handler(context.getMainLooper()) { // from class: com.tencent.connect.share.a.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    int i = message.what;
                    if (i == 101) {
                        interfaceC2083c.mo2021a(0, (String) message.obj);
                    } else if (i == 102) {
                        interfaceC2083c.mo2021a(message.arg1, (String) null);
                    } else {
                        super.handleMessage(message);
                    }
                }
            };
            new Thread(new Runnable() { // from class: com.tencent.connect.share.a.2
                @Override // java.lang.Runnable
                public void run() {
                    String strM2030a;
                    Bitmap bitmapM2029a = C2039a.m2029a(str, 140);
                    if (bitmapM2029a != null) {
                        String str2 = Environment.getExternalStorageDirectory() + "/tmp/";
                        String str3 = "share2qq_temp" + C2089i.m2275f(str) + UVCCameraHelper.SUFFIX_JPEG;
                        if (!C2039a.m2035b(str, 140, 140)) {
                            C2061f.m2130b("openSDK_LOG.AsynScaleCompressImage", "not out of bound,not compress!");
                            strM2030a = str;
                        } else {
                            C2061f.m2130b("openSDK_LOG.AsynScaleCompressImage", "out of bound,compress!");
                            strM2030a = C2039a.m2030a(bitmapM2029a, str2, str3);
                        }
                        C2061f.m2130b("openSDK_LOG.AsynScaleCompressImage", "-->destFilePath: " + strM2030a);
                        if (strM2030a != null) {
                            Message messageObtainMessage = handler.obtainMessage(101);
                            messageObtainMessage.obj = strM2030a;
                            handler.sendMessage(messageObtainMessage);
                            return;
                        }
                    }
                    Message messageObtainMessage2 = handler.obtainMessage(102);
                    messageObtainMessage2.arg1 = 3;
                    handler.sendMessage(messageObtainMessage2);
                }
            }).start();
        }
    }

    /* JADX INFO: renamed from: a */
    public static final void m2032a(Context context, final ArrayList<String> arrayList, final InterfaceC2083c interfaceC2083c) {
        C2061f.m2130b("openSDK_LOG.AsynScaleCompressImage", "batchScaleCompressImage");
        if (arrayList == null) {
            interfaceC2083c.mo2021a(1, (String) null);
        } else {
            final Handler handler = new Handler(context.getMainLooper()) { // from class: com.tencent.connect.share.a.3
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    if (message.what == 101) {
                        interfaceC2083c.mo2022a(0, message.getData().getStringArrayList("images"));
                    } else {
                        super.handleMessage(message);
                    }
                }
            };
            new Thread(new Runnable() { // from class: com.tencent.connect.share.a.4
                @Override // java.lang.Runnable
                public void run() {
                    Bitmap bitmapM2029a;
                    for (int i = 0; i < arrayList.size(); i++) {
                        String strM2030a = (String) arrayList.get(i);
                        if (!C2089i.m2278g(strM2030a) && C2089i.m2279h(strM2030a) && (bitmapM2029a = C2039a.m2029a(strM2030a, 10000)) != null) {
                            String str = Environment.getExternalStorageDirectory() + "/tmp/";
                            String str2 = "share2qzone_temp" + C2089i.m2275f(strM2030a) + UVCCameraHelper.SUFFIX_JPEG;
                            if (!C2039a.m2035b(strM2030a, UVCCamera.DEFAULT_PREVIEW_WIDTH, 10000)) {
                                C2061f.m2130b("openSDK_LOG.AsynScaleCompressImage", "not out of bound,not compress!");
                            } else {
                                C2061f.m2130b("openSDK_LOG.AsynScaleCompressImage", "out of bound, compress!");
                                strM2030a = C2039a.m2030a(bitmapM2029a, str, str2);
                            }
                            if (strM2030a != null) {
                                arrayList.set(i, strM2030a);
                            }
                        }
                    }
                    Message messageObtainMessage = handler.obtainMessage(101);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("images", arrayList);
                    messageObtainMessage.setData(bundle);
                    handler.sendMessage(messageObtainMessage);
                }
            }).start();
        }
    }

    /* JADX INFO: renamed from: a */
    private static Bitmap m2028a(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            width = height;
        }
        float f = i / width;
        matrix.postScale(f, f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* JADX INFO: renamed from: a */
    protected static final String m2030a(Bitmap bitmap, String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.append(str2);
        String string = stringBuffer.toString();
        File file2 = new File(string);
        if (file2.exists()) {
            file2.delete();
        }
        if (bitmap == null) {
            return null;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            bitmap.recycle();
            return string;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public static final boolean m2035b(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        if (options.mCancel || options.outWidth == -1 || options.outHeight == -1) {
            return false;
        }
        int i5 = i3 > i4 ? i3 : i4;
        if (i3 >= i4) {
            i3 = i4;
        }
        C2061f.m2130b("openSDK_LOG.AsynScaleCompressImage", "longSide=" + i5 + "shortSide=" + i3);
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return i5 > i2 || i3 > i;
    }

    /* JADX INFO: renamed from: a */
    public static final Bitmap m2029a(String str, int i) {
        Bitmap bitmapDecodeFile;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        if (options.mCancel || options.outWidth == -1 || options.outHeight == -1) {
            return null;
        }
        if (i2 <= i3) {
            i2 = i3;
        }
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        if (i2 > i) {
            options.inSampleSize = m2027a(options, -1, i * i);
        }
        options.inJustDecodeBounds = false;
        try {
            bitmapDecodeFile = BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            bitmapDecodeFile = null;
        }
        if (bitmapDecodeFile == null) {
            return null;
        }
        int i4 = options.outWidth;
        int i5 = options.outHeight;
        if (i4 <= i5) {
            i4 = i5;
        }
        return i4 > i ? m2028a(bitmapDecodeFile, i) : bitmapDecodeFile;
    }

    /* JADX INFO: renamed from: a */
    public static final int m2027a(BitmapFactory.Options options, int i, int i2) {
        int iM2034b = m2034b(options, i, i2);
        if (iM2034b > 8) {
            return ((iM2034b + 7) / 8) * 8;
        }
        int i3 = 1;
        while (i3 < iM2034b) {
            i3 <<= 1;
        }
        return i3;
    }

    /* JADX INFO: renamed from: b */
    private static int m2034b(BitmapFactory.Options options, int i, int i2) {
        int iMin;
        double d = options.outWidth;
        double d2 = options.outHeight;
        int iCeil = i2 == -1 ? 1 : (int) Math.ceil(Math.sqrt((d * d2) / ((double) i2)));
        if (i == -1) {
            iMin = 128;
        } else {
            double d3 = i;
            iMin = (int) Math.min(Math.floor(d / d3), Math.floor(d2 / d3));
        }
        if (iMin < iCeil) {
            return iCeil;
        }
        if (i2 == -1 && i == -1) {
            return 1;
        }
        return i == -1 ? iCeil : iMin;
    }
}
