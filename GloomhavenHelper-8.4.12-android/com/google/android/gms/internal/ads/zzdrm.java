package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

final class zzdrm {
    public static zzdwa zza(zzdtt zzdtt0) throws GeneralSecurityException {
        switch(zzdrp.zzhgd[zzdtt0.ordinal()]) {
            case 1: {
                return zzdwa.zzhmd;
            }
            case 2: {
                return zzdwa.zzhme;
            }
            case 3: {
                return zzdwa.zzhmf;
            }
            default: {
                throw new GeneralSecurityException("unknown curve type: " + zzdtt0);
            }
        }
    }

    public static zzdwd zza(zzdtf zzdtf0) throws GeneralSecurityException {
        switch(zzdrp.zzhge[zzdtf0.ordinal()]) {
            case 1: {
                return zzdwd.zzhmm;
            }
            case 2: {
                return zzdwd.zzhmo;
            }
            case 3: {
                return zzdwd.zzhmn;
            }
            default: {
                throw new GeneralSecurityException("unknown point format: " + zzdtf0);
            }
        }
    }

    public static String zza(zzdtv zzdtv0) throws NoSuchAlgorithmException {
        switch(zzdrp.zzhgc[zzdtv0.ordinal()]) {
            case 1: {
                return "HmacSha1";
            }
            case 2: {
                return "HmacSha256";
            }
            case 3: {
                return "HmacSha512";
            }
            default: {
                throw new NoSuchAlgorithmException("hash unsupported for HMAC: " + zzdtv0);
            }
        }
    }

    public static void zza(zzdtl zzdtl0) throws GeneralSecurityException {
        zzdvy.zza(zzdrm.zza(zzdtl0.zzaxg().zzaxt()));
        zzdrm.zza(zzdtl0.zzaxg().zzaxu());
        if(zzdtl0.zzaxi() == zzdtf.zzhhi) {
            throw new GeneralSecurityException("unknown EC point format");
        }
        zzdpu.zza(zzdtl0.zzaxh().zzaxb());
    }
}

