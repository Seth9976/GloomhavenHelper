package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;

public final class zzcsa extends zzvo {
    private final zzcsk zzgfo;

    public zzcsa(Context context0, zzbgk zzbgk0, zzdew zzdew0, zzbzg zzbzg0, zzvk zzvk0) {
        zzcsm zzcsm0 = new zzcsm(zzbzg0);
        zzcsm0.zzc(zzvk0);
        this.zzgfo = new zzcsk(new zzcss(zzbgk0, context0, zzcsm0, zzdew0), zzdew0.zzara());
    }

    @Override  // com.google.android.gms.internal.ads.zzvp
    public final String getMediationAdapterClassName() {
        synchronized(this) {
            return this.zzgfo.getMediationAdapterClassName();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvp
    public final boolean isLoading() throws RemoteException {
        synchronized(this) {
            return this.zzgfo.isLoading();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvp
    public final void zza(zzuh zzuh0, int v) throws RemoteException {
        synchronized(this) {
            this.zzgfo.zza(zzuh0, v);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzvp
    public final void zzb(zzuh zzuh0) throws RemoteException {
        this.zzgfo.zza(zzuh0, 1);
    }

    @Override  // com.google.android.gms.internal.ads.zzvp
    public final String zzkf() {
        synchronized(this) {
            return this.zzgfo.zzkf();
        }
    }
}

