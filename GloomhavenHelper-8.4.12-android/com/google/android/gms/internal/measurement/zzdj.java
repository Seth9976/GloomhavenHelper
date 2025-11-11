package com.google.android.gms.internal.measurement;

final class zzdj extends zzdf {
    private final zzdi zza;

    zzdj() {
        this.zza = new zzdi();
    }

    @Override  // com.google.android.gms.internal.measurement.zzdf
    public final void zza(Throwable throwable0, Throwable throwable1) {
        if(throwable1 == throwable0) {
            throw new IllegalArgumentException("Self suppression is not allowed.", throwable1);
        }
        if(throwable1 == null) {
            throw new NullPointerException("The suppressed exception cannot be null.");
        }
        this.zza.zza(throwable0, true).add(throwable1);
    }
}

