package com.google.android.gms.ads.formats;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzacy;
import com.google.android.gms.internal.ads.zzazh;
import com.google.android.gms.internal.ads.zzvh;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public final class NativeAdViewHolder {
    private zzacy zzbkm;
    public static WeakHashMap zzbkn;
    private WeakReference zzbko;

    static {
        NativeAdViewHolder.zzbkn = new WeakHashMap();
    }

    public NativeAdViewHolder(View view0, Map map0, Map map1) {
        Preconditions.checkNotNull(view0, "ContainerView must not be null");
        if(!(view0 instanceof NativeAdView) && !(view0 instanceof UnifiedNativeAdView)) {
            if(NativeAdViewHolder.zzbkn.get(view0) != null) {
                zzazh.zzey("The provided containerView is already in use with another NativeAdViewHolder.");
                return;
            }
            NativeAdViewHolder.zzbkn.put(view0, this);
            this.zzbko = new WeakReference(view0);
            this.zzbkm = zzvh.zzpa().zza(view0, NativeAdViewHolder.zzb(map0), NativeAdViewHolder.zzb(map1));
            return;
        }
        zzazh.zzey("The provided containerView is of type of NativeAdView, which cannot be usedwith NativeAdViewHolder.");
    }

    public final void setClickConfirmingView(View view0) {
        try {
            this.zzbkm.zze(ObjectWrapper.wrap(view0));
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("Unable to call setClickConfirmingView on delegate", remoteException0);
        }
    }

    public final void setNativeAd(NativeAd nativeAd0) {
        this.zza(((IObjectWrapper)nativeAd0.zzjo()));
    }

    public final void setNativeAd(UnifiedNativeAd unifiedNativeAd0) {
        this.zza(((IObjectWrapper)unifiedNativeAd0.zzjo()));
    }

    public final void unregisterNativeAd() {
        zzacy zzacy0 = this.zzbkm;
        if(zzacy0 != null) {
            try {
                zzacy0.unregisterNativeAd();
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("Unable to call unregisterNativeAd on delegate", remoteException0);
            }
        }
        View view0 = this.zzbko == null ? null : ((View)this.zzbko.get());
        if(view0 != null) {
            NativeAdViewHolder.zzbkn.remove(view0);
        }
    }

    private final void zza(IObjectWrapper iObjectWrapper0) {
        View view0 = this.zzbko == null ? null : ((View)this.zzbko.get());
        if(view0 == null) {
            zzazh.zzfa("NativeAdViewHolder.setNativeAd containerView doesn\'t exist, returning");
            return;
        }
        if(!NativeAdViewHolder.zzbkn.containsKey(view0)) {
            NativeAdViewHolder.zzbkn.put(view0, this);
        }
        zzacy zzacy0 = this.zzbkm;
        if(zzacy0 != null) {
            try {
                zzacy0.zza(iObjectWrapper0);
            }
            catch(RemoteException remoteException0) {
                zzazh.zzc("Unable to call setNativeAd on delegate", remoteException0);
            }
        }
    }

    private static HashMap zzb(Map map0) {
        return map0 == null ? new HashMap() : new HashMap(map0);
    }
}

