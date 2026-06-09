package com.baidu.lbsapi.auth;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.e */
/* JADX INFO: loaded from: classes.dex */
class C0649e {

    /* JADX INFO: renamed from: a */
    private Context f126a;

    /* JADX INFO: renamed from: b */
    private List<HashMap<String, String>> f127b = null;

    /* JADX INFO: renamed from: c */
    private a<String> f128c = null;

    /* JADX INFO: renamed from: com.baidu.lbsapi.auth.e$a */
    interface a<Result> {
        /* JADX INFO: renamed from: a */
        void mo157a(Result result);
    }

    protected C0649e(Context context) {
        this.f126a = context;
    }

    /* JADX INFO: renamed from: a */
    private List<HashMap<String, String>> m152a(HashMap<String, String> map, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        if (strArr == null || strArr.length <= 0) {
            HashMap map2 = new HashMap();
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                String string = it.next().toString();
                map2.put(string, map.get(string));
            }
            arrayList.add(map2);
        } else {
            for (String str : strArr) {
                HashMap map3 = new HashMap();
                Iterator<String> it2 = map.keySet().iterator();
                while (it2.hasNext()) {
                    String string2 = it2.next().toString();
                    map3.put(string2, map.get(string2));
                }
                map3.put("mcode", str);
                arrayList.add(map3);
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: a */
    private void m154a(String str) {
        JSONObject jSONObject;
        if (str == null) {
            str = "";
        }
        try {
            jSONObject = new JSONObject(str);
            if (!jSONObject.has("status")) {
                jSONObject.put("status", -1);
            }
        } catch (JSONException unused) {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("status", -1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        a<String> aVar = this.f128c;
        if (aVar != null) {
            aVar.mo157a(jSONObject.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m155a(List<HashMap<String, String>> list) throws Throwable {
        int i;
        C0645a.m130a("syncConnect start Thread id = " + String.valueOf(Thread.currentThread().getId()));
        if (list == null || list.size() == 0) {
            C0645a.m132c("syncConnect failed,params list is null or size is 0");
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < list.size()) {
            C0645a.m130a("syncConnect resuest " + i2 + "  start!!!");
            HashMap<String, String> map = list.get(i2);
            C0651g c0651g = new C0651g(this.f126a);
            if (c0651g.m164a()) {
                String strM163a = c0651g.m163a(map);
                if (strM163a == null) {
                    strM163a = "";
                }
                C0645a.m130a("syncConnect resuest " + i2 + "  result:" + strM163a);
                arrayList.add(strM163a);
                try {
                    JSONObject jSONObject = new JSONObject(strM163a);
                    if (jSONObject.has("status") && jSONObject.getInt("status") == 0) {
                        C0645a.m130a("auth end and break");
                        m154a(strM163a);
                        return;
                    }
                } catch (JSONException unused) {
                    C0645a.m130a("continue-------------------------------");
                }
            } else {
                C0645a.m130a("Current network is not available.");
                arrayList.add(ErrorMessage.m110a("Current network is not available."));
            }
            C0645a.m130a("syncConnect end");
            i2++;
        }
        C0645a.m130a("--iiiiii:" + i2 + "<><>paramList.size():" + list.size() + "<><>authResults.size():" + arrayList.size());
        if (list.size() <= 0 || i2 != list.size() || arrayList.size() <= 0 || i2 != arrayList.size() || i2 - 1 <= 0) {
            return;
        }
        try {
            JSONObject jSONObject2 = new JSONObject((String) arrayList.get(i));
            if (!jSONObject2.has("status") || jSONObject2.getInt("status") == 0) {
                return;
            }
            C0645a.m130a("i-1 result is not 0,return first result");
            m154a((String) arrayList.get(0));
        } catch (JSONException e) {
            m154a(ErrorMessage.m110a("JSONException:" + e.getMessage()));
        }
    }

    /* JADX INFO: renamed from: a */
    protected void m156a(HashMap<String, String> map, String[] strArr, a<String> aVar) {
        this.f127b = m152a(map, strArr);
        this.f128c = aVar;
        new Thread(new RunnableC0650f(this)).start();
    }
}
