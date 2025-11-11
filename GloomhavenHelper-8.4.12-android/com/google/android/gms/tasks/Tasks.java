package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

public final class Tasks {
    static final class zza implements zzb {
        private final CountDownLatch zzaf;

        private zza() {
            this.zzaf = new CountDownLatch(1);
        }

        zza(zzv zzv0) {
        }

        public final void await() throws InterruptedException {
            this.zzaf.await();
        }

        public final boolean await(long v, TimeUnit timeUnit0) throws InterruptedException {
            return this.zzaf.await(v, timeUnit0);
        }

        @Override  // com.google.android.gms.tasks.OnCanceledListener
        public final void onCanceled() {
            this.zzaf.countDown();
        }

        @Override  // com.google.android.gms.tasks.OnFailureListener
        public final void onFailure(@NonNull Exception exception0) {
            this.zzaf.countDown();
        }

        @Override  // com.google.android.gms.tasks.OnSuccessListener
        public final void onSuccess(Object object0) {
            this.zzaf.countDown();
        }
    }

    interface zzb extends OnCanceledListener, OnFailureListener, OnSuccessListener {
    }

    static final class zzc implements zzb {
        private final Object mLock;
        private final zzu zza;
        @GuardedBy("mLock")
        private Exception zzab;
        private final int zzag;
        @GuardedBy("mLock")
        private int zzah;
        @GuardedBy("mLock")
        private int zzai;
        @GuardedBy("mLock")
        private int zzaj;
        @GuardedBy("mLock")
        private boolean zzak;

        public zzc(int v, zzu zzu0) {
            this.mLock = new Object();
            this.zzag = v;
            this.zza = zzu0;
        }

        @Override  // com.google.android.gms.tasks.OnCanceledListener
        public final void onCanceled() {
            synchronized(this.mLock) {
                ++this.zzaj;
                this.zzak = true;
                this.zzf();
            }
        }

        @Override  // com.google.android.gms.tasks.OnFailureListener
        public final void onFailure(@NonNull Exception exception0) {
            synchronized(this.mLock) {
                ++this.zzai;
                this.zzab = exception0;
                this.zzf();
            }
        }

        @Override  // com.google.android.gms.tasks.OnSuccessListener
        public final void onSuccess(Object object0) {
            synchronized(this.mLock) {
                ++this.zzah;
                this.zzf();
            }
        }

        @GuardedBy("mLock")
        private final void zzf() {
            int v = this.zzai;
            int v1 = this.zzag;
            if(this.zzah + v + this.zzaj == v1) {
                if(this.zzab != null) {
                    ExecutionException executionException0 = new ExecutionException(v + " out of " + v1 + " underlying tasks failed", this.zzab);
                    this.zza.setException(executionException0);
                    return;
                }
                if(this.zzak) {
                    this.zza.zza();
                    return;
                }
                this.zza.setResult(null);
            }
        }
    }

    public static Object await(@NonNull Task task0) throws ExecutionException, InterruptedException {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotNull(task0, "Task must not be null");
        if(task0.isComplete()) {
            return Tasks.zzb(task0);
        }
        zza tasks$zza0 = new zza(null);
        Tasks.zza(task0, tasks$zza0);
        tasks$zza0.await();
        return Tasks.zzb(task0);
    }

    public static Object await(@NonNull Task task0, long v, @NonNull TimeUnit timeUnit0) throws ExecutionException, InterruptedException, TimeoutException {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotNull(task0, "Task must not be null");
        Preconditions.checkNotNull(timeUnit0, "TimeUnit must not be null");
        if(task0.isComplete()) {
            return Tasks.zzb(task0);
        }
        zza tasks$zza0 = new zza(null);
        Tasks.zza(task0, tasks$zza0);
        if(!tasks$zza0.await(v, timeUnit0)) {
            throw new TimeoutException("Timed out waiting for Task");
        }
        return Tasks.zzb(task0);
    }

    public static Task call(@NonNull Callable callable0) {
        return Tasks.call(TaskExecutors.MAIN_THREAD, callable0);
    }

    public static Task call(@NonNull Executor executor0, @NonNull Callable callable0) {
        Preconditions.checkNotNull(executor0, "Executor must not be null");
        Preconditions.checkNotNull(callable0, "Callback must not be null");
        Task task0 = new zzu();
        executor0.execute(new zzv(((zzu)task0), callable0));
        return task0;
    }

    public static Task forCanceled() {
        Task task0 = new zzu();
        ((zzu)task0).zza();
        return task0;
    }

    public static Task forException(@NonNull Exception exception0) {
        Task task0 = new zzu();
        ((zzu)task0).setException(exception0);
        return task0;
    }

    public static Task forResult(Object object0) {
        Task task0 = new zzu();
        ((zzu)task0).setResult(object0);
        return task0;
    }

    public static Task whenAll(Collection collection0) {
        if(collection0.isEmpty()) {
            return Tasks.forResult(null);
        }
        for(Object object0: collection0) {
            if(((Task)object0) == null) {
                throw new NullPointerException("null tasks are not accepted");
            }
            if(false) {
                break;
            }
        }
        Task task0 = new zzu();
        zzc tasks$zzc0 = new zzc(collection0.size(), ((zzu)task0));
        for(Object object1: collection0) {
            Tasks.zza(((Task)object1), tasks$zzc0);
        }
        return task0;
    }

    public static Task whenAll(Task[] arr_task) {
        return arr_task.length == 0 ? Tasks.forResult(null) : Tasks.whenAll(Arrays.asList(arr_task));
    }

    public static Task whenAllComplete(Collection collection0) {
        return Tasks.whenAll(collection0).continueWithTask(new zzx(collection0));
    }

    public static Task whenAllComplete(Task[] arr_task) {
        return Tasks.whenAllComplete(Arrays.asList(arr_task));
    }

    public static Task whenAllSuccess(Collection collection0) {
        return Tasks.whenAll(collection0).continueWith(new zzw(collection0));
    }

    public static Task whenAllSuccess(Task[] arr_task) {
        return Tasks.whenAllSuccess(Arrays.asList(arr_task));
    }

    private static void zza(Task task0, zzb tasks$zzb0) {
        task0.addOnSuccessListener(TaskExecutors.zzw, tasks$zzb0);
        task0.addOnFailureListener(TaskExecutors.zzw, tasks$zzb0);
        task0.addOnCanceledListener(TaskExecutors.zzw, tasks$zzb0);
    }

    private static Object zzb(Task task0) throws ExecutionException {
        if(task0.isSuccessful()) {
            return task0.getResult();
        }
        if(!task0.isCanceled()) {
            throw new ExecutionException(task0.getException());
        }
        throw new CancellationException("Task is already canceled");
    }
}

