package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

public final class zzqw extends zzqp {
    private MessageDigest zzbqv;

    @Override  // com.google.android.gms.internal.ads.zzqp
    public final byte[] zzbw(String s) {
        byte[] arr_b;
        String[] arr_s = s.split(" ");
        int v = 4;
        if(arr_s.length == 1) {
            int v1 = zzqt.zzby(arr_s[0]);
            ByteBuffer byteBuffer0 = ByteBuffer.allocate(4);
            byteBuffer0.order(ByteOrder.LITTLE_ENDIAN);
            byteBuffer0.putInt(v1);
            arr_b = byteBuffer0.array();
        }
        else if(arr_s.length < 5) {
            arr_b = new byte[arr_s.length << 1];
            for(int v2 = 0; v2 < arr_s.length; ++v2) {
                int v3 = zzqt.zzby(arr_s[v2]);
                int v4 = v3 >> 16 ^ 0xFFFF & v3;
                arr_b[v2 << 1] = (byte)v4;
                arr_b[(v2 << 1) + 1] = (byte)(v4 >> 8);
            }
        }
        else {
            arr_b = new byte[arr_s.length];
            for(int v5 = 0; v5 < arr_s.length; ++v5) {
                int v6 = zzqt.zzby(arr_s[v5]);
                arr_b[v5] = (byte)(v6 >> 24 ^ (v6 & 0xFF ^ v6 >> 8 & 0xFF ^ v6 >> 16 & 0xFF));
            }
        }
        this.zzbqv = this.zzml();
        synchronized(this.mLock) {
            if(this.zzbqv == null) {
                return new byte[0];
            }
            this.zzbqv.reset();
            this.zzbqv.update(arr_b);
            byte[] arr_b1 = this.zzbqv.digest();
            if(arr_b1.length <= 4) {
                v = arr_b1.length;
            }
            byte[] arr_b2 = new byte[v];
            System.arraycopy(arr_b1, 0, arr_b2, 0, arr_b2.length);
            return arr_b2;
        }
    }
}

