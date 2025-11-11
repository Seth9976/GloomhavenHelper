package androidx.core.os;

import android.content.res.Configuration;
import android.os.Build.VERSION;
import java.util.Locale;

public final class ConfigurationCompat {
    public static LocaleListCompat getLocales(Configuration configuration0) {
        return Build.VERSION.SDK_INT < 24 ? LocaleListCompat.create(new Locale[]{configuration0.locale}) : LocaleListCompat.wrap(configuration0.getLocales());
    }
}

