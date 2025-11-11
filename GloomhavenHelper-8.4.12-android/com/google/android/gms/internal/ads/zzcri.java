package com.google.android.gms.internal.ads;

import android.view.View;
import com.google.android.gms.ads.internal.zze;
import javax.annotation.concurrent.GuardedBy;

public final class zzcri implements zze {
    @GuardedBy("this")
    private zze zzgfa;

    public final void zza(zze zze0) {
        synchronized(this) {
            this.zzgfa = zze0;
        }
    }

    @Override  // com.google.android.gms.ads.internal.zze
    public final void zzg(View view0) {
        synchronized(this) {
            if(this.zzgfa != null) {
                this.zzgfa.zzg(view0);
            }
        }
    }

    @Override  // com.google.android.gms.ads.internal.zze
    public final void zzjw() {
        synchronized(this) {
            if(this.zzgfa != null) {
                this.zzgfa.zzjw();
            }
        }
    }

    @Override  // com.google.android.gms.ads.internal.zze
    public final void zzjx() {
        synchronized(this) {
            if(this.zzgfa != null) {
                this.zzgfa.zzjx();
            }
        }
    }
}

