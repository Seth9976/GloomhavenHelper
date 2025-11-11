package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.common.util.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class zzcpc {
    private final Clock zzbmz;
    private final List zzgdb;

    public zzcpc(Clock clock0) {
        this.zzgdb = Collections.synchronizedList(new ArrayList());
        this.zzbmz = clock0;
    }

    private final void zza(String s, int v, long v1) {
        this.zzgdb.add(s + "." + v + "." + v1);
    }

    public final zzdof zza(zzdei zzdei0, zzdof zzdof0) {
        long v = this.zzbmz.elapsedRealtime();
        String s = zzdei0.zzdcu;
        if(s != null) {
            zzdnt.zza(zzdof0, new zzcpf(this, s, v), zzazq.zzdxp);
        }
        return zzdof0;
    }

    public final String zzaoh() {
        return TextUtils.join("_", this.zzgdb);
    }
}

