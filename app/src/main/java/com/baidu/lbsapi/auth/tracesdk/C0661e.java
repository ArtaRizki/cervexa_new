package com.baidu.lbsapi.auth.tracesdk;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.tracesdk.e */
/* JADX INFO: loaded from: classes.dex */
class C0661e {

    /* JADX INFO: renamed from: a */
    private Context f162a;

    /* JADX INFO: renamed from: b */
    private List f163b = null;

    /* JADX INFO: renamed from: c */
    private a f164c = null;

    /* JADX INFO: renamed from: com.baidu.lbsapi.auth.tracesdk.e$a */
    interface a {
        /* JADX INFO: renamed from: a */
        void mo216a(Object obj);
    }

    protected C0661e(Context context) {
        this.f162a = context;
    }

    /* JADX INFO: renamed from: a */
    private List m211a(HashMap map, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        if (strArr == null || strArr.length <= 0) {
            HashMap map2 = new HashMap();
            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                String string = ((String) it.next()).toString();
                map2.put(string, map.get(string));
            }
            arrayList.add(map2);
        } else {
            for (String str : strArr) {
                HashMap map3 = new HashMap();
                Iterator it2 = map.keySet().iterator();
                while (it2.hasNext()) {
                    String string2 = ((String) it2.next()).toString();
                    map3.put(string2, map.get(string2));
                }
                map3.put("mcode", str);
                arrayList.add(map3);
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: a */
    private void m213a(String str) {
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
        a aVar = this.f164c;
        if (aVar != null) {
            aVar.mo216a(jSONObject.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m214a(List list) throws Throwable {
        int i;
        C0657a.m189a("syncConnect start Thread id = " + String.valueOf(Thread.currentThread().getId()));
        if (list == null || list.size() == 0) {
            C0657a.m191c("syncConnect failed,params list is null or size is 0");
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < list.size()) {
            C0657a.m189a("syncConnect resuest " + i2 + "  start!!!");
            HashMap map = (HashMap) list.get(i2);
            C0663g c0663g = new C0663g(this.f162a);
            if (c0663g.m223a()) {
                String strM222a = c0663g.m222a(map);
                if (strM222a == null) {
                    strM222a = "";
                }
                C0657a.m189a("syncConnect resuest " + i2 + "  result:" + strM222a);
                arrayList.add(strM222a);
                try {
                    JSONObject jSONObject = new JSONObject(strM222a);
                    if (jSONObject.has("status") && jSONObject.getInt("status") == 0) {
                        C0657a.m189a("auth end and break");
                        m213a(strM222a);
                        return;
                    }
                } catch (JSONException unused) {
                    C0657a.m189a("continue-------------------------------");
                }
            } else {
                C0657a.m189a("Current network is not available.");
                arrayList.add(ErrorMessage.m169a("Current network is not available."));
            }
            C0657a.m189a("syncConnect end");
            i2++;
        }
        C0657a.m189a("--iiiiii:" + i2 + "<><>paramList.size():" + list.size() + "<><>authResults.size():" + arrayList.size());
        if (list.size() <= 0 || i2 != list.size() || arrayList.size() <= 0 || i2 != arrayList.size() || i2 - 1 <= 0) {
            return;
        }
        try {
            JSONObject jSONObject2 = new JSONObject((String) arrayList.get(i));
            if (!jSONObject2.has("status") || jSONObject2.getInt("status") == 0) {
                return;
            }
            C0657a.m189a("i-1 result is not 0,return first result");
            m213a((String) arrayList.get(0));
        } catch (JSONException e) {
            m213a(ErrorMessage.m169a("JSONException:" + e.getMessage()));
        }
    }

    /* JADX INFO: renamed from: a */
    protected void m215a(HashMap map, String[] strArr, a aVar) {
        this.f163b = m211a(map, strArr);
        this.f164c = aVar;
        new Thread(new RunnableC0662f(this)).start();
    }
}
