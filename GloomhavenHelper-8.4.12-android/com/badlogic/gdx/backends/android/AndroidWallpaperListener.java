package com.badlogic.gdx.backends.android;

public interface AndroidWallpaperListener {
    void iconDropped(int arg1, int arg2);

    void offsetChange(float arg1, float arg2, float arg3, float arg4, int arg5, int arg6);

    void previewStateChange(boolean arg1);
}

