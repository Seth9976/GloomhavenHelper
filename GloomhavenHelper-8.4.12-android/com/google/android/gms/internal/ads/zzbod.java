package com.google.android.gms.internal.ads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public final class zzbod {
    private final Executor executor;
    private final zzciz zzfip;
    private final zzcjc zzfiq;
    private final zzdeu zzfir;
    private final zzdif zzfis;
    private final zzbjg zzfit;
    private final zzcph zzfiu;
    private final zzbsz zzfiv;
    @Nullable
    private final zzdeq zzfiw;
    private final zzcju zzfix;
    private final zzbpm zzfiy;

    zzbod(zzciz zzciz0, zzcjc zzcjc0, zzdeu zzdeu0, zzdif zzdif0, zzbjg zzbjg0, zzcph zzcph0, zzbsz zzbsz0, @Nullable zzdeq zzdeq0, zzcju zzcju0, zzbpm zzbpm0, Executor executor0) {
        this.zzfip = zzciz0;
        this.zzfiq = zzcjc0;
        this.zzfir = zzdeu0;
        this.zzfis = zzdif0;
        this.zzfit = zzbjg0;
        this.zzfiu = zzcph0;
        this.zzfiv = zzbsz0;
        this.zzfiw = zzdeq0;
        this.zzfix = zzcju0;
        this.zzfiy = zzbpm0;
        this.executor = executor0;
    }

    private final zzdof zza(zzdof zzdof0) {
        if(this.zzfiw != null) {
            return this.zzfis.zzu(zzdig.zzgvg).zze(zzdnt.zzaj(this.zzfiw)).zzata();
        }
        zzq.zzlb().zzmt();
        return this.zzfir.zzgqq.zzcda == null ? this.zzfis.zza(zzdig.zzgvg, zzdof0).zza(this.zzfip).zzata() : this.zzfis.zzu(zzdig.zzgvg).zze(this.zzfiq.zzanr()).zzata();
    }

    public final zzdof zza(@NonNull zzaqx zzaqx0) {
        return this.zza(zzdnt.zzaj(zzaqx0));
    }

    public final zzdof zza(zzdgg zzdgg0) {
        zzdof zzdof0 = this.zzfiy.zzaht();
        zzdof zzdof1 = this.zzfis.zza(zzdig.zzgvy, zzdof0).zza((zzaqx zzaqx0) -> {
            zzaqx0.zzdnc = zzdgg0;
            return this.zzfix.zzj(zzaqx0);
        }).zzata();
        zzdnt.zza(zzdof1, new zzbof(this), this.executor);
        return zzdof1;
    }

    // 检测为 Lambda 实现
    final zzdof zza(zzdgg zzdgg0, zzaqx zzaqx0) throws Exception [...]

    public final zzdof zzahp() {
        return this.zza(this.zzfiy.zzaht());
    }

    public final zzdof zzahq() {
        return this.zzb(this.zzahp());
    }

    public final zzbsz zzahr() {
        return this.zzfiv;
    }

    public final zzdof zzb(@NonNull zzaqx zzaqx0) {
        return this.zzb(this.zza(zzaqx0));
    }

    // 去混淆评级： 低(20)
    public final zzdof zzb(zzdof zzdof0) {
        return ((Boolean)zzvh.zzpd().zzd(zzzx.zzcpt)).booleanValue() ? this.zzfis.zza(zzdig.zzgvh, zzdof0).zza(this.zzfit).zza(this.zzfiu).zzata() : this.zzfis.zza(zzdig.zzgvh, zzdof0).zza(this.zzfit).zza(this.zzfiu).zza(((long)(((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcpu)))))), TimeUnit.SECONDS).zzata();
    }

    public final zzdof zzc(zzaqx zzaqx0) {
        zzdof zzdof0 = this.zzfix.zzk(zzaqx0);
        zzdof zzdof1 = this.zzfis.zza(zzdig.zzgvz, zzdof0).zzata();
        zzdnt.zza(zzdof1, new zzboe(this), this.executor);
        return zzdof1;
    }
}

