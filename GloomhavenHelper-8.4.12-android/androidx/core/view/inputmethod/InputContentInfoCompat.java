package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.inputmethod.InputContentInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public final class InputContentInfoCompat {
    @RequiresApi(25)
    static final class InputContentInfoCompatApi25Impl implements InputContentInfoCompatImpl {
        @NonNull
        final InputContentInfo mObject;

        InputContentInfoCompatApi25Impl(@NonNull Uri uri0, @NonNull ClipDescription clipDescription0, @Nullable Uri uri1) {
            this.mObject = new InputContentInfo(uri0, clipDescription0, uri1);
        }

        InputContentInfoCompatApi25Impl(@NonNull Object object0) {
            this.mObject = (InputContentInfo)object0;
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        @NonNull
        public Uri getContentUri() {
            return this.mObject.getContentUri();
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        @NonNull
        public ClipDescription getDescription() {
            return this.mObject.getDescription();
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        @Nullable
        public Object getInputContentInfo() {
            return this.mObject;
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        @Nullable
        public Uri getLinkUri() {
            return this.mObject.getLinkUri();
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public void releasePermission() {
            this.mObject.releasePermission();
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public void requestPermission() {
            this.mObject.requestPermission();
        }
    }

    static final class InputContentInfoCompatBaseImpl implements InputContentInfoCompatImpl {
        @NonNull
        private final Uri mContentUri;
        @NonNull
        private final ClipDescription mDescription;
        @Nullable
        private final Uri mLinkUri;

        InputContentInfoCompatBaseImpl(@NonNull Uri uri0, @NonNull ClipDescription clipDescription0, @Nullable Uri uri1) {
            this.mContentUri = uri0;
            this.mDescription = clipDescription0;
            this.mLinkUri = uri1;
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        @NonNull
        public Uri getContentUri() {
            return this.mContentUri;
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        @NonNull
        public ClipDescription getDescription() {
            return this.mDescription;
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        @Nullable
        public Object getInputContentInfo() {
            return null;
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        @Nullable
        public Uri getLinkUri() {
            return this.mLinkUri;
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public void releasePermission() {
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public void requestPermission() {
        }
    }

    interface InputContentInfoCompatImpl {
        @NonNull
        Uri getContentUri();

        @NonNull
        ClipDescription getDescription();

        @Nullable
        Object getInputContentInfo();

        @Nullable
        Uri getLinkUri();

        void releasePermission();

        void requestPermission();
    }

    private final InputContentInfoCompatImpl mImpl;

    public InputContentInfoCompat(@NonNull Uri uri0, @NonNull ClipDescription clipDescription0, @Nullable Uri uri1) {
        if(Build.VERSION.SDK_INT >= 25) {
            this.mImpl = new InputContentInfoCompatApi25Impl(uri0, clipDescription0, uri1);
            return;
        }
        this.mImpl = new InputContentInfoCompatBaseImpl(uri0, clipDescription0, uri1);
    }

    private InputContentInfoCompat(@NonNull InputContentInfoCompatImpl inputContentInfoCompat$InputContentInfoCompatImpl0) {
        this.mImpl = inputContentInfoCompat$InputContentInfoCompatImpl0;
    }

    @NonNull
    public Uri getContentUri() {
        return this.mImpl.getContentUri();
    }

    @NonNull
    public ClipDescription getDescription() {
        return this.mImpl.getDescription();
    }

    @Nullable
    public Uri getLinkUri() {
        return this.mImpl.getLinkUri();
    }

    public void releasePermission() {
        this.mImpl.releasePermission();
    }

    public void requestPermission() {
        this.mImpl.requestPermission();
    }

    @Nullable
    public Object unwrap() {
        return this.mImpl.getInputContentInfo();
    }

    @Nullable
    public static InputContentInfoCompat wrap(@Nullable Object object0) {
        if(object0 == null) {
            return null;
        }
        return Build.VERSION.SDK_INT >= 25 ? new InputContentInfoCompat(new InputContentInfoCompatApi25Impl(object0)) : null;
    }
}

