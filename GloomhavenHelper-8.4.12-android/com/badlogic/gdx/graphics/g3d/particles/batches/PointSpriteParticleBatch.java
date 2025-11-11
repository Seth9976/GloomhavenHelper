package com.badlogic.gdx.graphics.g3d.particles.batches;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.FloatChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Config;
import com.badlogic.gdx.graphics.g3d.particles.ParticleShader.ParticleType;
import com.badlogic.gdx.graphics.g3d.particles.ParticleShader;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData.SaveData;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.renderers.PointSpriteControllerRenderData;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class PointSpriteParticleBatch extends BufferedParticleBatch {
    protected static final VertexAttributes CPU_ATTRIBUTES = null;
    protected static final int CPU_COLOR_OFFSET = 0;
    protected static final int CPU_POSITION_OFFSET = 0;
    protected static final int CPU_REGION_OFFSET = 0;
    protected static final int CPU_SIZE_AND_ROTATION_OFFSET = 0;
    protected static final int CPU_VERTEX_SIZE = 0;
    protected static final Vector3 TMP_V1 = null;
    private static boolean pointSpritesEnabled = false;
    Renderable renderable;
    protected static final int sizeAndRotationUsage = 0x200;
    private float[] vertices;

    static {
        PointSpriteParticleBatch.TMP_V1 = new Vector3();
        PointSpriteParticleBatch.CPU_ATTRIBUTES = new VertexAttributes(new VertexAttribute[]{new VertexAttribute(1, 3, "a_position"), new VertexAttribute(2, 4, "a_color"), new VertexAttribute(16, 4, "a_region"), new VertexAttribute(0x200, 3, "a_sizeAndRotation")});
        PointSpriteParticleBatch.CPU_VERTEX_SIZE = (short)(PointSpriteParticleBatch.CPU_ATTRIBUTES.vertexSize / 4);
        PointSpriteParticleBatch.CPU_POSITION_OFFSET = (short)(PointSpriteParticleBatch.CPU_ATTRIBUTES.findByUsage(1).offset / 4);
        PointSpriteParticleBatch.CPU_COLOR_OFFSET = (short)(PointSpriteParticleBatch.CPU_ATTRIBUTES.findByUsage(2).offset / 4);
        PointSpriteParticleBatch.CPU_REGION_OFFSET = (short)(PointSpriteParticleBatch.CPU_ATTRIBUTES.findByUsage(16).offset / 4);
        PointSpriteParticleBatch.CPU_SIZE_AND_ROTATION_OFFSET = (short)(PointSpriteParticleBatch.CPU_ATTRIBUTES.findByUsage(0x200).offset / 4);
    }

    public PointSpriteParticleBatch() {
        this(1000);
    }

    public PointSpriteParticleBatch(int v) {
        this(v, new Config(ParticleType.Point));
    }

    public PointSpriteParticleBatch(int v, Config particleShader$Config0) {
        super(PointSpriteControllerRenderData.class);
        if(!PointSpriteParticleBatch.pointSpritesEnabled) {
            PointSpriteParticleBatch.enablePointSprites();
        }
        this.allocRenderable();
        this.ensureCapacity(v);
        Renderable renderable0 = this.renderable;
        renderable0.shader = new ParticleShader(renderable0, particleShader$Config0);
        this.renderable.shader.init();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch
    protected void allocParticlesData(int v) {
        this.vertices = new float[PointSpriteParticleBatch.CPU_VERTEX_SIZE * v];
        if(this.renderable.meshPart.mesh != null) {
            this.renderable.meshPart.mesh.dispose();
        }
        MeshPart meshPart0 = this.renderable.meshPart;
        meshPart0.mesh = new Mesh(false, v, 0, PointSpriteParticleBatch.CPU_ATTRIBUTES);
    }

    protected void allocRenderable() {
        this.renderable = new Renderable();
        this.renderable.meshPart.primitiveType = 0;
        this.renderable.meshPart.offset = 0;
        Renderable renderable0 = this.renderable;
        renderable0.material = new Material(new Attribute[]{new BlendingAttribute(1, 0x303, 1.0f), new DepthTestAttribute(0x203, false), TextureAttribute.createDiffuse(null)});
    }

    private static void enablePointSprites() {
        Gdx.gl.glEnable(34370);
        if(Gdx.app.getType() == ApplicationType.Desktop) {
            Gdx.gl.glEnable(0x8861);
        }
        PointSpriteParticleBatch.pointSpritesEnabled = true;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch
    protected void flush(int[] arr_v) {
        int v = 0;
        for(Object object0: this.renderData) {
            FloatChannel parallelArray$FloatChannel0 = ((PointSpriteControllerRenderData)object0).scaleChannel;
            FloatChannel parallelArray$FloatChannel1 = ((PointSpriteControllerRenderData)object0).regionChannel;
            FloatChannel parallelArray$FloatChannel2 = ((PointSpriteControllerRenderData)object0).positionChannel;
            FloatChannel parallelArray$FloatChannel3 = ((PointSpriteControllerRenderData)object0).colorChannel;
            FloatChannel parallelArray$FloatChannel4 = ((PointSpriteControllerRenderData)object0).rotationChannel;
            int v1 = v;
            int v2 = 0;
            while(v2 < ((PointSpriteControllerRenderData)object0).controller.particles.size) {
                int v3 = arr_v[v1] * PointSpriteParticleBatch.CPU_VERTEX_SIZE;
                int v4 = parallelArray$FloatChannel1.strideSize * v2;
                int v5 = parallelArray$FloatChannel2.strideSize * v2;
                int v6 = parallelArray$FloatChannel3.strideSize * v2;
                int v7 = parallelArray$FloatChannel4.strideSize * v2;
                this.vertices[v3 + PointSpriteParticleBatch.CPU_POSITION_OFFSET] = parallelArray$FloatChannel2.data[v5];
                this.vertices[PointSpriteParticleBatch.CPU_POSITION_OFFSET + v3 + 1] = parallelArray$FloatChannel2.data[v5 + 1];
                this.vertices[PointSpriteParticleBatch.CPU_POSITION_OFFSET + v3 + 2] = parallelArray$FloatChannel2.data[v5 + 2];
                this.vertices[PointSpriteParticleBatch.CPU_COLOR_OFFSET + v3] = parallelArray$FloatChannel3.data[v6];
                this.vertices[PointSpriteParticleBatch.CPU_COLOR_OFFSET + v3 + 1] = parallelArray$FloatChannel3.data[v6 + 1];
                this.vertices[PointSpriteParticleBatch.CPU_COLOR_OFFSET + v3 + 2] = parallelArray$FloatChannel3.data[v6 + 2];
                this.vertices[PointSpriteParticleBatch.CPU_COLOR_OFFSET + v3 + 3] = parallelArray$FloatChannel3.data[v6 + 3];
                this.vertices[PointSpriteParticleBatch.CPU_SIZE_AND_ROTATION_OFFSET + v3] = parallelArray$FloatChannel0.data[parallelArray$FloatChannel0.strideSize * v2];
                this.vertices[PointSpriteParticleBatch.CPU_SIZE_AND_ROTATION_OFFSET + v3 + 1] = parallelArray$FloatChannel4.data[v7];
                this.vertices[PointSpriteParticleBatch.CPU_SIZE_AND_ROTATION_OFFSET + v3 + 2] = parallelArray$FloatChannel4.data[v7 + 1];
                this.vertices[PointSpriteParticleBatch.CPU_REGION_OFFSET + v3] = parallelArray$FloatChannel1.data[v4];
                this.vertices[PointSpriteParticleBatch.CPU_REGION_OFFSET + v3 + 1] = parallelArray$FloatChannel1.data[v4 + 1];
                this.vertices[PointSpriteParticleBatch.CPU_REGION_OFFSET + v3 + 2] = parallelArray$FloatChannel1.data[v4 + 2];
                this.vertices[v3 + PointSpriteParticleBatch.CPU_REGION_OFFSET + 3] = parallelArray$FloatChannel1.data[v4 + 3];
                ++v2;
                ++v1;
            }
            v = v1;
        }
        this.renderable.meshPart.size = this.bufferedParticlesCount;
        this.renderable.meshPart.mesh.setVertices(this.vertices, 0, this.bufferedParticlesCount * PointSpriteParticleBatch.CPU_VERTEX_SIZE);
        this.renderable.meshPart.update();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(Array array0, Pool pool0) {
        if(this.bufferedParticlesCount > 0) {
            array0.add(((Renderable)pool0.obtain()).set(this.renderable));
        }
    }

    public Texture getTexture() {
        return (Texture)((TextureAttribute)this.renderable.material.get(TextureAttribute.Diffuse)).textureDescription.texture;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void load(AssetManager assetManager0, ResourceData resourceData0) {
        SaveData resourceData$SaveData0 = resourceData0.getSaveData("pointSpriteBatch");
        if(resourceData$SaveData0 != null) {
            this.setTexture(((Texture)assetManager0.get(resourceData$SaveData0.loadAsset())));
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void save(AssetManager assetManager0, ResourceData resourceData0) {
        resourceData0.createSaveData("pointSpriteBatch").saveAsset(assetManager0.getAssetFileName(this.getTexture()), Texture.class);
    }

    public void setTexture(Texture texture0) {
        ((TextureAttribute)this.renderable.material.get(TextureAttribute.Diffuse)).textureDescription.texture = texture0;
    }
}

