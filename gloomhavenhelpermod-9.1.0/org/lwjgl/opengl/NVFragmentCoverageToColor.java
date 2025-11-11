package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.NativeType;

public class NVFragmentCoverageToColor {
   public static final int GL_FRAGMENT_COVERAGE_TO_COLOR_NV = 37597;
   public static final int GL_FRAGMENT_COVERAGE_COLOR_NV = 37598;

   protected NVFragmentCoverageToColor() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(GLCapabilities caps) {
      return Checks.checkFunctions(caps.glFragmentCoverageColorNV);
   }

   public static native void glFragmentCoverageColorNV(@NativeType("GLuint") int var0);

   static {
      GL.initialize();
   }
}
