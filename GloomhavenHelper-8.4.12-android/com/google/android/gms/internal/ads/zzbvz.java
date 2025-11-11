package com.google.android.gms.internal.ads;

import android.view.View;
import androidx.annotation.Nullable;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;

public class zzbvz {
    @Nullable
    private final zzbdv zzdae;
    private final zzbxb zzfmb;

    public zzbvz(zzbxb zzbxb0) {
        this(zzbxb0, null);
    }

    public zzbvz(zzbxb zzbxb0, @Nullable zzbdv zzbdv0) {
        this.zzfmb = zzbxb0;
        this.zzdae = zzbdv0;
    }

    public Set zza(zzbxc zzbxc0) {
        return Collections.singleton(zzbuv.zzb(zzbxc0, zzazq.zzdxp));
    }

    @Nullable
    public final zzbdv zzagc() {
        return this.zzdae;
    }

    public final zzbxb zzajc() {
        return this.zzfmb;
    }

    @Nullable
    public final View zzajd() {
        zzbdv zzbdv0 = this.zzdae;
        return zzbdv0 != null ? zzbdv0.getWebView() : null;
    }

    @Nullable
    public final View zzaje() {
        zzbdv zzbdv0 = this.zzdae;
        return zzbdv0 == null ? null : zzbdv0.getWebView();
    }

    public final zzbuv zzb(Executor executor0) {
        return new zzbuv(new zzbwb(this.zzdae), executor0);
    }
}

