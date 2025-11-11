package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class InputConnectionCompat {
    public interface OnCommitContentListener {
        boolean onCommitContent(InputContentInfoCompat arg1, int arg2, Bundle arg3);
    }

    private static final String COMMIT_CONTENT_ACTION = "androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT";
    private static final String COMMIT_CONTENT_CONTENT_URI_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI";
    private static final String COMMIT_CONTENT_DESCRIPTION_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
    private static final String COMMIT_CONTENT_FLAGS_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
    private static final String COMMIT_CONTENT_LINK_URI_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
    private static final String COMMIT_CONTENT_OPTS_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
    private static final String COMMIT_CONTENT_RESULT_RECEIVER = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
    public static final int INPUT_CONTENT_GRANT_READ_URI_PERMISSION = 1;

    public static boolean commitContent(@NonNull InputConnection inputConnection0, @NonNull EditorInfo editorInfo0, @NonNull InputContentInfoCompat inputContentInfoCompat0, int v, @Nullable Bundle bundle0) {
        ClipDescription clipDescription0 = inputContentInfoCompat0.getDescription();
        String[] arr_s = EditorInfoCompat.getContentMimeTypes(editorInfo0);
        for(int v1 = 0; true; ++v1) {
            boolean z = false;
            if(v1 >= arr_s.length) {
                break;
            }
            if(clipDescription0.hasMimeType(arr_s[v1])) {
                z = true;
                break;
            }
        }
        if(!z) {
            return false;
        }
        if(Build.VERSION.SDK_INT >= 25) {
            return inputConnection0.commitContent(((InputContentInfo)inputContentInfoCompat0.unwrap()), v, bundle0);
        }
        Bundle bundle1 = new Bundle();
        bundle1.putParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI", inputContentInfoCompat0.getContentUri());
        bundle1.putParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION", inputContentInfoCompat0.getDescription());
        bundle1.putParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI", inputContentInfoCompat0.getLinkUri());
        bundle1.putInt("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS", v);
        bundle1.putParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS", bundle0);
        return inputConnection0.performPrivateCommand("androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", bundle1);
    }

    @NonNull
    public static InputConnection createWrapper(@NonNull InputConnection inputConnection0, @NonNull EditorInfo editorInfo0, @NonNull OnCommitContentListener inputConnectionCompat$OnCommitContentListener0) {
        if(inputConnection0 == null) {
            throw new IllegalArgumentException("inputConnection must be non-null");
        }
        if(editorInfo0 == null) {
            throw new IllegalArgumentException("editorInfo must be non-null");
        }
        if(inputConnectionCompat$OnCommitContentListener0 == null) {
            throw new IllegalArgumentException("onCommitContentListener must be non-null");
        }
        if(Build.VERSION.SDK_INT >= 25) {
            return new InputConnectionWrapper(inputConnection0, false) {
                @Override  // android.view.inputmethod.InputConnectionWrapper
                public boolean commitContent(InputContentInfo inputContentInfo0, int v, Bundle bundle0) {
                    InputContentInfoCompat inputContentInfoCompat0 = InputContentInfoCompat.wrap(inputContentInfo0);
                    return inputConnectionCompat$OnCommitContentListener0.onCommitContent(inputContentInfoCompat0, v, bundle0) ? true : super.commitContent(inputContentInfo0, v, bundle0);
                }
            };
        }
        return EditorInfoCompat.getContentMimeTypes(editorInfo0).length == 0 ? inputConnection0 : new InputConnectionWrapper(inputConnection0, false) {
            // 去混淆评级： 低(20)
            @Override  // android.view.inputmethod.InputConnectionWrapper
            public boolean performPrivateCommand(String s, Bundle bundle0) {
                return InputConnectionCompat.handlePerformPrivateCommand(s, bundle0, inputConnectionCompat$OnCommitContentListener0) ? true : super.performPrivateCommand(s, bundle0);
            }
        };
    }

    static boolean handlePerformPrivateCommand(@Nullable String s, @NonNull Bundle bundle0, @NonNull OnCommitContentListener inputConnectionCompat$OnCommitContentListener0) {
        boolean z;
        ResultReceiver resultReceiver0;
        if(!TextUtils.equals("androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", s)) {
            return false;
        }
        if(bundle0 == null) {
            return false;
        }
        try {
            resultReceiver0 = null;
            resultReceiver0 = (ResultReceiver)bundle0.getParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER");
            Uri uri0 = (Uri)bundle0.getParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI");
            ClipDescription clipDescription0 = (ClipDescription)bundle0.getParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION");
            Uri uri1 = (Uri)bundle0.getParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI");
            int v = bundle0.getInt("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS");
            Bundle bundle1 = (Bundle)bundle0.getParcelable("androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS");
            z = inputConnectionCompat$OnCommitContentListener0.onCommitContent(new InputContentInfoCompat(uri0, clipDescription0, uri1), v, bundle1);
        }
        catch(Throwable throwable0) {
            if(resultReceiver0 != null) {
                resultReceiver0.send(0, null);
            }
            throw throwable0;
        }
        if(resultReceiver0 != null) {
            resultReceiver0.send(((int)z), null);
        }
        return z;
    }
}

