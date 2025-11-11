package com.badlogic.gdx.utils;

class ComparableTimSort {
    private static final boolean DEBUG = false;
    private static final int INITIAL_TMP_STORAGE_LENGTH = 0x100;
    private static final int MIN_GALLOP = 7;
    private static final int MIN_MERGE = 0x20;
    private Object[] a;
    private int minGallop;
    private final int[] runBase;
    private final int[] runLen;
    private int stackSize;
    private Object[] tmp;
    private int tmpCount;

    ComparableTimSort() {
        this.minGallop = 7;
        this.stackSize = 0;
        this.tmp = new Object[0x100];
        this.runBase = new int[40];
        this.runLen = new int[40];
    }

    private ComparableTimSort(Object[] arr_object) {
        int v;
        this.minGallop = 7;
        this.stackSize = 0;
        this.a = arr_object;
        this.tmp = new Object[(arr_object.length >= 0x200 ? 0x100 : arr_object.length >>> 1)];
        if(arr_object.length < 120) {
            v = 5;
        }
        else if(arr_object.length < 0x606) {
            v = 10;
        }
        else {
            v = arr_object.length >= 0x1D16F ? 40 : 19;
        }
        this.runBase = new int[v];
        this.runLen = new int[v];
    }

    private static void binarySort(Object[] arr_object, int v, int v1, int v2) {
        if(v2 == v) {
            ++v2;
        }
        while(v2 < v1) {
            Comparable comparable0 = (Comparable)arr_object[v2];
            int v3 = v;
            int v4 = v2;
            while(v3 < v4) {
                int v5 = v3 + v4 >>> 1;
                if(comparable0.compareTo(arr_object[v5]) < 0) {
                    v4 = v5;
                }
                else {
                    v3 = v5 + 1;
                }
            }
            int v6 = v2 - v3;
            switch(v6) {
                case 1: {
                    arr_object[v3 + 1] = arr_object[v3];
                    break;
                }
                case 2: {
                    arr_object[v3 + 2] = arr_object[v3 + 1];
                    arr_object[v3 + 1] = arr_object[v3];
                    break;
                }
                default: {
                    System.arraycopy(arr_object, v3, arr_object, v3 + 1, v6);
                }
            }
            arr_object[v3] = comparable0;
            ++v2;
        }
    }

    private static int countRunAndMakeAscending(Object[] arr_object, int v, int v1) {
        if(v + 1 == v1) {
            return 1;
        }
        int v2 = v + 2;
        if(((Comparable)arr_object[v + 1]).compareTo(arr_object[v]) < 0) {
            while(v2 < v1 && ((Comparable)arr_object[v2]).compareTo(arr_object[v2 - 1]) < 0) {
                ++v2;
            }
            ComparableTimSort.reverseRange(arr_object, v, v2);
            return v2 - v;
        }
        while(v2 < v1 && ((Comparable)arr_object[v2]).compareTo(arr_object[v2 - 1]) >= 0) {
            ++v2;
        }
        return v2 - v;
    }

    public void doSort(Object[] arr_object, int v, int v1) {
        this.stackSize = 0;
        ComparableTimSort.rangeCheck(arr_object.length, v, v1);
        int v3 = v1 - v;
        if(v3 < 2) {
            return;
        }
        if(v3 < 0x20) {
            ComparableTimSort.binarySort(arr_object, v, v1, ComparableTimSort.countRunAndMakeAscending(arr_object, v, v1) + v);
            return;
        }
        this.a = arr_object;
        this.tmpCount = 0;
        int v4 = ComparableTimSort.minRunLength(v3);
        do {
            int v5 = ComparableTimSort.countRunAndMakeAscending(arr_object, v, v1);
            if(v5 < v4) {
                int v6 = v3 > v4 ? v4 : v3;
                ComparableTimSort.binarySort(arr_object, v, v + v6, v5 + v);
                v5 = v6;
            }
            this.pushRun(v, v5);
            this.mergeCollapse();
            v += v5;
            v3 -= v5;
        }
        while(v3 != 0);
        this.mergeForceCollapse();
        this.a = null;
        Object[] arr_object1 = this.tmp;
        int v7 = this.tmpCount;
        for(int v2 = 0; v2 < v7; ++v2) {
            arr_object1[v2] = null;
        }
    }

    private Object[] ensureCapacity(int v) {
        this.tmpCount = Math.max(this.tmpCount, v);
        if(this.tmp.length < v) {
            int v1 = v >> 1 | v;
            int v2 = v1 | v1 >> 2;
            int v3 = v2 | v2 >> 4;
            int v4 = v3 | v3 >> 8;
            int v5 = (v4 | v4 >> 16) + 1;
            if(v5 >= 0) {
                v = Math.min(v5, this.a.length >>> 1);
            }
            this.tmp = new Object[v];
        }
        return this.tmp;
    }

    private static int gallopLeft(Comparable comparable0, Object[] arr_object, int v, int v1, int v2) {
        int v10;
        int v9;
        int v3 = v + v2;
        if(comparable0.compareTo(arr_object[v3]) > 0) {
            int v4 = v1 - v2;
            int v5 = 0;
            int v6 = 1;
            while(v6 < v4 && comparable0.compareTo(arr_object[v3 + v6]) > 0) {
                int v7 = (v6 << 1) + 1;
                if(v7 <= 0) {
                    v5 = v6;
                    v6 = v4;
                }
                else {
                    int v8 = v6;
                    v6 = v7;
                    v5 = v8;
                }
            }
            if(v6 <= v4) {
                v4 = v6;
            }
            v9 = v4 + v2;
            v10 = v5 + v2;
        }
        else {
            int v11 = v2 + 1;
            int v12 = 0;
            int v13 = 1;
            while(v13 < v11 && comparable0.compareTo(arr_object[v3 - v13]) <= 0) {
                int v14 = (v13 << 1) + 1;
                if(v14 <= 0) {
                    v12 = v13;
                    v13 = v11;
                }
                else {
                    int v15 = v13;
                    v13 = v14;
                    v12 = v15;
                }
            }
            if(v13 <= v11) {
                v11 = v13;
            }
            v10 = v2 - v11;
            v9 = v2 - v12;
        }
        int v16 = v10 + 1;
        while(v16 < v9) {
            int v17 = (v9 - v16 >>> 1) + v16;
            if(comparable0.compareTo(arr_object[v + v17]) > 0) {
                v16 = v17 + 1;
            }
            else {
                v9 = v17;
            }
        }
        return v9;
    }

    private static int gallopRight(Comparable comparable0, Object[] arr_object, int v, int v1, int v2) {
        int v10;
        int v9;
        int v3 = v + v2;
        if(comparable0.compareTo(arr_object[v3]) < 0) {
            int v4 = v2 + 1;
            int v5 = 0;
            int v6 = 1;
            while(v6 < v4 && comparable0.compareTo(arr_object[v3 - v6]) < 0) {
                int v7 = (v6 << 1) + 1;
                if(v7 <= 0) {
                    v5 = v6;
                    v6 = v4;
                }
                else {
                    int v8 = v6;
                    v6 = v7;
                    v5 = v8;
                }
            }
            if(v6 <= v4) {
                v4 = v6;
            }
            v9 = v2 - v4;
            v10 = v2 - v5;
        }
        else {
            int v11 = v1 - v2;
            int v12 = 0;
            int v13 = 1;
            while(v13 < v11 && comparable0.compareTo(arr_object[v3 + v13]) >= 0) {
                int v14 = (v13 << 1) + 1;
                if(v14 <= 0) {
                    v12 = v13;
                    v13 = v11;
                }
                else {
                    int v15 = v13;
                    v13 = v14;
                    v12 = v15;
                }
            }
            if(v13 <= v11) {
                v11 = v13;
            }
            v10 = v2 + v11;
            v9 = v12 + v2;
        }
        int v16 = v9 + 1;
        while(v16 < v10) {
            int v17 = (v10 - v16 >>> 1) + v16;
            if(comparable0.compareTo(arr_object[v + v17]) < 0) {
                v10 = v17;
            }
            else {
                v16 = v17 + 1;
            }
        }
        return v10;
    }

    private void mergeAt(int v) {
        int[] arr_v = this.runBase;
        int v1 = arr_v[v];
        int[] arr_v1 = this.runLen;
        int v2 = arr_v1[v];
        int v3 = arr_v[v + 1];
        int v4 = arr_v1[v + 1];
        arr_v1[v] = v2 + v4;
        if(v == this.stackSize - 3) {
            arr_v[v + 1] = arr_v[v + 2];
            arr_v1[v + 1] = arr_v1[v + 2];
        }
        --this.stackSize;
        int v5 = ComparableTimSort.gallopRight(((Comparable)this.a[v3]), this.a, v1, v2, 0);
        int v6 = v1 + v5;
        int v7 = v2 - v5;
        if(v7 == 0) {
            return;
        }
        int v8 = ComparableTimSort.gallopLeft(((Comparable)this.a[v6 + v7 - 1]), this.a, v3, v4, v4 - 1);
        if(v8 == 0) {
            return;
        }
        if(v7 <= v8) {
            this.mergeLo(v6, v7, v3, v8);
            return;
        }
        this.mergeHi(v6, v7, v3, v8);
    }

    private void mergeCollapse() {
        int v;
        while((v = this.stackSize) > 1) {
            int v1 = v - 2;
            if(v1 > 0) {
                int[] arr_v = this.runLen;
                if(arr_v[v1 - 1] <= arr_v[v1] + arr_v[v1 + 1]) {
                    if(arr_v[v1 - 1] < arr_v[v1 + 1]) {
                        --v1;
                    }
                    this.mergeAt(v1);
                    continue;
                }
            }
            if(this.runLen[v1] > this.runLen[v1 + 1]) {
                break;
            }
            this.mergeAt(v1);
        }
    }

    private void mergeForceCollapse() {
        int v;
        while((v = this.stackSize) > 1) {
            int v1 = v - 2;
            this.mergeAt((v1 <= 0 || this.runLen[v1 - 1] >= this.runLen[v1 + 1] ? v - 2 : v1 - 1));
        }
    }

    private void mergeHi(int v, int v1, int v2, int v3) {
        int v19;
        int v18;
        int v15;
        Object[] arr_object = this.a;
        Object[] arr_object1 = this.ensureCapacity(v3);
        System.arraycopy(arr_object, v2, arr_object1, 0, v3);
        int v4 = v + v1 - 1;
        int v5 = v3 - 1;
        int v6 = v2 + v3 - 1;
        int v7 = v6 - 1;
        int v8 = v4 - 1;
        arr_object[v6] = arr_object[v4];
        int v9 = v1 - 1;
        if(v9 == 0) {
            System.arraycopy(arr_object1, 0, arr_object, v7 - v5, v3);
            return;
        }
        if(v3 == 1) {
            int v10 = v7 - v9;
            System.arraycopy(arr_object, v8 - v9 + 1, arr_object, v10 + 1, v9);
            arr_object[v10] = arr_object1[v5];
            return;
        }
        int v11 = this.minGallop;
        while(true) {
            int v12 = v9;
            int v13 = 0;
            int v14 = 0;
            do {
                if(((Comparable)arr_object1[v5]).compareTo(arr_object[v8]) < 0) {
                    arr_object[v7] = arr_object[v8];
                    ++v14;
                    --v12;
                    if(v12 == 0) {
                        v15 = v7 - 1;
                        --v8;
                        goto label_78;
                    }
                    --v7;
                    --v8;
                    v13 = 0;
                }
                else {
                    v15 = v7 - 1;
                    arr_object[v7] = arr_object1[v5];
                    ++v13;
                    --v3;
                    if(v3 == 1) {
                        --v5;
                        goto label_78;
                    }
                    v7 = v15;
                    --v5;
                    v14 = 0;
                }
            }
            while((v14 | v13) < v11);
            while(true) {
                int v16 = v12 - ComparableTimSort.gallopRight(((Comparable)arr_object1[v5]), arr_object, v, v12, v12 - 1);
                if(v16 == 0) {
                    v15 = v7;
                label_54:
                    arr_object[v15] = arr_object1[v5];
                    --v3;
                    if(v3 == 1) {
                        --v15;
                        --v5;
                    }
                    else {
                        int v17 = v3 - ComparableTimSort.gallopLeft(((Comparable)arr_object[v8]), arr_object1, 0, v3, v3 - 1);
                        if(v17 == 0) {
                            v18 = v15 - 1;
                            v19 = v5 - 1;
                        label_72:
                            arr_object[v18] = arr_object[v8];
                            --v12;
                            if(v12 == 0) {
                                v5 = v19;
                                v15 = v18 - 1;
                                --v8;
                            }
                            else {
                                goto label_90;
                            }
                        }
                        else {
                            v18 = v15 - 1 - v17;
                            v19 = v5 - 1 - v17;
                            v3 -= v17;
                            System.arraycopy(arr_object1, v19 + 1, arr_object, v18 + 1, v17);
                            if(v3 <= 1) {
                                v15 = v18;
                                v5 = v19;
                            }
                            else {
                                goto label_72;
                            }
                        }
                    }
                }
                else {
                    v15 = v7 - v16;
                    v8 -= v16;
                    v12 -= v16;
                    System.arraycopy(arr_object, v8 + 1, arr_object, v15 + 1, v16);
                    if(v12 != 0) {
                        goto label_54;
                    }
                }
            label_78:
                if(v11 < 1) {
                    v11 = 1;
                }
                this.minGallop = v11;
                if(v3 == 1) {
                    int v20 = v15 - v12;
                    System.arraycopy(arr_object, v8 - v12 + 1, arr_object, v20 + 1, v12);
                    arr_object[v20] = arr_object1[v5];
                    return;
                }
                if(v3 == 0) {
                    throw new IllegalArgumentException("Comparison method violates its general contract!");
                }
                System.arraycopy(arr_object1, 0, arr_object, v15 - (v3 - 1), v3);
                return;
            label_90:
                --v11;
                if(((v16 < 7 ? 0 : 1) | (v17 < 7 ? 0 : 1)) == 0) {
                    break;
                }
                v5 = v19;
                v7 = v18 - 1;
                --v8;
            }
            if(v11 < 0) {
                v11 = 0;
            }
            v11 += 2;
            v5 = v19;
            v9 = v12;
            v7 = v18 - 1;
            --v8;
        }
    }

    private void mergeLo(int v, int v1, int v2, int v3) {
        int v17;
        int v16;
        int v13;
        Object[] arr_object = this.a;
        Object[] arr_object1 = this.ensureCapacity(v1);
        System.arraycopy(arr_object, v, arr_object1, 0, v1);
        int v4 = v + 1;
        int v5 = v2 + 1;
        arr_object[v] = arr_object[v2];
        int v6 = v3 - 1;
        if(v6 == 0) {
            System.arraycopy(arr_object1, 0, arr_object, v4, v1);
            return;
        }
        if(v1 == 1) {
            System.arraycopy(arr_object, v5, arr_object, v4, v6);
            arr_object[v4 + v6] = arr_object1[0];
            return;
        }
        int v7 = this.minGallop;
        int v8 = v1;
        int v9 = 0;
        while(true) {
            int v10 = v6;
            int v11 = 0;
            int v12 = 0;
            do {
                if(((Comparable)arr_object[v5]).compareTo(arr_object1[v9]) < 0) {
                    arr_object[v4] = arr_object[v5];
                    ++v12;
                    --v10;
                    if(v10 == 0) {
                        v13 = v4 + 1;
                        ++v5;
                        goto label_76;
                    }
                    ++v4;
                    ++v5;
                    v11 = 0;
                }
                else {
                    v13 = v4 + 1;
                    arr_object[v4] = arr_object1[v9];
                    ++v11;
                    --v8;
                    if(v8 == 1) {
                        ++v9;
                        goto label_76;
                    }
                    v4 = v13;
                    ++v9;
                    v12 = 0;
                }
            }
            while((v11 | v12) < v7);
            while(true) {
                int v14 = ComparableTimSort.gallopRight(((Comparable)arr_object[v5]), arr_object1, v9, v8, 0);
                if(v14 == 0) {
                label_52:
                    v13 = v4 + 1;
                    arr_object[v4] = arr_object[v5];
                    --v10;
                    if(v10 == 0) {
                        ++v5;
                    }
                    else {
                        int v15 = ComparableTimSort.gallopLeft(((Comparable)arr_object1[v9]), arr_object, v5 + 1, v10, 0);
                        if(v15 == 0) {
                            v16 = v13;
                            v17 = v5 + 1;
                        label_70:
                            arr_object[v16] = arr_object1[v9];
                            --v8;
                            if(v8 == 1) {
                                v5 = v17;
                                v13 = v16 + 1;
                                ++v9;
                            }
                            else {
                                goto label_87;
                            }
                        }
                        else {
                            System.arraycopy(arr_object, v5 + 1, arr_object, v13, v15);
                            v16 = v13 + v15;
                            v17 = v5 + 1 + v15;
                            v10 -= v15;
                            if(v10 == 0) {
                                v13 = v16;
                                v5 = v17;
                            }
                            else {
                                goto label_70;
                            }
                        }
                    }
                }
                else {
                    System.arraycopy(arr_object1, v9, arr_object, v4, v14);
                    v4 += v14;
                    v9 += v14;
                    v8 -= v14;
                    if(v8 <= 1) {
                        v13 = v4;
                        goto label_76;
                    }
                    goto label_52;
                }
            label_76:
                if(v7 < 1) {
                    v7 = 1;
                }
                this.minGallop = v7;
                if(v8 == 1) {
                    System.arraycopy(arr_object, v5, arr_object, v13, v10);
                    arr_object[v13 + v10] = arr_object1[v9];
                    return;
                }
                if(v8 == 0) {
                    throw new IllegalArgumentException("Comparison method violates its general contract!");
                }
                System.arraycopy(arr_object1, v9, arr_object, v13, v8);
                return;
            label_87:
                --v7;
                if(((v15 < 7 ? 0 : 1) | (v14 < 7 ? 0 : 1)) == 0) {
                    break;
                }
                v5 = v17;
                v4 = v16 + 1;
                ++v9;
            }
            if(v7 < 0) {
                v7 = 0;
            }
            v7 += 2;
            v5 = v17;
            v6 = v10;
            v4 = v16 + 1;
            ++v9;
        }
    }

    private static int minRunLength(int v) {
        int v1 = 0;
        while(v >= 0x20) {
            v1 |= v & 1;
            v >>= 1;
        }
        return v + v1;
    }

    private void pushRun(int v, int v1) {
        int v2 = this.stackSize;
        this.runBase[v2] = v;
        this.runLen[v2] = v1;
        this.stackSize = v2 + 1;
    }

    private static void rangeCheck(int v, int v1, int v2) {
        if(v1 > v2) {
            throw new IllegalArgumentException("fromIndex(" + v1 + ") > toIndex(" + v2 + ")");
        }
        if(v1 < 0) {
            throw new ArrayIndexOutOfBoundsException(v1);
        }
        if(v2 > v) {
            throw new ArrayIndexOutOfBoundsException(v2);
        }
    }

    private static void reverseRange(Object[] arr_object, int v, int v1) {
        int v2 = v1 - 1;
        while(v < v2) {
            Object object0 = arr_object[v];
            arr_object[v] = arr_object[v2];
            arr_object[v2] = object0;
            --v2;
            ++v;
        }
    }

    static void sort(Object[] arr_object) {
        ComparableTimSort.sort(arr_object, 0, arr_object.length);
    }

    static void sort(Object[] arr_object, int v, int v1) {
        ComparableTimSort.rangeCheck(arr_object.length, v, v1);
        int v2 = v1 - v;
        if(v2 < 2) {
            return;
        }
        if(v2 < 0x20) {
            ComparableTimSort.binarySort(arr_object, v, v1, ComparableTimSort.countRunAndMakeAscending(arr_object, v, v1) + v);
            return;
        }
        ComparableTimSort comparableTimSort0 = new ComparableTimSort(arr_object);
        int v3 = ComparableTimSort.minRunLength(v2);
        do {
            int v4 = ComparableTimSort.countRunAndMakeAscending(arr_object, v, v1);
            if(v4 < v3) {
                int v5 = v2 > v3 ? v3 : v2;
                ComparableTimSort.binarySort(arr_object, v, v + v5, v4 + v);
                v4 = v5;
            }
            comparableTimSort0.pushRun(v, v4);
            comparableTimSort0.mergeCollapse();
            v += v4;
            v2 -= v4;
        }
        while(v2 != 0);
        comparableTimSort0.mergeForceCollapse();
    }
}

