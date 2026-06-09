package com.baidu.platform.comapi.map;

import android.os.Bundle;
import com.baidu.mapapi.map.WinRound;
import com.baidu.mapapi.model.inner.Point;
import com.jieli.lib.p015dv.control.utils.TopicKey;

/* JADX INFO: renamed from: com.baidu.platform.comapi.map.E */
/* JADX INFO: loaded from: classes.dex */
public class C0731E {

    /* JADX INFO: renamed from: t */
    private static final String f844t = C0731E.class.getSimpleName();

    /* JADX INFO: renamed from: m */
    public double f857m;

    /* JADX INFO: renamed from: n */
    public double f858n;

    /* JADX INFO: renamed from: o */
    public int f859o;

    /* JADX INFO: renamed from: p */
    public String f860p;

    /* JADX INFO: renamed from: q */
    public float f861q;

    /* JADX INFO: renamed from: r */
    public boolean f862r;

    /* JADX INFO: renamed from: s */
    public int f863s;

    /* JADX INFO: renamed from: a */
    public float f845a = 12.0f;

    /* JADX INFO: renamed from: b */
    public int f846b = 0;

    /* JADX INFO: renamed from: c */
    public int f847c = 0;

    /* JADX INFO: renamed from: d */
    public double f848d = 1.2958162E7d;

    /* JADX INFO: renamed from: e */
    public double f849e = 4825907.0d;

    /* JADX INFO: renamed from: h */
    public long f852h = 0;

    /* JADX INFO: renamed from: i */
    public long f853i = 0;

    /* JADX INFO: renamed from: f */
    public int f850f = -1;

    /* JADX INFO: renamed from: g */
    public int f851g = -1;

    /* JADX INFO: renamed from: j */
    public WinRound f854j = new WinRound();

    /* JADX INFO: renamed from: k */
    public a f855k = new a();

    /* JADX INFO: renamed from: l */
    public boolean f856l = false;

    /* JADX INFO: renamed from: com.baidu.platform.comapi.map.E$a */
    public class a {

        /* JADX INFO: renamed from: a */
        public long f864a = 0;

        /* JADX INFO: renamed from: b */
        public long f865b = 0;

        /* JADX INFO: renamed from: c */
        public long f866c = 0;

        /* JADX INFO: renamed from: d */
        public long f867d = 0;

        /* JADX INFO: renamed from: e */
        public Point f868e = new Point(0, 0);

        /* JADX INFO: renamed from: f */
        public Point f869f = new Point(0, 0);

        /* JADX INFO: renamed from: g */
        public Point f870g = new Point(0, 0);

        /* JADX INFO: renamed from: h */
        public Point f871h = new Point(0, 0);

        public a() {
        }
    }

    /* JADX INFO: renamed from: a */
    public Bundle m577a(C0746e c0746e) {
        int i;
        if (this.f845a < c0746e.f948b) {
            this.f845a = c0746e.f948b;
        }
        if (this.f845a > c0746e.f939a) {
            this.f845a = c0746e.f939a;
        }
        while (true) {
            i = this.f846b;
            if (i >= 0) {
                break;
            }
            this.f846b = i + 360;
        }
        this.f846b = i % 360;
        if (this.f847c > 0) {
            this.f847c = 0;
        }
        if (this.f847c < -45) {
            this.f847c = -45;
        }
        Bundle bundle = new Bundle();
        bundle.putDouble(TopicKey.LEVEL, this.f845a);
        bundle.putDouble("rotation", this.f846b);
        bundle.putDouble("overlooking", this.f847c);
        bundle.putDouble("centerptx", this.f848d);
        bundle.putDouble("centerpty", this.f849e);
        bundle.putInt(TopicKey.LEFT, this.f854j.left);
        bundle.putInt("right", this.f854j.right);
        bundle.putInt("top", this.f854j.top);
        bundle.putInt("bottom", this.f854j.bottom);
        int i2 = this.f850f;
        if (i2 >= 0 && this.f851g >= 0 && i2 <= this.f854j.right && this.f851g <= this.f854j.bottom && this.f854j.right > 0 && this.f854j.bottom > 0) {
            int i3 = (this.f854j.right - this.f854j.left) / 2;
            int i4 = (this.f854j.bottom - this.f854j.top) / 2;
            int i5 = this.f850f - i3;
            int i6 = this.f851g - i4;
            long j = i5;
            this.f852h = j;
            this.f853i = -i6;
            bundle.putLong("xoffset", j);
            bundle.putLong("yoffset", this.f853i);
        }
        bundle.putInt("lbx", this.f855k.f868e.f712x);
        bundle.putInt("lby", this.f855k.f868e.f713y);
        bundle.putInt("ltx", this.f855k.f869f.f712x);
        bundle.putInt("lty", this.f855k.f869f.f713y);
        bundle.putInt("rtx", this.f855k.f870g.f712x);
        bundle.putInt("rty", this.f855k.f870g.f713y);
        bundle.putInt("rbx", this.f855k.f871h.f712x);
        bundle.putInt("rby", this.f855k.f871h.f713y);
        bundle.putInt("bfpp", this.f856l ? 1 : 0);
        bundle.putInt("animation", 1);
        bundle.putInt("animatime", this.f859o);
        bundle.putString("panoid", this.f860p);
        bundle.putInt("autolink", 0);
        bundle.putFloat("siangle", this.f861q);
        bundle.putInt("isbirdeye", this.f862r ? 1 : 0);
        bundle.putInt("ssext", this.f863s);
        return bundle;
    }

    /* JADX INFO: renamed from: a */
    public void m578a(Bundle bundle) {
        this.f845a = (float) bundle.getDouble(TopicKey.LEVEL);
        this.f846b = (int) bundle.getDouble("rotation");
        this.f847c = (int) bundle.getDouble("overlooking");
        this.f848d = bundle.getDouble("centerptx");
        this.f849e = bundle.getDouble("centerpty");
        this.f854j.left = bundle.getInt(TopicKey.LEFT);
        this.f854j.right = bundle.getInt("right");
        this.f854j.top = bundle.getInt("top");
        this.f854j.bottom = bundle.getInt("bottom");
        this.f852h = bundle.getLong("xoffset");
        this.f853i = bundle.getLong("yoffset");
        if (this.f854j.right != 0 && this.f854j.bottom != 0) {
            int i = (this.f854j.right - this.f854j.left) / 2;
            int i2 = (this.f854j.bottom - this.f854j.top) / 2;
            int i3 = (int) this.f852h;
            int i4 = (int) (-this.f853i);
            this.f850f = i3 + i;
            this.f851g = i4 + i2;
        }
        this.f855k.f864a = bundle.getLong("gleft");
        this.f855k.f865b = bundle.getLong("gright");
        this.f855k.f866c = bundle.getLong("gtop");
        this.f855k.f867d = bundle.getLong("gbottom");
        if (this.f855k.f864a <= -20037508) {
            this.f855k.f864a = -20037508L;
        }
        if (this.f855k.f865b >= 20037508) {
            this.f855k.f865b = 20037508L;
        }
        if (this.f855k.f866c >= 20037508) {
            this.f855k.f866c = 20037508L;
        }
        if (this.f855k.f867d <= -20037508) {
            this.f855k.f867d = -20037508L;
        }
        this.f855k.f868e.f712x = bundle.getInt("lbx");
        this.f855k.f868e.f713y = bundle.getInt("lby");
        this.f855k.f869f.f712x = bundle.getInt("ltx");
        this.f855k.f869f.f713y = bundle.getInt("lty");
        this.f855k.f870g.f712x = bundle.getInt("rtx");
        this.f855k.f870g.f713y = bundle.getInt("rty");
        this.f855k.f871h.f712x = bundle.getInt("rbx");
        this.f855k.f871h.f713y = bundle.getInt("rby");
        this.f856l = bundle.getInt("bfpp") == 1;
        this.f857m = bundle.getDouble("adapterzoomunit");
        this.f858n = bundle.getDouble("zoomunit");
        this.f860p = bundle.getString("panoid");
        this.f861q = bundle.getFloat("siangle");
        this.f862r = bundle.getInt("isbirdeye") != 0;
        this.f863s = bundle.getInt("ssext");
    }
}
