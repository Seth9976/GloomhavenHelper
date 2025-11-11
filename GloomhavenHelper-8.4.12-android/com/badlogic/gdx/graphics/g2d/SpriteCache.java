package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntArray;
import java.nio.FloatBuffer;

public class SpriteCache implements Disposable {
    static class Cache {
        int[] counts;
        final int id;
        int maxCount;
        final int offset;
        int textureCount;
        Texture[] textures;

        public Cache(int v, int v1) {
            this.id = v;
            this.offset = v1;
        }
    }

    private Array caches;
    private final Color color;
    private float colorPacked;
    private final Matrix4 combinedMatrix;
    private final IntArray counts;
    private Cache currentCache;
    private ShaderProgram customShader;
    private boolean drawing;
    private final Mesh mesh;
    private final Matrix4 projectionMatrix;
    public int renderCalls;
    private final ShaderProgram shader;
    private static final float[] tempVertices;
    private final Array textures;
    public int totalRenderCalls;
    private final Matrix4 transformMatrix;

    static {
        SpriteCache.tempVertices = new float[30];
    }

    public SpriteCache() {
        this(1000, false);
    }

    public SpriteCache(int v, ShaderProgram shaderProgram0, boolean z) {
        this.transformMatrix = new Matrix4();
        this.projectionMatrix = new Matrix4();
        this.caches = new Array();
        this.combinedMatrix = new Matrix4();
        this.textures = new Array(8);
        this.counts = new IntArray(8);
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.colorPacked = Color.WHITE_FLOAT_BITS;
        this.customShader = null;
        int v1 = 0;
        this.renderCalls = 0;
        this.totalRenderCalls = 0;
        this.shader = shaderProgram0;
        if(z && v > 0x1FFF) {
            throw new IllegalArgumentException("Can\'t have more than 8191 sprites per batch: " + v);
        }
        this.mesh = new Mesh(true, (z ? 4 : 6) * v, (z ? v * 6 : 0), new VertexAttribute[]{new VertexAttribute(1, 2, "a_position"), new VertexAttribute(4, 4, "a_color"), new VertexAttribute(16, 2, "a_texCoord0")});
        this.mesh.setAutoBind(false);
        if(z) {
            short[] arr_v = new short[v * 6];
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
        }
        float f = (float)Gdx.graphics.getWidth();
        float f1 = (float)Gdx.graphics.getHeight();
        this.projectionMatrix.setToOrtho2D(0.0f, 0.0f, f, f1);
    }

    public SpriteCache(int v, boolean z) {
        this(v, SpriteCache.createDefaultShader(), z);
    }

    public void add(Texture texture0, float f, float f1) {
        float f2 = ((float)texture0.getWidth()) + f;
        float f3 = ((float)texture0.getHeight()) + f1;
        SpriteCache.tempVertices[0] = f;
        SpriteCache.tempVertices[1] = f1;
        float f4 = this.colorPacked;
        SpriteCache.tempVertices[2] = f4;
        SpriteCache.tempVertices[3] = 0.0f;
        SpriteCache.tempVertices[4] = 1.0f;
        SpriteCache.tempVertices[5] = f;
        SpriteCache.tempVertices[6] = f3;
        SpriteCache.tempVertices[7] = f4;
        SpriteCache.tempVertices[8] = 0.0f;
        SpriteCache.tempVertices[9] = 0.0f;
        SpriteCache.tempVertices[10] = f2;
        SpriteCache.tempVertices[11] = f3;
        SpriteCache.tempVertices[12] = f4;
        SpriteCache.tempVertices[13] = 1.0f;
        SpriteCache.tempVertices[14] = 0.0f;
        if(this.mesh.getNumIndices() > 0) {
            SpriteCache.tempVertices[15] = f2;
            SpriteCache.tempVertices[16] = f1;
            SpriteCache.tempVertices[17] = this.colorPacked;
            SpriteCache.tempVertices[18] = 1.0f;
            SpriteCache.tempVertices[19] = 1.0f;
            this.add(texture0, SpriteCache.tempVertices, 0, 20);
            return;
        }
        SpriteCache.tempVertices[15] = f2;
        SpriteCache.tempVertices[16] = f3;
        float f5 = this.colorPacked;
        SpriteCache.tempVertices[17] = f5;
        SpriteCache.tempVertices[18] = 1.0f;
        SpriteCache.tempVertices[19] = 0.0f;
        SpriteCache.tempVertices[20] = f2;
        SpriteCache.tempVertices[21] = f1;
        SpriteCache.tempVertices[22] = f5;
        SpriteCache.tempVertices[23] = 1.0f;
        SpriteCache.tempVertices[24] = 1.0f;
        SpriteCache.tempVertices[25] = f;
        SpriteCache.tempVertices[26] = f1;
        SpriteCache.tempVertices[27] = f5;
        SpriteCache.tempVertices[28] = 0.0f;
        SpriteCache.tempVertices[29] = 1.0f;
        this.add(texture0, SpriteCache.tempVertices, 0, 30);
    }

    public void add(Texture texture0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int v, int v1, int v2, int v3, boolean z, boolean z1) {
        float f28;
        float f27;
        float f26;
        float f21;
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
        float f29 = f11 + f9;
        float f30 = f12 + f10;
        float f31 = f13 + f9;
        float f32 = f27 + f10;
        float f33 = f26 + f9;
        float f34 = f28 + f10;
        float f35 = 1.0f / ((float)texture0.getWidth());
        float f36 = 1.0f / ((float)texture0.getHeight());
        float f37 = ((float)v) * f35;
        float f38 = ((float)(v1 + v3)) * f36;
        float f39 = ((float)(v + v2)) * f35;
        float f40 = ((float)v1) * f36;
        if(!z) {
            float f41 = f37;
            f37 = f39;
            f39 = f41;
        }
        if(z1) {
            float f42 = f38;
            f38 = f40;
            f40 = f42;
        }
        SpriteCache.tempVertices[0] = f29;
        SpriteCache.tempVertices[1] = f30;
        float f43 = this.colorPacked;
        SpriteCache.tempVertices[2] = f43;
        SpriteCache.tempVertices[3] = f39;
        SpriteCache.tempVertices[4] = f38;
        SpriteCache.tempVertices[5] = f21 + f9;
        SpriteCache.tempVertices[6] = f14 + f10;
        SpriteCache.tempVertices[7] = f43;
        SpriteCache.tempVertices[8] = f39;
        SpriteCache.tempVertices[9] = f40;
        SpriteCache.tempVertices[10] = f31;
        SpriteCache.tempVertices[11] = f32;
        SpriteCache.tempVertices[12] = f43;
        SpriteCache.tempVertices[13] = f37;
        SpriteCache.tempVertices[14] = f40;
        if(this.mesh.getNumIndices() > 0) {
            SpriteCache.tempVertices[15] = f33;
            SpriteCache.tempVertices[16] = f34;
            SpriteCache.tempVertices[17] = this.colorPacked;
            SpriteCache.tempVertices[18] = f37;
            SpriteCache.tempVertices[19] = f38;
            this.add(texture0, SpriteCache.tempVertices, 0, 20);
            return;
        }
        SpriteCache.tempVertices[15] = f31;
        SpriteCache.tempVertices[16] = f32;
        float f44 = this.colorPacked;
        SpriteCache.tempVertices[17] = f44;
        SpriteCache.tempVertices[18] = f37;
        SpriteCache.tempVertices[19] = f40;
        SpriteCache.tempVertices[20] = f33;
        SpriteCache.tempVertices[21] = f34;
        SpriteCache.tempVertices[22] = f44;
        SpriteCache.tempVertices[23] = f37;
        SpriteCache.tempVertices[24] = f38;
        SpriteCache.tempVertices[25] = f29;
        SpriteCache.tempVertices[26] = f30;
        SpriteCache.tempVertices[27] = f44;
        SpriteCache.tempVertices[28] = f39;
        SpriteCache.tempVertices[29] = f38;
        this.add(texture0, SpriteCache.tempVertices, 0, 30);
    }

    public void add(Texture texture0, float f, float f1, float f2, float f3, int v, int v1, int v2, int v3, boolean z, boolean z1) {
        float f4 = 1.0f / ((float)texture0.getWidth());
        float f5 = 1.0f / ((float)texture0.getHeight());
        float f6 = ((float)v) * f4;
        float f7 = ((float)(v1 + v3)) * f5;
        float f8 = ((float)(v + v2)) * f4;
        float f9 = ((float)v1) * f5;
        float f10 = f + f2;
        float f11 = f1 + f3;
        if(z) {
            float f12 = f6;
            f6 = f8;
            f8 = f12;
        }
        if(z1) {
            float f13 = f7;
            f7 = f9;
            f9 = f13;
        }
        SpriteCache.tempVertices[0] = f;
        SpriteCache.tempVertices[1] = f1;
        float f14 = this.colorPacked;
        SpriteCache.tempVertices[2] = f14;
        SpriteCache.tempVertices[3] = f6;
        SpriteCache.tempVertices[4] = f7;
        SpriteCache.tempVertices[5] = f;
        SpriteCache.tempVertices[6] = f11;
        SpriteCache.tempVertices[7] = f14;
        SpriteCache.tempVertices[8] = f6;
        SpriteCache.tempVertices[9] = f9;
        SpriteCache.tempVertices[10] = f10;
        SpriteCache.tempVertices[11] = f11;
        SpriteCache.tempVertices[12] = f14;
        SpriteCache.tempVertices[13] = f8;
        SpriteCache.tempVertices[14] = f9;
        if(this.mesh.getNumIndices() > 0) {
            SpriteCache.tempVertices[15] = f10;
            SpriteCache.tempVertices[16] = f1;
            SpriteCache.tempVertices[17] = this.colorPacked;
            SpriteCache.tempVertices[18] = f8;
            SpriteCache.tempVertices[19] = f7;
            this.add(texture0, SpriteCache.tempVertices, 0, 20);
            return;
        }
        SpriteCache.tempVertices[15] = f10;
        SpriteCache.tempVertices[16] = f11;
        float f15 = this.colorPacked;
        SpriteCache.tempVertices[17] = f15;
        SpriteCache.tempVertices[18] = f8;
        SpriteCache.tempVertices[19] = f9;
        SpriteCache.tempVertices[20] = f10;
        SpriteCache.tempVertices[21] = f1;
        SpriteCache.tempVertices[22] = f15;
        SpriteCache.tempVertices[23] = f8;
        SpriteCache.tempVertices[24] = f7;
        SpriteCache.tempVertices[25] = f;
        SpriteCache.tempVertices[26] = f1;
        SpriteCache.tempVertices[27] = f15;
        SpriteCache.tempVertices[28] = f6;
        SpriteCache.tempVertices[29] = f7;
        this.add(texture0, SpriteCache.tempVertices, 0, 30);
    }

    public void add(Texture texture0, float f, float f1, int v, int v1, float f2, float f3, float f4, float f5, float f6) {
        float f7 = ((float)v) + f;
        float f8 = ((float)v1) + f1;
        SpriteCache.tempVertices[0] = f;
        SpriteCache.tempVertices[1] = f1;
        SpriteCache.tempVertices[2] = f6;
        SpriteCache.tempVertices[3] = f2;
        SpriteCache.tempVertices[4] = f3;
        SpriteCache.tempVertices[5] = f;
        SpriteCache.tempVertices[6] = f8;
        SpriteCache.tempVertices[7] = f6;
        SpriteCache.tempVertices[8] = f2;
        SpriteCache.tempVertices[9] = f5;
        SpriteCache.tempVertices[10] = f7;
        SpriteCache.tempVertices[11] = f8;
        SpriteCache.tempVertices[12] = f6;
        SpriteCache.tempVertices[13] = f4;
        SpriteCache.tempVertices[14] = f5;
        if(this.mesh.getNumIndices() > 0) {
            SpriteCache.tempVertices[15] = f7;
            SpriteCache.tempVertices[16] = f1;
            SpriteCache.tempVertices[17] = f6;
            SpriteCache.tempVertices[18] = f4;
            SpriteCache.tempVertices[19] = f3;
            this.add(texture0, SpriteCache.tempVertices, 0, 20);
            return;
        }
        SpriteCache.tempVertices[15] = f7;
        SpriteCache.tempVertices[16] = f8;
        SpriteCache.tempVertices[17] = f6;
        SpriteCache.tempVertices[18] = f4;
        SpriteCache.tempVertices[19] = f5;
        SpriteCache.tempVertices[20] = f7;
        SpriteCache.tempVertices[21] = f1;
        SpriteCache.tempVertices[22] = f6;
        SpriteCache.tempVertices[23] = f4;
        SpriteCache.tempVertices[24] = f3;
        SpriteCache.tempVertices[25] = f;
        SpriteCache.tempVertices[26] = f1;
        SpriteCache.tempVertices[27] = f6;
        SpriteCache.tempVertices[28] = f2;
        SpriteCache.tempVertices[29] = f3;
        this.add(texture0, SpriteCache.tempVertices, 0, 30);
    }

    public void add(Texture texture0, float f, float f1, int v, int v1, int v2, int v3) {
        float f2 = 1.0f / ((float)texture0.getWidth());
        float f3 = 1.0f / ((float)texture0.getHeight());
        float f4 = ((float)v) * f2;
        float f5 = ((float)(v1 + v3)) * f3;
        float f6 = ((float)(v + v2)) * f2;
        float f7 = ((float)v1) * f3;
        float f8 = f + ((float)v2);
        float f9 = f1 + ((float)v3);
        SpriteCache.tempVertices[0] = f;
        SpriteCache.tempVertices[1] = f1;
        float f10 = this.colorPacked;
        SpriteCache.tempVertices[2] = f10;
        SpriteCache.tempVertices[3] = f4;
        SpriteCache.tempVertices[4] = f5;
        SpriteCache.tempVertices[5] = f;
        SpriteCache.tempVertices[6] = f9;
        SpriteCache.tempVertices[7] = f10;
        SpriteCache.tempVertices[8] = f4;
        SpriteCache.tempVertices[9] = f7;
        SpriteCache.tempVertices[10] = f8;
        SpriteCache.tempVertices[11] = f9;
        SpriteCache.tempVertices[12] = f10;
        SpriteCache.tempVertices[13] = f6;
        SpriteCache.tempVertices[14] = f7;
        if(this.mesh.getNumIndices() > 0) {
            SpriteCache.tempVertices[15] = f8;
            SpriteCache.tempVertices[16] = f1;
            SpriteCache.tempVertices[17] = this.colorPacked;
            SpriteCache.tempVertices[18] = f6;
            SpriteCache.tempVertices[19] = f5;
            this.add(texture0, SpriteCache.tempVertices, 0, 20);
            return;
        }
        SpriteCache.tempVertices[15] = f8;
        SpriteCache.tempVertices[16] = f9;
        float f11 = this.colorPacked;
        SpriteCache.tempVertices[17] = f11;
        SpriteCache.tempVertices[18] = f6;
        SpriteCache.tempVertices[19] = f7;
        SpriteCache.tempVertices[20] = f8;
        SpriteCache.tempVertices[21] = f1;
        SpriteCache.tempVertices[22] = f11;
        SpriteCache.tempVertices[23] = f6;
        SpriteCache.tempVertices[24] = f5;
        SpriteCache.tempVertices[25] = f;
        SpriteCache.tempVertices[26] = f1;
        SpriteCache.tempVertices[27] = f11;
        SpriteCache.tempVertices[28] = f4;
        SpriteCache.tempVertices[29] = f5;
        this.add(texture0, SpriteCache.tempVertices, 0, 30);
    }

    public void add(Texture texture0, float[] arr_f, int v, int v1) {
        if(this.currentCache == null) {
            throw new IllegalStateException("beginCache must be called before add.");
        }
        int v2 = v1 / ((this.mesh.getNumIndices() <= 0 ? 6 : 4) * 5) * 6;
        int v3 = this.textures.size - 1;
        if(v3 < 0 || this.textures.get(v3) != texture0) {
            this.textures.add(texture0);
            this.counts.add(v2);
        }
        else {
            this.counts.incr(v3, v2);
        }
        this.mesh.getVerticesBuffer().put(arr_f, v, v1);
    }

    public void add(Sprite sprite0) {
        if(this.mesh.getNumIndices() > 0) {
            this.add(sprite0.getTexture(), sprite0.getVertices(), 0, 20);
            return;
        }
        float[] arr_f = sprite0.getVertices();
        System.arraycopy(arr_f, 0, SpriteCache.tempVertices, 0, 15);
        System.arraycopy(arr_f, 10, SpriteCache.tempVertices, 15, 5);
        System.arraycopy(arr_f, 15, SpriteCache.tempVertices, 20, 5);
        System.arraycopy(arr_f, 0, SpriteCache.tempVertices, 25, 5);
        this.add(sprite0.getTexture(), SpriteCache.tempVertices, 0, 30);
    }

    public void add(TextureRegion textureRegion0, float f, float f1) {
        this.add(textureRegion0, f, f1, ((float)textureRegion0.getRegionWidth()), ((float)textureRegion0.getRegionHeight()));
    }

    public void add(TextureRegion textureRegion0, float f, float f1, float f2, float f3) {
        float f4 = f + f2;
        float f5 = f1 + f3;
        float f6 = textureRegion0.u;
        float f7 = textureRegion0.v2;
        float f8 = textureRegion0.u2;
        float f9 = textureRegion0.v;
        SpriteCache.tempVertices[0] = f;
        SpriteCache.tempVertices[1] = f1;
        float f10 = this.colorPacked;
        SpriteCache.tempVertices[2] = f10;
        SpriteCache.tempVertices[3] = f6;
        SpriteCache.tempVertices[4] = f7;
        SpriteCache.tempVertices[5] = f;
        SpriteCache.tempVertices[6] = f5;
        SpriteCache.tempVertices[7] = f10;
        SpriteCache.tempVertices[8] = f6;
        SpriteCache.tempVertices[9] = f9;
        SpriteCache.tempVertices[10] = f4;
        SpriteCache.tempVertices[11] = f5;
        SpriteCache.tempVertices[12] = f10;
        SpriteCache.tempVertices[13] = f8;
        SpriteCache.tempVertices[14] = f9;
        if(this.mesh.getNumIndices() > 0) {
            SpriteCache.tempVertices[15] = f4;
            SpriteCache.tempVertices[16] = f1;
            SpriteCache.tempVertices[17] = this.colorPacked;
            SpriteCache.tempVertices[18] = f8;
            SpriteCache.tempVertices[19] = f7;
            this.add(textureRegion0.texture, SpriteCache.tempVertices, 0, 20);
            return;
        }
        SpriteCache.tempVertices[15] = f4;
        SpriteCache.tempVertices[16] = f5;
        float f11 = this.colorPacked;
        SpriteCache.tempVertices[17] = f11;
        SpriteCache.tempVertices[18] = f8;
        SpriteCache.tempVertices[19] = f9;
        SpriteCache.tempVertices[20] = f4;
        SpriteCache.tempVertices[21] = f1;
        SpriteCache.tempVertices[22] = f11;
        SpriteCache.tempVertices[23] = f8;
        SpriteCache.tempVertices[24] = f7;
        SpriteCache.tempVertices[25] = f;
        SpriteCache.tempVertices[26] = f1;
        SpriteCache.tempVertices[27] = f11;
        SpriteCache.tempVertices[28] = f6;
        SpriteCache.tempVertices[29] = f7;
        this.add(textureRegion0.texture, SpriteCache.tempVertices, 0, 30);
    }

    public void add(TextureRegion textureRegion0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f28;
        float f27;
        float f26;
        float f24;
        float f21;
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
        float f29 = f11 + f9;
        float f30 = f12 + f10;
        float f31 = f24 + f9;
        float f32 = f27 + f10;
        float f33 = f26 + f9;
        float f34 = f28 + f10;
        float f35 = textureRegion0.u;
        float f36 = textureRegion0.v2;
        float f37 = textureRegion0.u2;
        float f38 = textureRegion0.v;
        SpriteCache.tempVertices[0] = f29;
        SpriteCache.tempVertices[1] = f30;
        float f39 = this.colorPacked;
        SpriteCache.tempVertices[2] = f39;
        SpriteCache.tempVertices[3] = f35;
        SpriteCache.tempVertices[4] = f36;
        SpriteCache.tempVertices[5] = f21 + f9;
        SpriteCache.tempVertices[6] = f14 + f10;
        SpriteCache.tempVertices[7] = f39;
        SpriteCache.tempVertices[8] = f35;
        SpriteCache.tempVertices[9] = f38;
        SpriteCache.tempVertices[10] = f31;
        SpriteCache.tempVertices[11] = f32;
        SpriteCache.tempVertices[12] = f39;
        SpriteCache.tempVertices[13] = f37;
        SpriteCache.tempVertices[14] = f38;
        if(this.mesh.getNumIndices() > 0) {
            SpriteCache.tempVertices[15] = f33;
            SpriteCache.tempVertices[16] = f34;
            SpriteCache.tempVertices[17] = this.colorPacked;
            SpriteCache.tempVertices[18] = f37;
            SpriteCache.tempVertices[19] = f36;
            this.add(textureRegion0.texture, SpriteCache.tempVertices, 0, 20);
            return;
        }
        SpriteCache.tempVertices[15] = f31;
        SpriteCache.tempVertices[16] = f32;
        float f40 = this.colorPacked;
        SpriteCache.tempVertices[17] = f40;
        SpriteCache.tempVertices[18] = f37;
        SpriteCache.tempVertices[19] = f38;
        SpriteCache.tempVertices[20] = f33;
        SpriteCache.tempVertices[21] = f34;
        SpriteCache.tempVertices[22] = f40;
        SpriteCache.tempVertices[23] = f37;
        SpriteCache.tempVertices[24] = f36;
        SpriteCache.tempVertices[25] = f29;
        SpriteCache.tempVertices[26] = f30;
        SpriteCache.tempVertices[27] = f40;
        SpriteCache.tempVertices[28] = f35;
        SpriteCache.tempVertices[29] = f36;
        this.add(textureRegion0.texture, SpriteCache.tempVertices, 0, 30);
    }

    public void begin() {
        if(this.drawing) {
            throw new IllegalStateException("end must be called before begin.");
        }
        if(this.currentCache != null) {
            throw new IllegalStateException("endCache must be called before begin");
        }
        this.renderCalls = 0;
        this.combinedMatrix.set(this.projectionMatrix).mul(this.transformMatrix);
        Gdx.gl20.glDepthMask(false);
        ShaderProgram shaderProgram0 = this.customShader;
        if(shaderProgram0 == null) {
            this.shader.bind();
            this.shader.setUniformMatrix("u_projectionViewMatrix", this.combinedMatrix);
            this.shader.setUniformi("u_texture", 0);
            this.mesh.bind(this.shader);
        }
        else {
            shaderProgram0.bind();
            this.customShader.setUniformMatrix("u_proj", this.projectionMatrix);
            this.customShader.setUniformMatrix("u_trans", this.transformMatrix);
            this.customShader.setUniformMatrix("u_projTrans", this.combinedMatrix);
            this.customShader.setUniformi("u_texture", 0);
            this.mesh.bind(this.customShader);
        }
        this.drawing = true;
    }

    public void beginCache() {
        if(this.drawing) {
            throw new IllegalStateException("end must be called before beginCache");
        }
        if(this.currentCache != null) {
            throw new IllegalStateException("endCache must be called before begin.");
        }
        this.mesh.getNumIndices();
        this.currentCache = new Cache(this.caches.size, this.mesh.getVerticesBuffer().limit());
        this.caches.add(this.currentCache);
        this.mesh.getVerticesBuffer().compact();
    }

    public void beginCache(int v) {
        if(this.drawing) {
            throw new IllegalStateException("end must be called before beginCache");
        }
        if(this.currentCache != null) {
            throw new IllegalStateException("endCache must be called before begin.");
        }
        if(v == this.caches.size - 1) {
            Cache spriteCache$Cache0 = (Cache)this.caches.removeIndex(v);
            this.mesh.getVerticesBuffer().limit(spriteCache$Cache0.offset);
            this.beginCache();
            return;
        }
        this.currentCache = (Cache)this.caches.get(v);
        this.mesh.getVerticesBuffer().position(this.currentCache.offset);
    }

    public void clear() {
        this.caches.clear();
        this.mesh.getVerticesBuffer().clear().flip();
    }

    static ShaderProgram createDefaultShader() {
        ShaderProgram shaderProgram0 = new ShaderProgram("attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projectionViewMatrix;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_color.a = v_color.a * (255.0/254.0);\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projectionViewMatrix * a_position;\n}\n", "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nvoid main()\n{\n  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n}");
        if(!shaderProgram0.isCompiled()) {
            throw new IllegalArgumentException("Error compiling shader: " + shaderProgram0.getLog());
        }
        return shaderProgram0;
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.mesh.dispose();
        ShaderProgram shaderProgram0 = this.shader;
        if(shaderProgram0 != null) {
            shaderProgram0.dispose();
        }
    }

    public void draw(int v) {
        if(!this.drawing) {
            throw new IllegalStateException("SpriteCache.begin must be called before draw.");
        }
        Cache spriteCache$Cache0 = (Cache)this.caches.get(v);
        int v1 = this.mesh.getNumIndices() <= 0 ? 6 : 4;
        int v2 = spriteCache$Cache0.offset / (v1 * 5) * 6;
        Texture[] arr_texture = spriteCache$Cache0.textures;
        int[] arr_v = spriteCache$Cache0.counts;
        int v3 = spriteCache$Cache0.textureCount;
        for(int v4 = 0; v4 < v3; ++v4) {
            int v5 = arr_v[v4];
            arr_texture[v4].bind();
            ShaderProgram shaderProgram0 = this.customShader;
            if(shaderProgram0 == null) {
                this.mesh.render(this.shader, 4, v2, v5);
            }
            else {
                this.mesh.render(shaderProgram0, 4, v2, v5);
            }
            v2 += v5;
        }
        this.renderCalls += v3;
        this.totalRenderCalls += v3;
    }

    public void draw(int v, int v1, int v2) {
        int v9;
        if(!this.drawing) {
            throw new IllegalStateException("SpriteCache.begin must be called before draw.");
        }
        Cache spriteCache$Cache0 = (Cache)this.caches.get(v);
        int v3 = this.mesh.getNumIndices() <= 0 ? 6 : 4;
        int v4 = spriteCache$Cache0.offset / (v3 * 5) * 6 + v1 * 6;
        int v5 = v2 * 6;
        Texture[] arr_texture = spriteCache$Cache0.textures;
        int[] arr_v = spriteCache$Cache0.counts;
        int v6 = spriteCache$Cache0.textureCount;
        int v7 = 0;
        while(v7 < v6) {
            arr_texture[v7].bind();
            int v8 = arr_v[v7];
            if(v8 > v5) {
                v9 = v5;
                v7 = v6;
            }
            else {
                v9 = v5 - v8;
                v5 = v8;
            }
            ShaderProgram shaderProgram0 = this.customShader;
            if(shaderProgram0 == null) {
                this.mesh.render(this.shader, 4, v4, v5);
            }
            else {
                this.mesh.render(shaderProgram0, 4, v4, v5);
            }
            v4 += v5;
            ++v7;
            v5 = v9;
        }
        this.renderCalls += spriteCache$Cache0.textureCount;
        this.totalRenderCalls += v6;
    }

    public void end() {
        if(!this.drawing) {
            throw new IllegalStateException("begin must be called before end.");
        }
        this.drawing = false;
        Gdx.gl20.glDepthMask(true);
        ShaderProgram shaderProgram0 = this.customShader;
        if(shaderProgram0 != null) {
            this.mesh.unbind(shaderProgram0);
            return;
        }
        this.mesh.unbind(this.shader);
    }

    public int endCache() {
        Cache spriteCache$Cache0 = this.currentCache;
        if(spriteCache$Cache0 == null) {
            throw new IllegalStateException("beginCache must be called before endCache.");
        }
        int v = this.mesh.getVerticesBuffer().position() - spriteCache$Cache0.offset;
        if(spriteCache$Cache0.textures == null) {
            spriteCache$Cache0.maxCount = v;
            spriteCache$Cache0.textureCount = this.textures.size;
            spriteCache$Cache0.textures = (Texture[])this.textures.toArray(Texture.class);
            spriteCache$Cache0.counts = new int[spriteCache$Cache0.textureCount];
            int v2 = this.counts.size;
            for(int v1 = 0; v1 < v2; ++v1) {
                int[] arr_v = spriteCache$Cache0.counts;
                arr_v[v1] = this.counts.get(v1);
            }
            this.mesh.getVerticesBuffer().flip();
        }
        else if(v <= spriteCache$Cache0.maxCount) {
            spriteCache$Cache0.textureCount = this.textures.size;
            if(spriteCache$Cache0.textures.length < spriteCache$Cache0.textureCount) {
                spriteCache$Cache0.textures = new Texture[spriteCache$Cache0.textureCount];
            }
            int v3 = spriteCache$Cache0.textureCount;
            for(int v4 = 0; v4 < v3; ++v4) {
                Texture[] arr_texture = spriteCache$Cache0.textures;
                arr_texture[v4] = (Texture)this.textures.get(v4);
            }
            if(spriteCache$Cache0.counts.length < spriteCache$Cache0.textureCount) {
                spriteCache$Cache0.counts = new int[spriteCache$Cache0.textureCount];
            }
            int v5 = spriteCache$Cache0.textureCount;
            for(int v6 = 0; v6 < v5; ++v6) {
                int[] arr_v1 = spriteCache$Cache0.counts;
                arr_v1[v6] = this.counts.get(v6);
            }
            FloatBuffer floatBuffer0 = this.mesh.getVerticesBuffer();
            floatBuffer0.position(0);
            Cache spriteCache$Cache1 = (Cache)this.caches.get(this.caches.size - 1);
            floatBuffer0.limit(spriteCache$Cache1.offset + spriteCache$Cache1.maxCount);
        }
        else {
            throw new GdxRuntimeException("If a cache is not the last created, it cannot be redefined with more entries than when it was first created: " + v + " (" + spriteCache$Cache0.maxCount + " max)");
        }
        this.currentCache = null;
        this.textures.clear();
        this.counts.clear();
        return spriteCache$Cache0.id;
    }

    public Color getColor() {
        return this.color;
    }

    public ShaderProgram getCustomShader() {
        return this.customShader;
    }

    public float getPackedColor() {
        return this.colorPacked;
    }

    public Matrix4 getProjectionMatrix() {
        return this.projectionMatrix;
    }

    public Matrix4 getTransformMatrix() {
        return this.transformMatrix;
    }

    public boolean isDrawing() {
        return this.drawing;
    }

    public void setColor(float f, float f1, float f2, float f3) {
        this.color.set(f, f1, f2, f3);
        this.colorPacked = this.color.toFloatBits();
    }

    public void setColor(Color color0) {
        this.color.set(color0);
        this.colorPacked = color0.toFloatBits();
    }

    public void setPackedColor(float f) {
        Color.abgr8888ToColor(this.color, f);
        this.colorPacked = f;
    }

    public void setProjectionMatrix(Matrix4 matrix40) {
        if(this.drawing) {
            throw new IllegalStateException("Can\'t set the matrix within begin/end.");
        }
        this.projectionMatrix.set(matrix40);
    }

    public void setShader(ShaderProgram shaderProgram0) {
        this.customShader = shaderProgram0;
    }

    public void setTransformMatrix(Matrix4 matrix40) {
        if(this.drawing) {
            throw new IllegalStateException("Can\'t set the matrix within begin/end.");
        }
        this.transformMatrix.set(matrix40);
    }
}

