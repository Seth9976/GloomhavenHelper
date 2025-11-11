package com.google.android.gms.internal.ads;

import android.os.Build.VERSION;
import java.io.PrintWriter;

public final class zzdww {
    static final class zza extends zzdwz {
        @Override  // com.google.android.gms.internal.ads.zzdwz
        public final void zza(Throwable throwable0, PrintWriter printWriter0) {
            throwable0.printStackTrace(printWriter0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdwz
        public final void zza(Throwable throwable0, Throwable throwable1) {
        }
    }

    private static final zzdwz zzhni;
    private static final int zzhnj;

    static {
        zza zzdww$zza0;
        Integer integer0;
        int v = 1;
        try {
            integer0 = null;
            integer0 = zzdww.zzbah();
            zzdww$zza0 = integer0 == null || ((int)integer0) < 19 ? new zzdxa() : new zzdxc();
        }
        catch(Throwable throwable0) {
            System.err.println("An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy com.google.android.gms.internal.ads.zzdww$zzawill be used. The error is: ");
            throwable0.printStackTrace(System.err);
            zzdww$zza0 = new zza();
        }
        zzdww.zzhni = zzdww$zza0;
        if(integer0 != null) {
            v = (int)integer0;
        }
        zzdww.zzhnj = v;
    }

    public static void zza(Throwable throwable0, PrintWriter printWriter0) {
        zzdww.zzhni.zza(throwable0, printWriter0);
    }

    public static void zza(Throwable throwable0, Throwable throwable1) {
        zzdww.zzhni.zza(throwable0, throwable1);
    }

    // 去混淆评级： 低(40)
    private static Integer zzbah() {
        return Build.VERSION.SDK_INT;
    }
}

