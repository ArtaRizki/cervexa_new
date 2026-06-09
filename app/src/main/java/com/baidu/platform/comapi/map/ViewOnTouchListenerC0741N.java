package com.baidu.platform.comapi.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.platform.comapi.commonutils.C0724a;

/* JADX INFO: renamed from: com.baidu.platform.comapi.map.N */
/* JADX INFO: loaded from: classes.dex */
public class ViewOnTouchListenerC0741N extends LinearLayout implements View.OnTouchListener {

    /* JADX INFO: renamed from: a */
    private ImageView f892a;

    /* JADX INFO: renamed from: b */
    private ImageView f893b;

    /* JADX INFO: renamed from: c */
    private Context f894c;

    /* JADX INFO: renamed from: d */
    private Bitmap f895d;

    /* JADX INFO: renamed from: e */
    private Bitmap f896e;

    /* JADX INFO: renamed from: f */
    private Bitmap f897f;

    /* JADX INFO: renamed from: g */
    private Bitmap f898g;

    /* JADX INFO: renamed from: h */
    private Bitmap f899h;

    /* JADX INFO: renamed from: i */
    private Bitmap f900i;

    /* JADX INFO: renamed from: j */
    private Bitmap f901j;

    /* JADX INFO: renamed from: k */
    private Bitmap f902k;

    /* JADX INFO: renamed from: l */
    private int f903l;

    /* JADX INFO: renamed from: m */
    private boolean f904m;

    /* JADX INFO: renamed from: n */
    private boolean f905n;

    @Deprecated
    public ViewOnTouchListenerC0741N(Context context) {
        super(context);
        this.f904m = false;
        this.f905n = false;
        this.f894c = context;
        m602c();
        if (this.f895d == null || this.f896e == null || this.f897f == null || this.f898g == null) {
            return;
        }
        this.f892a = new ImageView(this.f894c);
        this.f893b = new ImageView(this.f894c);
        this.f892a.setImageBitmap(this.f895d);
        this.f893b.setImageBitmap(this.f897f);
        this.f903l = m599a(this.f897f.getHeight() / 6);
        m601a(this.f892a, "main_topbtn_up.9.png");
        m601a(this.f893b, "main_bottombtn_up.9.png");
        this.f892a.setId(0);
        this.f893b.setId(1);
        this.f892a.setClickable(true);
        this.f893b.setClickable(true);
        this.f892a.setOnTouchListener(this);
        this.f893b.setOnTouchListener(this);
        setOrientation(1);
        setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        addView(this.f892a);
        addView(this.f893b);
        this.f905n = true;
    }

    public ViewOnTouchListenerC0741N(Context context, boolean z) {
        super(context);
        this.f904m = false;
        this.f905n = false;
        this.f894c = context;
        this.f904m = z;
        this.f892a = new ImageView(this.f894c);
        this.f893b = new ImageView(this.f894c);
        if (z) {
            m603d();
            if (this.f899h == null || this.f900i == null || this.f901j == null || this.f902k == null) {
                return;
            }
            this.f892a.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            this.f893b.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            this.f892a.setImageBitmap(this.f899h);
            this.f893b.setImageBitmap(this.f901j);
            setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            setOrientation(0);
        } else {
            m602c();
            Bitmap bitmap = this.f895d;
            if (bitmap == null || this.f896e == null || this.f897f == null || this.f898g == null) {
                return;
            }
            this.f892a.setImageBitmap(bitmap);
            this.f893b.setImageBitmap(this.f897f);
            this.f903l = m599a(this.f897f.getHeight() / 6);
            m601a(this.f892a, "main_topbtn_up.9.png");
            m601a(this.f893b, "main_bottombtn_up.9.png");
            setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            setOrientation(1);
        }
        this.f892a.setId(0);
        this.f893b.setId(1);
        this.f892a.setClickable(true);
        this.f893b.setClickable(true);
        this.f892a.setOnTouchListener(this);
        this.f893b.setOnTouchListener(this);
        addView(this.f892a);
        addView(this.f893b);
        this.f905n = true;
    }

    /* JADX INFO: renamed from: a */
    private int m599a(int i) {
        return (int) ((this.f894c.getResources().getDisplayMetrics().density * i) + 0.5f);
    }

    /* JADX INFO: renamed from: a */
    private Bitmap m600a(String str) {
        Matrix matrix = new Matrix();
        int densityDpi = SysOSUtil.getDensityDpi();
        float f = densityDpi > 480 ? 1.8f : (densityDpi <= 320 || densityDpi > 480) ? 1.2f : 1.5f;
        matrix.postScale(f, f);
        Bitmap bitmapM535a = C0724a.m535a(str, this.f894c);
        return Bitmap.createBitmap(bitmapM535a, 0, 0, bitmapM535a.getWidth(), bitmapM535a.getHeight(), matrix, true);
    }

    /* JADX INFO: renamed from: a */
    private void m601a(View view, String str) {
        Bitmap bitmapM535a = C0724a.m535a(str, this.f894c);
        byte[] ninePatchChunk = bitmapM535a.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk);
        view.setBackgroundDrawable(new NinePatchDrawable(bitmapM535a, ninePatchChunk, new Rect(), null));
        int i = this.f903l;
        view.setPadding(i, i, i, i);
    }

    /* JADX INFO: renamed from: c */
    private void m602c() {
        this.f895d = m600a("main_icon_zoomin.png");
        this.f896e = m600a("main_icon_zoomin_dis.png");
        this.f897f = m600a("main_icon_zoomout.png");
        this.f898g = m600a("main_icon_zoomout_dis.png");
    }

    /* JADX INFO: renamed from: d */
    private void m603d() {
        this.f899h = m600a("wear_zoom_in.png");
        this.f900i = m600a("wear_zoom_in_pressed.png");
        this.f901j = m600a("wear_zoon_out.png");
        this.f902k = m600a("wear_zoom_out_pressed.png");
    }

    /* JADX INFO: renamed from: a */
    public void m604a(View.OnClickListener onClickListener) {
        this.f892a.setOnClickListener(onClickListener);
    }

    /* JADX INFO: renamed from: a */
    public void m605a(boolean z) {
        ImageView imageView;
        Bitmap bitmap;
        this.f892a.setEnabled(z);
        if (z) {
            imageView = this.f892a;
            bitmap = this.f895d;
        } else {
            imageView = this.f892a;
            bitmap = this.f896e;
        }
        imageView.setImageBitmap(bitmap);
    }

    /* JADX INFO: renamed from: a */
    public boolean m606a() {
        return this.f905n;
    }

    /* JADX INFO: renamed from: b */
    public void m607b() {
        Bitmap bitmap = this.f895d;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f895d.recycle();
            this.f895d = null;
        }
        Bitmap bitmap2 = this.f896e;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            this.f896e.recycle();
            this.f896e = null;
        }
        Bitmap bitmap3 = this.f897f;
        if (bitmap3 != null && !bitmap3.isRecycled()) {
            this.f897f.recycle();
            this.f897f = null;
        }
        Bitmap bitmap4 = this.f898g;
        if (bitmap4 != null && !bitmap4.isRecycled()) {
            this.f898g.recycle();
            this.f898g = null;
        }
        Bitmap bitmap5 = this.f899h;
        if (bitmap5 != null && !bitmap5.isRecycled()) {
            this.f899h.recycle();
            this.f899h = null;
        }
        Bitmap bitmap6 = this.f900i;
        if (bitmap6 != null && !bitmap6.isRecycled()) {
            this.f900i.recycle();
            this.f900i = null;
        }
        Bitmap bitmap7 = this.f901j;
        if (bitmap7 != null && !bitmap7.isRecycled()) {
            this.f901j.recycle();
            this.f901j = null;
        }
        Bitmap bitmap8 = this.f902k;
        if (bitmap8 == null || bitmap8.isRecycled()) {
            return;
        }
        this.f902k.recycle();
        this.f902k = null;
    }

    /* JADX INFO: renamed from: b */
    public void m608b(View.OnClickListener onClickListener) {
        this.f893b.setOnClickListener(onClickListener);
    }

    /* JADX INFO: renamed from: b */
    public void m609b(boolean z) {
        ImageView imageView;
        Bitmap bitmap;
        this.f893b.setEnabled(z);
        if (z) {
            imageView = this.f893b;
            bitmap = this.f897f;
        } else {
            imageView = this.f893b;
            bitmap = this.f898g;
        }
        imageView.setImageBitmap(bitmap);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ImageView imageView;
        Bitmap bitmap;
        ImageView imageView2;
        String str;
        if (!(view instanceof ImageView)) {
            return false;
        }
        int id = ((ImageView) view).getId();
        if (id == 0) {
            if (motionEvent.getAction() == 0) {
                if (this.f904m) {
                    imageView = this.f892a;
                    bitmap = this.f900i;
                    imageView.setImageBitmap(bitmap);
                    return false;
                }
                imageView2 = this.f892a;
                str = "main_topbtn_down.9.png";
                m601a(imageView2, str);
                return false;
            }
            if (motionEvent.getAction() != 1) {
                return false;
            }
            if (this.f904m) {
                imageView = this.f892a;
                bitmap = this.f899h;
                imageView.setImageBitmap(bitmap);
                return false;
            }
            imageView2 = this.f892a;
            str = "main_topbtn_up.9.png";
            m601a(imageView2, str);
            return false;
        }
        if (id != 1) {
            return false;
        }
        if (motionEvent.getAction() == 0) {
            if (this.f904m) {
                imageView = this.f893b;
                bitmap = this.f902k;
                imageView.setImageBitmap(bitmap);
                return false;
            }
            imageView2 = this.f893b;
            str = "main_bottombtn_down.9.png";
            m601a(imageView2, str);
            return false;
        }
        if (motionEvent.getAction() != 1) {
            return false;
        }
        if (this.f904m) {
            imageView = this.f893b;
            bitmap = this.f901j;
            imageView.setImageBitmap(bitmap);
            return false;
        }
        imageView2 = this.f893b;
        str = "main_bottombtn_up.9.png";
        m601a(imageView2, str);
        return false;
    }
}
