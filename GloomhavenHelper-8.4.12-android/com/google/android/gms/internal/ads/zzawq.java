package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzawq implements Runnable {
    private final Context val$context;
    private final zzawo zzduc;

    zzawq(zzawo zzawo0, Context context0) {
        this.zzduc = zzawo0;
        this.val$context = context0;
        super();
    }

    @Override
    public final void run() {
        synchronized(this.zzduc.zzdty) {
            String s = zzawo.zzas(this.val$context);
            this.zzduc.zzbfa = s;
            this.zzduc.zzdty.notifyAll();
        }
    }
}

