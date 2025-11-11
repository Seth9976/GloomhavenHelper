package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;

public final class zzdvv implements zzdpc {
    private static final byte[] zzhfn;
    private final String zzhlu;
    private final byte[] zzhlv;
    private final zzdwd zzhlw;
    private final zzdvs zzhlx;
    private final ECPrivateKey zzhly;
    private final zzdvx zzhlz;

    static {
        zzdvv.zzhfn = new byte[0];
    }

    public zzdvv(ECPrivateKey eCPrivateKey0, byte[] arr_b, String s, zzdwd zzdwd0, zzdvs zzdvs0) throws GeneralSecurityException {
        this.zzhly = eCPrivateKey0;
        this.zzhlz = new zzdvx(eCPrivateKey0);
        this.zzhlv = arr_b;
        this.zzhlu = s;
        this.zzhlw = zzdwd0;
        this.zzhlx = zzdvs0;
    }
}

