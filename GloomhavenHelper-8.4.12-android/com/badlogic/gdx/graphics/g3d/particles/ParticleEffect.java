package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array.ArrayIterator;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class ParticleEffect implements Configurable, Disposable {
    private BoundingBox bounds;
    private Array controllers;

    public ParticleEffect() {
        this.controllers = new Array(true, 3, ParticleController.class);
    }

    public ParticleEffect(ParticleEffect particleEffect0) {
        this.controllers = new Array(true, particleEffect0.controllers.size);
        int v = particleEffect0.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            this.controllers.add(((ParticleController)particleEffect0.controllers.get(v1)).copy());
        }
    }

    public ParticleEffect(ParticleController[] arr_particleController) {
        this.controllers = new Array(arr_particleController);
    }

    public ParticleEffect copy() {
        return new ParticleEffect(this);
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleController)this.controllers.get(v1)).dispose();
        }
    }

    public void draw() {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleController)this.controllers.get(v1)).draw();
        }
    }

    public void end() {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleController)this.controllers.get(v1)).end();
        }
    }

    public ParticleController findController(String s) {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ParticleController particleController0 = (ParticleController)this.controllers.get(v1);
            if(particleController0.name.equals(s)) {
                return particleController0;
            }
        }
        return null;
    }

    public BoundingBox getBoundingBox() {
        if(this.bounds == null) {
            this.bounds = new BoundingBox();
        }
        BoundingBox boundingBox0 = this.bounds;
        boundingBox0.inf();
        for(Object object0: this.controllers) {
            boundingBox0.ext(((ParticleController)object0).getBoundingBox());
        }
        return boundingBox0;
    }

    public Array getControllers() {
        return this.controllers;
    }

    public void init() {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleController)this.controllers.get(v1)).init();
        }
    }

    public boolean isComplete() {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(!((ParticleController)this.controllers.get(v1)).isComplete()) {
                return false;
            }
        }
        return true;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ResourceData$Configurable
    public void load(AssetManager assetManager0, ResourceData resourceData0) {
        for(Object object0: this.controllers) {
            ((ParticleController)object0).load(assetManager0, resourceData0);
        }
    }

    public void reset() {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleController)this.controllers.get(v1)).reset();
        }
    }

    public void rotate(Quaternion quaternion0) {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleController)this.controllers.get(v1)).rotate(quaternion0);
        }
    }

    public void rotate(Vector3 vector30, float f) {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleController)this.controllers.get(v1)).rotate(vector30, f);
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ResourceData$Configurable
    public void save(AssetManager assetManager0, ResourceData resourceData0) {
        for(Object object0: this.controllers) {
            ((ParticleController)object0).save(assetManager0, resourceData0);
        }
    }

    public void scale(float f, float f1, float f2) {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleController)this.controllers.get(v1)).scale(f, f1, f2);
        }
    }

    public void scale(Vector3 vector30) {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleController)this.controllers.get(v1)).scale(vector30.x, vector30.y, vector30.z);
        }
    }

    public void setBatch(Array array0) {
        ArrayIterator array$ArrayIterator0 = this.controllers.iterator();
    label_1:
        while(array$ArrayIterator0.hasNext()) {
            Object object0 = array$ArrayIterator0.next();
            ParticleController particleController0 = (ParticleController)object0;
            ArrayIterator array$ArrayIterator1 = array0.iterator();
            do {
                if(!array$ArrayIterator1.hasNext()) {
                    continue label_1;
                }
                Object object1 = array$ArrayIterator1.next();
            }
            while(!particleController0.renderer.setBatch(((ParticleBatch)object1)));
        }
    }

    public void setTransform(Matrix4 matrix40) {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleController)this.controllers.get(v1)).setTransform(matrix40);
        }
    }

    public void start() {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleController)this.controllers.get(v1)).start();
        }
    }

    public void translate(Vector3 vector30) {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleController)this.controllers.get(v1)).translate(vector30);
        }
    }

    public void update() {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleController)this.controllers.get(v1)).update();
        }
    }

    public void update(float f) {
        int v = this.controllers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleController)this.controllers.get(v1)).update(f);
        }
    }
}

