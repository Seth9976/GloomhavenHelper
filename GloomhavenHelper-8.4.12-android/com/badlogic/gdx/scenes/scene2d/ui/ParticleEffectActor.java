package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class ParticleEffectActor extends Actor implements Disposable {
    private boolean autoRemove;
    protected boolean isRunning;
    protected float lastDelta;
    protected boolean ownsEffect;
    private final ParticleEffect particleEffect;
    private boolean resetOnStart;

    public ParticleEffectActor(FileHandle fileHandle0, FileHandle fileHandle1) {
        this.particleEffect = new ParticleEffect();
        this.particleEffect.load(fileHandle0, fileHandle1);
        this.ownsEffect = true;
    }

    public ParticleEffectActor(FileHandle fileHandle0, TextureAtlas textureAtlas0) {
        this.particleEffect = new ParticleEffect();
        this.particleEffect.load(fileHandle0, textureAtlas0);
        this.ownsEffect = true;
    }

    public ParticleEffectActor(ParticleEffect particleEffect0, boolean z) {
        this.particleEffect = particleEffect0;
        this.resetOnStart = z;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public void act(float f) {
        super.act(f);
        this.lastDelta += f;
        if(this.autoRemove && this.particleEffect.isComplete()) {
            this.remove();
        }
    }

    public void allowCompletion() {
        this.particleEffect.allowCompletion();
    }

    public void cancel() {
        this.isRunning = true;
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if(this.ownsEffect) {
            this.particleEffect.dispose();
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch0, float f) {
        float f1 = this.getX();
        float f2 = this.getY();
        this.particleEffect.setPosition(f1, f2);
        float f3 = this.lastDelta;
        if(f3 > 0.0f) {
            this.particleEffect.update(f3);
            this.lastDelta = 0.0f;
        }
        if(this.isRunning) {
            this.particleEffect.draw(batch0);
            this.isRunning = !this.particleEffect.isComplete();
        }
    }

    public ParticleEffect getEffect() {
        return this.particleEffect;
    }

    public boolean isAutoRemove() {
        return this.autoRemove;
    }

    public boolean isResetOnStart() {
        return this.resetOnStart;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    protected void scaleChanged() {
        super.scaleChanged();
        float f = this.getScaleX();
        float f1 = this.getScaleY();
        float f2 = this.getScaleY();
        this.particleEffect.scaleEffect(f, f1, f2);
    }

    public ParticleEffectActor setAutoRemove(boolean z) {
        this.autoRemove = z;
        return this;
    }

    public ParticleEffectActor setResetOnStart(boolean z) {
        this.resetOnStart = z;
        return this;
    }

    public void start() {
        this.isRunning = true;
        if(this.resetOnStart) {
            this.particleEffect.reset(false);
        }
        this.particleEffect.start();
    }
}

