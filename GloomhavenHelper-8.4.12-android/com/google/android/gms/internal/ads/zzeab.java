package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

public final class zzeab extends LinkedHashMap {
    private boolean zzhnt;
    private static final zzeab zzhuy;

    static {
        zzeab zzeab0 = new zzeab();
        zzeab.zzhuy = zzeab0;
        zzeab0.zzhnt = false;
    }

    private zzeab() {
        this.zzhnt = true;
    }

    private zzeab(Map map0) {
        super(map0);
        this.zzhnt = true;
    }

    @Override
    public final void clear() {
        this.zzbeb();
        super.clear();
    }

    // 去混淆评级： 低(20)
    @Override
    public final Set entrySet() {
        return this.isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 instanceof Map) {
            if(this != ((Map)object0)) {
                if(this.size() != ((Map)object0).size()) {
                    return false;
                }
                for(Object object1: this.entrySet()) {
                    if(!((Map)object0).containsKey(((Map.Entry)object1).getKey())) {
                        return false;
                    }
                    Object object2 = ((Map.Entry)object1).getValue();
                    Object object3 = ((Map)object0).get(((Map.Entry)object1).getKey());
                    if(!(!(object2 instanceof byte[]) || !(object3 instanceof byte[]) ? object2.equals(object3) : Arrays.equals(((byte[])object2), ((byte[])object3)))) {
                        return false;
                    }
                    if(false) {
                        break;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public final int hashCode() {
        int v = 0;
        for(Object object0: this.entrySet()) {
            int v1 = zzeab.zzaq(((Map.Entry)object0).getKey());
            v += zzeab.zzaq(((Map.Entry)object0).getValue()) ^ v1;
        }
        return v;
    }

    public final boolean isMutable() {
        return this.zzhnt;
    }

    @Override
    public final Object put(Object object0, Object object1) {
        this.zzbeb();
        zzdzc.checkNotNull(object0);
        zzdzc.checkNotNull(object1);
        return super.put(object0, object1);
    }

    @Override
    public final void putAll(Map map0) {
        this.zzbeb();
        for(Object object0: map0.keySet()) {
            zzdzc.checkNotNull(object0);
            zzdzc.checkNotNull(map0.get(object0));
        }
        super.putAll(map0);
    }

    @Override
    public final Object remove(Object object0) {
        this.zzbeb();
        return super.remove(object0);
    }

    public final void zza(zzeab zzeab0) {
        this.zzbeb();
        if(!zzeab0.isEmpty()) {
            this.putAll(zzeab0);
        }
    }

    private static int zzaq(Object object0) {
        if(object0 instanceof byte[]) {
            return zzdzc.hashCode(((byte[])object0));
        }
        if(object0 instanceof zzdzb) {
            throw new UnsupportedOperationException();
        }
        return object0.hashCode();
    }

    public final void zzban() {
        this.zzhnt = false;
    }

    public static zzeab zzbdz() {
        return zzeab.zzhuy;
    }

    // 去混淆评级： 低(20)
    public final zzeab zzbea() {
        return this.isEmpty() ? new zzeab() : new zzeab(this);
    }

    private final void zzbeb() {
        if(!this.zzhnt) {
            throw new UnsupportedOperationException();
        }
    }
}

