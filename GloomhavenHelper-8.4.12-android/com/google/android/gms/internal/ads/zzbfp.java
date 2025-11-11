package com.google.android.gms.internal.ads;

import android.view.View.OnAttachStateChangeListener;
import android.view.View;

final class zzbfp implements View.OnAttachStateChangeListener {
    private final zzaub zzegb;
    private final zzbfo zzejc;

    zzbfp(zzbfo zzbfo0, zzaub zzaub0) {
        this.zzejc = zzbfo0;
        this.zzegb = zzaub0;
        super();
    }

    @Override  // android.view.View$OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view0) {
        this.zzejc.zza(view0, this.zzegb, 10);
    }

    @Override  // android.view.View$OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view0) {
    }
}

