package androidx.loader.app;

import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class LoaderManager {
    public interface LoaderCallbacks {
        @MainThread
        @NonNull
        Loader onCreateLoader(int arg1, @Nullable Bundle arg2);

        @MainThread
        void onLoadFinished(@NonNull Loader arg1, Object arg2);

        @MainThread
        void onLoaderReset(@NonNull Loader arg1);
    }

    @MainThread
    public abstract void destroyLoader(int arg1);

    @Deprecated
    public abstract void dump(String arg1, FileDescriptor arg2, PrintWriter arg3, String[] arg4);

    public static void enableDebugLogging(boolean z) {
        LoaderManagerImpl.DEBUG = z;
    }

    @NonNull
    public static LoaderManager getInstance(@NonNull LifecycleOwner lifecycleOwner0) {
        return new LoaderManagerImpl(lifecycleOwner0, ((ViewModelStoreOwner)lifecycleOwner0).getViewModelStore());
    }

    @Nullable
    public abstract Loader getLoader(int arg1);

    public boolean hasRunningLoaders() {
        return false;
    }

    @MainThread
    @NonNull
    public abstract Loader initLoader(int arg1, @Nullable Bundle arg2, @NonNull LoaderCallbacks arg3);

    public abstract void markForRedelivery();

    @MainThread
    @NonNull
    public abstract Loader restartLoader(int arg1, @Nullable Bundle arg2, @NonNull LoaderCallbacks arg3);
}

