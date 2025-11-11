package com.google.android.gms.internal.ads;

import java.util.HashMap;

final class zzbdc implements Runnable {
    private final String zzdvm;
    private final String zzeeh;
    private final boolean zzeek;
    private final zzbda zzeel;
    private final long zzeen;
    private final long zzeeo;
    private final int zzeep;
    private final int zzeeq;

    zzbdc(zzbda zzbda0, String s, String s1, long v, long v1, boolean z, int v2, int v3) {
        this.zzeel = zzbda0;
        this.zzdvm = s;
        this.zzeeh = s1;
        this.zzeen = v;
        this.zzeeo = v1;
        this.zzeek = z;
        this.zzeep = v2;
        this.zzeeq = v3;
        super();
    }

    @Override
    public final void run() {
        HashMap hashMap0 = new HashMap();
        hashMap0.put("event", "precacheProgress");
        hashMap0.put("src", this.zzdvm);
        hashMap0.put("cachedSrc", this.zzeeh);
        hashMap0.put("bufferedDuration", Long.toString(this.zzeen));
        hashMap0.put("totalDuration", Long.toString(this.zzeeo));
        hashMap0.put("cacheReady", (this.zzeek ? "1" : "0"));
        hashMap0.put("playerCount", Integer.toString(this.zzeep));
        hashMap0.put("playerPreparedCount", Integer.toString(this.zzeeq));
        this.zzeel.zza("onPrecacheEvent", hashMap0);
    }
}

