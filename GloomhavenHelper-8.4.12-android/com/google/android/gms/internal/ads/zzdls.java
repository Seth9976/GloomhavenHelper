package com.google.android.gms.internal.ads;

import java.util.Iterator;

public abstract class zzdls {
    public zzdls zza(Iterator iterator0) {
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            this.zzaf(object0);
        }
        return this;
    }

    public abstract zzdls zzaf(Object arg1);

    public zzdls zze(Iterable iterable0) {
        for(Object object0: iterable0) {
            this.zzaf(object0);
        }
        return this;
    }
}

