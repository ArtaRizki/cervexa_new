package com.baidu.platform.comapi.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.lbsapi.auth.LBSAuthManagerListener;
import com.generalplus.GoPlusDrone.Fragment.BaseFragment;
import com.jieli.lib.p015dv.control.utils.TopicKey;
import java.util.Hashtable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PermissionCheck {

    /* JADX INFO: renamed from: b */
    private static Context f1049b;

    /* JADX INFO: renamed from: c */
    private static String f1050c;

    /* JADX INFO: renamed from: d */
    private static Hashtable<String, String> f1051d;

    /* JADX INFO: renamed from: a */
    private static final String f1048a = PermissionCheck.class.getSimpleName();

    /* JADX INFO: renamed from: e */
    private static LBSAuthManager f1052e = null;

    /* JADX INFO: renamed from: f */
    private static LBSAuthManagerListener f1053f = null;

    /* JADX INFO: renamed from: g */
    private static InterfaceC0774c f1054g = null;

    /* JADX INFO: renamed from: com.baidu.platform.comapi.util.PermissionCheck$a */
    private static class C0772a implements LBSAuthManagerListener {
        private C0772a() {
        }

        @Override // com.baidu.lbsapi.auth.LBSAuthManagerListener
        public void onAuthResult(int i, String str) {
            if (str == null) {
                return;
            }
            C0773b c0773b = new C0773b();
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("status")) {
                    c0773b.f1055a = jSONObject.optInt("status");
                }
                if (jSONObject.has("appid")) {
                    c0773b.f1057c = jSONObject.optString("appid");
                }
                if (jSONObject.has("uid")) {
                    c0773b.f1056b = jSONObject.optString("uid");
                }
                if (jSONObject.has("message")) {
                    c0773b.f1058d = jSONObject.optString("message");
                }
                if (jSONObject.has("token")) {
                    c0773b.f1059e = jSONObject.optString("token");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (PermissionCheck.f1054g != null) {
                PermissionCheck.f1054g.mo526a(c0773b);
            }
        }
    }

    /* JADX INFO: renamed from: com.baidu.platform.comapi.util.PermissionCheck$b */
    public static class C0773b {

        /* JADX INFO: renamed from: a */
        public int f1055a = 0;

        /* JADX INFO: renamed from: b */
        public String f1056b = "-1";

        /* JADX INFO: renamed from: c */
        public String f1057c = "-1";

        /* JADX INFO: renamed from: d */
        public String f1058d = "";

        /* JADX INFO: renamed from: e */
        public String f1059e;

        public String toString() {
            return String.format("=============================================\n----------------- 鉴权错误信息 ------------\nsha1;package:%s\nkey:%s\nerrorcode: %d uid: %s appid %s msg: %s\n请仔细核查 SHA1、package与key申请信息是否对应，key是否删除，平台是否匹配\nerrorcode为230时，请参考论坛链接：\nhttp://bbs.lbsyun.baidu.com/forum.php?mod=viewthread&tid=106461\n=============================================\n", C0775a.m769a(PermissionCheck.f1049b), PermissionCheck.f1050c, Integer.valueOf(this.f1055a), this.f1056b, this.f1057c, this.f1058d);
        }
    }

    /* JADX INFO: renamed from: com.baidu.platform.comapi.util.PermissionCheck$c */
    public interface InterfaceC0774c {
        /* JADX INFO: renamed from: a */
        void mo526a(C0773b c0773b);
    }

    public static void destory() {
        f1054g = null;
        f1049b = null;
        f1053f = null;
    }

    public static void init(Context context) {
        ApplicationInfo applicationInfo;
        String string;
        f1049b = context;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(f1049b.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            applicationInfo = null;
        }
        if (applicationInfo != null) {
            f1050c = applicationInfo.metaData.getString("com.baidu.lbsapi.API_KEY");
        }
        if (f1051d == null) {
            f1051d = new Hashtable<>();
        }
        if (f1052e == null) {
            f1052e = LBSAuthManager.getInstance(f1049b);
        }
        if (f1053f == null) {
            f1053f = new C0772a();
        }
        try {
            string = context.getPackageManager().getPackageInfo(f1049b.getPackageName(), 0).applicationInfo.loadLabel(f1049b.getPackageManager()).toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            string = "";
        }
        Bundle bundleM800b = C0779e.m800b();
        f1051d.put("mb", bundleM800b.getString("mb"));
        f1051d.put("os", bundleM800b.getString("os"));
        f1051d.put("sv", bundleM800b.getString("sv"));
        f1051d.put("imt", "1");
        f1051d.put("net", bundleM800b.getString("net"));
        f1051d.put("cpu", bundleM800b.getString("cpu"));
        f1051d.put("glr", bundleM800b.getString("glr"));
        f1051d.put("glv", bundleM800b.getString("glv"));
        f1051d.put("resid", bundleM800b.getString("resid"));
        f1051d.put("appid", "-1");
        f1051d.put(TopicKey.VERSION, "1");
        f1051d.put("screen", String.format("(%d,%d)", Integer.valueOf(bundleM800b.getInt("screen_x")), Integer.valueOf(bundleM800b.getInt("screen_y"))));
        f1051d.put("dpi", String.format("(%d,%d)", Integer.valueOf(bundleM800b.getInt("dpi_x")), Integer.valueOf(bundleM800b.getInt("dpi_y"))));
        f1051d.put("pcn", bundleM800b.getString("pcn"));
        f1051d.put("cuid", bundleM800b.getString("cuid"));
        f1051d.put(BaseFragment.DATA_NAME, string);
    }

    public static synchronized int permissionCheck() {
        if (f1052e != null && f1053f != null && f1049b != null) {
            return f1052e.authenticate(false, "lbs_androidsdk", f1051d, f1053f);
        }
        return 0;
    }

    public static void setPermissionCheckResultListener(InterfaceC0774c interfaceC0774c) {
        f1054g = interfaceC0774c;
    }
}
