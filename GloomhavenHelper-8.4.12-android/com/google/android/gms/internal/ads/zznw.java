package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zznw {
    private final Map zzbfy;
    private Map zzbfz;

    public zznw() {
        this.zzbfy = new HashMap();
    }

    public final Map zziq() {
        synchronized(this) {
            if(this.zzbfz == null) {
                this.zzbfz = Collections.unmodifiableMap(new HashMap(this.zzbfy));
            }
            return this.zzbfz;
        }
    }
}

