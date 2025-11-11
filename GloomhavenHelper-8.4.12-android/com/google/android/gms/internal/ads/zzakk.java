package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzakk {
    @VisibleForTesting
    private static final zzaxu zzdbw;
    @VisibleForTesting
    private static final zzaxu zzdbx;
    private final zzaja zzdby;

    static {
        zzakk.zzdbw = new zzakn();
        zzakk.zzdbx = new zzakm();
    }

    public zzakk(Context context0, zzazo zzazo0, String s) {
        this.zzdby = new zzaja(context0, zzazo0, s, zzakk.zzdbw, zzakk.zzdbx);
    }

    public final zzakc zza(String s, zzakh zzakh0, zzake zzake0) {
        return new zzakp(this.zzdby, s, zzakh0, zzake0);
    }

    public final zzakt zzsm() {
        return new zzakt(this.zzdby);
    }
}

