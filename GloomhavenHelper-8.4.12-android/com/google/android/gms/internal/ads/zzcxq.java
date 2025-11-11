package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcxq implements zzcyb {
    private final String zzbmj;
    private final int zzdkk;
    private final boolean zzdlh;
    private final boolean zzdxi;
    private final boolean zzgkc;
    private final int zzgkd;
    private final int zzgke;

    public zzcxq(boolean z, boolean z1, String s, boolean z2, int v, int v1, int v2) {
        this.zzgkc = z;
        this.zzdxi = z1;
        this.zzbmj = s;
        this.zzdlh = z2;
        this.zzdkk = v;
        this.zzgkd = v1;
        this.zzgke = v2;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        ((Bundle)object0).putString("js", this.zzbmj);
        ((Bundle)object0).putBoolean("is_nonagon", true);
        ((Bundle)object0).putString("extra_caps", ((String)zzvh.zzpd().zzd(zzzx.zzcmr)));
        ((Bundle)object0).putInt("target_api", this.zzdkk);
        ((Bundle)object0).putInt("dv", this.zzgkd);
        ((Bundle)object0).putInt("lv", this.zzgke);
        Bundle bundle0 = zzdez.zza(((Bundle)object0), "sdk_env");
        bundle0.putBoolean("mf", ((Boolean)zzabj.zzcux.get()).booleanValue());
        bundle0.putBoolean("instant_app", this.zzgkc);
        bundle0.putBoolean("lite", this.zzdxi);
        bundle0.putBoolean("is_privileged_process", this.zzdlh);
        ((Bundle)object0).putBundle("sdk_env", bundle0);
        Bundle bundle1 = zzdez.zza(bundle0, "build_meta");
        bundle1.putString("cl", "300152424");
        bundle1.putString("rapid_rc", "dev");
        bundle1.putString("rapid_rollup", "HEAD");
        bundle0.putBundle("build_meta", bundle1);
    }
}

