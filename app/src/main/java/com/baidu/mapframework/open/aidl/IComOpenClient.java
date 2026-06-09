package com.baidu.mapframework.open.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IComOpenClient extends IInterface {

    /* JADX INFO: renamed from: com.baidu.mapframework.open.aidl.IComOpenClient$a */
    public static abstract class AbstractBinderC0715a extends Binder implements IComOpenClient {

        /* JADX INFO: renamed from: com.baidu.mapframework.open.aidl.IComOpenClient$a$a */
        private static class a implements IComOpenClient {

            /* JADX INFO: renamed from: a */
            private IBinder f769a;

            a(IBinder iBinder) {
                this.f769a = iBinder;
            }

            @Override // com.baidu.mapframework.open.aidl.IComOpenClient
            /* JADX INFO: renamed from: a */
            public String mo505a(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.baidu.mapframework.open.aidl.IComOpenClient");
                    parcelObtain.writeString(str);
                    this.f769a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.baidu.mapframework.open.aidl.IComOpenClient
            /* JADX INFO: renamed from: a */
            public boolean mo506a(String str, String str2, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.baidu.mapframework.open.aidl.IComOpenClient");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.f769a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f769a;
            }
        }

        /* JADX INFO: renamed from: a */
        public static IComOpenClient m507a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.baidu.mapframework.open.aidl.IComOpenClient");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IComOpenClient)) ? new a(iBinder) : (IComOpenClient) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.baidu.mapframework.open.aidl.IComOpenClient");
                String strA = mo505a(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(strA);
                return true;
            }
            if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.baidu.mapframework.open.aidl.IComOpenClient");
                return true;
            }
            parcel.enforceInterface("com.baidu.mapframework.open.aidl.IComOpenClient");
            boolean zA = mo506a(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            parcel2.writeInt(zA ? 1 : 0);
            return true;
        }
    }

    /* JADX INFO: renamed from: a */
    String mo505a(String str) throws RemoteException;

    /* JADX INFO: renamed from: a */
    boolean mo506a(String str, String str2, Bundle bundle) throws RemoteException;
}
