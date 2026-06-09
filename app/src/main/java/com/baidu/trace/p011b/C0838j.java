package com.baidu.trace.p011b;

import com.baidu.trace.p011b.C0829a;
import com.jieli.stream.p016dv.running2.util.IConstant;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/* JADX INFO: renamed from: com.baidu.trace.b.j */
/* JADX INFO: loaded from: classes.dex */
public final class C0838j implements InterfaceC0830b {

    /* JADX INFO: renamed from: a */
    private Socket f1667a = null;

    /* JADX INFO: renamed from: b */
    private DataOutputStream f1668b = null;

    private C0838j() {
    }

    /* JADX INFO: renamed from: e */
    protected static C0838j m1161e() {
        return new C0838j();
    }

    @Override // com.baidu.trace.p011b.InterfaceC0830b
    /* JADX INFO: renamed from: a */
    public final void mo1128a() {
        try {
            if (this.f1668b != null) {
                this.f1668b.close();
            }
            if (this.f1667a != null && !this.f1667a.isClosed()) {
                this.f1667a.close();
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            this.f1668b = null;
            this.f1667a = null;
            throw th;
        }
        this.f1668b = null;
        this.f1667a = null;
    }

    @Override // com.baidu.trace.p011b.InterfaceC0830b
    /* JADX INFO: renamed from: a */
    public final void mo1129a(C0833e c0833e) {
        try {
            Socket socket = new Socket();
            this.f1667a = socket;
            socket.connect(new InetSocketAddress("gateway.yingyan.baidu.com", 8300), IConstant.AUDIO_SAMPLE_RATE_DEFAULT);
            this.f1667a.setKeepAlive(true);
            if (mo1131b()) {
                c0833e.m1152a();
            } else {
                c0833e.m1153b();
            }
        } catch (Exception unused) {
            if (C0829a.a.f1636a == C0829a.m1121a()) {
                c0833e.m1153b();
            }
        } catch (Throwable unused2) {
            if (C0829a.a.f1636a == C0829a.m1121a()) {
                c0833e.m1153b();
            }
        }
    }

    @Override // com.baidu.trace.p011b.InterfaceC0830b
    /* JADX INFO: renamed from: a */
    public final void mo1130a(byte[] bArr) throws IOException {
        if (this.f1668b == null) {
            this.f1668b = new DataOutputStream(this.f1667a.getOutputStream());
        }
        if (bArr != null) {
            this.f1668b.write(bArr);
            this.f1668b.flush();
        }
    }

    @Override // com.baidu.trace.p011b.InterfaceC0830b
    /* JADX INFO: renamed from: b */
    public final boolean mo1131b() {
        Socket socket = this.f1667a;
        return (socket == null || !socket.isConnected() || this.f1667a.isClosed() || this.f1667a.isInputShutdown() || this.f1667a.isOutputShutdown()) ? false : true;
    }

    @Override // com.baidu.trace.p011b.InterfaceC0830b
    /* JADX INFO: renamed from: c */
    public final int mo1132c() {
        Socket socket = this.f1667a;
        if (socket != null) {
            return socket.getLocalPort();
        }
        return 0;
    }

    @Override // com.baidu.trace.p011b.InterfaceC0830b
    /* JADX INFO: renamed from: d */
    public final InputStream mo1133d() throws IOException {
        return this.f1667a.getInputStream();
    }
}
