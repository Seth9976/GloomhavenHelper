package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.wrappers.Wrappers;
import javax.annotation.CheckReturnValue;

@KeepForSdk
@ShowFirstParty
@CheckReturnValue
public class GoogleSignatureVerifier {
    private final Context mContext;
    private static GoogleSignatureVerifier zzam;
    private volatile String zzan;

    private GoogleSignatureVerifier(Context context0) {
        this.mContext = context0.getApplicationContext();
    }

    @KeepForSdk
    public static GoogleSignatureVerifier getInstance(Context context0) {
        Preconditions.checkNotNull(context0);
        synchronized(GoogleSignatureVerifier.class) {
            if(GoogleSignatureVerifier.zzam == null) {
                zzc.zza(context0);
                GoogleSignatureVerifier.zzam = new GoogleSignatureVerifier(context0);
            }
            return GoogleSignatureVerifier.zzam;
        }
    }

    @KeepForSdk
    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo0) {
        if(packageInfo0 == null) {
            return false;
        }
        if(GoogleSignatureVerifier.zza(packageInfo0, false)) {
            return true;
        }
        if(GoogleSignatureVerifier.zza(packageInfo0, true)) {
            if(GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren\'t accepted on this build.");
        }
        return false;
    }

    @KeepForSdk
    @ShowFirstParty
    public boolean isPackageGoogleSigned(String s) {
        zzm zzm0 = this.zzc(s);
        zzm0.zzf();
        return zzm0.zzad;
    }

    @KeepForSdk
    @ShowFirstParty
    public boolean isUidGoogleSigned(int v) {
        zzm zzm0;
        String[] arr_s = Wrappers.packageManager(this.mContext).getPackagesForUid(v);
        if(arr_s == null || arr_s.length == 0) {
            zzm0 = zzm.zzb("no pkgs");
        }
        else {
            zzm0 = null;
            for(int v1 = 0; v1 < arr_s.length; ++v1) {
                zzm0 = this.zza(arr_s[v1], v);
                if(zzm0.zzad) {
                    break;
                }
            }
        }
        zzm0.zzf();
        return zzm0.zzad;
    }

    private static zze zza(PackageInfo packageInfo0, zze[] arr_zze) {
        if(packageInfo0.signatures == null) {
            return null;
        }
        if(packageInfo0.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzf zzf0 = new zzf(packageInfo0.signatures[0].toByteArray());
        for(int v = 0; v < arr_zze.length; ++v) {
            if(arr_zze[v].equals(zzf0)) {
                return arr_zze[v];
            }
        }
        return null;
    }

    private final zzm zza(String s, int v) {
        try {
            PackageInfo packageInfo0 = Wrappers.packageManager(this.mContext).zza(s, 0x40, v);
            boolean z = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
            if(packageInfo0 == null) {
                return zzm.zzb("null pkg");
            }
            if(packageInfo0.signatures.length != 1) {
                return zzm.zzb("single cert required");
            }
            zzf zzf0 = new zzf(packageInfo0.signatures[0].toByteArray());
            String s1 = packageInfo0.packageName;
            zzm zzm0 = zzc.zza(s1, zzf0, z, false);
            return !zzm0.zzad || packageInfo0.applicationInfo == null || (packageInfo0.applicationInfo.flags & 2) == 0 || !zzc.zza(s1, zzf0, false, true).zzad ? zzm0 : zzm.zzb("debuggable release cert app rejected");
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            String s2 = String.valueOf(s);
            return s2.length() == 0 ? zzm.zzb(new String("no pkg ")) : zzm.zzb(("no pkg " + s2));
        }
    }

    // 去混淆评级： 低(20)
    public static boolean zza(PackageInfo packageInfo0, boolean z) {
        return packageInfo0 == null || packageInfo0.signatures == null ? false : (z ? GoogleSignatureVerifier.zza(packageInfo0, zzh.zzx) : GoogleSignatureVerifier.zza(packageInfo0, new zze[]{zzh.zzx[0]})) != null;
    }

    private final zzm zzc(String s) {
        zzm zzm0;
        PackageInfo packageInfo0;
        if(s == null) {
            return zzm.zzb("null pkg");
        }
        if(s.equals(this.zzan)) {
            return zzm.zze();
        }
        try {
            packageInfo0 = Wrappers.packageManager(this.mContext).getPackageInfo(s, 0x40);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            String s1 = String.valueOf(s);
            return s1.length() == 0 ? zzm.zzb(new String("no pkg ")) : zzm.zzb(("no pkg " + s1));
        }
        boolean z = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
        if(packageInfo0 == null) {
            zzm0 = zzm.zzb("null pkg");
        }
        else if(packageInfo0.signatures.length == 1) {
            zzf zzf0 = new zzf(packageInfo0.signatures[0].toByteArray());
            String s2 = packageInfo0.packageName;
            zzm zzm1 = zzc.zza(s2, zzf0, z, false);
            zzm0 = !zzm1.zzad || packageInfo0.applicationInfo == null || (packageInfo0.applicationInfo.flags & 2) == 0 || !zzc.zza(s2, zzf0, false, true).zzad ? zzm1 : zzm.zzb("debuggable release cert app rejected");
        }
        else {
            zzm0 = zzm.zzb("single cert required");
        }
        if(zzm0.zzad) {
            this.zzan = s;
        }
        return zzm0;
    }
}

