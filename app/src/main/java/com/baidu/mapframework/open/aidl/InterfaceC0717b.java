package com.baidu.mapframework.open.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: renamed from: com.baidu.mapframework.open.aidl.b */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC0717b extends IInterface {

    /* JADX INFO: renamed from: com.baidu.mapframework.open.aidl.b$a */
    public static abstract class a extends Binder implements InterfaceC0717b {

        /* JADX INFO: renamed from: com.baidu.mapframework.open.aidl.b$a$a, reason: collision with other inner class name */
        private static class C2948a implements InterfaceC0717b {

            /* JADX INFO: renamed from: a */
            private IBinder f771a;

            C2948a(IBinder iBinder) {
                this.f771a = iBinder;
            }

            @Override // com.baidu.mapframework.open.aidl.InterfaceC0717b
            /* JADX INFO: renamed from: a */
            public void mo497a(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.baidu.mapframework.open.aidl.IOpenClientCallback");
                    parcelObtain.writeStrongBinder(iBinder);
                    this.f771a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f771a;
            }
        }

        public a() {
            attachInterface(this, "com.baidu.mapframework.open.aidl.IOpenClientCallback");
        }

        /* JADX INFO: renamed from: b */
        public static InterfaceC0717b m510b(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.baidu.mapframework.open.aidl.IOpenClientCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0717b)) ? new C2948a(iBinder) : (InterfaceC0717b) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.baidu.mapframework.open.aidl.IOpenClientCallback");
                return true;
            }
            parcel.enforceInterface("com.baidu.mapframework.open.aidl.IOpenClientCallback");
            mo497a(parcel.readStrongBinder());
            parcel2.writeNoException();
            return true;
        }
    }

    /* JADX INFO: renamed from: a */
    void mo497a(IBinder iBinder) throws RemoteException;
}
