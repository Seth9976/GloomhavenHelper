package com.google.android.gms.internal.ads;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class zzdvw {
    private ECPublicKey zzhma;

    public zzdvw(ECPublicKey eCPublicKey0) {
        this.zzhma = eCPublicKey0;
    }

    public final zzdvz zza(String s, byte[] arr_b, byte[] arr_b1, int v, zzdwd zzdwd0) throws GeneralSecurityException {
        byte[] arr_b3;
        KeyPair keyPair0 = zzdvy.zza(this.zzhma.getParams());
        ECPublicKey eCPublicKey0 = (ECPublicKey)keyPair0.getPublic();
        ECPrivateKey eCPrivateKey0 = (ECPrivateKey)keyPair0.getPrivate();
        ECPublicKey eCPublicKey1 = this.zzhma;
        try {
            ECParameterSpec eCParameterSpec0 = eCPublicKey1.getParams();
            ECParameterSpec eCParameterSpec1 = eCPrivateKey0.getParams();
            if(!eCParameterSpec0.getCurve().equals(eCParameterSpec1.getCurve()) || !eCParameterSpec0.getGenerator().equals(eCParameterSpec1.getGenerator()) || !eCParameterSpec0.getOrder().equals(eCParameterSpec1.getOrder()) || eCParameterSpec0.getCofactor() != eCParameterSpec1.getCofactor()) {
                goto label_10;
            }
            else {
                goto label_8;
            }
            goto label_11;
        }
        catch(IllegalArgumentException | NullPointerException illegalArgumentException0) {
            throw new GeneralSecurityException(illegalArgumentException0.toString());
        }
    label_8:
        boolean z = true;
        goto label_11;
    label_10:
        z = false;
    label_11:
        if(z) {
            byte[] arr_b2 = zzdvy.zza(eCPrivateKey0, eCPublicKey1.getW());
            EllipticCurve ellipticCurve0 = eCPublicKey0.getParams().getCurve();
            ECPoint eCPoint0 = eCPublicKey0.getW();
            zzdvy.zza(eCPoint0, ellipticCurve0);
            int v2 = (zzdvy.zza(ellipticCurve0).subtract(BigInteger.ONE).bitLength() + 7) / 8;
            switch(zzdwb.zzhmh[zzdwd0.ordinal()]) {
                case 1: {
                    int v3 = v2 * 2 + 1;
                    arr_b3 = new byte[v3];
                    byte[] arr_b4 = eCPoint0.getAffineX().toByteArray();
                    byte[] arr_b5 = eCPoint0.getAffineY().toByteArray();
                    System.arraycopy(arr_b5, 0, arr_b3, v3 - arr_b5.length, arr_b5.length);
                    System.arraycopy(arr_b4, 0, arr_b3, v2 + 1 - arr_b4.length, arr_b4.length);
                    arr_b3[0] = 4;
                    break;
                }
                case 2: {
                    arr_b3 = new byte[v2 * 2];
                    byte[] arr_b6 = eCPoint0.getAffineX().toByteArray();
                    if(arr_b6.length > v2) {
                        arr_b6 = Arrays.copyOfRange(arr_b6, arr_b6.length - v2, arr_b6.length);
                    }
                    byte[] arr_b7 = eCPoint0.getAffineY().toByteArray();
                    if(arr_b7.length > v2) {
                        arr_b7 = Arrays.copyOfRange(arr_b7, arr_b7.length - v2, arr_b7.length);
                    }
                    System.arraycopy(arr_b7, 0, arr_b3, v2 * 2 - arr_b7.length, arr_b7.length);
                    System.arraycopy(arr_b6, 0, arr_b3, v2 - arr_b6.length, arr_b6.length);
                    break;
                }
                case 3: {
                    byte[] arr_b8 = new byte[v2 + 1];
                    byte[] arr_b9 = eCPoint0.getAffineX().toByteArray();
                    System.arraycopy(arr_b9, 0, arr_b8, v2 + 1 - arr_b9.length, arr_b9.length);
                    arr_b8[0] = (byte)(eCPoint0.getAffineY().testBit(0) ? 3 : 2);
                    arr_b3 = arr_b8;
                    break;
                }
                default: {
                    throw new GeneralSecurityException("invalid format:" + zzdwd0);
                }
            }
            byte[] arr_b10 = zzdvp.zza(new byte[][]{arr_b3, arr_b2});
            Mac mac0 = (Mac)zzdwf.zzhms.zzhg(s);
            if(v > mac0.getMacLength() * 0xFF) {
                throw new GeneralSecurityException("size too large");
            }
            if(arr_b == null || arr_b.length == 0) {
                mac0.init(new SecretKeySpec(new byte[mac0.getMacLength()], s));
            }
            else {
                mac0.init(new SecretKeySpec(arr_b, s));
            }
            byte[] arr_b11 = mac0.doFinal(arr_b10);
            byte[] arr_b12 = new byte[v];
            mac0.init(new SecretKeySpec(arr_b11, s));
            byte[] arr_b13 = new byte[0];
            int v4 = 0;
            for(int v1 = 1; true; ++v1) {
                mac0.update(arr_b13);
                mac0.update(arr_b1);
                mac0.update(((byte)v1));
                arr_b13 = mac0.doFinal();
                if(arr_b13.length + v4 >= v) {
                    break;
                }
                System.arraycopy(arr_b13, 0, arr_b12, v4, arr_b13.length);
                v4 += arr_b13.length;
            }
            System.arraycopy(arr_b13, 0, arr_b12, v4, v - v4);
            return new zzdvz(arr_b3, arr_b12);
        }
        try {
            throw new GeneralSecurityException("invalid public key spec");
        }
        catch(IllegalArgumentException | NullPointerException illegalArgumentException0) {
            throw new GeneralSecurityException(illegalArgumentException0.toString());
        }
    }
}

