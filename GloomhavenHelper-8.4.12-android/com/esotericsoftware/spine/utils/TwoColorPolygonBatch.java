package com.esotericsoftware.spine.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh.VertexDataType;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g2d.PolygonBatch;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.NumberUtils;

public class TwoColorPolygonBatch implements PolygonBatch {
    static final int SPRITE_SIZE = 24;
    static final int VERTEX_SIZE = 6;
    private int blendDstFunc;
    private int blendDstFuncAlpha;
    private int blendSrcFunc;
    private int blendSrcFuncAlpha;
    private boolean blendingDisabled;
    private final Matrix4 combinedMatrix;
    private final Color dark;
    private float darkPacked;
    private ShaderProgram defaultShader;
    private boolean drawing;
    private float invTexHeight;
    private float invTexWidth;
    @Null
    private Texture lastTexture;
    private final Color light;
    private float lightPacked;
    private final Mesh mesh;
    private boolean ownsDefaultShader;
    private boolean premultipliedAlpha;
    private final Matrix4 projectionMatrix;
    private ShaderProgram shader;
    public int totalRenderCalls;
    private final Matrix4 transformMatrix;
    private int triangleIndex;
    private final short[] triangles;
    private int vertexIndex;
    private final float[] vertices;

    public TwoColorPolygonBatch() {
        this(2000);
    }

    public TwoColorPolygonBatch(int v) {
        this(v, v << 1, null);
    }

    public TwoColorPolygonBatch(int v, int v1) {
        this(v1, v1, null);
    }

    public TwoColorPolygonBatch(int v, int v1, @Null ShaderProgram shaderProgram0) {
        this.transformMatrix = new Matrix4();
        this.projectionMatrix = new Matrix4();
        this.combinedMatrix = new Matrix4();
        this.invTexWidth = 0.0f;
        this.invTexHeight = 0.0f;
        this.blendSrcFunc = 770;
        this.blendDstFunc = 0x303;
        this.blendSrcFuncAlpha = 770;
        this.blendDstFuncAlpha = 0x303;
        this.light = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.dark = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        this.lightPacked = Color.WHITE.toFloatBits();
        this.darkPacked = Color.BLACK.toFloatBits();
        boolean z = false;
        this.totalRenderCalls = 0;
        if(v > 0x7FFF) {
            throw new IllegalArgumentException("Can\'t have more than 32767 vertices per batch: " + v1);
        }
        this.mesh = new Mesh((Gdx.gl30 == null ? VertexDataType.VertexArray : VertexDataType.VertexBufferObjectWithVAO), false, v, v1 * 3, new VertexAttribute[]{new VertexAttribute(1, 2, "a_position"), new VertexAttribute(4, 4, "a_light"), new VertexAttribute(4, 4, "a_dark"), new VertexAttribute(16, 2, "a_texCoord0")});
        this.vertices = new float[v * 6];
        this.triangles = new short[v1 * 3];
        if(shaderProgram0 == null) {
            z = true;
        }
        this.ownsDefaultShader = z;
        if(this.ownsDefaultShader) {
            shaderProgram0 = TwoColorPolygonBatch.createDefaultShader();
        }
        this.defaultShader = shaderProgram0;
        this.shader = shaderProgram0;
        float f = (float)Gdx.graphics.getWidth();
        float f1 = (float)Gdx.graphics.getHeight();
        this.projectionMatrix.setToOrtho2D(0.0f, 0.0f, f, f1);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void begin() {
        if(this.drawing) {
            throw new IllegalStateException("end must be called before begin.");
        }
        Gdx.gl.glDepthMask(false);
        this.shader.bind();
        this.setupMatrices();
        this.drawing = true;
    }

    protected void bind(Texture texture0) {
        texture0.bind();
    }

    public static ShaderProgram createDefaultShader() {
        ShaderProgram shaderProgram0 = new ShaderProgram("attribute vec4 a_position;\nattribute vec4 a_light;\nattribute vec4 a_dark;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projTrans;\nvarying vec4 v_light;\nvarying vec4 v_dark;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n  v_light = a_light;\n  v_light.a = v_light.a * (255.0/254.0);\n  v_dark = a_dark;\n  v_texCoords = a_texCoord0;\n  gl_Position = u_projTrans * a_position;\n}\n", "#ifdef GL_ES\n#define LOWP lowp\nprecision mediump float;\n#else\n#define LOWP \n#endif\nvarying LOWP vec4 v_light;\nvarying LOWP vec4 v_dark;\nuniform float u_pma;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nvoid main()\n{\n  vec4 texColor = texture2D(u_texture, v_texCoords);\n  gl_FragColor.a = texColor.a * v_light.a;\n  gl_FragColor.rgb = ((texColor.a - 1.0) * u_pma + 1.0 - texColor.rgb) * v_dark.rgb + texColor.rgb * v_light.rgb;\n}");
        if(!shaderProgram0.isCompiled()) {
            throw new IllegalArgumentException("Error compiling shader: " + shaderProgram0.getLog());
        }
        return shaderProgram0;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void disableBlending() {
        this.flush();
        this.blendingDisabled = true;
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.mesh.dispose();
        if(this.ownsDefaultShader) {
            this.defaultShader.dispose();
        }
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1) {
        this.draw(texture0, f, f1, ((float)texture0.getWidth()), ((float)texture0.getHeight()));
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1, float f2, float f3) {
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 24 > arr_f.length) {
            this.flush();
        }
        int v = this.vertexIndex;
        int v1 = this.triangleIndex + 1;
        arr_v[this.triangleIndex] = (short)(v / 6);
        arr_v[v1] = (short)(v / 6 + 1);
        short v2 = (short)(v / 6 + 2);
        arr_v[v1 + 1] = v2;
        arr_v[v1 + 2] = v2;
        arr_v[v1 + 3] = (short)(v / 6 + 3);
        arr_v[v1 + 4] = (short)(v / 6);
        this.triangleIndex = v1 + 5;
        float f4 = f2 + f;
        float f5 = f3 + f1;
        float f6 = this.lightPacked;
        float f7 = this.darkPacked;
        arr_f[v] = f;
        arr_f[v + 1] = f1;
        arr_f[v + 2] = f6;
        arr_f[v + 3] = f7;
        arr_f[v + 4] = 0.0f;
        arr_f[v + 5] = 1.0f;
        arr_f[v + 6] = f;
        arr_f[v + 7] = f5;
        arr_f[v + 8] = f6;
        arr_f[v + 9] = f7;
        arr_f[v + 10] = 0.0f;
        arr_f[v + 11] = 0.0f;
        arr_f[v + 12] = f4;
        arr_f[v + 13] = f5;
        arr_f[v + 14] = f6;
        arr_f[v + 15] = f7;
        arr_f[v + 16] = 1.0f;
        arr_f[v + 17] = 0.0f;
        arr_f[v + 18] = f4;
        arr_f[v + 19] = f1;
        arr_f[v + 20] = f6;
        arr_f[v + 21] = f7;
        arr_f[v + 22] = 1.0f;
        arr_f[v + 23] = 1.0f;
        this.vertexIndex = v + 24;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7) {
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 24 > arr_f.length) {
            this.flush();
        }
        int v = this.vertexIndex;
        int v1 = this.triangleIndex + 1;
        arr_v[this.triangleIndex] = (short)(v / 6);
        arr_v[v1] = (short)(v / 6 + 1);
        short v2 = (short)(v / 6 + 2);
        arr_v[v1 + 1] = v2;
        arr_v[v1 + 2] = v2;
        arr_v[v1 + 3] = (short)(v / 6 + 3);
        arr_v[v1 + 4] = (short)(v / 6);
        this.triangleIndex = v1 + 5;
        float f8 = f + f2;
        float f9 = f1 + f3;
        float f10 = this.lightPacked;
        float f11 = this.darkPacked;
        arr_f[v] = f;
        arr_f[v + 1] = f1;
        arr_f[v + 2] = f10;
        arr_f[v + 3] = f11;
        arr_f[v + 4] = f4;
        arr_f[v + 5] = f5;
        arr_f[v + 6] = f;
        arr_f[v + 7] = f9;
        arr_f[v + 8] = f10;
        arr_f[v + 9] = f11;
        arr_f[v + 10] = f4;
        arr_f[v + 11] = f7;
        arr_f[v + 12] = f8;
        arr_f[v + 13] = f9;
        arr_f[v + 14] = f10;
        arr_f[v + 15] = f11;
        arr_f[v + 16] = f6;
        arr_f[v + 17] = f7;
        arr_f[v + 18] = f8;
        arr_f[v + 19] = f1;
        arr_f[v + 20] = f10;
        arr_f[v + 21] = f11;
        arr_f[v + 22] = f6;
        arr_f[v + 23] = f5;
        this.vertexIndex = v + 24;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int v, int v1, int v2, int v3, boolean z, boolean z1) {
        float f28;
        float f27;
        float f26;
        float f21;
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 24 > arr_f.length) {
            this.flush();
        }
        int v4 = this.vertexIndex / 6;
        int v5 = this.triangleIndex + 1;
        arr_v[this.triangleIndex] = (short)v4;
        arr_v[v5] = (short)(v4 + 1);
        arr_v[v5 + 1] = (short)(v4 + 2);
        arr_v[v5 + 2] = (short)(v4 + 2);
        arr_v[v5 + 3] = (short)(v4 + 3);
        arr_v[v5 + 4] = (short)v4;
        this.triangleIndex = v5 + 5;
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
        float f35 = this.lightPacked;
        float f36 = this.darkPacked;
        int v6 = this.vertexIndex + 1;
        arr_f[this.vertexIndex] = f11 + f9;
        arr_f[v6] = f12 + f10;
        arr_f[v6 + 1] = f35;
        arr_f[v6 + 2] = f36;
        arr_f[v6 + 3] = f31;
        arr_f[v6 + 4] = f32;
        arr_f[v6 + 5] = f21 + f9;
        arr_f[v6 + 6] = f14 + f10;
        arr_f[v6 + 7] = f35;
        arr_f[v6 + 8] = f36;
        arr_f[v6 + 9] = f31;
        arr_f[v6 + 10] = f30;
        arr_f[v6 + 11] = f13 + f9;
        arr_f[v6 + 12] = f27 + f10;
        arr_f[v6 + 13] = f35;
        arr_f[v6 + 14] = f36;
        arr_f[v6 + 15] = f29;
        arr_f[v6 + 16] = f30;
        arr_f[v6 + 17] = f26 + f9;
        arr_f[v6 + 18] = f28 + f10;
        arr_f[v6 + 19] = f35;
        arr_f[v6 + 20] = f36;
        arr_f[v6 + 21] = f29;
        arr_f[v6 + 22] = f32;
        this.vertexIndex = v6 + 23;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1, float f2, float f3, int v, int v1, int v2, int v3, boolean z, boolean z1) {
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 24 > arr_f.length) {
            this.flush();
        }
        int v4 = this.vertexIndex / 6;
        int v5 = this.triangleIndex + 1;
        arr_v[this.triangleIndex] = (short)v4;
        arr_v[v5] = (short)(v4 + 1);
        arr_v[v5 + 1] = (short)(v4 + 2);
        arr_v[v5 + 2] = (short)(v4 + 2);
        arr_v[v5 + 3] = (short)(v4 + 3);
        arr_v[v5 + 4] = (short)v4;
        this.triangleIndex = v5 + 5;
        float f4 = ((float)v) * this.invTexWidth;
        float f5 = ((float)(v1 + v3)) * this.invTexHeight;
        float f6 = ((float)(v + v2)) * this.invTexWidth;
        float f7 = ((float)v1) * this.invTexHeight;
        float f8 = f + f2;
        float f9 = f1 + f3;
        if(!z) {
            float f10 = f4;
            f4 = f6;
            f6 = f10;
        }
        if(z1) {
            float f11 = f5;
            f5 = f7;
            f7 = f11;
        }
        float f12 = this.lightPacked;
        float f13 = this.darkPacked;
        int v6 = this.vertexIndex + 1;
        arr_f[this.vertexIndex] = f;
        arr_f[v6] = f1;
        arr_f[v6 + 1] = f12;
        arr_f[v6 + 2] = f13;
        arr_f[v6 + 3] = f6;
        arr_f[v6 + 4] = f5;
        arr_f[v6 + 5] = f;
        arr_f[v6 + 6] = f9;
        arr_f[v6 + 7] = f12;
        arr_f[v6 + 8] = f13;
        arr_f[v6 + 9] = f6;
        arr_f[v6 + 10] = f7;
        arr_f[v6 + 11] = f8;
        arr_f[v6 + 12] = f9;
        arr_f[v6 + 13] = f12;
        arr_f[v6 + 14] = f13;
        arr_f[v6 + 15] = f4;
        arr_f[v6 + 16] = f7;
        arr_f[v6 + 17] = f8;
        arr_f[v6 + 18] = f1;
        arr_f[v6 + 19] = f12;
        arr_f[v6 + 20] = f13;
        arr_f[v6 + 21] = f4;
        arr_f[v6 + 22] = f5;
        this.vertexIndex = v6 + 23;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1, int v, int v1, int v2, int v3) {
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 24 > arr_f.length) {
            this.flush();
        }
        int v4 = this.vertexIndex;
        int v5 = this.triangleIndex + 1;
        arr_v[this.triangleIndex] = (short)(v4 / 6);
        arr_v[v5] = (short)(v4 / 6 + 1);
        short v6 = (short)(v4 / 6 + 2);
        arr_v[v5 + 1] = v6;
        arr_v[v5 + 2] = v6;
        arr_v[v5 + 3] = (short)(v4 / 6 + 3);
        arr_v[v5 + 4] = (short)(v4 / 6);
        this.triangleIndex = v5 + 5;
        float f2 = ((float)v) * this.invTexWidth;
        float f3 = ((float)(v1 + v3)) * this.invTexHeight;
        float f4 = ((float)(v + v2)) * this.invTexWidth;
        float f5 = ((float)v1) * this.invTexHeight;
        float f6 = ((float)v2) + f;
        float f7 = ((float)v3) + f1;
        float f8 = this.lightPacked;
        float f9 = this.darkPacked;
        arr_f[v4] = f;
        arr_f[v4 + 1] = f1;
        arr_f[v4 + 2] = f8;
        arr_f[v4 + 3] = f9;
        arr_f[v4 + 4] = f2;
        arr_f[v4 + 5] = f3;
        arr_f[v4 + 6] = f;
        arr_f[v4 + 7] = f7;
        arr_f[v4 + 8] = f8;
        arr_f[v4 + 9] = f9;
        arr_f[v4 + 10] = f2;
        arr_f[v4 + 11] = f5;
        arr_f[v4 + 12] = f6;
        arr_f[v4 + 13] = f7;
        arr_f[v4 + 14] = f8;
        arr_f[v4 + 15] = f9;
        arr_f[v4 + 16] = f4;
        arr_f[v4 + 17] = f5;
        arr_f[v4 + 18] = f6;
        arr_f[v4 + 19] = f1;
        arr_f[v4 + 20] = f8;
        arr_f[v4 + 21] = f9;
        arr_f[v4 + 22] = f4;
        arr_f[v4 + 23] = f3;
        this.vertexIndex = v4 + 24;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float[] arr_f, int v, int v1) {
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f1 = this.vertices;
        int v2 = v1 / 20 * 6;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + v2 > arr_v.length || this.vertexIndex + v1 / 5 * 6 > arr_f1.length) {
            this.flush();
        }
        int v3 = this.triangleIndex;
        short v4 = (short)(this.vertexIndex / 6);
        int v5 = v2 + v3;
        while(v3 < v5) {
            arr_v[v3] = v4;
            arr_v[v3 + 1] = (short)(v4 + 1);
            arr_v[v3 + 2] = (short)(v4 + 2);
            arr_v[v3 + 3] = (short)(v4 + 2);
            arr_v[v3 + 4] = (short)(v4 + 3);
            arr_v[v3 + 5] = v4;
            v3 += 6;
            v4 = (short)(v4 + 4);
        }
        this.triangleIndex = v3;
        int v6 = this.vertexIndex;
        int v7 = v1 + v;
        while(v < v7) {
            arr_f1[v6] = arr_f[v];
            arr_f1[v6 + 1] = arr_f[v + 1];
            arr_f1[v6 + 2] = arr_f[v + 2];
            int v8 = v6 + 4;
            arr_f1[v6 + 3] = 0.0f;
            arr_f1[v8] = arr_f[v + 3];
            v6 = v8 + 2;
            arr_f1[v8 + 1] = arr_f[v + 4];
            v += 5;
        }
        this.vertexIndex = v6;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.PolygonBatch
    public void draw(Texture texture0, float[] arr_f, int v, int v1, short[] arr_v, int v2, int v3) {
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v1 = this.triangles;
        float[] arr_f1 = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + v3 > arr_v1.length || this.vertexIndex + v1 / 5 * 6 > arr_f1.length) {
            this.flush();
        }
        int v4 = this.triangleIndex;
        int v5 = this.vertexIndex / 6;
        int v6 = v3 + v2;
        while(v2 < v6) {
            arr_v1[v4] = (short)(arr_v[v2] + v5);
            ++v2;
            ++v4;
        }
        this.triangleIndex = v4;
        int v7 = this.vertexIndex;
        int v8 = v1 + v;
        while(v < v8) {
            arr_f1[v7] = arr_f[v];
            arr_f1[v7 + 1] = arr_f[v + 1];
            arr_f1[v7 + 2] = arr_f[v + 2];
            int v9 = v7 + 4;
            arr_f1[v7 + 3] = 0.0f;
            arr_f1[v9] = arr_f[v + 3];
            v7 = v9 + 2;
            arr_f1[v9 + 1] = arr_f[v + 4];
            v += 5;
        }
        this.vertexIndex = v7;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.PolygonBatch
    public void draw(PolygonRegion polygonRegion0, float f, float f1) {
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        short[] arr_v1 = polygonRegion0.getTriangles();
        float[] arr_f = polygonRegion0.getVertices();
        Texture texture0 = polygonRegion0.getRegion().getTexture();
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + arr_v1.length > arr_v.length || this.vertexIndex + arr_f.length * 6 / 2 > this.vertices.length) {
            this.flush();
        }
        int v = this.vertexIndex;
        int v1 = v / 6;
        int v3 = this.triangleIndex;
        int v4 = 0;
        while(v4 < arr_v1.length) {
            arr_v[v3] = (short)(arr_v1[v4] + v1);
            ++v4;
            ++v3;
        }
        this.triangleIndex = v3;
        float[] arr_f1 = this.vertices;
        float f2 = this.lightPacked;
        float f3 = this.darkPacked;
        float[] arr_f2 = polygonRegion0.getTextureCoords();
        for(int v2 = 0; v2 < arr_f.length; v2 += 2) {
            arr_f1[v] = arr_f[v2] + f;
            arr_f1[v + 1] = arr_f[v2 + 1] + f1;
            arr_f1[v + 2] = f2;
            int v5 = v + 4;
            arr_f1[v + 3] = f3;
            arr_f1[v5] = arr_f2[v2];
            v = v5 + 2;
            arr_f1[v5 + 1] = arr_f2[v2 + 1];
        }
        this.vertexIndex = v;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.PolygonBatch
    public void draw(PolygonRegion polygonRegion0, float f, float f1, float f2, float f3) {
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        short[] arr_v1 = polygonRegion0.getTriangles();
        float[] arr_f = polygonRegion0.getVertices();
        TextureRegion textureRegion0 = polygonRegion0.getRegion();
        Texture texture0 = textureRegion0.getTexture();
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + arr_v1.length > arr_v.length || this.vertexIndex + arr_f.length * 6 / 2 > this.vertices.length) {
            this.flush();
        }
        int v = this.vertexIndex;
        int v1 = v / 6;
        int v3 = this.triangleIndex;
        int v4 = 0;
        while(v4 < arr_v1.length) {
            arr_v[v3] = (short)(arr_v1[v4] + v1);
            ++v4;
            ++v3;
        }
        this.triangleIndex = v3;
        float[] arr_f1 = this.vertices;
        float f4 = this.lightPacked;
        float f5 = this.darkPacked;
        float[] arr_f2 = polygonRegion0.getTextureCoords();
        float f6 = f2 / ((float)textureRegion0.getRegionWidth());
        float f7 = f3 / ((float)textureRegion0.getRegionHeight());
        for(int v2 = 0; v2 < arr_f.length; v2 += 2) {
            arr_f1[v] = arr_f[v2] * f6 + f;
            arr_f1[v + 1] = arr_f[v2 + 1] * f7 + f1;
            arr_f1[v + 2] = f4;
            int v5 = v + 4;
            arr_f1[v + 3] = f5;
            arr_f1[v5] = arr_f2[v2];
            v = v5 + 2;
            arr_f1[v5 + 1] = arr_f2[v2 + 1];
        }
        this.vertexIndex = v;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.PolygonBatch
    public void draw(PolygonRegion polygonRegion0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        short[] arr_v1 = polygonRegion0.getTriangles();
        float[] arr_f = polygonRegion0.getVertices();
        TextureRegion textureRegion0 = polygonRegion0.getRegion();
        Texture texture0 = textureRegion0.getTexture();
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + arr_v1.length > arr_v.length || this.vertexIndex + arr_f.length * 6 / 2 > this.vertices.length) {
            this.flush();
        }
        int v = this.vertexIndex;
        int v1 = v / 6;
        int v3 = this.triangleIndex;
        int v4 = 0;
        while(v4 < arr_v1.length) {
            arr_v[v3] = (short)(arr_v1[v4] + v1);
            ++v4;
            ++v3;
        }
        this.triangleIndex = v3;
        float[] arr_f1 = this.vertices;
        float f9 = this.lightPacked;
        float f10 = this.darkPacked;
        float[] arr_f2 = polygonRegion0.getTextureCoords();
        float f11 = f4 / ((float)textureRegion0.getRegionWidth());
        float f12 = f5 / ((float)textureRegion0.getRegionHeight());
        float f13 = MathUtils.cosDeg(f8);
        float f14 = MathUtils.sinDeg(f8);
        for(int v2 = 0; v2 < arr_f.length; v2 += 2) {
            float f15 = (arr_f[v2] * f11 - f2) * f6;
            float f16 = (arr_f[v2 + 1] * f12 - f3) * f7;
            arr_f1[v] = f13 * f15 - f14 * f16 + (f + f2);
            arr_f1[v + 1] = f15 * f14 + f16 * f13 + (f1 + f3);
            arr_f1[v + 2] = f9;
            int v5 = v + 4;
            arr_f1[v + 3] = f10;
            arr_f1[v5] = arr_f2[v2];
            v = v5 + 2;
            arr_f1[v5 + 1] = arr_f2[v2 + 1];
        }
        this.vertexIndex = v;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion0, float f, float f1) {
        this.draw(textureRegion0, f, f1, ((float)textureRegion0.getRegionWidth()), ((float)textureRegion0.getRegionHeight()));
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion0, float f, float f1, float f2, float f3) {
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        Texture texture0 = textureRegion0.getTexture();
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 24 > arr_f.length) {
            this.flush();
        }
        int v = this.vertexIndex / 6;
        int v1 = this.triangleIndex + 1;
        arr_v[this.triangleIndex] = (short)v;
        arr_v[v1] = (short)(v + 1);
        arr_v[v1 + 1] = (short)(v + 2);
        arr_v[v1 + 2] = (short)(v + 2);
        arr_v[v1 + 3] = (short)(v + 3);
        arr_v[v1 + 4] = (short)v;
        this.triangleIndex = v1 + 5;
        float f4 = f2 + f;
        float f5 = f3 + f1;
        float f6 = textureRegion0.getU();
        float f7 = textureRegion0.getV2();
        float f8 = textureRegion0.getU2();
        float f9 = textureRegion0.getV();
        float f10 = this.lightPacked;
        float f11 = this.darkPacked;
        int v2 = this.vertexIndex + 1;
        arr_f[this.vertexIndex] = f;
        arr_f[v2] = f1;
        arr_f[v2 + 1] = f10;
        arr_f[v2 + 2] = f11;
        arr_f[v2 + 3] = f6;
        arr_f[v2 + 4] = f7;
        arr_f[v2 + 5] = f;
        arr_f[v2 + 6] = f5;
        arr_f[v2 + 7] = f10;
        arr_f[v2 + 8] = f11;
        arr_f[v2 + 9] = f6;
        arr_f[v2 + 10] = f9;
        arr_f[v2 + 11] = f4;
        arr_f[v2 + 12] = f5;
        arr_f[v2 + 13] = f10;
        arr_f[v2 + 14] = f11;
        arr_f[v2 + 15] = f8;
        arr_f[v2 + 16] = f9;
        arr_f[v2 + 17] = f4;
        arr_f[v2 + 18] = f1;
        arr_f[v2 + 19] = f10;
        arr_f[v2 + 20] = f11;
        arr_f[v2 + 21] = f8;
        arr_f[v2 + 22] = f7;
        this.vertexIndex = v2 + 23;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f28;
        float f27;
        float f26;
        float f24;
        float f21;
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        Texture texture0 = textureRegion0.getTexture();
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 24 > arr_f.length) {
            this.flush();
        }
        int v = this.vertexIndex / 6;
        int v1 = this.triangleIndex + 1;
        arr_v[this.triangleIndex] = (short)v;
        arr_v[v1] = (short)(v + 1);
        arr_v[v1 + 1] = (short)(v + 2);
        arr_v[v1 + 2] = (short)(v + 2);
        arr_v[v1 + 3] = (short)(v + 3);
        arr_v[v1 + 4] = (short)v;
        this.triangleIndex = v1 + 5;
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
        float f29 = textureRegion0.getU();
        float f30 = textureRegion0.getV2();
        float f31 = textureRegion0.getU2();
        float f32 = textureRegion0.getV();
        float f33 = this.lightPacked;
        float f34 = this.darkPacked;
        int v2 = this.vertexIndex + 1;
        arr_f[this.vertexIndex] = f11 + f9;
        arr_f[v2] = f12 + f10;
        arr_f[v2 + 1] = f33;
        arr_f[v2 + 2] = f34;
        arr_f[v2 + 3] = f29;
        arr_f[v2 + 4] = f30;
        arr_f[v2 + 5] = f21 + f9;
        arr_f[v2 + 6] = f14 + f10;
        arr_f[v2 + 7] = f33;
        arr_f[v2 + 8] = f34;
        arr_f[v2 + 9] = f29;
        arr_f[v2 + 10] = f32;
        arr_f[v2 + 11] = f24 + f9;
        arr_f[v2 + 12] = f27 + f10;
        arr_f[v2 + 13] = f33;
        arr_f[v2 + 14] = f34;
        arr_f[v2 + 15] = f31;
        arr_f[v2 + 16] = f32;
        arr_f[v2 + 17] = f26 + f9;
        arr_f[v2 + 18] = f28 + f10;
        arr_f[v2 + 19] = f33;
        arr_f[v2 + 20] = f34;
        arr_f[v2 + 21] = f31;
        arr_f[v2 + 22] = f30;
        this.vertexIndex = v2 + 23;
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
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        Texture texture0 = textureRegion0.getTexture();
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 24 > arr_f.length) {
            this.flush();
        }
        int v = this.vertexIndex / 6;
        int v1 = this.triangleIndex + 1;
        arr_v[this.triangleIndex] = (short)v;
        arr_v[v1] = (short)(v + 1);
        arr_v[v1 + 1] = (short)(v + 2);
        arr_v[v1 + 2] = (short)(v + 2);
        arr_v[v1 + 3] = (short)(v + 3);
        arr_v[v1 + 4] = (short)v;
        this.triangleIndex = v1 + 5;
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
            f30 = textureRegion0.getU2();
            f31 = textureRegion0.getV2();
            f32 = textureRegion0.getU();
            f33 = textureRegion0.getV2();
            f34 = textureRegion0.getU();
            f35 = textureRegion0.getV();
            f36 = textureRegion0.getU2();
            f37 = textureRegion0.getV();
        }
        else {
            f30 = textureRegion0.getU();
            f31 = textureRegion0.getV();
            f32 = textureRegion0.getU2();
            f33 = textureRegion0.getV();
            f34 = textureRegion0.getU2();
            f35 = textureRegion0.getV2();
            f36 = textureRegion0.getU();
            f37 = textureRegion0.getV2();
        }
        f38 = f29;
        float f39 = this.lightPacked;
        float f40 = this.darkPacked;
        int v2 = this.vertexIndex + 1;
        arr_f[this.vertexIndex] = f11 + f9;
        arr_f[v2] = f12 + f10;
        arr_f[v2 + 1] = f39;
        arr_f[v2 + 2] = f40;
        arr_f[v2 + 3] = f30;
        arr_f[v2 + 4] = f31;
        arr_f[v2 + 5] = f21 + f9;
        arr_f[v2 + 6] = f14 + f10;
        arr_f[v2 + 7] = f39;
        arr_f[v2 + 8] = f40;
        arr_f[v2 + 9] = f32;
        arr_f[v2 + 10] = f33;
        arr_f[v2 + 11] = f24 + f9;
        arr_f[v2 + 12] = f27 + f10;
        arr_f[v2 + 13] = f39;
        arr_f[v2 + 14] = f40;
        arr_f[v2 + 15] = f34;
        arr_f[v2 + 16] = f35;
        arr_f[v2 + 17] = f26 + f9;
        arr_f[v2 + 18] = f38;
        arr_f[v2 + 19] = f39;
        arr_f[v2 + 20] = f40;
        arr_f[v2 + 21] = f36;
        arr_f[v2 + 22] = f37;
        this.vertexIndex = v2 + 23;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion0, float f, float f1, Affine2 affine20) {
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        Texture texture0 = textureRegion0.getTexture();
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 24 > arr_f.length) {
            this.flush();
        }
        int v = this.vertexIndex / 6;
        int v1 = this.triangleIndex + 1;
        arr_v[this.triangleIndex] = (short)v;
        arr_v[v1] = (short)(v + 1);
        arr_v[v1 + 1] = (short)(v + 2);
        arr_v[v1 + 2] = (short)(v + 2);
        arr_v[v1 + 3] = (short)(v + 3);
        arr_v[v1 + 4] = (short)v;
        this.triangleIndex = v1 + 5;
        float f2 = affine20.m12;
        float f3 = affine20.m01 * f1 + affine20.m02;
        float f4 = affine20.m11 * f1 + affine20.m12;
        float f5 = affine20.m00 * f + affine20.m01 * f1 + affine20.m02;
        float f6 = affine20.m10 * f + affine20.m11 * f1 + affine20.m12;
        float f7 = affine20.m00 * f + affine20.m02;
        float f8 = affine20.m10 * f + affine20.m12;
        float f9 = textureRegion0.getU();
        float f10 = textureRegion0.getV2();
        float f11 = textureRegion0.getU2();
        float f12 = textureRegion0.getV();
        float f13 = this.lightPacked;
        float f14 = this.darkPacked;
        int v2 = this.vertexIndex + 1;
        arr_f[this.vertexIndex] = affine20.m02;
        arr_f[v2] = f2;
        arr_f[v2 + 1] = f13;
        arr_f[v2 + 2] = f14;
        arr_f[v2 + 3] = f9;
        arr_f[v2 + 4] = f10;
        arr_f[v2 + 5] = f3;
        arr_f[v2 + 6] = f4;
        arr_f[v2 + 7] = f13;
        arr_f[v2 + 8] = f14;
        arr_f[v2 + 9] = f9;
        arr_f[v2 + 10] = f12;
        arr_f[v2 + 11] = f5;
        arr_f[v2 + 12] = f6;
        arr_f[v2 + 13] = f13;
        arr_f[v2 + 14] = f14;
        arr_f[v2 + 15] = f11;
        arr_f[v2 + 16] = f12;
        arr_f[v2 + 17] = f7;
        arr_f[v2 + 18] = f8;
        arr_f[v2 + 19] = f13;
        arr_f[v2 + 20] = f14;
        arr_f[v2 + 21] = f11;
        arr_f[v2 + 22] = f10;
        this.vertexIndex = v2 + 23;
    }

    public void drawTwoColor(Texture texture0, float[] arr_f, int v, int v1) {
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f1 = this.vertices;
        int v2 = v1 / 24 * 6;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + v2 > arr_v.length || this.vertexIndex + v1 > arr_f1.length) {
            this.flush();
        }
        int v3 = this.vertexIndex;
        int v4 = this.triangleIndex;
        short v5 = (short)(v3 / 6);
        int v6 = v2 + v4;
        while(v4 < v6) {
            arr_v[v4] = v5;
            arr_v[v4 + 1] = (short)(v5 + 1);
            arr_v[v4 + 2] = (short)(v5 + 2);
            arr_v[v4 + 3] = (short)(v5 + 2);
            arr_v[v4 + 4] = (short)(v5 + 3);
            arr_v[v4 + 5] = v5;
            v4 += 6;
            v5 = (short)(v5 + 4);
        }
        this.triangleIndex = v4;
        SpineUtils.arraycopy(arr_f, v, arr_f1, v3, v1);
        this.vertexIndex += v1;
    }

    public void drawTwoColor(Texture texture0, float[] arr_f, int v, int v1, short[] arr_v, int v2, int v3) {
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before draw.");
        }
        short[] arr_v1 = this.triangles;
        float[] arr_f1 = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + v3 > arr_v1.length || this.vertexIndex + v1 > arr_f1.length) {
            this.flush();
        }
        int v4 = this.triangleIndex;
        int v5 = this.vertexIndex;
        int v6 = v3 + v2;
        while(v2 < v6) {
            arr_v1[v4] = (short)(arr_v[v2] + v5 / 6);
            ++v2;
            ++v4;
        }
        this.triangleIndex = v4;
        SpineUtils.arraycopy(arr_f, v, arr_f1, v5, v1);
        this.vertexIndex += v1;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void enableBlending() {
        this.flush();
        this.blendingDisabled = false;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void end() {
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before end.");
        }
        if(this.vertexIndex > 0) {
            this.flush();
        }
        Gdx.gl.glDepthMask(true);
        if(!this.blendingDisabled) {
            Gdx.gl.glDisable(3042);
        }
        this.lastTexture = null;
        this.drawing = false;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void flush() {
        if(this.vertexIndex == 0) {
            return;
        }
        ++this.totalRenderCalls;
        this.bind(this.lastTexture);
        Mesh mesh0 = this.mesh;
        mesh0.setVertices(this.vertices, 0, this.vertexIndex);
        mesh0.setIndices(this.triangles, 0, this.triangleIndex);
        if(this.blendingDisabled) {
            Gdx.gl.glDisable(3042);
        }
        else {
            Gdx.gl.glEnable(3042);
            if(this.blendSrcFunc != -1) {
                Gdx.gl.glBlendFuncSeparate(this.blendSrcFunc, this.blendDstFunc, this.blendSrcFuncAlpha, this.blendDstFuncAlpha);
            }
        }
        mesh0.render(this.shader, 4, 0, this.triangleIndex);
        this.vertexIndex = 0;
        this.triangleIndex = 0;
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
        return this.light;
    }

    public Color getDarkColor() {
        return this.dark;
    }

    public ShaderProgram getDefaultShader() {
        return this.defaultShader;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public float getPackedColor() {
        return this.lightPacked;
    }

    public float getPackedDarkColor() {
        return this.darkPacked;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public Matrix4 getProjectionMatrix() {
        return this.projectionMatrix;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public ShaderProgram getShader() {
        return this.shader;
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
        this.light.set(f, f1, f2, f3);
        this.lightPacked = this.light.toFloatBits();
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void setColor(Color color0) {
        this.light.set(color0);
        this.lightPacked = color0.toFloatBits();
    }

    public void setDarkColor(float f, float f1, float f2, float f3) {
        this.dark.set(f, f1, f2, f3);
        this.darkPacked = this.dark.toFloatBits();
    }

    public void setDarkColor(Color color0) {
        this.dark.set(color0);
        this.darkPacked = color0.toFloatBits();
    }

    public void setDefaultShader(ShaderProgram shaderProgram0) {
        boolean z = true;
        boolean z1 = this.shader == this.defaultShader;
        if(z1 && this.drawing) {
            this.flush();
        }
        else {
            z = false;
        }
        if(this.ownsDefaultShader) {
            this.defaultShader.dispose();
        }
        this.defaultShader = shaderProgram0;
        if(z1) {
            this.shader = shaderProgram0;
        }
        if(z) {
            shaderProgram0.bind();
            this.setupMatrices();
        }
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void setPackedColor(float f) {
        Color.rgba8888ToColor(this.light, NumberUtils.floatToIntColor(f));
        this.lightPacked = f;
    }

    public void setPackedDarkColor(float f) {
        Color.rgba8888ToColor(this.dark, NumberUtils.floatToIntColor(f));
        this.darkPacked = f;
    }

    public void setPremultipliedAlpha(boolean z) {
        if(this.premultipliedAlpha == z) {
            return;
        }
        if(this.drawing) {
            this.flush();
        }
        this.premultipliedAlpha = z;
        if(this.drawing) {
            this.setupMatrices();
        }
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
    public void setShader(@Null ShaderProgram shaderProgram0) {
        if(shaderProgram0 == null) {
            shaderProgram0 = this.defaultShader;
        }
        if(this.shader == shaderProgram0) {
            return;
        }
        if(this.drawing) {
            this.flush();
        }
        this.shader = shaderProgram0;
        if(this.drawing) {
            this.shader.bind();
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
        this.shader.setUniformf("u_pma", (this.premultipliedAlpha ? 1.0f : 0.0f));
        this.shader.setUniformMatrix("u_projTrans", this.combinedMatrix);
        this.shader.setUniformi("u_texture", 0);
    }

    private void switchTexture(Texture texture0) {
        this.flush();
        this.lastTexture = texture0;
        this.invTexWidth = 1.0f / ((float)texture0.getWidth());
        this.invTexHeight = 1.0f / ((float)texture0.getHeight());
    }
}

