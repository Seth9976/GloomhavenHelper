package com.google.android.gms.internal.ads;

import java.util.Random;
import java.util.WeakHashMap;

public final class zzvh {
    private static zzvh zzcef;
    private final zzayx zzceg;
    private final zzus zzceh;
    private final String zzcei;
    private final zzzo zzcej;
    private final zzzq zzcek;
    private final zzzt zzcel;
    private final zzazo zzcem;
    private final Random zzcen;
    private final WeakHashMap zzceo;

    static {
        zzvh.zzcef = new zzvh();
    }

    protected zzvh() {
        this(new zzayx(), new zzus(new zzuf(), new zzuc(), new zzyh(), new zzaev(), new zzasf(), new zzatj(), new zzapc(), new zzaeu()), new zzzo(), new zzzq(), new zzzt(), "1187786203642853288", new zzazo(0, 20089000, true), new Random(), new WeakHashMap());
    }

    private zzvh(zzayx zzayx0, zzus zzus0, zzzo zzzo0, zzzq zzzq0, zzzt zzzt0, String s, zzazo zzazo0, Random random0, WeakHashMap weakHashMap0) {
        this.zzceg = zzayx0;
        this.zzceh = zzus0;
        this.zzcej = zzzo0;
        this.zzcek = zzzq0;
        this.zzcel = zzzt0;
        this.zzcei = s;
        this.zzcem = zzazo0;
        this.zzcen = random0;
        this.zzceo = weakHashMap0;
    }

    public static zzayx zzoz() {
        return zzvh.zzcef.zzceg;
    }

    public static zzus zzpa() {
        return zzvh.zzcef.zzceh;
    }

    public static zzzq zzpb() {
        return zzvh.zzcef.zzcek;
    }

    public static zzzo zzpc() {
        return zzvh.zzcef.zzcej;
    }

    public static zzzt zzpd() {
        return zzvh.zzcef.zzcel;
    }

    public static String zzpe() {
        return zzvh.zzcef.zzcei;
    }

    public static zzazo zzpf() {
        return zzvh.zzcef.zzcem;
    }

    public static Random zzpg() {
        return zzvh.zzcef.zzcen;
    }

    public static WeakHashMap zzph() {
        return zzvh.zzcef.zzceo;
    }
}

