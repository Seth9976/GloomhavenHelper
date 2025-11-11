package com.badlogic.gdx.graphics.g3d.particles.batches;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderData;

public interface ParticleBatch extends RenderableProvider, Configurable {
    void begin();

    void draw(ParticleControllerRenderData arg1);

    void end();

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ResourceData$Configurable
    void load(AssetManager arg1, ResourceData arg2);

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ResourceData$Configurable
    void save(AssetManager arg1, ResourceData arg2);
}

