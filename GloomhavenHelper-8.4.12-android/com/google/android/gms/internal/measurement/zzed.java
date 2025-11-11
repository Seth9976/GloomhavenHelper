package com.google.android.gms.internal.measurement;

final class zzed {
    private final zzek zza;
    private final byte[] zzb;

    private zzed(int v) {
        this.zzb = new byte[v];
        this.zza = zzek.zza(this.zzb);
    }

    zzed(int v, zzdu zzdu0) {
        this(v);
    }

    public final zzdv zza() {
        this.zza.zzb();
        return new zzef(this.zzb);
    }

    public final zzek zzb() {
        return this.zza;
    }
}

