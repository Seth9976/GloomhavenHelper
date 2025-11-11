package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class zzdvh implements zzdwp {
    private static final ThreadLocal zzhlf;
    private final SecretKeySpec zzhlg;
    private final int zzhlh;
    private final int zzhli;

    static {
        zzdvh.zzhlf = new zzdvg();
    }

    public zzdvh(byte[] arr_b, int v) throws GeneralSecurityException {
        zzdwv.zzez(arr_b.length);
        this.zzhlg = new SecretKeySpec(arr_b, "AES");
        this.zzhli = ((Cipher)zzdvh.zzhlf.get()).getBlockSize();
        if(v < 12 || v > this.zzhli) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.zzhlh = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdwp
    public final byte[] zzn(byte[] arr_b) throws GeneralSecurityException {
        int v = this.zzhlh;
        if(arr_b.length > 0x7FFFFFFF - v) {
            throw new GeneralSecurityException("plaintext length can not exceed " + (0x7FFFFFFF - v));
        }
        byte[] arr_b1 = new byte[arr_b.length + v];
        byte[] arr_b2 = zzdwq.zzey(v);
        System.arraycopy(arr_b2, 0, arr_b1, 0, this.zzhlh);
        Cipher cipher0 = (Cipher)zzdvh.zzhlf.get();
        byte[] arr_b3 = new byte[this.zzhli];
        System.arraycopy(arr_b2, 0, arr_b3, 0, this.zzhlh);
        IvParameterSpec ivParameterSpec0 = new IvParameterSpec(arr_b3);
        cipher0.init(1, this.zzhlg, ivParameterSpec0);
        if(cipher0.doFinal(arr_b, 0, arr_b.length, arr_b1, this.zzhlh) != arr_b.length) {
            throw new GeneralSecurityException("stored output\'s length does not match input\'s length");
        }
        return arr_b1;
    }
}

