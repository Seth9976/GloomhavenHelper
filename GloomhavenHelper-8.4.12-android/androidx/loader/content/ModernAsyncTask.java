package androidx.loader.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

abstract class ModernAsyncTask {
    static class AsyncTaskResult {
        final Object[] mData;
        final ModernAsyncTask mTask;

        AsyncTaskResult(ModernAsyncTask modernAsyncTask0, Object[] arr_object) {
            this.mTask = modernAsyncTask0;
            this.mData = arr_object;
        }
    }

    static class InternalHandler extends Handler {
        InternalHandler() {
            super(Looper.getMainLooper());
        }

        @Override  // android.os.Handler
        public void handleMessage(Message message0) {
            AsyncTaskResult modernAsyncTask$AsyncTaskResult0 = (AsyncTaskResult)message0.obj;
            switch(message0.what) {
                case 1: {
                    modernAsyncTask$AsyncTaskResult0.mTask.finish(modernAsyncTask$AsyncTaskResult0.mData[0]);
                    return;
                }
                case 2: {
                    modernAsyncTask$AsyncTaskResult0.mTask.onProgressUpdate(modernAsyncTask$AsyncTaskResult0.mData);
                }
            }
        }
    }

    public static enum Status {
        PENDING,
        RUNNING,
        FINISHED;

    }

    static abstract class WorkerRunnable implements Callable {
        Object[] mParams;

    }

    private static final int CORE_POOL_SIZE = 5;
    private static final int KEEP_ALIVE = 1;
    private static final String LOG_TAG = "AsyncTask";
    private static final int MAXIMUM_POOL_SIZE = 0x80;
    private static final int MESSAGE_POST_PROGRESS = 2;
    private static final int MESSAGE_POST_RESULT = 1;
    public static final Executor THREAD_POOL_EXECUTOR;
    final AtomicBoolean mCancelled;
    private final FutureTask mFuture;
    private volatile Status mStatus;
    final AtomicBoolean mTaskInvoked;
    private final WorkerRunnable mWorker;
    private static volatile Executor sDefaultExecutor;
    private static InternalHandler sHandler;
    private static final BlockingQueue sPoolWorkQueue;
    private static final ThreadFactory sThreadFactory;

    static {
        ModernAsyncTask.sThreadFactory = new ThreadFactory() {
            private final AtomicInteger mCount;

            {
                this.mCount = new AtomicInteger(1);
            }

            @Override
            public Thread newThread(Runnable runnable0) {
                return new Thread(runnable0, "ModernAsyncTask #" + this.mCount.getAndIncrement());
            }
        };
        ModernAsyncTask.sPoolWorkQueue = new LinkedBlockingQueue(10);
        ModernAsyncTask.THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(5, 0x80, 1L, TimeUnit.SECONDS, ModernAsyncTask.sPoolWorkQueue, ModernAsyncTask.sThreadFactory);
        ModernAsyncTask.sDefaultExecutor = ModernAsyncTask.THREAD_POOL_EXECUTOR;
    }

    ModernAsyncTask() {
        this.mStatus = Status.PENDING;
        this.mCancelled = new AtomicBoolean();
        this.mTaskInvoked = new AtomicBoolean();
        this.mWorker = new WorkerRunnable() {
            @Override
            public Object call() throws Exception {
                ModernAsyncTask.this.mTaskInvoked.set(true);
                Object object0 = null;
                try {
                    Process.setThreadPriority(10);
                    object0 = ModernAsyncTask.this.doInBackground(this.mParams);
                    Binder.flushPendingCommands();
                }
                catch(Throwable throwable0) {
                    try {
                        ModernAsyncTask.this.mCancelled.set(true);
                        throw throwable0;
                    }
                    catch(Throwable throwable1) {
                        ModernAsyncTask.this.postResult(object0);
                        throw throwable1;
                    }
                }
                ModernAsyncTask.this.postResult(object0);
                return object0;
            }
        };
        this.mFuture = new FutureTask(this.mWorker) {
            @Override
            protected void done() {
                try {
                    Object object0 = this.get();
                    ModernAsyncTask.this.postResultIfNotInvoked(object0);
                }
                catch(InterruptedException interruptedException0) {
                    Log.w("AsyncTask", interruptedException0);
                }
                catch(ExecutionException executionException0) {
                    throw new RuntimeException("An error occurred while executing doInBackground()", executionException0.getCause());
                }
                catch(CancellationException unused_ex) {
                    ModernAsyncTask.this.postResultIfNotInvoked(null);
                }
                catch(Throwable throwable0) {
                    throw new RuntimeException("An error occurred while executing doInBackground()", throwable0);
                }
            }
        };
    }

    public final boolean cancel(boolean z) {
        this.mCancelled.set(true);
        return this.mFuture.cancel(z);
    }

    protected abstract Object doInBackground(Object[] arg1);

    public static void execute(Runnable runnable0) {
        ModernAsyncTask.sDefaultExecutor.execute(runnable0);
    }

    public final ModernAsyncTask execute(Object[] arr_object) {
        return this.executeOnExecutor(ModernAsyncTask.sDefaultExecutor, arr_object);
    }

    public final ModernAsyncTask executeOnExecutor(Executor executor0, Object[] arr_object) {
        if(this.mStatus != Status.PENDING) {
            switch(this.mStatus) {
                case RUNNING: {
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                }
                case FINISHED: {
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
                }
                default: {
                    throw new IllegalStateException("We should never reach this state");
                }
            }
        }
        this.mStatus = Status.RUNNING;
        this.mWorker.mParams = arr_object;
        executor0.execute(this.mFuture);
        return this;
    }

    void finish(Object object0) {
        if(this.isCancelled()) {
            this.onCancelled(object0);
        }
        else {
            this.onPostExecute(object0);
        }
        this.mStatus = Status.FINISHED;
    }

    public final Object get() throws InterruptedException, ExecutionException {
        return this.mFuture.get();
    }

    public final Object get(long v, TimeUnit timeUnit0) throws InterruptedException, ExecutionException, TimeoutException {
        return this.mFuture.get(v, timeUnit0);
    }

    private static Handler getHandler() {
        synchronized(ModernAsyncTask.class) {
            if(ModernAsyncTask.sHandler == null) {
                ModernAsyncTask.sHandler = new InternalHandler();
            }
            return ModernAsyncTask.sHandler;
        }
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final boolean isCancelled() {
        return this.mCancelled.get();
    }

    protected void onCancelled() {
    }

    protected void onCancelled(Object object0) {
    }

    protected void onPostExecute(Object object0) {
    }

    protected void onPreExecute() {
    }

    protected void onProgressUpdate(Object[] arr_object) {
    }

    Object postResult(Object object0) {
        ModernAsyncTask.getHandler().obtainMessage(1, new AsyncTaskResult(this, new Object[]{object0})).sendToTarget();
        return object0;
    }

    void postResultIfNotInvoked(Object object0) {
        if(!this.mTaskInvoked.get()) {
            this.postResult(object0);
        }
    }

    protected final void publishProgress(Object[] arr_object) {
        if(!this.isCancelled()) {
            ModernAsyncTask.getHandler().obtainMessage(2, new AsyncTaskResult(this, arr_object)).sendToTarget();
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static void setDefaultExecutor(Executor executor0) {
        ModernAsyncTask.sDefaultExecutor = executor0;
    }
}

