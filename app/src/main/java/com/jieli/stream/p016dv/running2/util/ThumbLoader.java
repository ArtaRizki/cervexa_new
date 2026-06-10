package com.jieli.stream.p016dv.running2.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.LruCache;
import com.jiangdg.usbcamera.UVCCameraHelper;
import com.jieli.lib.p015dv.control.utils.Dlog;
import com.jieli.media.codec.FrameCodec;
import com.jieli.stream.p016dv.running2.C1438R;
import com.jieli.stream.p016dv.running2.interfaces.OnAviThumbListener;
import com.jieli.stream.p016dv.running2.p017ui.MainApplication;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes.dex */
public class ThumbLoader {
    private static String TAG = "ThumbLoader";
    private static ThumbLoader instance;
    private LruCache<String, Bitmap> mThumbnailCache = new LruCache<String, Bitmap>(((int) (Runtime.getRuntime().maxMemory() / 1024)) / 8) { // from class: com.jieli.stream.dv.running2.util.ThumbLoader.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        public int sizeOf(String str, Bitmap bitmap) {
            return ThumbLoader.this.sizeOfBitmap(bitmap);
        }
    };
    private Map<String, Integer> mDurationMap = new HashMap();

    public interface OnDownloadListener {
        void onResult(boolean z, String str);
    }

    public interface OnLoadThumbListener {
        void onComplete(Bitmap bitmap);
    }

    public interface OnLoadVideoThumbListener {
        void onComplete(Bitmap bitmap, int i);
    }

    private ThumbLoader() {
    }

    public static ThumbLoader getInstance() {
        if (instance == null) {
            synchronized (ThumbLoader.class) {
                if (instance == null) {
                    instance = new ThumbLoader();
                }
            }
        }
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int sizeOfBitmap(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 19) {
            return bitmap.getAllocationByteCount() / 1024;
        }
        return bitmap.getByteCount() / 1024;
    }

    public void loadWebThumbnail(final Context context, final String str, final int i, final int i2, final OnLoadThumbListener onLoadThumbListener) {
        final Handler handler = new Handler(context.getMainLooper());
        if (TextUtils.isEmpty(str)) {
            Bitmap bitmapDecodeResource = this.mThumbnailCache.get(IConstant.DEFAULT_PATH);
            if (bitmapDecodeResource == null) {
                bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), C1438R.mipmap.ic_default_picture);
                this.mThumbnailCache.put(IConstant.DEFAULT_PATH, bitmapDecodeResource);
            }
            handler.post(new OnCompleteRunnable(bitmapDecodeResource, onLoadThumbListener));
            return;
        }
        Bitmap bitmap = this.mThumbnailCache.get(str);
        if (bitmap == null) {
            HttpManager.downloadFile(str, new Callback() { // from class: com.jieli.stream.dv.running2.util.ThumbLoader.2
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    Dlog.m1383e(ThumbLoader.TAG, "-loadThumbnail- error = " + iOException.getMessage() + ", url = " + str);
                    ThumbLoader.this.loadWebThumbnail(context, null, i, i2, onLoadThumbListener);
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    ResponseBody responseBodyBody;
                    if (response.code() == 200 && (responseBodyBody = response.body()) != null) {
                        byte[] bArrBytes = responseBodyBody.bytes();
                        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrBytes, 0, bArrBytes.length);
                        if (bitmapDecodeByteArray != null) {
                            Bitmap bitmapExtractThumbnail = ThumbnailUtils.extractThumbnail(bitmapDecodeByteArray, i, i2, 2);
                            ThumbLoader.this.mThumbnailCache.put(str, bitmapExtractThumbnail);
                            handler.post(ThumbLoader.this.new OnCompleteRunnable(bitmapExtractThumbnail, onLoadThumbListener));
                        } else {
                            ThumbLoader.this.loadWebThumbnail(context, null, i, i2, onLoadThumbListener);
                        }
                    } else {
                        ThumbLoader.this.loadWebThumbnail(context, null, i, i2, onLoadThumbListener);
                    }
                    response.close();
                }
            });
        } else {
            handler.post(new OnCompleteRunnable(bitmap, onLoadThumbListener));
        }
    }

    public void loadWebThumbnail(final Context context, final String str, final String str2, final int i, final int i2, final OnLoadThumbListener onLoadThumbListener) {
        final Handler handler = new Handler(context.getMainLooper());
        if (TextUtils.isEmpty(str)) {
            Bitmap bitmapDecodeResource = this.mThumbnailCache.get(IConstant.DEFAULT_PATH);
            if (bitmapDecodeResource == null) {
                bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), C1438R.mipmap.ic_default_picture);
                this.mThumbnailCache.put(IConstant.DEFAULT_PATH, bitmapDecodeResource);
            }
            handler.post(new OnCompleteRunnable(bitmapDecodeResource, onLoadThumbListener));
            return;
        }
        Bitmap bitmap = this.mThumbnailCache.get(str);
        Dlog.m1386w(TAG, "-loadWebThumbnail- bitmap = " + bitmap + " ,url = " + str);
        if (bitmap == null) {
            HttpManager.downloadFile(str, new Callback() { // from class: com.jieli.stream.dv.running2.util.ThumbLoader.3
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    Dlog.m1383e(ThumbLoader.TAG, "-loadThumbnail- error = " + iOException.getMessage() + ", url = " + str);
                    ThumbLoader.this.loadWebThumbnail(context, null, null, i, i2, onLoadThumbListener);
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    ResponseBody responseBodyBody;
                    if (response.code() == 200 && (responseBodyBody = response.body()) != null) {
                        byte[] bArrBytes = responseBodyBody.bytes();
                        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrBytes, 0, bArrBytes.length);
                        if (bitmapDecodeByteArray != null) {
                            Bitmap bitmapExtractThumbnail = ThumbnailUtils.extractThumbnail(bitmapDecodeByteArray, i, i2, 2);
                            ThumbLoader.this.mThumbnailCache.put(str, bitmapExtractThumbnail);
                            if (AppUtils.bitmapToFile(bitmapExtractThumbnail, str2, 100)) {
                                Dbug.m1387d(ThumbLoader.TAG, "save thumdNail ok");
                            } else {
                                Dbug.m1387d(ThumbLoader.TAG, "save thumdNail fail");
                            }
                            handler.post(ThumbLoader.this.new OnCompleteRunnable(bitmapExtractThumbnail, onLoadThumbListener));
                        } else {
                            ThumbLoader.this.loadWebThumbnail(context, null, null, i, i2, onLoadThumbListener);
                        }
                    } else {
                        ThumbLoader.this.loadWebThumbnail(context, null, null, i, i2, onLoadThumbListener);
                    }
                    response.close();
                }
            });
        } else {
            handler.post(new OnCompleteRunnable(bitmap, onLoadThumbListener));
        }
    }

    private class OnCompleteRunnable implements Runnable {
        private WeakReference<OnLoadThumbListener> listenerReference;
        private WeakReference<Bitmap> weakReference;

        OnCompleteRunnable(Bitmap bitmap, OnLoadThumbListener onLoadThumbListener) {
            this.weakReference = new WeakReference<>(bitmap);
            this.listenerReference = new WeakReference<>(onLoadThumbListener);
        }

        @Override // java.lang.Runnable
        public void run() {
            Bitmap bitmap = this.weakReference.get();
            OnLoadThumbListener onLoadThumbListener = this.listenerReference.get();
            if (bitmap == null || onLoadThumbListener == null) {
                return;
            }
            onLoadThumbListener.onComplete(bitmap);
        }
    }

    public Bitmap loadLocalThumbnail(Context context, String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            Bitmap bitmap = this.mThumbnailCache.get(IConstant.DEFAULT_PATH);
            if (bitmap != null) {
                return bitmap;
            }
            Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), C1438R.mipmap.ic_default_picture);
            this.mThumbnailCache.put(IConstant.DEFAULT_PATH, bitmapDecodeResource);
            return bitmapDecodeResource;
        }
        Bitmap imageThumbnail = this.mThumbnailCache.get(str);
        if (imageThumbnail == null) {
            imageThumbnail = getImageThumbnail(str, i, i2);
            if (imageThumbnail == null) {
                return loadLocalThumbnail(context, null, i, i2);
            }
            this.mThumbnailCache.put(str, imageThumbnail);
        }
        return imageThumbnail;
    }

    public void loadLocalVideoThumb(Context context, String str, int i, int i2, OnLoadVideoThumbListener onLoadVideoThumbListener) {
        String str2;
        Handler handler = new Handler(context.getMainLooper());
        if (TextUtils.isEmpty(str)) {
            Bitmap bitmapDecodeResource = this.mThumbnailCache.get(IConstant.DEFAULT_PATH);
            if (bitmapDecodeResource == null) {
                bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), C1438R.mipmap.ic_default_picture);
                this.mThumbnailCache.put(IConstant.DEFAULT_PATH, bitmapDecodeResource);
            }
            handler.post(new OnLoadVideoThumbRunnable(bitmapDecodeResource, 0, onLoadVideoThumbListener));
            return;
        }
        Bitmap imageThumbnail = this.mThumbnailCache.get(str);
        if (imageThumbnail == null) {
            if (str.contains("/")) {
                str2 = str.split("/")[r1.length - 1];
            } else {
                str2 = "";
            }
            if (!TextUtils.isEmpty(str2)) {
                String strQueryThumbPath = AppUtils.queryThumbPath(str2, AppUtils.splicingFilePath(MainApplication.getApplication().getAppName(), null, null, null));
                if (!TextUtils.isEmpty(strQueryThumbPath)) {
                    imageThumbnail = getImageThumbnail(strQueryThumbPath, i, i2);
                }
                if (imageThumbnail != null) {
                    int thumbPathForDuration = AppUtils.parseThumbPathForDuration(strQueryThumbPath);
                    this.mThumbnailCache.put(str, imageThumbnail);
                    this.mDurationMap.put(str, Integer.valueOf(thumbPathForDuration));
                    handler.post(new OnLoadVideoThumbRunnable(imageThumbnail, thumbPathForDuration, onLoadVideoThumbListener));
                    return;
                }
                if (checkIsAvi(str)) {
                    getThumbForAvi(context, str, i, i2, handler, onLoadVideoThumbListener);
                    return;
                } else {
                    getThumbFromMov(context, str, i, i2, handler, onLoadVideoThumbListener);
                    return;
                }
            }
            loadLocalVideoThumb(context, null, i, i2, onLoadVideoThumbListener);
            return;
        }
        Integer num = this.mDurationMap.get(str);
        if (num == null) {
            num = 0;
        }
        handler.post(new OnLoadVideoThumbRunnable(imageThumbnail, num.intValue(), onLoadVideoThumbListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String replaceFilePath(String str, int i) {
        String strSubstring = "";
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iLastIndexOf = str.lastIndexOf("/");
        if (iLastIndexOf != -1) {
            String strSubstring2 = str.substring(0, iLastIndexOf);
            strSubstring = str.substring(iLastIndexOf);
            str = strSubstring2;
        }
        String strReplace = str.replace(IConstant.DIR_DOWNLOAD, IConstant.DIR_THUMB).replace(IConstant.DIR_RECORD, IConstant.DIR_THUMB);
        File file = new File(strReplace);
        if (!file.exists()) {
            file.mkdir();
        }
        if (TextUtils.isEmpty(strSubstring)) {
            return strReplace;
        }
        int iLastIndexOf2 = strSubstring.lastIndexOf(".");
        if (iLastIndexOf2 != -1) {
            strSubstring = strSubstring.substring(0, iLastIndexOf2);
        }
        return strReplace + File.separator + strSubstring + "_" + i + UVCCameraHelper.SUFFIX_JPEG;
    }

    private class OnLoadVideoThumbRunnable implements Runnable {
        private WeakReference<Bitmap> bitmapWeakReference;
        private int duration;
        private WeakReference<OnLoadVideoThumbListener> weakReference;

        OnLoadVideoThumbRunnable(Bitmap bitmap, int i, OnLoadVideoThumbListener onLoadVideoThumbListener) {
            this.duration = i;
            this.bitmapWeakReference = new WeakReference<>(bitmap);
            this.weakReference = new WeakReference<>(onLoadVideoThumbListener);
        }

        @Override // java.lang.Runnable
        public void run() {
            Bitmap bitmap = this.bitmapWeakReference.get();
            OnLoadVideoThumbListener onLoadVideoThumbListener = this.weakReference.get();
            if (bitmap == null || onLoadVideoThumbListener == null) {
                return;
            }
            onLoadVideoThumbListener.onComplete(bitmap, this.duration);
        }
    }

    public void downloadWebImage(Context context, String str, final String str2, final OnDownloadListener onDownloadListener) {
        final Handler handler = new Handler(context.getMainLooper());
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        File file = new File(str2);
        if (file.exists() && file.isFile() && file.delete()) {
            Dbug.m1391w(TAG, "delete file ok!");
        }
        HttpManager.downloadFile(str, new Callback() { // from class: com.jieli.stream.dv.running2.util.ThumbLoader.4
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Dlog.m1383e(ThumbLoader.TAG, "-downloadWebImage- onErrorResponse = " + iOException.getMessage());
                handler.post(ThumbLoader.this.new OnResultRunnable(false, str2, onDownloadListener));
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBodyBody;
                if (response.code() == 200 && (responseBodyBody = response.body()) != null) {
                    byte[] bArrBytes = responseBodyBody.bytes();
                    Dbug.m1389i(ThumbLoader.TAG, "downloadWebImage:saveUrl=" + str2);
                    if (AppUtils.bytesToFile(bArrBytes, str2)) {
                        handler.post(ThumbLoader.this.new OnResultRunnable(true, str2, onDownloadListener));
                    }
                }
                response.close();
            }
        });
    }

    private class OnResultRunnable implements Runnable {
        private String obj;
        private boolean result;
        private WeakReference<OnDownloadListener> weakReference;

        OnResultRunnable(boolean z, String str, OnDownloadListener onDownloadListener) {
            this.result = z;
            this.obj = str;
            this.weakReference = new WeakReference<>(onDownloadListener);
        }

        @Override // java.lang.Runnable
        public void run() {
            OnDownloadListener onDownloadListener = this.weakReference.get();
            if (onDownloadListener != null) {
                onDownloadListener.onResult(this.result, this.obj);
            }
        }
    }

    private Bitmap getImageThumbnail(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        int i3 = options.outHeight;
        int i4 = options.outWidth / i;
        int i5 = i3 / i2;
        if (i4 >= i5) {
            i4 = i5;
        }
        options.inSampleSize = i4 > 0 ? i4 : 1;
        return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(str, options), i, i2, 2);
    }

    public void addBitmap(String str, Bitmap bitmap) {
        LruCache<String, Bitmap> lruCache;
        if (TextUtils.isEmpty(str) || bitmap == null || (lruCache = this.mThumbnailCache) == null) {
            return;
        }
        lruCache.put(str, bitmap);
    }

    public Bitmap getBitmap(String str) {
        LruCache<String, Bitmap> lruCache = this.mThumbnailCache;
        if (lruCache != null) {
            return lruCache.get(str);
        }
        return null;
    }

    public void removeBitmap(String str) {
        Bitmap bitmapRemove;
        LruCache<String, Bitmap> lruCache = this.mThumbnailCache;
        if (lruCache == null || (bitmapRemove = lruCache.remove(str)) == null || bitmapRemove.isRecycled()) {
            return;
        }
        bitmapRemove.recycle();
    }

    public void clearCache() {
        LruCache<String, Bitmap> lruCache = this.mThumbnailCache;
        if (lruCache != null) {
            lruCache.evictAll();
        }
        Map<String, Integer> map = this.mDurationMap;
        if (map != null) {
            map.clear();
        }
        System.gc();
    }

    public void release() {
        instance = null;
        clearCache();
    }

    private boolean checkIsAvi(String str) {
        return !TextUtils.isEmpty(str) && (str.endsWith(".AVI") || str.endsWith(".avi"));
    }

    private void getThumbForAvi(final Context context, final String str, final int i, final int i2, final Handler handler, final OnLoadVideoThumbListener onLoadVideoThumbListener) {
        AviThumbUtil.getRecordVideoThumb(str, new OnAviThumbListener() { // from class: com.jieli.stream.dv.running2.util.ThumbLoader.5
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:21:0x004d  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00d2  */
            /* JADX WARN: Removed duplicated region for block: B:38:0x00e5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Type inference failed for: r0v19 */
            /* JADX WARN: Type inference failed for: r0v3, types: [java.io.ByteArrayInputStream] */
            /* JADX WARN: Type inference failed for: r0v4 */
            /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r1v17 */
            /* JADX WARN: Type inference failed for: r1v18, types: [java.io.ByteArrayInputStream] */
            /* JADX WARN: Type inference failed for: r1v19 */
            /* JADX WARN: Type inference failed for: r1v2 */
            /* JADX WARN: Type inference failed for: r1v20, types: [java.io.ByteArrayInputStream, java.io.InputStream] */
            /* JADX WARN: Type inference failed for: r1v21 */
            /* JADX WARN: Type inference failed for: r1v22 */
            /* JADX WARN: Type inference failed for: r1v3 */
            /* JADX WARN: Type inference failed for: r1v4 */
            @Override // com.jieli.stream.p016dv.running2.interfaces.OnAviThumbListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onCompleted(byte[] r8, com.jieli.media.codec.bean.MediaMeta r9) {
                /*
                    Method dump skipped, instruction units count: 239
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jieli.stream.p016dv.running2.util.ThumbLoader.C17665.onCompleted(byte[], com.jieli.media.codec.bean.MediaMeta):void");
            }

            @Override // com.jieli.stream.p016dv.running2.interfaces.OnAviThumbListener
            public void onError(String str2) {
                ThumbLoader.this.loadLocalVideoThumb(context, null, i, i2, onLoadVideoThumbListener);
            }
        });
    }

    private void getThumbFromMov(final Context context, final String str, final int i, final int i2, final Handler handler, final OnLoadVideoThumbListener onLoadVideoThumbListener) {
        FrameCodec frameCodec = new FrameCodec();
        frameCodec.setOnFrameCodecListener(new FrameCodec.OnFrameCodecListener() { // from class: com.jieli.stream.dv.running2.util.ThumbLoader.6
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:21:0x004d  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00d4  */
            /* JADX WARN: Type inference failed for: r0v19 */
            /* JADX WARN: Type inference failed for: r0v2 */
            /* JADX WARN: Type inference failed for: r0v3, types: [java.io.ByteArrayInputStream] */
            /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r1v19 */
            /* JADX WARN: Type inference failed for: r1v2 */
            /* JADX WARN: Type inference failed for: r1v20, types: [java.io.ByteArrayInputStream, java.io.InputStream] */
            /* JADX WARN: Type inference failed for: r1v21 */
            /* JADX WARN: Type inference failed for: r1v22 */
            /* JADX WARN: Type inference failed for: r1v3 */
            /* JADX WARN: Type inference failed for: r1v4 */
            /* JADX WARN: Type inference failed for: r1v5, types: [java.io.ByteArrayInputStream] */
            /* JADX WARN: Type inference failed for: r1v6 */
            @Override // com.jieli.media.codec.FrameCodec.OnFrameCodecListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onCompleted(byte[] r8, com.jieli.media.codec.bean.MediaMeta r9) {
                /*
                    Method dump skipped, instruction units count: 241
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jieli.stream.p016dv.running2.util.ThumbLoader.C17676.onCompleted(byte[], com.jieli.media.codec.bean.MediaMeta):void");
            }

            @Override // com.jieli.media.codec.FrameCodec.OnFrameCodecListener
            public void onError(String str2) {
                ThumbLoader.this.loadLocalVideoThumb(context, null, i, i2, onLoadVideoThumbListener);
            }
        });
        try {
            try {
                frameCodec.convertToJPG(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            frameCodec.destroy();
        }
    }
}
