package com.google.android.gms.internal.measurement;

import android.os.Build.VERSION;

public final class zzdg {
    static final class zza extends zzdf {
        @Override  // com.google.android.gms.internal.measurement.zzdf
        public final void zza(Throwable throwable0, Throwable throwable1) {
        }
    }

    private static final zzdf zza;
    private static final int zzb;

    static {
        zza zzdg$zza0;
        Integer integer0;
        int v = 1;
        try {
            integer0 = null;
            integer0 = zzdg.zza();
            zzdg$zza0 = integer0 == null || ((int)integer0) < 19 ? new zzdj() : new zzdk();
        }
        catch(Throwable throwable0) {
            System.err.println("An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy com.google.android.gms.internal.measurement.zzdg$zzawill be used. The error is: ");
            throwable0.printStackTrace(System.err);
            zzdg$zza0 = new zza();
        }
        zzdg.zza = zzdg$zza0;
        if(integer0 != null) {
            v = (int)integer0;
        }
        zzdg.zzb = v;
    }

    // 去混淆评级： 低(40)
    private static Integer zza() {
        return Build.VERSION.SDK_INT;
    }

    public static void zza(Throwable throwable0, Throwable throwable1) {
        zzdg.zza.zza(throwable0, throwable1);
    }
}

