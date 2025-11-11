package com.google.android.gms.internal.ads;

public final class zzabb {
    public static zzaax zzctp;
    public static zzaax zzctq;
    public static zzaax zzctr;
    public static zzaax zzcts;

    static {
        zzabb.zzctp = zzaax.zzf("gads:afs:csa_send_tcf_data", false);
        zzabb.zzctq = zzaax.zzi("gads:afs:csa_tcf_data_to_collect", "[{\"bk\":\"tcString\",\"sk\":\"IABTCF_TCString\",\"type\":0},{\"bk\":\"gdprApplies\",\"sk\":\"IABTCF_gdprApplies\",\"type\":1}]");
        zzabb.zzctr = zzaax.zzi("gads:afs:csa_webview_custom_domain_param_key", "csa_customDomain");
        zzabb.zzcts = zzaax.zzi("gads:afs:csa_webview_static_file_path", "/afs/ads/i/webview.html");
    }
}

