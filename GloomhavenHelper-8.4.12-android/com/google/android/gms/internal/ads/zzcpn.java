package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzcpn implements zzcmf {
    private final zzcdq zzgdj;
    private final Context zzur;

    public zzcpn(Context context0, zzcdq zzcdq0) {
        this.zzur = context0;
        this.zzgdj = zzcdq0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final void zza(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa {
        try {
            ((zzanq)zzcmd0.zzdel).zzdn(zzdei0.zzdfr);
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(this.zzur);
            zzcpo zzcpo0 = new zzcpo(this, zzcmd0, null);
            ((zzanq)zzcmd0.zzdel).zza(zzdei0.zzejm, zzdei0.zzgpt.toString(), zzdeq0.zzgql.zzfir.zzgqq, iObjectWrapper0, zzcpo0, ((zzalq)zzcmd0.zzgbd));
        }
        catch(RemoteException remoteException0) {
            zzawf.zza("Remote exception loading a rewarded RTB ad", remoteException0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final Object zzb(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa, zzcpe {
        zzcma zzcma0 = new zzcma(zzdei0);
        zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, zzcmd0.zzfik);
        zzcdo zzcdo0 = new zzcdo(new zzcpm(zzcmd0, zzcma0));
        zzcdp zzcdp0 = this.zzgdj.zza(zzbnv0, zzcdo0);
        zzcma0.zza(zzcdp0.zzadm());
        zzcqn zzcqn0 = zzcdp0.zzafg();
        ((zzcni)zzcmd0.zzgbd).zza(zzcqn0);
        return zzcdp0.zzafd();
    }
}

