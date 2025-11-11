package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

final class zzdyj implements zzecs {
    private final zzdyi zzhok;

    private zzdyj(zzdyi zzdyi0) {
        this.zzhok = (zzdyi)zzdzc.zza(zzdyi0, "output");
        this.zzhok.zzhpe = this;
    }

    public static zzdyj zza(zzdyi zzdyi0) {
        return zzdyi0.zzhpe == null ? new zzdyj(zzdyi0) : zzdyi0.zzhpe;
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zza(int v, float f) throws IOException {
        this.zzhok.zza(v, f);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zza(int v, zzdxn zzdxn0) throws IOException {
        this.zzhok.zza(v, zzdxn0);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zza(int v, zzeac zzeac0, Map map0) throws IOException {
        for(Object object0: map0.entrySet()) {
            this.zzhok.zzab(v, 2);
            int v1 = zzdzz.zza(zzeac0, ((Map.Entry)object0).getKey(), ((Map.Entry)object0).getValue());
            this.zzhok.zzfw(v1);
            Object object1 = ((Map.Entry)object0).getKey();
            Object object2 = ((Map.Entry)object0).getValue();
            zzdzz.zza(this.zzhok, zzeac0, object1, object2);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zza(int v, Object object0, zzebd zzebd0) throws IOException {
        this.zzhok.zza(v, ((zzeah)object0), zzebd0);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zza(int v, List list0) throws IOException {
        int v1 = 0;
        if(list0 instanceof zzdzs) {
            while(v1 < list0.size()) {
                Object object0 = ((zzdzs)list0).zzgm(v1);
                if(object0 instanceof String) {
                    this.zzhok.zzf(v, ((String)object0));
                }
                else {
                    this.zzhok.zza(v, ((zzdxn)object0));
                }
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            String s = (String)list0.get(v1);
            this.zzhok.zzf(v, s);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zza(int v, List list0, zzebd zzebd0) throws IOException {
        for(int v1 = 0; v1 < list0.size(); ++v1) {
            this.zza(v, list0.get(v1), zzebd0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zza(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zzhok.zzab(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzdyi.zzga(((int)(((Integer)list0.get(v2)))));
            }
            this.zzhok.zzfw(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.zzhok.zzfv(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.zzhok.zzac(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzac(int v, int v1) throws IOException {
        this.zzhok.zzac(v, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzad(int v, int v1) throws IOException {
        this.zzhok.zzad(v, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzae(int v, int v1) throws IOException {
        this.zzhok.zzae(v, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzaf(int v, int v1) throws IOException {
        this.zzhok.zzaf(v, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzam(int v, int v1) throws IOException {
        this.zzhok.zzaf(v, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzan(int v, int v1) throws IOException {
        this.zzhok.zzac(v, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzb(int v, double f) throws IOException {
        this.zzhok.zzb(v, f);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzb(int v, Object object0, zzebd zzebd0) throws IOException {
        this.zzhok.zzab(v, 3);
        zzebd0.zza(((zzeah)object0), this.zzhok.zzhpe);
        this.zzhok.zzab(v, 4);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzb(int v, List list0) throws IOException {
        for(int v1 = 0; v1 < list0.size(); ++v1) {
            zzdxn zzdxn0 = (zzdxn)list0.get(v1);
            this.zzhok.zza(v, zzdxn0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzb(int v, List list0, zzebd zzebd0) throws IOException {
        for(int v1 = 0; v1 < list0.size(); ++v1) {
            this.zzb(v, list0.get(v1), zzebd0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzb(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zzhok.zzab(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzdyi.zzgd(((int)(((Integer)list0.get(v2)))));
            }
            this.zzhok.zzfw(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.zzhok.zzfy(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.zzhok.zzaf(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final int zzbcf() {
        return zzf.zzhtn;
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzc(int v, Object object0) throws IOException {
        if(object0 instanceof zzdxn) {
            this.zzhok.zzb(v, ((zzdxn)object0));
            return;
        }
        this.zzhok.zzb(v, ((zzeah)object0));
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzc(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zzhok.zzab(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzdyi.zzfk(((long)(((Long)list0.get(v2)))));
            }
            this.zzhok.zzfw(v3);
            while(v1 < list0.size()) {
                long v4 = (long)(((Long)list0.get(v1)));
                this.zzhok.zzfh(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v5 = (long)(((Long)list0.get(v1)));
            this.zzhok.zzh(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzd(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zzhok.zzab(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzdyi.zzfl(((long)(((Long)list0.get(v2)))));
            }
            this.zzhok.zzfw(v3);
            while(v1 < list0.size()) {
                long v4 = (long)(((Long)list0.get(v1)));
                this.zzhok.zzfh(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v5 = (long)(((Long)list0.get(v1)));
            this.zzhok.zzh(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zze(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zzhok.zzab(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzdyi.zzfn(((long)(((Long)list0.get(v2)))));
            }
            this.zzhok.zzfw(v3);
            while(v1 < list0.size()) {
                long v4 = (long)(((Long)list0.get(v1)));
                this.zzhok.zzfj(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v5 = (long)(((Long)list0.get(v1)));
            this.zzhok.zzj(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzf(int v, String s) throws IOException {
        this.zzhok.zzf(v, s);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzf(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zzhok.zzab(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzdyi.zzg(((float)(((Float)list0.get(v2)))));
            }
            this.zzhok.zzfw(v3);
            while(v1 < list0.size()) {
                float f = (float)(((Float)list0.get(v1)));
                this.zzhok.zzf(f);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            float f1 = (float)(((Float)list0.get(v1)));
            this.zzhok.zza(v, f1);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzg(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zzhok.zzab(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzdyi.zzc(((double)(((Double)list0.get(v2)))));
            }
            this.zzhok.zzfw(v3);
            while(v1 < list0.size()) {
                double f = (double)(((Double)list0.get(v1)));
                this.zzhok.zzb(f);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            double f1 = (double)(((Double)list0.get(v1)));
            this.zzhok.zzb(v, f1);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzgi(int v) throws IOException {
        this.zzhok.zzab(v, 3);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzgj(int v) throws IOException {
        this.zzhok.zzab(v, 4);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzh(int v, long v1) throws IOException {
        this.zzhok.zzh(v, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzh(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zzhok.zzab(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzdyi.zzgf(((int)(((Integer)list0.get(v2)))));
            }
            this.zzhok.zzfw(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.zzhok.zzfv(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.zzhok.zzac(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzh(int v, boolean z) throws IOException {
        this.zzhok.zzh(v, z);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzi(int v, long v1) throws IOException {
        this.zzhok.zzi(v, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzi(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zzhok.zzab(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzdyi.zzbq(((Boolean)list0.get(v2)).booleanValue());
            }
            this.zzhok.zzfw(v3);
            while(v1 < list0.size()) {
                boolean z1 = ((Boolean)list0.get(v1)).booleanValue();
                this.zzhok.zzbp(z1);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            boolean z2 = ((Boolean)list0.get(v1)).booleanValue();
            this.zzhok.zzh(v, z2);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzj(int v, long v1) throws IOException {
        this.zzhok.zzj(v, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzj(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zzhok.zzab(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzdyi.zzgb(((int)(((Integer)list0.get(v2)))));
            }
            this.zzhok.zzfw(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.zzhok.zzfw(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.zzhok.zzad(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzk(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zzhok.zzab(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzdyi.zzge(((int)(((Integer)list0.get(v2)))));
            }
            this.zzhok.zzfw(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.zzhok.zzfy(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.zzhok.zzaf(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzl(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zzhok.zzab(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzdyi.zzfo(((long)(((Long)list0.get(v2)))));
            }
            this.zzhok.zzfw(v3);
            while(v1 < list0.size()) {
                long v4 = (long)(((Long)list0.get(v1)));
                this.zzhok.zzfj(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v5 = (long)(((Long)list0.get(v1)));
            this.zzhok.zzj(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzm(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zzhok.zzab(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzdyi.zzgc(((int)(((Integer)list0.get(v2)))));
            }
            this.zzhok.zzfw(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.zzhok.zzfx(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.zzhok.zzae(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzn(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.zzhok.zzab(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += zzdyi.zzfm(((long)(((Long)list0.get(v2)))));
            }
            this.zzhok.zzfw(v3);
            while(v1 < list0.size()) {
                long v4 = (long)(((Long)list0.get(v1)));
                this.zzhok.zzfi(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v5 = (long)(((Long)list0.get(v1)));
            this.zzhok.zzi(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzp(int v, long v1) throws IOException {
        this.zzhok.zzh(v, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzecs
    public final void zzq(int v, long v1) throws IOException {
        this.zzhok.zzj(v, v1);
    }
}

