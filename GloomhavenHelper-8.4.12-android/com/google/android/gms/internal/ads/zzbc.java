package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzbc implements zzbe {
    private static Logger zzcp;
    private ThreadLocal zzcq;

    static {
        zzbc.zzcp = Logger.getLogger(zzbc.class.getName());
    }

    public zzbc() {
        this.zzcq = new zzbb(this);
    }

    @Override  // com.google.android.gms.internal.ads.zzbe
    public final zzbf zza(zzedv zzedv0, zzbi zzbi0) throws IOException {
        long v6;
        long v3;
        long v = zzedv0.position();
        ((ByteBuffer)this.zzcq.get()).rewind().limit(8);
        int v1;
        while((v1 = zzedv0.read(((ByteBuffer)this.zzcq.get()))) != 8) {
            if(v1 < 0) {
                zzedv0.zzfc(v);
                throw new EOFException();
            }
        }
        ((ByteBuffer)this.zzcq.get()).rewind();
        long v2 = zzbg.zza(((ByteBuffer)this.zzcq.get()));
        byte[] arr_b = null;
        if(v2 < 8L && v2 > 1L) {
            zzbc.zzcp.logp(Level.SEVERE, "com.coremedia.iso.AbstractBoxParser", "parseBox", "Plausibility check failed: size < 8 (size = " + v2 + "). Stop parsing!");
            return null;
        }
        String s = zzbg.zzf(((ByteBuffer)this.zzcq.get()));
        if(v2 == 1L) {
            ((ByteBuffer)this.zzcq.get()).limit(16);
            zzedv0.read(((ByteBuffer)this.zzcq.get()));
            ((ByteBuffer)this.zzcq.get()).position(8);
            v3 = zzbg.zzc(((ByteBuffer)this.zzcq.get())) - 16L;
        }
        else {
            v3 = v2 == 0L ? zzedv0.size() - zzedv0.position() : v2 - 8L;
        }
        if("uuid".equals(s)) {
            ((ByteBuffer)this.zzcq.get()).limit(((ByteBuffer)this.zzcq.get()).limit() + 16);
            zzedv0.read(((ByteBuffer)this.zzcq.get()));
            arr_b = new byte[16];
            for(int v4 = ((ByteBuffer)this.zzcq.get()).position() - 16; v4 < ((ByteBuffer)this.zzcq.get()).position(); ++v4) {
                int v5 = ((ByteBuffer)this.zzcq.get()).position();
                arr_b[v4 - (v5 - 16)] = ((ByteBuffer)this.zzcq.get()).get(v4);
            }
            v6 = v3 - 16L;
        }
        else {
            v6 = v3;
        }
        zzbf zzbf0 = this.zza(s, arr_b, (zzbi0 instanceof zzbf ? ((zzbf)zzbi0).getType() : ""));
        zzbf0.zza(zzbi0);
        ((ByteBuffer)this.zzcq.get()).rewind();
        zzbf0.zza(zzedv0, ((ByteBuffer)this.zzcq.get()), v6, this);
        return zzbf0;
    }

    public abstract zzbf zza(String arg1, byte[] arg2, String arg3);
}

