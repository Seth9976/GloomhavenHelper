package com.badlogic.gdx.graphics.g3d.particles.batches;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.FloatChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParticleShader.AlignMode;
import com.badlogic.gdx.graphics.g3d.particles.ParticleShader;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData.SaveData;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.renderers.BillboardControllerRenderData;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class BillboardParticleBatch extends BufferedParticleBatch {
    public static class Config {
        AlignMode mode;
        boolean useGPU;

        public Config() {
        }

        public Config(boolean z, AlignMode particleShader$AlignMode0) {
            this.useGPU = z;
            this.mode = particleShader$AlignMode0;
        }
    }

    class RenderablePool extends Pool {
        public Renderable newObject() {
            return BillboardParticleBatch.this.allocRenderable();
        }

        @Override  // com.badlogic.gdx.utils.Pool
        public Object newObject() {
            return this.newObject();
        }
    }

    private static final VertexAttributes CPU_ATTRIBUTES = null;
    private static final int CPU_COLOR_OFFSET = 0;
    private static final int CPU_POSITION_OFFSET = 0;
    private static final int CPU_UV_OFFSET = 0;
    private static final int CPU_VERTEX_SIZE = 0;
    private static final VertexAttributes GPU_ATTRIBUTES = null;
    private static final int GPU_COLOR_OFFSET = 0;
    private static final int GPU_POSITION_OFFSET = 0;
    private static final int GPU_SIZE_ROTATION_OFFSET = 0;
    private static final int GPU_UV_OFFSET = 0;
    private static final int GPU_VERTEX_SIZE = 0;
    private static final int MAX_PARTICLES_PER_MESH = 0x1FFF;
    private static final int MAX_VERTICES_PER_MESH = 0x7FFC;
    protected static final Matrix3 TMP_M3 = null;
    protected static final Vector3 TMP_V1 = null;
    protected static final Vector3 TMP_V2 = null;
    protected static final Vector3 TMP_V3 = null;
    protected static final Vector3 TMP_V4 = null;
    protected static final Vector3 TMP_V5 = null;
    protected static final Vector3 TMP_V6 = null;
    protected BlendingAttribute blendingAttribute;
    private VertexAttributes currentAttributes;
    private int currentVertexSize;
    protected DepthTestAttribute depthTestAttribute;
    protected static final int directionUsage = 0x400;
    private short[] indices;
    protected AlignMode mode;
    private RenderablePool renderablePool;
    private Array renderables;
    Shader shader;
    protected static final int sizeAndRotationUsage = 0x200;
    protected Texture texture;
    protected boolean useGPU;
    private float[] vertices;

    static {
        BillboardParticleBatch.TMP_V1 = new Vector3();
        BillboardParticleBatch.TMP_V2 = new Vector3();
        BillboardParticleBatch.TMP_V3 = new Vector3();
        BillboardParticleBatch.TMP_V4 = new Vector3();
        BillboardParticleBatch.TMP_V5 = new Vector3();
        BillboardParticleBatch.TMP_V6 = new Vector3();
        BillboardParticleBatch.TMP_M3 = new Matrix3();
        BillboardParticleBatch.GPU_ATTRIBUTES = new VertexAttributes(new VertexAttribute[]{new VertexAttribute(1, 3, "a_position"), new VertexAttribute(16, 2, "a_texCoord0"), new VertexAttribute(2, 4, "a_color"), new VertexAttribute(0x200, 4, "a_sizeAndRotation")});
        BillboardParticleBatch.CPU_ATTRIBUTES = new VertexAttributes(new VertexAttribute[]{new VertexAttribute(1, 3, "a_position"), new VertexAttribute(16, 2, "a_texCoord0"), new VertexAttribute(2, 4, "a_color")});
        BillboardParticleBatch.GPU_POSITION_OFFSET = (short)(BillboardParticleBatch.GPU_ATTRIBUTES.findByUsage(1).offset / 4);
        BillboardParticleBatch.GPU_UV_OFFSET = (short)(BillboardParticleBatch.GPU_ATTRIBUTES.findByUsage(16).offset / 4);
        BillboardParticleBatch.GPU_SIZE_ROTATION_OFFSET = (short)(BillboardParticleBatch.GPU_ATTRIBUTES.findByUsage(0x200).offset / 4);
        BillboardParticleBatch.GPU_COLOR_OFFSET = (short)(BillboardParticleBatch.GPU_ATTRIBUTES.findByUsage(2).offset / 4);
        BillboardParticleBatch.GPU_VERTEX_SIZE = BillboardParticleBatch.GPU_ATTRIBUTES.vertexSize / 4;
        BillboardParticleBatch.CPU_POSITION_OFFSET = (short)(BillboardParticleBatch.CPU_ATTRIBUTES.findByUsage(1).offset / 4);
        BillboardParticleBatch.CPU_UV_OFFSET = (short)(BillboardParticleBatch.CPU_ATTRIBUTES.findByUsage(16).offset / 4);
        BillboardParticleBatch.CPU_COLOR_OFFSET = (short)(BillboardParticleBatch.CPU_ATTRIBUTES.findByUsage(2).offset / 4);
        BillboardParticleBatch.CPU_VERTEX_SIZE = BillboardParticleBatch.CPU_ATTRIBUTES.vertexSize / 4;
    }

    public BillboardParticleBatch() {
        this(AlignMode.Screen, false, 100);
    }

    public BillboardParticleBatch(int v) {
        this(AlignMode.Screen, false, v);
    }

    public BillboardParticleBatch(AlignMode particleShader$AlignMode0, boolean z, int v) {
        this(particleShader$AlignMode0, z, v, null, null);
    }

    public BillboardParticleBatch(AlignMode particleShader$AlignMode0, boolean z, int v, BlendingAttribute blendingAttribute0, DepthTestAttribute depthTestAttribute0) {
        super(BillboardControllerRenderData.class);
        this.currentVertexSize = 0;
        this.useGPU = false;
        this.mode = AlignMode.Screen;
        this.renderables = new Array();
        this.renderablePool = new RenderablePool(this);
        this.blendingAttribute = blendingAttribute0;
        this.depthTestAttribute = depthTestAttribute0;
        if(this.blendingAttribute == null) {
            this.blendingAttribute = new BlendingAttribute(1, 0x303, 1.0f);
        }
        if(this.depthTestAttribute == null) {
            this.depthTestAttribute = new DepthTestAttribute(0x203, false);
        }
        this.allocIndices();
        this.initRenderData();
        this.ensureCapacity(v);
        this.setUseGpu(z);
        this.setAlignMode(particleShader$AlignMode0);
    }

    private void allocIndices() {
        this.indices = new short[0xBFFA];
        int v1 = 0;
        for(int v = 0; v1 < 0xBFFA; v += 4) {
            short[] arr_v = this.indices;
            arr_v[v1] = (short)v;
            arr_v[v1 + 1] = (short)(v + 1);
            arr_v[v1 + 2] = (short)(v + 2);
            arr_v[v1 + 3] = (short)(v + 2);
            arr_v[v1 + 4] = (short)(v + 3);
            arr_v[v1 + 5] = (short)v;
            v1 += 6;
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch
    public void allocParticlesData(int v) {
        this.vertices = new float[this.currentVertexSize * 4 * v];
        this.allocRenderables(v);
    }

    protected Renderable allocRenderable() {
        Renderable renderable0 = new Renderable();
        renderable0.meshPart.primitiveType = 4;
        renderable0.meshPart.offset = 0;
        renderable0.material = new Material(new Attribute[]{this.blendingAttribute, this.depthTestAttribute, TextureAttribute.createDiffuse(this.texture)});
        renderable0.meshPart.mesh = new Mesh(false, 0x7FFC, 0xBFFA, this.currentAttributes);
        renderable0.meshPart.mesh.setIndices(this.indices);
        renderable0.shader = this.shader;
        return renderable0;
    }

    private void allocRenderables(int v) {
        int v1 = 0x4000 - ((int)(16384.0 - ((double)(((float)(v / 0x1FFF))))));
        int v2 = this.renderablePool.getFree();
        if(v2 < v1) {
            for(int v3 = 0; v3 < v1 - v2; ++v3) {
                this.renderablePool.free(this.renderablePool.newObject());
            }
        }
    }

    private void allocShader() {
        Renderable renderable0 = this.allocRenderable();
        Shader shader0 = this.getShader(renderable0);
        renderable0.shader = shader0;
        this.shader = shader0;
        this.renderablePool.free(renderable0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch
    public void begin() {
        super.begin();
        this.renderablePool.freeAll(this.renderables);
        this.renderables.clear();
    }

    private void clearRenderablesPool() {
        this.renderablePool.freeAll(this.renderables);
        int v = this.renderablePool.getFree();
        for(int v1 = 0; v1 < v; ++v1) {
            ((Renderable)this.renderablePool.obtain()).meshPart.mesh.dispose();
        }
        this.renderables.clear();
    }

    private void fillVerticesGPU(int[] arr_v) {
        int v = 0;
        for(Object object0: this.renderData) {
            FloatChannel parallelArray$FloatChannel0 = ((BillboardControllerRenderData)object0).scaleChannel;
            FloatChannel parallelArray$FloatChannel1 = ((BillboardControllerRenderData)object0).regionChannel;
            FloatChannel parallelArray$FloatChannel2 = ((BillboardControllerRenderData)object0).positionChannel;
            FloatChannel parallelArray$FloatChannel3 = ((BillboardControllerRenderData)object0).colorChannel;
            FloatChannel parallelArray$FloatChannel4 = ((BillboardControllerRenderData)object0).rotationChannel;
            int v1 = ((BillboardControllerRenderData)object0).controller.particles.size;
            int v2 = v;
            int v3 = 0;
            while(v3 < v1) {
                int v4 = arr_v[v2] * this.currentVertexSize * 4;
                float f = parallelArray$FloatChannel0.data[parallelArray$FloatChannel0.strideSize * v3];
                int v5 = parallelArray$FloatChannel1.strideSize * v3;
                int v6 = parallelArray$FloatChannel2.strideSize * v3;
                int v7 = parallelArray$FloatChannel3.strideSize * v3;
                int v8 = parallelArray$FloatChannel4.strideSize * v3;
                float f1 = parallelArray$FloatChannel2.data[v6];
                float f2 = parallelArray$FloatChannel2.data[v6 + 1];
                float f3 = parallelArray$FloatChannel2.data[v6 + 2];
                float f4 = parallelArray$FloatChannel1.data[v5];
                float f5 = parallelArray$FloatChannel1.data[v5 + 1];
                float f6 = parallelArray$FloatChannel1.data[v5 + 2];
                float f7 = parallelArray$FloatChannel1.data[v5 + 3];
                float f8 = parallelArray$FloatChannel1.data[v5 + 4] * f;
                float f9 = parallelArray$FloatChannel1.data[v5 + 5] * f;
                float f10 = parallelArray$FloatChannel3.data[v7];
                float f11 = parallelArray$FloatChannel3.data[v7 + 1];
                float f12 = parallelArray$FloatChannel3.data[v7 + 2];
                float f13 = parallelArray$FloatChannel3.data[v7 + 3];
                float f14 = parallelArray$FloatChannel4.data[v8];
                float f15 = parallelArray$FloatChannel4.data[v8 + 1];
                BillboardParticleBatch.putVertex(this.vertices, v4, f1, f2, f3, f4, f7, -f8, -f9, f14, f15, f10, f11, f12, f13);
                int v9 = v4 + this.currentVertexSize;
                BillboardParticleBatch.putVertex(this.vertices, v9, f1, f2, f3, f6, f7, f8, -f9, f14, f15, f10, f11, f12, f13);
                int v10 = v9 + this.currentVertexSize;
                BillboardParticleBatch.putVertex(this.vertices, v10, f1, f2, f3, f6, f5, f8, f9, f14, f15, f10, f11, f12, f13);
                BillboardParticleBatch.putVertex(this.vertices, v10 + this.currentVertexSize, f1, f2, f3, f4, f5, -f8, f9, f14, f15, f10, f11, f12, f13);
                ++v3;
                ++v2;
            }
            v = v2;
        }
    }

    private void fillVerticesToScreenCPU(int[] arr_v) {
        Vector3 vector30 = BillboardParticleBatch.TMP_V3.set(this.camera.direction).scl(-1.0f);
        Vector3 vector31 = BillboardParticleBatch.TMP_V4.set(this.camera.up).crs(vector30).nor();
        Vector3 vector32 = this.camera.up;
        int v = 0;
        for(Object object0: this.renderData) {
            FloatChannel parallelArray$FloatChannel0 = ((BillboardControllerRenderData)object0).scaleChannel;
            FloatChannel parallelArray$FloatChannel1 = ((BillboardControllerRenderData)object0).regionChannel;
            FloatChannel parallelArray$FloatChannel2 = ((BillboardControllerRenderData)object0).positionChannel;
            FloatChannel parallelArray$FloatChannel3 = ((BillboardControllerRenderData)object0).colorChannel;
            FloatChannel parallelArray$FloatChannel4 = ((BillboardControllerRenderData)object0).rotationChannel;
            int v1 = ((BillboardControllerRenderData)object0).controller.particles.size;
            int v2 = v;
            int v3 = 0;
            while(v3 < v1) {
                int v4 = arr_v[v2] * this.currentVertexSize * 4;
                float f = parallelArray$FloatChannel0.data[parallelArray$FloatChannel0.strideSize * v3];
                int v5 = parallelArray$FloatChannel1.strideSize * v3;
                int v6 = parallelArray$FloatChannel2.strideSize * v3;
                int v7 = parallelArray$FloatChannel3.strideSize * v3;
                int v8 = parallelArray$FloatChannel4.strideSize * v3;
                float f1 = parallelArray$FloatChannel2.data[v6];
                float f2 = parallelArray$FloatChannel2.data[v6 + 1];
                int v9 = v4;
                float f3 = parallelArray$FloatChannel2.data[v6 + 2];
                float f4 = parallelArray$FloatChannel1.data[v5];
                float f5 = parallelArray$FloatChannel1.data[v5 + 1];
                float f6 = parallelArray$FloatChannel1.data[v5 + 2];
                float f7 = f5;
                float f8 = parallelArray$FloatChannel1.data[v5 + 3];
                float f9 = parallelArray$FloatChannel1.data[v5 + 4] * f;
                float f10 = f6;
                float f11 = parallelArray$FloatChannel1.data[v5 + 5] * f;
                float f12 = parallelArray$FloatChannel3.data[v7];
                float f13 = parallelArray$FloatChannel3.data[v7 + 1];
                float f14 = parallelArray$FloatChannel3.data[v7 + 2];
                float f15 = parallelArray$FloatChannel3.data[v7 + 3];
                float f16 = parallelArray$FloatChannel4.data[v8];
                float f17 = parallelArray$FloatChannel4.data[v8 + 1];
                BillboardParticleBatch.TMP_V1.set(vector31).scl(f9);
                BillboardParticleBatch.TMP_V2.set(vector32).scl(f11);
                if(f16 == 1.0f) {
                    BillboardParticleBatch.putVertex(this.vertices, v9, BillboardParticleBatch.TMP_V6.set(-BillboardParticleBatch.TMP_V1.x - BillboardParticleBatch.TMP_V2.x + f1, -BillboardParticleBatch.TMP_V1.y - BillboardParticleBatch.TMP_V2.y + f2, -BillboardParticleBatch.TMP_V1.z - BillboardParticleBatch.TMP_V2.z + f3), f4, f8, f12, f13, f14, f15);
                    int v12 = v9 + this.currentVertexSize;
                    BillboardParticleBatch.putVertex(this.vertices, v12, BillboardParticleBatch.TMP_V6.set(BillboardParticleBatch.TMP_V1.x - BillboardParticleBatch.TMP_V2.x + f1, BillboardParticleBatch.TMP_V1.y - BillboardParticleBatch.TMP_V2.y + f2, BillboardParticleBatch.TMP_V1.z - BillboardParticleBatch.TMP_V2.z + f3), f10, f8, f12, f13, f14, f15);
                    int v13 = v12 + this.currentVertexSize;
                    BillboardParticleBatch.putVertex(this.vertices, v13, BillboardParticleBatch.TMP_V6.set(BillboardParticleBatch.TMP_V1.x + BillboardParticleBatch.TMP_V2.x + f1, BillboardParticleBatch.TMP_V1.y + BillboardParticleBatch.TMP_V2.y + f2, BillboardParticleBatch.TMP_V1.z + BillboardParticleBatch.TMP_V2.z + f3), f10, f7, f12, f13, f14, f15);
                    BillboardParticleBatch.putVertex(this.vertices, v13 + this.currentVertexSize, BillboardParticleBatch.TMP_V6.set(BillboardParticleBatch.TMP_V2.x - BillboardParticleBatch.TMP_V1.x + f1, BillboardParticleBatch.TMP_V2.y - BillboardParticleBatch.TMP_V1.y + f2, BillboardParticleBatch.TMP_V2.z - BillboardParticleBatch.TMP_V1.z + f3), f4, f7, f12, f13, f14, f15);
                }
                else {
                    BillboardParticleBatch.TMP_M3.setToRotation(vector30, f16, f17);
                    BillboardParticleBatch.putVertex(this.vertices, v9, BillboardParticleBatch.TMP_V6.set(-BillboardParticleBatch.TMP_V1.x - BillboardParticleBatch.TMP_V2.x, -BillboardParticleBatch.TMP_V1.y - BillboardParticleBatch.TMP_V2.y, -BillboardParticleBatch.TMP_V1.z - BillboardParticleBatch.TMP_V2.z).mul(BillboardParticleBatch.TMP_M3).add(f1, f2, f3), f4, f8, f12, f13, f14, f15);
                    int v10 = v9 + this.currentVertexSize;
                    BillboardParticleBatch.putVertex(this.vertices, v10, BillboardParticleBatch.TMP_V6.set(BillboardParticleBatch.TMP_V1.x - BillboardParticleBatch.TMP_V2.x, BillboardParticleBatch.TMP_V1.y - BillboardParticleBatch.TMP_V2.y, BillboardParticleBatch.TMP_V1.z - BillboardParticleBatch.TMP_V2.z).mul(BillboardParticleBatch.TMP_M3).add(f1, f2, f3), f10, f8, f12, f13, f14, f15);
                    int v11 = v10 + this.currentVertexSize;
                    BillboardParticleBatch.putVertex(this.vertices, v11, BillboardParticleBatch.TMP_V6.set(BillboardParticleBatch.TMP_V1.x + BillboardParticleBatch.TMP_V2.x, BillboardParticleBatch.TMP_V1.y + BillboardParticleBatch.TMP_V2.y, BillboardParticleBatch.TMP_V1.z + BillboardParticleBatch.TMP_V2.z).mul(BillboardParticleBatch.TMP_M3).add(f1, f2, f3), f10, f7, f12, f13, f14, f15);
                    BillboardParticleBatch.putVertex(this.vertices, v11 + this.currentVertexSize, BillboardParticleBatch.TMP_V6.set(BillboardParticleBatch.TMP_V2.x - BillboardParticleBatch.TMP_V1.x, BillboardParticleBatch.TMP_V2.y - BillboardParticleBatch.TMP_V1.y, BillboardParticleBatch.TMP_V2.z - BillboardParticleBatch.TMP_V1.z).mul(BillboardParticleBatch.TMP_M3).add(f1, f2, f3), f4, f7, f12, f13, f14, f15);
                }
                ++v3;
                ++v2;
            }
            v = v2;
        }
    }

    private void fillVerticesToViewPointCPU(int[] arr_v) {
        int v = 0;
        for(Object object0: this.renderData) {
            FloatChannel parallelArray$FloatChannel0 = ((BillboardControllerRenderData)object0).scaleChannel;
            FloatChannel parallelArray$FloatChannel1 = ((BillboardControllerRenderData)object0).regionChannel;
            FloatChannel parallelArray$FloatChannel2 = ((BillboardControllerRenderData)object0).positionChannel;
            FloatChannel parallelArray$FloatChannel3 = ((BillboardControllerRenderData)object0).colorChannel;
            FloatChannel parallelArray$FloatChannel4 = ((BillboardControllerRenderData)object0).rotationChannel;
            int v1 = ((BillboardControllerRenderData)object0).controller.particles.size;
            int v2 = v;
            int v3 = 0;
            while(v3 < v1) {
                int v4 = arr_v[v2] * this.currentVertexSize * 4;
                float f = parallelArray$FloatChannel0.data[parallelArray$FloatChannel0.strideSize * v3];
                int v5 = parallelArray$FloatChannel1.strideSize * v3;
                int v6 = parallelArray$FloatChannel2.strideSize * v3;
                int v7 = parallelArray$FloatChannel3.strideSize * v3;
                int v8 = parallelArray$FloatChannel4.strideSize * v3;
                float f1 = parallelArray$FloatChannel2.data[v6];
                float f2 = parallelArray$FloatChannel2.data[v6 + 1];
                float f3 = parallelArray$FloatChannel2.data[v6 + 2];
                float f4 = parallelArray$FloatChannel1.data[v5];
                float f5 = parallelArray$FloatChannel1.data[v5 + 1];
                float f6 = parallelArray$FloatChannel1.data[v5 + 2];
                float f7 = parallelArray$FloatChannel1.data[v5 + 3];
                float f8 = parallelArray$FloatChannel1.data[v5 + 4] * f;
                float f9 = parallelArray$FloatChannel1.data[v5 + 5] * f;
                float f10 = parallelArray$FloatChannel3.data[v7];
                float f11 = parallelArray$FloatChannel3.data[v7 + 1];
                float f12 = parallelArray$FloatChannel3.data[v7 + 2];
                float f13 = parallelArray$FloatChannel3.data[v7 + 3];
                float f14 = parallelArray$FloatChannel4.data[v8];
                float f15 = parallelArray$FloatChannel4.data[v8 + 1];
                Vector3 vector30 = BillboardParticleBatch.TMP_V3.set(this.camera.position).sub(f1, f2, f3).nor();
                Vector3 vector31 = BillboardParticleBatch.TMP_V1.set(this.camera.up).crs(vector30).nor();
                Vector3 vector32 = BillboardParticleBatch.TMP_V2.set(vector30).crs(vector31);
                vector31.scl(f8);
                vector32.scl(f9);
                if(f14 == 1.0f) {
                    BillboardParticleBatch.putVertex(this.vertices, v4, BillboardParticleBatch.TMP_V6.set(-BillboardParticleBatch.TMP_V1.x - BillboardParticleBatch.TMP_V2.x + f1, -BillboardParticleBatch.TMP_V1.y - BillboardParticleBatch.TMP_V2.y + f2, -BillboardParticleBatch.TMP_V1.z - BillboardParticleBatch.TMP_V2.z + f3), f4, f7, f10, f11, f12, f13);
                    int v11 = this.currentVertexSize + v4;
                    BillboardParticleBatch.putVertex(this.vertices, v11, BillboardParticleBatch.TMP_V6.set(BillboardParticleBatch.TMP_V1.x - BillboardParticleBatch.TMP_V2.x + f1, BillboardParticleBatch.TMP_V1.y - BillboardParticleBatch.TMP_V2.y + f2, BillboardParticleBatch.TMP_V1.z - BillboardParticleBatch.TMP_V2.z + f3), f6, f7, f10, f11, f12, f13);
                    int v12 = v11 + this.currentVertexSize;
                    BillboardParticleBatch.putVertex(this.vertices, v12, BillboardParticleBatch.TMP_V6.set(BillboardParticleBatch.TMP_V1.x + BillboardParticleBatch.TMP_V2.x + f1, BillboardParticleBatch.TMP_V1.y + BillboardParticleBatch.TMP_V2.y + f2, BillboardParticleBatch.TMP_V1.z + BillboardParticleBatch.TMP_V2.z + f3), f6, f5, f10, f11, f12, f13);
                    BillboardParticleBatch.putVertex(this.vertices, v12 + this.currentVertexSize, BillboardParticleBatch.TMP_V6.set(BillboardParticleBatch.TMP_V2.x - BillboardParticleBatch.TMP_V1.x + f1, BillboardParticleBatch.TMP_V2.y - BillboardParticleBatch.TMP_V1.y + f2, BillboardParticleBatch.TMP_V2.z - BillboardParticleBatch.TMP_V1.z + f3), f4, f5, f10, f11, f12, f13);
                }
                else {
                    BillboardParticleBatch.TMP_M3.setToRotation(vector30, f14, f15);
                    BillboardParticleBatch.putVertex(this.vertices, v4, BillboardParticleBatch.TMP_V6.set(-BillboardParticleBatch.TMP_V1.x - BillboardParticleBatch.TMP_V2.x, -BillboardParticleBatch.TMP_V1.y - BillboardParticleBatch.TMP_V2.y, -BillboardParticleBatch.TMP_V1.z - BillboardParticleBatch.TMP_V2.z).mul(BillboardParticleBatch.TMP_M3).add(f1, f2, f3), f4, f7, f10, f11, f12, f13);
                    int v9 = this.currentVertexSize + v4;
                    BillboardParticleBatch.putVertex(this.vertices, v9, BillboardParticleBatch.TMP_V6.set(BillboardParticleBatch.TMP_V1.x - BillboardParticleBatch.TMP_V2.x, BillboardParticleBatch.TMP_V1.y - BillboardParticleBatch.TMP_V2.y, BillboardParticleBatch.TMP_V1.z - BillboardParticleBatch.TMP_V2.z).mul(BillboardParticleBatch.TMP_M3).add(f1, f2, f3), f6, f7, f10, f11, f12, f13);
                    int v10 = v9 + this.currentVertexSize;
                    BillboardParticleBatch.putVertex(this.vertices, v10, BillboardParticleBatch.TMP_V6.set(BillboardParticleBatch.TMP_V1.x + BillboardParticleBatch.TMP_V2.x, BillboardParticleBatch.TMP_V1.y + BillboardParticleBatch.TMP_V2.y, BillboardParticleBatch.TMP_V1.z + BillboardParticleBatch.TMP_V2.z).mul(BillboardParticleBatch.TMP_M3).add(f1, f2, f3), f6, f5, f10, f11, f12, f13);
                    BillboardParticleBatch.putVertex(this.vertices, v10 + this.currentVertexSize, BillboardParticleBatch.TMP_V6.set(BillboardParticleBatch.TMP_V2.x - BillboardParticleBatch.TMP_V1.x, BillboardParticleBatch.TMP_V2.y - BillboardParticleBatch.TMP_V1.y, BillboardParticleBatch.TMP_V2.z - BillboardParticleBatch.TMP_V1.z).mul(BillboardParticleBatch.TMP_M3).add(f1, f2, f3), f4, f5, f10, f11, f12, f13);
                }
                ++v3;
                ++v2;
            }
            v = v2;
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch
    protected void flush(int[] arr_v) {
        if(this.useGPU) {
            this.fillVerticesGPU(arr_v);
        }
        else if(this.mode == AlignMode.Screen) {
            this.fillVerticesToScreenCPU(arr_v);
        }
        else if(this.mode == AlignMode.ViewPoint) {
            this.fillVerticesToViewPointCPU(arr_v);
        }
        int v = this.bufferedParticlesCount * 4;
        for(int v1 = 0; v1 < v; v1 += v2) {
            int v2 = Math.min(v - v1, 0x7FFC);
            Renderable renderable0 = (Renderable)this.renderablePool.obtain();
            renderable0.meshPart.size = v2 / 4 * 6;
            renderable0.meshPart.mesh.setVertices(this.vertices, this.currentVertexSize * v1, this.currentVertexSize * v2);
            renderable0.meshPart.update();
            this.renderables.add(renderable0);
        }
    }

    public AlignMode getAlignMode() {
        return this.mode;
    }

    public BlendingAttribute getBlendingAttribute() {
        return this.blendingAttribute;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(Array array0, Pool pool0) {
        for(Object object0: this.renderables) {
            array0.add(((Renderable)pool0.obtain()).set(((Renderable)object0)));
        }
    }

    protected Shader getShader(Renderable renderable0) {
        Shader shader0 = this.useGPU ? new ParticleShader(renderable0, new com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Config(this.mode)) : new DefaultShader(renderable0);
        shader0.init();
        return shader0;
    }

    public Texture getTexture() {
        return this.texture;
    }

    private void initRenderData() {
        this.setVertexData();
        this.clearRenderablesPool();
        this.allocShader();
        this.resetCapacity();
    }

    public boolean isUseGPU() {
        return this.useGPU;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void load(AssetManager assetManager0, ResourceData resourceData0) {
        SaveData resourceData$SaveData0 = resourceData0.getSaveData("billboardBatch");
        if(resourceData$SaveData0 != null) {
            this.setTexture(((Texture)assetManager0.get(resourceData$SaveData0.loadAsset())));
            Config billboardParticleBatch$Config0 = (Config)resourceData$SaveData0.load("cfg");
            this.setUseGpu(billboardParticleBatch$Config0.useGPU);
            this.setAlignMode(billboardParticleBatch$Config0.mode);
        }
    }

    private static void putVertex(float[] arr_f, int v, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12) {
        arr_f[v + BillboardParticleBatch.GPU_POSITION_OFFSET] = f;
        arr_f[v + BillboardParticleBatch.GPU_POSITION_OFFSET + 1] = f1;
        arr_f[BillboardParticleBatch.GPU_POSITION_OFFSET + v + 2] = f2;
        arr_f[v + BillboardParticleBatch.GPU_UV_OFFSET] = f3;
        arr_f[BillboardParticleBatch.GPU_UV_OFFSET + v + 1] = f4;
        arr_f[v + BillboardParticleBatch.GPU_SIZE_ROTATION_OFFSET] = f5;
        arr_f[v + BillboardParticleBatch.GPU_SIZE_ROTATION_OFFSET + 1] = f6;
        arr_f[v + BillboardParticleBatch.GPU_SIZE_ROTATION_OFFSET + 2] = f7;
        arr_f[BillboardParticleBatch.GPU_SIZE_ROTATION_OFFSET + v + 3] = f8;
        arr_f[v + BillboardParticleBatch.GPU_COLOR_OFFSET] = f9;
        arr_f[v + BillboardParticleBatch.GPU_COLOR_OFFSET + 1] = f10;
        arr_f[v + BillboardParticleBatch.GPU_COLOR_OFFSET + 2] = f11;
        arr_f[BillboardParticleBatch.GPU_COLOR_OFFSET + v + 3] = f12;
    }

    private static void putVertex(float[] arr_f, int v, Vector3 vector30, float f, float f1, float f2, float f3, float f4, float f5) {
        arr_f[BillboardParticleBatch.CPU_POSITION_OFFSET + v] = vector30.x;
        arr_f[BillboardParticleBatch.CPU_POSITION_OFFSET + v + 1] = vector30.y;
        arr_f[BillboardParticleBatch.CPU_POSITION_OFFSET + v + 2] = vector30.z;
        arr_f[v + BillboardParticleBatch.CPU_UV_OFFSET] = f;
        arr_f[BillboardParticleBatch.CPU_UV_OFFSET + v + 1] = f1;
        arr_f[v + BillboardParticleBatch.CPU_COLOR_OFFSET] = f2;
        arr_f[v + BillboardParticleBatch.CPU_COLOR_OFFSET + 1] = f3;
        arr_f[v + BillboardParticleBatch.CPU_COLOR_OFFSET + 2] = f4;
        arr_f[v + BillboardParticleBatch.CPU_COLOR_OFFSET + 3] = f5;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void save(AssetManager assetManager0, ResourceData resourceData0) {
        SaveData resourceData$SaveData0 = resourceData0.createSaveData("billboardBatch");
        resourceData$SaveData0.save("cfg", new Config(this.useGPU, this.mode));
        resourceData$SaveData0.saveAsset(assetManager0.getAssetFileName(this.texture), Texture.class);
    }

    public void setAlignMode(AlignMode particleShader$AlignMode0) {
        if(particleShader$AlignMode0 != this.mode) {
            this.mode = particleShader$AlignMode0;
            if(this.useGPU) {
                this.initRenderData();
                this.allocRenderables(this.bufferedParticlesCount);
            }
        }
    }

    public void setTexture(Texture texture0) {
        this.renderablePool.freeAll(this.renderables);
        this.renderables.clear();
        int v = this.renderablePool.getFree();
        for(int v1 = 0; v1 < v; ++v1) {
            ((TextureAttribute)((Renderable)this.renderablePool.obtain()).material.get(TextureAttribute.Diffuse)).textureDescription.texture = texture0;
        }
        this.texture = texture0;
    }

    public void setUseGpu(boolean z) {
        if(this.useGPU != z) {
            this.useGPU = z;
            this.initRenderData();
            this.allocRenderables(this.bufferedParticlesCount);
        }
    }

    public void setVertexData() {
        if(this.useGPU) {
            this.currentAttributes = BillboardParticleBatch.GPU_ATTRIBUTES;
            this.currentVertexSize = BillboardParticleBatch.GPU_VERTEX_SIZE;
            return;
        }
        this.currentAttributes = BillboardParticleBatch.CPU_ATTRIBUTES;
        this.currentVertexSize = BillboardParticleBatch.CPU_VERTEX_SIZE;
    }
}

