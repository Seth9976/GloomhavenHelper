package com.google.android.gms.internal.ads;

import android.view.MotionEvent;

final class zzdo implements Runnable {
    private final int zzwn;
    private final int zzwo;
    private final int zzwp;

    zzdo(zzdi zzdi0, int v, int v1, int v2) {
        this.zzwn = v;
        this.zzwo = v1;
        this.zzwp = v2;
        super();
    }

    @Override
    public final void run() {
        try {
            zzdi.zzby().zza(MotionEvent.obtain(0L, this.zzwn, 0, ((float)this.zzwo), ((float)this.zzwp), 0));
        }
        catch(Exception exception0) {
            zzdi.zzbz().zza(2022, -1L, exception0);
        }
    }
}

