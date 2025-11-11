package org.lwjgl.opengl;

import java.util.Set;
import org.lwjgl.system.Checks;
import org.lwjgl.system.NativeType;

public class ARBTextureStorage {
   public static final int GL_TEXTURE_IMMUTABLE_FORMAT = 37167;

   protected ARBTextureStorage() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(GLCapabilities caps, Set ext) {
      return Checks.checkFunctions(
         caps.glTexStorage1D,
         caps.glTexStorage2D,
         caps.glTexStorage3D,
         ext.contains("GL_EXT_direct_state_access") ? caps.glTextureStorage1DEXT : -1L,
         ext.contains("GL_EXT_direct_state_access") ? caps.glTextureStorage2DEXT : -1L,
         ext.contains("GL_EXT_direct_state_access") ? caps.glTextureStorage3DEXT : -1L
      );
   }

   public static void glTexStorage1D(
      @NativeType("GLenum") int target, @NativeType("GLsizei") int levels, @NativeType("GLenum") int internalformat, @NativeType("GLsizei") int width
   ) {
      GL42C.glTexStorage1D(target, levels, internalformat, width);
   }

   public static void glTexStorage2D(
      @NativeType("GLenum") int target,
      @NativeType("GLsizei") int levels,
      @NativeType("GLenum") int internalformat,
      @NativeType("GLsizei") int width,
      @NativeType("GLsizei") int height
   ) {
      GL42C.glTexStorage2D(target, levels, internalformat, width, height);
   }

   public static void glTexStorage3D(
      @NativeType("GLenum") int target,
      @NativeType("GLsizei") int levels,
      @NativeType("GLenum") int internalformat,
      @NativeType("GLsizei") int width,
      @NativeType("GLsizei") int height,
      @NativeType("GLsizei") int depth
   ) {
      GL42C.glTexStorage3D(target, levels, internalformat, width, height, depth);
   }

   public static native void glTextureStorage1DEXT(
      @NativeType("GLuint") int var0,
      @NativeType("GLenum") int var1,
      @NativeType("GLsizei") int var2,
      @NativeType("GLenum") int var3,
      @NativeType("GLsizei") int var4
   );

   public static native void glTextureStorage2DEXT(
      @NativeType("GLuint") int var0,
      @NativeType("GLenum") int var1,
      @NativeType("GLsizei") int var2,
      @NativeType("GLenum") int var3,
      @NativeType("GLsizei") int var4,
      @NativeType("GLsizei") int var5
   );

   public static native void glTextureStorage3DEXT(
      @NativeType("GLuint") int var0,
      @NativeType("GLenum") int var1,
      @NativeType("GLsizei") int var2,
      @NativeType("GLenum") int var3,
      @NativeType("GLsizei") int var4,
      @NativeType("GLsizei") int var5,
      @NativeType("GLsizei") int var6
   );

   static {
      GL.initialize();
   }
}
