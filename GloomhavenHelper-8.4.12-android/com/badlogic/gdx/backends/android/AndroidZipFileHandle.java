package com.badlogic.gdx.backends.android;

import android.content.res.AssetFileDescriptor;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

public class AndroidZipFileHandle extends AndroidFileHandle {
    private ZipResourceFile expansionFile;
    private long fdLength;
    private boolean hasAssetFd;
    private String path;

    public AndroidZipFileHandle(File file0, FileType files$FileType0) {
        super(null, file0, files$FileType0);
        this.initialize();
    }

    public AndroidZipFileHandle(String s) {
        super(null, s, FileType.Internal);
        this.initialize();
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidFileHandle
    public FileHandle child(String s) {
        return this.file.getPath().length() == 0 ? new AndroidZipFileHandle(new File(s), this.type) : new AndroidZipFileHandle(new File(this.file, s), this.type);
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidFileHandle
    public boolean exists() {
        return this.hasAssetFd || this.expansionFile.getEntriesAt(this.getPath()).length != 0;
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidFileHandle
    public AssetFileDescriptor getAssetFileDescriptor() throws IOException {
        return this.expansionFile.getAssetFileDescriptor(this.getPath());
    }

    private String getPath() {
        return this.path;
    }

    private void initialize() {
        this.path = this.file.getPath().replace('\\', '/');
        this.expansionFile = ((AndroidFiles)Gdx.files).getExpansionFile();
        AssetFileDescriptor assetFileDescriptor0 = this.expansionFile.getAssetFileDescriptor(this.getPath());
        if(assetFileDescriptor0 == null) {
            this.hasAssetFd = false;
        }
        else {
            this.hasAssetFd = true;
            this.fdLength = assetFileDescriptor0.getLength();
            try {
                assetFileDescriptor0.close();
            }
            catch(IOException unused_ex) {
            }
        }
        if(this.isDirectory()) {
            this.path = this.path + "/";
        }
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidFileHandle
    public boolean isDirectory() {
        return !this.hasAssetFd;
    }

    // 去混淆评级： 低(20)
    @Override  // com.badlogic.gdx.backends.android.AndroidFileHandle
    public long length() {
        return this.hasAssetFd ? this.fdLength : 0L;
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidFileHandle
    public FileHandle[] list() {
        ZipEntryRO[] arr_zipResourceFile$ZipEntryRO = this.expansionFile.getEntriesAt(this.getPath());
        FileHandle[] arr_fileHandle = new FileHandle[arr_zipResourceFile$ZipEntryRO.length - 1];
        int v1 = 0;
        for(int v = 0; v < arr_zipResourceFile$ZipEntryRO.length; ++v) {
            if(arr_zipResourceFile$ZipEntryRO[v].mFileName.length() != this.getPath().length()) {
                arr_fileHandle[v1] = new AndroidZipFileHandle(arr_zipResourceFile$ZipEntryRO[v].mFileName);
                ++v1;
            }
        }
        return arr_fileHandle;
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidFileHandle
    public FileHandle[] list(FileFilter fileFilter0) {
        ZipEntryRO[] arr_zipResourceFile$ZipEntryRO = this.expansionFile.getEntriesAt(this.getPath());
        FileHandle[] arr_fileHandle = new FileHandle[arr_zipResourceFile$ZipEntryRO.length - 1];
        int v1 = 0;
        for(int v = 0; v < arr_zipResourceFile$ZipEntryRO.length; ++v) {
            if(arr_zipResourceFile$ZipEntryRO[v].mFileName.length() != this.getPath().length()) {
                AndroidZipFileHandle androidZipFileHandle0 = new AndroidZipFileHandle(arr_zipResourceFile$ZipEntryRO[v].mFileName);
                if(fileFilter0.accept(androidZipFileHandle0.file())) {
                    arr_fileHandle[v1] = androidZipFileHandle0;
                    ++v1;
                }
            }
        }
        if(v1 < arr_fileHandle.length) {
            FileHandle[] arr_fileHandle1 = new FileHandle[v1];
            System.arraycopy(arr_fileHandle, 0, arr_fileHandle1, 0, v1);
            return arr_fileHandle1;
        }
        return arr_fileHandle;
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidFileHandle
    public FileHandle[] list(FilenameFilter filenameFilter0) {
        ZipEntryRO[] arr_zipResourceFile$ZipEntryRO = this.expansionFile.getEntriesAt(this.getPath());
        FileHandle[] arr_fileHandle = new FileHandle[arr_zipResourceFile$ZipEntryRO.length - 1];
        int v1 = 0;
        for(int v = 0; v < arr_zipResourceFile$ZipEntryRO.length; ++v) {
            if(arr_zipResourceFile$ZipEntryRO[v].mFileName.length() != this.getPath().length()) {
                String s = arr_zipResourceFile$ZipEntryRO[v].mFileName;
                if(filenameFilter0.accept(this.file, s)) {
                    arr_fileHandle[v1] = new AndroidZipFileHandle(s);
                    ++v1;
                }
            }
        }
        if(v1 < arr_fileHandle.length) {
            FileHandle[] arr_fileHandle1 = new FileHandle[v1];
            System.arraycopy(arr_fileHandle, 0, arr_fileHandle1, 0, v1);
            return arr_fileHandle1;
        }
        return arr_fileHandle;
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidFileHandle
    public FileHandle[] list(String s) {
        ZipEntryRO[] arr_zipResourceFile$ZipEntryRO = this.expansionFile.getEntriesAt(this.getPath());
        FileHandle[] arr_fileHandle = new FileHandle[arr_zipResourceFile$ZipEntryRO.length - 1];
        int v1 = 0;
        for(int v = 0; v < arr_zipResourceFile$ZipEntryRO.length; ++v) {
            if(arr_zipResourceFile$ZipEntryRO[v].mFileName.length() != this.getPath().length()) {
                String s1 = arr_zipResourceFile$ZipEntryRO[v].mFileName;
                if(s1.endsWith(s)) {
                    arr_fileHandle[v1] = new AndroidZipFileHandle(s1);
                    ++v1;
                }
            }
        }
        if(v1 < arr_fileHandle.length) {
            FileHandle[] arr_fileHandle1 = new FileHandle[v1];
            System.arraycopy(arr_fileHandle, 0, arr_fileHandle1, 0, v1);
            return arr_fileHandle1;
        }
        return arr_fileHandle;
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidFileHandle
    public FileHandle parent() {
        File file0 = this.file.getParentFile();
        if(file0 == null) {
            file0 = new File("");
        }
        return new AndroidZipFileHandle(file0.getPath());
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidFileHandle
    public InputStream read() {
        try {
            return this.expansionFile.getInputStream(this.getPath());
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException("Error reading file: " + this.file + " (ZipResourceFile)", iOException0);
        }
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidFileHandle
    public FileHandle sibling(String s) {
        if(this.file.getPath().length() == 0) {
            throw new GdxRuntimeException("Cannot get the sibling of the root.");
        }
        return Gdx.files.getFileHandle(new File(this.file.getParent(), s).getPath(), this.type);
    }
}

