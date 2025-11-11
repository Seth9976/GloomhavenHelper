package androidx.collection;

class ContainerHelpers {
    static final int[] EMPTY_INTS;
    static final long[] EMPTY_LONGS;
    static final Object[] EMPTY_OBJECTS;

    static {
        ContainerHelpers.EMPTY_INTS = new int[0];
        ContainerHelpers.EMPTY_LONGS = new long[0];
        ContainerHelpers.EMPTY_OBJECTS = new Object[0];
    }

    static int binarySearch(int[] arr_v, int v, int v1) {
        int v2 = v - 1;
        int v3 = 0;
        while(v3 <= v2) {
            int v4 = v3 + v2 >>> 1;
            int v5 = arr_v[v4];
            if(v5 < v1) {
                v3 = v4 + 1;
                continue;
            }
            if(v5 > v1) {
                v2 = v4 - 1;
                continue;
            }
            return v4;
        }
        return ~v3;
    }

    static int binarySearch(long[] arr_v, int v, long v1) {
        int v2 = v - 1;
        int v3 = 0;
        while(v3 <= v2) {
            int v4 = v3 + v2 >>> 1;
            long v5 = arr_v[v4];
            if(v5 < v1) {
                v3 = v4 + 1;
                continue;
            }
            if(v5 > v1) {
                v2 = v4 - 1;
                continue;
            }
            return v4;
        }
        return ~v3;
    }

    // 去混淆评级： 低(20)
    public static boolean equal(Object object0, Object object1) {
        return object0 == object1 || object0 != null && object0.equals(object1);
    }

    public static int idealByteArraySize(int v) {
        for(int v1 = 4; v1 < 0x20; ++v1) {
            int v2 = (1 << v1) - 12;
            if(v <= v2) {
                return v2;
            }
        }
        return v;
    }

    public static int idealIntArraySize(int v) {
        return ContainerHelpers.idealByteArraySize(v * 4) / 4;
    }

    public static int idealLongArraySize(int v) {
        return ContainerHelpers.idealByteArraySize(v * 8) / 8;
    }
}

