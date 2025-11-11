package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import javax.annotation.concurrent.GuardedBy;

public final class zzp {
    private static Object sLock;
    @GuardedBy("sLock")
    private static boolean zzeo;
    private static String zzep;
    private static int zzeq;

    static {
        zzp.sLock = new Object();
    }

    public static String zzc(Context context0) {
        zzp.zze(context0);
        return zzp.zzep;
    }

    public static int zzd(Context context0) {
        zzp.zze(context0);
        return zzp.zzeq;
    }

    private static void zze(Context context0) {
        synchronized(zzp.sLock) {
            if(zzp.zzeo) {
                return;
            }
            zzp.zzeo = true;
            PackageManagerWrapper packageManagerWrapper0 = Wrappers.packageManager(context0);
            try {
                Bundle bundle0 = packageManagerWrapper0.getApplicationInfo("com.esotericsoftware.gloomhavenhelper", 0x80).metaData;
                if(bundle0 == null) {
                    return;
                }
                zzp.zzep = bundle0.getString("com.google.app.id");
                zzp.zzeq = bundle0.getInt("com.google.android.gms.version");
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                Log.wtf("MetadataValueReader", "This should never happen.", packageManager$NameNotFoundException0);
            }
        }
    }
}

