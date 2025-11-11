package com.google.android.gms.internal.ads;

import android.view.View.OnAttachStateChangeListener;
import android.view.View;

final class zzbec implements View.OnAttachStateChangeListener {
    private final zzaub zzegb;
    private final zzbdy zzegd;

    zzbec(zzbdy zzbdy0, zzaub zzaub0) {
        this.zzegd = zzbdy0;
        this.zzegb = zzaub0;
        super();
    }

    @Override  // android.view.View$OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view0) {
        this.zzegd.zza(view0, this.zzegb, 10);
    }

    @Override  // android.view.View$OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view0) {
    }
}

