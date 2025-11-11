package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.Provider;

public final class zzdwj implements zzdwe {
    @Override  // com.google.android.gms.internal.ads.zzdwe
    public final Object zza(String s, Provider provider0) throws GeneralSecurityException {
        return provider0 == null ? KeyFactory.getInstance(s) : KeyFactory.getInstance(s, provider0);
    }
}

