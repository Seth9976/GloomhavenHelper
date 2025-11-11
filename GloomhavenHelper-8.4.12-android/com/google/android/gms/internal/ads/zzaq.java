package com.google.android.gms.internal.ads;

import androidx.annotation.VisibleForTesting;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

@VisibleForTesting
final class zzaq extends FilterInputStream {
    private final long zzce;
    private long zzcf;

    zzaq(InputStream inputStream0, long v) {
        super(inputStream0);
        this.zzce = v;
    }

    @Override
    public final int read() throws IOException {
        int v = super.read();
        if(v != -1) {
            ++this.zzcf;
        }
        return v;
    }

    @Override
    public final int read(byte[] arr_b, int v, int v1) throws IOException {
        int v2 = super.read(arr_b, v, v1);
        if(v2 != -1) {
            this.zzcf += (long)v2;
        }
        return v2;
    }

    final long zzp() {
        return this.zzce - this.zzcf;
    }
}

