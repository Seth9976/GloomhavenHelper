package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzaal {
    private final Map zzctb;
    @Nullable
    private final zzaak zzctc;

    public zzaal(@Nullable zzaak zzaak0) {
        this.zzctc = zzaak0;
        this.zzctb = new HashMap();
    }

    public final void zza(String s, zzaai zzaai0) {
        this.zzctb.put(s, zzaai0);
    }

    public final void zza(String s, String s1, long v) {
        zzaak zzaak0 = this.zzctc;
        zzaai zzaai0 = (zzaai)this.zzctb.get(s1);
        String[] arr_s = {s};
        if(zzaak0 != null && zzaai0 != null) {
            zzaak0.zza(zzaai0, v, arr_s);
        }
        zzaai zzaai1 = this.zzctc == null ? null : this.zzctc.zzex(v);
        this.zzctb.put(s, zzaai1);
    }

    @Nullable
    public final zzaak zzqw() {
        return this.zzctc;
    }
}

