package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

final class zzbcp implements zzedv {
    private final ByteBuffer zzakt;

    zzbcp(ByteBuffer byteBuffer0) {
        this.zzakt = byteBuffer0.duplicate();
    }

    @Override  // com.google.android.gms.internal.ads.zzedv
    public final void close() throws IOException {
    }

    @Override  // com.google.android.gms.internal.ads.zzedv
    public final long position() throws IOException {
        return (long)this.zzakt.position();
    }

    @Override  // com.google.android.gms.internal.ads.zzedv
    public final int read(ByteBuffer byteBuffer0) throws IOException {
        if(this.zzakt.remaining() == 0 && byteBuffer0.remaining() > 0) {
            return -1;
        }
        int v = Math.min(byteBuffer0.remaining(), this.zzakt.remaining());
        byte[] arr_b = new byte[v];
        this.zzakt.get(arr_b);
        byteBuffer0.put(arr_b);
        return v;
    }

    @Override  // com.google.android.gms.internal.ads.zzedv
    public final long size() throws IOException {
        return (long)this.zzakt.limit();
    }

    @Override  // com.google.android.gms.internal.ads.zzedv
    public final void zzfc(long v) throws IOException {
        this.zzakt.position(((int)v));
    }

    @Override  // com.google.android.gms.internal.ads.zzedv
    public final ByteBuffer zzh(long v, long v1) throws IOException {
        this.zzakt.position(((int)v));
        ByteBuffer byteBuffer0 = this.zzakt.slice();
        byteBuffer0.limit(((int)v1));
        this.zzakt.position(this.zzakt.position());
        return byteBuffer0;
    }
}

