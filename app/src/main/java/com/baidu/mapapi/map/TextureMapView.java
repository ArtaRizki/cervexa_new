package com.baidu.mapapi.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.UIMsg;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.platform.comapi.commonutils.C0724a;
import com.baidu.platform.comapi.map.C0750i;
import com.baidu.platform.comapi.map.GestureDetectorOnDoubleTapListenerC0732F;
import com.baidu.platform.comapi.map.InterfaceC0753l;
import com.baidu.platform.comapi.map.ViewOnTouchListenerC0741N;
import com.gizthon.camera.core.OnCameraConnectedListener;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class TextureMapView extends ViewGroup {

    /* JADX INFO: renamed from: a */
    private static final String f573a = TextureMapView.class.getSimpleName();

    /* JADX INFO: renamed from: i */
    private static String f574i;

    /* JADX INFO: renamed from: n */
    private static final SparseArray<Integer> f575n;

    /* JADX INFO: renamed from: b */
    private GestureDetectorOnDoubleTapListenerC0732F f576b;

    /* JADX INFO: renamed from: c */
    private BaiduMap f577c;

    /* JADX INFO: renamed from: d */
    private ImageView f578d;

    /* JADX INFO: renamed from: e */
    private Bitmap f579e;

    /* JADX INFO: renamed from: f */
    private ViewOnTouchListenerC0741N f580f;

    /* JADX INFO: renamed from: g */
    private Point f581g;

    /* JADX INFO: renamed from: h */
    private Point f582h;

    /* JADX INFO: renamed from: j */
    private RelativeLayout f583j;

    /* JADX INFO: renamed from: k */
    private TextView f584k;

    /* JADX INFO: renamed from: l */
    private TextView f585l;

    /* JADX INFO: renamed from: m */
    private ImageView f586m;

    /* JADX INFO: renamed from: o */
    private float f587o;

    /* JADX INFO: renamed from: p */
    private InterfaceC0753l f588p;

    /* JADX INFO: renamed from: q */
    private int f589q;

    /* JADX INFO: renamed from: r */
    private boolean f590r;

    /* JADX INFO: renamed from: s */
    private boolean f591s;

    /* JADX INFO: renamed from: t */
    private int f592t;

    /* JADX INFO: renamed from: u */
    private int f593u;

    /* JADX INFO: renamed from: v */
    private int f594v;

    /* JADX INFO: renamed from: w */
    private int f595w;

    /* JADX INFO: renamed from: x */
    private int f596x;

    /* JADX INFO: renamed from: y */
    private int f597y;

    static {
        SparseArray<Integer> sparseArray = new SparseArray<>();
        f575n = sparseArray;
        sparseArray.append(3, 2000000);
        f575n.append(4, 1000000);
        f575n.append(5, 500000);
        f575n.append(6, 200000);
        f575n.append(7, 100000);
        f575n.append(8, 50000);
        f575n.append(9, 25000);
        f575n.append(10, Integer.valueOf(OnCameraConnectedListener.ADMIN_ACTIVE));
        f575n.append(11, 10000);
        f575n.append(12, 5000);
        f575n.append(13, Integer.valueOf(UIMsg.m_AppUI.MSG_APP_DATA_OK));
        f575n.append(14, 1000);
        f575n.append(15, 500);
        f575n.append(16, 200);
        f575n.append(17, 100);
        f575n.append(18, 50);
        f575n.append(19, 20);
        f575n.append(20, 10);
        f575n.append(21, 5);
        f575n.append(22, 2);
    }

    public TextureMapView(Context context) {
        super(context);
        this.f589q = LogoPosition.logoPostionleftBottom.ordinal();
        this.f590r = true;
        this.f591s = true;
        m358a(context, (BaiduMapOptions) null);
    }

    public TextureMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f589q = LogoPosition.logoPostionleftBottom.ordinal();
        this.f590r = true;
        this.f591s = true;
        m358a(context, (BaiduMapOptions) null);
    }

    public TextureMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f589q = LogoPosition.logoPostionleftBottom.ordinal();
        this.f590r = true;
        this.f591s = true;
        m358a(context, (BaiduMapOptions) null);
    }

    public TextureMapView(Context context, BaiduMapOptions baiduMapOptions) {
        super(context);
        this.f589q = LogoPosition.logoPostionleftBottom.ordinal();
        this.f590r = true;
        this.f591s = true;
        m358a(context, baiduMapOptions);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m357a(android.content.Context r10) {
        /*
            r9 = this;
            int r0 = com.baidu.mapapi.common.SysOSUtil.getDensityDpi()
            r1 = 180(0xb4, float:2.52E-43)
            if (r0 >= r1) goto Lb
            java.lang.String r1 = "logo_l.png"
            goto Ld
        Lb:
            java.lang.String r1 = "logo_h.png"
        Ld:
            android.graphics.Bitmap r2 = com.baidu.platform.comapi.commonutils.C0724a.m535a(r1, r10)
            r1 = 480(0x1e0, float:6.73E-43)
            if (r0 <= r1) goto L31
            android.graphics.Matrix r7 = new android.graphics.Matrix
            r7.<init>()
            r0 = 1073741824(0x40000000, float:2.0)
        L1c:
            r7.postScale(r0, r0)
            r3 = 0
            r4 = 0
            int r5 = r2.getWidth()
            int r6 = r2.getHeight()
            r8 = 1
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r2, r3, r4, r5, r6, r7, r8)
            r9.f579e = r0
            goto L41
        L31:
            r3 = 320(0x140, float:4.48E-43)
            if (r0 <= r3) goto L3f
            if (r0 > r1) goto L3f
            android.graphics.Matrix r7 = new android.graphics.Matrix
            r7.<init>()
            r0 = 1069547520(0x3fc00000, float:1.5)
            goto L1c
        L3f:
            r9.f579e = r2
        L41:
            android.graphics.Bitmap r0 = r9.f579e
            if (r0 == 0) goto L56
            android.widget.ImageView r0 = new android.widget.ImageView
            r0.<init>(r10)
            r9.f578d = r0
            android.graphics.Bitmap r10 = r9.f579e
            r0.setImageBitmap(r10)
            android.widget.ImageView r10 = r9.f578d
            r9.addView(r10)
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.TextureMapView.m357a(android.content.Context):void");
    }

    /* JADX INFO: renamed from: a */
    private void m358a(Context context, BaiduMapOptions baiduMapOptions) {
        setBackgroundColor(-1);
        C0750i.m706a();
        BMapManager.init();
        m359a(context, baiduMapOptions, f574i);
        this.f577c = new BaiduMap(this.f576b);
        m357a(context);
        m363b(context);
        if (baiduMapOptions != null && !baiduMapOptions.f290h) {
            this.f580f.setVisibility(4);
        }
        m365c(context);
        if (baiduMapOptions != null && !baiduMapOptions.f291i) {
            this.f583j.setVisibility(4);
        }
        if (baiduMapOptions != null && baiduMapOptions.f292j != null) {
            this.f589q = baiduMapOptions.f292j.ordinal();
        }
        if (baiduMapOptions != null && baiduMapOptions.f294l != null) {
            this.f582h = baiduMapOptions.f294l;
        }
        if (baiduMapOptions == null || baiduMapOptions.f293k == null) {
            return;
        }
        this.f581g = baiduMapOptions.f293k;
    }

    /* JADX INFO: renamed from: a */
    private void m359a(Context context, BaiduMapOptions baiduMapOptions, String str) {
        f574i = str;
        if (baiduMapOptions == null) {
            this.f576b = new GestureDetectorOnDoubleTapListenerC0732F(context, null, str);
        } else {
            this.f576b = new GestureDetectorOnDoubleTapListenerC0732F(context, baiduMapOptions.m277a(), str);
        }
        addView(this.f576b);
        this.f588p = new C0695q(this);
        this.f576b.m585b().m645a(this.f588p);
    }

    /* JADX INFO: renamed from: a */
    private void m360a(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-2, -2);
        }
        int i = layoutParams.width;
        int iMakeMeasureSpec = i > 0 ? View.MeasureSpec.makeMeasureSpec(i, BasicMeasure.EXACTLY) : View.MeasureSpec.makeMeasureSpec(0, 0);
        int i2 = layoutParams.height;
        view.measure(iMakeMeasureSpec, i2 > 0 ? View.MeasureSpec.makeMeasureSpec(i2, BasicMeasure.EXACTLY) : View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public void m362b() {
        float f = this.f576b.m585b().m620D().f845a;
        if (this.f580f.m606a()) {
            this.f580f.m609b(f > this.f576b.m585b().f948b);
            this.f580f.m605a(f < this.f576b.m585b().f939a);
        }
    }

    /* JADX INFO: renamed from: b */
    private void m363b(Context context) {
        ViewOnTouchListenerC0741N viewOnTouchListenerC0741N = new ViewOnTouchListenerC0741N(context);
        this.f580f = viewOnTouchListenerC0741N;
        if (viewOnTouchListenerC0741N.m606a()) {
            this.f580f.m608b(new ViewOnClickListenerC0696r(this));
            this.f580f.m604a(new ViewOnClickListenerC0697s(this));
            addView(this.f580f);
        }
    }

    /* JADX INFO: renamed from: c */
    private void m365c(Context context) {
        this.f583j = new RelativeLayout(context);
        this.f583j.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.f584k = new TextView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        this.f584k.setTextColor(Color.parseColor("#FFFFFF"));
        this.f584k.setTextSize(2, 11.0f);
        TextView textView = this.f584k;
        textView.setTypeface(textView.getTypeface(), 1);
        this.f584k.setLayoutParams(layoutParams);
        this.f584k.setId(Integer.MAX_VALUE);
        this.f583j.addView(this.f584k);
        this.f585l = new TextView(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.width = -2;
        layoutParams2.height = -2;
        layoutParams2.addRule(14);
        this.f585l.setTextColor(Color.parseColor("#000000"));
        this.f585l.setTextSize(2, 11.0f);
        this.f585l.setLayoutParams(layoutParams2);
        this.f583j.addView(this.f585l);
        this.f586m = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.width = -2;
        layoutParams3.height = -2;
        layoutParams3.addRule(14);
        layoutParams3.addRule(3, this.f584k.getId());
        this.f586m.setLayoutParams(layoutParams3);
        Bitmap bitmapM535a = C0724a.m535a("icon_scale.9.png", context);
        byte[] ninePatchChunk = bitmapM535a.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk);
        this.f586m.setBackgroundDrawable(new NinePatchDrawable(bitmapM535a, ninePatchChunk, new Rect(), null));
        this.f583j.addView(this.f586m);
        addView(this.f583j);
    }

    public static void setCustomMapStylePath(String str) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("customMapStylePath String is illegal");
        }
        if (!new File(str).exists()) {
            throw new RuntimeException("please check whether the customMapStylePath file exits");
        }
        f574i = str;
    }

    public static void setMapCustomEnable(boolean z) {
        C0750i.m708a(z);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof MapViewLayoutParams) {
            super.addView(view, layoutParams);
        }
    }

    public final LogoPosition getLogoPosition() {
        int i = this.f589q;
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? LogoPosition.logoPostionleftBottom : LogoPosition.logoPostionRightTop : LogoPosition.logoPostionRightBottom : LogoPosition.logoPostionCenterTop : LogoPosition.logoPostionCenterBottom : LogoPosition.logoPostionleftTop;
    }

    public final BaiduMap getMap() {
        this.f577c.f257b = this;
        return this.f577c;
    }

    public final int getMapLevel() {
        return f575n.get((int) this.f576b.m585b().m620D().f845a).intValue();
    }

    public int getScaleControlViewHeight() {
        return this.f597y;
    }

    public int getScaleControlViewWidth() {
        return this.f597y;
    }

    public void onCreate(Context context, Bundle bundle) {
        BaiduMapOptions baiduMapOptionsMapStatus;
        if (bundle == null) {
            return;
        }
        f574i = bundle.getString("customMapPath");
        if (bundle == null) {
            baiduMapOptionsMapStatus = new BaiduMapOptions();
        } else {
            MapStatus mapStatus = (MapStatus) bundle.getParcelable("mapstatus");
            if (this.f581g != null) {
                this.f581g = (Point) bundle.getParcelable("scalePosition");
            }
            if (this.f582h != null) {
                this.f582h = (Point) bundle.getParcelable("zoomPosition");
            }
            this.f590r = bundle.getBoolean("mZoomControlEnabled");
            this.f591s = bundle.getBoolean("mScaleControlEnabled");
            this.f589q = bundle.getInt("logoPosition");
            setPadding(bundle.getInt("paddingLeft"), bundle.getInt("paddingTop"), bundle.getInt("paddingRight"), bundle.getInt("paddingBottom"));
            baiduMapOptionsMapStatus = new BaiduMapOptions().mapStatus(mapStatus);
        }
        m358a(context, baiduMapOptionsMapStatus);
    }

    public final void onDestroy() {
        this.f576b.m588e();
        Bitmap bitmap = this.f579e;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f579e.recycle();
        }
        this.f580f.m607b();
        BMapManager.destroy();
        C0750i.m709b();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float height;
        int measuredHeight;
        int measuredWidth;
        int childCount = getChildCount();
        m360a(this.f578d);
        float width = 1.0f;
        if (((getWidth() - this.f592t) - this.f593u) - this.f578d.getMeasuredWidth() <= 0 || ((getHeight() - this.f594v) - this.f595w) - this.f578d.getMeasuredHeight() <= 0) {
            this.f592t = 0;
            this.f593u = 0;
            this.f595w = 0;
            this.f594v = 0;
            height = 1.0f;
        } else {
            width = ((getWidth() - this.f592t) - this.f593u) / getWidth();
            height = ((getHeight() - this.f594v) - this.f595w) / getHeight();
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            GestureDetectorOnDoubleTapListenerC0732F gestureDetectorOnDoubleTapListenerC0732F = this.f576b;
            if (childAt == gestureDetectorOnDoubleTapListenerC0732F) {
                gestureDetectorOnDoubleTapListenerC0732F.layout(0, 0, getWidth(), getHeight());
            } else {
                ImageView imageView = this.f578d;
                if (childAt == imageView) {
                    float f = width * 5.0f;
                    int width2 = (int) (this.f592t + f);
                    int i6 = (int) (this.f593u + f);
                    float f2 = 5.0f * height;
                    int measuredHeight2 = (int) (this.f594v + f2);
                    int i7 = (int) (this.f595w + f2);
                    int i8 = this.f589q;
                    if (i8 != 1) {
                        if (i8 == 2) {
                            measuredHeight = getHeight() - i7;
                            measuredHeight2 = measuredHeight - this.f578d.getMeasuredHeight();
                        } else if (i8 != 3) {
                            if (i8 == 4) {
                                measuredHeight = getHeight() - i7;
                                measuredHeight2 = measuredHeight - this.f578d.getMeasuredHeight();
                            } else if (i8 != 5) {
                                measuredHeight = getHeight() - i7;
                                measuredWidth = this.f578d.getMeasuredWidth() + width2;
                                measuredHeight2 = measuredHeight - this.f578d.getMeasuredHeight();
                            } else {
                                measuredHeight = measuredHeight2 + imageView.getMeasuredHeight();
                            }
                            measuredWidth = getWidth() - i6;
                            width2 = measuredWidth - this.f578d.getMeasuredWidth();
                        } else {
                            measuredHeight = measuredHeight2 + imageView.getMeasuredHeight();
                        }
                        width2 = (((getWidth() - this.f578d.getMeasuredWidth()) + this.f592t) - this.f593u) / 2;
                        measuredWidth = (((getWidth() + this.f578d.getMeasuredWidth()) + this.f592t) - this.f593u) / 2;
                    } else {
                        measuredHeight = imageView.getMeasuredHeight() + measuredHeight2;
                        measuredWidth = this.f578d.getMeasuredWidth() + width2;
                    }
                    this.f578d.layout(width2, measuredHeight2, measuredWidth, measuredHeight);
                } else {
                    ViewOnTouchListenerC0741N viewOnTouchListenerC0741N = this.f580f;
                    if (childAt != viewOnTouchListenerC0741N) {
                        RelativeLayout relativeLayout = this.f583j;
                        if (childAt == relativeLayout) {
                            m360a(relativeLayout);
                            Point point = this.f581g;
                            if (point == null) {
                                this.f597y = this.f583j.getMeasuredWidth();
                                this.f596x = this.f583j.getMeasuredHeight();
                                int i9 = (int) (this.f592t + (5.0f * width));
                                int height2 = (getHeight() - ((int) ((this.f595w + (height * 5.0f)) + 56.0f))) - this.f578d.getMeasuredHeight();
                                this.f583j.layout(i9, height2, this.f597y + i9, this.f596x + height2);
                            } else {
                                this.f583j.layout(point.x, this.f581g.y, this.f581g.x + this.f583j.getMeasuredWidth(), this.f581g.y + this.f583j.getMeasuredHeight());
                            }
                        } else {
                            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                            if (layoutParams instanceof MapViewLayoutParams) {
                                MapViewLayoutParams mapViewLayoutParams = (MapViewLayoutParams) layoutParams;
                                Point pointM633a = mapViewLayoutParams.f438c == MapViewLayoutParams.ELayoutMode.absoluteMode ? mapViewLayoutParams.f437b : this.f576b.m585b().m633a(CoordUtil.ll2mc(mapViewLayoutParams.f436a));
                                m360a(childAt);
                                int measuredWidth2 = childAt.getMeasuredWidth();
                                int measuredHeight3 = childAt.getMeasuredHeight();
                                int i10 = (int) (pointM633a.x - (mapViewLayoutParams.f439d * measuredWidth2));
                                int i11 = ((int) (pointM633a.y - (mapViewLayoutParams.f440e * measuredHeight3))) + mapViewLayoutParams.f441f;
                                childAt.layout(i10, i11, measuredWidth2 + i10, measuredHeight3 + i11);
                            }
                        }
                    } else if (viewOnTouchListenerC0741N.m606a()) {
                        m360a(this.f580f);
                        Point point2 = this.f582h;
                        if (point2 == null) {
                            int height3 = (int) (((getHeight() - 15) * height) + this.f594v);
                            int width3 = (int) (((getWidth() - 15) * width) + this.f592t);
                            int measuredWidth3 = width3 - this.f580f.getMeasuredWidth();
                            int measuredHeight4 = height3 - this.f580f.getMeasuredHeight();
                            if (this.f589q == 4) {
                                height3 -= this.f578d.getMeasuredHeight();
                                measuredHeight4 -= this.f578d.getMeasuredHeight();
                            }
                            this.f580f.layout(measuredWidth3, measuredHeight4, width3, height3);
                        } else {
                            this.f580f.layout(point2.x, this.f582h.y, this.f582h.x + this.f580f.getMeasuredWidth(), this.f582h.y + this.f580f.getMeasuredHeight());
                        }
                    }
                }
            }
        }
    }

    public final void onPause() {
        this.f576b.m587d();
    }

    public final void onResume() {
        this.f576b.m586c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        BaiduMap baiduMap;
        if (bundle == null || (baiduMap = this.f577c) == null) {
            return;
        }
        bundle.putParcelable("mapstatus", baiduMap.getMapStatus());
        Point point = this.f581g;
        if (point != null) {
            bundle.putParcelable("scalePosition", point);
        }
        Point point2 = this.f582h;
        if (point2 != null) {
            bundle.putParcelable("zoomPosition", point2);
        }
        bundle.putBoolean("mZoomControlEnabled", this.f590r);
        bundle.putBoolean("mScaleControlEnabled", this.f591s);
        bundle.putInt("logoPosition", this.f589q);
        bundle.putInt("paddingLeft", this.f592t);
        bundle.putInt("paddingTop", this.f594v);
        bundle.putInt("paddingRight", this.f593u);
        bundle.putInt("paddingBottom", this.f595w);
        bundle.putString("customMapPath", f574i);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (view == this.f578d) {
            return;
        }
        super.removeView(view);
    }

    public final void setLogoPosition(LogoPosition logoPosition) {
        if (logoPosition == null) {
            this.f589q = LogoPosition.logoPostionleftBottom.ordinal();
        }
        this.f589q = logoPosition.ordinal();
        requestLayout();
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.f592t = i;
        this.f594v = i2;
        this.f593u = i3;
        this.f595w = i4;
    }

    public void setScaleControlPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f581g = point;
            requestLayout();
        }
    }

    public void setZoomControlsPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f582h = point;
            requestLayout();
        }
    }

    public void showScaleControl(boolean z) {
        this.f583j.setVisibility(z ? 0 : 8);
        this.f591s = z;
    }

    public void showZoomControls(boolean z) {
        if (this.f580f.m606a()) {
            this.f580f.setVisibility(z ? 0 : 8);
            this.f590r = z;
        }
    }
}
