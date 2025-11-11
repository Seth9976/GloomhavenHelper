package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;
import java.util.LinkedList;

final class zzdga {
    private final int maxEntries;
    private final LinkedList zzgru;
    private final int zzgrv;
    private final zzdgx zzgrw;

    public zzdga(int v, int v1) {
        this.zzgru = new LinkedList();
        this.maxEntries = v;
        this.zzgrv = v1;
        this.zzgrw = new zzdgx();
    }

    public final long getCreationTimeMillis() {
        return this.zzgrw.getCreationTimeMillis();
    }

    public final int size() {
        this.zzaro();
        return this.zzgru.size();
    }

    public final zzdgk zzarj() {
        this.zzgrw.zzase();
        this.zzaro();
        if(this.zzgru.isEmpty()) {
            return null;
        }
        zzdgk zzdgk0 = (zzdgk)this.zzgru.remove();
        if(zzdgk0 != null) {
            this.zzgrw.zzasf();
        }
        return zzdgk0;
    }

    public final long zzark() {
        return this.zzgrw.zzark();
    }

    public final int zzarl() {
        return this.zzgrw.zzarl();
    }

    public final String zzarm() {
        return this.zzgrw.zzarw();
    }

    public final zzdha zzarn() {
        return this.zzgrw.zzash();
    }

    private final void zzaro() {
        while(!this.zzgru.isEmpty()) {
            zzdgk zzdgk0 = (zzdgk)this.zzgru.getFirst();
            if(zzq.zzlc().currentTimeMillis() - zzdgk0.zzgtm >= ((long)this.zzgrv)) {
                this.zzgrw.zzasg();
                this.zzgru.remove();
                continue;
            }
            return;
        }
    }

    public final boolean zzb(zzdgk zzdgk0) {
        this.zzgrw.zzase();
        this.zzaro();
        if(this.zzgru.size() == this.maxEntries) {
            return false;
        }
        this.zzgru.add(zzdgk0);
        return true;
    }
}

