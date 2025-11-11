package com.google.android.gms.internal.ads;

import android.content.Context;
import java.io.File;
import java.util.regex.Pattern;

public final class zzaxq extends zzak {
    private final Context zzur;

    private zzaxq(Context context0, zzah zzah0) {
        super(zzah0);
        this.zzur = context0;
    }

    public static zzu zzbj(Context context0) {
        zzaxq zzaxq0 = new zzaxq(context0, new zzat());
        zzu zzu0 = new zzu(new zzal(new File(context0.getCacheDir(), "admob_volley"), 0x1400000), zzaxq0);
        zzu0.start();
        return zzu0;
    }

    @Override  // com.google.android.gms.internal.ads.zzak
    public final zzo zzc(zzq zzq0) throws zzae {
        if(zzq0.zzh() && zzq0.getMethod() == 0 && Pattern.matches(((String)zzvh.zzpd().zzd(zzzx.zzcoe)), zzq0.getUrl()) && zzayx.zzd(this.zzur, 13400000)) {
            zzo zzo0 = new zzagt(this.zzur).zzc(zzq0);
            if(zzo0 != null) {
                String s = zzq0.getUrl();
                zzawf.zzee((s.length() == 0 ? new String("Got gmscore asset response: ") : "Got gmscore asset response: " + s));
                return zzo0;
            }
            String s1 = zzq0.getUrl();
            zzawf.zzee((s1.length() == 0 ? new String("Failed to get gmscore asset response: ") : "Failed to get gmscore asset response: " + s1));
        }
        return super.zzc(zzq0);
    }
}

