package com.google.android.gms.internal.ads;

import java.util.Map.Entry;

final class zzdzo implements Map.Entry {
    private Map.Entry zzhuj;

    private zzdzo(Map.Entry map$Entry0) {
        this.zzhuj = map$Entry0;
    }

    zzdzo(Map.Entry map$Entry0, zzdzl zzdzl0) {
        this(map$Entry0);
    }

    @Override
    public final Object getKey() {
        return this.zzhuj.getKey();
    }

    @Override
    public final Object getValue() {
        return ((zzdzm)this.zzhuj.getValue()) == null ? null : zzdzm.zzbds();
    }

    @Override
    public final Object setValue(Object object0) {
        if(!(object0 instanceof zzeah)) {
            throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
        }
        return ((zzdzm)this.zzhuj.getValue()).zzn(((zzeah)object0));
    }

    public final zzdzm zzbdt() {
        return (zzdzm)this.zzhuj.getValue();
    }
}

