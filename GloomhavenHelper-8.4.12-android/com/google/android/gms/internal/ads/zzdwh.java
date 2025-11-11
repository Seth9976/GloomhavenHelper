package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.Provider;
import javax.crypto.Cipher;

public final class zzdwh implements zzdwe {
    @Override  // com.google.android.gms.internal.ads.zzdwe
    public final Object zza(String s, Provider provider0) throws GeneralSecurityException {
        return provider0 == null ? Cipher.getInstance(s) : Cipher.getInstance(s, provider0);
    }
}

