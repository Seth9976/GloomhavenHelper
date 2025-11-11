package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

public final class zzazk {
    public static Object zza(Context context0, String s, zzazj zzazj0) throws zzazm {
        try {
            return zzazj0.apply(zzazk.zzbt(context0).instantiate(s));
        }
        catch(Exception exception0) {
            throw new zzazm(exception0);
        }
    }

    public static Context zzbs(Context context0) throws zzazm {
        return zzazk.zzbt(context0).getModuleContext();
    }

    private static DynamiteModule zzbt(Context context0) throws zzazm {
        try {
            return DynamiteModule.load(context0, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.ads.dynamite");
        }
        catch(Exception exception0) {
            throw new zzazm(exception0);
        }
    }
}

