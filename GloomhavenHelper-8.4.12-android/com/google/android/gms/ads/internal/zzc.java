package com.google.android.gms.ads.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.ads.zzaqm;
import com.google.android.gms.internal.ads.zzaub;
import com.google.android.gms.internal.ads.zzawo;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzc {
    private boolean zzblb;
    private zzaub zzblc;
    private zzaqm zzbld;
    private final Context zzur;

    public zzc(Context context0, zzaub zzaub0, zzaqm zzaqm0) {
        this.zzur = context0;
        this.zzblc = zzaub0;
        this.zzbld = new zzaqm();
    }

    public final void recordClick() {
        this.zzblb = true;
    }

    public final void zzbr(@Nullable String s) {
        if(!this.zzju()) {
            return;
        }
        if(s == null) {
            s = "";
        }
        zzaub zzaub0 = this.zzblc;
        if(zzaub0 != null) {
            zzaub0.zza(s, null, 3);
            return;
        }
        if(this.zzbld.zzdms && this.zzbld.zzdmt != null) {
            for(Object object0: this.zzbld.zzdmt) {
                String s1 = (String)object0;
                if(!TextUtils.isEmpty(s1)) {
                    String s2 = s1.replace("{NAVIGATION_URL}", Uri.encode(s));
                    zzawo.zzb(this.zzur, "", s2);
                }
            }
        }
    }

    // 去混淆评级： 低(30)
    private final boolean zzju() {
        return this.zzblc != null && this.zzblc.zzup().zzdqc || this.zzbld.zzdms;
    }

    // 去混淆评级： 低(20)
    public final boolean zzjv() {
        return !this.zzju() || this.zzblb;
    }
}

