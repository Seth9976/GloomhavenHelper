package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class zzdat implements zzcso {
    private final Executor zzfeo;
    private final ViewGroup zzfgb;
    @GuardedBy("this")
    private final zzdew zzgfq;
    @Nullable
    @GuardedBy("this")
    private zzdof zzgga;
    private final zzbgk zzghf;
    private final Context zzgme;
    private final zzdbh zzgmf;
    private final zzdco zzgmg;

    public zzdat(Context context0, Executor executor0, zzbgk zzbgk0, zzdco zzdco0, zzdbh zzdbh0, zzdew zzdew0) {
        this.zzgme = context0;
        this.zzfeo = executor0;
        this.zzghf = zzbgk0;
        this.zzgmg = zzdco0;
        this.zzgmf = zzdbh0;
        this.zzgfq = zzdew0;
        this.zzfgb = new FrameLayout(context0);
    }

    @Override  // com.google.android.gms.internal.ads.zzcso
    public final boolean isLoading() {
        return this.zzgga != null && !this.zzgga.isDone();
    }

    private final zzbkx zza(zzdcn zzdcn0) {
        synchronized(this) {
            zzdbh zzdbh0 = zzdbh.zza(this.zzgmf);
            zza zzbtl$zza0 = new zza();
            zzbtl$zza0.zza(zzdbh0, this.zzfeo);
            zzbtl$zza0.zza(zzdbh0, this.zzfeo);
            zzbtl$zza0.zza(zzdbh0);
            return this.zzghf.zzacn().zza(new zzblf(this.zzfgb)).zzb(new com.google.android.gms.internal.ads.zzbpt.zza().zzcc(this.zzgme).zza(((zzdax)zzdcn0).zzfir).zzahz()).zzb(zzbtl$zza0.zzais());
        }
    }

    static zzdof zza(zzdat zzdat0, zzdof zzdof0) {
        zzdat0.zzgga = null;
        return null;
    }

    public final void zza(zzur zzur0) {
        this.zzgfq.zzb(zzur0);
    }

    @Override  // com.google.android.gms.internal.ads.zzcso
    public final boolean zza(zzuh zzuh0, String s, zzcsr zzcsr0, zzcsq zzcsq0) throws RemoteException {
        synchronized(this) {
            Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
            if(s == null) {
                zzawf.zzey("Ad unit ID should not be null for app open ad.");
                zzdaw zzdaw0 = () -> this.zzgmf.onAdFailedToLoad(1);
                this.zzfeo.execute(zzdaw0);
                return false;
            }
            if(this.zzgga != null) {
                return false;
            }
            zzdfc.zze(this.zzgme, zzuh0.zzccp);
            zzdeu zzdeu0 = this.zzgfq.zzgn(s).zzd(zzuk.zzor()).zzg(zzuh0).zzarb();
            zzdax zzdax0 = new zzdax(null);
            zzdax0.zzfir = zzdeu0;
            zzdcp zzdcp0 = new zzdcp(zzdax0);
            zzdav zzdav0 = new zzdav(this);
            this.zzgga = this.zzgmg.zza(zzdcp0, zzdav0);
            zzdnt.zza(this.zzgga, new zzday(this, zzcsq0, zzdax0), this.zzfeo);
            return true;
        }
    }

    // 检测为 Lambda 实现
    final void zzaqe() [...]

    final zzbkx zzb(zzdcn zzdcn0) {
        return this.zza(zzdcn0);
    }
}

