package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzq;
import java.util.Map;

public final class zzafj implements zzafz {
    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        long v2;
        long v1;
        long v;
        String s = (String)map0.get("action");
        if("tick".equals(s)) {
            String s1 = (String)map0.get("label");
            String s2 = (String)map0.get("start_label");
            String s3 = (String)map0.get("timestamp");
            if(TextUtils.isEmpty(s1)) {
                zzawf.zzfa("No label given for CSI tick.");
                return;
            }
            if(TextUtils.isEmpty(s3)) {
                zzawf.zzfa("No timestamp given for CSI tick.");
                return;
            }
            try {
                v = Long.parseLong(s3);
                v1 = zzq.zzlc().currentTimeMillis();
                v2 = zzq.zzlc().elapsedRealtime();
            }
            catch(NumberFormatException numberFormatException0) {
                zzawf.zzd("Malformed timestamp for CSI tick.", numberFormatException0);
                return;
            }
            if(TextUtils.isEmpty(s2)) {
                s2 = "native:view_load";
            }
            ((zzbdv)object0).zzyv().zza(s1, s2, v2 + (v - v1));
            return;
        }
        if("experiment".equals(s)) {
            String s4 = (String)map0.get("value");
            if(TextUtils.isEmpty(s4)) {
                zzawf.zzfa("No value given for CSI experiment.");
                return;
            }
            zzaak zzaak0 = ((zzbdv)object0).zzyv().zzqw();
            if(zzaak0 == null) {
                zzawf.zzfa("No ticker for WebView, dropping experiment ID.");
                return;
            }
            zzaak0.zzh("e", s4);
            return;
        }
        if("extra".equals(s)) {
            String s5 = (String)map0.get("name");
            String s6 = (String)map0.get("value");
            if(TextUtils.isEmpty(s6)) {
                zzawf.zzfa("No value given for CSI extra.");
                return;
            }
            if(TextUtils.isEmpty(s5)) {
                zzawf.zzfa("No name given for CSI extra.");
                return;
            }
            zzaak zzaak1 = ((zzbdv)object0).zzyv().zzqw();
            if(zzaak1 == null) {
                zzawf.zzfa("No ticker for WebView, dropping extra parameter.");
                return;
            }
            zzaak1.zzh(s5, s6);
        }
    }
}

