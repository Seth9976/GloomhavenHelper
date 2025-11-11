package com.google.android.gms.internal.ads;

import java.util.Map;

public final class zzcgc implements zzdil {
    private zzsn zzfvb;
    private Map zzfvj;

    zzcgc(zzsn zzsn0, Map map0) {
        this.zzfvj = map0;
        this.zzfvb = zzsn0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zza(zzdig zzdig0, String s) {
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zza(zzdig zzdig0, String s, Throwable throwable0) {
        if(this.zzfvj.containsKey(zzdig0)) {
            this.zzfvb.zza(((zzcge)this.zzfvj.get(zzdig0)).zzfvo);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zzb(zzdig zzdig0, String s) {
        if(this.zzfvj.containsKey(zzdig0)) {
            this.zzfvb.zza(((zzcge)this.zzfvj.get(zzdig0)).zzfvm);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zzc(zzdig zzdig0, String s) {
        if(this.zzfvj.containsKey(zzdig0)) {
            this.zzfvb.zza(((zzcge)this.zzfvj.get(zzdig0)).zzfvn);
        }
    }
}

