package com.tencent.p023mm.opensdk.modelbiz;

import android.os.Bundle;
import com.tencent.p023mm.opensdk.modelbase.BaseReq;
import com.tencent.p023mm.opensdk.modelbase.BaseResp;
import com.tencent.p023mm.opensdk.utils.C2052d;
import com.tencent.p023mm.opensdk.utils.Log;

/* JADX INFO: loaded from: classes2.dex */
public class WXInvoiceAuthInsert {

    public static final class Req extends BaseReq {
        private static final String TAG = "MicroMsg.SDK.WXInvoiceAuthInsert.Req";
        private static final int URL_LENGTH_LIMIT = 10240;
        public String url;

        @Override // com.tencent.p023mm.opensdk.modelbase.BaseReq
        public final boolean checkArgs() {
            if (C2052d.m2059a(this.url)) {
                Log.m2052i(TAG, "url should not be empty");
                return false;
            }
            if (this.url.length() <= URL_LENGTH_LIMIT) {
                return true;
            }
            Log.m2051e(TAG, "url must be in 10k");
            return false;
        }

        @Override // com.tencent.p023mm.opensdk.modelbase.BaseReq
        public final void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.url = bundle.getString("_wxapi_invoice_auth_insert_req_url");
        }

        @Override // com.tencent.p023mm.opensdk.modelbase.BaseReq
        public final int getType() {
            return 20;
        }

        @Override // com.tencent.p023mm.opensdk.modelbase.BaseReq
        public final void toBundle(Bundle bundle) {
            super.fromBundle(bundle);
            bundle.putString("_wxapi_invoice_auth_insert_req_url", this.url);
        }
    }

    public static final class Resp extends BaseResp {
        public String wxOrderId;

        @Override // com.tencent.p023mm.opensdk.modelbase.BaseResp
        public final boolean checkArgs() {
            return true;
        }

        @Override // com.tencent.p023mm.opensdk.modelbase.BaseResp
        public final void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.wxOrderId = bundle.getString("_wxapi_invoice_auth_insert_order_id");
        }

        @Override // com.tencent.p023mm.opensdk.modelbase.BaseResp
        public final int getType() {
            return 20;
        }

        @Override // com.tencent.p023mm.opensdk.modelbase.BaseResp
        public final void toBundle(Bundle bundle) {
            super.fromBundle(bundle);
            bundle.putString("_wxapi_invoice_auth_insert_order_id", this.wxOrderId);
        }
    }
}
