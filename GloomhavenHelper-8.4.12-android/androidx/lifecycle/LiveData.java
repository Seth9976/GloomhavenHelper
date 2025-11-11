package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.SafeIterableMap.IteratorWithAdditions;
import androidx.arch.core.internal.SafeIterableMap;
import java.util.Map.Entry;

public abstract class LiveData {
    class AlwaysActiveObserver extends ObserverWrapper {
        AlwaysActiveObserver(Observer observer0) {
            super(observer0);
        }

        @Override  // androidx.lifecycle.LiveData$ObserverWrapper
        boolean shouldBeActive() {
            return true;
        }
    }

    class LifecycleBoundObserver extends ObserverWrapper implements GenericLifecycleObserver {
        @NonNull
        final LifecycleOwner mOwner;

        LifecycleBoundObserver(@NonNull LifecycleOwner lifecycleOwner0, Observer observer0) {
            super(observer0);
            this.mOwner = lifecycleOwner0;
        }

        @Override  // androidx.lifecycle.LiveData$ObserverWrapper
        void detachObserver() {
            this.mOwner.getLifecycle().removeObserver(this);
        }

        @Override  // androidx.lifecycle.LiveData$ObserverWrapper
        boolean isAttachedTo(LifecycleOwner lifecycleOwner0) {
            return this.mOwner == lifecycleOwner0;
        }

        @Override  // androidx.lifecycle.GenericLifecycleObserver
        public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
            if(this.mOwner.getLifecycle().getCurrentState() == State.DESTROYED) {
                LiveData.this.removeObserver(this.mObserver);
                return;
            }
            this.activeStateChanged(this.shouldBeActive());
        }

        @Override  // androidx.lifecycle.LiveData$ObserverWrapper
        boolean shouldBeActive() {
            return this.mOwner.getLifecycle().getCurrentState().isAtLeast(State.STARTED);
        }
    }

    abstract class ObserverWrapper {
        boolean mActive;
        int mLastVersion;
        final Observer mObserver;

        ObserverWrapper(Observer observer0) {
            this.mLastVersion = -1;
            this.mObserver = observer0;
        }

        void activeStateChanged(boolean z) {
            if(z == this.mActive) {
                return;
            }
            this.mActive = z;
            int v = 1;
            boolean z1 = LiveData.this.mActiveCount == 0;
            LiveData liveData0 = LiveData.this;
            int v1 = liveData0.mActiveCount;
            if(!this.mActive) {
                v = -1;
            }
            liveData0.mActiveCount = v1 + v;
            if(z1 && this.mActive) {
                LiveData.this.onActive();
            }
            if(LiveData.this.mActiveCount == 0 && !this.mActive) {
                LiveData.this.onInactive();
            }
            if(this.mActive) {
                LiveData.this.dispatchingValue(this);
            }
        }

        void detachObserver() {
        }

        boolean isAttachedTo(LifecycleOwner lifecycleOwner0) {
            return false;
        }

        abstract boolean shouldBeActive();
    }

    static final Object NOT_SET = null;
    static final int START_VERSION = -1;
    int mActiveCount;
    private volatile Object mData;
    final Object mDataLock;
    private boolean mDispatchInvalidated;
    private boolean mDispatchingValue;
    private SafeIterableMap mObservers;
    volatile Object mPendingData;
    private final Runnable mPostValueRunnable;
    private int mVersion;

    static {
        LiveData.NOT_SET = new Object();
    }

    public LiveData() {
        this.mDataLock = new Object();
        this.mObservers = new SafeIterableMap();
        this.mActiveCount = 0;
        this.mData = LiveData.NOT_SET;
        this.mPendingData = LiveData.NOT_SET;
        this.mVersion = -1;
        this.mPostValueRunnable = new Runnable() {
            @Override
            public void run() {
                Object object0;
                synchronized(LiveData.this.mDataLock) {
                    object0 = LiveData.this.mPendingData;
                    LiveData.this.mPendingData = LiveData.NOT_SET;
                }
                LiveData.this.setValue(object0);
            }
        };
    }

    private static void assertMainThread(String s) {
        if(!ArchTaskExecutor.getInstance().isMainThread()) {
            throw new IllegalStateException("Cannot invoke " + s + " on a background" + " thread");
        }
    }

    private void considerNotify(ObserverWrapper liveData$ObserverWrapper0) {
        if(!liveData$ObserverWrapper0.mActive) {
            return;
        }
        if(!liveData$ObserverWrapper0.shouldBeActive()) {
            liveData$ObserverWrapper0.activeStateChanged(false);
            return;
        }
        int v = this.mVersion;
        if(liveData$ObserverWrapper0.mLastVersion >= v) {
            return;
        }
        liveData$ObserverWrapper0.mLastVersion = v;
        liveData$ObserverWrapper0.mObserver.onChanged(this.mData);
    }

    void dispatchingValue(@Nullable ObserverWrapper liveData$ObserverWrapper0) {
        if(this.mDispatchingValue) {
            this.mDispatchInvalidated = true;
            return;
        }
        this.mDispatchingValue = true;
        do {
            this.mDispatchInvalidated = false;
            if(liveData$ObserverWrapper0 == null) {
                IteratorWithAdditions safeIterableMap$IteratorWithAdditions0 = this.mObservers.iteratorWithAdditions();
                while(safeIterableMap$IteratorWithAdditions0.hasNext()) {
                    Object object0 = safeIterableMap$IteratorWithAdditions0.next();
                    this.considerNotify(((ObserverWrapper)((Map.Entry)object0).getValue()));
                    if(this.mDispatchInvalidated) {
                        break;
                    }
                }
            }
            else {
                this.considerNotify(liveData$ObserverWrapper0);
                liveData$ObserverWrapper0 = null;
            }
        }
        while(this.mDispatchInvalidated);
        this.mDispatchingValue = false;
    }

    @Nullable
    public Object getValue() {
        return this.mData == LiveData.NOT_SET ? null : this.mData;
    }

    int getVersion() {
        return this.mVersion;
    }

    public boolean hasActiveObservers() {
        return this.mActiveCount > 0;
    }

    public boolean hasObservers() {
        return this.mObservers.size() > 0;
    }

    @MainThread
    public void observe(@NonNull LifecycleOwner lifecycleOwner0, @NonNull Observer observer0) {
        LiveData.assertMainThread("observe");
        if(lifecycleOwner0.getLifecycle().getCurrentState() == State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver liveData$LifecycleBoundObserver0 = new LifecycleBoundObserver(this, lifecycleOwner0, observer0);
        ObserverWrapper liveData$ObserverWrapper0 = (ObserverWrapper)this.mObservers.putIfAbsent(observer0, liveData$LifecycleBoundObserver0);
        if(liveData$ObserverWrapper0 != null && !liveData$ObserverWrapper0.isAttachedTo(lifecycleOwner0)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if(liveData$ObserverWrapper0 != null) {
            return;
        }
        lifecycleOwner0.getLifecycle().addObserver(liveData$LifecycleBoundObserver0);
    }

    @MainThread
    public void observeForever(@NonNull Observer observer0) {
        LiveData.assertMainThread("observeForever");
        AlwaysActiveObserver liveData$AlwaysActiveObserver0 = new AlwaysActiveObserver(this, observer0);
        ObserverWrapper liveData$ObserverWrapper0 = (ObserverWrapper)this.mObservers.putIfAbsent(observer0, liveData$AlwaysActiveObserver0);
        if(liveData$ObserverWrapper0 != null && liveData$ObserverWrapper0 instanceof LifecycleBoundObserver) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if(liveData$ObserverWrapper0 != null) {
            return;
        }
        liveData$AlwaysActiveObserver0.activeStateChanged(true);
    }

    protected void onActive() {
    }

    protected void onInactive() {
    }

    protected void postValue(Object object0) {
        synchronized(this.mDataLock) {
            boolean z = this.mPendingData == LiveData.NOT_SET;
            this.mPendingData = object0;
        }
        if(!z) {
            return;
        }
        ArchTaskExecutor.getInstance().postToMainThread(this.mPostValueRunnable);
    }

    @MainThread
    public void removeObserver(@NonNull Observer observer0) {
        LiveData.assertMainThread("removeObserver");
        ObserverWrapper liveData$ObserverWrapper0 = (ObserverWrapper)this.mObservers.remove(observer0);
        if(liveData$ObserverWrapper0 == null) {
            return;
        }
        liveData$ObserverWrapper0.detachObserver();
        liveData$ObserverWrapper0.activeStateChanged(false);
    }

    @MainThread
    public void removeObservers(@NonNull LifecycleOwner lifecycleOwner0) {
        LiveData.assertMainThread("removeObservers");
        for(Object object0: this.mObservers) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(((ObserverWrapper)map$Entry0.getValue()).isAttachedTo(lifecycleOwner0)) {
                this.removeObserver(((Observer)map$Entry0.getKey()));
            }
        }
    }

    @MainThread
    protected void setValue(Object object0) {
        LiveData.assertMainThread("setValue");
        ++this.mVersion;
        this.mData = object0;
        this.dispatchingValue(null);
    }
}

