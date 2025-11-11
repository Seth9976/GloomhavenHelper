package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class zzgj implements zzhe, zzhh {
    private int index;
    private int state;
    private final int zzacj;
    private zzhg zzack;
    private zzmn zzacl;
    private long zzacm;
    private boolean zzacn;
    private boolean zzaco;

    public zzgj(int v) {
        this.zzacj = v;
        this.zzacn = true;
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public final void disable() {
        zzob.checkState(this.state == 1);
        this.state = 0;
        this.zzacl = null;
        this.zzaco = false;
        this.zzec();
    }

    protected final int getIndex() {
        return this.index;
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public final int getState() {
        return this.state;
    }

    @Override  // com.google.android.gms.internal.ads.zzhe, com.google.android.gms.internal.ads.zzhh
    public final int getTrackType() {
        return this.zzacj;
    }

    protected void onStarted() throws zzgk {
    }

    protected void onStopped() throws zzgk {
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public final void setIndex(int v) {
        this.index = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public final void start() throws zzgk {
        zzob.checkState(this.state == 1);
        this.state = 2;
        this.onStarted();
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public final void stop() throws zzgk {
        zzob.checkState(this.state == 2);
        this.state = 1;
        this.onStopped();
    }

    protected final int zza(zzhb zzhb0, zziv zziv0, boolean z) {
        int v = this.zzacl.zzb(zzhb0, zziv0, z);
        if(v == -4) {
            if(zziv0.zzgd()) {
                this.zzacn = true;
                return this.zzaco ? -4 : -3;
            }
            zziv0.zzamu += this.zzacm;
            return -4;
        }
        if(v == -5) {
            zzgz zzgz0 = zzhb0.zzagi;
            if(zzgz0.zzagc != 0x7FFFFFFFFFFFFFFFL) {
                zzhb0.zzagi = zzgz0.zzds(zzgz0.zzagc + this.zzacm);
            }
        }
        return v;
    }

    @Override  // com.google.android.gms.internal.ads.zzgp
    public void zza(int v, Object object0) throws zzgk {
    }

    protected void zza(long v, boolean z) throws zzgk {
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public final void zza(zzhg zzhg0, zzgz[] arr_zzgz, zzmn zzmn0, long v, boolean z, long v1) throws zzgk {
        zzob.checkState(this.state == 0);
        this.zzack = zzhg0;
        this.state = 1;
        this.zze(z);
        this.zza(arr_zzgz, zzmn0, v1);
        this.zza(v, z);
    }

    protected void zza(zzgz[] arr_zzgz, long v) throws zzgk {
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public final void zza(zzgz[] arr_zzgz, zzmn zzmn0, long v) throws zzgk {
        zzob.checkState(!this.zzaco);
        this.zzacl = zzmn0;
        this.zzacn = false;
        this.zzacm = v;
        this.zza(arr_zzgz, v);
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public final void zzdm(long v) throws zzgk {
        this.zzaco = false;
        this.zzacn = false;
        this.zza(v, false);
    }

    protected final void zzdn(long v) {
        this.zzacl.zzeh(v - this.zzacm);
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public final zzhh zzdu() {
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public zzof zzdv() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public final zzmn zzdw() {
        return this.zzacl;
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public final boolean zzdx() {
        return this.zzacn;
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public final void zzdy() {
        this.zzaco = true;
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public final boolean zzdz() {
        return this.zzaco;
    }

    protected void zze(boolean z) throws zzgk {
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public final void zzea() throws IOException {
        this.zzacl.zzhp();
    }

    @Override  // com.google.android.gms.internal.ads.zzhh
    public int zzeb() throws zzgk {
        return 0;
    }

    protected void zzec() {
    }

    protected final zzhg zzed() {
        return this.zzack;
    }

    // 去混淆评级： 低(20)
    protected final boolean zzee() {
        return this.zzacn ? this.zzaco : this.zzacl.isReady();
    }
}

