package com.google.android.gms.internal.ads;

import android.os.Looper;
import android.os.SystemClock;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

public final class zznv {
    private final ExecutorService zzbfv;
    private zznx zzbfw;
    private IOException zzbfx;

    public zznv(String s) {
        this.zzbfv = zzop.zzbl(s);
    }

    public final boolean isLoading() {
        return this.zzbfw != null;
    }

    public final long zza(zzoa zzoa0, zzny zzny0, int v) {
        Looper looper0 = Looper.myLooper();
        zzob.checkState(looper0 != null);
        long v1 = SystemClock.elapsedRealtime();
        new zznx(this, looper0, zzoa0, zzny0, v, v1).zzek(0L);
        return v1;
    }

    public final void zza(Runnable runnable0) {
        zznx zznx0 = this.zzbfw;
        if(zznx0 != null) {
            zznx0.zzl(true);
        }
        this.zzbfv.execute(runnable0);
        this.zzbfv.shutdown();
    }

    public final void zzbc(int v) throws IOException {
        IOException iOException0 = this.zzbfx;
        if(iOException0 != null) {
            throw iOException0;
        }
        zznx zznx0 = this.zzbfw;
        if(zznx0 != null) {
            zznx0.zzbc(zznx0.zzbgc);
        }
    }

    public final void zzip() {
        this.zzbfw.zzl(false);
    }
}

