package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzaxx {
    private static zzu zzdvg;
    private static final Object zzdvh;
    @Deprecated
    private static final zzayb zzdvi;

    static {
        zzaxx.zzdvh = new Object();
        zzaxx.zzdvi = new zzaya();
    }

    public zzaxx(Context context0) {
        if(context0.getApplicationContext() != null) {
            context0 = context0.getApplicationContext();
        }
        zzaxx.zzbk(context0);
    }

    public final zzdof zza(int v, String s, @Nullable Map map0, @Nullable byte[] arr_b) {
        zzdof zzdof0 = new zzaye(null);
        zzaxz zzaxz0 = new zzaxz(this, s, ((zzaye)zzdof0));
        zzazb zzazb0 = new zzazb(null);
        zzayc zzayc0 = new zzayc(this, v, s, ((zzab)zzdof0), zzaxz0, arr_b, map0, zzazb0);
        if(zzazb.isEnabled()) {
            try {
                zzazb0.zza(s, "GET", zzayc0.getHeaders(), zzayc0.zzg());
            }
            catch(zzb zzb0) {
                zzawf.zzfa(zzb0.getMessage());
            }
        }
        zzaxx.zzdvg.zze(zzayc0);
        return zzdof0;
    }

    @VisibleForTesting
    private static zzu zzbk(Context context0) {
        synchronized(zzaxx.zzdvh) {
            if(zzaxx.zzdvg == null) {
                zzzx.initialize(context0);
                zzaxx.zzdvg = ((Boolean)zzvh.zzpd().zzd(zzzx.zzcod)).booleanValue() ? zzaxq.zzbj(context0) : zzba.zza(context0);
            }
            return zzaxx.zzdvg;
        }
    }

    public final zzdof zzc(String s, Map map0) {
        return this.zza(0, s, map0, null);
    }

    public static zzdof zzer(String s) {
        zzdof zzdof0 = new zzazy();
        zzaxx.zzdvg.zze(new zzayd(s, ((zzazy)zzdof0)));
        return zzdof0;
    }
}

