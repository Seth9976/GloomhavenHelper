package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetLoaderParameters.LoadedCallback;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.HashMap;
import java.util.Map;

public class Texture extends GLTexture {
    public static enum TextureFilter {
        Nearest(0x2600),
        Linear(0x2601),
        MipMap(0x2703),
        MipMapNearestNearest(0x2700),
        MipMapLinearNearest(0x2701),
        MipMapNearestLinear(0x2702),
        MipMapLinearLinear(0x2703);

        final int glEnum;

        private TextureFilter(int v1) {
            this.glEnum = v1;
        }

        public int getGLEnum() {
            return this.glEnum;
        }

        public boolean isMipMap() {
            return this.glEnum != 0x2600 && this.glEnum != 0x2601;
        }
    }

    public static enum TextureWrap {
        MirroredRepeat(0x8370),
        ClampToEdge(0x812F),
        Repeat(0x2901);

        final int glEnum;

        private TextureWrap(int v1) {
            this.glEnum = v1;
        }

        public int getGLEnum() {
            return this.glEnum;
        }
    }

    private static AssetManager assetManager;
    TextureData data;
    static final Map managedTextures;

    static {
        Texture.managedTextures = new HashMap();
    }

    public Texture(int v, int v1, Format pixmap$Format0) {
        this(new PixmapTextureData(new Pixmap(v, v1, pixmap$Format0), null, false, true));
    }

    protected Texture(int v, int v1, TextureData textureData0) {
        super(v, v1);
        this.load(textureData0);
        if(textureData0.isManaged()) {
            Texture.addManagedTexture(Gdx.app, this);
        }
    }

    public Texture(FileHandle fileHandle0) {
        this(fileHandle0, null, false);
    }

    public Texture(FileHandle fileHandle0, Format pixmap$Format0, boolean z) {
        this(Factory.loadFromFile(fileHandle0, pixmap$Format0, z));
    }

    public Texture(FileHandle fileHandle0, boolean z) {
        this(fileHandle0, null, z);
    }

    public Texture(Pixmap pixmap0) {
        this(new PixmapTextureData(pixmap0, null, false, false));
    }

    public Texture(Pixmap pixmap0, Format pixmap$Format0, boolean z) {
        this(new PixmapTextureData(pixmap0, pixmap$Format0, z, false));
    }

    public Texture(Pixmap pixmap0, boolean z) {
        this(new PixmapTextureData(pixmap0, null, z, false));
    }

    public Texture(TextureData textureData0) {
        this(0xDE1, Gdx.gl.glGenTexture(), textureData0);
    }

    public Texture(String s) {
        this(Gdx.files.internal(s));
    }

    private static void addManagedTexture(Application application0, Texture texture0) {
        Array array0 = (Array)Texture.managedTextures.get(application0);
        if(array0 == null) {
            array0 = new Array();
        }
        array0.add(texture0);
        Texture.managedTextures.put(application0, array0);
    }

    public static void clearAllTextures(Application application0) {
        Texture.managedTextures.remove(application0);
    }

    @Override  // com.badlogic.gdx.graphics.GLTexture
    public void dispose() {
        if(this.glHandle == 0) {
            return;
        }
        this.delete();
        if(this.data.isManaged() && Texture.managedTextures.get(Gdx.app) != null) {
            ((Array)Texture.managedTextures.get(Gdx.app)).removeValue(this, true);
        }
    }

    public void draw(Pixmap pixmap0, int v, int v1) {
        if(this.data.isManaged()) {
            throw new GdxRuntimeException("can\'t draw to a managed texture");
        }
        this.bind();
        Gdx.gl.glTexSubImage2D(this.glTarget, 0, v, v1, pixmap0.getWidth(), pixmap0.getHeight(), pixmap0.getGLFormat(), pixmap0.getGLType(), pixmap0.getPixels());
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

    public static int getNumManagedTextures() {
        return ((Array)Texture.managedTextures.get(Gdx.app)).size;
    }

    public TextureData getTextureData() {
        return this.data;
    }

    @Override  // com.badlogic.gdx.graphics.GLTexture
    public int getWidth() {
        return this.data.getWidth();
    }

    public static void invalidateAllTextures(Application application0) {
        Array array0 = (Array)Texture.managedTextures.get(application0);
        if(array0 == null) {
            return;
        }
        AssetManager assetManager0 = Texture.assetManager;
        if(assetManager0 == null) {
            for(int v = 0; v < array0.size; ++v) {
                ((Texture)array0.get(v)).reload();
            }
            return;
        }
        assetManager0.finishLoading();
        Array array1 = new Array(array0);
        for(Object object0: array1) {
            Texture texture0 = (Texture)object0;
            String s = Texture.assetManager.getAssetFileName(texture0);
            if(s == null) {
                texture0.reload();
            }
            else {
                int v1 = Texture.assetManager.getReferenceCount(s);
                Texture.assetManager.setReferenceCount(s, 0);
                texture0.glHandle = 0;
                TextureParameter textureLoader$TextureParameter0 = new TextureParameter();
                textureLoader$TextureParameter0.textureData = texture0.getTextureData();
                textureLoader$TextureParameter0.minFilter = texture0.getMinFilter();
                textureLoader$TextureParameter0.magFilter = texture0.getMagFilter();
                textureLoader$TextureParameter0.wrapU = texture0.getUWrap();
                textureLoader$TextureParameter0.wrapV = texture0.getVWrap();
                textureLoader$TextureParameter0.genMipMaps = texture0.data.useMipMaps();
                textureLoader$TextureParameter0.texture = texture0;
                textureLoader$TextureParameter0.loadedCallback = new LoadedCallback() {
                    @Override  // com.badlogic.gdx.assets.AssetLoaderParameters$LoadedCallback
                    public void finishedLoading(AssetManager assetManager0, String s, Class class0) {
                        assetManager0.setReferenceCount(s, v1);
                    }
                };
                Texture.assetManager.unload(s);
                texture0.glHandle = Gdx.gl.glGenTexture();
                Texture.assetManager.load(s, Texture.class, textureLoader$TextureParameter0);
            }
        }
        array0.clear();
        array0.addAll(array1);
    }

    @Override  // com.badlogic.gdx.graphics.GLTexture
    public boolean isManaged() {
        return this.data.isManaged();
    }

    public void load(TextureData textureData0) {
        if(this.data != null && textureData0.isManaged() != this.data.isManaged()) {
            throw new GdxRuntimeException("New data must have the same managed status as the old data");
        }
        this.data = textureData0;
        if(!textureData0.isPrepared()) {
            textureData0.prepare();
        }
        this.bind();
        Texture.uploadImageData(0xDE1, textureData0);
        this.unsafeSetFilter(this.minFilter, this.magFilter, true);
        this.unsafeSetWrap(this.uWrap, this.vWrap, true);
        this.unsafeSetAnisotropicFilter(this.anisotropicFilterLevel, true);
        Gdx.gl.glBindTexture(this.glTarget, 0);
    }

    @Override  // com.badlogic.gdx.graphics.GLTexture
    protected void reload() {
        if(!this.isManaged()) {
            throw new GdxRuntimeException("Tried to reload unmanaged Texture");
        }
        this.glHandle = Gdx.gl.glGenTexture();
        this.load(this.data);
    }

    public static void setAssetManager(AssetManager assetManager0) {
        Texture.assetManager = assetManager0;
    }

    // 去混淆评级： 低(20)
    @Override
    public String toString() {
        return this.data instanceof FileTextureData ? this.data.toString() : super.toString();
    }
}

