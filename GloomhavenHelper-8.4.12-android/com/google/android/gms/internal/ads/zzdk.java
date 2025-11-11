package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzdk implements Runnable {
    private final Context zzwj;

    zzdk(zzdi zzdi0, Context context0) {
        this.zzwj = context0;
        super();
    }

    @Override
    public final void run() {
        try {
            zzdi.zzby().zzb(this.zzwj);
        }
        catch(Exception exception0) {
            zzdi.zzbz().zza(2019, -1L, exception0);
        }
    }
}

