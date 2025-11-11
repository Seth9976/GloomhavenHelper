package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.HashMap;
import java.util.Map;

public class TextureArray extends GLTexture {
    private TextureArrayData data;
    static final Map managedTextureArrays;

    static {
        TextureArray.managedTextureArrays = new HashMap();
    }

    public TextureArray(TextureArrayData textureArrayData0) {
        super(0x8C1A, Gdx.gl.glGenTexture());
        if(Gdx.gl30 == null) {
            throw new GdxRuntimeException("TextureArray requires a device running with GLES 3.0 compatibilty");
        }
        this.load(textureArrayData0);
        if(textureArrayData0.isManaged()) {
            TextureArray.addManagedTexture(Gdx.app, this);
        }
    }

    public TextureArray(boolean z, Format pixmap$Format0, FileHandle[] arr_fileHandle) {
        this(Factory.loadFromFiles(pixmap$Format0, z, arr_fileHandle));
    }

    public TextureArray(boolean z, FileHandle[] arr_fileHandle) {
        this(z, Format.RGBA8888, arr_fileHandle);
    }

    public TextureArray(FileHandle[] arr_fileHandle) {
        this(false, arr_fileHandle);
    }

    public TextureArray(String[] arr_s) {
        this(TextureArray.getInternalHandles(arr_s));
    }

    private static void addManagedTexture(Application application0, TextureArray textureArray0) {
        Array array0 = (Array)TextureArray.managedTextureArrays.get(application0);
        if(array0 == null) {
            array0 = new Array();
        }
        array0.add(textureArray0);
        TextureArray.managedTextureArrays.put(application0, array0);
    }

    public static void clearAllTextureArrays(Application application0) {
        TextureArray.managedTextureArrays.remove(application0);
    }

    @Override  // com.badlogic.gdx.graphics.GLTexture
    public int getDepth() {
        return this.data.getDepth();
    }

    @Override  // com.badlogic.gdx.graphics.GLTexture
    public int getHeight() {
        return this.data.getHeight();
    }

    private static FileHandle[] getInternalHandles(String[] arr_s) {
        FileHandle[] arr_fileHandle = new FileHandle[arr_s.length];
        for(int v = 0; v < arr_s.length; ++v) {
            arr_fileHandle[v] = Gdx.files.internal(arr_s[v]);
        }
        return arr_fileHandle;
    }

    public static String getManagedStatus() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("Managed TextureArrays/app: { ");
        for(Object object0: TextureArray.managedTextureArrays.keySet()) {
            stringBuilder0.append(((Array)TextureArray.managedTextureArrays.get(((Application)object0))).size);
            stringBuilder0.append(" ");
        }
        stringBuilder0.append("}");
        return stringBuilder0.toString();
    }

    public static int getNumManagedTextureArrays() {
        return ((Array)TextureArray.managedTextureArrays.get(Gdx.app)).size;
    }

    @Override  // com.badlogic.gdx.graphics.GLTexture
    public int getWidth() {
        return this.data.getWidth();
    }

    public static void invalidateAllTextureArrays(Application application0) {
        Array array0 = (Array)TextureArray.managedTextureArrays.get(application0);
        if(array0 == null) {
            return;
        }
        for(int v = 0; v < array0.size; ++v) {
            ((TextureArray)array0.get(v)).reload();
        }
    }

    @Override  // com.badlogic.gdx.graphics.GLTexture
    public boolean isManaged() {
        return this.data.isManaged();
    }

    private void load(TextureArrayData textureArrayData0) {
        if(this.data != null && textureArrayData0.isManaged() != this.data.isManaged()) {
            throw new GdxRuntimeException("New data must have the same managed status as the old data");
        }
        this.data = textureArrayData0;
        this.bind();
        Gdx.gl30.glTexImage3D(0x8C1A, 0, textureArrayData0.getInternalFormat(), textureArrayData0.getWidth(), textureArrayData0.getHeight(), textureArrayData0.getDepth(), 0, textureArrayData0.getInternalFormat(), textureArrayData0.getGLType(), null);
        if(!textureArrayData0.isPrepared()) {
            textureArrayData0.prepare();
        }
        textureArrayData0.consumeTextureArrayData();
        this.setFilter(this.minFilter, this.magFilter);
        this.setWrap(this.uWrap, this.vWrap);
        Gdx.gl.glBindTexture(this.glTarget, 0);
    }

    @Override  // com.badlogic.gdx.graphics.GLTexture
    protected void reload() {
        if(!this.isManaged()) {
            throw new GdxRuntimeException("Tried to reload an unmanaged TextureArray");
        }
        this.glHandle = Gdx.gl.glGenTexture();
        this.load(this.data);
    }
}

