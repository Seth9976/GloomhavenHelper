package com.esotericsoftware.spine;

import com.badlogic.gdx.graphics.g2d.Batch;

public enum BlendMode {
    normal(770, 1, 0x303, 1),
    additive(770, 1, 1, 1),
    multiply(0x306, 0x306, 0x303, 0x303),
    screen(1, 1, 0x301, 0x301);

    public final int destColor;
    public final int source;
    public final int sourceAlpha;
    public final int sourcePMA;
    public static final BlendMode[] values;

    static {
        BlendMode.values = (BlendMode[])BlendMode.$VALUES.clone();
    }

    private BlendMode(int v1, int v2, int v3, int v4) {
        this.source = v1;
        this.sourcePMA = v2;
        this.destColor = v3;
        this.sourceAlpha = v4;
    }

    public void apply(Batch batch0, boolean z) {
        batch0.setBlendFunctionSeparate((z ? this.sourcePMA : this.source), this.destColor, this.sourceAlpha, this.destColor);
    }
}

