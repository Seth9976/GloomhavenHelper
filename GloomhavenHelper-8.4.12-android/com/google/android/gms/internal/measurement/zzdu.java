package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

final class zzdu extends zzdw {
    private int zza;
    private final int zzb;
    private final zzdv zzc;

    zzdu(zzdv zzdv0) {
        this.zzc = zzdv0;
        super();
        this.zza = 0;
        this.zzb = this.zzc.zza();
    }

    @Override
    public final boolean hasNext() {
        return this.zza < this.zzb;
    }

    @Override  // com.google.android.gms.internal.measurement.zzea
    public final byte zza() {
        int v = this.zza;
        if(v >= this.zzb) {
            throw new NoSuchElementException();
        }
        this.zza = v + 1;
        return this.zzc.zzb(v);
    }
}

