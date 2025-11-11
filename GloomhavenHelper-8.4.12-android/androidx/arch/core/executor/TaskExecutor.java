package androidx.arch.core.executor;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class TaskExecutor {
    public abstract void executeOnDiskIO(@NonNull Runnable arg1);

    public void executeOnMainThread(@NonNull Runnable runnable0) {
        if(this.isMainThread()) {
            runnable0.run();
            return;
        }
        this.postToMainThread(runnable0);
    }

    public abstract boolean isMainThread();

    public abstract void postToMainThread(@NonNull Runnable arg1);
}

