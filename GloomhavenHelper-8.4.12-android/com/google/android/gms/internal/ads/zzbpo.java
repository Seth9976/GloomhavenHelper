package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import java.util.List;

public final class zzbpo implements zzeej {
    private final zzeew zzerc;
    private final zzeew zzetv;
    private final zzeew zzffi;
    private final zzeew zzfgx;
    private final zzeew zzfjr;
    private final zzeew zzfjs;
    private final zzeew zzfjt;
    private final zzeew zzfju;
    private final zzeew zzfjv;
    private final zzeew zzfjw;

    private zzbpo(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6, zzeew zzeew7, zzeew zzeew8, zzeew zzeew9) {
        this.zzfgx = zzeew0;
        this.zzffi = zzeew1;
        this.zzfjr = zzeew2;
        this.zzfjs = zzeew3;
        this.zzfjt = zzeew4;
        this.zzfju = zzeew5;
        this.zzfjv = zzeew6;
        this.zzerc = zzeew7;
        this.zzfjw = zzeew8;
        this.zzetv = zzeew9;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbpm(((zzdif)this.zzfgx.get()), ((zzazo)this.zzffi.get()), ((ApplicationInfo)this.zzfjr.get()), ((String)this.zzfjs.get()), ((List)this.zzfjt.get()), ((PackageInfo)this.zzfju.get()), zzeek.zzar(this.zzfjv), ((zzawh)this.zzerc.get()), ((String)this.zzfjw.get()), ((zzcyd)this.zzetv.get()));
    }

    public static zzbpo zza(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4, zzeew zzeew5, zzeew zzeew6, zzeew zzeew7, zzeew zzeew8, zzeew zzeew9) {
        return new zzbpo(zzeew0, zzeew1, zzeew2, zzeew3, zzeew4, zzeew5, zzeew6, zzeew7, zzeew8, zzeew9);
    }
}

