package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;

public final class zza {
    private static final IntentFilter filter;
    private static long zzgv;
    private static float zzgw;

    static {
        zza.filter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        zza.zzgw = NaNf;
    }

    @TargetApi(20)
    public static int zzg(Context context0) {
        if(context0 != null && context0.getApplicationContext() != null) {
            Intent intent0 = context0.getApplicationContext().registerReceiver(null, zza.filter);
            int v = 0;
            int v1 = ((intent0 == null ? 0 : intent0.getIntExtra("plugged", 0)) & 7) == 0 ? 0 : 1;
            PowerManager powerManager0 = (PowerManager)context0.getSystemService("power");
            if(powerManager0 == null) {
                return -1;
            }
            if(powerManager0.isInteractive()) {
                v = 2;
            }
            return v | v1;
        }
        return -1;
    }

    public static float zzh(Context context0) {
        synchronized(zza.class) {
            if(SystemClock.elapsedRealtime() - zza.zzgv < 60000L && !Float.isNaN(zza.zzgw)) {
                return zza.zzgw;
            }
            Intent intent0 = context0.getApplicationContext().registerReceiver(null, zza.filter);
            if(intent0 != null) {
                zza.zzgw = ((float)intent0.getIntExtra("level", -1)) / ((float)intent0.getIntExtra("scale", -1));
            }
            zza.zzgv = SystemClock.elapsedRealtime();
            return zza.zzgw;
        }
    }
}

