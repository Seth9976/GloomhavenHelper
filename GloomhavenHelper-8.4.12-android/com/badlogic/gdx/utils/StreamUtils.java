package com.badlogic.gdx.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.ByteBuffer;

public final class StreamUtils {
    public static class OptimizedByteArrayOutputStream extends ByteArrayOutputStream {
        public OptimizedByteArrayOutputStream(int v) {
            super(v);
        }

        public byte[] getBuffer() {
            return this.buf;
        }

        @Override
        public byte[] toByteArray() {
            synchronized(this) {
                return this.count == this.buf.length ? this.buf : super.toByteArray();
            }
        }
    }

    public static final int DEFAULT_BUFFER_SIZE = 0x1000;
    public static final byte[] EMPTY_BYTES;

    static {
        StreamUtils.EMPTY_BYTES = new byte[0];
    }

    public static void closeQuietly(Closeable closeable0) {
        if(closeable0 != null) {
            try {
                closeable0.close();
            }
            catch(Throwable unused_ex) {
            }
        }
    }

    public static int copyStream(InputStream inputStream0, ByteBuffer byteBuffer0, byte[] arr_b) throws IOException {
        int v = byteBuffer0.position();
        int v1 = 0;
        int v2;
        while((v2 = inputStream0.read(arr_b)) != -1) {
            BufferUtils.copy(arr_b, 0, byteBuffer0, v2);
            v1 += v2;
            byteBuffer0.position(v + v1);
        }
        byteBuffer0.position(v);
        return v1;
    }

    public static void copyStream(InputStream inputStream0, OutputStream outputStream0) throws IOException {
        StreamUtils.copyStream(inputStream0, outputStream0, new byte[0x1000]);
    }

    public static void copyStream(InputStream inputStream0, OutputStream outputStream0, int v) throws IOException {
        StreamUtils.copyStream(inputStream0, outputStream0, new byte[v]);
    }

    public static void copyStream(InputStream inputStream0, OutputStream outputStream0, byte[] arr_b) throws IOException {
        int v;
        while((v = inputStream0.read(arr_b)) != -1) {
            outputStream0.write(arr_b, 0, v);
        }
    }

    public static void copyStream(InputStream inputStream0, ByteBuffer byteBuffer0) throws IOException {
        StreamUtils.copyStream(inputStream0, byteBuffer0, new byte[0x1000]);
    }

    public static void copyStream(InputStream inputStream0, ByteBuffer byteBuffer0, int v) throws IOException {
        StreamUtils.copyStream(inputStream0, byteBuffer0, new byte[v]);
    }

    public static byte[] copyStreamToByteArray(InputStream inputStream0) throws IOException {
        return StreamUtils.copyStreamToByteArray(inputStream0, inputStream0.available());
    }

    public static byte[] copyStreamToByteArray(InputStream inputStream0, int v) throws IOException {
        OptimizedByteArrayOutputStream streamUtils$OptimizedByteArrayOutputStream0 = new OptimizedByteArrayOutputStream(Math.max(0, v));
        StreamUtils.copyStream(inputStream0, streamUtils$OptimizedByteArrayOutputStream0);
        return streamUtils$OptimizedByteArrayOutputStream0.toByteArray();
    }

    public static String copyStreamToString(InputStream inputStream0) throws IOException {
        return StreamUtils.copyStreamToString(inputStream0, inputStream0.available(), null);
    }

    public static String copyStreamToString(InputStream inputStream0, int v) throws IOException {
        return StreamUtils.copyStreamToString(inputStream0, v, null);
    }

    public static String copyStreamToString(InputStream inputStream0, int v, @Null String s) throws IOException {
        InputStreamReader inputStreamReader0 = s == null ? new InputStreamReader(inputStream0) : new InputStreamReader(inputStream0, s);
        StringWriter stringWriter0 = new StringWriter(Math.max(0, v));
        char[] arr_c = new char[0x1000];
        int v1;
        while((v1 = inputStreamReader0.read(arr_c)) != -1) {
            stringWriter0.write(arr_c, 0, v1);
        }
        return stringWriter0.toString();
    }
}

