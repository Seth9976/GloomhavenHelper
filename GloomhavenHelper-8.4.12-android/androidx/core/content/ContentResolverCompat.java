package androidx.core.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.OperationCanceledException;
import androidx.core.os.CancellationSignal;

public final class ContentResolverCompat {
    public static Cursor query(ContentResolver contentResolver0, Uri uri0, String[] arr_s, String s, String[] arr_s1, String s1, CancellationSignal cancellationSignal0) {
        if(Build.VERSION.SDK_INT >= 16) {
            try {
                return contentResolver0.query(uri0, arr_s, s, arr_s1, s1, ((android.os.CancellationSignal)(cancellationSignal0 == null ? null : cancellationSignal0.getCancellationSignalObject())));
            label_3:
                if(!(exception0 instanceof OperationCanceledException)) {
                    throw exception0;
                }
                throw new androidx.core.os.OperationCanceledException();
            }
            catch(Exception exception0) {
                goto label_3;
            }
            throw new androidx.core.os.OperationCanceledException();
        }
        if(cancellationSignal0 != null) {
            cancellationSignal0.throwIfCanceled();
        }
        return contentResolver0.query(uri0, arr_s, s, arr_s1, s1);
    }
}

