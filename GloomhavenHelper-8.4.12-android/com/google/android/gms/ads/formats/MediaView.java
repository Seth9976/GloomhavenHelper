package com.google.android.gms.ads.formats;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.internal.ads.zzacc;
import com.google.android.gms.internal.ads.zzace;

public class MediaView extends FrameLayout {
    private MediaContent zzbjx;
    private boolean zzbjy;
    private zzacc zzbjz;
    private ImageView.ScaleType zzbka;
    private boolean zzbkb;
    private zzace zzbkc;

    public MediaView(Context context0) {
        super(context0);
    }

    public MediaView(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    public MediaView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
    }

    @TargetApi(21)
    public MediaView(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
    }

    public void setImageScaleType(ImageView.ScaleType imageView$ScaleType0) {
        this.zzbkb = true;
        this.zzbka = imageView$ScaleType0;
        zzace zzace0 = this.zzbkc;
        if(zzace0 != null) {
            zzace0.setImageScaleType(this.zzbka);
        }
    }

    public void setMediaContent(MediaContent mediaContent0) {
        this.zzbjy = true;
        this.zzbjx = mediaContent0;
        zzacc zzacc0 = this.zzbjz;
        if(zzacc0 != null) {
            zzacc0.setMediaContent(mediaContent0);
        }
    }

    protected final void zza(zzacc zzacc0) {
        synchronized(this) {
            this.zzbjz = zzacc0;
            if(this.zzbjy) {
                zzacc0.setMediaContent(this.zzbjx);
            }
        }
    }

    protected final void zza(zzace zzace0) {
        synchronized(this) {
            this.zzbkc = zzace0;
            if(this.zzbkb) {
                zzace0.setImageScaleType(this.zzbka);
            }
        }
    }
}

