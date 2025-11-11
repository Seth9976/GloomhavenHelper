package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzvx extends IInterface {
    void destroy() throws RemoteException;

    Bundle getAdMetadata() throws RemoteException;

    String getAdUnitId() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    zzxj getVideoController() throws RemoteException;

    boolean isLoading() throws RemoteException;

    boolean isReady() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setImmersiveMode(boolean arg1) throws RemoteException;

    void setManualImpressionsEnabled(boolean arg1) throws RemoteException;

    void setUserId(String arg1) throws RemoteException;

    void showInterstitial() throws RemoteException;

    void stopLoading() throws RemoteException;

    void zza(zzaaq arg1) throws RemoteException;

    void zza(zzapl arg1) throws RemoteException;

    void zza(zzapr arg1, String arg2) throws RemoteException;

    void zza(zzasb arg1) throws RemoteException;

    void zza(zzrh arg1) throws RemoteException;

    void zza(zzuk arg1) throws RemoteException;

    void zza(zzur arg1) throws RemoteException;

    void zza(zzvj arg1) throws RemoteException;

    void zza(zzvk arg1) throws RemoteException;

    void zza(zzwa arg1) throws RemoteException;

    void zza(zzwf arg1) throws RemoteException;

    void zza(zzwl arg1) throws RemoteException;

    void zza(zzxd arg1) throws RemoteException;

    void zza(zzxp arg1) throws RemoteException;

    void zza(zzzc arg1) throws RemoteException;

    boolean zza(zzuh arg1) throws RemoteException;

    void zzbs(String arg1) throws RemoteException;

    IObjectWrapper zzkc() throws RemoteException;

    void zzkd() throws RemoteException;

    zzuk zzke() throws RemoteException;

    String zzkf() throws RemoteException;

    zzxe zzkg() throws RemoteException;

    zzwf zzkh() throws RemoteException;

    zzvk zzki() throws RemoteException;
}

