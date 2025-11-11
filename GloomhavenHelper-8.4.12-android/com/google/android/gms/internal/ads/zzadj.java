package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzadj extends IInterface {
    void destroy() throws RemoteException;

    String getAdvertiser() throws RemoteException;

    String getBody() throws RemoteException;

    String getCallToAction() throws RemoteException;

    Bundle getExtras() throws RemoteException;

    String getHeadline() throws RemoteException;

    List getImages() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    zzxj getVideoController() throws RemoteException;

    void performClick(Bundle arg1) throws RemoteException;

    boolean recordImpression(Bundle arg1) throws RemoteException;

    void reportTouchEvent(Bundle arg1) throws RemoteException;

    IObjectWrapper zzrj() throws RemoteException;

    zzacj zzrl() throws RemoteException;

    IObjectWrapper zzrm() throws RemoteException;

    zzacr zzrn() throws RemoteException;
}

