package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public final class zzfy {
    private final Map zzaar;

    public zzfy() {
        this.zzaar = new HashMap();
    }

    public final AtomicReference zzaw(String s) {
        synchronized(this) {
            if(!this.zzaar.containsKey(s)) {
                AtomicReference atomicReference0 = new AtomicReference();
                this.zzaar.put(s, atomicReference0);
            }
        }
        return (AtomicReference)this.zzaar.get(s);
    }
}

