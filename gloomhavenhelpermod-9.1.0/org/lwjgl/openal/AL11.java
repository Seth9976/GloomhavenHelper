package org.lwjgl.openal;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

public class AL11 extends AL10 {
   public static final int AL_SEC_OFFSET = 4132;
   public static final int AL_SAMPLE_OFFSET = 4133;
   public static final int AL_BYTE_OFFSET = 4134;
   public static final int AL_STATIC = 4136;
   public static final int AL_STREAMING = 4137;
   public static final int AL_UNDETERMINED = 4144;
   public static final int AL_ILLEGAL_COMMAND = 40964;
   public static final int AL_SPEED_OF_SOUND = 49155;
   public static final int AL_LINEAR_DISTANCE = 53251;
   public static final int AL_LINEAR_DISTANCE_CLAMPED = 53252;
   public static final int AL_EXPONENT_DISTANCE = 53253;
   public static final int AL_EXPONENT_DISTANCE_CLAMPED = 53254;

   protected AL11() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(ALCapabilities caps) {
      return Checks.checkFunctions(
         caps.alListener3i,
         caps.alGetListeneriv,
         caps.alSource3i,
         caps.alListeneriv,
         caps.alSourceiv,
         caps.alBufferf,
         caps.alBuffer3f,
         caps.alBufferfv,
         caps.alBufferi,
         caps.alBuffer3i,
         caps.alBufferiv,
         caps.alGetBufferiv,
         caps.alGetBufferfv,
         caps.alSpeedOfSound
      );
   }

   @NativeType("ALvoid")
   public static void alListener3i(
      @NativeType("ALenum") int paramName, @NativeType("ALint") int value1, @NativeType("ALint") int value2, @NativeType("ALint") int value3
   ) {
      long __functionAddress = AL.getICD().alListener3i;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokeV(paramName, value1, value2, value3, __functionAddress);
   }

   public static void nalGetListeneriv(int param, long values) {
      long __functionAddress = AL.getICD().alGetListeneriv;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(param, values, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetListeneriv(@NativeType("ALenum") int param, @NativeType("ALint *") IntBuffer values) {
      if (Checks.CHECKS) {
         Checks.check(values, 1);
      }

      nalGetListeneriv(param, MemoryUtil.memAddress(values));
   }

   @NativeType("ALvoid")
   public static void alSource3i(
      @NativeType("ALuint") int source,
      @NativeType("ALenum") int paramName,
      @NativeType("ALint") int value1,
      @NativeType("ALint") int value2,
      @NativeType("ALint") int value3
   ) {
      long __functionAddress = AL.getICD().alSource3i;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokeV(source, paramName, value1, value2, value3, __functionAddress);
   }

   public static void nalListeneriv(int listener, long value) {
      long __functionAddress = AL.getICD().alListeneriv;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(listener, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alListeneriv(@NativeType("ALenum") int listener, @NativeType("ALint const *") IntBuffer value) {
      if (Checks.CHECKS) {
         Checks.check(value, 1);
      }

      nalListeneriv(listener, MemoryUtil.memAddress(value));
   }

   public static void nalSourceiv(int source, int paramName, long value) {
      long __functionAddress = AL.getICD().alSourceiv;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(source, paramName, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alSourceiv(@NativeType("ALuint") int source, @NativeType("ALenum") int paramName, @NativeType("ALint const *") IntBuffer value) {
      if (Checks.CHECKS) {
         Checks.check(value, 1);
      }

      nalSourceiv(source, paramName, MemoryUtil.memAddress(value));
   }

   @NativeType("ALvoid")
   public static void alBufferf(@NativeType("ALuint") int buffer, @NativeType("ALenum") int paramName, @NativeType("ALfloat") float value) {
      long __functionAddress = AL.getICD().alBufferf;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokeV(buffer, paramName, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alBuffer3f(
      @NativeType("ALuint") int buffer,
      @NativeType("ALenum") int paramName,
      @NativeType("ALfloat") float value1,
      @NativeType("ALfloat") float value2,
      @NativeType("ALfloat") float value3
   ) {
      long __functionAddress = AL.getICD().alBuffer3f;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokeV(buffer, paramName, value1, value2, value3, __functionAddress);
   }

   public static void nalBufferfv(int buffer, int paramName, long value) {
      long __functionAddress = AL.getICD().alBufferfv;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(buffer, paramName, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alBufferfv(@NativeType("ALuint") int buffer, @NativeType("ALenum") int paramName, @NativeType("ALfloat const *") FloatBuffer value) {
      if (Checks.CHECKS) {
         Checks.check(value, 1);
      }

      nalBufferfv(buffer, paramName, MemoryUtil.memAddress(value));
   }

   @NativeType("ALvoid")
   public static void alBufferi(@NativeType("ALuint") int buffer, @NativeType("ALenum") int paramName, @NativeType("ALint") int value) {
      long __functionAddress = AL.getICD().alBufferi;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokeV(buffer, paramName, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alBuffer3i(
      @NativeType("ALuint") int buffer,
      @NativeType("ALenum") int paramName,
      @NativeType("ALint") int value1,
      @NativeType("ALint") int value2,
      @NativeType("ALint") int value3
   ) {
      long __functionAddress = AL.getICD().alBuffer3i;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokeV(buffer, paramName, value1, value2, value3, __functionAddress);
   }

   public static void nalBufferiv(int buffer, int paramName, long value) {
      long __functionAddress = AL.getICD().alBufferiv;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(buffer, paramName, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alBufferiv(@NativeType("ALuint") int buffer, @NativeType("ALenum") int paramName, @NativeType("ALint const *") IntBuffer value) {
      if (Checks.CHECKS) {
         Checks.check(value, 1);
      }

      nalBufferiv(buffer, paramName, MemoryUtil.memAddress(value));
   }

   public static void nalGetBufferiv(int buffer, int param, long values) {
      long __functionAddress = AL.getICD().alGetBufferiv;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(buffer, param, values, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetBufferiv(@NativeType("ALuint") int buffer, @NativeType("ALenum") int param, @NativeType("ALint *") IntBuffer values) {
      if (Checks.CHECKS) {
         Checks.check(values, 1);
      }

      nalGetBufferiv(buffer, param, MemoryUtil.memAddress(values));
   }

   public static void nalGetBufferfv(int buffer, int param, long values) {
      long __functionAddress = AL.getICD().alGetBufferfv;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokePV(buffer, param, values, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetBufferfv(@NativeType("ALuint") int buffer, @NativeType("ALenum") int param, @NativeType("ALfloat *") FloatBuffer values) {
      if (Checks.CHECKS) {
         Checks.check(values, 1);
      }

      nalGetBufferfv(buffer, param, MemoryUtil.memAddress(values));
   }

   @NativeType("ALvoid")
   public static void alSpeedOfSound(@NativeType("ALfloat") float value) {
      long __functionAddress = AL.getICD().alSpeedOfSound;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      JNI.invokeV(value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetListeneriv(@NativeType("ALenum") int param, @NativeType("ALint *") int[] values) {
      long __functionAddress = AL.getICD().alGetListeneriv;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(values, 1);
      }

      JNI.invokePV(param, values, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alListeneriv(@NativeType("ALenum") int listener, @NativeType("ALint const *") int[] value) {
      long __functionAddress = AL.getICD().alListeneriv;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(value, 1);
      }

      JNI.invokePV(listener, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alSourceiv(@NativeType("ALuint") int source, @NativeType("ALenum") int paramName, @NativeType("ALint const *") int[] value) {
      long __functionAddress = AL.getICD().alSourceiv;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(value, 1);
      }

      JNI.invokePV(source, paramName, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alBufferfv(@NativeType("ALuint") int buffer, @NativeType("ALenum") int paramName, @NativeType("ALfloat const *") float[] value) {
      long __functionAddress = AL.getICD().alBufferfv;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(value, 1);
      }

      JNI.invokePV(buffer, paramName, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alBufferiv(@NativeType("ALuint") int buffer, @NativeType("ALenum") int paramName, @NativeType("ALint const *") int[] value) {
      long __functionAddress = AL.getICD().alBufferiv;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(value, 1);
      }

      JNI.invokePV(buffer, paramName, value, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetBufferiv(@NativeType("ALuint") int buffer, @NativeType("ALenum") int param, @NativeType("ALint *") int[] values) {
      long __functionAddress = AL.getICD().alGetBufferiv;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(values, 1);
      }

      JNI.invokePV(buffer, param, values, __functionAddress);
   }

   @NativeType("ALvoid")
   public static void alGetBufferfv(@NativeType("ALuint") int buffer, @NativeType("ALenum") int param, @NativeType("ALfloat *") float[] values) {
      long __functionAddress = AL.getICD().alGetBufferfv;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(values, 1);
      }

      JNI.invokePV(buffer, param, values, __functionAddress);
   }
}
