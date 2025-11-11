package com.badlogic.gdx.graphics;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.ByteArray;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class PixmapIO {
    static class CIM {
        private static final int BUFFER_SIZE = 32000;
        private static final byte[] readBuffer;
        private static final byte[] writeBuffer;

        static {
            CIM.writeBuffer = new byte[32000];
            CIM.readBuffer = new byte[32000];
        }

        public static Pixmap read(FileHandle fileHandle0) {
            Pixmap pixmap0;
            Exception exception1;
            DataInputStream dataInputStream0;
            try {
                dataInputStream0 = new DataInputStream(new InflaterInputStream(new BufferedInputStream(fileHandle0.read())));
            }
            catch(Exception exception0) {
                exception1 = exception0;
                throw new GdxRuntimeException("Couldn\'t read Pixmap from file \'" + fileHandle0 + "\'", exception1);
            }
            finally {
                StreamUtils.closeQuietly(dataInputStream0);
            }
            try {
                pixmap0 = new Pixmap(dataInputStream0.readInt(), dataInputStream0.readInt(), Format.fromGdx2DPixmapFormat(dataInputStream0.readInt()));
                ByteBuffer byteBuffer0 = pixmap0.getPixels();
                byteBuffer0.position(0);
                byteBuffer0.limit(byteBuffer0.capacity());
                synchronized(CIM.readBuffer) {
                    int v2;
                    while((v2 = dataInputStream0.read(CIM.readBuffer)) > 0) {
                        byteBuffer0.put(CIM.readBuffer, 0, v2);
                    }
                }
                byteBuffer0.position(0);
                byteBuffer0.limit(byteBuffer0.capacity());
                return pixmap0;
            }
            catch(Exception exception1) {
            }
            throw new GdxRuntimeException("Couldn\'t read Pixmap from file \'" + fileHandle0 + "\'", exception1);
        }

        public static void write(FileHandle fileHandle0, Pixmap pixmap0) {
            DataOutputStream dataOutputStream0;
            try {
                dataOutputStream0 = new DataOutputStream(new DeflaterOutputStream(fileHandle0.write(false)));
                dataOutputStream0.writeInt(pixmap0.getWidth());
                dataOutputStream0.writeInt(pixmap0.getHeight());
                dataOutputStream0.writeInt(Format.toGdx2DPixmapFormat(pixmap0.getFormat()));
                ByteBuffer byteBuffer0 = pixmap0.getPixels();
                byteBuffer0.position(0);
                byteBuffer0.limit(byteBuffer0.capacity());
                int v1 = byteBuffer0.capacity();
                int v2 = byteBuffer0.capacity();
                synchronized(CIM.writeBuffer) {
                    for(int v3 = 0; v3 < v2 / 32000; ++v3) {
                        byteBuffer0.get(CIM.writeBuffer);
                        dataOutputStream0.write(CIM.writeBuffer);
                    }
                    byteBuffer0.get(CIM.writeBuffer, 0, v1 % 32000);
                    dataOutputStream0.write(CIM.writeBuffer, 0, v1 % 32000);
                }
                byteBuffer0.position(0);
                byteBuffer0.limit(byteBuffer0.capacity());
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Couldn\'t write Pixmap to file \'" + fileHandle0 + "\'", exception0);
            }
            finally {
                StreamUtils.closeQuietly(dataOutputStream0);
            }
        }
    }

    public static class PNG implements Disposable {
        static class ChunkBuffer extends DataOutputStream {
            final ByteArrayOutputStream buffer;
            final CRC32 crc;

            ChunkBuffer(int v) {
                this(new ByteArrayOutputStream(v), new CRC32());
            }

            private ChunkBuffer(ByteArrayOutputStream byteArrayOutputStream0, CRC32 cRC320) {
                super(new CheckedOutputStream(byteArrayOutputStream0, cRC320));
                this.buffer = byteArrayOutputStream0;
                this.crc = cRC320;
            }

            public void endChunk(DataOutputStream dataOutputStream0) throws IOException {
                this.flush();
                dataOutputStream0.writeInt(this.buffer.size() - 4);
                this.buffer.writeTo(dataOutputStream0);
                dataOutputStream0.writeInt(((int)this.crc.getValue()));
                this.buffer.reset();
                this.crc.reset();
            }
        }

        private static final byte COLOR_ARGB = 6;
        private static final byte COMPRESSION_DEFLATE = 0;
        private static final byte FILTER_NONE = 0;
        private static final int IDAT = 1229209940;
        private static final int IEND = 0x49454E44;
        private static final int IHDR = 1229472850;
        private static final byte INTERLACE_NONE = 0;
        private static final byte PAETH = 4;
        private static final byte[] SIGNATURE;
        private final ChunkBuffer buffer;
        private ByteArray curLineBytes;
        private final Deflater deflater;
        private boolean flipY;
        private int lastLineLen;
        private ByteArray lineOutBytes;
        private ByteArray prevLineBytes;

        static {
            PNG.SIGNATURE = new byte[]{(byte)0x89, 80, 78, 71, 13, 10, 26, 10};
        }

        public PNG() {
            this(0x4000);
        }

        public PNG(int v) {
            this.flipY = true;
            this.buffer = new ChunkBuffer(v);
            this.deflater = new Deflater();
        }

        @Override  // com.badlogic.gdx.utils.Disposable
        public void dispose() {
            this.deflater.end();
        }

        public void setCompression(int v) {
            this.deflater.setLevel(v);
        }

        public void setFlipY(boolean z) {
            this.flipY = z;
        }

        public void write(FileHandle fileHandle0, Pixmap pixmap0) throws IOException {
            OutputStream outputStream0 = fileHandle0.write(false);
            try {
                this.write(outputStream0, pixmap0);
            }
            finally {
                StreamUtils.closeQuietly(outputStream0);
            }
        }

        public void write(OutputStream outputStream0, Pixmap pixmap0) throws IOException {
            int v10;
            byte[] arr_b2;
            byte[] arr_b1;
            byte[] arr_b;
            DeflaterOutputStream deflaterOutputStream0 = new DeflaterOutputStream(this.buffer, this.deflater);
            DataOutputStream dataOutputStream0 = new DataOutputStream(outputStream0);
            dataOutputStream0.write(PNG.SIGNATURE);
            this.buffer.writeInt(1229472850);
            int v = pixmap0.getWidth();
            this.buffer.writeInt(v);
            int v1 = pixmap0.getHeight();
            this.buffer.writeInt(v1);
            this.buffer.writeByte(8);
            this.buffer.writeByte(6);
            this.buffer.writeByte(0);
            this.buffer.writeByte(0);
            this.buffer.writeByte(0);
            this.buffer.endChunk(dataOutputStream0);
            this.buffer.writeInt(1229209940);
            this.deflater.reset();
            int v2 = pixmap0.getWidth();
            ByteArray byteArray0 = this.lineOutBytes;
            if(byteArray0 == null) {
                ByteArray byteArray1 = new ByteArray(v2 * 4);
                this.lineOutBytes = byteArray1;
                arr_b = byteArray1.items;
                ByteArray byteArray2 = new ByteArray(v2 * 4);
                this.curLineBytes = byteArray2;
                arr_b1 = byteArray2.items;
                ByteArray byteArray3 = new ByteArray(v2 * 4);
                this.prevLineBytes = byteArray3;
                arr_b2 = byteArray3.items;
            }
            else {
                arr_b = byteArray0.ensureCapacity(v2 * 4);
                arr_b1 = this.curLineBytes.ensureCapacity(v2 * 4);
                arr_b2 = this.prevLineBytes.ensureCapacity(v2 * 4);
                int v3 = this.lastLineLen;
                for(int v4 = 0; v4 < v3; ++v4) {
                    arr_b2[v4] = 0;
                }
            }
            this.lastLineLen = v2 * 4;
            ByteBuffer byteBuffer0 = pixmap0.getPixels();
            int v5 = byteBuffer0.position();
            int v6 = pixmap0.getFormat() == Format.RGBA8888 ? 1 : 0;
            int v7 = pixmap0.getHeight();
            byte[] arr_b3 = arr_b2;
            byte[] arr_b4 = arr_b1;
            int v8 = 0;
            while(v8 < v7) {
                int v9 = this.flipY ? v7 - v8 - 1 : v8;
                if(v6 == 0) {
                    int v11 = 0;
                    for(int v12 = 0; v11 < pixmap0.getWidth(); v12 += 4) {
                        int v13 = pixmap0.getPixel(v11, v9);
                        arr_b4[v12] = (byte)(v13 >> 24 & 0xFF);
                        arr_b4[v12 + 1] = (byte)(v13 >> 16 & 0xFF);
                        arr_b4[v12 + 2] = (byte)(v13 >> 8 & 0xFF);
                        arr_b4[v12 + 3] = (byte)(v13 & 0xFF);
                        ++v11;
                    }
                    v10 = 0;
                }
                else {
                    byteBuffer0.position(v9 * (v2 * 4));
                    byteBuffer0.get(arr_b4, 0, v2 * 4);
                    v10 = v6;
                }
                arr_b[0] = (byte)(arr_b4[0] - arr_b3[0]);
                arr_b[1] = (byte)(arr_b4[1] - arr_b3[1]);
                arr_b[2] = (byte)(arr_b4[2] - arr_b3[2]);
                arr_b[3] = (byte)(arr_b4[3] - arr_b3[3]);
                for(int v14 = 4; v14 < v2 * 4; ++v14) {
                    int v15 = arr_b4[v14 - 4] & 0xFF;
                    int v16 = arr_b3[v14] & 0xFF;
                    int v17 = arr_b3[v14 - 4] & 0xFF;
                    int v18 = v15 + v16 - v17;
                    int v19 = v18 - v15;
                    v19 = v19 >= 0 ? v18 - v15 : -v19;
                    int v20 = v18 - v16;
                    v20 = v20 >= 0 ? v18 - v16 : -v20;
                    int v21 = v18 - v17;
                    v21 = v21 >= 0 ? v18 - v17 : -v21;
                    if(v19 <= v20 && v19 <= v21) {
                        v16 = v15;
                    }
                    else if(v20 > v21) {
                        v16 = v17;
                    }
                    arr_b[v14] = (byte)(arr_b4[v14] - v16);
                }
                deflaterOutputStream0.write(4);
                deflaterOutputStream0.write(arr_b, 0, v2 * 4);
                ++v8;
                v6 = v10;
                byte[] arr_b5 = arr_b3;
                arr_b3 = arr_b4;
                arr_b4 = arr_b5;
            }
            byteBuffer0.position(v5);
            deflaterOutputStream0.finish();
            this.buffer.endChunk(dataOutputStream0);
            this.buffer.writeInt(0x49454E44);
            this.buffer.endChunk(dataOutputStream0);
            outputStream0.flush();
        }
    }

    public static Pixmap readCIM(FileHandle fileHandle0) {
        return CIM.read(fileHandle0);
    }

    public static void writeCIM(FileHandle fileHandle0, Pixmap pixmap0) {
        CIM.write(fileHandle0, pixmap0);
    }

    public static void writePNG(FileHandle fileHandle0, Pixmap pixmap0) {
        PixmapIO.writePNG(fileHandle0, pixmap0, -1, false);
    }

    public static void writePNG(FileHandle fileHandle0, Pixmap pixmap0, int v, boolean z) {
        try {
            PNG pixmapIO$PNG0 = new PNG(((int)(((float)(pixmap0.getWidth() * pixmap0.getHeight())) * 1.5f)));
            try {
                pixmapIO$PNG0.setFlipY(z);
                pixmapIO$PNG0.setCompression(v);
                pixmapIO$PNG0.write(fileHandle0, pixmap0);
            }
            finally {
                pixmapIO$PNG0.dispose();
            }
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException("Error writing PNG: " + fileHandle0, iOException0);
        }
    }
}

