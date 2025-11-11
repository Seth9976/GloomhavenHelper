package com.google.android.gms.internal.ads;

public final class zzabd {
    private static zzaax zzctz;
    private static zzaax zzcua;
    private static zzaax zzcub;
    private static zzaax zzcuc;
    private static zzaax zzcud;

    static {
        zzabd.zzctz = zzaay.zzf("gads:consent:gmscore:dsid:enabled", true);
        zzabd.zzcua = zzaay.zzf("gads:consent:gmscore:lat:enabled", true);
        zzabd.zzcub = new zzaay("gads:consent:gmscore:backend_url", "https://adservice.google.com/getconfig/pubvendors", zzaaz.zzctm);
        zzabd.zzcuc = new zzaay("gads:consent:gmscore:time_out", 10000L, zzaaz.zzctk);
        zzabd.zzcud = zzaay.zzf("gads:consent:gmscore:enabled", true);
    }

    public static final void zza(zzabz zzabz0) {
    }
}

