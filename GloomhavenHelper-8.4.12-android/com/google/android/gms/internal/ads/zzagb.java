package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzq;
import java.util.Map;

public final class zzagb implements zzafz {
    private final Context zzur;

    public zzagb(Context context0) {
        this.zzur = context0;
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        if(!zzq.zzlt().zzad(this.zzur)) {
            return;
        }
        String s = (String)map0.get("eventName");
        String s1 = (String)map0.get("eventId");
        switch(s) {
            case "_aa": {
                zzq.zzlt().zzk(this.zzur, s1);
                return;
            }
            case "_ac": {
                zzq.zzlt().zzh(this.zzur, s1);
                return;
            }
            case "_ai": {
                zzq.zzlt().zzi(this.zzur, s1);
                return;
            }
            default: {
                zzawf.zzey("logScionEvent gmsg contained unsupported eventName");
            }
        }
    }
}

