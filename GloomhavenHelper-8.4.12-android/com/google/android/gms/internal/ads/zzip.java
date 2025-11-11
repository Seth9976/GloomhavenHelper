package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public final class zzip implements zzhp {
    private int zzafx;
    private float zzagk;
    private float zzagl;
    private ByteBuffer zzajs;
    private int zzakp;
    private ByteBuffer zzakt;
    private boolean zzaku;
    private zzim zzamb;
    private ShortBuffer zzamc;
    private long zzamd;
    private long zzame;

    public zzip() {
        this.zzagk = 1.0f;
        this.zzagl = 1.0f;
        this.zzafx = -1;
        this.zzakp = -1;
        this.zzakt = zzip.zzahl;
        this.zzamc = this.zzakt.asShortBuffer();
        this.zzajs = zzip.zzahl;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final void flush() {
        this.zzamb = new zzim(this.zzakp, this.zzafx);
        this.zzamb.setSpeed(this.zzagk);
        this.zzamb.zza(this.zzagl);
        this.zzajs = zzip.zzahl;
        this.zzamd = 0L;
        this.zzame = 0L;
        this.zzaku = false;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final boolean isActive() {
        return Math.abs(this.zzagk - 1.0f) >= 0.01f || Math.abs(this.zzagl - 1.0f) >= 0.01f;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final void reset() {
        this.zzamb = null;
        this.zzakt = zzip.zzahl;
        this.zzamc = this.zzakt.asShortBuffer();
        this.zzajs = zzip.zzahl;
        this.zzafx = -1;
        this.zzakp = -1;
        this.zzamd = 0L;
        this.zzame = 0L;
        this.zzaku = false;
    }

    public final float zzb(float f) {
        this.zzagk = zzop.zza(f, 0.1f, 8.0f);
        return this.zzagk;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final boolean zzb(int v, int v1, int v2) throws zzho {
        if(v2 != 2) {
            throw new zzho(v, v1, v2);
        }
        if(this.zzakp == v && this.zzafx == v1) {
            return false;
        }
        this.zzakp = v;
        this.zzafx = v1;
        return true;
    }

    public final float zzc(float f) {
        this.zzagl = zzop.zza(f, 0.1f, 8.0f);
        return f;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzhp
    public final boolean zzez() {
        return this.zzaku && (this.zzamb == null || this.zzamb.zzga() == 0);
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final int zzfe() {
        return this.zzafx;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final int zzff() {
        return 2;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final void zzfg() {
        this.zzamb.zzfg();
        this.zzaku = true;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final ByteBuffer zzfh() {
        ByteBuffer byteBuffer0 = this.zzajs;
        this.zzajs = zzip.zzahl;
        return byteBuffer0;
    }

    public final long zzgf() {
        return this.zzamd;
    }

    public final long zzgg() {
        return this.zzame;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final void zzi(ByteBuffer byteBuffer0) {
        if(byteBuffer0.hasRemaining()) {
            ShortBuffer shortBuffer0 = byteBuffer0.asShortBuffer();
            int v = byteBuffer0.remaining();
            this.zzamd += (long)v;
            this.zzamb.zza(shortBuffer0);
            byteBuffer0.position(byteBuffer0.position() + v);
        }
        int v1 = this.zzamb.zzga() * this.zzafx << 1;
        if(v1 > 0) {
            if(this.zzakt.capacity() < v1) {
                this.zzakt = ByteBuffer.allocateDirect(v1).order(ByteOrder.nativeOrder());
                this.zzamc = this.zzakt.asShortBuffer();
            }
            else {
                this.zzakt.clear();
                this.zzamc.clear();
            }
            this.zzamb.zzb(this.zzamc);
            this.zzame += (long)v1;
            this.zzakt.limit(v1);
            this.zzajs = this.zzakt;
        }
    }
}

