package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamite.DynamiteModule;
import java.lang.reflect.Method;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@ParametersAreNonnullByDefault
public final class zzaui {
    private final AtomicReference zzdqj;
    private final Object zzdqk;
    @Nullable
    @GuardedBy("gmpAppIdLock")
    private String zzdql;
    @Nullable
    @GuardedBy("gmpAppIdLock")
    private String zzdqm;
    @VisibleForTesting
    private final AtomicBoolean zzdqn;
    private final AtomicInteger zzdqo;
    private final AtomicReference zzdqp;
    private final AtomicReference zzdqq;
    private final ConcurrentMap zzdqr;
    private final AtomicReference zzdqs;
    @GuardedBy("proxyReference")
    private final BlockingQueue zzdqt;
    private final Object zzdqu;

    public zzaui() {
        this.zzdqj = new AtomicReference(null);
        this.zzdqk = new Object();
        this.zzdql = null;
        this.zzdqm = null;
        this.zzdqn = new AtomicBoolean(false);
        this.zzdqo = new AtomicInteger(-1);
        this.zzdqp = new AtomicReference(null);
        this.zzdqq = new AtomicReference(null);
        this.zzdqr = new ConcurrentHashMap(9);
        this.zzdqs = new AtomicReference(null);
        this.zzdqt = new ArrayBlockingQueue(20);
        this.zzdqu = new Object();
    }

    private final Object zza(String s, Context context0) {
        if(!this.zza(context0, "com.google.android.gms.measurement.AppMeasurement", this.zzdqp, true)) {
            return null;
        }
        Method method0 = this.zzm(context0, s);
        try {
            return method0.invoke(this.zzdqp.get());
        }
        catch(Exception exception0) {
            this.zza(exception0, s, true);
            return null;
        }
    }

    private final Object zza(String s, @Nullable Object object0, zzauv zzauv0) {
        synchronized(this.zzdqs) {
            if(((zzbgd)this.zzdqs.get()) != null) {
                try {
                    return zzauv0.zzb(((zzbgd)this.zzdqs.get()));
                }
                catch(Exception exception0) {
                    this.zza(exception0, s, false);
                }
            }
            return object0;
        }
    }

    private final void zza(Context context0, String s, String s1) {
        if(!this.zza(context0, "com.google.android.gms.measurement.AppMeasurement", this.zzdqp, true)) {
            return;
        }
        Method method0 = this.zzl(context0, s1);
        try {
            method0.invoke(this.zzdqp.get(), s);
            zzawf.zzee(("Invoke Firebase method " + s1 + ", Ad Unit Id: " + s));
        }
        catch(Exception exception0) {
            this.zza(exception0, s1, false);
        }
    }

    private final void zza(Context context0, String s, String s1, @Nullable Bundle bundle0) {
        if(!this.zzad(context0)) {
            return;
        }
        Bundle bundle1 = zzaui.zzl(s1, s);
        if(bundle0 != null) {
            bundle1.putAll(bundle0);
        }
        if(zzaui.zzae(context0)) {
            this.zza("logEventInternal", new zzauj(s, bundle1));
            return;
        }
        if(!this.zza(context0, "com.google.android.gms.measurement.AppMeasurement", this.zzdqp, true)) {
            return;
        }
        Method method0 = this.zzak(context0);
        try {
            method0.invoke(this.zzdqp.get(), "am", s, bundle1);
        }
        catch(Exception exception0) {
            this.zza(exception0, "logEventInternal", true);
        }
    }

    private final void zza(Exception exception0, String s, boolean z) {
        if(!this.zzdqn.get()) {
            zzawf.zzfa(("Invoke Firebase method " + s + " error."));
            if(z) {
                zzawf.zzfa("The Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires the latest Firebase SDK jar, but Firebase SDK is either missing or out of date");
                this.zzdqn.set(true);
            }
        }
    }

    private final void zza(String s, zzauy zzauy0) {
        synchronized(this.zzdqs) {
            FutureTask futureTask0 = new FutureTask(() -> if(((zzbgd)this.zzdqs.get()) != null) {
                try {
                    zzauy0.zza(((zzbgd)this.zzdqs.get()));
                }
                catch(Exception exception0) {
                    this.zza(exception0, s, false);
                }
            }, null);
            if(this.zzdqs.get() == null) {
                this.zzdqt.offer(futureTask0);
            }
            else {
                futureTask0.run();
            }
        }
    }

    private final boolean zza(Context context0, String s, AtomicReference atomicReference0, boolean z) {
        if(atomicReference0.get() == null) {
            try {
                atomicReference0.compareAndSet(null, context0.getClassLoader().loadClass(s).getDeclaredMethod("getInstance", Context.class).invoke(null, context0));
                return true;
            }
            catch(Exception exception0) {
                this.zza(exception0, "getInstance", z);
                return false;
            }
        }
        return true;
    }

    public final void zza(Context context0, zzuh zzuh0) {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjf)).booleanValue() && this.zzad(context0) && zzaui.zzae(context0)) {
        }
    }

    public final void zza(Context context0, zzyw zzyw0) {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjf)).booleanValue() && this.zzad(context0) && zzaui.zzae(context0)) {
        }
    }

    public final void zza(Context context0, String s, String s1, String s2, int v) {
        if(!this.zzad(context0)) {
            return;
        }
        Bundle bundle0 = new Bundle();
        bundle0.putString("_ai", s1);
        bundle0.putString("reward_type", s2);
        bundle0.putInt("reward_value", v);
        this.zza(context0, "_ar", s, bundle0);
        zzawf.zzee(("Log a Firebase reward video event, reward type: " + s2 + ", reward value: " + v));
    }

    // 检测为 Lambda 实现
    final void zza(zzauy zzauy0, String s) [...]

    public final boolean zzad(Context context0) {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcis)).booleanValue() && !this.zzdqn.get()) {
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjc)).booleanValue()) {
                return true;
            }
            if(this.zzdqo.get() == -1) {
                if(!zzayx.zzd(context0, 12451000) && zzayx.zzbn(context0)) {
                    zzawf.zzfa("Google Play Service is out of date, the Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires updated Google Play Service.");
                    this.zzdqo.set(0);
                    return this.zzdqo.get() == 1;
                }
                this.zzdqo.set(1);
            }
            return this.zzdqo.get() == 1;
        }
        return false;
    }

    @VisibleForTesting
    private static boolean zzae(Context context0) {
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzciz)).booleanValue()) {
            return false;
        }
        if(DynamiteModule.getLocalVersion(context0, "com.google.android.gms.ads.dynamite") < ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcja))))) {
            return false;
        }
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjb)).booleanValue()) {
            try {
                context0.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                return false;
            }
            catch(ClassNotFoundException unused_ex) {
                return true;
            }
        }
        return true;
    }

    public final String zzaf(Context context0) {
        if(!this.zzad(context0)) {
            return "";
        }
        if(zzaui.zzae(context0)) {
            return (String)this.zza("getCurrentScreenNameOrScreenClass", "", zzaun.zzdqw);
        }
        if(!this.zza(context0, "com.google.android.gms.measurement.AppMeasurement", this.zzdqp, true)) {
            return "";
        }
        try {
            String s = (String)this.zzm(context0, "getCurrentScreenName").invoke(this.zzdqp.get());
            if(s == null) {
                s = (String)this.zzm(context0, "getCurrentScreenClass").invoke(this.zzdqp.get());
            }
            return s == null ? "" : s;
        }
        catch(Exception exception0) {
            this.zza(exception0, "getCurrentScreenName", false);
            return "";
        }
    }

    @Nullable
    public final String zzag(Context context0) {
        if(!this.zzad(context0)) {
            return null;
        }
        synchronized(this.zzdqk) {
            if(this.zzdql != null) {
                return this.zzdql;
            }
            this.zzdql = zzaui.zzae(context0) ? ((String)this.zza("getGmpAppId", this.zzdql, zzaup.zzdqw)) : ((String)this.zza("getGmpAppId", context0));
            return this.zzdql;
        }
    }

    @Nullable
    public final String zzah(Context context0) {
        if(!this.zzad(context0)) {
            return null;
        }
        long v = (long)(((Long)zzvh.zzpd().zzd(zzzx.zzcix)));
        if(zzaui.zzae(context0)) {
            try {
                return v >= 0L ? ((String)this.zzuv().submit(() -> ((String)this.zza("getAppInstanceId", null, zzaul.zzdqw))).get(v, TimeUnit.MILLISECONDS)) : ((String)this.zza("getAppInstanceId", null, zzaus.zzdqw));
            }
            catch(TimeoutException unused_ex) {
                return "TIME_OUT";
            }
            catch(Exception unused_ex) {
                return null;
            }
        }
        if(v < 0L) {
            return (String)this.zza("getAppInstanceId", context0);
        }
        Future future0 = this.zzuv().submit(() -> ((String)this.zza("getAppInstanceId", context0)));
        try {
            return (String)future0.get(v, TimeUnit.MILLISECONDS);
        }
        catch(TimeoutException unused_ex) {
            return "TIME_OUT";
        }
        catch(Exception unused_ex) {
            return null;
        }
    }

    @Nullable
    public final String zzai(Context context0) {
        if(!this.zzad(context0)) {
            return null;
        }
        if(zzaui.zzae(context0)) {
            Long long0 = (Long)this.zza("getAdEventId", null, zzaut.zzdqw);
            return long0 == null ? null : Long.toString(((long)long0));
        }
        Object object0 = this.zza("generateEventId", context0);
        return object0 == null ? null : object0.toString();
    }

    @Nullable
    public final String zzaj(Context context0) {
        if(!this.zzad(context0)) {
            return null;
        }
        synchronized(this.zzdqk) {
            if(this.zzdqm != null) {
                return this.zzdqm;
            }
            this.zzdqm = zzaui.zzae(context0) ? ((String)this.zza("getAppIdOrigin", this.zzdqm, zzauk.zzdqw)) : "fa";
            return this.zzdqm;
        }
    }

    @Nullable
    private final Method zzak(Context context0) {
        Method method0 = (Method)this.zzdqr.get("logEventInternal");
        if(method0 != null) {
            return method0;
        }
        try {
            Method method1 = context0.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod("logEventInternal", String.class, String.class, Bundle.class);
            this.zzdqr.put("logEventInternal", method1);
            return method1;
        }
        catch(Exception exception0) {
            this.zza(exception0, "logEventInternal", true);
            return null;
        }
    }

    // 检测为 Lambda 实现
    final String zzal(Context context0) throws Exception [...]

    public final void zze(Context context0, String s) {
        if(!this.zzad(context0)) {
            return;
        }
        if(zzaui.zzae(context0)) {
            this.zza("beginAdUnitExposure", new zzauh(s));
            return;
        }
        this.zza(context0, s, "beginAdUnitExposure");
    }

    public final void zzf(Context context0, String s) {
        if(!this.zzad(context0)) {
            return;
        }
        if(zzaui.zzae(context0)) {
            this.zza("endAdUnitExposure", new zzauo(s));
            return;
        }
        this.zza(context0, s, "endAdUnitExposure");
    }

    public final void zzg(Context context0, String s) {
        if(!this.zzad(context0)) {
            return;
        }
        if(!(context0 instanceof Activity)) {
            return;
        }
        if(zzaui.zzae(context0)) {
            this.zza("setScreenName", new zzauq(context0, s));
            return;
        }
        if(!this.zza(context0, "com.google.firebase.analytics.FirebaseAnalytics", this.zzdqq, false)) {
            return;
        }
        Method method0 = this.zzn(context0, "setCurrentScreen");
        try {
            method0.invoke(this.zzdqq.get(), ((Activity)context0), s, "com.esotericsoftware.gloomhavenhelper");
        }
        catch(Exception exception0) {
            this.zza(exception0, "setCurrentScreen", false);
        }
    }

    public final void zzh(Context context0, String s) {
        this.zza(context0, "_ac", s, null);
    }

    public final void zzi(Context context0, String s) {
        this.zza(context0, "_ai", s, null);
    }

    public final void zzj(Context context0, String s) {
        this.zza(context0, "_aq", s, null);
    }

    public final void zzk(Context context0, String s) {
        this.zza(context0, "_aa", s, null);
    }

    private static Bundle zzl(String s, String s1) {
        Bundle bundle0 = new Bundle();
        try {
            bundle0.putLong("_aeid", Long.parseLong(s));
        }
        catch(NullPointerException | NumberFormatException nullPointerException0) {
            String s2 = String.valueOf(s);
            zzawf.zzc((s2.length() == 0 ? new String("Invalid event ID: ") : "Invalid event ID: " + s2), nullPointerException0);
        }
        if("_ac".equals(s1)) {
            bundle0.putInt("_r", 1);
        }
        return bundle0;
    }

    @Nullable
    private final Method zzl(Context context0, String s) {
        Method method0 = (Method)this.zzdqr.get(s);
        if(method0 != null) {
            return method0;
        }
        try {
            Method method1 = context0.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(s, String.class);
            this.zzdqr.put(s, method1);
            return method1;
        }
        catch(Exception exception0) {
            this.zza(exception0, s, false);
            return null;
        }
    }

    @Nullable
    private final Method zzm(Context context0, String s) {
        Method method0 = (Method)this.zzdqr.get(s);
        if(method0 != null) {
            return method0;
        }
        try {
            Method method1 = context0.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(s);
            this.zzdqr.put(s, method1);
            return method1;
        }
        catch(Exception exception0) {
            this.zza(exception0, s, false);
            return null;
        }
    }

    private final Method zzn(Context context0, String s) {
        Method method0 = (Method)this.zzdqr.get(s);
        if(method0 != null) {
            return method0;
        }
        try {
            Method method1 = context0.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics").getDeclaredMethod(s, Activity.class, String.class, String.class);
            this.zzdqr.put(s, method1);
            return method1;
        }
        catch(Exception exception0) {
            this.zza(exception0, s, false);
            return null;
        }
    }

    private final ThreadPoolExecutor zzuv() {
        if(this.zzdqj.get() == null) {
            ThreadPoolExecutor threadPoolExecutor0 = new ThreadPoolExecutor(((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzciy)))), ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzciy)))), 1L, TimeUnit.MINUTES, new LinkedBlockingQueue(), new zzauw(this));
            this.zzdqj.compareAndSet(null, threadPoolExecutor0);
        }
        return (ThreadPoolExecutor)this.zzdqj.get();
    }

    // 检测为 Lambda 实现
    final String zzuw() throws Exception [...]
}

