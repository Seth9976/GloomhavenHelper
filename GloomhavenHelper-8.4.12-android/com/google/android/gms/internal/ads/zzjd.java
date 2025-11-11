package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

public final class zzjd implements zzjf {
    private static final byte[] zzamx;
    private final zzno zzamy;
    private final long zzamz;
    private long zzana;
    private byte[] zzanb;
    private int zzanc;
    private int zzand;

    static {
        zzjd.zzamx = new byte[0x1000];
    }

    public zzjd(zzno zzno0, long v, long v1) {
        this.zzamy = zzno0;
        this.zzana = v;
        this.zzamz = v1;
        this.zzanb = new byte[0x10000];
    }

    @Override  // com.google.android.gms.internal.ads.zzjf
    public final long getLength() {
        return this.zzamz;
    }

    @Override  // com.google.android.gms.internal.ads.zzjf
    public final long getPosition() {
        return this.zzana;
    }

    @Override  // com.google.android.gms.internal.ads.zzjf
    public final int read(byte[] arr_b, int v, int v1) throws IOException, InterruptedException {
        int v2 = this.zzb(arr_b, v, v1);
        if(v2 == 0) {
            v2 = this.zza(arr_b, v, v1, 0, true);
        }
        this.zzag(v2);
        return v2;
    }

    @Override  // com.google.android.gms.internal.ads.zzjf
    public final void readFully(byte[] arr_b, int v, int v1) throws IOException, InterruptedException {
        this.zza(arr_b, v, v1, false);
    }

    private final int zza(byte[] arr_b, int v, int v1, int v2, boolean z) throws InterruptedException, IOException {
        if(Thread.interrupted()) {
            throw new InterruptedException();
        }
        int v3 = this.zzamy.read(arr_b, v + v2, v1 - v2);
        if(v3 == -1) {
            if(v2 != 0 || !z) {
                throw new EOFException();
            }
            return -1;
        }
        return v2 + v3;
    }

    @Override  // com.google.android.gms.internal.ads.zzjf
    public final void zza(byte[] arr_b, int v, int v1) throws IOException, InterruptedException {
        if(this.zzd(v1, false)) {
            System.arraycopy(this.zzanb, this.zzanc - v1, arr_b, v, v1);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzjf
    public final boolean zza(byte[] arr_b, int v, int v1, boolean z) throws IOException, InterruptedException {
        int v2;
        for(v2 = this.zzb(arr_b, v, v1); v2 < v1 && v2 != -1; v2 = this.zza(arr_b, v, v1, v2, z)) {
        }
        this.zzag(v2);
        return v2 != -1;
    }

    @Override  // com.google.android.gms.internal.ads.zzjf
    public final int zzab(int v) throws IOException, InterruptedException {
        int v1 = this.zzae(v);
        if(v1 == 0) {
            v1 = this.zza(zzjd.zzamx, 0, Math.min(v, zzjd.zzamx.length), 0, true);
        }
        this.zzag(v1);
        return v1;
    }

    @Override  // com.google.android.gms.internal.ads.zzjf
    public final void zzac(int v) throws IOException, InterruptedException {
        int v1;
        for(v1 = this.zzae(v); v1 < v && v1 != -1; v1 = this.zza(zzjd.zzamx, -v1, Math.min(v, zzjd.zzamx.length + v1), v1, false)) {
        }
        this.zzag(v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzjf
    public final void zzad(int v) throws IOException, InterruptedException {
        this.zzd(v, false);
    }

    private final int zzae(int v) {
        int v1 = Math.min(this.zzand, v);
        this.zzaf(v1);
        return v1;
    }

    private final void zzaf(int v) {
        this.zzand -= v;
        this.zzanc = 0;
        byte[] arr_b = this.zzanb;
        int v1 = this.zzand;
        if(v1 < arr_b.length - 0x80000) {
            arr_b = new byte[v1 + 0x10000];
        }
        System.arraycopy(this.zzanb, v, arr_b, 0, this.zzand);
        this.zzanb = arr_b;
    }

    private final void zzag(int v) {
        if(v != -1) {
            this.zzana += (long)v;
        }
    }

    private final int zzb(byte[] arr_b, int v, int v1) {
        int v2 = this.zzand;
        if(v2 == 0) {
            return 0;
        }
        int v3 = Math.min(v2, v1);
        System.arraycopy(this.zzanb, 0, arr_b, v, v3);
        this.zzaf(v3);
        return v3;
    }

    private final boolean zzd(int v, boolean z) throws IOException, InterruptedException {
        int v1 = this.zzanc + v;
        byte[] arr_b = this.zzanb;
        if(v1 > arr_b.length) {
            this.zzanb = Arrays.copyOf(this.zzanb, zzop.zzd(arr_b.length << 1, v1 + 0x10000, v1 + 0x80000));
        }
        int v2 = Math.min(this.zzand - this.zzanc, v);
        while(v2 < v) {
            v2 = this.zza(this.zzanb, this.zzanc, v, v2, false);
            if(v2 == -1) {
                return false;
            }
            if(false) {
                break;
            }
        }
        this.zzanc += v;
        this.zzand = Math.max(this.zzand, this.zzanc);
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzjf
    public final void zzgm() {
        this.zzanc = 0;
    }
}

