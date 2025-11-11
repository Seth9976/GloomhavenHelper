package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

public final class zzdvu implements zzdpf {
    private static final byte[] zzhfn;
    private final zzdvw zzhlt;
    private final String zzhlu;
    private final byte[] zzhlv;
    private final zzdwd zzhlw;
    private final zzdvs zzhlx;

    static {
        zzdvu.zzhfn = new byte[0];
    }

    public zzdvu(ECPublicKey eCPublicKey0, byte[] arr_b, String s, zzdwd zzdwd0, zzdvs zzdvs0) throws GeneralSecurityException {
        zzdvy.zza(eCPublicKey0.getW(), eCPublicKey0.getParams().getCurve());
        this.zzhlt = new zzdvw(eCPublicKey0);
        this.zzhlv = arr_b;
        this.zzhlu = s;
        this.zzhlw = zzdwd0;
        this.zzhlx = zzdvs0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdpf
    public final byte[] zzc(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        int v = this.zzhlx.zzavq();
        zzdvz zzdvz0 = this.zzhlt.zza(this.zzhlu, this.zzhlv, arr_b1, v, this.zzhlw);
        byte[] arr_b2 = zzdvz0.zzbad();
        byte[] arr_b3 = this.zzhlx.zzm(arr_b2).zzc(arr_b, zzdvu.zzhfn);
        byte[] arr_b4 = zzdvz0.zzbac();
        return ByteBuffer.allocate(arr_b4.length + arr_b3.length).put(arr_b4).put(arr_b3).array();
    }
}

