package org.lwjgl.opengl;

import java.util.Set;
import org.lwjgl.system.Checks;
import org.lwjgl.system.NativeType;

public class ARBSparseBuffer {
   public static final int GL_SPARSE_STORAGE_BIT_ARB = 1024;
   public static final int GL_SPARSE_BUFFER_PAGE_SIZE_ARB = 33528;

   protected ARBSparseBuffer() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(GLCapabilities caps, Set ext) {
      return Checks.checkFunctions(caps.glBufferPageCommitmentARB);
   }

   public static native void glBufferPageCommitmentARB(
      @NativeType("GLenum") int var0, @NativeType("GLintptr") long var1, @NativeType("GLsizeiptr") long var3, @NativeType("GLboolean") boolean var5
   );

   public static native void glNamedBufferPageCommitmentEXT(
      @NativeType("GLuint") int var0, @NativeType("GLintptr") long var1, @NativeType("GLsizeiptr") long var3, @NativeType("GLboolean") boolean var5
   );

   public static native void glNamedBufferPageCommitmentARB(
      @NativeType("GLuint") int var0, @NativeType("GLintptr") long var1, @NativeType("GLsizeiptr") long var3, @NativeType("GLboolean") boolean var5
   );

   static {
      GL.initialize();
   }
}
