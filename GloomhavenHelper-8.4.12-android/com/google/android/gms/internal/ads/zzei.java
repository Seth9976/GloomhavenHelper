package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzei {
    final class zza extends BroadcastReceiver {
        private final zzei zzyg;

        private zza() {
        }

        zza(zzel zzel0) {
        }

        @Override  // android.content.BroadcastReceiver
        public final void onReceive(Context context0, Intent intent0) {
            if("android.intent.action.USER_PRESENT".equals(intent0.getAction())) {
                zzei.this.zzyc = true;
                return;
            }
            if("android.intent.action.SCREEN_OFF".equals(intent0.getAction())) {
                zzei.this.zzyc = false;
            }
        }
    }

    private static final String TAG = "zzei";
    protected Context zzur;
    private volatile boolean zzvl;
    private ExecutorService zzxo;
    private DexClassLoader zzxp;
    private zzds zzxq;
    private byte[] zzxr;
    private volatile AdvertisingIdClient zzxs;
    private Future zzxt;
    private boolean zzxu;
    private volatile com.google.android.gms.internal.ads.zzbs.zza zzxv;
    private Future zzxw;
    private zzde zzxx;
    private boolean zzxy;
    private boolean zzxz;
    private Map zzya;
    private boolean zzyb;
    private boolean zzyc;
    private boolean zzyd;

    static {
    }

    private zzei(Context context0) {
        this.zzxs = null;
        this.zzvl = false;
        this.zzxt = null;
        this.zzxv = null;
        this.zzxw = null;
        this.zzxy = false;
        this.zzxz = false;
        this.zzyb = false;
        boolean z = true;
        this.zzyc = true;
        this.zzyd = false;
        Context context1 = context0.getApplicationContext();
        if(context1 == null) {
            z = false;
        }
        this.zzxu = z;
        if(this.zzxu) {
            context0 = context1;
        }
        this.zzur = context0;
        this.zzya = new HashMap();
    }

    public final Context getContext() {
        return this.zzur;
    }

    public final boolean isInitialized() {
        return this.zzyb;
    }

    public static zzei zza(Context context0, String s, String s1, boolean z) {
        zzei zzei0 = new zzei(context0);
        try {
            zzei0.zzxo = Executors.newCachedThreadPool(new zzel());
            zzei0.zzvl = z;
            if(z) {
                zzei0.zzxt = zzei0.zzxo.submit(new zzek(zzei0));
            }
            zzei0.zzxo.execute(new zzem(zzei0));
        }
        catch(zzeh unused_ex) {
            return zzei0;
        }
        try {
            GoogleApiAvailabilityLight googleApiAvailabilityLight0 = GoogleApiAvailabilityLight.getInstance();
            zzei0.zzxy = googleApiAvailabilityLight0.getApkVersion(zzei0.zzur) > 0;
            zzei0.zzxz = googleApiAvailabilityLight0.isGooglePlayServicesAvailable(zzei0.zzur) == 0;
        }
        catch(Throwable unused_ex) {
        }
        try {
            zzei0.zza(0, true);
            if(zzep.isMainThread() && ((Boolean)zzvh.zzpd().zzd(zzzx.zzcme)).booleanValue()) {
                throw new IllegalStateException("Task Context initialization must not be called from the UI thread.");
            }
            zzei0.zzxq = new zzds(null);
            try {
                zzei0.zzxr = zzei0.zzxq.zzas(s);
            }
            catch(zzdv unused_ex) {
                return zzei0;
            }
            try {
                File file0 = zzei0.zzur.getCacheDir();
                if(file0 != null) {
                    goto label_18;
                }
                file0 = zzei0.zzur.getDir("dex", 0);
                if(file0 != null) {
                label_18:
                    File file1 = new File(String.format("%s/%s.jar", file0, "1571257279724"));
                    if(!file1.exists()) {
                        byte[] arr_b = zzei0.zzxq.zza(zzei0.zzxr, s1);
                        file1.createNewFile();
                        FileOutputStream fileOutputStream0 = new FileOutputStream(file1);
                        fileOutputStream0.write(arr_b, 0, arr_b.length);
                        fileOutputStream0.close();
                    }
                    zzei0.zzb(file0, "1571257279724");
                    try {
                        zzei0.zzxp = new DexClassLoader(file1.getAbsolutePath(), file0.getAbsolutePath(), null, zzei0.zzur.getClassLoader());
                    }
                    finally {
                        zzei.zzb(file1);
                        zzei0.zza(file0, "1571257279724");
                        zzei.zzat(String.format("%s/%s.dex", file0, "1571257279724"));
                    }
                    goto label_33;
                }
            }
            catch(FileNotFoundException unused_ex) {
            }
            catch(IOException unused_ex) {
            }
            catch(zzdv unused_ex) {
            }
            catch(NullPointerException unused_ex) {
            }
            return zzei0;
        label_33:
            if(!zzei0.zzyd) {
                IntentFilter intentFilter0 = new IntentFilter();
                intentFilter0.addAction("android.intent.action.USER_PRESENT");
                intentFilter0.addAction("android.intent.action.SCREEN_OFF");
                zzei0.zzur.registerReceiver(new zza(zzei0, null), intentFilter0);
                zzei0.zzyd = true;
            }
            zzei0.zzxx = new zzde(zzei0);
            zzei0.zzyb = true;
            return zzei0;
        }
        catch(zzeh unused_ex) {
        }
        return zzei0;
    }

    private final void zza(File file0, String s) {
        FileOutputStream fileOutputStream1;
        com.google.android.gms.internal.ads.zzbs.zzc.zza zzbs$zzc$zza0;
        FileInputStream fileInputStream0;
        File file1 = new File(String.format("%s/%s.tmp", file0, s));
        if(file1.exists()) {
            return;
        }
        File file2 = new File(String.format("%s/%s.dex", file0, s));
        if(!file2.exists()) {
            return;
        }
        long v = file2.length();
        if(v <= 0L) {
            return;
        }
        byte[] arr_b = new byte[((int)v)];
        FileOutputStream fileOutputStream0 = null;
        try {
            fileInputStream0 = null;
            fileInputStream0 = new FileInputStream(file2);
            if(fileInputStream0.read(arr_b) > 0) {
                System.out.print("test");
                System.out.print("test");
                System.out.print("test");
                zzbs$zzc$zza0 = zzc.zzbc().zzh(zzdxn.zzt(Build.VERSION.SDK.getBytes())).zzg(zzdxn.zzt(s.getBytes()));
                byte[] arr_b1 = this.zzxq.zzb(this.zzxr, arr_b).getBytes();
                zzbs$zzc$zza0.zze(zzdxn.zzt(arr_b1)).zzf(zzdxn.zzt(zzck.zzb(arr_b1)));
                file1.createNewFile();
                fileOutputStream1 = new FileOutputStream(file1);
                goto label_25;
            }
            goto label_48;
        }
        catch(IOException throwable0) {
            goto label_38;
            try {
            label_25:
                byte[] arr_b2 = ((zzc)(((zzdyz)zzbs$zzc$zza0.zzbcx()))).toByteArray();
                fileOutputStream1.write(arr_b2, 0, arr_b2.length);
                fileOutputStream1.close();
                goto label_44;
            }
            catch(IOException | NoSuchAlgorithmException | zzdv unused_ex) {
            }
            catch(Throwable throwable0) {
                goto label_37;
            }
            fileOutputStream0 = fileOutputStream1;
        }
        catch(NoSuchAlgorithmException | zzdv | Throwable unused_ex) {
        }
        if(fileInputStream0 != null) {
            try {
                fileInputStream0.close();
            }
            catch(IOException unused_ex) {
            }
        }
        if(fileOutputStream0 != null) {
            try {
                fileOutputStream0.close();
            }
            catch(IOException unused_ex) {
            }
        }
        zzei.zzb(file2);
        return;
    label_37:
        fileOutputStream0 = fileOutputStream1;
    label_38:
        if(fileInputStream0 != null) {
            try {
                fileInputStream0.close();
            }
            catch(IOException unused_ex) {
            }
        }
        if(fileOutputStream0 != null) {
            try {
                fileOutputStream0.close();
            }
            catch(IOException unused_ex) {
            }
        }
        zzei.zzb(file2);
        throw throwable0;
        try {
        label_44:
            fileInputStream0.close();
        }
        catch(IOException unused_ex) {
        }
        try {
            fileOutputStream1.close();
        }
        catch(IOException unused_ex) {
        }
        zzei.zzb(file2);
        return;
        try {
        label_48:
            fileInputStream0.close();
        }
        catch(IOException unused_ex) {
        }
        zzei.zzb(file2);
    }

    // 去混淆评级： 低(26)
    private static boolean zza(int v, com.google.android.gms.internal.ads.zzbs.zza zzbs$zza0) {
        if(v < 4) {
            return zzbs$zza0 == null ? true : !zzbs$zza0.zzak() || !zzbs$zza0.zzam() || !zzbs$zza0.zzan().zzbe() || zzbs$zza0.zzan().zzbf() == -2L;
        }
        return false;
    }

    public final Method zza(String s, String s1) {
        zzft zzft0 = (zzft)this.zzya.get(new Pair(s, s1));
        return zzft0 == null ? null : zzft0.zzcx();
    }

    @VisibleForTesting
    final void zza(int v, boolean z) {
        if(!this.zzxz) {
            return;
        }
        Future future0 = this.zzxo.submit(new zzen(this, v, z));
        if(v == 0) {
            this.zzxw = future0;
        }
    }

    public final boolean zza(String s, String s1, Class[] arr_class) {
        if(!this.zzya.containsKey(new Pair(s, s1))) {
            this.zzya.put(new Pair(s, s1), new zzft(this, s, s1, arr_class));
            return true;
        }
        return false;
    }

    private static void zzat(String s) {
        zzei.zzb(new File(s));
    }

    private static void zzb(File file0) {
        if(!file0.exists()) {
            Log.d("zzei", String.format("File %s not found. No need for deletion", file0.getAbsolutePath()));
            return;
        }
        file0.delete();
    }

    private final boolean zzb(File file0, String s) {
        FileOutputStream fileOutputStream1;
        byte[] arr_b1;
        FileInputStream fileInputStream0;
        byte[] arr_b;
        FileOutputStream fileOutputStream0;
        File file1 = new File(String.format("%s/%s.tmp", file0, s));
        if(!file1.exists()) {
            return false;
        }
        File file2 = new File(String.format("%s/%s.dex", file0, s));
        if(file2.exists()) {
            return false;
        }
        try {
            fileOutputStream0 = null;
            long v = file1.length();
            if(v <= 0L) {
                zzei.zzb(file1);
                return false;
            }
            arr_b = new byte[((int)v)];
            fileInputStream0 = new FileInputStream(file1);
        }
        catch(IOException | NoSuchAlgorithmException | zzdv unused_ex) {
            fileInputStream0 = null;
            goto label_41;
        }
        catch(Throwable throwable0) {
            fileInputStream0 = null;
            goto label_47;
        }
        try {
            if(fileInputStream0.read(arr_b) > 0) {
                zzc zzbs$zzc0 = zzc.zzb(arr_b, zzdym.zzbch());
                if(s.equals(new String(zzbs$zzc0.zzba().toByteArray())) && Arrays.equals(zzbs$zzc0.zzaz().toByteArray(), zzck.zzb(zzbs$zzc0.zzay().toByteArray())) && Arrays.equals(zzbs$zzc0.zzbb().toByteArray(), Build.VERSION.SDK.getBytes())) {
                    arr_b1 = this.zzxq.zza(this.zzxr, new String(zzbs$zzc0.zzay().toByteArray()));
                    file2.createNewFile();
                    fileOutputStream1 = new FileOutputStream(file2);
                    goto label_25;
                }
                goto label_35;
            }
            goto label_38;
        }
        catch(IOException | NoSuchAlgorithmException | zzdv unused_ex) {
            goto label_41;
        }
        catch(Throwable throwable0) {
            goto label_47;
        }
        try {
        label_25:
            fileOutputStream1.write(arr_b1, 0, arr_b1.length);
        }
        catch(IOException | NoSuchAlgorithmException | zzdv unused_ex) {
            fileOutputStream0 = fileOutputStream1;
            goto label_41;
        }
        catch(Throwable throwable0) {
            fileOutputStream0 = fileOutputStream1;
            goto label_47;
        }
        try {
            fileInputStream0.close();
        }
        catch(IOException unused_ex) {
        }
        try {
            fileOutputStream1.close();
        }
        catch(IOException unused_ex) {
        }
        return true;
        try {
        label_35:
            zzei.zzb(file1);
        }
        catch(IOException | NoSuchAlgorithmException | zzdv unused_ex) {
            goto label_41;
        }
        catch(Throwable throwable0) {
            goto label_47;
        }
        try {
            fileInputStream0.close();
        }
        catch(IOException unused_ex) {
        }
        return false;
        try {
        label_38:
            Log.d("zzei", "Cannot read the cache data.");
            zzei.zzb(file1);
            goto label_52;
        }
        catch(IOException | NoSuchAlgorithmException | zzdv unused_ex) {
        label_41:
            if(fileInputStream0 != null) {
                try {
                    fileInputStream0.close();
                }
                catch(IOException unused_ex) {
                }
            }
            if(fileOutputStream0 != null) {
                try {
                    fileOutputStream0.close();
                }
                catch(IOException unused_ex) {
                }
            }
            return false;
        }
        catch(Throwable throwable0) {
        }
    label_47:
        if(fileInputStream0 != null) {
            try {
                fileInputStream0.close();
            }
            catch(IOException unused_ex) {
            }
        }
        if(fileOutputStream0 != null) {
            try {
                fileOutputStream0.close();
            }
            catch(IOException unused_ex) {
            }
        }
        throw throwable0;
        try {
        label_52:
            fileInputStream0.close();
        }
        catch(IOException unused_ex) {
        }
        return false;
    }

    @VisibleForTesting
    final com.google.android.gms.internal.ads.zzbs.zza zzb(int v, boolean z) {
        if(v > 0 && z) {
            try {
                Thread.sleep(v * 1000);
            }
            catch(InterruptedException unused_ex) {
            }
        }
        return this.zzcn();
    }

    public final int zzbs() {
        return this.zzxx == null ? 0x80000000 : zzde.zzbs();
    }

    public final ExecutorService zzcc() {
        return this.zzxo;
    }

    public final DexClassLoader zzcd() {
        return this.zzxp;
    }

    public final zzds zzce() {
        return this.zzxq;
    }

    public final byte[] zzcf() {
        return this.zzxr;
    }

    public final boolean zzcg() {
        return this.zzxy;
    }

    public final zzde zzch() {
        return this.zzxx;
    }

    public final boolean zzci() {
        return this.zzxz;
    }

    public final boolean zzcj() {
        return this.zzyc;
    }

    public final com.google.android.gms.internal.ads.zzbs.zza zzck() {
        return this.zzxv;
    }

    public final Future zzcl() {
        return this.zzxw;
    }

    private final void zzcm() {
        try {
            if(this.zzxs == null && this.zzxu) {
                AdvertisingIdClient advertisingIdClient0 = new AdvertisingIdClient(this.zzur);
                advertisingIdClient0.start();
                this.zzxs = advertisingIdClient0;
            }
        }
        catch(GooglePlayServicesNotAvailableException | IOException | GooglePlayServicesRepairableException unused_ex) {
            this.zzxs = null;
        }
    }

    @VisibleForTesting
    private final com.google.android.gms.internal.ads.zzbs.zza zzcn() {
        try {
            PackageInfo packageInfo0 = this.zzur.getPackageManager().getPackageInfo("com.esotericsoftware.gloomhavenhelper", 0);
            return zzdjd.zzj(this.zzur, "com.esotericsoftware.gloomhavenhelper", Integer.toString(packageInfo0.versionCode));
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    public final AdvertisingIdClient zzco() {
        if(!this.zzvl) {
            return null;
        }
        if(this.zzxs != null) {
            return this.zzxs;
        }
        Future future0 = this.zzxt;
        if(future0 != null) {
            try {
                future0.get(2000L, TimeUnit.MILLISECONDS);
                this.zzxt = null;
                return this.zzxs;
            }
            catch(InterruptedException unused_ex) {
            }
            catch(ExecutionException | TimeoutException unused_ex) {
                return this.zzxs;
            }
            this.zzxt.cancel(true);
        }
        return this.zzxs;
    }
}

