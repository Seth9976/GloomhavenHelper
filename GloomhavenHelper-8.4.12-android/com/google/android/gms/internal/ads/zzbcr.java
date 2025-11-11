package com.google.android.gms.internal.ads;

public final class zzbcr implements zzbdi {
    @Override  // com.google.android.gms.internal.ads.zzbdi
    public final zzbda zza(zzbbm zzbbm0, int v, String s, zzbbj zzbbj0) {
        if(v > 0) {
            if(0 < zzbbj0.zzear) {
                return new zzbdl(zzbbm0, zzbbj0);
            }
            return 0 < zzbbj0.zzeal ? new zzbdm(zzbbm0, zzbbj0) : new zzbdk(zzbbm0);
        }
        return new zzbdh(zzbbm0);
    }
}

