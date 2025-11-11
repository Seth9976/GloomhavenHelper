package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzalp extends IInterface {
    void destroy() throws RemoteException;

    Bundle getInterstitialAdapterInfo() throws RemoteException;

    zzxj getVideoController() throws RemoteException;

    boolean isInitialized() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setImmersiveMode(boolean arg1) throws RemoteException;

    void showInterstitial() throws RemoteException;

    void showVideo() throws RemoteException;

    void zza(IObjectWrapper arg1, zzahb arg2, List arg3) throws RemoteException;

    void zza(IObjectWrapper arg1, zzasm arg2, List arg3) throws RemoteException;

    void zza(IObjectWrapper arg1, zzuh arg2, String arg3, zzalq arg4) throws RemoteException;

    void zza(IObjectWrapper arg1, zzuh arg2, String arg3, zzasm arg4, String arg5) throws RemoteException;

    void zza(IObjectWrapper arg1, zzuh arg2, String arg3, String arg4, zzalq arg5) throws RemoteException;

    void zza(IObjectWrapper arg1, zzuh arg2, String arg3, String arg4, zzalq arg5, zzach arg6, List arg7) throws RemoteException;

    void zza(IObjectWrapper arg1, zzuk arg2, zzuh arg3, String arg4, zzalq arg5) throws RemoteException;

    void zza(IObjectWrapper arg1, zzuk arg2, zzuh arg3, String arg4, String arg5, zzalq arg6) throws RemoteException;

    void zza(zzuh arg1, String arg2) throws RemoteException;

    void zza(zzuh arg1, String arg2, String arg3) throws RemoteException;

    void zzb(IObjectWrapper arg1, zzuh arg2, String arg3, zzalq arg4) throws RemoteException;

    void zzs(IObjectWrapper arg1) throws RemoteException;

    IObjectWrapper zzsp() throws RemoteException;

    zzalx zzsq() throws RemoteException;

    zzaly zzsr() throws RemoteException;

    Bundle zzss() throws RemoteException;

    Bundle zzst() throws RemoteException;

    boolean zzsu() throws RemoteException;

    zzadn zzsv() throws RemoteException;

    zzamd zzsw() throws RemoteException;

    void zzt(IObjectWrapper arg1) throws RemoteException;
}

