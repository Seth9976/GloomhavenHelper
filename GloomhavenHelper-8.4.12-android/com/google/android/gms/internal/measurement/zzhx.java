package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

public final class zzhx {
    private static final zzhx zza;
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    static {
        zzhx.zza = new zzhx(0, new int[0], new Object[0], false);
    }

    private zzhx() {
        this(0, new int[8], new Object[8], true);
    }

    private zzhx(int v, int[] arr_v, Object[] arr_object, boolean z) {
        this.zze = -1;
        this.zzb = v;
        this.zzc = arr_v;
        this.zzd = arr_object;
        this.zzf = z;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzhx)) {
            return false;
        }
        int v = this.zzb;
        if(v == ((zzhx)object0).zzb) {
            int[] arr_v = this.zzc;
            int[] arr_v1 = ((zzhx)object0).zzc;
            for(int v1 = 0; true; ++v1) {
                boolean z = true;
                if(v1 >= v) {
                    break;
                }
                if(arr_v[v1] != arr_v1[v1]) {
                    z = false;
                    break;
                }
            }
            if(z) {
                Object[] arr_object = this.zzd;
                Object[] arr_object1 = ((zzhx)object0).zzd;
                int v2 = this.zzb;
                for(int v3 = 0; v3 < v2; ++v3) {
                    if(!arr_object[v3].equals(arr_object1[v3])) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public final int hashCode() {
        int v = this.zzb;
        int[] arr_v = this.zzc;
        int v2 = 17;
        int v4 = 17;
        for(int v3 = 0; v3 < v; ++v3) {
            v4 = v4 * 0x1F + arr_v[v3];
        }
        Object[] arr_object = this.zzd;
        int v5 = this.zzb;
        for(int v1 = 0; v1 < v5; ++v1) {
            v2 = v2 * 0x1F + arr_object[v1].hashCode();
        }
        return ((v + 0x20F) * 0x1F + v4) * 0x1F + v2;
    }

    public static zzhx zza() {
        return zzhx.zza;
    }

    static zzhx zza(zzhx zzhx0, zzhx zzhx1) {
        int v = zzhx0.zzb + zzhx1.zzb;
        int[] arr_v = Arrays.copyOf(zzhx0.zzc, v);
        System.arraycopy(zzhx1.zzc, 0, arr_v, zzhx0.zzb, zzhx1.zzb);
        Object[] arr_object = Arrays.copyOf(zzhx0.zzd, v);
        System.arraycopy(zzhx1.zzd, 0, arr_object, zzhx0.zzb, zzhx1.zzb);
        return new zzhx(v, arr_v, arr_object, true);
    }

    private static void zza(int v, Object object0, zzir zzir0) throws IOException {
        if((v & 7) != 5) {
            switch(v & 7) {
                case 0: {
                    zzir0.zza(v >>> 3, ((long)(((Long)object0))));
                    return;
                }
                case 1: {
                    zzir0.zzd(v >>> 3, ((long)(((Long)object0))));
                    return;
                }
                case 2: {
                    zzir0.zza(v >>> 3, ((zzdv)object0));
                    return;
                }
                case 3: {
                    if(zzir0.zza() == zzd.zzj) {
                        zzir0.zza(v >>> 3);
                        ((zzhx)object0).zzb(zzir0);
                        zzir0.zzb(v >>> 3);
                        return;
                    }
                    zzir0.zzb(v >>> 3);
                    ((zzhx)object0).zzb(zzir0);
                    zzir0.zza(v >>> 3);
                    return;
                }
                default: {
                    throw new RuntimeException(zzfn.zzf());
                }
            }
        }
        zzir0.zzd(v >>> 3, ((int)(((Integer)object0))));
    }

    final void zza(int v, Object object0) {
        if(!this.zzf) {
            throw new UnsupportedOperationException();
        }
        int v1 = this.zzb;
        if(v1 == this.zzc.length) {
            int v2 = this.zzb + (v1 >= 4 ? v1 >> 1 : 8);
            this.zzc = Arrays.copyOf(this.zzc, v2);
            this.zzd = Arrays.copyOf(this.zzd, v2);
        }
        int v3 = this.zzb;
        this.zzc[v3] = v;
        this.zzd[v3] = object0;
        this.zzb = v3 + 1;
    }

    final void zza(zzir zzir0) throws IOException {
        if(zzir0.zza() == zzd.zzk) {
            for(int v = this.zzb - 1; v >= 0; --v) {
                zzir0.zza(this.zzc[v] >>> 3, this.zzd[v]);
            }
            return;
        }
        for(int v1 = 0; v1 < this.zzb; ++v1) {
            zzir0.zza(this.zzc[v1] >>> 3, this.zzd[v1]);
        }
    }

    final void zza(StringBuilder stringBuilder0, int v) {
        for(int v1 = 0; v1 < this.zzb; ++v1) {
            zzgo.zza(stringBuilder0, v, String.valueOf(this.zzc[v1] >>> 3), this.zzd[v1]);
        }
    }

    static zzhx zzb() {
        return new zzhx();
    }

    public final void zzb(zzir zzir0) throws IOException {
        if(this.zzb == 0) {
            return;
        }
        if(zzir0.zza() == zzd.zzj) {
            for(int v = 0; v < this.zzb; ++v) {
                zzhx.zza(this.zzc[v], this.zzd[v], zzir0);
            }
            return;
        }
        for(int v1 = this.zzb - 1; v1 >= 0; --v1) {
            zzhx.zza(this.zzc[v1], this.zzd[v1], zzir0);
        }
    }

    public final void zzc() {
        this.zzf = false;
    }

    public final int zzd() {
        int v = this.zze;
        if(v != -1) {
            return v;
        }
        int v2 = 0;
        for(int v1 = 0; v1 < this.zzb; ++v1) {
            v2 += zzek.zzd(this.zzc[v1] >>> 3, ((zzdv)this.zzd[v1]));
        }
        this.zze = v2;
        return v2;
    }

    public final int zze() {
        int v = this.zze;
        if(v != -1) {
            return v;
        }
        int v2 = 0;
        for(int v1 = 0; v1 < this.zzb; ++v1) {
            int v3 = this.zzc[v1];
            int v4 = v3 >>> 3;
            int v5 = v3 & 7;
            if(v5 == 5) {
                v2 += zzek.zzi(v4, ((int)(((Integer)this.zzd[v1]))));
            }
            else {
                switch(v5) {
                    case 0: {
                        v2 += zzek.zze(v4, ((long)(((Long)this.zzd[v1]))));
                        break;
                    }
                    case 1: {
                        v2 += zzek.zzg(v4, ((long)(((Long)this.zzd[v1]))));
                        break;
                    }
                    case 2: {
                        v2 += zzek.zzc(v4, ((zzdv)this.zzd[v1]));
                        break;
                    }
                    case 3: {
                        v2 += (zzek.zze(v4) << 1) + ((zzhx)this.zzd[v1]).zze();
                        break;
                    }
                    default: {
                        throw new IllegalStateException(zzfn.zzf());
                    }
                }
            }
        }
        this.zze = v2;
        return v2;
    }
}

