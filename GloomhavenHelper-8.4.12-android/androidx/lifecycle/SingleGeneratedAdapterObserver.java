package androidx.lifecycle;

import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SingleGeneratedAdapterObserver implements GenericLifecycleObserver {
    private final GeneratedAdapter mGeneratedAdapter;

    SingleGeneratedAdapterObserver(GeneratedAdapter generatedAdapter0) {
        this.mGeneratedAdapter = generatedAdapter0;
    }

    @Override  // androidx.lifecycle.GenericLifecycleObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
        this.mGeneratedAdapter.callMethods(lifecycleOwner0, lifecycle$Event0, false, null);
        this.mGeneratedAdapter.callMethods(lifecycleOwner0, lifecycle$Event0, true, null);
    }
}

