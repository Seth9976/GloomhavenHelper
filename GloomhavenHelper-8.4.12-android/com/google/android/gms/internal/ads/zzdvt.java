package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

abstract class zzdvt implements zzdoy {
    private final zzdvr zzhlr;
    private final zzdvr zzhls;

    public zzdvt(byte[] arr_b) throws InvalidKeyException {
        this.zzhlr = this.zzc(arr_b, 1);
        this.zzhls = this.zzc(arr_b, 0);
    }

    abstract zzdvr zzc(byte[] arg1, int arg2) throws InvalidKeyException;

    @Override  // com.google.android.gms.internal.ads.zzdoy
    public byte[] zzc(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        int v = this.zzhlr.zzbab();
        if(arr_b.length > 0x7FFFFFEF - v) {
            throw new GeneralSecurityException("plaintext too long");
        }
        int v1 = this.zzhlr.zzbab();
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(arr_b.length + v1 + 16);
        int v2 = this.zzhlr.zzbab();
        if(byteBuffer0.remaining() < arr_b.length + v2 + 16) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        this.zzhlr.zza(byteBuffer0, arr_b);
        byteBuffer0.position(byteBuffer0.position());
        byte[] arr_b2 = new byte[this.zzhlr.zzbab()];
        byteBuffer0.get(arr_b2);
        byteBuffer0.limit(byteBuffer0.limit() - 16);
        if(arr_b1 == null) {
            arr_b1 = new byte[0];
        }
        ByteBuffer byteBuffer1 = this.zzhls.zzd(arr_b2, 0);
        byte[] arr_b3 = new byte[0x20];
        byteBuffer1.get(arr_b3);
        int v3 = arr_b1.length % 16 == 0 ? arr_b1.length : arr_b1.length + 16 - arr_b1.length % 16;
        int v4 = byteBuffer0.remaining();
        int v5 = (v4 % 16 == 0 ? v4 : v4 + 16 - v4 % 16) + v3;
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(v5 + 16).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer2.put(arr_b1);
        byteBuffer2.position(v3);
        byteBuffer2.put(byteBuffer0);
        byteBuffer2.position(v5);
        byteBuffer2.putLong(((long)arr_b1.length));
        byteBuffer2.putLong(((long)v4));
        byte[] arr_b4 = zzdwr.zze(arr_b3, byteBuffer2.array());
        byteBuffer0.limit(byteBuffer0.limit() + 16);
        byteBuffer0.put(arr_b4);
        return byteBuffer0.array();
    }
}

