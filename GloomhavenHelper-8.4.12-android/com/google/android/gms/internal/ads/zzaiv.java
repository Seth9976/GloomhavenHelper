package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Predicate;

final class zzaiv implements Predicate {
    private final zzafz zzdag;

    zzaiv(zzafz zzafz0) {
        this.zzdag = zzafz0;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.common.util.Predicate
    public final boolean apply(Object object0) {
        return ((zzafz)object0) instanceof zzaiy && ((zzaiy)(((zzafz)object0))).zzdaj.equals(this.zzdag);
    }
}

