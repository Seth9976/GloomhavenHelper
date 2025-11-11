package androidx.lifecycle;

class FullLifecycleObserverAdapter implements GenericLifecycleObserver {
    private final FullLifecycleObserver mObserver;

    FullLifecycleObserverAdapter(FullLifecycleObserver fullLifecycleObserver0) {
        this.mObserver = fullLifecycleObserver0;
    }

    @Override  // androidx.lifecycle.GenericLifecycleObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
        switch(lifecycle$Event0) {
            case ON_CREATE: {
                this.mObserver.onCreate(lifecycleOwner0);
                return;
            }
            case ON_START: {
                this.mObserver.onStart(lifecycleOwner0);
                return;
            }
            case ON_RESUME: {
                this.mObserver.onResume(lifecycleOwner0);
                return;
            }
            case ON_PAUSE: {
                this.mObserver.onPause(lifecycleOwner0);
                return;
            }
            case ON_STOP: {
                this.mObserver.onStop(lifecycleOwner0);
                return;
            }
            case ON_DESTROY: {
                this.mObserver.onDestroy(lifecycleOwner0);
                return;
            }
            case ON_ANY: {
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            }
        }
    }
}

