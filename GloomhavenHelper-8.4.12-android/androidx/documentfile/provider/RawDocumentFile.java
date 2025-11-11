package androidx.documentfile.provider;

import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;
import androidx.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class RawDocumentFile extends DocumentFile {
    private File mFile;

    RawDocumentFile(@Nullable DocumentFile documentFile0, File file0) {
        super(documentFile0);
        this.mFile = file0;
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean canRead() {
        return this.mFile.canRead();
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean canWrite() {
        return this.mFile.canWrite();
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    @Nullable
    public DocumentFile createDirectory(String s) {
        File file0 = new File(this.mFile, s);
        return !file0.isDirectory() && !file0.mkdir() ? null : new RawDocumentFile(this, file0);
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    @Nullable
    public DocumentFile createFile(String s, String s1) {
        String s2 = MimeTypeMap.getSingleton().getExtensionFromMimeType(s);
        if(s2 != null) {
            s1 = s1 + "." + s2;
        }
        File file0 = new File(this.mFile, s1);
        try {
            file0.createNewFile();
            return new RawDocumentFile(this, file0);
        }
        catch(IOException iOException0) {
            Log.w("DocumentFile", "Failed to createFile: " + iOException0);
            return null;
        }
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean delete() {
        RawDocumentFile.deleteContents(this.mFile);
        return this.mFile.delete();
    }

    private static boolean deleteContents(File file0) {
        File[] arr_file = file0.listFiles();
        if(arr_file != null) {
            boolean z = true;
            for(int v = 0; v < arr_file.length; ++v) {
                File file1 = arr_file[v];
                if(file1.isDirectory()) {
                    z &= RawDocumentFile.deleteContents(file1);
                }
                if(!file1.delete()) {
                    Log.w("DocumentFile", "Failed to delete " + file1);
                    z = false;
                }
            }
            return z;
        }
        return true;
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean exists() {
        return this.mFile.exists();
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public String getName() {
        return this.mFile.getName();
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.documentfile.provider.DocumentFile
    @Nullable
    public String getType() {
        return this.mFile.isDirectory() ? null : RawDocumentFile.getTypeForName(this.mFile.getName());
    }

    private static String getTypeForName(String s) {
        int v = s.lastIndexOf(46);
        if(v >= 0) {
            String s1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(s.substring(v + 1).toLowerCase());
            return s1 == null ? "application/octet-stream" : s1;
        }
        return "application/octet-stream";
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public Uri getUri() {
        return Uri.fromFile(this.mFile);
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean isDirectory() {
        return this.mFile.isDirectory();
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean isFile() {
        return this.mFile.isFile();
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean isVirtual() {
        return false;
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public long lastModified() {
        return this.mFile.lastModified();
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public long length() {
        return this.mFile.length();
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public DocumentFile[] listFiles() {
        ArrayList arrayList0 = new ArrayList();
        File[] arr_file = this.mFile.listFiles();
        if(arr_file != null) {
            for(int v = 0; v < arr_file.length; ++v) {
                arrayList0.add(new RawDocumentFile(this, arr_file[v]));
            }
        }
        return (DocumentFile[])arrayList0.toArray(new DocumentFile[arrayList0.size()]);
    }

    @Override  // androidx.documentfile.provider.DocumentFile
    public boolean renameTo(String s) {
        File file0 = new File(this.mFile.getParentFile(), s);
        if(this.mFile.renameTo(file0)) {
            this.mFile = file0;
            return true;
        }
        return false;
    }
}

