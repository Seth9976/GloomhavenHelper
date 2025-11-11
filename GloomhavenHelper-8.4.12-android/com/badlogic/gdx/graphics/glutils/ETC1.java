package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ETC1 {
    public static final class ETC1Data implements Disposable {
        public final ByteBuffer compressedData;
        public final int dataOffset;
        public final int height;
        public final int width;

        public ETC1Data(int v, int v1, ByteBuffer byteBuffer0, int v2) {
            this.width = v;
            this.height = v1;
            this.compressedData = byteBuffer0;
            this.dataOffset = v2;
            this.checkNPOT();
        }

        public ETC1Data(FileHandle fileHandle0) {
            DataInputStream dataInputStream0;
            byte[] arr_b = new byte[0x2800];
            try {
                dataInputStream0 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(fileHandle0.read())));
                this.compressedData = BufferUtils.newUnsafeByteBuffer(dataInputStream0.readInt());
                int v1;
                while((v1 = dataInputStream0.read(arr_b)) != -1) {
                    this.compressedData.put(arr_b, 0, v1);
                }
                this.compressedData.position(0);
                this.compressedData.limit(this.compressedData.capacity());
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Couldn\'t load pkm file \'" + fileHandle0 + "\'", exception0);
            }
            finally {
                StreamUtils.closeQuietly(dataInputStream0);
            }
            this.width = ETC1.getWidthPKM(this.compressedData, 0);
            this.height = ETC1.getHeightPKM(this.compressedData, 0);
            this.dataOffset = ETC1.PKM_HEADER_SIZE;
            this.compressedData.position(this.dataOffset);
            this.checkNPOT();
        }

        private void checkNPOT() {
            if(!MathUtils.isPowerOfTwo(this.width) || !MathUtils.isPowerOfTwo(this.height)) {
                System.out.println("ETC1Data warning: non-power-of-two ETC1 textures may crash the driver of PowerVR GPUs");
            }
        }

        @Override  // com.badlogic.gdx.utils.Disposable
        public void dispose() {
            BufferUtils.disposeUnsafeByteBuffer(this.compressedData);
        }

        public boolean hasPKMHeader() {
            return this.dataOffset == 16;
        }

        // 去混淆评级： 低(40)
        @Override
        public String toString() {
            return this.hasPKMHeader() ? (ETC1.isValidPKM(this.compressedData, 0) ? "valid" : "invalid") + " pkm [" + ETC1.getWidthPKM(this.compressedData, 0) + "x" + ETC1.getHeightPKM(this.compressedData, 0) + "], compressed: " + (this.compressedData.capacity() - ETC1.PKM_HEADER_SIZE) : "raw [" + this.width + "x" + this.height + "], compressed: " + (this.compressedData.capacity() - ETC1.PKM_HEADER_SIZE);
        }

        public void write(FileHandle fileHandle0) {
            DataOutputStream dataOutputStream0;
            byte[] arr_b = new byte[0x2800];
            this.compressedData.position(0);
            this.compressedData.limit(this.compressedData.capacity());
            try {
                dataOutputStream0 = new DataOutputStream(new GZIPOutputStream(fileHandle0.write(false)));
                dataOutputStream0.writeInt(this.compressedData.capacity());
                for(int v1 = 0; v1 != this.compressedData.capacity(); v1 += v2) {
                    int v2 = Math.min(this.compressedData.remaining(), 0x2800);
                    this.compressedData.get(arr_b, 0, v2);
                    dataOutputStream0.write(arr_b, 0, v2);
                }
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Couldn\'t write PKM file to \'" + fileHandle0 + "\'", exception0);
            }
            finally {
                StreamUtils.closeQuietly(dataOutputStream0);
            }
            this.compressedData.position(this.dataOffset);
            this.compressedData.limit(this.compressedData.capacity());
        }
    }

    public static int ETC1_RGB8_OES = 0x8D64;
    public static int PKM_HEADER_SIZE = 16;

    static {
    }

    public static Pixmap decodeImage(ETC1Data eTC1$ETC1Data0, Format pixmap$Format0) {
        int v3;
        int v2;
        int v1;
        if(eTC1$ETC1Data0.hasPKMHeader()) {
            int v = ETC1.getWidthPKM(eTC1$ETC1Data0.compressedData, 0);
            v1 = ETC1.getHeightPKM(eTC1$ETC1Data0.compressedData, 0);
            v2 = v;
            v3 = 16;
        }
        else {
            v1 = eTC1$ETC1Data0.height;
            v2 = eTC1$ETC1Data0.width;
            v3 = 0;
        }
        int v4 = ETC1.getPixelSize(pixmap$Format0);
        Pixmap pixmap0 = new Pixmap(v2, v1, pixmap$Format0);
        ByteBuffer byteBuffer0 = pixmap0.getPixels();
        ETC1.decodeImage(eTC1$ETC1Data0.compressedData, v3, byteBuffer0, 0, v2, v1, v4);
        return pixmap0;
    }

    private static native void decodeImage(ByteBuffer arg0, int arg1, ByteBuffer arg2, int arg3, int arg4, int arg5, int arg6) {
    }

    public static ETC1Data encodeImage(Pixmap pixmap0) {
        int v = ETC1.getPixelSize(pixmap0.getFormat());
        ByteBuffer byteBuffer0 = ETC1.encodeImage(pixmap0.getPixels(), 0, pixmap0.getWidth(), pixmap0.getHeight(), v);
        BufferUtils.newUnsafeByteBuffer(byteBuffer0);
        return new ETC1Data(pixmap0.getWidth(), pixmap0.getHeight(), byteBuffer0, 0);
    }

    private static native ByteBuffer encodeImage(ByteBuffer arg0, int arg1, int arg2, int arg3, int arg4) {
    }

    public static ETC1Data encodeImagePKM(Pixmap pixmap0) {
        int v = ETC1.getPixelSize(pixmap0.getFormat());
        ByteBuffer byteBuffer0 = ETC1.encodeImagePKM(pixmap0.getPixels(), 0, pixmap0.getWidth(), pixmap0.getHeight(), v);
        BufferUtils.newUnsafeByteBuffer(byteBuffer0);
        return new ETC1Data(pixmap0.getWidth(), pixmap0.getHeight(), byteBuffer0, 16);
    }

    private static native ByteBuffer encodeImagePKM(ByteBuffer arg0, int arg1, int arg2, int arg3, int arg4) {
    }

    public static native void formatHeader(ByteBuffer arg0, int arg1, int arg2, int arg3) {
    }

    public static native int getCompressedDataSize(int arg0, int arg1) {
    }

    static native int getHeightPKM(ByteBuffer arg0, int arg1) {
    }

    private static int getPixelSize(Format pixmap$Format0) {
        if(pixmap$Format0 == Format.RGB565) {
            return 2;
        }
        if(pixmap$Format0 != Format.RGB888) {
            throw new GdxRuntimeException("Can only handle RGB565 or RGB888 images");
        }
        return 3;
    }

    static native int getWidthPKM(ByteBuffer arg0, int arg1) {
    }

    static native boolean isValidPKM(ByteBuffer arg0, int arg1) {
    }
}

