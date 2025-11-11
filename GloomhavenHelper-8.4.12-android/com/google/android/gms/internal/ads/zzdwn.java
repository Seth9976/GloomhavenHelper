package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Signature;

public final class zzdwn implements zzdwe {
    @Override  // com.google.android.gms.internal.ads.zzdwe
    public final Object zza(String s, Provider provider0) throws GeneralSecurityException {
        return provider0 == null ? Signature.getInstance(s) : Signature.getInstance(s, provider0);
    }
}

