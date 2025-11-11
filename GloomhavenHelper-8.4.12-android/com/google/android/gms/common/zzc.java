package com.google.android.gms.common;

import android.content.Context;
import android.os.RemoteException;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzm;
import com.google.android.gms.common.internal.zzn;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.dynamite.DynamiteModule;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
final class zzc {
    private static volatile zzm zzn;
    private static final Object zzo;
    private static Context zzp;

    static {
        zzc.zzo = new Object();
    }

    static com.google.android.gms.common.zzm zza(String s, zze zze0, boolean z, boolean z1) {
        StrictMode.ThreadPolicy strictMode$ThreadPolicy0 = StrictMode.allowThreadDiskReads();
        try {
            return zzc.zzb(s, zze0, z, z1);
        }
        finally {
            StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
        }
    }

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    static final String zza(boolean z, String s, zze zze0) throws Exception [...]

    static void zza(Context context0) {
        synchronized(zzc.class) {
            if(zzc.zzp != null) {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            }
            else if(context0 != null) {
                zzc.zzp = context0.getApplicationContext();
            }
        }
    }

    private static com.google.android.gms.common.zzm zzb(String s, zze zze0, boolean z, boolean z1) {
        if(zzc.zzn == null) {
            try {
                Preconditions.checkNotNull(zzc.zzp);
                Object object0 = zzc.zzo;
                synchronized(object0) {
                    if(zzc.zzn == null) {
                        zzc.zzn = zzn.zzc(DynamiteModule.load(zzc.zzp, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate("com.google.android.gms.common.GoogleCertificatesImpl"));
                    }
                }
            }
            catch(LoadingException dynamiteModule$LoadingException0) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", dynamiteModule$LoadingException0);
                String s1 = dynamiteModule$LoadingException0.getMessage();
                return s1.length() == 0 ? com.google.android.gms.common.zzm.zza(new String("module init: "), dynamiteModule$LoadingException0) : com.google.android.gms.common.zzm.zza(("module init: " + s1), dynamiteModule$LoadingException0);
            }
        }
        Preconditions.checkNotNull(zzc.zzp);
        zzk zzk0 = new zzk(s, zze0, z, z1);
        try {
            if(zzc.zzn.zza(zzk0, ObjectWrapper.wrap(zzc.zzp.getPackageManager()))) {
                return com.google.android.gms.common.zzm.zze();
            }
        }
        catch(RemoteException remoteException0) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", remoteException0);
            return com.google.android.gms.common.zzm.zza("module call", remoteException0);
        }
        return com.google.android.gms.common.zzm.zza(() -> // 去混淆评级： 低(20)
        com.google.android.gms.common.zzm.zzc(s, zze0, z, !z && zzc.zzb(s, zze0, true, false).zzad));
    }
}

