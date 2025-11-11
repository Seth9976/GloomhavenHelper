package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class zzds {
    private final SecureRandom zzwx;
    private static Cipher zzwy;
    private static final Object zzwz;
    private static final Object zzxa;

    static {
        zzds.zzwz = new Object();
        zzds.zzxa = new Object();
    }

    public zzds(SecureRandom secureRandom0) {
        this.zzwx = null;
    }

    private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
        synchronized(zzds.zzxa) {
            if(zzds.zzwy == null) {
                zzds.zzwy = Cipher.getInstance("AES/CBC/PKCS5Padding");
            }
            return zzds.zzwy;
        }
    }

    public final byte[] zza(byte[] arr_b, String s) throws zzdv {
        if(arr_b.length == 16) {
            try {
                byte[] arr_b1 = zzci.zza(s, false);
                if(arr_b1.length <= 16) {
                    throw new zzdv(this);
                }
                ByteBuffer byteBuffer0 = ByteBuffer.allocate(arr_b1.length);
                byteBuffer0.put(arr_b1);
                byteBuffer0.flip();
                byte[] arr_b2 = new byte[16];
                byte[] arr_b3 = new byte[arr_b1.length - 16];
                byteBuffer0.get(arr_b2);
                byteBuffer0.get(arr_b3);
                SecretKeySpec secretKeySpec0 = new SecretKeySpec(arr_b, "AES");
                synchronized(zzds.zzwz) {
                    zzds.getCipher().init(2, secretKeySpec0, new IvParameterSpec(arr_b2));
                }
                return zzds.getCipher().doFinal(arr_b3);
            }
            catch(NoSuchAlgorithmException noSuchAlgorithmException0) {
                throw new zzdv(this, noSuchAlgorithmException0);
            }
            catch(InvalidKeyException invalidKeyException0) {
                throw new zzdv(this, invalidKeyException0);
            }
            catch(IllegalBlockSizeException illegalBlockSizeException0) {
                throw new zzdv(this, illegalBlockSizeException0);
            }
            catch(NoSuchPaddingException noSuchPaddingException0) {
                throw new zzdv(this, noSuchPaddingException0);
            }
            catch(BadPaddingException badPaddingException0) {
                throw new zzdv(this, badPaddingException0);
            }
            catch(InvalidAlgorithmParameterException invalidAlgorithmParameterException0) {
                throw new zzdv(this, invalidAlgorithmParameterException0);
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                throw new zzdv(this, illegalArgumentException0);
            }
        }
        throw new zzdv(this);
    }

    public final byte[] zzas(String s) throws zzdv {
        try {
            byte[] arr_b = zzci.zza(s, false);
            if(arr_b.length != 0x20) {
                throw new zzdv(this);
            }
            ByteBuffer byteBuffer0 = ByteBuffer.wrap(arr_b, 4, 16);
            byte[] arr_b1 = new byte[16];
            byteBuffer0.get(arr_b1);
            for(int v = 0; v < 16; ++v) {
                arr_b1[v] = (byte)(arr_b1[v] ^ 68);
            }
            return arr_b1;
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new zzdv(this, illegalArgumentException0);
        }
    }

    public final String zzb(byte[] arr_b, byte[] arr_b1) throws zzdv {
        byte[] arr_b3;
        byte[] arr_b2;
        if(arr_b.length == 16) {
            try {
                SecretKeySpec secretKeySpec0 = new SecretKeySpec(arr_b, "AES");
                synchronized(zzds.zzwz) {
                    zzds.getCipher().init(1, secretKeySpec0, null);
                    arr_b2 = zzds.getCipher().doFinal(arr_b1);
                    arr_b3 = zzds.getCipher().getIV();
                }
                int v1 = arr_b2.length + arr_b3.length;
                ByteBuffer byteBuffer0 = ByteBuffer.allocate(v1);
                byteBuffer0.put(arr_b3).put(arr_b2);
                byteBuffer0.flip();
                byte[] arr_b4 = new byte[v1];
                byteBuffer0.get(arr_b4);
                return zzci.zza(arr_b4, false);
            }
            catch(NoSuchAlgorithmException noSuchAlgorithmException0) {
                throw new zzdv(this, noSuchAlgorithmException0);
            }
            catch(InvalidKeyException invalidKeyException0) {
                throw new zzdv(this, invalidKeyException0);
            }
            catch(IllegalBlockSizeException illegalBlockSizeException0) {
                throw new zzdv(this, illegalBlockSizeException0);
            }
            catch(NoSuchPaddingException noSuchPaddingException0) {
                throw new zzdv(this, noSuchPaddingException0);
            }
            catch(BadPaddingException badPaddingException0) {
                throw new zzdv(this, badPaddingException0);
            }
        }
        throw new zzdv(this);
    }
}

