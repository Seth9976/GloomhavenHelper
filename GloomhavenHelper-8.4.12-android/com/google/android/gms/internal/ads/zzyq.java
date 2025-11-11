package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzyq extends zzasw {
    @Override  // com.google.android.gms.internal.ads.zzast
    public final Bundle getAdMetadata() throws RemoteException {
        return new Bundle();
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final String getMediationAdapterClassName() throws RemoteException {
        return "";
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final boolean isLoaded() throws RemoteException {
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(IObjectWrapper iObjectWrapper0, boolean z) {
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzasy zzasy0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzatg zzatg0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzato zzato0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzuh zzuh0, zzatb zzatb0) throws RemoteException {
        zzazh.zzey("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzyt zzyt0 = new zzyt(zzatb0);
        zzayx.zzyy.post(zzyt0);
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzwy zzwy0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzxd zzxd0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zzh(IObjectWrapper iObjectWrapper0) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final zzxe zzkg() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    @Nullable
    public final zzass zzqc() {
        return null;
    }
}

