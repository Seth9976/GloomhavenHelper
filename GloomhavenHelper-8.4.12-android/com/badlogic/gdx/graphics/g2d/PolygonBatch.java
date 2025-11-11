package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Texture;

public interface PolygonBatch extends Batch {
    void draw(Texture arg1, float[] arg2, int arg3, int arg4, short[] arg5, int arg6, int arg7);

    void draw(PolygonRegion arg1, float arg2, float arg3);

    void draw(PolygonRegion arg1, float arg2, float arg3, float arg4, float arg5);

    void draw(PolygonRegion arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10);
}

