package org.lwjgl.opengl;

import org.lwjgl.system.Checks;

public class NVTextureBarrier {
   protected NVTextureBarrier() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(GLCapabilities caps) {
      return Checks.checkFunctions(caps.glTextureBarrierNV);
   }

   public static native void glTextureBarrierNV();

   static {
      GL.initialize();
   }
}
