package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
public final class BitmapDescriptor {

    /* JADX INFO: renamed from: a */
    Bitmap f295a;

    /* JADX INFO: renamed from: b */
    private Bundle f296b;

    BitmapDescriptor(Bitmap bitmap) {
        if (bitmap != null) {
            this.f295a = m278a(bitmap, bitmap.getWidth(), bitmap.getHeight());
        }
    }

    /* JADX INFO: renamed from: a */
    private Bitmap m278a(Bitmap bitmap, int i, int i2) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return bitmapCreateBitmap;
    }

    /* JADX INFO: renamed from: a */
    byte[] m279a() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(this.f295a.getWidth() * this.f295a.getHeight() * 4);
        this.f295a.copyPixelsToBuffer(byteBufferAllocate);
        return byteBufferAllocate.array();
    }

    /* JADX INFO: renamed from: b */
    Bundle m280b() {
        if (this.f295a == null) {
            throw new IllegalStateException("the bitmap has been recycled! you can not use it again");
        }
        if (this.f296b == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("image_width", this.f295a.getWidth());
            bundle.putInt("image_height", this.f295a.getHeight());
            byte[] bArrM279a = m279a();
            bundle.putByteArray("image_data", bArrM279a);
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            messageDigest.update(bArrM279a, 0, bArrM279a.length);
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder("");
            for (byte b : bArrDigest) {
                sb.append(Integer.toString((b & UByte.MAX_VALUE) + 256, 16).substring(1));
            }
            bundle.putString("image_hashcode", sb.toString());
            this.f296b = bundle;
        }
        return this.f296b;
    }

    public Bitmap getBitmap() {
        return this.f295a;
    }

    public void recycle() {
        Bitmap bitmap = this.f295a;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.f295a.recycle();
        this.f295a = null;
    }
}
