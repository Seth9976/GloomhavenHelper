package androidx.core.view;

import android.view.View;

public interface ViewPropertyAnimatorListener {
    void onAnimationCancel(View arg1);

    void onAnimationEnd(View arg1);

    void onAnimationStart(View arg1);
}

