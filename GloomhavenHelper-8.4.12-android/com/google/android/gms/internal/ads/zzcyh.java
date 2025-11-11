package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzcyh implements zzcyb {
    private final boolean zzdoc;
    private final boolean zzdod;
    private final String zzdoe;
    private final boolean zzdof;
    private final boolean zzdog;
    private final boolean zzdoh;
    private final String zzdoi;
    private final String zzdoj;
    private final String zzdok;
    private final boolean zzdol;
    private final ArrayList zzgks;
    private final String zzgkt;
    private final String zzgku;
    private final long zzgkv;

    public zzcyh(boolean z, boolean z1, String s, boolean z2, boolean z3, boolean z4, String s1, ArrayList arrayList0, @Nullable String s2, @Nullable String s3, String s4, boolean z5, String s5, long v) {
        this.zzdoc = z;
        this.zzdod = z1;
        this.zzdoe = s;
        this.zzdof = z2;
        this.zzdog = z3;
        this.zzdoh = z4;
        this.zzdoi = s1;
        this.zzgks = arrayList0;
        this.zzdoj = s2;
        this.zzdok = s3;
        this.zzgkt = s4;
        this.zzdol = z5;
        this.zzgku = s5;
        this.zzgkv = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        ((Bundle)object0).putBoolean("cog", this.zzdoc);
        ((Bundle)object0).putBoolean("coh", this.zzdod);
        ((Bundle)object0).putString("gl", this.zzdoe);
        ((Bundle)object0).putBoolean("simulator", this.zzdof);
        ((Bundle)object0).putBoolean("is_latchsky", this.zzdog);
        ((Bundle)object0).putBoolean("is_sidewinder", this.zzdoh);
        ((Bundle)object0).putString("hl", this.zzdoi);
        if(!this.zzgks.isEmpty()) {
            ((Bundle)object0).putStringArrayList("hl_list", this.zzgks);
        }
        ((Bundle)object0).putString("mv", this.zzdoj);
        ((Bundle)object0).putString("submodel", this.zzgku);
        Bundle bundle0 = zzdez.zza(((Bundle)object0), "device");
        ((Bundle)object0).putBundle("device", bundle0);
        bundle0.putString("build", this.zzgkt);
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcmp)).booleanValue()) {
            bundle0.putLong("remaining_data_partition_space", this.zzgkv);
        }
        Bundle bundle1 = zzdez.zza(bundle0, "browser");
        bundle0.putBundle("browser", bundle1);
        bundle1.putBoolean("is_browser_custom_tabs_capable", this.zzdol);
        if(!TextUtils.isEmpty(this.zzdok)) {
            Bundle bundle2 = zzdez.zza(bundle0, "play_store");
            bundle0.putBundle("play_store", bundle2);
            bundle2.putString("package_version", this.zzdok);
        }
    }
}

