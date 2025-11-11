package com.google.android.gms.internal.ads;

import java.util.HashMap;

final class zzbdd implements Runnable {
    private final String zzdvm;
    private final String zzeeh;
    private final zzbda zzeel;
    private final long zzeeo;

    zzbdd(zzbda zzbda0, String s, String s1, long v) {
        this.zzeel = zzbda0;
        this.zzdvm = s;
        this.zzeeh = s1;
        this.zzeeo = v;
        super();
    }

    @Override
    public final void run() {
        HashMap hashMap0 = new HashMap();
        hashMap0.put("event", "precacheComplete");
        hashMap0.put("src", this.zzdvm);
        hashMap0.put("cachedSrc", this.zzeeh);
        hashMap0.put("totalDuration", Long.toString(this.zzeeo));
        this.zzeel.zza("onPrecacheEvent", hashMap0);
    }
}

