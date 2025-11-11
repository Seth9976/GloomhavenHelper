package com.google.android.gms.internal.ads;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class zzay extends ByteArrayOutputStream {
    private final zzaj zzbw;

    public zzay(zzaj zzaj0, int v) {
        this.zzbw = zzaj0;
        this.buf = this.zzbw.zzc(Math.max(v, 0x100));
    }

    @Override
    public final void close() throws IOException {
        this.zzbw.zza(this.buf);
        this.buf = null;
        super.close();
    }

    @Override
    public final void finalize() {
        this.zzbw.zza(this.buf);
    }

    @Override
    public final void write(int v) {
        synchronized(this) {
            this.zzd(1);
            super.write(v);
        }
    }

    @Override
    public final void write(byte[] arr_b, int v, int v1) {
        synchronized(this) {
            this.zzd(v1);
            super.write(arr_b, v, v1);
        }
    }

    private final void zzd(int v) {
        if(this.count + v <= this.buf.length) {
            return;
        }
        byte[] arr_b = this.zzbw.zzc(this.count + v << 1);
        System.arraycopy(this.buf, 0, arr_b, 0, this.count);
        this.zzbw.zza(this.buf);
        this.buf = arr_b;
    }
}

