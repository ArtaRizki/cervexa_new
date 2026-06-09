package com.baidu.trace.p011b;

import com.baidu.trace.C0791a;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/* JADX INFO: renamed from: com.baidu.trace.b.k */
/* JADX INFO: loaded from: classes.dex */
public final class C0839k implements InterfaceC0830b {

    /* JADX INFO: renamed from: a */
    private DatagramSocket f1669a = null;

    /* JADX INFO: renamed from: b */
    private DatagramPacket f1670b = null;

    /* JADX INFO: renamed from: c */
    private InetAddress f1671c = null;

    /* JADX INFO: renamed from: d */
    private int f1672d;

    private C0839k(int i) {
        this.f1672d = 8300;
        this.f1672d = i;
    }

    /* JADX INFO: renamed from: a */
    protected static C0839k m1162a(int i) {
        return new C0839k(i);
    }

    @Override // com.baidu.trace.p011b.InterfaceC0830b
    /* JADX INFO: renamed from: a */
    public final void mo1128a() {
        try {
            this.f1670b = null;
            this.f1671c = null;
            if (this.f1669a != null && !this.f1669a.isClosed()) {
                this.f1669a.close();
            }
        } catch (Exception unused) {
        } finally {
            this.f1669a = null;
        }
    }

    @Override // com.baidu.trace.p011b.InterfaceC0830b
    /* JADX INFO: renamed from: a */
    public final void mo1129a(C0833e c0833e) {
        try {
            this.f1669a = new DatagramSocket(this.f1672d);
            this.f1671c = InetAddress.getByName("gateway.yingyan.baidu.com");
            this.f1670b = new DatagramPacket((byte[]) null, 0, this.f1671c, 8300);
        } catch (IOException unused) {
            C0791a.m995a("udp socket connect failed");
        }
    }

    @Override // com.baidu.trace.p011b.InterfaceC0830b
    /* JADX INFO: renamed from: a */
    public final void mo1130a(byte[] bArr) throws IOException {
        DatagramPacket datagramPacket = this.f1670b;
        if (datagramPacket == null) {
            this.f1670b = new DatagramPacket(bArr, bArr.length, this.f1671c, 8300);
        } else {
            datagramPacket.setData(bArr);
            this.f1670b.setLength(bArr.length);
        }
        this.f1669a.send(this.f1670b);
    }

    @Override // com.baidu.trace.p011b.InterfaceC0830b
    /* JADX INFO: renamed from: b */
    public final boolean mo1131b() {
        return true;
    }

    @Override // com.baidu.trace.p011b.InterfaceC0830b
    /* JADX INFO: renamed from: c */
    public final int mo1132c() {
        return this.f1672d;
    }

    @Override // com.baidu.trace.p011b.InterfaceC0830b
    /* JADX INFO: renamed from: d */
    public final InputStream mo1133d() throws IOException {
        return null;
    }
}
