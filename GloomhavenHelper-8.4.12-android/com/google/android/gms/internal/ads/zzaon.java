package com.google.android.gms.internal.ads;

import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;

final class zzaon implements DialogInterface.OnClickListener {
    private final zzaol zzdgd;

    zzaon(zzaol zzaol0) {
        this.zzdgd = zzaol0;
        super();
    }

    @Override  // android.content.DialogInterface$OnClickListener
    public final void onClick(DialogInterface dialogInterface0, int v) {
        this.zzdgd.zzdt("Operation denied by user.");
    }
}

