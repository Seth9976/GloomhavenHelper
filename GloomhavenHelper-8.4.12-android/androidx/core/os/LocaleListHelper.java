package androidx.core.os;

import android.os.Build.VERSION;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.annotation.Size;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

@RestrictTo({Scope.LIBRARY_GROUP})
final class LocaleListHelper {
    private static final Locale EN_LATN = null;
    private static final Locale LOCALE_AR_XB = null;
    private static final Locale LOCALE_EN_XA = null;
    private static final int NUM_PSEUDO_LOCALES = 2;
    private static final String STRING_AR_XB = "ar-XB";
    private static final String STRING_EN_XA = "en-XA";
    private final Locale[] mList;
    @NonNull
    private final String mStringRepresentation;
    @GuardedBy("sLock")
    private static LocaleListHelper sDefaultAdjustedLocaleList;
    @GuardedBy("sLock")
    private static LocaleListHelper sDefaultLocaleList;
    private static final Locale[] sEmptyList;
    private static final LocaleListHelper sEmptyLocaleList;
    @GuardedBy("sLock")
    private static Locale sLastDefaultLocale;
    @GuardedBy("sLock")
    private static LocaleListHelper sLastExplicitlySetLocaleList;
    private static final Object sLock;

    static {
        LocaleListHelper.sEmptyList = new Locale[0];
        LocaleListHelper.sEmptyLocaleList = new LocaleListHelper(new Locale[0]);
        LocaleListHelper.LOCALE_EN_XA = new Locale("en", "XA");
        LocaleListHelper.LOCALE_AR_XB = new Locale("ar", "XB");
        LocaleListHelper.EN_LATN = LocaleHelper.forLanguageTag("en-Latn");
        LocaleListHelper.sLock = new Object();
        LocaleListHelper.sLastExplicitlySetLocaleList = null;
        LocaleListHelper.sDefaultLocaleList = null;
        LocaleListHelper.sDefaultAdjustedLocaleList = null;
        LocaleListHelper.sLastDefaultLocale = null;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    LocaleListHelper(@NonNull Locale locale0, LocaleListHelper localeListHelper0) {
        if(locale0 == null) {
            throw new NullPointerException("topLocale is null");
        }
        int v1 = localeListHelper0 == null ? 0 : localeListHelper0.mList.length;
        int v2;
        for(v2 = 0; true; ++v2) {
            if(v2 >= v1) {
                v2 = -1;
                break;
            }
            if(locale0.equals(localeListHelper0.mList[v2])) {
                break;
            }
        }
        int v3 = (v2 == -1 ? 1 : 0) + v1;
        Locale[] arr_locale = new Locale[v3];
        arr_locale[0] = (Locale)locale0.clone();
        if(v2 == -1) {
            for(int v4 = 0; v4 < v1; ++v4) {
                arr_locale[v4 + 1] = (Locale)localeListHelper0.mList[v4].clone();
            }
        }
        else {
            for(int v5 = 0; v5 < v2; ++v5) {
                arr_locale[v5 + 1] = (Locale)localeListHelper0.mList[v5].clone();
            }
            for(int v6 = v2 + 1; v6 < v1; ++v6) {
                arr_locale[v6] = (Locale)localeListHelper0.mList[v6].clone();
            }
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        for(int v = 0; v < v3; ++v) {
            stringBuilder0.append(LocaleHelper.toLanguageTag(arr_locale[v]));
            if(v < v3 - 1) {
                stringBuilder0.append(',');
            }
        }
        this.mList = arr_locale;
        this.mStringRepresentation = stringBuilder0.toString();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    LocaleListHelper(@NonNull Locale[] arr_locale) {
        if(arr_locale.length == 0) {
            this.mList = LocaleListHelper.sEmptyList;
            this.mStringRepresentation = "";
            return;
        }
        Locale[] arr_locale1 = new Locale[arr_locale.length];
        HashSet hashSet0 = new HashSet();
        StringBuilder stringBuilder0 = new StringBuilder();
        for(int v = 0; v < arr_locale.length; ++v) {
            Locale locale0 = arr_locale[v];
            if(locale0 == null) {
                throw new NullPointerException("list[" + v + "] is null");
            }
            if(hashSet0.contains(locale0)) {
                throw new IllegalArgumentException("list[" + v + "] is a repetition");
            }
            Locale locale1 = (Locale)locale0.clone();
            arr_locale1[v] = locale1;
            stringBuilder0.append(LocaleHelper.toLanguageTag(locale1));
            if(v < arr_locale.length - 1) {
                stringBuilder0.append(',');
            }
            hashSet0.add(locale1);
        }
        this.mList = arr_locale1;
        this.mStringRepresentation = stringBuilder0.toString();
    }

    private Locale computeFirstMatch(Collection collection0, boolean z) {
        int v = this.computeFirstMatchIndex(collection0, z);
        return v == -1 ? null : this.mList[v];
    }

    private int computeFirstMatchIndex(Collection collection0, boolean z) {
        int v;
        Locale[] arr_locale = this.mList;
        if(arr_locale.length == 1) {
            return 0;
        }
        if(arr_locale.length == 0) {
            return -1;
        }
        if(z) {
            v = this.findFirstMatchIndex(LocaleListHelper.EN_LATN);
            if(v == 0) {
                return 0;
            }
            if(v >= 0x7FFFFFFF) {
                v = 0x7FFFFFFF;
            }
        }
        else {
            v = 0x7FFFFFFF;
        }
        for(Object object0: collection0) {
            int v1 = this.findFirstMatchIndex(LocaleHelper.forLanguageTag(((String)object0)));
            if(v1 == 0) {
                return 0;
            }
            if(v1 < v) {
                v = v1;
            }
        }
        return v == 0x7FFFFFFF ? 0 : v;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof LocaleListHelper)) {
            return false;
        }
        Locale[] arr_locale = ((LocaleListHelper)object0).mList;
        if(this.mList.length != arr_locale.length) {
            return false;
        }
        for(int v = 0; true; ++v) {
            Locale[] arr_locale1 = this.mList;
            if(v >= arr_locale1.length) {
                break;
            }
            if(!arr_locale1[v].equals(arr_locale[v])) {
                return false;
            }
        }
        return true;
    }

    private int findFirstMatchIndex(Locale locale0) {
        for(int v = 0; true; ++v) {
            Locale[] arr_locale = this.mList;
            if(v >= arr_locale.length) {
                break;
            }
            if(LocaleListHelper.matchScore(locale0, arr_locale[v]) > 0) {
                return v;
            }
        }
        return 0x7FFFFFFF;
    }

    @NonNull
    @RestrictTo({Scope.LIBRARY_GROUP})
    static LocaleListHelper forLanguageTags(@Nullable String s) {
        if(s != null && !s.isEmpty()) {
            String[] arr_s = s.split(",", -1);
            Locale[] arr_locale = new Locale[arr_s.length];
            for(int v = 0; v < arr_locale.length; ++v) {
                arr_locale[v] = LocaleHelper.forLanguageTag(arr_s[v]);
            }
            return new LocaleListHelper(arr_locale);
        }
        return LocaleListHelper.getEmptyLocaleList();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    Locale get(int v) {
        if(v >= 0) {
            return v >= this.mList.length ? null : this.mList[v];
        }
        return null;
    }

    @NonNull
    @Size(min = 1L)
    static LocaleListHelper getAdjustedDefault() {
        LocaleListHelper.getDefault();
        synchronized(LocaleListHelper.sLock) {
        }
        return LocaleListHelper.sDefaultAdjustedLocaleList;
    }

    @NonNull
    @RestrictTo({Scope.LIBRARY_GROUP})
    @Size(min = 1L)
    static LocaleListHelper getDefault() {
        Locale locale0 = Locale.getDefault();
        synchronized(LocaleListHelper.sLock) {
            if(!locale0.equals(LocaleListHelper.sLastDefaultLocale)) {
                LocaleListHelper.sLastDefaultLocale = locale0;
                if(LocaleListHelper.sDefaultLocaleList != null && locale0.equals(LocaleListHelper.sDefaultLocaleList.get(0))) {
                    return LocaleListHelper.sDefaultLocaleList;
                }
                LocaleListHelper.sDefaultLocaleList = new LocaleListHelper(locale0, LocaleListHelper.sLastExplicitlySetLocaleList);
                LocaleListHelper.sDefaultAdjustedLocaleList = LocaleListHelper.sDefaultLocaleList;
            }
            return LocaleListHelper.sDefaultLocaleList;
        }
    }

    @NonNull
    @RestrictTo({Scope.LIBRARY_GROUP})
    static LocaleListHelper getEmptyLocaleList() {
        return LocaleListHelper.sEmptyLocaleList;
    }

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP})
    Locale getFirstMatch(String[] arr_s) {
        return this.computeFirstMatch(Arrays.asList(arr_s), false);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    int getFirstMatchIndex(String[] arr_s) {
        return this.computeFirstMatchIndex(Arrays.asList(arr_s), false);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    int getFirstMatchIndexWithEnglishSupported(Collection collection0) {
        return this.computeFirstMatchIndex(collection0, true);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    int getFirstMatchIndexWithEnglishSupported(String[] arr_s) {
        return this.getFirstMatchIndexWithEnglishSupported(Arrays.asList(arr_s));
    }

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP})
    Locale getFirstMatchWithEnglishSupported(String[] arr_s) {
        return this.computeFirstMatch(Arrays.asList(arr_s), true);
    }

    private static String getLikelyScript(Locale locale0) {
        if(Build.VERSION.SDK_INT >= 21) {
            String s = locale0.getScript();
            return s.isEmpty() ? "" : s;
        }
        return "";
    }

    @Override
    public int hashCode() {
        int v = 1;
        for(int v1 = 0; true; ++v1) {
            Locale[] arr_locale = this.mList;
            if(v1 >= arr_locale.length) {
                break;
            }
            v = v * 0x1F + arr_locale[v1].hashCode();
        }
        return v;
    }

    @IntRange(from = -1L)
    @RestrictTo({Scope.LIBRARY_GROUP})
    int indexOf(Locale locale0) {
        for(int v = 0; true; ++v) {
            Locale[] arr_locale = this.mList;
            if(v >= arr_locale.length) {
                break;
            }
            if(arr_locale[v].equals(locale0)) {
                return v;
            }
        }
        return -1;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    boolean isEmpty() {
        return this.mList.length == 0;
    }

    // 去混淆评级： 低(20)
    private static boolean isPseudoLocale(String s) {
        return "en-XA".equals(s) || "ar-XB".equals(s);
    }

    // 去混淆评级： 低(20)
    private static boolean isPseudoLocale(Locale locale0) {
        return LocaleListHelper.LOCALE_EN_XA.equals(locale0) || LocaleListHelper.LOCALE_AR_XB.equals(locale0);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    static boolean isPseudoLocalesOnly(@Nullable String[] arr_s) {
        if(arr_s == null) {
            return true;
        }
        if(arr_s.length > 3) {
            return false;
        }
        for(int v = 0; v < arr_s.length; ++v) {
            String s = arr_s[v];
            if(!s.isEmpty() && !LocaleListHelper.isPseudoLocale(s)) {
                return false;
            }
        }
        return true;
    }

    @IntRange(from = 0L, to = 1L)
    private static int matchScore(Locale locale0, Locale locale1) {
        if(locale0.equals(locale1)) {
            return 1;
        }
        if(!locale0.getLanguage().equals(locale1.getLanguage())) {
            return 0;
        }
        if(!LocaleListHelper.isPseudoLocale(locale0) && !LocaleListHelper.isPseudoLocale(locale1)) {
            String s = LocaleListHelper.getLikelyScript(locale0);
            if(s.isEmpty()) {
                String s1 = locale0.getCountry();
                return s1.isEmpty() || s1.equals(locale1.getCountry()) ? 1 : 0;
            }
            return s.equals(LocaleListHelper.getLikelyScript(locale1));
        }
        return 0;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    static void setDefault(@NonNull @Size(min = 1L) LocaleListHelper localeListHelper0) {
        LocaleListHelper.setDefault(localeListHelper0, 0);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    static void setDefault(@NonNull @Size(min = 1L) LocaleListHelper localeListHelper0, int v) {
        if(localeListHelper0 == null) {
            throw new NullPointerException("locales is null");
        }
        if(localeListHelper0.isEmpty()) {
            throw new IllegalArgumentException("locales is empty");
        }
        synchronized(LocaleListHelper.sLock) {
            LocaleListHelper.sLastDefaultLocale = localeListHelper0.get(v);
            Locale.setDefault(LocaleListHelper.sLastDefaultLocale);
            LocaleListHelper.sLastExplicitlySetLocaleList = localeListHelper0;
            LocaleListHelper.sDefaultLocaleList = localeListHelper0;
            LocaleListHelper.sDefaultAdjustedLocaleList = v == 0 ? LocaleListHelper.sDefaultLocaleList : new LocaleListHelper(LocaleListHelper.sLastDefaultLocale, LocaleListHelper.sDefaultLocaleList);
        }
    }

    @IntRange(from = 0L)
    @RestrictTo({Scope.LIBRARY_GROUP})
    int size() {
        return this.mList.length;
    }

    @NonNull
    @RestrictTo({Scope.LIBRARY_GROUP})
    String toLanguageTags() {
        return this.mStringRepresentation;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("[");
        for(int v = 0; true; ++v) {
            Locale[] arr_locale = this.mList;
            if(v >= arr_locale.length) {
                break;
            }
            stringBuilder0.append(arr_locale[v]);
            if(v < this.mList.length - 1) {
                stringBuilder0.append(',');
            }
        }
        stringBuilder0.append("]");
        return stringBuilder0.toString();
    }
}

