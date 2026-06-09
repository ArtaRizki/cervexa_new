package com.baidu.mapframework.open.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.baidu.mapframework.open.aidl.InterfaceC0717b;

/* JADX INFO: renamed from: com.baidu.mapframework.open.aidl.a */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC0716a extends IInterface {

    /* JADX INFO: renamed from: com.baidu.mapframework.open.aidl.a$a */
    public static abstract class a extends Binder implements InterfaceC0716a {

        /* JADX INFO: renamed from: com.baidu.mapframework.open.aidl.a$a$a, reason: collision with other inner class name */
        private static class C2947a implements InterfaceC0716a {

            /* JADX INFO: renamed from: a */
            private IBinder f770a;

            C2947a(IBinder iBinder) {
                this.f770a = iBinder;
            }

            @Override // com.baidu.mapframework.open.aidl.InterfaceC0716a
            /* JADX INFO: renamed from: a */
            public void mo508a(InterfaceC0717b interfaceC0717b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.baidu.mapframework.open.aidl.IMapOpenService");
                    parcelObtain.writeStrongBinder(interfaceC0717b != null ? interfaceC0717b.asBinder() : null);
                    this.f770a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f770a;
            }
        }

        /* JADX INFO: renamed from: a */
        public static InterfaceC0716a m509a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.baidu.mapframework.open.aidl.IMapOpenService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0716a)) ? new C2947a(iBinder) : (InterfaceC0716a) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.baidu.mapframework.open.aidl.IMapOpenService");
                return true;
            }
            parcel.enforceInterface("com.baidu.mapframework.open.aidl.IMapOpenService");
            mo508a(InterfaceC0717b.a.m510b(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }
    }

    /* JADX INFO: renamed from: a */
    void mo508a(InterfaceC0717b interfaceC0717b) throws RemoteException;
}
