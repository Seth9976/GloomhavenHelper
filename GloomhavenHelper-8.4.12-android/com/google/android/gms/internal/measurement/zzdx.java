package com.google.android.gms.internal.measurement;

import java.util.Comparator;

final class zzdx implements Comparator {
    @Override
    public final int compare(Object object0, Object object1) {
        zzea zzea0 = (zzea)((zzdv)object0).iterator();
        zzea zzea1 = (zzea)((zzdv)object1).iterator();
        while(zzea0.hasNext() && zzea1.hasNext()) {
            int v = Integer.compare(zzdv.zza(zzea0.zza()), zzdv.zza(zzea1.zza()));
            if(v != 0) {
                return v;
            }
            if(false) {
                break;
            }
        }
        return Integer.compare(((zzdv)object0).zza(), ((zzdv)object1).zza());
    }
}

