package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class zzbce implements zzno {
    private boolean isOpen;
    private Uri uri;
    private InputStream zzecz;
    private final zzno zzeda;
    @Nullable
    private final zzoc zzedb;
    private final zzbcd zzedc;
    private final Context zzur;

    public zzbce(Context context0, zzno zzno0, zzoc zzoc0, zzbcd zzbcd0) {
        this.zzur = context0;
        this.zzeda = zzno0;
        this.zzedb = zzoc0;
        this.zzedc = zzbcd0;
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final void close() throws IOException {
        if(!this.isOpen) {
            throw new IOException("Attempt to close an already closed CacheDataSource.");
        }
        this.isOpen = false;
        this.uri = null;
        InputStream inputStream0 = this.zzecz;
        if(inputStream0 == null) {
            this.zzeda.close();
        }
        else {
            IOUtils.closeQuietly(inputStream0);
            this.zzecz = null;
        }
        zzoc zzoc0 = this.zzedb;
        if(zzoc0 != null) {
            zzoc0.zze(this);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final Uri getUri() {
        return this.uri;
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final int read(byte[] arr_b, int v, int v1) throws IOException {
        if(!this.isOpen) {
            throw new IOException("Attempt to read closed CacheDataSource.");
        }
        InputStream inputStream0 = this.zzecz;
        int v2 = inputStream0 == null ? this.zzeda.read(arr_b, v, v1) : inputStream0.read(arr_b, v, v1);
        zzoc zzoc0 = this.zzedb;
        if(zzoc0 != null) {
            zzoc0.zzc(this, v2);
        }
        return v2;
    }

    @Override  // com.google.android.gms.internal.ads.zzno
    public final long zza(zznp zznp0) throws IOException {
        if(this.isOpen) {
            throw new IOException("Attempt to open an already open CacheDataSource.");
        }
        this.isOpen = true;
        this.uri = zznp0.uri;
        zzoc zzoc0 = this.zzedb;
        if(zzoc0 != null) {
            zzoc0.zza(this, zznp0);
        }
        zzry zzry0 = null;
        zzrz zzrz0 = zzrz.zzd(zznp0.uri);
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcoa)).booleanValue()) {
            if(zzrz0 != null) {
                zzrz0.zzbry = zznp0.zzana;
                zzry0 = zzq.zzlb().zza(zzrz0);
            }
            if(zzry0 != null && zzry0.zzmu()) {
                this.zzecz = zzry0.zzmv();
                return -1L;
            }
        }
        else if(zzrz0 != null) {
            zzrz0.zzbry = zznp0.zzana;
            long v = (long)(zzrz0.zzbrx ? ((Long)zzvh.zzpd().zzd(zzzx.zzcoc)) : ((Long)zzvh.zzpd().zzd(zzzx.zzcob)));
            long v1 = zzq.zzlc().elapsedRealtime();
            Future future0 = zzso.zza(this.zzur, zzrz0);
            try {
                try {
                    this.zzecz = (InputStream)future0.get(v, TimeUnit.MILLISECONDS);
                    goto label_32;
                }
                catch(ExecutionException | TimeoutException unused_ex) {
                }
                catch(InterruptedException unused_ex) {
                    goto label_21;
                }
                future0.cancel(true);
            }
            catch(Throwable throwable0) {
                goto label_28;
            }
            long v2 = zzq.zzlc().elapsedRealtime() - v1;
            this.zzedc.zzb(false, v2);
            zzawf.zzee(("Cache connection took " + v2 + "ms"));
            goto label_42;
            try {
            label_21:
                future0.cancel(true);
                Thread.currentThread().interrupt();
            }
            catch(Throwable throwable0) {
                goto label_28;
            }
            long v3 = zzq.zzlc().elapsedRealtime() - v1;
            this.zzedc.zzb(false, v3);
            zzawf.zzee(("Cache connection took " + v3 + "ms"));
            goto label_42;
        label_28:
            long v4 = zzq.zzlc().elapsedRealtime() - v1;
            this.zzedc.zzb(false, v4);
            zzawf.zzee(("Cache connection took " + v4 + "ms"));
            throw throwable0;
        label_32:
            long v5 = zzq.zzlc().elapsedRealtime() - v1;
            this.zzedc.zzb(true, v5);
            zzawf.zzee(("Cache connection took " + v5 + "ms"));
            return -1L;
        }
    label_42:
        if(zzrz0 != null) {
            zznp0 = new zznp(Uri.parse(zzrz0.url), zznp0.zzbet, zznp0.zzbeu, zznp0.zzana, zznp0.zzce, zznp0.zzcc, zznp0.flags);
        }
        return this.zzeda.zza(zznp0);
    }
}

