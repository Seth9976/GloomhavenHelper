package com.badlogic.gdx.math;

import java.io.Serializable;

public class GridPoint3 implements Serializable {
    private static final long serialVersionUID = 0x522FD697DCB5173EL;
    public int x;
    public int y;
    public int z;

    public GridPoint3() {
    }

    public GridPoint3(int v, int v1, int v2) {
        this.x = v;
        this.y = v1;
        this.z = v2;
    }

    public GridPoint3(GridPoint3 gridPoint30) {
        this.x = gridPoint30.x;
        this.y = gridPoint30.y;
        this.z = gridPoint30.z;
    }

    public GridPoint3 add(int v, int v1, int v2) {
        this.x += v;
        this.y += v1;
        this.z += v2;
        return this;
    }

    public GridPoint3 add(GridPoint3 gridPoint30) {
        this.x += gridPoint30.x;
        this.y += gridPoint30.y;
        this.z += gridPoint30.z;
        return this;
    }

    public GridPoint3 cpy() {
        return new GridPoint3(this);
    }

    public float dst(int v, int v1, int v2) {
        int v3 = v - this.x;
        int v4 = v1 - this.y;
        int v5 = v2 - this.z;
        return (float)Math.sqrt(v3 * v3 + v4 * v4 + v5 * v5);
    }

    public float dst(GridPoint3 gridPoint30) {
        int v = gridPoint30.x - this.x;
        int v1 = gridPoint30.y - this.y;
        int v2 = gridPoint30.z - this.z;
        return (float)Math.sqrt(v * v + v1 * v1 + v2 * v2);
    }

    public float dst2(int v, int v1, int v2) {
        int v3 = v - this.x;
        int v4 = v1 - this.y;
        int v5 = v2 - this.z;
        return (float)(v3 * v3 + v4 * v4 + v5 * v5);
    }

    public float dst2(GridPoint3 gridPoint30) {
        int v = gridPoint30.x - this.x;
        int v1 = gridPoint30.y - this.y;
        int v2 = gridPoint30.z - this.z;
        return (float)(v * v + v1 * v1 + v2 * v2);
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && object0.getClass() == this.getClass() && (this.x == ((GridPoint3)object0).x && this.y == ((GridPoint3)object0).y && this.z == ((GridPoint3)object0).z);
    }

    @Override
    public int hashCode() {
        return ((this.x + 17) * 17 + this.y) * 17 + this.z;
    }

    public GridPoint3 set(int v, int v1, int v2) {
        this.x = v;
        this.y = v1;
        this.z = v2;
        return this;
    }

    public GridPoint3 set(GridPoint3 gridPoint30) {
        this.x = gridPoint30.x;
        this.y = gridPoint30.y;
        this.z = gridPoint30.z;
        return this;
    }

    public GridPoint3 sub(int v, int v1, int v2) {
        this.x -= v;
        this.y -= v1;
        this.z -= v2;
        return this;
    }

    public GridPoint3 sub(GridPoint3 gridPoint30) {
        this.x -= gridPoint30.x;
        this.y -= gridPoint30.y;
        this.z -= gridPoint30.z;
        return this;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}

