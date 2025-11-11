package com.google.android.gms.internal.ads;

import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;

final class zzaxi implements DialogInterface.OnClickListener {
    private final zzaxg zzdug;
    private final int zzdul;
    private final int zzdum;
    private final int zzdun;

    zzaxi(zzaxg zzaxg0, int v, int v1, int v2) {
        this.zzdug = zzaxg0;
        this.zzdul = v;
        this.zzdum = v1;
        this.zzdun = v2;
    }

    @Override  // android.content.DialogInterface$OnClickListener
    public final void onClick(DialogInterface dialogInterface0, int v) {
        this.zzdug.zza(this.zzdul, this.zzdum, this.zzdun, dialogInterface0, v);
    }
}

