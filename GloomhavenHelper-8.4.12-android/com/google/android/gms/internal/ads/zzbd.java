package com.google.android.gms.internal.ads;

import java.io.Closeable;
import java.io.IOException;

public class zzbd extends zzedt implements Closeable {
    private static zzeeb zzcr;

    static {
        zzbd.zzcr = zzeeb.zzn(zzbd.class);
    }

    public zzbd(zzedv zzedv0, zzbe zzbe0) throws IOException {
        this.zza(zzedv0, zzedv0.size(), zzbe0);
    }

    @Override  // com.google.android.gms.internal.ads.zzedt
    public void close() throws IOException {
        this.zzifn.close();
    }

    @Override  // com.google.android.gms.internal.ads.zzedt
    public String toString() {
        return "model(" + this.zzifn.toString() + ")";
    }
}

