package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import java.util.HashMap;

public class zzus {
    private final zzuf zzcdl;
    private final zzuc zzcdm;
    private final zzyh zzcdn;
    private final zzaev zzcdo;
    private final zzasf zzcdp;
    private final zzatj zzcdq;
    private final zzapc zzcdr;
    private final zzaeu zzcds;

    public zzus(zzuf zzuf0, zzuc zzuc0, zzyh zzyh0, zzaev zzaev0, zzasf zzasf0, zzatj zzatj0, zzapc zzapc0, zzaeu zzaeu0) {
        this.zzcdl = zzuf0;
        this.zzcdm = zzuc0;
        this.zzcdn = zzyh0;
        this.zzcdo = zzaev0;
        this.zzcdp = zzasf0;
        this.zzcdq = zzatj0;
        this.zzcdr = zzapc0;
        this.zzcds = zzaeu0;
    }

    private static void zza(Context context0, String s) {
        Bundle bundle0 = new Bundle();
        bundle0.putString("action", "no_ads_fallback");
        bundle0.putString("flow", s);
        zzvh.zzoz().zza(context0, zzvh.zzpf().zzbmj, "gmob-apps", bundle0, true);
    }

    public final zzacv zza(Context context0, FrameLayout frameLayout0, FrameLayout frameLayout1) {
        return (zzacv)new zzvd(this, frameLayout0, frameLayout1, context0).zzd(context0, false);
    }

    public final zzacy zza(View view0, HashMap hashMap0, HashMap hashMap1) {
        return (zzacy)new zzvc(this, view0, hashMap0, hashMap1).zzd(view0.getContext(), false);
    }

    public final zzvx zza(Context context0, zzuk zzuk0, String s, zzalk zzalk0) {
        return (zzvx)new zzuw(this, context0, zzuk0, s, zzalk0).zzd(context0, false);
    }

    @Nullable
    public final zzapb zzb(Activity activity0) {
        zzux zzux0 = new zzux(this, activity0);
        Intent intent0 = activity0.getIntent();
        if(!intent0.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
            zzazh.zzey("useClientJar flag not found in activity intent extras.");
            return (zzapb)zzux0.zzd(activity0, false);
        }
        return (zzapb)zzux0.zzd(activity0, intent0.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false));
    }

    public final zzvq zzb(Context context0, String s, zzalk zzalk0) {
        return (zzvq)new zzvb(this, context0, s, zzalk0).zzd(context0, false);
    }

    public final zzast zzc(Context context0, String s, zzalk zzalk0) {
        return (zzast)new zzuu(this, context0, s, zzalk0).zzd(context0, false);
    }
}

