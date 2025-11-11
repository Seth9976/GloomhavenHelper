package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzq;
import java.util.Map;

public final class zzbcw implements zzafz {
    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        zzbbm zzbbm0 = (zzbbm)object0;
        zzbeq zzbeq0 = zzbbm0.zzyq();
        try {
            if(zzbeq0 == null) {
                zzbeq zzbeq1 = new zzbeq(zzbbm0, Float.parseFloat(((String)map0.get("duration"))), "1".equals(map0.get("customControlsAllowed")), "1".equals(map0.get("clickToExpandAllowed")));
                zzbbm0.zza(zzbeq1);
                zzbeq0 = zzbeq1;
            }
            float f = Float.parseFloat(((String)map0.get("duration")));
            boolean z = "1".equals(map0.get("muted"));
            float f1 = Float.parseFloat(((String)map0.get("currentTime")));
            int v = Integer.parseInt(((String)map0.get("playbackState")));
            if(v < 0 || 3 < v) {
                v = 0;
            }
            String s = (String)map0.get("aspectRatio");
            float f2 = TextUtils.isEmpty(s) ? 0.0f : Float.parseFloat(s);
            if(zzawf.isLoggable(3)) {
                zzawf.zzeb(("Video Meta GMSG: currentTime : " + f1 + " , duration : " + f + " , isMuted : " + z + " , playbackState : " + v + " , aspectRatio : " + s));
            }
            zzbeq0.zza(f1, f, v, z, f2);
        }
        catch(NullPointerException | NumberFormatException nullPointerException0) {
            zzawf.zzc("Unable to parse videoMeta message.", nullPointerException0);
            zzq.zzkz().zza(nullPointerException0, "VideoMetaGmsgHandler.onGmsg");
        }
    }
}

