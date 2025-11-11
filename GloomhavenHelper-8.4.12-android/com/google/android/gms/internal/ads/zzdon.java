package com.google.android.gms.internal.ads;

final class zzdon extends zzj implements Runnable {
    private final Runnable zzhdm;

    public zzdon(Runnable runnable0) {
        this.zzhdm = (Runnable)zzdlg.checkNotNull(runnable0);
    }

    @Override
    public final void run() {
        try {
            this.zzhdm.run();
        }
        catch(Throwable throwable0) {
            this.setException(throwable0);
            throw zzdlj.zzh(throwable0);
        }
    }
}

