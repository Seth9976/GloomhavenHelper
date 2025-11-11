package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;

public final class zzaaf {
    public static boolean zza(@Nullable zzaak zzaak0, @Nullable zzaai zzaai0, String[] arr_s) {
        return zzaak0 == null || zzaai0 == null || !zzaak0.zzcsx || zzaai0 == null ? false : zzaak0.zza(zzaai0, zzq.zzlc().elapsedRealtime(), arr_s);
    }

    @Nullable
    public static zzaai zzb(@Nullable zzaak zzaak0) {
        return zzaak0 == null ? null : zzaak0.zzex(zzq.zzlc().elapsedRealtime());
    }
}

