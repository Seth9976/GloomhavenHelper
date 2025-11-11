package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;

public final class zzbcv extends zzawb {
    final zzbbm zzdza;
    private final String zzdzm;
    private final String[] zzdzn;
    final zzbda zzeee;

    zzbcv(zzbbm zzbbm0, zzbda zzbda0, String s, String[] arr_s) {
        this.zzdza = zzbbm0;
        this.zzeee = zzbda0;
        this.zzdzm = s;
        this.zzdzn = arr_s;
        zzq.zzlr().zza(this);
    }

    @Override  // com.google.android.gms.internal.ads.zzawb
    public final void zztz() {
        try {
            this.zzeee.zze(this.zzdzm, this.zzdzn);
        }
        finally {
            zzbcy zzbcy0 = new zzbcy(this);
            zzawo.zzdtx.post(zzbcy0);
        }
    }
}

