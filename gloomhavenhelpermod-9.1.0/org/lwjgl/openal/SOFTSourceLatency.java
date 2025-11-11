package org.lwjgl.openal;

import java.nio.DoubleBuffer;
import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

public class SOFTSourceLatency {
   public static final int AL_SAMPLE_OFFSET_LATENCY_SOFT = 4608;
   public static final int AL_SEC_OFFSET_LATENCY_SOFT = 4609;

   protected SOFTSourceLatency() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(ALCapabilities caps) {
      return Checks.checkFunctions(
         caps.alSourcedSOFT,
         caps.alSource3dSOFT,
         caps.alSourcedvSOFT,
         caps.alGetSourcedSOFT,
         caps.alGetSource3dSOFT,
         caps.alGetSourcedvSOFT,
         caps.alSourcei64SOFT,
         caps.alSource3i64SOFT,
         caps.alSourcei64vSOFT,
         caps.alGetSourcei64SOFT,
         caps.alGetSource3i64SOFT,
         caps.alGetSourcei64vSOFT
      );
   }

   @NativeType("ALvoid")
   public static void alSourcedSOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALdouble") double value) {
      long __functionAddress = AL.getICD().alSourcedSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokeV(source, param, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alSource3dSOFT(
      @NativeType("ALuint") int source,
      @NativeType("ALenum") int param,
      @NativeType("ALdouble") double value1,
      @NativeType("ALdouble") double value2,
      @NativeType("ALdouble") double value3
   ) {
      long __functionAddress = AL.getICD().alSource3dSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokeV(source, param, value1, value2, value3, __functionAddress);
   }

   public static void nalSourcedvSOFT(int source, int param, long value) {
      long __functionAddress = AL.getICD().alSourcedvSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(source, param, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alSourcedvSOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALdouble const *") DoubleBuffer value) {
      if (Checks.CHECKS) {
         Checks.check(value, 1);
      }

      nalSourcedvSOFT(source, param, MemoryUtil.memAddress(value));
   }

   public static void nalGetSourcedSOFT(int source, int param, long value) {
      long __functionAddress = AL.getICD().alGetSourcedSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(source, param, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetSourcedSOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALdouble *") DoubleBuffer value) {
      if (Checks.CHECKS) {
         Checks.check(value, 1);
      }

      nalGetSourcedSOFT(source, param, MemoryUtil.memAddress(value));
   }

   @NativeType("ALvoid")
   public static double alGetSourcedSOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param) {
      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      double var5;
      try {
         DoubleBuffer value = stack.callocDouble(1);
         nalGetSourcedSOFT(source, param, MemoryUtil.memAddress(value));
         var5 = value.get(0);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var5;
   }

   public static void nalGetSource3dSOFT(int source, int param, long value1, long value2, long value3) {
      long __functionAddress = AL.getICD().alGetSource3dSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePPPV(source, param, value1, value2, value3, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetSource3dSOFT(
      @NativeType("ALuint") int source,
      @NativeType("ALenum") int param,
      @NativeType("ALdouble *") DoubleBuffer value1,
      @NativeType("ALdouble *") DoubleBuffer value2,
      @NativeType("ALdouble *") DoubleBuffer value3
   ) {
      if (Checks.CHECKS) {
         Checks.check(value1, 1);
         Checks.check(value2, 1);
         Checks.check(value3, 1);
      }

      nalGetSource3dSOFT(source, param, MemoryUtil.memAddress(value1), MemoryUtil.memAddress(value2), MemoryUtil.memAddress(value3));
   }

   public static void nalGetSourcedvSOFT(int source, int param, long values) {
      long __functionAddress = AL.getICD().alGetSourcedvSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(source, param, values, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetSourcedvSOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALdouble *") DoubleBuffer values) {
      if (Checks.CHECKS) {
         Checks.check(values, 1);
      }

      nalGetSourcedvSOFT(source, param, MemoryUtil.memAddress(values));
   }

   @NativeType("ALvoid")
   public static void alSourcei64SOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALint64SOFT") long value) {
      long __functionAddress = AL.getICD().alSourcei64SOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokeJV(source, param, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alSource3i64SOFT(
      @NativeType("ALuint") int source,
      @NativeType("ALenum") int param,
      @NativeType("ALint64SOFT") long value1,
      @NativeType("ALint64SOFT") long value2,
      @NativeType("ALint64SOFT") long value3
   ) {
      long __functionAddress = AL.getICD().alSource3i64SOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokeJJJV(source, param, value1, value2, value3, __functionAddress);
   }

   public static void nalSourcei64vSOFT(int source, int param, long values) {
      long __functionAddress = AL.getICD().alSourcei64vSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(source, param, values, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alSourcei64vSOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALint64SOFT const *") LongBuffer values) {
      if (Checks.CHECKS) {
         Checks.check(values, 1);
      }

      nalSourcei64vSOFT(source, param, MemoryUtil.memAddress(values));
   }

   public static void nalGetSourcei64SOFT(int source, int param, long value) {
      long __functionAddress = AL.getICD().alGetSourcei64SOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(source, param, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetSourcei64SOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALint64SOFT *") LongBuffer value) {
      if (Checks.CHECKS) {
         Checks.check(value, 1);
      }

      nalGetSourcei64SOFT(source, param, MemoryUtil.memAddress(value));
   }

   @NativeType("ALvoid")
   public static long alGetSourcei64SOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param) {
      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      long var5;
      try {
         LongBuffer value = stack.callocLong(1);
         nalGetSourcei64SOFT(source, param, MemoryUtil.memAddress(value));
         var5 = value.get(0);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var5;
   }

   public static void nalGetSource3i64SOFT(int source, int param, long value1, long value2, long value3) {
      long __functionAddress = AL.getICD().alGetSource3i64SOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePPPV(source, param, value1, value2, value3, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetSource3i64SOFT(
      @NativeType("ALuint") int source,
      @NativeType("ALenum") int param,
      @NativeType("ALint64SOFT *") LongBuffer value1,
      @NativeType("ALint64SOFT *") LongBuffer value2,
      @NativeType("ALint64SOFT *") LongBuffer value3
   ) {
      if (Checks.CHECKS) {
         Checks.check(value1, 1);
         Checks.check(value2, 1);
         Checks.check(value3, 1);
      }

      nalGetSource3i64SOFT(source, param, MemoryUtil.memAddress(value1), MemoryUtil.memAddress(value2), MemoryUtil.memAddress(value3));
   }

   public static void nalGetSourcei64vSOFT(int source, int param, long values) {
      long __functionAddress = AL.getICD().alGetSourcei64vSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(source, param, values, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetSourcei64vSOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALint64SOFT *") LongBuffer values) {
      if (Checks.CHECKS) {
         Checks.check(values, 1);
      }

      nalGetSourcei64vSOFT(source, param, MemoryUtil.memAddress(values));
   }

   @NativeType("ALvoid")
   public static void alSourcedvSOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALdouble const *") double[] value) {
      long __functionAddress = AL.getICD().alSourcedvSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(value, 1);
      }

      JNI.invokePV(source, param, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetSourcedSOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALdouble *") double[] value) {
      long __functionAddress = AL.getICD().alGetSourcedSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(value, 1);
      }

      JNI.invokePV(source, param, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetSource3dSOFT(
      @NativeType("ALuint") int source,
      @NativeType("ALenum") int param,
      @NativeType("ALdouble *") double[] value1,
      @NativeType("ALdouble *") double[] value2,
      @NativeType("ALdouble *") double[] value3
   ) {
      long __functionAddress = AL.getICD().alGetSource3dSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(value1, 1);
         Checks.check(value2, 1);
         Checks.check(value3, 1);
      }

      JNI.invokePPPV(source, param, value1, value2, value3, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetSourcedvSOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALdouble *") double[] values) {
      long __functionAddress = AL.getICD().alGetSourcedvSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(values, 1);
      }

      JNI.invokePV(source, param, values, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alSourcei64vSOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALint64SOFT const *") long[] values) {
      long __functionAddress = AL.getICD().alSourcei64vSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(values, 1);
      }

      JNI.invokePV(source, param, values, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetSourcei64SOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALint64SOFT *") long[] value) {
      long __functionAddress = AL.getICD().alGetSourcei64SOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(value, 1);
      }

      JNI.invokePV(source, param, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetSource3i64SOFT(
      @NativeType("ALuint") int source,
      @NativeType("ALenum") int param,
      @NativeType("ALint64SOFT *") long[] value1,
      @NativeType("ALint64SOFT *") long[] value2,
      @NativeType("ALint64SOFT *") long[] value3
   ) {
      long __functionAddress = AL.getICD().alGetSource3i64SOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(value1, 1);
         Checks.check(value2, 1);
         Checks.check(value3, 1);
      }

      JNI.invokePPPV(source, param, value1, value2, value3, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetSourcei64vSOFT(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALint64SOFT *") long[] values) {
      long __functionAddress = AL.getICD().alGetSourcei64vSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(values, 1);
      }

      JNI.invokePV(source, param, values, __functionAddress);
   }
}
