package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

public class zzbnf {
    protected final zzdeq zzfdp;
    protected final zzdei zzfhg;
    private final zzbqw zzfhu;
    private final zzbrm zzfhv;
    @Nullable
    private final zzdcl zzfhw;
    private final zzbqc zzfhx;

    protected zzbnf(zzbne zzbne0) {
        this.zzfdp = zzbne0.zzfdp;
        this.zzfhg = zzbne0.zzfhg;
        this.zzfhu = zzbne0.zzfhu;
        this.zzfhv = zzbne0.zzfhv;
        this.zzfhw = zzbne0.zzfhw;
        this.zzfhx = zzbne0.zzfhx;
    }

    public void destroy() {
        this.zzfhu.zzca(null);
    }

    public void zzags() {
        this.zzfhv.onAdLoaded();
    }

    public final zzbqw zzahh() {
        return this.zzfhu;
    }

    public final zzbqc zzahi() {
        return this.zzfhx;
    }

    @Nullable
    public final zzdcl zzahj() {
        return this.zzfhw;
    }
}

