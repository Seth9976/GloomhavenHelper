package org.lwjgl.opengl;

import org.lwjgl.system.Checks;

public class EXTShaderFramebufferFetchNonCoherent {
   protected EXTShaderFramebufferFetchNonCoherent() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(GLCapabilities caps) {
      return Checks.checkFunctions(caps.glFramebufferFetchBarrierEXT);
   }

   public static native void glFramebufferFetchBarrierEXT();

   static {
      GL.initialize();
   }
}
