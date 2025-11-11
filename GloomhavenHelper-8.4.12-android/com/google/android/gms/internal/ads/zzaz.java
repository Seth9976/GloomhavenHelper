package com.google.android.gms.internal.ads;

import android.content.Context;
import java.io.File;

final class zzaz implements zzap {
    private final Context val$appContext;
    private File zzco;

    zzaz(Context context0) {
        this.val$appContext = context0;
        super();
        this.zzco = null;
    }

    @Override  // com.google.android.gms.internal.ads.zzap
    public final File zzo() {
        if(this.zzco == null) {
            this.zzco = new File(this.val$appContext.getCacheDir(), "volley");
        }
        return this.zzco;
    }
}

