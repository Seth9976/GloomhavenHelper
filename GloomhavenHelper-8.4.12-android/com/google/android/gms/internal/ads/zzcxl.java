package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcxl implements zzcye {
    private final zzaui zzbnp;
    private final zzdoe zzfrv;
    private final Context zzur;

    public zzcxl(zzaui zzaui0, zzdoe zzdoe0, Context context0) {
        this.zzbnp = zzaui0;
        this.zzfrv = zzdoe0;
        this.zzur = context0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzcxo zzcxo0 = () -> {
            if(!this.zzbnp.zzad(this.zzur)) {
                return new zzcxm(null, null, null, null, null);
            }
            String s = this.zzbnp.zzag(this.zzur);
            if(s == null) {
                s = "";
            }
            String s1 = this.zzbnp.zzah(this.zzur);
            if(s1 == null) {
                s1 = "";
            }
            String s2 = this.zzbnp.zzai(this.zzur);
            if(s2 == null) {
                s2 = "";
            }
            String s3 = this.zzbnp.zzaj(this.zzur);
            if(s3 == null) {
                s3 = "";
            }
            return "TIME_OUT".equals(s1) ? new zzcxm(s, s1, s2, s3, ((Long)zzvh.zzpd().zzd(zzzx.zzcix))) : new zzcxm(s, s1, s2, s3, null);
        };
        return this.zzfrv.zzd(zzcxo0);
    }

    // 检测为 Lambda 实现
    final zzcxm zzapn() throws Exception [...]
}

