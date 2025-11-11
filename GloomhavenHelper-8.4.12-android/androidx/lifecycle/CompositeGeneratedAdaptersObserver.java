package androidx.lifecycle;

import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;

@RestrictTo({Scope.LIBRARY_GROUP})
public class CompositeGeneratedAdaptersObserver implements GenericLifecycleObserver {
    private final GeneratedAdapter[] mGeneratedAdapters;

    CompositeGeneratedAdaptersObserver(GeneratedAdapter[] arr_generatedAdapter) {
        this.mGeneratedAdapters = arr_generatedAdapter;
    }

    @Override  // androidx.lifecycle.GenericLifecycleObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
        MethodCallsLogger methodCallsLogger0 = new MethodCallsLogger();
        GeneratedAdapter[] arr_generatedAdapter = this.mGeneratedAdapters;
        for(int v1 = 0; v1 < arr_generatedAdapter.length; ++v1) {
            arr_generatedAdapter[v1].callMethods(lifecycleOwner0, lifecycle$Event0, false, methodCallsLogger0);
        }
        GeneratedAdapter[] arr_generatedAdapter1 = this.mGeneratedAdapters;
        for(int v = 0; v < arr_generatedAdapter1.length; ++v) {
            arr_generatedAdapter1[v].callMethods(lifecycleOwner0, lifecycle$Event0, true, methodCallsLogger0);
        }
    }
}

