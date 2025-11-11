package com.google.android.gms.internal.ads;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.overlay.zzv;

final class zzcda implements zzp, zzv, zzafe, zzafg, zztz {
    private zztz zzcch;
    private zzafe zzcxu;
    private zzafg zzcxv;
    private zzp zzdit;
    private zzv zzdix;

    private zzcda() {
    }

    zzcda(zzccw zzccw0) {
    }

    @Override  // com.google.android.gms.internal.ads.zztz
    public final void onAdClicked() {
        synchronized(this) {
            if(this.zzcch != null) {
                this.zzcch.onAdClicked();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzafg
    public final void onAppEvent(String s, @Nullable String s1) {
        synchronized(this) {
            if(this.zzcxv != null) {
                this.zzcxv.onAppEvent(s, s1);
            }
        }
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onPause() {
        synchronized(this) {
            if(this.zzdit != null) {
                this.zzdit.onPause();
            }
        }
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onResume() {
        synchronized(this) {
            if(this.zzdit != null) {
                this.zzdit.onResume();
            }
        }
    }

    private final void zza(zztz zztz0, zzafe zzafe0, zzp zzp0, zzafg zzafg0, zzv zzv0) {
        synchronized(this) {
            this.zzcch = zztz0;
            this.zzcxu = zzafe0;
            this.zzdit = zzp0;
            this.zzcxv = zzafg0;
            this.zzdix = zzv0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzafe
    public final void zza(String s, Bundle bundle0) {
        synchronized(this) {
            if(this.zzcxu != null) {
                this.zzcxu.zza(s, bundle0);
            }
        }
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztj() {
        synchronized(this) {
            if(this.zzdit != null) {
                this.zzdit.zztj();
            }
        }
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztk() {
        synchronized(this) {
            if(this.zzdit != null) {
                this.zzdit.zztk();
            }
        }
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzv
    public final void zzub() {
        synchronized(this) {
            if(this.zzdix != null) {
                this.zzdix.zzub();
            }
        }
    }
}

