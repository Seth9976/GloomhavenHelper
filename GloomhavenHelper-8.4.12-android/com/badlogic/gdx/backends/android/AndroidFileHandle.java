package com.badlogic.gdx.backends.android;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel.MapMode;

public class AndroidFileHandle extends FileHandle {
    private final AssetManager assets;

    AndroidFileHandle(AssetManager assetManager0, File file0, FileType files$FileType0) {
        super(file0, files$FileType0);
        this.assets = assetManager0;
    }

    AndroidFileHandle(AssetManager assetManager0, String s, FileType files$FileType0) {
        super(s.replace('\\', '/'), files$FileType0);
        this.assets = assetManager0;
    }

    @Override  // com.badlogic.gdx.files.FileHandle
    public FileHandle child(String s) {
        String s1 = s.replace('\\', '/');
        if(this.file.getPath().length() == 0) {
            File file0 = new File(s1);
            return new AndroidFileHandle(this.assets, file0, this.type);
        }
        File file1 = new File(this.file, s1);
        return new AndroidFileHandle(this.assets, file1, this.type);
    }

    @Override  // com.badlogic.gdx.files.FileHandle
    public boolean exists() {
        if(this.type == FileType.Internal) {
            String s = this.file.getPath();
            try {
                this.assets.open(s).close();
            }
            catch(Exception unused_ex) {
                try {
                    if(this.assets.list(s).length <= 0) {
                        return false;
                    }
                }
                catch(Exception unused_ex) {
                    return false;
                }
            }
            return true;
        }
        return super.exists();
    }

    @Override  // com.badlogic.gdx.files.FileHandle
    public File file() {
        return this.type == FileType.Local ? new File(Gdx.files.getLocalStoragePath(), this.file.getPath()) : super.file();
    }

    public AssetFileDescriptor getAssetFileDescriptor() throws IOException {
        return this.assets == null ? null : this.assets.openFd(this.path());
    }

    @Override  // com.badlogic.gdx.files.FileHandle
    public boolean isDirectory() {
        if(this.type == FileType.Internal) {
            try {
                String s = this.file.getPath();
                if(this.assets.list(s).length > 0) {
                    return true;
                }
            }
            catch(IOException unused_ex) {
            }
            return false;
        }
        return super.isDirectory();
    }

    @Override  // com.badlogic.gdx.files.FileHandle
    public long lastModified() {
        return super.lastModified();
    }

    @Override  // com.badlogic.gdx.files.FileHandle
    public long length() {
        long v;
        AssetFileDescriptor assetFileDescriptor0;
        if(this.type == FileType.Internal) {
            try {
                assetFileDescriptor0 = null;
                String s = this.file.getPath();
                assetFileDescriptor0 = this.assets.openFd(s);
                v = assetFileDescriptor0.getLength();
            }
            catch(IOException unused_ex) {
                if(assetFileDescriptor0 != null) {
                    try {
                        assetFileDescriptor0.close();
                        return super.length();
                    }
                    catch(IOException unused_ex) {
                    }
                }
                return super.length();
            }
            catch(Throwable throwable0) {
                if(assetFileDescriptor0 != null) {
                    try {
                        assetFileDescriptor0.close();
                    }
                    catch(IOException unused_ex) {
                    }
                }
                throw throwable0;
            }
            try {
                assetFileDescriptor0.close();
            }
            catch(IOException unused_ex) {
            }
            return v;
        }
        return super.length();
    }

    @Override  // com.badlogic.gdx.files.FileHandle
    public FileHandle[] list() {
        if(this.type == FileType.Internal) {
            try {
                String s = this.file.getPath();
                String[] arr_s = this.assets.list(s);
                FileHandle[] arr_fileHandle = new FileHandle[arr_s.length];
                for(int v = 0; v < arr_fileHandle.length; ++v) {
                    File file0 = new File(this.file, arr_s[v]);
                    arr_fileHandle[v] = new AndroidFileHandle(this.assets, file0, this.type);
                }
                return arr_fileHandle;
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Error listing children: " + this.file + " (" + this.type + ")", exception0);
            }
        }
        return super.list();
    }

    @Override  // com.badlogic.gdx.files.FileHandle
    public FileHandle[] list(FileFilter fileFilter0) {
        if(this.type == FileType.Internal) {
            try {
                String s = this.file.getPath();
                String[] arr_s = this.assets.list(s);
                FileHandle[] arr_fileHandle = new FileHandle[arr_s.length];
                int v1 = 0;
                for(int v = 0; v < arr_fileHandle.length; ++v) {
                    File file0 = new File(this.file, arr_s[v]);
                    AndroidFileHandle androidFileHandle0 = new AndroidFileHandle(this.assets, file0, this.type);
                    if(fileFilter0.accept(androidFileHandle0.file())) {
                        arr_fileHandle[v1] = androidFileHandle0;
                        ++v1;
                    }
                }
                if(v1 < arr_s.length) {
                    FileHandle[] arr_fileHandle1 = new FileHandle[v1];
                    System.arraycopy(arr_fileHandle, 0, arr_fileHandle1, 0, v1);
                    return arr_fileHandle1;
                }
                return arr_fileHandle;
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Error listing children: " + this.file + " (" + this.type + ")", exception0);
            }
        }
        return super.list(fileFilter0);
    }

    @Override  // com.badlogic.gdx.files.FileHandle
    public FileHandle[] list(FilenameFilter filenameFilter0) {
        if(this.type == FileType.Internal) {
            try {
                String s = this.file.getPath();
                String[] arr_s = this.assets.list(s);
                FileHandle[] arr_fileHandle = new FileHandle[arr_s.length];
                int v1 = 0;
                for(int v = 0; v < arr_fileHandle.length; ++v) {
                    String s1 = arr_s[v];
                    if(filenameFilter0.accept(this.file, s1)) {
                        File file0 = new File(this.file, s1);
                        arr_fileHandle[v1] = new AndroidFileHandle(this.assets, file0, this.type);
                        ++v1;
                    }
                }
                if(v1 < arr_s.length) {
                    FileHandle[] arr_fileHandle1 = new FileHandle[v1];
                    System.arraycopy(arr_fileHandle, 0, arr_fileHandle1, 0, v1);
                    return arr_fileHandle1;
                }
                return arr_fileHandle;
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Error listing children: " + this.file + " (" + this.type + ")", exception0);
            }
        }
        return super.list(filenameFilter0);
    }

    @Override  // com.badlogic.gdx.files.FileHandle
    public FileHandle[] list(String s) {
        if(this.type == FileType.Internal) {
            try {
                String s1 = this.file.getPath();
                String[] arr_s = this.assets.list(s1);
                FileHandle[] arr_fileHandle = new FileHandle[arr_s.length];
                int v1 = 0;
                for(int v = 0; v < arr_fileHandle.length; ++v) {
                    String s2 = arr_s[v];
                    if(s2.endsWith(s)) {
                        File file0 = new File(this.file, s2);
                        arr_fileHandle[v1] = new AndroidFileHandle(this.assets, file0, this.type);
                        ++v1;
                    }
                }
                if(v1 < arr_s.length) {
                    FileHandle[] arr_fileHandle1 = new FileHandle[v1];
                    System.arraycopy(arr_fileHandle, 0, arr_fileHandle1, 0, v1);
                    return arr_fileHandle1;
                }
                return arr_fileHandle;
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Error listing children: " + this.file + " (" + this.type + ")", exception0);
            }
        }
        return super.list(s);
    }

    @Override  // com.badlogic.gdx.files.FileHandle
    public ByteBuffer map(FileChannel.MapMode fileChannel$MapMode0) {
        ByteBuffer byteBuffer0;
        FileInputStream fileInputStream1;
        long v1;
        long v;
        FileInputStream fileInputStream0;
        if(this.type == FileType.Internal) {
            try {
                fileInputStream0 = null;
                AssetFileDescriptor assetFileDescriptor0 = this.getAssetFileDescriptor();
                v = assetFileDescriptor0.getStartOffset();
                v1 = assetFileDescriptor0.getDeclaredLength();
                fileInputStream1 = new FileInputStream(assetFileDescriptor0.getFileDescriptor());
                goto label_9;
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Error memory mapping file: " + this + " (" + this.type + ")", exception0);
                try {
                label_9:
                    byteBuffer0 = fileInputStream1.getChannel().map(fileChannel$MapMode0, v, v1);
                    byteBuffer0.order(ByteOrder.nativeOrder());
                    goto label_21;
                }
                catch(Exception exception0) {
                }
                catch(Throwable throwable0) {
                    fileInputStream0 = fileInputStream1;
                    StreamUtils.closeQuietly(fileInputStream0);
                    throw throwable0;
                }
                fileInputStream0 = fileInputStream1;
                try {
                    throw new GdxRuntimeException("Error memory mapping file: " + this + " (" + this.type + ")", exception0);
                }
                catch(Throwable throwable0) {
                    StreamUtils.closeQuietly(fileInputStream0);
                    throw throwable0;
                }
            }
            catch(Throwable throwable0) {
                StreamUtils.closeQuietly(fileInputStream0);
                throw throwable0;
            }
            fileInputStream0 = fileInputStream1;
            StreamUtils.closeQuietly(fileInputStream0);
            throw throwable0;
        label_21:
            StreamUtils.closeQuietly(fileInputStream1);
            return byteBuffer0;
        }
        return super.map(fileChannel$MapMode0);
    }

    @Override  // com.badlogic.gdx.files.FileHandle
    public FileHandle parent() {
        File file0 = this.file.getParentFile();
        if(file0 == null) {
            if(this.type == FileType.Absolute) {
                file0 = new File("/");
                return new AndroidFileHandle(this.assets, file0, this.type);
            }
            file0 = new File("");
        }
        return new AndroidFileHandle(this.assets, file0, this.type);
    }

    @Override  // com.badlogic.gdx.files.FileHandle
    public InputStream read() {
        if(this.type == FileType.Internal) {
            try {
                String s = this.file.getPath();
                return this.assets.open(s);
            }
            catch(IOException iOException0) {
                throw new GdxRuntimeException("Error reading file: " + this.file + " (" + this.type + ")", iOException0);
            }
        }
        return super.read();
    }

    @Override  // com.badlogic.gdx.files.FileHandle
    public FileHandle sibling(String s) {
        String s1 = s.replace('\\', '/');
        if(this.file.getPath().length() == 0) {
            throw new GdxRuntimeException("Cannot get the sibling of the root.");
        }
        return Gdx.files.getFileHandle(new File(this.file.getParent(), s1).getPath(), this.type);
    }
}

