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

public class PolygonSpriteBatch implements PolygonBatch {
    private int blendDstFunc;
    private int blendDstFuncAlpha;
    private int blendSrcFunc;
    private int blendSrcFuncAlpha;
    private boolean blendingDisabled;
    private final Color color;
    float colorPacked;
    private final Matrix4 combinedMatrix;
    private ShaderProgram customShader;
    private boolean drawing;
    private float invTexHeight;
    private float invTexWidth;
    private Texture lastTexture;
    public int maxTrianglesInBatch;
    private Mesh mesh;
    private boolean ownsShader;
    private final Matrix4 projectionMatrix;
    public int renderCalls;
    private final ShaderProgram shader;
    public int totalRenderCalls;
    private final Matrix4 transformMatrix;
    private int triangleIndex;
    private final short[] triangles;
    private int vertexIndex;
    private final float[] vertices;

    public PolygonSpriteBatch() {
        this(2000, null);
    }

    public PolygonSpriteBatch(int v) {
        this(v, v * 2, null);
    }

    public PolygonSpriteBatch(int v, int v1, ShaderProgram shaderProgram0) {
        this.invTexWidth = 0.0f;
        this.invTexHeight = 0.0f;
        this.transformMatrix = new Matrix4();
        this.projectionMatrix = new Matrix4();
        this.combinedMatrix = new Matrix4();
        this.blendSrcFunc = 770;
        this.blendDstFunc = 0x303;
        this.blendSrcFuncAlpha = 770;
        this.blendDstFuncAlpha = 0x303;
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.colorPacked = Color.WHITE_FLOAT_BITS;
        this.renderCalls = 0;
        this.totalRenderCalls = 0;
        this.maxTrianglesInBatch = 0;
        if(v > 0x7FFF) {
            throw new IllegalArgumentException("Can\'t have more than 32767 vertices per batch: " + v);
        }
        this.mesh = new Mesh((Gdx.gl30 == null ? VertexDataType.VertexArray : VertexDataType.VertexBufferObjectWithVAO), false, v, v1 * 3, new VertexAttribute[]{new VertexAttribute(1, 2, "a_position"), new VertexAttribute(4, 4, "a_color"), new VertexAttribute(16, 2, "a_texCoord0")});
        this.vertices = new float[v * 5];
        this.triangles = new short[v1 * 3];
        if(shaderProgram0 == null) {
            this.shader = SpriteBatch.createDefaultShader();
            this.ownsShader = true;
        }
        else {
            this.shader = shaderProgram0;
        }
        float f = (float)Gdx.graphics.getWidth();
        float f1 = (float)Gdx.graphics.getHeight();
        this.projectionMatrix.setToOrtho2D(0.0f, 0.0f, f, f1);
    }

    public PolygonSpriteBatch(int v, ShaderProgram shaderProgram0) {
        this(v, v * 2, shaderProgram0);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void begin() {
        if(this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.end must be called before begin.");
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

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void disableBlending() {
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
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 20 > arr_f.length) {
            this.flush();
        }
        int v = this.vertexIndex;
        int v1 = this.triangleIndex + 1;
        arr_v[this.triangleIndex] = (short)(v / 5);
        arr_v[v1] = (short)(v / 5 + 1);
        short v2 = (short)(v / 5 + 2);
        arr_v[v1 + 1] = v2;
        arr_v[v1 + 2] = v2;
        arr_v[v1 + 3] = (short)(v / 5 + 3);
        arr_v[v1 + 4] = (short)(v / 5);
        this.triangleIndex = v1 + 5;
        float f4 = f2 + f;
        float f5 = f3 + f1;
        float f6 = this.colorPacked;
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
        this.vertexIndex = v + 20;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7) {
        if(!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 20 > arr_f.length) {
            this.flush();
        }
        int v = this.vertexIndex;
        int v1 = this.triangleIndex + 1;
        arr_v[this.triangleIndex] = (short)(v / 5);
        arr_v[v1] = (short)(v / 5 + 1);
        short v2 = (short)(v / 5 + 2);
        arr_v[v1 + 1] = v2;
        arr_v[v1 + 2] = v2;
        arr_v[v1 + 3] = (short)(v / 5 + 3);
        arr_v[v1 + 4] = (short)(v / 5);
        this.triangleIndex = v1 + 5;
        float f8 = f + f2;
        float f9 = f1 + f3;
        float f10 = this.colorPacked;
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
        this.vertexIndex = v + 20;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int v, int v1, int v2, int v3, boolean z, boolean z1) {
        float f28;
        float f27;
        float f26;
        float f21;
        if(!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 20 > arr_f.length) {
            this.flush();
        }
        int v4 = this.vertexIndex / 5;
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
        float f35 = this.colorPacked;
        int v6 = this.vertexIndex + 1;
        arr_f[this.vertexIndex] = f11 + f9;
        arr_f[v6] = f12 + f10;
        arr_f[v6 + 1] = f35;
        arr_f[v6 + 2] = f31;
        arr_f[v6 + 3] = f32;
        arr_f[v6 + 4] = f21 + f9;
        arr_f[v6 + 5] = f14 + f10;
        arr_f[v6 + 6] = f35;
        arr_f[v6 + 7] = f31;
        arr_f[v6 + 8] = f30;
        arr_f[v6 + 9] = f13 + f9;
        arr_f[v6 + 10] = f27 + f10;
        arr_f[v6 + 11] = f35;
        arr_f[v6 + 12] = f29;
        arr_f[v6 + 13] = f30;
        arr_f[v6 + 14] = f26 + f9;
        arr_f[v6 + 15] = f28 + f10;
        arr_f[v6 + 16] = f35;
        arr_f[v6 + 17] = f29;
        arr_f[v6 + 18] = f32;
        this.vertexIndex = v6 + 19;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1, float f2, float f3, int v, int v1, int v2, int v3, boolean z, boolean z1) {
        if(!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 20 > arr_f.length) {
            this.flush();
        }
        int v4 = this.vertexIndex / 5;
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
        float f12 = this.colorPacked;
        int v6 = this.vertexIndex + 1;
        arr_f[this.vertexIndex] = f;
        arr_f[v6] = f1;
        arr_f[v6 + 1] = f12;
        arr_f[v6 + 2] = f6;
        arr_f[v6 + 3] = f5;
        arr_f[v6 + 4] = f;
        arr_f[v6 + 5] = f9;
        arr_f[v6 + 6] = f12;
        arr_f[v6 + 7] = f6;
        arr_f[v6 + 8] = f7;
        arr_f[v6 + 9] = f8;
        arr_f[v6 + 10] = f9;
        arr_f[v6 + 11] = f12;
        arr_f[v6 + 12] = f4;
        arr_f[v6 + 13] = f7;
        arr_f[v6 + 14] = f8;
        arr_f[v6 + 15] = f1;
        arr_f[v6 + 16] = f12;
        arr_f[v6 + 17] = f4;
        arr_f[v6 + 18] = f5;
        this.vertexIndex = v6 + 19;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float f, float f1, int v, int v1, int v2, int v3) {
        if(!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 20 > arr_f.length) {
            this.flush();
        }
        int v4 = this.vertexIndex;
        int v5 = this.triangleIndex + 1;
        arr_v[this.triangleIndex] = (short)(v4 / 5);
        arr_v[v5] = (short)(v4 / 5 + 1);
        short v6 = (short)(v4 / 5 + 2);
        arr_v[v5 + 1] = v6;
        arr_v[v5 + 2] = v6;
        arr_v[v5 + 3] = (short)(v4 / 5 + 3);
        arr_v[v5 + 4] = (short)(v4 / 5);
        this.triangleIndex = v5 + 5;
        float f2 = ((float)v) * this.invTexWidth;
        float f3 = ((float)(v1 + v3)) * this.invTexHeight;
        float f4 = ((float)(v + v2)) * this.invTexWidth;
        float f5 = ((float)v1) * this.invTexHeight;
        float f6 = ((float)v2) + f;
        float f7 = ((float)v3) + f1;
        float f8 = this.colorPacked;
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
        this.vertexIndex = v4 + 20;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture0, float[] arr_f, int v, int v1) {
        int v3;
        if(!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f1 = this.vertices;
        int v2 = v1 / 20 * 6;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
            v3 = Math.min(Math.min(v1, arr_f1.length - arr_f1.length % 20), arr_v.length / 6 * 20);
            v2 = v3 / 20 * 6;
        }
        else if(this.triangleIndex + v2 > arr_v.length || this.vertexIndex + v1 > arr_f1.length) {
            this.flush();
            v3 = Math.min(Math.min(v1, arr_f1.length - arr_f1.length % 20), arr_v.length / 6 * 20);
            v2 = v3 / 20 * 6;
        }
        else {
            v3 = v1;
        }
        int v4 = this.vertexIndex;
        short v5 = (short)(v4 / 5);
        int v6 = this.triangleIndex;
        int v7 = v2 + v6;
        while(v6 < v7) {
            arr_v[v6] = v5;
            arr_v[v6 + 1] = (short)(v5 + 1);
            arr_v[v6 + 2] = (short)(v5 + 2);
            arr_v[v6 + 3] = (short)(v5 + 2);
            arr_v[v6 + 4] = (short)(v5 + 3);
            arr_v[v6 + 5] = v5;
            v6 += 6;
            v5 = (short)(v5 + 4);
        }
        while(true) {
            System.arraycopy(arr_f, v, arr_f1, v4, v3);
            this.vertexIndex = v4 + v3;
            this.triangleIndex = v6;
            v1 -= v3;
            if(v1 == 0) {
                break;
            }
            v += v3;
            this.flush();
            v4 = 0;
            if(v3 > v1) {
                v3 = Math.min(v1, arr_v.length / 6 * 20);
                v6 = v3 / 20 * 6;
            }
        }
    }

    @Override  // com.badlogic.gdx.graphics.g2d.PolygonBatch
    public void draw(Texture texture0, float[] arr_f, int v, int v1, short[] arr_v, int v2, int v3) {
        if(!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
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
            arr_v1[v4] = (short)(arr_v[v2] + v5 / 5);
            ++v2;
            ++v4;
        }
        this.triangleIndex = v4;
        System.arraycopy(arr_f, v, arr_f1, v5, v1);
        this.vertexIndex += v1;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.PolygonBatch
    public void draw(PolygonRegion polygonRegion0, float f, float f1) {
        if(!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        short[] arr_v1 = polygonRegion0.triangles;
        float[] arr_f = polygonRegion0.vertices;
        Texture texture0 = polygonRegion0.region.texture;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + arr_v1.length > arr_v.length || this.vertexIndex + arr_f.length * 5 / 2 > this.vertices.length) {
            this.flush();
        }
        int v = this.vertexIndex;
        int v1 = v / 5;
        int v2 = 0;
        int v3 = this.triangleIndex;
        int v4 = 0;
        while(v4 < arr_v1.length) {
            arr_v[v3] = (short)(arr_v1[v4] + v1);
            ++v4;
            ++v3;
        }
        this.triangleIndex = v3;
        float[] arr_f1 = this.vertices;
        float f2 = this.colorPacked;
        float[] arr_f2 = polygonRegion0.textureCoords;
        while(v2 < arr_f.length) {
            arr_f1[v] = arr_f[v2] + f;
            arr_f1[v + 1] = arr_f[v2 + 1] + f1;
            arr_f1[v + 2] = f2;
            arr_f1[v + 3] = arr_f2[v2];
            arr_f1[v + 4] = arr_f2[v2 + 1];
            v2 += 2;
            v += 5;
        }
        this.vertexIndex = v;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.PolygonBatch
    public void draw(PolygonRegion polygonRegion0, float f, float f1, float f2, float f3) {
        if(!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        short[] arr_v1 = polygonRegion0.triangles;
        float[] arr_f = polygonRegion0.vertices;
        TextureRegion textureRegion0 = polygonRegion0.region;
        Texture texture0 = textureRegion0.texture;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + arr_v1.length > arr_v.length || this.vertexIndex + arr_f.length * 5 / 2 > this.vertices.length) {
            this.flush();
        }
        int v = this.vertexIndex;
        int v1 = v / 5;
        int v2 = 0;
        int v3 = this.triangleIndex;
        int v4 = 0;
        while(v4 < arr_v1.length) {
            arr_v[v3] = (short)(arr_v1[v4] + v1);
            ++v4;
            ++v3;
        }
        this.triangleIndex = v3;
        float[] arr_f1 = this.vertices;
        float f4 = this.colorPacked;
        float[] arr_f2 = polygonRegion0.textureCoords;
        float f5 = f2 / ((float)textureRegion0.regionWidth);
        float f6 = f3 / ((float)textureRegion0.regionHeight);
        while(v2 < arr_f.length) {
            arr_f1[v] = arr_f[v2] * f5 + f;
            arr_f1[v + 1] = arr_f[v2 + 1] * f6 + f1;
            arr_f1[v + 2] = f4;
            arr_f1[v + 3] = arr_f2[v2];
            arr_f1[v + 4] = arr_f2[v2 + 1];
            v2 += 2;
            v += 5;
        }
        this.vertexIndex = v;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.PolygonBatch
    public void draw(PolygonRegion polygonRegion0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if(!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        short[] arr_v1 = polygonRegion0.triangles;
        float[] arr_f = polygonRegion0.vertices;
        TextureRegion textureRegion0 = polygonRegion0.region;
        Texture texture0 = textureRegion0.texture;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + arr_v1.length > arr_v.length || this.vertexIndex + arr_f.length * 5 / 2 > this.vertices.length) {
            this.flush();
        }
        int v = this.vertexIndex;
        int v1 = v / 5;
        int v2 = 0;
        int v3 = this.triangleIndex;
        int v4 = 0;
        while(v4 < arr_v1.length) {
            arr_v[v3] = (short)(arr_v1[v4] + v1);
            ++v4;
            ++v3;
        }
        this.triangleIndex = v3;
        float[] arr_f1 = this.vertices;
        float f9 = this.colorPacked;
        float[] arr_f2 = polygonRegion0.textureCoords;
        float f10 = f4 / ((float)textureRegion0.regionWidth);
        float f11 = f5 / ((float)textureRegion0.regionHeight);
        float f12 = MathUtils.cosDeg(f8);
        float f13 = MathUtils.sinDeg(f8);
        while(v2 < arr_f.length) {
            float f14 = (arr_f[v2] * f10 - f2) * f6;
            float f15 = (arr_f[v2 + 1] * f11 - f3) * f7;
            arr_f1[v] = f12 * f14 - f13 * f15 + (f + f2);
            arr_f1[v + 1] = f14 * f13 + f15 * f12 + (f1 + f3);
            arr_f1[v + 2] = f9;
            arr_f1[v + 3] = arr_f2[v2];
            arr_f1[v + 4] = arr_f2[v2 + 1];
            v2 += 2;
            v += 5;
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
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        Texture texture0 = textureRegion0.texture;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 20 > arr_f.length) {
            this.flush();
        }
        int v = this.vertexIndex / 5;
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
        float f6 = textureRegion0.u;
        float f7 = textureRegion0.v2;
        float f8 = textureRegion0.u2;
        float f9 = textureRegion0.v;
        float f10 = this.colorPacked;
        int v2 = this.vertexIndex + 1;
        arr_f[this.vertexIndex] = f;
        arr_f[v2] = f1;
        arr_f[v2 + 1] = f10;
        arr_f[v2 + 2] = f6;
        arr_f[v2 + 3] = f7;
        arr_f[v2 + 4] = f;
        arr_f[v2 + 5] = f5;
        arr_f[v2 + 6] = f10;
        arr_f[v2 + 7] = f6;
        arr_f[v2 + 8] = f9;
        arr_f[v2 + 9] = f4;
        arr_f[v2 + 10] = f5;
        arr_f[v2 + 11] = f10;
        arr_f[v2 + 12] = f8;
        arr_f[v2 + 13] = f9;
        arr_f[v2 + 14] = f4;
        arr_f[v2 + 15] = f1;
        arr_f[v2 + 16] = f10;
        arr_f[v2 + 17] = f8;
        arr_f[v2 + 18] = f7;
        this.vertexIndex = v2 + 19;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f28;
        float f27;
        float f26;
        float f24;
        float f21;
        if(!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        Texture texture0 = textureRegion0.texture;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 20 > arr_f.length) {
            this.flush();
        }
        int v = this.vertexIndex / 5;
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
        float f29 = textureRegion0.u;
        float f30 = textureRegion0.v2;
        float f31 = textureRegion0.u2;
        float f32 = textureRegion0.v;
        float f33 = this.colorPacked;
        int v2 = this.vertexIndex + 1;
        arr_f[this.vertexIndex] = f11 + f9;
        arr_f[v2] = f12 + f10;
        arr_f[v2 + 1] = f33;
        arr_f[v2 + 2] = f29;
        arr_f[v2 + 3] = f30;
        arr_f[v2 + 4] = f21 + f9;
        arr_f[v2 + 5] = f14 + f10;
        arr_f[v2 + 6] = f33;
        arr_f[v2 + 7] = f29;
        arr_f[v2 + 8] = f32;
        arr_f[v2 + 9] = f24 + f9;
        arr_f[v2 + 10] = f27 + f10;
        arr_f[v2 + 11] = f33;
        arr_f[v2 + 12] = f31;
        arr_f[v2 + 13] = f32;
        arr_f[v2 + 14] = f26 + f9;
        arr_f[v2 + 15] = f28 + f10;
        arr_f[v2 + 16] = f33;
        arr_f[v2 + 17] = f31;
        arr_f[v2 + 18] = f30;
        this.vertexIndex = v2 + 19;
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
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        Texture texture0 = textureRegion0.texture;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 20 > arr_f.length) {
            this.flush();
        }
        int v = this.vertexIndex / 5;
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
        int v2 = this.vertexIndex + 1;
        arr_f[this.vertexIndex] = f11 + f9;
        arr_f[v2] = f12 + f10;
        arr_f[v2 + 1] = f39;
        arr_f[v2 + 2] = f36;
        arr_f[v2 + 3] = f30;
        arr_f[v2 + 4] = f21 + f9;
        arr_f[v2 + 5] = f14 + f10;
        arr_f[v2 + 6] = f39;
        arr_f[v2 + 7] = f31;
        arr_f[v2 + 8] = f32;
        arr_f[v2 + 9] = f24 + f9;
        arr_f[v2 + 10] = f27 + f10;
        arr_f[v2 + 11] = f39;
        arr_f[v2 + 12] = f33;
        arr_f[v2 + 13] = f37;
        arr_f[v2 + 14] = f26 + f9;
        arr_f[v2 + 15] = f38;
        arr_f[v2 + 16] = f39;
        arr_f[v2 + 17] = f35;
        arr_f[v2 + 18] = f34;
        this.vertexIndex = v2 + 19;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion0, float f, float f1, Affine2 affine20) {
        if(!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] arr_v = this.triangles;
        float[] arr_f = this.vertices;
        Texture texture0 = textureRegion0.texture;
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.triangleIndex + 6 > arr_v.length || this.vertexIndex + 20 > arr_f.length) {
            this.flush();
        }
        int v = this.vertexIndex / 5;
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
        float f9 = textureRegion0.u;
        float f10 = textureRegion0.v2;
        float f11 = textureRegion0.u2;
        float f12 = textureRegion0.v;
        float f13 = this.colorPacked;
        int v2 = this.vertexIndex + 1;
        arr_f[this.vertexIndex] = affine20.m02;
        arr_f[v2] = f2;
        arr_f[v2 + 1] = f13;
        arr_f[v2 + 2] = f9;
        arr_f[v2 + 3] = f10;
        arr_f[v2 + 4] = f3;
        arr_f[v2 + 5] = f4;
        arr_f[v2 + 6] = f13;
        arr_f[v2 + 7] = f9;
        arr_f[v2 + 8] = f12;
        arr_f[v2 + 9] = f5;
        arr_f[v2 + 10] = f6;
        arr_f[v2 + 11] = f13;
        arr_f[v2 + 12] = f11;
        arr_f[v2 + 13] = f12;
        arr_f[v2 + 14] = f7;
        arr_f[v2 + 15] = f8;
        arr_f[v2 + 16] = f13;
        arr_f[v2 + 17] = f11;
        arr_f[v2 + 18] = f10;
        this.vertexIndex = v2 + 19;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void enableBlending() {
        this.flush();
        this.blendingDisabled = false;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.Batch
    public void end() {
        if(!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before end.");
        }
        if(this.vertexIndex > 0) {
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
        if(this.vertexIndex == 0) {
            return;
        }
        ++this.renderCalls;
        ++this.totalRenderCalls;
        int v = this.triangleIndex;
        if(v > this.maxTrianglesInBatch) {
            this.maxTrianglesInBatch = v;
        }
        this.lastTexture.bind();
        Mesh mesh0 = this.mesh;
        mesh0.setVertices(this.vertices, 0, this.vertexIndex);
        mesh0.setIndices(this.triangles, 0, v);
        if(this.blendingDisabled) {
            Gdx.gl.glDisable(3042);
        }
        else {
            Gdx.gl.glEnable(3042);
            if(this.blendSrcFunc != -1) {
                Gdx.gl.glBlendFuncSeparate(this.blendSrcFunc, this.blendDstFunc, this.blendSrcFuncAlpha, this.blendDstFuncAlpha);
            }
        }
        mesh0.render((this.customShader == null ? this.shader : this.customShader), 4, 0, v);
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

    private void switchTexture(Texture texture0) {
        this.flush();
        this.lastTexture = texture0;
        this.invTexWidth = 1.0f / ((float)texture0.getWidth());
        this.invTexHeight = 1.0f / ((float)texture0.getHeight());
    }
}

