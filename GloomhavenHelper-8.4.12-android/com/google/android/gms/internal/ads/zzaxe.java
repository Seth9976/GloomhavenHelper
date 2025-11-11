package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.View;
import android.view.WindowInsets;

final class zzaxe implements View.OnApplyWindowInsetsListener {
    private final zzaxb zzdue;
    private final Activity zzduf;

    zzaxe(zzaxb zzaxb0, Activity activity0) {
        this.zzdue = zzaxb0;
        this.zzduf = activity0;
    }

    @Override  // android.view.View$OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view0, WindowInsets windowInsets0) {
        return zzaxb.zza(this.zzduf, view0, windowInsets0);
    }
}

