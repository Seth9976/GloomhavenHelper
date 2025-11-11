package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public final class zzcad {
    private final zzbzh zzfod;
    private final zzccv zzfqy;
    private final zzceb zzfrh;
    private final zzbkk zzfri;
    private final Context zzur;

    public zzcad(Context context0, zzceb zzceb0, zzccv zzccv0, zzbkk zzbkk0, zzbzh zzbzh0) {
        this.zzur = context0;
        this.zzfrh = zzceb0;
        this.zzfqy = zzccv0;
        this.zzfri = zzbkk0;
        this.zzfod = zzbzh0;
    }

    // 检测为 Lambda 实现
    final void zza(zzbdv zzbdv0, Map map0) [...]

    final void zza(Map map0, boolean z) {
        HashMap hashMap0 = new HashMap();
        hashMap0.put("messageType", "htmlLoaded");
        hashMap0.put("id", ((String)map0.get("id")));
        this.zzfqy.zza("sendMessageToNativeJs", hashMap0);
    }

    public final View zzalp() throws zzbei {
        zzuk zzuk0 = zzuk.zzh(this.zzur);
        zzbdv zzbdv0 = this.zzfrh.zza(zzuk0, false);
        zzbdv0.getView().setVisibility(8);
        zzbdv0.zza("/sendMessageToSdk", (zzbdv zzbdv0, Map map0) -> this.zzfqy.zza("sendMessageToNativeJs", map0));
        zzbdv0.zza("/adMuted", (zzbdv zzbdv0, Map map0) -> this.zzfod.zzajt());
        WeakReference weakReference0 = new WeakReference(zzbdv0);
        zzcae zzcae0 = new zzcae(this);
        this.zzfqy.zza(weakReference0, "/loadHtml", zzcae0);
        WeakReference weakReference1 = new WeakReference(zzbdv0);
        zzcah zzcah0 = (zzbdv zzbdv0, Map map0) -> {
            zzawf.zzez("Showing native ads overlay.");
            zzbdv0.getView().setVisibility(0);
            this.zzfri.zzbf(true);
        };
        this.zzfqy.zza(weakReference1, "/showOverlay", zzcah0);
        WeakReference weakReference2 = new WeakReference(zzbdv0);
        zzcag zzcag0 = (zzbdv zzbdv0, Map map0) -> {
            zzawf.zzez("Hiding native ads overlay.");
            zzbdv0.getView().setVisibility(8);
            this.zzfri.zzbf(false);
        };
        this.zzfqy.zza(weakReference2, "/hideOverlay", zzcag0);
        return zzbdv0.getView();
    }

    // 检测为 Lambda 实现
    final void zzb(zzbdv zzbdv0, Map map0) [...]

    // 检测为 Lambda 实现
    final void zzc(zzbdv zzbdv0, Map map0) [...]

    // 检测为 Lambda 实现
    final void zzd(zzbdv zzbdv0, Map map0) [...]
}

