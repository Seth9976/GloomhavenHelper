package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

public final class zzcrg implements zzeej {
    private final zzcrh zzgey;

    private zzcrg(zzcrh zzcrh0) {
        this.zzgey = zzcrh0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    @Nullable
    public final Object get() {
        return this.zzgey.zzaoi();
    }

    public static zzcrg zzc(zzcrh zzcrh0) {
        return new zzcrg(zzcrh0);
    }
}

