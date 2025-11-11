package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

final class zzakq implements zzagh {
    private final zzakp zzdcb;
    private final zzajr zzdcf;
    private final zzazy zzdcg;

    public zzakq(zzakp zzakp0, zzajr zzajr0, zzazy zzazy0) {
        this.zzdcb = zzakp0;
        super();
        this.zzdcf = zzajr0;
        this.zzdcg = zzazy0;
    }

    @Override  // com.google.android.gms.internal.ads.zzagh
    public final void onFailure(@Nullable String s) {
        try {
            if(s == null) {
                zzakd zzakd0 = new zzakd();
                this.zzdcg.setException(zzakd0);
            }
            else {
                zzakd zzakd1 = new zzakd(s);
                this.zzdcg.setException(zzakd1);
            }
        }
        catch(IllegalStateException unused_ex) {
        }
        finally {
            this.zzdcf.release();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzagh
    public final void zzc(JSONObject jSONObject0) {
        try {
            Object object0 = zzakp.zza(this.zzdcb).zzd(jSONObject0);
            this.zzdcg.set(object0);
        }
        catch(IllegalStateException unused_ex) {
        }
        catch(JSONException jSONException0) {
            this.zzdcg.setException(jSONException0);
        }
        finally {
            this.zzdcf.release();
        }
    }
}

