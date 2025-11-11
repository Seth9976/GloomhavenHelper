package com.badlogic.gdx.utils;

import java.util.Comparator;

public class Select {
    private static Select instance;
    private QuickSelect quickSelect;

    private int fastMax(Object[] arr_object, Comparator comparator0, int v) {
        int v2 = 0;
        for(int v1 = 1; v1 < v; ++v1) {
            if(comparator0.compare(arr_object[v1], arr_object[v2]) > 0) {
                v2 = v1;
            }
        }
        return v2;
    }

    private int fastMin(Object[] arr_object, Comparator comparator0, int v) {
        int v2 = 0;
        for(int v1 = 1; v1 < v; ++v1) {
            if(comparator0.compare(arr_object[v1], arr_object[v2]) < 0) {
                v2 = v1;
            }
        }
        return v2;
    }

    public static Select instance() {
        if(Select.instance == null) {
            Select.instance = new Select();
        }
        return Select.instance;
    }

    public Object select(Object[] arr_object, Comparator comparator0, int v, int v1) {
        return arr_object[this.selectIndex(arr_object, comparator0, v, v1)];
    }

    public int selectIndex(Object[] arr_object, Comparator comparator0, int v, int v1) {
        if(v1 < 1) {
            throw new GdxRuntimeException("cannot select from empty array (size < 1)");
        }
        if(v > v1) {
            throw new GdxRuntimeException("Kth rank is larger than size. k: " + v + ", size: " + v1);
        }
        if(v == 1) {
            return this.fastMin(arr_object, comparator0, v1);
        }
        if(v == v1) {
            return this.fastMax(arr_object, comparator0, v1);
        }
        if(this.quickSelect == null) {
            this.quickSelect = new QuickSelect();
        }
        return this.quickSelect.select(arr_object, comparator0, v, v1);
    }
}

