package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;

@TargetApi(17)
public final class zzber {
    private final zzbew zzeit;
    private final zzbev zzeiu;

    private zzber(zzbev zzbev0, zzbew zzbew0) {
        this.zzeit = zzbew0;
        this.zzeiu = zzbev0;
    }

    @JavascriptInterface
    public final String getClickSignals(String s) {
        if(TextUtils.isEmpty(s)) {
            zzawf.zzee("Click string is empty, not proceeding.");
            return "";
        }
        zzdq zzdq0 = ((zzbfd)this.zzeiu).zzaai();
        if(zzdq0 == null) {
            zzawf.zzee("Signal utils is empty, ignoring.");
            return "";
        }
        zzdg zzdg0 = zzdq0.zzcb();
        if(zzdg0 == null) {
            zzawf.zzee("Signals object is empty, ignoring.");
            return "";
        }
        if(this.zzeiu.getContext() == null) {
            zzawf.zzee("Context is null, ignoring.");
            return "";
        }
        return zzdg0.zza(this.zzeiu.getContext(), s, ((zzbff)this.zzeiu).getView(), this.zzeiu.zzys());
    }

    @JavascriptInterface
    public final void notify(String s) {
        if(TextUtils.isEmpty(s)) {
            zzawf.zzfa("URL is empty, ignoring message");
            return;
        }
        zzbet zzbet0 = () -> {
            Uri uri0 = Uri.parse(s);
            this.zzeit.zzh(uri0);
        };
        zzawo.zzdtx.post(zzbet0);
    }

    public static zzber zzc(zzbdv zzbdv0) {
        return new zzber(zzbdv0, new zzbeu(zzbdv0));
    }

    // 检测为 Lambda 实现
    final void zzfq(String s) [...]
}

