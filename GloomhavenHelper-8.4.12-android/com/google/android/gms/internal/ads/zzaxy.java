package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

public final class zzaxy {
    private final List zzdvj;
    private final List zzdvk;
    private final List zzdvl;

    public zzaxy() {
        this.zzdvj = new ArrayList();
        this.zzdvk = new ArrayList();
        this.zzdvl = new ArrayList();
    }

    public final zzaxy zza(String s, double f, double f1) {
        int v;
        for(v = 0; v < this.zzdvj.size(); ++v) {
            double f2 = (double)(((Double)this.zzdvl.get(v)));
            if(f < f2 || f2 == f && f1 < ((double)(((Double)this.zzdvk.get(v))))) {
                break;
            }
        }
        this.zzdvj.add(v, s);
        this.zzdvl.add(v, f);
        this.zzdvk.add(v, f1);
        return this;
    }

    public final zzaxt zzxf() {
        return new zzaxt(this, null);
    }
}

