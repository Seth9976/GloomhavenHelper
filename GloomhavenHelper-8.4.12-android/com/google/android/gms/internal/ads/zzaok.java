package com.google.android.gms.internal.ads;

import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;

final class zzaok implements DialogInterface.OnClickListener {
    private final zzaol zzdgd;

    zzaok(zzaol zzaol0) {
        this.zzdgd = zzaol0;
        super();
    }

    @Override  // android.content.DialogInterface$OnClickListener
    public final void onClick(DialogInterface dialogInterface0, int v) {
        Intent intent0 = this.zzdgd.createIntent();
        zzawo.zza(this.zzdgd.zzur, intent0);
    }
}

