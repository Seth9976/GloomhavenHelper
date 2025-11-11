package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetLoaderParameters.LoadedCallback;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.CubemapLoader.CubemapParameter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.FacedCubemapData;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.HashMap;
import java.util.Map;

public class Cubemap extends GLTexture {
    public static enum CubemapSide {
        PositiveX(0, 34069, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f),
        NegativeX(1, 34070, 0.0f, -1.0f, 0.0f, -1.0f, 0.0f, 0.0f),
        PositiveY(2, 34071, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f),
        NegativeY(3, 34072, 0.0f, 0.0f, -1.0f, 0.0f, -1.0f, 0.0f),
        PositiveZ(4, 34073, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f),
        NegativeZ(5, 34074, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, -1.0f);

        public final Vector3 direction;
        public final int glEnum;
        public final int index;
        public final Vector3 up;

        private CubemapSide(int v1, int v2, float f, float f1, float f2, float f3, float f4, float f5) {
            this.index = v1;
            this.glEnum = v2;
            this.up = new Vector3(f, f1, f2);
            this.direction = new Vector3(f3, f4, f5);
        }

        public Vector3 getDirection(Vector3 vector30) {
            return vector30.set(this.direction);
        }

        public int getGLEnum() {
            return this.glEnum;
        }

        public Vector3 getUp(Vector3 vector30) {
            return vector30.set(this.up);
        }
    }

    private static AssetManager assetManager;
    protected CubemapData data;
    static final Map managedCubemaps;

    static {
        Cubemap.managedCubemaps = new HashMap();
    }

    public Cubemap(int v, int v1, int v2, Format pixmap$Format0) {
        this(new PixmapTextureData(new Pixmap(v2, v1, pixmap$Format0), null, false, true), new PixmapTextureData(new Pixmap(v2, v1, pixmap$Format0), null, false, true), new PixmapTextureData(new Pixmap(v, v2, pixmap$Format0), null, false, true), new PixmapTextureData(new Pixmap(v, v2, pixmap$Format0), null, false, true), new PixmapTextureData(new Pixmap(v, v1, pixmap$Format0), null, false, true), new PixmapTextureData(new Pixmap(v, v1, pixmap$Format0), null, false, true));
    }

    public Cubemap(FileHandle fileHandle0, FileHandle fileHandle1, FileHandle fileHandle2, FileHandle fileHandle3, FileHandle fileHandle4, FileHandle fileHandle5) {
        this(fileHandle0, fileHandle1, fileHandle2, fileHandle3, fileHandle4, fileHandle5, false);
    }

    public Cubemap(FileHandle fileHandle0, FileHandle fileHandle1, FileHandle fileHandle2, FileHandle fileHandle3, FileHandle fileHandle4, FileHandle fileHandle5, boolean z) {
        this(Factory.loadFromFile(fileHandle0, z), Factory.loadFromFile(fileHandle1, z), Factory.loadFromFile(fileHandle2, z), Factory.loadFromFile(fileHandle3, z), Factory.loadFromFile(fileHandle4, z), Factory.loadFromFile(fileHandle5, z));
    }

    public Cubemap(CubemapData cubemapData0) {
        super(34067);
        this.data = cubemapData0;
        this.load(cubemapData0);
        if(cubemapData0.isManaged()) {
            Cubemap.addManagedCubemap(Gdx.app, this);
        }
    }

    public Cubemap(Pixmap pixmap0, Pixmap pixmap1, Pixmap pixmap2, Pixmap pixmap3, Pixmap pixmap4, Pixmap pixmap5) {
        this(pixmap0, pixmap1, pixmap2, pixmap3, pixmap4, pixmap5, false);
    }

    // 去混淆评级： 低(30)
    public Cubemap(Pixmap pixmap0, Pixmap pixmap1, Pixmap pixmap2, Pixmap pixmap3, Pixmap pixmap4, Pixmap pixmap5, boolean z) {
        this((pixmap0 == null ? null : new PixmapTextureData(pixmap0, null, z, false)), (pixmap1 == null ? null : new PixmapTextureData(pixmap1, null, z, false)), (pixmap2 == null ? null : new PixmapTextureData(pixmap2, null, z, false)), (pixmap3 == null ? null : new PixmapTextureData(pixmap3, null, z, false)), (pixmap4 == null ? null : new PixmapTextureData(pixmap4, null, z, false)), (pixmap5 == null ? null : new PixmapTextureData(pixmap5, null, z, false)));
    }

    public Cubemap(TextureData textureData0, TextureData textureData1, TextureData textureData2, TextureData textureData3, TextureData textureData4, TextureData textureData5) {
        this(new FacedCubemapData(textureData0, textureData1, textureData2, textureData3, textureData4, textureData5));
    }

    private static void addManagedCubemap(Application application0, Cubemap cubemap0) {
        Array array0 = (Array)Cubemap.managedCubemaps.get(application0);
        if(array0 == null) {
            array0 = new Array();
        }
        array0.add(cubemap0);
        Cubemap.managedCubemaps.put(application0, array0);
    }

    public static void clearAllCubemaps(Application application0) {
        Cubemap.managedCubemaps.remove(application0);
    }

    @Override  // com.badlogic.gdx.graphics.GLTexture
    public void dispose() {
        if(this.glHandle == 0) {
            return;
        }
        this.delete();
        if(this.data.isManaged() && Cubemap.managedCubemaps.get(Gdx.app) != null) {
            ((Array)Cubemap.managedCubemaps.get(Gdx.app)).removeValue(this, true);
        }
    }

    public CubemapData getCubemapData() {
        return this.data;
    }

    @Override  // com.badlogic.gdx.graphics.GLTexture
    public int getDepth() {
        return 0;
    }

    @Override  // com.badlogic.gdx.graphics.GLTexture
    public int getHeight() {
        return this.data.getHeight();
    }

    public static String getManagedStatus() [...] // 潜在的解密器

    public static int getNumManagedCubemaps() {
        return ((Array)Cubemap.managedCubemaps.get(Gdx.app)).size;
    }

    @Override  // com.badlogic.gdx.graphics.GLTexture
    public int getWidth() {
        return this.data.getWidth();
    }

    public static void invalidateAllCubemaps(Application application0) {
        Array array0 = (Array)Cubemap.managedCubemaps.get(application0);
        if(array0 == null) {
            return;
        }
        AssetManager assetManager0 = Cubemap.assetManager;
        if(assetManager0 == null) {
            for(int v = 0; v < array0.size; ++v) {
                ((Cubemap)array0.get(v)).reload();
            }
            return;
        }
        assetManager0.finishLoading();
        Array array1 = new Array(array0);
        for(Object object0: array1) {
            Cubemap cubemap0 = (Cubemap)object0;
            String s = Cubemap.assetManager.getAssetFileName(cubemap0);
            if(s == null) {
                cubemap0.reload();
            }
            else {
                int v1 = Cubemap.assetManager.getReferenceCount(s);
                Cubemap.assetManager.setReferenceCount(s, 0);
                cubemap0.glHandle = 0;
                CubemapParameter cubemapLoader$CubemapParameter0 = new CubemapParameter();
                cubemapLoader$CubemapParameter0.cubemapData = cubemap0.getCubemapData();
                cubemapLoader$CubemapParameter0.minFilter = cubemap0.getMinFilter();
                cubemapLoader$CubemapParameter0.magFilter = cubemap0.getMagFilter();
                cubemapLoader$CubemapParameter0.wrapU = cubemap0.getUWrap();
                cubemapLoader$CubemapParameter0.wrapV = cubemap0.getVWrap();
                cubemapLoader$CubemapParameter0.cubemap = cubemap0;
                cubemapLoader$CubemapParameter0.loadedCallback = new LoadedCallback() {
                    @Override  // com.badlogic.gdx.assets.AssetLoaderParameters$LoadedCallback
                    public void finishedLoading(AssetManager assetManager0, String s, Class class0) {
                        assetManager0.setReferenceCount(s, v1);
                    }
                };
                Cubemap.assetManager.unload(s);
                cubemap0.glHandle = Gdx.gl.glGenTexture();
                Cubemap.assetManager.load(s, Cubemap.class, cubemapLoader$CubemapParameter0);
            }
        }
        array0.clear();
        array0.addAll(array1);
    }

    @Override  // com.badlogic.gdx.graphics.GLTexture
    public boolean isManaged() {
        return this.data.isManaged();
    }

    public void load(CubemapData cubemapData0) {
        if(!cubemapData0.isPrepared()) {
            cubemapData0.prepare();
        }
        this.bind();
        this.unsafeSetFilter(this.minFilter, this.magFilter, true);
        this.unsafeSetWrap(this.uWrap, this.vWrap, true);
        this.unsafeSetAnisotropicFilter(this.anisotropicFilterLevel, true);
        cubemapData0.consumeCubemapData();
        Gdx.gl.glBindTexture(this.glTarget, 0);
    }

    @Override  // com.badlogic.gdx.graphics.GLTexture
    protected void reload() {
        if(!this.isManaged()) {
            throw new GdxRuntimeException("Tried to reload an unmanaged Cubemap");
        }
        this.glHandle = Gdx.gl.glGenTexture();
        this.load(this.data);
    }

    public static void setAssetManager(AssetManager assetManager0) {
        Cubemap.assetManager = assetManager0;
    }
}

