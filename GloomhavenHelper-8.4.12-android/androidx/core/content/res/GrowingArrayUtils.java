package androidx.core.content.res;

import java.lang.reflect.Array;

final class GrowingArrayUtils {
    static final boolean $assertionsDisabled;

    static {
    }

    public static int[] append(int[] arr_v, int v, int v1) {
        if(v + 1 > arr_v.length) {
            int[] arr_v1 = new int[GrowingArrayUtils.growSize(v)];
            System.arraycopy(arr_v, 0, arr_v1, 0, v);
            arr_v = arr_v1;
        }
        arr_v[v] = v1;
        return arr_v;
    }

    public static long[] append(long[] arr_v, int v, long v1) {
        if(v + 1 > arr_v.length) {
            long[] arr_v1 = new long[GrowingArrayUtils.growSize(v)];
            System.arraycopy(arr_v, 0, arr_v1, 0, v);
            arr_v = arr_v1;
        }
        arr_v[v] = v1;
        return arr_v;
    }

    public static Object[] append(Object[] arr_object, int v, Object object0) {
        if(v + 1 > arr_object.length) {
            Object[] arr_object1 = (Object[])Array.newInstance(arr_object.getClass().getComponentType(), GrowingArrayUtils.growSize(v));
            System.arraycopy(arr_object, 0, arr_object1, 0, v);
            arr_object = arr_object1;
        }
        arr_object[v] = object0;
        return arr_object;
    }

    public static boolean[] append(boolean[] arr_z, int v, boolean z) {
        if(v + 1 > arr_z.length) {
            boolean[] arr_z1 = new boolean[GrowingArrayUtils.growSize(v)];
            System.arraycopy(arr_z, 0, arr_z1, 0, v);
            arr_z = arr_z1;
        }
        arr_z[v] = z;
        return arr_z;
    }

    public static int growSize(int v) {
        return v > 4 ? v * 2 : 8;
    }

    public static int[] insert(int[] arr_v, int v, int v1, int v2) {
        if(v + 1 <= arr_v.length) {
            System.arraycopy(arr_v, v1, arr_v, v1 + 1, v - v1);
            arr_v[v1] = v2;
            return arr_v;
        }
        int[] arr_v1 = new int[GrowingArrayUtils.growSize(v)];
        System.arraycopy(arr_v, 0, arr_v1, 0, v1);
        arr_v1[v1] = v2;
        System.arraycopy(arr_v, v1, arr_v1, v1 + 1, arr_v.length - v1);
        return arr_v1;
    }

    public static long[] insert(long[] arr_v, int v, int v1, long v2) {
        if(v + 1 <= arr_v.length) {
            System.arraycopy(arr_v, v1, arr_v, v1 + 1, v - v1);
            arr_v[v1] = v2;
            return arr_v;
        }
        long[] arr_v1 = new long[GrowingArrayUtils.growSize(v)];
        System.arraycopy(arr_v, 0, arr_v1, 0, v1);
        arr_v1[v1] = v2;
        System.arraycopy(arr_v, v1, arr_v1, v1 + 1, arr_v.length - v1);
        return arr_v1;
    }

    public static Object[] insert(Object[] arr_object, int v, int v1, Object object0) {
        if(v + 1 <= arr_object.length) {
            System.arraycopy(arr_object, v1, arr_object, v1 + 1, v - v1);
            arr_object[v1] = object0;
            return arr_object;
        }
        Object[] arr_object1 = (Object[])Array.newInstance(arr_object.getClass().getComponentType(), GrowingArrayUtils.growSize(v));
        System.arraycopy(arr_object, 0, arr_object1, 0, v1);
        arr_object1[v1] = object0;
        System.arraycopy(arr_object, v1, arr_object1, v1 + 1, arr_object.length - v1);
        return arr_object1;
    }

    public static boolean[] insert(boolean[] arr_z, int v, int v1, boolean z) {
        if(v + 1 <= arr_z.length) {
            System.arraycopy(arr_z, v1, arr_z, v1 + 1, v - v1);
            arr_z[v1] = z;
            return arr_z;
        }
        boolean[] arr_z1 = new boolean[GrowingArrayUtils.growSize(v)];
        System.arraycopy(arr_z, 0, arr_z1, 0, v1);
        arr_z1[v1] = z;
        System.arraycopy(arr_z, v1, arr_z1, v1 + 1, arr_z.length - v1);
        return arr_z1;
    }
}

