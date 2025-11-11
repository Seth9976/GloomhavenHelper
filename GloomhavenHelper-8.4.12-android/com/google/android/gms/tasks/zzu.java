package com.google.android.gms.tasks;

import android.app.Activity;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzu extends Task {
    static class zza extends LifecycleCallback {
        private final List zzac;

        private zza(LifecycleFragment lifecycleFragment0) {
            super(lifecycleFragment0);
            this.zzac = new ArrayList();
            this.mLifecycleFragment.addCallback("TaskOnStopCallback", this);
        }

        @Override  // com.google.android.gms.common.api.internal.LifecycleCallback
        @MainThread
        public void onStop() {
            synchronized(this.zzac) {
                for(Object object0: this.zzac) {
                    zzq zzq0 = (zzq)((WeakReference)object0).get();
                    if(zzq0 != null) {
                        zzq0.cancel();
                    }
                }
                this.zzac.clear();
            }
        }

        public static zza zza(Activity activity0) {
            LifecycleFragment lifecycleFragment0 = zza.getFragment(activity0);
            zza zzu$zza0 = (zza)lifecycleFragment0.getCallbackOrNull("TaskOnStopCallback", zza.class);
            return zzu$zza0 == null ? new zza(lifecycleFragment0) : zzu$zza0;
        }

        public final void zzb(zzq zzq0) {
            synchronized(this.zzac) {
                WeakReference weakReference0 = new WeakReference(zzq0);
                this.zzac.add(weakReference0);
            }
        }
    }

    private final Object mLock;
    @GuardedBy("mLock")
    private Object zzaa;
    @GuardedBy("mLock")
    private Exception zzab;
    private final zzr zzx;
    @GuardedBy("mLock")
    private boolean zzy;
    private volatile boolean zzz;

    zzu() {
        this.mLock = new Object();
        this.zzx = new zzr();
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task addOnCanceledListener(@NonNull Activity activity0, @NonNull OnCanceledListener onCanceledListener0) {
        zzg zzg0 = new zzg(TaskExecutors.MAIN_THREAD, onCanceledListener0);
        this.zzx.zza(zzg0);
        zza.zza(activity0).zzb(zzg0);
        this.zze();
        return this;
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task addOnCanceledListener(@NonNull OnCanceledListener onCanceledListener0) {
        return this.addOnCanceledListener(TaskExecutors.MAIN_THREAD, onCanceledListener0);
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task addOnCanceledListener(@NonNull Executor executor0, @NonNull OnCanceledListener onCanceledListener0) {
        zzg zzg0 = new zzg(executor0, onCanceledListener0);
        this.zzx.zza(zzg0);
        this.zze();
        return this;
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task addOnCompleteListener(@NonNull Activity activity0, @NonNull OnCompleteListener onCompleteListener0) {
        zzi zzi0 = new zzi(TaskExecutors.MAIN_THREAD, onCompleteListener0);
        this.zzx.zza(zzi0);
        zza.zza(activity0).zzb(zzi0);
        this.zze();
        return this;
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task addOnCompleteListener(@NonNull OnCompleteListener onCompleteListener0) {
        return this.addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener0);
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task addOnCompleteListener(@NonNull Executor executor0, @NonNull OnCompleteListener onCompleteListener0) {
        zzi zzi0 = new zzi(executor0, onCompleteListener0);
        this.zzx.zza(zzi0);
        this.zze();
        return this;
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task addOnFailureListener(@NonNull Activity activity0, @NonNull OnFailureListener onFailureListener0) {
        zzk zzk0 = new zzk(TaskExecutors.MAIN_THREAD, onFailureListener0);
        this.zzx.zza(zzk0);
        zza.zza(activity0).zzb(zzk0);
        this.zze();
        return this;
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task addOnFailureListener(@NonNull OnFailureListener onFailureListener0) {
        return this.addOnFailureListener(TaskExecutors.MAIN_THREAD, onFailureListener0);
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task addOnFailureListener(@NonNull Executor executor0, @NonNull OnFailureListener onFailureListener0) {
        zzk zzk0 = new zzk(executor0, onFailureListener0);
        this.zzx.zza(zzk0);
        this.zze();
        return this;
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task addOnSuccessListener(@NonNull Activity activity0, @NonNull OnSuccessListener onSuccessListener0) {
        zzm zzm0 = new zzm(TaskExecutors.MAIN_THREAD, onSuccessListener0);
        this.zzx.zza(zzm0);
        zza.zza(activity0).zzb(zzm0);
        this.zze();
        return this;
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task addOnSuccessListener(@NonNull OnSuccessListener onSuccessListener0) {
        return this.addOnSuccessListener(TaskExecutors.MAIN_THREAD, onSuccessListener0);
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task addOnSuccessListener(@NonNull Executor executor0, @NonNull OnSuccessListener onSuccessListener0) {
        zzm zzm0 = new zzm(executor0, onSuccessListener0);
        this.zzx.zza(zzm0);
        this.zze();
        return this;
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task continueWith(@NonNull Continuation continuation0) {
        return this.continueWith(TaskExecutors.MAIN_THREAD, continuation0);
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task continueWith(@NonNull Executor executor0, @NonNull Continuation continuation0) {
        Task task0 = new zzu();
        zzc zzc0 = new zzc(executor0, continuation0, ((zzu)task0));
        this.zzx.zza(zzc0);
        this.zze();
        return task0;
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task continueWithTask(@NonNull Continuation continuation0) {
        return this.continueWithTask(TaskExecutors.MAIN_THREAD, continuation0);
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task continueWithTask(@NonNull Executor executor0, @NonNull Continuation continuation0) {
        Task task0 = new zzu();
        zze zze0 = new zze(executor0, continuation0, ((zzu)task0));
        this.zzx.zza(zze0);
        this.zze();
        return task0;
    }

    @Override  // com.google.android.gms.tasks.Task
    @Nullable
    public final Exception getException() {
        synchronized(this.mLock) {
        }
        return this.zzab;
    }

    @Override  // com.google.android.gms.tasks.Task
    public final Object getResult() {
        synchronized(this.mLock) {
            this.zzb();
            this.zzd();
            if(this.zzab == null) {
                return this.zzaa;
            }
        }
        throw new RuntimeExecutionException(this.zzab);
    }

    @Override  // com.google.android.gms.tasks.Task
    public final Object getResult(@NonNull Class class0) throws Throwable {
        synchronized(this.mLock) {
            this.zzb();
            this.zzd();
            if(!class0.isInstance(this.zzab)) {
                if(this.zzab != null) {
                    throw new RuntimeExecutionException(this.zzab);
                }
                return this.zzaa;
            }
        }
        throw (Throwable)class0.cast(this.zzab);
    }

    @Override  // com.google.android.gms.tasks.Task
    public final boolean isCanceled() {
        return this.zzz;
    }

    @Override  // com.google.android.gms.tasks.Task
    public final boolean isComplete() {
        synchronized(this.mLock) {
        }
        return this.zzy;
    }

    @Override  // com.google.android.gms.tasks.Task
    public final boolean isSuccessful() {
        synchronized(this.mLock) {
        }
        return this.zzy && !this.zzz && this.zzab == null;
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task onSuccessTask(@NonNull SuccessContinuation successContinuation0) {
        return this.onSuccessTask(TaskExecutors.MAIN_THREAD, successContinuation0);
    }

    @Override  // com.google.android.gms.tasks.Task
    @NonNull
    public final Task onSuccessTask(Executor executor0, SuccessContinuation successContinuation0) {
        Task task0 = new zzu();
        zzo zzo0 = new zzo(executor0, successContinuation0, ((zzu)task0));
        this.zzx.zza(zzo0);
        this.zze();
        return task0;
    }

    public final void setException(@NonNull Exception exception0) {
        Preconditions.checkNotNull(exception0, "Exception must not be null");
        synchronized(this.mLock) {
            this.zzc();
            this.zzy = true;
            this.zzab = exception0;
        }
        this.zzx.zza(this);
    }

    public final void setResult(Object object0) {
        synchronized(this.mLock) {
            this.zzc();
            this.zzy = true;
            this.zzaa = object0;
        }
        this.zzx.zza(this);
    }

    public final boolean trySetException(@NonNull Exception exception0) {
        Preconditions.checkNotNull(exception0, "Exception must not be null");
        synchronized(this.mLock) {
            if(this.zzy) {
                return false;
            }
            this.zzy = true;
            this.zzab = exception0;
        }
        this.zzx.zza(this);
        return true;
    }

    public final boolean trySetResult(Object object0) {
        synchronized(this.mLock) {
            if(this.zzy) {
                return false;
            }
            this.zzy = true;
            this.zzaa = object0;
        }
        this.zzx.zza(this);
        return true;
    }

    public final boolean zza() {
        synchronized(this.mLock) {
            if(this.zzy) {
                return false;
            }
            this.zzy = true;
            this.zzz = true;
        }
        this.zzx.zza(this);
        return true;
    }

    @GuardedBy("mLock")
    private final void zzb() {
        Preconditions.checkState(this.zzy, "Task is not yet complete");
    }

    @GuardedBy("mLock")
    private final void zzc() {
        Preconditions.checkState(!this.zzy, "Task is already complete");
    }

    @GuardedBy("mLock")
    private final void zzd() {
        if(this.zzz) {
            throw new CancellationException("Task is already canceled.");
        }
    }

    private final void zze() {
        synchronized(this.mLock) {
            if(!this.zzy) {
                return;
            }
        }
        this.zzx.zza(this);
    }
}

