package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzc;
import java.util.Map;
import java.util.concurrent.Executor;

public final class zzced {
    private final zzaub zzblc;
    private final zzdq zzehb;
    private final Executor zzfeo;
    private final zzbrc zzfil;
    private final zzbrv zzfmd;
    private final zzbqg zzfng;
    private final zzbkb zzfnh;
    private final zzbuu zzfnj;
    private final zzbrq zzfts;
    private final zzbsy zzftt;
    private final zzc zzfuj;
    private final zzbqw zzfuk;
    private final zzbsq zzful;

    public zzced(zzbqg zzbqg0, zzbrc zzbrc0, zzbrq zzbrq0, zzbrv zzbrv0, zzbsy zzbsy0, Executor executor0, zzbuu zzbuu0, zzbkb zzbkb0, zzc zzc0, zzbqw zzbqw0, @Nullable zzaub zzaub0, zzdq zzdq0, zzbsq zzbsq0) {
        this.zzfng = zzbqg0;
        this.zzfil = zzbrc0;
        this.zzfts = zzbrq0;
        this.zzfmd = zzbrv0;
        this.zzftt = zzbsy0;
        this.zzfeo = executor0;
        this.zzfnj = zzbuu0;
        this.zzfnh = zzbkb0;
        this.zzfuj = zzc0;
        this.zzfuk = zzbqw0;
        this.zzblc = zzaub0;
        this.zzehb = zzdq0;
        this.zzful = zzbsq0;
    }

    public static zzdof zza(zzbdv zzbdv0, String s, String s1) {
        zzdof zzdof0 = new zzazy();
        zzbdv0.zzaaf().zza(new zzcek(((zzazy)zzdof0)));
        zzbdv0.zzb(s, s1, null);
        return zzdof0;
    }

    // 检测为 Lambda 实现
    final void zza(zzbdv zzbdv0, zzbdv zzbdv1, Map map0) [...]

    // 检测为 Lambda 实现
    final boolean zza(View view0, MotionEvent motionEvent0) [...]

    // 检测为 Lambda 实现
    final void zzad(View view0) [...]

    // 检测为 Lambda 实现
    final void zzame() [...]

    // 检测为 Lambda 实现
    final void zzamf() [...]

    public final void zzb(zzbdv zzbdv0, boolean z) {
        zzbfi zzbfi0 = zzbdv0.zzaaf();
        zzcec zzcec0 = () -> this.zzfng.onAdClicked();
        zzcef zzcef0 = (String s, String s1) -> this.zzftt.onAppEvent(s, s1);
        zzcee zzcee0 = () -> this.zzfil.onAdLeftApplication();
        zzcen zzcen0 = new zzcen(this);
        zzbfi0.zza(zzcec0, this.zzfts, this.zzfmd, zzcef0, zzcee0, z, null, this.zzfuj, zzcen0, this.zzblc);
        zzbdv0.setOnTouchListener((View view0, MotionEvent motionEvent0) -> {
            this.zzfuj.recordClick();
            zzaub zzaub0 = this.zzblc;
            if(zzaub0 != null) {
                zzaub0.zzur();
            }
            return false;
        });
        zzbdv0.setOnClickListener((View view0) -> {
            this.zzfuj.recordClick();
            zzaub zzaub0 = this.zzblc;
            if(zzaub0 != null) {
                zzaub0.zzur();
            }
        });
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzclw)).booleanValue()) {
            zzdg zzdg0 = this.zzehb.zzcb();
            if(zzdg0 != null) {
                zzdg0.zzb(zzbdv0.getView());
            }
        }
        this.zzfnj.zza(zzbdv0, this.zzfeo);
        zzcej zzcej0 = new zzcej(zzbdv0);
        this.zzfnj.zza(zzcej0, this.zzfeo);
        View view0 = zzbdv0.getView();
        this.zzfnj.zzq(view0);
        zzbdv0.zza("/trackActiveViewUnit", (zzbdv zzbdv1, Map map0) -> this.zzfnh.zzf(zzbdv0));
        this.zzfnh.zzo(zzbdv0);
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcjo)).booleanValue()) {
            zzbdv0.getClass();
            zzbvl zzbvl0 = zzcel.zzn(zzbdv0);
            this.zzfuk.zza(zzbvl0, this.zzfeo);
        }
    }

    // 检测为 Lambda 实现
    final void zzp(String s, String s1) [...]
}

