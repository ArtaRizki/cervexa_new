package com.tencent.p023mm.opensdk.modelbiz;

import android.os.Bundle;
import com.tencent.p023mm.opensdk.modelbase.BaseReq;
import com.tencent.p023mm.opensdk.modelbase.BaseResp;
import com.tencent.p023mm.opensdk.utils.C2052d;
import com.tencent.p023mm.opensdk.utils.Log;

/* JADX INFO: loaded from: classes2.dex */
public class WXLaunchMiniProgram {

    public static final class Req extends BaseReq {
        public static final int MINIPROGRAM_TYPE_PREVIEW = 2;
        public static final int MINIPROGRAM_TYPE_TEST = 1;
        public static final int MINIPTOGRAM_TYPE_RELEASE = 0;
        private static final String TAG = "MicroMsg.SDK.WXLaunchMiniProgram.Req";
        public String userName;
        public String path = "";
        public int miniprogramType = 0;

        @Override // com.tencent.p023mm.opensdk.modelbase.BaseReq
        public final boolean checkArgs() {
            String str;
            if (C2052d.m2059a(this.userName)) {
                str = "userName is null";
            } else {
                int i = this.miniprogramType;
                if (i >= 0 && i <= 2) {
                    return true;
                }
                str = "miniprogram type should between MINIPTOGRAM_TYPE_RELEASE and MINIPROGRAM_TYPE_PREVIEW";
            }
            Log.m2051e(TAG, str);
            return false;
        }

        @Override // com.tencent.p023mm.opensdk.modelbase.BaseReq
        public final int getType() {
            return 19;
        }

        @Override // com.tencent.p023mm.opensdk.modelbase.BaseReq
        public final void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_launch_wxminiprogram_username", this.userName);
            bundle.putString("_launch_wxminiprogram_path", this.path);
            bundle.putInt("_launch_wxminiprogram_type", this.miniprogramType);
        }
    }

    public static final class Resp extends BaseResp {
        public String extMsg;

        public Resp() {
        }

        public Resp(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.tencent.p023mm.opensdk.modelbase.BaseResp
        public final boolean checkArgs() {
            return true;
        }

        @Override // com.tencent.p023mm.opensdk.modelbase.BaseResp
        public final void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.extMsg = bundle.getString("_launch_wxminiprogram_ext_msg");
        }

        @Override // com.tencent.p023mm.opensdk.modelbase.BaseResp
        public final int getType() {
            return 19;
        }

        @Override // com.tencent.p023mm.opensdk.modelbase.BaseResp
        public final void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_launch_wxminiprogram_ext_msg", this.extMsg);
            bundle.putInt("_wxapi_command_type", getType());
        }
    }
}
