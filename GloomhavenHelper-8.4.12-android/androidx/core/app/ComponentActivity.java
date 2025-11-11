package androidx.core.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.KeyEventDispatcher.Component;
import androidx.core.view.KeyEventDispatcher;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ComponentActivity extends Activity implements Component, LifecycleOwner {
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static class ExtraData {
    }

    private SimpleArrayMap mExtraDataMap;
    private LifecycleRegistry mLifecycleRegistry;

    public ComponentActivity() {
        this.mExtraDataMap = new SimpleArrayMap();
        this.mLifecycleRegistry = new LifecycleRegistry(this);
    }

    @Override  // android.app.Activity
    public boolean dispatchKeyEvent(KeyEvent keyEvent0) {
        View view0 = this.getWindow().getDecorView();
        return view0 == null || !KeyEventDispatcher.dispatchBeforeHierarchy(view0, keyEvent0) ? KeyEventDispatcher.dispatchKeyEvent(this, view0, this, keyEvent0) : true;
    }

    @Override  // android.app.Activity
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent0) {
        View view0 = this.getWindow().getDecorView();
        return view0 == null || !KeyEventDispatcher.dispatchBeforeHierarchy(view0, keyEvent0) ? super.dispatchKeyShortcutEvent(keyEvent0) : true;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public ExtraData getExtraData(Class class0) {
        return (ExtraData)this.mExtraDataMap.get(class0);
    }

    @Override  // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override  // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle0) {
        super.onCreate(bundle0);
        ReportFragment.injectIfNeededIn(this);
    }

    @Override  // android.app.Activity
    @CallSuper
    protected void onSaveInstanceState(Bundle bundle0) {
        this.mLifecycleRegistry.markState(State.CREATED);
        super.onSaveInstanceState(bundle0);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void putExtraData(ExtraData componentActivity$ExtraData0) {
        this.mExtraDataMap.put(componentActivity$ExtraData0.getClass(), componentActivity$ExtraData0);
    }

    @Override  // androidx.core.view.KeyEventDispatcher$Component
    @RestrictTo({Scope.LIBRARY_GROUP})
    public boolean superDispatchKeyEvent(KeyEvent keyEvent0) {
        return super.dispatchKeyEvent(keyEvent0);
    }
}

