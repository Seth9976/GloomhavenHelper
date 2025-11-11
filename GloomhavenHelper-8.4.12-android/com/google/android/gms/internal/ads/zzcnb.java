package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.browser.customtabs.CustomTabsIntent.Builder;
import androidx.browser.customtabs.CustomTabsIntent;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzb;
import java.util.concurrent.Executor;

public final class zzcnb implements zzcly {
    private final zzdeg zzfdn;
    private final Executor zzfeo;
    private final zzbwt zzgbv;
    private final Context zzur;

    public zzcnb(Context context0, Executor executor0, zzbwt zzbwt0, zzdeg zzdeg0) {
        this.zzur = context0;
        this.zzgbv = zzbwt0;
        this.zzfeo = executor0;
        this.zzfdn = zzdeg0;
    }

    // 检测为 Lambda 实现
    final zzdof zza(Uri uri0, zzdeq zzdeq0, zzdei zzdei0, Object object0) throws Exception [...]

    // 去混淆评级： 低(30)
    @Override  // com.google.android.gms.internal.ads.zzcly
    public final boolean zza(zzdeq zzdeq0, zzdei zzdei0) {
        return this.zzur instanceof Activity && zzaau.zzl(this.zzur) && !TextUtils.isEmpty(zzcnb.zzc(zzdei0));
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final zzdof zzb(zzdeq zzdeq0, zzdei zzdei0) {
        String s = zzcnb.zzc(zzdei0);
        if(s != null) {
            Uri uri0 = Uri.parse(s);
            return zzdnt.zzb(zzdnt.zzaj(null), (Object object0) -> try {
                CustomTabsIntent customTabsIntent0 = new Builder().build();
                customTabsIntent0.intent.setData(uri0);
                zzb zzb0 = new zzb(customTabsIntent0.intent);
                zzazy zzazy0 = new zzazy();
                zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, null);
                zzbvz zzbvz0 = new zzbvz(new zzcnd(zzazy0));
                zzbvw zzbvw0 = this.zzgbv.zza(zzbnv0, zzbvz0);
                zzazy0.set(new AdOverlayInfoParcel(zzb0, null, zzbvw0.zzaey(), null, new zzazo(0, 0, false)));
                this.zzfdn.zzvg();
                return zzdnt.zzaj(zzbvw0.zzaex());
            }
            catch(Throwable throwable0) {
                zzawf.zzc("Error in CustomTabsAdRenderer", throwable0);
                throw throwable0;
            }, this.zzfeo);
        }
        return zzdnt.zzb(zzdnt.zzaj(null), (Object object0) -> try {
            CustomTabsIntent customTabsIntent0 = new Builder().build();
            customTabsIntent0.intent.setData(null);
            zzb zzb0 = new zzb(customTabsIntent0.intent);
            zzazy zzazy0 = new zzazy();
            zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, null);
            zzbvz zzbvz0 = new zzbvz(new zzcnd(zzazy0));
            zzbvw zzbvw0 = this.zzgbv.zza(zzbnv0, zzbvz0);
            zzazy0.set(new AdOverlayInfoParcel(zzb0, null, zzbvw0.zzaey(), null, new zzazo(0, 0, false)));
            this.zzfdn.zzvg();
            return zzdnt.zzaj(zzbvw0.zzaex());
        }
        catch(Throwable throwable0) {
            zzawf.zzc("Error in CustomTabsAdRenderer", throwable0);
            throw throwable0;
        }, this.zzfeo);
    }

    private static String zzc(zzdei zzdei0) {
        try {
            return zzdei0.zzgpt.getString("tab_url");
        }
        catch(Exception unused_ex) {
            return null;
        }
    }
}

