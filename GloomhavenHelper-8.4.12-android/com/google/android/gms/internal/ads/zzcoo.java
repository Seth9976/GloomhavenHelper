package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzcoo implements zzcmf {
    private final zzbxo zzgcr;
    private zzamd zzgcv;
    private final Context zzur;

    public zzcoo(Context context0, zzbxo zzbxo0) {
        this.zzur = context0;
        this.zzgcr = zzbxo0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final void zza(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa {
        try {
            ((zzanq)zzcmd0.zzdel).zzdn(zzdei0.zzdfr);
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(this.zzur);
            zzcoq zzcoq0 = new zzcoq(this, zzcmd0, null);
            ((zzanq)zzcmd0.zzdel).zza(zzdei0.zzejm, zzdei0.zzgpt.toString(), zzdeq0.zzgql.zzfir.zzgqq, iObjectWrapper0, zzcoq0, ((zzalq)zzcmd0.zzgbd));
        }
        catch(RemoteException remoteException0) {
            throw new zzdfa(remoteException0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final Object zzb(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa, zzcpe {
        if(!zzdeq0.zzgql.zzfir.zzgqs.contains("6")) {
            throw new zzcpe("Unified must be used for RTB.", 1);
        }
        zzbyz zzbyz0 = zzbyz.zzb(this.zzgcv);
        String s = Integer.toString(zzbyz0.zzake());
        if(!zzdeq0.zzgql.zzfir.zzgqs.contains(s)) {
            throw new zzcpe("No corresponding native ad listener", 0);
        }
        zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, zzcmd0.zzfik);
        zzbzl zzbzl0 = new zzbzl(zzbyz0);
        zzcao zzcao0 = new zzcao(null, null, this.zzgcv);
        zzbza zzbza0 = this.zzgcr.zza(zzbnv0, zzbzl0, zzcao0);
        zzcqm zzcqm0 = zzbza0.zzadp();
        ((zzcni)zzcmd0.zzgbd).zza(zzcqm0);
        return zzbza0.zzadq();
    }
}

