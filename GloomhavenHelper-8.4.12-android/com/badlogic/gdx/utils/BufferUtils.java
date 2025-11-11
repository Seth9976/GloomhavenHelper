package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

public final class BufferUtils {
    static int allocatedUnsafe;
    static Array unsafeBuffers;

    static {
        BufferUtils.unsafeBuffers = new Array();
        BufferUtils.allocatedUnsafe = 0;
    }

    private static int bytesToElements(Buffer buffer0, int v) {
        if(buffer0 instanceof ByteBuffer) {
            return v;
        }
        if(buffer0 instanceof ShortBuffer) {
            return v >>> 1;
        }
        if(buffer0 instanceof CharBuffer) {
            return v >>> 1;
        }
        if(buffer0 instanceof IntBuffer) {
            return v >>> 2;
        }
        if(buffer0 instanceof LongBuffer) {
            return v >>> 3;
        }
        if(buffer0 instanceof FloatBuffer) {
            return v >>> 2;
        }
        if(!(buffer0 instanceof DoubleBuffer)) {
            throw new GdxRuntimeException("Can\'t copy to a " + buffer0.getClass().getName() + " instance");
        }
        return v >>> 3;
    }

    public static native void clear(ByteBuffer arg0, int arg1) {
    }

    public static void copy(Buffer buffer0, Buffer buffer1, int v) {
        int v1 = BufferUtils.elementsToBytes(buffer0, v);
        buffer1.limit(buffer1.position() + BufferUtils.bytesToElements(buffer1, v1));
        BufferUtils.copyJni(buffer0, BufferUtils.positionInBytes(buffer0), buffer1, BufferUtils.positionInBytes(buffer1), v1);
    }

    public static void copy(byte[] arr_b, int v, Buffer buffer0, int v1) {
        buffer0.limit(buffer0.position() + BufferUtils.bytesToElements(buffer0, v1));
        BufferUtils.copyJni(arr_b, v, buffer0, BufferUtils.positionInBytes(buffer0), v1);
    }

    public static void copy(char[] arr_c, int v, int v1, Buffer buffer0) {
        BufferUtils.copyJni(arr_c, v, buffer0, BufferUtils.positionInBytes(buffer0), v1 << 1);
    }

    public static void copy(char[] arr_c, int v, Buffer buffer0, int v1) {
        buffer0.limit(buffer0.position() + BufferUtils.bytesToElements(buffer0, v1 << 1));
        BufferUtils.copyJni(arr_c, v, buffer0, BufferUtils.positionInBytes(buffer0), v1 << 1);
    }

    public static void copy(double[] arr_f, int v, int v1, Buffer buffer0) {
        BufferUtils.copyJni(arr_f, v, buffer0, BufferUtils.positionInBytes(buffer0), v1 << 3);
    }

    public static void copy(double[] arr_f, int v, Buffer buffer0, int v1) {
        buffer0.limit(buffer0.position() + BufferUtils.bytesToElements(buffer0, v1 << 3));
        BufferUtils.copyJni(arr_f, v, buffer0, BufferUtils.positionInBytes(buffer0), v1 << 3);
    }

    public static void copy(float[] arr_f, int v, int v1, Buffer buffer0) {
        BufferUtils.copyJni(arr_f, v, buffer0, BufferUtils.positionInBytes(buffer0), v1 << 2);
    }

    public static void copy(float[] arr_f, int v, Buffer buffer0, int v1) {
        buffer0.limit(buffer0.position() + BufferUtils.bytesToElements(buffer0, v1 << 2));
        BufferUtils.copyJni(arr_f, v, buffer0, BufferUtils.positionInBytes(buffer0), v1 << 2);
    }

    public static void copy(float[] arr_f, Buffer buffer0, int v, int v1) {
        if(buffer0 instanceof ByteBuffer) {
            buffer0.limit(v << 2);
        }
        else if(buffer0 instanceof FloatBuffer) {
            buffer0.limit(v);
        }
        BufferUtils.copyJni(arr_f, buffer0, v, v1);
        buffer0.position(0);
    }

    public static void copy(int[] arr_v, int v, int v1, Buffer buffer0) {
        BufferUtils.copyJni(arr_v, v, buffer0, BufferUtils.positionInBytes(buffer0), v1 << 2);
    }

    public static void copy(int[] arr_v, int v, Buffer buffer0, int v1) {
        buffer0.limit(buffer0.position() + BufferUtils.bytesToElements(buffer0, v1 << 2));
        BufferUtils.copyJni(arr_v, v, buffer0, BufferUtils.positionInBytes(buffer0), v1 << 2);
    }

    public static void copy(long[] arr_v, int v, int v1, Buffer buffer0) {
        BufferUtils.copyJni(arr_v, v, buffer0, BufferUtils.positionInBytes(buffer0), v1 << 3);
    }

    public static void copy(long[] arr_v, int v, Buffer buffer0, int v1) {
        buffer0.limit(buffer0.position() + BufferUtils.bytesToElements(buffer0, v1 << 3));
        BufferUtils.copyJni(arr_v, v, buffer0, BufferUtils.positionInBytes(buffer0), v1 << 3);
    }

    public static void copy(short[] arr_v, int v, Buffer buffer0, int v1) {
        buffer0.limit(buffer0.position() + BufferUtils.bytesToElements(buffer0, v1 << 1));
        BufferUtils.copyJni(arr_v, v, buffer0, BufferUtils.positionInBytes(buffer0), v1 << 1);
    }

    private static native void copyJni(Buffer arg0, int arg1, Buffer arg2, int arg3, int arg4) {
    }

    private static native void copyJni(byte[] arg0, int arg1, Buffer arg2, int arg3, int arg4) {
    }

    private static native void copyJni(char[] arg0, int arg1, Buffer arg2, int arg3, int arg4) {
    }

    private static native void copyJni(double[] arg0, int arg1, Buffer arg2, int arg3, int arg4) {
    }

    private static native void copyJni(float[] arg0, int arg1, Buffer arg2, int arg3, int arg4) {
    }

    private static native void copyJni(float[] arg0, Buffer arg1, int arg2, int arg3) {
    }

    private static native void copyJni(int[] arg0, int arg1, Buffer arg2, int arg3, int arg4) {
    }

    private static native void copyJni(long[] arg0, int arg1, Buffer arg2, int arg3, int arg4) {
    }

    private static native void copyJni(short[] arg0, int arg1, Buffer arg2, int arg3, int arg4) {
    }

    public static void disposeUnsafeByteBuffer(ByteBuffer byteBuffer0) {
        int v = byteBuffer0.capacity();
        synchronized(BufferUtils.unsafeBuffers) {
            if(BufferUtils.unsafeBuffers.removeValue(byteBuffer0, true)) {
                BufferUtils.allocatedUnsafe -= v;
                BufferUtils.freeMemory(byteBuffer0);
                return;
            }
        }
        throw new IllegalArgumentException("buffer not allocated with newUnsafeByteBuffer or already disposed");
    }

    private static int elementsToBytes(Buffer buffer0, int v) {
        if(buffer0 instanceof ByteBuffer) {
            return v;
        }
        if(buffer0 instanceof ShortBuffer) {
            return v << 1;
        }
        if(buffer0 instanceof CharBuffer) {
            return v << 1;
        }
        if(buffer0 instanceof IntBuffer) {
            return v << 2;
        }
        if(buffer0 instanceof LongBuffer) {
            return v << 3;
        }
        if(buffer0 instanceof FloatBuffer) {
            return v << 2;
        }
        if(!(buffer0 instanceof DoubleBuffer)) {
            throw new GdxRuntimeException("Can\'t copy to a " + buffer0.getClass().getName() + " instance");
        }
        return v << 3;
    }

    private static native long find(Buffer arg0, int arg1, int arg2, Buffer arg3, int arg4, int arg5) {
    }

    private static native long find(Buffer arg0, int arg1, int arg2, Buffer arg3, int arg4, int arg5, float arg6) {
    }

    private static native long find(Buffer arg0, int arg1, int arg2, float[] arg3, int arg4, int arg5) {
    }

    private static native long find(Buffer arg0, int arg1, int arg2, float[] arg3, int arg4, int arg5, float arg6) {
    }

    private static native long find(float[] arg0, int arg1, int arg2, Buffer arg3, int arg4, int arg5) {
    }

    private static native long find(float[] arg0, int arg1, int arg2, Buffer arg3, int arg4, int arg5, float arg6) {
    }

    private static native long find(float[] arg0, int arg1, int arg2, float[] arg3, int arg4, int arg5) {
    }

    private static native long find(float[] arg0, int arg1, int arg2, float[] arg3, int arg4, int arg5, float arg6) {
    }

    public static long findFloats(Buffer buffer0, int v, Buffer buffer1, int v1) {
        return BufferUtils.find(buffer0, BufferUtils.positionInBytes(buffer0), v, buffer1, BufferUtils.positionInBytes(buffer1), v1);
    }

    public static long findFloats(Buffer buffer0, int v, Buffer buffer1, int v1, float f) {
        return BufferUtils.find(buffer0, BufferUtils.positionInBytes(buffer0), v, buffer1, BufferUtils.positionInBytes(buffer1), v1, f);
    }

    public static long findFloats(Buffer buffer0, int v, float[] arr_f, int v1) {
        return BufferUtils.find(buffer0, BufferUtils.positionInBytes(buffer0), v, arr_f, 0, v1);
    }

    public static long findFloats(Buffer buffer0, int v, float[] arr_f, int v1, float f) {
        return BufferUtils.find(buffer0, BufferUtils.positionInBytes(buffer0), v, arr_f, 0, v1, f);
    }

    public static long findFloats(float[] arr_f, int v, Buffer buffer0, int v1) {
        return BufferUtils.find(arr_f, 0, v, buffer0, BufferUtils.positionInBytes(buffer0), v1);
    }

    public static long findFloats(float[] arr_f, int v, Buffer buffer0, int v1, float f) {
        return BufferUtils.find(arr_f, 0, v, buffer0, BufferUtils.positionInBytes(buffer0), v1, f);
    }

    public static long findFloats(float[] arr_f, int v, float[] arr_f1, int v1) {
        return BufferUtils.find(arr_f, 0, v, arr_f1, 0, v1);
    }

    public static long findFloats(float[] arr_f, int v, float[] arr_f1, int v1, float f) {
        return BufferUtils.find(arr_f, 0, v, arr_f1, 0, v1, f);
    }

    private static native void freeMemory(ByteBuffer arg0) {
    }

    public static int getAllocatedBytesUnsafe() {
        return BufferUtils.allocatedUnsafe;
    }

    private static native long getBufferAddress(Buffer arg0) {
    }

    public static long getUnsafeBufferAddress(Buffer buffer0) {
        return BufferUtils.getBufferAddress(buffer0) + ((long)buffer0.position());
    }

    public static boolean isUnsafeByteBuffer(ByteBuffer byteBuffer0) {
        synchronized(BufferUtils.unsafeBuffers) {
        }
        return BufferUtils.unsafeBuffers.contains(byteBuffer0, true);
    }

    public static ByteBuffer newByteBuffer(int v) {
        ByteBuffer byteBuffer0 = ByteBuffer.allocateDirect(v);
        byteBuffer0.order(ByteOrder.nativeOrder());
        return byteBuffer0;
    }

    public static CharBuffer newCharBuffer(int v) {
        ByteBuffer byteBuffer0 = ByteBuffer.allocateDirect(v * 2);
        byteBuffer0.order(ByteOrder.nativeOrder());
        return byteBuffer0.asCharBuffer();
    }

    private static native ByteBuffer newDisposableByteBuffer(int arg0) {
    }

    public static DoubleBuffer newDoubleBuffer(int v) {
        ByteBuffer byteBuffer0 = ByteBuffer.allocateDirect(v * 8);
        byteBuffer0.order(ByteOrder.nativeOrder());
        return byteBuffer0.asDoubleBuffer();
    }

    public static FloatBuffer newFloatBuffer(int v) {
        ByteBuffer byteBuffer0 = ByteBuffer.allocateDirect(v * 4);
        byteBuffer0.order(ByteOrder.nativeOrder());
        return byteBuffer0.asFloatBuffer();
    }

    public static IntBuffer newIntBuffer(int v) {
        ByteBuffer byteBuffer0 = ByteBuffer.allocateDirect(v * 4);
        byteBuffer0.order(ByteOrder.nativeOrder());
        return byteBuffer0.asIntBuffer();
    }

    public static LongBuffer newLongBuffer(int v) {
        ByteBuffer byteBuffer0 = ByteBuffer.allocateDirect(v * 8);
        byteBuffer0.order(ByteOrder.nativeOrder());
        return byteBuffer0.asLongBuffer();
    }

    public static ShortBuffer newShortBuffer(int v) {
        ByteBuffer byteBuffer0 = ByteBuffer.allocateDirect(v * 2);
        byteBuffer0.order(ByteOrder.nativeOrder());
        return byteBuffer0.asShortBuffer();
    }

    public static ByteBuffer newUnsafeByteBuffer(int v) {
        ByteBuffer byteBuffer0 = BufferUtils.newDisposableByteBuffer(v);
        byteBuffer0.order(ByteOrder.nativeOrder());
        BufferUtils.allocatedUnsafe += v;
        synchronized(BufferUtils.unsafeBuffers) {
            BufferUtils.unsafeBuffers.add(byteBuffer0);
            return byteBuffer0;
        }
    }

    public static ByteBuffer newUnsafeByteBuffer(ByteBuffer byteBuffer0) {
        BufferUtils.allocatedUnsafe += byteBuffer0.capacity();
        synchronized(BufferUtils.unsafeBuffers) {
            BufferUtils.unsafeBuffers.add(byteBuffer0);
            return byteBuffer0;
        }
    }

    private static int positionInBytes(Buffer buffer0) {
        if(buffer0 instanceof ByteBuffer) {
            return buffer0.position();
        }
        if(buffer0 instanceof ShortBuffer) {
            return buffer0.position() << 1;
        }
        if(buffer0 instanceof CharBuffer) {
            return buffer0.position() << 1;
        }
        if(buffer0 instanceof IntBuffer) {
            return buffer0.position() << 2;
        }
        if(buffer0 instanceof LongBuffer) {
            return buffer0.position() << 3;
        }
        if(buffer0 instanceof FloatBuffer) {
            return buffer0.position() << 2;
        }
        if(!(buffer0 instanceof DoubleBuffer)) {
            throw new GdxRuntimeException("Can\'t copy to a " + buffer0.getClass().getName() + " instance");
        }
        return buffer0.position() << 3;
    }

    public static void transform(Buffer buffer0, int v, int v1, int v2, Matrix3 matrix30) {
        BufferUtils.transform(buffer0, v, v1, v2, matrix30, 0);
    }

    public static void transform(Buffer buffer0, int v, int v1, int v2, Matrix3 matrix30, int v3) {
        switch(v) {
            case 2: {
                BufferUtils.transformV2M3Jni(buffer0, v1, v2, matrix30.val, BufferUtils.positionInBytes(buffer0) + v3);
                return;
            }
            case 3: {
                BufferUtils.transformV3M3Jni(buffer0, v1, v2, matrix30.val, BufferUtils.positionInBytes(buffer0) + v3);
                return;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void transform(Buffer buffer0, int v, int v1, int v2, Matrix4 matrix40) {
        BufferUtils.transform(buffer0, v, v1, v2, matrix40, 0);
    }

    public static void transform(Buffer buffer0, int v, int v1, int v2, Matrix4 matrix40, int v3) {
        switch(v) {
            case 2: {
                int v4 = BufferUtils.positionInBytes(buffer0);
                BufferUtils.transformV2M4Jni(buffer0, v1, v2, matrix40.val, v4 + v3);
                return;
            }
            case 3: {
                int v5 = BufferUtils.positionInBytes(buffer0);
                BufferUtils.transformV3M4Jni(buffer0, v1, v2, matrix40.val, v5 + v3);
                return;
            }
            case 4: {
                int v6 = BufferUtils.positionInBytes(buffer0);
                BufferUtils.transformV4M4Jni(buffer0, v1, v2, matrix40.val, v6 + v3);
                return;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void transform(float[] arr_f, int v, int v1, int v2, Matrix3 matrix30) {
        BufferUtils.transform(arr_f, v, v1, v2, matrix30, 0);
    }

    public static void transform(float[] arr_f, int v, int v1, int v2, Matrix3 matrix30, int v3) {
        switch(v) {
            case 2: {
                BufferUtils.transformV2M3Jni(arr_f, v1, v2, matrix30.val, v3);
                return;
            }
            case 3: {
                BufferUtils.transformV3M3Jni(arr_f, v1, v2, matrix30.val, v3);
                return;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void transform(float[] arr_f, int v, int v1, int v2, Matrix4 matrix40) {
        BufferUtils.transform(arr_f, v, v1, v2, matrix40, 0);
    }

    public static void transform(float[] arr_f, int v, int v1, int v2, Matrix4 matrix40, int v3) {
        switch(v) {
            case 2: {
                BufferUtils.transformV2M4Jni(arr_f, v1, v2, matrix40.val, v3);
                return;
            }
            case 3: {
                BufferUtils.transformV3M4Jni(arr_f, v1, v2, matrix40.val, v3);
                return;
            }
            case 4: {
                BufferUtils.transformV4M4Jni(arr_f, v1, v2, matrix40.val, v3);
                return;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    private static native void transformV2M3Jni(Buffer arg0, int arg1, int arg2, float[] arg3, int arg4) {
    }

    private static native void transformV2M3Jni(float[] arg0, int arg1, int arg2, float[] arg3, int arg4) {
    }

    private static native void transformV2M4Jni(Buffer arg0, int arg1, int arg2, float[] arg3, int arg4) {
    }

    private static native void transformV2M4Jni(float[] arg0, int arg1, int arg2, float[] arg3, int arg4) {
    }

    private static native void transformV3M3Jni(Buffer arg0, int arg1, int arg2, float[] arg3, int arg4) {
    }

    private static native void transformV3M3Jni(float[] arg0, int arg1, int arg2, float[] arg3, int arg4) {
    }

    private static native void transformV3M4Jni(Buffer arg0, int arg1, int arg2, float[] arg3, int arg4) {
    }

    private static native void transformV3M4Jni(float[] arg0, int arg1, int arg2, float[] arg3, int arg4) {
    }

    private static native void transformV4M4Jni(Buffer arg0, int arg1, int arg2, float[] arg3, int arg4) {
    }

    private static native void transformV4M4Jni(float[] arg0, int arg1, int arg2, float[] arg3, int arg4) {
    }
}

