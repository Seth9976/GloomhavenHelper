package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter;
import com.badlogic.gdx.graphics.g3d.particles.influencers.Influencer;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.reflect.ClassReflection;

public class ParticleController implements Configurable, Serializable {
    protected static final float DEFAULT_TIME_STEP = 0.016667f;
    protected BoundingBox boundingBox;
    public float deltaTime;
    public float deltaTimeSqr;
    public Emitter emitter;
    public Array influencers;
    public String name;
    public ParticleChannels particleChannels;
    public ParallelArray particles;
    public ParticleControllerRenderer renderer;
    public Vector3 scale;
    public Matrix4 transform;

    public ParticleController() {
        this.transform = new Matrix4();
        this.scale = new Vector3(1.0f, 1.0f, 1.0f);
        this.influencers = new Array(true, 3, Influencer.class);
        this.setTimeStep(0.016667f);
    }

    public ParticleController(String s, Emitter emitter0, ParticleControllerRenderer particleControllerRenderer0, Influencer[] arr_influencer) {
        this.name = s;
        this.emitter = emitter0;
        this.renderer = particleControllerRenderer0;
        this.particleChannels = new ParticleChannels();
        this.influencers = new Array(arr_influencer);
    }

    public void activateParticles(int v, int v1) {
        this.emitter.activateParticles(v, v1);
        for(Object object0: this.influencers) {
            ((Influencer)object0).activateParticles(v, v1);
        }
    }

    protected void allocateChannels(int v) {
        this.particles = new ParallelArray(v);
        this.emitter.allocateChannels();
        for(Object object0: this.influencers) {
            ((Influencer)object0).allocateChannels();
        }
        this.renderer.allocateChannels();
    }

    protected void bind() {
        this.emitter.set(this);
        for(Object object0: this.influencers) {
            ((Influencer)object0).set(this);
        }
        this.renderer.set(this);
    }

    protected void calculateBoundingBox() {
        this.boundingBox.clr();
        FloatChannel parallelArray$FloatChannel0 = (FloatChannel)this.particles.getChannel(ParticleChannels.Position);
        int v = parallelArray$FloatChannel0.strideSize * this.particles.size;
        for(int v1 = 0; v1 < v; v1 += parallelArray$FloatChannel0.strideSize) {
            this.boundingBox.ext(parallelArray$FloatChannel0.data[v1], parallelArray$FloatChannel0.data[v1 + 1], parallelArray$FloatChannel0.data[v1 + 2]);
        }
    }

    public ParticleController copy() {
        Emitter emitter0 = (Emitter)this.emitter.copy();
        Influencer[] arr_influencer = new Influencer[this.influencers.size];
        int v = 0;
        for(Object object0: this.influencers) {
            arr_influencer[v] = (Influencer)((Influencer)object0).copy();
            ++v;
        }
        return new ParticleController(new String(this.name), emitter0, ((ParticleControllerRenderer)this.renderer.copy()), arr_influencer);
    }

    public void dispose() {
        for(Object object0: this.influencers) {
            ((Influencer)object0).dispose();
        }
    }

    public void draw() {
        if(this.particles.size > 0) {
            this.renderer.update();
        }
    }

    public void end() {
        for(Object object0: this.influencers) {
            ((Influencer)object0).end();
        }
        this.emitter.end();
    }

    private int findIndex(Class class0) {
        for(int v = 0; v < this.influencers.size; ++v) {
            if(ClassReflection.isAssignableFrom(class0, ((Influencer)this.influencers.get(v)).getClass())) {
                return v;
            }
        }
        return -1;
    }

    public Influencer findInfluencer(Class class0) {
        int v = this.findIndex(class0);
        return v <= -1 ? null : ((Influencer)this.influencers.get(v));
    }

    public BoundingBox getBoundingBox() {
        if(this.boundingBox == null) {
            this.boundingBox = new BoundingBox();
        }
        this.calculateBoundingBox();
        return this.boundingBox;
    }

    public void getTransform(Matrix4 matrix40) {
        matrix40.set(this.transform);
    }

    public void init() {
        this.bind();
        if(this.particles != null) {
            this.end();
            this.particleChannels.resetIds();
        }
        this.allocateChannels(this.emitter.maxParticleCount);
        this.emitter.init();
        for(Object object0: this.influencers) {
            ((Influencer)object0).init();
        }
        this.renderer.init();
    }

    public boolean isComplete() {
        return this.emitter.isComplete();
    }

    public void killParticles(int v, int v1) {
        for(Object object0: this.influencers) {
            ((Influencer)object0).killParticles(v, v1);
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ResourceData$Configurable
    public void load(AssetManager assetManager0, ResourceData resourceData0) {
        for(Object object0: this.influencers) {
            ((Influencer)object0).load(assetManager0, resourceData0);
        }
    }

    public void mul(Matrix4 matrix40) {
        this.transform.mul(matrix40);
        this.transform.getScale(this.scale);
    }

    @Override  // com.badlogic.gdx.utils.Json$Serializable
    public void read(Json json0, JsonValue jsonValue0) {
        this.name = (String)json0.readValue("name", String.class, jsonValue0);
        this.emitter = (Emitter)json0.readValue("emitter", Emitter.class, jsonValue0);
        this.influencers.addAll(((Array)json0.readValue("influencers", Array.class, Influencer.class, jsonValue0)));
        this.renderer = (ParticleControllerRenderer)json0.readValue("renderer", ParticleControllerRenderer.class, jsonValue0);
    }

    public void removeInfluencer(Class class0) {
        int v = this.findIndex(class0);
        if(v > -1) {
            this.influencers.removeIndex(v);
        }
    }

    public boolean replaceInfluencer(Class class0, Influencer influencer0) {
        int v = this.findIndex(class0);
        if(v > -1) {
            this.influencers.insert(v, influencer0);
            this.influencers.removeIndex(v + 1);
            return true;
        }
        return false;
    }

    public void reset() {
        this.end();
        this.start();
    }

    public void rotate(Quaternion quaternion0) {
        this.transform.rotate(quaternion0);
    }

    public void rotate(Vector3 vector30, float f) {
        this.transform.rotate(vector30, f);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ResourceData$Configurable
    public void save(AssetManager assetManager0, ResourceData resourceData0) {
        for(Object object0: this.influencers) {
            ((Influencer)object0).save(assetManager0, resourceData0);
        }
    }

    public void scale(float f, float f1, float f2) {
        this.transform.scale(f, f1, f2);
        this.transform.getScale(this.scale);
    }

    public void scale(Vector3 vector30) {
        this.scale(vector30.x, vector30.y, vector30.z);
    }

    private void setTimeStep(float f) {
        this.deltaTime = f;
        this.deltaTimeSqr = this.deltaTime * this.deltaTime;
    }

    public void setTransform(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.transform.set(f, f1, f2, f3, f4, f5, f6, f7, f7, f7);
        this.scale.set(f7, f7, f7);
    }

    public void setTransform(Matrix4 matrix40) {
        this.transform.set(matrix40);
        matrix40.getScale(this.scale);
    }

    public void setTranslation(Vector3 vector30) {
        this.transform.setTranslation(vector30);
    }

    public void start() {
        this.emitter.start();
        for(Object object0: this.influencers) {
            ((Influencer)object0).start();
        }
    }

    public void translate(Vector3 vector30) {
        this.transform.translate(vector30);
    }

    public void update() {
        this.update(Gdx.graphics.getDeltaTime());
    }

    public void update(float f) {
        this.setTimeStep(f);
        this.emitter.update();
        for(Object object0: this.influencers) {
            ((Influencer)object0).update();
        }
    }

    @Override  // com.badlogic.gdx.utils.Json$Serializable
    public void write(Json json0) {
        json0.writeValue("name", this.name);
        json0.writeValue("emitter", this.emitter, Emitter.class);
        json0.writeValue("influencers", this.influencers, Array.class, Influencer.class);
        json0.writeValue("renderer", this.renderer, ParticleControllerRenderer.class);
    }
}

