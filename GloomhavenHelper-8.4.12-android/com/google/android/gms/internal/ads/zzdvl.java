package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;

final class zzdvl extends ThreadLocal {
    @Override
    protected final Object initialValue() {
        return zzdvl.zzbaa();
    }

    private static Cipher zzbaa() {
        try {
            return (Cipher)zzdwf.zzhmr.zzhg("AES/CTR/NOPADDING");
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new IllegalStateException(generalSecurityException0);
        }
    }
}

