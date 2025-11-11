package com.google.android.gms.internal.ads;

final class zzht implements Runnable {
    private final zzhq zzahq;
    private final zzis zzahr;

    zzht(zzhq zzhq0, zzis zzis0) {
        this.zzahq = zzhq0;
        this.zzahr = zzis0;
        super();
    }

    @Override
    public final void run() {
        this.zzahq.zzahm.zzc(this.zzahr);
    }
}

