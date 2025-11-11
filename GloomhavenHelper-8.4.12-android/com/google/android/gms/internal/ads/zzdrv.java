package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;

final class zzdrv extends zzdpl {
    zzdrv(Class class0) {
        super(class0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpl
    public final Object zzak(Object object0) throws GeneralSecurityException {
        zzdtv zzdtv0 = ((zzdtx)object0).zzaxy().zzaye();
        SecretKeySpec secretKeySpec0 = new SecretKeySpec(((zzdtx)object0).zzavr().toByteArray(), "HMAC");
        int v = ((zzdtx)object0).zzaxy().zzavw();
        switch(zzdrx.zzhgc[zzdtv0.ordinal()]) {
            case 1: {
                return new zzdwo("HMACSHA1", secretKeySpec0, v);
            }
            case 2: {
                return new zzdwo("HMACSHA256", secretKeySpec0, v);
            }
            case 3: {
                return new zzdwo("HMACSHA512", secretKeySpec0, v);
            }
            default: {
                throw new GeneralSecurityException("unknown hash");
            }
        }
    }
}

