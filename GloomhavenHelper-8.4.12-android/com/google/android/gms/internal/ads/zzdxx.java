package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.charset.Charset;

class zzdxx extends zzdxy {
    protected final byte[] zzhol;

    zzdxx(byte[] arr_b) {
        if(arr_b == null) {
            throw new NullPointerException();
        }
        this.zzhol = arr_b;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof zzdxn)) {
            return false;
        }
        if(this.size() != ((zzdxn)object0).size()) {
            return false;
        }
        if(this.size() == 0) {
            return true;
        }
        if(object0 instanceof zzdxx) {
            int v = this.zzbax();
            int v1 = ((zzdxx)object0).zzbax();
            return v == 0 || v1 == 0 || v == v1 ? this.zza(((zzdxx)object0), 0, this.size()) : false;
        }
        return object0.equals(this);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    public int size() {
        return this.zzhol.length;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    protected final String zza(Charset charset0) {
        return new String(this.zzhol, this.zzbay(), this.size(), charset0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    final void zza(zzdxo zzdxo0) throws IOException {
        zzdxo0.zzi(this.zzhol, this.zzbay(), this.size());
    }

    @Override  // com.google.android.gms.internal.ads.zzdxy
    final boolean zza(zzdxn zzdxn0, int v, int v1) {
        if(v1 > zzdxn0.size()) {
            throw new IllegalArgumentException("Length too large: " + v1 + this.size());
        }
        int v2 = v + v1;
        if(v2 > zzdxn0.size()) {
            throw new IllegalArgumentException("Ran off end of other: " + v + ", " + v1 + ", " + zzdxn0.size());
        }
        if(zzdxn0 instanceof zzdxx) {
            byte[] arr_b = this.zzhol;
            byte[] arr_b1 = ((zzdxx)zzdxn0).zzhol;
            int v3 = this.zzbay();
            int v4 = this.zzbay();
            for(int v5 = ((zzdxx)zzdxn0).zzbay() + v; v4 < v3 + v1; ++v5) {
                if(arr_b[v4] != arr_b1[v5]) {
                    return false;
                }
                ++v4;
            }
            return true;
        }
        return zzdxn0.zzz(v, v2).equals(this.zzz(0, v1));
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    protected void zzb(byte[] arr_b, int v, int v1, int v2) {
        System.arraycopy(this.zzhol, v, arr_b, v1, v2);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    public final boolean zzbat() {
        int v = this.zzbay();
        return zzece.zzm(this.zzhol, v, this.size() + v);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    public final zzdxz zzbau() {
        return zzdxz.zzb(this.zzhol, this.zzbay(), this.size(), true);
    }

    protected int zzbay() {
        return 0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    public byte zzfe(int v) {
        return this.zzhol[v];
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    byte zzff(int v) {
        return this.zzhol[v];
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    protected final int zzg(int v, int v1, int v2) {
        int v3 = this.zzbay();
        return zzece.zzb(v, this.zzhol, v3 + v1, v2 + (v3 + v1));
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    protected final int zzh(int v, int v1, int v2) {
        return zzdzc.zza(v, this.zzhol, this.zzbay() + v1, v2);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    public final zzdxn zzz(int v, int v1) {
        int v2 = zzdxx.zzi(v, v1, this.size());
        return v2 == 0 ? zzdxn.zzhoe : new zzdxu(this.zzhol, this.zzbay() + v, v2);
    }
}

