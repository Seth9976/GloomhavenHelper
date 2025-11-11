package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;

public class Transformations {
    @MainThread
    public static LiveData map(@NonNull LiveData liveData0, @NonNull Function function0) {
        LiveData liveData1 = new MediatorLiveData();
        ((MediatorLiveData)liveData1).addSource(liveData0, new Observer() {
            @Override  // androidx.lifecycle.Observer
            public void onChanged(@Nullable Object object0) {
                Object object1 = function0.apply(object0);
                ((MediatorLiveData)liveData1).setValue(object1);
            }
        });
        return liveData1;
    }

    @MainThread
    public static LiveData switchMap(@NonNull LiveData liveData0, @NonNull Function function0) {
        LiveData liveData1 = new MediatorLiveData();
        ((MediatorLiveData)liveData1).addSource(liveData0, new Observer() {
            LiveData mSource;

            @Override  // androidx.lifecycle.Observer
            public void onChanged(@Nullable Object object0) {
                LiveData liveData0 = (LiveData)function0.apply(object0);
                LiveData liveData1 = this.mSource;
                if(liveData1 == liveData0) {
                    return;
                }
                if(liveData1 != null) {
                    ((MediatorLiveData)liveData1).removeSource(liveData1);
                }
                this.mSource = liveData0;
                LiveData liveData2 = this.mSource;
                if(liveData2 != null) {
                    androidx.lifecycle.Transformations.2.1 transformations$2$10 = new Observer() {
                        @Override  // androidx.lifecycle.Observer
                        public void onChanged(@Nullable Object object0) {
                            androidx.lifecycle.Transformations.2.this.val$result.setValue(object0);
                        }
                    };
                    ((MediatorLiveData)liveData1).addSource(liveData2, transformations$2$10);
                }
            }
        });
        return liveData1;
    }
}

