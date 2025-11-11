package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class zzayw extends BroadcastReceiver {
    private final zzayt zzdwm;

    zzayw(zzayt zzayt0) {
        this.zzdwm = zzayt0;
        super();
    }

    @Override  // android.content.BroadcastReceiver
    public final void onReceive(Context context0, Intent intent0) {
        this.zzdwm.zzc(context0, intent0);
    }
}

