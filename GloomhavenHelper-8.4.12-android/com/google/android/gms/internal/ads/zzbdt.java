package com.google.android.gms.internal.ads;

import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.widget.EditText;

final class zzbdt implements DialogInterface.OnClickListener {
    private final JsPromptResult zzefh;
    private final EditText zzefi;

    zzbdt(JsPromptResult jsPromptResult0, EditText editText0) {
        this.zzefh = jsPromptResult0;
        this.zzefi = editText0;
        super();
    }

    @Override  // android.content.DialogInterface$OnClickListener
    public final void onClick(DialogInterface dialogInterface0, int v) {
        String s = this.zzefi.getText().toString();
        this.zzefh.confirm(s);
    }
}

