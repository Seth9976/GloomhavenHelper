package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Arrays;

final class zzdro implements zzdvs {
    private final String zzhfx;
    private final int zzhfy;
    private zzdsx zzhfz;
    private zzdsh zzhga;
    private int zzhgb;

    // 去混淆评级： 低(40)
    zzdro(zzdui zzdui0) throws GeneralSecurityException {
        this.zzhfx = "";
        throw new GeneralSecurityException(new String("unsupported AEAD DEM key type: "));
    }

    @Override  // com.google.android.gms.internal.ads.zzdvs
    public final int zzavq() {
        return this.zzhfy;
    }

    @Override  // com.google.android.gms.internal.ads.zzdvs
    public final zzdoy zzm(byte[] arr_b) throws GeneralSecurityException {
        if(arr_b.length != this.zzhfy) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        }
        if(this.zzhfx.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            zzdsx zzdsx0 = (zzdsx)(((zzdyz)((zza)zzdsx.zzawv().zza(this.zzhfz)).zzw(zzdxn.zzh(arr_b, 0, this.zzhfy)).zzbcx()));
            return (zzdoy)zzdpu.zza(this.zzhfx, zzdsx0, zzdoy.class);
        }
        if(!this.zzhfx.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            throw new GeneralSecurityException("unknown DEM key type");
        }
        byte[] arr_b1 = Arrays.copyOfRange(arr_b, 0, this.zzhgb);
        byte[] arr_b2 = Arrays.copyOfRange(arr_b, this.zzhgb, this.zzhfy);
        zzdsl zzdsl0 = (zzdsl)(((zzdyz)((com.google.android.gms.internal.ads.zzdsl.zza)zzdsl.zzawh().zza(this.zzhga.zzavz())).zzu(zzdxn.zzt(arr_b1)).zzbcx()));
        zzdtx zzdtx0 = (zzdtx)(((zzdyz)((com.google.android.gms.internal.ads.zzdtx.zza)zzdtx.zzaxz().zza(this.zzhga.zzawa())).zzad(zzdxn.zzt(arr_b2)).zzbcx()));
        zzdsh zzdsh0 = (zzdsh)(((zzdyz)zzdsh.zzawb().zzed(this.zzhga.getVersion()).zzb(zzdsl0).zzb(zzdtx0).zzbcx()));
        return (zzdoy)zzdpu.zza(this.zzhfx, zzdsh0, zzdoy.class);
    }
}

