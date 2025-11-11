package com.google.android.gms.internal.ads;

import java.util.List;

public final class zzeeu {
    private final List zzign;
    private final List zzigo;

    private zzeeu(int v, int v1) {
        this.zzign = zzeei.zzhi(v);
        this.zzigo = zzeei.zzhi(v1);
    }

    zzeeu(int v, int v1, zzeer zzeer0) {
        this(v, v1);
    }

    public final zzeeu zzas(zzeew zzeew0) {
        this.zzign.add(zzeew0);
        return this;
    }

    public final zzeeu zzat(zzeew zzeew0) {
        this.zzigo.add(zzeew0);
        return this;
    }

    public final zzees zzbgo() {
        return new zzees(this.zzign, this.zzigo, null);
    }
}

