package androidx.documentfile.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@RequiresApi(19)
class DocumentsContractApi19 {
    private static final int FLAG_VIRTUAL_DOCUMENT = 0x200;
    private static final String TAG = "DocumentFile";

    public static boolean canRead(Context context0, Uri uri0) {
        return context0.checkCallingOrSelfUriPermission(uri0, 1) == 0 ? !TextUtils.isEmpty(DocumentsContractApi19.getRawType(context0, uri0)) : false;
    }

    public static boolean canWrite(Context context0, Uri uri0) {
        if(context0.checkCallingOrSelfUriPermission(uri0, 2) != 0) {
            return false;
        }
        String s = DocumentsContractApi19.getRawType(context0, uri0);
        int v = DocumentsContractApi19.queryForInt(context0, uri0, "flags", 0);
        if(TextUtils.isEmpty(s)) {
            return false;
        }
        if((v & 4) != 0) {
            return true;
        }
        return !"vnd.android.document/directory".equals(s) || (v & 8) == 0 ? !TextUtils.isEmpty(s) && (v & 2) != 0 : true;
    }

    private static void closeQuietly(@Nullable AutoCloseable autoCloseable0) {
        if(autoCloseable0 != null) {
            try {
                autoCloseable0.close();
                return;
            }
            catch(RuntimeException runtimeException0) {
            }
            catch(Exception unused_ex) {
                return;
            }
            throw runtimeException0;
        }
    }

    public static boolean exists(Context context0, Uri uri0) {
        ContentResolver contentResolver0 = context0.getContentResolver();
        boolean z = true;
        Cursor cursor0 = null;
        try {
            cursor0 = contentResolver0.query(uri0, new String[]{"document_id"}, null, null, null);
            if(cursor0.getCount() <= 0) {
                z = false;
            }
            return z;
        }
        catch(Exception exception0) {
            Log.w("DocumentFile", "Failed query: " + exception0);
            return false;
        }
        finally {
            DocumentsContractApi19.closeQuietly(cursor0);
        }
    }

    public static long getFlags(Context context0, Uri uri0) {
        return DocumentsContractApi19.queryForLong(context0, uri0, "flags", 0L);
    }

    @Nullable
    public static String getName(Context context0, Uri uri0) {
        return DocumentsContractApi19.queryForString(context0, uri0, "_display_name", null);
    }

    @Nullable
    private static String getRawType(Context context0, Uri uri0) {
        return DocumentsContractApi19.queryForString(context0, uri0, "mime_type", null);
    }

    @Nullable
    public static String getType(Context context0, Uri uri0) {
        String s = DocumentsContractApi19.getRawType(context0, uri0);
        return "vnd.android.document/directory".equals(s) ? null : s;
    }

    public static boolean isDirectory(Context context0, Uri uri0) {
        return "vnd.android.document/directory".equals(DocumentsContractApi19.getRawType(context0, uri0));
    }

    public static boolean isFile(Context context0, Uri uri0) {
        String s = DocumentsContractApi19.getRawType(context0, uri0);
        return !"vnd.android.document/directory".equals(s) && !TextUtils.isEmpty(s);
    }

    public static boolean isVirtual(Context context0, Uri uri0) {
        return DocumentsContract.isDocumentUri(context0, uri0) ? (DocumentsContractApi19.getFlags(context0, uri0) & 0x200L) != 0L : false;
    }

    public static long lastModified(Context context0, Uri uri0) {
        return DocumentsContractApi19.queryForLong(context0, uri0, "last_modified", 0L);
    }

    public static long length(Context context0, Uri uri0) {
        return DocumentsContractApi19.queryForLong(context0, uri0, "_size", 0L);
    }

    private static int queryForInt(Context context0, Uri uri0, String s, int v) {
        return (int)DocumentsContractApi19.queryForLong(context0, uri0, s, ((long)v));
    }

    private static long queryForLong(Context context0, Uri uri0, String s, long v) {
        ContentResolver contentResolver0 = context0.getContentResolver();
        Cursor cursor0 = null;
        try {
            cursor0 = contentResolver0.query(uri0, new String[]{s}, null, null, null);
            return cursor0.moveToFirst() && !cursor0.isNull(0) ? cursor0.getLong(0) : v;
        }
        catch(Exception exception0) {
            Log.w("DocumentFile", "Failed query: " + exception0);
            return v;
        }
        finally {
            DocumentsContractApi19.closeQuietly(cursor0);
        }
    }

    @Nullable
    private static String queryForString(Context context0, Uri uri0, String s, @Nullable String s1) {
        ContentResolver contentResolver0 = context0.getContentResolver();
        Cursor cursor0 = null;
        try {
            cursor0 = contentResolver0.query(uri0, new String[]{s}, null, null, null);
            return cursor0.moveToFirst() && !cursor0.isNull(0) ? cursor0.getString(0) : s1;
        }
        catch(Exception exception0) {
            Log.w("DocumentFile", "Failed query: " + exception0);
            return s1;
        }
        finally {
            DocumentsContractApi19.closeQuietly(cursor0);
        }
    }
}

