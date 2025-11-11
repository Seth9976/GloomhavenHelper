package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import java.nio.ByteBuffer;

@TargetApi(16)
public final class zzii extends zzkv implements zzof {
    private int zzafx;
    private int zzafz;
    private final zzhq zzakv;
    private final zzhz zzakw;
    private boolean zzakx;
    private boolean zzaky;
    private MediaFormat zzakz;
    private long zzala;
    private boolean zzalb;

    public zzii(zzkx zzkx0) {
        this(zzkx0, null, true);
    }

    private zzii(zzkx zzkx0, zziz zziz0, boolean z) {
        this(zzkx0, null, true, null, null);
    }

    private zzii(zzkx zzkx0, zziz zziz0, boolean z, zzdkp zzdkp0, zzhr zzhr0) {
        this(zzkx0, null, true, null, null, null, new zzhp[0]);
    }

    private zzii(zzkx zzkx0, zziz zziz0, boolean z, zzdkp zzdkp0, zzhr zzhr0, zzhm zzhm0, zzhp[] arr_zzhp) {
        super(1, zzkx0, zziz0, z);
        this.zzakw = new zzhz(null, arr_zzhp, new zzik(this, null));
        this.zzakv = new zzhq(null, null);
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzkv
    public final boolean isReady() {
        return this.zzakw.zzfm() || super.isReady();
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void onOutputFormatChanged(MediaCodec mediaCodec0, MediaFormat mediaFormat0) throws zzgk {
        int[] arr_v1;
        boolean z = this.zzakz != null;
        String s = z ? this.zzakz.getString("mime") : "audio/raw";
        if(z) {
            mediaFormat0 = this.zzakz;
        }
        int v1 = mediaFormat0.getInteger("channel-count");
        int v2 = mediaFormat0.getInteger("sample-rate");
        if(!this.zzaky || v1 != 6) {
            arr_v1 = null;
        }
        else {
            int v3 = this.zzafx;
            if(v3 < 6) {
                int[] arr_v = new int[v3];
                for(int v = 0; v < this.zzafx; ++v) {
                    arr_v[v] = v;
                }
                arr_v1 = arr_v;
            }
            else {
                arr_v1 = null;
            }
        }
        try {
            this.zzakw.zza(s, v1, v2, this.zzafz, 0, arr_v1);
        }
        catch(zzid zzid0) {
            throw zzgk.zza(zzid0, this.getIndex());
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void onStarted() {
        super.onStarted();
        this.zzakw.play();
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void onStopped() {
        this.zzakw.pause();
        super.onStopped();
    }

    static zzhq zza(zzii zzii0) {
        return zzii0.zzakv;
    }

    static boolean zza(zzii zzii0, boolean z) {
        zzii0.zzalb = true;
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final int zza(zzkx zzkx0, zzgz zzgz0) throws zzld {
        int v = 3;
        String s = zzgz0.zzafn;
        if(!zzoi.zzbi(s)) {
            return 0;
        }
        if(this.zzbd(s) && zzkx0.zzhg() != null) {
            return 23;
        }
        zzkw zzkw0 = zzkx0.zzb(s, false);
        if(zzkw0 == null) {
            return 1;
        }
        if(zzgz0.zzafy != -1 && !zzkw0.zzaq(zzgz0.zzafy) || zzgz0.zzafx != -1 && !zzkw0.zzar(zzgz0.zzafx)) {
            v = 2;
        }
        return 20 | v;
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final zzkw zza(zzkx zzkx0, zzgz zzgz0, boolean z) throws zzld {
        if(this.zzbd(zzgz0.zzafn)) {
            zzkw zzkw0 = zzkx0.zzhg();
            if(zzkw0 != null) {
                this.zzakx = true;
                return zzkw0;
            }
        }
        this.zzakx = false;
        return super.zza(zzkx0, zzgz0, z);
    }

    @Override  // com.google.android.gms.internal.ads.zzgj
    public final void zza(int v, Object object0) throws zzgk {
        switch(v) {
            case 2: {
                this.zzakw.setVolume(((float)(((Float)object0))));
                return;
            }
            case 3: {
                this.zzakw.setStreamType(((int)(((Integer)object0))));
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
        this.zzakw.reset();
        this.zzala = v;
        this.zzalb = true;
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void zza(zzkw zzkw0, MediaCodec mediaCodec0, zzgz zzgz0, MediaCrypto mediaCrypto0) {
        this.zzaky = false;
        if(this.zzakx) {
            this.zzakz = zzgz0.zzev();
            this.zzakz.setString("mime", "audio/raw");
            mediaCodec0.configure(this.zzakz, null, null, 0);
            this.zzakz.setString("mime", zzgz0.zzafn);
            return;
        }
        mediaCodec0.configure(zzgz0.zzev(), null, null, 0);
        this.zzakz = null;
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final boolean zza(long v, long v1, MediaCodec mediaCodec0, ByteBuffer byteBuffer0, int v2, int v3, long v4, boolean z) throws zzgk {
        if(this.zzakx && (v3 & 2) != 0) {
            mediaCodec0.releaseOutputBuffer(v2, false);
            return true;
        }
        if(z) {
            mediaCodec0.releaseOutputBuffer(v2, false);
            ++this.zzazg.zzamn;
            this.zzakw.zzfj();
            return true;
        }
        try {
            if(this.zzakw.zza(byteBuffer0, v4)) {
                mediaCodec0.releaseOutputBuffer(v2, false);
                ++this.zzazg.zzamm;
                return true;
            }
            return false;
        }
        catch(zzic | zzih zzic0) {
            throw zzgk.zza(zzic0, this.getIndex());
        }
    }

    protected static void zzb(int v, long v1, long v2) {
    }

    @Override  // com.google.android.gms.internal.ads.zzof
    public final zzhf zzb(zzhf zzhf0) {
        return this.zzakw.zzb(zzhf0);
    }

    private final boolean zzbd(String s) {
        return this.zzakw.zzbb(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void zzc(String s, long v, long v1) {
        this.zzakv.zza(s, v, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void zzd(zzgz zzgz0) throws zzgk {
        super.zzd(zzgz0);
        this.zzakv.zzb(zzgz0);
        this.zzafz = "audio/raw".equals(zzgz0.zzafn) ? zzgz0.zzafz : 2;
        this.zzafx = zzgz0.zzafx;
    }

    @Override  // com.google.android.gms.internal.ads.zzgj
    public final zzof zzdv() {
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void zze(boolean z) throws zzgk {
        super.zze(z);
        this.zzakv.zza(this.zzazg);
        int v = this.zzed().zzago;
        if(v != 0) {
            this.zzakw.zzt(v);
            return;
        }
        this.zzakw.zzfo();
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void zzec() {
        try {
            this.zzakw.release();
        }
        catch(Throwable unused_ex) {
            try {
                super.zzec();
            }
            throw throwable0;
        }
        finally {
            this.zzakv.zzb(this.zzazg);
        }
        super.zzec();
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzkv
    public final boolean zzez() {
        return super.zzez() && this.zzakw.zzez();
    }

    @Override  // com.google.android.gms.internal.ads.zzof
    public final zzhf zzfn() {
        return this.zzakw.zzfn();
    }

    @Override  // com.google.android.gms.internal.ads.zzof
    public final long zzfx() {
        boolean z = this.zzez();
        long v = this.zzakw.zzj(z);
        if(v != 0x8000000000000000L) {
            if(!this.zzalb) {
                v = Math.max(this.zzala, v);
            }
            this.zzala = v;
            this.zzalb = false;
        }
        return this.zzala;
    }

    protected static void zzfy() {
    }

    @Override  // com.google.android.gms.internal.ads.zzkv
    protected final void zzfz() throws zzgk {
        try {
            this.zzakw.zzfk();
        }
        catch(zzih zzih0) {
            throw zzgk.zza(zzih0, this.getIndex());
        }
    }

    protected static void zzs(int v) {
    }
}

