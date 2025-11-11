package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

abstract class zzbah {
    private final WeakReference zzdyb;

    public zzbah(View view0) {
        this.zzdyb = new WeakReference(view0);
    }

    public final void attach() {
        ViewTreeObserver viewTreeObserver0 = this.getViewTreeObserver();
        if(viewTreeObserver0 != null) {
            this.zza(viewTreeObserver0);
        }
    }

    public final void detach() {
        ViewTreeObserver viewTreeObserver0 = this.getViewTreeObserver();
        if(viewTreeObserver0 != null) {
            this.zzb(viewTreeObserver0);
        }
    }

    private final ViewTreeObserver getViewTreeObserver() {
        View view0 = (View)this.zzdyb.get();
        if(view0 == null) {
            return null;
        }
        ViewTreeObserver viewTreeObserver0 = view0.getViewTreeObserver();
        return viewTreeObserver0 == null || !viewTreeObserver0.isAlive() ? null : viewTreeObserver0;
    }

    protected abstract void zza(ViewTreeObserver arg1);

    protected abstract void zzb(ViewTreeObserver arg1);
}

