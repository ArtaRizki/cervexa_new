package com.baidu.trace;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.baidu.trace.IService;
import com.baidu.trace.model.StatusCodes;
import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: renamed from: com.baidu.trace.ap */
/* JADX INFO: loaded from: classes.dex */
final class ServiceConnectionC0817ap implements ServiceConnection {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ LBSTraceClient f1277a;

    ServiceConnectionC0817ap(LBSTraceClient lBSTraceClient) {
        this.f1277a = lBSTraceClient;
    }

    /* JADX WARN: Unreachable blocks removed: 2, instructions: 3 */
    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f1277a.f1131d = IService.Stub.asInterface(iBinder);
        try {
            try {
                this.f1277a.f1131d.registerListener(this.f1277a.f1132e);
                if (this.f1277a.f1135h != null) {
                    this.f1277a.f1135h.onBindServiceCallback(0, StatusCodes.MSG_SUCCESS);
                }
            } catch (Exception e) {
                StringWriter stringWriter = new StringWriter();
                e.printStackTrace(new PrintWriter(stringWriter));
                C0791a.m1005a("BaiduTraceSDK", "register client listener failed, Exception : " + stringWriter.toString());
                if (this.f1277a.f1135h != null) {
                    this.f1277a.f1135h.onBindServiceCallback(1, StatusCodes.MSG_FAILED);
                }
            }
        } catch (Throwable th) {
            if (this.f1277a.f1135h != null) {
                this.f1277a.f1135h.onBindServiceCallback(0, StatusCodes.MSG_SUCCESS);
            }
            throw th;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.f1277a.f1131d = null;
    }
}
