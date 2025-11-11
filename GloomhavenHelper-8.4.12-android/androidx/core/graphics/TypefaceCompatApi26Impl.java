package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface.Builder;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import androidx.core.content.res.FontResourcesParserCompat.FontFileResourceEntry;
import androidx.core.provider.FontsContractCompat.FontInfo;
import androidx.core.provider.FontsContractCompat;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

@RequiresApi(26)
@RestrictTo({Scope.LIBRARY_GROUP})
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    private static final String ABORT_CREATION_METHOD = "abortCreation";
    private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
    private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String DEFAULT_FAMILY = "sans-serif";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String FREEZE_METHOD = "freeze";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi26Impl";
    protected final Method mAbortCreation;
    protected final Method mAddFontFromAssetManager;
    protected final Method mAddFontFromBuffer;
    protected final Method mCreateFromFamiliesWithDefault;
    protected final Class mFontFamily;
    protected final Constructor mFontFamilyCtor;
    protected final Method mFreeze;

    public TypefaceCompatApi26Impl() {
        Method method4;
        Method method3;
        Method method2;
        Method method1;
        Method method0;
        Constructor constructor0;
        Class class0 = null;
        try {
            Class class1 = this.obtainFontFamily();
            constructor0 = this.obtainFontFamilyCtor(class1);
            method0 = this.obtainAddFontFromAssetManagerMethod(class1);
            method1 = this.obtainAddFontFromBufferMethod(class1);
            method2 = this.obtainFreezeMethod(class1);
            method3 = this.obtainAbortCreationMethod(class1);
            method4 = this.obtainCreateFromFamiliesWithDefaultMethod(class1);
            class0 = class1;
        }
        catch(ClassNotFoundException | NoSuchMethodException classNotFoundException0) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + classNotFoundException0.getClass().getName(), classNotFoundException0);
            method4 = null;
            constructor0 = null;
            method0 = null;
            method1 = null;
            method2 = null;
            method3 = null;
        }
        this.mFontFamily = class0;
        this.mFontFamilyCtor = constructor0;
        this.mAddFontFromAssetManager = method0;
        this.mAddFontFromBuffer = method1;
        this.mFreeze = method2;
        this.mAbortCreation = method3;
        this.mCreateFromFamiliesWithDefault = method4;
    }

    private void abortCreation(Object object0) {
        try {
            this.mAbortCreation.invoke(object0);
        }
        catch(IllegalAccessException | InvocationTargetException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
    }

    private boolean addFontFromAssetManager(Context context0, Object object0, String s, int v, int v1, int v2, @Nullable FontVariationAxis[] arr_fontVariationAxis) {
        try {
            Object[] arr_object = {context0.getAssets(), s, 0, Boolean.FALSE, v, v1, v2, arr_fontVariationAxis};
            return ((Boolean)this.mAddFontFromAssetManager.invoke(object0, arr_object)).booleanValue();
        }
        catch(IllegalAccessException | InvocationTargetException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
    }

    private boolean addFontFromBuffer(Object object0, ByteBuffer byteBuffer0, int v, int v1, int v2) {
        try {
            return ((Boolean)this.mAddFontFromBuffer.invoke(object0, byteBuffer0, v, null, v1, v2)).booleanValue();
        }
        catch(IllegalAccessException | InvocationTargetException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
    }

    protected Typeface createFromFamiliesWithDefault(Object object0) {
        try {
            Object object1 = Array.newInstance(this.mFontFamily, 1);
            Array.set(object1, 0, object0);
            return (Typeface)this.mCreateFromFamiliesWithDefault.invoke(null, object1, -1, -1);
        }
        catch(IllegalAccessException | InvocationTargetException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
    }

    @Override  // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context context0, FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry0, Resources resources0, int v) {
        if(!this.isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(context0, fontResourcesParserCompat$FontFamilyFilesResourceEntry0, resources0, v);
        }
        Object object0 = this.newFamily();
        FontFileResourceEntry[] arr_fontResourcesParserCompat$FontFileResourceEntry = fontResourcesParserCompat$FontFamilyFilesResourceEntry0.getEntries();
        for(int v1 = 0; v1 < arr_fontResourcesParserCompat$FontFileResourceEntry.length; ++v1) {
            FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry0 = arr_fontResourcesParserCompat$FontFileResourceEntry[v1];
            if(!this.addFontFromAssetManager(context0, object0, fontResourcesParserCompat$FontFileResourceEntry0.getFileName(), fontResourcesParserCompat$FontFileResourceEntry0.getTtcIndex(), fontResourcesParserCompat$FontFileResourceEntry0.getWeight(), ((int)fontResourcesParserCompat$FontFileResourceEntry0.isItalic()), FontVariationAxis.fromFontVariationSettings(fontResourcesParserCompat$FontFileResourceEntry0.getVariationSettings()))) {
                this.abortCreation(object0);
                return null;
            }
        }
        return this.freeze(object0) ? this.createFromFamiliesWithDefault(object0) : null;
    }

    @Override  // androidx.core.graphics.TypefaceCompatApi21Impl
    public Typeface createFromFontInfo(Context context0, @Nullable CancellationSignal cancellationSignal0, @NonNull FontInfo[] arr_fontsContractCompat$FontInfo, int v) {
        if(arr_fontsContractCompat$FontInfo.length < 1) {
            return null;
        }
        if(!this.isFontFamilyPrivateAPIAvailable()) {
            FontInfo fontsContractCompat$FontInfo0 = this.findBestInfo(arr_fontsContractCompat$FontInfo, v);
            ContentResolver contentResolver0 = context0.getContentResolver();
            try(ParcelFileDescriptor parcelFileDescriptor0 = contentResolver0.openFileDescriptor(fontsContractCompat$FontInfo0.getUri(), "r", cancellationSignal0)) {
                if(parcelFileDescriptor0 != null) {
                    return new Typeface.Builder(parcelFileDescriptor0.getFileDescriptor()).setWeight(fontsContractCompat$FontInfo0.getWeight()).setItalic(fontsContractCompat$FontInfo0.isItalic()).build();
                }
            }
            catch(IOException unused_ex) {
            }
            return null;
        }
        Map map0 = FontsContractCompat.prepareFontData(context0, arr_fontsContractCompat$FontInfo, cancellationSignal0);
        Object object0 = this.newFamily();
        boolean z = false;
        for(int v1 = 0; v1 < arr_fontsContractCompat$FontInfo.length; ++v1) {
            FontInfo fontsContractCompat$FontInfo1 = arr_fontsContractCompat$FontInfo[v1];
            ByteBuffer byteBuffer0 = (ByteBuffer)map0.get(fontsContractCompat$FontInfo1.getUri());
            if(byteBuffer0 != null) {
                if(!this.addFontFromBuffer(object0, byteBuffer0, fontsContractCompat$FontInfo1.getTtcIndex(), fontsContractCompat$FontInfo1.getWeight(), ((int)fontsContractCompat$FontInfo1.isItalic()))) {
                    this.abortCreation(object0);
                    return null;
                }
                z = true;
            }
        }
        if(!z) {
            this.abortCreation(object0);
            return null;
        }
        return this.freeze(object0) ? Typeface.create(this.createFromFamiliesWithDefault(object0), v) : null;
    }

    @Override  // androidx.core.graphics.TypefaceCompatBaseImpl
    @Nullable
    public Typeface createFromResourcesFontFile(Context context0, Resources resources0, int v, String s, int v1) {
        if(!this.isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context0, resources0, v, s, v1);
        }
        Object object0 = this.newFamily();
        if(!this.addFontFromAssetManager(context0, object0, s, 0, -1, -1, null)) {
            this.abortCreation(object0);
            return null;
        }
        return this.freeze(object0) ? this.createFromFamiliesWithDefault(object0) : null;
    }

    private boolean freeze(Object object0) {
        try {
            return ((Boolean)this.mFreeze.invoke(object0)).booleanValue();
        }
        catch(IllegalAccessException | InvocationTargetException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
    }

    private boolean isFontFamilyPrivateAPIAvailable() {
        if(this.mAddFontFromAssetManager == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.mAddFontFromAssetManager != null;
    }

    private Object newFamily() {
        try {
            return this.mFontFamilyCtor.newInstance();
        }
        catch(IllegalAccessException | InstantiationException | InvocationTargetException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
    }

    protected Method obtainAbortCreationMethod(Class class0) throws NoSuchMethodException {
        return class0.getMethod("abortCreation");
    }

    protected Method obtainAddFontFromAssetManagerMethod(Class class0) throws NoSuchMethodException {
        return class0.getMethod("addFontFromAssetManager", AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class);
    }

    protected Method obtainAddFontFromBufferMethod(Class class0) throws NoSuchMethodException {
        return class0.getMethod("addFontFromBuffer", ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE);
    }

    protected Method obtainCreateFromFamiliesWithDefaultMethod(Class class0) throws NoSuchMethodException {
        Class[] arr_class = {Array.newInstance(class0, 1).getClass(), Integer.TYPE, Integer.TYPE};
        Method method0 = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", arr_class);
        method0.setAccessible(true);
        return method0;
    }

    protected Class obtainFontFamily() throws ClassNotFoundException {
        return Class.forName("android.graphics.FontFamily");
    }

    protected Constructor obtainFontFamilyCtor(Class class0) throws NoSuchMethodException {
        return class0.getConstructor();
    }

    protected Method obtainFreezeMethod(Class class0) throws NoSuchMethodException {
        return class0.getMethod("freeze");
    }
}

