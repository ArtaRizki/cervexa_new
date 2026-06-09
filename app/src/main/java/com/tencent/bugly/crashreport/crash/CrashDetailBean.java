package com.tencent.bugly.crashreport.crash;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.proguard.C2023z;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class CrashDetailBean implements Parcelable, Comparable<CrashDetailBean> {
    public static final Parcelable.Creator<CrashDetailBean> CREATOR = new Parcelable.Creator<CrashDetailBean>() { // from class: com.tencent.bugly.crashreport.crash.CrashDetailBean.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ CrashDetailBean createFromParcel(Parcel parcel) {
            return new CrashDetailBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ CrashDetailBean[] newArray(int i) {
            return new CrashDetailBean[i];
        }
    };

    /* JADX INFO: renamed from: A */
    public String f2464A;

    /* JADX INFO: renamed from: B */
    public String f2465B;

    /* JADX INFO: renamed from: C */
    public long f2466C;

    /* JADX INFO: renamed from: D */
    public long f2467D;

    /* JADX INFO: renamed from: E */
    public long f2468E;

    /* JADX INFO: renamed from: F */
    public long f2469F;

    /* JADX INFO: renamed from: G */
    public long f2470G;

    /* JADX INFO: renamed from: H */
    public long f2471H;

    /* JADX INFO: renamed from: I */
    public String f2472I;

    /* JADX INFO: renamed from: J */
    public String f2473J;

    /* JADX INFO: renamed from: K */
    public String f2474K;

    /* JADX INFO: renamed from: L */
    public String f2475L;

    /* JADX INFO: renamed from: M */
    public long f2476M;

    /* JADX INFO: renamed from: N */
    public boolean f2477N;

    /* JADX INFO: renamed from: O */
    public Map<String, String> f2478O;

    /* JADX INFO: renamed from: P */
    public Map<String, String> f2479P;

    /* JADX INFO: renamed from: Q */
    public int f2480Q;

    /* JADX INFO: renamed from: R */
    public int f2481R;

    /* JADX INFO: renamed from: S */
    public Map<String, String> f2482S;

    /* JADX INFO: renamed from: T */
    public Map<String, String> f2483T;

    /* JADX INFO: renamed from: U */
    public byte[] f2484U;

    /* JADX INFO: renamed from: V */
    public String f2485V;

    /* JADX INFO: renamed from: W */
    public String f2486W;

    /* JADX INFO: renamed from: X */
    private String f2487X;

    /* JADX INFO: renamed from: a */
    public long f2488a;

    /* JADX INFO: renamed from: b */
    public int f2489b;

    /* JADX INFO: renamed from: c */
    public String f2490c;

    /* JADX INFO: renamed from: d */
    public boolean f2491d;

    /* JADX INFO: renamed from: e */
    public String f2492e;

    /* JADX INFO: renamed from: f */
    public String f2493f;

    /* JADX INFO: renamed from: g */
    public String f2494g;

    /* JADX INFO: renamed from: h */
    public Map<String, PlugInBean> f2495h;

    /* JADX INFO: renamed from: i */
    public Map<String, PlugInBean> f2496i;

    /* JADX INFO: renamed from: j */
    public boolean f2497j;

    /* JADX INFO: renamed from: k */
    public boolean f2498k;

    /* JADX INFO: renamed from: l */
    public int f2499l;

    /* JADX INFO: renamed from: m */
    public String f2500m;

    /* JADX INFO: renamed from: n */
    public String f2501n;

    /* JADX INFO: renamed from: o */
    public String f2502o;

    /* JADX INFO: renamed from: p */
    public String f2503p;

    /* JADX INFO: renamed from: q */
    public String f2504q;

    /* JADX INFO: renamed from: r */
    public long f2505r;

    /* JADX INFO: renamed from: s */
    public String f2506s;

    /* JADX INFO: renamed from: t */
    public int f2507t;

    /* JADX INFO: renamed from: u */
    public String f2508u;

    /* JADX INFO: renamed from: v */
    public String f2509v;

    /* JADX INFO: renamed from: w */
    public String f2510w;

    /* JADX INFO: renamed from: x */
    public String f2511x;

    /* JADX INFO: renamed from: y */
    public byte[] f2512y;

    /* JADX INFO: renamed from: z */
    public Map<String, String> f2513z;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(CrashDetailBean crashDetailBean) {
        CrashDetailBean crashDetailBean2 = crashDetailBean;
        if (crashDetailBean2 == null) {
            return 1;
        }
        long j = this.f2505r - crashDetailBean2.f2505r;
        if (j <= 0) {
            return j < 0 ? -1 : 0;
        }
        return 1;
    }

    public CrashDetailBean() {
        this.f2488a = -1L;
        this.f2489b = 0;
        this.f2490c = UUID.randomUUID().toString();
        this.f2491d = false;
        this.f2492e = "";
        this.f2493f = "";
        this.f2494g = "";
        this.f2495h = null;
        this.f2496i = null;
        this.f2497j = false;
        this.f2498k = false;
        this.f2499l = 0;
        this.f2500m = "";
        this.f2501n = "";
        this.f2502o = "";
        this.f2503p = "";
        this.f2504q = "";
        this.f2505r = -1L;
        this.f2506s = null;
        this.f2507t = 0;
        this.f2508u = "";
        this.f2509v = "";
        this.f2510w = null;
        this.f2511x = null;
        this.f2512y = null;
        this.f2513z = null;
        this.f2464A = "";
        this.f2465B = "";
        this.f2466C = -1L;
        this.f2467D = -1L;
        this.f2468E = -1L;
        this.f2469F = -1L;
        this.f2470G = -1L;
        this.f2471H = -1L;
        this.f2472I = "";
        this.f2487X = "";
        this.f2473J = "";
        this.f2474K = "";
        this.f2475L = "";
        this.f2476M = -1L;
        this.f2477N = false;
        this.f2478O = null;
        this.f2479P = null;
        this.f2480Q = -1;
        this.f2481R = -1;
        this.f2482S = null;
        this.f2483T = null;
        this.f2484U = null;
        this.f2485V = null;
        this.f2486W = null;
    }

    public CrashDetailBean(Parcel parcel) {
        this.f2488a = -1L;
        this.f2489b = 0;
        this.f2490c = UUID.randomUUID().toString();
        this.f2491d = false;
        this.f2492e = "";
        this.f2493f = "";
        this.f2494g = "";
        this.f2495h = null;
        this.f2496i = null;
        this.f2497j = false;
        this.f2498k = false;
        this.f2499l = 0;
        this.f2500m = "";
        this.f2501n = "";
        this.f2502o = "";
        this.f2503p = "";
        this.f2504q = "";
        this.f2505r = -1L;
        this.f2506s = null;
        this.f2507t = 0;
        this.f2508u = "";
        this.f2509v = "";
        this.f2510w = null;
        this.f2511x = null;
        this.f2512y = null;
        this.f2513z = null;
        this.f2464A = "";
        this.f2465B = "";
        this.f2466C = -1L;
        this.f2467D = -1L;
        this.f2468E = -1L;
        this.f2469F = -1L;
        this.f2470G = -1L;
        this.f2471H = -1L;
        this.f2472I = "";
        this.f2487X = "";
        this.f2473J = "";
        this.f2474K = "";
        this.f2475L = "";
        this.f2476M = -1L;
        this.f2477N = false;
        this.f2478O = null;
        this.f2479P = null;
        this.f2480Q = -1;
        this.f2481R = -1;
        this.f2482S = null;
        this.f2483T = null;
        this.f2484U = null;
        this.f2485V = null;
        this.f2486W = null;
        this.f2489b = parcel.readInt();
        this.f2490c = parcel.readString();
        this.f2491d = parcel.readByte() == 1;
        this.f2492e = parcel.readString();
        this.f2493f = parcel.readString();
        this.f2494g = parcel.readString();
        this.f2497j = parcel.readByte() == 1;
        this.f2498k = parcel.readByte() == 1;
        this.f2499l = parcel.readInt();
        this.f2500m = parcel.readString();
        this.f2501n = parcel.readString();
        this.f2502o = parcel.readString();
        this.f2503p = parcel.readString();
        this.f2504q = parcel.readString();
        this.f2505r = parcel.readLong();
        this.f2506s = parcel.readString();
        this.f2507t = parcel.readInt();
        this.f2508u = parcel.readString();
        this.f2509v = parcel.readString();
        this.f2510w = parcel.readString();
        this.f2513z = C2023z.m1921b(parcel);
        this.f2464A = parcel.readString();
        this.f2465B = parcel.readString();
        this.f2466C = parcel.readLong();
        this.f2467D = parcel.readLong();
        this.f2468E = parcel.readLong();
        this.f2469F = parcel.readLong();
        this.f2470G = parcel.readLong();
        this.f2471H = parcel.readLong();
        this.f2472I = parcel.readString();
        this.f2487X = parcel.readString();
        this.f2473J = parcel.readString();
        this.f2474K = parcel.readString();
        this.f2475L = parcel.readString();
        this.f2476M = parcel.readLong();
        this.f2477N = parcel.readByte() == 1;
        this.f2478O = C2023z.m1921b(parcel);
        this.f2495h = C2023z.m1908a(parcel);
        this.f2496i = C2023z.m1908a(parcel);
        this.f2480Q = parcel.readInt();
        this.f2481R = parcel.readInt();
        this.f2482S = C2023z.m1921b(parcel);
        this.f2483T = C2023z.m1921b(parcel);
        this.f2484U = parcel.createByteArray();
        this.f2512y = parcel.createByteArray();
        this.f2485V = parcel.readString();
        this.f2486W = parcel.readString();
        this.f2511x = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2489b);
        parcel.writeString(this.f2490c);
        parcel.writeByte(this.f2491d ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f2492e);
        parcel.writeString(this.f2493f);
        parcel.writeString(this.f2494g);
        parcel.writeByte(this.f2497j ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f2498k ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f2499l);
        parcel.writeString(this.f2500m);
        parcel.writeString(this.f2501n);
        parcel.writeString(this.f2502o);
        parcel.writeString(this.f2503p);
        parcel.writeString(this.f2504q);
        parcel.writeLong(this.f2505r);
        parcel.writeString(this.f2506s);
        parcel.writeInt(this.f2507t);
        parcel.writeString(this.f2508u);
        parcel.writeString(this.f2509v);
        parcel.writeString(this.f2510w);
        C2023z.m1923b(parcel, this.f2513z);
        parcel.writeString(this.f2464A);
        parcel.writeString(this.f2465B);
        parcel.writeLong(this.f2466C);
        parcel.writeLong(this.f2467D);
        parcel.writeLong(this.f2468E);
        parcel.writeLong(this.f2469F);
        parcel.writeLong(this.f2470G);
        parcel.writeLong(this.f2471H);
        parcel.writeString(this.f2472I);
        parcel.writeString(this.f2487X);
        parcel.writeString(this.f2473J);
        parcel.writeString(this.f2474K);
        parcel.writeString(this.f2475L);
        parcel.writeLong(this.f2476M);
        parcel.writeByte(this.f2477N ? (byte) 1 : (byte) 0);
        C2023z.m1923b(parcel, this.f2478O);
        C2023z.m1909a(parcel, this.f2495h);
        C2023z.m1909a(parcel, this.f2496i);
        parcel.writeInt(this.f2480Q);
        parcel.writeInt(this.f2481R);
        C2023z.m1923b(parcel, this.f2482S);
        C2023z.m1923b(parcel, this.f2483T);
        parcel.writeByteArray(this.f2484U);
        parcel.writeByteArray(this.f2512y);
        parcel.writeString(this.f2485V);
        parcel.writeString(this.f2486W);
        parcel.writeString(this.f2511x);
    }
}
