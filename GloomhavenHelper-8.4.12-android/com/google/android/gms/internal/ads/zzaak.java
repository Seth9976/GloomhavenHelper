package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzaak {
    private final Object lock;
    @VisibleForTesting
    boolean zzcsx;
    private final List zzcsy;
    private final Map zzcsz;
    @Nullable
    private zzaak zzcta;

    public zzaak(boolean z, String s, String s1) {
        this.zzcsy = new LinkedList();
        this.zzcsz = new LinkedHashMap();
        this.lock = new Object();
        this.zzcsx = true;
        this.zzcsz.put("action", s);
        this.zzcsz.put("ad_format", s1);
    }

    public final boolean zza(zzaai zzaai0, long v, String[] arr_s) {
        synchronized(this.lock) {
            for(int v2 = 0; v2 < arr_s.length; ++v2) {
                zzaai zzaai1 = new zzaai(v, arr_s[v2], zzaai0);
                this.zzcsy.add(zzaai1);
            }
            return true;
        }
    }

    public final void zzc(@Nullable zzaak zzaak0) {
        synchronized(this.lock) {
            this.zzcta = zzaak0;
        }
    }

    @Nullable
    public final zzaai zzex(long v) {
        return this.zzcsx ? new zzaai(v, null, null) : null;
    }

    public final void zzh(String s, String s1) {
        if(this.zzcsx && !TextUtils.isEmpty(s1)) {
            zzaaa zzaaa0 = zzq.zzkz().zzve();
            if(zzaaa0 == null) {
                return;
            }
            synchronized(this.lock) {
                String s2 = zzaaa0.zzcr(s).zzg(((String)this.zzcsz.get(s)), s1);
                this.zzcsz.put(s, s2);
            }
        }
    }

    public final String zzqu() {
        StringBuilder stringBuilder0 = new StringBuilder();
        synchronized(this.lock) {
            for(Object object1: this.zzcsy) {
                long v1 = ((zzaai)object1).getTime();
                String s = ((zzaai)object1).zzqs();
                zzaai zzaai0 = ((zzaai)object1).zzqt();
                if(zzaai0 != null && v1 > 0L) {
                    stringBuilder0.append(s);
                    stringBuilder0.append('.');
                    stringBuilder0.append(v1 - zzaai0.getTime());
                    stringBuilder0.append(',');
                }
            }
            this.zzcsy.clear();
            if(!TextUtils.isEmpty(null)) {
                stringBuilder0.append(null);
            }
            else if(stringBuilder0.length() > 0) {
                stringBuilder0.setLength(stringBuilder0.length() - 1);
            }
        }
        return stringBuilder0.toString();
    }

    @VisibleForTesting
    final Map zzqv() {
        synchronized(this.lock) {
            zzaaa zzaaa0 = zzq.zzkz().zzve();
            if(zzaaa0 != null && this.zzcta != null) {
                Map map0 = this.zzcta.zzqv();
                return zzaaa0.zza(this.zzcsz, map0);
            }
            return this.zzcsz;
        }
    }
}

