package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzcny implements zzcmf {
    private final zzbwt zzgbv;
    private final Context zzur;

    public zzcny(Context context0, zzbwt zzbwt0) {
        this.zzur = context0;
        this.zzgbv = zzbwt0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final void zza(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa {
        try {
            ((zzanq)zzcmd0.zzdel).zzdn(zzdei0.zzdfr);
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(this.zzur);
            zzcod zzcod0 = new zzcod(this, zzcmd0, null);
            ((zzanq)zzcmd0.zzdel).zza(zzdei0.zzejm, zzdei0.zzgpt.toString(), zzdeq0.zzgql.zzfir.zzgqq, iObjectWrapper0, zzcod0, ((zzalq)zzcmd0.zzgbd));
        }
        catch(RemoteException remoteException0) {
            throw new zzdfa(remoteException0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final Object zzb(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa, zzcpe {
        zzcma zzcma0 = new zzcma(zzdei0);
        zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, zzcmd0.zzfik);
        zzbvz zzbvz0 = new zzbvz(new zzcob(zzcmd0, zzcma0));
        zzbvw zzbvw0 = this.zzgbv.zza(zzbnv0, zzbvz0);
        zzcma0.zza(zzbvw0.zzadm());
        zzcqm zzcqm0 = zzbvw0.zzadp();
        ((zzcni)zzcmd0.zzgbd).zza(zzcqm0);
        return zzbvw0.zzaex();
    }
}

