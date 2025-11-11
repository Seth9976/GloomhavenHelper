package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedReader;
import java.io.IOException;

public class PolygonRegionLoader extends SynchronousAssetLoader {
    public static class PolygonRegionParameters extends AssetLoaderParameters {
        public int readerBuffer;
        public String[] textureExtensions;
        public String texturePrefix;

        public PolygonRegionParameters() {
            this.texturePrefix = "i ";
            this.readerBuffer = 0x400;
            this.textureExtensions = new String[]{"png", "PNG", "jpeg", "JPEG", "jpg", "JPG", "cim", "CIM", "etc1", "ETC1", "ktx", "KTX", "zktx", "ZKTX"};
        }
    }

    private PolygonRegionParameters defaultParameters;
    private EarClippingTriangulator triangulator;

    public PolygonRegionLoader() {
        this(new InternalFileHandleResolver());
    }

    public PolygonRegionLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
        this.defaultParameters = new PolygonRegionParameters();
        this.triangulator = new EarClippingTriangulator();
    }

    @Override  // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array getDependencies(String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.getDependencies(s, fileHandle0, ((PolygonRegionParameters)assetLoaderParameters0));
    }

    public Array getDependencies(String s, FileHandle fileHandle0, PolygonRegionParameters polygonRegionLoader$PolygonRegionParameters0) {
        String s1;
        if(polygonRegionLoader$PolygonRegionParameters0 == null) {
            polygonRegionLoader$PolygonRegionParameters0 = this.defaultParameters;
        }
        try {
            s1 = null;
            BufferedReader bufferedReader0 = fileHandle0.reader(polygonRegionLoader$PolygonRegionParameters0.readerBuffer);
            String s2;
            while((s2 = bufferedReader0.readLine()) != null) {
                if(s2.startsWith(polygonRegionLoader$PolygonRegionParameters0.texturePrefix)) {
                    s1 = s2.substring(polygonRegionLoader$PolygonRegionParameters0.texturePrefix.length());
                    break;
                }
            }
            bufferedReader0.close();
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException("Error reading " + s, iOException0);
        }
        if(s1 == null && polygonRegionLoader$PolygonRegionParameters0.textureExtensions != null) {
            String[] arr_s = polygonRegionLoader$PolygonRegionParameters0.textureExtensions;
            for(int v = 0; v < arr_s.length; ++v) {
                String s3 = arr_s[v];
                FileHandle fileHandle1 = fileHandle0.sibling(fileHandle0.nameWithoutExtension() + ("." + s3));
                if(fileHandle1.exists()) {
                    s1 = fileHandle1.name();
                }
            }
        }
        if(s1 != null) {
            Array array0 = new Array(1);
            array0.add(new AssetDescriptor(fileHandle0.sibling(s1), Texture.class));
            return array0;
        }
        return null;
    }

    public PolygonRegion load(AssetManager assetManager0, String s, FileHandle fileHandle0, PolygonRegionParameters polygonRegionLoader$PolygonRegionParameters0) {
        return this.load(new TextureRegion(((Texture)assetManager0.get(((String)assetManager0.getDependencies(s).first())))), fileHandle0);
    }

    public PolygonRegion load(TextureRegion textureRegion0, FileHandle fileHandle0) {
        BufferedReader bufferedReader0 = fileHandle0.reader(0x100);
        try {
            while(true) {
            label_2:
                String s = bufferedReader0.readLine();
                if(s == null) {
                    throw new GdxRuntimeException("Polygon shape not found: " + fileHandle0);
                }
                if(s.startsWith("s")) {
                    String[] arr_s = s.substring(1).trim().split(",");
                    float[] arr_f = new float[arr_s.length];
                    for(int v1 = 0; v1 < arr_f.length; ++v1) {
                        arr_f[v1] = Float.parseFloat(arr_s[v1]);
                    }
                    return new PolygonRegion(textureRegion0, arr_f, this.triangulator.computeTriangles(arr_f).toArray());
                }
            }
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException("Error reading polygon shape file: " + fileHandle0, iOException0);
        }
        finally {
            StreamUtils.closeQuietly(bufferedReader0);
        }
        goto label_2;
    }

    @Override  // com.badlogic.gdx.assets.loaders.SynchronousAssetLoader
    public Object load(AssetManager assetManager0, String s, FileHandle fileHandle0, AssetLoaderParameters assetLoaderParameters0) {
        return this.load(assetManager0, s, fileHandle0, ((PolygonRegionParameters)assetLoaderParameters0));
    }
}

