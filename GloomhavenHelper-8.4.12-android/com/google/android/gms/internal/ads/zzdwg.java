package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.Provider;
import javax.crypto.KeyAgreement;

public final class zzdwg implements zzdwe {
    @Override  // com.google.android.gms.internal.ads.zzdwe
    public final Object zza(String s, Provider provider0) throws GeneralSecurityException {
        return provider0 == null ? KeyAgreement.getInstance(s) : KeyAgreement.getInstance(s, provider0);
    }
}

