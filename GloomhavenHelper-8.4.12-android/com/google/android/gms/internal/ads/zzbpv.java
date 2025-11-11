package com.google.android.gms.internal.ads;

import android.os.Bundle;
import androidx.annotation.Nullable;

public final class zzbpv implements zzeej {
    private final zzbpt zzfkc;

    private zzbpv(zzbpt zzbpt0) {
        this.zzfkc = zzbpt0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    @Nullable
    public final Object get() {
        return this.zzfkc.zzahx();
    }

    public static zzbpv zzi(zzbpt zzbpt0) {
        return new zzbpv(zzbpt0);
    }

    @Nullable
    public static Bundle zzj(zzbpt zzbpt0) {
        return zzbpt0.zzahx();
    }
}

