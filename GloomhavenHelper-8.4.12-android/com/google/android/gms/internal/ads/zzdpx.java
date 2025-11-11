package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Set;

final class zzdpx implements zza {
    private final zzdpe zzhet;

    zzdpx(zzdpe zzdpe0) {
        this.zzhet = zzdpe0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdpu$zza
    public final Set zzavd() {
        return Collections.singleton(this.zzhet.zzauy());
    }

    @Override  // com.google.android.gms.internal.ads.zzdpu$zza
    public final zzdpe zzavm() {
        return this.zzhet;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpu$zza
    public final Class zzavn() {
        return this.zzhet.getClass();
    }

    @Override  // com.google.android.gms.internal.ads.zzdpu$zza
    public final Class zzavo() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpu$zza
    public final zzdpe zzb(Class class0) throws GeneralSecurityException {
        if(!this.zzhet.zzauy().equals(class0)) {
            throw new InternalError("This should never be called, as we always first check supportedPrimitives.");
        }
        return this.zzhet;
    }
}

