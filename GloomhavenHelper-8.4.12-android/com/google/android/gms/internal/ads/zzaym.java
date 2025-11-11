package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzaym {
    private Map zzdvx;
    private AtomicInteger zzdvy;

    public zzaym() {
        this.zzdvx = new ConcurrentHashMap();
        this.zzdvy = new AtomicInteger(0);
    }

    public final Bitmap zza(Integer integer0) {
        return (Bitmap)this.zzdvx.get(integer0);
    }
}

