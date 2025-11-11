package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

final class zzig implements zzhp {
    private int zzafx;
    private ByteBuffer zzajs;
    private int zzakp;
    private int[] zzakq;
    private boolean zzakr;
    private int[] zzaks;
    private ByteBuffer zzakt;
    private boolean zzaku;

    public zzig() {
        this.zzakt = zzig.zzahl;
        this.zzajs = zzig.zzahl;
        this.zzafx = -1;
        this.zzakp = -1;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final void flush() {
        this.zzajs = zzig.zzahl;
        this.zzaku = false;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final boolean isActive() {
        return this.zzakr;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final void reset() {
        this.flush();
        this.zzakt = zzig.zzahl;
        this.zzafx = -1;
        this.zzakp = -1;
        this.zzaks = null;
        this.zzakr = false;
    }

    public final void zzb(int[] arr_v) {
        this.zzakq = arr_v;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final boolean zzb(int v, int v1, int v2) throws zzho {
        boolean z = Arrays.equals(this.zzakq, this.zzaks);
        this.zzaks = this.zzakq;
        if(this.zzaks == null) {
            this.zzakr = false;
            return !z;
        }
        if(v2 != 2) {
            throw new zzho(v, v1, v2);
        }
        if(z && this.zzakp == v && this.zzafx == v1) {
            return false;
        }
        this.zzakp = v;
        this.zzafx = v1;
        this.zzakr = v1 != this.zzaks.length;
        for(int v3 = 0; true; ++v3) {
            int[] arr_v = this.zzaks;
            if(v3 >= arr_v.length) {
                break;
            }
            int v4 = arr_v[v3];
            if(v4 >= v1) {
                throw new zzho(v, v1, 2);
            }
            this.zzakr |= v4 != v3;
        }
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final boolean zzez() {
        return this.zzaku && this.zzajs == zzig.zzahl;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final int zzfe() {
        return this.zzaks == null ? this.zzafx : this.zzaks.length;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final int zzff() {
        return 2;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final void zzfg() {
        this.zzaku = true;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final ByteBuffer zzfh() {
        ByteBuffer byteBuffer0 = this.zzajs;
        this.zzajs = zzig.zzahl;
        return byteBuffer0;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final void zzi(ByteBuffer byteBuffer0) {
        int v = byteBuffer0.position();
        int v1 = byteBuffer0.limit();
        int v2 = (v1 - v) / (this.zzafx * 2) * this.zzaks.length << 1;
        if(this.zzakt.capacity() < v2) {
            this.zzakt = ByteBuffer.allocateDirect(v2).order(ByteOrder.nativeOrder());
        }
        else {
            this.zzakt.clear();
        }
        while(v < v1) {
            int[] arr_v = this.zzaks;
            for(int v3 = 0; v3 < arr_v.length; ++v3) {
                this.zzakt.putShort(byteBuffer0.getShort(arr_v[v3] * 2 + v));
            }
            v += this.zzafx << 1;
        }
        byteBuffer0.position(v1);
        this.zzakt.flip();
        this.zzajs = this.zzakt;
    }
}

