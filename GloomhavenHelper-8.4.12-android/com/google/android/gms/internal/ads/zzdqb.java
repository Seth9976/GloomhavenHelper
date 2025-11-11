package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

final class zzdqb {
    private static final Charset UTF_8;

    static {
        zzdqb.UTF_8 = Charset.forName("UTF-8");
    }

    public static zzdun zzb(zzdum zzdum0) {
        zza zzdun$zza0 = zzdun.zzazc().zzer(zzdum0.zzayu());
        for(Object object0: zzdum0.zzayv()) {
            zzdun$zza0.zzb(((zzb)(((zzdyz)zzb.zzaze().zzhf("").zza(((com.google.android.gms.internal.ads.zzdum.zza)object0).zzavi()).zza(((com.google.android.gms.internal.ads.zzdum.zza)object0).zzavj()).zzes(((com.google.android.gms.internal.ads.zzdum.zza)object0).zzaza()).zzbcx()))));
        }
        return (zzdun)(((zzdyz)zzdun$zza0.zzbcx()));
    }

    public static void zzc(zzdum zzdum0) throws GeneralSecurityException {
        int v = zzdum0.zzayu();
        int v1 = 0;
        boolean z = false;
        boolean z1 = true;
        for(Object object0: zzdum0.zzayv()) {
            com.google.android.gms.internal.ads.zzdum.zza zzdum$zza0 = (com.google.android.gms.internal.ads.zzdum.zza)object0;
            if(zzdum$zza0.zzavi() == zzdug.zzhjk) {
                if(!zzdum$zza0.zzayy()) {
                    throw new GeneralSecurityException(String.format("key %d has no key data", zzdum$zza0.zzaza()));
                }
                if(zzdum$zza0.zzavj() == zzduy.zzhkp) {
                    throw new GeneralSecurityException(String.format("key %d has unknown prefix", zzdum$zza0.zzaza()));
                }
                if(zzdum$zza0.zzavi() == zzdug.zzhjj) {
                    throw new GeneralSecurityException(String.format("key %d has unknown status", zzdum$zza0.zzaza()));
                }
                if(zzdum$zza0.zzaza() == v) {
                    if(z) {
                        throw new GeneralSecurityException("keyset contains multiple primary keys");
                    }
                    z = true;
                }
                if(zzdum$zza0.zzayz().zzayj() != com.google.android.gms.internal.ads.zzduc.zzb.zzhjf) {
                    z1 = false;
                }
                ++v1;
            }
        }
        if(v1 == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        }
        if(!z && !z1) {
            throw new GeneralSecurityException("keyset doesn\'t contain a valid primary key");
        }
    }
}

