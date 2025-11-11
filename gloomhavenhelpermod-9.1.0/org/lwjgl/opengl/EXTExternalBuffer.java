package org.lwjgl.opengl;

import java.util.Set;
import org.lwjgl.system.Checks;
import org.lwjgl.system.NativeType;

public class EXTExternalBuffer {
   protected EXTExternalBuffer() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(GLCapabilities caps, Set ext) {
      return Checks.checkFunctions(caps.glBufferStorageExternalEXT, caps.hasDSA(ext) ? caps.glNamedBufferStorageExternalEXT : -1L);
   }

   public static native void nglBufferStorageExternalEXT(int var0, long var1, long var3, long var5, int var7);

   public static void glBufferStorageExternalEXT(
      @NativeType("GLenum") int target,
      @NativeType("GLintptr") long offset,
      @NativeType("GLsizeiptr") long size,
      @NativeType("GLeglClientBufferEXT") long clientBuffer,
      @NativeType("GLbitfield") int flags
   ) {
      if (Checks.CHECKS) {
         Checks.check(clientBuffer);
      }

      nglBufferStorageExternalEXT(target, offset, size, clientBuffer, flags);
   }

   public static native void nglNamedBufferStorageExternalEXT(int var0, long var1, long var3, long var5, int var7);

   public static void glNamedBufferStorageExternalEXT(
      @NativeType("GLuint") int buffer,
      @NativeType("GLintptr") long offset,
      @NativeType("GLsizeiptr") long size,
      @NativeType("GLeglClientBufferEXT") long clientBuffer,
      @NativeType("GLbitfield") int flags
   ) {
      if (Checks.CHECKS) {
         Checks.check(clientBuffer);
      }

      nglNamedBufferStorageExternalEXT(buffer, offset, size, clientBuffer, flags);
   }

   static {
      GL.initialize();
   }
}
