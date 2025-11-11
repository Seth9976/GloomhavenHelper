package com.badlogic.gdx.math;

import java.io.Serializable;

public class GridPoint2 implements Serializable {
    private static final long serialVersionUID = 0xC83632A6953EA8FCL;
    public int x;
    public int y;

    public GridPoint2() {
    }

    public GridPoint2(int v, int v1) {
        this.x = v;
        this.y = v1;
    }

    public GridPoint2(GridPoint2 gridPoint20) {
        this.x = gridPoint20.x;
        this.y = gridPoint20.y;
    }

    public GridPoint2 add(int v, int v1) {
        this.x += v;
        this.y += v1;
        return this;
    }

    public GridPoint2 add(GridPoint2 gridPoint20) {
        this.x += gridPoint20.x;
        this.y += gridPoint20.y;
        return this;
    }

    public GridPoint2 cpy() {
        return new GridPoint2(this);
    }

    public float dst(int v, int v1) {
        int v2 = v - this.x;
        int v3 = v1 - this.y;
        return (float)Math.sqrt(v2 * v2 + v3 * v3);
    }

    public float dst(GridPoint2 gridPoint20) {
        int v = gridPoint20.x - this.x;
        int v1 = gridPoint20.y - this.y;
        return (float)Math.sqrt(v * v + v1 * v1);
    }

    public float dst2(int v, int v1) {
        int v2 = v - this.x;
        int v3 = v1 - this.y;
        return (float)(v2 * v2 + v3 * v3);
    }

    public float dst2(GridPoint2 gridPoint20) {
        int v = gridPoint20.x - this.x;
        int v1 = gridPoint20.y - this.y;
        return (float)(v * v + v1 * v1);
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && object0.getClass() == this.getClass() && (this.x == ((GridPoint2)object0).x && this.y == ((GridPoint2)object0).y);
    }

    @Override
    public int hashCode() {
        return (this.x + 53) * 53 + this.y;
    }

    public GridPoint2 set(int v, int v1) {
        this.x = v;
        this.y = v1;
        return this;
    }

    public GridPoint2 set(GridPoint2 gridPoint20) {
        this.x = gridPoint20.x;
        this.y = gridPoint20.y;
        return this;
    }

    public GridPoint2 sub(int v, int v1) {
        this.x -= v;
        this.y -= v1;
        return this;
    }

    public GridPoint2 sub(GridPoint2 gridPoint20) {
        this.x -= gridPoint20.x;
        this.y -= gridPoint20.y;
        return this;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}

