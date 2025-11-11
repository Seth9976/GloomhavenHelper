package com.hm.spine;

import com.badlogic.gdx.graphics.g2d.Batch;

public enum BlendMode {
   normal(770, 1, 771, 1),
   additive(770, 1, 1, 1),
   multiply(774, 774, 771, 771),
   screen(1, 1, 769, 769);

   public final int source;
   public final int sourcePMA;
   public final int destColor;
   public final int sourceAlpha;
   public static final BlendMode[] values = values();

   private BlendMode(int source, int sourcePMA, int destColor, int sourceAlpha) {
      this.source = source;
      this.sourcePMA = sourcePMA;
      this.destColor = destColor;
      this.sourceAlpha = sourceAlpha;
   }

   public void apply(Batch batch, boolean premultipliedAlpha) {
      batch.setBlendFunctionSeparate(premultipliedAlpha ? this.sourcePMA : this.source, this.destColor, this.sourceAlpha, this.destColor);
   }
}
