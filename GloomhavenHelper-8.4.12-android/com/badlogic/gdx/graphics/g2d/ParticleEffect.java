package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

public class ParticleEffect implements Disposable {
    private BoundingBox bounds;
    private final Array emitters;
    protected float motionScale;
    private boolean ownsTexture;
    protected float xSizeScale;
    protected float ySizeScale;

    public ParticleEffect() {
        this.xSizeScale = 1.0f;
        this.ySizeScale = 1.0f;
        this.motionScale = 1.0f;
        this.emitters = new Array(8);
    }

    public ParticleEffect(ParticleEffect particleEffect0) {
        this.xSizeScale = 1.0f;
        this.ySizeScale = 1.0f;
        this.motionScale = 1.0f;
        this.emitters = new Array(true, particleEffect0.emitters.size);
        int v = particleEffect0.emitters.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ParticleEmitter particleEmitter0 = this.newEmitter(((ParticleEmitter)particleEffect0.emitters.get(v1)));
            this.emitters.add(particleEmitter0);
        }
    }

    public void allowCompletion() {
        int v = this.emitters.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleEmitter)this.emitters.get(v1)).allowCompletion();
        }
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if(!this.ownsTexture) {
            return;
        }
        int v1 = this.emitters.size;
        for(int v = 0; v < v1; ++v) {
            for(Object object0: ((ParticleEmitter)this.emitters.get(v)).getSprites()) {
                ((Sprite)object0).getTexture().dispose();
            }
        }
    }

    public void draw(Batch batch0) {
        int v = this.emitters.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleEmitter)this.emitters.get(v1)).draw(batch0);
        }
    }

    public void draw(Batch batch0, float f) {
        int v = this.emitters.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleEmitter)this.emitters.get(v1)).draw(batch0, f);
        }
    }

    public ParticleEmitter findEmitter(String s) {
        int v = this.emitters.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ParticleEmitter particleEmitter0 = (ParticleEmitter)this.emitters.get(v1);
            if(particleEmitter0.getName().equals(s)) {
                return particleEmitter0;
            }
        }
        return null;
    }

    public void flipY() {
        int v = this.emitters.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleEmitter)this.emitters.get(v1)).flipY();
        }
    }

    public BoundingBox getBoundingBox() {
        if(this.bounds == null) {
            this.bounds = new BoundingBox();
        }
        BoundingBox boundingBox0 = this.bounds;
        boundingBox0.inf();
        for(Object object0: this.emitters) {
            boundingBox0.ext(((ParticleEmitter)object0).getBoundingBox());
        }
        return boundingBox0;
    }

    public Array getEmitters() {
        return this.emitters;
    }

    public boolean isComplete() {
        int v = this.emitters.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(!((ParticleEmitter)this.emitters.get(v1)).isComplete()) {
                return false;
            }
        }
        return true;
    }

    public void load(FileHandle fileHandle0, FileHandle fileHandle1) {
        this.loadEmitters(fileHandle0);
        this.loadEmitterImages(fileHandle1);
    }

    public void load(FileHandle fileHandle0, TextureAtlas textureAtlas0) {
        this.load(fileHandle0, textureAtlas0, null);
    }

    public void load(FileHandle fileHandle0, TextureAtlas textureAtlas0, String s) {
        this.loadEmitters(fileHandle0);
        this.loadEmitterImages(textureAtlas0, s);
    }

    public void loadEmitterImages(FileHandle fileHandle0) {
        this.ownsTexture = true;
        ObjectMap objectMap0 = new ObjectMap(this.emitters.size);
        int v = this.emitters.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ParticleEmitter particleEmitter0 = (ParticleEmitter)this.emitters.get(v1);
            if(particleEmitter0.getImagePaths().size != 0) {
                Array array0 = new Array();
                for(Object object0: particleEmitter0.getImagePaths()) {
                    String s = new File(((String)object0).replace('\\', '/')).getName();
                    Sprite sprite0 = (Sprite)objectMap0.get(s);
                    if(sprite0 == null) {
                        sprite0 = new Sprite(this.loadTexture(fileHandle0.child(s)));
                        objectMap0.put(s, sprite0);
                    }
                    array0.add(sprite0);
                }
                particleEmitter0.setSprites(array0);
            }
        }
    }

    public void loadEmitterImages(TextureAtlas textureAtlas0) {
        this.loadEmitterImages(textureAtlas0, null);
    }

    public void loadEmitterImages(TextureAtlas textureAtlas0, String s) {
        int v = this.emitters.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ParticleEmitter particleEmitter0 = (ParticleEmitter)this.emitters.get(v1);
            if(particleEmitter0.getImagePaths().size != 0) {
                Array array0 = new Array();
                for(Object object0: particleEmitter0.getImagePaths()) {
                    String s1 = new File(((String)object0).replace('\\', '/')).getName();
                    int v2 = s1.lastIndexOf(46);
                    if(v2 != -1) {
                        s1 = s1.substring(0, v2);
                    }
                    if(s != null) {
                        s1 = s + s1;
                    }
                    Sprite sprite0 = textureAtlas0.createSprite(s1);
                    if(sprite0 == null) {
                        throw new IllegalArgumentException("SpriteSheet missing image: " + s1);
                    }
                    array0.add(sprite0);
                }
                particleEmitter0.setSprites(array0);
            }
        }
    }

    public void loadEmitters(FileHandle fileHandle0) {
        BufferedReader bufferedReader1;
        InputStream inputStream0 = fileHandle0.read();
        this.emitters.clear();
        BufferedReader bufferedReader0 = null;
        try {
            bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream0), 0x200);
            goto label_7;
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException("Error loading effect: " + fileHandle0, iOException0);
            try {
                do {
                label_7:
                    ParticleEmitter particleEmitter0 = this.newEmitter(bufferedReader1);
                    this.emitters.add(particleEmitter0);
                }
                while(bufferedReader1.readLine() != null);
                goto label_20;
            }
            catch(IOException iOException0) {
            }
            catch(Throwable throwable0) {
                bufferedReader0 = bufferedReader1;
                StreamUtils.closeQuietly(bufferedReader0);
                throw throwable0;
            }
            bufferedReader0 = bufferedReader1;
            try {
                throw new GdxRuntimeException("Error loading effect: " + fileHandle0, iOException0);
            }
            catch(Throwable throwable0) {
                StreamUtils.closeQuietly(bufferedReader0);
                throw throwable0;
            }
        }
        catch(Throwable throwable0) {
            StreamUtils.closeQuietly(bufferedReader0);
            throw throwable0;
        }
        bufferedReader0 = bufferedReader1;
        StreamUtils.closeQuietly(bufferedReader0);
        throw throwable0;
    label_20:
        StreamUtils.closeQuietly(bufferedReader1);
    }

    protected Texture loadTexture(FileHandle fileHandle0) {
        return new Texture(fileHandle0, false);
    }

    protected ParticleEmitter newEmitter(ParticleEmitter particleEmitter0) {
        return new ParticleEmitter(particleEmitter0);
    }

    protected ParticleEmitter newEmitter(BufferedReader bufferedReader0) throws IOException {
        return new ParticleEmitter(bufferedReader0);
    }

    public void preAllocateParticles() {
        for(Object object0: this.emitters) {
            ((ParticleEmitter)object0).preAllocateParticles();
        }
    }

    public void reset() {
        this.reset(true);
    }

    public void reset(boolean z) {
        int v = this.emitters.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleEmitter)this.emitters.get(v1)).reset();
        }
        if(z && (this.xSizeScale != 1.0f || this.ySizeScale != 1.0f || this.motionScale != 1.0f)) {
            this.scaleEffect(1.0f / this.xSizeScale, 1.0f / this.ySizeScale, 1.0f / this.motionScale);
            this.motionScale = 1.0f;
            this.ySizeScale = 1.0f;
            this.xSizeScale = 1.0f;
        }
    }

    public void save(Writer writer0) throws IOException {
        int v = this.emitters.size;
        int v1 = 0;
        for(int v2 = 0; v1 < v; ++v2) {
            ParticleEmitter particleEmitter0 = (ParticleEmitter)this.emitters.get(v1);
            if(v2 > 0) {
                writer0.write("\n");
            }
            particleEmitter0.save(writer0);
            ++v1;
        }
    }

    public void scaleEffect(float f) {
        this.scaleEffect(f, f, f);
    }

    public void scaleEffect(float f, float f1) {
        this.scaleEffect(f, f, f1);
    }

    public void scaleEffect(float f, float f1, float f2) {
        this.xSizeScale *= f;
        this.ySizeScale *= f1;
        this.motionScale *= f2;
        for(Object object0: this.emitters) {
            ((ParticleEmitter)object0).scaleSize(f, f1);
            ((ParticleEmitter)object0).scaleMotion(f2);
        }
    }

    public void setDuration(int v) {
        int v1 = this.emitters.size;
        for(int v2 = 0; v2 < v1; ++v2) {
            ParticleEmitter particleEmitter0 = (ParticleEmitter)this.emitters.get(v2);
            particleEmitter0.setContinuous(false);
            particleEmitter0.duration = (float)v;
            particleEmitter0.durationTimer = 0.0f;
        }
    }

    public void setEmittersCleanUpBlendFunction(boolean z) {
        int v = this.emitters.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleEmitter)this.emitters.get(v1)).setCleansUpBlendFunction(z);
        }
    }

    public void setFlip(boolean z, boolean z1) {
        int v = this.emitters.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleEmitter)this.emitters.get(v1)).setFlip(z, z1);
        }
    }

    public void setPosition(float f, float f1) {
        int v = this.emitters.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleEmitter)this.emitters.get(v1)).setPosition(f, f1);
        }
    }

    public void start() {
        int v = this.emitters.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleEmitter)this.emitters.get(v1)).start();
        }
    }

    public void update(float f) {
        int v = this.emitters.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((ParticleEmitter)this.emitters.get(v1)).update(f);
        }
    }
}

