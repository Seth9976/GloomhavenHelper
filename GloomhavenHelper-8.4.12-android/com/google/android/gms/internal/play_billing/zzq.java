package com.google.android.gms.internal.play_billing;

final class zzq extends zzp {
    static final zzp zza;
    final transient Object[] zzb;

    static {
        zzq.zza = new zzq(new Object[0], 0);
    }

    zzq(Object[] arr_object, int v) {
        this.zzb = arr_object;
    }

    @Override
    public final Object get(int v) {
        zzj.zza(v, 0, "index");
        return this.zzb[v];
    }

    @Override
    public final int size() {
        return 0;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzp
    final int zza(Object[] arr_object, int v) {
        System.arraycopy(this.zzb, 0, arr_object, 0, 0);
        return 0;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzm
    final int zzb() {
        return 0;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzm
    final int zzc() {
        return 0;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzm
    final Object[] zze() {
        return this.zzb;
    }
}

