package com.baidu.lbsapi.auth.tracesdk;

import android.content.Context;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.tracesdk.c */
/* JADX INFO: loaded from: classes.dex */
class C0659c {

    /* JADX INFO: renamed from: a */
    private Context f158a;

    /* JADX INFO: renamed from: b */
    private HashMap f159b = null;

    /* JADX INFO: renamed from: c */
    private a f160c = null;

    /* JADX INFO: renamed from: com.baidu.lbsapi.auth.tracesdk.c$a */
    interface a {
        /* JADX INFO: renamed from: a */
        void mo209a(Object obj);
    }

    protected C0659c(Context context) {
        this.f158a = context;
    }

    /* JADX INFO: renamed from: a */
    private HashMap m204a(HashMap map) {
        HashMap map2 = new HashMap();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String string = ((String) it.next()).toString();
            map2.put(string, map.get(string));
        }
        return map2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m206a(String str) {
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
        a aVar = this.f160c;
        if (aVar != null) {
            aVar.mo209a(jSONObject.toString());
        }
    }

    /* JADX INFO: renamed from: a */
    protected void m208a(HashMap map, a aVar) {
        this.f159b = m204a(map);
        this.f160c = aVar;
        new Thread(new RunnableC0660d(this)).start();
    }
}
