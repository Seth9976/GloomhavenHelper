package com.google.android.gms.internal.ads;

import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import java.io.IOException;

final class zzgs implements Handler.Callback, zzmb, zzmd, zznh {
    private int repeatMode;
    private int state;
    private final zzhe[] zzacx;
    private final zzni zzacy;
    private final zzdkp zzada;
    private final zzhk zzadd;
    private final zzhl zzade;
    private boolean zzadg;
    private boolean zzadk;
    private zzhj zzadl;
    private zzhf zzadp;
    private zzgu zzadq;
    private final zzhh[] zzadu;
    private final zzhd zzadv;
    private final zzon zzadw;
    private final zzdkp zzadx;
    private final HandlerThread zzady;
    private final zzgn zzadz;
    private zzhe zzaea;
    private zzof zzaeb;
    private zzme zzaec;
    private zzhe[] zzaed;
    private boolean zzaee;
    private boolean zzaef;
    private int zzaeg;
    private int zzaeh;
    private long zzaei;
    private int zzaej;
    private zzgx zzaek;
    private long zzael;
    private zzgv zzaem;
    private zzgv zzaen;
    private zzgv zzaeo;

    public zzgs(zzhe[] arr_zzhe, zzni zzni0, zzhd zzhd0, boolean z, int v, zzdkp zzdkp0, zzgu zzgu0, zzgn zzgn0) {
        this.zzacx = arr_zzhe;
        this.zzacy = zzni0;
        this.zzadv = zzhd0;
        this.zzadg = z;
        this.repeatMode = 0;
        this.zzada = zzdkp0;
        this.state = 1;
        this.zzadq = zzgu0;
        this.zzadz = zzgn0;
        this.zzadu = new zzhh[arr_zzhe.length];
        for(int v1 = 0; v1 < arr_zzhe.length; ++v1) {
            arr_zzhe[v1].setIndex(v1);
            this.zzadu[v1] = arr_zzhe[v1].zzdu();
        }
        this.zzadw = new zzon();
        this.zzaed = new zzhe[0];
        this.zzadd = new zzhk();
        this.zzade = new zzhl();
        zzni0.zza(this);
        this.zzadp = zzhf.zzagj;
        this.zzady = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.zzady.start();
        this.zzadx = new zzdkp(this.zzady.getLooper(), this);
    }

    @Override  // android.os.Handler$Callback
    public final boolean handleMessage(Message message0) {
        int v37;
        zzgv zzgv7;
        int v36;
        int v22;
        long v20;
        boolean z8;
        boolean z7;
        int v4;
        long v3;
        try {
            long v = 0L;
            int v1 = 0;
            switch(message0.what) {
                case 0: {
                    zzme zzme0 = (zzme)message0.obj;
                    boolean z = message0.arg1 != 0;
                    this.zzada.sendEmptyMessage(0);
                    this.zzi(true);
                    this.zzadv.zzew();
                    if(z) {
                        this.zzadq = new zzgu(0, 0x8000000000000001L);
                    }
                    this.zzaec = zzme0;
                    zzme0.zza(this.zzadz, true, this);
                    this.setState(2);
                    this.zzadx.sendEmptyMessage(2);
                    return true;
                }
                case 1: {
                    this.zzaef = false;
                    this.zzadg = message0.arg1 != 0;
                    if(message0.arg1 == 0) {
                        this.zzem();
                        this.zzen();
                        return true;
                    }
                    switch(this.state) {
                        case 2: {
                            this.zzadx.sendEmptyMessage(2);
                            return true;
                        }
                        case 3: {
                            this.zzel();
                            this.zzadx.sendEmptyMessage(2);
                            return true;
                        }
                        default: {
                            return true;
                        }
                    }
                }
                case 2: {
                    long v2 = SystemClock.elapsedRealtime();
                    if(this.zzadl == null) {
                        this.zzaec.zzhw();
                        v3 = v2;
                    }
                    else {
                        if(this.zzaem == null) {
                            v4 = this.zzadq.zzaeq;
                            goto label_42;
                        }
                        else {
                            int v5 = this.zzaem.zzaeq;
                            if(this.zzaem.zzaez || !this.zzaem.zzes()) {
                                v3 = v2;
                            }
                            else if(this.zzadl.zza(v5, this.zzade, false).zzagy == 0x8000000000000001L) {
                                v3 = v2;
                            }
                            else if(this.zzaeo == null || this.zzaem.index - this.zzaeo.index != 100) {
                                v4 = this.zzadl.zza(v5, this.zzade, this.zzadd, this.repeatMode);
                            label_42:
                                if(v4 >= this.zzadl.zzfb()) {
                                    this.zzaec.zzhw();
                                    v3 = v2;
                                }
                                else if(this.zzaem == null) {
                                    v = this.zzadq.zzaes;
                                    v3 = v2;
                                    goto label_59;
                                }
                                else {
                                    this.zzadl.zza(v4, this.zzade, false);
                                    this.zzadl.zza(0, this.zzadd, false);
                                    if(v4 == 0) {
                                        long v6 = Math.max(0L, this.zzaem.zzer() + this.zzadl.zza(this.zzaem.zzaeq, this.zzade, false).zzagy - this.zzael);
                                        v3 = v2;
                                        Pair pair0 = this.zza(this.zzadl, 0, 0x8000000000000001L, v6);
                                        if(pair0 != null) {
                                            int v7 = (int)(((Integer)pair0.first));
                                            v = (long)(((Long)pair0.second));
                                            v4 = v7;
                                        label_59:
                                            long v8 = this.zzaem == null ? v + 60000000L : this.zzaem.zzer() + this.zzadl.zza(this.zzaem.zzaeq, this.zzade, false).zzagy;
                                            int v9 = this.zzaem == null ? 0 : this.zzaem.index + 1;
                                            boolean z1 = this.zzo(v4);
                                            this.zzadl.zza(v4, this.zzade, true);
                                            zzgv zzgv0 = new zzgv(this.zzacx, this.zzadu, v8, this.zzacy, this.zzadv, this.zzaec, this.zzade.zzaev, v9, v4, z1, v);
                                            if(this.zzaem != null) {
                                                this.zzaem.zzafc = zzgv0;
                                            }
                                            this.zzaem = zzgv0;
                                            this.zzaem.zzaeu.zza(this, v);
                                            this.zzh(true);
                                        }
                                    }
                                    else {
                                        v3 = v2;
                                        goto label_59;
                                    }
                                }
                            }
                            else {
                                v3 = v2;
                            }
                        }
                        if(this.zzaem == null || this.zzaem.zzes()) {
                            this.zzh(false);
                        }
                        else if(this.zzaem != null && !this.zzadk) {
                            this.zzeq();
                        }
                        if(this.zzaeo != null) {
                            while(this.zzaeo != this.zzaen && this.zzael >= this.zzaeo.zzafc.zzaey) {
                                this.zzaeo.release();
                                this.zzb(this.zzaeo.zzafc);
                                this.zzadq = new zzgu(this.zzaeo.zzaeq, this.zzaeo.zzaer);
                                this.zzen();
                                this.zzada.obtainMessage(5, this.zzadq).sendToTarget();
                            }
                            if(this.zzaen.zzaez) {
                                for(int v13 = 0; v13 < this.zzacx.length; ++v13) {
                                    zzhe zzhe2 = this.zzacx[v13];
                                    zzmn zzmn1 = this.zzaen.zzaew[v13];
                                    if(zzmn1 != null && zzhe2.zzdw() == zzmn1 && zzhe2.zzdx()) {
                                        zzhe2.zzdy();
                                    }
                                }
                            }
                            else {
                                int v10 = 0;
                                while(v10 < this.zzacx.length) {
                                    zzhe zzhe0 = this.zzacx[v10];
                                    zzmn zzmn0 = this.zzaen.zzaew[v10];
                                    if(zzhe0.zzdw() != zzmn0 || zzmn0 != null && !zzhe0.zzdx()) {
                                        goto label_134;
                                    }
                                    ++v10;
                                }
                                if(this.zzaen.zzafc != null && this.zzaen.zzafc.zzafa) {
                                    zznk zznk0 = this.zzaen.zzafd;
                                    this.zzaen = this.zzaen.zzafc;
                                    zznk zznk1 = this.zzaen.zzafd;
                                    boolean z2 = this.zzaen.zzaeu.zzhn() != 0x8000000000000001L;
                                    for(int v11 = 0; v11 < this.zzacx.length; ++v11) {
                                        zzhe zzhe1 = this.zzacx[v11];
                                        if(zznk0.zzbeo.zzay(v11) != null) {
                                            if(z2) {
                                                zzhe1.zzdy();
                                            }
                                            else if(!zzhe1.zzdz()) {
                                                zznd zznd0 = zznk1.zzbeo.zzay(v11);
                                                if(zznd0 == null || !zznk1.zzbeq[v11].equals(zznk0.zzbeq[v11])) {
                                                    zzhe1.zzdy();
                                                }
                                                else {
                                                    zzgz[] arr_zzgz = new zzgz[zznd0.length()];
                                                    for(int v12 = 0; v12 < arr_zzgz.length; ++v12) {
                                                        arr_zzgz[v12] = zznd0.zzav(v12);
                                                    }
                                                    zzhe1.zza(arr_zzgz, this.zzaen.zzaew[v11], this.zzaen.zzer());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                label_134:
                    if(this.zzaeo == null) {
                        this.zzep();
                        this.zza(v3, 10L);
                        return true;
                    }
                    zzoq.beginSection("doSomeWork");
                    this.zzen();
                    this.zzaeo.zzaeu.zzef(this.zzadq.zzaes);
                    zzhe[] arr_zzhe = this.zzaed;
                    boolean z3 = true;
                    boolean z4 = true;
                    for(int v14 = 0; v14 < arr_zzhe.length; ++v14) {
                        zzhe zzhe3 = arr_zzhe[v14];
                        zzhe3.zzb(this.zzael, this.zzaei);
                        z4 = z4 && zzhe3.zzez();
                        boolean z5 = zzhe3.isReady() || zzhe3.zzez();
                        if(!z5) {
                            zzhe3.zzea();
                        }
                        z3 = z3 && z5;
                    }
                    if(!z3) {
                        this.zzep();
                    }
                    if(this.zzaeb != null) {
                        zzhf zzhf0 = this.zzaeb.zzfn();
                        if(!zzhf0.equals(this.zzadp)) {
                            this.zzadp = zzhf0;
                            this.zzadw.zza(this.zzaeb);
                            this.zzada.obtainMessage(7, zzhf0).sendToTarget();
                        }
                    }
                    long v15 = this.zzadl.zza(this.zzaeo.zzaeq, this.zzade, false).zzagy;
                    if(!z4 || v15 != 0x8000000000000001L && v15 > this.zzadq.zzaes || !this.zzaeo.zzaez) {
                        switch(this.state) {
                            case 2: {
                                if(this.zzaed.length <= 0) {
                                    z8 = this.zzdr(v15);
                                }
                                else if(z3) {
                                    boolean z6 = this.zzaef;
                                    long v16 = this.zzaem.zzafa ? this.zzaem.zzaeu.zzho() : this.zzaem.zzaer;
                                    if(v16 != 0x8000000000000000L) {
                                    label_179:
                                        z7 = this.zzadv.zzc(v16 - (this.zzael - this.zzaem.zzer()), z6);
                                    }
                                    else if(this.zzaem.zzaez) {
                                        z7 = true;
                                    }
                                    else {
                                        v16 = this.zzadl.zza(this.zzaem.zzaeq, this.zzade, false).zzagy;
                                        goto label_179;
                                    }
                                    z8 = z7;
                                }
                                else {
                                    z8 = false;
                                }
                                if(z8) {
                                    this.setState(3);
                                    if(this.zzadg) {
                                        this.zzel();
                                    }
                                }
                                break;
                            }
                            case 3: {
                                if(this.zzaed.length <= 0) {
                                    z3 = this.zzdr(v15);
                                }
                                if(!z3) {
                                    this.zzaef = this.zzadg;
                                    this.setState(2);
                                    this.zzem();
                                }
                            }
                        }
                    }
                    else {
                        this.setState(4);
                        this.zzem();
                    }
                    if(this.state == 2) {
                        zzhe[] arr_zzhe1 = this.zzaed;
                        while(v1 < arr_zzhe1.length) {
                            arr_zzhe1[v1].zzea();
                            ++v1;
                        }
                    }
                    if(this.zzadg && this.state == 3 || this.state == 2) {
                        this.zza(v3, 10L);
                    }
                    else if(this.zzaed.length == 0) {
                        this.zzadx.removeMessages(2);
                    }
                    else {
                        this.zza(v3, 1000L);
                    }
                    zzoq.endSection();
                    return true;
                }
                case 3: {
                    zzgx zzgx0 = (zzgx)message0.obj;
                    if(this.zzadl == null) {
                        ++this.zzaej;
                        this.zzaek = zzgx0;
                        return true;
                    }
                    Pair pair1 = this.zza(zzgx0);
                    if(pair1 == null) {
                        this.zzadq = new zzgu(0, 0L);
                        this.zzada.obtainMessage(4, 1, 0, this.zzadq).sendToTarget();
                        this.zzadq = new zzgu(0, 0x8000000000000001L);
                        this.setState(4);
                        this.zzi(false);
                        return true;
                    }
                    int v17 = zzgx0.zzafh == 0x8000000000000001L ? 1 : 0;
                    int v18 = (int)(((Integer)pair1.first));
                    long v19 = (long)(((Long)pair1.second));
                    try {
                        if(v18 != this.zzadq.zzaeq || v19 / 1000L != this.zzadq.zzaes / 1000L) {
                            v20 = this.zza(v18, v19);
                            goto label_237;
                        }
                        goto label_240;
                    }
                    catch(Throwable throwable0) {
                        this.zzadq = new zzgu(v18, v19);
                        this.zzada.obtainMessage(4, (v17 == 0 ? 0 : 1), 0, this.zzadq).sendToTarget();
                        throw throwable0;
                    }
                label_237:
                    this.zzadq = new zzgu(v18, v20);
                    this.zzada.obtainMessage(4, ((v17 | (v19 == v20 ? 0 : 1)) == 0 ? 0 : 1), 0, this.zzadq).sendToTarget();
                    return true;
                label_240:
                    this.zzadq = new zzgu(v18, v19);
                    this.zzada.obtainMessage(4, (v17 == 0 ? 0 : 1), 0, this.zzadq).sendToTarget();
                    return true;
                }
                case 4: {
                    zzhf zzhf1 = (zzhf)message0.obj;
                    zzhf zzhf2 = this.zzaeb == null ? this.zzadw.zzb(zzhf1) : this.zzaeb.zzb(zzhf1);
                    this.zzadp = zzhf2;
                    this.zzada.obtainMessage(7, zzhf2).sendToTarget();
                    return true;
                }
                case 5: {
                    this.zzeo();
                    return true;
                }
                case 6: {
                    this.zzi(true);
                    this.zzadv.zzex();
                    this.setState(1);
                    synchronized(this) {
                        this.zzaee = true;
                        this.notifyAll();
                        return true;
                    }
                }
                case 7: {
                    Pair pair2 = (Pair)message0.obj;
                    zzhj zzhj0 = this.zzadl;
                    this.zzadl = (zzhj)pair2.first;
                    Object object0 = pair2.second;
                    if(zzhj0 != null) {
                        v22 = 0;
                    }
                    else if(this.zzaej > 0) {
                        Pair pair3 = this.zza(this.zzaek);
                        v22 = this.zzaej;
                        this.zzaej = 0;
                        this.zzaek = null;
                        if(pair3 == null) {
                            this.zza(object0, v22);
                            return true;
                        }
                        this.zzadq = new zzgu(((int)(((Integer)pair3.first))), ((long)(((Long)pair3.second))));
                    }
                    else {
                        if(this.zzadq.zzaer == 0x8000000000000001L) {
                            if(this.zzadl.isEmpty()) {
                                this.zza(object0, 0);
                                return true;
                            }
                            Pair pair4 = this.zzb(0, 0x8000000000000001L);
                            this.zzadq = new zzgu(((int)(((Integer)pair4.first))), ((long)(((Long)pair4.second))));
                        }
                        v22 = 0;
                    }
                    zzgv zzgv1 = this.zzaeo == null ? this.zzaem : this.zzaeo;
                    if(zzgv1 != null) {
                        int v23 = this.zzadl.zzc(zzgv1.zzaev);
                        if(v23 == -1) {
                            int v24 = this.zza(zzgv1.zzaeq, zzhj0, this.zzadl);
                            if(v24 == -1) {
                                this.zza(object0, v22);
                                return true;
                            }
                            this.zzadl.zza(v24, this.zzade, false);
                            Pair pair5 = this.zzb(0, 0x8000000000000001L);
                            int v25 = (int)(((Integer)pair5.first));
                            long v26 = (long)(((Long)pair5.second));
                            this.zzadl.zza(v25, this.zzade, true);
                            Object object1 = this.zzade.zzaev;
                            zzgv1.zzaeq = -1;
                            while(zzgv1.zzafc != null) {
                                zzgv1 = zzgv1.zzafc;
                                zzgv1.zzaeq = zzgv1.zzaev.equals(object1) ? v25 : -1;
                            }
                            this.zzadq = new zzgu(v25, this.zza(v25, v26));
                        }
                        else {
                            zzgv1.zzc(v23, this.zzo(v23));
                            int v27 = zzgv1 == this.zzaen ? 1 : 0;
                            if(v23 != this.zzadq.zzaeq) {
                                zzgu zzgu0 = this.zzadq;
                                zzgu zzgu1 = new zzgu(v23, zzgu0.zzaer);
                                zzgu1.zzaes = zzgu0.zzaes;
                                zzgu1.zzaet = zzgu0.zzaet;
                                this.zzadq = zzgu1;
                            }
                            while(zzgv1.zzafc != null) {
                                zzgv zzgv2 = zzgv1.zzafc;
                                v23 = this.zzadl.zza(v23, this.zzade, this.zzadd, this.repeatMode);
                                if(v23 != -1) {
                                    zzhl zzhl0 = this.zzadl.zza(v23, this.zzade, true);
                                    if(zzgv2.zzaev.equals(zzhl0.zzaev)) {
                                        zzgv2.zzc(v23, this.zzo(v23));
                                        v27 |= (zzgv2 == this.zzaen ? 1 : 0);
                                        zzgv1 = zzgv2;
                                        continue;
                                    }
                                }
                                if(v27 == 0) {
                                    int v28 = this.zzaeo.zzaeq;
                                    this.zzadq = new zzgu(v28, this.zza(v28, this.zzadq.zzaes));
                                    break;
                                }
                                this.zzaem = zzgv1;
                                this.zzaem.zzafc = null;
                                zzgs.zza(zzgv2);
                                if(true) {
                                    break;
                                }
                            }
                        }
                    }
                    this.zzb(object0, v22);
                    return true;
                }
                case 8: {
                    if(this.zzaem != null && this.zzaem.zzaeu == ((zzmc)message0.obj)) {
                        zzgv zzgv3 = this.zzaem;
                        zzgv3.zzafa = true;
                        zzgv3.zzet();
                        zzgv3.zzaer = zzgv3.zzb(zzgv3.zzaer, false);
                        if(this.zzaeo == null) {
                            this.zzaen = this.zzaem;
                            this.zzdq(this.zzaen.zzaer);
                            this.zzb(this.zzaen);
                        }
                        this.zzeq();
                    }
                    return true;
                }
                case 9: {
                    if(this.zzaem != null && this.zzaem.zzaeu == ((zzmc)message0.obj)) {
                        this.zzeq();
                    }
                    return true;
                }
                case 10: {
                    if(this.zzaeo != null) {
                        zzgv zzgv4 = this.zzaeo;
                        boolean z9 = true;
                        while(zzgv4 != null && zzgv4.zzafa) {
                            if(zzgv4.zzet()) {
                                if(z9) {
                                    boolean z10 = this.zzaen != this.zzaeo;
                                    zzgs.zza(this.zzaeo.zzafc);
                                    this.zzaeo.zzafc = null;
                                    this.zzaem = this.zzaeo;
                                    this.zzaen = this.zzaeo;
                                    boolean[] arr_z = new boolean[this.zzacx.length];
                                    long v29 = this.zzaeo.zza(this.zzadq.zzaes, z10, arr_z);
                                    if(v29 != this.zzadq.zzaes) {
                                        this.zzadq.zzaes = v29;
                                        this.zzdq(v29);
                                    }
                                    boolean[] arr_z1 = new boolean[this.zzacx.length];
                                    int v31 = 0;
                                    for(int v30 = 0; v30 < this.zzacx.length; ++v30) {
                                        zzhe zzhe4 = this.zzacx[v30];
                                        arr_z1[v30] = zzhe4.getState() != 0;
                                        zzmn zzmn2 = this.zzaeo.zzaew[v30];
                                        if(zzmn2 != null) {
                                            ++v31;
                                        }
                                        if(arr_z1[v30]) {
                                            if(zzmn2 != zzhe4.zzdw()) {
                                                if(zzhe4 == this.zzaea) {
                                                    if(zzmn2 == null) {
                                                        this.zzadw.zza(this.zzaeb);
                                                    }
                                                    this.zzaeb = null;
                                                    this.zzaea = null;
                                                }
                                                zzgs.zza(zzhe4);
                                                zzhe4.disable();
                                            }
                                            else if(arr_z[v30]) {
                                                zzhe4.zzdm(this.zzael);
                                            }
                                        }
                                    }
                                    this.zzada.obtainMessage(3, zzgv4.zzafd).sendToTarget();
                                    this.zza(arr_z1, v31);
                                }
                                else {
                                    this.zzaem = zzgv4;
                                    for(zzgv zzgv5 = this.zzaem.zzafc; zzgv5 != null; zzgv5 = zzgv5.zzafc) {
                                        zzgv5.release();
                                    }
                                    this.zzaem.zzafc = null;
                                    if(this.zzaem.zzafa) {
                                        this.zzaem.zzb(Math.max(this.zzaem.zzaer, this.zzael - this.zzaem.zzer()), false);
                                    }
                                }
                                this.zzeq();
                                this.zzen();
                                this.zzadx.sendEmptyMessage(2);
                                if(true) {
                                    break;
                                }
                            }
                            else {
                                if(zzgv4 == this.zzaen) {
                                    z9 = false;
                                }
                                zzgv4 = zzgv4.zzafc;
                            }
                        }
                    }
                    return true;
                }
                case 11: {
                    zzgo[] arr_zzgo = (zzgo[])message0.obj;
                    try {
                        while(v1 < arr_zzgo.length) {
                            zzgo zzgo0 = arr_zzgo[v1];
                            zzgo0.zzacu.zza(zzgo0.zzacv, zzgo0.zzacw);
                            ++v1;
                        }
                        if(this.zzaec != null) {
                            this.zzadx.sendEmptyMessage(2);
                        }
                        return true;
                    }
                    finally {
                        synchronized(this) {
                            ++this.zzaeh;
                            this.notifyAll();
                        }
                    }
                }
                case 12: {
                    int v34 = message0.arg1;
                    this.repeatMode = v34;
                    zzgv zzgv6 = this.zzaeo == null ? this.zzaem : this.zzaeo;
                    if(zzgv6 != null) {
                        int v35 = zzgv6 == this.zzaen ? 1 : 0;
                        if(zzgv6 == this.zzaem) {
                            v36 = v35;
                            zzgv7 = zzgv6;
                            v37 = 1;
                        }
                        else {
                            v36 = v35;
                            zzgv7 = zzgv6;
                            v37 = 0;
                        }
                        while(true) {
                            int v38 = this.zzadl.zza(zzgv7.zzaeq, this.zzade, this.zzadd, v34);
                            if(zzgv7.zzafc == null || v38 == -1 || zzgv7.zzafc.zzaeq != v38) {
                                break;
                            }
                            zzgv7 = zzgv7.zzafc;
                            v36 |= (zzgv7 == this.zzaen ? 1 : 0);
                            v37 |= (zzgv7 == this.zzaem ? 1 : 0);
                        }
                        if(zzgv7.zzafc != null) {
                            zzgs.zza(zzgv7.zzafc);
                            zzgv7.zzafc = null;
                        }
                        zzgv7.zzaez = this.zzo(zzgv7.zzaeq);
                        if(v37 == 0) {
                            this.zzaem = zzgv7;
                        }
                        if(v36 == 0 && this.zzaeo != null) {
                            int v39 = this.zzaeo.zzaeq;
                            this.zzadq = new zzgu(v39, this.zza(v39, this.zzadq.zzaes));
                        }
                        if(this.state == 4 && v34 != 0) {
                            this.setState(2);
                        }
                    }
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        catch(zzgk zzgk0) {
            Log.e("ExoPlayerImplInternal", "Renderer error.", zzgk0);
            this.zzada.obtainMessage(8, zzgk0).sendToTarget();
            this.zzeo();
            return true;
        }
        catch(IOException iOException0) {
            Log.e("ExoPlayerImplInternal", "Source error.", iOException0);
            zzgk zzgk1 = zzgk.zza(iOException0);
            this.zzada.obtainMessage(8, zzgk1).sendToTarget();
            this.zzeo();
            return true;
        }
        catch(RuntimeException runtimeException0) {
            Log.e("ExoPlayerImplInternal", "Internal runtime error.", runtimeException0);
            zzgk zzgk2 = zzgk.zza(runtimeException0);
            this.zzada.obtainMessage(8, zzgk2).sendToTarget();
            this.zzeo();
            return true;
        }
    }

    public final void release() {
        synchronized(this) {
            if(this.zzaee) {
                return;
            }
            this.zzadx.sendEmptyMessage(6);
            while(!this.zzaee) {
                try {
                    this.wait();
                }
                catch(InterruptedException unused_ex) {
                    Thread.currentThread().interrupt();
                }
            }
            this.zzady.quit();
        }
    }

    private final void setState(int v) {
        if(this.state != v) {
            this.state = v;
            this.zzada.obtainMessage(1, v, 0).sendToTarget();
        }
    }

    public final void stop() {
        this.zzadx.sendEmptyMessage(5);
    }

    private final int zza(int v, zzhj zzhj0, zzhj zzhj1) {
        int v1 = zzhj0.zzfb();
        int v3 = v;
        int v4 = -1;
        for(int v2 = 0; v2 < v1 && v4 == -1; ++v2) {
            v3 = zzhj0.zza(v3, this.zzade, this.zzadd, this.repeatMode);
            v4 = zzhj1.zzc(zzhj0.zza(v3, this.zzade, true).zzaev);
        }
        return v4;
    }

    private final long zza(int v, long v1) throws zzgk {
        zzgv zzgv2;
        this.zzem();
        this.zzaef = false;
        this.setState(2);
        zzgv zzgv0 = this.zzaeo;
        if(zzgv0 == null) {
            zzgv zzgv1 = this.zzaem;
            if(zzgv1 != null) {
                zzgv1.release();
            }
            zzgv2 = null;
        }
        else {
            zzgv2 = null;
            while(zzgv0 != null) {
                if(zzgv0.zzaeq != v || !zzgv0.zzafa) {
                    zzgv0.release();
                }
                else {
                    zzgv2 = zzgv0;
                }
                zzgv0 = zzgv0.zzafc;
            }
        }
        if(this.zzaeo != zzgv2 || this.zzaeo != this.zzaen) {
            zzhe[] arr_zzhe = this.zzaed;
            for(int v2 = 0; v2 < arr_zzhe.length; ++v2) {
                arr_zzhe[v2].disable();
            }
            this.zzaed = new zzhe[0];
            this.zzaeb = null;
            this.zzaea = null;
            this.zzaeo = null;
        }
        if(zzgv2 == null) {
            this.zzaem = null;
            this.zzaen = null;
            this.zzaeo = null;
            this.zzdq(v1);
        }
        else {
            zzgv2.zzafc = null;
            this.zzaem = zzgv2;
            this.zzaen = zzgv2;
            this.zzb(zzgv2);
            if(this.zzaeo.zzafb) {
                v1 = this.zzaeo.zzaeu.zzeg(v1);
            }
            this.zzdq(v1);
            this.zzeq();
        }
        this.zzadx.sendEmptyMessage(2);
        return v1;
    }

    private final Pair zza(zzgx zzgx0) {
        zzhj zzhj1;
        Pair pair0;
        zzhj zzhj0 = zzgx0.zzadl.isEmpty() ? this.zzadl : zzgx0.zzadl;
        try {
            pair0 = this.zzb(zzhj0, zzgx0.zzafg, zzgx0.zzafh);
            zzhj1 = this.zzadl;
        }
        catch(IndexOutOfBoundsException unused_ex) {
            throw new zzha(this.zzadl, zzgx0.zzafg, zzgx0.zzafh);
        }
        if(zzhj1 == zzhj0) {
            return pair0;
        }
        int v = zzhj1.zzc(zzhj0.zza(((int)(((Integer)pair0.first))), this.zzade, true).zzaev);
        if(v != -1) {
            return Pair.create(v, ((Long)pair0.second));
        }
        int v1 = this.zza(((int)(((Integer)pair0.first))), zzhj0, this.zzadl);
        if(v1 != -1) {
            this.zzadl.zza(v1, this.zzade, false);
            return this.zzb(0, 0x8000000000000001L);
        }
        return null;
    }

    private final Pair zza(zzhj zzhj0, int v, long v1, long v2) {
        zzob.zzc(v, 0, zzhj0.zzfa());
        zzhj0.zza(v, this.zzadd, false, v2);
        if(v1 == 0x8000000000000001L) {
            v1 = this.zzadd.zzagx;
            if(v1 == 0x8000000000000001L) {
                return null;
            }
        }
        long v3 = this.zzadd.zzagz + v1;
        boolean z = zzhj0.zza(0, this.zzade, false).zzagy == 0x8000000000000001L;
        return Pair.create(0, v3);
    }

    private final void zza(long v, long v1) {
        this.zzadx.removeMessages(2);
        long v2 = v + v1 - SystemClock.elapsedRealtime();
        if(v2 <= 0L) {
            this.zzadx.sendEmptyMessage(2);
            return;
        }
        this.zzadx.sendEmptyMessageDelayed(2, v2);
    }

    private static void zza(zzgv zzgv0) {
        while(zzgv0 != null) {
            zzgv0.release();
            zzgv0 = zzgv0.zzafc;
        }
    }

    private static void zza(zzhe zzhe0) throws zzgk {
        if(zzhe0.getState() == 2) {
            zzhe0.stop();
        }
    }

    private final void zza(Object object0, int v) {
        this.zzadq = new zzgu(0, 0L);
        this.zzb(object0, v);
        this.zzadq = new zzgu(0, 0x8000000000000001L);
        this.setState(4);
        this.zzi(false);
    }

    private final void zza(boolean[] arr_z, int v) throws zzgk {
        this.zzaed = new zzhe[v];
        int v2 = 0;
        for(int v1 = 0; true; ++v1) {
            zzhe[] arr_zzhe = this.zzacx;
            if(v1 >= arr_zzhe.length) {
                break;
            }
            zzhe zzhe0 = arr_zzhe[v1];
            zznd zznd0 = this.zzaeo.zzafd.zzbeo.zzay(v1);
            if(zznd0 != null) {
                this.zzaed[v2] = zzhe0;
                if(zzhe0.getState() == 0) {
                    zzhg zzhg0 = this.zzaeo.zzafd.zzbeq[v1];
                    boolean z = this.zzadg && this.state == 3;
                    boolean z1 = !arr_z[v1] && z;
                    zzgz[] arr_zzgz = new zzgz[zznd0.length()];
                    for(int v3 = 0; v3 < arr_zzgz.length; ++v3) {
                        arr_zzgz[v3] = zznd0.zzav(v3);
                    }
                    zzhe0.zza(zzhg0, arr_zzgz, this.zzaeo.zzaew[v1], this.zzael, z1, this.zzaeo.zzer());
                    zzof zzof0 = zzhe0.zzdv();
                    if(zzof0 != null) {
                        if(this.zzaeb != null) {
                            throw zzgk.zza(new IllegalStateException("Multiple renderer media clocks enabled."));
                        }
                        this.zzaeb = zzof0;
                        this.zzaea = zzhe0;
                        this.zzaeb.zzb(this.zzadp);
                    }
                    if(z) {
                        zzhe0.start();
                    }
                }
                ++v2;
            }
        }
    }

    public final void zza(zzhj zzhj0, int v, long v1) {
        zzgx zzgx0 = new zzgx(zzhj0, v, v1);
        this.zzadx.obtainMessage(3, zzgx0).sendToTarget();
    }

    @Override  // com.google.android.gms.internal.ads.zzmb
    public final void zza(zzmc zzmc0) {
        this.zzadx.obtainMessage(8, zzmc0).sendToTarget();
    }

    public final void zza(zzme zzme0, boolean z) {
        this.zzadx.obtainMessage(0, 1, 0, zzme0).sendToTarget();
    }

    @Override  // com.google.android.gms.internal.ads.zzmp
    public final void zza(zzmq zzmq0) {
        this.zzadx.obtainMessage(9, ((zzmc)zzmq0)).sendToTarget();
    }

    public final void zza(zzgo[] arr_zzgo) {
        if(this.zzaee) {
            Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
            return;
        }
        ++this.zzaeg;
        this.zzadx.obtainMessage(11, arr_zzgo).sendToTarget();
    }

    private final Pair zzb(int v, long v1) {
        return this.zzb(this.zzadl, v, 0x8000000000000001L);
    }

    private final Pair zzb(zzhj zzhj0, int v, long v1) {
        return this.zza(zzhj0, v, v1, 0L);
    }

    private final void zzb(zzgv zzgv0) throws zzgk {
        if(this.zzaeo == zzgv0) {
            return;
        }
        boolean[] arr_z = new boolean[this.zzacx.length];
        int v1 = 0;
        for(int v = 0; true; ++v) {
            zzhe[] arr_zzhe = this.zzacx;
            if(v >= arr_zzhe.length) {
                break;
            }
            zzhe zzhe0 = arr_zzhe[v];
            arr_z[v] = zzhe0.getState() != 0;
            zznd zznd0 = zzgv0.zzafd.zzbeo.zzay(v);
            if(zznd0 != null) {
                ++v1;
            }
            if(arr_z[v] && (zznd0 == null || zzhe0.zzdz() && zzhe0.zzdw() == this.zzaeo.zzaew[v])) {
                if(zzhe0 == this.zzaea) {
                    this.zzadw.zza(this.zzaeb);
                    this.zzaeb = null;
                    this.zzaea = null;
                }
                zzgs.zza(zzhe0);
                zzhe0.disable();
            }
        }
        this.zzaeo = zzgv0;
        this.zzada.obtainMessage(3, zzgv0.zzafd).sendToTarget();
        this.zza(arr_z, v1);
    }

    private final void zzb(Object object0, int v) {
        zzgw zzgw0 = new zzgw(this.zzadl, object0, this.zzadq, v);
        this.zzada.obtainMessage(6, zzgw0).sendToTarget();
    }

    @Override  // com.google.android.gms.internal.ads.zzmd
    public final void zzb(zzhj zzhj0, Object object0) {
        Pair pair0 = Pair.create(zzhj0, object0);
        this.zzadx.obtainMessage(7, pair0).sendToTarget();
    }

    public final void zzb(zzgo[] arr_zzgo) {
        synchronized(this) {
            if(this.zzaee) {
                Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
                return;
            }
            int v1 = this.zzaeg;
            this.zzaeg = v1 + 1;
            this.zzadx.obtainMessage(11, arr_zzgo).sendToTarget();
            while(this.zzaeh <= v1) {
                try {
                    this.wait();
                }
                catch(InterruptedException unused_ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private final void zzdq(long v) throws zzgk {
        this.zzael = this.zzaeo == null ? v + 60000000L : v + this.zzaeo.zzer();
        this.zzadw.zzel(this.zzael);
        zzhe[] arr_zzhe = this.zzaed;
        for(int v1 = 0; v1 < arr_zzhe.length; ++v1) {
            arr_zzhe[v1].zzdm(this.zzael);
        }
    }

    // 去混淆评级： 低(20)
    private final boolean zzdr(long v) {
        return v == 0x8000000000000001L || this.zzadq.zzaes < v || this.zzaeo.zzafc != null && this.zzaeo.zzafc.zzafa;
    }

    @Override  // com.google.android.gms.internal.ads.zznh
    public final void zzek() {
        this.zzadx.sendEmptyMessage(10);
    }

    private final void zzel() throws zzgk {
        this.zzaef = false;
        this.zzadw.start();
        zzhe[] arr_zzhe = this.zzaed;
        for(int v = 0; v < arr_zzhe.length; ++v) {
            arr_zzhe[v].start();
        }
    }

    private final void zzem() throws zzgk {
        this.zzadw.stop();
        zzhe[] arr_zzhe = this.zzaed;
        for(int v = 0; v < arr_zzhe.length; ++v) {
            zzgs.zza(arr_zzhe[v]);
        }
    }

    private final void zzen() throws zzgk {
        zzgv zzgv0 = this.zzaeo;
        if(zzgv0 == null) {
            return;
        }
        long v = zzgv0.zzaeu.zzhn();
        if(v == 0x8000000000000001L) {
            if(this.zzaea == null || this.zzaea.zzez()) {
                this.zzael = this.zzadw.zzfx();
            }
            else {
                this.zzael = this.zzaeb.zzfx();
                this.zzadw.zzel(this.zzael);
            }
            v = this.zzael - this.zzaeo.zzer();
        }
        else {
            this.zzdq(v);
        }
        this.zzadq.zzaes = v;
        this.zzaei = SystemClock.elapsedRealtime() * 1000L;
        long v1 = this.zzaed.length == 0 ? 0x8000000000000000L : this.zzaeo.zzaeu.zzho();
        zzgu zzgu0 = this.zzadq;
        if(v1 == 0x8000000000000000L) {
            v1 = this.zzadl.zza(this.zzaeo.zzaeq, this.zzade, false).zzagy;
        }
        zzgu0.zzaet = v1;
    }

    private final void zzeo() {
        this.zzi(true);
        this.zzadv.onStopped();
        this.setState(1);
    }

    private final void zzep() throws IOException {
        if(this.zzaem != null && !this.zzaem.zzafa && (this.zzaen == null || this.zzaen.zzafc == this.zzaem)) {
            zzhe[] arr_zzhe = this.zzaed;
            for(int v = 0; v < arr_zzhe.length; ++v) {
                if(!arr_zzhe[v].zzdx()) {
                    return;
                }
            }
            this.zzaem.zzaeu.zzhl();
        }
    }

    private final void zzeq() {
        long v = this.zzaem.zzafa ? this.zzaem.zzaeu.zzhk() : 0L;
        if(v == 0x8000000000000000L) {
            this.zzh(false);
            return;
        }
        long v1 = this.zzael - this.zzaem.zzer();
        boolean z = this.zzadv.zzdt(v - v1);
        this.zzh(z);
        if(z) {
            this.zzaem.zzaeu.zzee(v1);
        }
    }

    public final void zzg(boolean z) {
        this.zzadx.obtainMessage(1, ((int)z), 0).sendToTarget();
    }

    private final void zzh(boolean z) {
        if(this.zzadk != z) {
            this.zzadk = z;
            this.zzada.obtainMessage(2, ((int)z), 0).sendToTarget();
        }
    }

    private final void zzi(boolean z) {
        this.zzadx.removeMessages(2);
        this.zzaef = false;
        this.zzadw.stop();
        this.zzaeb = null;
        this.zzaea = null;
        this.zzael = 60000000L;
        zzhe[] arr_zzhe = this.zzaed;
        for(int v = 0; v < arr_zzhe.length; ++v) {
            zzhe zzhe0 = arr_zzhe[v];
            try {
                zzgs.zza(zzhe0);
                zzhe0.disable();
            }
            catch(zzgk | RuntimeException zzgk0) {
                Log.e("ExoPlayerImplInternal", "Stop failed.", zzgk0);
            }
        }
        this.zzaed = new zzhe[0];
        zzgs.zza((this.zzaeo == null ? this.zzaem : this.zzaeo));
        this.zzaem = null;
        this.zzaen = null;
        this.zzaeo = null;
        this.zzh(false);
        if(z) {
            zzme zzme0 = this.zzaec;
            if(zzme0 != null) {
                zzme0.zzhx();
                this.zzaec = null;
            }
            this.zzadl = null;
        }
    }

    private final boolean zzo(int v) {
        this.zzadl.zza(v, this.zzade, false);
        return !this.zzadl.zza(0, this.zzadd, false).zzagu && this.zzadl.zza(v, this.zzade, this.zzadd, this.repeatMode) == -1;
    }
}

