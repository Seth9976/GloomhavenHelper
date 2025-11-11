package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.Texture;

public class FloatFrameBuffer extends FrameBuffer {
    FloatFrameBuffer() {
    }

    public FloatFrameBuffer(int v, int v1, boolean z) {
        FloatFrameBufferBuilder gLFrameBuffer$FloatFrameBufferBuilder0 = new FloatFrameBufferBuilder(v, v1);
        gLFrameBuffer$FloatFrameBufferBuilder0.addFloatAttachment(0x8814, 6408, 0x1406, false);
        if(z) {
            gLFrameBuffer$FloatFrameBufferBuilder0.addBasicDepthRenderBuffer();
        }
        this.bufferBuilder = gLFrameBuffer$FloatFrameBufferBuilder0;
        this.build();
    }

    protected FloatFrameBuffer(GLFrameBufferBuilder gLFrameBuffer$GLFrameBufferBuilder0) {
        super(gLFrameBuffer$GLFrameBufferBuilder0);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.FrameBuffer
    protected GLTexture createTexture(FrameBufferTextureAttachmentSpec gLFrameBuffer$FrameBufferTextureAttachmentSpec0) {
        return this.createTexture(gLFrameBuffer$FrameBufferTextureAttachmentSpec0);
    }

    @Override  // com.badlogic.gdx.graphics.glutils.FrameBuffer
    protected Texture createTexture(FrameBufferTextureAttachmentSpec gLFrameBuffer$FrameBufferTextureAttachmentSpec0) {
        Texture texture0 = new Texture(new FloatTextureData(this.bufferBuilder.width, this.bufferBuilder.height, gLFrameBuffer$FrameBufferTextureAttachmentSpec0.internalFormat, gLFrameBuffer$FrameBufferTextureAttachmentSpec0.format, gLFrameBuffer$FrameBufferTextureAttachmentSpec0.type, gLFrameBuffer$FrameBufferTextureAttachmentSpec0.isGpuOnly));
        if(Gdx.app.getType() == ApplicationType.Desktop || Gdx.app.getType() == ApplicationType.Applet) {
            texture0.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        }
        else {
            texture0.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        }
        texture0.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
        return texture0;
    }
}

