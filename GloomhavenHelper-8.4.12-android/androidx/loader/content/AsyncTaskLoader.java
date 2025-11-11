package androidx.loader.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.core.os.OperationCanceledException;
import androidx.core.util.TimeUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public abstract class AsyncTaskLoader extends Loader {
    final class LoadTask extends ModernAsyncTask implements Runnable {
        private final CountDownLatch mDone;
        boolean waiting;

        LoadTask() {
            this.mDone = new CountDownLatch(1);
        }

        @Override  // androidx.loader.content.ModernAsyncTask
        protected Object doInBackground(Object[] arr_object) {
            return this.doInBackground(((Void[])arr_object));
        }

        protected Object doInBackground(Void[] arr_void) {
            try {
                return AsyncTaskLoader.this.onLoadInBackground();
            }
            catch(OperationCanceledException operationCanceledException0) {
                if(!this.isCancelled()) {
                    throw operationCanceledException0;
                }
                return null;
            }
        }

        @Override  // androidx.loader.content.ModernAsyncTask
        protected void onCancelled(Object object0) {
            try {
                AsyncTaskLoader.this.dispatchOnCancelled(this, object0);
            }
            finally {
                this.mDone.countDown();
            }
        }

        @Override  // androidx.loader.content.ModernAsyncTask
        protected void onPostExecute(Object object0) {
            try {
                AsyncTaskLoader.this.dispatchOnLoadComplete(this, object0);
            }
            finally {
                this.mDone.countDown();
            }
        }

        @Override
        public void run() {
            this.waiting = false;
            AsyncTaskLoader.this.executePendingTask();
        }

        public void waitForLoader() {
            try {
                this.mDone.await();
            }
            catch(InterruptedException unused_ex) {
            }
        }
    }

    static final boolean DEBUG = false;
    static final String TAG = "AsyncTaskLoader";
    volatile LoadTask mCancellingTask;
    private final Executor mExecutor;
    Handler mHandler;
    long mLastLoadCompleteTime;
    volatile LoadTask mTask;
    long mUpdateThrottle;

    public AsyncTaskLoader(@NonNull Context context0) {
        this(context0, ModernAsyncTask.THREAD_POOL_EXECUTOR);
    }

    private AsyncTaskLoader(@NonNull Context context0, @NonNull Executor executor0) {
        super(context0);
        this.mLastLoadCompleteTime = -10000L;
        this.mExecutor = executor0;
    }

    public void cancelLoadInBackground() {
    }

    void dispatchOnCancelled(LoadTask asyncTaskLoader$LoadTask0, Object object0) {
        this.onCanceled(object0);
        if(this.mCancellingTask == asyncTaskLoader$LoadTask0) {
            this.rollbackContentChanged();
            this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
            this.mCancellingTask = null;
            this.deliverCancellation();
            this.executePendingTask();
        }
    }

    void dispatchOnLoadComplete(LoadTask asyncTaskLoader$LoadTask0, Object object0) {
        if(this.mTask != asyncTaskLoader$LoadTask0) {
            this.dispatchOnCancelled(asyncTaskLoader$LoadTask0, object0);
            return;
        }
        if(this.isAbandoned()) {
            this.onCanceled(object0);
            return;
        }
        this.commitContentChanged();
        this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
        this.mTask = null;
        this.deliverResult(object0);
    }

    @Override  // androidx.loader.content.Loader
    @Deprecated
    public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        super.dump(s, fileDescriptor0, printWriter0, arr_s);
        if(this.mTask != null) {
            printWriter0.print(s);
            printWriter0.print("mTask=");
            printWriter0.print(this.mTask);
            printWriter0.print(" waiting=");
            printWriter0.println(this.mTask.waiting);
        }
        if(this.mCancellingTask != null) {
            printWriter0.print(s);
            printWriter0.print("mCancellingTask=");
            printWriter0.print(this.mCancellingTask);
            printWriter0.print(" waiting=");
            printWriter0.println(this.mCancellingTask.waiting);
        }
        if(this.mUpdateThrottle != 0L) {
            printWriter0.print(s);
            printWriter0.print("mUpdateThrottle=");
            TimeUtils.formatDuration(this.mUpdateThrottle, printWriter0);
            printWriter0.print(" mLastLoadCompleteTime=");
            TimeUtils.formatDuration(this.mLastLoadCompleteTime, SystemClock.uptimeMillis(), printWriter0);
            printWriter0.println();
        }
    }

    void executePendingTask() {
        if(this.mCancellingTask == null && this.mTask != null) {
            if(this.mTask.waiting) {
                this.mTask.waiting = false;
                this.mHandler.removeCallbacks(this.mTask);
            }
            if(this.mUpdateThrottle > 0L && SystemClock.uptimeMillis() < this.mLastLoadCompleteTime + this.mUpdateThrottle) {
                this.mTask.waiting = true;
                this.mHandler.postAtTime(this.mTask, this.mLastLoadCompleteTime + this.mUpdateThrottle);
                return;
            }
            this.mTask.executeOnExecutor(this.mExecutor, null);
        }
    }

    public boolean isLoadInBackgroundCanceled() {
        return this.mCancellingTask != null;
    }

    @Nullable
    public abstract Object loadInBackground();

    @Override  // androidx.loader.content.Loader
    protected boolean onCancelLoad() {
        if(this.mTask != null) {
            if(!this.mStarted) {
                this.mContentChanged = true;
            }
            if(this.mCancellingTask != null) {
                if(this.mTask.waiting) {
                    this.mTask.waiting = false;
                    this.mHandler.removeCallbacks(this.mTask);
                }
                this.mTask = null;
                return false;
            }
            if(this.mTask.waiting) {
                this.mTask.waiting = false;
                this.mHandler.removeCallbacks(this.mTask);
                this.mTask = null;
                return false;
            }
            boolean z = this.mTask.cancel(false);
            if(z) {
                this.mCancellingTask = this.mTask;
                this.cancelLoadInBackground();
            }
            this.mTask = null;
            return z;
        }
        return false;
    }

    public void onCanceled(@Nullable Object object0) {
    }

    @Override  // androidx.loader.content.Loader
    protected void onForceLoad() {
        super.onForceLoad();
        this.cancelLoad();
        this.mTask = new LoadTask(this);
        this.executePendingTask();
    }

    @Nullable
    protected Object onLoadInBackground() {
        return this.loadInBackground();
    }

    public void setUpdateThrottle(long v) {
        this.mUpdateThrottle = v;
        if(v != 0L) {
            this.mHandler = new Handler();
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void waitForLoader() {
        LoadTask asyncTaskLoader$LoadTask0 = this.mTask;
        if(asyncTaskLoader$LoadTask0 != null) {
            asyncTaskLoader$LoadTask0.waitForLoader();
        }
    }
}

