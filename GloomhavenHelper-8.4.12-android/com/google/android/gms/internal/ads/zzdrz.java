package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.logging.Logger;

class zzdrz implements zzdpt {
    static final class zza implements zzdpp {
        private final zzdpr zzhfw;
        private final byte[] zzhgk;

        private zza(zzdpr zzdpr0) {
            this.zzhgk = new byte[]{0};
            this.zzhfw = zzdpr0;
        }

        zza(zzdpr zzdpr0, zzdry zzdry0) {
            this(zzdpr0);
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.android.gms.internal.ads.zzdpp
        public final byte[] zzl(byte[] arr_b) throws GeneralSecurityException {
            return this.zzhfw.zzavl().zzavj().equals(zzduy.zzhkr) ? zzdvp.zza(new byte[][]{this.zzhfw.zzavl().zzavk(), ((zzdpp)this.zzhfw.zzavl().zzavh()).zzl(zzdvp.zza(new byte[][]{arr_b, this.zzhgk}))}) : zzdvp.zza(new byte[][]{this.zzhfw.zzavl().zzavk(), ((zzdpp)this.zzhfw.zzavl().zzavh()).zzl(arr_b)});
        }
    }

    private static final Logger logger;

    static {
        zzdrz.logger = Logger.getLogger(zzdrz.class.getName());
    }

    @Override  // com.google.android.gms.internal.ads.zzdpt
    public final Object zza(zzdpr zzdpr0) throws GeneralSecurityException {
        return new zza(zzdpr0, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpt
    public final Class zzauy() {
        return zzdpp.class;
    }
}

