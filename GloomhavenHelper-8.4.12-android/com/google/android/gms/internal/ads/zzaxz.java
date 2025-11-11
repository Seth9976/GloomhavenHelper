package com.google.android.gms.internal.ads;

final class zzaxz implements zzy {
    private final String zzdvm;
    private final zzaye zzdvn;

    zzaxz(zzaxx zzaxx0, String s, zzaye zzaye0) {
        this.zzdvm = s;
        this.zzdvn = zzaye0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzy
    public final void zzc(zzae zzae0) {
        zzawf.zzfa(("Failed to load URL: " + this.zzdvm + "\n" + zzae0.toString()));
        this.zzdvn.zzb(null);
    }
}

