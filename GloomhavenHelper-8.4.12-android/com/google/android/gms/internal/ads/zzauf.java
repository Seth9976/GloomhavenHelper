package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzq;

public final class zzauf implements zzpt {
    private final Object lock;
    private String zzbri;
    private final Context zzcgw;
    private boolean zzdqf;

    public zzauf(Context context0, String s) {
        if(context0.getApplicationContext() != null) {
            context0 = context0.getApplicationContext();
        }
        this.zzcgw = context0;
        this.zzbri = s;
        this.zzdqf = false;
        this.lock = new Object();
    }

    public final String getAdUnitId() {
        return this.zzbri;
    }

    @Override  // com.google.android.gms.internal.ads.zzpt
    public final void zza(zzpu zzpu0) {
        this.zzam(zzpu0.zzbnz);
    }

    public final void zzam(boolean z) {
        if(!zzq.zzlt().zzad(this.zzcgw)) {
            return;
        }
        synchronized(this.lock) {
            if(this.zzdqf == z) {
                return;
            }
            this.zzdqf = z;
            if(TextUtils.isEmpty(this.zzbri)) {
                return;
            }
            if(this.zzdqf) {
                zzq.zzlt().zze(this.zzcgw, this.zzbri);
            }
            else {
                zzq.zzlt().zzf(this.zzcgw, this.zzbri);
            }
        }
    }
}

