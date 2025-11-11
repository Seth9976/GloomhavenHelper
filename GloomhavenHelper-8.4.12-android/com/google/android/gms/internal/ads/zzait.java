package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zza;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
public final class zzait implements zzaii, zzair {
    private final zzbdv zzdae;
    private final Context zzur;

    public zzait(Context context0, zzazo zzazo0, @Nullable zzdq zzdq0, zza zza0) throws zzbei {
        this.zzur = context0;
        this.zzdae = zzbee.zza(context0, zzbfl.zzabv(), "", false, false, zzdq0, zzazo0, null, null, null, zzsn.zzmy(), null, false);
        this.zzdae.getView().setWillNotDraw(true);
    }

    @Override  // com.google.android.gms.internal.ads.zzair
    public final void destroy() {
        this.zzdae.destroy();
    }

    @Override  // com.google.android.gms.internal.ads.zzair
    public final boolean isDestroyed() {
        return this.zzdae.isDestroyed();
    }

    private static void runOnUiThread(Runnable runnable0) {
        if(zzayx.zzxj()) {
            runnable0.run();
            return;
        }
        zzawo.zzdtx.post(runnable0);
    }

    @Override  // com.google.android.gms.internal.ads.zzair
    public final void zza(zzaiq zzaiq0) {
        zzbfi zzbfi0 = this.zzdae.zzaaf();
        zzaiq0.getClass();
        zzbfi0.zza(zzaiu.zzb(zzaiq0));
    }

    @Override  // com.google.android.gms.internal.ads.zzajy
    public final void zza(String s, zzafz zzafz0) {
        zzaiy zzaiy0 = new zzaiy(this, zzafz0);
        this.zzdae.zza(s, zzaiy0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaia
    public final void zza(String s, Map map0) {
        zzail.zza(this, s, map0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaii, com.google.android.gms.internal.ads.zzaia
    public final void zza(String s, JSONObject jSONObject0) {
        zzail.zzb(this, s, jSONObject0);
    }

    @Override  // com.google.android.gms.internal.ads.zzajy
    public final void zzb(String s, zzafz zzafz0) {
        zzaiv zzaiv0 = new zzaiv(zzafz0);
        this.zzdae.zza(s, zzaiv0);
    }

    @Override  // com.google.android.gms.internal.ads.zzajb
    public final void zzb(String s, JSONObject jSONObject0) {
        zzail.zza(this, s, jSONObject0);
    }

    @Override  // com.google.android.gms.internal.ads.zzair
    public final void zzcw(String s) {
        zzait.runOnUiThread(new zzaix(this, String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", s)));
    }

    @Override  // com.google.android.gms.internal.ads.zzair
    public final void zzcx(String s) {
        zzait.runOnUiThread(new zzaiw(this, s));
    }

    @Override  // com.google.android.gms.internal.ads.zzair
    public final void zzcy(String s) {
        zzait.runOnUiThread(new zzaiz(this, s));
    }

    @Override  // com.google.android.gms.internal.ads.zzaii, com.google.android.gms.internal.ads.zzajb
    public final void zzcz(String s) {
        zzait.runOnUiThread(() -> this.zzdae.zzcz(s));
    }

    // 检测为 Lambda 实现
    final void zzde(String s) [...]

    @Override  // com.google.android.gms.internal.ads.zzaii
    public final void zzj(String s, String s1) {
        zzail.zza(this, s, s1);
    }

    @Override  // com.google.android.gms.internal.ads.zzair
    public final zzakb zzse() {
        return new zzaka(this);
    }
}

