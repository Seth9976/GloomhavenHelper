package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

public class NVDrawVulkanImage {
   protected NVDrawVulkanImage() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(GLCapabilities caps) {
      return Checks.checkFunctions(caps.glDrawVkImageNV, caps.glGetVkProcAddrNV, caps.glWaitVkSemaphoreNV, caps.glSignalVkSemaphoreNV, caps.glSignalVkFenceNV);
   }

   public static native void glDrawVkImageNV(
      @NativeType("GLuint64") long var0,
      @NativeType("GLuint") int var2,
      @NativeType("GLfloat") float var3,
      @NativeType("GLfloat") float var4,
      @NativeType("GLfloat") float var5,
      @NativeType("GLfloat") float var6,
      @NativeType("GLfloat") float var7,
      @NativeType("GLfloat") float var8,
      @NativeType("GLfloat") float var9,
      @NativeType("GLfloat") float var10,
      @NativeType("GLfloat") float var11
   );

   public static native long nglGetVkProcAddrNV(long var0);

   @NativeType("VULKANPROCNV")
   public static long glGetVkProcAddrNV(@NativeType("GLchar const *") ByteBuffer name) {
      if (Checks.CHECKS) {
         Checks.checkNT1(name);
      }

      return nglGetVkProcAddrNV(MemoryUtil.memAddress(name));
   }

   @NativeType("VULKANPROCNV")
   public static long glGetVkProcAddrNV(@NativeType("GLchar const *") CharSequence name) {
      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      long var5;
      try {
         stack.nASCII(name, true);
         long nameEncoded = stack.getPointerAddress();
         var5 = nglGetVkProcAddrNV(nameEncoded);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var5;
   }

   public static native void glWaitVkSemaphoreNV(@NativeType("GLuint64") long var0);

   public static native void glSignalVkSemaphoreNV(@NativeType("GLuint64") long var0);

   public static native void glSignalVkFenceNV(@NativeType("GLuint64") long var0);

   static {
      GL.initialize();
   }
}
