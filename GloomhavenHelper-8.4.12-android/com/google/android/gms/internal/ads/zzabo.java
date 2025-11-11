package com.google.android.gms.internal.ads;

public final class zzabo {
    public static zzaax zzcvh;
    public static zzaax zzcvi;
    public static zzaax zzcvj;
    public static zzaax zzcvk;
    public static zzaax zzcvl;
    public static zzaax zzcvm;
    public static zzaax zzcvn;

    static {
        zzabo.zzcvh = zzaax.zzb("gads:dynamite_load:fail:sample_rate", 10000L);
        zzabo.zzcvi = zzaax.zzf("gads:report_dynamite_crash_in_background_thread", false);
        zzabo.zzcvj = zzaax.zzi("gads:public_beta:traffic_multiplier", "1.0");
        zzabo.zzcvk = zzaax.zzi("gads:sdk_crash_report_class_prefix", "com.google.");
        zzabo.zzcvl = zzaax.zzf("gads:sdk_crash_report_enabled", false);
        zzabo.zzcvm = zzaax.zzf("gads:sdk_crash_report_full_stacktrace", false);
        zzabo.zzcvn = new zzaax("gads:trapped_exception_sample_rate", 0.01, zzaaz.zzctl);
    }
}

