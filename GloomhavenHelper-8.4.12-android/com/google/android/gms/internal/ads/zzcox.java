package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class zzcox implements zzcly {
    protected abstract zzdof zza(zzdeu arg1, Bundle arg2);

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final boolean zza(zzdeq zzdeq0, zzdei zzdei0) {
        return !TextUtils.isEmpty(zzdei0.zzgpt.optString("pubid", ""));
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final zzdof zzb(zzdeq zzdeq0, zzdei zzdei0) {
        String s = zzdei0.zzgpt.optString("pubid", "");
        zzdeu zzdeu0 = zzdeq0.zzgql.zzfir;
        zzdew zzdew0 = new zzdew().zzg(zzdeu0.zzgqq).zzd(zzdeu0.zzblv).zzc(zzdeu0.zzgqo).zzgn(zzdeu0.zzgqr).zzc(zzdeu0.zzgqp).zzb(zzdeu0.zzgqs).zzc(zzdeu0.zzgqt).zzb(zzdeu0.zzdff).zzb(zzdeu0.zzgqu).zzb(zzdeu0.zzgqv).zzgn(s);
        Bundle bundle0 = zzcox.zzm(zzdeu0.zzgqq.zzcct);
        Bundle bundle1 = zzcox.zzm(bundle0.getBundle("com.google.ads.mediation.admob.AdMobAdapter"));
        bundle1.putInt("gw", 1);
        String s1 = zzdei0.zzgpt.optString("mad_hac", null);
        if(s1 != null) {
            bundle1.putString("mad_hac", s1);
        }
        String s2 = zzdei0.zzgpt.optString("adJson", null);
        if(s2 != null) {
            bundle1.putString("_ad", s2);
        }
        bundle1.putBoolean("_noRefresh", true);
        Iterator iterator0 = zzdei0.zzgpw.keys();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            String s3 = (String)object0;
            String s4 = zzdei0.zzgpw.optString(s3, null);
            if(s3 != null) {
                bundle1.putString(s3, s4);
            }
        }
        bundle0.putBundle("com.google.ads.mediation.admob.AdMobAdapter", bundle1);
        zzdeu zzdeu1 = zzdew0.zzg(new zzuh(zzdeu0.zzgqq.versionCode, zzdeu0.zzgqq.zzccm, bundle1, zzdeu0.zzgqq.zzccn, zzdeu0.zzgqq.zzcco, zzdeu0.zzgqq.zzccp, zzdeu0.zzgqq.zzabv, zzdeu0.zzgqq.zzbkp, zzdeu0.zzgqq.zzccq, zzdeu0.zzgqq.zzccr, zzdeu0.zzgqq.zzmk, zzdeu0.zzgqq.zzccs, bundle0, zzdeu0.zzgqq.zzccu, zzdeu0.zzgqq.zzccv, zzdeu0.zzgqq.zzccw, zzdeu0.zzgqq.zzccx, zzdeu0.zzgqq.zzccy, zzdeu0.zzgqq.zzcda, zzdeu0.zzgqq.zzabw, zzdeu0.zzgqq.zzabx, zzdeu0.zzgqq.zzccz)).zzarb();
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = new Bundle();
        bundle3.putStringArrayList("nofill_urls", new ArrayList(zzdeq0.zzgqm.zzgqj.zzdds));
        bundle3.putInt("refresh_interval", zzdeq0.zzgqm.zzgqj.zzgqf);
        bundle3.putString("gws_query_id", zzdeq0.zzgqm.zzgqj.zzcac);
        bundle2.putBundle("parent_common_config", bundle3);
        Bundle bundle4 = new Bundle();
        bundle4.putString("initial_ad_unit_id", zzdeq0.zzgql.zzfir.zzgqr);
        bundle4.putString("allocation_id", zzdei0.zzdcu);
        bundle4.putStringArrayList("click_urls", new ArrayList(zzdei0.zzddp));
        bundle4.putStringArrayList("imp_urls", new ArrayList(zzdei0.zzddq));
        bundle4.putStringArrayList("manual_tracking_urls", new ArrayList(zzdei0.zzdlr));
        bundle4.putStringArrayList("fill_urls", new ArrayList(zzdei0.zzgpo));
        bundle4.putStringArrayList("video_start_urls", new ArrayList(zzdei0.zzdme));
        bundle4.putStringArrayList("video_reward_urls", new ArrayList(zzdei0.zzdmf));
        bundle4.putStringArrayList("video_complete_urls", new ArrayList(zzdei0.zzgpn));
        bundle4.putString("transaction_id", zzdei0.zzddf);
        bundle4.putString("valid_from_timestamp", zzdei0.zzddg);
        bundle4.putBoolean("is_closable_area_disabled", zzdei0.zzblo);
        if(zzdei0.zzdmd != null) {
            Bundle bundle5 = new Bundle();
            bundle5.putInt("rb_amount", zzdei0.zzdmd.zzdot);
            bundle5.putString("rb_type", zzdei0.zzdmd.type);
            bundle4.putParcelableArray("rewards", new Bundle[]{bundle5});
        }
        bundle2.putBundle("parent_ad_config", bundle4);
        return this.zza(zzdeu1, bundle2);
    }

    private static Bundle zzm(Bundle bundle0) {
        return bundle0 == null ? new Bundle() : new Bundle(bundle0);
    }
}

