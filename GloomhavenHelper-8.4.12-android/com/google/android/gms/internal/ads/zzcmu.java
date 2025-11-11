package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzcmu implements zzcmf {
    private View view;
    private final zzbmc zzgbj;
    private final Context zzur;

    public zzcmu(Context context0, zzbmc zzbmc0) {
        this.zzur = context0;
        this.zzgbj = zzbmc0;
    }

    static final zzxj zza(zzcmd zzcmd0) throws zzdfa {
        try {
            return ((zzanq)zzcmd0.zzdel).getVideoController();
        }
        catch(RemoteException remoteException0) {
            throw new zzdfa(remoteException0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final void zza(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa {
        try {
            ((zzanq)zzcmd0.zzdel).zzdn(zzdei0.zzdfr);
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(this.zzur);
            zzcmz zzcmz0 = new zzcmz(this, zzcmd0, null);
            ((zzanq)zzcmd0.zzdel).zza(zzdei0.zzejm, zzdei0.zzgpt.toString(), zzdeq0.zzgql.zzfir.zzgqq, iObjectWrapper0, zzcmz0, ((zzalq)zzcmd0.zzgbd), zzdeq0.zzgql.zzfir.zzblv);
        }
        catch(RemoteException remoteException0) {
            throw new zzdfa(remoteException0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcmf
    public final Object zzb(zzdeq zzdeq0, zzdei zzdei0, zzcmd zzcmd0) throws zzdfa, zzcpe {
        zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, zzcmd0.zzfik);
        zzbln zzbln0 = new zzbln(this.view, null, new zzcmx(zzcmd0), ((zzdeh)zzdei0.zzgps.get(0)));
        zzblj zzblj0 = this.zzgbj.zza(zzbnv0, zzbln0);
        zzblj0.zzaep().zzq(this.view);
        zzcqm zzcqm0 = zzblj0.zzadp();
        ((zzcni)zzcmd0.zzgbd).zza(zzcqm0);
        return zzblj0.zzaeo();
    }
}

