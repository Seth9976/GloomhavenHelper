package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzahb extends IInterface {
    void onInitializationFailed(String arg1) throws RemoteException;

    void onInitializationSucceeded() throws RemoteException;
}

