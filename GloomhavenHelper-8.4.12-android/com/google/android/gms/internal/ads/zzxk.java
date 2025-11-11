package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzxk extends IInterface {
    void onVideoEnd() throws RemoteException;

    void onVideoMute(boolean arg1) throws RemoteException;

    void onVideoPause() throws RemoteException;

    void onVideoPlay() throws RemoteException;

    void onVideoStart() throws RemoteException;
}

