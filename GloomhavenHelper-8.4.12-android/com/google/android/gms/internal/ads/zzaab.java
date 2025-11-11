package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build.VERSION;
import com.google.android.gms.ads.internal.zzq;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;

public final class zzaab {
    private String zzbmj;
    private String zzcsn;
    private Map zzcsq;
    private Context zzur;

    public zzaab(Context context0, String s) {
        this.zzur = context0;
        this.zzbmj = s;
        this.zzcsn = (String)zzabf.zzcuh.get();
        this.zzcsq = new LinkedHashMap();
        this.zzcsq.put("s", "gmob_sdk");
        this.zzcsq.put("v", "3");
        this.zzcsq.put("os", Build.VERSION.RELEASE);
        this.zzcsq.put("sdk", Build.VERSION.SDK);
        this.zzcsq.put("device", zzawo.zzwq());
        Map map0 = this.zzcsq;
        boolean z = context0.getApplicationContext() == null;
        map0.put("app", "com.esotericsoftware.gloomhavenhelper");
        this.zzcsq.put("is_lite_sdk", (zzawo.zzba(context0) ? "1" : "0"));
        Future future0 = zzq.zzlg().zzz(this.zzur);
        try {
            this.zzcsq.put("network_coarse", Integer.toString(((zzarg)future0.get()).zzdnt));
            this.zzcsq.put("network_fine", Integer.toString(((zzarg)future0.get()).zzdnu));
        }
        catch(Exception exception0) {
            zzq.zzkz().zza(exception0, "CsiConfiguration.CsiConfiguration");
        }
    }

    final Context getContext() {
        return this.zzur;
    }

    final String zzkp() {
        return this.zzbmj;
    }

    final String zzqq() {
        return this.zzcsn;
    }

    final Map zzqr() {
        return this.zzcsq;
    }
}

