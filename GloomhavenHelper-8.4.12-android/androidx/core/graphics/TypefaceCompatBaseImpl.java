package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import androidx.core.content.res.FontResourcesParserCompat.FontFileResourceEntry;
import androidx.core.provider.FontsContractCompat.FontInfo;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestrictTo({Scope.LIBRARY_GROUP})
class TypefaceCompatBaseImpl {
    interface StyleExtractor {
        int getWeight(Object arg1);

        boolean isItalic(Object arg1);
    }

    private static final String CACHE_FILE_PREFIX = "cached_font_";
    private static final String TAG = "TypefaceCompatBaseImpl";

    @Nullable
    public Typeface createFromFontFamilyFilesResourceEntry(Context context0, FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry0, Resources resources0, int v) {
        FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry0 = this.findBestEntry(fontResourcesParserCompat$FontFamilyFilesResourceEntry0, v);
        return fontResourcesParserCompat$FontFileResourceEntry0 == null ? null : TypefaceCompat.createFromResourcesFontFile(context0, resources0, fontResourcesParserCompat$FontFileResourceEntry0.getResourceId(), fontResourcesParserCompat$FontFileResourceEntry0.getFileName(), v);
    }

    public Typeface createFromFontInfo(Context context0, @Nullable CancellationSignal cancellationSignal0, @NonNull FontInfo[] arr_fontsContractCompat$FontInfo, int v) {
        InputStream inputStream0;
        if(arr_fontsContractCompat$FontInfo.length < 1) {
            return null;
        }
        FontInfo fontsContractCompat$FontInfo0 = this.findBestInfo(arr_fontsContractCompat$FontInfo, v);
        try {
            inputStream0 = null;
            inputStream0 = context0.getContentResolver().openInputStream(fontsContractCompat$FontInfo0.getUri());
            return this.createFromInputStream(context0, inputStream0);
        }
        catch(IOException unused_ex) {
            return null;
        }
        finally {
            TypefaceCompatUtil.closeQuietly(inputStream0);
        }
    }

    protected Typeface createFromInputStream(Context context0, InputStream inputStream0) {
        File file0 = TypefaceCompatUtil.getTempFile(context0);
        if(file0 == null) {
            return null;
        }
        try {
            return TypefaceCompatUtil.copyToFile(file0, inputStream0) ? Typeface.createFromFile(file0.getPath()) : null;
        }
        catch(RuntimeException unused_ex) {
            return null;
        }
        finally {
            file0.delete();
        }
    }

    @Nullable
    public Typeface createFromResourcesFontFile(Context context0, Resources resources0, int v, String s, int v1) {
        File file0 = TypefaceCompatUtil.getTempFile(context0);
        if(file0 == null) {
            return null;
        }
        try {
            return TypefaceCompatUtil.copyToFile(file0, resources0, v) ? Typeface.createFromFile(file0.getPath()) : null;
        }
        catch(RuntimeException unused_ex) {
            return null;
        }
        finally {
            file0.delete();
        }
    }

    private FontFileResourceEntry findBestEntry(FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry0, int v) {
        return (FontFileResourceEntry)TypefaceCompatBaseImpl.findBestFont(fontResourcesParserCompat$FontFamilyFilesResourceEntry0.getEntries(), v, new StyleExtractor() {
            public int getWeight(FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry0) {
                return fontResourcesParserCompat$FontFileResourceEntry0.getWeight();
            }

            @Override  // androidx.core.graphics.TypefaceCompatBaseImpl$StyleExtractor
            public int getWeight(Object object0) {
                return this.getWeight(((FontFileResourceEntry)object0));
            }

            public boolean isItalic(FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry0) {
                return fontResourcesParserCompat$FontFileResourceEntry0.isItalic();
            }

            @Override  // androidx.core.graphics.TypefaceCompatBaseImpl$StyleExtractor
            public boolean isItalic(Object object0) {
                return this.isItalic(((FontFileResourceEntry)object0));
            }
        });
    }

    private static Object findBestFont(Object[] arr_object, int v, StyleExtractor typefaceCompatBaseImpl$StyleExtractor0) {
        Object object0 = null;
        int v2 = 0x7FFFFFFF;
        for(int v1 = 0; v1 < arr_object.length; ++v1) {
            Object object1 = arr_object[v1];
            int v3 = Math.abs(typefaceCompatBaseImpl$StyleExtractor0.getWeight(object1) - ((v & 1) == 0 ? 400 : 700)) * 2 + (typefaceCompatBaseImpl$StyleExtractor0.isItalic(object1) == ((v & 2) != 0) ? 0 : 1);
            if(object0 == null || v2 > v3) {
                object0 = object1;
                v2 = v3;
            }
        }
        return object0;
    }

    protected FontInfo findBestInfo(FontInfo[] arr_fontsContractCompat$FontInfo, int v) {
        return (FontInfo)TypefaceCompatBaseImpl.findBestFont(arr_fontsContractCompat$FontInfo, v, new StyleExtractor() {
            public int getWeight(FontInfo fontsContractCompat$FontInfo0) {
                return fontsContractCompat$FontInfo0.getWeight();
            }

            @Override  // androidx.core.graphics.TypefaceCompatBaseImpl$StyleExtractor
            public int getWeight(Object object0) {
                return this.getWeight(((FontInfo)object0));
            }

            public boolean isItalic(FontInfo fontsContractCompat$FontInfo0) {
                return fontsContractCompat$FontInfo0.isItalic();
            }

            @Override  // androidx.core.graphics.TypefaceCompatBaseImpl$StyleExtractor
            public boolean isItalic(Object object0) {
                return this.isItalic(((FontInfo)object0));
            }
        });
    }
}

