package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzmm implements zzjn {
    private final zzom zzaod;
    private final zznm zzbaf;
    private final int zzbcu;
    private final zzmk zzbcv;
    private final zzmj zzbcw;
    private final AtomicInteger zzbcx;
    private zzml zzbcy;
    private zzml zzbcz;
    private zzgz zzbda;
    private boolean zzbdb;
    private zzgz zzbdc;
    private long zzbdd;
    private int zzbde;
    private zzmo zzbdf;

    public zzmm(zznm zznm0) {
        this.zzbaf = zznm0;
        this.zzbcu = zznm0.zzil();
        this.zzbcv = new zzmk();
        this.zzbcw = new zzmj();
        this.zzaod = new zzom(0x20);
        this.zzbcx = new AtomicInteger();
        this.zzbde = this.zzbcu;
        this.zzbcy = new zzml(0L, this.zzbcu);
        this.zzbcz = this.zzbcy;
    }

    public final void disable() {
        if(this.zzbcx.getAndSet(2) == 0) {
            this.zzhy();
        }
    }

    private final void zza(long v, byte[] arr_b, int v1) {
        this.zzej(v);
        int v2 = 0;
        while(v2 < v1) {
            int v3 = (int)(v - this.zzbcy.zzbcq);
            int v4 = Math.min(v1 - v2, this.zzbcu - v3);
            zznj zznj0 = this.zzbcy.zzbcs;
            System.arraycopy(zznj0.data, zznj0.zzaz(v3), arr_b, v2, v4);
            v += (long)v4;
            v2 += v4;
            if(v == this.zzbcy.zzare) {
                this.zzbaf.zza(zznj0);
                this.zzbcy = this.zzbcy.zzie();
            }
        }
    }

    public final int zza(zzhb zzhb0, zziv zziv0, boolean z, boolean z1, long v) {
        int v5;
        switch(this.zzbcv.zza(zzhb0, zziv0, z, z1, this.zzbda, this.zzbcw)) {
            case -5: {
                this.zzbda = zzhb0.zzagi;
                return -5;
            }
            case -4: {
                if(!zziv0.zzgd()) {
                    if(zziv0.zzamu < v) {
                        zziv0.zzw(0x80000000);
                    }
                    if(zziv0.zzgj()) {
                        zzmj zzmj0 = this.zzbcw;
                        long v1 = zzmj0.zzavf;
                        this.zzaod.reset(1);
                        this.zza(v1, this.zzaod.data, 1);
                        int v3 = this.zzaod.data[0];
                        boolean z2 = (v3 & 0x80) != 0;
                        if(zziv0.zzamt.iv == null) {
                            zziv0.zzamt.iv = new byte[16];
                        }
                        this.zza(v1 + 1L, zziv0.zzamt.iv, v3 & 0x7F);
                        long v4 = v1 + 1L + ((long)(v3 & 0x7F));
                        if(z2) {
                            this.zzaod.reset(2);
                            this.zza(v4, this.zzaod.data, 2);
                            v4 += 2L;
                            v5 = this.zzaod.readUnsignedShort();
                        }
                        else {
                            v5 = 1;
                        }
                        int[] arr_v = zziv0.zzamt.numBytesOfClearData;
                        int[] arr_v1 = arr_v == null || arr_v.length < v5 ? new int[v5] : arr_v;
                        int[] arr_v2 = zziv0.zzamt.numBytesOfEncryptedData;
                        int[] arr_v3 = arr_v2 == null || arr_v2.length < v5 ? new int[v5] : arr_v2;
                        if(z2) {
                            this.zzaod.reset(v5 * 6);
                            this.zza(v4, this.zzaod.data, v5 * 6);
                            v4 += (long)(v5 * 6);
                            this.zzaod.zzbh(0);
                            for(int v2 = 0; v2 < v5; ++v2) {
                                arr_v1[v2] = this.zzaod.readUnsignedShort();
                                arr_v3[v2] = this.zzaod.zzjc();
                            }
                        }
                        else {
                            arr_v1[0] = 0;
                            arr_v3[0] = zzmj0.size - ((int)(v4 - zzmj0.zzavf));
                        }
                        zziv0.zzamt.set(v5, arr_v1, arr_v3, zzmj0.zzapz.zzans, zziv0.zzamt.iv, zzmj0.zzapz.zzanr);
                        int v6 = (int)(v4 - zzmj0.zzavf);
                        zzmj0.zzavf += (long)v6;
                        zzmj0.size -= v6;
                    }
                    zziv0.zzz(this.zzbcw.size);
                    long v7 = this.zzbcw.zzavf;
                    ByteBuffer byteBuffer0 = zziv0.zzcs;
                    int v8 = this.zzbcw.size;
                    this.zzej(v7);
                    while(v8 > 0) {
                        int v9 = (int)(v7 - this.zzbcy.zzbcq);
                        int v10 = Math.min(v8, this.zzbcu - v9);
                        zznj zznj0 = this.zzbcy.zzbcs;
                        byteBuffer0.put(zznj0.data, zznj0.zzaz(v9), v10);
                        v7 += (long)v10;
                        v8 -= v10;
                        if(v7 == this.zzbcy.zzare) {
                            this.zzbaf.zza(zznj0);
                            this.zzbcy = this.zzbcy.zzie();
                        }
                    }
                    this.zzej(this.zzbcw.zzbcd);
                }
                return -4;
            }
            case -3: {
                return -3;
            }
            default: {
                throw new IllegalStateException();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzjn
    public final int zza(zzjf zzjf0, int v, boolean z) throws IOException, InterruptedException {
        if(!this.zzig()) {
            int v1 = zzjf0.zzab(v);
            if(v1 == -1) {
                throw new EOFException();
            }
            return v1;
        }
        try {
            int v3 = this.zzau(v);
            int v4 = zzjf0.read(this.zzbcz.zzbcs.data, this.zzbcz.zzbcs.zzaz(this.zzbde), v3);
            if(v4 != -1) {
                this.zzbde += v4;
                this.zzbdd += (long)v4;
                return v4;
            }
        }
        finally {
            this.zzih();
        }
        throw new EOFException();
    }

    @Override  // com.google.android.gms.internal.ads.zzjn
    public final void zza(long v, int v1, int v2, int v3, zzjq zzjq0) {
        if(!this.zzig()) {
            this.zzbcv.zzei(v);
            return;
        }
        try {
            this.zzbcv.zza(v, v1, this.zzbdd - ((long)v2), v2, zzjq0);
        }
        finally {
            this.zzih();
        }
    }

    public final void zza(zzmo zzmo0) {
        this.zzbdf = zzmo0;
    }

    @Override  // com.google.android.gms.internal.ads.zzjn
    public final void zza(zzom zzom0, int v) {
        if(!this.zzig()) {
            zzom0.zzbi(v);
            return;
        }
        while(v > 0) {
            int v1 = this.zzau(v);
            zzom0.zze(this.zzbcz.zzbcs.data, this.zzbcz.zzbcs.zzaz(this.zzbde), v1);
            this.zzbde += v1;
            this.zzbdd += (long)v1;
            v -= v1;
        }
        this.zzih();
    }

    private final int zzau(int v) {
        if(this.zzbde == this.zzbcu) {
            this.zzbde = 0;
            if(this.zzbcz.zzbcr) {
                this.zzbcz = this.zzbcz.zzbct;
            }
            zzml zzml0 = this.zzbcz;
            zznj zznj0 = this.zzbaf.zzik();
            zzml zzml1 = new zzml(this.zzbcz.zzare, this.zzbcu);
            zzml0.zzbcs = zznj0;
            zzml0.zzbct = zzml1;
            zzml0.zzbcr = true;
        }
        return Math.min(v, this.zzbcu - this.zzbde);
    }

    @Override  // com.google.android.gms.internal.ads.zzjn
    public final void zze(zzgz zzgz0) {
        zzgz zzgz1 = zzgz0 == null ? null : zzgz0;
        boolean z = this.zzbcv.zzg(zzgz1);
        this.zzbdc = zzgz0;
        this.zzbdb = false;
        zzmo zzmo0 = this.zzbdf;
        if(zzmo0 != null && z) {
            zzmo0.zzf(zzgz1);
        }
    }

    public final boolean zze(long v, boolean z) {
        long v1 = this.zzbcv.zzd(v, z);
        if(v1 == -1L) {
            return false;
        }
        this.zzej(v1);
        return true;
    }

    private final void zzej(long v) {
        while(v >= this.zzbcy.zzare) {
            this.zzbaf.zza(this.zzbcy.zzbcs);
            this.zzbcy = this.zzbcy.zzie();
        }
    }

    public final long zzhs() {
        return this.zzbcv.zzhs();
    }

    private final void zzhy() {
        this.zzbcv.zzhy();
        zzml zzml0 = this.zzbcy;
        if(zzml0.zzbcr) {
            zznj[] arr_zznj = new zznj[this.zzbcz.zzbcr + ((int)(this.zzbcz.zzbcq - zzml0.zzbcq)) / this.zzbcu];
            for(int v = 0; v < arr_zznj.length; ++v) {
                arr_zznj[v] = zzml0.zzbcs;
                zzml0 = zzml0.zzie();
            }
            this.zzbaf.zza(arr_zznj);
        }
        this.zzbcy = new zzml(0L, this.zzbcu);
        this.zzbcz = this.zzbcy;
        this.zzbdd = 0L;
        this.zzbde = this.zzbcu;
        this.zzbaf.zzn();
    }

    public final int zzia() {
        return this.zzbcv.zzia();
    }

    public final boolean zzib() {
        return this.zzbcv.zzib();
    }

    public final zzgz zzic() {
        return this.zzbcv.zzic();
    }

    public final void zzif() {
        long v = this.zzbcv.zzid();
        if(v != -1L) {
            this.zzej(v);
        }
    }

    private final boolean zzig() {
        return this.zzbcx.compareAndSet(0, 1);
    }

    private final void zzih() {
        if(!this.zzbcx.compareAndSet(1, 0)) {
            this.zzhy();
        }
    }

    public final void zzk(boolean z) {
        int v = this.zzbcx.getAndSet((z ? 0 : 2));
        this.zzhy();
        this.zzbcv.zzhz();
        if(v == 2) {
            this.zzbda = null;
        }
    }
}

