package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class zzdvk implements zzdoy {
    private final SecretKey zzhlb;
    private static final ThreadLocal zzhlf;

    static {
        zzdvk.zzhlf = new zzdvn();
    }

    public zzdvk(byte[] arr_b) throws GeneralSecurityException {
        zzdwv.zzez(arr_b.length);
        this.zzhlb = new SecretKeySpec(arr_b, "AES");
    }

    @Override  // com.google.android.gms.internal.ads.zzdoy
    public final byte[] zzc(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(arr_b.length > 0x7FFFFFE3) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] arr_b2 = new byte[arr_b.length + 28];
        byte[] arr_b3 = zzdwq.zzey(12);
        System.arraycopy(arr_b3, 0, arr_b2, 0, 12);
        AlgorithmParameterSpec algorithmParameterSpec0 = zzdvk.zzf(arr_b3, 0, arr_b3.length);
        ((Cipher)zzdvk.zzhlf.get()).init(1, this.zzhlb, algorithmParameterSpec0);
        if(arr_b1 != null && arr_b1.length != 0) {
            ((Cipher)zzdvk.zzhlf.get()).updateAAD(arr_b1);
        }
        int v = ((Cipher)zzdvk.zzhlf.get()).doFinal(arr_b, 0, arr_b.length, arr_b2, 12);
        if(v != arr_b.length + 16) {
            throw new GeneralSecurityException(String.format("encryption failed; GCM tag must be %s bytes, but got only %s bytes", 16, ((int)(v - arr_b.length))));
        }
        return arr_b2;
    }

    private static AlgorithmParameterSpec zzf(byte[] arr_b, int v, int v1) throws GeneralSecurityException {
        try {
            Class.forName("javax.crypto.spec.GCMParameterSpec");
            return new GCMParameterSpec(0x80, arr_b, 0, v1);
        }
        catch(ClassNotFoundException unused_ex) {
            if(!zzdws.zzbag()) {
                throw new GeneralSecurityException("cannot use AES-GCM: javax.crypto.spec.GCMParameterSpec not found");
            }
            return new IvParameterSpec(arr_b, 0, v1);
        }
    }
}

