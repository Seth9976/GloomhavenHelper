package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Comparator;

class TimSort {
    private static final boolean DEBUG = false;
    private static final int INITIAL_TMP_STORAGE_LENGTH = 0x100;
    private static final int MIN_GALLOP = 7;
    private static final int MIN_MERGE = 0x20;
    private Object[] a;
    private Comparator c;
    private int minGallop;
    private final int[] runBase;
    private final int[] runLen;
    private int stackSize;
    private Object[] tmp;
    private int tmpCount;

    TimSort() {
        this.minGallop = 7;
        this.stackSize = 0;
        this.tmp = new Object[0x100];
        this.runBase = new int[40];
        this.runLen = new int[40];
    }

    private TimSort(Object[] arr_object, Comparator comparator0) {
        int v;
        this.minGallop = 7;
        this.stackSize = 0;
        this.a = arr_object;
        this.c = comparator0;
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

    private static void binarySort(Object[] arr_object, int v, int v1, int v2, Comparator comparator0) {
        if(v2 == v) {
            ++v2;
        }
        while(v2 < v1) {
            Object object0 = arr_object[v2];
            int v3 = v;
            int v4 = v2;
            while(v3 < v4) {
                int v5 = v3 + v4 >>> 1;
                if(comparator0.compare(object0, arr_object[v5]) < 0) {
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
            arr_object[v3] = object0;
            ++v2;
        }
    }

    private static int countRunAndMakeAscending(Object[] arr_object, int v, int v1, Comparator comparator0) {
        if(v + 1 == v1) {
            return 1;
        }
        int v2 = v + 2;
        if(comparator0.compare(arr_object[v + 1], arr_object[v]) < 0) {
            while(v2 < v1 && comparator0.compare(arr_object[v2], arr_object[v2 - 1]) < 0) {
                ++v2;
            }
            TimSort.reverseRange(arr_object, v, v2);
            return v2 - v;
        }
        while(v2 < v1 && comparator0.compare(arr_object[v2], arr_object[v2 - 1]) >= 0) {
            ++v2;
        }
        return v2 - v;
    }

    public void doSort(Object[] arr_object, Comparator comparator0, int v, int v1) {
        this.stackSize = 0;
        TimSort.rangeCheck(arr_object.length, v, v1);
        int v3 = v1 - v;
        if(v3 < 2) {
            return;
        }
        if(v3 < 0x20) {
            TimSort.binarySort(arr_object, v, v1, TimSort.countRunAndMakeAscending(arr_object, v, v1, comparator0) + v, comparator0);
            return;
        }
        this.a = arr_object;
        this.c = comparator0;
        this.tmpCount = 0;
        int v4 = TimSort.minRunLength(v3);
        do {
            int v5 = TimSort.countRunAndMakeAscending(arr_object, v, v1, comparator0);
            if(v5 < v4) {
                int v6 = v3 > v4 ? v4 : v3;
                TimSort.binarySort(arr_object, v, v + v6, v5 + v, comparator0);
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
        this.c = null;
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

    private static int gallopLeft(Object object0, Object[] arr_object, int v, int v1, int v2, Comparator comparator0) {
        int v10;
        int v9;
        int v3 = v + v2;
        if(comparator0.compare(object0, arr_object[v3]) > 0) {
            int v4 = v1 - v2;
            int v5 = 0;
            int v6 = 1;
            while(v6 < v4 && comparator0.compare(object0, arr_object[v3 + v6]) > 0) {
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
            while(v13 < v11 && comparator0.compare(object0, arr_object[v3 - v13]) <= 0) {
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
            if(comparator0.compare(object0, arr_object[v + v17]) > 0) {
                v16 = v17 + 1;
            }
            else {
                v9 = v17;
            }
        }
        return v9;
    }

    private static int gallopRight(Object object0, Object[] arr_object, int v, int v1, int v2, Comparator comparator0) {
        int v10;
        int v9;
        int v3 = v + v2;
        if(comparator0.compare(object0, arr_object[v3]) < 0) {
            int v4 = v2 + 1;
            int v5 = 0;
            int v6 = 1;
            while(v6 < v4 && comparator0.compare(object0, arr_object[v3 - v6]) < 0) {
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
            while(v13 < v11 && comparator0.compare(object0, arr_object[v3 + v13]) >= 0) {
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
            if(comparator0.compare(object0, arr_object[v + v17]) < 0) {
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
        int v5 = TimSort.gallopRight(this.a[v3], this.a, v1, v2, 0, this.c);
        int v6 = v1 + v5;
        int v7 = v2 - v5;
        if(v7 == 0) {
            return;
        }
        int v8 = TimSort.gallopLeft(this.a[v6 + v7 - 1], this.a, v3, v4, v4 - 1, this.c);
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
            if((v1 < 1 || this.runLen[v1 - 1] > this.runLen[v1] + this.runLen[v1 + 1]) && (v1 < 2 || this.runLen[v1 - 2] > this.runLen[v1] + this.runLen[v1 - 1])) {
                if(this.runLen[v1] <= this.runLen[v1 + 1]) {
                    goto label_8;
                }
                break;
            }
            else if(this.runLen[v1 - 1] < this.runLen[v1 + 1]) {
                --v1;
            }
        label_8:
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
        int v23;
        int v17;
        int v16;
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
        Comparator comparator0 = this.c;
        int v11 = this.minGallop;
        while(true) {
            int v12 = v9;
            int v13 = 0;
            int v14 = 0;
            do {
                if(comparator0.compare(arr_object1[v5], arr_object[v8]) < 0) {
                    v15 = v7 - 1;
                    arr_object[v7] = arr_object[v8];
                    ++v14;
                    --v12;
                    if(v12 == 0) {
                        v16 = v5;
                        v17 = v8 - 1;
                        goto label_99;
                    }
                    v7 = v15;
                    --v8;
                    v13 = 0;
                }
                else {
                    arr_object[v7] = arr_object1[v5];
                    ++v13;
                    --v3;
                    if(v3 == 1) {
                        v17 = v8;
                        v15 = v7 - 1;
                        v16 = v5 - 1;
                        goto label_99;
                    }
                    --v7;
                    --v5;
                    v14 = 0;
                }
            }
            while((v14 | v13) < v11);
            int v18 = v3;
            int v19 = v11;
            v16 = v5;
            int v20 = v7;
            v17 = v8;
            while(true) {
                int v21 = v12 - TimSort.gallopRight(arr_object1[v16], arr_object, v, v12, v12 - 1, comparator0);
                if(v21 == 0) {
                label_66:
                    v23 = v20 - 1;
                    arr_object[v20] = arr_object1[v16];
                    if(v18 - 1 == 1) {
                        v3 = 1;
                        v11 = v19;
                        v15 = v23;
                        --v16;
                    }
                    else {
                        int v24 = v18 - 1 - TimSort.gallopLeft(arr_object[v17], arr_object1, 0, v18 - 1, v18 - 2, comparator0);
                        if(v24 == 0) {
                            --v18;
                            --v16;
                        label_92:
                            arr_object[v23] = arr_object[v17];
                            --v12;
                            if(v12 == 0) {
                                v15 = v23 - 1;
                                --v17;
                                v3 = v18;
                                v11 = v19;
                            }
                            else {
                                goto label_111;
                            }
                        }
                        else {
                            int v25 = v23 - v24;
                            int v26 = v16 - 1 - v24;
                            int v27 = v18 - 1 - v24;
                            System.arraycopy(arr_object1, v26 + 1, arr_object, v25 + 1, v24);
                            if(v27 <= 1) {
                                v15 = v25;
                                v3 = v27;
                                v16 = v26;
                                v11 = v19;
                            }
                            else {
                                v23 = v25;
                                v18 = v27;
                                v16 = v26;
                                goto label_92;
                            }
                        }
                    }
                }
                else {
                    v15 = v20 - v21;
                    int v22 = v17 - v21;
                    v12 -= v21;
                    System.arraycopy(arr_object, v22 + 1, arr_object, v15 + 1, v21);
                    if(v12 == 0) {
                        v17 = v22;
                        v3 = v18;
                        v11 = v19;
                    }
                    else {
                        v20 = v15;
                        v17 = v22;
                        goto label_66;
                    }
                }
            label_99:
                if(v11 < 1) {
                    v11 = 1;
                }
                this.minGallop = v11;
                if(v3 == 1) {
                    int v28 = v15 - v12;
                    System.arraycopy(arr_object, v17 - v12 + 1, arr_object, v28 + 1, v12);
                    arr_object[v28] = arr_object1[v16];
                    return;
                }
                if(v3 == 0) {
                    throw new IllegalArgumentException("Comparison method violates its general contract!");
                }
                System.arraycopy(arr_object1, 0, arr_object, v15 - (v3 - 1), v3);
                return;
            label_111:
                --v19;
                if(((v24 < 7 ? 0 : 1) | (v21 < 7 ? 0 : 1)) == 0) {
                    break;
                }
                v20 = v23 - 1;
                --v17;
            }
            if(v19 < 0) {
                v19 = 0;
            }
            v7 = v23 - 1;
            v8 = v17 - 1;
            v5 = v16;
            v3 = v18;
            v11 = v19 + 2;
            v9 = v12;
        }
    }

    private void mergeLo(int v, int v1, int v2, int v3) {
        int v25;
        int v16;
        int v15;
        int v14;
        int v13;
        int v12;
        Object[] arr_object = this.a;
        Object[] arr_object1 = this.ensureCapacity(v1);
        System.arraycopy(arr_object, v, arr_object1, 0, v1);
        int v4 = v2 + 1;
        arr_object[v] = arr_object[v2];
        int v5 = v3 - 1;
        if(v5 == 0) {
            System.arraycopy(arr_object1, 0, arr_object, v + 1, v1);
            return;
        }
        if(v1 == 1) {
            System.arraycopy(arr_object, v4, arr_object, v + 1, v5);
            arr_object[v + 1 + v5] = arr_object1[0];
            return;
        }
        Comparator comparator0 = this.c;
        int v6 = this.minGallop;
        int v7 = v + 1;
        int v8 = 0;
        while(true) {
            int v9 = v5;
            int v10 = 0;
            int v11 = 0;
            do {
                if(comparator0.compare(arr_object[v4], arr_object1[v8]) < 0) {
                    v12 = v7 + 1;
                    arr_object[v7] = arr_object[v4];
                    ++v11;
                    --v9;
                    if(v9 == 0) {
                        v13 = v6;
                        v14 = 0;
                        ++v4;
                        v15 = v1;
                        v16 = v8;
                        goto label_99;
                    }
                    v7 = v12;
                    ++v4;
                    v10 = 0;
                }
                else {
                    arr_object[v7] = arr_object1[v8];
                    ++v10;
                    --v1;
                    if(v1 == 1) {
                        v13 = v6;
                        v12 = v7 + 1;
                        v14 = v9;
                        v16 = v8 + 1;
                        v15 = 1;
                        goto label_99;
                    }
                    ++v7;
                    ++v8;
                    v11 = 0;
                }
            }
            while((v10 | v11) < v6);
            v15 = v1;
            int v17 = v4;
            int v18 = v7;
            int v19 = v6;
            int v20 = v9;
            v16 = v8;
            while(true) {
                int v21 = TimSort.gallopRight(arr_object[v17], arr_object1, v16, v15, 0, comparator0);
                if(v21 == 0) {
                label_72:
                    v4 = v17 + 1;
                    arr_object[v18] = arr_object[v17];
                    v14 = v20 - 1;
                    if(v14 == 0) {
                        v12 = v18 + 1;
                        v13 = v19;
                    }
                    else {
                        int v24 = TimSort.gallopLeft(arr_object1[v16], arr_object, v4, v14, 0, comparator0);
                        if(v24 == 0) {
                            v25 = v18 + 1;
                        label_93:
                            arr_object[v25] = arr_object1[v16];
                            --v15;
                            if(v15 == 1) {
                                v12 = v25 + 1;
                                ++v16;
                                v13 = v19;
                            }
                            else {
                                goto label_110;
                            }
                        }
                        else {
                            System.arraycopy(arr_object, v4, arr_object, v18 + 1, v24);
                            v25 = v18 + 1 + v24;
                            v4 += v24;
                            int v26 = v14 - v24;
                            if(v26 == 0) {
                                v12 = v25;
                                v14 = 0;
                                v13 = v19;
                            }
                            else {
                                v14 = v26;
                                goto label_93;
                            }
                        }
                    }
                }
                else {
                    System.arraycopy(arr_object1, v16, arr_object, v18, v21);
                    v12 = v18 + v21;
                    int v22 = v16 + v21;
                    int v23 = v15 - v21;
                    if(v23 <= 1) {
                        v15 = v23;
                        v16 = v22;
                        v4 = v17;
                        v14 = v20;
                        v13 = v19;
                    }
                    else {
                        v18 = v12;
                        v15 = v23;
                        v16 = v22;
                        goto label_72;
                    }
                }
            label_99:
                if(v13 < 1) {
                    v13 = 1;
                }
                this.minGallop = v13;
                if(v15 == 1) {
                    System.arraycopy(arr_object, v4, arr_object, v12, v14);
                    arr_object[v12 + v14] = arr_object1[v16];
                    return;
                }
                if(v15 == 0) {
                    throw new IllegalArgumentException("Comparison method violates its general contract!");
                }
                System.arraycopy(arr_object1, v16, arr_object, v12, v15);
                return;
            label_110:
                --v19;
                if(((v24 < 7 ? 0 : 1) | (v21 < 7 ? 0 : 1)) == 0) {
                    break;
                }
                v18 = v25 + 1;
                ++v16;
                v20 = v14;
                v17 = v4;
            }
            if(v19 < 0) {
                v19 = 0;
            }
            v6 = v19 + 2;
            v5 = v14;
            v1 = v15;
            v7 = v25 + 1;
            v8 = v16 + 1;
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

    static void sort(Object[] arr_object, int v, int v1, Comparator comparator0) {
        if(comparator0 == null) {
            Arrays.sort(arr_object, v, v1);
            return;
        }
        TimSort.rangeCheck(arr_object.length, v, v1);
        int v2 = v1 - v;
        if(v2 < 2) {
            return;
        }
        if(v2 < 0x20) {
            TimSort.binarySort(arr_object, v, v1, TimSort.countRunAndMakeAscending(arr_object, v, v1, comparator0) + v, comparator0);
            return;
        }
        TimSort timSort0 = new TimSort(arr_object, comparator0);
        int v3 = TimSort.minRunLength(v2);
        do {
            int v4 = TimSort.countRunAndMakeAscending(arr_object, v, v1, comparator0);
            if(v4 < v3) {
                int v5 = v2 > v3 ? v3 : v2;
                TimSort.binarySort(arr_object, v, v + v5, v4 + v, comparator0);
                v4 = v5;
            }
            timSort0.pushRun(v, v4);
            timSort0.mergeCollapse();
            v += v4;
            v2 -= v4;
        }
        while(v2 != 0);
        timSort0.mergeForceCollapse();
    }

    static void sort(Object[] arr_object, Comparator comparator0) {
        TimSort.sort(arr_object, 0, arr_object.length, comparator0);
    }
}

