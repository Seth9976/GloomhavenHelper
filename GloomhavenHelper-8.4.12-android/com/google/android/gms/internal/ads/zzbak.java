package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View.MeasureSpec;
import com.google.android.gms.ads.internal.zzq;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@TargetApi(14)
public final class zzbak extends zzbat implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, TextureView.SurfaceTextureListener {
    private static final Map zzdyd;
    private final zzbbl zzdye;
    private final boolean zzdyf;
    private int zzdyg;
    private int zzdyh;
    private MediaPlayer zzdyi;
    private Uri zzdyj;
    private int zzdyk;
    private int zzdyl;
    private int zzdym;
    private int zzdyn;
    private int zzdyo;
    private zzbbk zzdyp;
    private boolean zzdyq;
    private int zzdyr;
    private zzbau zzdys;

    static {
        zzbak.zzdyd = new HashMap();
        if(Build.VERSION.SDK_INT >= 17) {
            zzbak.zzdyd.put(-1004, "MEDIA_ERROR_IO");
            zzbak.zzdyd.put(-1007, "MEDIA_ERROR_MALFORMED");
            zzbak.zzdyd.put(-1010, "MEDIA_ERROR_UNSUPPORTED");
            zzbak.zzdyd.put(-110, "MEDIA_ERROR_TIMED_OUT");
            zzbak.zzdyd.put(3, "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzbak.zzdyd.put(100, "MEDIA_ERROR_SERVER_DIED");
        zzbak.zzdyd.put(1, "MEDIA_ERROR_UNKNOWN");
        zzbak.zzdyd.put(1, "MEDIA_INFO_UNKNOWN");
        zzbak.zzdyd.put(700, "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzbak.zzdyd.put(701, "MEDIA_INFO_BUFFERING_START");
        zzbak.zzdyd.put(702, "MEDIA_INFO_BUFFERING_END");
        zzbak.zzdyd.put(800, "MEDIA_INFO_BAD_INTERLEAVING");
        zzbak.zzdyd.put(801, "MEDIA_INFO_NOT_SEEKABLE");
        zzbak.zzdyd.put(802, "MEDIA_INFO_METADATA_UPDATE");
        if(Build.VERSION.SDK_INT >= 19) {
            zzbak.zzdyd.put(901, "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzbak.zzdyd.put(902, "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }

    public zzbak(Context context0, boolean z, boolean z1, zzbbj zzbbj0, zzbbl zzbbl0) {
        super(context0);
        this.zzdyg = 0;
        this.zzdyh = 0;
        this.setSurfaceTextureListener(this);
        this.zzdye = zzbbl0;
        this.zzdyq = z;
        this.zzdyf = z1;
        this.zzdye.zzb(this);
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzbat
    public final int getCurrentPosition() {
        return this.zzxw() ? this.zzdyi.getCurrentPosition() : 0;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzbat
    public final int getDuration() {
        return this.zzxw() ? this.zzdyi.getDuration() : -1;
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final int getVideoHeight() {
        return this.zzdyi == null ? 0 : this.zzdyi.getVideoHeight();
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final int getVideoWidth() {
        return this.zzdyi == null ? 0 : this.zzdyi.getVideoWidth();
    }

    @Override  // android.media.MediaPlayer$OnBufferingUpdateListener
    public final void onBufferingUpdate(MediaPlayer mediaPlayer0, int v) {
        this.zzdym = v;
    }

    @Override  // android.media.MediaPlayer$OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer0) {
        zzawf.zzee("AdMediaPlayerView completion");
        this.zzct(5);
        this.zzdyh = 5;
        zzbal zzbal0 = new zzbal(this);
        zzawo.zzdtx.post(zzbal0);
    }

    @Override  // android.media.MediaPlayer$OnErrorListener
    public final boolean onError(MediaPlayer mediaPlayer0, int v, int v1) {
        String s = (String)zzbak.zzdyd.get(v);
        String s1 = (String)zzbak.zzdyd.get(v1);
        zzawf.zzfa(("AdMediaPlayerView MediaPlayer error: " + s + ":" + s1));
        this.zzct(-1);
        this.zzdyh = -1;
        zzbao zzbao0 = new zzbao(this, s, s1);
        zzawo.zzdtx.post(zzbao0);
        return true;
    }

    @Override  // android.media.MediaPlayer$OnInfoListener
    public final boolean onInfo(MediaPlayer mediaPlayer0, int v, int v1) {
        zzawf.zzee(("AdMediaPlayerView MediaPlayer info: " + ((String)zzbak.zzdyd.get(v)) + ":" + ((String)zzbak.zzdyd.get(v1))));
        return true;
    }

    @Override  // android.view.View
    protected final void onMeasure(int v, int v1) {
        int v5;
        int v2 = zzbak.getDefaultSize(this.zzdyk, v);
        int v3 = zzbak.getDefaultSize(this.zzdyl, v1);
        if(this.zzdyk <= 0 || this.zzdyl <= 0 || this.zzdyp != null) {
            v5 = v2;
        }
        else {
            int v4 = View.MeasureSpec.getMode(v);
            v5 = View.MeasureSpec.getSize(v);
            int v6 = View.MeasureSpec.getMode(v1);
            int v7 = View.MeasureSpec.getSize(v1);
            if(v4 == 0x40000000 && v6 == 0x40000000) {
                int v8 = this.zzdyk;
                int v9 = this.zzdyl;
                if(v8 * v7 < v5 * v9) {
                    v3 = v7;
                    v5 = v8 * v7 / v9;
                }
                else if(v8 * v7 > v5 * v9) {
                    v3 = v9 * v5 / v8;
                }
                else {
                    v3 = v7;
                }
            }
            else if(v4 == 0x40000000) {
                int v10 = this.zzdyl * v5 / this.zzdyk;
                v3 = v6 == 0x80000000 && v10 > v7 ? v7 : v10;
            }
            else if(v6 == 0x40000000) {
                int v11 = this.zzdyk * v7 / this.zzdyl;
                if(v4 != 0x80000000 || v11 <= v5) {
                    v5 = v11;
                }
                v3 = v7;
            }
            else {
                int v12 = this.zzdyk;
                int v13 = this.zzdyl;
                if(v6 != 0x80000000 || v13 <= v7) {
                    v3 = v13;
                }
                else {
                    v12 = v12 * v7 / v13;
                    v3 = v7;
                }
                if(v4 != 0x80000000 || v12 <= v5) {
                    v5 = v12;
                }
                else {
                    v3 = this.zzdyl * v5 / this.zzdyk;
                }
            }
        }
        this.setMeasuredDimension(v5, v3);
        zzbbk zzbbk0 = this.zzdyp;
        if(zzbbk0 != null) {
            zzbbk0.zzm(v5, v3);
        }
        if(Build.VERSION.SDK_INT == 16) {
            if(this.zzdyn > 0 && this.zzdyn != v5 || this.zzdyo > 0 && this.zzdyo != v3) {
                this.zzxv();
            }
            this.zzdyn = v5;
            this.zzdyo = v3;
        }
    }

    @Override  // android.media.MediaPlayer$OnPreparedListener
    public final void onPrepared(MediaPlayer mediaPlayer0) {
        zzawf.zzee("AdMediaPlayerView prepared");
        this.zzct(2);
        this.zzdye.zzew();
        zzbam zzbam0 = new zzbam(this);
        zzawo.zzdtx.post(zzbam0);
        this.zzdyk = mediaPlayer0.getVideoWidth();
        this.zzdyl = mediaPlayer0.getVideoHeight();
        int v = this.zzdyr;
        if(v != 0) {
            this.seekTo(v);
        }
        this.zzxv();
        zzawf.zzez(("AdMediaPlayerView stream dimensions: " + this.zzdyk + " x " + this.zzdyl));
        if(this.zzdyh == 3) {
            this.play();
        }
        this.zzxx();
    }

    @Override  // android.view.TextureView$SurfaceTextureListener
    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture0, int v, int v1) {
        zzawf.zzee("AdMediaPlayerView surface created");
        this.zzxu();
        zzban zzban0 = new zzban(this);
        zzawo.zzdtx.post(zzban0);
    }

    @Override  // android.view.TextureView$SurfaceTextureListener
    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture0) {
        zzawf.zzee("AdMediaPlayerView surface destroyed");
        MediaPlayer mediaPlayer0 = this.zzdyi;
        if(mediaPlayer0 != null && this.zzdyr == 0) {
            this.zzdyr = mediaPlayer0.getCurrentPosition();
        }
        zzbbk zzbbk0 = this.zzdyp;
        if(zzbbk0 != null) {
            zzbbk0.zzyk();
        }
        zzbap zzbap0 = new zzbap(this);
        zzawo.zzdtx.post(zzbap0);
        this.zzat(true);
        return true;
    }

    @Override  // android.view.TextureView$SurfaceTextureListener
    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture0, int v, int v1) {
        zzawf.zzee("AdMediaPlayerView surface changed");
        boolean z = true;
        boolean z1 = this.zzdyh == 3;
        if(this.zzdyk != v || this.zzdyl != v1) {
            z = false;
        }
        if(this.zzdyi != null && z1 && z) {
            int v2 = this.zzdyr;
            if(v2 != 0) {
                this.seekTo(v2);
            }
            this.play();
        }
        zzbbk zzbbk0 = this.zzdyp;
        if(zzbbk0 != null) {
            zzbbk0.zzm(v, v1);
        }
        zzbaq zzbaq0 = new zzbaq(this, v, v1);
        zzawo.zzdtx.post(zzbaq0);
    }

    @Override  // android.view.TextureView$SurfaceTextureListener
    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture0) {
        this.zzdye.zzc(this);
        this.zzdyy.zza(surfaceTexture0, this.zzdys);
    }

    @Override  // android.media.MediaPlayer$OnVideoSizeChangedListener
    public final void onVideoSizeChanged(MediaPlayer mediaPlayer0, int v, int v1) {
        zzawf.zzee(("AdMediaPlayerView size changed: " + v + " x " + v1));
        this.zzdyk = mediaPlayer0.getVideoWidth();
        this.zzdyl = mediaPlayer0.getVideoHeight();
        if(this.zzdyk != 0 && this.zzdyl != 0) {
            this.requestLayout();
        }
    }

    @Override  // android.view.View
    protected final void onWindowVisibilityChanged(int v) {
        zzawf.zzee(("AdMediaPlayerView window visibility changed to " + v));
        zzbaj zzbaj0 = () -> {
            zzbau zzbau0 = this.zzdys;
            if(zzbau0 != null) {
                zzbau0.onWindowVisibilityChanged(v);
            }
        };
        zzawo.zzdtx.post(zzbaj0);
        super.onWindowVisibilityChanged(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void pause() {
        zzawf.zzee("AdMediaPlayerView pause");
        if(this.zzxw() && this.zzdyi.isPlaying()) {
            this.zzdyi.pause();
            this.zzct(4);
            zzbar zzbar0 = new zzbar(this);
            zzawo.zzdtx.post(zzbar0);
        }
        this.zzdyh = 4;
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void play() {
        zzawf.zzee("AdMediaPlayerView play");
        if(this.zzxw()) {
            this.zzdyi.start();
            this.zzct(3);
            this.zzdyy.zzxz();
            zzbas zzbas0 = new zzbas(this);
            zzawo.zzdtx.post(zzbas0);
        }
        this.zzdyh = 3;
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void seekTo(int v) {
        zzawf.zzee(("AdMediaPlayerView seek " + v));
        if(this.zzxw()) {
            this.zzdyi.seekTo(v);
            this.zzdyr = 0;
            return;
        }
        this.zzdyr = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void setVideoPath(String s) {
        Uri uri0 = Uri.parse(s);
        zzrz zzrz0 = zzrz.zzd(uri0);
        if(zzrz0 == null || zzrz0.url != null) {
            if(zzrz0 != null) {
                uri0 = Uri.parse(zzrz0.url);
            }
            this.zzdyj = uri0;
            this.zzdyr = 0;
            this.zzxu();
            this.requestLayout();
            this.invalidate();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void stop() {
        zzawf.zzee("AdMediaPlayerView stop");
        MediaPlayer mediaPlayer0 = this.zzdyi;
        if(mediaPlayer0 != null) {
            mediaPlayer0.stop();
            this.zzdyi.release();
            this.zzdyi = null;
            this.zzct(0);
            this.zzdyh = 0;
        }
        this.zzdye.onStop();
    }

    @Override  // android.view.View
    public final String toString() {
        return this.getClass().getName() + "@" + Integer.toHexString(this.hashCode());
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

    private final void zzat(boolean z) {
        zzawf.zzee("AdMediaPlayerView release");
        zzbbk zzbbk0 = this.zzdyp;
        if(zzbbk0 != null) {
            zzbbk0.zzyk();
            this.zzdyp = null;
        }
        MediaPlayer mediaPlayer0 = this.zzdyi;
        if(mediaPlayer0 != null) {
            mediaPlayer0.reset();
            this.zzdyi.release();
            this.zzdyi = null;
            this.zzct(0);
            if(z) {
                this.zzdyh = 0;
            }
        }
    }

    private final void zzct(int v) {
        if(v == 3) {
            this.zzdye.zzyn();
            this.zzdyz.zzyn();
        }
        else if(this.zzdyg == 3) {
            this.zzdye.zzyo();
            this.zzdyz.zzyo();
        }
        this.zzdyg = v;
    }

    // 检测为 Lambda 实现
    final void zzcu(int v) [...]

    private final void zzd(float f) {
        MediaPlayer mediaPlayer0 = this.zzdyi;
        if(mediaPlayer0 != null) {
            try {
                mediaPlayer0.setVolume(f, f);
            }
            catch(IllegalStateException unused_ex) {
            }
            return;
        }
        zzawf.zzfa("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final String zzxt() {
        String s = String.valueOf((this.zzdyq ? " spherical" : ""));
        return s.length() == 0 ? new String("MediaPlayer") : "MediaPlayer" + s;
    }

    private final void zzxu() {
        zzawf.zzee("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture0 = this.getSurfaceTexture();
        if(this.zzdyj != null && surfaceTexture0 != null) {
            this.zzat(false);
            try {
                this.zzdyi = new MediaPlayer();
                this.zzdyi.setOnBufferingUpdateListener(this);
                this.zzdyi.setOnCompletionListener(this);
                this.zzdyi.setOnErrorListener(this);
                this.zzdyi.setOnInfoListener(this);
                this.zzdyi.setOnPreparedListener(this);
                this.zzdyi.setOnVideoSizeChangedListener(this);
                this.zzdym = 0;
                if(this.zzdyq) {
                    this.zzdyp = new zzbbk(this.getContext());
                    this.zzdyp.zza(surfaceTexture0, this.getWidth(), this.getHeight());
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
                this.zzdyi.setDataSource(this.getContext(), this.zzdyj);
                Surface surface0 = new Surface(surfaceTexture0);
                this.zzdyi.setSurface(surface0);
                this.zzdyi.setAudioStreamType(3);
                this.zzdyi.setScreenOnWhilePlaying(true);
                this.zzdyi.prepareAsync();
                this.zzct(1);
            }
            catch(IOException | IllegalArgumentException | IllegalStateException iOException0) {
                zzawf.zzd(("Failed to initialize MediaPlayer at " + this.zzdyj), iOException0);
                this.onError(this.zzdyi, 1, 0);
            }
        }
    }

    private final void zzxv() {
        if(!this.zzdyf) {
            return;
        }
        if(this.zzxw() && this.zzdyi.getCurrentPosition() > 0 && this.zzdyh != 3) {
            zzawf.zzee("AdMediaPlayerView nudging MediaPlayer");
            this.zzd(0.0f);
            this.zzdyi.start();
            int v = this.zzdyi.getCurrentPosition();
            long v1 = zzq.zzlc().currentTimeMillis();
            while(this.zzxw() && this.zzdyi.getCurrentPosition() == v && zzq.zzlc().currentTimeMillis() - v1 <= 0xFAL) {
            }
            this.zzdyi.pause();
            this.zzxx();
        }
    }

    private final boolean zzxw() {
        return this.zzdyi != null && (this.zzdyg != -1 && this.zzdyg != 0 && this.zzdyg != 1);
    }

    @Override  // com.google.android.gms.internal.ads.zzbat
    public final void zzxx() {
        this.zzd(this.zzdyz.getVolume());
    }
}

