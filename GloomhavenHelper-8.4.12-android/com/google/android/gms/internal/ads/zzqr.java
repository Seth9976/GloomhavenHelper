package com.google.android.gms.internal.ads;

import java.util.Comparator;

final class zzqr implements Comparator {
    zzqr(zzqs zzqs0) {
    }

    @Override
    public final int compare(Object object0, Object object1) {
        int v = ((zzqx)object0).zzbqp - ((zzqx)object1).zzbqp;
        return v == 0 ? ((int)(((zzqx)object0).value - ((zzqx)object1).value)) : v;
    }
}

