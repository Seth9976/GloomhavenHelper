package com.google.android.gms.internal.ads;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import java.io.UnsupportedEncodingException;

public class zzax extends zzq {
    private final Object mLock;
    @GuardedBy("mLock")
    @Nullable
    private zzab zzcn;

    public zzax(int v, String s, zzab zzab0, @Nullable zzy zzy0) {
        super(v, s, zzy0);
        this.mLock = new Object();
        this.zzcn = zzab0;
    }

    @Override  // com.google.android.gms.internal.ads.zzq
    protected final zzz zza(zzo zzo0) {
        try {
            byte[] arr_b = zzo0.data;
            String s = "ISO-8859-1";
            String s1 = (String)zzo0.zzab.get("Content-Type");
            if(s1 != null) {
                String[] arr_s = s1.split(";", 0);
                int v = 1;
                while(v < arr_s.length) {
                    String[] arr_s1 = arr_s[v].trim().split("=", 0);
                    if(arr_s1.length != 2 || !arr_s1[0].equals("charset")) {
                        ++v;
                    }
                    else {
                        s = arr_s1[1];
                        if(true) {
                            break;
                        }
                    }
                }
            }
            return zzz.zza(new String(arr_b, s), zzas.zzb(zzo0));
        }
        catch(UnsupportedEncodingException unused_ex) {
            return zzz.zza(new String(zzo0.data), zzas.zzb(zzo0));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzq
    protected void zza(Object object0) {
        this.zzh(((String)object0));
    }

    protected void zzh(String s) {
        zzab zzab0;
        synchronized(this.mLock) {
            zzab0 = this.zzcn;
        }
        if(zzab0 != null) {
            zzab0.zzb(s);
        }
    }
}

