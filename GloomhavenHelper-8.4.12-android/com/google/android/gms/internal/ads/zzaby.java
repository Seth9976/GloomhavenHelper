package com.google.android.gms.internal.ads;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;

public final class zzaby extends zzaci {
    private final int backgroundColor;
    private final int textColor;
    private final int textSize;
    private static final int zzcwe;
    private static final int zzcwf;
    private static final int zzcwg;
    private static final int zzcwh;
    private final String zzcwi;
    private final List zzcwj;
    private final List zzcwk;
    private final int zzcwl;
    private final int zzcwm;
    private final boolean zzcwn;

    static {
        zzaby.zzcwe = Color.rgb(12, 0xAE, 206);
        int v = Color.rgb(204, 204, 204);
        zzaby.zzcwf = v;
        zzaby.zzcwg = v;
        zzaby.zzcwh = zzaby.zzcwe;
    }

    public zzaby(String s, List list0, Integer integer0, Integer integer1, Integer integer2, int v, int v1, boolean z) {
        this.zzcwj = new ArrayList();
        this.zzcwk = new ArrayList();
        this.zzcwi = s;
        if(list0 != null) {
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                zzacd zzacd0 = (zzacd)list0.get(v2);
                this.zzcwj.add(zzacd0);
                this.zzcwk.add(zzacd0);
            }
        }
        this.backgroundColor = integer0 == null ? zzaby.zzcwg : ((int)integer0);
        this.textColor = integer1 == null ? zzaby.zzcwh : ((int)integer1);
        this.textSize = integer2 == null ? 12 : ((int)integer2);
        this.zzcwl = v;
        this.zzcwm = v1;
        this.zzcwn = z;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    @Override  // com.google.android.gms.internal.ads.zzacj
    public final String getText() {
        return this.zzcwi;
    }

    public final int getTextColor() {
        return this.textColor;
    }

    public final int getTextSize() {
        return this.textSize;
    }

    @Override  // com.google.android.gms.internal.ads.zzacj
    public final List zzrb() {
        return this.zzcwk;
    }

    public final List zzrc() {
        return this.zzcwj;
    }

    public final int zzrd() {
        return this.zzcwl;
    }

    public final int zzre() {
        return this.zzcwm;
    }
}

