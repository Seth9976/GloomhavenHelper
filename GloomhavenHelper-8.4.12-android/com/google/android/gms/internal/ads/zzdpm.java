package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.concurrent.CopyOnWriteArrayList;

public final class zzdpm {
    private static final CopyOnWriteArrayList zzhec;

    static {
        zzdpm.zzhec = new CopyOnWriteArrayList();
    }

    public static zzdpn zzgx(String s) throws GeneralSecurityException {
        for(Object object0: zzdpm.zzhec) {
            zzdpn zzdpn0 = (zzdpn)object0;
            if(zzdpn0.zzgy(s)) {
                return zzdpn0;
            }
            if(false) {
                break;
            }
        }
        String s1 = String.valueOf(s);
        throw new GeneralSecurityException((s1.length() == 0 ? new String("No KMS client does support: ") : "No KMS client does support: " + s1));
    }
}

