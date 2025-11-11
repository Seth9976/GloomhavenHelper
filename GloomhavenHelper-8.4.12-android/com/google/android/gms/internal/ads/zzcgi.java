package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzq;
import java.util.HashMap;
import java.util.regex.Pattern;

public final class zzcgi implements zzbqu, zzbsv {
    private final zzdei zzfdq;
    private final zzcgq zzfvp;
    private final zzcgx zzfvq;
    private final boolean zzfvs;

    public zzcgi(zzcgq zzcgq0, zzcgx zzcgx0, zzdei zzdei0, Context context0) {
        this.zzfvp = zzcgq0;
        this.zzfvq = zzcgx0;
        this.zzfdq = zzdei0;
        this.zzfvs = zzcgi.zzf(((String)zzvh.zzpd().zzd(zzzx.zzclj)), zzawo.zzbe(context0));
    }

    @Override  // com.google.android.gms.internal.ads.zzbqu
    public final void onAdImpression() {
        if(!this.zzfvs) {
            return;
        }
        if(!this.zzfdq.zzgpr.isEmpty()) {
            HashMap hashMap0 = new HashMap(this.zzfvp.zzqv());
            hashMap0.put("ancn", ((String)this.zzfdq.zzgpr.get(0)));
            hashMap0.put("action", "impression");
            this.zzfvq.zzm(hashMap0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbsv
    public final void zzaif() {
        if(!this.zzfvs) {
            return;
        }
        if(!this.zzfdq.zzgpr.isEmpty()) {
            HashMap hashMap0 = new HashMap(this.zzfvp.zzqv());
            hashMap0.put("ancn", ((String)this.zzfdq.zzgpr.get(0)));
            hashMap0.put("action", "adapter_impression");
            this.zzfvq.zzm(hashMap0);
        }
    }

    private static boolean zzf(String s, String s1) {
        if(s != null && s1 != null) {
            try {
                return Pattern.matches(s, s1);
            }
            catch(RuntimeException runtimeException0) {
                zzq.zzkz().zza(runtimeException0, "CsiImpressionListener.isPatternMatched");
                return false;
            }
        }
        return false;
    }
}

