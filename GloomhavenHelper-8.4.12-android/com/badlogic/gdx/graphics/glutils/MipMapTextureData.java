package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData.TextureDataType;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class MipMapTextureData implements TextureData {
    TextureData[] mips;

    public MipMapTextureData(TextureData[] arr_textureData) {
        this.mips = new TextureData[arr_textureData.length];
        System.arraycopy(arr_textureData, 0, this.mips, 0, arr_textureData.length);
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int v) {
        for(int v1 = 0; true; ++v1) {
            TextureData[] arr_textureData = this.mips;
            if(v1 >= arr_textureData.length) {
                break;
            }
            GLTexture.uploadImageData(v, arr_textureData[v1], v1);
        }
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public Pixmap consumePixmap() {
        throw new GdxRuntimeException("It\'s compressed, use the compressed method");
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean disposePixmap() {
        return false;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public Format getFormat() {
        return this.mips[0].getFormat();
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public int getHeight() {
        return this.mips[0].getHeight();
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public TextureDataType getType() {
        return TextureDataType.Custom;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public int getWidth() {
        return this.mips[0].getWidth();
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean isManaged() {
        return true;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean isPrepared() {
        return true;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public void prepare() {
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return false;
    }
}

