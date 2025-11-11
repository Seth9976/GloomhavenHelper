package androidx.loader.content;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.DebugUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Loader {
    public final class ForceLoadContentObserver extends ContentObserver {
        public ForceLoadContentObserver() {
            super(new Handler());
        }

        @Override  // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override  // android.database.ContentObserver
        public void onChange(boolean z) {
            Loader.this.onContentChanged();
        }
    }

    public interface OnLoadCanceledListener {
        void onLoadCanceled(@NonNull Loader arg1);
    }

    public interface OnLoadCompleteListener {
        void onLoadComplete(@NonNull Loader arg1, @Nullable Object arg2);
    }

    boolean mAbandoned;
    boolean mContentChanged;
    Context mContext;
    int mId;
    OnLoadCompleteListener mListener;
    OnLoadCanceledListener mOnLoadCanceledListener;
    boolean mProcessingChange;
    boolean mReset;
    boolean mStarted;

    public Loader(@NonNull Context context0) {
        this.mStarted = false;
        this.mAbandoned = false;
        this.mReset = true;
        this.mContentChanged = false;
        this.mProcessingChange = false;
        this.mContext = context0.getApplicationContext();
    }

    @MainThread
    public void abandon() {
        this.mAbandoned = true;
    }

    @MainThread
    public boolean cancelLoad() {
        return this.onCancelLoad();
    }

    public void commitContentChanged() {
        this.mProcessingChange = false;
    }

    @NonNull
    public String dataToString(@Nullable Object object0) {
        StringBuilder stringBuilder0 = new StringBuilder(0x40);
        DebugUtils.buildShortClassTag(object0, stringBuilder0);
        stringBuilder0.append("}");
        return stringBuilder0.toString();
    }

    @MainThread
    public void deliverCancellation() {
        OnLoadCanceledListener loader$OnLoadCanceledListener0 = this.mOnLoadCanceledListener;
        if(loader$OnLoadCanceledListener0 != null) {
            loader$OnLoadCanceledListener0.onLoadCanceled(this);
        }
    }

    @MainThread
    public void deliverResult(@Nullable Object object0) {
        OnLoadCompleteListener loader$OnLoadCompleteListener0 = this.mListener;
        if(loader$OnLoadCompleteListener0 != null) {
            loader$OnLoadCompleteListener0.onLoadComplete(this, object0);
        }
    }

    @Deprecated
    public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        printWriter0.print(s);
        printWriter0.print("mId=");
        printWriter0.print(this.mId);
        printWriter0.print(" mListener=");
        printWriter0.println(this.mListener);
        if(this.mStarted || this.mContentChanged || this.mProcessingChange) {
            printWriter0.print(s);
            printWriter0.print("mStarted=");
            printWriter0.print(this.mStarted);
            printWriter0.print(" mContentChanged=");
            printWriter0.print(this.mContentChanged);
            printWriter0.print(" mProcessingChange=");
            printWriter0.println(this.mProcessingChange);
        }
        if(this.mAbandoned || this.mReset) {
            printWriter0.print(s);
            printWriter0.print("mAbandoned=");
            printWriter0.print(this.mAbandoned);
            printWriter0.print(" mReset=");
            printWriter0.println(this.mReset);
        }
    }

    @MainThread
    public void forceLoad() {
        this.onForceLoad();
    }

    @NonNull
    public Context getContext() {
        return this.mContext;
    }

    public int getId() {
        return this.mId;
    }

    public boolean isAbandoned() {
        return this.mAbandoned;
    }

    public boolean isReset() {
        return this.mReset;
    }

    public boolean isStarted() {
        return this.mStarted;
    }

    @MainThread
    protected void onAbandon() {
    }

    @MainThread
    protected boolean onCancelLoad() {
        return false;
    }

    @MainThread
    public void onContentChanged() {
        if(this.mStarted) {
            this.forceLoad();
            return;
        }
        this.mContentChanged = true;
    }

    @MainThread
    protected void onForceLoad() {
    }

    @MainThread
    protected void onReset() {
    }

    @MainThread
    protected void onStartLoading() {
    }

    @MainThread
    protected void onStopLoading() {
    }

    @MainThread
    public void registerListener(int v, @NonNull OnLoadCompleteListener loader$OnLoadCompleteListener0) {
        if(this.mListener != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.mListener = loader$OnLoadCompleteListener0;
        this.mId = v;
    }

    @MainThread
    public void registerOnLoadCanceledListener(@NonNull OnLoadCanceledListener loader$OnLoadCanceledListener0) {
        if(this.mOnLoadCanceledListener != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.mOnLoadCanceledListener = loader$OnLoadCanceledListener0;
    }

    @MainThread
    public void reset() {
        this.onReset();
        this.mReset = true;
        this.mStarted = false;
        this.mAbandoned = false;
        this.mContentChanged = false;
        this.mProcessingChange = false;
    }

    public void rollbackContentChanged() {
        if(this.mProcessingChange) {
            this.onContentChanged();
        }
    }

    @MainThread
    public final void startLoading() {
        this.mStarted = true;
        this.mReset = false;
        this.mAbandoned = false;
        this.onStartLoading();
    }

    @MainThread
    public void stopLoading() {
        this.mStarted = false;
        this.onStopLoading();
    }

    public boolean takeContentChanged() {
        boolean z = this.mContentChanged;
        this.mContentChanged = false;
        this.mProcessingChange |= z;
        return z;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder(0x40);
        DebugUtils.buildShortClassTag(this, stringBuilder0);
        stringBuilder0.append(" id=");
        stringBuilder0.append(this.mId);
        stringBuilder0.append("}");
        return stringBuilder0.toString();
    }

    @MainThread
    public void unregisterListener(@NonNull OnLoadCompleteListener loader$OnLoadCompleteListener0) {
        OnLoadCompleteListener loader$OnLoadCompleteListener1 = this.mListener;
        if(loader$OnLoadCompleteListener1 == null) {
            throw new IllegalStateException("No listener register");
        }
        if(loader$OnLoadCompleteListener1 != loader$OnLoadCompleteListener0) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.mListener = null;
    }

    @MainThread
    public void unregisterOnLoadCanceledListener(@NonNull OnLoadCanceledListener loader$OnLoadCanceledListener0) {
        OnLoadCanceledListener loader$OnLoadCanceledListener1 = this.mOnLoadCanceledListener;
        if(loader$OnLoadCanceledListener1 == null) {
            throw new IllegalStateException("No listener register");
        }
        if(loader$OnLoadCanceledListener1 != loader$OnLoadCanceledListener0) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.mOnLoadCanceledListener = null;
    }
}

