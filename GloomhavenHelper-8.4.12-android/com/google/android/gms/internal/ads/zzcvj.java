package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcvj implements zzcye {
    private final zzdoe zzfrv;
    private final Context zzyz;

    zzcvj(Context context0, zzdoe zzdoe0) {
        this.zzyz = context0;
        this.zzfrv = zzdoe0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzcvm zzcvm0 = () -> {
            String s = zzawo.zzbb(this.zzyz);
            return ((Boolean)zzvh.zzpd().zzd(zzzx.zzcqx)).booleanValue() ? new zzcvo(s, this.zzyz.getSharedPreferences("mobileads_consent", 0).getString("fc_consent", ""), zzawo.zzbc(this.zzyz), null) : new zzcvo(s, "", zzawo.zzbc(this.zzyz), null);
        };
        return this.zzfrv.zzd(zzcvm0);
    }

    // 检测为 Lambda 实现
    final zzcvo zzapg() throws Exception [...]
}

