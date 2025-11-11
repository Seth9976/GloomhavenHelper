package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzdpk {
    private zzdum zzheb;

    private zzdpk(zzdum zzdum0) {
        this.zzheb = zzdum0;
    }

    @Override
    public final String toString() {
        return zzdqb.zzb(this.zzheb).toString();
    }

    static final zzdpk zza(zzdum zzdum0) throws GeneralSecurityException {
        if(zzdum0 == null || zzdum0.zzayw() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        return new zzdpk(zzdum0);
    }

    final zzdum zzavg() {
        return this.zzheb;
    }
}

