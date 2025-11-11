package com.google.android.gms.internal.ads;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzdxb extends WeakReference {
    private final int zzhno;

    public zzdxb(Throwable throwable0, ReferenceQueue referenceQueue0) {
        super(throwable0, referenceQueue0);
        if(throwable0 == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.zzhno = System.identityHashCode(throwable0);
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 != null && object0.getClass() == this.getClass()) {
            return this == object0 ? true : this.zzhno == ((zzdxb)object0).zzhno && this.get() == ((zzdxb)object0).get();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return this.zzhno;
    }
}

