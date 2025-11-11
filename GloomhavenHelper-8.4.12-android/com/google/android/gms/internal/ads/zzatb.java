package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzatb extends IInterface {
    void onRewardedAdFailedToLoad(int arg1) throws RemoteException;

    void onRewardedAdLoaded() throws RemoteException;
}

