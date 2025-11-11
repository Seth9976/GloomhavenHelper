package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@TargetApi(21)
@ParametersAreNonnullByDefault
final class zzaug {
    private static final Map zzdqg;
    private final List zzdqh;
    private final zzats zzdqi;
    private final Context zzur;

    static {
        HashMap hashMap0 = new HashMap();
        hashMap0.put("android.webkit.resource.AUDIO_CAPTURE", "android.permission.RECORD_AUDIO");
        hashMap0.put("android.webkit.resource.VIDEO_CAPTURE", "android.permission.CAMERA");
        zzaug.zzdqg = hashMap0;
    }

    zzaug(Context context0, List list0, zzats zzats0) {
        this.zzur = context0;
        this.zzdqh = list0;
        this.zzdqi = zzats0;
    }

    // This method was un-flattened
    final List zzb(String[] arr_s) {
        List list0 = new ArrayList();
        int v = 0;
        while(v < arr_s.length) {
            String s = arr_s[v];
            for(Object object0: this.zzdqh) {
                String s1 = (String)object0;
                if(!s1.equals(s)) {
                    String s2 = String.valueOf(s1);
                    if(!(s2.length() == 0 ? new String("android.webkit.resource.") : "android.webkit.resource." + s2).equals(s)) {
                        continue;
                    }
                }
                if(zzaug.zzdqg.containsKey(s)) {
                    String s3 = (String)zzaug.zzdqg.get(s);
                    if(!zzawo.zzq(this.zzur, s3)) {
                        this.zzdqi.zzdy(s);
                        goto label_19;
                    }
                }
                list0.add(s);
                goto label_19;
            }
            this.zzdqi.zzdx(s);
        label_19:
            ++v;
        }
        return list0;
    }
}

