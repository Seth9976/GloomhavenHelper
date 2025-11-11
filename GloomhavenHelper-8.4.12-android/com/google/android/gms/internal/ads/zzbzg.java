package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;

public final class zzbzg {
    @Nullable
    private final zzads zzfps;
    @Nullable
    private final zzadr zzfpt;
    @Nullable
    private final zzaeg zzfpu;
    @Nullable
    private final zzaef zzfpv;
    @Nullable
    private final zzaht zzfpw;
    private final SimpleArrayMap zzfpx;
    private final SimpleArrayMap zzfpy;
    public static final zzbzg zzfpz;

    static {
        zzbzg.zzfpz = new zzbzi().zzala();
    }

    private zzbzg(zzbzi zzbzi0) {
        this.zzfps = zzbzi0.zzfps;
        this.zzfpt = zzbzi0.zzfpt;
        this.zzfpu = zzbzi0.zzfpu;
        this.zzfpx = new SimpleArrayMap(zzbzi0.zzfpx);
        this.zzfpy = new SimpleArrayMap(zzbzi0.zzfpy);
        this.zzfpv = zzbzi0.zzfpv;
        this.zzfpw = zzbzi0.zzfpw;
    }

    zzbzg(zzbzi zzbzi0, zzbzj zzbzj0) {
        this(zzbzi0);
    }

    @Nullable
    public final zzads zzakt() {
        return this.zzfps;
    }

    @Nullable
    public final zzadr zzaku() {
        return this.zzfpt;
    }

    @Nullable
    public final zzaeg zzakv() {
        return this.zzfpu;
    }

    @Nullable
    public final zzaef zzakw() {
        return this.zzfpv;
    }

    @Nullable
    public final zzaht zzakx() {
        return this.zzfpw;
    }

    public final ArrayList zzaky() {
        ArrayList arrayList0 = new ArrayList();
        if(this.zzfpu != null) {
            arrayList0.add("6");
        }
        if(this.zzfps != null) {
            arrayList0.add("1");
        }
        if(this.zzfpt != null) {
            arrayList0.add("2");
        }
        if(this.zzfpx.size() > 0) {
            arrayList0.add("3");
        }
        if(this.zzfpw != null) {
            arrayList0.add("7");
        }
        return arrayList0;
    }

    public final ArrayList zzakz() {
        ArrayList arrayList0 = new ArrayList(this.zzfpx.size());
        for(int v = 0; v < this.zzfpx.size(); ++v) {
            arrayList0.add(((String)this.zzfpx.keyAt(v)));
        }
        return arrayList0;
    }

    @Nullable
    public final zzady zzga(String s) {
        return (zzady)this.zzfpx.get(s);
    }

    @Nullable
    public final zzadx zzgb(String s) {
        return (zzadx)this.zzfpy.get(s);
    }
}

