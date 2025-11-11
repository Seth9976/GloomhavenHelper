package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzacm extends IInterface {
    float getAspectRatio() throws RemoteException;

    float getCurrentTime() throws RemoteException;

    float getDuration() throws RemoteException;

    zzxj getVideoController() throws RemoteException;

    boolean hasVideoContent() throws RemoteException;

    void zza(zzaed arg1) throws RemoteException;

    void zzo(IObjectWrapper arg1) throws RemoteException;

    IObjectWrapper zzri() throws RemoteException;
}

