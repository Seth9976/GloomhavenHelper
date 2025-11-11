package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

final class zzbaf extends zzbah implements ViewTreeObserver.OnGlobalLayoutListener {
    private final WeakReference zzdya;

    public zzbaf(View view0, ViewTreeObserver.OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener0) {
        super(view0);
        this.zzdya = new WeakReference(viewTreeObserver$OnGlobalLayoutListener0);
    }

    @Override  // android.view.ViewTreeObserver$OnGlobalLayoutListener
    public final void onGlobalLayout() {
        ViewTreeObserver.OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener0 = (ViewTreeObserver.OnGlobalLayoutListener)this.zzdya.get();
        if(viewTreeObserver$OnGlobalLayoutListener0 != null) {
            viewTreeObserver$OnGlobalLayoutListener0.onGlobalLayout();
            return;
        }
        this.detach();
    }

    @Override  // com.google.android.gms.internal.ads.zzbah
    protected final void zza(ViewTreeObserver viewTreeObserver0) {
        viewTreeObserver0.addOnGlobalLayoutListener(this);
    }

    @Override  // com.google.android.gms.internal.ads.zzbah
    protected final void zzb(ViewTreeObserver viewTreeObserver0) {
        viewTreeObserver0.removeOnGlobalLayoutListener(this);
    }
}

