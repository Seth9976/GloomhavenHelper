package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;

public final class zzdq {
    private String zzwr;
    private String zzws;
    private String zzwt;
    private String[] zzwu;
    private static final String[] zzwv;
    private zzdg zzww;

    static {
        zzdq.zzwv = new String[]{"/aclk", "/pcs/click", "/dbm/clk"};
    }

    public zzdq(zzdg zzdg0) {
        this.zzwr = "googleads.g.doubleclick.net";
        this.zzws = "/pagead/ads";
        this.zzwt = "ad.doubleclick.net";
        this.zzwu = new String[]{".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
        this.zzww = zzdg0;
    }

    private final Uri zza(Uri uri0, String s) throws zzdt {
        try {
            boolean z = this.zza(uri0);
            if(z) {
                if(uri0.toString().contains("dc_ms=")) {
                    throw new zzdt("Parameter already exists: dc_ms");
                }
            }
            else if(uri0.getQueryParameter("ms") != null) {
                throw new zzdt("Query parameter already exists: ms");
            }
            if(z) {
                String s1 = uri0.toString();
                int v = s1.indexOf(";adurl");
                if(v != -1) {
                    return Uri.parse((s1.substring(0, v + 1) + "dc_ms" + "=" + s + ";" + s1.substring(v + 1)));
                }
                String s2 = uri0.getEncodedPath();
                int v1 = s1.indexOf(s2);
                return Uri.parse((s1.substring(0, s2.length() + v1) + ";" + "dc_ms" + "=" + s + ";" + s1.substring(v1 + s2.length())));
            }
            String s3 = uri0.toString();
            int v2 = s3.indexOf("&adurl");
            if(v2 == -1) {
                v2 = s3.indexOf("?adurl");
            }
            return v2 == -1 ? uri0.buildUpon().appendQueryParameter("ms", s).build() : Uri.parse((s3.substring(0, v2 + 1) + "ms" + "=" + s + "&" + s3.substring(v2 + 1)));
        }
        catch(UnsupportedOperationException unused_ex) {
            throw new zzdt("Provided Uri is not in a valid state");
        }
    }

    private final boolean zza(Uri uri0) {
        if(uri0 != null) {
            try {
                return uri0.getHost().equals(this.zzwt);
            }
            catch(NullPointerException unused_ex) {
                return false;
            }
        }
        throw new NullPointerException();
    }

    public final Uri zza(Uri uri0, Context context0) throws zzdt {
        return this.zza(uri0, this.zzww.zzb(context0));
    }

    public final Uri zza(Uri uri0, Context context0, View view0, Activity activity0) throws zzdt {
        try {
            return this.zza(uri0, this.zzww.zza(context0, uri0.getQueryParameter("ai"), view0, activity0));
        }
        catch(UnsupportedOperationException unused_ex) {
            throw new zzdt("Provided Uri is not in a valid state");
        }
    }

    public final void zza(MotionEvent motionEvent0) {
        this.zzww.zza(motionEvent0);
    }

    public final boolean zzb(Uri uri0) {
        if(uri0 != null) {
            try {
                String s = uri0.getHost();
                String[] arr_s = this.zzwu;
                for(int v = 0; true; ++v) {
                    if(v >= arr_s.length) {
                        return false;
                    }
                    if(s.endsWith(arr_s[v])) {
                        break;
                    }
                }
                return true;
            }
            catch(NullPointerException unused_ex) {
                return false;
            }
        }
        throw new NullPointerException();
    }

    public final boolean zzc(Uri uri0) {
        if(this.zzb(uri0)) {
            String[] arr_s = zzdq.zzwv;
            for(int v = 0; v < arr_s.length; ++v) {
                String s = arr_s[v];
                if(uri0.getPath().endsWith(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final zzdg zzcb() {
        return this.zzww;
    }
}

