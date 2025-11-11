package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

public final class zzddq implements zzcso {
    private final Executor zzfeo;
    private final zzdep zzfkb;
    @GuardedBy("this")
    private final zzdew zzgfq;
    private final zzbgk zzghf;
    private final Context zzgme;
    private final zzdco zzgmg;
    private final zzdct zzgot;
    @GuardedBy("this")
    private zzdof zzgou;

    public zzddq(Context context0, Executor executor0, zzbgk zzbgk0, zzdco zzdco0, zzdct zzdct0, zzdew zzdew0, zzdep zzdep0) {
        this.zzgme = context0;
        this.zzfeo = executor0;
        this.zzghf = zzbgk0;
        this.zzgmg = zzdco0;
        this.zzgot = zzdct0;
        this.zzgfq = zzdew0;
        this.zzfkb = zzdep0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcso
    public final boolean isLoading() {
        return this.zzgou != null && !this.zzgou.isDone();
    }

    @Override  // com.google.android.gms.internal.ads.zzcso
    public final boolean zza(zzuh zzuh0, String s, zzcsr zzcsr0, zzcsq zzcsq0) throws RemoteException {
        zzash zzash0 = new zzash(zzuh0, s);
        String s1 = zzcsr0 instanceof zzddn ? ((zzddn)zzcsr0).zzgor : null;
        if(zzash0.zzbri == null) {
            zzawf.zzey("Ad unit ID should not be null for rewarded video ad.");
            zzddp zzddp0 = () -> this.zzgot.onAdFailedToLoad(1);
            this.zzfeo.execute(zzddp0);
            return false;
        }
        if(this.zzgou != null && !this.zzgou.isDone()) {
            return false;
        }
        zzdfc.zze(this.zzgme, zzash0.zzdjt.zzccp);
        zzdeu zzdeu0 = this.zzgfq.zzgn(zzash0.zzbri).zzd(zzuk.zzoq()).zzg(zzash0.zzdjt).zzarb();
        zzddu zzddu0 = new zzddu(null);
        zzddu0.zzfir = zzdeu0;
        zzddu0.zzgor = s1;
        zzdcp zzdcp0 = new zzdcp(zzddu0);
        zzdds zzdds0 = new zzdds(this);
        this.zzgou = this.zzgmg.zza(zzdcp0, zzdds0);
        zzdnt.zza(this.zzgou, new zzddr(this, zzcsq0, zzddu0), this.zzfeo);
        return true;
    }

    final void zzaqq() {
        this.zzgfq.zzgqx.add("new_rewarded");
    }

    // 检测为 Lambda 实现
    final void zzaqr() [...]

    private final zzcdt zzd(zzdcn zzdcn0) {
        zzdct zzdct0 = zzdct.zza(this.zzgot);
        return this.zzghf.zzacr().zze(new zza().zzcc(this.zzgme).zza(((zzddu)zzdcn0).zzfir).zzft(((zzddu)zzdcn0).zzgor).zza(this.zzfkb).zzahz()).zze(new com.google.android.gms.internal.ads.zzbtl.zza().zza(zzdct0, this.zzfeo).zza(zzdct0, this.zzfeo).zza(zzdct0, this.zzfeo).zza(zzdct0, this.zzfeo).zza(zzdct0, this.zzfeo).zza(zzdct0, this.zzfeo).zza(zzdct0).zzais());
    }

    final zzcdt zze(zzdcn zzdcn0) {
        return this.zzd(zzdcn0);
    }
}

