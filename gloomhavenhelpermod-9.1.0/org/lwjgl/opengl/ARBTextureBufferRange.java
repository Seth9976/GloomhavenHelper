package org.lwjgl.opengl;

import java.util.Set;
import org.lwjgl.system.Checks;
import org.lwjgl.system.NativeType;

public class ARBTextureBufferRange {
   public static final int GL_TEXTURE_BUFFER_OFFSET = 37277;
   public static final int GL_TEXTURE_BUFFER_SIZE = 37278;
   public static final int GL_TEXTURE_BUFFER_OFFSET_ALIGNMENT = 37279;

   protected ARBTextureBufferRange() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(GLCapabilities caps, Set ext) {
      return Checks.checkFunctions(caps.glTexBufferRange, ext.contains("GL_EXT_direct_state_access") ? caps.glTextureBufferRangeEXT : -1L);
   }

   public static void glTexBufferRange(
      @NativeType("GLenum") int target,
      @NativeType("GLenum") int internalformat,
      @NativeType("GLuint") int buffer,
      @NativeType("GLintptr") long offset,
      @NativeType("GLsizeiptr") long size
   ) {
      GL43C.glTexBufferRange(target, internalformat, buffer, offset, size);
   }

   public static native void glTextureBufferRangeEXT(
      @NativeType("GLuint") int var0,
      @NativeType("GLenum") int var1,
      @NativeType("GLenum") int var2,
      @NativeType("GLuint") int var3,
      @NativeType("GLintptr") long var4,
      @NativeType("GLsizeiptr") long var6
   );

   static {
      GL.initialize();
   }
}
