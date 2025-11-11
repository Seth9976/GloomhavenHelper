package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Set;

final class zzdpz implements zza {
    private final zzdpv zzhev;
    private final zzdpj zzhew;

    zzdpz(zzdpv zzdpv0, zzdpj zzdpj0) {
        this.zzhev = zzdpv0;
        this.zzhew = zzdpj0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdpu$zza
    public final Set zzavd() {
        return this.zzhev.zzavd();
    }

    @Override  // com.google.android.gms.internal.ads.zzdpu$zza
    public final zzdpe zzavm() {
        return new zzdps(this.zzhev, this.zzhew, this.zzhev.zzave());
    }

    @Override  // com.google.android.gms.internal.ads.zzdpu$zza
    public final Class zzavn() {
        return this.zzhev.getClass();
    }

    @Override  // com.google.android.gms.internal.ads.zzdpu$zza
    public final Class zzavo() {
        return this.zzhew.getClass();
    }

    @Override  // com.google.android.gms.internal.ads.zzdpu$zza
    public final zzdpe zzb(Class class0) throws GeneralSecurityException {
        try {
            return new zzdps(this.zzhev, this.zzhew, class0);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new GeneralSecurityException("Primitive type not supported", illegalArgumentException0);
        }
    }
}

