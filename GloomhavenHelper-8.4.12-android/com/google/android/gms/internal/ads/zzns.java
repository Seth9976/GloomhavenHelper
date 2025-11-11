package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzns implements zznm {
    private final boolean zzbfm;
    private final int zzbfn;
    private final byte[] zzbfo;
    private final zznj[] zzbfp;
    private int zzbfq;
    private int zzbfr;
    private int zzbfs;
    private zznj[] zzbft;

    public zzns(boolean z, int v) {
        this(true, 0x10000, 0);
    }

    private zzns(boolean z, int v, int v1) {
        zzob.checkArgument(true);
        zzob.checkArgument(true);
        this.zzbfm = true;
        this.zzbfn = 0x10000;
        this.zzbfs = 0;
        this.zzbft = new zznj[100];
        this.zzbfo = null;
        this.zzbfp = new zznj[1];
    }

    public final void reset() {
        synchronized(this) {
            if(this.zzbfm) {
                this.zzbb(0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zznm
    public final void zza(zznj zznj0) {
        synchronized(this) {
            this.zzbfp[0] = zznj0;
            this.zza(this.zzbfp);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zznm
    public final void zza(zznj[] arr_zznj) {
        synchronized(this) {
            if(this.zzbfs + arr_zznj.length >= this.zzbft.length) {
                this.zzbft = (zznj[])Arrays.copyOf(this.zzbft, Math.max(this.zzbft.length << 1, this.zzbfs + arr_zznj.length));
            }
            for(int v1 = 0; v1 < arr_zznj.length; ++v1) {
                zznj zznj0 = arr_zznj[v1];
                zzob.checkArgument(zznj0.data == null || zznj0.data.length == this.zzbfn);
                int v2 = this.zzbfs;
                this.zzbfs = v2 + 1;
                this.zzbft[v2] = zznj0;
            }
            this.zzbfr -= arr_zznj.length;
            this.notifyAll();
        }
    }

    public final void zzbb(int v) {
        synchronized(this) {
            boolean z = v < this.zzbfq;
            this.zzbfq = v;
            if(z) {
                this.zzn();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zznm
    public final zznj zzik() {
        zznj zznj0;
        synchronized(this) {
            ++this.zzbfr;
            if(this.zzbfs > 0) {
                int v1 = this.zzbfs - 1;
                this.zzbfs = v1;
                zznj0 = this.zzbft[v1];
                this.zzbft[this.zzbfs] = null;
            }
            else {
                zznj0 = new zznj(new byte[this.zzbfn], 0);
            }
            return zznj0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zznm
    public final int zzil() {
        return this.zzbfn;
    }

    public final int zzio() {
        synchronized(this) {
        }
        return this.zzbfr * this.zzbfn;
    }

    @Override  // com.google.android.gms.internal.ads.zznm
    public final void zzn() {
        synchronized(this) {
            int v1 = Math.max(0, (this.zzbfq + this.zzbfn - 1) / this.zzbfn - this.zzbfr);
            if(v1 >= this.zzbfs) {
                return;
            }
            Arrays.fill(this.zzbft, v1, this.zzbfs, null);
            this.zzbfs = v1;
        }
    }
}

