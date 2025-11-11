package com.google.android.gms.internal.ads;

import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface;
import android.webkit.JsPromptResult;

final class zzbdr implements DialogInterface.OnCancelListener {
    private final JsPromptResult zzefh;

    zzbdr(JsPromptResult jsPromptResult0) {
        this.zzefh = jsPromptResult0;
        super();
    }

    @Override  // android.content.DialogInterface$OnCancelListener
    public final void onCancel(DialogInterface dialogInterface0) {
        this.zzefh.cancel();
    }
}

