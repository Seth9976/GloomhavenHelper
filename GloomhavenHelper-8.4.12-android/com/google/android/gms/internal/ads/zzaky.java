package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

final class zzaky implements zzagh {
    private final zzazy zzdcg;
    private final zzakw zzdcj;

    public zzaky(zzakw zzakw0, zzazy zzazy0) {
        this.zzdcj = zzakw0;
        super();
        this.zzdcg = zzazy0;
    }

    @Override  // com.google.android.gms.internal.ads.zzagh
    public final void onFailure(@Nullable String s) {
        try {
            if(s == null) {
                zzakd zzakd0 = new zzakd();
                this.zzdcg.setException(zzakd0);
                return;
            }
            zzakd zzakd1 = new zzakd(s);
            this.zzdcg.setException(zzakd1);
        }
        catch(IllegalStateException unused_ex) {
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzagh
    public final void zzc(JSONObject jSONObject0) {
        try {
            Object object0 = this.zzdcj.zzdcc.zzd(jSONObject0);
            this.zzdcg.set(object0);
        }
        catch(IllegalStateException unused_ex) {
        }
        catch(JSONException jSONException0) {
            this.zzdcg.setException(jSONException0);
        }
    }
}

