package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

final class zzbai extends zzbah implements ViewTreeObserver.OnScrollChangedListener {
    private final WeakReference zzdya;

    public zzbai(View view0, ViewTreeObserver.OnScrollChangedListener viewTreeObserver$OnScrollChangedListener0) {
        super(view0);
        this.zzdya = new WeakReference(viewTreeObserver$OnScrollChangedListener0);
    }

    @Override  // android.view.ViewTreeObserver$OnScrollChangedListener
    public final void onScrollChanged() {
        ViewTreeObserver.OnScrollChangedListener viewTreeObserver$OnScrollChangedListener0 = (ViewTreeObserver.OnScrollChangedListener)this.zzdya.get();
        if(viewTreeObserver$OnScrollChangedListener0 != null) {
            viewTreeObserver$OnScrollChangedListener0.onScrollChanged();
            return;
        }
        this.detach();
    }

    @Override  // com.google.android.gms.internal.ads.zzbah
    protected final void zza(ViewTreeObserver viewTreeObserver0) {
        viewTreeObserver0.addOnScrollChangedListener(this);
    }

    @Override  // com.google.android.gms.internal.ads.zzbah
    protected final void zzb(ViewTreeObserver viewTreeObserver0) {
        viewTreeObserver0.removeOnScrollChangedListener(this);
    }
}

