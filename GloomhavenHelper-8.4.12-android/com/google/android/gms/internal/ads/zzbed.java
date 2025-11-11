package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zza;

final class zzbed implements zzdng {
    private final Context zzcgz;
    private final zzdq zzdaq;
    private final zzazo zzegf;
    private final zza zzegg;
    private final String zzegh;

    zzbed(Context context0, zzdq zzdq0, zzazo zzazo0, zza zza0, String s) {
        this.zzcgz = context0;
        this.zzdaq = zzdq0;
        this.zzegf = zzazo0;
        this.zzegg = zza0;
        this.zzegh = s;
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) {
        zzbfl zzbfl0 = zzbfl.zzabv();
        zzsn zzsn0 = zzsn.zzmy();
        zzbdv zzbdv0 = zzbee.zza(this.zzcgz, zzbfl0, "", false, false, this.zzdaq, this.zzegf, null, null, this.zzegg, zzsn0, null, false);
        zzdof zzdof0 = zzazv.zzl(zzbdv0);
        zzbdv0.zzaaf().zza(new zzbef(((zzazv)zzdof0)));
        zzbdv0.loadUrl(this.zzegh);
        return zzdof0;
    }
}

