package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.NativeType;

public class EXTStencilClearTag {
   public static final int GL_STENCIL_TAG_BITS_EXT = 35058;
   public static final int GL_STENCIL_CLEAR_TAG_VALUE_EXT = 35059;

   protected EXTStencilClearTag() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(GLCapabilities caps) {
      return Checks.checkFunctions(caps.glStencilClearTagEXT);
   }

   public static native void glStencilClearTagEXT(@NativeType("GLsizei") int var0, @NativeType("GLuint") int var1);

   static {
      GL.initialize();
   }
}
