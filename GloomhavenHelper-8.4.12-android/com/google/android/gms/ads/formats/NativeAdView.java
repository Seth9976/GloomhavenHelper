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
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzacv;
import com.google.android.gms.internal.ads.zzazh;
import com.google.android.gms.internal.ads.zzvh;
import com.google.android.gms.internal.ads.zzzx;

@Deprecated
public class NativeAdView extends FrameLayout {
    private final FrameLayout zzbkk;
    private final zzacv zzbkl;

    public NativeAdView(Context context0) {
        super(context0);
        this.zzbkk = this.zze(context0);
        this.zzbkl = this.zzjq();
    }

    public NativeAdView(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.zzbkk = this.zze(context0);
        this.zzbkl = this.zzjq();
    }

    public NativeAdView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.zzbkk = this.zze(context0);
        this.zzbkl = this.zzjq();
    }

    @TargetApi(21)
    public NativeAdView(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.zzbkk = this.zze(context0);
        this.zzbkl = this.zzjq();
    }

    @Override  // android.view.ViewGroup
    public void addView(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        super.addView(view0, v, viewGroup$LayoutParams0);
        super.bringChildToFront(this.zzbkk);
    }

    @Override  // android.view.ViewGroup
    public void bringChildToFront(View view0) {
        super.bringChildToFront(view0);
        FrameLayout frameLayout0 = this.zzbkk;
        if(frameLayout0 != view0) {
            super.bringChildToFront(frameLayout0);
        }
    }

    public void destroy() {
        try {
            this.zzbkl.destroy();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to destroy native ad view", remoteException0);
        }
    }

    @Override  // android.view.ViewGroup
    public boolean dispatchTouchEvent(MotionEvent motionEvent0) {
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

    public AdChoicesView getAdChoicesView() {
        View view0 = this.zzbq("1098");
        return view0 instanceof AdChoicesView ? ((AdChoicesView)view0) : null;
    }

    @Override  // android.view.View
    public void onVisibilityChanged(View view0, int v) {
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
    public void removeAllViews() {
        super.removeAllViews();
        super.addView(this.zzbkk);
    }

    @Override  // android.view.ViewGroup
    public void removeView(View view0) {
        if(this.zzbkk == view0) {
            return;
        }
        super.removeView(view0);
    }

    public void setAdChoicesView(AdChoicesView adChoicesView0) {
        this.zza("1098", adChoicesView0);
    }

    public void setNativeAd(NativeAd nativeAd0) {
        try {
            IObjectWrapper iObjectWrapper0 = (IObjectWrapper)nativeAd0.zzjo();
            this.zzbkl.zza(iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to call setNativeAd on delegate", remoteException0);
        }
    }

    protected final void zza(String s, View view0) {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(view0);
            this.zzbkl.zzb(s, iObjectWrapper0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to call setAssetView on delegate", remoteException0);
        }
    }

    protected final View zzbq(String s) {
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
        Preconditions.checkNotNull(this.zzbkk, "createDelegate must be called after mOverlayFrame has been created");
        return this.isInEditMode() ? null : zzvh.zzpa().zza(this.zzbkk.getContext(), this, this.zzbkk);
    }
}

