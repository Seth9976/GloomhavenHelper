package com.google.android.gms.internal.ads;

final class zzbbo implements Runnable {
    private boolean zzbpn;
    private zzbav zzdzw;

    zzbbo(zzbav zzbav0) {
        this.zzbpn = false;
        this.zzdzw = zzbav0;
    }

    public final void pause() {
        this.zzbpn = true;
        this.zzdzw.zzyg();
    }

    public final void resume() {
        this.zzbpn = false;
        this.zzzb();
    }

    @Override
    public final void run() {
        if(!this.zzbpn) {
            this.zzdzw.zzyg();
            this.zzzb();
        }
    }

    private final void zzzb() {
        zzawo.zzdtx.removeCallbacks(this);
        zzawo.zzdtx.postDelayed(this, 0xFAL);
    }
}

