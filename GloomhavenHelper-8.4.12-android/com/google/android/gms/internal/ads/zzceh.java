package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View;

final class zzceh implements View.OnTouchListener {
    private final zzced zzfui;

    zzceh(zzced zzced0) {
        this.zzfui = zzced0;
    }

    @Override  // android.view.View$OnTouchListener
    public final boolean onTouch(View view0, MotionEvent motionEvent0) {
        return this.zzfui.zza(view0, motionEvent0);
    }
}

