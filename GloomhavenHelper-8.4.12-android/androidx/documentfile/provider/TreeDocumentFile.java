package androidx.documentfile.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;

@RequiresApi(21)
class TreeDocumentFile extends DocumentFile {
    private Context mContext;
    private Uri mUri;

    TreeDocumentFile(@Nullable DocumentFile documentFile0, Context context0, Uri uri0) {
        super(documentFile0);
        this.mContext = context0;
        this.mUri = uri0;
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean canRead() {
        return DocumentsContractApi19.canRead(this.mContext, this.mUri);
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean canWrite() {
        return DocumentsContractApi19.canWrite(this.mContext, this.mUri);
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

    @Override  // androidx.documentfile.provider.DocumentFile
    @Nullable
    public DocumentFile createDirectory(String s) {
        Uri uri0 = TreeDocumentFile.createFile(this.mContext, this.mUri, "vnd.android.document/directory", s);
        return uri0 != null ? new TreeDocumentFile(this, this.mContext, uri0) : null;
    }

    @Nullable
    private static Uri createFile(Context context0, Uri uri0, String s, String s1) {
        try {
            return DocumentsContract.createDocument(context0.getContentResolver(), uri0, s, s1);
        }
        catch(Exception unused_ex) {
            return null;
        }
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    @Nullable
    public DocumentFile createFile(String s, String s1) {
        Uri uri0 = TreeDocumentFile.createFile(this.mContext, this.mUri, s, s1);
        return uri0 != null ? new TreeDocumentFile(this, this.mContext, uri0) : null;
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean delete() {
        try {
            return DocumentsContract.deleteDocument(this.mContext.getContentResolver(), this.mUri);
        }
        catch(Exception unused_ex) {
            return false;
        }
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean exists() {
        return DocumentsContractApi19.exists(this.mContext, this.mUri);
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    @Nullable
    public String getName() {
        return DocumentsContractApi19.getName(this.mContext, this.mUri);
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    @Nullable
    public String getType() {
        return DocumentsContractApi19.getType(this.mContext, this.mUri);
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public Uri getUri() {
        return this.mUri;
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean isDirectory() {
        return DocumentsContractApi19.isDirectory(this.mContext, this.mUri);
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean isFile() {
        return DocumentsContractApi19.isFile(this.mContext, this.mUri);
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean isVirtual() {
        return DocumentsContractApi19.isVirtual(this.mContext, this.mUri);
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public long lastModified() {
        return DocumentsContractApi19.lastModified(this.mContext, this.mUri);
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public long length() {
        return DocumentsContractApi19.length(this.mContext, this.mUri);
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public DocumentFile[] listFiles() {
        ContentResolver contentResolver0 = this.mContext.getContentResolver();
        Uri uri0 = DocumentsContract.buildChildDocumentsUriUsingTree(this.mUri, DocumentsContract.getDocumentId(this.mUri));
        ArrayList arrayList0 = new ArrayList();
        Cursor cursor0 = null;
        try {
            cursor0 = contentResolver0.query(uri0, new String[]{"document_id"}, null, null, null);
            while(cursor0.moveToNext()) {
                String s = cursor0.getString(0);
                arrayList0.add(DocumentsContract.buildDocumentUriUsingTree(this.mUri, s));
            }
        }
        catch(Exception exception0) {
            Log.w("DocumentFile", "Failed query: " + exception0);
        }
        finally {
            TreeDocumentFile.closeQuietly(cursor0);
        }
        Uri[] arr_uri = (Uri[])arrayList0.toArray(new Uri[arrayList0.size()]);
        DocumentFile[] arr_documentFile = new DocumentFile[arr_uri.length];
        for(int v = 0; v < arr_uri.length; ++v) {
            arr_documentFile[v] = new TreeDocumentFile(this, this.mContext, arr_uri[v]);
        }
        return arr_documentFile;
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean renameTo(String s) {
        try {
            Uri uri0 = DocumentsContract.renameDocument(this.mContext.getContentResolver(), this.mUri, s);
            if(uri0 != null) {
                this.mUri = uri0;
                return true;
            }
        }
        catch(Exception unused_ex) {
        }
        return false;
    }
}

