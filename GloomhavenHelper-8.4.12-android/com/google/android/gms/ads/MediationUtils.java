package com.google.android.gms.ads;

import android.content.Context;
import java.util.List;

public class MediationUtils {
    protected static final double MIN_HEIGHT_RATIO = 0.7;
    protected static final double MIN_WIDTH_RATIO = 0.5;

    // This method was un-flattened
    public static AdSize findClosestSize(Context context0, AdSize adSize0, List list0) {
        AdSize adSize1 = null;
        if(list0 != null && adSize0 != null) {
            if(!adSize0.zzdn()) {
                float f = context0.getResources().getDisplayMetrics().density;
                adSize0 = new AdSize(Math.round(((float)adSize0.getWidthInPixels(context0)) / f), Math.round(((float)adSize0.getHeightInPixels(context0)) / f));
            }
            for(Object object0: list0) {
                AdSize adSize2 = (AdSize)object0;
                if(adSize2 != null) {
                    int v = adSize0.getWidth();
                    int v1 = adSize2.getWidth();
                    int v2 = adSize0.getHeight();
                    int v3 = adSize2.getHeight();
                    if(((double)v) * 0.5 <= ((double)v1) && v >= v1) {
                        if(!adSize0.zzdn()) {
                            if(((double)v2) * 0.7 <= ((double)v3) && v2 >= v3) {
                                goto label_19;
                            }
                            continue;
                        }
                        else if(adSize0.zzdo() < v3) {
                            continue;
                        }
                    label_19:
                        if(adSize1 != null && adSize1.getWidth() * adSize1.getHeight() > adSize2.getWidth() * adSize2.getHeight()) {
                            adSize2 = adSize1;
                        }
                        adSize1 = adSize2;
                    }
                }
            }
            return adSize1;
        }
        return null;
    }
}

