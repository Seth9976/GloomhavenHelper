package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzacr extends IInterface {
    int getHeight() throws RemoteException;

    double getScale() throws RemoteException;

    Uri getUri() throws RemoteException;

    int getWidth() throws RemoteException;

    IObjectWrapper zzrg() throws RemoteException;
}

