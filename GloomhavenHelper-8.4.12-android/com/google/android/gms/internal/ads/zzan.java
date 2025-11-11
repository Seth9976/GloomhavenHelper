package com.google.android.gms.internal.ads;

import androidx.annotation.VisibleForTesting;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

@VisibleForTesting
final class zzan {
    long zzcb;
    final String zzcc;
    final String zzg;
    final long zzh;
    final long zzi;
    final long zzj;
    final long zzk;
    final List zzm;

    zzan(String s, zzd zzd0) {
        List list0;
        String s1 = zzd0.zzg;
        long v = zzd0.zzh;
        long v1 = zzd0.zzi;
        long v2 = zzd0.zzj;
        long v3 = zzd0.zzk;
        if(zzd0.zzm == null) {
            Map map0 = zzd0.zzl;
            ArrayList arrayList0 = new ArrayList(map0.size());
            for(Object object0: map0.entrySet()) {
                arrayList0.add(new zzk(((String)((Map.Entry)object0).getKey()), ((String)((Map.Entry)object0).getValue())));
            }
            list0 = arrayList0;
        }
        else {
            list0 = zzd0.zzm;
        }
        this(s, s1, v, v1, v2, v3, list0);
    }

    private zzan(String s, String s1, long v, long v1, long v2, long v3, List list0) {
        this.zzcc = s;
        if("".equals(s1)) {
            s1 = null;
        }
        this.zzg = s1;
        this.zzh = v;
        this.zzi = v1;
        this.zzj = v2;
        this.zzk = v3;
        this.zzm = list0;
    }

    final boolean zza(OutputStream outputStream0) {
        try {
            zzal.zza(outputStream0, 0x20150306);
            zzal.zza(outputStream0, this.zzcc);
            zzal.zza(outputStream0, (this.zzg == null ? "" : this.zzg));
            zzal.zza(outputStream0, this.zzh);
            zzal.zza(outputStream0, this.zzi);
            zzal.zza(outputStream0, this.zzj);
            zzal.zza(outputStream0, this.zzk);
            List list0 = this.zzm;
            if(list0 == null) {
                zzal.zza(outputStream0, 0);
            }
            else {
                zzal.zza(outputStream0, list0.size());
                for(Object object0: list0) {
                    zzal.zza(outputStream0, ((zzk)object0).getName());
                    zzal.zza(outputStream0, ((zzk)object0).getValue());
                }
            }
            outputStream0.flush();
            return true;
        }
        catch(IOException iOException0) {
            zzag.d("%s", new Object[]{iOException0.toString()});
            return false;
        }
    }

    static zzan zzc(zzaq zzaq0) throws IOException {
        if(zzal.zzb(zzaq0) != 0x20150306) {
            throw new IOException();
        }
        return new zzan(zzal.zza(zzaq0), zzal.zza(zzaq0), zzal.zzc(zzaq0), zzal.zzc(zzaq0), zzal.zzc(zzaq0), zzal.zzc(zzaq0), zzal.zzb(zzaq0));
    }
}

