package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzq;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
public final class zzaid extends zzaim implements zzaii, zzair {
    private final zzbfz zzczt;
    private zzaiq zzczu;

    public zzaid(Context context0, zzazo zzazo0) throws zzbei {
        try {
            this.zzczt = new zzbfz(context0, new zzaij(this, null));
            this.zzczt.setWillNotDraw(true);
            zzaig zzaig0 = new zzaig(this, null);
            this.zzczt.addJavascriptInterface(zzaig0, "GoogleJsInterface");
            zzq.zzkv().zza(context0, zzazo0.zzbmj, this.zzczt.getSettings());
        }
        catch(Throwable throwable0) {
            throw new zzbei("Init failed.", throwable0);
        }
        super.zzg(this);
    }

    @Override  // com.google.android.gms.internal.ads.zzair
    public final void destroy() {
        this.zzczt.destroy();
    }

    @Override  // com.google.android.gms.internal.ads.zzair
    public final boolean isDestroyed() {
        return this.zzczt.isDestroyed();
    }

    static zzaiq zza(zzaid zzaid0) {
        return zzaid0.zzczu;
    }

    @Override  // com.google.android.gms.internal.ads.zzair
    public final void zza(zzaiq zzaiq0) {
        this.zzczu = zzaiq0;
    }

    @Override  // com.google.android.gms.internal.ads.zzaia
    public final void zza(String s, Map map0) {
        zzail.zza(this, s, map0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaii, com.google.android.gms.internal.ads.zzaia
    public final void zza(String s, JSONObject jSONObject0) {
        zzail.zzb(this, s, jSONObject0);
    }

    @Override  // com.google.android.gms.internal.ads.zzajb
    public final void zzb(String s, JSONObject jSONObject0) {
        zzail.zza(this, s, jSONObject0);
    }

    @Override  // com.google.android.gms.internal.ads.zzair
    public final void zzcw(String s) {
        this.zzcx(String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head></html>", s));
    }

    @Override  // com.google.android.gms.internal.ads.zzair
    public final void zzcx(String s) {
        zzaic zzaic0 = () -> this.zzczt.loadData(s, "text/html", "UTF-8");
        zzazq.zzdxo.execute(zzaic0);
    }

    @Override  // com.google.android.gms.internal.ads.zzair
    public final void zzcy(String s) {
        zzaif zzaif0 = () -> this.zzczt.loadUrl(s);
        zzazq.zzdxo.execute(zzaif0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaii, com.google.android.gms.internal.ads.zzajb
    public final void zzcz(String s) {
        zzaie zzaie0 = () -> this.zzczt.zzcz(s);
        zzazq.zzdxo.execute(zzaie0);
    }

    // 检测为 Lambda 实现
    final void zzda(String s) [...]

    // 检测为 Lambda 实现
    final void zzdb(String s) [...]

    // 检测为 Lambda 实现
    final void zzdc(String s) [...]

    @Override  // com.google.android.gms.internal.ads.zzaii
    public final void zzj(String s, String s1) {
        zzail.zza(this, s, s1);
    }

    @Override  // com.google.android.gms.internal.ads.zzair
    public final zzakb zzse() {
        return new zzaka(this);
    }
}

