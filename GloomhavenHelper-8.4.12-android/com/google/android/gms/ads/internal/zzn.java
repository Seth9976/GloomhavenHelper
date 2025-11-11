package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View;

final class zzn implements View.OnTouchListener {
    private final zzl zzblt;

    zzn(zzl zzl0) {
        this.zzblt = zzl0;
        super();
    }

    @Override  // android.view.View$OnTouchListener
    public final boolean onTouch(View view0, MotionEvent motionEvent0) {
        if(this.zzblt.zzbma != null) {
            this.zzblt.zzbma.zza(motionEvent0);
        }
        return false;
    }
}

