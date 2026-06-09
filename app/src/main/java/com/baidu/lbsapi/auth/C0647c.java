package com.baidu.lbsapi.auth;

import android.content.Context;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.c */
/* JADX INFO: loaded from: classes.dex */
class C0647c {

    /* JADX INFO: renamed from: a */
    private Context f122a;

    /* JADX INFO: renamed from: b */
    private HashMap<String, String> f123b = null;

    /* JADX INFO: renamed from: c */
    private a<String> f124c = null;

    /* JADX INFO: renamed from: com.baidu.lbsapi.auth.c$a */
    interface a<Result> {
        /* JADX INFO: renamed from: a */
        void mo150a(Result result);
    }

    protected C0647c(Context context) {
        this.f122a = context;
    }

    /* JADX INFO: renamed from: a */
    private HashMap<String, String> m145a(HashMap<String, String> map) {
        HashMap<String, String> map2 = new HashMap<>();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String string = it.next().toString();
            map2.put(string, map.get(string));
        }
        return map2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m147a(String str) {
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
        a<String> aVar = this.f124c;
        if (aVar != null) {
            aVar.mo150a(jSONObject.toString());
        }
    }

    /* JADX INFO: renamed from: a */
    protected void m149a(HashMap<String, String> map, a<String> aVar) {
        this.f123b = m145a(map);
        this.f124c = aVar;
        new Thread(new RunnableC0648d(this)).start();
    }
}
