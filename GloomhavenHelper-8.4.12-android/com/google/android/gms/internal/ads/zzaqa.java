package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzaqa implements zzaqe {
    private static final Object lock;
    private final Context zzcgw;
    @VisibleForTesting
    private static zzaqe zzdjk;
    @VisibleForTesting
    private static zzaqe zzdjl;
    private final Object zzdjm;
    private final WeakHashMap zzdjn;
    private final zzazo zzdjo;
    private final ExecutorService zzxo;

    static {
        zzaqa.lock = new Object();
        zzaqa.zzdjk = null;
        zzaqa.zzdjl = null;
    }

    private zzaqa(Context context0) {
        this(context0, zzazo.zzxr());
    }

    private zzaqa(Context context0, zzazo zzazo0) {
        this.zzdjm = new Object();
        this.zzdjn = new WeakHashMap();
        this.zzxo = zzdkl.zzatr().zzdt(zzdkm.zzgym);
        if(context0.getApplicationContext() != null) {
            context0 = context0.getApplicationContext();
        }
        this.zzcgw = context0;
        this.zzdjo = zzazo0;
    }

    @VisibleForTesting
    private final Uri.Builder zza(String s, String s1, String s2, int v) {
        try {
            boolean z = Wrappers.packageManager(this.zzcgw).isCallerInstantApp();
            return new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("is_aia", Boolean.toString(z)).appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", Build.VERSION.RELEASE).appendQueryParameter("api", "33").appendQueryParameter("device", (Build.MODEL.startsWith(Build.MANUFACTURER) ? Build.MODEL : Build.MANUFACTURER + " " + Build.MODEL)).appendQueryParameter("js", this.zzdjo.zzbmj).appendQueryParameter("appid", "com.esotericsoftware.gloomhavenhelper").appendQueryParameter("exceptiontype", s).appendQueryParameter("stacktrace", s1).appendQueryParameter("eids", TextUtils.join(",", zzzx.zzqj())).appendQueryParameter("exceptionkey", s2).appendQueryParameter("cl", "300152424").appendQueryParameter("rc", "dev").appendQueryParameter("session_id", zzvh.zzpe()).appendQueryParameter("sampling_rate", Integer.toString(v)).appendQueryParameter("pb_tm", String.valueOf(zzabo.zzcvj.get()));
        }
        catch(Throwable throwable0) {
            zzazh.zzc("Error fetching instant app info", throwable0);
            return new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("is_aia", "false").appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", Build.VERSION.RELEASE).appendQueryParameter("api", "33").appendQueryParameter("device", (Build.MODEL.startsWith(Build.MANUFACTURER) ? Build.MODEL : Build.MANUFACTURER + " " + Build.MODEL)).appendQueryParameter("js", this.zzdjo.zzbmj).appendQueryParameter("appid", "com.esotericsoftware.gloomhavenhelper").appendQueryParameter("exceptiontype", s).appendQueryParameter("stacktrace", s1).appendQueryParameter("eids", TextUtils.join(",", zzzx.zzqj())).appendQueryParameter("exceptionkey", s2).appendQueryParameter("cl", "300152424").appendQueryParameter("rc", "dev").appendQueryParameter("session_id", zzvh.zzpe()).appendQueryParameter("sampling_rate", Integer.toString(v)).appendQueryParameter("pb_tm", String.valueOf(zzabo.zzcvj.get()));
        }
    }

    protected final void zza(Thread thread0, Throwable throwable0) {
        if(throwable0 != null) {
            Throwable throwable1 = throwable0;
            int v = 0;
            int v1;
            for(v1 = 0; throwable1 != null; v1 = v2) {
                StackTraceElement[] arr_stackTraceElement = throwable1.getStackTrace();
                int v2 = v1;
                int v3 = v;
                for(int v4 = 0; v4 < arr_stackTraceElement.length; ++v4) {
                    StackTraceElement stackTraceElement0 = arr_stackTraceElement[v4];
                    if(zzayx.zzeu(stackTraceElement0.getClassName())) {
                        v3 = 1;
                    }
                    if(this.getClass().getName().equals(stackTraceElement0.getClassName())) {
                        v2 = 1;
                    }
                }
                throwable1 = throwable1.getCause();
                v = v3;
            }
            if(v != 0 && v1 == 0) {
                this.zza(throwable0, "", 1.0f);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzaqe
    public final void zza(Throwable throwable0, String s) {
        this.zza(throwable0, s, 1.0f);
    }

    @Override  // com.google.android.gms.internal.ads.zzaqe
    public final void zza(Throwable throwable0, String s, float f) {
        if(zzayx.zzc(throwable0) == null) {
            return;
        }
        String s1 = throwable0.getClass().getName();
        StringWriter stringWriter0 = new StringWriter();
        zzdww.zza(throwable0, new PrintWriter(stringWriter0));
        String s2 = stringWriter0.toString();
        int v = 0;
        int v1 = 1;
        boolean z = Math.random() < ((double)f);
        if(f > 0.0f) {
            v1 = (int)(1.0f / f);
        }
        if(z) {
            ArrayList arrayList0 = new ArrayList();
            arrayList0.add(this.zza(s1, s2, s, v1).toString());
            int v2 = arrayList0.size();
            while(v < v2) {
                Object object0 = arrayList0.get(v);
                ++v;
                zzapz zzapz0 = new zzapz(new zzazl(), ((String)object0));
                this.zzxo.execute(zzapz0);
            }
        }
    }

    public static zzaqe zzc(Context context0, zzazo zzazo0) {
        synchronized(zzaqa.lock) {
            if(zzaqa.zzdjl == null) {
                if(((Boolean)zzabo.zzcvl.get()).booleanValue()) {
                    zzaqa zzaqa0 = new zzaqa(context0, zzazo0);
                    Thread thread0 = Looper.getMainLooper().getThread();
                    if(thread0 != null) {
                        synchronized(zzaqa0.zzdjm) {
                            zzaqa0.zzdjn.put(thread0, Boolean.TRUE);
                        }
                        thread0.setUncaughtExceptionHandler(new zzaqb(zzaqa0, thread0.getUncaughtExceptionHandler()));
                    }
                    Thread.setDefaultUncaughtExceptionHandler(new zzaqc(zzaqa0, Thread.getDefaultUncaughtExceptionHandler()));
                    zzaqa.zzdjl = zzaqa0;
                }
                else {
                    zzaqa.zzdjl = new zzaqd();
                }
            }
            return zzaqa.zzdjl;
        }
    }

    public static zzaqe zzu(Context context0) {
        synchronized(zzaqa.lock) {
            if(zzaqa.zzdjk == null) {
                zzaqa.zzdjk = ((Boolean)zzabo.zzcvl.get()).booleanValue() ? new zzaqa(context0) : new zzaqd();
            }
            return zzaqa.zzdjk;
        }
    }
}

