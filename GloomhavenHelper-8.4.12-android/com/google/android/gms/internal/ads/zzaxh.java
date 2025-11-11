package com.google.android.gms.internal.ads;

import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;

final class zzaxh implements DialogInterface.OnClickListener {
    private final String zzczs;
    private final zzaxg zzdug;

    zzaxh(zzaxg zzaxg0, String s) {
        this.zzdug = zzaxg0;
        this.zzczs = s;
    }

    @Override  // android.content.DialogInterface$OnClickListener
    public final void onClick(DialogInterface dialogInterface0, int v) {
        this.zzdug.zza(this.zzczs, dialogInterface0, v);
    }
}

