package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

public final class zzcia implements zzeej {
    private final zzeew zzekz;
    private final zzeew zzelv;
    private final zzeew zzfev;
    private final zzeew zzffi;
    private final zzeew zzfgm;
    private final zzeew zzfst;
    private final zzeew zzfxf;
    private final zzeew zzfxg;

    public zzcia(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6, zzeew zzeew7) {
        this.zzfgm = zzeew0;
        this.zzfxf = zzeew1;
        this.zzfxg = zzeew2;
        this.zzfev = zzeew3;
        this.zzekz = zzeew4;
        this.zzfst = zzeew5;
        this.zzelv = zzeew6;
        this.zzffi = zzeew7;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcho(((Executor)this.zzfgm.get()), ((Context)this.zzfxf.get()), ((WeakReference)this.zzfxg.get()), ((Executor)this.zzfev.get()), ((zzcnk)this.zzekz.get()), ((ScheduledExecutorService)this.zzfst.get()), ((zzcgy)this.zzelv.get()), ((zzazo)this.zzffi.get()));
    }
}

