package com.google.android.gms.internal.ads;

import android.media.AudioAttributes.Builder;
import android.media.AudioFormat.Builder;
import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.os.SystemClock;
import android.util.Log;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.LinkedList;

public final class zzhz {
    private int streamType;
    private zzhf zzadp;
    private int zzafy;
    private static boolean zzaia = false;
    private static boolean zzaib = false;
    private final zzhm zzaic;
    private final zzig zzaid;
    private final zzip zzaie;
    private final zzhp[] zzaif;
    private final zzif zzaig;
    private final ConditionVariable zzaih;
    private final long[] zzaii;
    private final zzib zzaij;
    private final LinkedList zzaik;
    private AudioTrack zzail;
    private int zzaim;
    private int zzain;
    private int zzaio;
    private boolean zzaip;
    private int zzaiq;
    private long zzair;
    private zzhf zzais;
    private long zzait;
    private long zzaiu;
    private ByteBuffer zzaiv;
    private int zzaiw;
    private int zzaix;
    private int zzaiy;
    private long zzaiz;
    private long zzaja;
    private boolean zzajb;
    private long zzajc;
    private Method zzajd;
    private int zzaje;
    private long zzajf;
    private long zzajg;
    private int zzajh;
    private long zzaji;
    private long zzajj;
    private int zzajk;
    private int zzajl;
    private long zzajm;
    private long zzajn;
    private long zzajo;
    private zzhp[] zzajp;
    private ByteBuffer[] zzajq;
    private ByteBuffer zzajr;
    private ByteBuffer zzajs;
    private byte[] zzajt;
    private int zzaju;
    private int zzajv;
    private boolean zzajw;
    private boolean zzajx;
    private int zzajy;
    private boolean zzajz;
    private boolean zzaka;
    private long zzakb;
    private float zzcy;

    static {
    }

    public zzhz(zzhm zzhm0, zzhp[] arr_zzhp, zzif zzif0) {
        this.zzaic = null;
        this.zzaig = zzif0;
        this.zzaih = new ConditionVariable(true);
        if(zzop.SDK_INT >= 18) {
            try {
                this.zzajd = AudioTrack.class.getMethod("getLatency", null);
            }
            catch(NoSuchMethodException unused_ex) {
            }
        }
        this.zzaij = zzop.SDK_INT >= 19 ? new zzia() : new zzib(null);
        this.zzaid = new zzig();
        this.zzaie = new zzip();
        this.zzaif = new zzhp[arr_zzhp.length + 3];
        this.zzaif[0] = new zzin();
        this.zzaif[1] = this.zzaid;
        System.arraycopy(arr_zzhp, 0, this.zzaif, 2, arr_zzhp.length);
        this.zzaif[arr_zzhp.length + 2] = this.zzaie;
        this.zzaii = new long[10];
        this.zzcy = 1.0f;
        this.zzajl = 0;
        this.streamType = 3;
        this.zzajy = 0;
        this.zzadp = zzhf.zzagj;
        this.zzajv = -1;
        this.zzajp = new zzhp[0];
        this.zzajq = new ByteBuffer[0];
        this.zzaik = new LinkedList();
    }

    private final boolean isInitialized() {
        return this.zzail != null;
    }

    public final void pause() {
        this.zzajx = false;
        if(this.isInitialized()) {
            this.zzfr();
            this.zzaij.pause();
        }
    }

    public final void play() {
        this.zzajx = true;
        if(this.isInitialized()) {
            this.zzajn = System.nanoTime() / 1000L;
            this.zzail.play();
        }
    }

    public final void release() {
        this.reset();
        zzhp[] arr_zzhp = this.zzaif;
        for(int v = 0; v < arr_zzhp.length; ++v) {
            arr_zzhp[v].reset();
        }
        this.zzajy = 0;
        this.zzajx = false;
    }

    public final void reset() {
        if(this.isInitialized()) {
            this.zzajf = 0L;
            this.zzajg = 0L;
            this.zzaji = 0L;
            this.zzajj = 0L;
            this.zzajk = 0;
            zzhf zzhf0 = this.zzais;
            if(zzhf0 != null) {
                this.zzadp = zzhf0;
                this.zzais = null;
            }
            else if(!this.zzaik.isEmpty()) {
                this.zzadp = ((zzie)this.zzaik.getLast()).zzadp;
            }
            this.zzaik.clear();
            this.zzait = 0L;
            this.zzaiu = 0L;
            this.zzajr = null;
            this.zzajs = null;
            for(int v = 0; true; ++v) {
                zzhp[] arr_zzhp = this.zzajp;
                if(v >= arr_zzhp.length) {
                    break;
                }
                zzhp zzhp0 = arr_zzhp[v];
                zzhp0.flush();
                ByteBuffer[] arr_byteBuffer = this.zzajq;
                arr_byteBuffer[v] = zzhp0.zzfh();
            }
            this.zzajw = false;
            this.zzajv = -1;
            this.zzaiv = null;
            this.zzaiw = 0;
            this.zzajl = 0;
            this.zzajo = 0L;
            this.zzfr();
            if(this.zzail.getPlayState() == 3) {
                this.zzail.pause();
            }
            AudioTrack audioTrack0 = this.zzail;
            this.zzail = null;
            this.zzaij.zza(null, false);
            this.zzaih.close();
            new zzhy(this, audioTrack0).start();
        }
    }

    public final void setStreamType(int v) {
        if(this.streamType == v) {
            return;
        }
        this.streamType = v;
        if(this.zzajz) {
            return;
        }
        this.reset();
        this.zzajy = 0;
    }

    public final void setVolume(float f) {
        if(this.zzcy != f) {
            this.zzcy = f;
            this.zzfp();
        }
    }

    public final void zza(String s, int v, int v1, int v2, int v3, int[] arr_v) throws zzid {
        int v9;
        int v8;
        boolean z4;
        boolean z3;
        boolean z = "audio/raw".equals(s);
        boolean z1 = true;
        int v4 = !z == 0 ? v2 : zzhz.zzbc(s);
        if(!z == 0) {
            this.zzaje = zzop.zzg(v2, v);
            this.zzaid.zzb(arr_v);
            zzhp[] arr_zzhp = this.zzaif;
            int v5 = v4;
            int v6 = v;
            boolean z2 = false;
            for(int v7 = 0; v7 < arr_zzhp.length; ++v7) {
                zzhp zzhp0 = arr_zzhp[v7];
                try {
                    z3 = zzhp0.zzb(v1, v6, v5);
                }
                catch(zzho zzho0) {
                    throw new zzid(zzho0);
                }
                z2 |= z3;
                if(zzhp0.isActive()) {
                    v6 = zzhp0.zzfe();
                    v5 = zzhp0.zzff();
                }
            }
            if(z2) {
                this.zzfi();
            }
            z4 = z2;
            v = v6;
            v4 = v5;
        }
        else {
            z4 = false;
        }
        switch(v) {
            case 1: {
                v8 = 4;
                break;
            }
            case 2: {
                v8 = 12;
                break;
            }
            case 3: {
                v8 = 28;
                break;
            }
            case 4: {
                v8 = 204;
                break;
            }
            case 5: {
                v8 = 220;
                break;
            }
            case 6: {
                v8 = 0xFC;
                break;
            }
            case 7: {
                v8 = 0x4FC;
                break;
            }
            case 8: {
                v8 = zzgl.CHANNEL_OUT_7POINT1_SURROUND;
                break;
            }
            default: {
                throw new zzid("Unsupported channel count: " + v);
            }
        }
        if(!z4 && this.isInitialized() && this.zzain == v4 && this.zzafy == v1 && this.zzaim == v8) {
            return;
        }
        this.reset();
        this.zzain = v4;
        this.zzaip = !z;
        this.zzafy = v1;
        this.zzaim = v8;
        if(!z == 0) {
            v4 = 2;
        }
        this.zzaio = v4;
        this.zzajh = zzop.zzg(2, v);
        if(!z == 0) {
            int v10 = AudioTrack.getMinBufferSize(v1, v8, this.zzaio);
            if(v10 == -2) {
                z1 = false;
            }
            zzob.checkState(z1);
            int v11 = this.zzajh * ((int)this.zzdx(250000L));
            v9 = (int)Math.max(v10, this.zzdx(750000L) * ((long)this.zzajh));
            if(v10 << 2 < v11) {
                v9 = v11;
            }
            else if(v10 << 2 <= v9) {
                v9 = v10 << 2;
            }
        }
        else if(this.zzaio != 5 && this.zzaio != 6) {
            v9 = 0xC000;
        }
        else {
            v9 = 0x5000;
        }
        this.zzaiq = v9;
        this.zzair = !z == 0 ? this.zzdw(((long)(this.zzaiq / this.zzajh))) : 0x8000000000000001L;
        this.zzb(this.zzadp);
    }

    public final boolean zza(ByteBuffer byteBuffer0, long v) throws zzic, zzih {
        int v13;
        zzob.checkArgument(this.zzajr == null || byteBuffer0 == this.zzajr);
        if(!this.isInitialized()) {
            this.zzaih.block();
            if(this.zzajz) {
                int v1 = this.zzafy;
                int v2 = this.zzaim;
                int v3 = this.zzaio;
                int v4 = this.zzaiq;
                int v5 = this.zzajy;
                this.zzail = new AudioTrack(new AudioAttributes.Builder().setUsage(1).setContentType(3).setFlags(16).build(), new AudioFormat.Builder().setChannelMask(v2).setEncoding(v3).setSampleRate(v1).build(), v4, 1, v5);
            }
            else {
                int v6 = this.zzajy;
                this.zzail = v6 == 0 ? new AudioTrack(this.streamType, this.zzafy, this.zzaim, this.zzaio, this.zzaiq, 1) : new AudioTrack(this.streamType, this.zzafy, this.zzaim, this.zzaio, this.zzaiq, 1, v6);
            }
            int v7 = this.zzail.getState();
            if(v7 != 1) {
                goto label_28;
            }
            int v8 = this.zzail.getAudioSessionId();
            if(this.zzajy != v8) {
                this.zzajy = v8;
                this.zzaig.zzs(v8);
            }
            this.zzaij.zza(this.zzail, false);
            this.zzfp();
            this.zzaka = false;
            if(this.zzajx) {
                this.play();
                goto label_37;
                try {
                label_28:
                    this.zzail.release();
                    this.zzail = null;
                }
                catch(Exception unused_ex) {
                    this.zzail = null;
                }
                catch(Throwable throwable0) {
                    this.zzail = null;
                    throw throwable0;
                }
                throw new zzic(v7, this.zzafy, this.zzaim, this.zzaiq);
            }
        }
    label_37:
        boolean z = this.zzaka;
        this.zzaka = this.zzfm();
        if(z && !this.zzaka && this.zzail.getPlayState() != 1) {
            long v9 = SystemClock.elapsedRealtime() - this.zzakb;
            int v10 = this.zzaiq;
            long v11 = zzgl.zzdo(this.zzair);
            this.zzaig.zzc(v10, v11, v9);
        }
        if(this.zzajr == null) {
            if(!byteBuffer0.hasRemaining()) {
                return true;
            }
            if(this.zzaip && this.zzajk == 0) {
                int v12 = this.zzaio;
                switch(v12) {
                    case 5: {
                        v13 = 0x600;
                        break;
                    }
                    case 6: {
                        v13 = zzhn.zzh(byteBuffer0);
                        break;
                    }
                    case 7: 
                    case 8: {
                        v13 = zzij.zzj(byteBuffer0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("Unexpected audio encoding: " + v12);
                    }
                }
                this.zzajk = v13;
            }
            if(this.zzais != null) {
                if(!this.zzfl()) {
                    return false;
                }
                zzie zzie0 = new zzie(this.zzais, Math.max(0L, v), this.zzdw(this.zzfq()), null);
                this.zzaik.add(zzie0);
                this.zzais = null;
                this.zzfi();
            }
            if(this.zzajl == 0) {
                this.zzajm = Math.max(0L, v);
                this.zzajl = 1;
            }
            else {
                long v14 = this.zzajm + this.zzdw((this.zzaip ? this.zzajg : this.zzajf / ((long)this.zzaje)));
                if(this.zzajl == 1 && Math.abs(v14 - v) > 200000L) {
                    Log.e("AudioTrack", "Discontinuity detected [expected " + v14 + ", got " + v + "]");
                    this.zzajl = 2;
                }
                if(this.zzajl == 2) {
                    this.zzajm += v - v14;
                    this.zzajl = 1;
                    this.zzaig.zzef();
                }
            }
            if(this.zzaip) {
                this.zzajg += (long)this.zzajk;
            }
            else {
                this.zzajf += (long)byteBuffer0.remaining();
            }
            this.zzajr = byteBuffer0;
        }
        if(this.zzaip) {
            this.zzb(this.zzajr, v);
        }
        else {
            this.zzdv(v);
        }
        if(!this.zzajr.hasRemaining()) {
            this.zzajr = null;
            return true;
        }
        return false;
    }

    private final boolean zzb(ByteBuffer byteBuffer0, long v) throws zzih {
        int v6;
        if(!byteBuffer0.hasRemaining()) {
            return true;
        }
        ByteBuffer byteBuffer1 = this.zzajs;
        if(byteBuffer1 == null) {
            this.zzajs = byteBuffer0;
            if(zzop.SDK_INT < 21) {
                int v1 = byteBuffer0.remaining();
                if(this.zzajt == null || this.zzajt.length < v1) {
                    this.zzajt = new byte[v1];
                }
                byteBuffer0.get(this.zzajt, 0, v1);
                byteBuffer0.position(byteBuffer0.position());
                this.zzaju = 0;
            }
        }
        else {
            zzob.checkArgument(byteBuffer1 == byteBuffer0);
        }
        int v2 = byteBuffer0.remaining();
        if(zzop.SDK_INT < 21) {
            long v3 = this.zzaji;
            long v4 = this.zzaij.zzfw();
            int v5 = this.zzaiq - ((int)(v3 - v4 * ((long)this.zzajh)));
            if(v5 > 0) {
                v6 = this.zzail.write(this.zzajt, this.zzaju, Math.min(v2, v5));
                if(v6 > 0) {
                    this.zzaju += v6;
                    byteBuffer0.position(byteBuffer0.position() + v6);
                }
            }
            else {
                v6 = 0;
            }
        }
        else if(this.zzajz) {
            zzob.checkState(v != 0x8000000000000001L);
            AudioTrack audioTrack0 = this.zzail;
            if(this.zzaiv == null) {
                this.zzaiv = ByteBuffer.allocate(16);
                this.zzaiv.order(ByteOrder.BIG_ENDIAN);
                this.zzaiv.putInt(0x55550001);
            }
            if(this.zzaiw == 0) {
                this.zzaiv.putInt(4, v2);
                this.zzaiv.putLong(8, v * 1000L);
                this.zzaiv.position(0);
                this.zzaiw = v2;
            }
            int v7 = this.zzaiv.remaining();
            if(v7 > 0) {
                int v8 = audioTrack0.write(this.zzaiv, v7, 1);
                if(v8 < 0) {
                    this.zzaiw = 0;
                    v6 = v8;
                }
                else {
                    if(v8 < v7) {
                        v6 = 0;
                        goto label_57;
                    }
                    goto label_49;
                }
            }
            else {
            label_49:
                int v9 = audioTrack0.write(byteBuffer0, v2, 1);
                if(v9 < 0) {
                    this.zzaiw = 0;
                }
                else {
                    this.zzaiw -= v9;
                }
                v6 = v9;
            }
        }
        else {
            v6 = this.zzail.write(byteBuffer0, v2, 1);
        }
    label_57:
        this.zzakb = SystemClock.elapsedRealtime();
        if(v6 < 0) {
            throw new zzih(v6);
        }
        if(!this.zzaip) {
            this.zzaji += (long)v6;
        }
        if(v6 == v2) {
            if(this.zzaip) {
                this.zzajj += (long)this.zzajk;
            }
            this.zzajs = null;
            return true;
        }
        return false;
    }

    public final zzhf zzb(zzhf zzhf0) {
        if(this.zzaip) {
            this.zzadp = zzhf.zzagj;
            return this.zzadp;
        }
        zzhf zzhf1 = new zzhf(this.zzaie.zzb(zzhf0.zzagk), this.zzaie.zzc(zzhf0.zzagl));
        zzhf zzhf2 = this.zzais;
        if(zzhf2 == null) {
            zzhf2 = this.zzaik.isEmpty() ? this.zzadp : ((zzie)this.zzaik.getLast()).zzadp;
        }
        if(!zzhf1.equals(zzhf2)) {
            if(this.isInitialized()) {
                this.zzais = zzhf1;
                return this.zzadp;
            }
            this.zzadp = zzhf1;
        }
        return this.zzadp;
    }

    public final boolean zzbb(String s) {
        return this.zzaic != null && this.zzaic.zzq(zzhz.zzbc(s));
    }

    private static int zzbc(String s) {
        switch(s) {
            case "audio/ac3": {
                return 5;
            }
            case "audio/eac3": {
                return 6;
            }
            case "audio/vnd.dts": {
                return 7;
            }
            case "audio/vnd.dts.hd": {
                return 8;
            }
            default: {
                return 0;
            }
        }
    }

    private final void zzdv(long v) throws zzih {
        ByteBuffer byteBuffer0;
        int v1 = this.zzajp.length;
        int v2 = v1;
        while(v2 >= 0) {
            if(v2 > 0) {
                byteBuffer0 = this.zzajq[v2 - 1];
            }
            else {
                byteBuffer0 = this.zzajr == null ? zzhp.zzahl : this.zzajr;
            }
            if(v2 == v1) {
                this.zzb(byteBuffer0, v);
            }
            else {
                zzhp zzhp0 = this.zzajp[v2];
                zzhp0.zzi(byteBuffer0);
                ByteBuffer byteBuffer1 = zzhp0.zzfh();
                this.zzajq[v2] = byteBuffer1;
                if(byteBuffer1.hasRemaining()) {
                    ++v2;
                    continue;
                }
            }
            if(byteBuffer0.hasRemaining()) {
                return;
            }
            --v2;
        }
    }

    private final long zzdw(long v) {
        return v * 1000000L / ((long)this.zzafy);
    }

    private final long zzdx(long v) {
        return v * ((long)this.zzafy) / 1000000L;
    }

    // 去混淆评级： 低(30)
    public final boolean zzez() {
        return !this.isInitialized() || this.zzajw && !this.zzfm();
    }

    private final void zzfi() {
        ArrayList arrayList0 = new ArrayList();
        zzhp[] arr_zzhp = this.zzaif;
        for(int v1 = 0; v1 < arr_zzhp.length; ++v1) {
            zzhp zzhp0 = arr_zzhp[v1];
            if(zzhp0.isActive()) {
                arrayList0.add(zzhp0);
            }
            else {
                zzhp0.flush();
            }
        }
        int v2 = arrayList0.size();
        this.zzajp = (zzhp[])arrayList0.toArray(new zzhp[v2]);
        this.zzajq = new ByteBuffer[v2];
        for(int v = 0; v < v2; ++v) {
            zzhp zzhp1 = this.zzajp[v];
            zzhp1.flush();
            ByteBuffer[] arr_byteBuffer = this.zzajq;
            arr_byteBuffer[v] = zzhp1.zzfh();
        }
    }

    public final void zzfj() {
        if(this.zzajl == 1) {
            this.zzajl = 2;
        }
    }

    public final void zzfk() throws zzih {
        if(!this.zzajw && this.isInitialized() && this.zzfl()) {
            long v = this.zzfq();
            this.zzaij.zzdy(v);
            this.zzaiw = 0;
            this.zzajw = true;
        }
    }

    private final boolean zzfl() throws zzih {
        boolean z;
        if(this.zzajv == -1) {
            this.zzajv = this.zzaip ? this.zzajp.length : 0;
            z = true;
        }
        else {
            z = false;
        }
        while(true) {
            int v = this.zzajv;
            zzhp[] arr_zzhp = this.zzajp;
            if(v >= arr_zzhp.length) {
                break;
            }
            zzhp zzhp0 = arr_zzhp[v];
            if(z) {
                zzhp0.zzfg();
            }
            this.zzdv(0x8000000000000001L);
            if(!zzhp0.zzez()) {
                return false;
            }
            ++this.zzajv;
            z = true;
        }
        ByteBuffer byteBuffer0 = this.zzajs;
        if(byteBuffer0 != null) {
            this.zzb(byteBuffer0, 0x8000000000000001L);
            if(this.zzajs != null) {
                return false;
            }
        }
        this.zzajv = -1;
        return true;
    }

    // 去混淆评级： 中等(50)
    public final boolean zzfm() {
        return this.isInitialized() && this.zzfq() > this.zzaij.zzfw();
    }

    public final zzhf zzfn() {
        return this.zzadp;
    }

    public final void zzfo() {
        if(this.zzajz) {
            this.zzajz = false;
            this.zzajy = 0;
            this.reset();
        }
    }

    private final void zzfp() {
        if(this.isInitialized()) {
            if(zzop.SDK_INT >= 21) {
                this.zzail.setVolume(this.zzcy);
                return;
            }
            this.zzail.setStereoVolume(this.zzcy, this.zzcy);
        }
    }

    // 去混淆评级： 低(20)
    private final long zzfq() {
        return this.zzaip ? this.zzajj : this.zzaji / ((long)this.zzajh);
    }

    private final void zzfr() {
        this.zzaiz = 0L;
        this.zzaiy = 0;
        this.zzaix = 0;
        this.zzaja = 0L;
        this.zzajb = false;
        this.zzajc = 0L;
    }

    private final boolean zzfs() [...] // 潜在的解密器

    public final long zzj(boolean z) {
        long v10;
        if(!this.isInitialized() || this.zzajl == 0) {
            return 0x8000000000000000L;
        }
        if(this.zzail.getPlayState() == 3) {
            long v = this.zzaij.zzfx();
            if(v != 0L) {
                long v1 = System.nanoTime();
                if(v1 / 1000L - this.zzaja >= 30000L) {
                    int v2 = this.zzaix;
                    this.zzaii[v2] = v - v1 / 1000L;
                    this.zzaix = (v2 + 1) % 10;
                    int v3 = this.zzaiy;
                    if(v3 < 10) {
                        this.zzaiy = v3 + 1;
                    }
                    this.zzaja = v1 / 1000L;
                    this.zzaiz = 0L;
                    for(int v4 = 0; true; ++v4) {
                        int v5 = this.zzaiy;
                        if(v4 >= v5) {
                            break;
                        }
                        this.zzaiz += this.zzaii[v4] / ((long)v5);
                    }
                }
                if(v1 / 1000L - this.zzajc >= 500000L) {
                    this.zzajb = this.zzaij.zzft();
                    if(this.zzajb) {
                        long v6 = this.zzaij.zzfu();
                        long v7 = this.zzaij.zzfv();
                        if(v6 / 1000L < this.zzajn) {
                            this.zzajb = false;
                        }
                        else if(Math.abs(v6 / 1000L - v1 / 1000L) > 5000000L) {
                            Log.w("AudioTrack", "Spurious audio timestamp (system clock mismatch): " + v7 + ", " + v6 / 1000L + ", " + v1 / 1000L + ", " + v);
                            this.zzajb = false;
                        }
                        else if(Math.abs(this.zzdw(v7) - v) > 5000000L) {
                            Log.w("AudioTrack", "Spurious audio timestamp (frame position mismatch): " + v7 + ", " + v6 / 1000L + ", " + v1 / 1000L + ", " + v);
                            this.zzajb = false;
                        }
                    }
                    Method method0 = this.zzajd;
                    if(method0 != null && !this.zzaip) {
                        try {
                            ((Integer)method0.invoke(this.zzail, null)).intValue();
                            this.zzajo = Math.max(this.zzajo, 0L);
                            if(this.zzajo > 5000000L) {
                                Log.w("AudioTrack", "Ignoring impossibly large audio latency: " + this.zzajo);
                                this.zzajo = 0L;
                            }
                        }
                        catch(Exception unused_ex) {
                            this.zzajd = null;
                        }
                    }
                    this.zzajc = v1 / 1000L;
                }
            }
        }
        long v8 = System.nanoTime();
        if(this.zzajb) {
            long v9 = this.zzdx(v8 / 1000L - this.zzaij.zzfu() / 1000L);
            v10 = this.zzdw(this.zzaij.zzfv() + v9);
        }
        else {
            v10 = this.zzaiy == 0 ? this.zzaij.zzfx() : v8 / 1000L + this.zzaiz;
            if(!z) {
                v10 -= this.zzajo;
            }
        }
        long v11 = this.zzajm;
        while(!this.zzaik.isEmpty() && v10 >= ((zzie)this.zzaik.getFirst()).zzaes) {
            zzie zzie0 = (zzie)this.zzaik.remove();
            this.zzadp = zzie0.zzadp;
            this.zzaiu = zzie0.zzaes;
            this.zzait = zzie0.zzako - this.zzajm;
        }
        if(this.zzadp.zzagk == 1.0f) {
            return v11 + (v10 + this.zzait - this.zzaiu);
        }
        if(this.zzaik.isEmpty() && this.zzaie.zzgg() >= 0x400L) {
            long v12 = this.zzait;
            return v11 + (zzop.zza(v10 - this.zzaiu, this.zzaie.zzgf(), this.zzaie.zzgg()) + v12);
        }
        return v11 + (((long)(((double)this.zzadp.zzagk) * ((double)(v10 - this.zzaiu)))) + this.zzait);
    }

    public final void zzt(int v) {
        zzob.checkState(true);
        if(!this.zzajz || this.zzajy != v) {
            this.zzajz = true;
            this.zzajy = v;
            this.reset();
        }
    }
}

