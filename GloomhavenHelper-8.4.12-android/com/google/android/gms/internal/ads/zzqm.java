package com.google.android.gms.internal.ads;

import android.view.View;

final class zzqm implements Runnable {
    private final zzqj zzbqi;
    private final View zzbqj;

    zzqm(zzqj zzqj0, View view0) {
        this.zzbqi = zzqj0;
        this.zzbqj = view0;
        super();
    }

    @Override
    public final void run() {
        this.zzbqi.zzi(this.zzbqj);
    }
}

