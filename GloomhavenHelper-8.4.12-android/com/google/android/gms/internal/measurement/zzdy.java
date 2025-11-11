package com.google.android.gms.internal.measurement;

final class zzdy extends zzef {
    private final int zzc;
    private final int zzd;

    zzdy(byte[] arr_b, int v, int v1) {
        super(arr_b);
        zzdy.zzb(v, v + v1, arr_b.length);
        this.zzc = v;
        this.zzd = v1;
    }

    @Override  // com.google.android.gms.internal.measurement.zzef
    public final byte zza(int v) {
        int v1 = this.zza();
        if((v1 - (v + 1) | v) < 0) {
            throw v >= 0 ? new ArrayIndexOutOfBoundsException("Index > length: " + v + ", " + v1) : new ArrayIndexOutOfBoundsException("Index < 0: " + v);
        }
        return this.zzb[this.zzc + v];
    }

    @Override  // com.google.android.gms.internal.measurement.zzef
    public final int zza() {
        return this.zzd;
    }

    @Override  // com.google.android.gms.internal.measurement.zzef
    final byte zzb(int v) {
        return this.zzb[this.zzc + v];
    }

    @Override  // com.google.android.gms.internal.measurement.zzef
    protected final int zze() {
        return this.zzc;
    }
}

