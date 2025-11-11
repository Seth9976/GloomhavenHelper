package androidx.core.os;

import android.os.Build.VERSION;
import android.os.LocaleList;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.Size;
import java.util.Locale;

public final class LocaleListCompat {
    @RequiresApi(24)
    static class LocaleListCompatApi24Impl implements LocaleListInterface {
        private LocaleList mLocaleList;

        LocaleListCompatApi24Impl() {
            this.mLocaleList = new LocaleList(new Locale[0]);
        }

        @Override  // androidx.core.os.LocaleListInterface
        public boolean equals(Object object0) {
            return this.mLocaleList.equals(((LocaleListCompat)object0).unwrap());
        }

        @Override  // androidx.core.os.LocaleListInterface
        public Locale get(int v) {
            return this.mLocaleList.get(v);
        }

        @Override  // androidx.core.os.LocaleListInterface
        @Nullable
        public Locale getFirstMatch(String[] arr_s) {
            return this.mLocaleList == null ? null : this.mLocaleList.getFirstMatch(arr_s);
        }

        @Override  // androidx.core.os.LocaleListInterface
        public Object getLocaleList() {
            return this.mLocaleList;
        }

        @Override  // androidx.core.os.LocaleListInterface
        public int hashCode() {
            return this.mLocaleList.hashCode();
        }

        @Override  // androidx.core.os.LocaleListInterface
        @IntRange(from = -1L)
        public int indexOf(Locale locale0) {
            return this.mLocaleList.indexOf(locale0);
        }

        @Override  // androidx.core.os.LocaleListInterface
        public boolean isEmpty() {
            return this.mLocaleList.isEmpty();
        }

        @Override  // androidx.core.os.LocaleListInterface
        public void setLocaleList(@NonNull Locale[] arr_locale) {
            this.mLocaleList = new LocaleList(arr_locale);
        }

        @Override  // androidx.core.os.LocaleListInterface
        @IntRange(from = 0L)
        public int size() {
            return this.mLocaleList.size();
        }

        @Override  // androidx.core.os.LocaleListInterface
        public String toLanguageTags() {
            return this.mLocaleList.toLanguageTags();
        }

        @Override  // androidx.core.os.LocaleListInterface
        public String toString() {
            return this.mLocaleList.toString();
        }
    }

    static class LocaleListCompatBaseImpl implements LocaleListInterface {
        private LocaleListHelper mLocaleList;

        LocaleListCompatBaseImpl() {
            this.mLocaleList = new LocaleListHelper(new Locale[0]);
        }

        @Override  // androidx.core.os.LocaleListInterface
        public boolean equals(Object object0) {
            return this.mLocaleList.equals(((LocaleListCompat)object0).unwrap());
        }

        @Override  // androidx.core.os.LocaleListInterface
        public Locale get(int v) {
            return this.mLocaleList.get(v);
        }

        @Override  // androidx.core.os.LocaleListInterface
        @Nullable
        public Locale getFirstMatch(String[] arr_s) {
            return this.mLocaleList == null ? null : this.mLocaleList.getFirstMatch(arr_s);
        }

        @Override  // androidx.core.os.LocaleListInterface
        public Object getLocaleList() {
            return this.mLocaleList;
        }

        @Override  // androidx.core.os.LocaleListInterface
        public int hashCode() {
            return this.mLocaleList.hashCode();
        }

        @Override  // androidx.core.os.LocaleListInterface
        @IntRange(from = -1L)
        public int indexOf(Locale locale0) {
            return this.mLocaleList.indexOf(locale0);
        }

        @Override  // androidx.core.os.LocaleListInterface
        public boolean isEmpty() {
            return this.mLocaleList.isEmpty();
        }

        @Override  // androidx.core.os.LocaleListInterface
        public void setLocaleList(@NonNull Locale[] arr_locale) {
            this.mLocaleList = new LocaleListHelper(arr_locale);
        }

        @Override  // androidx.core.os.LocaleListInterface
        @IntRange(from = 0L)
        public int size() {
            return this.mLocaleList.size();
        }

        @Override  // androidx.core.os.LocaleListInterface
        public String toLanguageTags() {
            return this.mLocaleList.toLanguageTags();
        }

        @Override  // androidx.core.os.LocaleListInterface
        public String toString() {
            return this.mLocaleList.toString();
        }
    }

    static final LocaleListInterface IMPL;
    private static final LocaleListCompat sEmptyLocaleList;

    static {
        LocaleListCompat.sEmptyLocaleList = new LocaleListCompat();
        if(Build.VERSION.SDK_INT >= 24) {
            LocaleListCompat.IMPL = new LocaleListCompatApi24Impl();
            return;
        }
        LocaleListCompat.IMPL = new LocaleListCompatBaseImpl();
    }

    public static LocaleListCompat create(@NonNull Locale[] arr_locale) {
        LocaleListCompat localeListCompat0 = new LocaleListCompat();
        localeListCompat0.setLocaleListArray(arr_locale);
        return localeListCompat0;
    }

    @Override
    public boolean equals(Object object0) {
        return LocaleListCompat.IMPL.equals(object0);
    }

    @NonNull
    public static LocaleListCompat forLanguageTags(@Nullable String s) {
        if(s != null && !s.isEmpty()) {
            String[] arr_s = s.split(",", -1);
            Locale[] arr_locale = new Locale[arr_s.length];
            for(int v = 0; v < arr_locale.length; ++v) {
                arr_locale[v] = Build.VERSION.SDK_INT < 21 ? LocaleHelper.forLanguageTag(arr_s[v]) : Locale.forLanguageTag(arr_s[v]);
            }
            LocaleListCompat localeListCompat0 = new LocaleListCompat();
            localeListCompat0.setLocaleListArray(arr_locale);
            return localeListCompat0;
        }
        return LocaleListCompat.getEmptyLocaleList();
    }

    public Locale get(int v) {
        return LocaleListCompat.IMPL.get(v);
    }

    @NonNull
    @Size(min = 1L)
    public static LocaleListCompat getAdjustedDefault() {
        return Build.VERSION.SDK_INT < 24 ? LocaleListCompat.create(new Locale[]{Locale.getDefault()}) : LocaleListCompat.wrap(LocaleList.getAdjustedDefault());
    }

    @NonNull
    @Size(min = 1L)
    public static LocaleListCompat getDefault() {
        return Build.VERSION.SDK_INT < 24 ? LocaleListCompat.create(new Locale[]{Locale.getDefault()}) : LocaleListCompat.wrap(LocaleList.getDefault());
    }

    @NonNull
    public static LocaleListCompat getEmptyLocaleList() {
        return LocaleListCompat.sEmptyLocaleList;
    }

    public Locale getFirstMatch(String[] arr_s) {
        return LocaleListCompat.IMPL.getFirstMatch(arr_s);
    }

    @Override
    public int hashCode() {
        return LocaleListCompat.IMPL.hashCode();
    }

    @IntRange(from = -1L)
    public int indexOf(Locale locale0) {
        return LocaleListCompat.IMPL.indexOf(locale0);
    }

    public boolean isEmpty() {
        return LocaleListCompat.IMPL.isEmpty();
    }

    @RequiresApi(24)
    private void setLocaleList(LocaleList localeList0) {
        int v = localeList0.size();
        if(v > 0) {
            Locale[] arr_locale = new Locale[v];
            for(int v1 = 0; v1 < v; ++v1) {
                arr_locale[v1] = localeList0.get(v1);
            }
            LocaleListCompat.IMPL.setLocaleList(arr_locale);
        }
    }

    private void setLocaleListArray(Locale[] arr_locale) {
        LocaleListCompat.IMPL.setLocaleList(arr_locale);
    }

    @IntRange(from = 0L)
    public int size() {
        return LocaleListCompat.IMPL.size();
    }

    @NonNull
    public String toLanguageTags() {
        return LocaleListCompat.IMPL.toLanguageTags();
    }

    @Override
    public String toString() {
        return LocaleListCompat.IMPL.toString();
    }

    @Nullable
    public Object unwrap() {
        return LocaleListCompat.IMPL.getLocaleList();
    }

    @RequiresApi(24)
    public static LocaleListCompat wrap(Object object0) {
        LocaleListCompat localeListCompat0 = new LocaleListCompat();
        if(object0 instanceof LocaleList) {
            localeListCompat0.setLocaleList(((LocaleList)object0));
        }
        return localeListCompat0;
    }
}

