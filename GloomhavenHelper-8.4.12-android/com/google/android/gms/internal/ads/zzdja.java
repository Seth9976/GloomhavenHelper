package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public final class zzdja {
    private static HashMap zzgwr;
    private zzdkb zzgws;
    private Object zzgwt;
    private final Context zzur;
    private final zzdix zzuu;

    static {
        zzdja.zzgwr = new HashMap();
    }

    public zzdja(@NonNull Context context0, @NonNull zzdix zzdix0) {
        this.zzur = context0;
        this.zzuu = zzdix0;
    }

    private final void close() {
        synchronized(this) {
            long v1 = System.currentTimeMillis();
            try {
                this.zzgwt.getClass().getDeclaredMethod("close").invoke(this.zzgwt);
                this.zza(3001, null, v1);
            }
            catch(Exception exception0) {
                this.zzb(2003, v1, exception0);
            }
        }
    }

    private final Class zza(zzdkb zzdkb0) {
        File file0 = zzdkb0.zzatp();
        File file1 = zzdkb0.zzato();
        long v = System.currentTimeMillis();
        try {
            if(!file0.exists()) {
                file0.mkdirs();
            }
            return new DexClassLoader(file1.getAbsolutePath(), file0.getAbsolutePath(), null, this.zzur.getClassLoader()).loadClass("com.google.ccc.abuse.droidguard.DroidGuard");
        }
        catch(ClassNotFoundException | SecurityException | IllegalArgumentException classNotFoundException0) {
            this.zzb(2008, v, classNotFoundException0);
            return null;
        }
    }

    private final Object zza(Class class0, zzdkb zzdkb0) {
        long v;
        try {
            v = System.currentTimeMillis();
            return class0.getDeclaredConstructor(Context.class, String.class, byte[].class, Object.class, Bundle.class, Integer.TYPE).newInstance(this.zzur, "msa-r", zzdkb0.zzatq(), null, new Bundle(), 2);
        }
        catch(IllegalAccessException illegalAccessException0) {
            this.zzb(2004, v, illegalAccessException0);
            return null;
        }
        catch(InstantiationException instantiationException0) {
            this.zzb(2004, v, instantiationException0);
            return null;
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            this.zzb(2004, v, noSuchMethodException0);
            return null;
        }
        catch(InvocationTargetException invocationTargetException0) {
            this.zzb(2004, v, invocationTargetException0);
            return null;
        }
    }

    private final void zza(int v, Exception exception0, long v1) {
        this.zzuu.zza(v, System.currentTimeMillis() - v1, exception0);
    }

    public final String zza(Context context0, String s, String s1, View view0, Activity activity0) {
        synchronized(this) {
            HashMap hashMap0 = new HashMap();
            hashMap0.put("ctx", context0);
            hashMap0.put("clickString", s1);
            hashMap0.put("aid", null);
            hashMap0.put("view", view0);
            hashMap0.put("act", activity0);
            return zzdja.zzj(this.zzb(null, hashMap0));
        }
    }

    public final void zza(String s, MotionEvent motionEvent0) {
        synchronized(this) {
            long v1 = System.currentTimeMillis();
            try {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("aid", null);
                hashMap0.put("evt", motionEvent0);
                this.zzgwt.getClass().getDeclaredMethod("he", Map.class).invoke(this.zzgwt, hashMap0);
                this.zza(3003, null, v1);
            }
            catch(IllegalAccessException | NoSuchMethodException | InvocationTargetException illegalAccessException0) {
                this.zzb(2005, v1, illegalAccessException0);
            }
        }
    }

    public final zzdkb zzatb() {
        return this.zzgws;
    }

    private final void zzb(int v, long v1, Exception exception0) {
        this.zza(v, exception0, v1);
    }

    private final byte[] zzb(Map map0, Map map1) {
        long v = System.currentTimeMillis();
        Object object0 = this.zzgwt;
        if(object0 == null) {
            return null;
        }
        try {
            return (byte[])object0.getClass().getDeclaredMethod("xss", Map.class, Map.class).invoke(this.zzgwt, null, map1);
        }
        catch(IllegalAccessException illegalAccessException0) {
            this.zzb(2007, v, illegalAccessException0);
            return null;
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            this.zzb(2007, v, noSuchMethodException0);
            return null;
        }
        catch(InvocationTargetException invocationTargetException0) {
            this.zzb(2007, v, invocationTargetException0);
            return null;
        }
    }

    public final zzdjb zzb(@NonNull zzdkb zzdkb0) {
        long v = System.currentTimeMillis();
        if(zzdkb0.zzatn() == null) {
            return new zzdjb(2, "mc");
        }
        Class class0 = (Class)zzdja.zzgwr.get("");
        if(class0 == null) {
            class0 = this.zza(zzdkb0);
            if(class0 != null) {
                zzdja.zzgwr.put("", class0);
            }
        }
        if(class0 == null) {
            return new zzdjb(2, "lc");
        }
        Object object0 = this.zza(class0, zzdkb0);
        if(object0 == null) {
            return new zzdjb(3, "it");
        }
        if(this.zzx(object0)) {
            int v1 = this.zzz(object0);
            if(v1 != 0) {
                this.zzuu.zza(4001, System.currentTimeMillis() - v, Integer.toString(v1), null);
                return new zzdjb(5, "ci" + v1);
            }
            this.zzy(object0);
            this.zzgws = zzdkb0;
            this.zza(3000, null, v);
            return null;
        }
        this.zza(4000, null, v);
        return new zzdjb(4, "ri");
    }

    public final String zzb(Context context0, String s, View view0, Activity activity0) {
        synchronized(this) {
            HashMap hashMap0 = new HashMap();
            hashMap0.put("ctx", context0);
            hashMap0.put("aid", null);
            hashMap0.put("view", view0);
            hashMap0.put("act", activity0);
            return zzdja.zzj(this.zzb(null, hashMap0));
        }
    }

    private static String zzj(byte[] arr_b) {
        return arr_b == null ? null : Base64.encodeToString(((zzf)(((zzdyz)zzf.zzbj().zza(zzbz.zzkz).zzi(zzdxn.zzt(arr_b)).zzbcx()))).toByteArray(), 11);
    }

    public final String zzu(Context context0, String s) {
        synchronized(this) {
            HashMap hashMap0 = new HashMap();
            hashMap0.put("ctx", context0);
            hashMap0.put("aid", null);
            return zzdja.zzj(this.zzb(null, hashMap0));
        }
    }

    private final boolean zzx(Object object0) {
        long v;
        try {
            v = System.currentTimeMillis();
            return ((Boolean)object0.getClass().getDeclaredMethod("init").invoke(object0)).booleanValue();
        }
        catch(IllegalAccessException | NoSuchMethodException | InvocationTargetException illegalAccessException0) {
            this.zzb(2001, v, illegalAccessException0);
            return false;
        }
    }

    private final void zzy(Object object0) {
        synchronized(this) {
            if(this.zzgwt != null) {
                this.close();
            }
            this.zzgwt = object0;
        }
    }

    private final int zzz(Object object0) {
        synchronized(this) {
            long v1 = System.currentTimeMillis();
            try {
                return (int)(((Integer)object0.getClass().getDeclaredMethod("lcs").invoke(object0)));
            }
            catch(Exception exception0) {
                this.zzb(2006, v1, exception0);
                return 0;
            }
        }
    }
}

