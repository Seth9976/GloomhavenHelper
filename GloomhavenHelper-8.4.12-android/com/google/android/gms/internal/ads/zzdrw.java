package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzdrw {
    @Deprecated
    private static final zzdva zzhfe;
    @Deprecated
    private static final zzdva zzhff;
    @Deprecated
    private static final zzdva zzhfg;
    private static final String zzhgf;

    static {
        try {
            zzdrw.zzhgf = "type.googleapis.com/google.crypto.tink.HmacKey";
            zzdva zzdva0 = zzdva.zzazu();
            zzdrw.zzhfe = zzdva0;
            zzdrw.zzhff = zzdva0;
            zzdrw.zzhfg = zzdrw.zzhfe;
            zzdrw.zzavp();
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new ExceptionInInitializerError(generalSecurityException0);
        }
    }

    public static void zzavp() throws GeneralSecurityException {
        zzdpu.zza(new zzdrs(), true);
        zzdpu.zza(new zzdrr(), true);
        zzdpu.zza(new zzdrz());
    }
}

