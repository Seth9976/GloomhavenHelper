package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads_identifier.zze;
import com.google.android.gms.internal.ads_identifier.zzf;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
@ParametersAreNonnullByDefault
public class AdvertisingIdClient {
    @KeepForSdkWithMembers
    public static final class Info {
        private final String zzq;
        private final boolean zzr;

        public Info(String s, boolean z) {
            this.zzq = s;
            this.zzr = z;
        }

        public final String getId() {
            return this.zzq;
        }

        public final boolean isLimitAdTrackingEnabled() {
            return this.zzr;
        }

        @Override
        public final String toString() {
            return "{" + this.zzq + "}" + this.zzr;
        }
    }

    @VisibleForTesting
    static final class zza extends Thread {
        private WeakReference zzm;
        private long zzn;
        CountDownLatch zzo;
        boolean zzp;

        public zza(AdvertisingIdClient advertisingIdClient0, long v) {
            this.zzm = new WeakReference(advertisingIdClient0);
            this.zzn = v;
            this.zzo = new CountDownLatch(1);
            this.zzp = false;
            this.start();
        }

        private final void disconnect() {
            AdvertisingIdClient advertisingIdClient0 = (AdvertisingIdClient)this.zzm.get();
            if(advertisingIdClient0 != null) {
                advertisingIdClient0.finish();
                this.zzp = true;
            }
        }

        @Override
        public final void run() {
            try {
                if(!this.zzo.await(this.zzn, TimeUnit.MILLISECONDS)) {
                    this.disconnect();
                }
            }
            catch(InterruptedException unused_ex) {
                this.disconnect();
            }
        }
    }

    @GuardedBy("this")
    private final Context mContext;
    @Nullable
    @GuardedBy("this")
    private BlockingServiceConnection zze;
    @Nullable
    @GuardedBy("this")
    private zze zzf;
    @GuardedBy("this")
    private boolean zzg;
    private final Object zzh;
    @Nullable
    @GuardedBy("mAutoDisconnectTaskLock")
    private zza zzi;
    private final boolean zzj;
    private final long zzk;

    @KeepForSdk
    public AdvertisingIdClient(Context context0) {
        this(context0, 30000L, false, false);
    }

    @VisibleForTesting
    private AdvertisingIdClient(Context context0, long v, boolean z, boolean z1) {
        this.zzh = new Object();
        Preconditions.checkNotNull(context0);
        if(z) {
            Context context1 = context0.getApplicationContext();
            if(context1 != null) {
                context0 = context1;
            }
        }
        this.mContext = context0;
        this.zzg = false;
        this.zzk = v;
        this.zzj = z1;
    }

    @Override
    protected void finalize() throws Throwable {
        this.finish();
        super.finalize();
    }

    public final void finish() {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        synchronized(this) {
            if(this.mContext != null && this.zze != null) {
                if(this.zzg) {
                    try {
                        ConnectionTracker.getInstance().unbindService(this.mContext, this.zze);
                    }
                    catch(Throwable throwable0) {
                        Log.i("AdvertisingIdClient", "AdvertisingIdClient unbindService failed.", throwable0);
                    }
                }
                this.zzg = false;
                this.zzf = null;
                this.zze = null;
            }
        }
    }

    @KeepForSdk
    public static Info getAdvertisingIdInfo(Context context0) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        Info advertisingIdClient$Info0;
        zzb zzb0 = new zzb(context0);
        boolean z = zzb0.getBoolean("gads:ad_id_app_context:enabled", false);
        float f = zzb0.getFloat("gads:ad_id_app_context:ping_ratio", 0.0f);
        String s = zzb0.getString("gads:ad_id_use_shared_preference:experiment_id", "");
        AdvertisingIdClient advertisingIdClient0 = new AdvertisingIdClient(context0, -1L, z, zzb0.getBoolean("gads:ad_id_use_persistent_service:enabled", false));
        try {
            long v = SystemClock.elapsedRealtime();
            advertisingIdClient0.zza(false);
            advertisingIdClient$Info0 = advertisingIdClient0.getInfo();
            advertisingIdClient0.zza(advertisingIdClient$Info0, z, f, SystemClock.elapsedRealtime() - v, s, null);
        }
        catch(Throwable throwable0) {
            try {
                advertisingIdClient0.zza(null, z, f, -1L, s, throwable0);
                throw throwable0;
            }
            catch(Throwable throwable1) {
                advertisingIdClient0.finish();
                throw throwable1;
            }
        }
        advertisingIdClient0.finish();
        return advertisingIdClient$Info0;
    }

    @KeepForSdk
    public Info getInfo() throws IOException {
        Info advertisingIdClient$Info0;
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        synchronized(this) {
            if(!this.zzg) {
                Object object0 = this.zzh;
                synchronized(object0) {
                    if(this.zzi == null || !this.zzi.zzp) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
                try {
                    this.zza(false);
                }
                catch(Exception exception0) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.", exception0);
                }
                if(!this.zzg) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.");
                }
            }
            Preconditions.checkNotNull(this.zze);
            Preconditions.checkNotNull(this.zzf);
            try {
                advertisingIdClient$Info0 = new Info(this.zzf.getId(), this.zzf.zzb(true));
            }
            catch(RemoteException remoteException0) {
                Log.i("AdvertisingIdClient", "GMS remote exception ", remoteException0);
                throw new IOException("Remote exception");
            }
        }
        this.zza();
        return advertisingIdClient$Info0;
    }

    @KeepForSdk
    public static boolean getIsAdIdFakeForDebugLogging(Context context0) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        zzb zzb0 = new zzb(context0);
        AdvertisingIdClient advertisingIdClient0 = new AdvertisingIdClient(context0, -1L, zzb0.getBoolean("gads:ad_id_app_context:enabled", false), zzb0.getBoolean("com.google.android.gms.ads.identifier.service.PERSISTENT_START", false));
        try {
            advertisingIdClient0.zza(false);
            return advertisingIdClient0.zzb();
        }
        finally {
            advertisingIdClient0.finish();
        }
    }

    @KeepForSdk
    public static void setShouldSkipGmsCoreVersionCheck(boolean z) {
    }

    @KeepForSdk
    public void start() throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        this.zza(true);
    }

    private static BlockingServiceConnection zza(Context context0, boolean z) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        try {
            context0.getPackageManager().getPackageInfo("com.android.vending", 0);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
        switch(GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context0, 12451000)) {
            case 0: 
            case 2: {
                BlockingServiceConnection blockingServiceConnection0 = new BlockingServiceConnection();
                Intent intent0 = new Intent((z ? "com.google.android.gms.ads.identifier.service.PERSISTENT_START" : "com.google.android.gms.ads.identifier.service.START"));
                intent0.setPackage("com.google.android.gms");
                try {
                    if(ConnectionTracker.getInstance().bindService(context0, intent0, blockingServiceConnection0, 1)) {
                        return blockingServiceConnection0;
                    }
                }
                catch(Throwable throwable0) {
                    throw new IOException(throwable0);
                }
                throw new IOException("Connection failure");
            }
            default: {
                throw new IOException("Google Play services not available");
            }
        }
    }

    @VisibleForTesting
    private static zze zza(Context context0, BlockingServiceConnection blockingServiceConnection0) throws IOException {
        try {
            return zzf.zza(blockingServiceConnection0.getServiceWithTimeout(10000L, TimeUnit.MILLISECONDS));
        }
        catch(InterruptedException unused_ex) {
            throw new IOException("Interrupted exception");
        }
        catch(Throwable throwable0) {
            throw new IOException(throwable0);
        }
    }

    private final void zza() {
        synchronized(this.zzh) {
            if(this.zzi != null) {
                this.zzi.zzo.countDown();
                try {
                    this.zzi.join();
                }
                catch(InterruptedException unused_ex) {
                }
            }
            if(this.zzk > 0L) {
                this.zzi = new zza(this, this.zzk);
            }
        }
    }

    @VisibleForTesting
    private final void zza(boolean z) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        synchronized(this) {
            if(this.zzg) {
                this.finish();
            }
            this.zze = AdvertisingIdClient.zza(this.mContext, this.zzj);
            this.zzf = AdvertisingIdClient.zza(this.mContext, this.zze);
            this.zzg = true;
            if(z) {
                this.zza();
            }
        }
    }

    @VisibleForTesting
    private final boolean zza(Info advertisingIdClient$Info0, boolean z, float f, long v, String s, Throwable throwable0) {
        if(Math.random() > ((double)f)) {
            return false;
        }
        HashMap hashMap0 = new HashMap();
        hashMap0.put("app_context", (z ? "1" : "0"));
        if(advertisingIdClient$Info0 != null) {
            hashMap0.put("limit_ad_tracking", (advertisingIdClient$Info0.isLimitAdTrackingEnabled() ? "1" : "0"));
        }
        if(advertisingIdClient$Info0 != null && advertisingIdClient$Info0.getId() != null) {
            hashMap0.put("ad_id_size", Integer.toString(advertisingIdClient$Info0.getId().length()));
        }
        if(throwable0 != null) {
            hashMap0.put("error", throwable0.getClass().getName());
        }
        if(s != null && !s.isEmpty()) {
            hashMap0.put("experiment_id", s);
        }
        hashMap0.put("tag", "AdvertisingIdClient");
        hashMap0.put("time_spent", Long.toString(v));
        new com.google.android.gms.ads.identifier.zza(this, hashMap0).start();
        return true;
    }

    private final boolean zzb() throws IOException {
        boolean z;
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        synchronized(this) {
            if(!this.zzg) {
                Object object0 = this.zzh;
                synchronized(object0) {
                    if(this.zzi == null || !this.zzi.zzp) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
                try {
                    this.zza(false);
                }
                catch(Exception exception0) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.", exception0);
                }
                if(!this.zzg) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.");
                }
            }
            Preconditions.checkNotNull(this.zze);
            Preconditions.checkNotNull(this.zzf);
            try {
                z = this.zzf.zzc();
            }
            catch(RemoteException remoteException0) {
                Log.i("AdvertisingIdClient", "GMS remote exception ", remoteException0);
                throw new IOException("Remote exception");
            }
        }
        this.zza();
        return z;
    }
}

