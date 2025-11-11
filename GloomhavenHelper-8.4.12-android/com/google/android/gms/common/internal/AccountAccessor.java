package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

public class AccountAccessor extends Stub {
    @Override
    public boolean equals(Object object0) {
        throw new NoSuchMethodError();
    }

    @Override  // com.google.android.gms.common.internal.IAccountAccessor
    public final Account getAccount() {
        throw new NoSuchMethodError();
    }

    @KeepForSdk
    public static Account getAccountBinderSafe(IAccountAccessor iAccountAccessor0) {
        if(iAccountAccessor0 != null) {
            long v = Binder.clearCallingIdentity();
            try {
                return iAccountAccessor0.getAccount();
            }
            catch(RemoteException unused_ex) {
                Log.w("AccountAccessor", "Remote account accessor probably died");
            }
            finally {
                Binder.restoreCallingIdentity(v);
            }
        }
        return null;
    }
}

