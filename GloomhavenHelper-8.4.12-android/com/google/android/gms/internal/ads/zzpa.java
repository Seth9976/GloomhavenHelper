package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import java.nio.ByteBuffer;

@TargetApi(16)
public final class zzpa extends zzkv {
    private int zzago;
    private boolean zzajz;
    private static final int[] zzbhs;
    private final zzpe zzbht;
    private final zzpf zzbhu;
    private final long zzbhv;
    private final int zzbhw;
    private final boolean zzbhx;
    private final long[] zzbhy;
    private zzgz[] zzbhz;
    private zzpc zzbia;
    private Surface zzbib;
    private Surface zzbic;
    private int zzbid;
    private boolean zzbie;
    private long zzbif;
    private long zzbig;
    private int zzbih;
    private int zzbii;
    private int zzbij;
    private float zzbik;
    private int zzbil;
    private int zzbim;
    private int zzbin;
    private float zzbio;
    private int zzbip;
    private int zzbiq;
    private int zzbir;
    private float zzbis;
    zzpb zzbit;
    private long zzbiu;
    private int zzbiv;
    private final Context zzur;

    static {
        zzpa.zzbhs = new int[]{0x780, 1600, 0x5A0, 0x500, 960, 854, 640, 540, 480};
    }

    public zzpa(Context context0, zzkx zzkx0, long v, zzdkp zzdkp0, zzpg zzpg0, int v1) {
        this(context0, zzkx0, 0L, null, false, zzdkp0, zzpg0, -1);
    }

    private zzpa(Context context0, zzkx zzkx0, long v, zziz zziz0, boolean z, zzdkp zzdkp0, zzpg zzpg0, int v1) {
        super(2, zzkx0, null, false);
        this.zzbhv = 0L;
        this.zzbhw = -1;
        this.zzur = context0.getApplicationContext();
        this.zzbht = new zzpe(context0);
        this.zzbhu = new zzpf(zzdkp0, zzpg0);
        this.zzbhx = false;
        this.zzbhy = new long[10];
        this.zzbiu = 0x8000000000000001L;
        this.zzbif = 0x8000000000000001L;
        this.zzbil = -1;
        this.zzbim = -1;
        this.zzbio = -1.0f;
        this.zzbik = -1.0f;
        this.zzbid = 1;
        this.zzjh();
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    public final boolean isReady() {
        if(super.isReady() && (this.zzbie || (this.zzbic != null && this.zzbib == this.zzbic || this.zzha() == null))) {
            this.zzbif = 0x8000000000000001L;
            return true;
        }
        if(this.zzbif == 0x8000000000000001L) {
            return false;
        }
        if(SystemClock.elapsedRealtime() < this.zzbif) {
            return true;
        }
        this.zzbif = 0x8000000000000001L;
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void onOutputFormatChanged(MediaCodec mediaCodec0, MediaFormat mediaFormat0) {
        boolean z = mediaFormat0.containsKey("crop-right") && mediaFormat0.containsKey("crop-left") && mediaFormat0.containsKey("crop-bottom") && mediaFormat0.containsKey("crop-top");
        this.zzbil = z ? mediaFormat0.getInteger("crop-right") - mediaFormat0.getInteger("crop-left") + 1 : mediaFormat0.getInteger("width");
        this.zzbim = z ? mediaFormat0.getInteger("crop-bottom") - mediaFormat0.getInteger("crop-top") + 1 : mediaFormat0.getInteger("height");
        this.zzbio = this.zzbik;
        if(this.zzbij == 90 || this.zzbij == 270) {
            int v = this.zzbil;
            this.zzbil = this.zzbim;
            this.zzbim = v;
            this.zzbio = 1.0f / this.zzbio;
        }
        mediaCodec0.setVideoScalingMode(this.zzbid);
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void onStarted() {
        super.onStarted();
        this.zzbih = 0;
        this.zzbig = SystemClock.elapsedRealtime();
        this.zzbif = 0x8000000000000001L;
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void onStopped() {
        this.zzjk();
        super.onStopped();
    }

    private static int zza(String s, int v, int v1) {
        if(v != -1 && v1 != -1) {
            switch(s) {
                case "video/3gpp": {
                    return v * v1 * 3 / 4;
                }
                case "video/avc": {
                    return "BRAVIA 4K 2015".equals(zzop.MODEL) ? -1 : ((v + 15) / 16 * ((v1 + 15) / 16) << 4 << 4) * 3 / 4;
                }
                case "video/hevc": {
                    return v * v1 * 3 / 8;
                }
                case "video/mp4v-es": {
                    return v * v1 * 3 / 4;
                }
                case "video/x-vnd.on2.vp8": {
                    return v * v1 * 3 / 4;
                }
                case "video/x-vnd.on2.vp9": {
                    return v * v1 * 3 / 8;
                }
                default: {
                    return -1;
                }
            }
        }
        return -1;
    }

    private final void zza(MediaCodec mediaCodec0, int v, long v1) {
        zzoq.beginSection("skipVideoBuffer");
        mediaCodec0.releaseOutputBuffer(v, false);
        zzoq.endSection();
        ++this.zzazg.zzamn;
    }

    @TargetApi(21)
    private final void zza(MediaCodec mediaCodec0, int v, long v1, long v2) {
        this.zzji();
        zzoq.beginSection("releaseOutputBuffer");
        mediaCodec0.releaseOutputBuffer(v, v2);
        zzoq.endSection();
        ++this.zzazg.zzamm;
        this.zzbii = 0;
        this.zzjg();
    }

    // 去混淆评级： 低(20)
    private static boolean zza(boolean z, zzgz zzgz0, zzgz zzgz1) {
        return zzgz0.zzafn.equals(zzgz1.zzafn) && zzpa.zzj(zzgz0) == zzpa.zzj(zzgz1) && (z || zzgz0.width == zzgz1.width && zzgz0.height == zzgz1.height);
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final int zza(zzkx zzkx0, zzgz zzgz0) throws zzld {
        boolean z;
        String s = zzgz0.zzafn;
        int v = 0;
        if(!zzoi.zzbj(s)) {
            return 0;
        }
        zziu zziu0 = zzgz0.zzafq;
        if(zziu0 == null) {
            z = false;
        }
        else {
            z = false;
            for(int v1 = 0; v1 < zziu0.zzams; ++v1) {
                z |= zziu0.zzy(v1).zzamw;
            }
        }
        zzkw zzkw0 = zzkx0.zzb(s, z);
        if(zzkw0 == null) {
            return 1;
        }
        boolean z1 = zzkw0.zzbf(zzgz0.zzafk);
        if(z1 && zzgz0.width > 0 && zzgz0.height > 0) {
            z1 = zzkw0.zza(zzgz0.width, zzgz0.height, ((double)zzgz0.zzafr));
        }
        int v2 = zzkw0.zzazh ? 8 : 4;
        if(zzkw0.zzajz) {
            v = 16;
        }
        return z1 ? 3 | (v2 | v) : 2 | (v2 | v);
    }

    @Override  // com.google.android.gms.internal.ads.zzgj
    public final void zza(int v, Object object0) throws zzgk {
        switch(v) {
            case 1: {
                Surface surface0 = (Surface)object0;
                if(surface0 == null) {
                    Surface surface1 = this.zzbic;
                    if(surface1 == null) {
                        zzkw zzkw0 = this.zzhb();
                        if(zzkw0 != null && this.zzn(zzkw0.zzazi)) {
                            this.zzbic = zzow.zzc(this.zzur, zzkw0.zzazi);
                            surface0 = this.zzbic;
                        }
                    }
                    else {
                        surface0 = surface1;
                    }
                }
                if(this.zzbib != surface0) {
                    this.zzbib = surface0;
                    int v1 = this.getState();
                    if(v1 == 1 || v1 == 2) {
                        MediaCodec mediaCodec0 = this.zzha();
                        if(mediaCodec0 == null || surface0 == null) {
                            this.zzhc();
                            this.zzgz();
                        }
                        else {
                            mediaCodec0.setOutputSurface(surface0);
                        }
                    }
                    if(surface0 == null || surface0 == this.zzbic) {
                        this.zzjh();
                        this.zzjf();
                    }
                    else {
                        this.zzjj();
                        this.zzjf();
                        if(v1 == 2) {
                            this.zzje();
                            return;
                        }
                    }
                    return;
                }
                if(surface0 != null && surface0 != this.zzbic) {
                    this.zzjj();
                    if(this.zzbie) {
                        this.zzbhu.zza(this.zzbib);
                    }
                }
                return;
            }
            case 4: {
                this.zzbid = (int)(((Integer)object0));
                MediaCodec mediaCodec1 = this.zzha();
                if(mediaCodec1 != null) {
                    mediaCodec1.setVideoScalingMode(this.zzbid);
                }
                return;
            }
            default: {
                super.zza(v, object0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void zza(long v, boolean z) throws zzgk {
        super.zza(v, z);
        this.zzjf();
        this.zzbii = 0;
        int v1 = this.zzbiv;
        if(v1 != 0) {
            this.zzbiu = this.zzbhy[v1 - 1];
            this.zzbiv = 0;
        }
        if(z) {
            this.zzje();
            return;
        }
        this.zzbif = 0x8000000000000001L;
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void zza(zziv zziv0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void zza(zzkw zzkw0, MediaCodec mediaCodec0, zzgz zzgz0, MediaCrypto mediaCrypto0) throws zzld {
        zzpc zzpc0;
        zzgz[] arr_zzgz = this.zzbhz;
        int v = zzgz0.width;
        int v1 = zzgz0.height;
        int v2 = zzpa.zzi(zzgz0);
        if(arr_zzgz.length == 1) {
            zzpc0 = new zzpc(v, v1, v2);
        }
        else {
            int v3 = v1;
            int v4 = v2;
            int v5 = 0;
            int v6 = v;
            for(int v7 = 0; v7 < arr_zzgz.length; ++v7) {
                zzgz zzgz1 = arr_zzgz[v7];
                if(zzpa.zza(zzkw0.zzazh, zzgz0, zzgz1)) {
                    v5 |= (zzgz1.width == -1 || zzgz1.height == -1 ? 1 : 0);
                    v6 = Math.max(v6, zzgz1.width);
                    v4 = Math.max(v4, zzpa.zzi(zzgz1));
                    v3 = Math.max(v3, zzgz1.height);
                }
            }
            if(v5 != 0) {
                Point point0 = null;
                Log.w("MediaCodecVideoRenderer", "Resolutions unknown. Codec max resolution: " + v6 + "x" + v3);
                boolean z = zzgz0.height > zzgz0.width;
                int v8 = z ? zzgz0.height : zzgz0.width;
                int v9 = z ? zzgz0.width : zzgz0.height;
                int[] arr_v = zzpa.zzbhs;
                for(int v10 = 0; v10 < arr_v.length; ++v10) {
                    int v11 = arr_v[v10];
                    int v12 = (int)(((float)v11) * (((float)v9) / ((float)v8)));
                    if(v11 <= v8 || v12 <= v9) {
                        break;
                    }
                    int v13 = z ? v12 : v11;
                    if(!z) {
                        v11 = v12;
                    }
                    Point point1 = zzkw0.zzd(v13, v11);
                    Point point2 = point1;
                    if(zzkw0.zza(point1.x, point1.y, ((double)zzgz0.zzafr))) {
                        point0 = point2;
                        break;
                    }
                }
                if(point0 != null) {
                    v6 = Math.max(v6, point0.x);
                    v3 = Math.max(v3, point0.y);
                    v4 = Math.max(v4, zzpa.zza(zzgz0.zzafn, v6, v3));
                    Log.w("MediaCodecVideoRenderer", "Codec max resolution adjusted to: " + v6 + "x" + v3);
                }
            }
            zzpc0 = new zzpc(v6, v3, v4);
        }
        this.zzbia = zzpc0;
        zzpc zzpc1 = this.zzbia;
        boolean z1 = this.zzbhx;
        int v14 = this.zzago;
        MediaFormat mediaFormat0 = zzgz0.zzev();
        mediaFormat0.setInteger("max-width", zzpc1.width);
        mediaFormat0.setInteger("max-height", zzpc1.height);
        if(zzpc1.zzbix != -1) {
            mediaFormat0.setInteger("max-input-size", zzpc1.zzbix);
        }
        if(z1) {
            mediaFormat0.setInteger("auto-frc", 0);
        }
        if(v14 != 0) {
            mediaFormat0.setFeatureEnabled("tunneled-playback", true);
            mediaFormat0.setInteger("audio-session-id", v14);
        }
        if(this.zzbib == null) {
            zzob.checkState(this.zzn(zzkw0.zzazi));
            if(this.zzbic == null) {
                this.zzbic = zzow.zzc(this.zzur, zzkw0.zzazi);
            }
            this.zzbib = this.zzbic;
        }
        mediaCodec0.configure(mediaFormat0, this.zzbib, null, 0);
        if(this.zzajz) {
            this.zzbit = new zzpb(this, mediaCodec0, null);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzgj
    protected final void zza(zzgz[] arr_zzgz, long v) throws zzgk {
        this.zzbhz = arr_zzgz;
        if(this.zzbiu == 0x8000000000000001L) {
            this.zzbiu = v;
        }
        else {
            int v1 = this.zzbiv;
            long[] arr_v = this.zzbhy;
            if(v1 == arr_v.length) {
                Log.w("MediaCodecVideoRenderer", "Too many stream changes, so dropping offset: " + arr_v[v1 - 1]);
            }
            else {
                this.zzbiv = v1 + 1;
            }
            this.zzbhy[this.zzbiv - 1] = v;
        }
        super.zza(arr_zzgz, v);
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final boolean zza(long v, long v1, MediaCodec mediaCodec0, ByteBuffer byteBuffer0, int v2, int v3, long v4, boolean z) {
        int v5;
        while((v5 = this.zzbiv) != 0) {
            long[] arr_v = this.zzbhy;
            if(v4 < arr_v[0]) {
                break;
            }
            this.zzbiu = arr_v[0];
            this.zzbiv = v5 - 1;
            System.arraycopy(arr_v, 1, arr_v, 0, this.zzbiv);
        }
        long v6 = v4 - this.zzbiu;
        if(z) {
            this.zza(mediaCodec0, v2, v6);
            return true;
        }
        long v7 = v4 - v;
        if(this.zzbib == this.zzbic) {
            if(zzpa.zzem(v7)) {
                this.zza(mediaCodec0, v2, v6);
                return true;
            }
            return false;
        }
        if(!this.zzbie) {
            this.zza(mediaCodec0, v2, v6, System.nanoTime());
            return true;
        }
        if(this.getState() != 2) {
            return false;
        }
        long v8 = SystemClock.elapsedRealtime();
        long v9 = System.nanoTime();
        long v10 = this.zzbht.zzf(v4, (v7 - (v8 * 1000L - v1)) * 1000L + v9);
        long v11 = (v10 - v9) / 1000L;
        if(zzpa.zzem(v11)) {
            zzoq.beginSection("dropVideoBuffer");
            mediaCodec0.releaseOutputBuffer(v2, false);
            zzoq.endSection();
            ++this.zzazg.zzamo;
            ++this.zzbih;
            ++this.zzbii;
            this.zzazg.zzamp = Math.max(this.zzbii, this.zzazg.zzamp);
            if(this.zzbih == this.zzbhw) {
                this.zzjk();
            }
            return true;
        }
        if(v11 < 50000L) {
            this.zza(mediaCodec0, v2, v6, v10);
            return true;
        }
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final boolean zza(MediaCodec mediaCodec0, boolean z, zzgz zzgz0, zzgz zzgz1) {
        return zzpa.zza(z, zzgz0, zzgz1) && zzgz1.width <= this.zzbia.width && zzgz1.height <= this.zzbia.height && zzgz1.zzafo <= this.zzbia.zzbix;
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final boolean zza(zzkw zzkw0) {
        return this.zzbib != null || this.zzn(zzkw0.zzazi);
    }

    private final void zzb(MediaCodec mediaCodec0, int v, long v1) {
        this.zzji();
        zzoq.beginSection("releaseOutputBuffer");
        mediaCodec0.releaseOutputBuffer(v, true);
        zzoq.endSection();
        ++this.zzazg.zzamm;
        this.zzbii = 0;
        this.zzjg();
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void zzc(String s, long v, long v1) {
        this.zzbhu.zza(s, v, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void zzd(zzgz zzgz0) throws zzgk {
        super.zzd(zzgz0);
        this.zzbhu.zzb(zzgz0);
        this.zzbik = zzgz0.zzaft == -1.0f ? 1.0f : zzgz0.zzaft;
        this.zzbij = zzpa.zzj(zzgz0);
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void zze(boolean z) throws zzgk {
        super.zze(z);
        this.zzago = this.zzed().zzago;
        this.zzajz = this.zzago != 0;
        this.zzbhu.zza(this.zzazg);
        this.zzbht.enable();
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void zzec() {
        this.zzbil = -1;
        this.zzbim = -1;
        this.zzbio = -1.0f;
        this.zzbik = -1.0f;
        this.zzbiu = 0x8000000000000001L;
        this.zzbiv = 0;
        this.zzjh();
        this.zzjf();
        this.zzbht.disable();
        this.zzbit = null;
        this.zzajz = false;
        try {
            super.zzec();
        }
        finally {
            this.zzbhu.zzb(this.zzazg);
        }
    }

    private static boolean zzem(long v) {
        return v < -30000L;
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void zzhc() {
        try {
            super.zzhc();
            Surface surface0 = this.zzbic;
        }
        catch(Throwable throwable0) {
            Surface surface1 = this.zzbic;
            if(surface1 != null) {
                if(this.zzbib == surface1) {
                    this.zzbib = null;
                }
                this.zzbic.release();
                this.zzbic = null;
            }
            throw throwable0;
        }
        if(surface0 != null) {
            if(this.zzbib == surface0) {
                this.zzbib = null;
            }
            this.zzbic.release();
            this.zzbic = null;
        }
    }

    private static int zzi(zzgz zzgz0) {
        return zzgz0.zzafo == -1 ? zzpa.zza(zzgz0.zzafn, zzgz0.width, zzgz0.height) : zzgz0.zzafo;
    }

    private static int zzj(zzgz zzgz0) {
        return zzgz0.zzafs == -1 ? 0 : zzgz0.zzafs;
    }

    private final void zzje() {
        this.zzbif = this.zzbhv <= 0L ? 0x8000000000000001L : SystemClock.elapsedRealtime() + this.zzbhv;
    }

    private final void zzjf() {
        this.zzbie = false;
        if(this.zzajz) {
            MediaCodec mediaCodec0 = this.zzha();
            if(mediaCodec0 != null) {
                this.zzbit = new zzpb(this, mediaCodec0, null);
            }
        }
    }

    final void zzjg() {
        if(!this.zzbie) {
            this.zzbie = true;
            this.zzbhu.zza(this.zzbib);
        }
    }

    private final void zzjh() {
        this.zzbip = -1;
        this.zzbiq = -1;
        this.zzbis = -1.0f;
        this.zzbir = -1;
    }

    private final void zzji() {
        if(this.zzbip != this.zzbil || this.zzbiq != this.zzbim || this.zzbir != this.zzbin || this.zzbis != this.zzbio) {
            this.zzbhu.zza(this.zzbil, this.zzbim, this.zzbin, this.zzbio);
            this.zzbip = this.zzbil;
            this.zzbiq = this.zzbim;
            this.zzbir = this.zzbin;
            this.zzbis = this.zzbio;
        }
    }

    private final void zzjj() {
        if(this.zzbip != -1 || this.zzbiq != -1) {
            this.zzbhu.zza(this.zzbil, this.zzbim, this.zzbin, this.zzbio);
        }
    }

    private final void zzjk() {
        if(this.zzbih > 0) {
            long v = SystemClock.elapsedRealtime();
            this.zzbhu.zze(this.zzbih, v - this.zzbig);
            this.zzbih = 0;
            this.zzbig = v;
        }
    }

    // 去混淆评级： 低(30)
    private final boolean zzn(boolean z) {
        return !this.zzajz && (!z || zzow.zzd(this.zzur));
    }
}

