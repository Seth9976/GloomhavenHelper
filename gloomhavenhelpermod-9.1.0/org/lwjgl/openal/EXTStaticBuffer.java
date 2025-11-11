package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

public class EXTStaticBuffer {
   protected EXTStaticBuffer() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(ALCapabilities caps) {
      return Checks.checkFunctions(caps.alBufferDataStatic);
   }

   public static void nalBufferDataStatic(int buffer, int format, long data, int len, int freq) {
      long __functionAddress = AL.getICD().alBufferDataStatic;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(buffer, format, data, len, freq, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alBufferDataStatic(
      @NativeType("ALint") int buffer, @NativeType("ALenum") int format, @NativeType("ALvoid *") ByteBuffer data, @NativeType("ALsizei") int freq
   ) {
      nalBufferDataStatic(buffer, format, MemoryUtil.memAddress(data), data.remaining(), freq);
   }

   @NativeType("ALvoid")
   public static void alBufferDataStatic(
      @NativeType("ALint") int buffer, @NativeType("ALenum") int format, @NativeType("ALvoid *") ShortBuffer data, @NativeType("ALsizei") int freq
   ) {
      nalBufferDataStatic(buffer, format, MemoryUtil.memAddress(data), data.remaining() << 1, freq);
   }

   @NativeType("ALvoid")
   public static void alBufferDataStatic(
      @NativeType("ALint") int buffer, @NativeType("ALenum") int format, @NativeType("ALvoid *") IntBuffer data, @NativeType("ALsizei") int freq
   ) {
      nalBufferDataStatic(buffer, format, MemoryUtil.memAddress(data), data.remaining() << 2, freq);
   }

   @NativeType("ALvoid")
   public static void alBufferDataStatic(
      @NativeType("ALint") int buffer, @NativeType("ALenum") int format, @NativeType("ALvoid *") FloatBuffer data, @NativeType("ALsizei") int freq
   ) {
      nalBufferDataStatic(buffer, format, MemoryUtil.memAddress(data), data.remaining() << 2, freq);
   }

   @NativeType("ALvoid")
   public static void alBufferDataStatic(
      @NativeType("ALint") int buffer, @NativeType("ALenum") int format, @NativeType("ALvoid *") short[] data, @NativeType("ALsizei") int freq
   ) {
      long __functionAddress = AL.getICD().alBufferDataStatic;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(buffer, format, data, data.length << 1, freq, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alBufferDataStatic(
      @NativeType("ALint") int buffer, @NativeType("ALenum") int format, @NativeType("ALvoid *") int[] data, @NativeType("ALsizei") int freq
   ) {
      long __functionAddress = AL.getICD().alBufferDataStatic;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(buffer, format, data, data.length << 2, freq, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alBufferDataStatic(
      @NativeType("ALint") int buffer, @NativeType("ALenum") int format, @NativeType("ALvoid *") float[] data, @NativeType("ALsizei") int freq
   ) {
      long __functionAddress = AL.getICD().alBufferDataStatic;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(buffer, format, data, data.length << 2, freq, __functionAddress);
   }
}
