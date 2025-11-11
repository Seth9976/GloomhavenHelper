package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import jeb.synthetic.TWR;

public class zzbw {
    public static final Uri zza;
    public static final Pattern zzb;
    public static final Pattern zzc;
    private static final Uri zzd;
    private static final AtomicBoolean zze;
    private static HashMap zzf;
    private static final HashMap zzg;
    private static final HashMap zzh;
    private static final HashMap zzi;
    private static final HashMap zzj;
    private static Object zzk;
    private static boolean zzl;
    private static String[] zzm;

    static {
        zzbw.zza = Uri.parse("content://com.google.android.gsf.gservices");
        zzbw.zzd = Uri.parse("content://com.google.android.gsf.gservices/prefix");
        zzbw.zzb = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
        zzbw.zzc = Pattern.compile("^(0|false|f|off|no|n)$", 2);
        zzbw.zze = new AtomicBoolean();
        zzbw.zzg = new HashMap();
        zzbw.zzh = new HashMap();
        zzbw.zzi = new HashMap();
        zzbw.zzj = new HashMap();
        zzbw.zzm = new String[0];
    }

    public static String zza(ContentResolver contentResolver0, String s, String s1) {
        Object object0;
        String s4;
        synchronized(zzbw.class) {
            zzbw.zza(contentResolver0);
            object0 = zzbw.zzk;
            if(zzbw.zzf.containsKey(s)) {
                String s2 = (String)zzbw.zzf.get(s);
                if(s2 == null) {
                    s2 = null;
                }
                return s2;
            }
            String[] arr_s = zzbw.zzm;
            for(int v1 = 0; v1 < arr_s.length; ++v1) {
                if(s.startsWith(arr_s[v1])) {
                    if(!zzbw.zzl || zzbw.zzf.isEmpty()) {
                        zzbw.zzf.putAll(zzbw.zza(contentResolver0, zzbw.zzm));
                        zzbw.zzl = true;
                        if(zzbw.zzf.containsKey(s)) {
                            String s3 = (String)zzbw.zzf.get(s);
                            if(s3 == null) {
                                s3 = null;
                            }
                            return s3;
                        }
                    }
                    return null;
                }
            }
        }
        Cursor cursor0 = contentResolver0.query(zzbw.zza, null, null, new String[]{s}, null);
        if(cursor0 == null) {
            return null;
        }
        try {
            if(cursor0.moveToFirst()) {
                s4 = cursor0.getString(1);
                if(s4 != null && s4.equals(null)) {
                    s4 = null;
                }
                zzbw.zza(object0, s, s4);
                if(s4 == null) {
                    goto label_40;
                }
                goto label_41;
            }
            goto label_43;
        }
        catch(Throwable throwable0) {
            goto label_46;
        }
    label_40:
        s4 = null;
    label_41:
        cursor0.close();
        return s4;
        try {
        label_43:
            zzbw.zza(object0, s, null);
        }
        catch(Throwable throwable0) {
        label_46:
            TWR.safeClose$NT(cursor0, throwable0);
            throw throwable0;
        }
        cursor0.close();
        return null;
    }

    private static Map zza(ContentResolver contentResolver0, String[] arr_s) {
        Cursor cursor0 = contentResolver0.query(zzbw.zzd, null, null, arr_s, null);
        Map map0 = new TreeMap();
        if(cursor0 == null) {
            return map0;
        }
        while(true) {
            try {
                if(!cursor0.moveToNext()) {
                    break;
                }
                ((TreeMap)map0).put(cursor0.getString(0), cursor0.getString(1));
            }
            catch(Throwable throwable0) {
                TWR.safeClose$NT(cursor0, throwable0);
                throw throwable0;
            }
        }
        cursor0.close();
        return map0;
    }

    static AtomicBoolean zza() {
        return zzbw.zze;
    }

    private static void zza(ContentResolver contentResolver0) {
        if(zzbw.zzf == null) {
            zzbw.zze.set(false);
            zzbw.zzf = new HashMap();
            zzbw.zzk = new Object();
            zzbw.zzl = false;
            zzbv zzbv0 = new zzbv(null);
            contentResolver0.registerContentObserver(zzbw.zza, true, zzbv0);
            return;
        }
        if(zzbw.zze.getAndSet(false)) {
            zzbw.zzf.clear();
            zzbw.zzg.clear();
            zzbw.zzh.clear();
            zzbw.zzi.clear();
            zzbw.zzj.clear();
            zzbw.zzk = new Object();
            zzbw.zzl = false;
        }
    }

    private static void zza(Object object0, String s, String s1) {
        synchronized(zzbw.class) {
            if(object0 == zzbw.zzk) {
                zzbw.zzf.put(s, s1);
            }
        }
    }
}

