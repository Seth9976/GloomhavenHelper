package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.ImageResolver;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader.Element;

public class AtlasTmxMapLoader extends BaseTmxMapLoader {
    public interface AtlasResolver extends ImageResolver {
        public static class AssetManagerAtlasResolver implements AtlasResolver {
            private final AssetManager assetManager;
            private final String atlasName;

            public AssetManagerAtlasResolver(AssetManager assetManager0, String s) {
                this.assetManager = assetManager0;
                this.atlasName = s;
            }

            @Override  // com.badlogic.gdx.maps.tiled.AtlasTmxMapLoader$AtlasResolver
            public TextureAtlas getAtlas() {
                return (TextureAtlas)this.assetManager.get(this.atlasName, TextureAtlas.class);
            }

            @Override  // com.badlogic.gdx.maps.ImageResolver
            public TextureRegion getImage(String s) {
                return this.getAtlas().findRegion(s);
            }
        }

        public static class DirectAtlasResolver implements AtlasResolver {
            private final TextureAtlas atlas;

            public DirectAtlasResolver(TextureAtlas textureAtlas0) {
                this.atlas = textureAtlas0;
            }

            @Override  // com.badlogic.gdx.maps.tiled.AtlasTmxMapLoader$AtlasResolver
            public TextureAtlas getAtlas() {
                return this.atlas;
            }

            @Override  // com.badlogic.gdx.maps.ImageResolver
            public TextureRegion getImage(String s) {
                return this.atlas.findRegion(s);
            }
        }

        TextureAtlas getAtlas();
    }

    public static class AtlasTiledMapLoaderParameters extends Parameters {
        public boolean forceTextureFilters;

        public AtlasTiledMapLoaderParameters() {
            this.forceTextureFilters = false;
        }
    }

    protected AtlasResolver atlasResolver;
    protected Array trackedTextures;

    public AtlasTmxMapLoader() {
        super(new InternalFileHandleResolver());
        this.trackedTextures = new Array();
    }

    public AtlasTmxMapLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
        this.trackedTextures = new Array();
    }

    @Override  // com.badlogic.gdx.maps.tiled.BaseTmxMapLoader
    protected void addStaticTiles(FileHandle fileHandle0, ImageResolver imageResolver0, TiledMapTileSet tiledMapTileSet0, Element xmlReader$Element0, Array array0, String s, int v, int v1, int v2, int v3, int v4, String s1, int v5, int v6, String s2, int v7, int v8, FileHandle fileHandle1) {
        TextureAtlas textureAtlas0 = this.atlasResolver.getAtlas();
        for(Object object0: textureAtlas0.getTextures()) {
            this.trackedTextures.add(((Texture)object0));
        }
        MapProperties mapProperties0 = tiledMapTileSet0.getProperties();
        mapProperties0.put("imagesource", s2);
        mapProperties0.put("imagewidth", v7);
        mapProperties0.put("imageheight", v8);
        mapProperties0.put("tilewidth", v1);
        mapProperties0.put("tileheight", v2);
        mapProperties0.put("margin", v4);
        mapProperties0.put("spacing", v3);
        if(s2 != null && s2.length() > 0) {
            for(Object object1: textureAtlas0.findRegions(s)) {
                AtlasRegion textureAtlas$AtlasRegion0 = (AtlasRegion)object1;
                if(textureAtlas$AtlasRegion0 != null) {
                    int v9 = v + textureAtlas$AtlasRegion0.index;
                    if(v9 >= v && v9 <= v7 / v1 * (v8 / v2) + v - 1) {
                        this.addStaticTiledMapTile(tiledMapTileSet0, textureAtlas$AtlasRegion0, v9, ((float)v5), ((float)v6));
                    }
                }
            }
        }
        for(Object object2: array0) {
            Element xmlReader$Element1 = (Element)object2;
            int v10 = v + xmlReader$Element1.getIntAttribute("id", 0);
            if(tiledMapTileSet0.getTile(v10) == null) {
                Element xmlReader$Element2 = xmlReader$Element1.getChildByName("image");
                if(xmlReader$Element2 != null) {
                    String s3 = xmlReader$Element2.getAttribute("source");
                    String s4 = s3.substring(0, s3.lastIndexOf(46));
                    AtlasRegion textureAtlas$AtlasRegion1 = textureAtlas0.findRegion(s4);
                    if(textureAtlas$AtlasRegion1 == null) {
                        throw new GdxRuntimeException("Tileset atlasRegion not found: " + s4);
                    }
                    this.addStaticTiledMapTile(tiledMapTileSet0, textureAtlas$AtlasRegion1, v10, ((float)v5), ((float)v6));
                }
            }
        }
    }

    protected FileHandle getAtlasFileHandle(FileHandle fileHandle0) {
        String s = null;
        Element xmlReader$Element0 = this.root.getChildByName("properties");
        if(xmlReader$Element0 != null) {
            for(Object object0: xmlReader$Element0.getChildrenByName("property")) {
                Element xmlReader$Element1 = (Element)object0;
                if(xmlReader$Element1.getAttribute("name").startsWith("atlas")) {
                    s = xmlReader$Element1.getAttribute("value");
                    break;
                }
            }
        }
        if(s == null) {
            throw new GdxRuntimeException("The map is missing the \'atlas\' property");
        }
        FileHandle fileHandle1 = AtlasTmxMapLoader.getRelativeFileHandle(fileHandle0, s);
        if(!fileHandle1.exists()) {
            throw new GdxRuntimeException("The \'atlas\' file could not be found: \'" + s + "\'");
        }
        return fileHandle1;
    }

    @Override  // com.badlogic.gdx.maps.tiled.BaseTmxMapLoader
    protected Array getDependencyAssetDescriptors(FileHandle fileHandle0, TextureParameter textureLoader$TextureParameter0) {
        Array array0 = new Array();
        FileHandle fileHandle1 = this.getAtlasFileHandle(fileHandle0);
        if(fileHandle1 != null) {
            array0.add(new AssetDescriptor(fileHandle1, TextureAtlas.class));
        }
        return array0;
    }

    public TiledMap load(String s) {
        return this.load(s, new AtlasTiledMapLoaderParameters());
    }

    public TiledMap load(String s, AtlasTiledMapLoaderParameters atlasTmxMapLoader$AtlasTiledMapLoaderParameters0) {
        FileHandle fileHandle0 = this.resolve(s);
        this.root = this.xml.parse(fileHandle0);
        TextureAtlas textureAtlas0 = new TextureAtlas(this.getAtlasFileHandle(fileHandle0));
        this.atlasResolver = new DirectAtlasResolver(textureAtlas0);
        TiledMap tiledMap0 = this.loadTiledMap(fileHandle0, atlasTmxMapLoader$AtlasTiledMapLoaderParameters0, this.atlasResolver);
        tiledMap0.setOwnedResources(new Array(new TextureAtlas[]{textureAtlas0}));
        this.setTextureFilters(atlasTmxMapLoader$AtlasTiledMapLoaderParameters0.textureMinFilter, atlasTmxMapLoader$AtlasTiledMapLoaderParameters0.textureMagFilter);
        return tiledMap0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        this.loadAsync(assetManager0, s, fileHandle0, ((AtlasTiledMapLoaderParameters)assetLoaderParameters0));
    }

    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, AtlasTiledMapLoaderParameters atlasTmxMapLoader$AtlasTiledMapLoaderParameters0) {
        this.atlasResolver = new AssetManagerAtlasResolver(assetManager0, this.getAtlasFileHandle(fileHandle0).path());
        this.map = this.loadTiledMap(fileHandle0, atlasTmxMapLoader$AtlasTiledMapLoaderParameters0, this.atlasResolver);
    }

    public TiledMap loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, AtlasTiledMapLoaderParameters atlasTmxMapLoader$AtlasTiledMapLoaderParameters0) {
        if(atlasTmxMapLoader$AtlasTiledMapLoaderParameters0 != null) {
            this.setTextureFilters(atlasTmxMapLoader$AtlasTiledMapLoaderParameters0.textureMinFilter, atlasTmxMapLoader$AtlasTiledMapLoaderParameters0.textureMagFilter);
        }
        return this.map;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Object loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.loadSync(assetManager0, s, fileHandle0, ((AtlasTiledMapLoaderParameters)assetLoaderParameters0));
    }

    protected void setTextureFilters(TextureFilter texture$TextureFilter0, TextureFilter texture$TextureFilter1) {
        for(Object object0: this.trackedTextures) {
            ((Texture)object0).setFilter(texture$TextureFilter0, texture$TextureFilter1);
        }
        this.trackedTextures.clear();
    }
}

