package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

public interface zzalq extends IInterface {
    void onAdClicked() throws RemoteException;

    void onAdClosed() throws RemoteException;

    void onAdFailedToLoad(int arg1) throws RemoteException;

    void onAdImpression() throws RemoteException;

    void onAdLeftApplication() throws RemoteException;

    void onAdLoaded() throws RemoteException;

    void onAdOpened() throws RemoteException;

    void onAppEvent(String arg1, String arg2) throws RemoteException;

    void onVideoEnd() throws RemoteException;

    void onVideoPause() throws RemoteException;

    void onVideoPlay() throws RemoteException;

    void zza(zzadn arg1, String arg2) throws RemoteException;

    void zza(zzalv arg1) throws RemoteException;

    void zza(zzass arg1) throws RemoteException;

    void zzb(Bundle arg1) throws RemoteException;

    void zzb(zzasq arg1) throws RemoteException;

    void zzco(int arg1) throws RemoteException;

    void zzdk(String arg1) throws RemoteException;

    void zzsx() throws RemoteException;

    void zzsy() throws RemoteException;
}

