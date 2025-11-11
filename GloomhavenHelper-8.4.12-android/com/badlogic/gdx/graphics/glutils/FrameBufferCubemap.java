package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cubemap.CubemapSide;
import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class FrameBufferCubemap extends GLFrameBuffer {
    private static final CubemapSide[] cubemapSides;
    private int currentSide;

    static {
        FrameBufferCubemap.cubemapSides = CubemapSide.values();
    }

    FrameBufferCubemap() {
    }

    public FrameBufferCubemap(Format pixmap$Format0, int v, int v1, boolean z) {
        this(pixmap$Format0, v, v1, z, false);
    }

    public FrameBufferCubemap(Format pixmap$Format0, int v, int v1, boolean z, boolean z1) {
        FrameBufferCubemapBuilder gLFrameBuffer$FrameBufferCubemapBuilder0 = new FrameBufferCubemapBuilder(v, v1);
        gLFrameBuffer$FrameBufferCubemapBuilder0.addBasicColorTextureAttachment(pixmap$Format0);
        if(z) {
            gLFrameBuffer$FrameBufferCubemapBuilder0.addBasicDepthRenderBuffer();
        }
        if(z1) {
            gLFrameBuffer$FrameBufferCubemapBuilder0.addBasicStencilRenderBuffer();
        }
        this.bufferBuilder = gLFrameBuffer$FrameBufferCubemapBuilder0;
        this.build();
    }

    protected FrameBufferCubemap(GLFrameBufferBuilder gLFrameBuffer$GLFrameBufferBuilder0) {
        super(gLFrameBuffer$GLFrameBufferBuilder0);
    }

    protected void attachFrameBufferColorTexture(Cubemap cubemap0) {
        GL20 gL200 = Gdx.gl20;
        int v = cubemap0.getTextureObjectHandle();
        CubemapSide[] arr_cubemap$CubemapSide = CubemapSide.values();
        for(int v1 = 0; v1 < arr_cubemap$CubemapSide.length; ++v1) {
            gL200.glFramebufferTexture2D(0x8D40, 0x8CE0, arr_cubemap$CubemapSide[v1].glEnum, v, 0);
        }
    }

    @Override  // com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    protected void attachFrameBufferColorTexture(GLTexture gLTexture0) {
        this.attachFrameBufferColorTexture(((Cubemap)gLTexture0));
    }

    @Override  // com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    public void bind() {
        this.currentSide = -1;
        super.bind();
    }

    protected void bindSide(CubemapSide cubemap$CubemapSide0) {
        GL20 gL200 = Gdx.gl20;
        int v = ((Cubemap)this.getColorBufferTexture()).getTextureObjectHandle();
        gL200.glFramebufferTexture2D(0x8D40, 0x8CE0, cubemap$CubemapSide0.glEnum, v, 0);
    }

    protected Cubemap createTexture(FrameBufferTextureAttachmentSpec gLFrameBuffer$FrameBufferTextureAttachmentSpec0) {
        GLOnlyTextureData gLOnlyTextureData0 = new GLOnlyTextureData(this.bufferBuilder.width, this.bufferBuilder.height, 0, gLFrameBuffer$FrameBufferTextureAttachmentSpec0.internalFormat, gLFrameBuffer$FrameBufferTextureAttachmentSpec0.format, gLFrameBuffer$FrameBufferTextureAttachmentSpec0.type);
        Cubemap cubemap0 = new Cubemap(gLOnlyTextureData0, gLOnlyTextureData0, gLOnlyTextureData0, gLOnlyTextureData0, gLOnlyTextureData0, gLOnlyTextureData0);
        cubemap0.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        cubemap0.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
        return cubemap0;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    protected GLTexture createTexture(FrameBufferTextureAttachmentSpec gLFrameBuffer$FrameBufferTextureAttachmentSpec0) {
        return this.createTexture(gLFrameBuffer$FrameBufferTextureAttachmentSpec0);
    }

    protected void disposeColorTexture(Cubemap cubemap0) {
        cubemap0.dispose();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    protected void disposeColorTexture(GLTexture gLTexture0) {
        this.disposeColorTexture(((Cubemap)gLTexture0));
    }

    public CubemapSide getSide() {
        return this.currentSide >= 0 ? FrameBufferCubemap.cubemapSides[this.currentSide] : null;
    }

    public boolean nextSide() {
        int v = this.currentSide;
        if(v > 5) {
            throw new GdxRuntimeException("No remaining sides.");
        }
        if(v == 5) {
            return false;
        }
        this.currentSide = v + 1;
        this.bindSide(this.getSide());
        return true;
    }
}

