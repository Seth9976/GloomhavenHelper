package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzh;
import com.google.android.gms.ads.internal.zzq;
import java.lang.ref.WeakReference;

public class zzbgn {
    public static final class zza {
        private zzazo zzblr;
        private WeakReference zzekx;
        private Context zzyz;

        public final zza zza(zzazo zzazo0) {
            this.zzblr = zzazo0;
            return this;
        }

        public final zza zzbv(Context context0) {
            this.zzekx = new WeakReference(context0);
            if(context0.getApplicationContext() != null) {
                context0 = context0.getApplicationContext();
            }
            this.zzyz = context0;
            return this;
        }
    }

    private final zzazo zzblr;
    private final WeakReference zzekx;
    private final Context zzyz;

    private zzbgn(zza zzbgn$zza0) {
        this.zzblr = zzbgn$zza0.zzblr;
        this.zzyz = zzbgn$zza0.zzyz;
        this.zzekx = zzbgn$zza0.zzekx;
    }

    zzbgn(zza zzbgn$zza0, zzbgm zzbgm0) {
        this(zzbgn$zza0);
    }

    final Context zzacu() {
        return this.zzyz;
    }

    final WeakReference zzacv() {
        return this.zzekx;
    }

    final zzazo zzacw() {
        return this.zzblr;
    }

    final String zzacx() {
        return zzq.zzkv().zzr(this.zzyz, this.zzblr.zzbmj);
    }

    public final zzdq zzacy() {
        return new zzdq(new zzh(this.zzyz, this.zzblr));
    }
}

