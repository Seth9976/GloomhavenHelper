package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;

final class zzdgx {
    private final long zzgub;
    private final zzdha zzguc;
    private long zzgud;
    private int zzgue;
    private int zzguf;
    private int zzgug;

    public zzdgx() {
        this.zzguc = new zzdha();
        this.zzgue = 0;
        this.zzguf = 0;
        this.zzgug = 0;
        this.zzgub = zzq.zzlc().currentTimeMillis();
        this.zzgud = this.zzgub;
    }

    public final long getCreationTimeMillis() {
        return this.zzgub;
    }

    public final long zzark() {
        return this.zzgud;
    }

    public final int zzarl() {
        return this.zzgue;
    }

    public final String zzarw() {
        return "Created: " + this.zzgub + " Last accessed: " + this.zzgud + " Accesses: " + this.zzgue + "\nEntries retrieved: Valid: " + this.zzguf + " Stale: " + this.zzgug;
    }

    public final void zzase() {
        this.zzgud = zzq.zzlc().currentTimeMillis();
        ++this.zzgue;
    }

    public final void zzasf() {
        ++this.zzguf;
        this.zzguc.zzguh = true;
    }

    public final void zzasg() {
        ++this.zzgug;
        ++this.zzguc.zzgug;
    }

    public final zzdha zzash() {
        zzdha zzdha0 = (zzdha)this.zzguc.clone();
        this.zzguc.zzguh = false;
        this.zzguc.zzgug = 0;
        return zzdha0;
    }
}

