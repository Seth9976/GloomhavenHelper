package com.google.android.gms.internal.ads;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class zzdlj {
    @NullableDecl
    private static final Object zzgzy;
    @NullableDecl
    private static final Method zzgzz;
    @NullableDecl
    private static final Method zzhaa;

    static {
        Object object0 = zzdlj.zzatt();
        zzdlj.zzgzy = object0;
        Method method0 = null;
        zzdlj.zzgzz = object0 == null ? null : zzdlj.zza("getStackTraceElement", new Class[]{Throwable.class, Integer.TYPE});
        if(zzdlj.zzgzy != null) {
            method0 = zzdlj.zzatu();
        }
        zzdlj.zzhaa = method0;
    }

    public static String zza(Throwable throwable0) {
        StringWriter stringWriter0 = new StringWriter();
        zzdww.zza(throwable0, new PrintWriter(stringWriter0));
        return stringWriter0.toString();
    }

    @NullableDecl
    private static Method zza(String s, Class[] arr_class) throws ThreadDeath {
        try {
            return Class.forName("sun.misc.JavaLangAccess", false, null).getMethod(s, arr_class);
        }
        catch(ThreadDeath threadDeath0) {
            throw threadDeath0;
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    @NullableDecl
    private static Object zzatt() {
        try {
            return Class.forName("sun.misc.SharedSecrets", false, null).getMethod("getJavaLangAccess").invoke(null);
        }
        catch(ThreadDeath threadDeath0) {
            throw threadDeath0;
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    @NullableDecl
    private static Method zzatu() {
        try {
            Method method0 = zzdlj.zza("getStackTraceDepth", new Class[]{Throwable.class});
            if(method0 == null) {
                return null;
            }
            method0.invoke(zzdlj.zzatt(), new Throwable());
            return method0;
        }
        catch(UnsupportedOperationException | IllegalAccessException | InvocationTargetException unused_ex) {
            return null;
        }
    }

    public static void zzg(Throwable throwable0) {
        zzdlg.checkNotNull(throwable0);
        if(throwable0 instanceof RuntimeException) {
            throw (RuntimeException)throwable0;
        }
        if(throwable0 instanceof Error) {
            throw (Error)throwable0;
        }
    }

    @Deprecated
    public static RuntimeException zzh(Throwable throwable0) {
        zzdlj.zzg(throwable0);
        throw new RuntimeException(throwable0);
    }
}

