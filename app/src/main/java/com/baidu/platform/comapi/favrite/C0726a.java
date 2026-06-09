package com.baidu.platform.comapi.favrite;

import android.os.Bundle;
import android.text.TextUtils;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.platform.comjni.map.favorite.C0784a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.platform.comapi.favrite.a */
/* JADX INFO: loaded from: classes.dex */
public class C0726a {

    /* JADX INFO: renamed from: b */
    private static C0726a f811b;

    /* JADX INFO: renamed from: a */
    private C0784a f812a = null;

    /* JADX INFO: renamed from: c */
    private boolean f813c = false;

    /* JADX INFO: renamed from: d */
    private boolean f814d = false;

    /* JADX INFO: renamed from: e */
    private Vector<String> f815e = null;

    /* JADX INFO: renamed from: f */
    private Vector<String> f816f = null;

    /* JADX INFO: renamed from: g */
    private boolean f817g = false;

    /* JADX INFO: renamed from: h */
    private c f818h;

    /* JADX INFO: renamed from: i */
    private b f819i;

    /* JADX INFO: renamed from: com.baidu.platform.comapi.favrite.a$a */
    class a implements Comparator<String> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(String str, String str2) {
            return str2.compareTo(str);
        }
    }

    /* JADX INFO: renamed from: com.baidu.platform.comapi.favrite.a$b */
    private class b {

        /* JADX INFO: renamed from: b */
        private long f822b;

        /* JADX INFO: renamed from: c */
        private long f823c;

        private b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a */
        public void m556a() {
            this.f822b = System.currentTimeMillis();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: b */
        public void m558b() {
            this.f823c = System.currentTimeMillis();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c */
        public boolean m561c() {
            return this.f823c - this.f822b > 1000;
        }
    }

    /* JADX INFO: renamed from: com.baidu.platform.comapi.favrite.a$c */
    private class c {

        /* JADX INFO: renamed from: b */
        private String f825b;

        /* JADX INFO: renamed from: c */
        private long f826c;

        /* JADX INFO: renamed from: d */
        private long f827d;

        private c() {
            this.f826c = 5000L;
            this.f827d = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a */
        public String m562a() {
            return this.f825b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a */
        public void m564a(String str) {
            this.f825b = str;
            this.f827d = System.currentTimeMillis();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: b */
        public boolean m566b() {
            return TextUtils.isEmpty(this.f825b);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c */
        public boolean m569c() {
            return true;
        }
    }

    private C0726a() {
        this.f818h = new c();
        this.f819i = new b();
    }

    /* JADX INFO: renamed from: a */
    public static C0726a m540a() {
        if (f811b == null) {
            synchronized (C0726a.class) {
                if (f811b == null) {
                    C0726a c0726a = new C0726a();
                    f811b = c0726a;
                    c0726a.m542h();
                }
            }
        }
        return f811b;
    }

    /* JADX INFO: renamed from: g */
    public static boolean m541g() {
        C0784a c0784a;
        C0726a c0726a = f811b;
        return (c0726a == null || (c0784a = c0726a.f812a) == null || !c0784a.m894d()) ? false : true;
    }

    /* JADX INFO: renamed from: h */
    private boolean m542h() {
        if (this.f812a == null) {
            C0784a c0784a = new C0784a();
            this.f812a = c0784a;
            if (c0784a.m884a() == 0) {
                this.f812a = null;
                return false;
            }
            m544j();
            m543i();
        }
        return true;
    }

    /* JADX INFO: renamed from: i */
    private boolean m543i() {
        if (this.f812a == null) {
            return false;
        }
        String str = SysOSUtil.getModuleFileName() + "/";
        this.f812a.m885a(1);
        return this.f812a.m888a(str, "fav_poi", "fifo", 10, 501, -1);
    }

    /* JADX INFO: renamed from: j */
    private void m544j() {
        this.f813c = false;
        this.f814d = false;
    }

    /* JADX INFO: renamed from: a */
    public synchronized int m545a(String str, FavSyncPoi favSyncPoi) {
        if (this.f812a == null) {
            return 0;
        }
        if (str != null && !str.equals("") && favSyncPoi != null) {
            m544j();
            ArrayList<String> arrayListM553e = m553e();
            if ((arrayListM553e != null ? arrayListM553e.size() : 0) + 1 > 500) {
                return -2;
            }
            if (arrayListM553e != null && arrayListM553e.size() > 0) {
                Iterator<String> it = arrayListM553e.iterator();
                while (it.hasNext()) {
                    FavSyncPoi favSyncPoiM547b = m547b(it.next());
                    if (favSyncPoiM547b != null && str.equals(favSyncPoiM547b.f802b)) {
                        return -1;
                    }
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                favSyncPoi.f802b = str;
                String strValueOf = String.valueOf(System.currentTimeMillis());
                String str2 = strValueOf + "_" + favSyncPoi.hashCode();
                favSyncPoi.f808h = strValueOf;
                favSyncPoi.f801a = str2;
                jSONObject.put("bdetail", favSyncPoi.f809i);
                jSONObject.put("uspoiname", favSyncPoi.f802b);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("x", favSyncPoi.f803c.getmPtx());
                jSONObject2.put("y", favSyncPoi.f803c.getmPty());
                jSONObject.put("pt", jSONObject2);
                jSONObject.put("ncityid", favSyncPoi.f805e);
                jSONObject.put("npoitype", favSyncPoi.f807g);
                jSONObject.put("uspoiuid", favSyncPoi.f806f);
                jSONObject.put("addr", favSyncPoi.f804d);
                jSONObject.put("addtimesec", favSyncPoi.f808h);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("Fav_Sync", jSONObject);
                jSONObject3.put("Fav_Content", favSyncPoi.f810j);
                if (!this.f812a.m887a(str2, jSONObject3.toString())) {
                    return 0;
                }
                m544j();
                return 1;
            } catch (JSONException unused) {
                return 0;
            } finally {
                m541g();
            }
        }
        return -1;
    }

    /* JADX INFO: renamed from: a */
    public synchronized boolean m546a(String str) {
        if (this.f812a == null) {
            return false;
        }
        if (str != null && !str.equals("")) {
            if (!m551c(str)) {
                return false;
            }
            m544j();
            return this.f812a.m886a(str);
        }
        return false;
    }

    /* JADX INFO: renamed from: b */
    public FavSyncPoi m547b(String str) {
        if (this.f812a != null && str != null && !str.equals("")) {
            try {
                if (!m551c(str)) {
                    return null;
                }
                FavSyncPoi favSyncPoi = new FavSyncPoi();
                String strM890b = this.f812a.m890b(str);
                if (strM890b != null && !strM890b.equals("")) {
                    JSONObject jSONObject = new JSONObject(strM890b);
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("Fav_Sync");
                    String strOptString = jSONObject.optString("Fav_Content");
                    favSyncPoi.f802b = jSONObjectOptJSONObject.optString("uspoiname");
                    JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("pt");
                    favSyncPoi.f803c = new Point(jSONObjectOptJSONObject2.optInt("x"), jSONObjectOptJSONObject2.optInt("y"));
                    favSyncPoi.f805e = jSONObjectOptJSONObject.optString("ncityid");
                    favSyncPoi.f806f = jSONObjectOptJSONObject.optString("uspoiuid");
                    favSyncPoi.f807g = jSONObjectOptJSONObject.optInt("npoitype");
                    favSyncPoi.f804d = jSONObjectOptJSONObject.optString("addr");
                    favSyncPoi.f808h = jSONObjectOptJSONObject.optString("addtimesec");
                    favSyncPoi.f809i = jSONObjectOptJSONObject.optBoolean("bdetail");
                    favSyncPoi.f810j = strOptString;
                    favSyncPoi.f801a = str;
                    return favSyncPoi;
                }
                return null;
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (JSONException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: b */
    public void m548b() {
        C0726a c0726a = f811b;
        if (c0726a != null) {
            C0784a c0784a = c0726a.f812a;
            if (c0784a != null) {
                c0784a.m889b();
                f811b.f812a = null;
            }
            f811b = null;
        }
    }

    /* JADX INFO: renamed from: b */
    public synchronized boolean m549b(String str, FavSyncPoi favSyncPoi) {
        boolean z = false;
        if (this.f812a != null && str != null && !str.equals("") && favSyncPoi != null) {
            if (!m551c(str)) {
                return false;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("uspoiname", favSyncPoi.f802b);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("x", favSyncPoi.f803c.getmPtx());
                jSONObject2.put("y", favSyncPoi.f803c.getmPty());
                jSONObject.put("pt", jSONObject2);
                jSONObject.put("ncityid", favSyncPoi.f805e);
                jSONObject.put("npoitype", favSyncPoi.f807g);
                jSONObject.put("uspoiuid", favSyncPoi.f806f);
                jSONObject.put("addr", favSyncPoi.f804d);
                favSyncPoi.f808h = String.valueOf(System.currentTimeMillis());
                jSONObject.put("addtimesec", favSyncPoi.f808h);
                jSONObject.put("bdetail", false);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("Fav_Sync", jSONObject);
                jSONObject3.put("Fav_Content", favSyncPoi.f810j);
                m544j();
                if (this.f812a != null) {
                    if (this.f812a.m891b(str, jSONObject3.toString())) {
                        z = true;
                    }
                }
                return z;
            } catch (JSONException unused) {
                return false;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: c */
    public synchronized boolean m550c() {
        if (this.f812a == null) {
            return false;
        }
        m544j();
        boolean zM892c = this.f812a.m892c();
        m541g();
        return zM892c;
    }

    /* JADX INFO: renamed from: c */
    public boolean m551c(String str) {
        return (this.f812a == null || str == null || str.equals("") || !this.f812a.m893c(str)) ? false : true;
    }

    /* JADX INFO: renamed from: d */
    public ArrayList<String> m552d() {
        String strM890b;
        if (this.f812a == null) {
            return null;
        }
        if (this.f814d && this.f816f != null) {
            return new ArrayList<>(this.f816f);
        }
        try {
            Bundle bundle = new Bundle();
            this.f812a.m883a(bundle);
            String[] stringArray = bundle.getStringArray("rstString");
            if (stringArray != null) {
                if (this.f816f == null) {
                    this.f816f = new Vector<>();
                } else {
                    this.f816f.clear();
                }
                for (int i = 0; i < stringArray.length; i++) {
                    if (!stringArray[i].equals("data_version") && (strM890b = this.f812a.m890b(stringArray[i])) != null && !strM890b.equals("")) {
                        this.f816f.add(stringArray[i]);
                    }
                }
                if (this.f816f.size() > 0) {
                    try {
                        Collections.sort(this.f816f, new a());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.f814d = true;
                }
            } else if (this.f816f != null) {
                this.f816f.clear();
                this.f816f = null;
            }
            if (this.f816f != null && !this.f816f.isEmpty()) {
                return new ArrayList<>(this.f816f);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: e */
    public ArrayList<String> m553e() {
        if (this.f812a == null) {
            return null;
        }
        if (this.f813c && this.f815e != null) {
            return new ArrayList<>(this.f815e);
        }
        try {
            Bundle bundle = new Bundle();
            this.f812a.m883a(bundle);
            String[] stringArray = bundle.getStringArray("rstString");
            if (stringArray != null) {
                if (this.f815e == null) {
                    this.f815e = new Vector<>();
                } else {
                    this.f815e.clear();
                }
                for (String str : stringArray) {
                    if (!str.equals("data_version")) {
                        this.f815e.add(str);
                    }
                }
                if (this.f815e.size() > 0) {
                    try {
                        Collections.sort(this.f815e, new a());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.f813c = true;
                }
            } else if (this.f815e != null) {
                this.f815e.clear();
                this.f815e = null;
            }
            Vector<String> vector = this.f815e;
            if (vector == null || vector.size() == 0) {
                return null;
            }
            return new ArrayList<>(this.f815e);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: f */
    public String m554f() {
        String strM890b;
        if (this.f819i.m561c() && !this.f818h.m569c() && !this.f818h.m566b()) {
            return this.f818h.m562a();
        }
        this.f819i.m556a();
        if (this.f812a == null) {
            return null;
        }
        ArrayList<String> arrayListM552d = m552d();
        JSONObject jSONObject = new JSONObject();
        if (arrayListM552d != null) {
            try {
                JSONArray jSONArray = new JSONArray();
                int i = 0;
                for (String str : arrayListM552d) {
                    if (str != null && !str.equals("data_version") && (strM890b = this.f812a.m890b(str)) != null && !strM890b.equals("")) {
                        JSONObject jSONObjectOptJSONObject = new JSONObject(strM890b).optJSONObject("Fav_Sync");
                        jSONObjectOptJSONObject.put("key", str);
                        jSONArray.put(i, jSONObjectOptJSONObject);
                        i++;
                    }
                }
                if (i > 0) {
                    jSONObject.put("favcontents", jSONArray);
                    jSONObject.put("favpoinum", i);
                }
            } catch (JSONException unused) {
                return null;
            }
        }
        this.f819i.m558b();
        this.f818h.m564a(jSONObject.toString());
        return this.f818h.m562a();
    }
}
