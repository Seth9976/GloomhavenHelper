package com.badlogic.gdx.utils;

import java.util.Comparator;

public class QuickSelect {
    private Object[] array;
    private Comparator comp;

    private int medianOfThreePivot(int v, int v1) {
        Object[] arr_object = this.array;
        Object object0 = arr_object[v];
        int v2 = (v + v1) / 2;
        Object object1 = arr_object[v2];
        Object object2 = arr_object[v1];
        if(this.comp.compare(object0, object1) > 0) {
            if(this.comp.compare(object1, object2) > 0) {
                return v2;
            }
            return this.comp.compare(object0, object2) <= 0 ? v : v1;
        }
        if(this.comp.compare(object0, object2) > 0) {
            return v;
        }
        return this.comp.compare(object1, object2) <= 0 ? v2 : v1;
    }

    private int partition(int v, int v1, int v2) {
        Object object0 = this.array[v2];
        this.swap(v1, v2);
        int v3 = v;
        while(v < v1) {
            if(this.comp.compare(this.array[v], object0) < 0) {
                this.swap(v3, v);
                ++v3;
            }
            ++v;
        }
        this.swap(v1, v3);
        return v3;
    }

    private int recursiveSelect(int v, int v1, int v2) {
        if(v == v1) {
            return v;
        }
        int v3 = this.partition(v, v1, this.medianOfThreePivot(v, v1));
        int v4 = v3 - v + 1;
        if(v4 != v2) {
            return v2 >= v4 ? this.recursiveSelect(v3 + 1, v1, v2 - v4) : this.recursiveSelect(v, v3 - 1, v2);
        }
        return v3;
    }

    public int select(Object[] arr_object, Comparator comparator0, int v, int v1) {
        this.array = arr_object;
        this.comp = comparator0;
        return this.recursiveSelect(0, v1 - 1, v);
    }

    private void swap(int v, int v1) {
        Object[] arr_object = this.array;
        Object object0 = arr_object[v];
        arr_object[v] = arr_object[v1];
        arr_object[v1] = object0;
    }
}

