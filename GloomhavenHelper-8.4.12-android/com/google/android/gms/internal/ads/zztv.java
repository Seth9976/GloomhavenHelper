package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zztv extends zzect {
    private Integer zzcbd;
    public String zzcbe;
    private Integer zzcbf;
    private zztf zzcbg;
    private zztu zzcbh;
    public long[] zzcbi;
    public zztt zzcbj;
    private zzts zzcbk;
    private zzh zzcbl;
    public zztr zzcbm;
    public zzj zzcbn;
    public zzw zzcbo;
    public zza zzcbp;

    public zztv() {
        this.zzcbd = null;
        this.zzcbe = null;
        this.zzcbf = null;
        this.zzcbg = null;
        this.zzcbh = null;
        this.zzcbi = zzedb.zziag;
        this.zzcbj = null;
        this.zzcbk = null;
        this.zzcbl = null;
        this.zzcbm = null;
        this.zzcbn = null;
        this.zzcbo = null;
        this.zzcbp = null;
        this.zzhzu = null;
        this.zzhnu = -1;
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    public final void zza(zzecr zzecr0) throws IOException {
        String s = this.zzcbe;
        if(s != null) {
            zzecr0.zzf(10, s);
        }
        if(this.zzcbi != null && this.zzcbi.length > 0) {
            for(int v = 0; true; ++v) {
                long[] arr_v = this.zzcbi;
                if(v >= arr_v.length) {
                    break;
                }
                long v1 = arr_v[v];
                zzecr0.zzab(14, 0);
                zzecr0.zzft(v1);
            }
        }
        zztt zztt0 = this.zzcbj;
        if(zztt0 != null) {
            zzecr0.zza(15, zztt0);
        }
        zztr zztr0 = this.zzcbm;
        if(zztr0 != null) {
            zzecr0.zza(18, zztr0);
        }
        zzj zzsz$zzj0 = this.zzcbn;
        if(zzsz$zzj0 != null) {
            zzecr0.zze(19, zzsz$zzj0);
        }
        zzw zzsz$zzw0 = this.zzcbo;
        if(zzsz$zzw0 != null) {
            zzecr0.zze(20, zzsz$zzw0);
        }
        zza zzsz$zza0 = this.zzcbp;
        if(zzsz$zza0 != null) {
            zzecr0.zze(21, zzsz$zza0);
        }
        super.zza(zzecr0);
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    protected final int zzon() {
        int v4;
        long[] arr_v;
        int v = super.zzon();
        String s = this.zzcbe;
        if(s != null) {
            v += zzecr.zzg(10, s);
        }
        if(this.zzcbi != null && this.zzcbi.length > 0) {
            int v2 = 0;
            for(int v1 = 0; true; ++v1) {
                arr_v = this.zzcbi;
                if(v1 >= arr_v.length) {
                    break;
                }
                long v3 = arr_v[v1];
                if((0xFFFFFFFFFFFFFF80L & v3) == 0L) {
                    v4 = 1;
                }
                else if((0xFFFFFFFFFFFFC000L & v3) == 0L) {
                    v4 = 2;
                }
                else if((0xFFFFFFFFFFE00000L & v3) == 0L) {
                    v4 = 3;
                }
                else if((0xFFFFFFFFF0000000L & v3) == 0L) {
                    v4 = 4;
                }
                else if((0xFFFFFFF800000000L & v3) == 0L) {
                    v4 = 5;
                }
                else if((0xFFFFFC0000000000L & v3) == 0L) {
                    v4 = 6;
                }
                else if((0xFFFE000000000000L & v3) == 0L) {
                    v4 = 7;
                }
                else if((0xFF00000000000000L & v3) == 0L) {
                    v4 = 8;
                }
                else {
                    v4 = (0x8000000000000000L & v3) == 0L ? 9 : 10;
                }
                v2 += v4;
            }
            v = v + v2 + arr_v.length;
        }
        zztt zztt0 = this.zzcbj;
        if(zztt0 != null) {
            v += zzecr.zzb(15, zztt0);
        }
        zztr zztr0 = this.zzcbm;
        if(zztr0 != null) {
            v += zzecr.zzb(18, zztr0);
        }
        zzj zzsz$zzj0 = this.zzcbn;
        if(zzsz$zzj0 != null) {
            v += zzdyi.zzc(19, zzsz$zzj0);
        }
        zzw zzsz$zzw0 = this.zzcbo;
        if(zzsz$zzw0 != null) {
            v += zzdyi.zzc(20, zzsz$zzw0);
        }
        return this.zzcbp == null ? v : v + zzdyi.zzc(21, this.zzcbp);
    }
}

