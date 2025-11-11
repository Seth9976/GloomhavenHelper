package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public final class zzkn implements zzjg, zzjl {
    private long zzagy;
    private static final zzjh zzanu;
    private final zzom zzaob;
    private final zzom zzaoc;
    private int zzapq;
    private int zzapr;
    private zzji zzapu;
    private static final int zzavv;
    private final zzom zzavw;
    private final Stack zzavx;
    private int zzavy;
    private int zzavz;
    private long zzawa;
    private int zzawb;
    private zzom zzawc;
    private zzkp[] zzawd;
    private boolean zzawe;

    static {
        zzkn.zzanu = new zzkq();
        zzkn.zzavv = zzop.zzbo("qt  ");
    }

    public zzkn() {
        this.zzavw = new zzom(16);
        this.zzavx = new Stack();
        this.zzaob = new zzom(zzoh.zzbgi);
        this.zzaoc = new zzom(4);
    }

    @Override  // com.google.android.gms.internal.ads.zzjl
    public final long getDurationUs() {
        return this.zzagy;
    }

    @Override  // com.google.android.gms.internal.ads.zzjg
    public final void release() {
    }

    // This method was un-flattened
    @Override  // com.google.android.gms.internal.ads.zzjg
    public final int zza(zzjf zzjf0, zzjm zzjm0) throws IOException, InterruptedException {
        int v13;
        zzkp[] arr_zzkp;
        boolean z1;
        boolean z;
        long v18;
        long v17;
        while(true) {
            switch(this.zzavy) {
                case 0: {
                    if(this.zzawb == 0) {
                        if(!zzjf0.zza(this.zzavw.data, 0, 8, true)) {
                            return -1;
                        }
                        this.zzawb = 8;
                        this.zzavw.zzbh(0);
                        this.zzawa = this.zzavw.zziz();
                        this.zzavz = this.zzavw.readInt();
                    }
                    if(this.zzawa == 1L) {
                        zzjf0.readFully(this.zzavw.data, 8, 8);
                        this.zzawb += 8;
                        this.zzawa = this.zzavw.zzjd();
                    }
                    if(this.zzavz != zzkc.zzasi && this.zzavz != zzkc.zzask && this.zzavz != zzkc.zzasl && this.zzavz != zzkc.zzasm && this.zzavz != zzkc.zzasn && this.zzavz != zzkc.zzasw) {
                        if(this.zzavz != zzkc.zzasy && this.zzavz != zzkc.zzasj && this.zzavz != zzkc.zzasz && this.zzavz != zzkc.zzata && this.zzavz != zzkc.zzatt && this.zzavz != zzkc.zzatu && this.zzavz != zzkc.zzatv && this.zzavz != zzkc.zzasx && this.zzavz != zzkc.zzatw && this.zzavz != zzkc.zzatx && this.zzavz != zzkc.zzaty && this.zzavz != zzkc.zzatz && this.zzavz != zzkc.zzaua && this.zzavz != zzkc.zzasv && this.zzavz != zzkc.zzarh && this.zzavz != zzkc.zzauh) {
                            this.zzawc = null;
                        }
                        else {
                            zzob.checkState(this.zzawb == 8);
                            zzob.checkState(this.zzawa <= 0x7FFFFFFFL);
                            this.zzawc = new zzom(((int)this.zzawa));
                            System.arraycopy(this.zzavw.data, 0, this.zzawc.data, 0, 8);
                        }
                        this.zzavy = 1;
                    }
                    else {
                        long v16 = zzjf0.getPosition() + this.zzawa - ((long)this.zzawb);
                        zzkb zzkb0 = new zzkb(this.zzavz, v16);
                        this.zzavx.add(zzkb0);
                        if(this.zzawa == ((long)this.zzawb)) {
                            this.zzeb(v16);
                        }
                        else {
                            this.zzgy();
                        }
                    }
                    continue;
                }
                case 1: {
                    v17 = this.zzawa - ((long)this.zzawb);
                    v18 = zzjf0.getPosition();
                    zzom zzom0 = this.zzawc;
                    if(zzom0 != null) {
                        zzjf0.readFully(zzom0.data, this.zzawb, ((int)v17));
                        if(this.zzavz == zzkc.zzarh) {
                            zzom zzom1 = this.zzawc;
                            zzom1.zzbh(8);
                            if(zzom1.readInt() == zzkn.zzavv) {
                                z = true;
                            }
                            else {
                                z = false;
                                zzom1.zzbi(4);
                                while(zzom1.zzix() > 0) {
                                    if(zzom1.readInt() == zzkn.zzavv) {
                                        z = true;
                                        break;
                                    }
                                }
                            }
                            this.zzawe = z;
                        }
                        else if(!this.zzavx.isEmpty()) {
                            zzkb zzkb1 = (zzkb)this.zzavx.peek();
                            zzke zzke0 = new zzke(this.zzavz, this.zzawc);
                            zzkb1.zzarf.add(zzke0);
                        }
                        z1 = false;
                    }
                    else if(v17 < 0x40000L) {
                        zzjf0.zzac(((int)v17));
                        z1 = false;
                    }
                    else {
                        zzjm0.zzana = zzjf0.getPosition() + v17;
                        z1 = true;
                    }
                    break;
                }
                case 2: {
                    long v = 0x7FFFFFFFFFFFFFFFL;
                    int v2 = -1;
                    for(int v1 = 0; true; ++v1) {
                        arr_zzkp = this.zzawd;
                        if(v1 >= arr_zzkp.length) {
                            break;
                        }
                        zzkp zzkp0 = arr_zzkp[v1];
                        int v3 = zzkp0.zzavq;
                        if(v3 != zzkp0.zzaxk.zzavm) {
                            long v4 = zzkp0.zzaxk.zzanf[v3];
                            if(v4 < v) {
                                v2 = v1;
                                v = v4;
                            }
                        }
                    }
                    if(v2 == -1) {
                        return -1;
                    }
                    zzkp zzkp1 = arr_zzkp[v2];
                    zzjn zzjn0 = zzkp1.zzaxl;
                    int v5 = zzkp1.zzavq;
                    long v6 = zzkp1.zzaxk.zzanf[v5];
                    int v7 = zzkp1.zzaxk.zzane[v5];
                    if(zzkp1.zzaxj.zzaxn == 1) {
                        v6 += 8L;
                        v7 -= 8;
                    }
                    long v8 = v6 - zzjf0.getPosition() + ((long)this.zzapr);
                    if(v8 >= 0L && v8 < 0x40000L) {
                        zzjf0.zzac(((int)v8));
                        if(zzkp1.zzaxj.zzara == 0) {
                            int v14;
                            while((v14 = this.zzapr) < v7) {
                                int v15 = zzjn0.zza(zzjf0, v7 - v14, false);
                                this.zzapr += v15;
                                this.zzapq -= v15;
                            }
                        }
                        else {
                            byte[] arr_b = this.zzaoc.data;
                            arr_b[0] = 0;
                            arr_b[1] = 0;
                            arr_b[2] = 0;
                            int v9 = zzkp1.zzaxj.zzara;
                            int v10 = 4 - zzkp1.zzaxj.zzara;
                            while(this.zzapr < v7) {
                                int v11 = this.zzapq;
                                if(v11 == 0) {
                                    zzjf0.readFully(this.zzaoc.data, v10, v9);
                                    this.zzaoc.zzbh(0);
                                    this.zzapq = this.zzaoc.zzjc();
                                    this.zzaob.zzbh(0);
                                    zzjn0.zza(this.zzaob, 4);
                                    this.zzapr += 4;
                                    v7 += v10;
                                }
                                else {
                                    int v12 = zzjn0.zza(zzjf0, v11, false);
                                    this.zzapr += v12;
                                    this.zzapq -= v12;
                                }
                            }
                        }
                        v13 = v7;
                        zzjn0.zza(zzkp1.zzaxk.zzaxs[v5], zzkp1.zzaxk.zzavu[v5], v13, 0, null);
                        ++zzkp1.zzavq;
                        this.zzapr = 0;
                        this.zzapq = 0;
                        return 0;
                    }
                    zzjm0.zzana = v6;
                    return 1;
                }
                default: {
                    throw new IllegalStateException();
                }
            }
            this.zzeb(v18 + v17);
            if(z1 && this.zzavy != 2) {
                break;
            }
        }
        return 1;
    }

    @Override  // com.google.android.gms.internal.ads.zzjg
    public final void zza(zzji zzji0) {
        this.zzapu = zzji0;
    }

    @Override  // com.google.android.gms.internal.ads.zzjg
    public final boolean zza(zzjf zzjf0) throws IOException, InterruptedException {
        return zzks.zzd(zzjf0);
    }

    @Override  // com.google.android.gms.internal.ads.zzjg
    public final void zzc(long v, long v1) {
        this.zzavx.clear();
        this.zzawb = 0;
        this.zzapr = 0;
        this.zzapq = 0;
        if(v == 0L) {
            this.zzgy();
            return;
        }
        zzkp[] arr_zzkp = this.zzawd;
        if(arr_zzkp != null) {
            for(int v2 = 0; v2 < arr_zzkp.length; ++v2) {
                zzkp zzkp0 = arr_zzkp[v2];
                zzkt zzkt0 = zzkp0.zzaxk;
                int v3 = zzkt0.zzec(v1);
                if(v3 == -1) {
                    v3 = zzkt0.zzed(v1);
                }
                zzkp0.zzavq = v3;
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzjl
    public final long zzdz(long v) {
        zzkp[] arr_zzkp = this.zzawd;
        long v1 = 0x7FFFFFFFFFFFFFFFL;
        for(int v2 = 0; v2 < arr_zzkp.length; ++v2) {
            zzkt zzkt0 = arr_zzkp[v2].zzaxk;
            int v3 = zzkt0.zzec(v);
            if(v3 == -1) {
                v3 = zzkt0.zzed(v);
            }
            long v4 = zzkt0.zzanf[v3];
            if(v4 < v1) {
                v1 = v4;
            }
        }
        return v1;
    }

    private final void zzeb(long v) throws zzhc {
        zzjk zzjk1;
        zzlh zzlh1;
        while(!this.zzavx.isEmpty() && ((zzkb)this.zzavx.peek()).zzare == v) {
            zzkb zzkb0 = (zzkb)this.zzavx.pop();
            if(zzkb0.type == zzkc.zzasi) {
                ArrayList arrayList0 = new ArrayList();
                zzlh zzlh0 = null;
                zzjk zzjk0 = new zzjk();
                zzke zzke0 = zzkb0.zzal(zzkc.zzauh);
                if(zzke0 != null) {
                    zzlh0 = zzkd.zza(zzke0, this.zzawe);
                    if(zzlh0 != null) {
                        zzjk0.zzb(zzlh0);
                    }
                }
                long v1 = 0x7FFFFFFFFFFFFFFFL;
                long v2 = 0x8000000000000001L;
                int v3 = 0;
                while(v3 < zzkb0.zzarg.size()) {
                    zzkb zzkb1 = (zzkb)zzkb0.zzarg.get(v3);
                    if(zzkb1.type == zzkc.zzask) {
                        zzkr zzkr0 = zzkd.zza(zzkb1, zzkb0.zzal(zzkc.zzasj), 0x8000000000000001L, null, this.zzawe);
                        if(zzkr0 == null) {
                            zzlh1 = zzlh0;
                            zzjk1 = zzjk0;
                        }
                        else {
                            zzkt zzkt0 = zzkd.zza(zzkr0, zzkb1.zzam(zzkc.zzasl).zzam(zzkc.zzasm).zzam(zzkc.zzasn), zzjk0);
                            if(zzkt0.zzavm == 0) {
                                zzlh1 = zzlh0;
                                zzjk1 = zzjk0;
                            }
                            else {
                                zzkp zzkp0 = new zzkp(zzkr0, zzkt0, this.zzapu.zzc(v3, zzkr0.type));
                                zzgz zzgz0 = zzkr0.zzagi.zzp(zzkt0.zzavs + 30);
                                if(zzkr0.type == 1) {
                                    if(zzjk0.zzgq()) {
                                        zzgz0 = zzgz0.zzb(zzjk0.zzaga, zzjk0.zzagb);
                                    }
                                    if(zzlh0 != null) {
                                        zzgz0 = zzgz0.zza(zzlh0);
                                    }
                                }
                                zzkp0.zzaxl.zze(zzgz0);
                                zzlh1 = zzlh0;
                                zzjk1 = zzjk0;
                                v2 = Math.max(v2, zzkr0.zzagy);
                                arrayList0.add(zzkp0);
                                long v4 = zzkt0.zzanf[0];
                                if(v4 < v1) {
                                    v1 = v4;
                                }
                            }
                        }
                    }
                    else {
                        zzlh1 = zzlh0;
                        zzjk1 = zzjk0;
                    }
                    ++v3;
                    zzjk0 = zzjk1;
                    zzlh0 = zzlh1;
                }
                this.zzagy = v2;
                this.zzawd = (zzkp[])arrayList0.toArray(new zzkp[arrayList0.size()]);
                this.zzapu.zzgp();
                this.zzapu.zza(this);
                this.zzavx.clear();
                this.zzavy = 2;
            }
            else if(!this.zzavx.isEmpty()) {
                ((zzkb)this.zzavx.peek()).zzarg.add(zzkb0);
            }
        }
        if(this.zzavy != 2) {
            this.zzgy();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzjl
    public final boolean zzgn() {
        return true;
    }

    private final void zzgy() {
        this.zzavy = 0;
        this.zzawb = 0;
    }
}

