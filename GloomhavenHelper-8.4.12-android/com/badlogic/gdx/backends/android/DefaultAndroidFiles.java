package com.badlogic.gdx.backends.android;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.File;
import java.io.IOException;

public class DefaultAndroidFiles implements AndroidFiles {
    protected final AssetManager assets;
    private ZipResourceFile expansionFile;
    protected final String externalFilesPath;
    protected final String localpath;

    public DefaultAndroidFiles(AssetManager assetManager0, ContextWrapper contextWrapper0, boolean z) {
        this.expansionFile = null;
        this.assets = assetManager0;
        String s = contextWrapper0.getFilesDir().getAbsolutePath();
        if(!s.endsWith("/")) {
            s = s + "/";
        }
        this.localpath = s;
        if(z) {
            this.externalFilesPath = this.initExternalFilesPath(contextWrapper0);
            return;
        }
        this.externalFilesPath = null;
    }

    @Override  // com.badlogic.gdx.Files
    public FileHandle absolute(String s) {
        return new AndroidFileHandle(null, s, FileType.Absolute);
    }

    @Override  // com.badlogic.gdx.Files
    public FileHandle classpath(String s) {
        return new AndroidFileHandle(null, s, FileType.Classpath);
    }

    @Override  // com.badlogic.gdx.Files
    public FileHandle external(String s) {
        return new AndroidFileHandle(null, s, FileType.External);
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidFiles
    public ZipResourceFile getExpansionFile() {
        return this.expansionFile;
    }

    @Override  // com.badlogic.gdx.Files
    public String getExternalStoragePath() {
        return this.externalFilesPath;
    }

    @Override  // com.badlogic.gdx.Files
    public FileHandle getFileHandle(String s, FileType files$FileType0) {
        FileHandle fileHandle0 = new AndroidFileHandle((files$FileType0 == FileType.Internal ? this.assets : null), s, files$FileType0);
        return this.expansionFile == null || files$FileType0 != FileType.Internal ? fileHandle0 : this.getZipFileHandleIfExists(fileHandle0, s);
    }

    @Override  // com.badlogic.gdx.Files
    public String getLocalStoragePath() {
        return this.localpath;
    }

    private FileHandle getZipFileHandleIfExists(FileHandle fileHandle0, String s) {
        try {
            this.assets.open(s).close();
            return fileHandle0;
        }
        catch(Exception unused_ex) {
            FileHandle fileHandle1 = new AndroidZipFileHandle(s);
            if(!fileHandle1.isDirectory()) {
                return fileHandle1;
            }
            return fileHandle1.exists() ? fileHandle1 : fileHandle0;
        }
    }

    protected String initExternalFilesPath(ContextWrapper contextWrapper0) {
        File file0 = contextWrapper0.getExternalFilesDir(null);
        if(file0 != null) {
            String s = file0.getAbsolutePath();
            return s.endsWith("/") ? s : s + "/";
        }
        return null;
    }

    @Override  // com.badlogic.gdx.Files
    public FileHandle internal(String s) {
        FileHandle fileHandle0 = new AndroidFileHandle(this.assets, s, FileType.Internal);
        return this.expansionFile == null ? fileHandle0 : this.getZipFileHandleIfExists(fileHandle0, s);
    }

    @Override  // com.badlogic.gdx.Files
    public boolean isExternalStorageAvailable() {
        return this.externalFilesPath != null;
    }

    @Override  // com.badlogic.gdx.Files
    public boolean isLocalStorageAvailable() {
        return true;
    }

    @Override  // com.badlogic.gdx.Files
    public FileHandle local(String s) {
        return new AndroidFileHandle(null, s, FileType.Local);
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidFiles
    public boolean setAPKExpansion(int v, int v1) {
        Context context0;
        try {
            if(Gdx.app instanceof Activity) {
                context0 = ((Activity)Gdx.app).getBaseContext();
            }
            else {
                if(!(Gdx.app instanceof Fragment)) {
                    throw new GdxRuntimeException("APK expansion not supported for application type");
                }
                context0 = ((Fragment)Gdx.app).getActivity().getBaseContext();
            }
            this.expansionFile = APKExpansionSupport.getAPKExpansionZipFile(context0, v, v1);
            return this.expansionFile != null;
        }
        catch(IOException unused_ex) {
            throw new GdxRuntimeException("APK expansion main version " + v + " or patch version " + v1 + " couldn\'t be opened!");
        }
    }
}

