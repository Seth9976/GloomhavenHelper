package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.R.styleable;

public final class zzut {
    private final String zzbri;
    private final AdSize[] zzcdt;

    public zzut(Context context0, AttributeSet attributeSet0) {
        TypedArray typedArray0 = context0.getResources().obtainAttributes(attributeSet0, styleable.AdsAttrs);
        String s = typedArray0.getString(styleable.AdsAttrs_adSize);
        String s1 = typedArray0.getString(styleable.AdsAttrs_adSizes);
        boolean z = TextUtils.isEmpty(s);
        boolean z1 = TextUtils.isEmpty(s1);
        if(!z != 0 && !z1 == 0) {
            this.zzcdt = zzut.zzcd(s);
        }
        else if(!z == 0 && !z1 != 0) {
            this.zzcdt = zzut.zzcd(s1);
        }
        else {
            throw !z == 0 ? new IllegalArgumentException("Required XML attribute \"adSize\" was missing.") : new IllegalArgumentException("Either XML attribute \"adSize\" or XML attribute \"supportedAdSizes\" should be specified, but not both.");
        }
        this.zzbri = typedArray0.getString(styleable.AdsAttrs_adUnitId);
        if(TextUtils.isEmpty(this.zzbri)) {
            throw new IllegalArgumentException("Required XML attribute \"adUnitId\" was missing.");
        }
    }

    public final String getAdUnitId() {
        return this.zzbri;
    }

    private static AdSize[] zzcd(String s) {
        int v2;
        int v1;
        String[] arr_s = s.split("\\s*,\\s*");
        AdSize[] arr_adSize = new AdSize[arr_s.length];
        int v = 0;
        while(v < arr_s.length) {
            String s1 = arr_s[v].trim();
            if(s1.matches("^(\\d+|FULL_WIDTH)\\s*[xX]\\s*(\\d+|AUTO_HEIGHT)$")) {
                String[] arr_s1 = s1.split("[xX]");
                arr_s1[0] = arr_s1[0].trim();
                arr_s1[1] = arr_s1[1].trim();
                try {
                    v1 = "FULL_WIDTH".equals(arr_s1[0]) ? -1 : Integer.parseInt(arr_s1[0]);
                    v2 = "AUTO_HEIGHT".equals(arr_s1[1]) ? -2 : Integer.parseInt(arr_s1[1]);
                }
                catch(NumberFormatException unused_ex) {
                    String s2 = String.valueOf(s1);
                    throw new IllegalArgumentException((s2.length() == 0 ? new String("Could not parse XML attribute \"adSize\": ") : "Could not parse XML attribute \"adSize\": " + s2));
                }
                arr_adSize[v] = new AdSize(v1, v2);
            }
            else if("BANNER".equals(s1)) {
                arr_adSize[v] = AdSize.BANNER;
            }
            else if("LARGE_BANNER".equals(s1)) {
                arr_adSize[v] = AdSize.LARGE_BANNER;
            }
            else if("FULL_BANNER".equals(s1)) {
                arr_adSize[v] = AdSize.FULL_BANNER;
            }
            else if("LEADERBOARD".equals(s1)) {
                arr_adSize[v] = AdSize.LEADERBOARD;
            }
            else if("MEDIUM_RECTANGLE".equals(s1)) {
                arr_adSize[v] = AdSize.MEDIUM_RECTANGLE;
            }
            else if("SMART_BANNER".equals(s1)) {
                arr_adSize[v] = AdSize.SMART_BANNER;
            }
            else if("WIDE_SKYSCRAPER".equals(s1)) {
                arr_adSize[v] = AdSize.WIDE_SKYSCRAPER;
            }
            else if("FLUID".equals(s1)) {
                arr_adSize[v] = AdSize.FLUID;
            }
            else {
                if(!"ICON".equals(s1)) {
                    goto label_44;
                }
                arr_adSize[v] = AdSize.zzabj;
            }
            ++v;
            continue;
        label_44:
            String s3 = String.valueOf(s1);
            throw new IllegalArgumentException((s3.length() == 0 ? new String("Could not parse XML attribute \"adSize\": ") : "Could not parse XML attribute \"adSize\": " + s3));
        }
        if(arr_adSize.length == 0) {
            String s4 = String.valueOf(s);
            throw new IllegalArgumentException((s4.length() == 0 ? new String("Could not parse XML attribute \"adSize\": ") : "Could not parse XML attribute \"adSize\": " + s4));
        }
        return arr_adSize;
    }

    public final AdSize[] zzy(boolean z) {
        if(!z && this.zzcdt.length != 1) {
            throw new IllegalArgumentException("The adSizes XML attribute is only allowed on PublisherAdViews.");
        }
        return this.zzcdt;
    }
}

