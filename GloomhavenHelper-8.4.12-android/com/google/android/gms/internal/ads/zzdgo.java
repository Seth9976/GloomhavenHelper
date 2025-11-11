package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.HashMap;

public final class zzdgo {
    private HashMap zzgtp;

    public zzdgo() {
        this.zzgtp = new HashMap();
    }

    public final zzdgn zza(zzdgf zzdgf0, Context context0, zzdfw zzdfw0, zzdgv zzdgv0) {
        zzdgn zzdgn0 = (zzdgn)this.zzgtp.get(zzdgf0);
        if(zzdgn0 == null) {
            zzdgc zzdgc0 = new zzdgc(zzdgg.zza(zzdgf0, context0));
            zzdgn zzdgn1 = new zzdgn(zzdgc0, new zzdgs(zzdgc0, zzdfw0, zzdgv0));
            this.zzgtp.put(zzdgf0, zzdgn1);
            return zzdgn1;
        }
        return zzdgn0;
    }
}

