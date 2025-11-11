package com.google.android.gms.internal.ads;

import java.util.Map.Entry;

final class zzebn implements Comparable, Map.Entry {
    private Object value;
    private final zzebi zzhxd;
    private final Comparable zzhxh;

    zzebn(zzebi zzebi0, Comparable comparable0, Object object0) {
        this.zzhxd = zzebi0;
        super();
        this.zzhxh = comparable0;
        this.value = object0;
    }

    zzebn(zzebi zzebi0, Map.Entry map$Entry0) {
        this(zzebi0, ((Comparable)map$Entry0.getKey()), map$Entry0.getValue());
    }

    @Override
    public final int compareTo(Object object0) {
        return ((Comparable)this.getKey()).compareTo(((Comparable)((zzebn)object0).getKey()));
    }

    private static boolean equals(Object object0, Object object1) {
        return object0 == null ? object1 == null : object0.equals(object1);
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof Map.Entry)) {
            return false;
        }
        Object object1 = ((Map.Entry)object0).getKey();
        return zzebn.equals(this.zzhxh, object1) && zzebn.equals(this.value, ((Map.Entry)object0).getValue());
    }

    @Override
    public final Object getKey() {
        return this.zzhxh;
    }

    @Override
    public final Object getValue() {
        return this.value;
    }

    @Override
    public final int hashCode() {
        int v = 0;
        int v1 = this.zzhxh == null ? 0 : this.zzhxh.hashCode();
        Object object0 = this.value;
        if(object0 != null) {
            v = object0.hashCode();
        }
        return v1 ^ v;
    }

    @Override
    public final Object setValue(Object object0) {
        this.zzhxd.zzbez();
        Object object1 = this.value;
        this.value = object0;
        return object1;
    }

    @Override
    public final String toString() {
        return this.zzhxh + "=" + this.value;
    }
}

