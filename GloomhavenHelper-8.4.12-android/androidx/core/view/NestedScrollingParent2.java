package androidx.core.view;

import android.view.View;
import androidx.annotation.NonNull;

public interface NestedScrollingParent2 extends NestedScrollingParent {
    void onNestedPreScroll(@NonNull View arg1, int arg2, int arg3, @NonNull int[] arg4, int arg5);

    void onNestedScroll(@NonNull View arg1, int arg2, int arg3, int arg4, int arg5, int arg6);

    void onNestedScrollAccepted(@NonNull View arg1, @NonNull View arg2, int arg3, int arg4);

    boolean onStartNestedScroll(@NonNull View arg1, @NonNull View arg2, int arg3, int arg4);

    void onStopNestedScroll(@NonNull View arg1, int arg2);
}

