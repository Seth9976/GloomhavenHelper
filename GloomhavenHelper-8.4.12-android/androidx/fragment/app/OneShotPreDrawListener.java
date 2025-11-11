package androidx.fragment.app;

import android.view.View.OnAttachStateChangeListener;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.ViewTreeObserver;

class OneShotPreDrawListener implements View.OnAttachStateChangeListener, ViewTreeObserver.OnPreDrawListener {
    private final Runnable mRunnable;
    private final View mView;
    private ViewTreeObserver mViewTreeObserver;

    private OneShotPreDrawListener(View view0, Runnable runnable0) {
        this.mView = view0;
        this.mViewTreeObserver = view0.getViewTreeObserver();
        this.mRunnable = runnable0;
    }

    public static OneShotPreDrawListener add(View view0, Runnable runnable0) {
        OneShotPreDrawListener oneShotPreDrawListener0 = new OneShotPreDrawListener(view0, runnable0);
        view0.getViewTreeObserver().addOnPreDrawListener(oneShotPreDrawListener0);
        view0.addOnAttachStateChangeListener(oneShotPreDrawListener0);
        return oneShotPreDrawListener0;
    }

    @Override  // android.view.ViewTreeObserver$OnPreDrawListener
    public boolean onPreDraw() {
        this.removeListener();
        this.mRunnable.run();
        return true;
    }

    @Override  // android.view.View$OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view0) {
        this.mViewTreeObserver = view0.getViewTreeObserver();
    }

    @Override  // android.view.View$OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view0) {
        this.removeListener();
    }

    public void removeListener() {
        if(this.mViewTreeObserver.isAlive()) {
            this.mViewTreeObserver.removeOnPreDrawListener(this);
        }
        else {
            this.mView.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.mView.removeOnAttachStateChangeListener(this);
    }
}

