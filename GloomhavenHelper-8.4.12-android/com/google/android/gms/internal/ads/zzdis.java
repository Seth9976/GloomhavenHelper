package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public final class zzdis {
    private final Executor executor;
    private final String zzbmj;
    private final Clock zzbmz;
    private final String zzcei;
    private final String zzdjw;
    private final zzdq zzfds;
    private final zzdep zzfkb;
    private final zzcpc zzfli;
    private final zzazl zzgwg;
    private final Context zzur;

    public zzdis(Executor executor0, zzazl zzazl0, zzcpc zzcpc0, zzazo zzazo0, String s, String s1, Context context0, @Nullable zzdep zzdep0, Clock clock0, zzdq zzdq0) {
        this.executor = executor0;
        this.zzgwg = zzazl0;
        this.zzfli = zzcpc0;
        this.zzbmj = zzazo0.zzbmj;
        this.zzdjw = s;
        this.zzcei = s1;
        this.zzur = context0;
        this.zzfkb = zzdep0;
        this.zzbmz = clock0;
        this.zzfds = zzdq0;
    }

    public final void zza(zzdeq zzdeq0, zzdei zzdei0, List list0) {
        this.zza(zzdeq0, zzdei0, false, "", list0);
    }

    public final void zza(zzdeq zzdeq0, zzdei zzdei0, List list0, zzarr zzarr0) {
        String s1;
        String s;
        long v = this.zzbmz.currentTimeMillis();
        try {
            s = zzarr0.getType();
            s1 = Integer.toString(zzarr0.getAmount());
        }
        catch(RemoteException unused_ex) {
            return;
        }
        ArrayList arrayList0 = new ArrayList();
        String s2 = this.zzfkb == null ? "" : zzdis.zzgr(this.zzfkb.zzdpa);
        String s3 = this.zzfkb == null ? "" : zzdis.zzgr(this.zzfkb.zzdpb);
        for(Object object0: list0) {
            arrayList0.add(zzaux.zzb(zzdis.zzc(zzdis.zzc(zzdis.zzc(zzdis.zzc(zzdis.zzc(zzdis.zzc(((String)object0), "@gw_rwd_userid@", Uri.encode(s2)), "@gw_rwd_custom_data@", Uri.encode(s3)), "@gw_tmstmp@", Long.toString(v)), "@gw_rwd_itm@", Uri.encode(s)), "@gw_rwd_amt@", s1), "@gw_sdkver@", this.zzbmj), this.zzur, zzdei0.zzdmq));
        }
        this.zzh(arrayList0);
    }

    public final void zza(zzdeq zzdeq0, @Nullable zzdei zzdei0, boolean z, String s, List list0) {
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: list0) {
            String s1 = zzdis.zzc(zzdis.zzc(zzdis.zzc(((String)object0), "@gw_adlocid@", zzdeq0.zzgql.zzfir.zzgqr), "@gw_adnetrefresh@", (z ? "1" : "0")), "@gw_sdkver@", this.zzbmj);
            if(zzdei0 != null) {
                s1 = zzaux.zzb(zzdis.zzc(zzdis.zzc(zzdis.zzc(s1, "@gw_qdata@", zzdei0.zzddv), "@gw_adnetid@", zzdei0.zzafi), "@gw_allocid@", zzdei0.zzdcu), this.zzur, zzdei0.zzdmq);
            }
            String s2 = zzdis.zzc(zzdis.zzc(zzdis.zzc(s1, "@gw_adnetstatus@", this.zzfli.zzaoh()), "@gw_seqnum@", this.zzdjw), "@gw_sessid@", this.zzcei);
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcmo)).booleanValue() && !TextUtils.isEmpty(s)) {
                Uri uri0 = Uri.parse(s2);
                if(this.zzfds.zzb(uri0)) {
                    s2 = Uri.parse(s2).buildUpon().appendQueryParameter("ms", s).build().toString();
                }
            }
            arrayList0.add(s2);
        }
        this.zzh(arrayList0);
    }

    private static String zzc(String s, String s1, @Nullable String s2) {
        if(TextUtils.isEmpty(s2)) {
            s2 = "";
        }
        return s.replaceAll(s1, s2);
    }

    public final void zzeo(String s) {
        zzdir zzdir0 = () -> this.zzgwg.zzeo(s);
        this.executor.execute(zzdir0);
    }

    // 去混淆评级： 低(20)
    @Nullable
    private static String zzgr(@Nullable String s) {
        return TextUtils.isEmpty(s) || !zzazb.isEnabled() ? s : "fakeForAdDebugLog";
    }

    // 检测为 Lambda 实现
    final void zzgs(String s) [...]

    public final void zzh(List list0) {
        for(Object object0: list0) {
            this.zzeo(((String)object0));
        }
    }
}

