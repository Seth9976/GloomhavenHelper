package com.google.android.gms.internal.ads;

import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

public abstract class zzzi {
    private final String zzcc;
    private final int zzcgk;
    private final Object zzcgl;

    private zzzi(int v, String s, Object object0) {
        this.zzcgk = v;
        this.zzcc = s;
        this.zzcgl = object0;
        zzvh.zzpc().zza(this);
    }

    zzzi(int v, String s, Object object0, zzzl zzzl0) {
        this(v, s, object0);
    }

    public final String getKey() {
        return this.zzcc;
    }

    public final int getSource() {
        return this.zzcgk;
    }

    public static zzzi zza(int v, String s, float f) {
        return new zzzm(1, s, 0.0f);
    }

    public static zzzi zza(int v, String s, int v1) {
        return new zzzk(1, s, v1);
    }

    public static zzzi zza(int v, String s, Boolean boolean0) {
        return new zzzl(1, s, boolean0);
    }

    public static zzzi zza(int v, String s, String s1) {
        return new zzzp(1, s, s1);
    }

    protected abstract Object zza(SharedPreferences arg1);

    public abstract Object zza(Bundle arg1);

    public abstract void zza(SharedPreferences.Editor arg1, Object arg2);

    public static zzzi zzb(int v, String s) {
        zzzi zzzi0 = zzzi.zza(1, s, null);
        zzvh.zzpc().zzc(zzzi0);
        return zzzi0;
    }

    public static zzzi zzb(int v, String s, long v1) {
        return new zzzn(1, s, v1);
    }

    protected abstract Object zzb(JSONObject arg1);

    public final Object zzqi() {
        return this.zzcgl;
    }
}

