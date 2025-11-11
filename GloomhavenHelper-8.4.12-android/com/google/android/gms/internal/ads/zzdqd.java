package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.logging.Logger;

public class zzdqd implements zzdpt {
    static final class zza implements zzdoy {
        private final zzdpr zzhfh;

        private zza(zzdpr zzdpr0) {
            this.zzhfh = zzdpr0;
        }

        zza(zzdpr zzdpr0, zzdqc zzdqc0) {
            this(zzdpr0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdoy
        public final byte[] zzc(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
            return zzdvp.zza(new byte[][]{this.zzhfh.zzavl().zzavk(), ((zzdoy)this.zzhfh.zzavl().zzavh()).zzc(arr_b, arr_b1)});
        }
    }

    private static final Logger logger;

    static {
        zzdqd.logger = Logger.getLogger(zzdqd.class.getName());
    }

    @Override  // com.google.android.gms.internal.ads.zzdpt
    public final Object zza(zzdpr zzdpr0) throws GeneralSecurityException {
        return new zza(zzdpr0, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpt
    public final Class zzauy() {
        return zzdoy.class;
    }
}

