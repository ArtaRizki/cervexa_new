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
import android.util.Log;
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
import com.baidu.platform.comapi.map.GestureDetectorOnDoubleTapListenerC0751j;
import com.baidu.platform.comapi.map.InterfaceC0753l;
import com.baidu.platform.comapi.map.ViewOnTouchListenerC0741N;
import com.gizthon.camera.core.OnCameraConnectedListener;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class MapView extends ViewGroup {

    /* JADX INFO: renamed from: a */
    private static final String f411a = MapView.class.getSimpleName();

    /* JADX INFO: renamed from: b */
    private static String f412b;

    /* JADX INFO: renamed from: n */
    private static final SparseArray<Integer> f413n;

    /* JADX INFO: renamed from: c */
    private GestureDetectorOnDoubleTapListenerC0751j f414c;

    /* JADX INFO: renamed from: d */
    private BaiduMap f415d;

    /* JADX INFO: renamed from: e */
    private ImageView f416e;

    /* JADX INFO: renamed from: f */
    private Bitmap f417f;

    /* JADX INFO: renamed from: g */
    private ViewOnTouchListenerC0741N f418g;

    /* JADX INFO: renamed from: h */
    private Point f419h;

    /* JADX INFO: renamed from: i */
    private Point f420i;

    /* JADX INFO: renamed from: j */
    private RelativeLayout f421j;

    /* JADX INFO: renamed from: k */
    private TextView f422k;

    /* JADX INFO: renamed from: l */
    private TextView f423l;

    /* JADX INFO: renamed from: m */
    private ImageView f424m;

    /* JADX INFO: renamed from: o */
    private int f425o;

    /* JADX INFO: renamed from: p */
    private boolean f426p;

    /* JADX INFO: renamed from: q */
    private boolean f427q;

    /* JADX INFO: renamed from: r */
    private float f428r;

    /* JADX INFO: renamed from: s */
    private InterfaceC0753l f429s;

    /* JADX INFO: renamed from: t */
    private int f430t;

    /* JADX INFO: renamed from: u */
    private int f431u;

    /* JADX INFO: renamed from: v */
    private int f432v;

    /* JADX INFO: renamed from: w */
    private int f433w;

    /* JADX INFO: renamed from: x */
    private int f434x;

    /* JADX INFO: renamed from: y */
    private int f435y;

    static {
        SparseArray<Integer> sparseArray = new SparseArray<>();
        f413n = sparseArray;
        sparseArray.append(3, 2000000);
        f413n.append(4, 1000000);
        f413n.append(5, 500000);
        f413n.append(6, 200000);
        f413n.append(7, 100000);
        f413n.append(8, 50000);
        f413n.append(9, 25000);
        f413n.append(10, Integer.valueOf(OnCameraConnectedListener.ADMIN_ACTIVE));
        f413n.append(11, 10000);
        f413n.append(12, 5000);
        f413n.append(13, Integer.valueOf(UIMsg.m_AppUI.MSG_APP_DATA_OK));
        f413n.append(14, 1000);
        f413n.append(15, 500);
        f413n.append(16, 200);
        f413n.append(17, 100);
        f413n.append(18, 50);
        f413n.append(19, 20);
        f413n.append(20, 10);
        f413n.append(21, 5);
        f413n.append(22, 2);
    }

    public MapView(Context context) {
        super(context);
        this.f425o = LogoPosition.logoPostionleftBottom.ordinal();
        this.f426p = true;
        this.f427q = true;
        m324a(context, (BaiduMapOptions) null);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f425o = LogoPosition.logoPostionleftBottom.ordinal();
        this.f426p = true;
        this.f427q = true;
        m324a(context, (BaiduMapOptions) null);
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f425o = LogoPosition.logoPostionleftBottom.ordinal();
        this.f426p = true;
        this.f427q = true;
        m324a(context, (BaiduMapOptions) null);
    }

    public MapView(Context context, BaiduMapOptions baiduMapOptions) {
        super(context);
        this.f425o = LogoPosition.logoPostionleftBottom.ordinal();
        this.f426p = true;
        this.f427q = true;
        m324a(context, baiduMapOptions);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m323a(android.content.Context r10) {
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
            r9.f417f = r0
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
            r9.f417f = r2
        L41:
            android.graphics.Bitmap r0 = r9.f417f
            if (r0 == 0) goto L56
            android.widget.ImageView r0 = new android.widget.ImageView
            r0.<init>(r10)
            r9.f416e = r0
            android.graphics.Bitmap r10 = r9.f417f
            r0.setImageBitmap(r10)
            android.widget.ImageView r10 = r9.f416e
            r9.addView(r10)
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.MapView.m323a(android.content.Context):void");
    }

    /* JADX INFO: renamed from: a */
    private void m324a(Context context, BaiduMapOptions baiduMapOptions) {
        C0750i.m706a();
        BMapManager.init();
        m325a(context, baiduMapOptions, f412b);
        this.f415d = new BaiduMap(this.f414c);
        m323a(context);
        m329b(context);
        if (baiduMapOptions != null && !baiduMapOptions.f290h) {
            this.f418g.setVisibility(4);
        }
        m331c(context);
        if (baiduMapOptions != null && !baiduMapOptions.f291i) {
            this.f421j.setVisibility(4);
        }
        if (baiduMapOptions != null && baiduMapOptions.f292j != null) {
            this.f425o = baiduMapOptions.f292j.ordinal();
        }
        if (baiduMapOptions != null && baiduMapOptions.f294l != null) {
            this.f420i = baiduMapOptions.f294l;
        }
        if (baiduMapOptions == null || baiduMapOptions.f293k == null) {
            return;
        }
        this.f419h = baiduMapOptions.f293k;
    }

    /* JADX INFO: renamed from: a */
    private void m325a(Context context, BaiduMapOptions baiduMapOptions, String str) {
        if (baiduMapOptions == null) {
            this.f414c = new GestureDetectorOnDoubleTapListenerC0751j(context, null, str);
        } else {
            this.f414c = new GestureDetectorOnDoubleTapListenerC0751j(context, baiduMapOptions.m277a(), str);
        }
        addView(this.f414c);
        this.f429s = new C0686i(this);
        this.f414c.m716a().m645a(this.f429s);
    }

    /* JADX INFO: renamed from: a */
    private void m326a(View view) {
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
    public void m328b() {
        float f = this.f414c.m716a().m620D().f845a;
        if (this.f418g.m606a()) {
            this.f418g.m609b(f > this.f414c.m716a().f948b);
            this.f418g.m605a(f < this.f414c.m716a().f939a);
        }
    }

    /* JADX INFO: renamed from: b */
    private void m329b(Context context) {
        ViewOnTouchListenerC0741N viewOnTouchListenerC0741N = new ViewOnTouchListenerC0741N(context, false);
        this.f418g = viewOnTouchListenerC0741N;
        if (viewOnTouchListenerC0741N.m606a()) {
            this.f418g.m608b(new ViewOnClickListenerC0687j(this));
            this.f418g.m604a(new ViewOnClickListenerC0688k(this));
            addView(this.f418g);
        }
    }

    /* JADX INFO: renamed from: c */
    private void m331c(Context context) {
        this.f421j = new RelativeLayout(context);
        this.f421j.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.f422k = new TextView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        this.f422k.setTextColor(Color.parseColor("#FFFFFF"));
        this.f422k.setTextSize(2, 11.0f);
        TextView textView = this.f422k;
        textView.setTypeface(textView.getTypeface(), 1);
        this.f422k.setLayoutParams(layoutParams);
        this.f422k.setId(Integer.MAX_VALUE);
        this.f421j.addView(this.f422k);
        this.f423l = new TextView(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.width = -2;
        layoutParams2.height = -2;
        layoutParams2.addRule(14);
        this.f423l.setTextColor(Color.parseColor("#000000"));
        this.f423l.setTextSize(2, 11.0f);
        this.f423l.setLayoutParams(layoutParams2);
        this.f421j.addView(this.f423l);
        this.f424m = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.width = -2;
        layoutParams3.height = -2;
        layoutParams3.addRule(14);
        layoutParams3.addRule(3, this.f422k.getId());
        this.f424m.setLayoutParams(layoutParams3);
        Bitmap bitmapM535a = C0724a.m535a("icon_scale.9.png", context);
        byte[] ninePatchChunk = bitmapM535a.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk);
        this.f424m.setBackgroundDrawable(new NinePatchDrawable(bitmapM535a, ninePatchChunk, new Rect(), null));
        this.f421j.addView(this.f424m);
        addView(this.f421j);
    }

    public static void setCustomMapStylePath(String str) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("customMapStylePath String is illegal");
        }
        if (!new File(str).exists()) {
            throw new RuntimeException("please check whether the customMapStylePath file exits");
        }
        f412b = str;
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
        int i = this.f425o;
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? LogoPosition.logoPostionleftBottom : LogoPosition.logoPostionRightTop : LogoPosition.logoPostionRightBottom : LogoPosition.logoPostionCenterTop : LogoPosition.logoPostionCenterBottom : LogoPosition.logoPostionleftTop;
    }

    public final BaiduMap getMap() {
        this.f415d.f256a = this;
        return this.f415d;
    }

    public final int getMapLevel() {
        return f413n.get((int) this.f414c.m716a().m620D().f845a).intValue();
    }

    public int getScaleControlViewHeight() {
        return this.f434x;
    }

    public int getScaleControlViewWidth() {
        return this.f435y;
    }

    public void onCreate(Context context, Bundle bundle) {
        BaiduMapOptions baiduMapOptionsMapStatus;
        if (bundle == null) {
            return;
        }
        f412b = bundle.getString("customMapPath");
        if (bundle == null) {
            baiduMapOptionsMapStatus = new BaiduMapOptions();
        } else {
            MapStatus mapStatus = (MapStatus) bundle.getParcelable("mapstatus");
            if (this.f419h != null) {
                this.f419h = (Point) bundle.getParcelable("scalePosition");
            }
            if (this.f420i != null) {
                this.f420i = (Point) bundle.getParcelable("zoomPosition");
            }
            this.f426p = bundle.getBoolean("mZoomControlEnabled");
            this.f427q = bundle.getBoolean("mScaleControlEnabled");
            this.f425o = bundle.getInt("logoPosition");
            setPadding(bundle.getInt("paddingLeft"), bundle.getInt("paddingTop"), bundle.getInt("paddingRight"), bundle.getInt("paddingBottom"));
            baiduMapOptionsMapStatus = new BaiduMapOptions().mapStatus(mapStatus);
        }
        m324a(context, baiduMapOptionsMapStatus);
    }

    public final void onDestroy() {
        this.f414c.m719b();
        Bitmap bitmap = this.f417f;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f417f.recycle();
            this.f417f = null;
        }
        this.f418g.m607b();
        BMapManager.destroy();
        C0750i.m709b();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float height;
        int measuredHeight;
        int measuredWidth;
        int childCount = getChildCount();
        m326a(this.f416e);
        float width = 1.0f;
        if (((getWidth() - this.f430t) - this.f431u) - this.f416e.getMeasuredWidth() <= 0 || ((getHeight() - this.f432v) - this.f433w) - this.f416e.getMeasuredHeight() <= 0) {
            this.f430t = 0;
            this.f431u = 0;
            this.f433w = 0;
            this.f432v = 0;
            height = 1.0f;
        } else {
            width = ((getWidth() - this.f430t) - this.f431u) / getWidth();
            height = ((getHeight() - this.f432v) - this.f433w) / getHeight();
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt != null) {
                GestureDetectorOnDoubleTapListenerC0751j gestureDetectorOnDoubleTapListenerC0751j = this.f414c;
                if (childAt == gestureDetectorOnDoubleTapListenerC0751j) {
                    gestureDetectorOnDoubleTapListenerC0751j.layout(0, 0, getWidth(), getHeight());
                } else {
                    ImageView imageView = this.f416e;
                    if (childAt == imageView) {
                        float f = width * 5.0f;
                        int width2 = (int) (this.f430t + f);
                        int i6 = (int) (this.f431u + f);
                        float f2 = 5.0f * height;
                        int measuredHeight2 = (int) (this.f432v + f2);
                        int i7 = (int) (this.f433w + f2);
                        int i8 = this.f425o;
                        if (i8 != 1) {
                            if (i8 == 2) {
                                measuredHeight = getHeight() - i7;
                                measuredHeight2 = measuredHeight - this.f416e.getMeasuredHeight();
                            } else if (i8 != 3) {
                                if (i8 == 4) {
                                    measuredHeight = getHeight() - i7;
                                    measuredHeight2 = measuredHeight - this.f416e.getMeasuredHeight();
                                } else if (i8 != 5) {
                                    measuredHeight = getHeight() - i7;
                                    measuredWidth = this.f416e.getMeasuredWidth() + width2;
                                    measuredHeight2 = measuredHeight - this.f416e.getMeasuredHeight();
                                } else {
                                    measuredHeight = measuredHeight2 + imageView.getMeasuredHeight();
                                }
                                measuredWidth = getWidth() - i6;
                                width2 = measuredWidth - this.f416e.getMeasuredWidth();
                            } else {
                                measuredHeight = measuredHeight2 + imageView.getMeasuredHeight();
                            }
                            width2 = (((getWidth() - this.f416e.getMeasuredWidth()) + this.f430t) - this.f431u) / 2;
                            measuredWidth = (((getWidth() + this.f416e.getMeasuredWidth()) + this.f430t) - this.f431u) / 2;
                        } else {
                            measuredHeight = imageView.getMeasuredHeight() + measuredHeight2;
                            measuredWidth = this.f416e.getMeasuredWidth() + width2;
                        }
                        this.f416e.layout(width2, measuredHeight2, measuredWidth, measuredHeight);
                    } else {
                        ViewOnTouchListenerC0741N viewOnTouchListenerC0741N = this.f418g;
                        if (childAt != viewOnTouchListenerC0741N) {
                            RelativeLayout relativeLayout = this.f421j;
                            if (childAt == relativeLayout) {
                                m326a(relativeLayout);
                                Point point = this.f419h;
                                if (point == null) {
                                    this.f435y = this.f421j.getMeasuredWidth();
                                    this.f434x = this.f421j.getMeasuredHeight();
                                    int i9 = (int) (this.f430t + (5.0f * width));
                                    int height2 = (getHeight() - ((int) ((this.f433w + (height * 5.0f)) + 56.0f))) - this.f416e.getMeasuredHeight();
                                    this.f421j.layout(i9, height2, this.f435y + i9, this.f434x + height2);
                                } else {
                                    this.f421j.layout(point.x, this.f419h.y, this.f419h.x + this.f421j.getMeasuredWidth(), this.f419h.y + this.f421j.getMeasuredHeight());
                                }
                            } else {
                                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                                if (layoutParams == null) {
                                    Log.e("test", "lp == null");
                                }
                                if (layoutParams instanceof MapViewLayoutParams) {
                                    MapViewLayoutParams mapViewLayoutParams = (MapViewLayoutParams) layoutParams;
                                    Point pointM633a = mapViewLayoutParams.f438c == MapViewLayoutParams.ELayoutMode.absoluteMode ? mapViewLayoutParams.f437b : this.f414c.m716a().m633a(CoordUtil.ll2mc(mapViewLayoutParams.f436a));
                                    m326a(childAt);
                                    int measuredWidth2 = childAt.getMeasuredWidth();
                                    int measuredHeight3 = childAt.getMeasuredHeight();
                                    int i10 = (int) (pointM633a.x - (mapViewLayoutParams.f439d * measuredWidth2));
                                    int i11 = ((int) (pointM633a.y - (mapViewLayoutParams.f440e * measuredHeight3))) + mapViewLayoutParams.f441f;
                                    childAt.layout(i10, i11, measuredWidth2 + i10, measuredHeight3 + i11);
                                }
                            }
                        } else if (viewOnTouchListenerC0741N.m606a()) {
                            m326a(this.f418g);
                            Point point2 = this.f420i;
                            if (point2 == null) {
                                int height3 = (int) (((getHeight() - 15) * height) + this.f432v);
                                int width3 = (int) (((getWidth() - 15) * width) + this.f430t);
                                int measuredWidth3 = width3 - this.f418g.getMeasuredWidth();
                                int measuredHeight4 = height3 - this.f418g.getMeasuredHeight();
                                if (this.f425o == 4) {
                                    height3 -= this.f416e.getMeasuredHeight();
                                    measuredHeight4 -= this.f416e.getMeasuredHeight();
                                }
                                this.f418g.layout(measuredWidth3, measuredHeight4, width3, height3);
                            } else {
                                this.f418g.layout(point2.x, this.f420i.y, this.f420i.x + this.f418g.getMeasuredWidth(), this.f420i.y + this.f418g.getMeasuredHeight());
                            }
                        }
                    }
                }
            }
        }
    }

    public final void onPause() {
        this.f414c.onPause();
    }

    public final void onResume() {
        this.f414c.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        BaiduMap baiduMap;
        if (bundle == null || (baiduMap = this.f415d) == null) {
            return;
        }
        bundle.putParcelable("mapstatus", baiduMap.getMapStatus());
        Point point = this.f419h;
        if (point != null) {
            bundle.putParcelable("scalePosition", point);
        }
        Point point2 = this.f420i;
        if (point2 != null) {
            bundle.putParcelable("zoomPosition", point2);
        }
        bundle.putBoolean("mZoomControlEnabled", this.f426p);
        bundle.putBoolean("mScaleControlEnabled", this.f427q);
        bundle.putInt("logoPosition", this.f425o);
        bundle.putInt("paddingLeft", this.f430t);
        bundle.putInt("paddingTop", this.f432v);
        bundle.putInt("paddingRight", this.f431u);
        bundle.putInt("paddingBottom", this.f433w);
        bundle.putString("customMapPath", f412b);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (view == this.f416e) {
            return;
        }
        super.removeView(view);
    }

    public final void setLogoPosition(LogoPosition logoPosition) {
        if (logoPosition == null) {
            this.f425o = LogoPosition.logoPostionleftBottom.ordinal();
        }
        this.f425o = logoPosition.ordinal();
        requestLayout();
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.f430t = i;
        this.f432v = i2;
        this.f431u = i3;
        this.f433w = i4;
    }

    public void setScaleControlPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f419h = point;
            requestLayout();
        }
    }

    public final void setZOrderMediaOverlay(boolean z) {
        GestureDetectorOnDoubleTapListenerC0751j gestureDetectorOnDoubleTapListenerC0751j = this.f414c;
        if (gestureDetectorOnDoubleTapListenerC0751j == null) {
            return;
        }
        gestureDetectorOnDoubleTapListenerC0751j.setZOrderMediaOverlay(z);
    }

    public void setZoomControlsPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f420i = point;
            requestLayout();
        }
    }

    public void showScaleControl(boolean z) {
        this.f421j.setVisibility(z ? 0 : 8);
        this.f427q = z;
    }

    public void showZoomControls(boolean z) {
        if (this.f418g.m606a()) {
            this.f418g.setVisibility(z ? 0 : 8);
            this.f426p = z;
        }
    }
}
