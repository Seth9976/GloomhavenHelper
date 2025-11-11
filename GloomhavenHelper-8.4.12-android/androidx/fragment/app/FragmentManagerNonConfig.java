package androidx.fragment.app;

import java.util.List;

public class FragmentManagerNonConfig {
    private final List mChildNonConfigs;
    private final List mFragments;
    private final List mViewModelStores;

    FragmentManagerNonConfig(List list0, List list1, List list2) {
        this.mFragments = list0;
        this.mChildNonConfigs = list1;
        this.mViewModelStores = list2;
    }

    List getChildNonConfigs() {
        return this.mChildNonConfigs;
    }

    List getFragments() {
        return this.mFragments;
    }

    List getViewModelStores() {
        return this.mViewModelStores;
    }
}

