package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.Executor;

public final class zzbjs {
    private final Executor executor;
    private final String zzbmi;
    private final zzakt zzfdw;
    private zzbkb zzfdx;
    private final zzafz zzfdy;
    private final zzafz zzfdz;

    public zzbjs(String s, zzakt zzakt0, Executor executor0) {
        this.zzfdy = new zzbjv(this);
        this.zzfdz = new zzbjx(this);
        this.zzbmi = s;
        this.zzfdw = zzakt0;
        this.executor = executor0;
    }

    public final void zza(zzbkb zzbkb0) {
        this.zzfdw.zzc("/updateActiveView", this.zzfdy);
        this.zzfdw.zzc("/untrackActiveViewUnit", this.zzfdz);
        this.zzfdx = zzbkb0;
    }

    public final void zzafv() {
        this.zzfdw.zzd("/updateActiveView", this.zzfdy);
        this.zzfdw.zzd("/untrackActiveViewUnit", this.zzfdz);
    }

    public final void zzd(zzbdv zzbdv0) {
        zzbdv0.zza("/updateActiveView", this.zzfdy);
        zzbdv0.zza("/untrackActiveViewUnit", this.zzfdz);
    }

    public final void zze(zzbdv zzbdv0) {
        zzbdv0.zzb("/updateActiveView", this.zzfdy);
        zzbdv0.zzb("/untrackActiveViewUnit", this.zzfdz);
    }

    private final boolean zzl(@Nullable Map map0) {
        if(map0 == null) {
            return false;
        }
        String s = (String)map0.get("hashCode");
        return !TextUtils.isEmpty(s) && s.equals(this.zzbmi);
    }
}

