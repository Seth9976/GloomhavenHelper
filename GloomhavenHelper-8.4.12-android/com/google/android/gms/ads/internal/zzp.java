package com.google.android.gms.ads.internal;

import android.os.AsyncTask;
import com.google.android.gms.internal.ads.zzazh;
import com.google.android.gms.internal.ads.zzdq;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class zzp extends AsyncTask {
    private final zzl zzblt;

    private zzp(zzl zzl0) {
        this.zzblt = zzl0;
        super();
    }

    zzp(zzl zzl0, zzk zzk0) {
        this(zzl0);
    }

    @Override  // android.os.AsyncTask
    protected final Object doInBackground(Object[] arr_object) {
        return this.zza(((Void[])arr_object));
    }

    @Override  // android.os.AsyncTask
    protected final void onPostExecute(Object object0) {
        if(this.zzblt.zzbly != null && ((String)object0) != null) {
            this.zzblt.zzbly.loadUrl(((String)object0));
        }
    }

    private final String zza(Void[] arr_void) {
        try {
            zzdq zzdq0 = (zzdq)this.zzblt.zzblw.get(1000L, TimeUnit.MILLISECONDS);
            this.zzblt.zzbma = zzdq0;
            return this.zzblt.zzkj();
        }
        catch(InterruptedException | ExecutionException | TimeoutException interruptedException0) {
            zzazh.zzd("", interruptedException0);
            return this.zzblt.zzkj();
        }
    }
}

