package com.google.android.gms.internal.ads;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzapb extends IInterface {
    void onActivityResult(int arg1, int arg2, Intent arg3) throws RemoteException;

    void onBackPressed() throws RemoteException;

    void onCreate(Bundle arg1) throws RemoteException;

    void onDestroy() throws RemoteException;

    void onPause() throws RemoteException;

    void onRestart() throws RemoteException;

    void onResume() throws RemoteException;

    void onSaveInstanceState(Bundle arg1) throws RemoteException;

    void onStart() throws RemoteException;

    void onStop() throws RemoteException;

    void zzad(IObjectWrapper arg1) throws RemoteException;

    void zzdk() throws RemoteException;

    boolean zztr() throws RemoteException;
}

