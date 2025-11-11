package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import java.util.HashSet;
import javax.annotation.concurrent.GuardedBy;

public final class zzdfj implements zzavx, zzbqm {
    private final zzawc zzdrf;
    @GuardedBy("this")
    private final HashSet zzgrf;
    private final Context zzur;

    public zzdfj(Context context0, zzawc zzawc0) {
        this.zzgrf = new HashSet();
        this.zzur = context0;
        this.zzdrf = zzawc0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqm
    public final void onAdFailedToLoad(int v) {
        synchronized(this) {
            if(v != 3) {
                this.zzdrf.zzb(this.zzgrf);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzavx
    public final void zza(HashSet hashSet0) {
        synchronized(this) {
            this.zzgrf.clear();
            this.zzgrf.addAll(hashSet0);
        }
    }

    public final Bundle zzare() {
        return this.zzdrf.zza(this.zzur, this);
    }
}

