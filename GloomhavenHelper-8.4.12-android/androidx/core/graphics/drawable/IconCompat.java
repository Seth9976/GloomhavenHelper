package androidx.core.graphics.drawable;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent.ShortcutIconResource;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.Preconditions;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

public class IconCompat extends CustomVersionedParcelable {
    @RestrictTo({Scope.LIBRARY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface IconType {
    }

    private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25f;
    private static final int AMBIENT_SHADOW_ALPHA = 30;
    private static final float BLUR_FACTOR = 0.010417f;
    static final PorterDuff.Mode DEFAULT_TINT_MODE = null;
    private static final float DEFAULT_VIEW_PORT_SCALE = 0.666667f;
    private static final String EXTRA_INT1 = "int1";
    private static final String EXTRA_INT2 = "int2";
    private static final String EXTRA_OBJ = "obj";
    private static final String EXTRA_TINT_LIST = "tint_list";
    private static final String EXTRA_TINT_MODE = "tint_mode";
    private static final String EXTRA_TYPE = "type";
    private static final float ICON_DIAMETER_FACTOR = 0.916667f;
    private static final int KEY_SHADOW_ALPHA = 61;
    private static final float KEY_SHADOW_OFFSET_FACTOR = 0.020833f;
    private static final String TAG = "IconCompat";
    public static final int TYPE_UNKNOWN = -1;
    @RestrictTo({Scope.LIBRARY})
    public byte[] mData;
    @RestrictTo({Scope.LIBRARY})
    public int mInt1;
    @RestrictTo({Scope.LIBRARY})
    public int mInt2;
    Object mObj1;
    @RestrictTo({Scope.LIBRARY})
    public Parcelable mParcelable;
    @RestrictTo({Scope.LIBRARY})
    public ColorStateList mTintList;
    PorterDuff.Mode mTintMode;
    @RestrictTo({Scope.LIBRARY})
    public String mTintModeStr;
    @RestrictTo({Scope.LIBRARY})
    public int mType;

    static {
        IconCompat.DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    }

    @RestrictTo({Scope.LIBRARY})
    public IconCompat() {
        this.mTintList = null;
        this.mTintMode = IconCompat.DEFAULT_TINT_MODE;
    }

    private IconCompat(int v) {
        this.mTintList = null;
        this.mTintMode = IconCompat.DEFAULT_TINT_MODE;
        this.mType = v;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void addToShortcutIntent(@NonNull Intent intent0, @Nullable Drawable drawable0, @NonNull Context context0) {
        Bitmap bitmap0;
        this.checkResource(context0);
        int v = this.mType;
        if(v == 5) {
            bitmap0 = IconCompat.createLegacyIconFromAdaptiveIcon(((Bitmap)this.mObj1), true);
        }
        else {
            switch(v) {
                case 1: {
                    bitmap0 = (Bitmap)this.mObj1;
                    if(drawable0 != null) {
                        bitmap0 = bitmap0.copy(bitmap0.getConfig(), true);
                    }
                    break;
                }
                case 2: {
                    try {
                        Context context1 = context0.createPackageContext(this.getResPackage(), 0);
                        if(drawable0 == null) {
                            intent0.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(context1, this.mInt1));
                            return;
                        }
                        Drawable drawable1 = ContextCompat.getDrawable(context1, this.mInt1);
                        if(drawable1.getIntrinsicWidth() <= 0 || drawable1.getIntrinsicHeight() <= 0) {
                            int v1 = ((ActivityManager)context1.getSystemService("activity")).getLauncherLargeIconSize();
                            bitmap0 = Bitmap.createBitmap(v1, v1, Bitmap.Config.ARGB_8888);
                        }
                        else {
                            bitmap0 = Bitmap.createBitmap(drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                        }
                        drawable1.setBounds(0, 0, bitmap0.getWidth(), bitmap0.getHeight());
                        drawable1.draw(new Canvas(bitmap0));
                        break;
                    }
                    catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                        throw new IllegalArgumentException("Can\'t find package " + this.mObj1, packageManager$NameNotFoundException0);
                    }
                }
                default: {
                    throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
                }
            }
        }
        if(drawable0 != null) {
            int v2 = bitmap0.getWidth();
            int v3 = bitmap0.getHeight();
            drawable0.setBounds(v2 / 2, v3 / 2, v2, v3);
            drawable0.draw(new Canvas(bitmap0));
        }
        intent0.putExtra("android.intent.extra.shortcut.ICON", bitmap0);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void checkResource(Context context0) {
        if(this.mType == 2) {
            String s = (String)this.mObj1;
            if(!s.contains(":")) {
                return;
            }
            String s1 = s.split(":", -1)[1];
            String s2 = s1.split("/", -1)[0];
            String s3 = s1.split("/", -1)[1];
            String s4 = s.split(":", -1)[0];
            int v = IconCompat.getResources(context0, s4).getIdentifier(s3, s2, s4);
            if(this.mInt1 != v) {
                Log.i("IconCompat", "Id has changed for " + s4 + "/" + s3);
                this.mInt1 = v;
            }
        }
    }

    @Nullable
    public static IconCompat createFromBundle(@NonNull Bundle bundle0) {
        int v = bundle0.getInt("type");
        IconCompat iconCompat0 = new IconCompat(v);
        iconCompat0.mInt1 = bundle0.getInt("int1");
        iconCompat0.mInt2 = bundle0.getInt("int2");
        if(bundle0.containsKey("tint_list")) {
            iconCompat0.mTintList = (ColorStateList)bundle0.getParcelable("tint_list");
        }
        if(bundle0.containsKey("tint_mode")) {
            iconCompat0.mTintMode = PorterDuff.Mode.valueOf(bundle0.getString("tint_mode"));
        }
        if(v != -1) {
            switch(v) {
                case 3: {
                    iconCompat0.mObj1 = bundle0.getByteArray("obj");
                    return iconCompat0;
                }
                case 2: 
                case 4: {
                    iconCompat0.mObj1 = bundle0.getString("obj");
                    return iconCompat0;
                }
                case 1: 
                case 5: {
                    break;
                }
                default: {
                    Log.w("IconCompat", "Unknown type " + v);
                    return null;
                }
            }
        }
        iconCompat0.mObj1 = bundle0.getParcelable("obj");
        return iconCompat0;
    }

    @Nullable
    @RequiresApi(23)
    public static IconCompat createFromIcon(@NonNull Context context0, @NonNull Icon icon0) {
        Preconditions.checkNotNull(icon0);
        switch(IconCompat.getType(icon0)) {
            case 2: {
                String s = IconCompat.getResPackage(icon0);
                try {
                    return IconCompat.createWithResource(IconCompat.getResources(context0, s), s, IconCompat.getResId(icon0));
                }
                catch(Resources.NotFoundException unused_ex) {
                    throw new IllegalArgumentException("Icon resource cannot be found");
                }
            }
            case 4: {
                return IconCompat.createWithContentUri(IconCompat.getUri(icon0));
            }
            default: {
                IconCompat iconCompat0 = new IconCompat(-1);
                iconCompat0.mObj1 = icon0;
                return iconCompat0;
            }
        }
    }

    @Nullable
    @RequiresApi(23)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static IconCompat createFromIcon(@NonNull Icon icon0) {
        Preconditions.checkNotNull(icon0);
        switch(IconCompat.getType(icon0)) {
            case 2: {
                return IconCompat.createWithResource(null, IconCompat.getResPackage(icon0), IconCompat.getResId(icon0));
            }
            case 4: {
                return IconCompat.createWithContentUri(IconCompat.getUri(icon0));
            }
            default: {
                IconCompat iconCompat0 = new IconCompat(-1);
                iconCompat0.mObj1 = icon0;
                return iconCompat0;
            }
        }
    }

    @VisibleForTesting
    static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap bitmap0, boolean z) {
        int v = (int)(((float)Math.min(bitmap0.getWidth(), bitmap0.getHeight())) * 0.666667f);
        Bitmap bitmap1 = Bitmap.createBitmap(v, v, Bitmap.Config.ARGB_8888);
        Canvas canvas0 = new Canvas(bitmap1);
        Paint paint0 = new Paint(3);
        float f = 0.5f * ((float)v);
        if(z) {
            paint0.setColor(0);
            paint0.setShadowLayer(0.010417f * ((float)v), 0.0f, ((float)v) * 0.020833f, 0x3D000000);
            canvas0.drawCircle(f, f, 0.916667f * f, paint0);
            paint0.setShadowLayer(0.010417f * ((float)v), 0.0f, 0.0f, 0x1E000000);
            canvas0.drawCircle(f, f, 0.916667f * f, paint0);
            paint0.clearShadowLayer();
        }
        paint0.setColor(0xFF000000);
        BitmapShader bitmapShader0 = new BitmapShader(bitmap0, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Matrix matrix0 = new Matrix();
        matrix0.setTranslate(((float)(-(bitmap0.getWidth() - v) / 2)), ((float)(-(bitmap0.getHeight() - v) / 2)));
        bitmapShader0.setLocalMatrix(matrix0);
        paint0.setShader(bitmapShader0);
        canvas0.drawCircle(f, f, 0.916667f * f, paint0);
        canvas0.setBitmap(null);
        return bitmap1;
    }

    public static IconCompat createWithAdaptiveBitmap(Bitmap bitmap0) {
        if(bitmap0 == null) {
            throw new IllegalArgumentException("Bitmap must not be null.");
        }
        IconCompat iconCompat0 = new IconCompat(5);
        iconCompat0.mObj1 = bitmap0;
        return iconCompat0;
    }

    public static IconCompat createWithBitmap(Bitmap bitmap0) {
        if(bitmap0 == null) {
            throw new IllegalArgumentException("Bitmap must not be null.");
        }
        IconCompat iconCompat0 = new IconCompat(1);
        iconCompat0.mObj1 = bitmap0;
        return iconCompat0;
    }

    public static IconCompat createWithContentUri(Uri uri0) {
        if(uri0 == null) {
            throw new IllegalArgumentException("Uri must not be null.");
        }
        return IconCompat.createWithContentUri(uri0.toString());
    }

    public static IconCompat createWithContentUri(String s) {
        if(s == null) {
            throw new IllegalArgumentException("Uri must not be null.");
        }
        IconCompat iconCompat0 = new IconCompat(4);
        iconCompat0.mObj1 = s;
        return iconCompat0;
    }

    public static IconCompat createWithData(byte[] arr_b, int v, int v1) {
        if(arr_b == null) {
            throw new IllegalArgumentException("Data must not be null.");
        }
        IconCompat iconCompat0 = new IconCompat(3);
        iconCompat0.mObj1 = arr_b;
        iconCompat0.mInt1 = v;
        iconCompat0.mInt2 = v1;
        return iconCompat0;
    }

    public static IconCompat createWithResource(Context context0, @DrawableRes int v) {
        if(context0 == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        return IconCompat.createWithResource(context0.getResources(), "com.esotericsoftware.gloomhavenhelper", v);
    }

    @RestrictTo({Scope.LIBRARY})
    public static IconCompat createWithResource(Resources resources0, String s, @DrawableRes int v) {
        if(s == null) {
            throw new IllegalArgumentException("Package must not be null.");
        }
        if(v == 0) {
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
        }
        IconCompat iconCompat0 = new IconCompat(2);
        iconCompat0.mInt1 = v;
        if(resources0 != null) {
            try {
                iconCompat0.mObj1 = resources0.getResourceName(v);
                return iconCompat0;
            }
            catch(Resources.NotFoundException unused_ex) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        }
        iconCompat0.mObj1 = s;
        return iconCompat0;
    }

    @DrawableRes
    @IdRes
    @RequiresApi(23)
    private static int getResId(@NonNull Icon icon0) {
        if(Build.VERSION.SDK_INT >= 28) {
            return icon0.getResId();
        }
        try {
            return (int)(((Integer)icon0.getClass().getMethod("getResId").invoke(icon0)));
        }
        catch(IllegalAccessException illegalAccessException0) {
            Log.e("IconCompat", "Unable to get icon resource", illegalAccessException0);
            return 0;
        }
        catch(InvocationTargetException invocationTargetException0) {
            Log.e("IconCompat", "Unable to get icon resource", invocationTargetException0);
            return 0;
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            Log.e("IconCompat", "Unable to get icon resource", noSuchMethodException0);
            return 0;
        }
    }

    @IdRes
    public int getResId() {
        if(this.mType == -1 && Build.VERSION.SDK_INT >= 23) {
            return IconCompat.getResId(((Icon)this.mObj1));
        }
        if(this.mType != 2) {
            throw new IllegalStateException("called getResId() on " + this);
        }
        return this.mInt1;
    }

    @Nullable
    @RequiresApi(23)
    private static String getResPackage(@NonNull Icon icon0) {
        if(Build.VERSION.SDK_INT >= 28) {
            return icon0.getResPackage();
        }
        try {
            return (String)icon0.getClass().getMethod("getResPackage").invoke(icon0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            Log.e("IconCompat", "Unable to get icon package", illegalAccessException0);
            return null;
        }
        catch(InvocationTargetException invocationTargetException0) {
            Log.e("IconCompat", "Unable to get icon package", invocationTargetException0);
            return null;
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            Log.e("IconCompat", "Unable to get icon package", noSuchMethodException0);
            return null;
        }
    }

    @NonNull
    public String getResPackage() {
        if(this.mType == -1 && Build.VERSION.SDK_INT >= 23) {
            return IconCompat.getResPackage(((Icon)this.mObj1));
        }
        if(this.mType != 2) {
            throw new IllegalStateException("called getResPackage() on " + this);
        }
        return ((String)this.mObj1).split(":", -1)[0];
    }

    private static Resources getResources(Context context0, String s) {
        if("android".equals(s)) {
            return Resources.getSystem();
        }
        PackageManager packageManager0 = context0.getPackageManager();
        try {
            ApplicationInfo applicationInfo0 = packageManager0.getApplicationInfo(s, 0x2000);
            return applicationInfo0 == null ? null : packageManager0.getResourcesForApplication(applicationInfo0);
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            Log.e("IconCompat", String.format("Unable to find pkg=%s for icon", s), packageManager$NameNotFoundException0);
            return null;
        }
    }

    @RequiresApi(23)
    private static int getType(@NonNull Icon icon0) {
        if(Build.VERSION.SDK_INT >= 28) {
            return icon0.getType();
        }
        try {
            return (int)(((Integer)icon0.getClass().getMethod("getType").invoke(icon0)));
        }
        catch(IllegalAccessException illegalAccessException0) {
            Log.e("IconCompat", "Unable to get icon type " + icon0, illegalAccessException0);
            return -1;
        }
        catch(InvocationTargetException invocationTargetException0) {
            Log.e("IconCompat", "Unable to get icon type " + icon0, invocationTargetException0);
            return -1;
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            Log.e("IconCompat", "Unable to get icon type " + icon0, noSuchMethodException0);
            return -1;
        }
    }

    public int getType() {
        return this.mType != -1 || Build.VERSION.SDK_INT < 23 ? this.mType : IconCompat.getType(((Icon)this.mObj1));
    }

    @Nullable
    @RequiresApi(23)
    private static Uri getUri(@NonNull Icon icon0) {
        if(Build.VERSION.SDK_INT >= 28) {
            return icon0.getUri();
        }
        try {
            return (Uri)icon0.getClass().getMethod("getUri").invoke(icon0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            Log.e("IconCompat", "Unable to get icon uri", illegalAccessException0);
            return null;
        }
        catch(InvocationTargetException invocationTargetException0) {
            Log.e("IconCompat", "Unable to get icon uri", invocationTargetException0);
            return null;
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            Log.e("IconCompat", "Unable to get icon uri", noSuchMethodException0);
            return null;
        }
    }

    @NonNull
    public Uri getUri() {
        return this.mType != -1 || Build.VERSION.SDK_INT < 23 ? Uri.parse(((String)this.mObj1)) : IconCompat.getUri(((Icon)this.mObj1));
    }

    public Drawable loadDrawable(Context context0) {
        this.checkResource(context0);
        if(Build.VERSION.SDK_INT >= 23) {
            return this.toIcon().loadDrawable(context0);
        }
        Drawable drawable0 = this.loadDrawableInner(context0);
        if(drawable0 != null && (this.mTintList != null || this.mTintMode != IconCompat.DEFAULT_TINT_MODE)) {
            drawable0.mutate();
            DrawableCompat.setTintList(drawable0, this.mTintList);
            DrawableCompat.setTintMode(drawable0, this.mTintMode);
        }
        return drawable0;
    }

    private Drawable loadDrawableInner(Context context0) {
        InputStream inputStream0;
        switch(this.mType) {
            case 1: {
                return new BitmapDrawable(context0.getResources(), ((Bitmap)this.mObj1));
            }
            case 2: {
                String s = this.getResPackage();
                if(TextUtils.isEmpty(s)) {
                    s = "com.esotericsoftware.gloomhavenhelper";
                }
                Resources resources0 = IconCompat.getResources(context0, s);
                try {
                    return ResourcesCompat.getDrawable(resources0, this.mInt1, context0.getTheme());
                }
                catch(RuntimeException runtimeException0) {
                    Log.e("IconCompat", String.format("Unable to load resource 0x%08x from pkg=%s", this.mInt1, this.mObj1), runtimeException0);
                    return null;
                }
            }
            case 3: {
                return new BitmapDrawable(context0.getResources(), BitmapFactory.decodeByteArray(((byte[])this.mObj1), this.mInt1, this.mInt2));
            }
            case 4: {
                Uri uri0 = Uri.parse(((String)this.mObj1));
                String s1 = uri0.getScheme();
                if("content".equals(s1) || "file".equals(s1)) {
                    try {
                        inputStream0 = context0.getContentResolver().openInputStream(uri0);
                        return inputStream0 != null ? new BitmapDrawable(context0.getResources(), BitmapFactory.decodeStream(inputStream0)) : null;
                    }
                    catch(Exception exception0) {
                        Log.w("IconCompat", "Unable to load image from URI: " + uri0, exception0);
                    }
                    inputStream0 = null;
                }
                else {
                    try {
                        inputStream0 = new FileInputStream(new File(((String)this.mObj1)));
                    }
                    catch(FileNotFoundException fileNotFoundException0) {
                        Log.w("IconCompat", "Unable to load image from path: " + uri0, fileNotFoundException0);
                        inputStream0 = null;
                        return inputStream0 != null ? new BitmapDrawable(context0.getResources(), BitmapFactory.decodeStream(inputStream0)) : null;
                    }
                }
                return inputStream0 != null ? new BitmapDrawable(context0.getResources(), BitmapFactory.decodeStream(inputStream0)) : null;
            }
            case 5: {
                return new BitmapDrawable(context0.getResources(), IconCompat.createLegacyIconFromAdaptiveIcon(((Bitmap)this.mObj1), false));
            }
            default: {
                return null;
            }
        }
    }

    @Override  // androidx.versionedparcelable.CustomVersionedParcelable
    public void onPostParceling() {
        this.mTintMode = PorterDuff.Mode.valueOf(this.mTintModeStr);
        int v = this.mType;
        if(v != -1) {
            switch(v) {
                case 3: {
                    this.mObj1 = this.mData;
                    return;
                }
                case 2: 
                case 4: {
                    this.mObj1 = new String(this.mData, Charset.forName("UTF-16"));
                    return;
                }
                case 1: 
                case 5: {
                    Parcelable parcelable0 = this.mParcelable;
                    if(parcelable0 != null) {
                        this.mObj1 = parcelable0;
                        return;
                    }
                    this.mObj1 = this.mData;
                    this.mType = 3;
                    this.mInt1 = 0;
                    this.mInt2 = this.mData.length;
                    return;
                }
                default: {
                    return;
                }
            }
        }
        Parcelable parcelable1 = this.mParcelable;
        if(parcelable1 == null) {
            throw new IllegalArgumentException("Invalid icon");
        }
        this.mObj1 = parcelable1;
    }

    @Override  // androidx.versionedparcelable.CustomVersionedParcelable
    public void onPreParceling(boolean z) {
        this.mTintModeStr = this.mTintMode.name();
        int v = this.mType;
        if(v != -1) {
            switch(v) {
                case 2: {
                    this.mData = ((String)this.mObj1).getBytes(Charset.forName("UTF-16"));
                    return;
                }
                case 3: {
                    this.mData = (byte[])this.mObj1;
                    return;
                }
                case 4: {
                    this.mData = this.mObj1.toString().getBytes(Charset.forName("UTF-16"));
                    return;
                }
                case 1: 
                case 5: {
                    if(z) {
                        Bitmap bitmap0 = (Bitmap)this.mObj1;
                        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
                        bitmap0.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream0);
                        this.mData = byteArrayOutputStream0.toByteArray();
                        return;
                    }
                    this.mParcelable = (Parcelable)this.mObj1;
                    return;
                }
                default: {
                    return;
                }
            }
        }
        if(z) {
            throw new IllegalArgumentException("Can\'t serialize Icon created with IconCompat#createFromIcon");
        }
        this.mParcelable = (Parcelable)this.mObj1;
    }

    public IconCompat setTint(@ColorInt int v) {
        return this.setTintList(ColorStateList.valueOf(v));
    }

    public IconCompat setTintList(ColorStateList colorStateList0) {
        this.mTintList = colorStateList0;
        return this;
    }

    public IconCompat setTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.mTintMode = porterDuff$Mode0;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle0 = new Bundle();
        int v = this.mType;
        if(v == -1) {
            bundle0.putParcelable("obj", ((Parcelable)this.mObj1));
        }
        else {
            switch(v) {
                case 3: {
                    bundle0.putByteArray("obj", ((byte[])this.mObj1));
                    break;
                }
                case 2: 
                case 4: {
                    bundle0.putString("obj", ((String)this.mObj1));
                    break;
                }
                case 1: 
                case 5: {
                    bundle0.putParcelable("obj", ((Bitmap)this.mObj1));
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Invalid icon");
                }
            }
        }
        bundle0.putInt("type", this.mType);
        bundle0.putInt("int1", this.mInt1);
        bundle0.putInt("int2", this.mInt2);
        ColorStateList colorStateList0 = this.mTintList;
        if(colorStateList0 != null) {
            bundle0.putParcelable("tint_list", colorStateList0);
        }
        PorterDuff.Mode porterDuff$Mode0 = this.mTintMode;
        if(porterDuff$Mode0 != IconCompat.DEFAULT_TINT_MODE) {
            bundle0.putString("tint_mode", porterDuff$Mode0.name());
        }
        return bundle0;
    }

    @RequiresApi(23)
    public Icon toIcon() {
        Icon icon0;
        int v = this.mType;
        if(v != -1) {
            switch(v) {
                case 1: {
                    icon0 = Icon.createWithBitmap(((Bitmap)this.mObj1));
                    break;
                }
                case 2: {
                    icon0 = Icon.createWithResource(this.getResPackage(), this.mInt1);
                    break;
                }
                case 3: {
                    icon0 = Icon.createWithData(((byte[])this.mObj1), this.mInt1, this.mInt2);
                    break;
                }
                case 4: {
                    icon0 = Icon.createWithContentUri(((String)this.mObj1));
                    break;
                }
                case 5: {
                    icon0 = Build.VERSION.SDK_INT < 26 ? Icon.createWithBitmap(IconCompat.createLegacyIconFromAdaptiveIcon(((Bitmap)this.mObj1), false)) : Icon.createWithAdaptiveBitmap(((Bitmap)this.mObj1));
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unknown type");
                }
            }
            ColorStateList colorStateList0 = this.mTintList;
            if(colorStateList0 != null) {
                icon0.setTintList(colorStateList0);
            }
            PorterDuff.Mode porterDuff$Mode0 = this.mTintMode;
            if(porterDuff$Mode0 != IconCompat.DEFAULT_TINT_MODE) {
                icon0.setTintMode(porterDuff$Mode0);
            }
            return icon0;
        }
        return (Icon)this.mObj1;
    }

    @Override
    public String toString() {
        if(this.mType == -1) {
            return String.valueOf(this.mObj1);
        }
        StringBuilder stringBuilder0 = new StringBuilder("Icon(typ=");
        stringBuilder0.append(IconCompat.typeToString(this.mType));
        switch(this.mType) {
            case 2: {
                stringBuilder0.append(" pkg=");
                stringBuilder0.append(this.getResPackage());
                stringBuilder0.append(" id=");
                stringBuilder0.append(String.format("0x%08x", this.getResId()));
                break;
            }
            case 3: {
                stringBuilder0.append(" len=");
                stringBuilder0.append(this.mInt1);
                if(this.mInt2 != 0) {
                    stringBuilder0.append(" off=");
                    stringBuilder0.append(this.mInt2);
                }
                break;
            }
            case 4: {
                stringBuilder0.append(" uri=");
                stringBuilder0.append(this.mObj1);
                break;
            }
            case 1: 
            case 5: {
                stringBuilder0.append(" size=");
                stringBuilder0.append(((Bitmap)this.mObj1).getWidth());
                stringBuilder0.append("x");
                stringBuilder0.append(((Bitmap)this.mObj1).getHeight());
            }
        }
        if(this.mTintList != null) {
            stringBuilder0.append(" tint=");
            stringBuilder0.append(this.mTintList);
        }
        if(this.mTintMode != IconCompat.DEFAULT_TINT_MODE) {
            stringBuilder0.append(" mode=");
            stringBuilder0.append(this.mTintMode);
        }
        stringBuilder0.append(")");
        return stringBuilder0.toString();
    }

    private static String typeToString(int v) {
        switch(v) {
            case 1: {
                return "BITMAP";
            }
            case 2: {
                return "RESOURCE";
            }
            case 3: {
                return "DATA";
            }
            case 4: {
                return "URI";
            }
            case 5: {
                return "BITMAP_MASKABLE";
            }
            default: {
                return "UNKNOWN";
            }
        }
    }
}

