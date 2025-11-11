package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Arrays;
import java.util.List;

public final class zzccp extends zzadm {
    private final zzbyz zzfne;
    private zzbyo zzfqg;
    private zzbzv zzftg;
    private final Context zzyz;

    public zzccp(Context context0, zzbyz zzbyz0, zzbzv zzbzv0, zzbyo zzbyo0) {
        this.zzyz = context0;
        this.zzfne = zzbyz0;
        this.zzftg = zzbzv0;
        this.zzfqg = zzbyo0;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final void destroy() {
        zzbyo zzbyo0 = this.zzfqg;
        if(zzbyo0 != null) {
            zzbyo0.destroy();
        }
        this.zzfqg = null;
        this.zzftg = null;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final List getAvailableAssetNames() {
        SimpleArrayMap simpleArrayMap0 = this.zzfne.zzakm();
        SimpleArrayMap simpleArrayMap1 = this.zzfne.zzako();
        String[] arr_s = new String[simpleArrayMap0.size() + simpleArrayMap1.size()];
        int v = 0;
        int v1 = 0;
        int v2;
        for(v2 = 0; v1 < simpleArrayMap0.size(); ++v2) {
            arr_s[v2] = (String)simpleArrayMap0.keyAt(v1);
            ++v1;
        }
        while(v < simpleArrayMap1.size()) {
            arr_s[v2] = (String)simpleArrayMap1.keyAt(v);
            ++v;
            ++v2;
        }
        return Arrays.asList(arr_s);
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final String getCustomTemplateId() {
        return this.zzfne.getCustomTemplateId();
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final zzxj getVideoController() {
        return this.zzfne.getVideoController();
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final void performClick(String s) {
        zzbyo zzbyo0 = this.zzfqg;
        if(zzbyo0 != null) {
            zzbyo0.zzfv(s);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final void recordImpression() {
        zzbyo zzbyo0 = this.zzfqg;
        if(zzbyo0 != null) {
            zzbyo0.zzajs();
        }
    }

    static zzbyo zza(zzccp zzccp0) {
        return zzccp0.zzfqg;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final String zzcu(String s) {
        return (String)this.zzfne.zzako().get(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final zzacr zzcv(String s) {
        return (zzacr)this.zzfne.zzakm().get(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final boolean zzp(IObjectWrapper iObjectWrapper0) {
        Object object0 = ObjectWrapper.unwrap(iObjectWrapper0);
        if(!(object0 instanceof ViewGroup)) {
            return false;
        }
        if(this.zzftg == null || !this.zzftg.zza(((ViewGroup)object0))) {
            return false;
        }
        this.zzfne.zzakj().zza(new zzcco(this));
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final void zzq(IObjectWrapper iObjectWrapper0) {
        Object object0 = ObjectWrapper.unwrap(iObjectWrapper0);
        if(!(object0 instanceof View)) {
            return;
        }
        if(this.zzfne.zzakl() == null) {
            return;
        }
        zzbyo zzbyo0 = this.zzfqg;
        if(zzbyo0 != null) {
            zzbyo0.zzz(((View)object0));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final IObjectWrapper zzrj() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final IObjectWrapper zzro() {
        return ObjectWrapper.wrap(this.zzyz);
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final boolean zzrp() {
        if(this.zzfqg != null && !this.zzfqg.zzaka()) {
            return false;
        }
        return this.zzfne.zzakk() == null ? false : this.zzfne.zzakj() == null;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final boolean zzrq() {
        IObjectWrapper iObjectWrapper0 = this.zzfne.zzakl();
        if(iObjectWrapper0 != null) {
            zzq.zzlk().zzab(iObjectWrapper0);
            return true;
        }
        zzawf.zzfa("Trying to start OMID session before creation.");
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzadn
    public final void zzrr() {
        String s = this.zzfne.zzakn();
        if("Google".equals(s)) {
            zzawf.zzfa("Illegal argument specified for omid partner name.");
            return;
        }
        zzbyo zzbyo0 = this.zzfqg;
        if(zzbyo0 != null) {
            zzbyo0.zzg(s, false);
        }
    }
}

