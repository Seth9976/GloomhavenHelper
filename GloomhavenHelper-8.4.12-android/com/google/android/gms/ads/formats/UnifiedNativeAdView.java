package com.google.android.gms.ads.formats;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzacm;
import com.google.android.gms.internal.ads.zzacv;
import com.google.android.gms.internal.ads.zzazh;
import com.google.android.gms.internal.ads.zzvh;
import com.google.android.gms.internal.ads.zzye;
import com.google.android.gms.internal.ads.zzzx;

public final class UnifiedNativeAdView extends FrameLayout {
    private final FrameLayout zzbkk;
    private final zzacv zzbkl;

    public UnifiedNativeAdView(Context context0) {
        super(context0);
        this.zzbkk = this.zze(context0);
        this.zzbkl = this.zzjq();
    }

    public UnifiedNativeAdView(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.zzbkk = this.zze(context0);
        this.zzbkl = this.zzjq();
    }

    public UnifiedNativeAdView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.zzbkk = this.zze(context0);
        this.zzbkl = this.zzjq();
    }

    @TargetApi(21)
    public UnifiedNativeAdView(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.zzbkk = this.zze(context0);
        this.zzbkl = this.zzjq();
    }

    @Override  // android.view.ViewGroup
    public final void addView(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        super.addView(view0, v, viewGroup$LayoutParams0);
        super.bringChildToFront(this.zzbkk);
    }

    @Override  // android.view.ViewGroup
    public final void bringChildToFront(View view0) {
        super.bringChildToFront(view0);
        FrameLayout frameLayout0 = this.zzbkk;
        if(frameLayout0 != view0) {
            super.bringChildToFront(frameLayout0);
        }
    }

    public final void destroy() {
        try {
            this.zzbkl.destroy();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to destroy native ad view", remoteException0);
        }
    }

    @Override  // android.view.ViewGroup
    public final boolean dispatchTouchEvent(MotionEvent motionEvent0) {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcmm)).booleanValue()) {
            zzacv zzacv0 = this.zzbkl;
            if(zzacv0 != null) {
                try {
                    zzacv0.zzf(ObjectWrapper.wrap(motionEvent0));
                    return super.dispatchTouchEvent(motionEvent0);
                }
                catch(RemoteException remoteException0) {
                    zzazh.zzc("Unable to call handleTouchEvent on delegate", remoteException0);
                    return super.dispatchTouchEvent(motionEvent0);
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent0);
    }

    public final AdChoicesView getAdChoicesView() {
        View view0 = this.zzbq("3011");
        return view0 instanceof AdChoicesView ? ((AdChoicesView)view0) : null;
    }

    public final View getAdvertiserView() {
        return this.zzbq("3005");
    }

    public final View getBodyView() {
        return this.zzbq("3004");
    }

    public final View getCallToActionView() {
        return this.zzbq("3002");
    }

    public final View getHeadlineView() {
        return this.zzbq("3001");
    }

    public final View getIconView() {
        return this.zzbq("3003");
    }

    public final View getImageView() {
        return this.zzbq("3008");
    }

    public final MediaView getMediaView() {
        View view0 = this.zzbq("3010");
        if(view0 instanceof MediaView) {
            return (MediaView)view0;
        }
        if(view0 != null) {
            zzazh.zzeb("View is not an instance of MediaView");
        }
        return null;
    }

    public final View getPriceView() {
        return this.zzbq("3007");
    }

    public final View getStarRatingView() {
        return this.zzbq("3009");
    }

    public final View getStoreView() {
        return this.zzbq("3006");
    }

    @Override  // android.view.View
    public final void onVisibilityChanged(View view0, int v) {
        super.onVisibilityChanged(view0, v);
        zzacv zzacv0 = this.zzbkl;
        if(zzacv0 != null) {
            try {
                zzacv0.zzc(ObjectWrapper.wrap(view0), v);
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("Unable to call onVisibilityChanged on delegate", remoteException0);
            }
        }
    }

    @Override  // android.view.ViewGroup
    public final void removeAllViews() {
        super.removeAllViews();
        super.addView(this.zzbkk);
    }

    @Override  // android.view.ViewGroup
    public final void removeView(View view0) {
        if(this.zzbkk == view0) {
            return;
        }
        super.removeView(view0);
    }

    public final void setAdChoicesView(AdChoicesView adChoicesView0) {
        this.zza("3011", adChoicesView0);
    }

    public final void setAdvertiserView(View view0) {
        this.zza("3005", view0);
    }

    public final void setBodyView(View view0) {
        this.zza("3004", view0);
    }

    public final void setCallToActionView(View view0) {
        this.zza("3002", view0);
    }

    public final void setClickConfirmingView(View view0) {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(view0);
            this.zzbkl.zze(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to call setClickConfirmingView on delegate", remoteException0);
        }
    }

    public final void setHeadlineView(View view0) {
        this.zza("3001", view0);
    }

    public final void setIconView(View view0) {
        this.zza("3003", view0);
    }

    public final void setImageView(View view0) {
        this.zza("3008", view0);
    }

    public final void setMediaView(MediaView mediaView0) {
        this.zza("3010", mediaView0);
        if(mediaView0 != null) {
            mediaView0.zza((MediaContent mediaContent0) -> try {
                if(mediaContent0 instanceof zzye) {
                    zzacm zzacm0 = ((zzye)mediaContent0).zzqa();
                    this.zzbkl.zza(zzacm0);
                    return;
                }
                if(mediaContent0 == null) {
                    this.zzbkl.zza(null);
                    return;
                }
                zzazh.zzeb("Use MediaContent provided by UnifiedNativeAd.getMediaContent");
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("Unable to call setMediaContent on delegate", remoteException0);
            });
            mediaView0.zza((ImageView.ScaleType imageView$ScaleType0) -> if(imageView$ScaleType0 != null) {
                try {
                    IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(imageView$ScaleType0);
                    this.zzbkl.zzg(iObjectWrapper0);
                }
                catch(RemoteException remoteException0) {
                    zzazh.zzc("Unable to call setMediaViewImageScaleType on delegate", remoteException0);
                }
            });
        }
    }

    public final void setNativeAd(UnifiedNativeAd unifiedNativeAd0) {
        try {
            IObjectWrapper iObjectWrapper0 = (IObjectWrapper)unifiedNativeAd0.zzjo();
            this.zzbkl.zza(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to call setNativeAd on delegate", remoteException0);
        }
    }

    public final void setPriceView(View view0) {
        this.zza("3007", view0);
    }

    public final void setStarRatingView(View view0) {
        this.zza("3009", view0);
    }

    public final void setStoreView(View view0) {
        this.zza("3006", view0);
    }

    private final void zza(String s, View view0) {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(view0);
            this.zzbkl.zzb(s, iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to call setAssetView on delegate", remoteException0);
        }
    }

    // 检测为 Lambda 实现
    final void zza(ImageView.ScaleType imageView$ScaleType0) [...]

    // 检测为 Lambda 实现
    final void zza(MediaContent mediaContent0) [...]

    private final View zzbq(String s) {
        try {
            IObjectWrapper iObjectWrapper0 = this.zzbkl.zzcp(s);
            if(iObjectWrapper0 != null) {
                return (View)ObjectWrapper.unwrap(iObjectWrapper0);
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to call getAssetView on delegate", remoteException0);
        }
        return null;
    }

    private final FrameLayout zze(Context context0) {
        FrameLayout frameLayout0 = new FrameLayout(context0);
        frameLayout0.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.addView(frameLayout0);
        return frameLayout0;
    }

    private final zzacv zzjq() {
        Preconditions.checkNotNull(this.zzbkk, "createDelegate must be called after overlayFrame has been created");
        return this.isInEditMode() ? null : zzvh.zzpa().zza(this.zzbkk.getContext(), this, this.zzbkk);
    }
}

