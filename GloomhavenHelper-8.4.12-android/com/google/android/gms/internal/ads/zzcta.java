package com.google.android.gms.internal.ads;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class zzcta {
    public static final class zza {
        private String zzghg;

        public final zza zzgm(String s) {
            this.zzghg = s;
            return this;
        }
    }

    private String zzghg;

    private zzcta(zza zzcta$zza0) {
        this.zzghg = zzcta$zza0.zzghg;
    }

    zzcta(zza zzcta$zza0, zzctc zzctc0) {
        this(zzcta$zza0);
    }

    public final Set zzaoy() {
        Set set0 = new HashSet();
        set0.add(this.zzghg.toLowerCase(Locale.ROOT));
        return set0;
    }

    public final String zzaoz() {
        return this.zzghg.toLowerCase(Locale.ROOT);
    }

    public final int zzapa() {
        switch(this.zzghg) {
            case "BANNER": {
                return 1;
            }
            case "INTERSTITIAL": {
                return 3;
            }
            case "NATIVE": {
                return 6;
            }
            case "REWARDED": {
                return 7;
            }
            default: {
                return 0;
            }
        }
    }
}

