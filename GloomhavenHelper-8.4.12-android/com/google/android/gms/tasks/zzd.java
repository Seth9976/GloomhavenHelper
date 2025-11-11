package com.google.android.gms.tasks;

final class zzd implements Runnable {
    private final Task zzg;
    private final zzc zzh;

    zzd(zzc zzc0, Task task0) {
        this.zzh = zzc0;
        this.zzg = task0;
        super();
    }

    @Override
    public final void run() {
        Object object0;
        if(this.zzg.isCanceled()) {
            zzc.zza(this.zzh).zza();
            return;
        }
        try {
            object0 = zzc.zzb(this.zzh).then(this.zzg);
        }
        catch(RuntimeExecutionException runtimeExecutionException0) {
            if(runtimeExecutionException0.getCause() instanceof Exception) {
                zzc.zza(this.zzh).setException(((Exception)runtimeExecutionException0.getCause()));
                return;
            }
            zzc.zza(this.zzh).setException(runtimeExecutionException0);
            return;
        }
        catch(Exception exception0) {
            zzc.zza(this.zzh).setException(exception0);
            return;
        }
        zzc.zza(this.zzh).setResult(object0);
    }
}

