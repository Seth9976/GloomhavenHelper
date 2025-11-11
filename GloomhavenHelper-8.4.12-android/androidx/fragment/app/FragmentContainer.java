package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

public abstract class FragmentContainer {
    public Fragment instantiate(Context context0, String s, Bundle bundle0) {
        return Fragment.instantiate(context0, s, bundle0);
    }

    @Nullable
    public abstract View onFindViewById(@IdRes int arg1);

    public abstract boolean onHasView();
}

