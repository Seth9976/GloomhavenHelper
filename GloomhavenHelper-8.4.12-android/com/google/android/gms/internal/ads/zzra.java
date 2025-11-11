package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzra extends zzqp {
    private MessageDigest zzbqv;
    private final int zzbrc;
    private final int zzbrd;

    public zzra(int v) {
        this.zzbrc = v % 8 <= 0 ? v / 8 : v / 8 + 1;
        this.zzbrd = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzqp
    public final byte[] zzbw(String s) {
        synchronized(this.mLock) {
            this.zzbqv = this.zzml();
            if(this.zzbqv == null) {
                return new byte[0];
            }
            this.zzbqv.reset();
            this.zzbqv.update(s.getBytes(Charset.forName("UTF-8")));
            byte[] arr_b = this.zzbqv.digest();
            byte[] arr_b1 = new byte[(arr_b.length <= this.zzbrc ? arr_b.length : this.zzbrc)];
            System.arraycopy(arr_b, 0, arr_b1, 0, arr_b1.length);
            if(this.zzbrd % 8 > 0) {
                long v2 = 0L;
                for(int v1 = 0; v1 < arr_b1.length; ++v1) {
                    if(v1 > 0) {
                        v2 <<= 8;
                    }
                    v2 += (long)(arr_b1[v1] & 0xFF);
                }
                long v3 = v2 >>> 8 - this.zzbrd % 8;
                for(int v4 = this.zzbrc - 1; v4 >= 0; --v4) {
                    arr_b1[v4] = (byte)(((int)(0xFFL & v3)));
                    v3 >>>= 8;
                }
            }
            return arr_b1;
        }
    }
}

