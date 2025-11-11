package androidx.arch.core.executor;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@RestrictTo({Scope.LIBRARY_GROUP})
public class DefaultTaskExecutor extends TaskExecutor {
    private final ExecutorService mDiskIO;
    private final Object mLock;
    @Nullable
    private volatile Handler mMainHandler;

    public DefaultTaskExecutor() {
        this.mLock = new Object();
        this.mDiskIO = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private static final String THREAD_NAME_STEM = "arch_disk_io_%d";
            private final AtomicInteger mThreadId;

            {
                this.mThreadId = new AtomicInteger(0);
            }

            @Override
            public Thread newThread(Runnable runnable0) {
                Thread thread0 = new Thread(runnable0);
                thread0.setName(String.format("arch_disk_io_%d", this.mThreadId.getAndIncrement()));
                return thread0;
            }
        });
    }

    @Override  // androidx.arch.core.executor.TaskExecutor
    public void executeOnDiskIO(Runnable runnable0) {
        this.mDiskIO.execute(runnable0);
    }

    @Override  // androidx.arch.core.executor.TaskExecutor
    public boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override  // androidx.arch.core.executor.TaskExecutor
    public void postToMainThread(Runnable runnable0) {
        if(this.mMainHandler == null) {
            Object object0 = this.mLock;
            synchronized(object0) {
                if(this.mMainHandler == null) {
                    this.mMainHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        this.mMainHandler.post(runnable0);
    }
}

