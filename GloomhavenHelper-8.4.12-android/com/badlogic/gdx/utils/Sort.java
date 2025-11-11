package com.badlogic.gdx.utils;

import java.util.Comparator;

public class Sort {
    private ComparableTimSort comparableTimSort;
    private static Sort instance;
    private TimSort timSort;

    public static Sort instance() {
        if(Sort.instance == null) {
            Sort.instance = new Sort();
        }
        return Sort.instance;
    }

    public void sort(Array array0) {
        if(this.comparableTimSort == null) {
            this.comparableTimSort = new ComparableTimSort();
        }
        this.comparableTimSort.doSort(array0.items, 0, array0.size);
    }

    public void sort(Array array0, Comparator comparator0) {
        if(this.timSort == null) {
            this.timSort = new TimSort();
        }
        this.timSort.doSort(array0.items, comparator0, 0, array0.size);
    }

    public void sort(Object[] arr_object) {
        if(this.comparableTimSort == null) {
            this.comparableTimSort = new ComparableTimSort();
        }
        this.comparableTimSort.doSort(arr_object, 0, arr_object.length);
    }

    public void sort(Object[] arr_object, int v, int v1) {
        if(this.comparableTimSort == null) {
            this.comparableTimSort = new ComparableTimSort();
        }
        this.comparableTimSort.doSort(arr_object, v, v1);
    }

    public void sort(Object[] arr_object, Comparator comparator0) {
        if(this.timSort == null) {
            this.timSort = new TimSort();
        }
        this.timSort.doSort(arr_object, comparator0, 0, arr_object.length);
    }

    public void sort(Object[] arr_object, Comparator comparator0, int v, int v1) {
        if(this.timSort == null) {
            this.timSort = new TimSort();
        }
        this.timSort.doSort(arr_object, comparator0, v, v1);
    }
}

