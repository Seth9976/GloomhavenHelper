package com.google.android.gms.internal.measurement;

import java.util.Map.Entry;

final class zzfq implements Map.Entry {
    private Map.Entry zza;

    private zzfq(Map.Entry map$Entry0) {
        this.zza = map$Entry0;
    }

    zzfq(Map.Entry map$Entry0, zzfr zzfr0) {
        this(map$Entry0);
    }

    @Override
    public final Object getKey() {
        return this.zza.getKey();
    }

    @Override
    public final Object getValue() {
        return ((zzfo)this.zza.getValue()) == null ? null : zzfo.zza();
    }

    @Override
    public final Object setValue(Object object0) {
        if(!(object0 instanceof zzgn)) {
            throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
        }
        return ((zzfo)this.zza.getValue()).zza(((zzgn)object0));
    }

    public final zzfo zza() {
        return (zzfo)this.zza.getValue();
    }
}

