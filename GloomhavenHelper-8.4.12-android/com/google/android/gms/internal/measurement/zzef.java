package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.charset.Charset;

class zzef extends zzec {
    protected final byte[] zzb;

    zzef(byte[] arr_b) {
        if(arr_b == null) {
            throw new NullPointerException();
        }
        this.zzb = arr_b;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdv
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof zzdv)) {
            return false;
        }
        if(this.zza() != ((zzdv)object0).zza()) {
            return false;
        }
        if(this.zza() == 0) {
            return true;
        }
        if(object0 instanceof zzef) {
            int v = this.zzd();
            int v1 = ((zzef)object0).zzd();
            return v == 0 || v1 == 0 || v == v1 ? this.zza(((zzef)object0), 0, this.zza()) : false;
        }
        return object0.equals(this);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdv
    public byte zza(int v) {
        return this.zzb[v];
    }

    @Override  // com.google.android.gms.internal.measurement.zzdv
    public int zza() {
        return this.zzb.length;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdv
    protected final int zza(int v, int v1, int v2) {
        return zzfe.zza(v, this.zzb, this.zze(), v2);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdv
    public final zzdv zza(int v, int v1) {
        int v2 = zzef.zzb(0, v1, this.zza());
        return v2 == 0 ? zzdv.zza : new zzdy(this.zzb, this.zze(), v2);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdv
    protected final String zza(Charset charset0) {
        int v = this.zza();
        return new String(this.zzb, this.zze(), v, charset0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdv
    final void zza(zzds zzds0) throws IOException {
        int v = this.zza();
        zzds0.zza(this.zzb, this.zze(), v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzec
    final boolean zza(zzdv zzdv0, int v, int v1) {
        if(v1 > zzdv0.zza()) {
            throw new IllegalArgumentException("Length too large: " + v1 + this.zza());
        }
        if(v1 > zzdv0.zza()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + v1 + ", " + zzdv0.zza());
        }
        if(zzdv0 instanceof zzef) {
            byte[] arr_b = this.zzb;
            byte[] arr_b1 = ((zzef)zzdv0).zzb;
            int v2 = this.zze();
            int v3 = this.zze();
            for(int v4 = ((zzef)zzdv0).zze(); v3 < v2 + v1; ++v4) {
                if(arr_b[v3] != arr_b1[v4]) {
                    return false;
                }
                ++v3;
            }
            return true;
        }
        return zzdv0.zza(0, v1).equals(this.zza(0, v1));
    }

    @Override  // com.google.android.gms.internal.measurement.zzdv
    byte zzb(int v) {
        return this.zzb[v];
    }

    @Override  // com.google.android.gms.internal.measurement.zzdv
    public final boolean zzc() {
        int v = this.zze();
        int v1 = this.zza();
        return zzid.zza(this.zzb, v, v1 + v);
    }

    protected int zze() {
        return 0;
    }
}

