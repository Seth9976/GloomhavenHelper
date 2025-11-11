package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzc;

public interface IAccountAccessor extends IInterface {
    public static abstract class Stub extends zzb implements IAccountAccessor {
        public static final class zza extends com.google.android.gms.internal.common.zza implements IAccountAccessor {
            zza(IBinder iBinder0) {
                super(iBinder0, "com.google.android.gms.common.internal.IAccountAccessor");
            }

            @Override  // com.google.android.gms.common.internal.IAccountAccessor
            public final Account getAccount() throws RemoteException {
                Parcel parcel0 = this.zza(2, this.zza());
                Account account0 = (Account)zzc.zza(parcel0, Account.CREATOR);
                parcel0.recycle();
                return account0;
            }
        }

        public Stub() {
            super("com.google.android.gms.common.internal.IAccountAccessor");
        }

        public static IAccountAccessor asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
            return iInterface0 instanceof IAccountAccessor ? ((IAccountAccessor)iInterface0) : new zza(iBinder0);
        }

        @Override  // com.google.android.gms.internal.common.zzb
        protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            if(v == 2) {
                Account account0 = this.getAccount();
                parcel1.writeNoException();
                zzc.zzb(parcel1, account0);
                return true;
            }
            return false;
        }
    }

    Account getAccount() throws RemoteException;
}

