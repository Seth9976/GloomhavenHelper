package com.badlogic.gdx;

import com.badlogic.gdx.files.FileHandle;

public interface Files {
    public static enum FileType {
        Classpath,
        Internal,
        External,
        Absolute,
        Local;

    }

    FileHandle absolute(String arg1);

    FileHandle classpath(String arg1);

    FileHandle external(String arg1);

    String getExternalStoragePath();

    FileHandle getFileHandle(String arg1, FileType arg2);

    String getLocalStoragePath();

    FileHandle internal(String arg1);

    boolean isExternalStorageAvailable();

    boolean isLocalStorageAvailable();

    FileHandle local(String arg1);
}

