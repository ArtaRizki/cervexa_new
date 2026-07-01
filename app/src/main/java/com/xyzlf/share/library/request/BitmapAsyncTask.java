package com.xyzlf.share.library.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.serenegiant.usb.UVCCamera;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class BitmapAsyncTask extends AbstractAsyncTask<Bitmap> {
    private OnBitmapListener listener;
    private String urlStr;

    public interface OnBitmapListener {
        void onException(Exception exc);

        void onSuccess(Bitmap bitmap);
    }

    public BitmapAsyncTask(String str, OnBitmapListener onBitmapListener) {
        this.urlStr = str;
        this.listener = onBitmapListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.xyzlf.share.library.request.AbstractAsyncTask
    public Bitmap doLoadData() throws Exception {
        InputStream inputStreamOpenStream = new URL(this.urlStr).openStream();
        Bitmap sampleBitmap = getSampleBitmap(inputStreamOpenStream, UVCCamera.DEFAULT_PREVIEW_WIDTH, UVCCamera.DEFAULT_PREVIEW_WIDTH);
        inputStreamOpenStream.close();
        return sampleBitmap;
    }

    private Bitmap getSampleBitmap(InputStream inputStream, int i, int i2) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        bufferedInputStream.mark(4096);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(bufferedInputStream, null, options);
        calculateInSampleSize(i, i2, options, true);
        try {
            bufferedInputStream.reset();
        } catch (IOException unused) {
        }
        return BitmapFactory.decodeStream(bufferedInputStream, null, options);
    }

    static void calculateInSampleSize(int i, int i2, BitmapFactory.Options options, boolean z) {
        calculateInSampleSize(i, i2, options.outWidth, options.outHeight, options, z);
    }

    static void calculateInSampleSize(int i, int i2, int i3, int i4, BitmapFactory.Options options, boolean z) {
        int iMin;
        if (i4 > i2 || i3 > i) {
            if (i2 == 0) {
                iMin = (int) Math.floor(i3 / i);
            } else if (i == 0) {
                iMin = (int) Math.floor(i4 / i2);
            } else {
                int iFloor = (int) Math.floor(i4 / i2);
                int iFloor2 = (int) Math.floor(i3 / i);
                if (z) {
                    iMin = Math.max(iFloor, iFloor2);
                } else {
                    iMin = Math.min(iFloor, iFloor2);
                }
            }
        } else {
            iMin = 1;
        }
        options.inSampleSize = iMin;
        options.inJustDecodeBounds = false;
    }

    @Override // com.xyzlf.share.library.request.AbstractAsyncTask
    public void onSuccess(Bitmap bitmap) {
        super.onSuccess(bitmap);
        OnBitmapListener onBitmapListener = this.listener;
        if (onBitmapListener != null) {
            onBitmapListener.onSuccess(bitmap);
        }
    }

    @Override // com.xyzlf.share.library.request.AbstractAsyncTask
    public void onException(Exception exc) {
        super.onException(exc);
        OnBitmapListener onBitmapListener = this.listener;
        if (onBitmapListener != null) {
            onBitmapListener.onException(exc);
        }
    }

    @Override // com.xyzlf.share.library.request.AbstractAsyncTask
    public void onFinally() {
        super.onFinally();
    }
}
