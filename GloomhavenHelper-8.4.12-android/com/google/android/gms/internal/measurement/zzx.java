package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzgp;
import com.google.android.gms.measurement.internal.zzgq;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class zzx {
    abstract class zza implements Runnable {
        final long zza;
        final long zzb;
        private final boolean zzc;
        private final zzx zzd;

        zza() {
            this(true);
        }

        zza(boolean z) {
            this.zza = zzx0.zza.currentTimeMillis();
            this.zzb = zzx0.zza.elapsedRealtime();
            this.zzc = z;
        }

        @Override
        public void run() {
            if(zzx.this.zzp) {
                this.zzb();
                return;
            }
            try {
                this.zza();
            }
            catch(Exception exception0) {
                zzx.this.zza(exception0, false, this.zzc);
                this.zzb();
            }
        }

        abstract void zza() throws RemoteException;

        protected void zzb() {
        }
    }

    static final class zzb extends zzr {
        private final zzgp zza;

        zzb(zzgp zzgp0) {
            this.zza = zzgp0;
        }

        @Override  // com.google.android.gms.internal.measurement.zzs
        public final int zza() {
            return System.identityHashCode(this.zza);
        }

        @Override  // com.google.android.gms.internal.measurement.zzs
        public final void zza(String s, String s1, Bundle bundle0, long v) {
            this.zza.onEvent(s, s1, bundle0, v);
        }
    }

    static final class zzc extends zzr {
        private final zzgq zza;

        zzc(zzgq zzgq0) {
            this.zza = zzgq0;
        }

        @Override  // com.google.android.gms.internal.measurement.zzs
        public final int zza() {
            return System.identityHashCode(this.zza);
        }

        @Override  // com.google.android.gms.internal.measurement.zzs
        public final void zza(String s, String s1, Bundle bundle0, long v) {
            this.zza.interceptEvent(s, s1, bundle0, v);
        }
    }

    final class zzd implements Application.ActivityLifecycleCallbacks {
        final zzx zza;

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity0, Bundle bundle0) {
            zzbc zzbc0 = new zzbc(this, activity0, bundle0);
            zzx.this.zza(zzbc0);
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity0) {
            zzbh zzbh0 = new zzbh(this, activity0);
            zzx.this.zza(zzbh0);
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity0) {
            zzbg zzbg0 = new zzbg(this, activity0);
            zzx.this.zza(zzbg0);
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity0) {
            zzbd zzbd0 = new zzbd(this, activity0);
            zzx.this.zza(zzbd0);
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity0, Bundle bundle0) {
            zzk zzk0 = new zzk();
            zzbi zzbi0 = new zzbi(this, activity0, zzk0);
            zzx.this.zza(zzbi0);
            Bundle bundle1 = zzk0.zzb(50L);
            if(bundle1 != null) {
                bundle0.putAll(bundle1);
            }
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity0) {
            zzbe zzbe0 = new zzbe(this, activity0);
            zzx.this.zza(zzbe0);
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity0) {
            zzbf zzbf0 = new zzbf(this, activity0);
            zzx.this.zza(zzbf0);
        }
    }

    protected final Clock zza;
    private static volatile zzx zzb = null;
    private final String zzc;
    private final ExecutorService zzd;
    private final AppMeasurementSdk zze;
    private List zzf;
    private int zzg;
    private static Boolean zzh = null;
    private static Boolean zzi = null;
    private static boolean zzj = false;
    private static Boolean zzk = null;
    @VisibleForTesting
    private static String zzl = "use_dynamite_api";
    @VisibleForTesting
    private static String zzm = "allow_remote_dynamite";
    private static boolean zzn = false;
    private static boolean zzo = false;
    private boolean zzp;
    private String zzq;
    private zzm zzr;

    static {
    }

    private zzx(Context context0, String s, String s1, String s2, Bundle bundle0) {
        this.zzc = s == null || !zzx.zzc(s1, s2) ? "FA" : s;
        this.zza = DefaultClock.getInstance();
        this.zzd = new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.zze = new AppMeasurementSdk(this);
        int v = 0;
        if(zzx.zzf(context0) && !zzx.zzk()) {
            this.zzq = null;
            this.zzp = true;
            Log.w(this.zzc, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Remove this value or add Google Analytics for Firebase to resume data collection.");
            return;
        }
        if(zzx.zzc(s1, s2)) {
            this.zzq = s1;
        }
        else {
            this.zzq = "fa";
            if(s1 != null && s2 != null) {
                Log.v(this.zzc, "Deferring to Google Analytics for Firebase for event data collection. https://goo.gl/J1sWQy");
                this.zzp = true;
                return;
            }
            if(s2 == null) {
                v = 1;
            }
            if(((s1 == null ? 1 : 0) ^ v) != 0) {
                Log.w(this.zzc, "Specified origin or custom app id is null. Both parameters will be ignored.");
            }
        }
        this.zza(new zzaa(this, s1, s2, context0, bundle0));
        Application application0 = (Application)context0.getApplicationContext();
        if(application0 == null) {
            Log.w(this.zzc, "Unable to register lifecycle notifications. Application null.");
            return;
        }
        application0.registerActivityLifecycleCallbacks(new zzd(this));
    }

    public static zzx zza(@NonNull Context context0) {
        return zzx.zza(context0, null, null, null, null);
    }

    public static zzx zza(Context context0, String s, String s1, String s2, Bundle bundle0) {
        Preconditions.checkNotNull(context0);
        if(zzx.zzb == null) {
            synchronized(zzx.class) {
                if(zzx.zzb == null) {
                    zzx.zzb = new zzx(context0, s, s1, s2, bundle0);
                }
                return zzx.zzb;
            }
        }
        return zzx.zzb;
    }

    private final void zza(zza zzx$zza0) {
        this.zzd.execute(zzx$zza0);
    }

    private final void zza(Exception exception0, boolean z, boolean z1) {
        this.zzp |= z;
        if(z) {
            Log.w(this.zzc, "Data collection startup failed. No data will be collected.", exception0);
            return;
        }
        if(z1) {
            this.zza(5, "Error with data collection. Data lost.", exception0, null, null);
        }
        Log.w(this.zzc, "Error with data collection. Data lost.", exception0);
    }

    private final void zza(String s, String s1, Bundle bundle0, boolean z, boolean z1, Long long0) {
        this.zza(new zzba(this, long0, s, s1, bundle0, z, z1));
    }

    private final void zza(String s, String s1, Object object0, boolean z) {
        this.zza(new zzaz(this, s, s1, object0, z));
    }

    private static boolean zza(Context context0, @Size(min = 1L) String s) {
        Preconditions.checkNotEmpty(s);
        try {
            ApplicationInfo applicationInfo0 = Wrappers.packageManager(context0).getApplicationInfo("com.esotericsoftware.gloomhavenhelper", 0x80);
            return applicationInfo0 == null || applicationInfo0.metaData == null ? false : applicationInfo0.metaData.getBoolean(s);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return false;
        }
    }

    static boolean zza(zzx zzx0, String s, String s1) {
        return zzx.zzc(s, s1);
    }

    public final Bundle zza(Bundle bundle0, boolean z) {
        zzk zzk0 = new zzk();
        this.zza(new zzar(this, bundle0, zzk0));
        return z ? zzk0.zzb(5000L) : null;
    }

    protected final zzm zza(Context context0, boolean z) {
        try {
            return zzl.asInterface(DynamiteModule.load(context0, (z ? DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION : DynamiteModule.PREFER_LOCAL), "com.google.android.gms.measurement.dynamite").instantiate("com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
        }
        catch(LoadingException dynamiteModule$LoadingException0) {
            this.zza(dynamiteModule$LoadingException0, true, false);
            return null;
        }
    }

    public final AppMeasurementSdk zza() {
        return this.zze;
    }

    public final Object zza(int v) {
        zzk zzk0 = new zzk();
        this.zza(new zzav(this, zzk0, v));
        return zzk.zza(zzk0.zzb(15000L), Object.class);
    }

    public final Map zza(String s, String s1, boolean z) {
        zzk zzk0 = new zzk();
        this.zza(new zzap(this, s, s1, z, zzk0));
        Bundle bundle0 = zzk0.zzb(5000L);
        if(bundle0 != null && bundle0.size() != 0) {
            Map map0 = new HashMap(bundle0.size());
            for(Object object0: bundle0.keySet()) {
                String s2 = (String)object0;
                Object object1 = bundle0.get(s2);
                if(object1 instanceof Double || object1 instanceof Long || object1 instanceof String) {
                    map0.put(s2, object1);
                }
            }
            return map0;
        }
        return Collections.emptyMap();
    }

    public final void zza(int v, String s, Object object0, Object object1, Object object2) {
        this.zza(new zzas(this, false, 5, s, object0, null, null));
    }

    public final void zza(long v) {
        this.zza(new zzaf(this, v));
    }

    public final void zza(Activity activity0, String s, String s1) {
        this.zza(new zzae(this, activity0, s, s1));
    }

    public final void zza(Bundle bundle0) {
        this.zza(new zzbb(this, bundle0));
    }

    public final void zza(zzgp zzgp0) {
        Preconditions.checkNotNull(zzgp0);
        this.zza(new zzaw(this, zzgp0));
    }

    public final void zza(zzgq zzgq0) {
        this.zza(new zzaj(this, zzgq0));
    }

    public final void zza(String s) {
        this.zza(new zzab(this, s));
    }

    public final void zza(@NonNull String s, Bundle bundle0) {
        this.zza(null, s, bundle0, false, true, null);
    }

    public final void zza(String s, String s1) {
        this.zza(null, s, s1, false);
    }

    public final void zza(String s, String s1, Bundle bundle0) {
        this.zza(s, s1, bundle0, true, true, null);
    }

    public final void zza(String s, String s1, Bundle bundle0, long v) {
        this.zza(s, s1, bundle0, true, false, v);
    }

    public final void zza(String s, String s1, Object object0) {
        this.zza(s, s1, object0, true);
    }

    public final void zza(boolean z) {
        this.zza(new zzad(this, z));
    }

    public static boolean zzb(Context context0) {
        zzx.zzi(context0);
        synchronized(zzx.class) {
            if(!zzx.zzj) {
                try {
                    String s = (String)Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, "measurement.dynamite.enabled", "");
                    if("true".equals(s)) {
                        zzx.zzk = Boolean.TRUE;
                    }
                    else if("false".equals(s)) {
                        zzx.zzk = Boolean.FALSE;
                    }
                    else {
                        zzx.zzk = null;
                    }
                }
                catch(Exception exception0) {
                    Log.e("FA", "Unable to call SystemProperties.get()", exception0);
                    zzx.zzk = null;
                }
                finally {
                    zzx.zzj = true;
                }
            }
        }
        return (zzx.zzk == null ? zzx.zzh : zzx.zzk).booleanValue();
    }

    public final List zzb(String s, String s1) {
        zzk zzk0 = new zzk();
        this.zza(new zzac(this, s, s1, zzk0));
        List list0 = (List)zzk.zza(zzk0.zzb(5000L), List.class);
        return list0 == null ? Collections.emptyList() : list0;
    }

    public final void zzb() {
        this.zza(new zzag(this));
    }

    public final void zzb(long v) {
        this.zza(new zzai(this, v));
    }

    public final void zzb(zzgp zzgp0) {
        Preconditions.checkNotNull(zzgp0);
        this.zza(new zzax(this, zzgp0));
    }

    public final void zzb(String s) {
        this.zza(new zzah(this, s));
    }

    public final void zzb(String s, String s1, Bundle bundle0) {
        this.zza(new zzz(this, s, s1, bundle0));
    }

    public final void zzb(boolean z) {
        this.zza(new zzay(this, z));
    }

    private static boolean zzc(String s, String s1) {
        return s1 != null && s != null && !zzx.zzk();
    }

    public final String zzc() {
        zzk zzk0 = new zzk();
        this.zza(new zzam(this, zzk0));
        return zzk0.zza(500L);
    }

    public final void zzc(String s) {
        this.zza(new zzak(this, s));
    }

    public final int zzd(String s) {
        zzk zzk0 = new zzk();
        this.zza(new zzau(this, s, zzk0));
        Integer integer0 = (Integer)zzk.zza(zzk0.zzb(10000L), Integer.class);
        return integer0 == null ? 25 : ((int)integer0);
    }

    public final String zzd() {
        zzk zzk0 = new zzk();
        this.zza(new zzal(this, zzk0));
        return zzk0.zza(50L);
    }

    public final long zze() {
        zzk zzk0 = new zzk();
        this.zza(new zzao(this, zzk0));
        Long long0 = (Long)zzk.zza(zzk0.zzb(500L), Long.class);
        if(long0 == null) {
            long v = new Random(System.nanoTime() ^ this.zza.currentTimeMillis()).nextLong();
            int v1 = this.zzg + 1;
            this.zzg = v1;
            return v + ((long)v1);
        }
        return (long)long0;
    }

    private static boolean zzf(Context context0) {
        try {
            GoogleServices.initialize(context0);
            if(GoogleServices.getGoogleAppId() != null) {
                return true;
            }
        }
        catch(IllegalStateException unused_ex) {
        }
        return false;
    }

    public final String zzf() {
        zzk zzk0 = new zzk();
        this.zza(new zzan(this, zzk0));
        return zzk0.zza(500L);
    }

    private static int zzg(Context context0) {
        return DynamiteModule.getRemoteVersion(context0, "com.google.android.gms.measurement.dynamite");
    }

    public final String zzg() {
        zzk zzk0 = new zzk();
        this.zza(new zzaq(this, zzk0));
        return zzk0.zza(500L);
    }

    private static int zzh(Context context0) {
        return DynamiteModule.getLocalVersion(context0, "com.google.android.gms.measurement.dynamite");
    }

    @WorkerThread
    public final String zzh() {
        zzk zzk0 = new zzk();
        this.zza(new zzat(this, zzk0));
        return zzk0.zza(120000L);
    }

    private static void zzi(Context context0) {
        synchronized(zzx.class) {
            try {
                if(zzx.zzh != null) {
                    goto label_4;
                }
                goto label_9;
            }
            catch(Exception exception0) {
                Log.e("FA", "Exception reading flag from SharedPreferences.", exception0);
                zzx.zzh = Boolean.FALSE;
                zzx.zzi = Boolean.FALSE;
                return;
            }
        }
    label_4:
        if(zzx.zzi != null) {
            return;
        }
        try {
        label_9:
            if(zzx.zza(context0, "app_measurement_internal_disable_startup_flags")) {
                zzx.zzh = Boolean.FALSE;
                zzx.zzi = Boolean.FALSE;
                return;
            }
            SharedPreferences sharedPreferences0 = context0.getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
            zzx.zzh = Boolean.valueOf(sharedPreferences0.getBoolean("use_dynamite_api", false));
            zzx.zzi = Boolean.valueOf(sharedPreferences0.getBoolean("allow_remote_dynamite", false));
            SharedPreferences.Editor sharedPreferences$Editor0 = sharedPreferences0.edit();
            sharedPreferences$Editor0.remove("use_dynamite_api");
            sharedPreferences$Editor0.remove("allow_remote_dynamite");
            sharedPreferences$Editor0.apply();
        }
        catch(Exception exception0) {
            Log.e("FA", "Exception reading flag from SharedPreferences.", exception0);
            zzx.zzh = Boolean.FALSE;
            zzx.zzi = Boolean.FALSE;
        }
    }

    public final String zzi() {
        return this.zzq;
    }

    private static boolean zzk() {
        try {
            Class.forName("com.google.firebase.analytics.FirebaseAnalytics");
            return true;
        }
        catch(ClassNotFoundException unused_ex) {
            return false;
        }
    }
}

