package androidx.lifecycle;

interface FullLifecycleObserver extends LifecycleObserver {
    void onCreate(LifecycleOwner arg1);

    void onDestroy(LifecycleOwner arg1);

    void onPause(LifecycleOwner arg1);

    void onResume(LifecycleOwner arg1);

    void onStart(LifecycleOwner arg1);

    void onStop(LifecycleOwner arg1);
}

