package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import java.util.concurrent.TimeUnit;

public final class zzbbl {
    private final zzazo zzdjo;
    private final String zzdkd;
    @Nullable
    private final zzaak zzdzc;
    private boolean zzdzg;
    @Nullable
    private final zzaai zzebu;
    private final zzaxt zzebv;
    private final long[] zzebw;
    private final String[] zzebx;
    private boolean zzeby;
    private boolean zzebz;
    private boolean zzeca;
    private boolean zzecb;
    private zzbat zzecc;
    private boolean zzecd;
    private boolean zzece;
    private long zzecf;
    private final Context zzur;

    public zzbbl(Context context0, zzazo zzazo0, String s, @Nullable zzaak zzaak0, @Nullable zzaai zzaai0) {
        this.zzebv = new zzaxy().zza("min_1", 4.900000E-324, 1.0).zza("1_5", 1.0, 5.0).zza("5_10", 5.0, 10.0).zza("10_20", 10.0, 20.0).zza("20_30", 20.0, 30.0).zza("30_max", 30.0, 1.797693E+308).zzxf();
        this.zzeby = false;
        this.zzebz = false;
        this.zzeca = false;
        this.zzecb = false;
        this.zzecf = -1L;
        this.zzur = context0;
        this.zzdjo = zzazo0;
        this.zzdkd = s;
        this.zzdzc = zzaak0;
        this.zzebu = zzaai0;
        String s1 = (String)zzvh.zzpd().zzd(zzzx.zzcht);
        if(s1 == null) {
            this.zzebx = new String[0];
            this.zzebw = new long[0];
            return;
        }
        String[] arr_s = TextUtils.split(s1, ",");
        this.zzebx = new String[arr_s.length];
        this.zzebw = new long[arr_s.length];
        for(int v = 0; v < arr_s.length; ++v) {
            try {
                this.zzebw[v] = Long.parseLong(arr_s[v]);
            }
            catch(NumberFormatException numberFormatException0) {
                zzawf.zzd("Unable to parse frame hash target time number.", numberFormatException0);
                this.zzebw[v] = -1L;
            }
        }
    }

    public final void onStop() {
        if(((Boolean)zzabv.zzcwb.get()).booleanValue() && !this.zzecd) {
            Bundle bundle0 = new Bundle();
            bundle0.putString("type", "native-player-metrics");
            bundle0.putString("request", this.zzdkd);
            bundle0.putString("player", this.zzecc.zzxt());
            for(Object object0: this.zzebv.zzxe()) {
                String s = String.valueOf(((zzaxv)object0).name);
                bundle0.putString((s.length() == 0 ? new String("fps_c_") : "fps_c_" + s), Integer.toString(((zzaxv)object0).count));
                String s1 = String.valueOf(((zzaxv)object0).name);
                bundle0.putString((s1.length() == 0 ? new String("fps_p_") : "fps_p_" + s1), Double.toString(((zzaxv)object0).zzdvf));
            }
            for(int v = 0; true; ++v) {
                long[] arr_v = this.zzebw;
                if(v >= arr_v.length) {
                    break;
                }
                String s2 = this.zzebx[v];
                if(s2 != null) {
                    bundle0.putString("fh_" + ((long)arr_v[v]), s2);
                }
            }
            zzq.zzkv().zza(this.zzur, this.zzdjo.zzbmj, "gmob-apps", bundle0, true);
            this.zzecd = true;
        }
    }

    public final void zzb(zzbat zzbat0) {
        zzaaf.zza(this.zzdzc, this.zzebu, new String[]{"vpc2"});
        this.zzeby = true;
        zzaak zzaak0 = this.zzdzc;
        if(zzaak0 != null) {
            zzaak0.zzh("vpn", zzbat0.zzxt());
        }
        this.zzecc = zzbat0;
    }

    public final void zzc(zzbat zzbat0) {
        if(this.zzeca && !this.zzecb) {
            if(zzawf.zzvx() && !this.zzecb) {
                zzawf.zzee("VideoMetricsMixin first frame");
            }
            zzaaf.zza(this.zzdzc, this.zzebu, new String[]{"vff2"});
            this.zzecb = true;
        }
        long v = zzq.zzlc().nanoTime();
        if(this.zzdzg && this.zzece && this.zzecf != -1L) {
            double f = (double)TimeUnit.SECONDS.toNanos(1L);
            this.zzebv.zza(f / ((double)(v - this.zzecf)));
        }
        this.zzece = this.zzdzg;
        this.zzecf = v;
        long v1 = (long)(((Long)zzvh.zzpd().zzd(zzzx.zzchu)));
        long v2 = (long)zzbat0.getCurrentPosition();
        for(int v3 = 0; true; ++v3) {
            String[] arr_s = this.zzebx;
            if(v3 >= arr_s.length) {
                break;
            }
            if(arr_s[v3] == null && v1 > Math.abs(v2 - this.zzebw[v3])) {
                String[] arr_s1 = this.zzebx;
                Bitmap bitmap0 = zzbat0.getBitmap(8, 8);
                long v5 = 0L;
                int v6 = 0;
                for(long v4 = 0x3FL; v6 < 8; v4 = v7) {
                    long v7 = v4;
                    int v8 = 0;
                    while(v8 < 8) {
                        int v9 = bitmap0.getPixel(v8, v6);
                        v5 |= (Color.blue(v9) + Color.red(v9) + Color.green(v9) <= 0x80 ? 0L : 1L) << ((int)v7);
                        ++v8;
                        --v7;
                    }
                    ++v6;
                }
                arr_s1[v3] = String.format("%016X", v5);
                return;
            }
        }
    }

    public final void zzew() {
        if(this.zzeby && !this.zzebz) {
            zzaaf.zza(this.zzdzc, this.zzebu, new String[]{"vfr2"});
            this.zzebz = true;
        }
    }

    public final void zzyn() {
        this.zzdzg = true;
        if(this.zzebz && !this.zzeca) {
            zzaaf.zza(this.zzdzc, this.zzebu, new String[]{"vfp2"});
            this.zzeca = true;
        }
    }

    public final void zzyo() {
        this.zzdzg = false;
    }
}

