package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzaac {
    public static void zza(zzaaa zzaaa0, @Nullable zzaab zzaab0) {
        if(zzaab0.getContext() == null) {
            throw new IllegalArgumentException("Context can\'t be null. Please set up context in CsiConfiguration.");
        }
        if(TextUtils.isEmpty(zzaab0.zzkp())) {
            throw new IllegalArgumentException("AfmaVersion can\'t be null or empty. Please set up afmaVersion in CsiConfiguration.");
        }
        zzaaa0.zza(zzaab0.getContext(), zzaab0.zzkp(), zzaab0.zzqq(), zzaab0.zzqr());
    }
}

