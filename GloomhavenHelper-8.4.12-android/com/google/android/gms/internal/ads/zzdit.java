package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzdit extends IInterface {
    String getVersion() throws RemoteException;

    IObjectWrapper zza(String arg1, IObjectWrapper arg2, String arg3, String arg4, String arg5, String arg6) throws RemoteException;

    void zzab(IObjectWrapper arg1) throws RemoteException;

    void zzac(IObjectWrapper arg1) throws RemoteException;

    boolean zzau(IObjectWrapper arg1) throws RemoteException;

    void zzc(IObjectWrapper arg1, IObjectWrapper arg2) throws RemoteException;

    void zzd(IObjectWrapper arg1, IObjectWrapper arg2) throws RemoteException;
}

