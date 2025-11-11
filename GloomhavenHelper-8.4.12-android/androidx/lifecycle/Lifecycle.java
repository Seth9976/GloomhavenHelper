package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

public abstract class Lifecycle {
    public static enum Event {
        ON_CREATE,
        ON_START,
        ON_RESUME,
        ON_PAUSE,
        ON_STOP,
        ON_DESTROY,
        ON_ANY;

    }

    public static enum State {
        DESTROYED,
        INITIALIZED,
        CREATED,
        STARTED,
        RESUMED;

        public boolean isAtLeast(@NonNull State lifecycle$State0) {
            return this.compareTo(lifecycle$State0) >= 0;
        }
    }

    @MainThread
    public abstract void addObserver(@NonNull LifecycleObserver arg1);

    @MainThread
    @NonNull
    public abstract State getCurrentState();

    @MainThread
    public abstract void removeObserver(@NonNull LifecycleObserver arg1);
}

