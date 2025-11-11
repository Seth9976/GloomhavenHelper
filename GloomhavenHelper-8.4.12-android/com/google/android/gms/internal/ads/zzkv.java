package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodec.CryptoException;
import android.media.MediaCodec.CryptoInfo;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.RequiresApi;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@TargetApi(19)
@RequiresApi(19)
public abstract class zzkv extends zzgj {
    private zzgz zzagi;
    private ByteBuffer[] zzajq;
    private static final byte[] zzaxw;
    private final zzkx zzaxx;
    private final zziz zzaxy;
    private final boolean zzaxz;
    private final zziv zzaya;
    private final zziv zzayb;
    private final zzhb zzayc;
    private final List zzayd;
    private final MediaCodec.BufferInfo zzaye;
    private zzix zzayf;
    private zzix zzayg;
    private MediaCodec zzayh;
    private zzkw zzayi;
    private boolean zzayj;
    private boolean zzayk;
    private boolean zzayl;
    private boolean zzaym;
    private boolean zzayn;
    private boolean zzayo;
    private boolean zzayp;
    private boolean zzayq;
    private boolean zzayr;
    private ByteBuffer[] zzays;
    private long zzayt;
    private int zzayu;
    private int zzayv;
    private boolean zzayw;
    private boolean zzayx;
    private int zzayy;
    private int zzayz;
    private boolean zzaza;
    private boolean zzazb;
    private boolean zzazc;
    private boolean zzazd;
    private boolean zzaze;
    private boolean zzazf;
    protected zzis zzazg;

    static {
        zzkv.zzaxw = new byte[]{0, 0, 1, 103, 66, (byte)0xC0, 11, -38, 37, (byte)0x90, 0, 0, 1, 104, -50, 15, 19, 0x20, 0, 0, 1, 101, -120, (byte)0x84, 13, -50, 0x71, 24, (byte)0xA0, 0, 0x2F, -65, 28, 49, -61, 39, 93, 120};
    }

    public zzkv(int v, zzkx zzkx0, zziz zziz0, boolean z) {
        super(v);
        zzob.checkState(true);
        this.zzaxx = (zzkx)zzob.checkNotNull(zzkx0);
        this.zzaxy = zziz0;
        this.zzaxz = z;
        this.zzaya = new zziv(0);
        this.zzayb = new zziv(0);
        this.zzayc = new zzhb();
        this.zzayd = new ArrayList();
        this.zzaye = new MediaCodec.BufferInfo();
        this.zzayy = 0;
        this.zzayz = 0;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzhe
    public boolean isReady() {
        return this.zzagi != null && !this.zzaze && (this.zzee() || this.zzayv >= 0 || this.zzayt != 0x8000000000000001L && SystemClock.elapsedRealtime() < this.zzayt);
    }

    protected void onOutputFormatChanged(MediaCodec mediaCodec0, MediaFormat mediaFormat0) throws zzgk {
    }

    @Override  // com.google.android.gms.internal.ads.zzgj
    protected void onStarted() {
    }

    @Override  // com.google.android.gms.internal.ads.zzgj
    protected void onStopped() {
    }

    private final void zza(zzky zzky0) throws zzgk {
        throw zzgk.zza(zzky0, this.getIndex());
    }

    @Override  // com.google.android.gms.internal.ads.zzhh
    public final int zza(zzgz zzgz0) throws zzgk {
        try {
            return this.zza(this.zzaxx, zzgz0);
        }
        catch(zzld zzld0) {
            throw zzgk.zza(zzld0, this.getIndex());
        }
    }

    protected abstract int zza(zzkx arg1, zzgz arg2) throws zzld;

    protected zzkw zza(zzkx zzkx0, zzgz zzgz0, boolean z) throws zzld {
        return zzkx0.zzb(zzgz0.zzafn, z);
    }

    @Override  // com.google.android.gms.internal.ads.zzgj
    protected void zza(long v, boolean z) throws zzgk {
        this.zzazc = false;
        this.zzazd = false;
        if(this.zzayh != null) {
            this.zzayt = 0x8000000000000001L;
            this.zzayu = -1;
            this.zzayv = -1;
            this.zzazf = true;
            this.zzaze = false;
            this.zzayw = false;
            this.zzayd.clear();
            this.zzayq = false;
            this.zzayr = false;
            if(this.zzayk || this.zzayn && this.zzazb) {
                this.zzhc();
                this.zzgz();
            }
            else if(this.zzayz != 0) {
                this.zzhc();
                this.zzgz();
            }
            else {
                this.zzayh.flush();
                this.zzaza = false;
            }
            if(this.zzayx && this.zzagi != null) {
                this.zzayy = 1;
            }
        }
    }

    protected void zza(zziv zziv0) {
    }

    protected abstract void zza(zzkw arg1, MediaCodec arg2, zzgz arg3, MediaCrypto arg4) throws zzld;

    protected abstract boolean zza(long arg1, long arg2, MediaCodec arg3, ByteBuffer arg4, int arg5, int arg6, long arg7, boolean arg8) throws zzgk;

    protected boolean zza(MediaCodec mediaCodec0, boolean z, zzgz zzgz0, zzgz zzgz1) {
        return false;
    }

    protected boolean zza(zzkw zzkw0) {
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public final void zzb(long v, long v1) throws zzgk {
        if(this.zzazd) {
            this.zzfz();
            return;
        }
        if(this.zzagi == null) {
            this.zzayb.clear();
            int v2 = this.zza(this.zzayc, this.zzayb, true);
            if(v2 == -5) {
                this.zzd(this.zzayc.zzagi);
                goto label_15;
            }
            if(v2 == -4) {
                zzob.checkState(this.zzayb.zzgd());
                this.zzazc = true;
                this.zzhe();
                return;
            }
            return;
        }
    label_15:
        this.zzgz();
        if(this.zzayh != null) {
            zzoq.beginSection("drainAndFeed");
            while(this.zzd(v, v1)) {
            }
            while(this.zzhd()) {
            }
            zzoq.endSection();
            return;
        }
        this.zzdn(v);
        this.zzayb.clear();
        int v3 = this.zza(this.zzayc, this.zzayb, false);
        if(v3 == -5) {
            this.zzd(this.zzayc.zzagi);
            return;
        }
        if(v3 == -4) {
            zzob.checkState(this.zzayb.zzgd());
            this.zzazc = true;
            this.zzhe();
        }
    }

    protected void zzc(String s, long v, long v1) {
    }

    private final boolean zzd(long v, long v1) throws zzgk {
        boolean z1;
        boolean z;
        if(this.zzayv < 0) {
            if(!this.zzayo || !this.zzazb) {
                this.zzayv = this.zzayh.dequeueOutputBuffer(this.zzaye, 0L);
            }
            else {
                try {
                    this.zzayv = this.zzayh.dequeueOutputBuffer(this.zzaye, 0L);
                }
                catch(IllegalStateException unused_ex) {
                    this.zzhe();
                    if(this.zzazd) {
                        this.zzhc();
                    }
                    return false;
                }
            }
            int v2 = this.zzayv;
            if(v2 >= 0) {
                if(this.zzayr) {
                    this.zzayr = false;
                    this.zzayh.releaseOutputBuffer(v2, false);
                    this.zzayv = -1;
                    return true;
                }
                if((this.zzaye.flags & 4) != 0) {
                    this.zzhe();
                    this.zzayv = -1;
                    return false;
                }
                ByteBuffer byteBuffer0 = this.zzajq[this.zzayv];
                if(byteBuffer0 != null) {
                    byteBuffer0.position(this.zzaye.offset);
                    byteBuffer0.limit(this.zzaye.offset + this.zzaye.size);
                }
                long v3 = this.zzaye.presentationTimeUs;
                int v4 = this.zzayd.size();
                for(int v5 = 0; true; ++v5) {
                    z = false;
                    if(v5 >= v4) {
                        break;
                    }
                    if(((long)(((Long)this.zzayd.get(v5)))) == v3) {
                        this.zzayd.remove(v5);
                        z = true;
                        break;
                    }
                }
                this.zzayw = z;
                goto label_52;
            }
            switch(v2) {
                case -3: {
                    this.zzajq = this.zzayh.getOutputBuffers();
                    return true;
                }
                case -2: {
                    MediaFormat mediaFormat0 = this.zzayh.getOutputFormat();
                    if(this.zzayl && mediaFormat0.getInteger("width") == 0x20 && mediaFormat0.getInteger("height") == 0x20) {
                        this.zzayr = true;
                        return true;
                    }
                    if(this.zzayp) {
                        mediaFormat0.setInteger("channel-count", 1);
                    }
                    this.onOutputFormatChanged(this.zzayh, mediaFormat0);
                    return true;
                }
                default: {
                    if(this.zzaym && (this.zzazc || this.zzayz == 2)) {
                        this.zzhe();
                    }
                    return false;
                }
            }
        }
    label_52:
        if(!this.zzayo || !this.zzazb) {
            z1 = this.zza(v, v1, this.zzayh, this.zzajq[this.zzayv], this.zzayv, this.zzaye.flags, this.zzaye.presentationTimeUs, this.zzayw);
        }
        else {
            try {
                z1 = this.zza(v, v1, this.zzayh, this.zzajq[this.zzayv], this.zzayv, this.zzaye.flags, this.zzaye.presentationTimeUs, this.zzayw);
            }
            catch(IllegalStateException unused_ex) {
                this.zzhe();
                if(this.zzazd) {
                    this.zzhc();
                }
                return false;
            }
        }
        if(z1) {
            this.zzayv = -1;
            return true;
        }
        return false;
    }

    protected void zzd(zzgz zzgz0) throws zzgk {
        zzgz zzgz1 = this.zzagi;
        this.zzagi = zzgz0;
        boolean z = true;
        if(!zzop.zza(this.zzagi.zzafq, (zzgz1 == null ? null : zzgz1.zzafq)) != 0) {
            if(this.zzagi.zzafq == null) {
                this.zzayg = null;
            }
            else {
                zziz zziz0 = this.zzaxy;
                if(zziz0 == null) {
                    throw zzgk.zza(new IllegalStateException("Media requires a DrmSessionManager"), this.getIndex());
                }
                this.zzayg = zziz0.zza(Looper.myLooper(), this.zzagi.zzafq);
                zzix zzix0 = this.zzayg;
                if(zzix0 == this.zzayf) {
                    this.zzaxy.zza(zzix0);
                }
            }
        }
        if(this.zzayg == this.zzayf && (this.zzayh != null && this.zza(this.zzayh, this.zzayi.zzazh, zzgz1, this.zzagi))) {
            this.zzayx = true;
            this.zzayy = 1;
            if(!this.zzayl || this.zzagi.width != zzgz1.width || this.zzagi.height != zzgz1.height) {
                z = false;
            }
            this.zzayq = z;
            return;
        }
        if(this.zzaza) {
            this.zzayz = 1;
            return;
        }
        this.zzhc();
        this.zzgz();
    }

    @Override  // com.google.android.gms.internal.ads.zzgj
    protected void zze(boolean z) throws zzgk {
        this.zzazg = new zzis();
    }

    @Override  // com.google.android.gms.internal.ads.zzgj
    public final int zzeb() {
        return 4;
    }

    @Override  // com.google.android.gms.internal.ads.zzgj
    protected void zzec() {
        try {
            this.zzagi = null;
            this.zzhc();
        }
        catch(Throwable throwable0) {
            try {
                if(this.zzayf != null) {
                    this.zzaxy.zza(this.zzayf);
                }
            }
            catch(Throwable throwable1) {
                if(this.zzayg != null && this.zzayg != this.zzayf) {
                    try {
                        this.zzaxy.zza(this.zzayg);
                    }
                    catch(Throwable throwable2) {
                        this.zzayf = null;
                        this.zzayg = null;
                        throw throwable2;
                    }
                }
                this.zzayf = null;
                this.zzayg = null;
                throw throwable1;
            }
            if(this.zzayg != null && this.zzayg != this.zzayf) {
                try {
                    this.zzaxy.zza(this.zzayg);
                }
                catch(Throwable throwable3) {
                    this.zzayf = null;
                    this.zzayg = null;
                    throw throwable3;
                }
            }
            this.zzayf = null;
            this.zzayg = null;
            throw throwable0;
        }
        try {
            if(this.zzayf != null) {
                this.zzaxy.zza(this.zzayf);
            }
        }
        catch(Throwable throwable4) {
            if(this.zzayg != null && this.zzayg != this.zzayf) {
                try {
                    this.zzaxy.zza(this.zzayg);
                }
                catch(Throwable throwable5) {
                    this.zzayf = null;
                    this.zzayg = null;
                    throw throwable5;
                }
            }
            this.zzayf = null;
            this.zzayg = null;
            throw throwable4;
        }
        try {
            if(this.zzayg != null && this.zzayg != this.zzayf) {
                this.zzaxy.zza(this.zzayg);
            }
            this.zzayf = null;
            this.zzayg = null;
        }
        catch(Throwable throwable6) {
            this.zzayf = null;
            this.zzayg = null;
            throw throwable6;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzhe
    public boolean zzez() {
        return this.zzazd;
    }

    protected void zzfz() throws zzgk {
    }

    protected final void zzgz() throws zzgk {
        String s;
        if(this.zzayh == null && this.zzagi != null) {
            this.zzayf = this.zzayg;
            zzix zzix0 = this.zzayf;
            if(zzix0 != null) {
                switch(zzix0.getState()) {
                    case 0: {
                        throw zzgk.zza(this.zzayf.zzgl(), this.getIndex());
                    }
                    case 3: 
                    case 4: {
                        zzjb zzjb0 = (zzjb)this.zzayf.zzgk();
                        throw new NoSuchMethodError();
                    }
                    default: {
                        return;
                    }
                }
            }
            if(this.zzayi == null) {
                try {
                    this.zzayi = this.zza(this.zzaxx, this.zzagi, false);
                }
                catch(zzld zzld0) {
                    this.zza(new zzky(this.zzagi, zzld0, false, 0xFFFF3CB2));
                }
                if(this.zzayi == null) {
                    this.zza(new zzky(this.zzagi, null, false, 0xFFFF3CB1));
                }
            }
            if(!this.zza(this.zzayi)) {
                return;
            }
            try {
                s = this.zzayi.name;
                this.zzayj = false;
                this.zzayk = false;
                this.zzayl = false;
                this.zzaym = false;
                this.zzayn = false;
                this.zzayo = false;
                this.zzayp = false;
                long v = SystemClock.elapsedRealtime();
                String s1 = String.valueOf(s);
                zzoq.beginSection((s1.length() == 0 ? new String("createCodec:") : "createCodec:" + s1));
                this.zzayh = MediaCodec.createByCodecName(s);
                zzoq.endSection();
                zzoq.beginSection("configureCodec");
                this.zza(this.zzayi, this.zzayh, this.zzagi, null);
                zzoq.endSection();
                zzoq.beginSection("startCodec");
                this.zzayh.start();
                zzoq.endSection();
                long v1 = SystemClock.elapsedRealtime();
                this.zzc(s, v1, v1 - v);
                this.zzays = this.zzayh.getInputBuffers();
                this.zzajq = this.zzayh.getOutputBuffers();
            }
            catch(Exception exception0) {
                this.zza(new zzky(this.zzagi, exception0, false, s));
            }
            this.zzayt = this.getState() == 2 ? SystemClock.elapsedRealtime() + 1000L : 0x8000000000000001L;
            this.zzayu = -1;
            this.zzayv = -1;
            this.zzazf = true;
            ++this.zzazg.zzamj;
        }
    }

    protected final MediaCodec zzha() {
        return this.zzayh;
    }

    protected final zzkw zzhb() {
        return this.zzayi;
    }

    protected void zzhc() {
        zzix zzix2;
        zzix zzix0;
        this.zzayt = 0x8000000000000001L;
        this.zzayu = -1;
        this.zzayv = -1;
        this.zzaze = false;
        this.zzayw = false;
        this.zzayd.clear();
        this.zzays = null;
        this.zzajq = null;
        this.zzayi = null;
        this.zzayx = false;
        this.zzaza = false;
        this.zzayj = false;
        this.zzayk = false;
        this.zzayl = false;
        this.zzaym = false;
        this.zzayn = false;
        this.zzayp = false;
        this.zzayq = false;
        this.zzayr = false;
        this.zzazb = false;
        this.zzayy = 0;
        this.zzayz = 0;
        this.zzaya.zzcs = null;
        if(this.zzayh != null) {
            try {
                ++this.zzazg.zzamk;
                this.zzayh.stop();
            }
            catch(Throwable throwable0) {
                try {
                    this.zzayh.release();
                    this.zzayh = null;
                    zzix0 = this.zzayf;
                }
                catch(Throwable throwable1) {
                    this.zzayh = null;
                    zzix zzix1 = this.zzayf;
                    if(zzix1 != null && this.zzayg != zzix1) {
                        try {
                            this.zzaxy.zza(zzix1);
                            this.zzayf = null;
                        }
                        catch(Throwable throwable2) {
                            this.zzayf = null;
                            throw throwable2;
                        }
                    }
                    throw throwable1;
                }
                if(zzix0 != null && this.zzayg != zzix0) {
                    try {
                        this.zzaxy.zza(zzix0);
                        this.zzayf = null;
                    }
                    catch(Throwable throwable3) {
                        this.zzayf = null;
                        throw throwable3;
                    }
                }
                throw throwable0;
            }
            try {
                this.zzayh.release();
                this.zzayh = null;
                zzix2 = this.zzayf;
            }
            catch(Throwable throwable4) {
                this.zzayh = null;
                zzix zzix3 = this.zzayf;
                if(zzix3 != null && this.zzayg != zzix3) {
                    try {
                        this.zzaxy.zza(zzix3);
                        this.zzayf = null;
                    }
                    catch(Throwable throwable5) {
                        this.zzayf = null;
                        throw throwable5;
                    }
                }
                throw throwable4;
            }
            if(zzix2 != null && this.zzayg != zzix2) {
                try {
                    this.zzaxy.zza(zzix2);
                    this.zzayf = null;
                }
                catch(Throwable throwable6) {
                    this.zzayf = null;
                    throw throwable6;
                }
            }
        }
    }

    private final boolean zzhd() throws zzgk {
        boolean z1;
        int v2;
        int v1;
        MediaCodec mediaCodec0 = this.zzayh;
        if(mediaCodec0 != null && this.zzayz != 2 && !this.zzazc) {
            if(this.zzayu < 0) {
                this.zzayu = mediaCodec0.dequeueInputBuffer(0L);
                int v = this.zzayu;
                if(v < 0) {
                    return false;
                }
                this.zzaya.zzcs = this.zzays[v];
                this.zzaya.clear();
            }
            if(this.zzayz == 1) {
                if(!this.zzaym) {
                    this.zzazb = true;
                    this.zzayh.queueInputBuffer(this.zzayu, 0, 0, 0L, 4);
                    this.zzayu = -1;
                }
                this.zzayz = 2;
                return false;
            }
            if(this.zzayq) {
                this.zzayq = false;
                this.zzaya.zzcs.put(zzkv.zzaxw);
                this.zzayh.queueInputBuffer(this.zzayu, 0, zzkv.zzaxw.length, 0L, 0);
                this.zzayu = -1;
                this.zzaza = true;
                return true;
            }
            if(this.zzaze) {
                v1 = -4;
                v2 = 0;
            }
            else {
                if(this.zzayy == 1) {
                    for(int v3 = 0; v3 < this.zzagi.zzafp.size(); ++v3) {
                        byte[] arr_b = (byte[])this.zzagi.zzafp.get(v3);
                        this.zzaya.zzcs.put(arr_b);
                    }
                    this.zzayy = 2;
                }
                v2 = this.zzaya.zzcs.position();
                v1 = this.zza(this.zzayc, this.zzaya, false);
            }
            switch(v1) {
                case -5: {
                    if(this.zzayy == 2) {
                        this.zzaya.clear();
                        this.zzayy = 1;
                    }
                    this.zzd(this.zzayc.zzagi);
                    return true;
                }
                case -3: {
                    return false;
                }
                default: {
                    if(this.zzaya.zzgd()) {
                        if(this.zzayy == 2) {
                            this.zzaya.clear();
                            this.zzayy = 1;
                        }
                        this.zzazc = true;
                        if(!this.zzaza) {
                            this.zzhe();
                            return false;
                        }
                        try {
                            if(!this.zzaym) {
                                this.zzazb = true;
                                this.zzayh.queueInputBuffer(this.zzayu, 0, 0, 0L, 4);
                                this.zzayu = -1;
                            }
                            return false;
                        }
                        catch(MediaCodec.CryptoException mediaCodec$CryptoException0) {
                            throw zzgk.zza(mediaCodec$CryptoException0, this.getIndex());
                        }
                    }
                    if(this.zzazf && !this.zzaya.zzge()) {
                        this.zzaya.clear();
                        if(this.zzayy == 2) {
                            this.zzayy = 1;
                        }
                        return true;
                    }
                    this.zzazf = false;
                    boolean z = this.zzaya.zzgj();
                    zzix zzix0 = this.zzayf;
                    if(zzix0 == null) {
                        z1 = false;
                    }
                    else {
                        switch(zzix0.getState()) {
                            case 0: {
                                throw zzgk.zza(this.zzayf.zzgl(), this.getIndex());
                            }
                            case 4: {
                                z1 = false;
                                break;
                            }
                            default: {
                                z1 = !z && this.zzaxz ? false : true;
                                break;
                            }
                        }
                    }
                    this.zzaze = z1;
                    if(this.zzaze) {
                        return false;
                    }
                    if(this.zzayj && !z) {
                        zzoh.zzk(this.zzaya.zzcs);
                        if(this.zzaya.zzcs.position() == 0) {
                            return true;
                        }
                        this.zzayj = false;
                    }
                    try {
                        long v4 = this.zzaya.zzamu;
                        if(this.zzaya.zzgc()) {
                            this.zzayd.add(v4);
                        }
                        this.zzaya.zzcs.flip();
                        this.zza(this.zzaya);
                        if(z) {
                            MediaCodec.CryptoInfo mediaCodec$CryptoInfo0 = this.zzaya.zzamt.zzgh();
                            if(v2 != 0) {
                                if(mediaCodec$CryptoInfo0.numBytesOfClearData == null) {
                                    mediaCodec$CryptoInfo0.numBytesOfClearData = new int[1];
                                }
                                mediaCodec$CryptoInfo0.numBytesOfClearData[0] += v2;
                            }
                            this.zzayh.queueSecureInputBuffer(this.zzayu, 0, mediaCodec$CryptoInfo0, v4, 0);
                        }
                        else {
                            this.zzayh.queueInputBuffer(this.zzayu, 0, this.zzaya.zzcs.limit(), v4, 0);
                        }
                        this.zzayu = -1;
                        this.zzaza = true;
                        this.zzayy = 0;
                        ++this.zzazg.zzaml;
                        return true;
                    }
                    catch(MediaCodec.CryptoException mediaCodec$CryptoException1) {
                        throw zzgk.zza(mediaCodec$CryptoException1, this.getIndex());
                    }
                }
            }
        }
        return false;
    }

    private final void zzhe() throws zzgk {
        if(this.zzayz == 2) {
            this.zzhc();
            this.zzgz();
            return;
        }
        this.zzazd = true;
        this.zzfz();
    }
}

