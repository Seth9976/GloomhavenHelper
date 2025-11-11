package com.esotericsoftware.spine.attachments;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;

public interface HasTextureRegion {
    Color getColor();

    String getPath();

    TextureRegion getRegion();

    @Null
    Sequence getSequence();

    void setPath(String arg1);

    void setRegion(TextureRegion arg1);

    void setSequence(@Null Sequence arg1);

    void updateRegion();
}

