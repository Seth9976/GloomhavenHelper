package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.NativeType;

public class NVPrimitiveRestart {
   public static final int GL_PRIMITIVE_RESTART_NV = 34136;
   public static final int GL_PRIMITIVE_RESTART_INDEX_NV = 34137;

   protected NVPrimitiveRestart() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(GLCapabilities caps) {
      return Checks.checkFunctions(caps.glPrimitiveRestartNV, caps.glPrimitiveRestartIndexNV);
   }

   public static native void glPrimitiveRestartNV();

   public static native void glPrimitiveRestartIndexNV(@NativeType("GLuint") int var0);

   static {
      GL.initialize();
   }
}
