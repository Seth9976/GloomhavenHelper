package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

public final class zzgh extends LinkedHashMap {
    private boolean zza;
    private static final zzgh zzb;

    static {
        zzgh zzgh0 = new zzgh();
        zzgh.zzb = zzgh0;
        zzgh0.zza = false;
    }

    private zzgh() {
        this.zza = true;
    }

    private zzgh(Map map0) {
        super(map0);
        this.zza = true;
    }

    @Override
    public final void clear() {
        this.zze();
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
            int v1 = zzgh.zza(((Map.Entry)object0).getKey());
            v += zzgh.zza(((Map.Entry)object0).getValue()) ^ v1;
        }
        return v;
    }

    @Override
    public final Object put(Object object0, Object object1) {
        this.zze();
        zzfe.zza(object0);
        zzfe.zza(object1);
        return super.put(object0, object1);
    }

    @Override
    public final void putAll(Map map0) {
        this.zze();
        for(Object object0: map0.keySet()) {
            zzfe.zza(object0);
            zzfe.zza(map0.get(object0));
        }
        super.putAll(map0);
    }

    @Override
    public final Object remove(Object object0) {
        this.zze();
        return super.remove(object0);
    }

    private static int zza(Object object0) {
        if(object0 instanceof byte[]) {
            return zzfe.zzc(((byte[])object0));
        }
        if(object0 instanceof zzfh) {
            throw new UnsupportedOperationException();
        }
        return object0.hashCode();
    }

    public static zzgh zza() {
        return zzgh.zzb;
    }

    public final void zza(zzgh zzgh0) {
        this.zze();
        if(!zzgh0.isEmpty()) {
            this.putAll(zzgh0);
        }
    }

    // 去混淆评级： 低(20)
    public final zzgh zzb() {
        return this.isEmpty() ? new zzgh() : new zzgh(this);
    }

    public final void zzc() {
        this.zza = false;
    }

    public final boolean zzd() {
        return this.zza;
    }

    private final void zze() {
        if(!this.zza) {
            throw new UnsupportedOperationException();
        }
    }
}

