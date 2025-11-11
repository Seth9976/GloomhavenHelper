package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import androidx.annotation.Nullable;
import java.util.Collections;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzbga {
    private final String method;
    public final Uri uri;
    public final String url;
    public final Map zzab;

    @TargetApi(21)
    public zzbga(WebResourceRequest webResourceRequest0) {
        this(webResourceRequest0.getUrl().toString(), webResourceRequest0.getUrl(), webResourceRequest0.getMethod(), webResourceRequest0.getRequestHeaders());
    }

    public zzbga(String s) {
        this(s, Uri.parse(s), null, null);
    }

    private zzbga(String s, Uri uri0, @Nullable String s1, @Nullable Map map0) {
        this.url = s;
        this.uri = uri0;
        if(s1 == null) {
            s1 = "GET";
        }
        this.method = s1;
        if(map0 == null) {
            map0 = Collections.emptyMap();
        }
        this.zzab = map0;
    }
}

