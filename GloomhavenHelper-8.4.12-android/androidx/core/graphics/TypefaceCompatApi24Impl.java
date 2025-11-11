package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import androidx.core.content.res.FontResourcesParserCompat.FontFileResourceEntry;
import androidx.core.provider.FontsContractCompat.FontInfo;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

@RequiresApi(24)
@RestrictTo({Scope.LIBRARY_GROUP})
class TypefaceCompatApi24Impl extends TypefaceCompatBaseImpl {
    private static final String ADD_FONT_WEIGHT_STYLE_METHOD = "addFontWeightStyle";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String TAG = "TypefaceCompatApi24Impl";
    private static final Method sAddFontWeightStyle;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;

    static {
        Method method1;
        Method method0;
        Class class0;
        Constructor constructor0 = null;
        try {
            class0 = Class.forName("android.graphics.FontFamily");
            Constructor constructor1 = class0.getConstructor();
            method0 = class0.getMethod("addFontWeightStyle", ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE);
            Class[] arr_class = {Array.newInstance(class0, 1).getClass()};
            method1 = Typeface.class.getMethod("createFromFamiliesWithDefault", arr_class);
            constructor0 = constructor1;
        }
        catch(ClassNotFoundException | NoSuchMethodException classNotFoundException0) {
            Log.e("TypefaceCompatApi24Impl", classNotFoundException0.getClass().getName(), classNotFoundException0);
            class0 = null;
            method1 = null;
            method0 = null;
        }
        TypefaceCompatApi24Impl.sFontFamilyCtor = constructor0;
        TypefaceCompatApi24Impl.sFontFamily = class0;
        TypefaceCompatApi24Impl.sAddFontWeightStyle = method0;
        TypefaceCompatApi24Impl.sCreateFromFamiliesWithDefault = method1;
    }

    private static boolean addFontWeightStyle(Object object0, ByteBuffer byteBuffer0, int v, int v1, boolean z) {
        try {
            return ((Boolean)TypefaceCompatApi24Impl.sAddFontWeightStyle.invoke(object0, byteBuffer0, v, null, v1, Boolean.valueOf(z))).booleanValue();
        }
        catch(IllegalAccessException | InvocationTargetException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
    }

    private static Typeface createFromFamiliesWithDefault(Object object0) {
        try {
            Object object1 = Array.newInstance(TypefaceCompatApi24Impl.sFontFamily, 1);
            Array.set(object1, 0, object0);
            return (Typeface)TypefaceCompatApi24Impl.sCreateFromFamiliesWithDefault.invoke(null, object1);
        }
        catch(IllegalAccessException | InvocationTargetException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
    }

    @Override  // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context context0, FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry0, Resources resources0, int v) {
        Object object0 = TypefaceCompatApi24Impl.newFamily();
        FontFileResourceEntry[] arr_fontResourcesParserCompat$FontFileResourceEntry = fontResourcesParserCompat$FontFamilyFilesResourceEntry0.getEntries();
        for(int v1 = 0; v1 < arr_fontResourcesParserCompat$FontFileResourceEntry.length; ++v1) {
            FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry0 = arr_fontResourcesParserCompat$FontFileResourceEntry[v1];
            ByteBuffer byteBuffer0 = TypefaceCompatUtil.copyToDirectBuffer(context0, resources0, fontResourcesParserCompat$FontFileResourceEntry0.getResourceId());
            if(byteBuffer0 == null) {
                return null;
            }
            if(!TypefaceCompatApi24Impl.addFontWeightStyle(object0, byteBuffer0, fontResourcesParserCompat$FontFileResourceEntry0.getTtcIndex(), fontResourcesParserCompat$FontFileResourceEntry0.getWeight(), fontResourcesParserCompat$FontFileResourceEntry0.isItalic())) {
                return null;
            }
        }
        return TypefaceCompatApi24Impl.createFromFamiliesWithDefault(object0);
    }

    @Override  // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontInfo(Context context0, @Nullable CancellationSignal cancellationSignal0, @NonNull FontInfo[] arr_fontsContractCompat$FontInfo, int v) {
        Object object0 = TypefaceCompatApi24Impl.newFamily();
        SimpleArrayMap simpleArrayMap0 = new SimpleArrayMap();
        for(int v1 = 0; v1 < arr_fontsContractCompat$FontInfo.length; ++v1) {
            FontInfo fontsContractCompat$FontInfo0 = arr_fontsContractCompat$FontInfo[v1];
            Uri uri0 = fontsContractCompat$FontInfo0.getUri();
            ByteBuffer byteBuffer0 = (ByteBuffer)simpleArrayMap0.get(uri0);
            if(byteBuffer0 == null) {
                byteBuffer0 = TypefaceCompatUtil.mmap(context0, cancellationSignal0, uri0);
                simpleArrayMap0.put(uri0, byteBuffer0);
            }
            if(!TypefaceCompatApi24Impl.addFontWeightStyle(object0, byteBuffer0, fontsContractCompat$FontInfo0.getTtcIndex(), fontsContractCompat$FontInfo0.getWeight(), fontsContractCompat$FontInfo0.isItalic())) {
                return null;
            }
        }
        return Typeface.create(TypefaceCompatApi24Impl.createFromFamiliesWithDefault(object0), v);
    }

    public static boolean isUsable() {
        if(TypefaceCompatApi24Impl.sAddFontWeightStyle == null) {
            Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return TypefaceCompatApi24Impl.sAddFontWeightStyle != null;
    }

    private static Object newFamily() {
        try {
            return TypefaceCompatApi24Impl.sFontFamilyCtor.newInstance();
        }
        catch(IllegalAccessException | InstantiationException | InvocationTargetException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
    }
}

