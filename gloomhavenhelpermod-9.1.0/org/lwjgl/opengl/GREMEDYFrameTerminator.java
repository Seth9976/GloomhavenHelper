package org.lwjgl.opengl;

import org.lwjgl.system.Checks;

public class GREMEDYFrameTerminator {
   protected GREMEDYFrameTerminator() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(GLCapabilities caps) {
      return Checks.checkFunctions(caps.glFrameTerminatorGREMEDY);
   }

   public static native void glFrameTerminatorGREMEDY();

   static {
      GL.initialize();
   }
}
