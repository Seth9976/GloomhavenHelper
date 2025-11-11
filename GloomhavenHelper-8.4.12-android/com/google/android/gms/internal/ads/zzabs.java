package com.google.android.gms.internal.ads;

public final class zzabs {
    private static zzaax zzcvu;
    public static zzaax zzcvv;
    private static zzaax zzcvw;

    static {
        zzabs.zzcvu = zzaax.zzb("gads:ad_loader:timeout_ms", 60000L);
        zzabs.zzcvv = zzaax.zzb("gads:rendering:timeout_ms", 60000L);
        zzabs.zzcvw = zzaax.zzb("gads:resolve_future:default_timeout_ms", 30000L);
    }
}

