package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class zzcsz extends zzave {
    private zzazo zzblr;
    private zzdq zzfds;
    private static final List zzghb;
    private static final List zzghc;
    private static final List zzghd;
    private static final List zzghe;
    private zzbgk zzghf;
    private Context zzur;

    static {
        zzcsz.zzghb = new ArrayList(Arrays.asList(new String[]{"/aclk", "/pcs/click"}));
        zzcsz.zzghc = new ArrayList(Arrays.asList(new String[]{".doubleclick.net", ".googleadservices.com"}));
        zzcsz.zzghd = new ArrayList(Arrays.asList(new String[]{"/pagead/adview", "/pcs/view", "/pagead/conversion"}));
        zzcsz.zzghe = new ArrayList(Arrays.asList(new String[]{".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"}));
    }

    public zzcsz(zzbgk zzbgk0, Context context0, zzdq zzdq0, zzazo zzazo0) {
        this.zzghf = zzbgk0;
        this.zzur = context0;
        this.zzfds = zzdq0;
        this.zzblr = zzazo0;
    }

    @Override  // com.google.android.gms.internal.ads.zzavb
    public final void zza(IObjectWrapper iObjectWrapper0, zzavh zzavh0, zzava zzava0) {
        this.zzur = (Context)ObjectWrapper.unwrap(iObjectWrapper0);
        Context context0 = this.zzur;
        String s = zzavh0.zzbri;
        String s1 = zzavh0.zzbmg;
        zzuk zzuk0 = zzavh0.zzdqz;
        zzuh zzuh0 = zzavh0.zzdra;
        zzcsw zzcsw0 = this.zzghf.zzact();
        zza zzbpt$zza0 = new zza().zzcc(context0);
        zzdew zzdew0 = new zzdew();
        if(s == null) {
            s = "adUnitId";
        }
        zzdew zzdew1 = zzdew0.zzgn(s);
        if(zzuh0 == null) {
            zzuh0 = new zzug().zzop();
        }
        zzdew zzdew2 = zzdew1.zzg(zzuh0);
        if(zzuk0 == null) {
            zzuk0 = new zzuk();
        }
        zzdnt.zza(zzcsw0.zzf(zzbpt$zza0.zza(zzdew2.zzd(zzuk0).zzarb()).zzahz()).zza(new zzcta(new com.google.android.gms.internal.ads.zzcta.zza().zzgm(s1), null)).zzf(new com.google.android.gms.internal.ads.zzbtl.zza().zzais()).zzafi().zzafh(), new zzcsy(this, zzava0), this.zzghf.zzacf());
    }

    @Override  // com.google.android.gms.internal.ads.zzavb
    public final void zzan(IObjectWrapper iObjectWrapper0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzavb
    public final IObjectWrapper zzao(IObjectWrapper iObjectWrapper0) {
        return iObjectWrapper0;
    }

    @Override  // com.google.android.gms.internal.ads.zzavb
    public final IObjectWrapper zzb(IObjectWrapper iObjectWrapper0, IObjectWrapper iObjectWrapper1) {
        return iObjectWrapper0;
    }
}

