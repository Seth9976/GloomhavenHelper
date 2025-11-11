package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

@Deprecated
public final class zzdpo {
    @Deprecated
    public static final zzdpk zzk(byte[] arr_b) throws GeneralSecurityException {
        try {
            zzdum zzdum0 = zzdum.zzc(arr_b, zzdym.zzbcg());
            for(Object object0: zzdum0.zzayv()) {
                if(((zza)object0).zzayz().zzayj() == zzb.zzhjc || ((zza)object0).zzayz().zzayj() == zzb.zzhjd || ((zza)object0).zzayz().zzayj() == zzb.zzhje) {
                    throw new GeneralSecurityException("keyset contains secret key material");
                }
                if(false) {
                    break;
                }
            }
            return zzdpk.zza(zzdum0);
        }
        catch(zzdzh unused_ex) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }
}

