package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Arrays;

public final class zzeby {
    private int count;
    private boolean zzhnt;
    private int zzhsx;
    private Object[] zzhvg;
    private static final zzeby zzhxl;
    private int[] zzhxm;

    static {
        zzeby.zzhxl = new zzeby(0, new int[0], new Object[0], false);
    }

    private zzeby() {
        this(0, new int[8], new Object[8], true);
    }

    private zzeby(int v, int[] arr_v, Object[] arr_object, boolean z) {
        this.zzhsx = -1;
        this.count = v;
        this.zzhxm = arr_v;
        this.zzhvg = arr_object;
        this.zzhnt = z;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzeby)) {
            return false;
        }
        int v = this.count;
        if(v == ((zzeby)object0).count) {
            int[] arr_v = this.zzhxm;
            int[] arr_v1 = ((zzeby)object0).zzhxm;
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
                Object[] arr_object = this.zzhvg;
                Object[] arr_object1 = ((zzeby)object0).zzhvg;
                int v2 = this.count;
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
        int v = this.count;
        int[] arr_v = this.zzhxm;
        int v2 = 17;
        int v4 = 17;
        for(int v3 = 0; v3 < v; ++v3) {
            v4 = v4 * 0x1F + arr_v[v3];
        }
        Object[] arr_object = this.zzhvg;
        int v5 = this.count;
        for(int v1 = 0; v1 < v5; ++v1) {
            v2 = v2 * 0x1F + arr_object[v1].hashCode();
        }
        return ((v + 0x20F) * 0x1F + v4) * 0x1F + v2;
    }

    static zzeby zza(zzeby zzeby0, zzeby zzeby1) {
        int v = zzeby0.count + zzeby1.count;
        int[] arr_v = Arrays.copyOf(zzeby0.zzhxm, v);
        System.arraycopy(zzeby1.zzhxm, 0, arr_v, zzeby0.count, zzeby1.count);
        Object[] arr_object = Arrays.copyOf(zzeby0.zzhvg, v);
        System.arraycopy(zzeby1.zzhvg, 0, arr_object, zzeby0.count, zzeby1.count);
        return new zzeby(v, arr_v, arr_object, true);
    }

    final void zza(zzecs zzecs0) throws IOException {
        if(zzecs0.zzbcf() == zzf.zzhto) {
            for(int v = this.count - 1; v >= 0; --v) {
                zzecs0.zzc(this.zzhxm[v] >>> 3, this.zzhvg[v]);
            }
            return;
        }
        for(int v1 = 0; v1 < this.count; ++v1) {
            zzecs0.zzc(this.zzhxm[v1] >>> 3, this.zzhvg[v1]);
        }
    }

    final void zza(StringBuilder stringBuilder0, int v) {
        for(int v1 = 0; v1 < this.count; ++v1) {
            zzeam.zza(stringBuilder0, v, String.valueOf(this.zzhxm[v1] >>> 3), this.zzhvg[v1]);
        }
    }

    private static void zzb(int v, Object object0, zzecs zzecs0) throws IOException {
        if((v & 7) != 5) {
            switch(v & 7) {
                case 0: {
                    zzecs0.zzp(v >>> 3, ((long)(((Long)object0))));
                    return;
                }
                case 1: {
                    zzecs0.zzj(v >>> 3, ((long)(((Long)object0))));
                    return;
                }
                case 2: {
                    zzecs0.zza(v >>> 3, ((zzdxn)object0));
                    return;
                }
                case 3: {
                    if(zzecs0.zzbcf() == zzf.zzhtn) {
                        zzecs0.zzgi(v >>> 3);
                        ((zzeby)object0).zzb(zzecs0);
                        zzecs0.zzgj(v >>> 3);
                        return;
                    }
                    zzecs0.zzgj(v >>> 3);
                    ((zzeby)object0).zzb(zzecs0);
                    zzecs0.zzgi(v >>> 3);
                    return;
                }
                default: {
                    throw new RuntimeException(zzdzh.zzbdn());
                }
            }
        }
        zzecs0.zzaf(v >>> 3, ((int)(((Integer)object0))));
    }

    public final void zzb(zzecs zzecs0) throws IOException {
        if(this.count == 0) {
            return;
        }
        if(zzecs0.zzbcf() == zzf.zzhtn) {
            for(int v = 0; v < this.count; ++v) {
                zzeby.zzb(this.zzhxm[v], this.zzhvg[v], zzecs0);
            }
            return;
        }
        for(int v1 = this.count - 1; v1 >= 0; --v1) {
            zzeby.zzb(this.zzhxm[v1], this.zzhvg[v1], zzecs0);
        }
    }

    public final void zzban() {
        this.zzhnt = false;
    }

    public final int zzbda() {
        int v = this.zzhsx;
        if(v != -1) {
            return v;
        }
        int v2 = 0;
        for(int v1 = 0; v1 < this.count; ++v1) {
            int v3 = this.zzhxm[v1];
            int v4 = v3 >>> 3;
            int v5 = v3 & 7;
            if(v5 == 5) {
                v2 += zzdyi.zzaj(v4, ((int)(((Integer)this.zzhvg[v1]))));
            }
            else {
                switch(v5) {
                    case 0: {
                        v2 += zzdyi.zzl(v4, ((long)(((Long)this.zzhvg[v1]))));
                        break;
                    }
                    case 1: {
                        v2 += zzdyi.zzn(v4, ((long)(((Long)this.zzhvg[v1]))));
                        break;
                    }
                    case 2: {
                        v2 += zzdyi.zzc(v4, ((zzdxn)this.zzhvg[v1]));
                        break;
                    }
                    case 3: {
                        v2 += (zzdyi.zzfz(v4) << 1) + ((zzeby)this.zzhvg[v1]).zzbda();
                        break;
                    }
                    default: {
                        throw new IllegalStateException(zzdzh.zzbdn());
                    }
                }
            }
        }
        this.zzhsx = v2;
        return v2;
    }

    public static zzeby zzbff() {
        return zzeby.zzhxl;
    }

    static zzeby zzbfg() {
        return new zzeby();
    }

    public final int zzbfh() {
        int v = this.zzhsx;
        if(v != -1) {
            return v;
        }
        int v2 = 0;
        for(int v1 = 0; v1 < this.count; ++v1) {
            v2 += zzdyi.zzd(this.zzhxm[v1] >>> 3, ((zzdxn)this.zzhvg[v1]));
        }
        this.zzhsx = v2;
        return v2;
    }

    final void zzd(int v, Object object0) {
        if(!this.zzhnt) {
            throw new UnsupportedOperationException();
        }
        int v1 = this.count;
        if(v1 == this.zzhxm.length) {
            int v2 = this.count + (v1 >= 4 ? v1 >> 1 : 8);
            this.zzhxm = Arrays.copyOf(this.zzhxm, v2);
            this.zzhvg = Arrays.copyOf(this.zzhvg, v2);
        }
        int v3 = this.count;
        this.zzhxm[v3] = v;
        this.zzhvg[v3] = object0;
        this.count = v3 + 1;
    }
}

