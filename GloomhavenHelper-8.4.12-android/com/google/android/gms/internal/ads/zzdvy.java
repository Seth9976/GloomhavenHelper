package com.google.android.gms.internal.ads;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECField;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import javax.crypto.KeyAgreement;

public final class zzdvy {
    public static BigInteger zza(EllipticCurve ellipticCurve0) throws GeneralSecurityException {
        ECField eCField0 = ellipticCurve0.getField();
        if(!(eCField0 instanceof ECFieldFp)) {
            throw new GeneralSecurityException("Only curves over prime order fields are supported");
        }
        return ((ECFieldFp)eCField0).getP();
    }

    public static KeyPair zza(ECParameterSpec eCParameterSpec0) throws GeneralSecurityException {
        KeyPairGenerator keyPairGenerator0 = (KeyPairGenerator)zzdwf.zzhmw.zzhg("EC");
        keyPairGenerator0.initialize(eCParameterSpec0);
        return keyPairGenerator0.generateKeyPair();
    }

    public static ECPublicKey zza(zzdwa zzdwa0, byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        ECParameterSpec eCParameterSpec0 = zzdvy.zza(zzdwa0);
        ECPoint eCPoint0 = new ECPoint(new BigInteger(1, arr_b), new BigInteger(1, arr_b1));
        zzdvy.zza(eCPoint0, eCParameterSpec0.getCurve());
        ECPublicKeySpec eCPublicKeySpec0 = new ECPublicKeySpec(eCPoint0, eCParameterSpec0);
        return (ECPublicKey)((KeyFactory)zzdwf.zzhmx.zzhg("EC")).generatePublic(eCPublicKeySpec0);
    }

    public static ECParameterSpec zza(zzdwa zzdwa0) throws NoSuchAlgorithmException {
        switch(zzdwb.zzhmi[zzdwa0.ordinal()]) {
            case 1: {
                return zzdvy.zza("115792089210356248762697446949407573530086143415290314195533631308867097853951", "115792089210356248762697446949407573529996955224135760342422259061068512044369", "5ac635d8aa3a93e7b3ebbd55769886bc651d06b0cc53b0f63bce3c3e27d2604b", "6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296", "4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5");
            }
            case 2: {
                return zzdvy.zza("39402006196394479212279040100143613805079739270465446667948293404245721771496870329047266088258938001861606973112319", "39402006196394479212279040100143613805079739270465446667946905279627659399113263569398956308152294913554433653942643", "b3312fa7e23ee7e4988e056be3f82d19181d9c6efe8141120314088f5013875ac656398d8a2ed19d2a85c8edd3ec2aef", "aa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7", "3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f");
            }
            case 3: {
                return zzdvy.zza("6864797660130609714981900799081393217269435300143305409394463459185543183397656052122559640661454554977296311391480858037121987999716643812574028291115057151", "6864797660130609714981900799081393217269435300143305409394463459185543183397655394245057746333217197532963996371363321113864768612440380340372808892707005449", "051953eb9618e1c9a1f929a21a0b68540eea2da725b99b315f3b8b489918ef109e156193951ec7e937b1652c0bd3bb1bf073573df883d2c34f1ef451fd46b503f00", "c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66", "11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650");
            }
            default: {
                throw new NoSuchAlgorithmException("curve not implemented:" + zzdwa0);
            }
        }
    }

    private static ECParameterSpec zza(String s, String s1, String s2, String s3, String s4) {
        BigInteger bigInteger0 = new BigInteger(s);
        BigInteger bigInteger1 = new BigInteger(s1);
        BigInteger bigInteger2 = bigInteger0.subtract(new BigInteger("3"));
        BigInteger bigInteger3 = new BigInteger(s2, 16);
        BigInteger bigInteger4 = new BigInteger(s3, 16);
        BigInteger bigInteger5 = new BigInteger(s4, 16);
        return new ECParameterSpec(new EllipticCurve(new ECFieldFp(bigInteger0), bigInteger2, bigInteger3), new ECPoint(bigInteger4, bigInteger5), bigInteger1, 1);
    }

    static void zza(ECPoint eCPoint0, EllipticCurve ellipticCurve0) throws GeneralSecurityException {
        BigInteger bigInteger0 = zzdvy.zza(ellipticCurve0);
        BigInteger bigInteger1 = eCPoint0.getAffineX();
        BigInteger bigInteger2 = eCPoint0.getAffineY();
        if(bigInteger1 == null || bigInteger2 == null) {
            throw new GeneralSecurityException("point is at infinity");
        }
        if(bigInteger1.signum() == -1 || bigInteger1.compareTo(bigInteger0) >= 0) {
            throw new GeneralSecurityException("x is out of range");
        }
        if(bigInteger2.signum() == -1 || bigInteger2.compareTo(bigInteger0) >= 0) {
            throw new GeneralSecurityException("y is out of range");
        }
        if(!bigInteger2.multiply(bigInteger2).mod(bigInteger0).equals(bigInteger1.multiply(bigInteger1).add(ellipticCurve0.getA()).multiply(bigInteger1).add(ellipticCurve0.getB()).mod(bigInteger0))) {
            throw new GeneralSecurityException("Point is not on curve");
        }
    }

    public static byte[] zza(ECPrivateKey eCPrivateKey0, ECPoint eCPoint0) throws GeneralSecurityException {
        BigInteger bigInteger8;
        zzdvy.zza(eCPoint0, eCPrivateKey0.getParams().getCurve());
        ECPublicKeySpec eCPublicKeySpec0 = new ECPublicKeySpec(eCPoint0, eCPrivateKey0.getParams());
        PublicKey publicKey0 = ((KeyFactory)zzdwf.zzhmx.zzhg("EC")).generatePublic(eCPublicKeySpec0);
        KeyAgreement keyAgreement0 = (KeyAgreement)zzdwf.zzhmv.zzhg("ECDH");
        keyAgreement0.init(eCPrivateKey0);
        try {
            keyAgreement0.doPhase(publicKey0, true);
            byte[] arr_b = keyAgreement0.generateSecret();
            EllipticCurve ellipticCurve0 = eCPrivateKey0.getParams().getCurve();
            BigInteger bigInteger0 = new BigInteger(1, arr_b);
            if(bigInteger0.signum() == -1 || bigInteger0.compareTo(zzdvy.zza(ellipticCurve0)) >= 0) {
                throw new GeneralSecurityException("shared secret is out of range");
            }
            BigInteger bigInteger1 = zzdvy.zza(ellipticCurve0);
            BigInteger bigInteger2 = ellipticCurve0.getA();
            BigInteger bigInteger3 = ellipticCurve0.getB();
            BigInteger bigInteger4 = bigInteger0.multiply(bigInteger0).add(bigInteger2).multiply(bigInteger0).add(bigInteger3).mod(bigInteger1);
            if(bigInteger1.signum() != 1) {
                throw new InvalidAlgorithmParameterException("p must be positive");
            }
            BigInteger bigInteger5 = bigInteger4.mod(bigInteger1);
            BigInteger bigInteger6 = null;
            if(bigInteger5.equals(BigInteger.ZERO)) {
                bigInteger6 = BigInteger.ZERO;
            }
            else {
                if(bigInteger1.testBit(0) && bigInteger1.testBit(1)) {
                    bigInteger6 = bigInteger5.modPow(bigInteger1.add(BigInteger.ONE).shiftRight(2), bigInteger1);
                }
                else if(bigInteger1.testBit(0) && !bigInteger1.testBit(1)) {
                    bigInteger6 = BigInteger.ONE;
                    BigInteger bigInteger7 = bigInteger1.subtract(BigInteger.ONE).shiftRight(1);
                    int v = 0;
                    do {
                        bigInteger8 = bigInteger6.multiply(bigInteger6).subtract(bigInteger5).mod(bigInteger1);
                        if(bigInteger8.equals(BigInteger.ZERO)) {
                            goto label_54;
                        }
                        BigInteger bigInteger9 = bigInteger8.modPow(bigInteger7, bigInteger1);
                        if(bigInteger9.add(BigInteger.ONE).equals(bigInteger1)) {
                            goto label_37;
                        }
                        if(!bigInteger9.equals(BigInteger.ONE)) {
                            throw new InvalidAlgorithmParameterException("p is not prime");
                        }
                        bigInteger6 = bigInteger6.add(BigInteger.ONE);
                        ++v;
                    }
                    while(v != 0x80 || bigInteger1.isProbablePrime(80));
                    throw new InvalidAlgorithmParameterException("p is not prime");
                label_37:
                    BigInteger bigInteger10 = bigInteger1.add(BigInteger.ONE).shiftRight(1);
                    BigInteger bigInteger11 = BigInteger.ONE;
                    int v1 = bigInteger10.bitLength() - 2;
                    BigInteger bigInteger12 = bigInteger6;
                    while(v1 >= 0) {
                        BigInteger bigInteger13 = bigInteger12.multiply(bigInteger11);
                        bigInteger12 = bigInteger12.multiply(bigInteger12).add(bigInteger11.multiply(bigInteger11).mod(bigInteger1).multiply(bigInteger8)).mod(bigInteger1);
                        bigInteger11 = bigInteger13.add(bigInteger13).mod(bigInteger1);
                        if(bigInteger10.testBit(v1)) {
                            BigInteger bigInteger14 = bigInteger12.multiply(bigInteger6).add(bigInteger11.multiply(bigInteger8)).mod(bigInteger1);
                            bigInteger11 = bigInteger6.multiply(bigInteger11).add(bigInteger12).mod(bigInteger1);
                            bigInteger12 = bigInteger14;
                        }
                        --v1;
                    }
                    bigInteger6 = bigInteger12;
                }
                if(bigInteger6 != null && bigInteger6.multiply(bigInteger6).mod(bigInteger1).compareTo(bigInteger5) != 0) {
                    throw new GeneralSecurityException("Could not find a modular square root");
                }
            }
        label_54:
            if(!bigInteger6.testBit(0)) {
                bigInteger1.subtract(bigInteger6).mod(bigInteger1);
            }
            return arr_b;
        }
        catch(IllegalStateException illegalStateException0) {
            throw new GeneralSecurityException(illegalStateException0.toString());
        }
    }
}

