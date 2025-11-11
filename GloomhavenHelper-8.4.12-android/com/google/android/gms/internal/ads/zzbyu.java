package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

public final class zzbyu {
    @Nullable
    private zzacm zzcfx;

    public zzbyu(zzbym zzbym0) {
        this.zzcfx = zzbym0;
    }

    public final void zza(@Nullable zzacm zzacm0) {
        synchronized(this) {
            this.zzcfx = zzacm0;
        }
    }

    @Nullable
    public final zzacm zzrv() {
        synchronized(this) {
        }
        return this.zzcfx;
    }
}

