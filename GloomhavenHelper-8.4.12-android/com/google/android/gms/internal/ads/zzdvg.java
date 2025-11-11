package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;

final class zzdvg extends ThreadLocal {
    @Override
    protected final Object initialValue() {
        return zzdvg.zzbaa();
    }

    private static Cipher zzbaa() {
        try {
            return (Cipher)zzdwf.zzhmr.zzhg("AES/CTR/NoPadding");
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new IllegalStateException(generalSecurityException0);
        }
    }
}

