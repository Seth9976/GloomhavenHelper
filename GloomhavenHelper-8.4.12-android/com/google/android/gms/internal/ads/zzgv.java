package com.google.android.gms.internal.ads;

import android.util.Log;

final class zzgv {
    public final int index;
    private final zzhe[] zzacx;
    private final zzni zzacy;
    private final zzhh[] zzadu;
    private final zzhd zzadv;
    private final zzme zzaec;
    public int zzaeq;
    public long zzaer;
    public final zzmc zzaeu;
    public final Object zzaev;
    public final zzmn[] zzaew;
    private final boolean[] zzaex;
    public final long zzaey;
    public boolean zzaez;
    public boolean zzafa;
    public boolean zzafb;
    public zzgv zzafc;
    public zznk zzafd;
    private zznk zzafe;

    public zzgv(zzhe[] arr_zzhe, zzhh[] arr_zzhh, long v, zzni zzni0, zzhd zzhd0, zzme zzme0, Object object0, int v1, int v2, boolean z, long v3) {
        this.zzacx = arr_zzhe;
        this.zzadu = arr_zzhh;
        this.zzaey = v;
        this.zzacy = zzni0;
        this.zzadv = zzhd0;
        this.zzaec = zzme0;
        this.zzaev = zzob.checkNotNull(object0);
        this.index = v1;
        this.zzaeq = v2;
        this.zzaez = z;
        this.zzaer = v3;
        this.zzaew = new zzmn[arr_zzhe.length];
        this.zzaex = new boolean[arr_zzhe.length];
        this.zzaeu = zzme0.zza(v2, zzhd0.zzey());
    }

    public final void release() {
        try {
            this.zzaec.zzb(this.zzaeu);
        }
        catch(RuntimeException runtimeException0) {
            Log.e("ExoPlayerImplInternal", "Period release failed.", runtimeException0);
        }
    }

    public final long zza(long v, boolean z, boolean[] arr_z) {
        zznf zznf0 = this.zzafd.zzbeo;
        for(int v1 = 0; true; ++v1) {
            boolean z1 = true;
            if(v1 >= zznf0.length) {
                break;
            }
            boolean[] arr_z1 = this.zzaex;
            if(z || !this.zzafd.zza(this.zzafe, v1)) {
                z1 = false;
            }
            arr_z1[v1] = z1;
        }
        zznd[] arr_zznd = zznf0.zzij();
        long v2 = this.zzaeu.zza(arr_zznd, this.zzaex, this.zzaew, arr_z, v);
        this.zzafe = this.zzafd;
        this.zzafb = false;
        for(int v3 = 0; true; ++v3) {
            zzmn[] arr_zzmn = this.zzaew;
            if(v3 >= arr_zzmn.length) {
                break;
            }
            if(arr_zzmn[v3] == null) {
                zzob.checkState(zznf0.zzay(v3) == null);
            }
            else {
                zzob.checkState(zznf0.zzay(v3) != null);
                this.zzafb = true;
            }
        }
        this.zzadv.zza(this.zzacx, this.zzafd.zzben, zznf0);
        return v2;
    }

    public final long zzb(long v, boolean z) {
        return this.zza(v, false, new boolean[this.zzacx.length]);
    }

    public final void zzc(int v, boolean z) {
        this.zzaeq = v;
        this.zzaez = z;
    }

    public final long zzer() {
        return this.zzaey - this.zzaer;
    }

    // 去混淆评级： 低(20)
    public final boolean zzes() {
        return this.zzafa && (!this.zzafb || this.zzaeu.zzho() == 0x8000000000000000L);
    }

    public final boolean zzet() throws zzgk {
        boolean z;
        zzmu zzmu0 = this.zzaeu.zzhm();
        zznk zznk0 = this.zzacy.zza(this.zzadu, zzmu0);
        zznk zznk1 = this.zzafe;
        if(zznk1 == null) {
            z = false;
        }
        else {
            for(int v = 0; true; ++v) {
                z = true;
                if(v >= zznk0.zzbeo.length) {
                    break;
                }
                if(!zznk0.zza(zznk1, v)) {
                    z = false;
                    break;
                }
            }
        }
        if(z) {
            return false;
        }
        this.zzafd = zznk0;
        return true;
    }
}

