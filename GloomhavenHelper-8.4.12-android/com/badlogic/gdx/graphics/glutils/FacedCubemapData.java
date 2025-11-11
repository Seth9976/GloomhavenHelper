package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Cubemap.CubemapSide;
import com.badlogic.gdx.graphics.CubemapData;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData.Factory;
import com.badlogic.gdx.graphics.TextureData.TextureDataType;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class FacedCubemapData implements CubemapData {
    protected final TextureData[] data;

    public FacedCubemapData() {
        this(null, null, null, null, null, null);
    }

    public FacedCubemapData(int v, int v1, int v2, Format pixmap$Format0) {
        this(new PixmapTextureData(new Pixmap(v2, v1, pixmap$Format0), null, false, true), new PixmapTextureData(new Pixmap(v2, v1, pixmap$Format0), null, false, true), new PixmapTextureData(new Pixmap(v, v2, pixmap$Format0), null, false, true), new PixmapTextureData(new Pixmap(v, v2, pixmap$Format0), null, false, true), new PixmapTextureData(new Pixmap(v, v1, pixmap$Format0), null, false, true), new PixmapTextureData(new Pixmap(v, v1, pixmap$Format0), null, false, true));
    }

    public FacedCubemapData(FileHandle fileHandle0, FileHandle fileHandle1, FileHandle fileHandle2, FileHandle fileHandle3, FileHandle fileHandle4, FileHandle fileHandle5) {
        this(Factory.loadFromFile(fileHandle0, false), Factory.loadFromFile(fileHandle1, false), Factory.loadFromFile(fileHandle2, false), Factory.loadFromFile(fileHandle3, false), Factory.loadFromFile(fileHandle4, false), Factory.loadFromFile(fileHandle5, false));
    }

    public FacedCubemapData(FileHandle fileHandle0, FileHandle fileHandle1, FileHandle fileHandle2, FileHandle fileHandle3, FileHandle fileHandle4, FileHandle fileHandle5, boolean z) {
        this(Factory.loadFromFile(fileHandle0, z), Factory.loadFromFile(fileHandle1, z), Factory.loadFromFile(fileHandle2, z), Factory.loadFromFile(fileHandle3, z), Factory.loadFromFile(fileHandle4, z), Factory.loadFromFile(fileHandle5, z));
    }

    public FacedCubemapData(Pixmap pixmap0, Pixmap pixmap1, Pixmap pixmap2, Pixmap pixmap3, Pixmap pixmap4, Pixmap pixmap5) {
        this(pixmap0, pixmap1, pixmap2, pixmap3, pixmap4, pixmap5, false);
    }

    // 去混淆评级： 低(30)
    public FacedCubemapData(Pixmap pixmap0, Pixmap pixmap1, Pixmap pixmap2, Pixmap pixmap3, Pixmap pixmap4, Pixmap pixmap5, boolean z) {
        this((pixmap0 == null ? null : new PixmapTextureData(pixmap0, null, z, false)), (pixmap1 == null ? null : new PixmapTextureData(pixmap1, null, z, false)), (pixmap2 == null ? null : new PixmapTextureData(pixmap2, null, z, false)), (pixmap3 == null ? null : new PixmapTextureData(pixmap3, null, z, false)), (pixmap4 == null ? null : new PixmapTextureData(pixmap4, null, z, false)), (pixmap5 == null ? null : new PixmapTextureData(pixmap5, null, z, false)));
    }

    public FacedCubemapData(TextureData textureData0, TextureData textureData1, TextureData textureData2, TextureData textureData3, TextureData textureData4, TextureData textureData5) {
        this.data = new TextureData[6];
        this.data[0] = textureData0;
        this.data[1] = textureData1;
        this.data[2] = textureData2;
        this.data[3] = textureData3;
        this.data[4] = textureData4;
        this.data[5] = textureData5;
    }

    @Override  // com.badlogic.gdx.graphics.CubemapData
    public void consumeCubemapData() {
        for(int v = 0; true; ++v) {
            TextureData[] arr_textureData = this.data;
            if(v >= arr_textureData.length) {
                break;
            }
            if(arr_textureData[v].getType() == TextureDataType.Custom) {
                this.data[v].consumeCustomData(v + 34069);
            }
            else {
                Pixmap pixmap0 = this.data[v].consumePixmap();
                boolean z = this.data[v].disposePixmap();
                if(this.data[v].getFormat() != pixmap0.getFormat()) {
                    Pixmap pixmap1 = new Pixmap(pixmap0.getWidth(), pixmap0.getHeight(), this.data[v].getFormat());
                    pixmap1.setBlending(Blending.None);
                    pixmap1.drawPixmap(pixmap0, 0, 0, 0, 0, pixmap0.getWidth(), pixmap0.getHeight());
                    if(this.data[v].disposePixmap()) {
                        pixmap0.dispose();
                    }
                    pixmap0 = pixmap1;
                    z = true;
                }
                Gdx.gl.glPixelStorei(0xCF5, 1);
                Gdx.gl.glTexImage2D(v + 34069, 0, pixmap0.getGLInternalFormat(), pixmap0.getWidth(), pixmap0.getHeight(), 0, pixmap0.getGLFormat(), pixmap0.getGLType(), pixmap0.getPixels());
                if(z) {
                    pixmap0.dispose();
                }
            }
        }
    }

    @Override  // com.badlogic.gdx.graphics.CubemapData
    public int getHeight() {
        int v;
        if(this.data[CubemapSide.PositiveZ.index] == null) {
            v = 0;
        }
        else {
            v = this.data[CubemapSide.PositiveZ.index].getHeight();
            if(v <= 0) {
                v = 0;
            }
        }
        if(this.data[CubemapSide.NegativeZ.index] != null) {
            int v1 = this.data[CubemapSide.NegativeZ.index].getHeight();
            if(v1 > v) {
                v = v1;
            }
        }
        if(this.data[CubemapSide.PositiveX.index] != null) {
            int v2 = this.data[CubemapSide.PositiveX.index].getHeight();
            if(v2 > v) {
                v = v2;
            }
        }
        if(this.data[CubemapSide.NegativeX.index] != null) {
            int v3 = this.data[CubemapSide.NegativeX.index].getHeight();
            return v3 <= v ? v : v3;
        }
        return v;
    }

    public TextureData getTextureData(CubemapSide cubemap$CubemapSide0) {
        return this.data[cubemap$CubemapSide0.index];
    }

    @Override  // com.badlogic.gdx.graphics.CubemapData
    public int getWidth() {
        int v;
        if(this.data[CubemapSide.PositiveZ.index] == null) {
            v = 0;
        }
        else {
            v = this.data[CubemapSide.PositiveZ.index].getWidth();
            if(v <= 0) {
                v = 0;
            }
        }
        if(this.data[CubemapSide.NegativeZ.index] != null) {
            int v1 = this.data[CubemapSide.NegativeZ.index].getWidth();
            if(v1 > v) {
                v = v1;
            }
        }
        if(this.data[CubemapSide.PositiveY.index] != null) {
            int v2 = this.data[CubemapSide.PositiveY.index].getWidth();
            if(v2 > v) {
                v = v2;
            }
        }
        if(this.data[CubemapSide.NegativeY.index] != null) {
            int v3 = this.data[CubemapSide.NegativeY.index].getWidth();
            return v3 <= v ? v : v3;
        }
        return v;
    }

    public boolean isComplete() {
        for(int v = 0; true; ++v) {
            TextureData[] arr_textureData = this.data;
            if(v >= arr_textureData.length) {
                break;
            }
            if(arr_textureData[v] == null) {
                return false;
            }
        }
        return true;
    }

    @Override  // com.badlogic.gdx.graphics.CubemapData
    public boolean isManaged() {
        TextureData[] arr_textureData = this.data;
        for(int v = 0; v < arr_textureData.length; ++v) {
            if(!arr_textureData[v].isManaged()) {
                return false;
            }
        }
        return true;
    }

    @Override  // com.badlogic.gdx.graphics.CubemapData
    public boolean isPrepared() {
        return false;
    }

    public void load(CubemapSide cubemap$CubemapSide0, FileHandle fileHandle0) {
        this.data[cubemap$CubemapSide0.index] = Factory.loadFromFile(fileHandle0, false);
    }

    public void load(CubemapSide cubemap$CubemapSide0, Pixmap pixmap0) {
        this.data[cubemap$CubemapSide0.index] = pixmap0 == null ? null : new PixmapTextureData(pixmap0, null, false, false);
    }

    @Override  // com.badlogic.gdx.graphics.CubemapData
    public void prepare() {
        if(!this.isComplete()) {
            throw new GdxRuntimeException("You need to complete your cubemap data before using it");
        }
        for(int v = 0; true; ++v) {
            TextureData[] arr_textureData = this.data;
            if(v >= arr_textureData.length) {
                break;
            }
            if(!arr_textureData[v].isPrepared()) {
                this.data[v].prepare();
            }
        }
    }
}

