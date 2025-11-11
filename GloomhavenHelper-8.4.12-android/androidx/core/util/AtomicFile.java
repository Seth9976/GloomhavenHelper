package androidx.core.util;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AtomicFile {
    private final File mBackupName;
    private final File mBaseName;

    public AtomicFile(@NonNull File file0) {
        this.mBaseName = file0;
        this.mBackupName = new File(file0.getPath() + ".bak");
    }

    public void delete() {
        this.mBaseName.delete();
        this.mBackupName.delete();
    }

    public void failWrite(@Nullable FileOutputStream fileOutputStream0) {
        if(fileOutputStream0 != null) {
            AtomicFile.sync(fileOutputStream0);
            try {
                fileOutputStream0.close();
                this.mBaseName.delete();
                this.mBackupName.renameTo(this.mBaseName);
            }
            catch(IOException iOException0) {
                Log.w("AtomicFile", "failWrite: Got exception:", iOException0);
            }
        }
    }

    public void finishWrite(@Nullable FileOutputStream fileOutputStream0) {
        if(fileOutputStream0 != null) {
            AtomicFile.sync(fileOutputStream0);
            try {
                fileOutputStream0.close();
                this.mBackupName.delete();
            }
            catch(IOException iOException0) {
                Log.w("AtomicFile", "finishWrite: Got exception:", iOException0);
            }
        }
    }

    @NonNull
    public File getBaseFile() {
        return this.mBaseName;
    }

    @NonNull
    public FileInputStream openRead() throws FileNotFoundException {
        if(this.mBackupName.exists()) {
            this.mBaseName.delete();
            this.mBackupName.renameTo(this.mBaseName);
        }
        return new FileInputStream(this.mBaseName);
    }

    @NonNull
    public byte[] readFully() throws IOException {
        try(FileInputStream fileInputStream0 = this.openRead()) {
            byte[] arr_b = new byte[fileInputStream0.available()];
            int v = 0;
            int v1;
            while((v1 = fileInputStream0.read(arr_b, v, arr_b.length - v)) > 0) {
                v += v1;
                int v2 = fileInputStream0.available();
                if(v2 <= arr_b.length - v) {
                    continue;
                }
                byte[] arr_b1 = new byte[v2 + v];
                System.arraycopy(arr_b, 0, arr_b1, 0, v);
                arr_b = arr_b1;
            }
            return arr_b;
        }
    }

    @NonNull
    public FileOutputStream startWrite() throws IOException {
        if(this.mBaseName.exists()) {
            if(this.mBackupName.exists()) {
                this.mBaseName.delete();
            }
            else if(!this.mBaseName.renameTo(this.mBackupName)) {
                Log.w("AtomicFile", "Couldn\'t rename file " + this.mBaseName + " to backup file " + this.mBackupName);
            }
        }
        try {
            return new FileOutputStream(this.mBaseName);
        }
        catch(FileNotFoundException unused_ex) {
            if(this.mBaseName.getParentFile().mkdirs()) {
                try {
                    return new FileOutputStream(this.mBaseName);
                }
                catch(FileNotFoundException unused_ex) {
                    throw new IOException("Couldn\'t create " + this.mBaseName);
                }
            }
            throw new IOException("Couldn\'t create directory " + this.mBaseName);
        }
    }

    private static boolean sync(@NonNull FileOutputStream fileOutputStream0) {
        try {
            fileOutputStream0.getFD().sync();
            return true;
        }
        catch(IOException unused_ex) {
            return false;
        }
    }
}

