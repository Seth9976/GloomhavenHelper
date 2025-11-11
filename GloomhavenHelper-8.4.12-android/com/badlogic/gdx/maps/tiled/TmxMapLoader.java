package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.ImageResolver.AssetManagerImageResolver;
import com.badlogic.gdx.maps.ImageResolver.DirectImageResolver;
import com.badlogic.gdx.maps.ImageResolver;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader.Element;

public class TmxMapLoader extends BaseTmxMapLoader {
    public static class Parameters extends com.badlogic.gdx.maps.tiled.BaseTmxMapLoader.Parameters {
    }

    public TmxMapLoader() {
        super(new InternalFileHandleResolver());
    }

    public TmxMapLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
    }

    @Override  // com.badlogic.gdx.maps.tiled.BaseTmxMapLoader
    protected void addStaticTiles(FileHandle fileHandle0, ImageResolver imageResolver0, TiledMapTileSet tiledMapTileSet0, Element xmlReader$Element0, Array array0, String s, int v, int v1, int v2, int v3, int v4, String s1, int v5, int v6, String s2, int v7, int v8, FileHandle fileHandle1) {
        MapProperties mapProperties0 = tiledMapTileSet0.getProperties();
        if(fileHandle1 != null) {
            TextureRegion textureRegion0 = imageResolver0.getImage(fileHandle1.path());
            mapProperties0.put("imagesource", s2);
            mapProperties0.put("imagewidth", v7);
            mapProperties0.put("imageheight", v8);
            mapProperties0.put("tilewidth", v1);
            mapProperties0.put("tileheight", v2);
            mapProperties0.put("margin", v4);
            mapProperties0.put("spacing", v3);
            int v9 = textureRegion0.getRegionWidth();
            int v10 = textureRegion0.getRegionHeight();
            int v11 = v;
            int v12 = v4;
            while(v12 <= v10 - v2) {
                int v13 = v4;
                int v14;
                for(v14 = v11; v13 <= v9 - v1; ++v14) {
                    this.addStaticTiledMapTile(tiledMapTileSet0, new TextureRegion(textureRegion0, v13, v12, v1, v2), v14, ((float)v5), ((float)v6));
                    v13 += v1 + v3;
                }
                v12 += v2 + v3;
                v11 = v14;
            }
            return;
        }
        FileHandle fileHandle2 = null;
        for(Object object0: array0) {
            Element xmlReader$Element1 = ((Element)object0).getChildByName("image");
            if(xmlReader$Element1 != null) {
                String s3 = xmlReader$Element1.getAttribute("source");
                fileHandle2 = s1 == null ? TmxMapLoader.getRelativeFileHandle(fileHandle0, s3) : TmxMapLoader.getRelativeFileHandle(TmxMapLoader.getRelativeFileHandle(fileHandle0, s1), s3);
            }
            this.addStaticTiledMapTile(tiledMapTileSet0, imageResolver0.getImage(fileHandle2.path()), v + ((Element)object0).getIntAttribute("id"), ((float)v5), ((float)v6));
        }
    }

    @Override  // com.badlogic.gdx.maps.tiled.BaseTmxMapLoader
    protected Array getDependencyAssetDescriptors(FileHandle fileHandle0, TextureParameter textureLoader$TextureParameter0) {
        Array array0 = new Array();
        for(Object object0: this.getDependencyFileHandles(fileHandle0)) {
            array0.add(new AssetDescriptor(((FileHandle)object0), Texture.class, textureLoader$TextureParameter0));
        }
        return array0;
    }

    protected Array getDependencyFileHandles(FileHandle fileHandle0) {
        Array array0 = new Array();
        for(Object object0: this.root.getChildrenByName("tileset")) {
            Element xmlReader$Element0 = (Element)object0;
            String s = xmlReader$Element0.getAttribute("source", null);
            if(s != null) {
                FileHandle fileHandle1 = TmxMapLoader.getRelativeFileHandle(fileHandle0, s);
                Element xmlReader$Element1 = this.xml.parse(fileHandle1);
                if(xmlReader$Element1.getChildByName("image") == null) {
                    for(Object object1: xmlReader$Element1.getChildrenByName("tile")) {
                        array0.add(TmxMapLoader.getRelativeFileHandle(fileHandle1, ((Element)object1).getChildByName("image").getAttribute("source")));
                    }
                }
                else {
                    array0.add(TmxMapLoader.getRelativeFileHandle(fileHandle1, xmlReader$Element1.getChildByName("image").getAttribute("source")));
                }
            }
            else if(xmlReader$Element0.getChildByName("image") == null) {
                for(Object object2: xmlReader$Element0.getChildrenByName("tile")) {
                    array0.add(TmxMapLoader.getRelativeFileHandle(fileHandle0, ((Element)object2).getChildByName("image").getAttribute("source")));
                }
            }
            else {
                array0.add(TmxMapLoader.getRelativeFileHandle(fileHandle0, xmlReader$Element0.getChildByName("image").getAttribute("source")));
            }
        }
        for(Object object3: this.root.getChildrenByName("imagelayer")) {
            String s1 = ((Element)object3).getChildByName("image").getAttribute("source", null);
            if(s1 != null) {
                array0.add(TmxMapLoader.getRelativeFileHandle(fileHandle0, s1));
            }
        }
        return array0;
    }

    public TiledMap load(String s) {
        return this.load(s, new Parameters());
    }

    public TiledMap load(String s, Parameters tmxMapLoader$Parameters0) {
        FileHandle fileHandle0 = this.resolve(s);
        this.root = this.xml.parse(fileHandle0);
        ObjectMap objectMap0 = new ObjectMap();
        for(Object object0: this.getDependencyFileHandles(fileHandle0)) {
            Texture texture0 = new Texture(((FileHandle)object0), tmxMapLoader$Parameters0.generateMipMaps);
            texture0.setFilter(tmxMapLoader$Parameters0.textureMinFilter, tmxMapLoader$Parameters0.textureMagFilter);
            objectMap0.put(((FileHandle)object0).path(), texture0);
        }
        TiledMap tiledMap0 = this.loadTiledMap(fileHandle0, tmxMapLoader$Parameters0, new DirectImageResolver(objectMap0));
        tiledMap0.setOwnedResources(objectMap0.values().toArray());
        return tiledMap0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        this.loadAsync(assetManager0, s, fileHandle0, ((Parameters)assetLoaderParameters0));
    }

    public void loadAsync(AssetManager assetManager0, String s, FileHandle fileHandle0, Parameters tmxMapLoader$Parameters0) {
        this.map = this.loadTiledMap(fileHandle0, tmxMapLoader$Parameters0, new AssetManagerImageResolver(assetManager0));
    }

    public TiledMap loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, Parameters tmxMapLoader$Parameters0) {
        return this.map;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Object loadSync(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.loadSync(assetManager0, s, fileHandle0, ((Parameters)assetLoaderParameters0));
    }
}

