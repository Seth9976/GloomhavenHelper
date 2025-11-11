package androidx.core.content;

import android.content.Intent;
import android.os.Build.VERSION;
import androidx.annotation.NonNull;

public final class IntentCompat {
    public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final String EXTRA_START_PLAYBACK = "android.intent.extra.START_PLAYBACK";

    @NonNull
    public static Intent makeMainSelectorActivity(@NonNull String s, @NonNull String s1) {
        if(Build.VERSION.SDK_INT >= 15) {
            return Intent.makeMainSelectorActivity(s, s1);
        }
        Intent intent0 = new Intent(s);
        intent0.addCategory(s1);
        return intent0;
    }
}

