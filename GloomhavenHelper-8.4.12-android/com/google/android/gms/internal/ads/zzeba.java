package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;

final class zzeba extends zzdxn {
    static final int[] zzhwf;
    private final int zzhwg;
    private final zzdxn zzhwh;
    private final zzdxn zzhwi;
    private final int zzhwj;
    private final int zzhwk;

    static {
        zzeba.zzhwf = new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 0x90, 0xE9, 377, 610, 987, 0x63D, 0xA18, 0x1055, 6765, 10946, 0x452F, 0x6FF1, 0xB520, 75025, 0x1DA31, 0x2FF42, 0x4D973, 0x7D8B5, 832040, 0x148ADD, 0x213D05, 0x35C7E2, 0x5704E7, 0x8CCCC9, 0xE3D1B0, 0x1709E79, 0x2547029, 0x3C50EA2, 102334155, 165580141, 0xFF80C38, 0x19D699A5, 701408733, 1134903170, 0x6D73E55F, 0x7FFFFFFF};
    }

    private zzeba(zzdxn zzdxn0, zzdxn zzdxn1) {
        this.zzhwh = zzdxn0;
        this.zzhwi = zzdxn1;
        this.zzhwj = zzdxn0.size();
        this.zzhwg = this.zzhwj + zzdxn1.size();
        this.zzhwk = Math.max(zzdxn0.zzbav(), zzdxn1.zzbav()) + 1;
    }

    zzeba(zzdxn zzdxn0, zzdxn zzdxn1, zzeaz zzeaz0) {
        this(zzdxn0, zzdxn1);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof zzdxn)) {
            return false;
        }
        if(this.zzhwg != ((zzdxn)object0).size()) {
            return false;
        }
        if(this.zzhwg == 0) {
            return true;
        }
        int v = this.zzbax();
        int v1 = ((zzdxn)object0).zzbax();
        if(v != 0 && v1 != 0 && v != v1) {
            return false;
        }
        zzebb zzebb0 = new zzebb(this, null);
        Object object1 = zzebb0.next();
        zzdxy zzdxy0 = (zzdxy)object1;
        zzebb zzebb1 = new zzebb(((zzdxn)object0), null);
        Object object2 = zzebb1.next();
        zzdxy zzdxy1 = (zzdxy)object2;
        int v2 = 0;
        int v3 = 0;
        int v4 = 0;
        while(true) {
            int v5 = zzdxy0.size() - v2;
            int v6 = zzdxy1.size() - v3;
            int v7 = Math.min(v5, v6);
            if(!(v2 == 0 ? zzdxy0.zza(zzdxy1, v3, v7) : zzdxy1.zza(zzdxy0, v2, v7))) {
                return false;
            }
            v4 += v7;
            int v8 = this.zzhwg;
            if(v4 >= v8) {
                if(v4 != v8) {
                    throw new IllegalStateException();
                }
                return true;
            }
            if(v7 == v5) {
                Object object3 = zzebb0.next();
                zzdxy0 = (zzdxy)object3;
                v2 = 0;
            }
            else {
                v2 += v7;
            }
            if(v7 == v6) {
                Object object4 = zzebb1.next();
                zzdxy1 = (zzdxy)object4;
                v3 = 0;
            }
            else {
                v3 += v7;
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    public final Iterator iterator() {
        return this.zzbar();
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    public final int size() {
        return this.zzhwg;
    }

    static zzdxn zza(zzdxn zzdxn0, zzdxn zzdxn1) {
        if(zzdxn1.size() == 0) {
            return zzdxn0;
        }
        if(zzdxn0.size() == 0) {
            return zzdxn1;
        }
        int v = zzdxn0.size() + zzdxn1.size();
        if(v < 0x80) {
            return zzeba.zzb(zzdxn0, zzdxn1);
        }
        if(zzdxn0 instanceof zzeba) {
            if(((zzeba)zzdxn0).zzhwi.size() + zzdxn1.size() < 0x80) {
                zzdxn zzdxn2 = zzeba.zzb(((zzeba)zzdxn0).zzhwi, zzdxn1);
                return new zzeba(((zzeba)zzdxn0).zzhwh, zzdxn2);
            }
            if(((zzeba)zzdxn0).zzhwh.zzbav() > ((zzeba)zzdxn0).zzhwi.zzbav() && ((zzeba)zzdxn0).zzbav() > zzdxn1.zzbav()) {
                zzeba zzeba0 = new zzeba(((zzeba)zzdxn0).zzhwi, zzdxn1);
                return new zzeba(((zzeba)zzdxn0).zzhwh, zzeba0);
            }
        }
        return v >= zzeba.zzgu(Math.max(zzdxn0.zzbav(), zzdxn1.zzbav()) + 1) ? new zzeba(zzdxn0, zzdxn1) : new zzebc(null).zzc(zzdxn0, zzdxn1);
    }

    static zzdxn zza(zzeba zzeba0) {
        return zzeba0.zzhwh;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    protected final String zza(Charset charset0) {
        return new String(this.toByteArray(), charset0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    final void zza(zzdxo zzdxo0) throws IOException {
        this.zzhwh.zza(zzdxo0);
        this.zzhwi.zza(zzdxo0);
    }

    private static zzdxn zzb(zzdxn zzdxn0, zzdxn zzdxn1) {
        int v = zzdxn0.size();
        int v1 = zzdxn1.size();
        byte[] arr_b = new byte[v + v1];
        zzdxn0.zza(arr_b, 0, 0, v);
        zzdxn1.zza(arr_b, 0, v, v1);
        return zzdxn.zzu(arr_b);
    }

    static zzdxn zzb(zzeba zzeba0) {
        return zzeba0.zzhwi;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    protected final void zzb(byte[] arr_b, int v, int v1, int v2) {
        int v3 = this.zzhwj;
        if(v + v2 <= v3) {
            this.zzhwh.zzb(arr_b, v, v1, v2);
            return;
        }
        if(v >= v3) {
            this.zzhwi.zzb(arr_b, v - v3, v1, v2);
            return;
        }
        int v4 = v3 - v;
        this.zzhwh.zzb(arr_b, v, v1, v4);
        this.zzhwi.zzb(arr_b, 0, v1 + v4, v2 - v4);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    public final zzdxw zzbar() {
        return new zzeaz(this);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    public final boolean zzbat() {
        int v = this.zzhwh.zzg(0, 0, this.zzhwj);
        return this.zzhwi.zzg(v, 0, this.zzhwi.size()) == 0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    public final zzdxz zzbau() {
        return new zzdye(new zzebe(this), 0x1000, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    protected final int zzbav() {
        return this.zzhwk;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    protected final boolean zzbaw() {
        int v = zzeba.zzgu(this.zzhwk);
        return this.zzhwg >= v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    public final byte zzfe(int v) {
        zzeba.zzaa(v, this.zzhwg);
        return this.zzff(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    final byte zzff(int v) {
        return v >= this.zzhwj ? this.zzhwi.zzff(v - this.zzhwj) : this.zzhwh.zzff(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    protected final int zzg(int v, int v1, int v2) {
        int v3 = this.zzhwj;
        if(v1 + v2 <= v3) {
            return this.zzhwh.zzg(v, v1, v2);
        }
        if(v1 >= v3) {
            return this.zzhwi.zzg(v, v1 - v3, v2);
        }
        int v4 = v3 - v1;
        int v5 = this.zzhwh.zzg(v, v1, v4);
        return this.zzhwi.zzg(v5, 0, v2 - v4);
    }

    static int zzgu(int v) {
        return v < zzeba.zzhwf.length ? zzeba.zzhwf[v] : 0x7FFFFFFF;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    protected final int zzh(int v, int v1, int v2) {
        int v3 = this.zzhwj;
        if(v1 + v2 <= v3) {
            return this.zzhwh.zzh(v, v1, v2);
        }
        if(v1 >= v3) {
            return this.zzhwi.zzh(v, v1 - v3, v2);
        }
        int v4 = v3 - v1;
        int v5 = this.zzhwh.zzh(v, v1, v4);
        return this.zzhwi.zzh(v5, 0, v2 - v4);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxn
    public final zzdxn zzz(int v, int v1) {
        int v2 = zzeba.zzi(v, v1, this.zzhwg);
        if(v2 == 0) {
            return zzdxn.zzhoe;
        }
        if(v2 == this.zzhwg) {
            return this;
        }
        int v3 = this.zzhwj;
        if(v1 <= v3) {
            return this.zzhwh.zzz(v, v1);
        }
        return v >= v3 ? this.zzhwi.zzz(v - v3, v1 - v3) : new zzeba(this.zzhwh.zzz(v, this.zzhwh.size()), this.zzhwi.zzz(0, v1 - this.zzhwj));
    }
}

