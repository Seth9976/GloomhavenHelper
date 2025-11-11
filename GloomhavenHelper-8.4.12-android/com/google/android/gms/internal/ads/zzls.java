package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.util.SparseArray;
import java.io.IOException;

final class zzls implements zzji, zzmc, zzmo, zzny {
    private final Uri uri;
    private final zzdkp zzada;
    private final zzdkp zzadx;
    private boolean zzaee;
    private boolean zzafa;
    private long zzagy;
    private final zzno zzamy;
    private final int zzbac;
    private final zzlz zzbad;
    private final zzmd zzbae;
    private final zznm zzbaf;
    private final String zzbag;
    private final long zzbah;
    private final zznv zzbai;
    private final zzly zzbaj;
    private final zzod zzbak;
    private final Runnable zzbal;
    private final Runnable zzbam;
    private final SparseArray zzban;
    private zzmb zzbao;
    private zzjl zzbap;
    private boolean zzbaq;
    private boolean zzbar;
    private boolean zzbas;
    private int zzbat;
    private zzmu zzbau;
    private boolean[] zzbav;
    private boolean[] zzbaw;
    private boolean zzbax;
    private long zzbay;
    private long zzbaz;
    private int zzbba;
    private boolean zzbbb;
    private long zzce;

    public zzls(Uri uri0, zzno zzno0, zzjg[] arr_zzjg, int v, zzdkp zzdkp0, zzlz zzlz0, zzmd zzmd0, zznm zznm0, String s, int v1) {
        this.uri = uri0;
        this.zzamy = zzno0;
        this.zzbac = v;
        this.zzada = zzdkp0;
        this.zzbad = zzlz0;
        this.zzbae = zzmd0;
        this.zzbaf = zznm0;
        this.zzbag = s;
        this.zzbah = (long)v1;
        this.zzbai = new zznv("Loader:ExtractorMediaPeriod");
        this.zzbaj = new zzly(arr_zzjg, this);
        this.zzbak = new zzod();
        this.zzbal = new zzlr(this);
        this.zzbam = new zzlu(this);
        this.zzadx = new zzdkp();
        this.zzbaz = 0x8000000000000001L;
        this.zzban = new SparseArray();
        this.zzce = -1L;
    }

    public final void release() {
        zzlt zzlt0 = new zzlt(this, this.zzbaj);
        this.zzbai.zza(zzlt0);
        this.zzadx.removeCallbacksAndMessages(null);
        this.zzaee = true;
    }

    private final void startLoading() {
        zzlv zzlv0 = new zzlv(this, this.uri, this.zzamy, this.zzbaj, this.zzbak);
        if(this.zzafa) {
            zzob.checkState(this.zzht());
            if(this.zzagy != 0x8000000000000001L && this.zzbaz >= this.zzagy) {
                this.zzbbb = true;
                this.zzbaz = 0x8000000000000001L;
                return;
            }
            zzlv0.zze(this.zzbap.zzdz(this.zzbaz), this.zzbaz);
            this.zzbaz = 0x8000000000000001L;
        }
        this.zzbba = this.zzhr();
        int v = this.zzbac;
        if(v == -1) {
            v = !this.zzafa || this.zzce != -1L || this.zzbap != null && this.zzbap.getDurationUs() != 0x8000000000000001L ? 3 : 6;
        }
        this.zzbai.zza(zzlv0, this, v);
    }

    static void zza(zzls zzls0) {
        zzls0.zzhq();
    }

    private final void zza(zzlv zzlv0) {
        if(this.zzce == -1L) {
            this.zzce = zzlv0.zzce;
        }
    }

    // 去混淆评级： 低(20)
    final int zza(int v, zzhb zzhb0, zziv zziv0, boolean z) {
        return this.zzbas || this.zzht() ? -3 : ((zzmm)this.zzban.valueAt(v)).zza(zzhb0, zziv0, z, this.zzbbb, this.zzbay);
    }

    @Override  // com.google.android.gms.internal.ads.zzny
    public final int zza(zzoa zzoa0, long v, long v1, IOException iOException0) {
        this.zza(((zzlv)zzoa0));
        zzdkp zzdkp0 = this.zzada;
        if(zzdkp0 != null && this.zzbad != null) {
            zzdkp0.post(new zzlw(this, iOException0));
        }
        if(iOException0 instanceof zzmt) {
            return 3;
        }
        boolean z = this.zzhr() > this.zzbba;
        if(this.zzce == -1L && (this.zzbap == null || this.zzbap.getDurationUs() == 0x8000000000000001L)) {
            this.zzbay = 0L;
            this.zzbas = this.zzafa;
            int v2 = this.zzban.size();
            for(int v3 = 0; v3 < v2; ++v3) {
                ((zzmm)this.zzban.valueAt(v3)).zzk(!this.zzafa || this.zzbav[v3]);
            }
            ((zzlv)zzoa0).zze(0L, 0L);
        }
        this.zzbba = this.zzhr();
        return z ? 1 : 0;
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final long zza(zznd[] arr_zznd, boolean[] arr_z, zzmn[] arr_zzmn, boolean[] arr_z1, long v) {
        zzob.checkState(this.zzafa);
        for(int v2 = 0; v2 < arr_zznd.length; ++v2) {
            if(arr_zzmn[v2] != null && (arr_zznd[v2] == null || !arr_z[v2])) {
                int v3 = ((zzlx)arr_zzmn[v2]).track;
                zzob.checkState(this.zzbav[v3]);
                --this.zzbat;
                this.zzbav[v3] = false;
                ((zzmm)this.zzban.valueAt(v3)).disable();
                arr_zzmn[v2] = null;
            }
        }
        boolean z = false;
        for(int v4 = 0; v4 < arr_zznd.length; ++v4) {
            if(arr_zzmn[v4] == null && arr_zznd[v4] != null) {
                zznd zznd0 = arr_zznd[v4];
                zzob.checkState(zznd0.length() == 1);
                zzob.checkState(zznd0.zzax(0) == 0);
                int v5 = this.zzbau.zza(zznd0.zzii());
                zzob.checkState(!this.zzbav[v5]);
                ++this.zzbat;
                this.zzbav[v5] = true;
                arr_zzmn[v4] = new zzlx(this, v5);
                arr_z1[v4] = true;
                z = true;
            }
        }
        if(!this.zzbar) {
            int v6 = this.zzban.size();
            for(int v7 = 0; v7 < v6; ++v7) {
                if(!this.zzbav[v7]) {
                    ((zzmm)this.zzban.valueAt(v7)).disable();
                }
            }
        }
        if(this.zzbat == 0) {
            this.zzbas = false;
            if(this.zzbai.isLoading()) {
                this.zzbai.zzip();
            }
        }
        else if(!this.zzbar) {
            if(v != 0L) {
                v = this.zzeg(v);
            label_48:
                for(int v1 = 0; v1 < arr_zzmn.length; ++v1) {
                    if(arr_zzmn[v1] != null) {
                        arr_z1[v1] = true;
                    }
                }
            }
        }
        else if(z) {
            v = this.zzeg(v);
            goto label_48;
        }
        this.zzbar = true;
        return v;
    }

    @Override  // com.google.android.gms.internal.ads.zzji
    public final void zza(zzjl zzjl0) {
        this.zzbap = zzjl0;
        this.zzadx.post(this.zzbal);
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final void zza(zzmb zzmb0, long v) {
        this.zzbao = zzmb0;
        this.zzbak.open();
        this.startLoading();
    }

    @Override  // com.google.android.gms.internal.ads.zzny
    public final void zza(zzoa zzoa0, long v, long v1) {
        this.zza(((zzlv)zzoa0));
        this.zzbbb = true;
        if(this.zzagy == 0x8000000000000001L) {
            long v2 = this.zzhs();
            this.zzagy = v2 == 0x8000000000000000L ? 0L : v2 + 10000L;
            zzms zzms0 = new zzms(this.zzagy, this.zzbap.zzgn());
            this.zzbae.zzb(zzms0, null);
        }
        this.zzbao.zza(this);
    }

    @Override  // com.google.android.gms.internal.ads.zzny
    public final void zza(zzoa zzoa0, long v, long v1, boolean z) {
        this.zza(((zzlv)zzoa0));
        if(!z && this.zzbat > 0) {
            int v2 = this.zzban.size();
            for(int v3 = 0; v3 < v2; ++v3) {
                ((zzmm)this.zzban.valueAt(v3)).zzk(this.zzbav[v3]);
            }
            this.zzbao.zza(this);
        }
    }

    // 去混淆评级： 低(30)
    final boolean zzat(int v) {
        return this.zzbbb || !this.zzht() && ((zzmm)this.zzban.valueAt(v)).zzib();
    }

    static boolean zzb(zzls zzls0) {
        return zzls0.zzaee;
    }

    static zzmb zzc(zzls zzls0) {
        return zzls0.zzbao;
    }

    @Override  // com.google.android.gms.internal.ads.zzji
    public final zzjn zzc(int v, int v1) {
        zzjn zzjn0 = (zzmm)this.zzban.get(v);
        if(zzjn0 == null) {
            zzjn0 = new zzmm(this.zzbaf);
            ((zzmm)zzjn0).zza(this);
            this.zzban.put(v, zzjn0);
        }
        return zzjn0;
    }

    static SparseArray zzd(zzls zzls0) {
        return zzls0.zzban;
    }

    final void zzd(int v, long v1) {
        zzmm zzmm0 = (zzmm)this.zzban.valueAt(v);
        if(this.zzbbb && v1 > zzmm0.zzhs()) {
            zzmm0.zzif();
            return;
        }
        zzmm0.zze(v1, true);
    }

    static zzlz zze(zzls zzls0) {
        return zzls0.zzbad;
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final boolean zzee(long v) {
        if(!this.zzbbb && (!this.zzafa || this.zzbat != 0)) {
            boolean z = this.zzbak.open();
            if(!this.zzbai.isLoading()) {
                this.startLoading();
                return true;
            }
            return z;
        }
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final void zzef(long v) {
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final long zzeg(long v) {
        if(!this.zzbap.zzgn()) {
            v = 0L;
        }
        this.zzbay = v;
        int v1 = this.zzban.size();
        int v2 = !this.zzht();
        for(int v3 = 0; v2 != 0 && v3 < v1; ++v3) {
            if(this.zzbav[v3]) {
                v2 = ((zzmm)this.zzban.valueAt(v3)).zze(v, false);
            }
        }
        if(v2 == 0) {
            this.zzbaz = v;
            this.zzbbb = false;
            if(this.zzbai.isLoading()) {
                this.zzbai.zzip();
            }
            else {
                for(int v4 = 0; v4 < v1; ++v4) {
                    ((zzmm)this.zzban.valueAt(v4)).zzk(this.zzbav[v4]);
                }
            }
        }
        this.zzbas = false;
        return v;
    }

    static String zzf(zzls zzls0) {
        return zzls0.zzbag;
    }

    @Override  // com.google.android.gms.internal.ads.zzmo
    public final void zzf(zzgz zzgz0) {
        this.zzadx.post(this.zzbal);
    }

    static long zzg(zzls zzls0) {
        return zzls0.zzbah;
    }

    @Override  // com.google.android.gms.internal.ads.zzji
    public final void zzgp() {
        this.zzbaq = true;
        this.zzadx.post(this.zzbal);
    }

    static Runnable zzh(zzls zzls0) {
        return zzls0.zzbam;
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final long zzhk() {
        return this.zzbat == 0 ? 0x8000000000000000L : this.zzho();
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final void zzhl() throws IOException {
        this.zzbai.zzbc(0x80000000);
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final zzmu zzhm() {
        return this.zzbau;
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final long zzhn() {
        if(this.zzbas) {
            this.zzbas = false;
            return this.zzbay;
        }
        return 0x8000000000000001L;
    }

    @Override  // com.google.android.gms.internal.ads.zzmc
    public final long zzho() {
        long v;
        if(this.zzbbb) {
            return 0x8000000000000000L;
        }
        if(this.zzht()) {
            return this.zzbaz;
        }
        if(this.zzbax) {
            v = 0x7FFFFFFFFFFFFFFFL;
            int v1 = this.zzban.size();
            for(int v2 = 0; v2 < v1; ++v2) {
                if(this.zzbaw[v2]) {
                    v = Math.min(v, ((zzmm)this.zzban.valueAt(v2)).zzhs());
                }
            }
            return v == 0x8000000000000000L ? this.zzbay : v;
        }
        v = this.zzhs();
        return v == 0x8000000000000000L ? this.zzbay : v;
    }

    final void zzhp() throws IOException {
        this.zzbai.zzbc(0x80000000);
    }

    private final void zzhq() {
        if(!this.zzaee && !this.zzafa && this.zzbap != null && this.zzbaq) {
            int v = this.zzban.size();
            for(int v1 = 0; v1 < v; ++v1) {
                if(((zzmm)this.zzban.valueAt(v1)).zzic() == null) {
                    return;
                }
            }
            this.zzbak.zzir();
            zzmr[] arr_zzmr = new zzmr[v];
            this.zzbaw = new boolean[v];
            this.zzbav = new boolean[v];
            this.zzagy = this.zzbap.getDurationUs();
            for(int v2 = 0; true; ++v2) {
                boolean z = true;
                if(v2 >= v) {
                    break;
                }
                zzgz zzgz0 = ((zzmm)this.zzban.valueAt(v2)).zzic();
                arr_zzmr[v2] = new zzmr(new zzgz[]{zzgz0});
                if(!zzoi.zzbj(zzgz0.zzafn) && !zzoi.zzbi(zzgz0.zzafn)) {
                    z = false;
                }
                this.zzbaw[v2] = z;
                this.zzbax |= z;
            }
            this.zzbau = new zzmu(arr_zzmr);
            this.zzafa = true;
            zzms zzms0 = new zzms(this.zzagy, this.zzbap.zzgn());
            this.zzbae.zzb(zzms0, null);
            this.zzbao.zza(this);
        }
    }

    private final int zzhr() {
        int v = this.zzban.size();
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            v2 += ((zzmm)this.zzban.valueAt(v1)).zzia();
        }
        return v2;
    }

    private final long zzhs() {
        int v = this.zzban.size();
        long v1 = 0x8000000000000000L;
        for(int v2 = 0; v2 < v; ++v2) {
            v1 = Math.max(v1, ((zzmm)this.zzban.valueAt(v2)).zzhs());
        }
        return v1;
    }

    private final boolean zzht() {
        return this.zzbaz != 0x8000000000000001L;
    }

    static zzdkp zzi(zzls zzls0) {
        return zzls0.zzadx;
    }
}

