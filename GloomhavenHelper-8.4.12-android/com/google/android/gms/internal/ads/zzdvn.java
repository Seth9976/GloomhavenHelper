package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;

final class zzdvn extends ThreadLocal {
    @Override
    protected final Object initialValue() {
        return zzdvn.zzbaa();
    }

    private static Cipher zzbaa() {
        try {
            return (Cipher)zzdwf.zzhmr.zzhg("AES/GCM/NoPadding");
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new IllegalStateException(generalSecurityException0);
        }
    }
}

