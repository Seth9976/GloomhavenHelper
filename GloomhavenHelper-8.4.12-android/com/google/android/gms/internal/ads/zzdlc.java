package com.google.android.gms.internal.ads;

import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzdlc {
    static final class zza {
        private zza() {
        }

        zza(zzdle zzdle0) {
        }
    }

    private static final Logger logger;
    private static final zzdld zzgyu;

    static {
        zzdlc.logger = Logger.getLogger(zzdlc.class.getName());
        zzdlc.zzgyu = new zza(null);
    }

    // 去混淆评级： 低(30)
    static String emptyToNull(@NullableDecl String s) {
        return s == null || s.isEmpty() ? null : s;
    }
}

