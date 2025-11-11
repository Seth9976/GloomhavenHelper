package com.google.android.gms.internal.ads;

public final class zzcrj implements zzeej {
    private final zzcrh zzgey;

    private zzcrj(zzcrh zzcrh0) {
        this.zzgey = zzcrh0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzcrh zzcrh0 = this.zzgey;
        if(zzcrh0 == null) {
            throw null;
        }
        return (zzcrh)zzeep.zza(zzcrh0, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzcrj zzd(zzcrh zzcrh0) {
        return new zzcrj(zzcrh0);
    }
}

