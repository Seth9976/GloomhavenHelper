package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class Bresenham2 {
    private final Array points;
    private final Pool pool;

    public Bresenham2() {
        this.points = new Array();
        this.pool = new Pool() {
            protected GridPoint2 newObject() {
                return new GridPoint2();
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }
        };
    }

    public Array line(int v, int v1, int v2, int v3) {
        this.pool.freeAll(this.points);
        this.points.clear();
        return this.line(v, v1, v2, v3, this.pool, this.points);
    }

    public Array line(int v, int v1, int v2, int v3, Pool pool0, Array array0) {
        int v10;
        int v9;
        int v8;
        int v4 = v2 - v;
        int v5 = v3 - v1;
        int v6 = -1;
        if(v4 < 0) {
            v8 = -1;
            v9 = -1;
        }
        else if(v4 > 0) {
            v8 = 1;
            v9 = 1;
        }
        else {
            v8 = 0;
            v9 = 0;
        }
        if(v5 < 0) {
            v10 = -1;
        }
        else {
            v10 = v5 <= 0 ? 0 : 1;
        }
        int v11 = Math.abs(v4);
        int v12 = Math.abs(v5);
        if(v11 < v12) {
            v11 = Math.abs(v5);
            v12 = Math.abs(v4);
            if(v5 >= 0) {
                v6 = v5 <= 0 ? 0 : 1;
            }
            v9 = 0;
        }
        else {
            v6 = 0;
        }
        int v13 = 0;
        for(int v7 = 0; v7 <= v11; ++v7) {
            GridPoint2 gridPoint20 = (GridPoint2)pool0.obtain();
            gridPoint20.set(v, v1);
            array0.add(gridPoint20);
            v13 += v12 << 1;
            if(v13 > v11) {
                v13 -= v11 << 1;
                v += v8;
                v1 += v10;
            }
            else {
                v += v9;
                v1 += v6;
            }
        }
        return array0;
    }

    public Array line(GridPoint2 gridPoint20, GridPoint2 gridPoint21) {
        return this.line(gridPoint20.x, gridPoint20.y, gridPoint21.x, gridPoint21.y);
    }
}

