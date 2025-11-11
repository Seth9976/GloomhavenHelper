package com.google.android.gms.tasks;

final class zzf implements Runnable {
    private final Task zzg;
    private final zze zzi;

    zzf(zze zze0, Task task0) {
        this.zzi = zze0;
        this.zzg = task0;
        super();
    }

    @Override
    public final void run() {
        Task task0;
        try {
            task0 = (Task)zze.zza(this.zzi).then(this.zzg);
        }
        catch(RuntimeExecutionException runtimeExecutionException0) {
            if(runtimeExecutionException0.getCause() instanceof Exception) {
                zze.zzb(this.zzi).setException(((Exception)runtimeExecutionException0.getCause()));
                return;
            }
            zze.zzb(this.zzi).setException(runtimeExecutionException0);
            return;
        }
        catch(Exception exception0) {
            zze.zzb(this.zzi).setException(exception0);
            return;
        }
        if(task0 == null) {
            NullPointerException nullPointerException0 = new NullPointerException("Continuation returned null");
            this.zzi.onFailure(nullPointerException0);
            return;
        }
        task0.addOnSuccessListener(TaskExecutors.zzw, this.zzi);
        task0.addOnFailureListener(TaskExecutors.zzw, this.zzi);
        task0.addOnCanceledListener(TaskExecutors.zzw, this.zzi);
    }
}

