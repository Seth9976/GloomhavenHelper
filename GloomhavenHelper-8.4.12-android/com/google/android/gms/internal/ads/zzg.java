package com.google.android.gms.internal.ads;

public final class zzg implements zzad {
    private int zzr;
    private int zzs;
    private final int zzt;
    private final float zzu;

    public zzg() {
        this(2500, 1, 1.0f);
    }

    private zzg(int v, int v1, float f) {
        this.zzr = 2500;
        this.zzt = 1;
        this.zzu = 1.0f;
    }

    @Override  // com.google.android.gms.internal.ads.zzad
    public final void zza(zzae zzae0) throws zzae {
        ++this.zzs;
        this.zzr += (int)(((float)this.zzr) * this.zzu);
        if(this.zzs > this.zzt) {
            throw zzae0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzad
    public final int zzb() {
        return this.zzr;
    }

    @Override  // com.google.android.gms.internal.ads.zzad
    public final int zzc() {
        return this.zzs;
    }
}

