package com.google.android.gms.internal.ads;

import java.util.Comparator;

final class zzdxp implements Comparator {
    @Override
    public final int compare(Object object0, Object object1) {
        zzdxw zzdxw0 = (zzdxw)((zzdxn)object0).iterator();
        zzdxw zzdxw1 = (zzdxw)((zzdxn)object1).iterator();
        while(zzdxw0.hasNext() && zzdxw1.hasNext()) {
            int v = Integer.compare(zzdxn.zzc(zzdxw0.nextByte()), zzdxn.zzc(zzdxw1.nextByte()));
            if(v != 0) {
                return v;
            }
            if(false) {
                break;
            }
        }
        return Integer.compare(((zzdxn)object0).size(), ((zzdxn)object1).size());
    }
}

