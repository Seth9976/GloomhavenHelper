package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import java.io.File;
import java.util.Collections;
import java.util.Map;

@TargetApi(11)
public class zzbfa extends zzbdy {
    public zzbfa(zzbdv zzbdv0, zzsn zzsn0, boolean z) {
        super(zzbdv0, zzsn0, z);
    }

    @Nullable
    protected final WebResourceResponse zza(WebView webView0, String s, @Nullable Map map0) {
        String s1;
        if(!(webView0 instanceof zzbdv)) {
            zzawf.zzfa("Tried to intercept request from a WebView that wasn\'t an AdWebView.");
            return null;
        }
        if(this.zzefw != null) {
            this.zzefw.zza(s, map0, 1);
        }
        if(!"mraid.js".equalsIgnoreCase(new File(s).getName())) {
            if(map0 == null) {
                map0 = Collections.emptyMap();
            }
            return super.zzd(s, map0);
        }
        if(((zzbdv)webView0).zzaaf() != null) {
            ((zzbdv)webView0).zzaaf().zzts();
        }
        if(((zzbdv)webView0).zzaad().zzaby()) {
            s1 = (String)zzvh.zzpd().zzd(zzzx.zzcig);
            return zzawo.zzd(((zzbdv)webView0).getContext(), ((zzbdv)webView0).zzyw().zzbmj, s1);
        }
        if(((zzbdv)webView0).zzaak()) {
            s1 = (String)zzvh.zzpd().zzd(zzzx.zzcif);
            return zzawo.zzd(((zzbdv)webView0).getContext(), ((zzbdv)webView0).zzyw().zzbmj, s1);
        }
        s1 = (String)zzvh.zzpd().zzd(zzzx.zzcie);
        return zzawo.zzd(((zzbdv)webView0).getContext(), ((zzbdv)webView0).zzyw().zzbmj, s1);
    }
}

