package com.google.android.gms.internal.ads;

import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface;
import android.webkit.JsResult;

final class zzbdq implements DialogInterface.OnCancelListener {
    private final JsResult zzefg;

    zzbdq(JsResult jsResult0) {
        this.zzefg = jsResult0;
        super();
    }

    @Override  // android.content.DialogInterface$OnCancelListener
    public final void onCancel(DialogInterface dialogInterface0) {
        this.zzefg.cancel();
    }
}

