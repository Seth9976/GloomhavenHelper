package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzaer extends IInterface {
    void cancelUnconfirmedClick() throws RemoteException;

    void destroy() throws RemoteException;

    String getAdvertiser() throws RemoteException;

    String getBody() throws RemoteException;

    String getCallToAction() throws RemoteException;

    Bundle getExtras() throws RemoteException;

    String getHeadline() throws RemoteException;

    List getImages() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    List getMuteThisAdReasons() throws RemoteException;

    String getPrice() throws RemoteException;

    double getStarRating() throws RemoteException;

    String getStore() throws RemoteException;

    zzxj getVideoController() throws RemoteException;

    boolean isCustomClickGestureEnabled() throws RemoteException;

    boolean isCustomMuteThisAdEnabled() throws RemoteException;

    void performClick(Bundle arg1) throws RemoteException;

    void recordCustomClickGesture() throws RemoteException;

    boolean recordImpression(Bundle arg1) throws RemoteException;

    void reportTouchEvent(Bundle arg1) throws RemoteException;

    void zza(zzaem arg1) throws RemoteException;

    void zza(zzwq arg1) throws RemoteException;

    void zza(zzwu arg1) throws RemoteException;

    void zza(zzxd arg1) throws RemoteException;

    zzxe zzkg() throws RemoteException;

    IObjectWrapper zzrj() throws RemoteException;

    zzacr zzrk() throws RemoteException;

    zzacj zzrl() throws RemoteException;

    IObjectWrapper zzrm() throws RemoteException;

    void zzru() throws RemoteException;

    zzacm zzrv() throws RemoteException;
}

