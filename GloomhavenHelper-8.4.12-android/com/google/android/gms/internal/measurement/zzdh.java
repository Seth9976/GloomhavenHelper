package com.google.android.gms.internal.measurement;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzdh extends WeakReference {
    private final int zza;

    public zzdh(Throwable throwable0, ReferenceQueue referenceQueue0) {
        super(throwable0, referenceQueue0);
        if(throwable0 == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.zza = System.identityHashCode(throwable0);
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 != null && object0.getClass() == this.getClass()) {
            return this == object0 ? true : this.zza == ((zzdh)object0).zza && this.get() == ((zzdh)object0).get();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return this.zza;
    }
}

