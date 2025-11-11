package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.PriorityQueue;

public final class zzqs {
    private final int zzbqp;
    private final int zzbqq;
    private final int zzbqr;
    private final zzqp zzbqs;

    public zzqs(int v) {
        this.zzbqs = new zzqw();
        this.zzbqq = v;
        this.zzbqp = 6;
        this.zzbqr = 0;
    }

    public final String zza(ArrayList arrayList0) {
        StringBuilder stringBuilder0 = new StringBuilder();
        int v = arrayList0.size();
        int v1 = 0;
        while(v1 < v) {
            Object object0 = arrayList0.get(v1);
            ++v1;
            stringBuilder0.append(((String)object0).toLowerCase(Locale.US));
            stringBuilder0.append('\n');
        }
        return this.zzbx(stringBuilder0.toString());
    }

    @VisibleForTesting
    private final String zzbx(String s) {
        String[] arr_s = s.split("\n");
        if(arr_s.length == 0) {
            return "";
        }
        zzqu zzqu0 = new zzqu();
        zzqr zzqr0 = new zzqr(this);
        PriorityQueue priorityQueue0 = new PriorityQueue(this.zzbqq, zzqr0);
        for(int v = 0; v < arr_s.length; ++v) {
            String[] arr_s1 = zzqt.zzd(arr_s[v], false);
            if(arr_s1.length != 0) {
                zzqy.zza(arr_s1, this.zzbqq, this.zzbqp, priorityQueue0);
            }
        }
        for(Object object0: priorityQueue0) {
            zzqx zzqx0 = (zzqx)object0;
            try {
                zzqu0.write(this.zzbqs.zzbw(zzqx0.zzbqw));
            }
            catch(IOException iOException0) {
                zzawf.zzc("Error while writing hash to byteStream", iOException0);
                if(true) {
                    break;
                }
            }
        }
        return zzqu0.toString();
    }
}

