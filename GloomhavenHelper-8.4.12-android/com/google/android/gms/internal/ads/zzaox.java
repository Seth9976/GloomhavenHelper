package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzq;
import java.util.Map;
import org.json.JSONObject;

public final class zzaox extends zzaow implements zzafz {
    private float density;
    private int maxHeight;
    private int maxWidth;
    private int rotation;
    private final WindowManager zzbnu;
    private final zzbdv zzdae;
    private final zzze zzdhi;
    private int zzdhj;
    private int zzdhk;
    private int zzdhl;
    private int zzdhm;
    private final Context zzur;
    private DisplayMetrics zzwi;

    public zzaox(zzbdv zzbdv0, Context context0, zzze zzze0) {
        super(zzbdv0);
        this.zzdhj = -1;
        this.zzdhk = -1;
        this.maxWidth = -1;
        this.maxHeight = -1;
        this.zzdhl = -1;
        this.zzdhm = -1;
        this.zzdae = zzbdv0;
        this.zzur = context0;
        this.zzdhi = zzze0;
        this.zzbnu = (WindowManager)context0.getSystemService("window");
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        zzbdv zzbdv0 = (zzbdv)object0;
        this.zzwi = new DisplayMetrics();
        Display display0 = this.zzbnu.getDefaultDisplay();
        display0.getMetrics(this.zzwi);
        this.density = this.zzwi.density;
        this.rotation = display0.getRotation();
        this.zzdhj = zzayx.zzb(this.zzwi, this.zzwi.widthPixels);
        this.zzdhk = zzayx.zzb(this.zzwi, this.zzwi.heightPixels);
        Activity activity0 = this.zzdae.zzys();
        if(activity0 == null || activity0.getWindow() == null) {
            this.maxWidth = this.zzdhj;
            this.maxHeight = this.zzdhk;
        }
        else {
            int[] arr_v = zzawo.zzd(activity0);
            this.maxWidth = zzayx.zzb(this.zzwi, arr_v[0]);
            this.maxHeight = zzayx.zzb(this.zzwi, arr_v[1]);
        }
        if(this.zzdae.zzaad().zzaby()) {
            this.zzdhl = this.zzdhj;
            this.zzdhm = this.zzdhk;
        }
        else {
            this.zzdae.measure(0, 0);
        }
        this.zza(this.zzdhj, this.zzdhk, this.maxWidth, this.maxHeight, this.density, this.rotation);
        JSONObject jSONObject0 = new zzaos(new zzaou().zzae(this.zzdhi.zzqe()).zzad(this.zzdhi.zzqf()).zzaf(this.zzdhi.zzqh()).zzag(this.zzdhi.zzqg()).zzah(true), null).zztm();
        this.zzdae.zza("onDeviceFeaturesReceived", jSONObject0);
        int[] arr_v1 = new int[2];
        this.zzdae.getLocationOnScreen(arr_v1);
        this.zzj(zzvh.zzoz().zzc(this.zzur, arr_v1[0]), zzvh.zzoz().zzc(this.zzur, arr_v1[1]));
        if(zzawf.isLoggable(2)) {
            zzawf.zzez("Dispatching Ready Event.");
        }
        this.zzdu(this.zzdae.zzyw().zzbmj);
    }

    public final void zzj(int v, int v1) {
        int v2 = this.zzur instanceof Activity ? zzq.zzkv().zzf(((Activity)this.zzur))[0] : 0;
        if(this.zzdae.zzaad() == null || !this.zzdae.zzaad().zzaby()) {
            int v3 = this.zzdae.getWidth();
            int v4 = this.zzdae.getHeight();
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcij)).booleanValue()) {
                if(v3 == 0 && this.zzdae.zzaad() != null) {
                    v3 = this.zzdae.zzaad().widthPixels;
                }
                if(v4 == 0 && this.zzdae.zzaad() != null) {
                    v4 = this.zzdae.zzaad().heightPixels;
                }
            }
            this.zzdhl = zzvh.zzoz().zzc(this.zzur, v3);
            this.zzdhm = zzvh.zzoz().zzc(this.zzur, v4);
        }
        this.zzb(v, v1 - v2, this.zzdhl, this.zzdhm);
        this.zzdae.zzaaf().zzi(v, v1);
    }
}

