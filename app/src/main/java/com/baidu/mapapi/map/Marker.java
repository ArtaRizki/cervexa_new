package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.ParcelItem;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.EnumC0749h;
import com.jieli.lib.p015dv.control.utils.Constants;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
public final class Marker extends Overlay {

    /* JADX INFO: renamed from: a */
    LatLng f450a;

    /* JADX INFO: renamed from: b */
    BitmapDescriptor f451b;

    /* JADX INFO: renamed from: c */
    float f452c;

    /* JADX INFO: renamed from: d */
    float f453d;

    /* JADX INFO: renamed from: e */
    boolean f454e;

    /* JADX INFO: renamed from: f */
    boolean f455f;

    /* JADX INFO: renamed from: g */
    float f456g;

    /* JADX INFO: renamed from: h */
    String f457h;

    /* JADX INFO: renamed from: i */
    int f458i;

    /* JADX INFO: renamed from: l */
    float f461l;

    /* JADX INFO: renamed from: m */
    int f462m;

    /* JADX INFO: renamed from: n */
    ArrayList<BitmapDescriptor> f463n;

    /* JADX INFO: renamed from: j */
    boolean f459j = false;

    /* JADX INFO: renamed from: k */
    boolean f460k = false;

    /* JADX INFO: renamed from: o */
    int f464o = 20;

    Marker() {
        this.type = EnumC0749h.marker;
    }

    /* JADX INFO: renamed from: a */
    private void m335a(ArrayList<BitmapDescriptor> arrayList, Bundle bundle) {
        int i;
        ArrayList arrayList2 = new ArrayList();
        Iterator<BitmapDescriptor> it = arrayList.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            BitmapDescriptor next = it.next();
            ParcelItem parcelItem = new ParcelItem();
            Bundle bundle2 = new Bundle();
            Bitmap bitmap = next.f295a;
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getWidth() * bitmap.getHeight() * 4);
            bitmap.copyPixelsToBuffer(byteBufferAllocate);
            byte[] bArrArray = byteBufferAllocate.array();
            bundle2.putByteArray("image_data", bArrArray);
            bundle2.putInt("image_width", bitmap.getWidth());
            bundle2.putInt("image_height", bitmap.getHeight());
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            messageDigest.update(bArrArray, 0, bArrArray.length);
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder("");
            while (i < bArrDigest.length) {
                sb.append(Integer.toString((bArrDigest[i] & UByte.MAX_VALUE) + 256, 16).substring(1));
                i++;
            }
            bundle2.putString("image_hashcode", sb.toString());
            parcelItem.setBundle(bundle2);
            arrayList2.add(parcelItem);
        }
        if (arrayList2.size() > 0) {
            ParcelItem[] parcelItemArr = new ParcelItem[arrayList2.size()];
            while (i < arrayList2.size()) {
                parcelItemArr[i] = (ParcelItem) arrayList2.get(i);
                i++;
            }
            bundle.putParcelableArray("icons", parcelItemArr);
        }
    }

    @Override // com.baidu.mapapi.map.Overlay
    /* JADX INFO: renamed from: a */
    Bundle mo237a(Bundle bundle) {
        super.mo237a(bundle);
        Bundle bundle2 = new Bundle();
        BitmapDescriptor bitmapDescriptor = this.f451b;
        if (bitmapDescriptor != null) {
            bundle.putBundle("image_info", bitmapDescriptor.m280b());
        }
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(this.f450a);
        bundle.putInt("animatetype", this.f462m);
        bundle.putDouble("location_x", geoPointLl2mc.getLongitudeE6());
        bundle.putDouble("location_y", geoPointLl2mc.getLatitudeE6());
        bundle.putInt("perspective", this.f454e ? 1 : 0);
        bundle.putFloat("anchor_x", this.f452c);
        bundle.putFloat("anchor_y", this.f453d);
        bundle.putFloat("rotate", this.f456g);
        bundle.putInt("y_offset", this.f458i);
        bundle.putInt("isflat", this.f459j ? 1 : 0);
        bundle.putInt("istop", this.f460k ? 1 : 0);
        bundle.putInt("period", this.f464o);
        bundle.putFloat("alpha", this.f461l);
        ArrayList<BitmapDescriptor> arrayList = this.f463n;
        if (arrayList != null && arrayList.size() > 0) {
            m335a(this.f463n, bundle);
        }
        bundle2.putBundle(Constants.JSON_PARAM, bundle);
        return bundle;
    }

    public float getAlpha() {
        return this.f461l;
    }

    public float getAnchorX() {
        return this.f452c;
    }

    public float getAnchorY() {
        return this.f453d;
    }

    public BitmapDescriptor getIcon() {
        return this.f451b;
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        return this.f463n;
    }

    public int getPeriod() {
        return this.f464o;
    }

    public LatLng getPosition() {
        return this.f450a;
    }

    public float getRotate() {
        return this.f456g;
    }

    public String getTitle() {
        return this.f457h;
    }

    public boolean isDraggable() {
        return this.f455f;
    }

    public boolean isFlat() {
        return this.f459j;
    }

    public boolean isPerspective() {
        return this.f454e;
    }

    public void setAlpha(float f) {
        if (f < 0.0f || f > 1.0d) {
            this.f461l = 1.0f;
        } else {
            this.f461l = f;
            this.listener.mo342b(this);
        }
    }

    public void setAnchor(float f, float f2) {
        if (f < 0.0f || f > 1.0f || f2 < 0.0f || f2 > 1.0f) {
            return;
        }
        this.f452c = f;
        this.f453d = f2;
        this.listener.mo342b(this);
    }

    public void setDraggable(boolean z) {
        this.f455f = z;
        this.listener.mo342b(this);
    }

    public void setFlat(boolean z) {
        this.f459j = z;
        this.listener.mo342b(this);
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            throw new IllegalArgumentException("marker's icon can not be null");
        }
        this.f451b = bitmapDescriptor;
        this.listener.mo342b(this);
    }

    public void setIcons(ArrayList<BitmapDescriptor> arrayList) {
        if (arrayList == null) {
            throw new IllegalArgumentException("marker's icons can not be null");
        }
        if (arrayList.size() == 0) {
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) == null || arrayList.get(i).f295a == null) {
                return;
            }
        }
        this.f463n = arrayList;
        this.listener.mo342b(this);
    }

    public void setPeriod(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("marker's period must be greater than zero ");
        }
        this.f464o = i;
        this.listener.mo342b(this);
    }

    public void setPerspective(boolean z) {
        this.f454e = z;
        this.listener.mo342b(this);
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("marker's position can not be null");
        }
        this.f450a = latLng;
        this.listener.mo342b(this);
    }

    public void setRotate(float f) {
        while (f < 0.0f) {
            f += 360.0f;
        }
        this.f456g = f % 360.0f;
        this.listener.mo342b(this);
    }

    public void setTitle(String str) {
        this.f457h = str;
    }

    public void setToTop() {
        this.f460k = true;
        this.listener.mo342b(this);
    }
}
