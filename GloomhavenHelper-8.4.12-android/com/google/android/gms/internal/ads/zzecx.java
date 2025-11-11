package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;

public final class zzecx {
    private static final Charset ISO_8859_1;
    private static final Charset UTF_8;
    public static final Object zzhzz;

    static {
        zzecx.UTF_8 = Charset.forName("UTF-8");
        zzecx.ISO_8859_1 = Charset.forName("ISO-8859-1");
        zzecx.zzhzz = new Object();
    }

    public static void zza(zzect zzect0, zzect zzect1) {
        if(zzect0.zzhzu != null) {
            zzect1.zzhzu = (zzecv)zzect0.zzhzu.clone();
        }
    }
}

