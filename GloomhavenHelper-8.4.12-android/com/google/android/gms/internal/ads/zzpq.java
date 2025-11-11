package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.ArrayList;
import java.util.WeakHashMap;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzpq {
    private final Object lock;
    private final zzazo zzblu;
    private final WeakHashMap zzboc;
    private final ArrayList zzbod;
    private final zzaja zzboe;
    private final Context zzyz;

    public zzpq(Context context0, zzazo zzazo0) {
        this.lock = new Object();
        this.zzboc = new WeakHashMap();
        this.zzbod = new ArrayList();
        this.zzyz = context0.getApplicationContext();
        this.zzblu = zzazo0;
        this.zzboe = new zzaja(context0.getApplicationContext(), zzazo0, ((String)zzvh.zzpd().zzd(zzzx.zzchb)));
    }
}

