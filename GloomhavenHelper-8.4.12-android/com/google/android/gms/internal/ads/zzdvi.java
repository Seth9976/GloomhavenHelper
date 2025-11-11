package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;

final class zzdvi extends ThreadLocal {
    @Override
    protected final Object initialValue() {
        return zzdvi.zzbaa();
    }

    private static Cipher zzbaa() {
        try {
            return (Cipher)zzdwf.zzhmr.zzhg("AES/ECB/NOPADDING");
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new IllegalStateException(generalSecurityException0);
        }
    }
}

