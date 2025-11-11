package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class zzkb extends zzkc {
    public final long zzare;
    public final List zzarf;
    public final List zzarg;

    public zzkb(int v, long v1) {
        super(v);
        this.zzare = v1;
        this.zzarf = new ArrayList();
        this.zzarg = new ArrayList();
    }

    @Override  // com.google.android.gms.internal.ads.zzkc
    public final String toString() {
        return zzkb.zzap(this.type) + " leaves: " + Arrays.toString(this.zzarf.toArray()) + " containers: " + Arrays.toString(this.zzarg.toArray());
    }

    public final zzke zzal(int v) {
        int v1 = this.zzarf.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            zzke zzke0 = (zzke)this.zzarf.get(v2);
            if(zzke0.type == v) {
                return zzke0;
            }
        }
        return null;
    }

    public final zzkb zzam(int v) {
        int v1 = this.zzarg.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            zzkb zzkb0 = (zzkb)this.zzarg.get(v2);
            if(zzkb0.type == v) {
                return zzkb0;
            }
        }
        return null;
    }
}

