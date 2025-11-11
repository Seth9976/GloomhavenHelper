package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.Provider;

public final class zzdwk implements zzdwe {
    @Override  // com.google.android.gms.internal.ads.zzdwe
    public final Object zza(String s, Provider provider0) throws GeneralSecurityException {
        return provider0 == null ? MessageDigest.getInstance(s) : MessageDigest.getInstance(s, provider0);
    }
}

