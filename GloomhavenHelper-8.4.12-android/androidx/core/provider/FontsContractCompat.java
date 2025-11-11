package androidx.core.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri.Builder;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.content.res.ResourcesCompat.FontCallback;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.graphics.TypefaceCompatUtil;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import jeb.synthetic.TWR;

public class FontsContractCompat {
    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";

    }

    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo[] mFonts;
        private final int mStatusCode;

        @RestrictTo({Scope.LIBRARY_GROUP})
        public FontFamilyResult(int v, @Nullable FontInfo[] arr_fontsContractCompat$FontInfo) {
            this.mStatusCode = v;
            this.mFonts = arr_fontsContractCompat$FontInfo;
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }
    }

    public static class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        @RestrictTo({Scope.LIBRARY_GROUP})
        public FontInfo(@NonNull Uri uri0, @IntRange(from = 0L) int v, @IntRange(from = 1L, to = 1000L) int v1, boolean z, int v2) {
            this.mUri = (Uri)Preconditions.checkNotNull(uri0);
            this.mTtcIndex = v;
            this.mWeight = v1;
            this.mItalic = z;
            this.mResultCode = v2;
        }

        public int getResultCode() {
            return this.mResultCode;
        }

        @IntRange(from = 0L)
        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        @NonNull
        public Uri getUri() {
            return this.mUri;
        }

        @IntRange(from = 1L, to = 1000L)
        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }
    }

    public static class FontRequestCallback {
        @RestrictTo({Scope.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        public @interface FontRequestFailReason {
        }

        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
        @RestrictTo({Scope.LIBRARY_GROUP})
        public static final int RESULT_OK;

        public void onTypefaceRequestFailed(int v) {
        }

        public void onTypefaceRetrieved(Typeface typeface0) {
        }
    }

    static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(@Nullable Typeface typeface0, int v) {
            this.mTypeface = typeface0;
            this.mResult = v;
        }
    }

    private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final String PARCEL_FONT_RESULTS = "font_results";
    @RestrictTo({Scope.LIBRARY_GROUP})
    static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
    @RestrictTo({Scope.LIBRARY_GROUP})
    static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    private static final String TAG = "FontsContractCompat";
    private static final SelfDestructiveThread sBackgroundThread;
    private static final Comparator sByteArrayComparator;
    static final Object sLock;
    @GuardedBy("sLock")
    static final SimpleArrayMap sPendingReplies;
    static final LruCache sTypefaceCache;

    static {
        FontsContractCompat.sTypefaceCache = new LruCache(16);
        FontsContractCompat.sBackgroundThread = new SelfDestructiveThread("fonts", 10, 10000);
        FontsContractCompat.sLock = new Object();
        FontsContractCompat.sPendingReplies = new SimpleArrayMap();
        FontsContractCompat.sByteArrayComparator = new Comparator() {
            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((byte[])object0), ((byte[])object1));
            }

            public int compare(byte[] arr_b, byte[] arr_b1) {
                if(arr_b.length != arr_b1.length) {
                    return arr_b.length - arr_b1.length;
                }
                for(int v = 0; v < arr_b.length; ++v) {
                    if(arr_b[v] != arr_b1[v]) {
                        return arr_b[v] - arr_b1[v];
                    }
                }
                return 0;
            }
        };
    }

    @Nullable
    public static Typeface buildTypeface(@NonNull Context context0, @Nullable CancellationSignal cancellationSignal0, @NonNull FontInfo[] arr_fontsContractCompat$FontInfo) {
        return TypefaceCompat.createFromFontInfo(context0, cancellationSignal0, arr_fontsContractCompat$FontInfo, 0);
    }

    private static List convertToByteArrayList(Signature[] arr_signature) {
        List list0 = new ArrayList();
        for(int v = 0; v < arr_signature.length; ++v) {
            list0.add(arr_signature[v].toByteArray());
        }
        return list0;
    }

    private static boolean equalsByteArrayList(List list0, List list1) {
        if(list0.size() != list1.size()) {
            return false;
        }
        for(int v = 0; v < list0.size(); ++v) {
            if(!Arrays.equals(((byte[])list0.get(v)), ((byte[])list1.get(v)))) {
                return false;
            }
        }
        return true;
    }

    @NonNull
    public static FontFamilyResult fetchFonts(@NonNull Context context0, @Nullable CancellationSignal cancellationSignal0, @NonNull FontRequest fontRequest0) throws PackageManager.NameNotFoundException {
        ProviderInfo providerInfo0 = FontsContractCompat.getProvider(context0.getPackageManager(), fontRequest0, context0.getResources());
        return providerInfo0 == null ? new FontFamilyResult(1, null) : new FontFamilyResult(0, FontsContractCompat.getFontFromProvider(context0, fontRequest0, providerInfo0.authority, cancellationSignal0));
    }

    private static List getCertificates(FontRequest fontRequest0, Resources resources0) {
        return fontRequest0.getCertificates() == null ? FontResourcesParserCompat.readCerts(resources0, fontRequest0.getCertificatesArrayResId()) : fontRequest0.getCertificates();
    }

    @NonNull
    @VisibleForTesting
    static FontInfo[] getFontFromProvider(Context context0, FontRequest fontRequest0, String s, CancellationSignal cancellationSignal0) {
        ArrayList arrayList0 = new ArrayList();
        Uri uri0 = new Uri.Builder().scheme("content").authority(s).build();
        Uri uri1 = new Uri.Builder().scheme("content").authority(s).appendPath("file").build();
        Cursor cursor0 = null;
        try {
            cursor0 = Build.VERSION.SDK_INT <= 16 ? context0.getContentResolver().query(uri0, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{fontRequest0.getQuery()}, null) : context0.getContentResolver().query(uri0, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{fontRequest0.getQuery()}, null, cancellationSignal0);
            if(cursor0 != null && cursor0.getCount() > 0) {
                int v = cursor0.getColumnIndex("result_code");
                ArrayList arrayList1 = new ArrayList();
                int v1 = cursor0.getColumnIndex("_id");
                int v2 = cursor0.getColumnIndex("file_id");
                int v3 = cursor0.getColumnIndex("font_ttc_index");
                int v4 = cursor0.getColumnIndex("font_weight");
                int v5 = cursor0.getColumnIndex("font_italic");
                while(cursor0.moveToNext()) {
                    int v6 = v == -1 ? 0 : cursor0.getInt(v);
                    int v7 = v3 == -1 ? 0 : cursor0.getInt(v3);
                    arrayList1.add(new FontInfo((v2 == -1 ? ContentUris.withAppendedId(uri0, cursor0.getLong(v1)) : ContentUris.withAppendedId(uri1, cursor0.getLong(v2))), v7, (v4 == -1 ? 400 : cursor0.getInt(v4)), v5 != -1 && cursor0.getInt(v5) == 1, v6));
                }
                arrayList0 = arrayList1;
            }
        }
        catch(Throwable throwable0) {
            TWR.safeClose$NT(cursor0, throwable0);
            throw throwable0;
        }
        if(cursor0 != null) {
            cursor0.close();
        }
        return (FontInfo[])arrayList0.toArray(new FontInfo[0]);
    }

    @NonNull
    static TypefaceResult getFontInternal(Context context0, FontRequest fontRequest0, int v) {
        FontFamilyResult fontsContractCompat$FontFamilyResult0;
        int v1 = -3;
        try {
            fontsContractCompat$FontFamilyResult0 = FontsContractCompat.fetchFonts(context0, null, fontRequest0);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return new TypefaceResult(null, -1);
        }
        switch(fontsContractCompat$FontFamilyResult0.getStatusCode()) {
            case 0: {
                Typeface typeface0 = TypefaceCompat.createFromFontInfo(context0, null, fontsContractCompat$FontFamilyResult0.getFonts(), v);
                if(typeface0 != null) {
                    v1 = 0;
                }
                return new TypefaceResult(typeface0, v1);
            }
            case 1: {
                return new TypefaceResult(null, -2);
            }
            default: {
                return new TypefaceResult(null, -3);
            }
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static Typeface getFontSync(Context context0, FontRequest fontRequest0, @Nullable FontCallback resourcesCompat$FontCallback0, @Nullable Handler handler0, boolean z, int v, int v1) {
        String s = fontRequest0.getIdentifier() + "-" + v1;
        Typeface typeface0 = (Typeface)FontsContractCompat.sTypefaceCache.get(s);
        if(typeface0 != null) {
            if(resourcesCompat$FontCallback0 != null) {
                resourcesCompat$FontCallback0.onFontRetrieved(typeface0);
            }
            return typeface0;
        }
        if(z && v == -1) {
            TypefaceResult fontsContractCompat$TypefaceResult0 = FontsContractCompat.getFontInternal(context0, fontRequest0, v1);
            if(resourcesCompat$FontCallback0 != null) {
                if(fontsContractCompat$TypefaceResult0.mResult == 0) {
                    resourcesCompat$FontCallback0.callbackSuccessAsync(fontsContractCompat$TypefaceResult0.mTypeface, handler0);
                    return fontsContractCompat$TypefaceResult0.mTypeface;
                }
                resourcesCompat$FontCallback0.callbackFailAsync(fontsContractCompat$TypefaceResult0.mResult, handler0);
            }
            return fontsContractCompat$TypefaceResult0.mTypeface;
        }
        androidx.core.provider.FontsContractCompat.1 fontsContractCompat$10 = new Callable() {
            public TypefaceResult call() throws Exception {
                TypefaceResult fontsContractCompat$TypefaceResult0 = FontsContractCompat.getFontInternal(context0, fontRequest0, v1);
                if(fontsContractCompat$TypefaceResult0.mTypeface != null) {
                    FontsContractCompat.sTypefaceCache.put(s, fontsContractCompat$TypefaceResult0.mTypeface);
                }
                return fontsContractCompat$TypefaceResult0;
            }

            @Override
            public Object call() throws Exception {
                return this.call();
            }
        };
        if(z) {
            try {
                return ((TypefaceResult)FontsContractCompat.sBackgroundThread.postAndWait(fontsContractCompat$10, v)).mTypeface;
            }
            catch(InterruptedException unused_ex) {
                return null;
            }
        }
        androidx.core.provider.FontsContractCompat.2 fontsContractCompat$20 = resourcesCompat$FontCallback0 == null ? null : new ReplyCallback() {
            public void onReply(TypefaceResult fontsContractCompat$TypefaceResult0) {
                if(fontsContractCompat$TypefaceResult0 == null) {
                    resourcesCompat$FontCallback0.callbackFailAsync(1, handler0);
                    return;
                }
                if(fontsContractCompat$TypefaceResult0.mResult == 0) {
                    resourcesCompat$FontCallback0.callbackSuccessAsync(fontsContractCompat$TypefaceResult0.mTypeface, handler0);
                    return;
                }
                resourcesCompat$FontCallback0.callbackFailAsync(fontsContractCompat$TypefaceResult0.mResult, handler0);
            }

            @Override  // androidx.core.provider.SelfDestructiveThread$ReplyCallback
            public void onReply(Object object0) {
                this.onReply(((TypefaceResult)object0));
            }
        };
        synchronized(FontsContractCompat.sLock) {
            if(FontsContractCompat.sPendingReplies.containsKey(s)) {
                if(fontsContractCompat$20 != null) {
                    ((ArrayList)FontsContractCompat.sPendingReplies.get(s)).add(fontsContractCompat$20);
                }
                return null;
            }
            if(fontsContractCompat$20 != null) {
                ArrayList arrayList0 = new ArrayList();
                arrayList0.add(fontsContractCompat$20);
                FontsContractCompat.sPendingReplies.put(s, arrayList0);
            }
        }
        androidx.core.provider.FontsContractCompat.3 fontsContractCompat$30 = new ReplyCallback() {
            public void onReply(TypefaceResult fontsContractCompat$TypefaceResult0) {
                ArrayList arrayList0;
                synchronized(FontsContractCompat.sLock) {
                    arrayList0 = (ArrayList)FontsContractCompat.sPendingReplies.get(s);
                    if(arrayList0 == null) {
                        return;
                    }
                    FontsContractCompat.sPendingReplies.remove(s);
                }
                for(int v1 = 0; v1 < arrayList0.size(); ++v1) {
                    ((ReplyCallback)arrayList0.get(v1)).onReply(fontsContractCompat$TypefaceResult0);
                }
            }

            @Override  // androidx.core.provider.SelfDestructiveThread$ReplyCallback
            public void onReply(Object object0) {
                this.onReply(((TypefaceResult)object0));
            }
        };
        FontsContractCompat.sBackgroundThread.postAndReply(fontsContractCompat$10, fontsContractCompat$30);
        return null;
    }

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP})
    @VisibleForTesting
    public static ProviderInfo getProvider(@NonNull PackageManager packageManager0, @NonNull FontRequest fontRequest0, @Nullable Resources resources0) throws PackageManager.NameNotFoundException {
        String s = fontRequest0.getProviderAuthority();
        ProviderInfo providerInfo0 = packageManager0.resolveContentProvider(s, 0);
        if(providerInfo0 == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + s);
        }
        if(!providerInfo0.packageName.equals(fontRequest0.getProviderPackage())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + s + ", but package was not " + fontRequest0.getProviderPackage());
        }
        List list0 = FontsContractCompat.convertToByteArrayList(packageManager0.getPackageInfo(providerInfo0.packageName, 0x40).signatures);
        Collections.sort(list0, FontsContractCompat.sByteArrayComparator);
        List list1 = FontsContractCompat.getCertificates(fontRequest0, resources0);
        for(int v = 0; v < list1.size(); ++v) {
            ArrayList arrayList0 = new ArrayList(((Collection)list1.get(v)));
            Collections.sort(arrayList0, FontsContractCompat.sByteArrayComparator);
            if(FontsContractCompat.equalsByteArrayList(list0, arrayList0)) {
                return providerInfo0;
            }
        }
        return null;
    }

    @RequiresApi(19)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static Map prepareFontData(Context context0, FontInfo[] arr_fontsContractCompat$FontInfo, CancellationSignal cancellationSignal0) {
        HashMap hashMap0 = new HashMap();
        for(int v = 0; v < arr_fontsContractCompat$FontInfo.length; ++v) {
            FontInfo fontsContractCompat$FontInfo0 = arr_fontsContractCompat$FontInfo[v];
            if(fontsContractCompat$FontInfo0.getResultCode() == 0) {
                Uri uri0 = fontsContractCompat$FontInfo0.getUri();
                if(!hashMap0.containsKey(uri0)) {
                    hashMap0.put(uri0, TypefaceCompatUtil.mmap(context0, cancellationSignal0, uri0));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap0);
    }

    public static void requestFont(@NonNull Context context0, @NonNull FontRequest fontRequest0, @NonNull FontRequestCallback fontsContractCompat$FontRequestCallback0, @NonNull Handler handler0) {
        handler0.post(new Runnable() {
            @Override
            public void run() {
                FontFamilyResult fontsContractCompat$FontFamilyResult0;
                try {
                    fontsContractCompat$FontFamilyResult0 = FontsContractCompat.fetchFonts(context0, null, fontRequest0);
                }
                catch(PackageManager.NameNotFoundException unused_ex) {
                    androidx.core.provider.FontsContractCompat.4.1 fontsContractCompat$4$10 = new Runnable() {
                        @Override
                        public void run() {
                        }
                    };
                    new Handler().post(fontsContractCompat$4$10);
                    return;
                }
                if(fontsContractCompat$FontFamilyResult0.getStatusCode() != 0) {
                    switch(fontsContractCompat$FontFamilyResult0.getStatusCode()) {
                        case 1: {
                            androidx.core.provider.FontsContractCompat.4.2 fontsContractCompat$4$20 = new Runnable() {
                                @Override
                                public void run() {
                                }
                            };
                            new Handler().post(fontsContractCompat$4$20);
                            return;
                        }
                        case 2: {
                            androidx.core.provider.FontsContractCompat.4.3 fontsContractCompat$4$30 = new Runnable() {
                                @Override
                                public void run() {
                                }
                            };
                            new Handler().post(fontsContractCompat$4$30);
                            return;
                        }
                        default: {
                            androidx.core.provider.FontsContractCompat.4.4 fontsContractCompat$4$40 = new Runnable() {
                                @Override
                                public void run() {
                                }
                            };
                            new Handler().post(fontsContractCompat$4$40);
                            return;
                        }
                    }
                }
                FontInfo[] arr_fontsContractCompat$FontInfo = fontsContractCompat$FontFamilyResult0.getFonts();
                if(arr_fontsContractCompat$FontInfo != null && arr_fontsContractCompat$FontInfo.length != 0) {
                    for(int v = 0; v < arr_fontsContractCompat$FontInfo.length; ++v) {
                        FontInfo fontsContractCompat$FontInfo0 = arr_fontsContractCompat$FontInfo[v];
                        if(fontsContractCompat$FontInfo0.getResultCode() != 0) {
                            int v1 = fontsContractCompat$FontInfo0.getResultCode();
                            if(v1 < 0) {
                                androidx.core.provider.FontsContractCompat.4.6 fontsContractCompat$4$60 = new Runnable() {
                                    @Override
                                    public void run() {
                                    }
                                };
                                new Handler().post(fontsContractCompat$4$60);
                                return;
                            }
                            androidx.core.provider.FontsContractCompat.4.7 fontsContractCompat$4$70 = new Runnable() {
                                @Override
                                public void run() {
                                }
                            };
                            new Handler().post(fontsContractCompat$4$70);
                            return;
                        }
                    }
                    Typeface typeface0 = FontsContractCompat.buildTypeface(context0, null, arr_fontsContractCompat$FontInfo);
                    if(typeface0 == null) {
                        androidx.core.provider.FontsContractCompat.4.8 fontsContractCompat$4$80 = new Runnable() {
                            @Override
                            public void run() {
                            }
                        };
                        new Handler().post(fontsContractCompat$4$80);
                        return;
                    }
                    androidx.core.provider.FontsContractCompat.4.9 fontsContractCompat$4$90 = new Runnable() {
                        @Override
                        public void run() {
                        }
                    };
                    new Handler().post(fontsContractCompat$4$90);
                    return;
                }
                androidx.core.provider.FontsContractCompat.4.5 fontsContractCompat$4$50 = new Runnable() {
                    @Override
                    public void run() {
                    }
                };
                new Handler().post(fontsContractCompat$4$50);
            }
        });
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static void resetCache() {
        FontsContractCompat.sTypefaceCache.evictAll();
    }
}

