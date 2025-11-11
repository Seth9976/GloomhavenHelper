package com.google.android.gms.internal.ads;

import java.io.IOException;

public class zzeda {
    protected volatile int zzhnu;

    public zzeda() {
        this.zzhnu = -1;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.zzbfq();
    }

    @Override
    public String toString() {
        return zzecz.zza(this);
    }

    public void zza(zzecr zzecr0) throws IOException {
    }

    public static final byte[] zzb(zzeda zzeda0) {
        byte[] arr_b = new byte[zzeda0.zzbda()];
        try {
            zzecr zzecr0 = zzecr.zzq(arr_b, 0, arr_b.length);
            zzeda0.zza(zzecr0);
            zzecr0.zzbcc();
            return arr_b;
        }
        catch(IOException iOException0) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", iOException0);
        }
    }

    public final int zzbda() {
        int v = this.zzon();
        this.zzhnu = v;
        return v;
    }

    public zzeda zzbfq() throws CloneNotSupportedException {
        return (zzeda)super.clone();
    }

    protected int zzon() {
        return 0;
    }
}

