package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.overlay.zzb;
import com.google.android.gms.ads.internal.zzc;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.VisibleForTesting;
import java.net.URISyntaxException;
import java.util.Map;

public final class zzagd implements zzafz {
    private final zzc zzcyt;
    private final zzaom zzcyu;

    public zzagd(zzc zzc0, zzaom zzaom0) {
        this.zzcyt = zzc0;
        this.zzcyu = zzaom0;
    }

    @VisibleForTesting
    static Uri zza(Context context0, zzdq zzdq0, Uri uri0, View view0, @Nullable Activity activity0) {
        if(zzdq0 == null) {
            return uri0;
        }
        try {
            if(zzdq0.zzc(uri0)) {
                return zzdq0.zza(uri0, context0, view0, activity0);
            }
        }
        catch(zzdt exception0) {
            zzq.zzkz().zza(exception0, "OpenGmsgHandler.maybeAddClickSignalsToUri");
        }
        catch(Exception unused_ex) {
        }
        return uri0;
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        String s4;
        zzbev zzbev0 = (zzbev)object0;
        String s = zzaux.zzb(((String)map0.get("u")), zzbev0.getContext(), true);
        String s1 = (String)map0.get("a");
        if(s1 == null) {
            zzawf.zzfa("Action missing from an open GMSG.");
            return;
        }
        if(this.zzcyt != null && !this.zzcyt.zzjv()) {
            this.zzcyt.zzbr(s);
            return;
        }
        if("expand".equalsIgnoreCase(s1)) {
            if(((zzbey)zzbev0).zzaak()) {
                zzawf.zzfa("Cannot expand WebView that is already expanded.");
                return;
            }
            this.zzab(false);
            ((zzbfc)zzbev0).zzc(zzagd.zzd(map0), zzagd.zze(map0));
            return;
        }
        if("webapp".equalsIgnoreCase(s1)) {
            this.zzab(false);
            if(s != null) {
                ((zzbfc)zzbev0).zza(zzagd.zzd(map0), zzagd.zze(map0), s);
                return;
            }
            ((zzbfc)zzbev0).zza(zzagd.zzd(map0), zzagd.zze(map0), ((String)map0.get("html")), ((String)map0.get("baseurl")));
            return;
        }
        if("app".equalsIgnoreCase(s1) && "true".equalsIgnoreCase(((String)map0.get("system_browser")))) {
            this.zzab(true);
            if(TextUtils.isEmpty(s)) {
                zzawf.zzfa("Destination url cannot be empty.");
                return;
            }
            Intent intent0 = new zzagc(zzbev0.getContext(), ((zzbfd)zzbev0).zzaai(), ((zzbff)zzbev0).getView()).zzc(map0);
            try {
                ((zzbfc)zzbev0).zza(new zzb(intent0));
            }
            catch(ActivityNotFoundException activityNotFoundException0) {
                zzawf.zzfa(activityNotFoundException0.getMessage());
            }
            return;
        }
        this.zzab(true);
        String s2 = (String)map0.get("intent_url");
        Intent intent1 = null;
        if(!TextUtils.isEmpty(s2)) {
            try {
                intent1 = Intent.parseUri(s2, 0);
            }
            catch(URISyntaxException uRISyntaxException0) {
                String s3 = String.valueOf(s2);
                zzawf.zzc((s3.length() == 0 ? new String("Error parsing the url: ") : "Error parsing the url: " + s3), uRISyntaxException0);
            }
        }
        if(intent1 != null && intent1.getData() != null) {
            Uri uri0 = intent1.getData();
            if(!Uri.EMPTY.equals(uri0)) {
                intent1.setData(zzagd.zzf(zzagd.zza(zzbev0.getContext(), ((zzbfd)zzbev0).zzaai(), uri0, ((zzbff)zzbev0).getView(), zzbev0.zzys())));
            }
        }
        if(intent1 != null) {
            ((zzbfc)zzbev0).zza(new zzb(intent1));
            return;
        }
        if(TextUtils.isEmpty(s)) {
            s4 = s;
        }
        else {
            Uri uri1 = Uri.parse(s);
            s4 = zzagd.zzf(zzagd.zza(zzbev0.getContext(), ((zzbfd)zzbev0).zzaai(), uri1, ((zzbff)zzbev0).getView(), zzbev0.zzys())).toString();
        }
        ((zzbfc)zzbev0).zza(new zzb(((String)map0.get("i")), s4, ((String)map0.get("m")), ((String)map0.get("p")), ((String)map0.get("c")), ((String)map0.get("f")), ((String)map0.get("e"))));
    }

    private final void zzab(boolean z) {
        zzaom zzaom0 = this.zzcyu;
        if(zzaom0 != null) {
            zzaom0.zzac(z);
        }
    }

    private static boolean zzd(Map map0) {
        return "1".equals(map0.get("custom_close"));
    }

    private static int zze(Map map0) {
        String s = (String)map0.get("o");
        if(s != null) {
            if("p".equalsIgnoreCase(s)) {
                return 7;
            }
            if("l".equalsIgnoreCase(s)) {
                return 6;
            }
            return "c".equalsIgnoreCase(s) ? zzq.zzkx().zzwt() : -1;
        }
        return -1;
    }

    @VisibleForTesting
    static Uri zzf(Uri uri0) {
        try {
            if(uri0.getQueryParameter("aclk_ms") != null) {
                return uri0.buildUpon().appendQueryParameter("aclk_upms", "40717387").build();
            }
        }
        catch(UnsupportedOperationException unsupportedOperationException0) {
            String s = uri0.toString();
            zzawf.zzc((s.length() == 0 ? new String("Error adding click uptime parameter to url: ") : "Error adding click uptime parameter to url: " + s), unsupportedOperationException0);
        }
        return uri0;
    }
}

