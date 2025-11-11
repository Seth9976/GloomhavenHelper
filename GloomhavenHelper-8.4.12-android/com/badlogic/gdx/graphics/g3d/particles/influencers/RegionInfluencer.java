package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray.FloatChannel;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData.SaveData;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public abstract class RegionInfluencer extends Influencer {
    public static class Animated extends RegionInfluencer {
        FloatChannel lifeChannel;

        public Animated() {
        }

        public Animated(Texture texture0) {
            super(texture0);
        }

        public Animated(TextureRegion textureRegion0) {
            super(new TextureRegion[]{textureRegion0});
        }

        public Animated(Animated regionInfluencer$Animated0) {
            super(regionInfluencer$Animated0);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.influencers.RegionInfluencer
        public void allocateChannels() {
            super.allocateChannels();
            this.lifeChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.Life);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return this.copy();
        }

        public Animated copy() {
            return new Animated(this);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int v = this.controller.particles.size * this.regionChannel.strideSize;
            int v2 = 0;
            for(int v1 = 2; v2 < v; v1 += this.lifeChannel.strideSize) {
                AspectTextureRegion regionInfluencer$AspectTextureRegion0 = (AspectTextureRegion)this.regions.get(((int)(this.lifeChannel.data[v1] * ((float)(this.regions.size - 1)))));
                this.regionChannel.data[v2] = regionInfluencer$AspectTextureRegion0.u;
                this.regionChannel.data[v2 + 1] = regionInfluencer$AspectTextureRegion0.v;
                this.regionChannel.data[v2 + 2] = regionInfluencer$AspectTextureRegion0.u2;
                this.regionChannel.data[v2 + 3] = regionInfluencer$AspectTextureRegion0.v2;
                this.regionChannel.data[v2 + 4] = 0.5f;
                this.regionChannel.data[v2 + 5] = regionInfluencer$AspectTextureRegion0.halfInvAspectRatio;
                v2 += this.regionChannel.strideSize;
            }
        }
    }

    public static class AspectTextureRegion {
        public float halfInvAspectRatio;
        public String imageName;
        public float u;
        public float u2;
        public float v;
        public float v2;

        public AspectTextureRegion() {
        }

        public AspectTextureRegion(TextureRegion textureRegion0) {
            this.set(textureRegion0);
        }

        public AspectTextureRegion(AspectTextureRegion regionInfluencer$AspectTextureRegion0) {
            this.set(regionInfluencer$AspectTextureRegion0);
        }

        public void set(TextureRegion textureRegion0) {
            this.u = textureRegion0.getU();
            this.v = textureRegion0.getV();
            this.u2 = textureRegion0.getU2();
            this.v2 = textureRegion0.getV2();
            this.halfInvAspectRatio = ((float)textureRegion0.getRegionHeight()) / ((float)textureRegion0.getRegionWidth()) * 0.5f;
            if(textureRegion0 instanceof AtlasRegion) {
                this.imageName = ((AtlasRegion)textureRegion0).name;
            }
        }

        public void set(AspectTextureRegion regionInfluencer$AspectTextureRegion0) {
            this.u = regionInfluencer$AspectTextureRegion0.u;
            this.v = regionInfluencer$AspectTextureRegion0.v;
            this.u2 = regionInfluencer$AspectTextureRegion0.u2;
            this.v2 = regionInfluencer$AspectTextureRegion0.v2;
            this.halfInvAspectRatio = regionInfluencer$AspectTextureRegion0.halfInvAspectRatio;
            this.imageName = regionInfluencer$AspectTextureRegion0.imageName;
        }

        public void updateUV(TextureAtlas textureAtlas0) {
            String s = this.imageName;
            if(s == null) {
                return;
            }
            AtlasRegion textureAtlas$AtlasRegion0 = textureAtlas0.findRegion(s);
            this.u = textureAtlas$AtlasRegion0.getU();
            this.v = textureAtlas$AtlasRegion0.getV();
            this.u2 = textureAtlas$AtlasRegion0.getU2();
            this.v2 = textureAtlas$AtlasRegion0.getV2();
            this.halfInvAspectRatio = ((float)textureAtlas$AtlasRegion0.getRegionHeight()) / ((float)textureAtlas$AtlasRegion0.getRegionWidth()) * 0.5f;
        }
    }

    public static class Random extends RegionInfluencer {
        public Random() {
        }

        public Random(Texture texture0) {
            super(texture0);
        }

        public Random(TextureRegion textureRegion0) {
            super(new TextureRegion[]{textureRegion0});
        }

        public Random(Random regionInfluencer$Random0) {
            super(regionInfluencer$Random0);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int v, int v1) {
            int v2 = v * this.regionChannel.strideSize;
            int v3 = v1 * this.regionChannel.strideSize + v2;
            while(v2 < v3) {
                AspectTextureRegion regionInfluencer$AspectTextureRegion0 = (AspectTextureRegion)this.regions.random();
                this.regionChannel.data[v2] = regionInfluencer$AspectTextureRegion0.u;
                this.regionChannel.data[v2 + 1] = regionInfluencer$AspectTextureRegion0.v;
                this.regionChannel.data[v2 + 2] = regionInfluencer$AspectTextureRegion0.u2;
                this.regionChannel.data[v2 + 3] = regionInfluencer$AspectTextureRegion0.v2;
                this.regionChannel.data[v2 + 4] = 0.5f;
                this.regionChannel.data[v2 + 5] = regionInfluencer$AspectTextureRegion0.halfInvAspectRatio;
                v2 += this.regionChannel.strideSize;
            }
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return this.copy();
        }

        public Random copy() {
            return new Random(this);
        }
    }

    public static class Single extends RegionInfluencer {
        public Single() {
        }

        public Single(Texture texture0) {
            super(texture0);
        }

        public Single(TextureRegion textureRegion0) {
            super(new TextureRegion[]{textureRegion0});
        }

        public Single(Single regionInfluencer$Single0) {
            super(regionInfluencer$Single0);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return this.copy();
        }

        public Single copy() {
            return new Single(this);
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void init() {
            AspectTextureRegion regionInfluencer$AspectTextureRegion0 = ((AspectTextureRegion[])this.regions.items)[0];
            int v1 = this.controller.emitter.maxParticleCount * this.regionChannel.strideSize;
            for(int v = 0; v < v1; v += this.regionChannel.strideSize) {
                this.regionChannel.data[v] = regionInfluencer$AspectTextureRegion0.u;
                this.regionChannel.data[v + 1] = regionInfluencer$AspectTextureRegion0.v;
                this.regionChannel.data[v + 2] = regionInfluencer$AspectTextureRegion0.u2;
                this.regionChannel.data[v + 3] = regionInfluencer$AspectTextureRegion0.v2;
                this.regionChannel.data[v + 4] = 0.5f;
                this.regionChannel.data[v + 5] = regionInfluencer$AspectTextureRegion0.halfInvAspectRatio;
            }
        }
    }

    private static final String ASSET_DATA = "atlasAssetData";
    public String atlasName;
    FloatChannel regionChannel;
    public Array regions;

    public RegionInfluencer() {
        this(1);
        AspectTextureRegion regionInfluencer$AspectTextureRegion0 = new AspectTextureRegion();
        regionInfluencer$AspectTextureRegion0.v = 0.0f;
        regionInfluencer$AspectTextureRegion0.u = 0.0f;
        regionInfluencer$AspectTextureRegion0.v2 = 1.0f;
        regionInfluencer$AspectTextureRegion0.u2 = 1.0f;
        regionInfluencer$AspectTextureRegion0.halfInvAspectRatio = 0.5f;
        this.regions.add(regionInfluencer$AspectTextureRegion0);
    }

    public RegionInfluencer(int v) {
        this.regions = new Array(false, v, AspectTextureRegion.class);
    }

    public RegionInfluencer(Texture texture0) {
        this(new TextureRegion[]{new TextureRegion(texture0)});
    }

    public RegionInfluencer(RegionInfluencer regionInfluencer0) {
        this(regionInfluencer0.regions.size);
        this.regions.ensureCapacity(regionInfluencer0.regions.size);
        for(int v = 0; v < regionInfluencer0.regions.size; ++v) {
            this.regions.add(new AspectTextureRegion(((AspectTextureRegion)regionInfluencer0.regions.get(v))));
        }
    }

    public RegionInfluencer(TextureRegion[] arr_textureRegion) {
        this.setAtlasName(null);
        this.regions = new Array(false, arr_textureRegion.length, AspectTextureRegion.class);
        this.add(arr_textureRegion);
    }

    public void add(TextureRegion[] arr_textureRegion) {
        this.regions.ensureCapacity(arr_textureRegion.length);
        for(int v = 0; v < arr_textureRegion.length; ++v) {
            this.regions.add(new AspectTextureRegion(arr_textureRegion[v]));
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.regionChannel = (FloatChannel)this.controller.particles.addChannel(ParticleChannels.TextureRegion);
    }

    public void clear() {
        this.atlasName = null;
        this.regions.clear();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void load(AssetManager assetManager0, ResourceData resourceData0) {
        super.load(assetManager0, resourceData0);
        SaveData resourceData$SaveData0 = resourceData0.getSaveData("atlasAssetData");
        if(resourceData$SaveData0 == null) {
            return;
        }
        TextureAtlas textureAtlas0 = (TextureAtlas)assetManager0.get(resourceData$SaveData0.loadAsset());
        for(Object object0: this.regions) {
            ((AspectTextureRegion)object0).updateUV(textureAtlas0);
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void read(Json json0, JsonValue jsonValue0) {
        this.regions.clear();
        this.regions.addAll(((Array)json0.readValue("regions", Array.class, AspectTextureRegion.class, jsonValue0)));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void save(AssetManager assetManager0, ResourceData resourceData0) {
        super.save(assetManager0, resourceData0);
        if(this.atlasName != null) {
            SaveData resourceData$SaveData0 = resourceData0.getSaveData("atlasAssetData");
            if(resourceData$SaveData0 == null) {
                resourceData$SaveData0 = resourceData0.createSaveData("atlasAssetData");
            }
            resourceData$SaveData0.saveAsset(this.atlasName, TextureAtlas.class);
        }
    }

    public void setAtlasName(String s) {
        this.atlasName = s;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void write(Json json0) {
        json0.writeValue("regions", this.regions, Array.class, AspectTextureRegion.class);
    }
}

