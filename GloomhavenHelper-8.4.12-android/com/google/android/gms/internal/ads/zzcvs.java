package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzq;
import java.util.concurrent.Callable;

final class zzcvs implements Callable {
    private final zzcvp zzgjf;

    zzcvs(zzcvp zzcvp0) {
        this.zzgjf = zzcvp0;
    }

    @Override
    public final Object call() {
        String s2;
        String s1;
        String s;
        zzqj zzqj0 = zzq.zzkz().zzvk().zzvy();
        Bundle bundle0 = null;
        if(zzqj0 != null && (!zzq.zzkz().zzvk().zzvz() || !zzq.zzkz().zzvk().zzwb())) {
            if(zzqj0.zzmk()) {
                zzqj0.wakeup();
            }
            zzqd zzqd0 = zzqj0.zzmi();
            if(zzqd0 == null) {
                s = zzq.zzkz().zzvk().zzwa();
                s2 = zzq.zzkz().zzvk().zzwc();
                s1 = null;
            }
            else {
                s = zzqd0.zzlx();
                s1 = zzqd0.zzly();
                s2 = zzqd0.zzlz();
                if(s != null) {
                    zzq.zzkz().zzvk().zzef(s);
                }
                if(s2 != null) {
                    zzq.zzkz().zzvk().zzeg(s2);
                }
            }
            Bundle bundle1 = new Bundle(1);
            if(!zzq.zzkz().zzvk().zzwb()) {
                if(s2 == null || TextUtils.isEmpty(s2)) {
                    bundle1.putString("v_fp_vertical", "no_hash");
                }
                else {
                    bundle1.putString("v_fp_vertical", s2);
                }
            }
            if(s != null && !zzq.zzkz().zzvk().zzvz()) {
                bundle1.putString("fingerprint", s);
                if(!s.equals(s1)) {
                    bundle1.putString("v_fp", s1);
                }
            }
            if(!bundle1.isEmpty()) {
                bundle0 = bundle1;
            }
        }
        return new zzcvq(bundle0);
    }
}

