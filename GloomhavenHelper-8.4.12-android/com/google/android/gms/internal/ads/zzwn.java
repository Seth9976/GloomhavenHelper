package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzwn extends IInterface {
    String getVersionString() throws RemoteException;

    void initialize() throws RemoteException;

    void setAppMuted(boolean arg1) throws RemoteException;

    void setAppVolume(float arg1) throws RemoteException;

    void zza(zzahc arg1) throws RemoteException;

    void zza(zzalk arg1) throws RemoteException;

    void zza(zzyw arg1) throws RemoteException;

    void zza(String arg1, IObjectWrapper arg2) throws RemoteException;

    void zzb(IObjectWrapper arg1, String arg2) throws RemoteException;

    void zzce(String arg1) throws RemoteException;

    void zzcf(String arg1) throws RemoteException;

    float zzpj() throws RemoteException;

    boolean zzpk() throws RemoteException;

    List zzpl() throws RemoteException;
}

