package androidx.browser.browseractions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

class BrowserActionsFallbackMenuDialog extends Dialog {
    private static final long ENTER_ANIMATION_DURATION_MS = 0xFAL;
    private static final long EXIT_ANIMATION_DURATION_MS = 150L;
    private final View mContentView;

    BrowserActionsFallbackMenuDialog(Context context0, View view0) {
        super(context0);
        this.mContentView = view0;
    }

    @Override  // android.app.Dialog
    public void dismiss() {
        this.startAnimation(false);
    }

    @Override  // android.app.Dialog
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        if(motionEvent0.getAction() == 0) {
            this.dismiss();
            return true;
        }
        return false;
    }

    @Override  // android.app.Dialog
    public void show() {
        this.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.startAnimation(true);
        super.show();
    }

    private void startAnimation(boolean z) {
        this.mContentView.setScaleX((z ? 0.0f : 1.0f));
        this.mContentView.setScaleY((z ? 0.0f : 1.0f));
        this.mContentView.animate().scaleX((z ? 1.0f : 0.0f)).scaleY((z ? 1.0f : 0.0f)).setDuration((z ? 0xFAL : 150L)).setInterpolator(new LinearOutSlowInInterpolator()).setListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                if(!z) {
                    BrowserActionsFallbackMenuDialog.this.super.dismiss();
                }
            }
        }).start();
    }
}

