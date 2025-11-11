package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcum implements zzcye {
    private static final Object lock;
    private final String zzcei;
    private final String zzdjw;
    private final zzdeu zzfir;
    private final zzbop zzgii;
    private final zzdfj zzgij;

    static {
        zzcum.lock = new Object();
    }

    public zzcum(String s, String s1, zzbop zzbop0, zzdfj zzdfj0, zzdeu zzdeu0) {
        this.zzdjw = s;
        this.zzcei = s1;
        this.zzgii = zzbop0;
        this.zzgij = zzdfj0;
        this.zzfir = zzdeu0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        Bundle bundle0 = new Bundle();
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcpx)).booleanValue()) {
            this.zzgii.zzf(this.zzfir.zzgqq);
            bundle0.putAll(this.zzgij.zzare());
        }
        return zzdnt.zzaj((Bundle bundle1) -> {
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcpx)).booleanValue()) {
                bundle1.putBundle("quality_signals", bundle0);
            }
            else if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcpw)).booleanValue()) {
                synchronized(zzcum.lock) {
                    this.zzgii.zzf(this.zzfir.zzgqq);
                    bundle1.putBundle("quality_signals", this.zzgij.zzare());
                }
            }
            else {
                this.zzgii.zzf(this.zzfir.zzgqq);
                bundle1.putBundle("quality_signals", this.zzgij.zzare());
            }
            bundle1.putString("seq_num", this.zzdjw);
            bundle1.putString("session_id", this.zzcei);
        });
    }

    // 检测为 Lambda 实现
    final void zzb(Bundle bundle0, Bundle bundle1) [...]
}

