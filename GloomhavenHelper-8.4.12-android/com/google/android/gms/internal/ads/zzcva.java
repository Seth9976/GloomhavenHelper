package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public final class zzcva implements zzcye {
    private final zzdoe zzfrv;
    private final Context zzur;

    public zzcva(zzdoe zzdoe0, Context context0) {
        this.zzfrv = zzdoe0;
        this.zzur = context0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzcuz zzcuz0 = () -> {
            IntentFilter intentFilter0 = new IntentFilter("android.intent.action.BATTERY_CHANGED");
            Intent intent0 = this.zzur.registerReceiver(null, intentFilter0);
            if(intent0 != null) {
                int v = intent0.getIntExtra("status", -1);
                double f = ((double)intent0.getIntExtra("level", -1)) / ((double)intent0.getIntExtra("scale", -1));
                return v != 2 && v != 5 ? new zzcux(f, false) : new zzcux(f, true);
            }
            return new zzcux(-1.0, false);
        };
        return this.zzfrv.zzd(zzcuz0);
    }

    // 检测为 Lambda 实现
    final zzcux zzapf() throws Exception [...]
}

