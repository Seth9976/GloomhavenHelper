package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public final class zzdvf implements zzdpp {
    private final SecretKey zzhlb;
    private final int zzhlc;
    private byte[] zzhld;
    private byte[] zzhle;

    public zzdvf(byte[] arr_b, int v) throws GeneralSecurityException {
        zzdwv.zzez(arr_b.length);
        if(v < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, min is 10 bytes");
        }
        if(v > 16) {
            throw new InvalidAlgorithmParameterException("tag size too large, max is 16 bytes");
        }
        this.zzhlb = new SecretKeySpec(arr_b, "AES");
        this.zzhlc = v;
        Cipher cipher0 = zzdvf.zzazz();
        cipher0.init(1, this.zzhlb);
        this.zzhld = zzdvm.zzp(cipher0.doFinal(new byte[16]));
        this.zzhle = zzdvm.zzp(this.zzhld);
    }

    private static Cipher zzazz() throws GeneralSecurityException {
        return (Cipher)zzdwf.zzhmr.zzhg("AES/ECB/NoPadding");
    }

    @Override  // com.google.android.gms.internal.ads.zzdpp
    public final byte[] zzl(byte[] arr_b) throws GeneralSecurityException {
        byte[] arr_b1;
        Cipher cipher0 = zzdvf.zzazz();
        cipher0.init(1, this.zzhlb);
        int v = Math.max(1, ((int)Math.ceil(((double)arr_b.length) / 16.0)));
        if(v << 4 == arr_b.length) {
            arr_b1 = zzdvp.zza(arr_b, v - 1 << 4, this.zzhld, 0, 16);
        }
        else {
            byte[] arr_b2 = Arrays.copyOfRange(arr_b, v - 1 << 4, arr_b.length);
            if(arr_b2.length >= 16) {
                throw new IllegalArgumentException("x must be smaller than a block.");
            }
            byte[] arr_b3 = Arrays.copyOf(arr_b2, 16);
            arr_b3[arr_b2.length] = (byte)0x80;
            arr_b1 = zzdvp.zzd(arr_b3, this.zzhle);
        }
        byte[] arr_b4 = new byte[16];
        for(int v1 = 0; v1 < v - 1; ++v1) {
            arr_b4 = cipher0.doFinal(zzdvp.zza(arr_b4, 0, arr_b, v1 << 4, 16));
        }
        byte[] arr_b5 = zzdvp.zzd(arr_b1, arr_b4);
        byte[] arr_b6 = new byte[this.zzhlc];
        System.arraycopy(cipher0.doFinal(arr_b5), 0, arr_b6, 0, this.zzhlc);
        return arr_b6;
    }
}

