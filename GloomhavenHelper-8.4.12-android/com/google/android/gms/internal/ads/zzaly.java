package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzaly extends IInterface {
    String getAdvertiser() throws RemoteException;

    String getBody() throws RemoteException;

    String getCallToAction() throws RemoteException;

    Bundle getExtras() throws RemoteException;

    String getHeadline() throws RemoteException;

    List getImages() throws RemoteException;

    boolean getOverrideClickHandling() throws RemoteException;

    boolean getOverrideImpressionRecording() throws RemoteException;

    zzxj getVideoController() throws RemoteException;

    void recordImpression() throws RemoteException;

    void zzc(IObjectWrapper arg1, IObjectWrapper arg2, IObjectWrapper arg3) throws RemoteException;

    zzacj zzrl() throws RemoteException;

    IObjectWrapper zzrm() throws RemoteException;

    zzacr zzrn() throws RemoteException;

    IObjectWrapper zzsz() throws RemoteException;

    IObjectWrapper zzta() throws RemoteException;

    void zzu(IObjectWrapper arg1) throws RemoteException;

    void zzv(IObjectWrapper arg1) throws RemoteException;

    void zzw(IObjectWrapper arg1) throws RemoteException;
}

