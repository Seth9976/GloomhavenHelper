package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcxm implements zzcyb {
    private final String zzdql;
    private final String zzgjx;
    private final String zzgjy;
    private final String zzgjz;
    private final Long zzgka;

    public zzcxm(String s, String s1, String s2, String s3, Long long0) {
        this.zzdql = s;
        this.zzgjx = s1;
        this.zzgjy = s2;
        this.zzgjz = s3;
        this.zzgka = long0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        zzdez.zza(((Bundle)object0), "gmp_app_id", this.zzdql);
        zzdez.zza(((Bundle)object0), "fbs_aiid", this.zzgjx);
        zzdez.zza(((Bundle)object0), "fbs_aeid", this.zzgjy);
        zzdez.zza(((Bundle)object0), "apm_id_origin", this.zzgjz);
        Long long0 = this.zzgka;
        if(long0 != null) {
            ((Bundle)object0).putLong("sai_timeout", ((long)long0));
        }
    }
}

