package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

public class zzedr extends zzedt implements zzbf {
    private String type;
    private long zzavf;
    private zzbi zzifg;
    private boolean zzifh;

    public zzedr(String s) {
        this.type = s;
    }

    @Override  // com.google.android.gms.internal.ads.zzbf
    public final String getType() {
        return this.type;
    }

    @Override  // com.google.android.gms.internal.ads.zzbf
    public final void zza(zzbi zzbi0) {
        this.zzifg = zzbi0;
    }

    @Override  // com.google.android.gms.internal.ads.zzedt
    public final void zza(zzedv zzedv0, long v, zzbe zzbe0) throws IOException {
        this.zzifn = zzedv0;
        this.zzifs = zzedv0.position();
        this.zzbcq = this.zzifs - ((long)(this.zzifh || v + 8L >= 0x100000000L ? 16 : 8));
        zzedv0.zzfc(zzedv0.position() + v);
        this.zzare = zzedv0.position();
        this.zzifq = zzbe0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbf
    public final void zza(zzedv zzedv0, ByteBuffer byteBuffer0, long v, zzbe zzbe0) throws IOException {
        this.zzavf = zzedv0.position() - ((long)byteBuffer0.remaining());
        this.zzifh = byteBuffer0.remaining() == 16;
        this.zza(zzedv0, v, zzbe0);
    }
}

