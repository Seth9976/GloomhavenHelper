package com.esotericsoftware.spine;

public abstract class ConstraintData {
    final String name;
    int order;
    boolean skinRequired;

    public ConstraintData(String s) {
        if(s == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        this.name = s;
    }

    public String getName() {
        return this.name;
    }

    public int getOrder() {
        return this.order;
    }

    public boolean getSkinRequired() {
        return this.skinRequired;
    }

    public void setOrder(int v) {
        this.order = v;
    }

    public void setSkinRequired(boolean z) {
        this.skinRequired = z;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

