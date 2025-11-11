package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class zzdvj implements zzdoy {
    private final SecretKeySpec zzhlg;
    private static final ThreadLocal zzhlj;
    private static final ThreadLocal zzhlk;
    private final byte[] zzhll;
    private final byte[] zzhlm;
    private final int zzhln;

    static {
        zzdvj.zzhlj = new zzdvi();
        zzdvj.zzhlk = new zzdvl();
    }

    public zzdvj(byte[] arr_b, int v) throws GeneralSecurityException {
        if(v != 12 && v != 16) {
            throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
        }
        this.zzhln = v;
        zzdwv.zzez(arr_b.length);
        this.zzhlg = new SecretKeySpec(arr_b, "AES");
        Cipher cipher0 = (Cipher)zzdvj.zzhlj.get();
        cipher0.init(1, this.zzhlg);
        this.zzhll = zzdvj.zzo(cipher0.doFinal(new byte[16]));
        this.zzhlm = zzdvj.zzo(this.zzhll);
    }

    private final byte[] zza(Cipher cipher0, int v, byte[] arr_b, int v1, int v2) throws IllegalBlockSizeException, BadPaddingException {
        byte[] arr_b1 = new byte[16];
        arr_b1[15] = (byte)v;
        if(v2 == 0) {
            return cipher0.doFinal(zzdvj.zzd(arr_b1, this.zzhll));
        }
        byte[] arr_b2 = cipher0.doFinal(arr_b1);
        int v4;
        for(v4 = 0; v2 - v4 > 16; v4 += 16) {
            for(int v5 = 0; v5 < 16; ++v5) {
                arr_b2[v5] = (byte)(arr_b2[v5] ^ arr_b[v1 + v4 + v5]);
            }
            arr_b2 = cipher0.doFinal(arr_b2);
        }
        byte[] arr_b3 = Arrays.copyOfRange(arr_b, v4 + v1, v1 + v2);
        if(arr_b3.length == 16) {
            return cipher0.doFinal(zzdvj.zzd(arr_b2, zzdvj.zzd(arr_b3, this.zzhll)));
        }
        byte[] arr_b4 = Arrays.copyOf(this.zzhlm, 16);
        for(int v3 = 0; v3 < arr_b3.length; ++v3) {
            arr_b4[v3] = (byte)(arr_b4[v3] ^ arr_b3[v3]);
        }
        arr_b4[arr_b3.length] = (byte)(arr_b4[arr_b3.length] ^ 0x80);
        return cipher0.doFinal(zzdvj.zzd(arr_b2, arr_b4));
    }

    @Override  // com.google.android.gms.internal.ads.zzdoy
    public final byte[] zzc(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        int v = this.zzhln;
        if(arr_b.length > 0x7FFFFFEF - v) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] arr_b2 = new byte[arr_b.length + v + 16];
        byte[] arr_b3 = zzdwq.zzey(v);
        System.arraycopy(arr_b3, 0, arr_b2, 0, this.zzhln);
        Object object0 = zzdvj.zzhlj.get();
        ((Cipher)object0).init(1, this.zzhlg);
        byte[] arr_b4 = this.zza(((Cipher)object0), 0, arr_b3, 0, arr_b3.length);
        byte[] arr_b5 = arr_b1 == null ? new byte[0] : arr_b1;
        byte[] arr_b6 = this.zza(((Cipher)object0), 1, arr_b5, 0, arr_b5.length);
        Cipher cipher0 = (Cipher)zzdvj.zzhlk.get();
        IvParameterSpec ivParameterSpec0 = new IvParameterSpec(arr_b4);
        cipher0.init(1, this.zzhlg, ivParameterSpec0);
        cipher0.doFinal(arr_b, 0, arr_b.length, arr_b2, this.zzhln);
        byte[] arr_b7 = this.zza(((Cipher)object0), 2, arr_b2, this.zzhln, arr_b.length);
        int v1 = arr_b.length + this.zzhln;
        for(int v2 = 0; v2 < 16; ++v2) {
            arr_b2[v1 + v2] = (byte)(arr_b6[v2] ^ arr_b4[v2] ^ arr_b7[v2]);
        }
        return arr_b2;
    }

    private static byte[] zzd(byte[] arr_b, byte[] arr_b1) {
        byte[] arr_b2 = new byte[arr_b.length];
        for(int v = 0; v < arr_b.length; ++v) {
            arr_b2[v] = (byte)(arr_b[v] ^ arr_b1[v]);
        }
        return arr_b2;
    }

    private static byte[] zzo(byte[] arr_b) {
        byte[] arr_b1 = new byte[16];
        int v = 0;
        for(int v1 = 0; v1 < 15; ++v1) {
            arr_b1[v1] = (byte)(arr_b[v1] << 1 ^ (arr_b[v1 + 1] & 0xFF) >>> 7);
        }
        int v2 = arr_b[15] << 1;
        if((arr_b[0] & 0x80) != 0) {
            v = 0x87;
        }
        arr_b1[15] = (byte)(v2 ^ v);
        return arr_b1;
    }
}

