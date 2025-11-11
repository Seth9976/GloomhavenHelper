package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzbav extends FrameLayout implements zzbau {
    private final zzbbm zzdza;
    private final FrameLayout zzdzb;
    private final zzaak zzdzc;
    private final zzbbo zzdzd;
    private final long zzdze;
    @Nullable
    private zzbat zzdzf;
    private boolean zzdzg;
    private boolean zzdzh;
    private boolean zzdzi;
    private boolean zzdzj;
    private long zzdzk;
    private long zzdzl;
    private String zzdzm;
    private String[] zzdzn;
    private Bitmap zzdzo;
    private ImageView zzdzp;
    private boolean zzdzq;

    public zzbav(Context context0, zzbbm zzbbm0, int v, boolean z, zzaak zzaak0, zzbbj zzbbj0) {
        super(context0);
        this.zzdza = zzbbm0;
        this.zzdzc = zzaak0;
        this.zzdzb = new FrameLayout(context0);
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = new FrameLayout.LayoutParams(-1, -1);
        this.addView(this.zzdzb, frameLayout$LayoutParams0);
        Preconditions.checkNotNull(zzbbm0.zzyt());
        this.zzdzf = zzbbm0.zzyt().zzbkx.zza(context0, zzbbm0, v, z, zzaak0, zzbbj0);
        zzbat zzbat0 = this.zzdzf;
        if(zzbat0 != null) {
            FrameLayout.LayoutParams frameLayout$LayoutParams1 = new FrameLayout.LayoutParams(-1, -1, 17);
            this.zzdzb.addView(zzbat0, frameLayout$LayoutParams1);
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzchv)).booleanValue()) {
                this.zzyf();
            }
        }
        this.zzdzp = new ImageView(context0);
        this.zzdze = (long)(((Long)zzvh.zzpd().zzd(zzzx.zzchz)));
        this.zzdzj = ((Boolean)zzvh.zzpd().zzd(zzzx.zzchx)).booleanValue();
        zzaak zzaak1 = this.zzdzc;
        if(zzaak1 != null) {
            zzaak1.zzh("spinner_used", (this.zzdzj ? "1" : "0"));
        }
        this.zzdzd = new zzbbo(this);
        zzbat zzbat1 = this.zzdzf;
        if(zzbat1 != null) {
            zzbat1.zza(this);
        }
        if(this.zzdzf == null) {
            this.zzm("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }

    public final void destroy() {
        this.zzdzd.pause();
        zzbat zzbat0 = this.zzdzf;
        if(zzbat0 != null) {
            zzbat0.stop();
        }
        this.zzyi();
    }

    @Override
    public final void finalize() throws Throwable {
        try {
            this.zzdzd.pause();
            if(this.zzdzf != null) {
                zzbat zzbat0 = this.zzdzf;
                zzbat0.getClass();
                Runnable runnable0 = zzbay.zza(zzbat0);
                zzazq.zzdxo.execute(runnable0);
            }
        }
        finally {
            super.finalize();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbau
    public final void onPaused() {
        this.zzd("pause", new String[0]);
        this.zzyi();
        this.zzdzg = false;
    }

    @Override  // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if(z) {
            this.zzdzd.resume();
        }
        else {
            this.zzdzd.pause();
            this.zzdzl = this.zzdzk;
        }
        zzbax zzbax0 = () -> this.zzd("windowFocusChanged", new String[]{"hasWindowFocus", String.valueOf(z)});
        zzawo.zzdtx.post(zzbax0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbau, android.view.View
    public final void onWindowVisibilityChanged(int v) {
        boolean z;
        super.onWindowVisibilityChanged(v);
        if(v == 0) {
            this.zzdzd.resume();
            z = true;
        }
        else {
            this.zzdzd.pause();
            this.zzdzl = this.zzdzk;
            z = false;
        }
        zzbbc zzbbc0 = new zzbbc(this, z);
        zzawo.zzdtx.post(zzbbc0);
    }

    public final void pause() {
        zzbat zzbat0 = this.zzdzf;
        if(zzbat0 == null) {
            return;
        }
        zzbat0.pause();
    }

    public final void play() {
        zzbat zzbat0 = this.zzdzf;
        if(zzbat0 == null) {
            return;
        }
        zzbat0.play();
    }

    public final void seekTo(int v) {
        zzbat zzbat0 = this.zzdzf;
        if(zzbat0 == null) {
            return;
        }
        zzbat0.seekTo(v);
    }

    public final void setVolume(float f) {
        zzbat zzbat0 = this.zzdzf;
        if(zzbat0 == null) {
            return;
        }
        zzbat0.zzdyz.setVolume(f);
        zzbat0.zzxx();
    }

    public static void zza(zzbbm zzbbm0) {
        HashMap hashMap0 = new HashMap();
        hashMap0.put("event", "no_video_view");
        zzbbm0.zza("onVideoEvent", hashMap0);
    }

    public static void zza(zzbbm zzbbm0, String s) {
        HashMap hashMap0 = new HashMap();
        hashMap0.put("event", "decoderProps");
        hashMap0.put("error", s);
        zzbbm0.zza("onVideoEvent", hashMap0);
    }

    public static void zza(zzbbm zzbbm0, Map map0) {
        HashMap hashMap0 = new HashMap();
        hashMap0.put("event", "decoderProps");
        hashMap0.put("mimeTypes", map0);
        zzbbm0.zza("onVideoEvent", hashMap0);
    }

    public final void zza(float f, float f1) {
        zzbat zzbat0 = this.zzdzf;
        if(zzbat0 != null) {
            zzbat0.zza(f, f1);
        }
    }

    // 检测为 Lambda 实现
    final void zzau(boolean z) [...]

    public final void zzc(String s, String[] arr_s) {
        this.zzdzm = s;
        this.zzdzn = arr_s;
    }

    public final void zzcv(int v) {
        this.zzdzf.zzcv(v);
    }

    public final void zzcw(int v) {
        this.zzdzf.zzcw(v);
    }

    public final void zzcx(int v) {
        this.zzdzf.zzcx(v);
    }

    public final void zzcy(int v) {
        this.zzdzf.zzcy(v);
    }

    public final void zzcz(int v) {
        this.zzdzf.zzcz(v);
    }

    private final void zzd(String s, String[] arr_s) {
        HashMap hashMap0 = new HashMap();
        hashMap0.put("event", s);
        String s1 = null;
        for(int v = 0; v < arr_s.length; ++v) {
            String s2 = arr_s[v];
            if(s1 == null) {
                s1 = s2;
            }
            else {
                hashMap0.put(s1, s2);
                s1 = null;
            }
        }
        this.zzdza.zza("onVideoEvent", hashMap0);
    }

    public final void zzd(int v, int v1, int v2, int v3) {
        if(v2 != 0 && v3 != 0) {
            FrameLayout.LayoutParams frameLayout$LayoutParams0 = new FrameLayout.LayoutParams(v2, v3);
            frameLayout$LayoutParams0.setMargins(v, v1, 0, 0);
            this.zzdzb.setLayoutParams(frameLayout$LayoutParams0);
            this.requestLayout();
        }
    }

    @TargetApi(14)
    public final void zze(MotionEvent motionEvent0) {
        zzbat zzbat0 = this.zzdzf;
        if(zzbat0 == null) {
            return;
        }
        zzbat0.dispatchTouchEvent(motionEvent0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbau
    public final void zzew() {
        zzbat zzbat0 = this.zzdzf;
        if(zzbat0 == null) {
            return;
        }
        if(this.zzdzl == 0L) {
            this.zzd("canplaythrough", new String[]{"duration", String.valueOf(((float)zzbat0.getDuration()) / 1000.0f), "videoWidth", String.valueOf(this.zzdzf.getVideoWidth()), "videoHeight", String.valueOf(this.zzdzf.getVideoHeight())});
        }
    }

    public final void zzhv() {
        if(this.zzdzf == null) {
            return;
        }
        if(!TextUtils.isEmpty(this.zzdzm)) {
            this.zzdzf.zzb(this.zzdzm, this.zzdzn);
            return;
        }
        this.zzd("no_src", new String[0]);
    }

    @Override  // com.google.android.gms.internal.ads.zzbau
    public final void zzk(int v, int v1) {
        if(this.zzdzj) {
            int v2 = Math.max(v / ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzchy)))), 1);
            int v3 = Math.max(v1 / ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzchy)))), 1);
            if(this.zzdzo == null || this.zzdzo.getWidth() != v2 || this.zzdzo.getHeight() != v3) {
                this.zzdzo = Bitmap.createBitmap(v2, v3, Bitmap.Config.ARGB_8888);
                this.zzdzq = false;
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbau
    public final void zzm(String s, @Nullable String s1) {
        this.zzd("error", new String[]{"what", s, "extra", s1});
    }

    @Override  // com.google.android.gms.internal.ads.zzbau
    public final void zzxy() {
        this.zzdzd.resume();
        zzbba zzbba0 = new zzbba(this);
        zzawo.zzdtx.post(zzbba0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbau
    public final void zzxz() {
        if(this.zzdza.zzys() != null && !this.zzdzh) {
            this.zzdzi = (this.zzdza.zzys().getWindow().getAttributes().flags & 0x80) != 0;
            if(!this.zzdzi) {
                this.zzdza.zzys().getWindow().addFlags(0x80);
                this.zzdzh = true;
            }
        }
        this.zzdzg = true;
    }

    @Override  // com.google.android.gms.internal.ads.zzbau
    public final void zzya() {
        this.zzd("ended", new String[0]);
        this.zzyi();
    }

    @Override  // com.google.android.gms.internal.ads.zzbau
    public final void zzyb() {
        if(this.zzdzq && this.zzdzo != null && !this.zzyh()) {
            this.zzdzp.setImageBitmap(this.zzdzo);
            this.zzdzp.invalidate();
            ImageView imageView0 = this.zzdzp;
            FrameLayout.LayoutParams frameLayout$LayoutParams0 = new FrameLayout.LayoutParams(-1, -1);
            this.zzdzb.addView(imageView0, frameLayout$LayoutParams0);
            this.zzdzb.bringChildToFront(this.zzdzp);
        }
        this.zzdzd.pause();
        this.zzdzl = this.zzdzk;
        zzbaz zzbaz0 = new zzbaz(this);
        zzawo.zzdtx.post(zzbaz0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbau
    public final void zzyc() {
        if(this.zzdzg && this.zzyh()) {
            this.zzdzb.removeView(this.zzdzp);
        }
        if(this.zzdzo != null) {
            long v = zzq.zzlc().elapsedRealtime();
            if(this.zzdzf.getBitmap(this.zzdzo) != null) {
                this.zzdzq = true;
            }
            long v1 = zzq.zzlc().elapsedRealtime() - v;
            if(zzawf.zzvx()) {
                zzawf.zzee(("Spinner frame grab took " + v1 + "ms"));
            }
            if(v1 > this.zzdze) {
                zzawf.zzfa("Spinner frame grab crossed jank threshold! Suspending spinner.");
                this.zzdzj = false;
                this.zzdzo = null;
                zzaak zzaak0 = this.zzdzc;
                if(zzaak0 != null) {
                    zzaak0.zzh("spinner_jank", Long.toString(v1));
                }
            }
        }
    }

    public final void zzyd() {
        zzbat zzbat0 = this.zzdzf;
        if(zzbat0 == null) {
            return;
        }
        zzbat0.zzdyz.setMuted(true);
        zzbat0.zzxx();
    }

    public final void zzye() {
        zzbat zzbat0 = this.zzdzf;
        if(zzbat0 == null) {
            return;
        }
        zzbat0.zzdyz.setMuted(false);
        zzbat0.zzxx();
    }

    @TargetApi(14)
    public final void zzyf() {
        zzbat zzbat0 = this.zzdzf;
        if(zzbat0 == null) {
            return;
        }
        TextView textView0 = new TextView(zzbat0.getContext());
        String s = this.zzdzf.zzxt();
        textView0.setText((s.length() == 0 ? new String("AdMob - ") : "AdMob - " + s));
        textView0.setTextColor(0xFFFF0000);
        textView0.setBackgroundColor(0xFFFFFF00);
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = new FrameLayout.LayoutParams(-2, -2, 17);
        this.zzdzb.addView(textView0, frameLayout$LayoutParams0);
        this.zzdzb.bringChildToFront(textView0);
    }

    final void zzyg() {
        zzbat zzbat0 = this.zzdzf;
        if(zzbat0 == null) {
            return;
        }
        long v = (long)zzbat0.getCurrentPosition();
        if(this.zzdzk != v && v > 0L) {
            this.zzd("timeupdate", new String[]{"time", String.valueOf(((float)v) / 1000.0f)});
            this.zzdzk = v;
        }
    }

    private final boolean zzyh() {
        return this.zzdzp.getParent() != null;
    }

    private final void zzyi() {
        if(this.zzdza.zzys() == null) {
            return;
        }
        if(this.zzdzh && !this.zzdzi) {
            this.zzdza.zzys().getWindow().clearFlags(0x80);
            this.zzdzh = false;
        }
    }
}

