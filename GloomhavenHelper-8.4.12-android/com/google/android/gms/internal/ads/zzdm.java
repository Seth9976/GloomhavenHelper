package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;

final class zzdm implements Runnable {
    private final Activity val$activity;
    private final Context zzwj;
    private final String zzwk;
    private final View zzwl;

    zzdm(zzdi zzdi0, Context context0, String s, View view0, Activity activity0) {
        this.zzwj = context0;
        this.zzwk = s;
        this.zzwl = view0;
        this.val$activity = activity0;
        super();
    }

    @Override
    public final void run() {
        try {
            zzdi.zzby().zza(this.zzwj, this.zzwk, this.zzwl, this.val$activity);
        }
        catch(Exception exception0) {
            zzdi.zzbz().zza(2021, -1L, exception0);
        }
    }
}

