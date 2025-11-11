package com.google.android.gms.internal.ads;

import java.util.HashMap;

final class zzbdb implements Runnable {
    private final String zzdvm;
    private final String zzeeh;
    private final int zzeei;
    private final int zzeej;
    private final boolean zzeek;
    private final zzbda zzeel;
    private final long zzeen;
    private final long zzeeo;
    private final int zzeep;
    private final int zzeeq;

    zzbdb(zzbda zzbda0, String s, String s1, int v, int v1, long v2, long v3, boolean z, int v4, int v5) {
        this.zzeel = zzbda0;
        this.zzdvm = s;
        this.zzeeh = s1;
        this.zzeei = v;
        this.zzeej = v1;
        this.zzeen = v2;
        this.zzeeo = v3;
        this.zzeek = z;
        this.zzeep = v4;
        this.zzeeq = v5;
        super();
    }

    @Override
    public final void run() {
        HashMap hashMap0 = new HashMap();
        hashMap0.put("event", "precacheProgress");
        hashMap0.put("src", this.zzdvm);
        hashMap0.put("cachedSrc", this.zzeeh);
        hashMap0.put("bytesLoaded", Integer.toString(this.zzeei));
        hashMap0.put("totalBytes", Integer.toString(this.zzeej));
        hashMap0.put("bufferedDuration", Long.toString(this.zzeen));
        hashMap0.put("totalDuration", Long.toString(this.zzeeo));
        hashMap0.put("cacheReady", (this.zzeek ? "1" : "0"));
        hashMap0.put("playerCount", Integer.toString(this.zzeep));
        hashMap0.put("playerPreparedCount", Integer.toString(this.zzeeq));
        this.zzeel.zza("onPrecacheEvent", hashMap0);
    }
}

