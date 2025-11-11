package com.google.android.gms.internal.ads;

import android.view.MotionEvent;

final class zzdp implements Runnable {
    private final MotionEvent zzwq;

    zzdp(zzdi zzdi0, MotionEvent motionEvent0) {
        this.zzwq = motionEvent0;
        super();
    }

    @Override
    public final void run() {
        try {
            zzdi.zzby().zza(this.zzwq);
        }
        catch(Exception exception0) {
            zzdi.zzbz().zza(2022, -1L, exception0);
        }
    }
}

