package com.google.android.gms.internal.ads;

import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.net.Uri;

final class zzaxn implements DialogInterface.OnClickListener {
    private final zzaxo zzdut;

    zzaxn(zzaxo zzaxo0) {
        this.zzdut = zzaxo0;
        super();
    }

    @Override  // android.content.DialogInterface$OnClickListener
    public final void onClick(DialogInterface dialogInterface0, int v) {
        Uri uri0 = Uri.parse("https://support.google.com/dfp_premium/answer/7160685#push");
        zzawo.zza(this.zzdut.val$context, uri0);
    }
}

