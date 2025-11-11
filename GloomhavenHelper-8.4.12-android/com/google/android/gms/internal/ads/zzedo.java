package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzedo extends zzect {
    public String url;
    public zzg zziee;
    private zzc zzief;
    public String zzieg;
    private String zzieh;
    public zza zziei;
    public zzedp[] zziej;
    public String zziek;
    public zzedq zziel;
    private Boolean zziem;
    private String[] zzien;
    private String zzieo;
    private Boolean zziep;
    private Boolean zzieq;
    private byte[] zzier;
    public zzi zzies;
    public String[] zziet;
    public String[] zzieu;

    public zzedo() {
        this.zziee = null;
        this.zzief = null;
        this.url = null;
        this.zzieg = null;
        this.zzieh = null;
        this.zziei = null;
        this.zziej = zzedp.zzbgh();
        this.zziek = null;
        this.zziel = null;
        this.zziem = null;
        this.zzien = zzedb.zziak;
        this.zzieo = null;
        this.zziep = null;
        this.zzieq = null;
        this.zzier = null;
        this.zzies = null;
        this.zziet = zzedb.zziak;
        this.zzieu = zzedb.zziak;
        this.zzhzu = null;
        this.zzhnu = -1;
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    public final void zza(zzecr zzecr0) throws IOException {
        String s = this.url;
        if(s != null) {
            zzecr0.zzf(1, s);
        }
        String s1 = this.zzieg;
        if(s1 != null) {
            zzecr0.zzf(2, s1);
        }
        if(this.zziej != null && this.zziej.length > 0) {
            for(int v1 = 0; true; ++v1) {
                zzedp[] arr_zzedp = this.zziej;
                if(v1 >= arr_zzedp.length) {
                    break;
                }
                zzedp zzedp0 = arr_zzedp[v1];
                if(zzedp0 != null) {
                    zzecr0.zza(4, zzedp0);
                }
            }
        }
        if(this.zzien != null && this.zzien.length > 0) {
            for(int v2 = 0; true; ++v2) {
                String[] arr_s = this.zzien;
                if(v2 >= arr_s.length) {
                    break;
                }
                String s2 = arr_s[v2];
                if(s2 != null) {
                    zzecr0.zzf(6, s2);
                }
            }
        }
        zzg zzede$zzb$zzg0 = this.zziee;
        if(zzede$zzb$zzg0 != null) {
            zzecr0.zzac(10, zzede$zzb$zzg0.zzaf());
        }
        zza zzede$zzb$zza0 = this.zziei;
        if(zzede$zzb$zza0 != null) {
            zzecr0.zze(12, zzede$zzb$zza0);
        }
        String s3 = this.zziek;
        if(s3 != null) {
            zzecr0.zzf(13, s3);
        }
        zzedq zzedq0 = this.zziel;
        if(zzedq0 != null) {
            zzecr0.zza(14, zzedq0);
        }
        zzi zzede$zzb$zzi0 = this.zzies;
        if(zzede$zzb$zzi0 != null) {
            zzecr0.zze(17, zzede$zzb$zzi0);
        }
        if(this.zziet != null && this.zziet.length > 0) {
            for(int v3 = 0; true; ++v3) {
                String[] arr_s1 = this.zziet;
                if(v3 >= arr_s1.length) {
                    break;
                }
                String s4 = arr_s1[v3];
                if(s4 != null) {
                    zzecr0.zzf(20, s4);
                }
            }
        }
        if(this.zzieu != null && this.zzieu.length > 0) {
            for(int v = 0; true; ++v) {
                String[] arr_s2 = this.zzieu;
                if(v >= arr_s2.length) {
                    break;
                }
                String s5 = arr_s2[v];
                if(s5 != null) {
                    zzecr0.zzf(21, s5);
                }
            }
        }
        super.zza(zzecr0);
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    protected final int zzon() {
        int v = super.zzon();
        String s = this.url;
        if(s != null) {
            v += zzecr.zzg(1, s);
        }
        String s1 = this.zzieg;
        if(s1 != null) {
            v += zzecr.zzg(2, s1);
        }
        if(this.zziej != null && this.zziej.length > 0) {
            int v2 = v;
            for(int v3 = 0; true; ++v3) {
                zzedp[] arr_zzedp = this.zziej;
                if(v3 >= arr_zzedp.length) {
                    break;
                }
                zzedp zzedp0 = arr_zzedp[v3];
                if(zzedp0 != null) {
                    v2 += zzecr.zzb(4, zzedp0);
                }
            }
            v = v2;
        }
        if(this.zzien != null && this.zzien.length > 0) {
            int v5 = 0;
            int v6 = 0;
            for(int v4 = 0; true; ++v4) {
                String[] arr_s = this.zzien;
                if(v4 >= arr_s.length) {
                    break;
                }
                String s2 = arr_s[v4];
                if(s2 != null) {
                    ++v6;
                    v5 += zzecr.zzhk(s2);
                }
            }
            v = v + v5 + v6;
        }
        zzg zzede$zzb$zzg0 = this.zziee;
        if(zzede$zzb$zzg0 != null) {
            v += zzecr.zzag(10, zzede$zzb$zzg0.zzaf());
        }
        zza zzede$zzb$zza0 = this.zziei;
        if(zzede$zzb$zza0 != null) {
            v += zzdyi.zzc(12, zzede$zzb$zza0);
        }
        String s3 = this.zziek;
        if(s3 != null) {
            v += zzecr.zzg(13, s3);
        }
        zzedq zzedq0 = this.zziel;
        if(zzedq0 != null) {
            v += zzecr.zzb(14, zzedq0);
        }
        zzi zzede$zzb$zzi0 = this.zzies;
        if(zzede$zzb$zzi0 != null) {
            v += zzdyi.zzc(17, zzede$zzb$zzi0);
        }
        if(this.zziet != null && this.zziet.length > 0) {
            int v8 = 0;
            int v9 = 0;
            for(int v7 = 0; true; ++v7) {
                String[] arr_s1 = this.zziet;
                if(v7 >= arr_s1.length) {
                    break;
                }
                String s4 = arr_s1[v7];
                if(s4 != null) {
                    ++v9;
                    v8 += zzecr.zzhk(s4);
                }
            }
            v = v + v8 + v9 * 2;
        }
        if(this.zzieu != null && this.zzieu.length > 0) {
            int v10 = 0;
            int v11 = 0;
            for(int v1 = 0; true; ++v1) {
                String[] arr_s2 = this.zzieu;
                if(v1 >= arr_s2.length) {
                    break;
                }
                String s5 = arr_s2[v1];
                if(s5 != null) {
                    ++v11;
                    v10 += zzecr.zzhk(s5);
                }
            }
            return v + v10 + v11 * 2;
        }
        return v;
    }
}

