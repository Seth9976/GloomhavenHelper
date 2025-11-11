package androidx.lifecycle;

import java.util.HashMap;

public class ViewModelStore {
    private final HashMap mMap;

    public ViewModelStore() {
        this.mMap = new HashMap();
    }

    public final void clear() {
        for(Object object0: this.mMap.values()) {
            ((ViewModel)object0).onCleared();
        }
        this.mMap.clear();
    }

    final ViewModel get(String s) {
        return (ViewModel)this.mMap.get(s);
    }

    final void put(String s, ViewModel viewModel0) {
        ViewModel viewModel1 = (ViewModel)this.mMap.put(s, viewModel0);
        if(viewModel1 != null) {
            viewModel1.onCleared();
        }
    }
}

