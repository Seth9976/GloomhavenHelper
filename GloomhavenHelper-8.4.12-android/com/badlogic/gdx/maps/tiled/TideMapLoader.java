package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.ImageResolver.AssetManagerImageResolver;
import com.badlogic.gdx.maps.ImageResolver.DirectImageResolver;
import com.badlogic.gdx.maps.ImageResolver;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.badlogic.gdx.utils.XmlReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class TideMapLoader extends SynchronousAssetLoader {
    public static class Parameters extends AssetLoaderParameters {
    }

    private Element root;
    private XmlReader xml;

    public TideMapLoader() {
        super(new InternalFileHandleResolver());
        this.xml = new XmlReader();
    }

    public TideMapLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
        this.xml = new XmlReader();
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((Parameters)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, Parameters tideMapLoader$Parameters0) {
        Array array0 = new Array();
        try {
            this.root = this.xml.parse(fileHandle0);
            for(Object object0: this.loadTileSheets(this.root, fileHandle0)) {
                array0.add(new AssetDescriptor(((FileHandle)object0).path(), Texture.class));
            }
            return array0;
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException("Couldn\'t load tilemap \'" + s + "\'", iOException0);
        }
    }

    private static FileHandle getRelativeFileHandle(FileHandle fileHandle0, String s) {
        StringTokenizer stringTokenizer0 = new StringTokenizer(s, "\\/");
        FileHandle fileHandle1;
        for(fileHandle1 = fileHandle0.parent(); stringTokenizer0.hasMoreElements(); fileHandle1 = s1.equals("..") ? fileHandle1.parent() : fileHandle1.child(s1)) {
            String s1 = stringTokenizer0.nextToken();
        }
        return fileHandle1;
    }

    public TiledMap load(AssetManager assetManager0, String s, FileHandle fileHandle0, Parameters tideMapLoader$Parameters0) {
        try {
            return this.loadMap(this.root, fileHandle0, new AssetManagerImageResolver(assetManager0));
        }
        catch(Exception exception0) {
            throw new GdxRuntimeException("Couldn\'t load tilemap \'" + s + "\'", exception0);
        }
    }

    public TiledMap load(String s) {
        try {
            FileHandle fileHandle0 = this.resolve(s);
            this.root = this.xml.parse(fileHandle0);
            ObjectMap objectMap0 = new ObjectMap();
            for(Object object0: this.loadTileSheets(this.root, fileHandle0)) {
                objectMap0.put(((FileHandle)object0).path(), new Texture(((FileHandle)object0)));
            }
            DirectImageResolver imageResolver$DirectImageResolver0 = new DirectImageResolver(objectMap0);
            TiledMap tiledMap0 = this.loadMap(this.root, fileHandle0, imageResolver$DirectImageResolver0);
            tiledMap0.setOwnedResources(objectMap0.values().toArray());
            return tiledMap0;
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException("Couldn\'t load tilemap \'" + s + "\'", iOException0);
        }
    }

    @Override  // com.badlogic.gdx.assets.loaders.SynchronousAssetLoader
    public Object load(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.load(assetManager0, s, fileHandle0, ((Parameters)assetLoaderParameters0));
    }

    private void loadLayer(TiledMap tiledMap0, Element xmlReader$Element0) {
        Element xmlReader$Element4;
        int v10;
        Array array1;
        if(xmlReader$Element0.getName().equals("Layer")) {
            String s = xmlReader$Element0.getAttribute("Id");
            String s1 = xmlReader$Element0.getAttribute("Visible");
            Element xmlReader$Element1 = xmlReader$Element0.getChildByName("Dimensions");
            String s2 = xmlReader$Element1.getAttribute("LayerSize");
            String s3 = xmlReader$Element1.getAttribute("TileSize");
            String[] arr_s = s2.split(" x ");
            int v = Integer.parseInt(arr_s[0]);
            int v1 = Integer.parseInt(arr_s[1]);
            String[] arr_s1 = s3.split(" x ");
            TiledMapTileLayer tiledMapTileLayer0 = new TiledMapTileLayer(v, v1, Integer.parseInt(arr_s1[0]), Integer.parseInt(arr_s1[1]));
            tiledMapTileLayer0.setName(s);
            tiledMapTileLayer0.setVisible(s1.equalsIgnoreCase("True"));
            Array array0 = xmlReader$Element0.getChildByName("TileArray").getChildrenByName("Row");
            TiledMapTileSets tiledMapTileSets0 = tiledMap0.getTileSets();
            int v2 = array0.size;
            TiledMapTileSet tiledMapTileSet0 = null;
            int v3 = 0;
            for(int v4 = 0; v3 < v2; v4 = v7) {
                Element xmlReader$Element2 = (Element)array0.get(v3);
                int v5 = v2 - 1 - v3;
                int v6 = xmlReader$Element2.getChildCount();
                TiledMapTileSet tiledMapTileSet1 = tiledMapTileSet0;
                int v7 = v4;
                int v8 = 0;
                int v9 = 0;
                while(v8 < v6) {
                    Element xmlReader$Element3 = xmlReader$Element2.getChild(v8);
                    String s4 = xmlReader$Element3.getName();
                    if(s4.equals("TileSheet")) {
                        tiledMapTileSet1 = tiledMapTileSets0.getTileSet(xmlReader$Element3.getAttribute("Ref"));
                        v7 = (int)(((Integer)tiledMapTileSet1.getProperties().get("firstgid", Integer.class)));
                        array1 = array0;
                        v10 = v2;
                        xmlReader$Element4 = xmlReader$Element2;
                    }
                    else if(s4.equals("Null")) {
                        v9 += xmlReader$Element3.getIntAttribute("Count");
                        array1 = array0;
                        v10 = v2;
                        xmlReader$Element4 = xmlReader$Element2;
                    }
                    else if(s4.equals("Static")) {
                        Cell tiledMapTileLayer$Cell0 = new Cell();
                        tiledMapTileLayer$Cell0.setTile(tiledMapTileSet1.getTile(xmlReader$Element3.getIntAttribute("Index") + v7));
                        tiledMapTileLayer0.setCell(v9, v5, tiledMapTileLayer$Cell0);
                        array1 = array0;
                        v10 = v2;
                        xmlReader$Element4 = xmlReader$Element2;
                        ++v9;
                    }
                    else if(s4.equals("Animated")) {
                        int v11 = xmlReader$Element3.getInt("Interval");
                        Element xmlReader$Element5 = xmlReader$Element3.getChildByName("Frames");
                        Array array2 = new Array();
                        array1 = array0;
                        int v12 = xmlReader$Element5.getChildCount();
                        int v13 = v7;
                        TiledMapTileSet tiledMapTileSet2 = tiledMapTileSet1;
                        for(int v14 = 0; v14 < v12; ++v14) {
                            Element xmlReader$Element6 = xmlReader$Element5.getChild(v14);
                            String s5 = xmlReader$Element6.getName();
                            if(s5.equals("TileSheet")) {
                                tiledMapTileSet2 = tiledMapTileSets0.getTileSet(xmlReader$Element6.getAttribute("Ref"));
                                v13 = (int)(((Integer)tiledMapTileSet2.getProperties().get("firstgid", Integer.class)));
                            }
                            else if(s5.equals("Static")) {
                                array2.add(((StaticTiledMapTile)tiledMapTileSet2.getTile(v13 + xmlReader$Element6.getIntAttribute("Index"))));
                            }
                        }
                        v10 = v2;
                        xmlReader$Element4 = xmlReader$Element2;
                        Cell tiledMapTileLayer$Cell1 = new Cell();
                        tiledMapTileLayer$Cell1.setTile(new AnimatedTiledMapTile(((float)v11) / 1000.0f, array2));
                        tiledMapTileLayer0.setCell(v9, v5, tiledMapTileLayer$Cell1);
                        ++v9;
                        tiledMapTileSet1 = tiledMapTileSet2;
                        v7 = v13;
                    }
                    else {
                        array1 = array0;
                        v10 = v2;
                        xmlReader$Element4 = xmlReader$Element2;
                    }
                    ++v8;
                    array0 = array1;
                    v2 = v10;
                    xmlReader$Element2 = xmlReader$Element4;
                }
                ++v3;
                tiledMapTileSet0 = tiledMapTileSet1;
            }
            Element xmlReader$Element7 = xmlReader$Element0.getChildByName("Properties");
            if(xmlReader$Element7 != null) {
                this.loadProperties(tiledMapTileLayer0.getProperties(), xmlReader$Element7);
            }
            tiledMap0.getLayers().add(tiledMapTileLayer0);
        }
    }

    private TiledMap loadMap(Element xmlReader$Element0, FileHandle fileHandle0, ImageResolver imageResolver0) {
        TiledMap tiledMap0 = new TiledMap();
        Element xmlReader$Element1 = xmlReader$Element0.getChildByName("Properties");
        if(xmlReader$Element1 != null) {
            this.loadProperties(tiledMap0.getProperties(), xmlReader$Element1);
        }
        for(Object object0: xmlReader$Element0.getChildByName("TileSheets").getChildrenByName("TileSheet")) {
            this.loadTileSheet(tiledMap0, ((Element)object0), fileHandle0, imageResolver0);
        }
        for(Object object1: xmlReader$Element0.getChildByName("Layers").getChildrenByName("Layer")) {
            this.loadLayer(tiledMap0, ((Element)object1));
        }
        return tiledMap0;
    }

    private void loadProperties(MapProperties mapProperties0, Element xmlReader$Element0) {
        if(xmlReader$Element0.getName().equals("Properties")) {
            for(Object object0: xmlReader$Element0.getChildrenByName("Property")) {
                String s = ((Element)object0).getAttribute("Key", null);
                String s1 = ((Element)object0).getAttribute("Type", null);
                String s2 = ((Element)object0).getText();
                if(s1.equals("Int32")) {
                    mapProperties0.put(s, Integer.parseInt(s2));
                }
                else if(s1.equals("String")) {
                    mapProperties0.put(s, s2);
                }
                else if(s1.equals("Boolean")) {
                    mapProperties0.put(s, Boolean.valueOf(s2.equalsIgnoreCase("true")));
                }
                else {
                    mapProperties0.put(s, s2);
                }
            }
        }
    }

    private void loadTileSheet(TiledMap tiledMap0, Element xmlReader$Element0, FileHandle fileHandle0, ImageResolver imageResolver0) {
        if(xmlReader$Element0.getName().equals("TileSheet")) {
            String s = xmlReader$Element0.getAttribute("Id");
            xmlReader$Element0.getChildByName("Description").getText();
            String s1 = xmlReader$Element0.getChildByName("ImageSource").getText();
            Element xmlReader$Element1 = xmlReader$Element0.getChildByName("Alignment");
            String s2 = xmlReader$Element1.getAttribute("SheetSize");
            String s3 = xmlReader$Element1.getAttribute("TileSize");
            String s4 = xmlReader$Element1.getAttribute("Margin");
            xmlReader$Element1.getAttribute("Spacing");
            String[] arr_s = s2.split(" x ");
            Integer.parseInt(arr_s[0]);
            int v = 1;
            Integer.parseInt(arr_s[1]);
            String[] arr_s1 = s3.split(" x ");
            int v1 = Integer.parseInt(arr_s1[0]);
            int v2 = Integer.parseInt(arr_s1[1]);
            String[] arr_s2 = s4.split(" x ");
            int v3 = Integer.parseInt(arr_s2[0]);
            int v4 = Integer.parseInt(arr_s2[1]);
            String[] arr_s3 = s4.split(" x ");
            int v5 = Integer.parseInt(arr_s3[0]);
            int v6 = Integer.parseInt(arr_s3[1]);
            TextureRegion textureRegion0 = imageResolver0.getImage(TideMapLoader.getRelativeFileHandle(fileHandle0, s1).path());
            TiledMapTileSets tiledMapTileSets0 = tiledMap0.getTileSets();
            for(Object object0: tiledMapTileSets0) {
                v += ((TiledMapTileSet)object0).size();
            }
            TiledMapTileSet tiledMapTileSet0 = new TiledMapTileSet();
            tiledMapTileSet0.setName(s);
            tiledMapTileSet0.getProperties().put("firstgid", v);
            int v7 = textureRegion0.getRegionWidth() - v1;
            int v8 = textureRegion0.getRegionHeight() - v2;
            int v9 = v;
            int v10 = v4;
            while(v10 <= v8) {
                int v11 = v9;
                for(int v12 = v3; v12 <= v7; v12 += v1 + v5) {
                    int v13 = v11;
                    StaticTiledMapTile staticTiledMapTile0 = new StaticTiledMapTile(new TextureRegion(textureRegion0, v12, v10, v1, v2));
                    staticTiledMapTile0.setId(v13);
                    v11 = v13 + 1;
                    tiledMapTileSet0.putTile(v13, staticTiledMapTile0);
                }
                v10 += v2 + v6;
                v9 = v11;
            }
            Element xmlReader$Element2 = xmlReader$Element0.getChildByName("Properties");
            if(xmlReader$Element2 != null) {
                this.loadProperties(tiledMapTileSet0.getProperties(), xmlReader$Element2);
            }
            tiledMapTileSets0.addTileSet(tiledMapTileSet0);
        }
    }

    private Array loadTileSheets(Element xmlReader$Element0, FileHandle fileHandle0) throws IOException {
        Array array0 = new Array();
        for(Object object0: xmlReader$Element0.getChildByName("TileSheets").getChildrenByName("TileSheet")) {
            array0.add(TideMapLoader.getRelativeFileHandle(fileHandle0, ((Element)object0).getChildByName("ImageSource").getText()));
        }
        return array0;
    }
}

