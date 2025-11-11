package com.google.android.gms.internal.ads;

final class zzaij extends zzbfx {
    private final zzaid zzczw;

    private zzaij(zzaid zzaid0) {
        this.zzczw = zzaid0;
        super();
    }

    zzaij(zzaid zzaid0, zzaih zzaih0) {
        this(zzaid0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbfx
    public final void zza(zzbga zzbga0) {
        if(zzaid.zza(this.zzczw) != null) {
            zzaid.zza(this.zzczw).zzsf();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbfx
    public final void zzb(zzbga zzbga0) {
        this.zzczw.zzg(zzbga0.uri);
    }

    @Override  // com.google.android.gms.internal.ads.zzbfx
    public final boolean zzc(zzbga zzbga0) {
        return this.zzczw.zzg(zzbga0.uri);
    }
}

