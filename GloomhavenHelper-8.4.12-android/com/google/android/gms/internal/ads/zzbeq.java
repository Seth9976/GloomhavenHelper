package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

public final class zzbeq extends zzxi {
    private final Object lock;
    @GuardedBy("lock")
    private boolean zzace;
    @GuardedBy("lock")
    private boolean zzacf;
    @GuardedBy("lock")
    private int zzadh;
    @GuardedBy("lock")
    private zzxk zzdek;
    private final zzbbm zzdza;
    private final boolean zzeil;
    private final boolean zzeim;
    @GuardedBy("lock")
    private boolean zzein;
    @GuardedBy("lock")
    private boolean zzeio;
    @GuardedBy("lock")
    private float zzeip;
    @GuardedBy("lock")
    private float zzeiq;
    @GuardedBy("lock")
    private float zzeir;
    @GuardedBy("lock")
    private zzaed zzeis;

    public zzbeq(zzbbm zzbbm0, float f, boolean z, boolean z1) {
        this.lock = new Object();
        this.zzeio = true;
        this.zzdza = zzbbm0;
        this.zzeip = f;
        this.zzeil = z;
        this.zzeim = z1;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final float getAspectRatio() {
        synchronized(this.lock) {
        }
        return this.zzeir;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final float getCurrentTime() {
        synchronized(this.lock) {
        }
        return this.zzeiq;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final float getDuration() {
        synchronized(this.lock) {
        }
        return this.zzeip;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final int getPlaybackState() {
        synchronized(this.lock) {
        }
        return this.zzadh;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final boolean isClickToExpandEnabled() {
        boolean z = this.isCustomControlsEnabled();
        synchronized(this.lock) {
        }
        return !z && this.zzacf && this.zzeim;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final boolean isCustomControlsEnabled() {
        synchronized(this.lock) {
        }
        return this.zzeil && this.zzace;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final boolean isMuted() {
        synchronized(this.lock) {
        }
        return this.zzeio;
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final void mute(boolean z) {
        this.zzf((z ? "mute" : "unmute"), null);
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final void pause() {
        this.zzf("pause", null);
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final void play() {
        this.zzf("play", null);
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final void stop() {
        this.zzf("stop", null);
    }

    private final void zza(int v, int v1, boolean z, boolean z1) {
        zzbes zzbes0 = () -> {
            Object object0 = this.lock;
            __monitor_enter(object0);
            boolean z2 = false;
            boolean z3 = v != v1;
            try {
                boolean z4 = !this.zzein && v1 == 1;
                if(this.zzein || z4) {
                    z2 = true;
                }
                try {
                    this.zzein = z2;
                    if(z4 && this.zzdek != null) {
                        this.zzdek.onVideoStart();
                    }
                    if(z3 && v1 == 1 && this.zzdek != null) {
                        this.zzdek.onVideoPlay();
                    }
                    if(z3 && v1 == 2 && this.zzdek != null) {
                        this.zzdek.onVideoPause();
                    }
                    if(z3 && v1 == 3) {
                        if(this.zzdek != null) {
                            this.zzdek.onVideoEnd();
                        }
                        this.zzdza.zzyz();
                    }
                    if(z != z1 && this.zzdek != null) {
                        this.zzdek.onVideoMute(z1);
                    }
                }
                catch(RemoteException remoteException0) {
                    zzazh.zze("#007 Could not call remote method.", remoteException0);
                }
            }
            finally {
                __monitor_exit(object0);
            }
        };
        zzazq.zzdxo.execute(zzbes0);
    }

    public final void zza(float f, float f1, int v, boolean z, float f2) {
        int v2;
        boolean z2;
        synchronized(this.lock) {
            boolean z1 = f1 != this.zzeip || f2 != this.zzeir;
            this.zzeip = f1;
            this.zzeiq = f;
            z2 = this.zzeio;
            this.zzeio = z;
            v2 = this.zzadh;
            this.zzadh = v;
            float f3 = this.zzeir;
            this.zzeir = f2;
            if(Math.abs(this.zzeir - f3) > 0.0001f) {
                this.zzdza.getView().invalidate();
            }
        }
        if(z1 && this.zzeis != null) {
            try {
                this.zzeis.zzrt();
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
        this.zza(v2, v, z2, z);
    }

    public final void zza(zzaed zzaed0) {
        synchronized(this.lock) {
            this.zzeis = zzaed0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final void zza(zzxk zzxk0) {
        synchronized(this.lock) {
            this.zzdek = zzxk0;
        }
    }

    public final void zzabt() {
        int v;
        synchronized(this.lock) {
            v = this.zzadh;
            this.zzadh = 3;
        }
        this.zza(v, 3, this.zzeio, this.zzeio);
    }

    // 检测为 Lambda 实现
    final void zzb(int v, int v1, boolean z, boolean z1) [...]

    public final void zzb(zzzc zzzc0) {
        synchronized(this.lock) {
            this.zzace = zzzc0.zzace;
            this.zzacf = zzzc0.zzacf;
        }
        this.zzf("initialState", CollectionUtils.mapOf("muteStart", (zzzc0.zzacd ? "1" : "0"), "customControlsRequested", (zzzc0.zzace ? "1" : "0"), "clickToExpandRequested", (zzzc0.zzacf ? "1" : "0")));
    }

    public final void zze(float f) {
        synchronized(this.lock) {
            this.zzeiq = f;
        }
    }

    private final void zzf(String s, @Nullable Map map0) {
        HashMap hashMap0 = map0 == null ? new HashMap() : new HashMap(map0);
        hashMap0.put("action", s);
        zzbep zzbep0 = () -> this.zzdza.zza("pubVideoCmd", hashMap0);
        zzazq.zzdxo.execute(zzbep0);
    }

    // 检测为 Lambda 实现
    final void zzj(Map map0) [...]

    @Override  // com.google.android.gms.internal.ads.zzxj
    public final zzxk zzpo() throws RemoteException {
        synchronized(this.lock) {
        }
        return this.zzdek;
    }
}

