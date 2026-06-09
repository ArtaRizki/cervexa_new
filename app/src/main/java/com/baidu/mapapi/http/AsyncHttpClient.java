package com.baidu.mapapi.http;

import android.os.Build;
import com.baidu.mapapi.UIMsg;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.platform.comapi.util.PermissionCheck;
import com.tencent.bugly.Bugly;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class AsyncHttpClient {

    /* JADX INFO: renamed from: a */
    private int f210a = UIMsg.m_AppUI.MSG_APP_SAVESCREEN;

    /* JADX INFO: renamed from: b */
    private int f211b = UIMsg.m_AppUI.MSG_APP_SAVESCREEN;

    /* JADX INFO: renamed from: c */
    private ExecutorService f212c = Executors.newCachedThreadPool();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.baidu.mapapi.http.AsyncHttpClient$a */
    static abstract class AbstractRunnableC0670a implements Runnable {
        private AbstractRunnableC0670a() {
        }

        /* synthetic */ AbstractRunnableC0670a(C0671a c0671a) {
            this();
        }

        /* JADX INFO: renamed from: a */
        public abstract void mo235a();

        @Override // java.lang.Runnable
        public void run() {
            mo235a();
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 8) {
            System.setProperty("http.keepAlive", Bugly.SDK_IS_DEV);
        }
    }

    public void get(String str, HttpClient.ProtoResultCallback protoResultCallback) {
        if (str == null) {
            throw new IllegalArgumentException("URI cannot be null");
        }
        this.f212c.submit(new C0671a(this, protoResultCallback, str));
    }

    protected boolean isAuthorized() {
        int iPermissionCheck = PermissionCheck.permissionCheck();
        return iPermissionCheck == 0 || iPermissionCheck == 602 || iPermissionCheck == 601;
    }
}
