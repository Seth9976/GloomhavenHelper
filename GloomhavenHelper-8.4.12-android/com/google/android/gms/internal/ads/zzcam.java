package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.View;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzcam implements zzbzh {
    private final zzazo zzblu;
    private final zzdei zzfhg;
    private final zzdeu zzfir;
    private final zzbqp zzfnf;
    private final zzbqg zzfng;
    private boolean zzfnk;
    private boolean zzfnn;
    @Nullable
    private final zzalx zzfrl;
    @Nullable
    private final zzaly zzfrm;
    @Nullable
    private final zzamd zzfrn;
    private final Context zzur;

    public zzcam(@Nullable zzalx zzalx0, @Nullable zzaly zzaly0, @Nullable zzamd zzamd0, zzbqp zzbqp0, zzbqg zzbqg0, Context context0, zzdei zzdei0, zzazo zzazo0, zzdeu zzdeu0) {
        this.zzfnk = false;
        this.zzfnn = false;
        this.zzfrl = zzalx0;
        this.zzfrm = zzaly0;
        this.zzfrn = zzamd0;
        this.zzfnf = zzbqp0;
        this.zzfng = zzbqg0;
        this.zzur = context0;
        this.zzfhg = zzdei0;
        this.zzblu = zzazo0;
        this.zzfir = zzdeu0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void cancelUnconfirmedClick() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void destroy() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final boolean isCustomClickGestureEnabled() {
        return this.zzfhg.zzdee;
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void setClickConfirmingView(View view0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(View view0, MotionEvent motionEvent0, @Nullable View view1) {
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(View view0, @Nullable View view1, @Nullable Map map0, @Nullable Map map1, boolean z) {
        if(this.zzfnn && this.zzfhg.zzdee) {
            return;
        }
        this.zzac(view0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(View view0, @Nullable Map map0) {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(view0);
            if(this.zzfrn != null) {
                this.zzfrn.zzw(iObjectWrapper0);
                return;
            }
            if(this.zzfrl != null) {
                this.zzfrl.zzw(iObjectWrapper0);
                return;
            }
            if(this.zzfrm != null) {
                this.zzfrm.zzw(iObjectWrapper0);
            }
        }
        catch(RemoteException remoteException0) {
            zzawf.zzd("Failed to call untrackView", remoteException0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(@Nullable View view0, @Nullable Map map0, @Nullable Map map1) {
        try {
            if(!this.zzfnk && this.zzfhg.zzgpv != null) {
                this.zzfnk |= zzq.zzlf().zzb(this.zzur, this.zzblu.zzbmj, this.zzfhg.zzgpv.toString(), this.zzfir.zzgqr);
            }
            if(this.zzfrn != null && !this.zzfrn.getOverrideImpressionRecording()) {
                this.zzfrn.recordImpression();
                this.zzfnf.onAdImpression();
                return;
            }
            if(this.zzfrl != null && !this.zzfrl.getOverrideImpressionRecording()) {
                this.zzfrl.recordImpression();
                this.zzfnf.onAdImpression();
                return;
            }
            if(this.zzfrm != null && !this.zzfrm.getOverrideImpressionRecording()) {
                this.zzfrm.recordImpression();
                this.zzfnf.onAdImpression();
            }
        }
        catch(RemoteException remoteException0) {
            zzawf.zzd("Failed to call recordImpression", remoteException0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(View view0, @Nullable Map map0, @Nullable Map map1, View.OnTouchListener view$OnTouchListener0, View.OnClickListener view$OnClickListener0) {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(view0);
            HashMap hashMap0 = zzcam.zzb(map0);
            HashMap hashMap1 = zzcam.zzb(map1);
            if(this.zzfrn != null) {
                IObjectWrapper iObjectWrapper1 = ObjectWrapper.wrap(hashMap0);
                IObjectWrapper iObjectWrapper2 = ObjectWrapper.wrap(hashMap1);
                this.zzfrn.zzc(iObjectWrapper0, iObjectWrapper1, iObjectWrapper2);
                return;
            }
            if(this.zzfrl != null) {
                IObjectWrapper iObjectWrapper3 = ObjectWrapper.wrap(hashMap0);
                IObjectWrapper iObjectWrapper4 = ObjectWrapper.wrap(hashMap1);
                this.zzfrl.zzc(iObjectWrapper0, iObjectWrapper3, iObjectWrapper4);
                this.zzfrl.zzv(iObjectWrapper0);
                return;
            }
            if(this.zzfrm != null) {
                IObjectWrapper iObjectWrapper5 = ObjectWrapper.wrap(hashMap0);
                IObjectWrapper iObjectWrapper6 = ObjectWrapper.wrap(hashMap1);
                this.zzfrm.zzc(iObjectWrapper0, iObjectWrapper5, iObjectWrapper6);
                this.zzfrm.zzv(iObjectWrapper0);
            }
        }
        catch(RemoteException remoteException0) {
            zzawf.zzd("Failed to call trackView", remoteException0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(View view0, Map map0, Map map1, boolean z) {
        if(!this.zzfnn) {
            zzawf.zzfa("Custom click reporting for 3p ads failed. enableCustomClickGesture is not set.");
            return;
        }
        if(!this.zzfhg.zzdee) {
            zzawf.zzfa("Custom click reporting for 3p ads failed. Ad unit id not whitelisted.");
            return;
        }
        this.zzac(view0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(zzaem zzaem0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(zzwq zzwq0) {
        zzawf.zzfa("Mute This Ad is not supported for 3rd party ads");
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zza(@Nullable zzwu zzwu0) {
        zzawf.zzfa("Mute This Ad is not supported for 3rd party ads");
    }

    private final void zzac(View view0) {
        try {
            if(this.zzfrn != null && !this.zzfrn.getOverrideClickHandling()) {
                IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(view0);
                this.zzfrn.zzu(iObjectWrapper0);
                this.zzfng.onAdClicked();
                return;
            }
            if(this.zzfrl != null && !this.zzfrl.getOverrideClickHandling()) {
                IObjectWrapper iObjectWrapper1 = ObjectWrapper.wrap(view0);
                this.zzfrl.zzu(iObjectWrapper1);
                this.zzfng.onAdClicked();
                return;
            }
            if(this.zzfrm != null && !this.zzfrm.getOverrideClickHandling()) {
                IObjectWrapper iObjectWrapper2 = ObjectWrapper.wrap(view0);
                this.zzfrm.zzu(iObjectWrapper2);
                this.zzfng.onAdClicked();
            }
        }
        catch(RemoteException remoteException0) {
            zzawf.zzd("Failed to call handleClick", remoteException0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zzajs() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zzajt() {
        zzawf.zzfa("Mute This Ad is not supported for 3rd party ads");
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zzaju() {
    }

    private static HashMap zzb(Map map0) {
        HashMap hashMap0 = new HashMap();
        if(map0 == null) {
            return hashMap0;
        }
        synchronized(map0) {
            for(Object object0: map0.entrySet()) {
                Map.Entry map$Entry0 = (Map.Entry)object0;
                View view0 = (View)((WeakReference)map$Entry0.getValue()).get();
                if(view0 != null) {
                    hashMap0.put(((String)map$Entry0.getKey()), view0);
                }
            }
            return hashMap0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zzf(Bundle bundle0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zzfv(String s) {
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zzg(Bundle bundle0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final boolean zzh(Bundle bundle0) {
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzbzh
    public final void zzru() {
        this.zzfnn = true;
    }
}

