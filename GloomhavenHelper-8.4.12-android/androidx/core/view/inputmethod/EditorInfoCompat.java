package androidx.core.view.inputmethod;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class EditorInfoCompat {
    private static final String CONTENT_MIME_TYPES_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
    private static final String[] EMPTY_STRING_ARRAY = null;
    public static final int IME_FLAG_FORCE_ASCII = 0x80000000;
    public static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 0x1000000;

    static {
        EditorInfoCompat.EMPTY_STRING_ARRAY = new String[0];
    }

    @NonNull
    public static String[] getContentMimeTypes(EditorInfo editorInfo0) {
        if(Build.VERSION.SDK_INT >= 25) {
            return editorInfo0.contentMimeTypes == null ? EditorInfoCompat.EMPTY_STRING_ARRAY : editorInfo0.contentMimeTypes;
        }
        if(editorInfo0.extras == null) {
            return EditorInfoCompat.EMPTY_STRING_ARRAY;
        }
        String[] arr_s = editorInfo0.extras.getStringArray("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES");
        return arr_s == null ? EditorInfoCompat.EMPTY_STRING_ARRAY : arr_s;
    }

    public static void setContentMimeTypes(@NonNull EditorInfo editorInfo0, @Nullable String[] arr_s) {
        if(Build.VERSION.SDK_INT >= 25) {
            editorInfo0.contentMimeTypes = arr_s;
            return;
        }
        if(editorInfo0.extras == null) {
            editorInfo0.extras = new Bundle();
        }
        editorInfo0.extras.putStringArray("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES", arr_s);
    }
}

