package com.google.android.gms.internal.ads;

import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Looper;
import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;

public final class zzcax {
    private final Executor executor;
    private final Clock zzbmz;
    private final zzaxx zzfru;

    public zzcax(zzaxx zzaxx0, Clock clock0, Executor executor0) {
        this.zzfru = zzaxx0;
        this.zzbmz = clock0;
        this.executor = executor0;
    }

    private final Bitmap zza(byte[] arr_b, double f, boolean z) {
        BitmapFactory.Options bitmapFactory$Options0 = new BitmapFactory.Options();
        bitmapFactory$Options0.inDensity = (int)(((long)f) * 0x4064000000000000L);
        if(!z) {
            bitmapFactory$Options0.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcqz)).booleanValue()) {
            bitmapFactory$Options0.inJustDecodeBounds = true;
            this.zza(arr_b, bitmapFactory$Options0);
            bitmapFactory$Options0.inJustDecodeBounds = false;
            int v = bitmapFactory$Options0.outWidth * bitmapFactory$Options0.outHeight;
            if(v > 0) {
                bitmapFactory$Options0.inSampleSize = 1 << (33 - Integer.numberOfLeadingZeros((v - 1) / ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcra)))))) / 2;
            }
        }
        return this.zza(arr_b, bitmapFactory$Options0);
    }

    private final Bitmap zza(byte[] arr_b, BitmapFactory.Options bitmapFactory$Options0) {
        long v = this.zzbmz.elapsedRealtime();
        boolean z = false;
        Bitmap bitmap0 = BitmapFactory.decodeByteArray(arr_b, 0, arr_b.length, bitmapFactory$Options0);
        long v1 = this.zzbmz.elapsedRealtime();
        if(Build.VERSION.SDK_INT >= 19 && bitmap0 != null) {
            int v2 = bitmap0.getWidth();
            int v3 = bitmap0.getHeight();
            int v4 = bitmap0.getAllocationByteCount();
            if(Looper.getMainLooper().getThread() == Thread.currentThread()) {
                z = true;
            }
            zzawf.zzee(("Decoded image w: " + v2 + " h:" + v3 + " bytes: " + v4 + " time: " + (v1 - v) + " on ui thread: " + z));
        }
        return bitmap0;
    }

    public final zzdof zza(String s, double f, boolean z) {
        return zzdnt.zzb(zzaxx.zzer(s), new zzcaw(this, f, z), this.executor);
    }
}

