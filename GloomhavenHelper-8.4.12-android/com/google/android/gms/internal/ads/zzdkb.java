package com.google.android.gms.internal.ads;

import androidx.annotation.NonNull;
import java.io.File;
import java.util.Arrays;

public final class zzdkb {
    private final zzgb zzgxz;
    private final File zzgya;
    private final File zzgyb;
    private final File zzgyc;
    private byte[] zzgyd;

    public zzdkb(@NonNull zzgb zzgb0, @NonNull File file0, @NonNull File file1, @NonNull File file2) {
        this.zzgxz = zzgb0;
        this.zzgya = file0;
        this.zzgyb = file2;
        this.zzgyc = file1;
    }

    public final boolean zza() {
        return System.currentTimeMillis() / 1000L > this.zzgxz.zzde();
    }

    public final zzgb zzatn() {
        return this.zzgxz;
    }

    public final File zzato() {
        return this.zzgya;
    }

    public final File zzatp() {
        return this.zzgyb;
    }

    public final byte[] zzatq() {
        if(this.zzgyd == null) {
            this.zzgyd = zzdkd.zze(this.zzgyc);
        }
        return this.zzgyd == null ? null : Arrays.copyOf(this.zzgyd, this.zzgyd.length);
    }

    public final boolean zzff(long v) {
        return this.zzgxz.zzde() - System.currentTimeMillis() / 1000L < 3600L;
    }
}

