package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

final class zzbdj {
    private final ArrayList zzeev;
    private long zzeew;

    zzbdj() {
        this.zzeev = new ArrayList();
    }

    final void zza(zznr zznr0) {
        this.zzeev.add(zznr0);
    }

    final long zzzv() {
        Iterator iterator0 = this.zzeev.iterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            Map map0 = ((zznr)object0).getResponseHeaders();
            if(map0 != null) {
                for(Object object1: map0.entrySet()) {
                    Map.Entry map$Entry0 = (Map.Entry)object1;
                    try {
                        if(!"content-length".equalsIgnoreCase(((String)map$Entry0.getKey()))) {
                            continue;
                        }
                        long v = Long.parseLong(((String)((List)map$Entry0.getValue()).get(0)));
                        this.zzeew = Math.max(this.zzeew, v);
                    }
                    catch(RuntimeException unused_ex) {
                    }
                }
                iterator0.remove();
            }
        }
        return this.zzeew;
    }
}

