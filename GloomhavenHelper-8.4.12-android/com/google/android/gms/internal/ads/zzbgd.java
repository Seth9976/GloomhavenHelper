package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;
import java.util.Map;

public interface zzbgd extends IInterface {
    void beginAdUnitExposure(String arg1) throws RemoteException;

    void clearConditionalUserProperty(String arg1, String arg2, Bundle arg3) throws RemoteException;

    void endAdUnitExposure(String arg1) throws RemoteException;

    long generateEventId() throws RemoteException;

    String getAppIdOrigin() throws RemoteException;

    String getAppInstanceId() throws RemoteException;

    List getConditionalUserProperties(String arg1, String arg2) throws RemoteException;

    String getCurrentScreenClass() throws RemoteException;

    String getCurrentScreenName() throws RemoteException;

    String getGmpAppId() throws RemoteException;

    int getMaxUserProperties(String arg1) throws RemoteException;

    Map getUserProperties(String arg1, String arg2, boolean arg3) throws RemoteException;

    void logEvent(String arg1, String arg2, Bundle arg3) throws RemoteException;

    void performAction(Bundle arg1) throws RemoteException;

    Bundle performActionWithResponse(Bundle arg1) throws RemoteException;

    void setConditionalUserProperty(Bundle arg1) throws RemoteException;

    void zza(String arg1, String arg2, IObjectWrapper arg3) throws RemoteException;

    void zzb(IObjectWrapper arg1, String arg2, String arg3) throws RemoteException;
}

