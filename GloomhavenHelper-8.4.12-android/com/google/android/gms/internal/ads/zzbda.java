package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class zzbda implements Releasable {
    protected Context mContext;
    protected String zzdwe;
    protected WeakReference zzeem;

    public zzbda(zzbbm zzbbm0) {
        this.mContext = zzbbm0.getContext();
        this.zzdwe = zzq.zzkv().zzr(this.mContext, zzbbm0.zzyw().zzbmj);
        this.zzeem = new WeakReference(zzbbm0);
    }

    public abstract void abort();

    @Override  // com.google.android.gms.common.api.Releasable
    public void release() {
    }

    static String zza(zzbda zzbda0, String s) {
        return zzbda.zzfl(s);
    }

    private final void zza(String s, Map map0) {
        zzbbm zzbbm0 = (zzbbm)this.zzeem.get();
        if(zzbbm0 != null) {
            zzbbm0.zza(s, map0);
        }
    }

    protected final void zza(String s, String s1, int v) {
        zzbde zzbde0 = new zzbde(this, s, s1, v);
        zzayx.zzyy.post(zzbde0);
    }

    @VisibleForTesting
    public final void zza(String s, String s1, int v, int v1, long v2, long v3, boolean z, int v4, int v5) {
        zzbdb zzbdb0 = new zzbdb(this, s, s1, v, v1, v2, v3, z, v4, v5);
        zzayx.zzyy.post(zzbdb0);
    }

    @VisibleForTesting
    public final void zza(String s, String s1, long v, long v1, boolean z, int v2, int v3) {
        zzbdc zzbdc0 = new zzbdc(this, s, s1, v, v1, z, v2, v3);
        zzayx.zzyy.post(zzbdc0);
    }

    @VisibleForTesting
    public final void zza(String s, String s1, String s2, @Nullable String s3) {
        zzbdg zzbdg0 = new zzbdg(this, s, s1, s2, s3);
        zzayx.zzyy.post(zzbdg0);
    }

    @VisibleForTesting
    public final void zzb(String s, String s1, long v) {
        zzbdd zzbdd0 = new zzbdd(this, s, s1, v);
        zzayx.zzyy.post(zzbdd0);
    }

    protected void zzcv(int v) {
    }

    protected void zzcw(int v) {
    }

    protected void zzcx(int v) {
    }

    protected void zzcy(int v) {
    }

    public boolean zze(String s, String[] arr_s) {
        return this.zzfj(s);
    }

    public abstract boolean zzfj(String arg1);

    protected String zzfk(String s) {
        return zzayx.zzet(s);
    }

    private static String zzfl(String s) {
        switch(s) {
            case "badUrl": {
                return "network";
            }
            case "contentLengthMissing": {
                return "internal";
            }
            case "downloadTimeout": {
                return "network";
            }
            case "error": {
                return "internal";
            }
            case "expireFailed": {
                return "io";
            }
            case "externalAbort": {
                return "policy";
            }
            case "inProgress": {
                return "internal";
            }
            case "interrupted": {
                return "internal";
            }
            case "noCacheDir": {
                return "io";
            }
            case "noop": {
                return "internal";
            }
            case "playerFailed": {
                return "internal";
            }
            case "sizeExceeded": {
                return "policy";
            }
            default: {
                return "internal";
            }
        }
    }
}

