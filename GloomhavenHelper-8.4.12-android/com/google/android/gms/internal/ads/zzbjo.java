package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;

public final class zzbjo implements zzbqt {
    private final zzdfb zzfdo;

    public zzbjo(zzdfb zzdfb0) {
        this.zzfdo = zzdfb0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqt
    public final void zzby(@Nullable Context context0) {
        try {
            this.zzfdo.pause();
        }
        catch(zzdfa zzdfa0) {
            zzawf.zzd("Cannot invoke onPause for the mediation adapter.", zzdfa0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqt
    public final void zzbz(@Nullable Context context0) {
        try {
            this.zzfdo.resume();
            if(context0 != null) {
                this.zzfdo.onContextChanged(context0);
            }
        }
        catch(zzdfa zzdfa0) {
            zzawf.zzd("Cannot invoke onResume for the mediation adapter.", zzdfa0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqt
    public final void zzca(@Nullable Context context0) {
        try {
            this.zzfdo.destroy();
        }
        catch(zzdfa zzdfa0) {
            zzawf.zzd("Cannot invoke onDestroy for the mediation adapter.", zzdfa0);
        }
    }
}

