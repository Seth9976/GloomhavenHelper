package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzys extends zzarx {
    private zzasb zzcge;

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void destroy() throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final Bundle getAdMetadata() throws RemoteException {
        return new Bundle();
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final boolean isLoaded() throws RemoteException {
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void pause() throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void resume() throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void setAppPackageName(String s) {
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void setCustomData(String s) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void setImmersiveMode(boolean z) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void setUserId(String s) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void show() throws RemoteException {
    }

    static zzasb zza(zzys zzys0) {
        return zzys0.zzcge;
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zza(zzarw zzarw0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zza(zzasb zzasb0) throws RemoteException {
        this.zzcge = zzasb0;
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zza(zzash zzash0) throws RemoteException {
        zzazh.zzey("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzyv zzyv0 = new zzyv(this);
        zzayx.zzyy.post(zzyv0);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zza(zzwa zzwa0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zzi(IObjectWrapper iObjectWrapper0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zzj(IObjectWrapper iObjectWrapper0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zzk(IObjectWrapper iObjectWrapper0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final zzxe zzkg() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zzl(IObjectWrapper iObjectWrapper0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final boolean zzqd() {
        return false;
    }
}

