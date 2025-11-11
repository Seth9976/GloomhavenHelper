package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzamd extends IInterface {
    String getAdvertiser() throws RemoteException;

    String getBody() throws RemoteException;

    String getCallToAction() throws RemoteException;

    Bundle getExtras() throws RemoteException;

    String getHeadline() throws RemoteException;

    List getImages() throws RemoteException;

    float getMediaContentAspectRatio() throws RemoteException;

    boolean getOverrideClickHandling() throws RemoteException;

    boolean getOverrideImpressionRecording() throws RemoteException;

    String getPrice() throws RemoteException;

    double getStarRating() throws RemoteException;

    String getStore() throws RemoteException;

    zzxj getVideoController() throws RemoteException;

    float getVideoCurrentTime() throws RemoteException;

    float getVideoDuration() throws RemoteException;

    void recordImpression() throws RemoteException;

    void zzc(IObjectWrapper arg1, IObjectWrapper arg2, IObjectWrapper arg3) throws RemoteException;

    zzacr zzrk() throws RemoteException;

    zzacj zzrl() throws RemoteException;

    IObjectWrapper zzrm() throws RemoteException;

    IObjectWrapper zzsz() throws RemoteException;

    IObjectWrapper zzta() throws RemoteException;

    void zzu(IObjectWrapper arg1) throws RemoteException;

    void zzw(IObjectWrapper arg1) throws RemoteException;
}

