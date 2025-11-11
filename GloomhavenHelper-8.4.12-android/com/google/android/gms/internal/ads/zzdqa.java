package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzdqa {
    public static final String zzhex;
    public static final String zzhey;
    private static final String zzhez;
    private static final String zzhfa;
    private static final String zzhfb;
    private static final String zzhfc;
    private static final String zzhfd;
    @Deprecated
    private static final zzdva zzhfe;
    @Deprecated
    private static final zzdva zzhff;
    @Deprecated
    private static final zzdva zzhfg;

    static {
        try {
            zzdqa.zzhex = "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
            zzdqa.zzhey = "type.googleapis.com/google.crypto.tink.AesGcmKey";
            zzdqa.zzhez = "type.googleapis.com/google.crypto.tink.AesEaxKey";
            zzdqa.zzhfa = "type.googleapis.com/google.crypto.tink.KmsAeadKey";
            zzdqa.zzhfb = "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
            zzdqa.zzhfc = "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
            zzdqa.zzhfd = "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key";
            zzdva zzdva0 = zzdva.zzazu();
            zzdqa.zzhfe = zzdva0;
            zzdqa.zzhff = zzdva0;
            zzdqa.zzhfg = zzdqa.zzhfe;
            zzdqa.zzavp();
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new ExceptionInInitializerError(generalSecurityException0);
        }
    }

    public static void zzavp() throws GeneralSecurityException {
        zzdrw.zzavp();
        zzdpu.zza(new zzdqe(), true);
        zzdpu.zza(new zzdqj(), true);
        zzdpu.zza(new zzdqo(), true);
        zzdpu.zza(new zzdqp(), true);
        zzdpu.zza(new zzdqu(), true);
        zzdpu.zza(new zzdqy(), true);
        zzdpu.zza(new zzdqz(), true);
        zzdpu.zza(new zzdqd());
    }
}

