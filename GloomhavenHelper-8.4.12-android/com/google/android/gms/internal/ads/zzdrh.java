package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzdrh {
    @Deprecated
    public static final zzdva zzhfe;
    @Deprecated
    private static final zzdva zzhff;
    @Deprecated
    private static final zzdva zzhfg;
    private static final String zzhfu;
    private static final String zzhfv;

    static {
        try {
            zzdrh.zzhfu = "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey";
            zzdrh.zzhfv = "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
            zzdrh.zzhfe = zzdva.zzazu();
            zzdrh.zzhff = zzdva.zzazu();
            zzdrh.zzhfg = zzdva.zzazu();
            zzdqa.zzavp();
            zzdpu.zza(new zzdre(), new zzdrf(), true);
            zzdpu.zza(new zzdrj());
            zzdpu.zza(new zzdrk());
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new ExceptionInInitializerError(generalSecurityException0);
        }
    }
}

