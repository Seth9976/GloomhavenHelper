package com.google.android.gms.internal.ads;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

final class zzdwy {
    private final ConcurrentHashMap zzhnk;
    private final ReferenceQueue zzhnl;

    zzdwy() {
        this.zzhnk = new ConcurrentHashMap(16, 0.75f, 10);
        this.zzhnl = new ReferenceQueue();
    }

    public final List zza(Throwable throwable0, boolean z) {
        for(Reference reference0 = this.zzhnl.poll(); reference0 != null; reference0 = this.zzhnl.poll()) {
            this.zzhnk.remove(reference0);
        }
        zzdxb zzdxb0 = new zzdxb(throwable0, null);
        List list0 = (List)this.zzhnk.get(zzdxb0);
        if(!z) {
            return list0;
        }
        if(list0 != null) {
            return list0;
        }
        List list1 = new Vector(2);
        zzdxb zzdxb1 = new zzdxb(throwable0, this.zzhnl);
        List list2 = (List)this.zzhnk.putIfAbsent(zzdxb1, list1);
        return list2 == null ? list1 : list2;
    }
}

