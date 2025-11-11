package com.google.android.gms.internal.ads;

import android.text.TextUtils;

public final class zzna {
    public final int viewportHeight;
    public final int viewportWidth;
    public final String zzbdt;
    public final String zzbdu;
    public final boolean zzbdv;
    public final boolean zzbdw;
    public final int zzbdx;
    public final int zzbdy;
    public final int zzbdz;
    public final boolean zzbea;
    public final boolean zzbeb;
    public final boolean zzbec;

    public zzna() {
        this(null, null, false, true, 0x7FFFFFFF, 0x7FFFFFFF, 0x7FFFFFFF, true, true, 0x7FFFFFFF, 0x7FFFFFFF, true);
    }

    private zzna(String s, String s1, boolean z, boolean z1, int v, int v1, int v2, boolean z2, boolean z3, int v3, int v4, boolean z4) {
        this.zzbdt = null;
        this.zzbdu = null;
        this.zzbdv = false;
        this.zzbdw = true;
        this.zzbdx = 0x7FFFFFFF;
        this.zzbdy = 0x7FFFFFFF;
        this.zzbdz = 0x7FFFFFFF;
        this.zzbea = true;
        this.zzbeb = true;
        this.viewportWidth = 0x7FFFFFFF;
        this.viewportHeight = 0x7FFFFFFF;
        this.zzbec = true;
    }

    // 去混淆评级： 低(30)
    @Override
    public final boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && this.zzbdw == ((zzna)object0).zzbdw && this.zzbdx == ((zzna)object0).zzbdx && this.zzbdy == ((zzna)object0).zzbdy && this.zzbea == ((zzna)object0).zzbea && this.zzbeb == ((zzna)object0).zzbeb && this.zzbec == ((zzna)object0).zzbec && this.viewportWidth == ((zzna)object0).viewportWidth && this.viewportHeight == ((zzna)object0).viewportHeight && this.zzbdz == ((zzna)object0).zzbdz && TextUtils.equals(null, null) && TextUtils.equals(null, null);
    }

    @Override
    public final int hashCode() {
        throw new NullPointerException();
    }
}

