package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.core.provider.FontsContractCompat.FontInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RequiresApi(21)
@RestrictTo({Scope.LIBRARY_GROUP})
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    @Override  // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontInfo(Context context0, CancellationSignal cancellationSignal0, @NonNull FontInfo[] arr_fontsContractCompat$FontInfo, int v) {
        if(arr_fontsContractCompat$FontInfo.length < 1) {
            return null;
        }
        FontInfo fontsContractCompat$FontInfo0 = this.findBestInfo(arr_fontsContractCompat$FontInfo, v);
        ContentResolver contentResolver0 = context0.getContentResolver();
        try(ParcelFileDescriptor parcelFileDescriptor0 = contentResolver0.openFileDescriptor(fontsContractCompat$FontInfo0.getUri(), "r", cancellationSignal0)) {
            File file0 = this.getFile(parcelFileDescriptor0);
            if(file0 != null && file0.canRead()) {
                return Typeface.createFromFile(file0);
            }
            try(FileInputStream fileInputStream0 = new FileInputStream(parcelFileDescriptor0.getFileDescriptor())) {
                return super.createFromInputStream(context0, fileInputStream0);
            }
        }
        catch(IOException unused_ex) {
            return null;
        }
    }

    private File getFile(ParcelFileDescriptor parcelFileDescriptor0) {
        try {
            String s = Os.readlink(("/proc/self/fd/" + parcelFileDescriptor0.getFd()));
            return OsConstants.S_ISREG(Os.stat(s).st_mode) ? new File(s) : null;
        }
        catch(ErrnoException unused_ex) {
            return null;
        }
    }
}

