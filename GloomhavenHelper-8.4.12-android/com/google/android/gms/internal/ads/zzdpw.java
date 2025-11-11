package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Set;

final class zzdpw implements zza {
    private final zzdpj zzhes;

    zzdpw(zzdpj zzdpj0) {
        this.zzhes = zzdpj0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdpu$zza
    public final Set zzavd() {
        return this.zzhes.zzavd();
    }

    @Override  // com.google.android.gms.internal.ads.zzdpu$zza
    public final zzdpe zzavm() {
        Class class0 = this.zzhes.zzave();
        return new zzdph(this.zzhes, class0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpu$zza
    public final Class zzavn() {
        return this.zzhes.getClass();
    }

    @Override  // com.google.android.gms.internal.ads.zzdpu$zza
    public final Class zzavo() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpu$zza
    public final zzdpe zzb(Class class0) throws GeneralSecurityException {
        try {
            return new zzdph(this.zzhes, class0);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new GeneralSecurityException("Primitive type not supported", illegalArgumentException0);
        }
    }
}

