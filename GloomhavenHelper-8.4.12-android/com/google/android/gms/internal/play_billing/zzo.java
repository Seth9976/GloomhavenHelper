package com.google.android.gms.internal.play_billing;

import java.util.List;

final class zzo extends zzp {
    final transient int zza;
    final transient int zzb;
    final zzp zzc;

    zzo(zzp zzp0, int v, int v1) {
        this.zzc = zzp0;
        super();
        this.zza = v;
        this.zzb = v1;
    }

    @Override
    public final Object get(int v) {
        zzj.zza(v, this.zzb, "index");
        return this.zzc.get(v + this.zza);
    }

    @Override
    public final int size() {
        return this.zzb;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzp
    public final List subList(int v, int v1) {
        return this.zzf(v, v1);
    }

    @Override  // com.google.android.gms.internal.play_billing.zzm
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzm
    final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzm
    final Object[] zze() {
        return this.zzc.zze();
    }

    @Override  // com.google.android.gms.internal.play_billing.zzp
    public final zzp zzf(int v, int v1) {
        zzj.zzc(v, v1, this.zzb);
        return this.zzc.zzf(v + this.zza, v1 + this.zza);
    }
}

