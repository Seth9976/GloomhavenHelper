package com.google.android.gms.internal.ads;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

public final class zzdgs {
    private final zzdfz zzgnx;
    private final zzdfw zzgts;
    @GuardedBy("this")
    private zzdgy zzgtt;
    @GuardedBy("this")
    private zzdof zzgtu;
    @GuardedBy("this")
    private int zzgtv;
    private final zzdgv zzgtw;
    private final LinkedList zzgtx;
    private final zzdnu zzgty;

    public zzdgs(zzdfz zzdfz0, zzdfw zzdfw0, zzdgv zzdgv0) {
        this.zzgtv = zzdgi.zzgtg;
        this.zzgty = new zzdgt(this);
        this.zzgnx = zzdfz0;
        this.zzgts = zzdfw0;
        this.zzgtw = zzdgv0;
        this.zzgtx = new LinkedList();
        zzdgu zzdgu0 = () -> synchronized(this) {
            this.zzd(this.zzgtt);
        };
        this.zzgts.zza(zzdgu0);
    }

    private final boolean zzasc() {
        return this.zzgtu == null || this.zzgtu.isDone();
    }

    // 检测为 Lambda 实现
    final void zzasd() [...]

    public final void zzb(zzdgy zzdgy0) {
        this.zzgtx.add(zzdgy0);
    }

    // 检测为 Lambda 实现
    final zzdof zzc(zzdgk zzdgk0) throws Exception [...]

    public final zzdof zzc(zzdgy zzdgy0) {
        synchronized(this) {
            if(this.zzasc()) {
                return null;
            }
            this.zzgtv = zzdgi.zzgti;
            if(this.zzgtt.zzaql() != null && zzdgy0.zzaql() != null && this.zzgtt.zzaql().equals(zzdgy0.zzaql())) {
                this.zzgtv = zzdgi.zzgth;
                return zzdnt.zzb(this.zzgtu, (zzdgk zzdgk0) -> zzdnt.zzaj(new zzdgw(zzdgk0, this.zzgtt)), zzdgy0.getExecutor());
            }
            return null;
        }
    }

    private final void zzd(zzdgy zzdgy0) {
        while(true) {
            if(!this.zzasc()) {
                if(zzdgy0 != null) {
                    this.zzgtx.add(zzdgy0);
                }
                return;
            }
            if(zzdgy0 == null && this.zzgtx.isEmpty()) {
                return;
            }
            if(zzdgy0 == null) {
                zzdgy0 = (zzdgy)this.zzgtx.remove();
            }
            if(zzdgy0.zzaql() != null) {
                zzdgj zzdgj0 = zzdgy0.zzaql();
                if(this.zzgnx.zzb(zzdgj0)) {
                    this.zzgtt = zzdgy0.zzaqm();
                    this.zzgtu = this.zzgtw.zza(this.zzgtt);
                    zzdof zzdof0 = this.zzgtu;
                    Executor executor0 = zzdgy0.getExecutor();
                    zzdnt.zza(zzdof0, this.zzgty, executor0);
                    return;
                }
            }
            zzdgy0 = null;
        }
    }
}

