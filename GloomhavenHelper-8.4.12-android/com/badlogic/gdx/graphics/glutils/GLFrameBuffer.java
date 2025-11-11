package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.utils.Array.ArrayIterator;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

public abstract class GLFrameBuffer implements Disposable {
    public static class FloatFrameBufferBuilder extends GLFrameBufferBuilder {
        public FloatFrameBufferBuilder(int v, int v1) {
            super(v, v1);
        }

        public FloatFrameBuffer build() {
            return new FloatFrameBuffer(this);
        }

        @Override  // com.badlogic.gdx.graphics.glutils.GLFrameBuffer$GLFrameBufferBuilder
        public GLFrameBuffer build() {
            return this.build();
        }
    }

    public static class FrameBufferBuilder extends GLFrameBufferBuilder {
        public FrameBufferBuilder(int v, int v1) {
            super(v, v1);
        }

        public FrameBuffer build() {
            return new FrameBuffer(this);
        }

        @Override  // com.badlogic.gdx.graphics.glutils.GLFrameBuffer$GLFrameBufferBuilder
        public GLFrameBuffer build() {
            return this.build();
        }
    }

    public static class FrameBufferCubemapBuilder extends GLFrameBufferBuilder {
        public FrameBufferCubemapBuilder(int v, int v1) {
            super(v, v1);
        }

        public FrameBufferCubemap build() {
            return new FrameBufferCubemap(this);
        }

        @Override  // com.badlogic.gdx.graphics.glutils.GLFrameBuffer$GLFrameBufferBuilder
        public GLFrameBuffer build() {
            return this.build();
        }
    }

    public static class FrameBufferRenderBufferAttachmentSpec {
        int internalFormat;

        public FrameBufferRenderBufferAttachmentSpec(int v) {
            this.internalFormat = v;
        }
    }

    public static class FrameBufferTextureAttachmentSpec {
        int format;
        int internalFormat;
        boolean isDepth;
        boolean isFloat;
        boolean isGpuOnly;
        boolean isStencil;
        int type;

        public FrameBufferTextureAttachmentSpec(int v, int v1, int v2) {
            this.internalFormat = v;
            this.format = v1;
            this.type = v2;
        }

        // 去混淆评级： 低(20)
        public boolean isColorTexture() {
            return !this.isDepth && !this.isStencil;
        }
    }

    public static abstract class GLFrameBufferBuilder {
        protected FrameBufferRenderBufferAttachmentSpec depthRenderBufferSpec;
        protected boolean hasDepthRenderBuffer;
        protected boolean hasPackedStencilDepthRenderBuffer;
        protected boolean hasStencilRenderBuffer;
        protected int height;
        protected FrameBufferRenderBufferAttachmentSpec packedStencilDepthRenderBufferSpec;
        protected FrameBufferRenderBufferAttachmentSpec stencilRenderBufferSpec;
        protected Array textureAttachmentSpecs;
        protected int width;

        public GLFrameBufferBuilder(int v, int v1) {
            this.textureAttachmentSpecs = new Array();
            this.width = v;
            this.height = v1;
        }

        public GLFrameBufferBuilder addBasicColorTextureAttachment(Format pixmap$Format0) {
            int v = Format.toGlFormat(pixmap$Format0);
            return this.addColorTextureAttachment(v, v, Format.toGlType(pixmap$Format0));
        }

        public GLFrameBufferBuilder addBasicDepthRenderBuffer() {
            return this.addDepthRenderBuffer(0x81A5);
        }

        public GLFrameBufferBuilder addBasicStencilDepthPackedRenderBuffer() {
            return this.addStencilDepthPackedRenderBuffer(0x88F0);
        }

        public GLFrameBufferBuilder addBasicStencilRenderBuffer() {
            return this.addStencilRenderBuffer(0x8D48);
        }

        public GLFrameBufferBuilder addColorTextureAttachment(int v, int v1, int v2) {
            this.textureAttachmentSpecs.add(new FrameBufferTextureAttachmentSpec(v, v1, v2));
            return this;
        }

        public GLFrameBufferBuilder addDepthRenderBuffer(int v) {
            this.depthRenderBufferSpec = new FrameBufferRenderBufferAttachmentSpec(v);
            this.hasDepthRenderBuffer = true;
            return this;
        }

        public GLFrameBufferBuilder addDepthTextureAttachment(int v, int v1) {
            FrameBufferTextureAttachmentSpec gLFrameBuffer$FrameBufferTextureAttachmentSpec0 = new FrameBufferTextureAttachmentSpec(v, 6402, v1);
            gLFrameBuffer$FrameBufferTextureAttachmentSpec0.isDepth = true;
            this.textureAttachmentSpecs.add(gLFrameBuffer$FrameBufferTextureAttachmentSpec0);
            return this;
        }

        public GLFrameBufferBuilder addFloatAttachment(int v, int v1, int v2, boolean z) {
            FrameBufferTextureAttachmentSpec gLFrameBuffer$FrameBufferTextureAttachmentSpec0 = new FrameBufferTextureAttachmentSpec(v, v1, v2);
            gLFrameBuffer$FrameBufferTextureAttachmentSpec0.isFloat = true;
            gLFrameBuffer$FrameBufferTextureAttachmentSpec0.isGpuOnly = z;
            this.textureAttachmentSpecs.add(gLFrameBuffer$FrameBufferTextureAttachmentSpec0);
            return this;
        }

        public GLFrameBufferBuilder addStencilDepthPackedRenderBuffer(int v) {
            this.packedStencilDepthRenderBufferSpec = new FrameBufferRenderBufferAttachmentSpec(v);
            this.hasPackedStencilDepthRenderBuffer = true;
            return this;
        }

        public GLFrameBufferBuilder addStencilRenderBuffer(int v) {
            this.stencilRenderBufferSpec = new FrameBufferRenderBufferAttachmentSpec(v);
            this.hasStencilRenderBuffer = true;
            return this;
        }

        public GLFrameBufferBuilder addStencilTextureAttachment(int v, int v1) {
            FrameBufferTextureAttachmentSpec gLFrameBuffer$FrameBufferTextureAttachmentSpec0 = new FrameBufferTextureAttachmentSpec(v, 0x8D20, v1);
            gLFrameBuffer$FrameBufferTextureAttachmentSpec0.isStencil = true;
            this.textureAttachmentSpecs.add(gLFrameBuffer$FrameBufferTextureAttachmentSpec0);
            return this;
        }

        public abstract GLFrameBuffer build();
    }

    protected static final int GL_DEPTH24_STENCIL8_OES = 0x88F0;
    protected GLFrameBufferBuilder bufferBuilder;
    protected static final Map buffers;
    protected static int defaultFramebufferHandle;
    protected static boolean defaultFramebufferHandleInitialized;
    protected int depthStencilPackedBufferHandle;
    protected int depthbufferHandle;
    protected int framebufferHandle;
    protected boolean hasDepthStencilPackedBuffer;
    protected boolean isMRT;
    protected int stencilbufferHandle;
    protected Array textureAttachments;

    static {
        GLFrameBuffer.buffers = new HashMap();
        GLFrameBuffer.defaultFramebufferHandleInitialized = false;
    }

    GLFrameBuffer() {
        this.textureAttachments = new Array();
    }

    protected GLFrameBuffer(GLFrameBufferBuilder gLFrameBuffer$GLFrameBufferBuilder0) {
        this.textureAttachments = new Array();
        this.bufferBuilder = gLFrameBuffer$GLFrameBufferBuilder0;
        this.build();
    }

    private static void addManagedFrameBuffer(Application application0, GLFrameBuffer gLFrameBuffer0) {
        Array array0 = (Array)GLFrameBuffer.buffers.get(application0);
        if(array0 == null) {
            array0 = new Array();
        }
        array0.add(gLFrameBuffer0);
        GLFrameBuffer.buffers.put(application0, array0);
    }

    protected abstract void attachFrameBufferColorTexture(GLTexture arg1);

    public void begin() {
        this.bind();
        this.setFrameBufferViewport();
    }

    public void bind() {
        Gdx.gl20.glBindFramebuffer(0x8D40, this.framebufferHandle);
    }

    protected void build() {
        int v3;
        GL20 gL200 = Gdx.gl20;
        this.checkValidBuilder();
        if(!GLFrameBuffer.defaultFramebufferHandleInitialized) {
            GLFrameBuffer.defaultFramebufferHandleInitialized = true;
            if(Gdx.app.getType() == ApplicationType.iOS) {
                IntBuffer intBuffer0 = ByteBuffer.allocateDirect(0x40).order(ByteOrder.nativeOrder()).asIntBuffer();
                gL200.glGetIntegerv(36006, intBuffer0);
                GLFrameBuffer.defaultFramebufferHandle = intBuffer0.get(0);
            }
            else {
                GLFrameBuffer.defaultFramebufferHandle = 0;
            }
        }
        this.framebufferHandle = gL200.glGenFramebuffer();
        gL200.glBindFramebuffer(0x8D40, this.framebufferHandle);
        int v = this.bufferBuilder.width;
        int v1 = this.bufferBuilder.height;
        if(this.bufferBuilder.hasDepthRenderBuffer) {
            this.depthbufferHandle = gL200.glGenRenderbuffer();
            gL200.glBindRenderbuffer(0x8D41, this.depthbufferHandle);
            gL200.glRenderbufferStorage(0x8D41, this.bufferBuilder.depthRenderBufferSpec.internalFormat, v, v1);
        }
        if(this.bufferBuilder.hasStencilRenderBuffer) {
            this.stencilbufferHandle = gL200.glGenRenderbuffer();
            gL200.glBindRenderbuffer(0x8D41, this.stencilbufferHandle);
            gL200.glRenderbufferStorage(0x8D41, this.bufferBuilder.stencilRenderBufferSpec.internalFormat, v, v1);
        }
        if(this.bufferBuilder.hasPackedStencilDepthRenderBuffer) {
            this.depthStencilPackedBufferHandle = gL200.glGenRenderbuffer();
            gL200.glBindRenderbuffer(0x8D41, this.depthStencilPackedBufferHandle);
            gL200.glRenderbufferStorage(0x8D41, this.bufferBuilder.packedStencilDepthRenderBufferSpec.internalFormat, v, v1);
        }
        this.isMRT = this.bufferBuilder.textureAttachmentSpecs.size > 1;
        if(this.isMRT) {
            int v2 = 0;
            for(Object object0: this.bufferBuilder.textureAttachmentSpecs) {
                FrameBufferTextureAttachmentSpec gLFrameBuffer$FrameBufferTextureAttachmentSpec0 = (FrameBufferTextureAttachmentSpec)object0;
                GLTexture gLTexture0 = this.createTexture(gLFrameBuffer$FrameBufferTextureAttachmentSpec0);
                this.textureAttachments.add(gLTexture0);
                if(gLFrameBuffer$FrameBufferTextureAttachmentSpec0.isColorTexture()) {
                    gL200.glFramebufferTexture2D(0x8D40, v2 + 0x8CE0, 0xDE1, gLTexture0.getTextureObjectHandle(), 0);
                    ++v2;
                }
                else if(gLFrameBuffer$FrameBufferTextureAttachmentSpec0.isDepth) {
                    gL200.glFramebufferTexture2D(0x8D40, 0x8D00, 0xDE1, gLTexture0.getTextureObjectHandle(), 0);
                }
                else if(gLFrameBuffer$FrameBufferTextureAttachmentSpec0.isStencil) {
                    gL200.glFramebufferTexture2D(0x8D40, 0x8D20, 0xDE1, gLTexture0.getTextureObjectHandle(), 0);
                }
            }
            v3 = v2;
        }
        else {
            GLTexture gLTexture1 = this.createTexture(((FrameBufferTextureAttachmentSpec)this.bufferBuilder.textureAttachmentSpecs.first()));
            this.textureAttachments.add(gLTexture1);
            gL200.glBindTexture(gLTexture1.glTarget, gLTexture1.getTextureObjectHandle());
            v3 = 0;
        }
        if(this.isMRT) {
            IntBuffer intBuffer1 = BufferUtils.newIntBuffer(v3);
            for(int v4 = 0; v4 < v3; ++v4) {
                intBuffer1.put(v4 + 0x8CE0);
            }
            intBuffer1.position(0);
            Gdx.gl30.glDrawBuffers(v3, intBuffer1);
        }
        else {
            this.attachFrameBufferColorTexture(((GLTexture)this.textureAttachments.first()));
        }
        if(this.bufferBuilder.hasDepthRenderBuffer) {
            gL200.glFramebufferRenderbuffer(0x8D40, 0x8D00, 0x8D41, this.depthbufferHandle);
        }
        if(this.bufferBuilder.hasStencilRenderBuffer) {
            gL200.glFramebufferRenderbuffer(0x8D40, 0x8D20, 0x8D41, this.stencilbufferHandle);
        }
        if(this.bufferBuilder.hasPackedStencilDepthRenderBuffer) {
            gL200.glFramebufferRenderbuffer(0x8D40, 33306, 0x8D41, this.depthStencilPackedBufferHandle);
        }
        gL200.glBindRenderbuffer(0x8D41, 0);
        for(Object object1: this.textureAttachments) {
            gL200.glBindTexture(((GLTexture)object1).glTarget, 0);
        }
        int v5 = gL200.glCheckFramebufferStatus(0x8D40);
        if(v5 == 36061 && this.bufferBuilder.hasDepthRenderBuffer && this.bufferBuilder.hasStencilRenderBuffer && (Gdx.graphics.supportsExtension("GL_OES_packed_depth_stencil") || Gdx.graphics.supportsExtension("GL_EXT_packed_depth_stencil"))) {
            if(this.bufferBuilder.hasDepthRenderBuffer) {
                gL200.glDeleteRenderbuffer(this.depthbufferHandle);
                this.depthbufferHandle = 0;
            }
            if(this.bufferBuilder.hasStencilRenderBuffer) {
                gL200.glDeleteRenderbuffer(this.stencilbufferHandle);
                this.stencilbufferHandle = 0;
            }
            if(this.bufferBuilder.hasPackedStencilDepthRenderBuffer) {
                gL200.glDeleteRenderbuffer(this.depthStencilPackedBufferHandle);
                this.depthStencilPackedBufferHandle = 0;
            }
            this.depthStencilPackedBufferHandle = gL200.glGenRenderbuffer();
            this.hasDepthStencilPackedBuffer = true;
            gL200.glBindRenderbuffer(0x8D41, this.depthStencilPackedBufferHandle);
            gL200.glRenderbufferStorage(0x8D41, 0x88F0, v, v1);
            gL200.glBindRenderbuffer(0x8D41, 0);
            gL200.glFramebufferRenderbuffer(0x8D40, 0x8D00, 0x8D41, this.depthStencilPackedBufferHandle);
            gL200.glFramebufferRenderbuffer(0x8D40, 0x8D20, 0x8D41, this.depthStencilPackedBufferHandle);
            v5 = gL200.glCheckFramebufferStatus(0x8D40);
        }
        gL200.glBindFramebuffer(0x8D40, GLFrameBuffer.defaultFramebufferHandle);
        if(v5 != 36053) {
            for(Object object2: this.textureAttachments) {
                this.disposeColorTexture(((GLTexture)object2));
            }
            if(this.hasDepthStencilPackedBuffer) {
                gL200.glDeleteBuffer(this.depthStencilPackedBufferHandle);
            }
            else {
                if(this.bufferBuilder.hasDepthRenderBuffer) {
                    gL200.glDeleteRenderbuffer(this.depthbufferHandle);
                }
                if(this.bufferBuilder.hasStencilRenderBuffer) {
                    gL200.glDeleteRenderbuffer(this.stencilbufferHandle);
                }
            }
            gL200.glDeleteFramebuffer(this.framebufferHandle);
            switch(v5) {
                case 36054: {
                    throw new IllegalStateException("Frame buffer couldn\'t be constructed: incomplete attachment");
                }
                case 36055: {
                    throw new IllegalStateException("Frame buffer couldn\'t be constructed: missing attachment");
                }
                case 36057: {
                    throw new IllegalStateException("Frame buffer couldn\'t be constructed: incomplete dimensions");
                }
                case 36061: {
                    throw new IllegalStateException("Frame buffer couldn\'t be constructed: unsupported combination of formats");
                }
                default: {
                    throw new IllegalStateException("Frame buffer couldn\'t be constructed: unknown error " + v5);
                }
            }
        }
        GLFrameBuffer.addManagedFrameBuffer(Gdx.app, this);
    }

    private void checkValidBuilder() {
        if(!Gdx.graphics.isGL30Available()) {
            if(this.bufferBuilder.hasPackedStencilDepthRenderBuffer) {
                throw new GdxRuntimeException("Packed Stencil/Render render buffers are not available on GLES 2.0");
            }
            if(this.bufferBuilder.textureAttachmentSpecs.size <= 1) {
                ArrayIterator array$ArrayIterator0 = this.bufferBuilder.textureAttachmentSpecs.iterator();
                while(true) {
                    if(!array$ArrayIterator0.hasNext()) {
                        return;
                    }
                    Object object0 = array$ArrayIterator0.next();
                    if(((FrameBufferTextureAttachmentSpec)object0).isDepth) {
                        throw new GdxRuntimeException("Depth texture FrameBuffer Attachment not available on GLES 2.0");
                    }
                    if(((FrameBufferTextureAttachmentSpec)object0).isStencil) {
                        throw new GdxRuntimeException("Stencil texture FrameBuffer Attachment not available on GLES 2.0");
                    }
                    if(((FrameBufferTextureAttachmentSpec)object0).isFloat && !Gdx.graphics.supportsExtension("OES_texture_float")) {
                        throw new GdxRuntimeException("Float texture FrameBuffer Attachment not available on GLES 2.0");
                    }
                }
            }
            throw new GdxRuntimeException("Multiple render targets not available on GLES 2.0");
        }
    }

    public static void clearAllFrameBuffers(Application application0) {
        GLFrameBuffer.buffers.remove(application0);
    }

    protected abstract GLTexture createTexture(FrameBufferTextureAttachmentSpec arg1);

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        GL20 gL200 = Gdx.gl20;
        for(Object object0: this.textureAttachments) {
            this.disposeColorTexture(((GLTexture)object0));
        }
        if(this.hasDepthStencilPackedBuffer) {
            gL200.glDeleteRenderbuffer(this.depthStencilPackedBufferHandle);
        }
        else {
            if(this.bufferBuilder.hasDepthRenderBuffer) {
                gL200.glDeleteRenderbuffer(this.depthbufferHandle);
            }
            if(this.bufferBuilder.hasStencilRenderBuffer) {
                gL200.glDeleteRenderbuffer(this.stencilbufferHandle);
            }
        }
        gL200.glDeleteFramebuffer(this.framebufferHandle);
        if(GLFrameBuffer.buffers.get(Gdx.app) != null) {
            ((Array)GLFrameBuffer.buffers.get(Gdx.app)).removeValue(this, true);
        }
    }

    protected abstract void disposeColorTexture(GLTexture arg1);

    public void end() {
        this.end(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
    }

    public void end(int v, int v1, int v2, int v3) {
        GLFrameBuffer.unbind();
        Gdx.gl20.glViewport(v, v1, v2, v3);
    }

    public GLTexture getColorBufferTexture() {
        return (GLTexture)this.textureAttachments.first();
    }

    public int getDepthBufferHandle() {
        return this.depthbufferHandle;
    }

    protected int getDepthStencilPackedBuffer() {
        return this.depthStencilPackedBufferHandle;
    }

    public int getFramebufferHandle() {
        return this.framebufferHandle;
    }

    public int getHeight() {
        return this.bufferBuilder.height;
    }

    public static String getManagedStatus() {
        return GLFrameBuffer.getManagedStatus(new StringBuilder()).toString();
    }

    public static StringBuilder getManagedStatus(StringBuilder stringBuilder0) {
        stringBuilder0.append("Managed buffers/app: { ");
        for(Object object0: GLFrameBuffer.buffers.keySet()) {
            stringBuilder0.append(((Array)GLFrameBuffer.buffers.get(((Application)object0))).size);
            stringBuilder0.append(" ");
        }
        stringBuilder0.append("}");
        return stringBuilder0;
    }

    public int getStencilBufferHandle() {
        return this.stencilbufferHandle;
    }

    public Array getTextureAttachments() {
        return this.textureAttachments;
    }

    public int getWidth() {
        return this.bufferBuilder.width;
    }

    public static void invalidateAllFrameBuffers(Application application0) {
        if(Gdx.gl20 == null) {
            return;
        }
        Array array0 = (Array)GLFrameBuffer.buffers.get(application0);
        if(array0 == null) {
            return;
        }
        for(int v = 0; v < array0.size; ++v) {
            ((GLFrameBuffer)array0.get(v)).build();
        }
    }

    protected void setFrameBufferViewport() {
        Gdx.gl20.glViewport(0, 0, this.bufferBuilder.width, this.bufferBuilder.height);
    }

    public static void unbind() {
        Gdx.gl20.glBindFramebuffer(0x8D40, GLFrameBuffer.defaultFramebufferHandle);
    }
}

