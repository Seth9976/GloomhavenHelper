package com.badlogic.gdx.graphics.g3d.particles.batches;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderData;

public interface ParticleBatch extends RenderableProvider, ResourceData.Configurable {
   void begin();

   void draw(ParticleControllerRenderData var1);

   void end();

   @Override
   void save(AssetManager var1, ResourceData var2);

   @Override
   void load(AssetManager var1, ResourceData var2);
}
