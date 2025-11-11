package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzyi extends zzvo {
    final zzyg zzcfz;

    private zzyi(zzyg zzyg0) {
        this.zzcfz = zzyg0;
        super();
    }

    zzyi(zzyg zzyg0, zzyj zzyj0) {
        this(zzyg0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvp
    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzvp
    public final boolean isLoading() throws RemoteException {
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzvp
    public final void zza(zzuh zzuh0, int v) throws RemoteException {
        zzazh.zzey("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzyl zzyl0 = new zzyl(this);
        zzayx.zzyy.post(zzyl0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvp
    public final void zzb(zzuh zzuh0) throws RemoteException {
        this.zza(zzuh0, 1);
    }

    @Override  // com.google.android.gms.internal.ads.zzvp
    public final String zzkf() throws RemoteException {
        return null;
    }
}

