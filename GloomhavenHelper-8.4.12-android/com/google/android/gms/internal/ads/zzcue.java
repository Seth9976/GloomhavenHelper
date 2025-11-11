package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcue implements zzcyb {
    private final zzur zzgif;

    public zzcue(zzur zzur0) {
        this.zzgif = zzur0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        zzur zzur0 = this.zzgif;
        if(zzur0 != null) {
            if(zzur0.orientation == 1) {
                ((Bundle)object0).putString("avo", "p");
                return;
            }
            if(this.zzgif.orientation == 2) {
                ((Bundle)object0).putString("avo", "l");
            }
        }
    }
}

