package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.NativeType;

public class ARBDrawBuffersBlend {
   protected ARBDrawBuffersBlend() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(GLCapabilities caps) {
      return Checks.checkFunctions(caps.glBlendEquationiARB, caps.glBlendEquationSeparateiARB, caps.glBlendFunciARB, caps.glBlendFuncSeparateiARB);
   }

   public static native void glBlendEquationiARB(@NativeType("GLuint") int var0, @NativeType("GLenum") int var1);

   public static native void glBlendEquationSeparateiARB(@NativeType("GLuint") int var0, @NativeType("GLenum") int var1, @NativeType("GLenum") int var2);

   public static native void glBlendFunciARB(@NativeType("GLuint") int var0, @NativeType("GLenum") int var1, @NativeType("GLenum") int var2);

   public static native void glBlendFuncSeparateiARB(
      @NativeType("GLuint") int var0,
      @NativeType("GLenum") int var1,
      @NativeType("GLenum") int var2,
      @NativeType("GLenum") int var3,
      @NativeType("GLenum") int var4
   );

   static {
      GL.initialize();
   }
}
