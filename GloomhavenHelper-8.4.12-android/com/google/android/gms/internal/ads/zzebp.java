package com.google.android.gms.internal.ads;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

class zzebp extends AbstractSet {
    private final zzebi zzhxd;

    private zzebp(zzebi zzebi0) {
        this.zzhxd = zzebi0;
        super();
    }

    zzebp(zzebi zzebi0, zzebh zzebh0) {
        this(zzebi0);
    }

    @Override
    public boolean add(Object object0) {
        if(!this.contains(((Map.Entry)object0))) {
            Comparable comparable0 = (Comparable)((Map.Entry)object0).getKey();
            Object object1 = ((Map.Entry)object0).getValue();
            this.zzhxd.zza(comparable0, object1);
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        this.zzhxd.clear();
    }

    @Override
    public boolean contains(Object object0) {
        Object object1 = ((Map.Entry)object0).getKey();
        Object object2 = this.zzhxd.get(object1);
        Object object3 = ((Map.Entry)object0).getValue();
        return object2 == object3 || object2 != null && object2.equals(object3);
    }

    @Override
    public Iterator iterator() {
        return new zzebq(this.zzhxd, null);
    }

    @Override
    public boolean remove(Object object0) {
        if(this.contains(((Map.Entry)object0))) {
            Object object1 = ((Map.Entry)object0).getKey();
            this.zzhxd.remove(object1);
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.zzhxd.size();
    }
}

