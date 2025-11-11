package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.common.util.Clock;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.annotation.concurrent.GuardedBy;

public final class zzavq {
    private final Object lock;
    private final Clock zzbmz;
    private final String zzdkf;
    @GuardedBy("lock")
    private boolean zzdlp;
    @GuardedBy("lock")
    private long zzdlt;
    private final zzawc zzdrf;
    @GuardedBy("lock")
    private final LinkedList zzdrg;
    private final String zzdrh;
    @GuardedBy("lock")
    private long zzdri;
    @GuardedBy("lock")
    private long zzdrj;
    @GuardedBy("lock")
    private long zzdrk;
    @GuardedBy("lock")
    private long zzdrl;
    @GuardedBy("lock")
    private long zzdrm;

    zzavq(Clock clock0, zzawc zzawc0, String s, String s1) {
        this.lock = new Object();
        this.zzdlt = -1L;
        this.zzdri = -1L;
        this.zzdlp = false;
        this.zzdrj = -1L;
        this.zzdrk = 0L;
        this.zzdrl = -1L;
        this.zzdrm = -1L;
        this.zzbmz = clock0;
        this.zzdrf = zzawc0;
        this.zzdrh = s;
        this.zzdkf = s1;
        this.zzdrg = new LinkedList();
    }

    public final Bundle toBundle() {
        synchronized(this.lock) {
            Bundle bundle0 = new Bundle();
            bundle0.putString("seq_num", this.zzdrh);
            bundle0.putString("slotid", this.zzdkf);
            bundle0.putBoolean("ismediation", false);
            bundle0.putLong("treq", this.zzdrl);
            bundle0.putLong("tresponse", this.zzdrm);
            bundle0.putLong("timp", this.zzdri);
            bundle0.putLong("tload", this.zzdrj);
            bundle0.putLong("pcc", this.zzdrk);
            bundle0.putLong("tfetch", this.zzdlt);
            ArrayList arrayList0 = new ArrayList();
            for(Object object1: this.zzdrg) {
                arrayList0.add(((zzavp)object1).toBundle());
            }
            bundle0.putParcelableArrayList("tclick", arrayList0);
            return bundle0;
        }
    }

    public final void zzan(boolean z) {
        synchronized(this.lock) {
            if(this.zzdrm != -1L) {
                this.zzdrj = this.zzbmz.elapsedRealtime();
            }
        }
    }

    public final void zze(zzuh zzuh0) {
        synchronized(this.lock) {
            this.zzdrl = this.zzbmz.elapsedRealtime();
            this.zzdrf.zza(zzuh0, this.zzdrl);
        }
    }

    public final void zzey(long v) {
        synchronized(this.lock) {
            this.zzdrm = v;
            if(this.zzdrm != -1L) {
                this.zzdrf.zzb(this);
            }
        }
    }

    public final void zzva() {
        synchronized(this.lock) {
            if(this.zzdrm != -1L && this.zzdri == -1L) {
                this.zzdri = this.zzbmz.elapsedRealtime();
                this.zzdrf.zzb(this);
            }
            this.zzdrf.zzva();
        }
    }

    public final void zzvb() {
        synchronized(this.lock) {
            if(this.zzdrm != -1L) {
                zzavp zzavp0 = new zzavp(this);
                zzavp0.zzuz();
                this.zzdrg.add(zzavp0);
                ++this.zzdrk;
                this.zzdrf.zzvb();
                this.zzdrf.zzb(this);
            }
        }
    }

    public final void zzvc() {
        synchronized(this.lock) {
            if(this.zzdrm != -1L && !this.zzdrg.isEmpty()) {
                zzavp zzavp0 = (zzavp)this.zzdrg.getLast();
                if(zzavp0.zzux() == -1L) {
                    zzavp0.zzuy();
                    this.zzdrf.zzb(this);
                }
            }
        }
    }

    public final String zzvd() {
        return this.zzdrh;
    }
}

