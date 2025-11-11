package com.google.android.gms.internal.ads;

import android.os.Bundle;
import androidx.annotation.Nullable;

public final class zzcvb implements zzcye {
    @Nullable
    private final zzdby zzgit;

    zzcvb(@Nullable zzdby zzdby0) {
        this.zzgit = zzdby0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        return this.zzgit == null || this.zzgit.zzaqn() == null || this.zzgit.zzaqn().isEmpty() ? zzdnt.zzaj(null) : zzdnt.zzaj(new zzcve(this));
    }

    final void zzo(Bundle bundle0) {
        bundle0.putString("key_schema", this.zzgit.zzaqn());
    }
}

