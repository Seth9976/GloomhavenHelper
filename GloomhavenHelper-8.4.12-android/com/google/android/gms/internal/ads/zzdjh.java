package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.ShowFirstParty;

@ShowFirstParty
public final class zzdjh {
    private final Looper zzgxc;
    private final Context zzur;

    public zzdjh(@NonNull Context context0, @NonNull Looper looper0) {
        this.zzur = context0;
        this.zzgxc = looper0;
    }

    public final void zzgt(@NonNull String s) {
        zzdjn zzdjn0 = (zzdjn)(((zzdyz)zzdjn.zzati().zzgw("com.esotericsoftware.gloomhavenhelper").zzb(zzb.zzgxr).zza(zzdjj.zzatg().zzgv(s).zzb(zza.zzgxk)).zzbcx()));
        new zzdjg(this.zzur, this.zzgxc, zzdjn0).zzatf();
    }
}

