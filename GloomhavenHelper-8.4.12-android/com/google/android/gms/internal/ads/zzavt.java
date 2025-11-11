package com.google.android.gms.internal.ads;

final class zzavt extends zzawb {
    private final zzavr zzdsc;

    zzavt(zzavr zzavr0) {
        this.zzdsc = zzavr0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzawb
    public final void zztz() {
        zzaab zzaab0 = new zzaab(this.zzdsc.zzur, this.zzdsc.zzblu.zzbmj);
        synchronized(this.zzdsc.lock) {
            try {
                zzaac.zza(this.zzdsc.zzdrp, zzaab0);
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                zzawf.zzd("Cannot config CSI reporter.", illegalArgumentException0);
            }
        }
    }
}

