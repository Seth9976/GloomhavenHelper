package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat.FamilyResourceEntry;
import androidx.core.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import androidx.core.content.res.FontResourcesParserCompat.ProviderResourceEntry;
import androidx.core.content.res.ResourcesCompat.FontCallback;
import androidx.core.provider.FontsContractCompat.FontInfo;
import androidx.core.provider.FontsContractCompat;

@RestrictTo({Scope.LIBRARY_GROUP})
public class TypefaceCompat {
    private static final String TAG = "TypefaceCompat";
    private static final LruCache sTypefaceCache;
    private static final TypefaceCompatBaseImpl sTypefaceCompatImpl;

    static {
        if(Build.VERSION.SDK_INT >= 28) {
            TypefaceCompat.sTypefaceCompatImpl = new TypefaceCompatApi28Impl();
        }
        else if(Build.VERSION.SDK_INT >= 26) {
            TypefaceCompat.sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
        }
        else if(Build.VERSION.SDK_INT >= 24 && TypefaceCompatApi24Impl.isUsable()) {
            TypefaceCompat.sTypefaceCompatImpl = new TypefaceCompatApi24Impl();
        }
        else if(Build.VERSION.SDK_INT >= 21) {
            TypefaceCompat.sTypefaceCompatImpl = new TypefaceCompatApi21Impl();
        }
        else {
            TypefaceCompat.sTypefaceCompatImpl = new TypefaceCompatBaseImpl();
        }
        TypefaceCompat.sTypefaceCache = new LruCache(16);
    }

    @Nullable
    public static Typeface createFromFontInfo(@NonNull Context context0, @Nullable CancellationSignal cancellationSignal0, @NonNull FontInfo[] arr_fontsContractCompat$FontInfo, int v) {
        return TypefaceCompat.sTypefaceCompatImpl.createFromFontInfo(context0, cancellationSignal0, arr_fontsContractCompat$FontInfo, v);
    }

    @Nullable
    public static Typeface createFromResourcesFamilyXml(@NonNull Context context0, @NonNull FamilyResourceEntry fontResourcesParserCompat$FamilyResourceEntry0, @NonNull Resources resources0, int v, int v1, @Nullable FontCallback resourcesCompat$FontCallback0, @Nullable Handler handler0, boolean z) {
        Typeface typeface0;
        boolean z1 = false;
        if(fontResourcesParserCompat$FamilyResourceEntry0 instanceof ProviderResourceEntry) {
            if(!z) {
                if(resourcesCompat$FontCallback0 == null) {
                    z1 = true;
                }
            }
            else if(((ProviderResourceEntry)fontResourcesParserCompat$FamilyResourceEntry0).getFetchStrategy() == 0) {
                z1 = true;
            }
            typeface0 = FontsContractCompat.getFontSync(context0, ((ProviderResourceEntry)fontResourcesParserCompat$FamilyResourceEntry0).getRequest(), resourcesCompat$FontCallback0, handler0, z1, (z ? ((ProviderResourceEntry)fontResourcesParserCompat$FamilyResourceEntry0).getTimeout() : -1), v1);
        }
        else {
            typeface0 = TypefaceCompat.sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(context0, ((FontFamilyFilesResourceEntry)fontResourcesParserCompat$FamilyResourceEntry0), resources0, v1);
            if(resourcesCompat$FontCallback0 != null) {
                if(typeface0 == null) {
                    resourcesCompat$FontCallback0.callbackFailAsync(-3, handler0);
                }
                else {
                    resourcesCompat$FontCallback0.callbackSuccessAsync(typeface0, handler0);
                }
            }
        }
        if(typeface0 != null) {
            String s = TypefaceCompat.createResourceUid(resources0, v, v1);
            TypefaceCompat.sTypefaceCache.put(s, typeface0);
        }
        return typeface0;
    }

    @Nullable
    public static Typeface createFromResourcesFontFile(@NonNull Context context0, @NonNull Resources resources0, int v, String s, int v1) {
        Typeface typeface0 = TypefaceCompat.sTypefaceCompatImpl.createFromResourcesFontFile(context0, resources0, v, s, v1);
        if(typeface0 != null) {
            String s1 = TypefaceCompat.createResourceUid(resources0, v, v1);
            TypefaceCompat.sTypefaceCache.put(s1, typeface0);
        }
        return typeface0;
    }

    private static String createResourceUid(Resources resources0, int v, int v1) {
        return resources0.getResourcePackageName(v) + "-" + v + "-" + v1;
    }

    @Nullable
    public static Typeface findFromCache(@NonNull Resources resources0, int v, int v1) {
        String s = TypefaceCompat.createResourceUid(resources0, v, v1);
        return (Typeface)TypefaceCompat.sTypefaceCache.get(s);
    }
}

