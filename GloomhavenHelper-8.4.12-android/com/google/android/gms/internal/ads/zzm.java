package com.google.android.gms.internal.ads;

import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public final class zzm extends Thread {
    private final zzn zzaa;
    private final zza zzc;
    private final zzaa zzd;
    private volatile boolean zze;
    private final BlockingQueue zzz;

    public zzm(BlockingQueue blockingQueue0, zzn zzn0, zza zza0, zzaa zzaa0) {
        this.zze = false;
        this.zzz = blockingQueue0;
        this.zzaa = zzn0;
        this.zzc = zza0;
        this.zzd = zzaa0;
    }

    private final void processRequest() throws InterruptedException {
        zzq zzq0 = (zzq)this.zzz.take();
        long v = SystemClock.elapsedRealtime();
        zzq0.zza(3);
        try {
            zzq0.zzb("network-queue-take");
            TrafficStats.setThreadStatsTag(zzq0.zzd());
            zzo zzo0 = this.zzaa.zzc(zzq0);
            zzq0.zzb("network-http-complete");
            if(!zzo0.zzac || !zzq0.zzl()) {
                zzz zzz0 = zzq0.zza(zzo0);
                zzq0.zzb("network-parse-complete");
                if(zzq0.zzh() && zzz0.zzbh != null) {
                    String s = zzq0.zze();
                    this.zzc.zza(s, zzz0.zzbh);
                    zzq0.zzb("network-cache-written");
                }
                zzq0.zzk();
                this.zzd.zzb(zzq0, zzz0);
                zzq0.zza(zzz0);
                return;
            }
            zzq0.zzc("not-modified");
            zzq0.zzm();
        }
        catch(zzae zzae0) {
            zzae0.zza(SystemClock.elapsedRealtime() - v);
            this.zzd.zza(zzq0, zzae0);
            zzq0.zzm();
        }
        catch(Exception exception0) {
            zzag.zza(exception0, "Unhandled exception %s", new Object[]{exception0.toString()});
            zzae zzae1 = new zzae(exception0);
            zzae1.zza(SystemClock.elapsedRealtime() - v);
            this.zzd.zza(zzq0, zzae1);
            zzq0.zzm();
        }
        finally {
            zzq0.zza(4);
        }
    }

    public final void quit() {
        this.zze = true;
        this.interrupt();
    }

    @Override
    public final void run() {
        Process.setThreadPriority(10);
        while(true) {
            try {
            label_1:
                this.processRequest();
                goto label_1;
            }
            catch(InterruptedException unused_ex) {
            }
            if(this.zze) {
                break;
            }
            zzag.e("Ignoring spurious interrupt of NetworkDispatcher thread; use quit() to terminate it", new Object[0]);
        }
        Thread.currentThread().interrupt();
    }
}

