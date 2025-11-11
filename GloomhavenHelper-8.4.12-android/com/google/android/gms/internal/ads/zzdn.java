package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;

final class zzdn implements Runnable {
    private final Context zzwj;
    private final View zzwl;
    private final Activity zzwm;

    zzdn(zzdi zzdi0, Context context0, View view0, Activity activity0) {
        this.zzwj = context0;
        this.zzwl = view0;
        this.zzwm = activity0;
        super();
    }

    @Override
    public final void run() {
        try {
            zzdi.zzby().zza(this.zzwj, this.zzwl, this.zzwm);
        }
        catch(Exception exception0) {
            zzdi.zzbz().zza(2020, -1L, exception0);
        }
    }
}

