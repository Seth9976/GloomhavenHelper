package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zza;

public final class zzceb {
    private final zzazo zzblu;
    private final zzbee zzbms;
    private final zzsn zzefm;
    private final zzdq zzehb;
    private final zza zzehd;
    private final zzrp zzehf;
    private final zzdeu zzfir;
    private final zzbsf zzfuh;
    private final Context zzur;

    public zzceb(zzbee zzbee0, Context context0, zzdeu zzdeu0, zzdq zzdq0, zzazo zzazo0, zza zza0, zzsn zzsn0, zzbsf zzbsf0, zzbvt zzbvt0) {
        this.zzbms = zzbee0;
        this.zzur = context0;
        this.zzfir = zzdeu0;
        this.zzehb = zzdq0;
        this.zzblu = zzazo0;
        this.zzehd = zza0;
        this.zzefm = zzsn0;
        this.zzfuh = zzbsf0;
        this.zzehf = zzbvt0;
    }

    public final zzbdv zza(zzuk zzuk0, boolean z) throws zzbei {
        zzbfl zzbfl0 = zzbfl.zzb(zzuk0);
        zzcea zzcea0 = new zzcea(this);
        return zzbee.zza(this.zzur, zzbfl0, zzuk0.zzabk, false, false, this.zzehb, this.zzblu, null, zzcea0, this.zzehd, this.zzefm, this.zzehf, z);
    }

    public final zzbdv zzc(zzuk zzuk0) throws zzbei {
        return this.zza(zzuk0, false);
    }
}

