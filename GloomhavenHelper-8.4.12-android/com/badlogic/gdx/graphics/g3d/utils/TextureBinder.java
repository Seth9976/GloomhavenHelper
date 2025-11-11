package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.GLTexture;

public interface TextureBinder {
    void begin();

    int bind(GLTexture arg1);

    int bind(TextureDescriptor arg1);

    void end();

    int getBindCount();

    int getReuseCount();

    void resetCounts();
}

