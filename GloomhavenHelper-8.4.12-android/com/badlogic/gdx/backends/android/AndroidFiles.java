package com.badlogic.gdx.backends.android;

import com.badlogic.gdx.Files;

public interface AndroidFiles extends Files {
    ZipResourceFile getExpansionFile();

    boolean setAPKExpansion(int arg1, int arg2);
}

