package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh.VertexDataType;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;

public class SpriteBatch implements Batch {
    private int blendDstFunc;
    private int blendDstFuncAlpha;
    private int blendSrcFunc;
    private int blendSrcFuncAlpha;
    private boolean blendingDisabled;
    private final Color color;
    float colorPacked;
    private final Matrix4 combinedMatrix;
    private ShaderProgram customShader;
    @Deprecated
    public static VertexDataType defaultVertexDataType;
    boolean drawing;
    int idx;
    float invTexHeight;
    float invTexWidth;
    Texture lastTexture;
    public int maxSpritesInBatch;
    private Mesh mesh;
    private boolean ownsShader;
    private final Matrix4 projectionMatrix;
    public int renderCalls;
    private final ShaderProgram shader;
    public int totalRenderCalls;
    private final Matrix4 transformMatrix;
    final float[] vertices;

    static {
        SpriteBatch.defaultVertexDataType = VertexDataType.VertexArray;
    }

    public SpriteBatch() {
        this(1000, null);
    }

    public SpriteBatch(int v) {
        this(v, null);
    }

    public SpriteBatch(int v, ShaderProgram shaderProgram0) {
        this.idx = 0;
        this.lastTexture = null;
        this.invTexWidth = 0.0f;
        this.invTexHeight = 0.0f;
        this.drawing = false;
        this.transformMatrix = new Matrix4();
        this.projectionMatrix = new Matrix4();
        this.combinedMatrix = new Matrix4();
        this.blendingDisabled = false;
        this.blendSrcFunc = 770;
        this.blendDstFunc = 0x303;
        this.blendSrcFuncAlpha = 770;
        this.blendDstFuncAlpha = 0x303;
        this.customShader = null;
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.colorPacked = Color.WHITE_FLOAT_BITS;
        this.renderCalls = 0;
        this.totalRenderCalls = 0;
        this.maxSpritesInBatch = 0;
        if(v > 0x1FFF) {
            throw new IllegalArgumentException("Can\'t have more than 8191 sprites per batch: " + v);
        }
        this.mesh = new Mesh((Gdx.gl30 == null ? SpriteBatch.defaultVertexDataType : VertexDataType.VertexBufferObjectWithVAO), false, v * 4, v * 6, new VertexAttribute[]{new VertexAttribute(1, 2, "a_position"), new VertexAttribute(4, 4, "a_color"), new VertexAttribute(16, 2, "a_texCoord0")});
        float f = (float)Gdx.graphics.getWidth();
        float f1 = (float)Gdx.graphics.getHeight();
        this.projectionMatrix.setToOrtho2D(0.0f, 0.0f, f, f1);
        this.vertices = new float[v * 20];
        short[] arr_v = new short[v * 6];
        int v1 = 0;
        for(short v2 = 0; v1 < v * 6; v2 = (short)(v2 + 4)) {
            arr_v[v1] = v2;
            arr_v[v1 + 1] = (short)(v2 + 1);
            arr_v[v1 + 2] = (short)(v2 + 2);
            arr_v[v1 + 3] = (short)(v2 + 2);
            arr_v[v1 + 4] = (short)(v2 + 3);
            arr_v[v1 + 5] = v2;
            v1 += 6;
        }
        this.mesh.setIndices(arr_v);
        if(shaderProgram0 == null) {
            this.shader = SpriteBatch.createDefaultShader();
            this.ownsShader = true;
            return;
        }
        this.shader = shaderProgram0;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void begin() {
        if(this.drawing) {
            throw new IllegalStateException("SpriteBatch.end must be called before begin.");
        }
        this.renderCalls = 0;
        Gdx.gl.glDepthMask(false);
        ShaderProgram shaderProgram0 = this.customShader;
        if(shaderProgram0 == null) {
            this.shader.bind();
        }
        else {
            shaderProgram0.bind();
        }
        this.setupMatrices();
        this.drawing = true;
    }

    public static ShaderProgram createDefaultShader() {
        ShaderProgram shaderProgram0 = new ShaderProgram("attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_color.a = v_color.a * (255.0/254.0);\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projTrans * a_position;\n}\n", "#ifdef GL_ES\n#define LOWP lowp\nprecision mediump float;\n#else\n#define LOWP \n#endif\nvarying LOWP vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nvoid main()\n{\n  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n}");
        if(!shaderProgram0.isCompiled()) {
            throw new IllegalArgumentException("Error compiling shader: " + shaderProgram0.getLog());
        }
        return shaderProgram0;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void disableBlending() {
        if(this.blendingDisabled) {
            return;
        }
        this.flush();
        this.blendingDisabled = true;
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.mesh.dispose();
        if(this.ownsShader) {
            ShaderProgram shaderProgram0 = this.shader;
            if(shaderProgram0 != null) {
                shaderProgram0.dispose();
            }
        }
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1) {
        this.draw(texture0, f, f1, ((float)texture0.getWidth()), ((float)texture0.getHeight()));
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1, float f2, float f3) {
        if(!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] arr_f = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.idx == arr_f.length) {
            this.flush();
        }
        float f4 = f2 + f;
        float f5 = f3 + f1;
        float f6 = this.colorPacked;
        int v = this.idx;
        arr_f[v] = f;
        arr_f[v + 1] = f1;
        arr_f[v + 2] = f6;
        arr_f[v + 3] = 0.0f;
        arr_f[v + 4] = 1.0f;
        arr_f[v + 5] = f;
        arr_f[v + 6] = f5;
        arr_f[v + 7] = f6;
        arr_f[v + 8] = 0.0f;
        arr_f[v + 9] = 0.0f;
        arr_f[v + 10] = f4;
        arr_f[v + 11] = f5;
        arr_f[v + 12] = f6;
        arr_f[v + 13] = 1.0f;
        arr_f[v + 14] = 0.0f;
        arr_f[v + 15] = f4;
        arr_f[v + 16] = f1;
        arr_f[v + 17] = f6;
        arr_f[v + 18] = 1.0f;
        arr_f[v + 19] = 1.0f;
        this.idx = v + 20;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7) {
        if(!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] arr_f = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.idx == arr_f.length) {
            this.flush();
        }
        float f8 = f2 + f;
        float f9 = f3 + f1;
        float f10 = this.colorPacked;
        int v = this.idx;
        arr_f[v] = f;
        arr_f[v + 1] = f1;
        arr_f[v + 2] = f10;
        arr_f[v + 3] = f4;
        arr_f[v + 4] = f5;
        arr_f[v + 5] = f;
        arr_f[v + 6] = f9;
        arr_f[v + 7] = f10;
        arr_f[v + 8] = f4;
        arr_f[v + 9] = f7;
        arr_f[v + 10] = f8;
        arr_f[v + 11] = f9;
        arr_f[v + 12] = f10;
        arr_f[v + 13] = f6;
        arr_f[v + 14] = f7;
        arr_f[v + 15] = f8;
        arr_f[v + 16] = f1;
        arr_f[v + 17] = f10;
        arr_f[v + 18] = f6;
        arr_f[v + 19] = f5;
        this.idx = v + 20;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int v, int v1, int v2, int v3, boolean z, boolean z1) {
        float f28;
        float f27;
        float f26;
        float f21;
        if(!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] arr_f = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.idx == arr_f.length) {
            this.flush();
        }
        float f9 = f + f2;
        float f10 = f1 + f3;
        float f11 = -f2;
        float f12 = -f3;
        float f13 = f4 - f2;
        float f14 = f5 - f3;
        if(f6 != 1.0f || f7 != 1.0f) {
            f11 *= f6;
            f12 *= f7;
            f13 *= f6;
            f14 *= f7;
        }
        if(f8 == 0.0f) {
            f26 = f13;
            f27 = f14;
            f21 = f11;
            f28 = f12;
        }
        else {
            float f15 = MathUtils.cosDeg(f8);
            float f16 = MathUtils.sinDeg(f8);
            float f17 = f15 * f11;
            float f18 = f17 - f16 * f12;
            float f19 = f11 * f16;
            f12 = f12 * f15 + f19;
            float f20 = f16 * f14;
            f21 = f17 - f20;
            float f22 = f14 * f15;
            float f23 = f19 + f22;
            float f24 = f15 * f13 - f20;
            float f25 = f22 + f16 * f13;
            f26 = f24 - f21 + f18;
            f13 = f24;
            f27 = f25;
            f14 = f23;
            f11 = f18;
            f28 = f25 - (f23 - f12);
        }
        float f29 = ((float)v) * this.invTexWidth;
        float f30 = ((float)(v1 + v3)) * this.invTexHeight;
        float f31 = ((float)(v + v2)) * this.invTexWidth;
        float f32 = ((float)v1) * this.invTexHeight;
        if(!z) {
            float f33 = f29;
            f29 = f31;
            f31 = f33;
        }
        if(!z1) {
            float f34 = f30;
            f30 = f32;
            f32 = f34;
        }
        float f35 = this.colorPacked;
        int v4 = this.idx;
        arr_f[v4] = f11 + f9;
        arr_f[v4 + 1] = f12 + f10;
        arr_f[v4 + 2] = f35;
        arr_f[v4 + 3] = f31;
        arr_f[v4 + 4] = f32;
        arr_f[v4 + 5] = f21 + f9;
        arr_f[v4 + 6] = f14 + f10;
        arr_f[v4 + 7] = f35;
        arr_f[v4 + 8] = f31;
        arr_f[v4 + 9] = f30;
        arr_f[v4 + 10] = f13 + f9;
        arr_f[v4 + 11] = f27 + f10;
        arr_f[v4 + 12] = f35;
        arr_f[v4 + 13] = f29;
        arr_f[v4 + 14] = f30;
        arr_f[v4 + 15] = f26 + f9;
        arr_f[v4 + 16] = f28 + f10;
        arr_f[v4 + 17] = f35;
        arr_f[v4 + 18] = f29;
        arr_f[v4 + 19] = f32;
        this.idx = v4 + 20;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1, float f2, float f3, int v, int v1, int v2, int v3, boolean z, boolean z1) {
        if(!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] arr_f = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.idx == arr_f.length) {
            this.flush();
        }
        float f4 = ((float)v) * this.invTexWidth;
        float f5 = ((float)(v3 + v1)) * this.invTexHeight;
        float f6 = ((float)(v + v2)) * this.invTexWidth;
        float f7 = ((float)v1) * this.invTexHeight;
        float f8 = f2 + f;
        float f9 = f3 + f1;
        if(z) {
            float f10 = f6;
            f6 = f4;
            f4 = f10;
        }
        if(!z1) {
            float f11 = f5;
            f5 = f7;
            f7 = f11;
        }
        float f12 = this.colorPacked;
        int v4 = this.idx;
        arr_f[v4] = f;
        arr_f[v4 + 1] = f1;
        arr_f[v4 + 2] = f12;
        arr_f[v4 + 3] = f4;
        arr_f[v4 + 4] = f7;
        arr_f[v4 + 5] = f;
        arr_f[v4 + 6] = f9;
        arr_f[v4 + 7] = f12;
        arr_f[v4 + 8] = f4;
        arr_f[v4 + 9] = f5;
        arr_f[v4 + 10] = f8;
        arr_f[v4 + 11] = f9;
        arr_f[v4 + 12] = f12;
        arr_f[v4 + 13] = f6;
        arr_f[v4 + 14] = f5;
        arr_f[v4 + 15] = f8;
        arr_f[v4 + 16] = f1;
        arr_f[v4 + 17] = f12;
        arr_f[v4 + 18] = f6;
        arr_f[v4 + 19] = f7;
        this.idx = v4 + 20;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1, int v, int v1, int v2, int v3) {
        if(!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] arr_f = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.idx == arr_f.length) {
            this.flush();
        }
        float f2 = ((float)v) * this.invTexWidth;
        float f3 = ((float)(v1 + v3)) * this.invTexHeight;
        float f4 = ((float)(v + v2)) * this.invTexWidth;
        float f5 = ((float)v1) * this.invTexHeight;
        float f6 = ((float)v2) + f;
        float f7 = ((float)v3) + f1;
        float f8 = this.colorPacked;
        int v4 = this.idx;
        arr_f[v4] = f;
        arr_f[v4 + 1] = f1;
        arr_f[v4 + 2] = f8;
        arr_f[v4 + 3] = f2;
        arr_f[v4 + 4] = f3;
        arr_f[v4 + 5] = f;
        arr_f[v4 + 6] = f7;
        arr_f[v4 + 7] = f8;
        arr_f[v4 + 8] = f2;
        arr_f[v4 + 9] = f5;
        arr_f[v4 + 10] = f6;
        arr_f[v4 + 11] = f7;
        arr_f[v4 + 12] = f8;
        arr_f[v4 + 13] = f4;
        arr_f[v4 + 14] = f5;
        arr_f[v4 + 15] = f6;
        arr_f[v4 + 16] = f1;
        arr_f[v4 + 17] = f8;
        arr_f[v4 + 18] = f4;
        arr_f[v4 + 19] = f3;
        this.idx = v4 + 20;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float[] arr_f, int v, int v1) {
        int v2;
        if(!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        if(texture0 == this.lastTexture) {
            v2 = this.vertices.length - this.idx;
            if(v2 == 0) {
                this.flush();
                v2 = this.vertices.length;
            }
        }
        else {
            this.switchTexture(texture0);
            v2 = this.vertices.length;
        }
        int v3 = Math.min(v2, v1);
        System.arraycopy(arr_f, v, this.vertices, this.idx, v3);
        this.idx += v3;
        for(int v4 = v1 - v3; v4 > 0; v4 -= v3) {
            v += v3;
            this.flush();
            v3 = Math.min(this.vertices.length, v4);
            System.arraycopy(arr_f, v, this.vertices, 0, v3);
            this.idx += v3;
        }
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion0, float f, float f1) {
        this.draw(textureRegion0, f, f1, ((float)textureRegion0.getRegionWidth()), ((float)textureRegion0.getRegionHeight()));
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion0, float f, float f1, float f2, float f3) {
        if(!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] arr_f = this.vertices;
        Texture texture0 = textureRegion0.texture;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.idx == arr_f.length) {
            this.flush();
        }
        float f4 = f2 + f;
        float f5 = f3 + f1;
        float f6 = textureRegion0.u;
        float f7 = textureRegion0.v2;
        float f8 = textureRegion0.u2;
        float f9 = textureRegion0.v;
        float f10 = this.colorPacked;
        int v = this.idx;
        arr_f[v] = f;
        arr_f[v + 1] = f1;
        arr_f[v + 2] = f10;
        arr_f[v + 3] = f6;
        arr_f[v + 4] = f7;
        arr_f[v + 5] = f;
        arr_f[v + 6] = f5;
        arr_f[v + 7] = f10;
        arr_f[v + 8] = f6;
        arr_f[v + 9] = f9;
        arr_f[v + 10] = f4;
        arr_f[v + 11] = f5;
        arr_f[v + 12] = f10;
        arr_f[v + 13] = f8;
        arr_f[v + 14] = f9;
        arr_f[v + 15] = f4;
        arr_f[v + 16] = f1;
        arr_f[v + 17] = f10;
        arr_f[v + 18] = f8;
        arr_f[v + 19] = f7;
        this.idx = v + 20;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f27;
        float f26;
        float f24;
        float f21;
        float f18;
        if(!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] arr_f = this.vertices;
        Texture texture0 = textureRegion0.texture;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.idx == arr_f.length) {
            this.flush();
        }
        float f9 = f + f2;
        float f10 = f1 + f3;
        float f11 = -f2;
        float f12 = -f3;
        float f13 = f4 - f2;
        float f14 = f5 - f3;
        if(f6 != 1.0f || f7 != 1.0f) {
            f11 *= f6;
            f12 *= f7;
            f13 *= f6;
            f14 *= f7;
        }
        if(f8 == 0.0f) {
            f24 = f13;
            f27 = f14;
            f21 = f11;
            f18 = f21;
            f26 = f12;
        }
        else {
            float f15 = MathUtils.cosDeg(f8);
            float f16 = MathUtils.sinDeg(f8);
            float f17 = f15 * f11;
            f18 = f17 - f16 * f12;
            float f19 = f11 * f16;
            f12 = f12 * f15 + f19;
            float f20 = f16 * f14;
            f21 = f17 - f20;
            float f22 = f14 * f15;
            float f23 = f19 + f22;
            f24 = f15 * f13 - f20;
            float f25 = f22 + f16 * f13;
            f13 = f18 + (f24 - f21);
            f26 = f25 - (f23 - f12);
            f27 = f25;
            f14 = f23;
        }
        float f28 = textureRegion0.u;
        float f29 = textureRegion0.v2;
        float f30 = textureRegion0.u2;
        float f31 = textureRegion0.v;
        float f32 = this.colorPacked;
        int v = this.idx;
        arr_f[v] = f18 + f9;
        arr_f[v + 1] = f12 + f10;
        arr_f[v + 2] = f32;
        arr_f[v + 3] = f28;
        arr_f[v + 4] = f29;
        arr_f[v + 5] = f21 + f9;
        arr_f[v + 6] = f14 + f10;
        arr_f[v + 7] = f32;
        arr_f[v + 8] = f28;
        arr_f[v + 9] = f31;
        arr_f[v + 10] = f24 + f9;
        arr_f[v + 11] = f27 + f10;
        arr_f[v + 12] = f32;
        arr_f[v + 13] = f30;
        arr_f[v + 14] = f31;
        arr_f[v + 15] = f13 + f9;
        arr_f[v + 16] = f26 + f10;
        arr_f[v + 17] = f32;
        arr_f[v + 18] = f30;
        arr_f[v + 19] = f29;
        this.idx = v + 20;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, boolean z) {
        float f38;
        float f37;
        float f36;
        float f35;
        float f34;
        float f33;
        float f32;
        float f31;
        float f30;
        float f28;
        float f27;
        float f26;
        float f24;
        float f21;
        if(!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] arr_f = this.vertices;
        Texture texture0 = textureRegion0.texture;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.idx == arr_f.length) {
            this.flush();
        }
        float f9 = f + f2;
        float f10 = f1 + f3;
        float f11 = -f2;
        float f12 = -f3;
        float f13 = f4 - f2;
        float f14 = f5 - f3;
        if(f6 != 1.0f || f7 != 1.0f) {
            f11 *= f6;
            f12 *= f7;
            f13 *= f6;
            f14 *= f7;
        }
        if(f8 == 0.0f) {
            f24 = f13;
            f26 = f24;
            f27 = f14;
            f21 = f11;
            f28 = f12;
        }
        else {
            float f15 = MathUtils.cosDeg(f8);
            float f16 = MathUtils.sinDeg(f8);
            float f17 = f15 * f11;
            float f18 = f17 - f16 * f12;
            float f19 = f11 * f16;
            f12 = f12 * f15 + f19;
            float f20 = f16 * f14;
            f21 = f17 - f20;
            float f22 = f14 * f15;
            float f23 = f19 + f22;
            f24 = f15 * f13 - f20;
            float f25 = f22 + f16 * f13;
            f26 = f24 - f21 + f18;
            f27 = f25;
            f14 = f23;
            f11 = f18;
            f28 = f25 - (f23 - f12);
        }
        float f29 = f28 + f10;
        if(z) {
            f30 = textureRegion0.v2;
            f31 = textureRegion0.u;
            f32 = textureRegion0.v2;
            f33 = textureRegion0.u;
            f34 = textureRegion0.v;
            f35 = textureRegion0.u2;
            f36 = textureRegion0.u2;
            f37 = textureRegion0.v;
        }
        else {
            f30 = textureRegion0.v;
            f31 = textureRegion0.u2;
            f32 = textureRegion0.v;
            f33 = textureRegion0.u2;
            f34 = textureRegion0.v2;
            f35 = textureRegion0.u;
            f36 = textureRegion0.u;
            f37 = textureRegion0.v2;
        }
        f38 = f29;
        float f39 = this.colorPacked;
        int v = this.idx;
        arr_f[v] = f11 + f9;
        arr_f[v + 1] = f12 + f10;
        arr_f[v + 2] = f39;
        arr_f[v + 3] = f36;
        arr_f[v + 4] = f30;
        arr_f[v + 5] = f21 + f9;
        arr_f[v + 6] = f14 + f10;
        arr_f[v + 7] = f39;
        arr_f[v + 8] = f31;
        arr_f[v + 9] = f32;
        arr_f[v + 10] = f24 + f9;
        arr_f[v + 11] = f27 + f10;
        arr_f[v + 12] = f39;
        arr_f[v + 13] = f33;
        arr_f[v + 14] = f37;
        arr_f[v + 15] = f26 + f9;
        arr_f[v + 16] = f38;
        arr_f[v + 17] = f39;
        arr_f[v + 18] = f35;
        arr_f[v + 19] = f34;
        this.idx = v + 20;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion0, float f, float f1, Affine2 affine20) {
        if(!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] arr_f = this.vertices;
        Texture texture0 = textureRegion0.texture;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.idx == arr_f.length) {
            this.flush();
        }
        float f2 = affine20.m12;
        float f3 = affine20.m01 * f1 + affine20.m02;
        float f4 = affine20.m11 * f1 + affine20.m12;
        float f5 = affine20.m00 * f + affine20.m01 * f1 + affine20.m02;
        float f6 = affine20.m10 * f + affine20.m11 * f1 + affine20.m12;
        float f7 = affine20.m00 * f + affine20.m02;
        float f8 = affine20.m10 * f + affine20.m12;
        float f9 = textureRegion0.u;
        float f10 = textureRegion0.v2;
        float f11 = textureRegion0.u2;
        float f12 = textureRegion0.v;
        float f13 = this.colorPacked;
        int v = this.idx;
        arr_f[v] = affine20.m02;
        arr_f[v + 1] = f2;
        arr_f[v + 2] = f13;
        arr_f[v + 3] = f9;
        arr_f[v + 4] = f10;
        arr_f[v + 5] = f3;
        arr_f[v + 6] = f4;
        arr_f[v + 7] = f13;
        arr_f[v + 8] = f9;
        arr_f[v + 9] = f12;
        arr_f[v + 10] = f5;
        arr_f[v + 11] = f6;
        arr_f[v + 12] = f13;
        arr_f[v + 13] = f11;
        arr_f[v + 14] = f12;
        arr_f[v + 15] = f7;
        arr_f[v + 16] = f8;
        arr_f[v + 17] = f13;
        arr_f[v + 18] = f11;
        arr_f[v + 19] = f10;
        this.idx = v + 20;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void enableBlending() {
        if(!this.blendingDisabled) {
            return;
        }
        this.flush();
        this.blendingDisabled = false;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void end() {
        if(!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before end.");
        }
        if(this.idx > 0) {
            this.flush();
        }
        this.lastTexture = null;
        this.drawing = false;
        GL20 gL200 = Gdx.gl;
        gL200.glDepthMask(true);
        if(this.isBlendingEnabled()) {
            gL200.glDisable(3042);
        }
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void flush() {
        int v = this.idx;
        if(v == 0) {
            return;
        }
        ++this.renderCalls;
        ++this.totalRenderCalls;
        if(v / 20 > this.maxSpritesInBatch) {
            this.maxSpritesInBatch = v / 20;
        }
        int v1 = v / 20 * 6;
        this.lastTexture.bind();
        Mesh mesh0 = this.mesh;
        mesh0.setVertices(this.vertices, 0, this.idx);
        mesh0.getIndicesBuffer().position(0);
        mesh0.getIndicesBuffer().limit(v1);
        if(this.blendingDisabled) {
            Gdx.gl.glDisable(3042);
        }
        else {
            Gdx.gl.glEnable(3042);
            if(this.blendSrcFunc != -1) {
                Gdx.gl.glBlendFuncSeparate(this.blendSrcFunc, this.blendDstFunc, this.blendSrcFuncAlpha, this.blendDstFuncAlpha);
            }
        }
        mesh0.render((this.customShader == null ? this.shader : this.customShader), 4, 0, v1);
        this.idx = 0;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public int getBlendDstFunc() {
        return this.blendDstFunc;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public int getBlendDstFuncAlpha() {
        return this.blendDstFuncAlpha;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public int getBlendSrcFunc() {
        return this.blendSrcFunc;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public int getBlendSrcFuncAlpha() {
        return this.blendSrcFuncAlpha;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public Color getColor() {
        return this.color;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public float getPackedColor() {
        return this.colorPacked;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public Matrix4 getProjectionMatrix() {
        return this.projectionMatrix;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public ShaderProgram getShader() {
        return this.customShader == null ? this.shader : this.customShader;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public Matrix4 getTransformMatrix() {
        return this.transformMatrix;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public boolean isBlendingEnabled() {
        return !this.blendingDisabled;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public boolean isDrawing() {
        return this.drawing;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void setBlendFunction(int v, int v1) {
        this.setBlendFunctionSeparate(v, v1, v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void setBlendFunctionSeparate(int v, int v1, int v2, int v3) {
        if(this.blendSrcFunc == v && this.blendDstFunc == v1 && this.blendSrcFuncAlpha == v2 && this.blendDstFuncAlpha == v3) {
            return;
        }
        this.flush();
        this.blendSrcFunc = v;
        this.blendDstFunc = v1;
        this.blendSrcFuncAlpha = v2;
        this.blendDstFuncAlpha = v3;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void setColor(float f, float f1, float f2, float f3) {
        this.color.set(f, f1, f2, f3);
        this.colorPacked = this.color.toFloatBits();
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void setColor(Color color0) {
        this.color.set(color0);
        this.colorPacked = color0.toFloatBits();
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void setPackedColor(float f) {
        Color.abgr8888ToColor(this.color, f);
        this.colorPacked = f;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void setProjectionMatrix(Matrix4 matrix40) {
        if(this.drawing) {
            this.flush();
        }
        this.projectionMatrix.set(matrix40);
        if(this.drawing) {
            this.setupMatrices();
        }
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void setShader(ShaderProgram shaderProgram0) {
        if(this.drawing) {
            this.flush();
        }
        this.customShader = shaderProgram0;
        if(this.drawing) {
            ShaderProgram shaderProgram1 = this.customShader;
            if(shaderProgram1 == null) {
                this.shader.bind();
            }
            else {
                shaderProgram1.bind();
            }
            this.setupMatrices();
        }
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void setTransformMatrix(Matrix4 matrix40) {
        if(this.drawing) {
            this.flush();
        }
        this.transformMatrix.set(matrix40);
        if(this.drawing) {
            this.setupMatrices();
        }
    }

    protected void setupMatrices() {
        this.combinedMatrix.set(this.projectionMatrix).mul(this.transformMatrix);
        ShaderProgram shaderProgram0 = this.customShader;
        if(shaderProgram0 != null) {
            shaderProgram0.setUniformMatrix("u_projTrans", this.combinedMatrix);
            this.customShader.setUniformi("u_texture", 0);
            return;
        }
        this.shader.setUniformMatrix("u_projTrans", this.combinedMatrix);
        this.shader.setUniformi("u_texture", 0);
    }

    protected void switchTexture(Texture texture0) {
        this.flush();
        this.lastTexture = texture0;
        this.invTexWidth = 1.0f / ((float)texture0.getWidth());
        this.invTexHeight = 1.0f / ((float)texture0.getHeight());
    }
}

