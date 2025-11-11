package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;

final class zzebe extends InputStream {
    private int mark;
    private final zzeba zzhwe;
    private zzebb zzhwo;
    private zzdxy zzhwp;
    private int zzhwq;
    private int zzhwr;
    private int zzhws;

    public zzebe(zzeba zzeba0) {
        this.zzhwe = zzeba0;
        super();
        this.initialize();
    }

    @Override
    public final int available() throws IOException {
        int v = this.zzhws + this.zzhwr;
        return this.zzhwe.size() - v;
    }

    private final void initialize() {
        this.zzhwo = new zzebb(this.zzhwe, null);
        this.zzhwp = (zzdxy)this.zzhwo.next();
        this.zzhwq = this.zzhwp.size();
        this.zzhwr = 0;
        this.zzhws = 0;
    }

    @Override
    public final void mark(int v) {
        this.mark = this.zzhws + this.zzhwr;
    }

    @Override
    public final boolean markSupported() {
        return true;
    }

    @Override
    public final int read() throws IOException {
        this.zzbeq();
        zzdxy zzdxy0 = this.zzhwp;
        if(zzdxy0 == null) {
            return -1;
        }
        int v = this.zzhwr;
        this.zzhwr = v + 1;
        return zzdxy0.zzfe(v) & 0xFF;
    }

    @Override
    public final int read(byte[] arr_b, int v, int v1) {
        if(arr_b == null) {
            throw new NullPointerException();
        }
        if(v < 0 || v1 < 0 || v1 > arr_b.length - v) {
            throw new IndexOutOfBoundsException();
        }
        int v2 = this.zzl(arr_b, v, v1);
        return v2 == 0 ? -1 : v2;
    }

    @Override
    public final void reset() {
        synchronized(this) {
            this.initialize();
            this.zzl(null, 0, this.mark);
        }
    }

    @Override
    public final long skip(long v) {
        if(v < 0L) {
            throw new IndexOutOfBoundsException();
        }
        if(v > 0x7FFFFFFFL) {
            v = 0x7FFFFFFFL;
        }
        return (long)this.zzl(null, 0, ((int)v));
    }

    private final void zzbeq() {
        if(this.zzhwp != null) {
            int v = this.zzhwq;
            if(this.zzhwr == v) {
                this.zzhws += v;
                this.zzhwr = 0;
                if(this.zzhwo.hasNext()) {
                    this.zzhwp = (zzdxy)this.zzhwo.next();
                    this.zzhwq = this.zzhwp.size();
                    return;
                }
                this.zzhwp = null;
                this.zzhwq = 0;
            }
        }
    }

    private final int zzl(byte[] arr_b, int v, int v1) {
        int v2;
        for(v2 = v1; v2 > 0; v2 -= v3) {
            this.zzbeq();
            if(this.zzhwp == null) {
                break;
            }
            int v3 = Math.min(this.zzhwq - this.zzhwr, v2);
            if(arr_b != null) {
                this.zzhwp.zza(arr_b, this.zzhwr, v, v3);
                v += v3;
            }
            this.zzhwr += v3;
        }
        return v1 - v2;
    }
}

