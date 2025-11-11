package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

public final class zzctv implements zzcyb {
    private final String zzabk;
    private final zzuk zzblv;
    private final float zzboq;
    private final boolean zzcde;
    private final int zzdhj;
    private final int zzdhk;
    private final String zzghw;
    private final String zzghx;

    public zzctv(zzuk zzuk0, String s, boolean z, String s1, float f, int v, int v1, String s2) {
        Preconditions.checkNotNull(zzuk0, "the adSize must not be null");
        this.zzblv = zzuk0;
        this.zzabk = s;
        this.zzcde = z;
        this.zzghw = s1;
        this.zzboq = f;
        this.zzdhj = v;
        this.zzdhk = v1;
        this.zzghx = s2;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        zzdez.zza(((Bundle)object0), "smart_w", "full", this.zzblv.width == -1);
        zzdez.zza(((Bundle)object0), "smart_h", "auto", this.zzblv.height == -2);
        zzdez.zza(((Bundle)object0), "ene", Boolean.TRUE, this.zzblv.zzcdf);
        zzdez.zza(((Bundle)object0), "rafmt", "102", this.zzblv.zzcdi);
        zzdez.zza(((Bundle)object0), "rafmt", "103", this.zzblv.zzcdj);
        zzdez.zza(((Bundle)object0), "format", this.zzabk);
        zzdez.zza(((Bundle)object0), "fluid", "height", this.zzcde);
        boolean z = TextUtils.isEmpty(this.zzghw);
        zzdez.zza(((Bundle)object0), "sz", this.zzghw, !z);
        ((Bundle)object0).putFloat("u_sd", this.zzboq);
        ((Bundle)object0).putInt("sw", this.zzdhj);
        ((Bundle)object0).putInt("sh", this.zzdhk);
        boolean z1 = TextUtils.isEmpty(this.zzghx);
        zzdez.zza(((Bundle)object0), "sc", this.zzghx, !z1);
        ArrayList arrayList0 = new ArrayList();
        if(this.zzblv.zzcdd == null) {
            Bundle bundle0 = new Bundle();
            bundle0.putInt("height", this.zzblv.height);
            bundle0.putInt("width", this.zzblv.width);
            bundle0.putBoolean("is_fluid_height", this.zzblv.zzcde);
            arrayList0.add(bundle0);
        }
        else {
            zzuk[] arr_zzuk = this.zzblv.zzcdd;
            for(int v = 0; v < arr_zzuk.length; ++v) {
                zzuk zzuk0 = arr_zzuk[v];
                Bundle bundle1 = new Bundle();
                bundle1.putBoolean("is_fluid_height", zzuk0.zzcde);
                bundle1.putInt("height", zzuk0.height);
                bundle1.putInt("width", zzuk0.width);
                arrayList0.add(bundle1);
            }
        }
        ((Bundle)object0).putParcelableArrayList("valid_ad_sizes", arrayList0);
    }
}

