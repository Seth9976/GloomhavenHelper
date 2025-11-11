package com.google.android.gms.internal.ads;

import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

final class zzatt implements Runnable {
    private final Bitmap val$bitmap;
    private final zzats zzdpu;

    zzatt(zzats zzats0, Bitmap bitmap0) {
        this.zzdpu = zzats0;
        this.val$bitmap = bitmap0;
        super();
    }

    @Override
    public final void run() {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        this.val$bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream0);
        synchronized(this.zzdpu.lock) {
            this.zzdpu.zzdpj.zziel = new zzedq();
            zzedq zzedq0 = this.zzdpu.zzdpj.zziel;
            zzedq0.zziff = byteArrayOutputStream0.toByteArray();
            this.zzdpu.zzdpj.zziel.mimeType = "image/png";
            this.zzdpu.zzdpj.zziel.zzife = zza.zzicu;
        }
    }
}

