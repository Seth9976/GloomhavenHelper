package com.google.android.gms.internal.ads;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public final class zzc extends Thread {
    private static final boolean DEBUG;
    private final BlockingQueue zza;
    private final BlockingQueue zzb;
    private final zza zzc;
    private final zzaa zzd;
    private volatile boolean zze;
    private final zze zzf;

    static {
        zzc.DEBUG = zzag.DEBUG;
    }

    public zzc(BlockingQueue blockingQueue0, BlockingQueue blockingQueue1, zza zza0, zzaa zzaa0) {
        this.zze = false;
        this.zza = blockingQueue0;
        this.zzb = blockingQueue1;
        this.zzc = zza0;
        this.zzd = zzaa0;
        this.zzf = new zze(this);
    }

    private final void processRequest() throws InterruptedException {
        zzq zzq0 = (zzq)this.zza.take();
        zzq0.zzb("cache-queue-take");
        zzq0.zza(1);
        try {
            String s = zzq0.zze();
            zzd zzd0 = this.zzc.zza(s);
            if(zzd0 == null) {
                zzq0.zzb("cache-miss");
                if(!this.zzf.zzb(zzq0)) {
                    this.zzb.put(zzq0);
                }
                return;
            }
            if(zzd0.zza()) {
                zzq0.zzb("cache-hit-expired");
                zzq0.zza(zzd0);
                if(!this.zzf.zzb(zzq0)) {
                    this.zzb.put(zzq0);
                }
                return;
            }
            zzq0.zzb("cache-hit");
            zzz zzz0 = zzq0.zza(new zzo(zzd0.data, zzd0.zzl));
            zzq0.zzb("cache-hit-parsed");
            if(zzd0.zzk >= System.currentTimeMillis()) {
                this.zzd.zzb(zzq0, zzz0);
            }
            else {
                zzq0.zzb("cache-hit-refresh-needed");
                zzq0.zza(zzd0);
                zzz0.zzbj = true;
                if(this.zzf.zzb(zzq0)) {
                    this.zzd.zzb(zzq0, zzz0);
                }
                else {
                    zzf zzf0 = new zzf(this, zzq0);
                    this.zzd.zza(zzq0, zzz0, zzf0);
                }
            }
        }
        finally {
            zzq0.zza(2);
        }
    }

    public final void quit() {
        this.zze = true;
        this.interrupt();
    }

    @Override
    public final void run() {
        if(zzc.DEBUG) {
            zzag.v("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.zzc.initialize();
        while(true) {
            try {
            label_4:
                this.processRequest();
                goto label_4;
            }
            catch(InterruptedException unused_ex) {
            }
            if(this.zze) {
                break;
            }
            zzag.e("Ignoring spurious interrupt of CacheDispatcher thread; use quit() to terminate it", new Object[0]);
        }
        Thread.currentThread().interrupt();
    }
}

