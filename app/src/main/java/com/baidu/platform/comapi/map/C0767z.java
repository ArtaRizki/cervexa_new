package com.baidu.platform.comapi.map;

import android.os.Message;

/* JADX INFO: renamed from: com.baidu.platform.comapi.map.z */
/* JADX INFO: loaded from: classes.dex */
class C0767z {

    /* JADX INFO: renamed from: a */
    private static final String f1035a = C0767z.class.getSimpleName();

    /* JADX INFO: renamed from: b */
    private InterfaceC0766y f1036b;

    C0767z() {
    }

    /* JADX INFO: renamed from: a */
    void m753a(Message message) {
        if (message.what != 65289) {
            return;
        }
        int i = message.arg1;
        if (i != 12 && i != 101 && i != 102) {
            switch (i) {
            }
            return;
        }
        InterfaceC0766y interfaceC0766y = this.f1036b;
        if (interfaceC0766y != null) {
            interfaceC0766y.mo447a(message.arg1, message.arg2);
        }
    }

    /* JADX INFO: renamed from: a */
    void m754a(InterfaceC0766y interfaceC0766y) {
        this.f1036b = interfaceC0766y;
    }

    /* JADX INFO: renamed from: b */
    void m755b(InterfaceC0766y interfaceC0766y) {
        this.f1036b = null;
    }
}
