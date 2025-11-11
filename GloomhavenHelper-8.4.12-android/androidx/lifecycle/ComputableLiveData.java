package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.arch.core.executor.ArchTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class ComputableLiveData {
    final AtomicBoolean mComputing;
    final Executor mExecutor;
    final AtomicBoolean mInvalid;
    @VisibleForTesting
    final Runnable mInvalidationRunnable;
    final LiveData mLiveData;
    @VisibleForTesting
    final Runnable mRefreshRunnable;

    public ComputableLiveData() {
        this(ArchTaskExecutor.getIOThreadExecutor());
    }

    public ComputableLiveData(@NonNull Executor executor0) {
        this.mInvalid = new AtomicBoolean(true);
        this.mComputing = new AtomicBoolean(false);
        this.mRefreshRunnable = new Runnable() {
            @Override
            @WorkerThread
            public void run() {
                boolean z;
                do {
                    if(ComputableLiveData.this.mComputing.compareAndSet(false, true)) {
                        Object object0 = null;
                        z = false;
                        try {
                            while(ComputableLiveData.this.mInvalid.compareAndSet(true, false)) {
                                object0 = ComputableLiveData.this.compute();
                                z = true;
                            }
                            if(z) {
                                ComputableLiveData.this.mLiveData.postValue(object0);
                            }
                        }
                        finally {
                            ComputableLiveData.this.mComputing.set(false);
                        }
                    }
                    else {
                        z = false;
                    }
                }
                while(z && ComputableLiveData.this.mInvalid.get());
            }
        };
        this.mInvalidationRunnable = new Runnable() {
            @Override
            @MainThread
            public void run() {
                boolean z = ComputableLiveData.this.mLiveData.hasActiveObservers();
                if(ComputableLiveData.this.mInvalid.compareAndSet(false, true) && z) {
                    ComputableLiveData.this.mExecutor.execute(ComputableLiveData.this.mRefreshRunnable);
                }
            }
        };
        this.mExecutor = executor0;
        this.mLiveData = new LiveData() {
            @Override  // androidx.lifecycle.LiveData
            protected void onActive() {
                ComputableLiveData.this.mExecutor.execute(ComputableLiveData.this.mRefreshRunnable);
            }
        };
    }

    @WorkerThread
    protected abstract Object compute();

    @NonNull
    public LiveData getLiveData() {
        return this.mLiveData;
    }

    public void invalidate() {
        ArchTaskExecutor.getInstance().executeOnMainThread(this.mInvalidationRunnable);
    }
}

