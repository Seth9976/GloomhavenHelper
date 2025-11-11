package com.google.android.gms.internal.ads;

public final class zzabr {
    private static zzaax zzcvr;
    public static zzaax zzcvs;
    private static zzaax zzcvt;

    static {
        zzabr.zzcvr = zzaax.zzi("gads:native:engine_js_url_with_protocol", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/production/native_ads.js");
        zzabr.zzcvs = zzaax.zzi("gads:sdk_core_location", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/sdk-core-v40-loader.html");
        zzabr.zzcvt = zzaax.zzi("gads:sdk_core_js_location", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/production/sdk-core-v40-impl.js");
    }
}

