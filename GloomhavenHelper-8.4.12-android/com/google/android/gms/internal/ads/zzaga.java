package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzc;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.Map;

public final class zzaga implements zzafz {
    private final zzc zzcyp;
    private final zzaom zzcyq;
    private final zzaoz zzcyr;
    private static final Map zzcys;

    static {
        zzaga.zzcys = CollectionUtils.mapOfKeyValueArrays(new String[]{"resize", "playVideo", "storePicture", "createCalendarEvent", "setOrientationProperties", "closeResizedAd", "unload"}, new Integer[]{1, 2, 3, 4, 5, 6, 7});
    }

    public zzaga(zzc zzc0, zzaom zzaom0, zzaoz zzaoz0) {
        this.zzcyp = zzc0;
        this.zzcyq = zzaom0;
        this.zzcyr = zzaoz0;
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        String s = (String)map0.get("a");
        int v = (int)(((Integer)zzaga.zzcys.get(s)));
        if(v != 5 && v != 7 && (this.zzcyp != null && !this.zzcyp.zzjv())) {
            this.zzcyp.zzbr(null);
            return;
        }
        if(v != 1) {
            switch(v) {
                case 3: {
                    new zzaor(((zzbdv)object0), map0).execute();
                    return;
                }
                case 4: {
                    new zzaol(((zzbdv)object0), map0).execute();
                    return;
                }
                case 5: {
                    new zzaoo(((zzbdv)object0), map0).execute();
                    return;
                }
                case 6: {
                    this.zzcyq.zzac(true);
                    return;
                }
                case 7: {
                    this.zzcyr.zzto();
                    return;
                }
                default: {
                    zzawf.zzez("Unknown MRAID command called.");
                    return;
                }
            }
        }
        this.zzcyq.zzg(map0);
    }
}

