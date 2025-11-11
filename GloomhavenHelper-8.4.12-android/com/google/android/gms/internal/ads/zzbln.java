package com.google.android.gms.internal.ads;

import android.view.View;
import androidx.annotation.Nullable;
import java.util.Set;

public class zzbln {
    private final View view;
    private final zzbdv zzdae;
    private final zzdeh zzffr;
    private final zzbnc zzfgd;

    public zzbln(View view0, @Nullable zzbdv zzbdv0, zzbnc zzbnc0, zzdeh zzdeh0) {
        this.view = view0;
        this.zzdae = zzbdv0;
        this.zzfgd = zzbnc0;
        this.zzffr = zzdeh0;
    }

    public zzbrm zza(Set set0) {
        return new zzbrm(set0);
    }

    @Nullable
    public final zzbdv zzagc() {
        return this.zzdae;
    }

    public final View zzagm() {
        return this.view;
    }

    public final zzbnc zzagu() {
        return this.zzfgd;
    }

    public final zzdeh zzagv() {
        return this.zzffr;
    }
}

