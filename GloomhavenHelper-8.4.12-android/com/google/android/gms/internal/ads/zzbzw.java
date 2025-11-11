package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.ViewGroup;

final class zzbzw implements zzacf {
    private final zzcal zzfqu;
    private final ViewGroup zzfqv;
    private final zzbzv zzfqw;

    zzbzw(zzbzv zzbzv0, zzcal zzcal0, ViewGroup viewGroup0) {
        this.zzfqw = zzbzv0;
        this.zzfqu = zzcal0;
        this.zzfqv = viewGroup0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzacf
    public final void zzc(MotionEvent motionEvent0) {
        this.zzfqu.onTouch(null, motionEvent0);
    }

    @Override  // com.google.android.gms.internal.ads.zzacf
    public final void zzrh() {
        if(zzbzv.zza(this.zzfqw, this.zzfqu, zzbzt.zzfqk)) {
            this.zzfqu.onClick(this.zzfqv);
        }
    }
}

