package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class zzawr extends BroadcastReceiver {
    private final zzawo zzduc;

    private zzawr(zzawo zzawo0) {
        this.zzduc = zzawo0;
        super();
    }

    zzawr(zzawo zzawo0, zzawn zzawn0) {
        this(zzawo0);
    }

    @Override  // android.content.BroadcastReceiver
    public final void onReceive(Context context0, Intent intent0) {
        if("android.intent.action.USER_PRESENT".equals(intent0.getAction())) {
            this.zzduc.zzyc = true;
            return;
        }
        if("android.intent.action.SCREEN_OFF".equals(intent0.getAction())) {
            this.zzduc.zzyc = false;
        }
    }
}

