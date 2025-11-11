package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;

final class zzmg implements zzmb, zzmc {
    private zzmu zzadn;
    private zzmb zzbao;
    public final zzmc[] zzbbv;
    private final IdentityHashMap zzbbw;
    private int zzbbx;
    private zzmc[] zzbby;
    private zzmq zzbbz;

    public zzmg(zzmc[] arr_zzmc) {
        this.zzbbv = arr_zzmc;
        this.zzbbw = new IdentityHashMap();
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final long zza(zznd[] arr_zznd, boolean[] arr_z, zzmn[] arr_zzmn, boolean[] arr_z1, long v) {
        int[] arr_v = new int[arr_zznd.length];
        int[] arr_v1 = new int[arr_zznd.length];
        for(int v1 = 0; v1 < arr_zznd.length; ++v1) {
            arr_v[v1] = arr_zzmn[v1] == null ? -1 : ((int)(((Integer)this.zzbbw.get(arr_zzmn[v1]))));
            arr_v1[v1] = -1;
            if(arr_zznd[v1] != null) {
                zzmr zzmr0 = arr_zznd[v1].zzii();
                for(int v2 = 0; true; ++v2) {
                    zzmc[] arr_zzmc = this.zzbbv;
                    if(v2 >= arr_zzmc.length) {
                        break;
                    }
                    if(arr_zzmc[v2].zzhm().zza(zzmr0) != -1) {
                        arr_v1[v1] = v2;
                        break;
                    }
                }
            }
        }
        this.zzbbw.clear();
        zzmn[] arr_zzmn1 = new zzmn[arr_zznd.length];
        zzmn[] arr_zzmn2 = new zzmn[arr_zznd.length];
        zznd[] arr_zznd1 = new zznd[arr_zznd.length];
        ArrayList arrayList0 = new ArrayList(this.zzbbv.length);
        long v3 = v;
        for(int v4 = 0; v4 < this.zzbbv.length; v4 = v6 + 1) {
            for(int v5 = 0; true; ++v5) {
                zznd zznd0 = null;
                if(v5 >= arr_zznd.length) {
                    break;
                }
                arr_zzmn2[v5] = arr_v[v5] == v4 ? arr_zzmn[v5] : null;
                if(arr_v1[v5] == v4) {
                    zznd0 = arr_zznd[v5];
                }
                arr_zznd1[v5] = zznd0;
            }
            int v6 = v4;
            long v7 = this.zzbbv[v4].zza(arr_zznd1, arr_z, arr_zzmn2, arr_z1, v3);
            if(v6 == 0) {
                v3 = v7;
            }
            else if(v7 != v3) {
                throw new IllegalStateException("Children enabled at different positions");
            }
            boolean z = false;
            for(int v8 = 0; true; ++v8) {
                boolean z1 = true;
                if(v8 >= arr_zznd.length) {
                    break;
                }
                if(arr_v1[v8] == v6) {
                    zzob.checkState(arr_zzmn2[v8] != null);
                    arr_zzmn1[v8] = arr_zzmn2[v8];
                    this.zzbbw.put(arr_zzmn2[v8], v6);
                    z = true;
                }
                else if(arr_v[v8] == v6) {
                    if(arr_zzmn2[v8] != null) {
                        z1 = false;
                    }
                    zzob.checkState(z1);
                }
            }
            if(z) {
                arrayList0.add(this.zzbbv[v6]);
            }
        }
        System.arraycopy(arr_zzmn1, 0, arr_zzmn, 0, arr_zzmn1.length);
        this.zzbby = new zzmc[arrayList0.size()];
        arrayList0.toArray(this.zzbby);
        this.zzbbz = new zzlp(this.zzbby);
        return v3;
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final void zza(zzmb zzmb0, long v) {
        this.zzbao = zzmb0;
        zzmc[] arr_zzmc = this.zzbbv;
        this.zzbbx = arr_zzmc.length;
        for(int v1 = 0; v1 < arr_zzmc.length; ++v1) {
            arr_zzmc[v1].zza(this, v);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzmb
    public final void zza(zzmc zzmc0) {
        int v = this.zzbbx - 1;
        this.zzbbx = v;
        if(v > 0) {
            return;
        }
        zzmc[] arr_zzmc = this.zzbbv;
        int v2 = 0;
        for(int v1 = 0; v1 < arr_zzmc.length; ++v1) {
            v2 += arr_zzmc[v1].zzhm().length;
        }
        zzmr[] arr_zzmr = new zzmr[v2];
        zzmc[] arr_zzmc1 = this.zzbbv;
        int v3 = 0;
        for(int v4 = 0; v3 < arr_zzmc1.length; v4 = v6) {
            zzmu zzmu0 = arr_zzmc1[v3].zzhm();
            int v5 = zzmu0.length;
            int v6 = v4;
            int v7 = 0;
            while(v7 < v5) {
                arr_zzmr[v6] = zzmu0.zzaw(v7);
                ++v7;
                ++v6;
            }
            ++v3;
        }
        this.zzadn = new zzmu(arr_zzmr);
        this.zzbao.zza(this);
    }

    @Override  // com.google.android.gms.internal.ads.zzmp
    public final void zza(zzmq zzmq0) {
        if(this.zzadn != null) {
            this.zzbao.zza(this);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final boolean zzee(long v) {
        return this.zzbbz.zzee(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final void zzef(long v) {
        zzmc[] arr_zzmc = this.zzbby;
        for(int v1 = 0; v1 < arr_zzmc.length; ++v1) {
            arr_zzmc[v1].zzef(v);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final long zzeg(long v) {
        long v1 = this.zzbby[0].zzeg(v);
        for(int v2 = 1; true; ++v2) {
            zzmc[] arr_zzmc = this.zzbby;
            if(v2 >= arr_zzmc.length) {
                break;
            }
            if(arr_zzmc[v2].zzeg(v1) != v1) {
                throw new IllegalStateException("Children seeked to different positions");
            }
        }
        return v1;
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final long zzhk() {
        return this.zzbbz.zzhk();
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final void zzhl() throws IOException {
        zzmc[] arr_zzmc = this.zzbbv;
        for(int v = 0; v < arr_zzmc.length; ++v) {
            arr_zzmc[v].zzhl();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final zzmu zzhm() {
        return this.zzadn;
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final long zzhn() {
        long v = this.zzbbv[0].zzhn();
        for(int v1 = 1; true; ++v1) {
            zzmc[] arr_zzmc = this.zzbbv;
            if(v1 >= arr_zzmc.length) {
                break;
            }
            if(arr_zzmc[v1].zzhn() != 0x8000000000000001L) {
                throw new IllegalStateException("Child reported discontinuity");
            }
        }
        if(v != 0x8000000000000001L) {
            zzmc[] arr_zzmc1 = this.zzbby;
            for(int v2 = 0; v2 < arr_zzmc1.length; ++v2) {
                zzmc zzmc0 = arr_zzmc1[v2];
                if(zzmc0 != this.zzbbv[0] && zzmc0.zzeg(v) != v) {
                    throw new IllegalStateException("Children seeked to different positions");
                }
            }
        }
        return v;
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final long zzho() {
        zzmc[] arr_zzmc = this.zzbby;
        long v1 = 0x7FFFFFFFFFFFFFFFL;
        for(int v = 0; v < arr_zzmc.length; ++v) {
            long v2 = arr_zzmc[v].zzho();
            if(v2 != 0x8000000000000000L) {
                v1 = Math.min(v1, v2);
            }
        }
        return v1 == 0x7FFFFFFFFFFFFFFFL ? 0x8000000000000000L : v1;
    }
}

