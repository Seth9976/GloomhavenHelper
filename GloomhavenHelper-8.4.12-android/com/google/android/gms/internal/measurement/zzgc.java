package com.google.android.gms.internal.measurement;

final class zzgc implements zzgk {
    private zzgk[] zza;

    zzgc(zzgk[] arr_zzgk) {
        this.zza = arr_zzgk;
    }

    @Override  // com.google.android.gms.internal.measurement.zzgk
    public final boolean zza(Class class0) {
        zzgk[] arr_zzgk = this.zza;
        for(int v = 0; v < arr_zzgk.length; ++v) {
            if(arr_zzgk[v].zza(class0)) {
                return true;
            }
        }
        return false;
    }

    @Override  // com.google.android.gms.internal.measurement.zzgk
    public final zzgl zzb(Class class0) {
        zzgk[] arr_zzgk = this.zza;
        for(int v = 0; v < arr_zzgk.length; ++v) {
            zzgk zzgk0 = arr_zzgk[v];
            if(zzgk0.zza(class0)) {
                return zzgk0.zzb(class0);
            }
        }
        String s = class0.getName();
        throw new UnsupportedOperationException((s.length() == 0 ? new String("No factory is available for message type: ") : "No factory is available for message type: " + s));
    }
}

