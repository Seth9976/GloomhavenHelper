package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzary extends IInterface {
    void destroy() throws RemoteException;

    Bundle getAdMetadata() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    boolean isLoaded() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setAppPackageName(String arg1) throws RemoteException;

    void setCustomData(String arg1) throws RemoteException;

    void setImmersiveMode(boolean arg1) throws RemoteException;

    void setUserId(String arg1) throws RemoteException;

    void show() throws RemoteException;

    void zza(zzarw arg1) throws RemoteException;

    void zza(zzasb arg1) throws RemoteException;

    void zza(zzash arg1) throws RemoteException;

    void zza(zzwa arg1) throws RemoteException;

    void zzi(IObjectWrapper arg1) throws RemoteException;

    void zzj(IObjectWrapper arg1) throws RemoteException;

    void zzk(IObjectWrapper arg1) throws RemoteException;

    zzxe zzkg() throws RemoteException;

    void zzl(IObjectWrapper arg1) throws RemoteException;

    boolean zzqd() throws RemoteException;
}

