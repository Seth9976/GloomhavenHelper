package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.Texture;

public class FrameBuffer extends GLFrameBuffer {
    FrameBuffer() {
    }

    public FrameBuffer(Format pixmap$Format0, int v, int v1, boolean z) {
        this(pixmap$Format0, v, v1, z, false);
    }

    public FrameBuffer(Format pixmap$Format0, int v, int v1, boolean z, boolean z1) {
        FrameBufferBuilder gLFrameBuffer$FrameBufferBuilder0 = new FrameBufferBuilder(v, v1);
        gLFrameBuffer$FrameBufferBuilder0.addBasicColorTextureAttachment(pixmap$Format0);
        if(z) {
            gLFrameBuffer$FrameBufferBuilder0.addBasicDepthRenderBuffer();
        }
        if(z1) {
            gLFrameBuffer$FrameBufferBuilder0.addBasicStencilRenderBuffer();
        }
        this.bufferBuilder = gLFrameBuffer$FrameBufferBuilder0;
        this.build();
    }

    protected FrameBuffer(GLFrameBufferBuilder gLFrameBuffer$GLFrameBufferBuilder0) {
        super(gLFrameBuffer$GLFrameBufferBuilder0);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    protected void attachFrameBufferColorTexture(GLTexture gLTexture0) {
        this.attachFrameBufferColorTexture(((Texture)gLTexture0));
    }

    protected void attachFrameBufferColorTexture(Texture texture0) {
        Gdx.gl20.glFramebufferTexture2D(0x8D40, 0x8CE0, 0xDE1, texture0.getTextureObjectHandle(), 0);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    protected GLTexture createTexture(FrameBufferTextureAttachmentSpec gLFrameBuffer$FrameBufferTextureAttachmentSpec0) {
        return this.createTexture(gLFrameBuffer$FrameBufferTextureAttachmentSpec0);
    }

    protected Texture createTexture(FrameBufferTextureAttachmentSpec gLFrameBuffer$FrameBufferTextureAttachmentSpec0) {
        Texture texture0 = new Texture(new GLOnlyTextureData(this.bufferBuilder.width, this.bufferBuilder.height, 0, gLFrameBuffer$FrameBufferTextureAttachmentSpec0.internalFormat, gLFrameBuffer$FrameBufferTextureAttachmentSpec0.format, gLFrameBuffer$FrameBufferTextureAttachmentSpec0.type));
        texture0.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        texture0.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
        return texture0;
    }

    @Override  // com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    protected void disposeColorTexture(GLTexture gLTexture0) {
        this.disposeColorTexture(((Texture)gLTexture0));
    }

    protected void disposeColorTexture(Texture texture0) {
        texture0.dispose();
    }

    @Override  // com.badlogic.gdx.graphics.glutils.GLFrameBuffer
    public static void unbind() {
        GLFrameBuffer.unbind();
    }
}

