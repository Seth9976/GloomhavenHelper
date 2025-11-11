package com.google.android.gms.internal.ads;

import android.webkit.JavascriptInterface;
import androidx.annotation.Nullable;

final class zzaig {
    private final zzain zzczv;

    private zzaig(zzain zzain0) {
        this.zzczv = zzain0;
    }

    zzaig(zzain zzain0, zzaih zzaih0) {
        this(zzain0);
    }

    @JavascriptInterface
    public final void notify(@Nullable String s) {
        this.zzczv.zzdd(s);
    }
}

