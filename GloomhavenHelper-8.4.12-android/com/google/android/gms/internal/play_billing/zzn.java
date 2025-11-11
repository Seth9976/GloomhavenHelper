package com.google.android.gms.internal.play_billing;

final class zzn extends zzl {
    private final zzp zza;

    zzn(zzp zzp0, int v) {
        super(zzp0.size(), v);
        this.zza = zzp0;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzl
    protected final Object zza(int v) {
        return this.zza.get(v);
    }
}

