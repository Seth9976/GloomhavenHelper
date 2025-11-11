package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.zza;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
public class WakeLockTracker {
    private static WakeLockTracker zzgc;
    private static Boolean zzgd;
    @VisibleForTesting
    private static boolean zzge;

    static {
        WakeLockTracker.zzgc = new WakeLockTracker();
        WakeLockTracker.zzge = false;
    }

    @KeepForSdk
    public static WakeLockTracker getInstance() {
        return WakeLockTracker.zzgc;
    }

    @KeepForSdk
    public void registerAcquireEvent(Context context0, Intent intent0, String s, String s1, String s2, int v, String s3) {
        List list0 = Arrays.asList(new String[]{s3});
        this.registerEvent(context0, intent0.getStringExtra("WAKE_LOCK_KEY"), 7, s, s1, s2, v, list0);
    }

    @KeepForSdk
    public void registerDeadlineEvent(Context context0, String s, String s1, String s2, int v, List list0, boolean z, long v1) {
        if(!WakeLockTracker.zzw()) {
            return;
        }
        WakeLockTracker.zza(context0, new WakeLockEvent(System.currentTimeMillis(), 16, s, v, StatsUtils.zza(list0), null, v1, zza.zzg(context0), s1, "com.esotericsoftware.gloomhavenhelper", zza.zzh(context0), 0L, s2, z));
    }

    @KeepForSdk
    public void registerEvent(Context context0, String s, int v, String s1, String s2, String s3, int v1, List list0) {
        this.registerEvent(context0, s, v, s1, s2, s3, v1, list0, 0L);
    }

    @KeepForSdk
    public void registerEvent(Context context0, String s, int v, String s1, String s2, String s3, int v1, List list0, long v2) {
        if(!WakeLockTracker.zzw()) {
            return;
        }
        if(TextUtils.isEmpty(s)) {
            String s4 = String.valueOf(s);
            Log.e("WakeLockTracker", (s4.length() == 0 ? new String("missing wakeLock key. ") : "missing wakeLock key. " + s4));
            return;
        }
        if(7 == v || 8 == v || 10 == v || 11 == v) {
            WakeLockTracker.zza(context0, new WakeLockEvent(System.currentTimeMillis(), v, s1, v1, StatsUtils.zza(list0), s, SystemClock.elapsedRealtime(), zza.zzg(context0), s2, "com.esotericsoftware.gloomhavenhelper", zza.zzh(context0), v2, s3, false));
        }
    }

    @KeepForSdk
    public void registerReleaseEvent(Context context0, Intent intent0) {
        this.registerEvent(context0, intent0.getStringExtra("WAKE_LOCK_KEY"), 8, null, null, null, 0, null);
    }

    private static void zza(Context context0, WakeLockEvent wakeLockEvent0) {
        try {
            context0.startService(new Intent().setComponent(LoggingConstants.zzfg).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", wakeLockEvent0));
        }
        catch(Exception exception0) {
            Log.wtf("WakeLockTracker", exception0);
        }
    }

    private static boolean zzw() {
        if(WakeLockTracker.zzgd == null) {
            WakeLockTracker.zzgd = Boolean.FALSE;
        }
        return WakeLockTracker.zzgd.booleanValue();
    }
}

