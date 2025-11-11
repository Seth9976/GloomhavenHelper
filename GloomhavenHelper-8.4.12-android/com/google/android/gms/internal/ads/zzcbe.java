package com.google.android.gms.internal.ads;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;

final class zzcbe implements zzdku {
    private final String zzczz;
    private final int zzdum;
    private final int zzdun;
    private final double zzfsn;

    zzcbe(String s, double f, int v, int v1) {
        this.zzczz = s;
        this.zzfsn = f;
        this.zzdum = v;
        this.zzdun = v1;
    }

    @Override  // com.google.android.gms.internal.ads.zzdku
    public final Object apply(Object object0) {
        return new zzacd(new BitmapDrawable(Resources.getSystem(), ((Bitmap)object0)), Uri.parse(this.zzczz), this.zzfsn, this.zzdum, this.zzdun);
    }
}

