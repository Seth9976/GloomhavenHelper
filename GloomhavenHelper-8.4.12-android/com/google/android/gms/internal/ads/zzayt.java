package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.concurrent.GuardedBy;

public final class zzayt {
    @GuardedBy("this")
    private final BroadcastReceiver zzdwf;
    @GuardedBy("this")
    private final Map zzdwg;
    private boolean zzdwh;
    private boolean zzyb;
    private Context zzyz;

    public zzayt() {
        this.zzyb = false;
        this.zzdwg = new WeakHashMap();
        this.zzdwf = new zzayw(this);
    }

    public final void initialize(Context context0) {
        synchronized(this) {
            if(this.zzyb) {
                return;
            }
            this.zzyz = context0.getApplicationContext();
            if(this.zzyz == null) {
                this.zzyz = context0;
            }
            zzzx.initialize(this.zzyz);
            this.zzdwh = ((Boolean)zzvh.zzpd().zzd(zzzx.zzcnc)).booleanValue();
            IntentFilter intentFilter0 = new IntentFilter();
            intentFilter0.addAction("android.intent.action.SCREEN_ON");
            intentFilter0.addAction("android.intent.action.SCREEN_OFF");
            intentFilter0.addAction("android.intent.action.USER_PRESENT");
            this.zzyz.registerReceiver(this.zzdwf, intentFilter0);
            this.zzyb = true;
        }
    }

    public final void zza(Context context0, BroadcastReceiver broadcastReceiver0) {
        synchronized(this) {
            if(this.zzdwh) {
                this.zzdwg.remove(broadcastReceiver0);
                return;
            }
            context0.unregisterReceiver(broadcastReceiver0);
        }
    }

    public final void zza(Context context0, BroadcastReceiver broadcastReceiver0, IntentFilter intentFilter0) {
        synchronized(this) {
            if(this.zzdwh) {
                this.zzdwg.put(broadcastReceiver0, intentFilter0);
                return;
            }
            context0.registerReceiver(broadcastReceiver0, intentFilter0);
        }
    }

    private final void zzc(Context context0, Intent intent0) {
        synchronized(this) {
            ArrayList arrayList0 = new ArrayList();
            for(Object object0: this.zzdwg.entrySet()) {
                Map.Entry map$Entry0 = (Map.Entry)object0;
                if(((IntentFilter)map$Entry0.getValue()).hasAction(intent0.getAction())) {
                    arrayList0.add(((BroadcastReceiver)map$Entry0.getKey()));
                }
            }
            int v1 = arrayList0.size();
            int v2 = 0;
            while(v2 < v1) {
                Object object1 = arrayList0.get(v2);
                ++v2;
                ((BroadcastReceiver)object1).onReceive(context0, intent0);
            }
        }
    }
}

