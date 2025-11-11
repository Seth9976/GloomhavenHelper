package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.WindowManager;

@TargetApi(16)
public final class zzpe {
    private final zzpd zzbjd;
    private final boolean zzbje;
    private final long zzbjf;
    private final long zzbjg;
    private long zzbjh;
    private long zzbji;
    private long zzbjj;
    private boolean zzbjk;
    private long zzbjl;
    private long zzbjm;
    private long zzbjn;

    public zzpe() {
        this(-1.0);
    }

    private zzpe(double f) {
        this.zzbje = ((long)f) != 0xBFF0000000000000L;
        if(this.zzbje) {
            this.zzbjd = zzpd.zzjl();
            this.zzbjf = (long)(0x41CDCD6500000000L / ((long)f));
            this.zzbjg = this.zzbjf * 80L / 100L;
            return;
        }
        this.zzbjd = null;
        this.zzbjf = -1L;
        this.zzbjg = -1L;
    }

    public zzpe(Context context0) {
        WindowManager windowManager0 = (WindowManager)context0.getSystemService("window");
        this((windowManager0.getDefaultDisplay() == null ? -1.0 : ((double)windowManager0.getDefaultDisplay().getRefreshRate())));
    }

    public final void disable() {
        if(this.zzbje) {
            this.zzbjd.zzjn();
        }
    }

    public final void enable() {
        this.zzbjk = false;
        if(this.zzbje) {
            this.zzbjd.zzjm();
        }
    }

    public final long zzf(long v, long v1) {
        long v8;
        long v4;
        long v3;
        if(this.zzbjk) {
            if(v != this.zzbjh) {
                ++this.zzbjn;
                this.zzbji = this.zzbjj;
            }
            long v2 = this.zzbjn;
            if(v2 >= 6L) {
                v3 = this.zzbji + (1000L * v - this.zzbjm) / v2;
                if(this.zzg(v3, v1)) {
                    this.zzbjk = false;
                    v4 = v1;
                    v3 = 1000L * v;
                }
                else {
                    v4 = this.zzbjl + v3 - this.zzbjm;
                }
            }
            else {
                if(this.zzg(1000L * v, v1)) {
                    this.zzbjk = false;
                }
                v4 = v1;
                v3 = 1000L * v;
            }
        }
        else {
            v4 = v1;
            v3 = 1000L * v;
        }
        if(!this.zzbjk) {
            this.zzbjm = 1000L * v;
            this.zzbjl = v1;
            this.zzbjn = 0L;
            this.zzbjk = true;
        }
        this.zzbjh = v;
        this.zzbjj = v3;
        if(this.zzbjd != null && this.zzbjd.zzbiy != 0L) {
            long v5 = this.zzbjd.zzbiy;
            long v6 = this.zzbjf;
            long v7 = v5 + (v4 - v5) / v6 * v6;
            if(v4 <= v7) {
                v8 = v7 - v6;
            }
            else {
                long v9 = v7;
                v7 = v6 + v7;
                v8 = v9;
            }
            if(v7 - v4 >= v4 - v8) {
                v7 = v8;
            }
            return v7 - this.zzbjg;
        }
        return v4;
    }

    private final boolean zzg(long v, long v1) {
        return Math.abs(v1 - this.zzbjl - (v - this.zzbjm)) > 20000000L;
    }
}

