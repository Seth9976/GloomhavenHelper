package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;

public final class zzdwo implements zzdpp {
    private final Mac zzhnb;
    private final int zzhnc;
    private final String zzhnd;
    private final Key zzhne;

    public zzdwo(String s, Key key0, int v) throws GeneralSecurityException {
        if(v < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
        }
        if(key0.getEncoded().length < 16) {
            throw new InvalidAlgorithmParameterException("key size too small, need at least 16 bytes");
        }
        switch(s) {
            case "HMACSHA1": {
                if(v > 20) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                break;
            }
            case "HMACSHA256": {
                if(v > 0x20) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                break;
            }
            case "HMACSHA512": {
                if(v > 0x40) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                break;
            }
            default: {
                String s1 = String.valueOf(s);
                throw new NoSuchAlgorithmException((s1.length() == 0 ? new String("unknown Hmac algorithm: ") : "unknown Hmac algorithm: " + s1));
            }
        }
        this.zzhnd = s;
        this.zzhnc = v;
        this.zzhne = key0;
        this.zzhnb = (Mac)zzdwf.zzhms.zzhg(s);
        this.zzhnb.init(key0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdpp
    public final byte[] zzl(byte[] arr_b) throws GeneralSecurityException {
        Mac mac0;
        try {
            mac0 = (Mac)this.zzhnb.clone();
        }
        catch(CloneNotSupportedException unused_ex) {
            mac0 = (Mac)zzdwf.zzhms.zzhg(this.zzhnd);
            mac0.init(this.zzhne);
        }
        mac0.update(arr_b);
        byte[] arr_b1 = new byte[this.zzhnc];
        System.arraycopy(mac0.doFinal(), 0, arr_b1, 0, this.zzhnc);
        return arr_b1;
    }
}

