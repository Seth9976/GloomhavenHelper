package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzadn extends IInterface {
    void destroy() throws RemoteException;

    List getAvailableAssetNames() throws RemoteException;

    String getCustomTemplateId() throws RemoteException;

    zzxj getVideoController() throws RemoteException;

    void performClick(String arg1) throws RemoteException;

    void recordImpression() throws RemoteException;

    String zzcu(String arg1) throws RemoteException;

    zzacr zzcv(String arg1) throws RemoteException;

    boolean zzp(IObjectWrapper arg1) throws RemoteException;

    void zzq(IObjectWrapper arg1) throws RemoteException;

    IObjectWrapper zzrj() throws RemoteException;

    IObjectWrapper zzro() throws RemoteException;

    boolean zzrp() throws RemoteException;

    boolean zzrq() throws RemoteException;

    void zzrr() throws RemoteException;
}

