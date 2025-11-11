package com.google.android.gms.internal.ads;

final class zzbcu extends zzbc {
    static final zzbcu zzeed;

    static {
        zzbcu.zzeed = new zzbcu();
    }

    @Override  // com.google.android.gms.internal.ads.zzbc
    public final zzbf zza(String s, byte[] arr_b, String s1) {
        if("moov".equals(s)) {
            return new zzbh();
        }
        return "mvhd".equals(s) ? new zzbk() : new zzbj(s);
    }
}

