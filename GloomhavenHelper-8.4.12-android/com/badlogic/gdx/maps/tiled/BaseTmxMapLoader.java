package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.ImageResolver;
import com.badlogic.gdx.maps.MapGroupLayer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.SerializationException;
import com.badlogic.gdx.utils.StreamUtils;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.badlogic.gdx.utils.XmlReader;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

public abstract class BaseTmxMapLoader extends AsynchronousAssetLoader {
    public static class Parameters extends AssetLoaderParameters {
        public boolean convertObjectToTileSpace;
        public boolean flipY;
        public boolean generateMipMaps;
        public TextureFilter textureMagFilter;
        public TextureFilter textureMinFilter;

        public Parameters() {
            this.generateMipMaps = false;
            this.textureMinFilter = TextureFilter.Nearest;
            this.textureMagFilter = TextureFilter.Nearest;
            this.convertObjectToTileSpace = false;
            this.flipY = true;
        }
    }

    protected static final int FLAG_FLIP_DIAGONALLY = 0x20000000;
    protected static final int FLAG_FLIP_HORIZONTALLY = 0x80000000;
    protected static final int FLAG_FLIP_VERTICALLY = 0x40000000;
    protected static final int MASK_CLEAR = 0xE0000000;
    protected boolean convertObjectToTileSpace;
    protected boolean flipY;
    protected TiledMap map;
    protected int mapHeightInPixels;
    protected int mapTileHeight;
    protected int mapTileWidth;
    protected int mapWidthInPixels;
    protected Element root;
    protected XmlReader xml;

    public BaseTmxMapLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
        this.xml = new XmlReader();
        this.flipY = true;
    }

    protected void addStaticTiledMapTile(TiledMapTileSet tiledMapTileSet0, TextureRegion textureRegion0, int v, float f, float f1) {
        StaticTiledMapTile staticTiledMapTile0 = new StaticTiledMapTile(textureRegion0);
        staticTiledMapTile0.setId(v);
        staticTiledMapTile0.setOffsetX(f);
        if(this.flipY) {
            f1 = -f1;
        }
        staticTiledMapTile0.setOffsetY(f1);
        tiledMapTileSet0.putTile(v, staticTiledMapTile0);
    }

    protected abstract void addStaticTiles(FileHandle arg1, ImageResolver arg2, TiledMapTileSet arg3, Element arg4, Array arg5, String arg6, int arg7, int arg8, int arg9, int arg10, int arg11, String arg12, int arg13, int arg14, String arg15, int arg16, int arg17, FileHandle arg18);

    protected void addTileObjectGroup(TiledMapTile tiledMapTile0, Element xmlReader$Element0) {
        Element xmlReader$Element1 = xmlReader$Element0.getChildByName("objectgroup");
        if(xmlReader$Element1 != null) {
            for(Object object0: xmlReader$Element1.getChildrenByName("object")) {
                this.loadObject(this.map, tiledMapTile0, ((Element)object0));
            }
        }
    }

    protected void addTileProperties(TiledMapTile tiledMapTile0, Element xmlReader$Element0) {
        String s = xmlReader$Element0.getAttribute("terrain", null);
        if(s != null) {
            tiledMapTile0.getProperties().put("terrain", s);
        }
        String s1 = xmlReader$Element0.getAttribute("probability", null);
        if(s1 != null) {
            tiledMapTile0.getProperties().put("probability", s1);
        }
        Element xmlReader$Element1 = xmlReader$Element0.getChildByName("properties");
        if(xmlReader$Element1 != null) {
            this.loadProperties(tiledMapTile0.getProperties(), xmlReader$Element1);
        }
    }

    protected Object castProperty(String s, String s1, String s2) {
        if(s2 == null) {
            return s1;
        }
        if(s2.equals("int")) {
            return Integer.valueOf(s1);
        }
        if(s2.equals("float")) {
            return Float.valueOf(s1);
        }
        if(s2.equals("bool")) {
            return Boolean.valueOf(s1);
        }
        if(!s2.equals("color")) {
            throw new GdxRuntimeException("Wrong type given for property " + s + ", given : " + s2 + ", supported : string, bool, int, float, color");
        }
        return Color.valueOf((s1.substring(3) + s1.substring(1, 3)));
    }

    protected AnimatedTiledMapTile createAnimatedTile(TiledMapTileSet tiledMapTileSet0, TiledMapTile tiledMapTile0, Element xmlReader$Element0, int v) {
        Element xmlReader$Element1 = xmlReader$Element0.getChildByName("animation");
        if(xmlReader$Element1 != null) {
            Array array0 = new Array();
            IntArray intArray0 = new IntArray();
            for(Object object0: xmlReader$Element1.getChildrenByName("frame")) {
                array0.add(((StaticTiledMapTile)tiledMapTileSet0.getTile(((Element)object0).getIntAttribute("tileid") + v)));
                intArray0.add(((Element)object0).getIntAttribute("duration"));
            }
            AnimatedTiledMapTile animatedTiledMapTile0 = new AnimatedTiledMapTile(intArray0, array0);
            animatedTiledMapTile0.setId(tiledMapTile0.getId());
            return animatedTiledMapTile0;
        }
        return null;
    }

    protected Cell createTileLayerCell(boolean z, boolean z1, boolean z2) {
        Cell tiledMapTileLayer$Cell0 = new Cell();
        if(z2) {
            if(z && z1) {
                tiledMapTileLayer$Cell0.setFlipHorizontally(true);
                tiledMapTileLayer$Cell0.setRotation(3);
                return tiledMapTileLayer$Cell0;
            }
            if(z) {
                tiledMapTileLayer$Cell0.setRotation(3);
                return tiledMapTileLayer$Cell0;
            }
            if(z1) {
                tiledMapTileLayer$Cell0.setRotation(1);
                return tiledMapTileLayer$Cell0;
            }
            tiledMapTileLayer$Cell0.setFlipVertically(true);
            tiledMapTileLayer$Cell0.setRotation(3);
            return tiledMapTileLayer$Cell0;
        }
        tiledMapTileLayer$Cell0.setFlipHorizontally(z);
        tiledMapTileLayer$Cell0.setFlipVertically(z1);
        return tiledMapTileLayer$Cell0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((Parameters)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, Parameters baseTmxMapLoader$Parameters0) {
        this.root = this.xml.parse(fileHandle0);
        TextureParameter textureLoader$TextureParameter0 = new TextureParameter();
        if(baseTmxMapLoader$Parameters0 != null) {
            textureLoader$TextureParameter0.genMipMaps = baseTmxMapLoader$Parameters0.generateMipMaps;
            textureLoader$TextureParameter0.minFilter = baseTmxMapLoader$Parameters0.textureMinFilter;
            textureLoader$TextureParameter0.magFilter = baseTmxMapLoader$Parameters0.textureMagFilter;
        }
        return this.getDependencyAssetDescriptors(fileHandle0, textureLoader$TextureParameter0);
    }

    protected abstract Array getDependencyAssetDescriptors(FileHandle arg1, TextureParameter arg2);

    protected static FileHandle getRelativeFileHandle(FileHandle fileHandle0, String s) {
        StringTokenizer stringTokenizer0 = new StringTokenizer(s, "\\/");
        FileHandle fileHandle1;
        for(fileHandle1 = fileHandle0.parent(); stringTokenizer0.hasMoreElements(); fileHandle1 = s1.equals("..") ? fileHandle1.parent() : fileHandle1.child(s1)) {
            String s1 = stringTokenizer0.nextToken();
        }
        return fileHandle1;
    }

    public static int[] getTileIds(Element xmlReader$Element0, int v, int v1) {
        ByteArrayInputStream byteArrayInputStream0;
        Element xmlReader$Element1 = xmlReader$Element0.getChildByName("data");
        String s = xmlReader$Element1.getAttribute("encoding", null);
        if(s == null) {
            throw new GdxRuntimeException("Unsupported encoding (XML) for TMX Layer Data");
        }
        int[] arr_v = new int[v * v1];
        if(s.equals("csv")) {
            String[] arr_s = xmlReader$Element1.getText().split(",");
            for(int v2 = 0; v2 < arr_s.length; ++v2) {
                arr_v[v2] = (int)Long.parseLong(arr_s[v2].trim());
            }
            return arr_v;
        }
        if(s.equals("base64")) {
            try {
                String s1 = xmlReader$Element1.getAttribute("compression", null);
                byte[] arr_b = Base64Coder.decode(xmlReader$Element1.getText());
                if(s1 == null) {
                    byteArrayInputStream0 = new ByteArrayInputStream(arr_b);
                }
                else if(!s1.equals("gzip")) {
                    if(!s1.equals("zlib")) {
                        throw new GdxRuntimeException("Unrecognised compression (" + s1 + ") for TMX Layer Data");
                    }
                    byteArrayInputStream0 = new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(arr_b)));
                }
                else {
                    byteArrayInputStream0 = new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(arr_b), arr_b.length));
                }
                byte[] arr_b1 = new byte[4];
                for(int v4 = 0; v4 < v1; ++v4) {
                    for(int v5 = 0; v5 < v; ++v5) {
                        int v6;
                        for(v6 = byteArrayInputStream0.read(arr_b1); v6 < 4; v6 += v7) {
                            int v7 = byteArrayInputStream0.read(arr_b1, v6, 4 - v6);
                            if(v7 == -1) {
                                break;
                            }
                        }
                        if(v6 != 4) {
                            throw new GdxRuntimeException("Error Reading TMX Layer Data: Premature end of tile data");
                        }
                        arr_v[v4 * v + v5] = arr_b1[0] & 0xFF | (arr_b1[1] & 0xFF) << 8 | (arr_b1[2] & 0xFF) << 16 | (arr_b1[3] & 0xFF) << 24;
                    }
                }
                return arr_v;
            }
            catch(IOException iOException0) {
                throw new GdxRuntimeException("Error Reading TMX Layer Data - IOException: " + iOException0.getMessage());
            }
            finally {
                StreamUtils.closeQuietly(byteArrayInputStream0);
            }
        }
        throw new GdxRuntimeException("Unrecognised encoding (" + s + ") for TMX Layer Data");
    }

    protected void loadBasicLayerInfo(MapLayer mapLayer0, Element xmlReader$Element0) {
        String s = xmlReader$Element0.getAttribute("name", null);
        float f = Float.parseFloat(xmlReader$Element0.getAttribute("opacity", "1.0"));
        boolean z = xmlReader$Element0.getIntAttribute("visible", 1) == 1;
        float f1 = xmlReader$Element0.getFloatAttribute("offsetx", 0.0f);
        float f2 = xmlReader$Element0.getFloatAttribute("offsety", 0.0f);
        mapLayer0.setName(s);
        mapLayer0.setOpacity(f);
        mapLayer0.setVisible(z);
        mapLayer0.setOffsetX(f1);
        mapLayer0.setOffsetY(f2);
    }

    protected void loadImageLayer(TiledMap tiledMap0, MapLayers mapLayers0, Element xmlReader$Element0, FileHandle fileHandle0, ImageResolver imageResolver0) {
        if(xmlReader$Element0.getName().equals("imagelayer")) {
            float f = xmlReader$Element0.hasAttribute("offsetx") ? Float.parseFloat(xmlReader$Element0.getAttribute("offsetx", "0")) : Float.parseFloat(xmlReader$Element0.getAttribute("x", "0"));
            float f1 = xmlReader$Element0.hasAttribute("offsety") ? Float.parseFloat(xmlReader$Element0.getAttribute("offsety", "0")) : Float.parseFloat(xmlReader$Element0.getAttribute("y", "0"));
            if(this.flipY) {
                f1 = ((float)this.mapHeightInPixels) - f1;
            }
            TextureRegion textureRegion0 = null;
            Element xmlReader$Element1 = xmlReader$Element0.getChildByName("image");
            if(xmlReader$Element1 != null) {
                textureRegion0 = imageResolver0.getImage(BaseTmxMapLoader.getRelativeFileHandle(fileHandle0, xmlReader$Element1.getAttribute("source")).path());
                f1 -= (float)textureRegion0.getRegionHeight();
            }
            TiledMapImageLayer tiledMapImageLayer0 = new TiledMapImageLayer(textureRegion0, f, f1);
            this.loadBasicLayerInfo(tiledMapImageLayer0, xmlReader$Element0);
            Element xmlReader$Element2 = xmlReader$Element0.getChildByName("properties");
            if(xmlReader$Element2 != null) {
                this.loadProperties(tiledMapImageLayer0.getProperties(), xmlReader$Element2);
            }
            mapLayers0.add(tiledMapImageLayer0);
        }
    }

    protected void loadLayer(TiledMap tiledMap0, MapLayers mapLayers0, Element xmlReader$Element0, FileHandle fileHandle0, ImageResolver imageResolver0) {
        String s = xmlReader$Element0.getName();
        if(s.equals("group")) {
            this.loadLayerGroup(tiledMap0, mapLayers0, xmlReader$Element0, fileHandle0, imageResolver0);
            return;
        }
        if(s.equals("layer")) {
            this.loadTileLayer(tiledMap0, mapLayers0, xmlReader$Element0);
            return;
        }
        if(s.equals("objectgroup")) {
            this.loadObjectGroup(tiledMap0, mapLayers0, xmlReader$Element0);
            return;
        }
        if(s.equals("imagelayer")) {
            this.loadImageLayer(tiledMap0, mapLayers0, xmlReader$Element0, fileHandle0, imageResolver0);
        }
    }

    protected void loadLayerGroup(TiledMap tiledMap0, MapLayers mapLayers0, Element xmlReader$Element0, FileHandle fileHandle0, ImageResolver imageResolver0) {
        if(xmlReader$Element0.getName().equals("group")) {
            MapGroupLayer mapGroupLayer0 = new MapGroupLayer();
            this.loadBasicLayerInfo(mapGroupLayer0, xmlReader$Element0);
            Element xmlReader$Element1 = xmlReader$Element0.getChildByName("properties");
            if(xmlReader$Element1 != null) {
                this.loadProperties(mapGroupLayer0.getProperties(), xmlReader$Element1);
            }
            int v1 = xmlReader$Element0.getChildCount();
            for(int v = 0; v < v1; ++v) {
                this.loadLayer(tiledMap0, mapGroupLayer0.getLayers(), xmlReader$Element0.getChild(v), fileHandle0, imageResolver0);
            }
            for(Object object0: mapGroupLayer0.getLayers()) {
                ((MapLayer)object0).setParent(mapGroupLayer0);
            }
            mapLayers0.add(mapGroupLayer0);
        }
    }

    protected void loadObject(TiledMap tiledMap0, MapLayer mapLayer0, Element xmlReader$Element0) {
        this.loadObject(tiledMap0, mapLayer0.getObjects(), xmlReader$Element0, ((float)this.mapHeightInPixels));
    }

    protected void loadObject(TiledMap tiledMap0, MapObjects mapObjects0, Element xmlReader$Element0, float f) {
        MapObject mapObject0;
        float f1 = 1.0f;
        if(xmlReader$Element0.getName().equals("object")) {
            float f2 = this.convertObjectToTileSpace ? 1.0f / ((float)this.mapTileWidth) : 1.0f;
            if(this.convertObjectToTileSpace) {
                f1 = 1.0f / ((float)this.mapTileHeight);
            }
            float f3 = xmlReader$Element0.getFloatAttribute("x", 0.0f) * f2;
            float f4 = (this.flipY ? f - xmlReader$Element0.getFloatAttribute("y", 0.0f) : xmlReader$Element0.getFloatAttribute("y", 0.0f)) * f1;
            float f5 = xmlReader$Element0.getFloatAttribute("width", 0.0f) * f2;
            float f6 = xmlReader$Element0.getFloatAttribute("height", 0.0f) * f1;
            boolean z = false;
            if(xmlReader$Element0.getChildCount() > 0) {
                Element xmlReader$Element1 = xmlReader$Element0.getChildByName("polygon");
                if(xmlReader$Element1 == null) {
                    Element xmlReader$Element2 = xmlReader$Element0.getChildByName("polyline");
                    if(xmlReader$Element2 != null) {
                        String[] arr_s2 = xmlReader$Element2.getAttribute("points").split(" ");
                        float[] arr_f1 = new float[arr_s2.length * 2];
                        for(int v1 = 0; v1 < arr_s2.length; ++v1) {
                            String[] arr_s3 = arr_s2[v1].split(",");
                            arr_f1[v1 * 2] = Float.parseFloat(arr_s3[0]) * f2;
                            arr_f1[v1 * 2 + 1] = Float.parseFloat(arr_s3[1]) * f1 * ((float)(this.flipY ? -1 : 1));
                        }
                        Polyline polyline0 = new Polyline(arr_f1);
                        polyline0.setPosition(f3, f4);
                        mapObject0 = new PolylineMapObject(polyline0);
                    }
                    else if(xmlReader$Element0.getChildByName("ellipse") == null) {
                        mapObject0 = null;
                    }
                    else {
                        mapObject0 = new EllipseMapObject(f3, (this.flipY ? f4 - f6 : f4), f5, f6);
                    }
                }
                else {
                    String[] arr_s = xmlReader$Element1.getAttribute("points").split(" ");
                    float[] arr_f = new float[arr_s.length * 2];
                    for(int v = 0; v < arr_s.length; ++v) {
                        String[] arr_s1 = arr_s[v].split(",");
                        arr_f[v * 2] = Float.parseFloat(arr_s1[0]) * f2;
                        arr_f[v * 2 + 1] = Float.parseFloat(arr_s1[1]) * f1 * ((float)(this.flipY ? -1 : 1));
                    }
                    Polygon polygon0 = new Polygon(arr_f);
                    polygon0.setPosition(f3, f4);
                    mapObject0 = new PolygonMapObject(polygon0);
                }
            }
            else {
                mapObject0 = null;
            }
            if(mapObject0 == null) {
                String s = xmlReader$Element0.getAttribute("gid", null);
                if(s == null) {
                    mapObject0 = new RectangleMapObject(f3, (this.flipY ? f4 - f6 : f4), f5, f6);
                }
                else {
                    int v2 = (int)Long.parseLong(s);
                    TiledMapTileMapObject tiledMapTileMapObject0 = new TiledMapTileMapObject(tiledMap0.getTileSets().getTile(0x1FFFFFFF & v2), (0x80000000 & v2) != 0, (0x40000000 & v2) != 0);
                    TextureRegion textureRegion0 = tiledMapTileMapObject0.getTextureRegion();
                    tiledMapTileMapObject0.getProperties().put("gid", v2);
                    tiledMapTileMapObject0.setX(f3);
                    tiledMapTileMapObject0.setY((this.flipY ? f4 : f4 - f6));
                    float f7 = xmlReader$Element0.getFloatAttribute("width", ((float)textureRegion0.getRegionWidth()));
                    float f8 = xmlReader$Element0.getFloatAttribute("height", ((float)textureRegion0.getRegionHeight()));
                    tiledMapTileMapObject0.setScaleX(f2 * (f7 / ((float)textureRegion0.getRegionWidth())));
                    tiledMapTileMapObject0.setScaleY(f1 * (f8 / ((float)textureRegion0.getRegionHeight())));
                    tiledMapTileMapObject0.setRotation(xmlReader$Element0.getFloatAttribute("rotation", 0.0f));
                    mapObject0 = tiledMapTileMapObject0;
                }
            }
            mapObject0.setName(xmlReader$Element0.getAttribute("name", null));
            String s1 = xmlReader$Element0.getAttribute("rotation", null);
            if(s1 != null) {
                mapObject0.getProperties().put("rotation", Float.parseFloat(s1));
            }
            String s2 = xmlReader$Element0.getAttribute("type", null);
            if(s2 != null) {
                mapObject0.getProperties().put("type", s2);
            }
            int v3 = xmlReader$Element0.getIntAttribute("id", 0);
            if(v3 != 0) {
                mapObject0.getProperties().put("id", v3);
            }
            mapObject0.getProperties().put("x", f3);
            if(mapObject0 instanceof TiledMapTileMapObject) {
                mapObject0.getProperties().put("y", f4);
            }
            else {
                MapProperties mapProperties0 = mapObject0.getProperties();
                if(this.flipY) {
                    f4 -= f6;
                }
                mapProperties0.put("y", f4);
            }
            mapObject0.getProperties().put("width", f5);
            mapObject0.getProperties().put("height", f6);
            if(xmlReader$Element0.getIntAttribute("visible", 1) == 1) {
                z = true;
            }
            mapObject0.setVisible(z);
            Element xmlReader$Element3 = xmlReader$Element0.getChildByName("properties");
            if(xmlReader$Element3 != null) {
                this.loadProperties(mapObject0.getProperties(), xmlReader$Element3);
            }
            mapObjects0.add(mapObject0);
        }
    }

    protected void loadObject(TiledMap tiledMap0, TiledMapTile tiledMapTile0, Element xmlReader$Element0) {
        this.loadObject(tiledMap0, tiledMapTile0.getObjects(), xmlReader$Element0, ((float)tiledMapTile0.getTextureRegion().getRegionHeight()));
    }

    protected void loadObjectGroup(TiledMap tiledMap0, MapLayers mapLayers0, Element xmlReader$Element0) {
        if(xmlReader$Element0.getName().equals("objectgroup")) {
            MapLayer mapLayer0 = new MapLayer();
            this.loadBasicLayerInfo(mapLayer0, xmlReader$Element0);
            Element xmlReader$Element1 = xmlReader$Element0.getChildByName("properties");
            if(xmlReader$Element1 != null) {
                this.loadProperties(mapLayer0.getProperties(), xmlReader$Element1);
            }
            for(Object object0: xmlReader$Element0.getChildrenByName("object")) {
                this.loadObject(tiledMap0, mapLayer0, ((Element)object0));
            }
            mapLayers0.add(mapLayer0);
        }
    }

    protected void loadProperties(MapProperties mapProperties0, Element xmlReader$Element0) {
        if(xmlReader$Element0 == null) {
            return;
        }
        if(xmlReader$Element0.getName().equals("properties")) {
            for(Object object0: xmlReader$Element0.getChildrenByName("property")) {
                Element xmlReader$Element1 = (Element)object0;
                String s = xmlReader$Element1.getAttribute("name", null);
                String s1 = xmlReader$Element1.getAttribute("value", null);
                String s2 = xmlReader$Element1.getAttribute("type", null);
                if(s1 == null) {
                    s1 = xmlReader$Element1.getText();
                }
                mapProperties0.put(s, this.castProperty(s, s1, s2));
            }
        }
    }

    protected void loadTileLayer(TiledMap tiledMap0, MapLayers mapLayers0, Element xmlReader$Element0) {
        if(xmlReader$Element0.getName().equals("layer")) {
            int v = xmlReader$Element0.getIntAttribute("width", 0);
            int v1 = xmlReader$Element0.getIntAttribute("height", 0);
            TiledMapTileLayer tiledMapTileLayer0 = new TiledMapTileLayer(v, v1, ((int)(((Integer)tiledMap0.getProperties().get("tilewidth", Integer.class)))), ((int)(((Integer)tiledMap0.getProperties().get("tileheight", Integer.class)))));
            this.loadBasicLayerInfo(tiledMapTileLayer0, xmlReader$Element0);
            int[] arr_v = BaseTmxMapLoader.getTileIds(xmlReader$Element0, v, v1);
            TiledMapTileSets tiledMapTileSets0 = tiledMap0.getTileSets();
            for(int v2 = 0; v2 < v1; ++v2) {
                for(int v3 = 0; v3 < v; ++v3) {
                    int v4 = arr_v[v2 * v + v3];
                    boolean z = true;
                    boolean z1 = (0x80000000 & v4) != 0;
                    if((0x20000000 & v4) == 0) {
                        z = false;
                    }
                    TiledMapTile tiledMapTile0 = tiledMapTileSets0.getTile(v4 & 0x1FFFFFFF);
                    if(tiledMapTile0 != null) {
                        Cell tiledMapTileLayer$Cell0 = this.createTileLayerCell(z1, (0x40000000 & v4) != 0, z);
                        tiledMapTileLayer$Cell0.setTile(tiledMapTile0);
                        tiledMapTileLayer0.setCell(v3, (this.flipY ? v1 - 1 - v2 : v2), tiledMapTileLayer$Cell0);
                    }
                }
            }
            Element xmlReader$Element1 = xmlReader$Element0.getChildByName("properties");
            if(xmlReader$Element1 != null) {
                this.loadProperties(tiledMapTileLayer0.getProperties(), xmlReader$Element1);
            }
            mapLayers0.add(tiledMapTileLayer0);
        }
    }

    protected void loadTileSet(Element xmlReader$Element0, FileHandle fileHandle0, ImageResolver imageResolver0) {
        int v12;
        int v11;
        Element xmlReader$Element3;
        int v4;
        int v3;
        String s2;
        FileHandle fileHandle3;
        FileHandle fileHandle2;
        int v2;
        int v1;
        Element xmlReader$Element1;
        if(xmlReader$Element0.getName().equals("tileset")) {
            int v = xmlReader$Element0.getIntAttribute("firstgid", 1);
            String s = "";
            String s1 = xmlReader$Element0.getAttribute("source", null);
            if(s1 == null) {
                Element xmlReader$Element4 = xmlReader$Element0.getChildByName("image");
                if(xmlReader$Element4 == null) {
                    xmlReader$Element3 = xmlReader$Element0;
                    s2 = "";
                    fileHandle3 = null;
                    v4 = 0;
                    v3 = 0;
                }
                else {
                    String s3 = xmlReader$Element4.getAttribute("source");
                    int v5 = xmlReader$Element4.getIntAttribute("width", 0);
                    s2 = s3;
                    v3 = xmlReader$Element4.getIntAttribute("height", 0);
                    v4 = v5;
                    fileHandle3 = BaseTmxMapLoader.getRelativeFileHandle(fileHandle0, s3);
                    xmlReader$Element3 = xmlReader$Element0;
                }
            }
            else {
                FileHandle fileHandle1 = BaseTmxMapLoader.getRelativeFileHandle(fileHandle0, s1);
                try {
                    xmlReader$Element1 = this.xml.parse(fileHandle1);
                    Element xmlReader$Element2 = xmlReader$Element1.getChildByName("image");
                    if(xmlReader$Element2 == null) {
                        fileHandle2 = null;
                        v2 = 0;
                        v1 = 0;
                    }
                    else {
                        s = xmlReader$Element2.getAttribute("source");
                        v1 = xmlReader$Element2.getIntAttribute("width", 0);
                        v2 = xmlReader$Element2.getIntAttribute("height", 0);
                        fileHandle2 = BaseTmxMapLoader.getRelativeFileHandle(fileHandle1, s);
                    }
                }
                catch(SerializationException unused_ex) {
                    throw new GdxRuntimeException("Error parsing external tileset.");
                }
                fileHandle3 = fileHandle2;
                s2 = s;
                v3 = v2;
                v4 = v1;
                xmlReader$Element3 = xmlReader$Element1;
            }
            String s4 = xmlReader$Element3.get("name", null);
            int v6 = xmlReader$Element3.getIntAttribute("tilewidth", 0);
            int v7 = xmlReader$Element3.getIntAttribute("tileheight", 0);
            int v8 = xmlReader$Element3.getIntAttribute("spacing", 0);
            int v9 = xmlReader$Element3.getIntAttribute("margin", 0);
            Element xmlReader$Element5 = xmlReader$Element3.getChildByName("tileoffset");
            if(xmlReader$Element5 == null) {
                v12 = 0;
                v11 = 0;
            }
            else {
                int v10 = xmlReader$Element5.getIntAttribute("x", 0);
                v11 = xmlReader$Element5.getIntAttribute("y", 0);
                v12 = v10;
            }
            TiledMapTileSet tiledMapTileSet0 = new TiledMapTileSet();
            tiledMapTileSet0.setName(s4);
            MapProperties mapProperties0 = tiledMapTileSet0.getProperties();
            Element xmlReader$Element6 = xmlReader$Element3.getChildByName("properties");
            if(xmlReader$Element6 != null) {
                this.loadProperties(mapProperties0, xmlReader$Element6);
            }
            mapProperties0.put("firstgid", v);
            Array array0 = xmlReader$Element3.getChildrenByName("tile");
            this.addStaticTiles(fileHandle0, imageResolver0, tiledMapTileSet0, xmlReader$Element3, array0, s4, v, v6, v7, v8, v9, s1, v12, v11, s2, v4, v3, fileHandle3);
            Array array1 = new Array();
            for(Object object0: array0) {
                Element xmlReader$Element7 = (Element)object0;
                TiledMapTile tiledMapTile0 = tiledMapTileSet0.getTile(v + xmlReader$Element7.getIntAttribute("id", 0));
                if(tiledMapTile0 != null) {
                    AnimatedTiledMapTile animatedTiledMapTile0 = this.createAnimatedTile(tiledMapTileSet0, tiledMapTile0, xmlReader$Element7, v);
                    if(animatedTiledMapTile0 != null) {
                        array1.add(animatedTiledMapTile0);
                        tiledMapTile0 = animatedTiledMapTile0;
                    }
                    this.addTileProperties(tiledMapTile0, xmlReader$Element7);
                    this.addTileObjectGroup(tiledMapTile0, xmlReader$Element7);
                }
            }
            for(Object object1: array1) {
                tiledMapTileSet0.putTile(((AnimatedTiledMapTile)object1).getId(), ((AnimatedTiledMapTile)object1));
            }
            this.map.getTileSets().addTileSet(tiledMapTileSet0);
        }
    }

    protected TiledMap loadTiledMap(FileHandle fileHandle0, Parameters baseTmxMapLoader$Parameters0, ImageResolver imageResolver0) {
        this.map = new TiledMap();
        if(baseTmxMapLoader$Parameters0 == null) {
            this.convertObjectToTileSpace = false;
            this.flipY = true;
        }
        else {
            this.convertObjectToTileSpace = baseTmxMapLoader$Parameters0.convertObjectToTileSpace;
            this.flipY = baseTmxMapLoader$Parameters0.flipY;
        }
        String s = this.root.getAttribute("orientation", null);
        int v = this.root.getIntAttribute("width", 0);
        int v1 = this.root.getIntAttribute("height", 0);
        int v2 = this.root.getIntAttribute("tilewidth", 0);
        int v3 = this.root.getIntAttribute("tileheight", 0);
        int v4 = this.root.getIntAttribute("hexsidelength", 0);
        String s1 = this.root.getAttribute("staggeraxis", null);
        String s2 = this.root.getAttribute("staggerindex", null);
        String s3 = this.root.getAttribute("backgroundcolor", null);
        MapProperties mapProperties0 = this.map.getProperties();
        if(s != null) {
            mapProperties0.put("orientation", s);
        }
        mapProperties0.put("width", v);
        mapProperties0.put("height", v1);
        mapProperties0.put("tilewidth", v2);
        mapProperties0.put("tileheight", v3);
        mapProperties0.put("hexsidelength", v4);
        if(s1 != null) {
            mapProperties0.put("staggeraxis", s1);
        }
        if(s2 != null) {
            mapProperties0.put("staggerindex", s2);
        }
        if(s3 != null) {
            mapProperties0.put("backgroundcolor", s3);
        }
        this.mapTileWidth = v2;
        this.mapTileHeight = v3;
        this.mapWidthInPixels = v * v2;
        this.mapHeightInPixels = v1 * v3;
        if(s != null && "staggered".equals(s) && v1 > 1) {
            this.mapWidthInPixels += v2 / 2;
            this.mapHeightInPixels = this.mapHeightInPixels / 2 + v3 / 2;
        }
        Element xmlReader$Element0 = this.root.getChildByName("properties");
        if(xmlReader$Element0 != null) {
            this.loadProperties(this.map.getProperties(), xmlReader$Element0);
        }
        for(Object object0: this.root.getChildrenByName("tileset")) {
            this.loadTileSet(((Element)object0), fileHandle0, imageResolver0);
            this.root.removeChild(((Element)object0));
        }
        int v5 = this.root.getChildCount();
        for(int v6 = 0; v6 < v5; ++v6) {
            Element xmlReader$Element1 = this.root.getChild(v6);
            this.loadLayer(this.map, this.map.getLayers(), xmlReader$Element1, fileHandle0, imageResolver0);
        }
        return this.map;
    }

    protected static int unsignedByteToInt(byte b) [...] // Inlined contents
}

