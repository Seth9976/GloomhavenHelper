package com.google.android.gms.internal.ads;

import android.net.Uri;

final class zzbeu implements zzbew {
    private final zzbdv zzeiw;

    zzbeu(zzbdv zzbdv0) {
        this.zzeiw = zzbdv0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbew
    public final void zzh(Uri uri0) {
        zzbfi zzbfi0 = this.zzeiw.zzaaf();
        if(zzbfi0 == null) {
            zzawf.zzey("Unable to pass GMSG, no AdWebViewClient for AdWebView!");
            return;
        }
        zzbfi0.zzh(uri0);
    }
}

