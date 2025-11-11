package com.google.android.gms.internal.ads;

import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;

final class zzaot implements DialogInterface.OnClickListener {
    private final zzaor zzdhb;

    zzaot(zzaor zzaor0) {
        this.zzdhb = zzaor0;
        super();
    }

    @Override  // android.content.DialogInterface$OnClickListener
    public final void onClick(DialogInterface dialogInterface0, int v) {
        this.zzdhb.zzdt("User canceled the download.");
    }
}

