package androidx.lifecycle;

import android.app.Application;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;

public class ViewModelProvider {
    public static class AndroidViewModelFactory extends NewInstanceFactory {
        private Application mApplication;
        private static AndroidViewModelFactory sInstance;

        public AndroidViewModelFactory(@NonNull Application application0) {
            this.mApplication = application0;
        }

        @Override  // androidx.lifecycle.ViewModelProvider$NewInstanceFactory
        @NonNull
        public ViewModel create(@NonNull Class class0) {
            if(AndroidViewModel.class.isAssignableFrom(class0)) {
                try {
                    return (ViewModel)class0.getConstructor(Application.class).newInstance(this.mApplication);
                }
                catch(NoSuchMethodException noSuchMethodException0) {
                    throw new RuntimeException("Cannot create an instance of " + class0, noSuchMethodException0);
                }
                catch(IllegalAccessException illegalAccessException0) {
                    throw new RuntimeException("Cannot create an instance of " + class0, illegalAccessException0);
                }
                catch(InstantiationException instantiationException0) {
                    throw new RuntimeException("Cannot create an instance of " + class0, instantiationException0);
                }
                catch(InvocationTargetException invocationTargetException0) {
                    throw new RuntimeException("Cannot create an instance of " + class0, invocationTargetException0);
                }
            }
            return super.create(class0);
        }

        @NonNull
        public static AndroidViewModelFactory getInstance(@NonNull Application application0) {
            if(AndroidViewModelFactory.sInstance == null) {
                AndroidViewModelFactory.sInstance = new AndroidViewModelFactory(application0);
            }
            return AndroidViewModelFactory.sInstance;
        }
    }

    public interface Factory {
        @NonNull
        ViewModel create(@NonNull Class arg1);
    }

    public static class NewInstanceFactory implements Factory {
        @Override  // androidx.lifecycle.ViewModelProvider$Factory
        @NonNull
        public ViewModel create(@NonNull Class class0) {
            try {
                return (ViewModel)class0.newInstance();
            }
            catch(InstantiationException instantiationException0) {
                throw new RuntimeException("Cannot create an instance of " + class0, instantiationException0);
            }
            catch(IllegalAccessException illegalAccessException0) {
                throw new RuntimeException("Cannot create an instance of " + class0, illegalAccessException0);
            }
        }
    }

    private static final String DEFAULT_KEY = "androidx.lifecycle.ViewModelProvider.DefaultKey";
    private final Factory mFactory;
    private final ViewModelStore mViewModelStore;

    public ViewModelProvider(@NonNull ViewModelStore viewModelStore0, @NonNull Factory viewModelProvider$Factory0) {
        this.mFactory = viewModelProvider$Factory0;
        this.mViewModelStore = viewModelStore0;
    }

    public ViewModelProvider(@NonNull ViewModelStoreOwner viewModelStoreOwner0, @NonNull Factory viewModelProvider$Factory0) {
        this(viewModelStoreOwner0.getViewModelStore(), viewModelProvider$Factory0);
    }

    @MainThread
    @NonNull
    public ViewModel get(@NonNull Class class0) {
        String s = class0.getCanonicalName();
        if(s == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        return this.get("androidx.lifecycle.ViewModelProvider.DefaultKey:" + s, class0);
    }

    @MainThread
    @NonNull
    public ViewModel get(@NonNull String s, @NonNull Class class0) {
        ViewModel viewModel0 = this.mViewModelStore.get(s);
        if(class0.isInstance(viewModel0)) {
            return viewModel0;
        }
        ViewModel viewModel1 = this.mFactory.create(class0);
        this.mViewModelStore.put(s, viewModel1);
        return viewModel1;
    }
}

