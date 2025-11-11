package androidx.core.content;

import android.content.SharedPreferences.Editor;
import androidx.annotation.NonNull;

@Deprecated
public final class SharedPreferencesCompat {
    @Deprecated
    public static final class EditorCompat {
        static class Helper {
            public void apply(@NonNull SharedPreferences.Editor sharedPreferences$Editor0) {
                try {
                    sharedPreferences$Editor0.apply();
                }
                catch(AbstractMethodError unused_ex) {
                    sharedPreferences$Editor0.commit();
                }
            }
        }

        private final Helper mHelper;
        private static EditorCompat sInstance;

        private EditorCompat() {
            this.mHelper = new Helper();
        }

        @Deprecated
        public void apply(@NonNull SharedPreferences.Editor sharedPreferences$Editor0) {
            this.mHelper.apply(sharedPreferences$Editor0);
        }

        @Deprecated
        public static EditorCompat getInstance() {
            if(EditorCompat.sInstance == null) {
                EditorCompat.sInstance = new EditorCompat();
            }
            return EditorCompat.sInstance;
        }
    }

}

