package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;

final class zzly {
    private final zzji zzapu;
    private final zzjg[] zzbbi;
    private zzjg zzbbj;

    public zzly(zzjg[] arr_zzjg, zzji zzji0) {
        this.zzbbi = arr_zzjg;
        this.zzapu = zzji0;
    }

    public final void release() {
        zzjg zzjg0 = this.zzbbj;
        if(zzjg0 != null) {
            zzjg0.release();
            this.zzbbj = null;
        }
    }

    public final zzjg zza(zzjf zzjf0, Uri uri0) throws IOException, InterruptedException {
        zzjg zzjg0 = this.zzbbj;
        if(zzjg0 != null) {
            return zzjg0;
        }
        zzjg[] arr_zzjg = this.zzbbi;
        for(int v = 0; v < arr_zzjg.length; ++v) {
            zzjg zzjg1 = arr_zzjg[v];
            try {
                if(zzjg1.zza(zzjf0)) {
                    this.zzbbj = zzjg1;
                    break;
                }
            }
            catch(EOFException unused_ex) {
                zzjf0.zzgm();
                continue;
            }
            finally {
                zzjf0.zzgm();
            }
        }
        zzjg zzjg2 = this.zzbbj;
        if(zzjg2 == null) {
            throw new zzmt("None of the available extractors (" + zzop.zza(this.zzbbi) + ") could read the stream.", uri0);
        }
        zzjg2.zza(this.zzapu);
        return this.zzbbj;
    }
}

