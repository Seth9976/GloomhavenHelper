package com.google.android.gms.internal.ads;

import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.webkit.JsPromptResult;

final class zzbdu implements DialogInterface.OnClickListener {
    private final JsPromptResult zzefh;

    zzbdu(JsPromptResult jsPromptResult0) {
        this.zzefh = jsPromptResult0;
        super();
    }

    @Override  // android.content.DialogInterface$OnClickListener
    public final void onClick(DialogInterface dialogInterface0, int v) {
        this.zzefh.cancel();
    }
}

