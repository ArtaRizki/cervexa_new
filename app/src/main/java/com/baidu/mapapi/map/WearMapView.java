package com.baidu.mapapi.map;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
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
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class WearMapView extends ViewGroup implements View.OnApplyWindowInsetsListener {
    public static final int BT_INVIEW = 1;

    /* JADX INFO: renamed from: c */
    private static String f620c;

    /* JADX INFO: renamed from: u */
    private static final SparseArray<Integer> f624u;

    /* JADX INFO: renamed from: A */
    private int f625A;

    /* JADX INFO: renamed from: B */
    private int f626B;

    /* JADX INFO: renamed from: C */
    private int f627C;

    /* JADX INFO: renamed from: D */
    private int f628D;

    /* JADX INFO: renamed from: E */
    private int f629E;

    /* JADX INFO: renamed from: a */
    EnumC0676a f630a;

    /* JADX INFO: renamed from: d */
    private GestureDetectorOnDoubleTapListenerC0751j f631d;

    /* JADX INFO: renamed from: e */
    private BaiduMap f632e;

    /* JADX INFO: renamed from: f */
    private ImageView f633f;

    /* JADX INFO: renamed from: g */
    private Bitmap f634g;

    /* JADX INFO: renamed from: h */
    private ViewOnTouchListenerC0741N f635h;

    /* JADX INFO: renamed from: i */
    private boolean f636i;

    /* JADX INFO: renamed from: j */
    private Point f637j;

    /* JADX INFO: renamed from: k */
    private Point f638k;

    /* JADX INFO: renamed from: l */
    private RelativeLayout f639l;

    /* JADX INFO: renamed from: m */
    private SwipeDismissView f640m;
    public AnimationTask mTask;
    public Timer mTimer;
    public HandlerC0677b mTimerHandler;

    /* JADX INFO: renamed from: n */
    private TextView f641n;

    /* JADX INFO: renamed from: o */
    private TextView f642o;

    /* JADX INFO: renamed from: p */
    private ImageView f643p;

    /* JADX INFO: renamed from: t */
    private boolean f644t;

    /* JADX INFO: renamed from: v */
    private boolean f645v;

    /* JADX INFO: renamed from: w */
    private boolean f646w;

    /* JADX INFO: renamed from: x */
    private float f647x;

    /* JADX INFO: renamed from: y */
    private InterfaceC0753l f648y;

    /* JADX INFO: renamed from: z */
    private int f649z;

    /* JADX INFO: renamed from: b */
    private static final String f619b = MapView.class.getSimpleName();

    /* JADX INFO: renamed from: q */
    private static int f621q = 0;

    /* JADX INFO: renamed from: r */
    private static int f622r = 0;

    /* JADX INFO: renamed from: s */
    private static int f623s = 10;

    public class AnimationTask extends TimerTask {
        public AnimationTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Message message = new Message();
            message.what = 1;
            WearMapView.this.mTimerHandler.sendMessage(message);
        }
    }

    public interface OnDismissCallback {
        void onDismiss();

        void onNotify();
    }

    /* JADX INFO: renamed from: com.baidu.mapapi.map.WearMapView$a */
    enum EnumC0676a {
        ROUND,
        RECTANGLE,
        UNDETECTED
    }

    /* JADX INFO: renamed from: com.baidu.mapapi.map.WearMapView$b */
    private class HandlerC0677b extends Handler {

        /* JADX INFO: renamed from: b */
        private final WeakReference<Context> f656b;

        public HandlerC0677b(Context context) {
            this.f656b = new WeakReference<>(context);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f656b.get() == null) {
                return;
            }
            super.handleMessage(message);
            if (message.what == 1 && WearMapView.this.f635h != null) {
                WearMapView.this.m394a(true);
            }
        }
    }

    static {
        SparseArray<Integer> sparseArray = new SparseArray<>();
        f624u = sparseArray;
        sparseArray.append(3, 2000000);
        f624u.append(4, 1000000);
        f624u.append(5, 500000);
        f624u.append(6, 200000);
        f624u.append(7, 100000);
        f624u.append(8, 50000);
        f624u.append(9, 25000);
        f624u.append(10, Integer.valueOf(OnCameraConnectedListener.ADMIN_ACTIVE));
        f624u.append(11, 10000);
        f624u.append(12, 5000);
        f624u.append(13, Integer.valueOf(UIMsg.m_AppUI.MSG_APP_DATA_OK));
        f624u.append(14, 1000);
        f624u.append(15, 500);
        f624u.append(16, 200);
        f624u.append(17, 100);
        f624u.append(18, 50);
        f624u.append(19, 20);
        f624u.append(20, 10);
        f624u.append(21, 5);
        f624u.append(22, 2);
    }

    public WearMapView(Context context) {
        super(context);
        this.f636i = true;
        this.f644t = true;
        this.f630a = EnumC0676a.ROUND;
        this.f645v = true;
        this.f646w = true;
        m389a(context, (BaiduMapOptions) null);
    }

    public WearMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f636i = true;
        this.f644t = true;
        this.f630a = EnumC0676a.ROUND;
        this.f645v = true;
        this.f646w = true;
        m389a(context, (BaiduMapOptions) null);
    }

    public WearMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f636i = true;
        this.f644t = true;
        this.f630a = EnumC0676a.ROUND;
        this.f645v = true;
        this.f646w = true;
        m389a(context, (BaiduMapOptions) null);
    }

    public WearMapView(Context context, BaiduMapOptions baiduMapOptions) {
        super(context);
        this.f636i = true;
        this.f644t = true;
        this.f630a = EnumC0676a.ROUND;
        this.f645v = true;
        this.f646w = true;
        m389a(context, baiduMapOptions);
    }

    /* JADX INFO: renamed from: a */
    private int m384a(int i, int i2) {
        return i - ((int) Math.sqrt(Math.pow(i, 2.0d) - Math.pow(i2, 2.0d)));
    }

    /* JADX INFO: renamed from: a */
    private void m387a(int i) {
        GestureDetectorOnDoubleTapListenerC0751j gestureDetectorOnDoubleTapListenerC0751j = this.f631d;
        if (gestureDetectorOnDoubleTapListenerC0751j == null) {
            return;
        }
        if (i == 0) {
            gestureDetectorOnDoubleTapListenerC0751j.onPause();
            m396b();
        } else {
            if (i != 1) {
                return;
            }
            gestureDetectorOnDoubleTapListenerC0751j.onResume();
            m399c();
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m388a(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        f621q = point.x;
        f622r = point.y;
    }

    /* JADX INFO: renamed from: a */
    private void m389a(Context context, BaiduMapOptions baiduMapOptions) {
        AnimationTask animationTask;
        m388a(context);
        setOnApplyWindowInsetsListener(this);
        this.mTimerHandler = new HandlerC0677b(context);
        Timer timer = new Timer();
        this.mTimer = timer;
        if (timer != null && (animationTask = this.mTask) != null) {
            animationTask.cancel();
        }
        AnimationTask animationTask2 = new AnimationTask();
        this.mTask = animationTask2;
        this.mTimer.schedule(animationTask2, 5000L);
        C0750i.m706a();
        BMapManager.init();
        m390a(context, baiduMapOptions, f620c);
        this.f632e = new BaiduMap(this.f631d);
        this.f631d.m716a().m692p(false);
        this.f631d.m716a().m691o(false);
        m400c(context);
        m403d(context);
        m397b(context);
        if (baiduMapOptions != null && !baiduMapOptions.f290h) {
            this.f635h.setVisibility(4);
        }
        m406e(context);
        if (baiduMapOptions != null && !baiduMapOptions.f291i) {
            this.f639l.setVisibility(4);
        }
        if (baiduMapOptions != null && baiduMapOptions.f294l != null) {
            this.f638k = baiduMapOptions.f294l;
        }
        if (baiduMapOptions == null || baiduMapOptions.f293k == null) {
            return;
        }
        this.f637j = baiduMapOptions.f293k;
    }

    /* JADX INFO: renamed from: a */
    private void m390a(Context context, BaiduMapOptions baiduMapOptions, String str) {
        if (baiduMapOptions == null) {
            this.f631d = new GestureDetectorOnDoubleTapListenerC0751j(context, null, str);
        } else {
            this.f631d = new GestureDetectorOnDoubleTapListenerC0751j(context, baiduMapOptions.m277a(), str);
        }
        addView(this.f631d);
        this.f648y = new C0699u(this);
        this.f631d.m716a().m645a(this.f648y);
    }

    /* JADX INFO: renamed from: a */
    private void m391a(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-2, -2);
        }
        int i = layoutParams.width;
        int iMakeMeasureSpec = i > 0 ? View.MeasureSpec.makeMeasureSpec(i, BasicMeasure.EXACTLY) : View.MeasureSpec.makeMeasureSpec(0, 0);
        int i2 = layoutParams.height;
        view.measure(iMakeMeasureSpec, i2 > 0 ? View.MeasureSpec.makeMeasureSpec(i2, BasicMeasure.EXACTLY) : View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    /* JADX INFO: renamed from: a */
    private void m392a(View view, boolean z) {
        AnimatorSet animatorSet;
        if (z) {
            animatorSet = new AnimatorSet();
            animatorSet.playTogether(ObjectAnimator.ofFloat(view, "TranslationY", 0.0f, -50.0f), ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f));
            animatorSet.addListener(new C0702x(this, view));
        } else {
            view.setVisibility(0);
            animatorSet = new AnimatorSet();
            animatorSet.playTogether(ObjectAnimator.ofFloat(view, "TranslationY", -50.0f, 0.0f), ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f));
        }
        animatorSet.setDuration(1200L);
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m394a(boolean z) {
        if (this.f636i) {
            m392a(this.f635h, z);
        }
    }

    /* JADX INFO: renamed from: b */
    private void m396b() {
        if (this.f631d == null || this.f644t) {
            return;
        }
        m402d();
        this.f644t = true;
    }

    /* JADX INFO: renamed from: b */
    private void m397b(Context context) {
        this.f640m = new SwipeDismissView(context, this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams((int) ((context.getResources().getDisplayMetrics().density * 34.0f) + 0.5f), f622r);
        this.f640m.setBackgroundColor(Color.argb(0, 0, 0, 0));
        this.f640m.setLayoutParams(layoutParams);
        addView(this.f640m);
    }

    /* JADX INFO: renamed from: c */
    private void m399c() {
        if (this.f631d != null && this.f644t) {
            m405e();
            this.f644t = false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m400c(android.content.Context r10) {
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
            r9.f634g = r0
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
            r9.f634g = r2
        L41:
            android.graphics.Bitmap r0 = r9.f634g
            if (r0 == 0) goto L56
            android.widget.ImageView r0 = new android.widget.ImageView
            r0.<init>(r10)
            r9.f633f = r0
            android.graphics.Bitmap r10 = r9.f634g
            r0.setImageBitmap(r10)
            android.widget.ImageView r10 = r9.f633f
            r9.addView(r10)
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.WearMapView.m400c(android.content.Context):void");
    }

    /* JADX INFO: renamed from: d */
    private void m402d() {
        GestureDetectorOnDoubleTapListenerC0751j gestureDetectorOnDoubleTapListenerC0751j = this.f631d;
        if (gestureDetectorOnDoubleTapListenerC0751j == null) {
            return;
        }
        gestureDetectorOnDoubleTapListenerC0751j.m720c();
    }

    /* JADX INFO: renamed from: d */
    private void m403d(Context context) {
        ViewOnTouchListenerC0741N viewOnTouchListenerC0741N = new ViewOnTouchListenerC0741N(context, true);
        this.f635h = viewOnTouchListenerC0741N;
        if (viewOnTouchListenerC0741N.m606a()) {
            this.f635h.m608b(new ViewOnClickListenerC0700v(this));
            this.f635h.m604a(new ViewOnClickListenerC0701w(this));
            addView(this.f635h);
        }
    }

    /* JADX INFO: renamed from: e */
    private void m405e() {
        GestureDetectorOnDoubleTapListenerC0751j gestureDetectorOnDoubleTapListenerC0751j = this.f631d;
        if (gestureDetectorOnDoubleTapListenerC0751j == null) {
            return;
        }
        gestureDetectorOnDoubleTapListenerC0751j.m721d();
    }

    /* JADX INFO: renamed from: e */
    private void m406e(Context context) {
        this.f639l = new RelativeLayout(context);
        this.f639l.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.f641n = new TextView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        this.f641n.setTextColor(Color.parseColor("#FFFFFF"));
        this.f641n.setTextSize(2, 11.0f);
        TextView textView = this.f641n;
        textView.setTypeface(textView.getTypeface(), 1);
        this.f641n.setLayoutParams(layoutParams);
        this.f641n.setId(Integer.MAX_VALUE);
        this.f639l.addView(this.f641n);
        this.f642o = new TextView(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.width = -2;
        layoutParams2.height = -2;
        layoutParams2.addRule(14);
        this.f642o.setTextColor(Color.parseColor("#000000"));
        this.f642o.setTextSize(2, 11.0f);
        this.f642o.setLayoutParams(layoutParams2);
        this.f639l.addView(this.f642o);
        this.f643p = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.width = -2;
        layoutParams3.height = -2;
        layoutParams3.addRule(14);
        layoutParams3.addRule(3, this.f641n.getId());
        this.f643p.setLayoutParams(layoutParams3);
        Bitmap bitmapM535a = C0724a.m535a("icon_scale.9.png", context);
        byte[] ninePatchChunk = bitmapM535a.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk);
        this.f643p.setBackgroundDrawable(new NinePatchDrawable(bitmapM535a, ninePatchChunk, new Rect(), null));
        this.f639l.addView(this.f643p);
        addView(this.f639l);
    }

    public static void setCustomMapStylePath(String str) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("customMapStylePath String is illegal");
        }
        if (!new File(str).exists()) {
            throw new RuntimeException("please check whether the customMapStylePath file exits");
        }
        f620c = str;
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

    public final BaiduMap getMap() {
        this.f632e.f258c = this;
        return this.f632e;
    }

    public final int getMapLevel() {
        return f624u.get((int) this.f631d.m716a().m620D().f845a).intValue();
    }

    public int getScaleControlViewHeight() {
        return this.f628D;
    }

    public int getScaleControlViewWidth() {
        return this.f629E;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        this.f630a = windowInsets.isRound() ? EnumC0676a.ROUND : EnumC0676a.RECTANGLE;
        return windowInsets;
    }

    public void onCreate(Context context, Bundle bundle) {
        BaiduMapOptions baiduMapOptionsMapStatus;
        if (bundle == null) {
            return;
        }
        f620c = bundle.getString("customMapPath");
        if (bundle == null) {
            baiduMapOptionsMapStatus = new BaiduMapOptions();
        } else {
            MapStatus mapStatus = (MapStatus) bundle.getParcelable("mapstatus");
            if (this.f637j != null) {
                this.f637j = (Point) bundle.getParcelable("scalePosition");
            }
            if (this.f638k != null) {
                this.f638k = (Point) bundle.getParcelable("zoomPosition");
            }
            this.f645v = bundle.getBoolean("mZoomControlEnabled");
            this.f646w = bundle.getBoolean("mScaleControlEnabled");
            setPadding(bundle.getInt("paddingLeft"), bundle.getInt("paddingTop"), bundle.getInt("paddingRight"), bundle.getInt("paddingBottom"));
            baiduMapOptionsMapStatus = new BaiduMapOptions().mapStatus(mapStatus);
        }
        m389a(context, baiduMapOptionsMapStatus);
    }

    public final void onDestroy() {
        this.f631d.m719b();
        Bitmap bitmap = this.f634g;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f634g.recycle();
            this.f634g = null;
        }
        this.f635h.m607b();
        BMapManager.destroy();
        C0750i.m709b();
        AnimationTask animationTask = this.mTask;
        if (animationTask != null) {
            animationTask.cancel();
        }
    }

    public final void onDismiss() {
        removeAllViews();
    }

    public final void onEnterAmbient(Bundle bundle) {
        m387a(0);
    }

    public void onExitAmbient() {
        m387a(1);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        AnimationTask animationTask;
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                Timer timer = new Timer();
                this.mTimer = timer;
                if (timer != null && (animationTask = this.mTask) != null) {
                    animationTask.cancel();
                }
                AnimationTask animationTask2 = new AnimationTask();
                this.mTask = animationTask2;
                this.mTimer.schedule(animationTask2, 5000L);
            }
        } else if (this.f635h.getVisibility() == 0) {
            Timer timer2 = this.mTimer;
            if (timer2 != null) {
                if (this.mTask != null) {
                    timer2.cancel();
                    this.mTask.cancel();
                }
                this.mTimer = null;
                this.mTask = null;
            }
        } else if (this.f635h.getVisibility() == 4) {
            if (this.mTimer != null) {
                AnimationTask animationTask3 = this.mTask;
                if (animationTask3 != null) {
                    animationTask3.cancel();
                }
                this.mTimer.cancel();
                this.mTask = null;
                this.mTimer = null;
            }
            m394a(false);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float width;
        int iM384a;
        int iM384a2;
        int iM384a3;
        int iM384a4;
        int childCount = getChildCount();
        m391a(this.f633f);
        float height = 1.0f;
        if (((getWidth() - this.f649z) - this.f625A) - this.f633f.getMeasuredWidth() <= 0 || ((getHeight() - this.f626B) - this.f627C) - this.f633f.getMeasuredHeight() <= 0) {
            this.f649z = 0;
            this.f625A = 0;
            this.f627C = 0;
            this.f626B = 0;
            width = 1.0f;
        } else {
            height = ((getHeight() - this.f626B) - this.f627C) / getHeight();
            width = ((getWidth() - this.f649z) - this.f625A) / getWidth();
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            GestureDetectorOnDoubleTapListenerC0751j gestureDetectorOnDoubleTapListenerC0751j = this.f631d;
            if (childAt == gestureDetectorOnDoubleTapListenerC0751j) {
                gestureDetectorOnDoubleTapListenerC0751j.layout(0, 0, getWidth(), getHeight());
            } else if (childAt == this.f633f) {
                int i6 = (int) (this.f627C + (12.0f * height));
                if (this.f630a == EnumC0676a.ROUND) {
                    m391a(this.f635h);
                    int i7 = f621q / 2;
                    iM384a3 = m384a(i7, this.f635h.getMeasuredWidth() / 2);
                    iM384a4 = ((f621q / 2) - m384a(i7, i7 - iM384a3)) + f623s;
                } else {
                    iM384a3 = 0;
                    iM384a4 = 0;
                }
                int i8 = (f622r - iM384a3) - i6;
                int measuredHeight = i8 - this.f633f.getMeasuredHeight();
                int i9 = f621q - iM384a4;
                this.f633f.layout(i9 - this.f633f.getMeasuredWidth(), measuredHeight, i9, i8);
            } else {
                ViewOnTouchListenerC0741N viewOnTouchListenerC0741N = this.f635h;
                if (childAt == viewOnTouchListenerC0741N) {
                    if (viewOnTouchListenerC0741N.m606a()) {
                        m391a(this.f635h);
                        Point point = this.f638k;
                        if (point == null) {
                            int iM384a5 = (int) ((12.0f * height) + this.f626B + (this.f630a == EnumC0676a.ROUND ? m384a(f622r / 2, this.f635h.getMeasuredWidth() / 2) : 0));
                            int measuredWidth = (f621q - this.f635h.getMeasuredWidth()) / 2;
                            this.f635h.layout(measuredWidth, iM384a5, this.f635h.getMeasuredWidth() + measuredWidth, this.f635h.getMeasuredHeight() + iM384a5);
                        } else {
                            this.f635h.layout(point.x, this.f638k.y, this.f638k.x + this.f635h.getMeasuredWidth(), this.f638k.y + this.f635h.getMeasuredHeight());
                        }
                    }
                } else if (childAt == this.f639l) {
                    if (this.f630a == EnumC0676a.ROUND) {
                        m391a(this.f635h);
                        int i10 = f621q / 2;
                        iM384a = m384a(i10, this.f635h.getMeasuredWidth() / 2);
                        iM384a2 = ((f621q / 2) - m384a(i10, i10 - iM384a)) + f623s;
                    } else {
                        iM384a = 0;
                        iM384a2 = 0;
                    }
                    m391a(this.f639l);
                    Point point2 = this.f637j;
                    if (point2 == null) {
                        this.f629E = this.f639l.getMeasuredWidth();
                        this.f628D = this.f639l.getMeasuredHeight();
                        int i11 = (int) (this.f649z + (5.0f * width) + iM384a2);
                        int i12 = (f622r - ((int) (this.f627C + (12.0f * height)))) - iM384a;
                        this.f639l.layout(i11, i12 - this.f639l.getMeasuredHeight(), this.f629E + i11, i12);
                    } else {
                        this.f639l.layout(point2.x, this.f637j.y, this.f637j.x + this.f639l.getMeasuredWidth(), this.f637j.y + this.f639l.getMeasuredHeight());
                    }
                } else {
                    SwipeDismissView swipeDismissView = this.f640m;
                    if (childAt == swipeDismissView) {
                        m391a(swipeDismissView);
                        this.f640m.layout(0, 0, this.f640m.getMeasuredWidth(), f622r);
                    } else {
                        ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                        if (layoutParams instanceof MapViewLayoutParams) {
                            MapViewLayoutParams mapViewLayoutParams = (MapViewLayoutParams) layoutParams;
                            Point pointM633a = mapViewLayoutParams.f438c == MapViewLayoutParams.ELayoutMode.absoluteMode ? mapViewLayoutParams.f437b : this.f631d.m716a().m633a(CoordUtil.ll2mc(mapViewLayoutParams.f436a));
                            m391a(childAt);
                            int measuredWidth2 = childAt.getMeasuredWidth();
                            int measuredHeight2 = childAt.getMeasuredHeight();
                            float f = mapViewLayoutParams.f439d;
                            int i13 = (int) (pointM633a.x - (f * measuredWidth2));
                            int i14 = ((int) (pointM633a.y - (mapViewLayoutParams.f440e * measuredHeight2))) + mapViewLayoutParams.f441f;
                            childAt.layout(i13, i14, measuredWidth2 + i13, measuredHeight2 + i14);
                        }
                    }
                }
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        BaiduMap baiduMap;
        if (bundle == null || (baiduMap = this.f632e) == null) {
            return;
        }
        bundle.putParcelable("mapstatus", baiduMap.getMapStatus());
        Point point = this.f637j;
        if (point != null) {
            bundle.putParcelable("scalePosition", point);
        }
        Point point2 = this.f638k;
        if (point2 != null) {
            bundle.putParcelable("zoomPosition", point2);
        }
        bundle.putBoolean("mZoomControlEnabled", this.f645v);
        bundle.putBoolean("mScaleControlEnabled", this.f646w);
        bundle.putInt("paddingLeft", this.f649z);
        bundle.putInt("paddingTop", this.f626B);
        bundle.putInt("paddingRight", this.f625A);
        bundle.putInt("paddingBottom", this.f627C);
        bundle.putString("customMapPath", f620c);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (view == this.f633f) {
            return;
        }
        super.removeView(view);
    }

    public void setOnDismissCallbackListener(OnDismissCallback onDismissCallback) {
        SwipeDismissView swipeDismissView = this.f640m;
        if (swipeDismissView == null) {
            return;
        }
        swipeDismissView.setCallback(onDismissCallback);
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.f649z = i;
        this.f626B = i2;
        this.f625A = i3;
        this.f627C = i4;
    }

    public void setScaleControlPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f637j = point;
            requestLayout();
        }
    }

    public void setShape(EnumC0676a enumC0676a) {
        this.f630a = enumC0676a;
    }

    public void setViewAnimitionEnable(boolean z) {
        this.f636i = z;
    }

    public void setZoomControlsPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f638k = point;
            requestLayout();
        }
    }

    public void showScaleControl(boolean z) {
        this.f639l.setVisibility(z ? 0 : 8);
        this.f646w = z;
    }

    public void showZoomControls(boolean z) {
        if (this.f635h.m606a()) {
            this.f635h.setVisibility(z ? 0 : 8);
            this.f645v = z;
        }
    }
}
