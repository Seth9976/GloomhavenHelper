package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;

@KeepForSdk
public class Wrappers {
    private PackageManagerWrapper zzhy;
    private static Wrappers zzhz;

    static {
        Wrappers.zzhz = new Wrappers();
    }

    public Wrappers() {
        this.zzhy = null;
    }

    @KeepForSdk
    public static PackageManagerWrapper packageManager(Context context0) {
        return Wrappers.zzhz.zzi(context0);
    }

    @VisibleForTesting
    private final PackageManagerWrapper zzi(Context context0) {
        synchronized(this) {
            if(this.zzhy == null) {
                if(context0.getApplicationContext() != null) {
                    context0 = context0.getApplicationContext();
                }
                this.zzhy = new PackageManagerWrapper(context0);
            }
            return this.zzhy;
        }
    }
}

