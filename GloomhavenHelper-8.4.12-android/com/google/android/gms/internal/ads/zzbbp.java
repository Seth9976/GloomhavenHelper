package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import com.google.android.gms.ads.internal.zzq;
import java.nio.ByteBuffer;
import java.util.Arrays;

@TargetApi(16)
public final class zzbbp extends zzbat implements TextureView.SurfaceTextureListener, zzbcn {
    private Surface zzbib;
    private final zzbbl zzdye;
    private final boolean zzdyf;
    private int zzdyk;
    private int zzdyl;
    private int zzdyn;
    private int zzdyo;
    private zzbbk zzdyp;
    private final boolean zzdyq;
    private zzbau zzdys;
    private final zzbbm zzdza;
    private String[] zzdzn;
    private final zzbbj zzeck;
    private zzbcf zzecl;
    private String zzecm;
    private boolean zzecn;
    private int zzeco;
    private boolean zzecp;
    private boolean zzecq;
    private float zzecr;

    public zzbbp(Context context0, zzbbl zzbbl0, zzbbm zzbbm0, boolean z, boolean z1, zzbbj zzbbj0) {
        super(context0);
        this.zzeco = 1;
        this.zzdyf = z1;
        this.zzdza = zzbbm0;
        this.zzdye = zzbbl0;
        this.zzdyq = z;
        this.zzeck = zzbbj0;
        this.setSurfaceTextureListener(this);
        this.zzdye.zzb(this);
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzbat
    public final int getCurrentPosition() {
        return this.zzzf() ? ((int)this.zzecl.zzzr().zzei()) : 0;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzbat
    public final int getDuration() {
        return this.zzzf() ? ((int)this.zzecl.zzzr().getDuration()) : 0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final int getVideoHeight() {
        return this.zzdyl;
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final int getVideoWidth() {
        return this.zzdyk;
    }

    @Override  // android.view.View
    protected final void onMeasure(int v, int v1) {
        super.onMeasure(v, v1);
        int v2 = this.getMeasuredWidth();
        int v3 = this.getMeasuredHeight();
        float f = this.zzecr;
        if(f != 0.0f && this.zzdyp == null) {
            float f1 = ((float)v2) / ((float)v3);
            if(f > f1) {
                v3 = (int)(((float)v2) / f);
            }
            float f2 = this.zzecr;
            if(f2 < f1) {
                v2 = (int)(((float)v3) * f2);
            }
        }
        this.setMeasuredDimension(v2, v3);
        zzbbk zzbbk0 = this.zzdyp;
        if(zzbbk0 != null) {
            zzbbk0.zzm(v2, v3);
        }
        if(Build.VERSION.SDK_INT == 16) {
            if((this.zzdyn > 0 && this.zzdyn != v2 || this.zzdyo > 0 && this.zzdyo != v3) && (this.zzdyf && this.zzze())) {
                zzgn zzgn0 = this.zzecl.zzzr();
                if(zzgn0.zzei() > 0L && !zzgn0.zzeg()) {
                    this.zza(0.0f, true);
                    zzgn0.zzg(true);
                    long v4 = zzgn0.zzei();
                    long v5 = zzq.zzlc().currentTimeMillis();
                    while(this.zzze() && zzgn0.zzei() == v4 && zzq.zzlc().currentTimeMillis() - v5 <= 0xFAL) {
                    }
                    zzgn0.zzg(false);
                    this.zzxx();
                }
            }
            this.zzdyn = v2;
            this.zzdyo = v3;
        }
    }

    @Override  // android.view.TextureView$SurfaceTextureListener
    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture0, int v, int v1) {
        if(this.zzdyq) {
            this.zzdyp = new zzbbk(this.getContext());
            this.zzdyp.zza(surfaceTexture0, v, v1);
            this.zzdyp.start();
            SurfaceTexture surfaceTexture1 = this.zzdyp.zzyl();
            if(surfaceTexture1 == null) {
                this.zzdyp.zzyk();
                this.zzdyp = null;
            }
            else {
                surfaceTexture0 = surfaceTexture1;
            }
        }
        this.zzbib = new Surface(surfaceTexture0);
        if(this.zzecl == null) {
            this.zzzg();
        }
        else {
            this.zza(this.zzbib, true);
            if(!this.zzeck.zzeak) {
                this.zzzj();
            }
        }
        if(this.zzdyk == 0 || this.zzdyl == 0) {
            this.zzo(v, v1);
        }
        else {
            this.zzzi();
        }
        zzbbv zzbbv0 = () -> {
            zzbau zzbau0 = this.zzdys;
            if(zzbau0 != null) {
                zzbau0.zzxy();
            }
        };
        zzawo.zzdtx.post(zzbbv0);
    }

    @Override  // android.view.TextureView$SurfaceTextureListener
    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture0) {
        this.pause();
        zzbbk zzbbk0 = this.zzdyp;
        if(zzbbk0 != null) {
            zzbbk0.zzyk();
            this.zzdyp = null;
        }
        if(this.zzecl != null) {
            this.zzzk();
            Surface surface0 = this.zzbib;
            if(surface0 != null) {
                surface0.release();
            }
            this.zzbib = null;
            this.zza(null, true);
        }
        zzbbx zzbbx0 = () -> {
            zzbau zzbau0 = this.zzdys;
            if(zzbau0 != null) {
                zzbau0.zzyb();
            }
        };
        zzawo.zzdtx.post(zzbbx0);
        return true;
    }

    @Override  // android.view.TextureView$SurfaceTextureListener
    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture0, int v, int v1) {
        zzbbk zzbbk0 = this.zzdyp;
        if(zzbbk0 != null) {
            zzbbk0.zzm(v, v1);
        }
        zzbby zzbby0 = () -> {
            zzbau zzbau0 = this.zzdys;
            if(zzbau0 != null) {
                zzbau0.zzk(v, v1);
            }
        };
        zzawo.zzdtx.post(zzbby0);
    }

    @Override  // android.view.TextureView$SurfaceTextureListener
    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture0) {
        this.zzdye.zzc(this);
        this.zzdyy.zza(surfaceTexture0, this.zzdys);
    }

    @Override  // android.view.View
    protected final void onWindowVisibilityChanged(int v) {
        zzawf.zzee(("AdExoPlayerView3 window visibility changed to " + v));
        zzbca zzbca0 = () -> {
            zzbau zzbau0 = this.zzdys;
            if(zzbau0 != null) {
                zzbau0.onWindowVisibilityChanged(v);
            }
        };
        zzawo.zzdtx.post(zzbca0);
        super.onWindowVisibilityChanged(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void pause() {
        if(this.zzzf()) {
            if(this.zzeck.zzeak) {
                this.zzzk();
            }
            this.zzecl.zzzr().zzg(false);
            this.zzdye.zzyo();
            this.zzdyz.zzyo();
            zzbbw zzbbw0 = () -> {
                zzbau zzbau0 = this.zzdys;
                if(zzbau0 != null) {
                    zzbau0.onPaused();
                }
            };
            zzawo.zzdtx.post(zzbbw0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void play() {
        if(this.zzzf()) {
            if(this.zzeck.zzeak) {
                this.zzzj();
            }
            this.zzecl.zzzr().zzg(true);
            this.zzdye.zzyn();
            this.zzdyz.zzyn();
            this.zzdyy.zzxz();
            zzbbt zzbbt0 = () -> {
                zzbau zzbau0 = this.zzdys;
                if(zzbau0 != null) {
                    zzbau0.zzxz();
                }
            };
            zzawo.zzdtx.post(zzbbt0);
            return;
        }
        this.zzecq = true;
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void seekTo(int v) {
        if(this.zzzf()) {
            this.zzecl.zzzr().seekTo(((long)v));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void setVideoPath(String s) {
        if(s != null) {
            this.zzecm = s;
            this.zzdzn = new String[]{s};
            this.zzzg();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void stop() {
        if(this.zzze()) {
            this.zzecl.zzzr().stop();
            if(this.zzecl != null) {
                this.zza(null, true);
                zzbcf zzbcf0 = this.zzecl;
                if(zzbcf0 != null) {
                    zzbcf0.zza(null);
                    this.zzecl.release();
                    this.zzecl = null;
                }
                this.zzeco = 1;
                this.zzecn = false;
                this.zzecp = false;
                this.zzecq = false;
            }
        }
        this.zzdye.zzyo();
        this.zzdyz.zzyo();
        this.zzdye.onStop();
    }

    private final void zza(float f, boolean z) {
        zzbcf zzbcf0 = this.zzecl;
        if(zzbcf0 != null) {
            zzbcf0.zzb(f, z);
            return;
        }
        zzawf.zzfa("Trying to set volume before player is initalized.");
    }

    private final void zza(Surface surface0, boolean z) {
        zzbcf zzbcf0 = this.zzecl;
        if(zzbcf0 != null) {
            zzbcf0.zza(surface0, z);
            return;
        }
        zzawf.zzfa("Trying to set surface before player is initalized.");
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void zza(float f, float f1) {
        zzbbk zzbbk0 = this.zzdyp;
        if(zzbbk0 != null) {
            zzbbk0.zzb(f, f1);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void zza(zzbau zzbau0) {
        this.zzdys = zzbau0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbcn
    public final void zza(String s, Exception exception0) {
        String s1 = s + "/" + exception0.getClass().getCanonicalName() + ":" + exception0.getMessage();
        String s2 = String.valueOf(s1);
        zzawf.zzfa((s2.length() == 0 ? new String("ExoPlayerAdapter error: ") : "ExoPlayerAdapter error: " + s2));
        this.zzecn = true;
        if(this.zzeck.zzeak) {
            this.zzzk();
        }
        zzbbu zzbbu0 = () -> {
            zzbau zzbau0 = this.zzdys;
            if(zzbau0 != null) {
                zzbau0.zzm("ExoPlayerAdapter error", s1);
            }
        };
        zzawo.zzdtx.post(zzbbu0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void zzb(String s, String[] arr_s) {
        if(s != null) {
            if(arr_s == null) {
                this.setVideoPath(s);
            }
            this.zzecm = s;
            this.zzdzn = (String[])Arrays.copyOf(arr_s, arr_s.length);
            this.zzzg();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbcn
    public final void zzb(boolean z, long v) {
        if(this.zzdza != null) {
            zzbbz zzbbz0 = () -> this.zzdza.zza(z, v);
            zzazq.zzdxo.execute(zzbbz0);
        }
    }

    // 检测为 Lambda 实现
    final void zzc(boolean z, long v) [...]

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void zzcv(int v) {
        zzbcf zzbcf0 = this.zzecl;
        if(zzbcf0 != null) {
            zzbcf0.zzzu().zzdc(v);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void zzcw(int v) {
        zzbcf zzbcf0 = this.zzecl;
        if(zzbcf0 != null) {
            zzbcf0.zzzu().zzdd(v);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void zzcx(int v) {
        zzbcf zzbcf0 = this.zzecl;
        if(zzbcf0 != null) {
            zzbcf0.zzzu().zzcx(v);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void zzcy(int v) {
        zzbcf zzbcf0 = this.zzecl;
        if(zzbcf0 != null) {
            zzbcf0.zzzu().zzcy(v);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void zzcz(int v) {
        zzbcf zzbcf0 = this.zzecl;
        if(zzbcf0 != null) {
            zzbcf0.zzcz(v);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbcn
    public final void zzda(int v) {
        if(this.zzeco != v) {
            this.zzeco = v;
            switch(v) {
                case 3: {
                    this.zzzh();
                    return;
                }
                case 4: {
                    if(this.zzeck.zzeak) {
                        this.zzzk();
                    }
                    this.zzdye.zzyo();
                    this.zzdyz.zzyo();
                    zzbbr zzbbr0 = () -> {
                        zzbau zzbau0 = this.zzdys;
                        if(zzbau0 != null) {
                            zzbau0.zzya();
                        }
                    };
                    zzawo.zzdtx.post(zzbbr0);
                }
            }
        }
    }

    // 检测为 Lambda 实现
    final void zzdb(int v) [...]

    // 检测为 Lambda 实现
    final void zzfg(String s) [...]

    @Override  // com.google.android.gms.internal.ads.zzbcn
    public final void zzn(int v, int v1) {
        this.zzdyk = v;
        this.zzdyl = v1;
        this.zzzi();
    }

    private final void zzo(int v, int v1) {
        float f = v1 <= 0 ? 1.0f : ((float)v) / ((float)v1);
        if(this.zzecr != f) {
            this.zzecr = f;
            this.requestLayout();
        }
    }

    // 检测为 Lambda 实现
    final void zzp(int v, int v1) [...]

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final String zzxt() {
        String s = String.valueOf((this.zzdyq ? " spherical" : ""));
        return s.length() == 0 ? new String("ExoPlayer/3") : "ExoPlayer/3" + s;
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void zzxx() {
        this.zza(this.zzdyz.getVolume(), false);
    }

    private final zzbcf zzzc() {
        return new zzbcf(this.zzdza.getContext(), this.zzeck);
    }

    private final String zzzd() {
        return zzq.zzkv().zzr(this.zzdza.getContext(), this.zzdza.zzyw().zzbmj);
    }

    private final boolean zzze() {
        return this.zzecl != null && !this.zzecn;
    }

    private final boolean zzzf() {
        return this.zzze() && this.zzeco != 1;
    }

    private final void zzzg() {
        if(this.zzecl != null) {
            return;
        }
        String s = this.zzecm;
        if(s != null && this.zzbib != null) {
            if(s.startsWith("cache:")) {
                zzbda zzbda0 = this.zzdza.zzff(this.zzecm);
                if(zzbda0 instanceof zzbdl) {
                    this.zzecl = ((zzbdl)zzbda0).zzzw();
                    if(this.zzecl.zzzr() == null) {
                        zzawf.zzfa("Precached video player has been released.");
                        return;
                    }
                }
                else {
                    if(zzbda0 instanceof zzbdm) {
                        String s1 = this.zzzd();
                        ByteBuffer byteBuffer0 = ((zzbdm)zzbda0).getByteBuffer();
                        boolean z = ((zzbdm)zzbda0).zzzx();
                        String s2 = ((zzbdm)zzbda0).getUrl();
                        if(s2 == null) {
                            zzawf.zzfa("Stream cache URL is null.");
                            return;
                        }
                        this.zzecl = this.zzzc();
                        this.zzecl.zza(new Uri[]{Uri.parse(s2)}, s1, byteBuffer0, z);
                        goto label_35;
                    }
                    String s3 = String.valueOf(this.zzecm);
                    zzawf.zzfa((s3.length() == 0 ? new String("Stream cache miss: ") : "Stream cache miss: " + s3));
                    return;
                }
            }
            else {
                this.zzecl = this.zzzc();
                String s4 = this.zzzd();
                Uri[] arr_uri = new Uri[this.zzdzn.length];
                for(int v = 0; true; ++v) {
                    String[] arr_s = this.zzdzn;
                    if(v >= arr_s.length) {
                        break;
                    }
                    arr_uri[v] = Uri.parse(arr_s[v]);
                }
                this.zzecl.zza(arr_uri, s4);
            }
        label_35:
            this.zzecl.zza(this);
            this.zza(this.zzbib, false);
            this.zzeco = this.zzecl.zzzr().getPlaybackState();
            if(this.zzeco == 3) {
                this.zzzh();
            }
        }
    }

    private final void zzzh() {
        if(this.zzecp) {
            return;
        }
        this.zzecp = true;
        zzbbs zzbbs0 = () -> {
            zzbau zzbau0 = this.zzdys;
            if(zzbau0 != null) {
                zzbau0.zzew();
            }
        };
        zzawo.zzdtx.post(zzbbs0);
        this.zzxx();
        this.zzdye.zzew();
        if(this.zzecq) {
            this.play();
        }
    }

    private final void zzzi() {
        this.zzo(this.zzdyk, this.zzdyl);
    }

    private final void zzzj() {
        zzbcf zzbcf0 = this.zzecl;
        if(zzbcf0 != null) {
            zzbcf0.zzaw(true);
        }
    }

    private final void zzzk() {
        zzbcf zzbcf0 = this.zzecl;
        if(zzbcf0 != null) {
            zzbcf0.zzaw(false);
        }
    }

    // 检测为 Lambda 实现
    final void zzzl() [...]

    // 检测为 Lambda 实现
    final void zzzm() [...]

    // 检测为 Lambda 实现
    final void zzzn() [...]

    // 检测为 Lambda 实现
    final void zzzo() [...]

    // 检测为 Lambda 实现
    final void zzzp() [...]

    // 检测为 Lambda 实现
    final void zzzq() [...]
}

