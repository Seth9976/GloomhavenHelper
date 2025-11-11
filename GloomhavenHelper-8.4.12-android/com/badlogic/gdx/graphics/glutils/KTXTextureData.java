package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.CubemapData;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData.TextureDataType;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.zip.GZIPInputStream;

public class KTXTextureData implements CubemapData, TextureData {
    private static final int GL_TEXTURE_1D = 4660;
    private static final int GL_TEXTURE_1D_ARRAY_EXT = 4660;
    private static final int GL_TEXTURE_2D_ARRAY_EXT = 4660;
    private static final int GL_TEXTURE_3D = 4660;
    private ByteBuffer compressedData;
    private FileHandle file;
    private int glBaseInternalFormat;
    private int glFormat;
    private int glInternalFormat;
    private int glType;
    private int glTypeSize;
    private int imagePos;
    private int numberOfArrayElements;
    private int numberOfFaces;
    private int numberOfMipmapLevels;
    private int pixelDepth;
    private int pixelHeight;
    private int pixelWidth;
    private boolean useMipMaps;

    public KTXTextureData(FileHandle fileHandle0, boolean z) {
        this.pixelWidth = -1;
        this.pixelHeight = -1;
        this.pixelDepth = -1;
        this.file = fileHandle0;
        this.useMipMaps = z;
    }

    @Override  // com.badlogic.gdx.graphics.CubemapData
    public void consumeCubemapData() {
        this.consumeCustomData(34067);
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int v) {
        int v19;
        int v18;
        int v6;
        int v3;
        int v2;
        int v1;
        if(this.compressedData == null) {
            throw new GdxRuntimeException("Call prepare() before calling consumeCompressedData()");
        }
        IntBuffer intBuffer0 = BufferUtils.newIntBuffer(16);
        if(this.glType != 0 && this.glFormat != 0) {
            v1 = 0;
        }
        else if(this.glType + this.glFormat == 0) {
            v1 = 1;
        }
        else {
            throw new GdxRuntimeException("either both or none of glType, glFormat must be zero");
        }
        if(this.pixelHeight > 0) {
            v2 = 2;
            v3 = 0xDE1;
        }
        else {
            v2 = 1;
            v3 = 4660;
        }
        if(this.pixelDepth > 0) {
            v2 = 3;
            v3 = 4660;
        }
        int v4 = this.numberOfFaces;
        if(v4 == 6) {
            if(v2 != 2) {
                throw new GdxRuntimeException("cube map needs 2D faces");
            }
            v3 = 34067;
        }
        else if(v4 != 1) {
            throw new GdxRuntimeException("numberOfFaces must be either 1 or 6");
        }
        if(this.numberOfArrayElements > 0) {
            if(v3 != 0xDE1 && v3 != 4660) {
                throw new GdxRuntimeException("No API for 3D and cube arrays yet");
            }
            ++v2;
            v3 = 4660;
        }
        int v5 = 34069;
        if(v3 == 4660) {
            throw new GdxRuntimeException("Unsupported texture format (only 2D texture are supported in LibGdx for the time being)");
        }
        if(this.numberOfFaces == 6 && v != 34067) {
            if(34069 > v || v > 34074) {
                throw new GdxRuntimeException("You must specify either GL_TEXTURE_CUBE_MAP to bind all 6 faces of the cube or the requested face GL_TEXTURE_CUBE_MAP_POSITIVE_X and followings.");
            }
            v6 = v - 34069;
        }
        else if(this.numberOfFaces == 6 && v == 34067) {
            v6 = -1;
        }
        else {
            if(v != v3) {
                throw new GdxRuntimeException("Invalid target requested : 0x" + Integer.toHexString(v) + ", expecting : 0x" + Integer.toHexString(v3));
            }
            v5 = v;
            v6 = -1;
        }
        Gdx.gl.glGetIntegerv(0xCF5, intBuffer0);
        int v7 = intBuffer0.get(0);
        if(v7 != 4) {
            Gdx.gl.glPixelStorei(0xCF5, 4);
        }
        int v8 = this.glInternalFormat;
        int v9 = this.glFormat;
        int v10 = this.imagePos;
        for(int v11 = 0; v11 < this.numberOfMipmapLevels; ++v11) {
            int v12 = Math.max(1, this.pixelWidth >> v11);
            int v13 = Math.max(1, this.pixelHeight >> v11);
            this.compressedData.position(v10);
            int v14 = this.compressedData.getInt();
            int v15 = v14 + 3 & -4;
            v10 += 4;
            int v16 = v13;
            int v17 = 0;
            while(v17 < this.numberOfFaces) {
                this.compressedData.position(v10);
                v10 += v15;
                if(v6 == -1 || v6 == v17) {
                    ByteBuffer byteBuffer0 = this.compressedData.slice();
                    byteBuffer0.limit(v15);
                    if(v2 == 1) {
                        v18 = v6;
                        v19 = v1;
                    }
                    else if(v2 == 2) {
                        int v20 = this.numberOfArrayElements > 0 ? this.numberOfArrayElements : v16;
                        if(v1 == 0) {
                            v18 = v6;
                            v19 = 0;
                            Gdx.gl.glTexImage2D(v5 + v17, v11, v8, v12, v20, 0, v9, this.glType, byteBuffer0);
                        }
                        else {
                            v18 = v6;
                            if(v8 == ETC1.ETC1_RGB8_OES) {
                                v19 = v1;
                                if(Gdx.graphics.supportsExtension("GL_OES_compressed_ETC1_RGB8_texture")) {
                                    Gdx.gl.glCompressedTexImage2D(v5 + v17, v11, v8, v12, v20, 0, v14, byteBuffer0);
                                }
                                else {
                                    Pixmap pixmap0 = ETC1.decodeImage(new ETC1Data(v12, v20, byteBuffer0, 0), Format.RGB888);
                                    Gdx.gl.glTexImage2D(v5 + v17, v11, pixmap0.getGLInternalFormat(), pixmap0.getWidth(), pixmap0.getHeight(), 0, pixmap0.getGLFormat(), pixmap0.getGLType(), pixmap0.getPixels());
                                    pixmap0.dispose();
                                }
                            }
                            else {
                                v19 = v1;
                                Gdx.gl.glCompressedTexImage2D(v5 + v17, v11, v8, v12, v20, 0, v14, byteBuffer0);
                            }
                        }
                        v16 = v20;
                    }
                    else {
                        v18 = v6;
                        v19 = v1;
                    }
                }
                else {
                    v18 = v6;
                    v19 = v1;
                }
                ++v17;
                v6 = v18;
                v1 = v19;
            }
        }
        if(v7 != 4) {
            Gdx.gl.glPixelStorei(0xCF5, v7);
        }
        if(this.useMipMaps()) {
            Gdx.gl.glGenerateMipmap(v5);
        }
        this.disposePreparedData();
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public Pixmap consumePixmap() {
        throw new GdxRuntimeException("This TextureData implementation does not return a Pixmap");
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean disposePixmap() {
        throw new GdxRuntimeException("This TextureData implementation does not return a Pixmap");
    }

    public void disposePreparedData() {
        ByteBuffer byteBuffer0 = this.compressedData;
        if(byteBuffer0 != null) {
            BufferUtils.disposeUnsafeByteBuffer(byteBuffer0);
        }
        this.compressedData = null;
    }

    public ByteBuffer getData(int v, int v1) {
        int v2 = this.imagePos;
        for(int v3 = 0; v3 < this.numberOfMipmapLevels; ++v3) {
            int v4 = this.compressedData.getInt(v2) + 3 & -4;
            if(v3 == v) {
                int v5 = v2 + 4;
                for(int v6 = 0; v6 < this.numberOfFaces; ++v6) {
                    if(v6 == v1) {
                        this.compressedData.position(v5);
                        ByteBuffer byteBuffer0 = this.compressedData.slice();
                        byteBuffer0.limit(v4);
                        return byteBuffer0;
                    }
                    v5 += v4;
                }
                v2 = v5;
            }
            else {
                v2 = v2 + 4 + v4 * this.numberOfFaces;
            }
        }
        return null;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public Format getFormat() {
        throw new GdxRuntimeException("This TextureData implementation directly handles texture formats.");
    }

    public int getGlInternalFormat() {
        return this.glInternalFormat;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData, com.badlogic.gdx.graphics.CubemapData
    public int getHeight() {
        return this.pixelHeight;
    }

    public int getNumberOfFaces() {
        return this.numberOfFaces;
    }

    public int getNumberOfMipMapLevels() {
        return this.numberOfMipmapLevels;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public TextureDataType getType() {
        return TextureDataType.Custom;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData, com.badlogic.gdx.graphics.CubemapData
    public int getWidth() {
        return this.pixelWidth;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData, com.badlogic.gdx.graphics.CubemapData
    public boolean isManaged() {
        return true;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData, com.badlogic.gdx.graphics.CubemapData
    public boolean isPrepared() {
        return this.compressedData != null;
    }

    @Override  // com.badlogic.gdx.graphics.TextureData, com.badlogic.gdx.graphics.CubemapData
    public void prepare() {
        DataInputStream dataInputStream0;
        if(this.compressedData != null) {
            throw new GdxRuntimeException("Already prepared");
        }
        FileHandle fileHandle0 = this.file;
        if(fileHandle0 == null) {
            throw new GdxRuntimeException("Need a file to load from");
        }
        if(fileHandle0.name().endsWith(".zktx")) {
            try {
                byte[] arr_b = new byte[0x2800];
                dataInputStream0 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(this.file.read())));
                this.compressedData = BufferUtils.newUnsafeByteBuffer(dataInputStream0.readInt());
                int v1;
                while((v1 = dataInputStream0.read(arr_b)) != -1) {
                    this.compressedData.put(arr_b, 0, v1);
                }
                this.compressedData.position(0);
                this.compressedData.limit(this.compressedData.capacity());
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Couldn\'t load zktx file \'" + this.file + "\'", exception0);
            }
            finally {
                StreamUtils.closeQuietly(dataInputStream0);
            }
        }
        else {
            this.compressedData = ByteBuffer.wrap(this.file.readBytes());
        }
        if(this.compressedData.get() != -85 || this.compressedData.get() != 75 || this.compressedData.get() != 84 || this.compressedData.get() != 88 || this.compressedData.get() != 0x20 || this.compressedData.get() != 49 || this.compressedData.get() != 49 || this.compressedData.get() != -69 || this.compressedData.get() != 13 || this.compressedData.get() != 10 || this.compressedData.get() != 26 || this.compressedData.get() != 10) {
            throw new GdxRuntimeException("Invalid KTX Header");
        }
        int v2 = this.compressedData.getInt();
        if(v2 != 0x1020304 && v2 != 0x4030201) {
            throw new GdxRuntimeException("Invalid KTX Header");
        }
        if(v2 != 0x4030201) {
            this.compressedData.order((this.compressedData.order() == ByteOrder.BIG_ENDIAN ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN));
        }
        this.glType = this.compressedData.getInt();
        this.glTypeSize = this.compressedData.getInt();
        this.glFormat = this.compressedData.getInt();
        this.glInternalFormat = this.compressedData.getInt();
        this.glBaseInternalFormat = this.compressedData.getInt();
        this.pixelWidth = this.compressedData.getInt();
        this.pixelHeight = this.compressedData.getInt();
        this.pixelDepth = this.compressedData.getInt();
        this.numberOfArrayElements = this.compressedData.getInt();
        this.numberOfFaces = this.compressedData.getInt();
        this.numberOfMipmapLevels = this.compressedData.getInt();
        if(this.numberOfMipmapLevels == 0) {
            this.numberOfMipmapLevels = 1;
            this.useMipMaps = true;
        }
        int v3 = this.compressedData.getInt();
        this.imagePos = this.compressedData.position() + v3;
        if(!this.compressedData.isDirect()) {
            int v4 = this.imagePos;
            for(int v5 = 0; v5 < this.numberOfMipmapLevels; ++v5) {
                v4 += (this.compressedData.getInt(v4) + 3 & -4) * this.numberOfFaces + 4;
            }
            this.compressedData.limit(v4);
            this.compressedData.position(0);
            ByteBuffer byteBuffer0 = BufferUtils.newUnsafeByteBuffer(v4);
            byteBuffer0.order(this.compressedData.order());
            byteBuffer0.put(this.compressedData);
            this.compressedData = byteBuffer0;
        }
    }

    @Override  // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return this.useMipMaps;
    }
}

