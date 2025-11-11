package com.google.android.gms.internal.ads;

import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.webkit.JsResult;

final class zzbdp implements DialogInterface.OnClickListener {
    private final JsResult zzefg;

    zzbdp(JsResult jsResult0) {
        this.zzefg = jsResult0;
        super();
    }

    @Override  // android.content.DialogInterface$OnClickListener
    public final void onClick(DialogInterface dialogInterface0, int v) {
        this.zzefg.cancel();
    }
}

