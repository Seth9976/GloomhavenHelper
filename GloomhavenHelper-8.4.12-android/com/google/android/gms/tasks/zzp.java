package com.google.android.gms.tasks;

import java.util.concurrent.CancellationException;

final class zzp implements Runnable {
    private final Task zzg;
    private final zzo zzs;

    zzp(zzo zzo0, Task task0) {
        this.zzs = zzo0;
        this.zzg = task0;
        super();
    }

    @Override
    public final void run() {
        Task task0;
        try {
            task0 = zzo.zza(this.zzs).then(this.zzg.getResult());
        }
        catch(RuntimeExecutionException runtimeExecutionException0) {
            if(runtimeExecutionException0.getCause() instanceof Exception) {
                this.zzs.onFailure(((Exception)runtimeExecutionException0.getCause()));
                return;
            }
            this.zzs.onFailure(runtimeExecutionException0);
            return;
        }
        catch(CancellationException unused_ex) {
            this.zzs.onCanceled();
            return;
        }
        catch(Exception exception0) {
            this.zzs.onFailure(exception0);
            return;
        }
        if(task0 == null) {
            NullPointerException nullPointerException0 = new NullPointerException("Continuation returned null");
            this.zzs.onFailure(nullPointerException0);
            return;
        }
        task0.addOnSuccessListener(TaskExecutors.zzw, this.zzs);
        task0.addOnFailureListener(TaskExecutors.zzw, this.zzs);
        task0.addOnCanceledListener(TaskExecutors.zzw, this.zzs);
    }
}

