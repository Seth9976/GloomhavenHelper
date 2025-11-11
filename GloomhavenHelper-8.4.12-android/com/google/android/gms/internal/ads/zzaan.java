package com.google.android.gms.internal.ads;

import android.view.View;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzaan extends zzaao {
    private final zze zzcte;
    @Nullable
    private final String zzctf;
    private final String zzctg;

    public zzaan(zze zze0, @Nullable String s, String s1) {
        this.zzcte = zze0;
        this.zzctf = s;
        this.zzctg = s1;
    }

    @Override  // com.google.android.gms.internal.ads.zzaap
    public final String getContent() {
        return this.zzctg;
    }

    @Override  // com.google.android.gms.internal.ads.zzaap
    public final void recordClick() {
        this.zzcte.zzjw();
    }

    @Override  // com.google.android.gms.internal.ads.zzaap
    public final void recordImpression() {
        this.zzcte.zzjx();
    }

    @Override  // com.google.android.gms.internal.ads.zzaap
    public final void zzn(@Nullable IObjectWrapper iObjectWrapper0) {
        if(iObjectWrapper0 == null) {
            return;
        }
        View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
        this.zzcte.zzg(view0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaap
    public final String zzqx() {
        return this.zzctf;
    }
}

