package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import javax.annotation.Nullable;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

public class ALC11 extends ALC10 {
   public static final int ALC_MONO_SOURCES = 4112;
   public static final int ALC_STEREO_SOURCES = 4113;
   public static final int ALC_DEFAULT_ALL_DEVICES_SPECIFIER = 4114;
   public static final int ALC_ALL_DEVICES_SPECIFIER = 4115;
   public static final int ALC_CAPTURE_DEVICE_SPECIFIER = 784;
   public static final int ALC_CAPTURE_DEFAULT_DEVICE_SPECIFIER = 785;
   public static final int ALC_CAPTURE_SAMPLES = 786;

   protected ALC11() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(ALCCapabilities caps) {
      return Checks.checkFunctions(caps.alcCaptureOpenDevice, caps.alcCaptureCloseDevice, caps.alcCaptureStart, caps.alcCaptureStop, caps.alcCaptureSamples);
   }

   public static long nalcCaptureOpenDevice(long deviceName, int frequency, int format, int samples) {
      long __functionAddress = ALC.getICD().alcCaptureOpenDevice;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      return JNI.invokePP(deviceName, frequency, format, samples, __functionAddress);
   }

   @NativeType("ALCdevice *")
   public static long alcCaptureOpenDevice(
      @Nullable @NativeType("ALCchar const *") ByteBuffer deviceName,
      @NativeType("ALCuint") int frequency,
      @NativeType("ALCenum") int format,
      @NativeType("ALCsizei") int samples
   ) {
      if (Checks.CHECKS) {
         Checks.checkNT1Safe(deviceName);
      }

      return nalcCaptureOpenDevice(MemoryUtil.memAddressSafe(deviceName), frequency, format, samples);
   }

   @NativeType("ALCdevice *")
   public static long alcCaptureOpenDevice(
      @Nullable @NativeType("ALCchar const *") CharSequence deviceName,
      @NativeType("ALCuint") int frequency,
      @NativeType("ALCenum") int format,
      @NativeType("ALCsizei") int samples
   ) {
      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      long var8;
      try {
         stack.nUTF8Safe(deviceName, true);
         long deviceNameEncoded = deviceName == null ? 0L : stack.getPointerAddress();
         var8 = nalcCaptureOpenDevice(deviceNameEncoded, frequency, format, samples);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var8;
   }

   @NativeType("ALCboolean")
   public static boolean alcCaptureCloseDevice(@NativeType("ALCdevice *") long device) {
      long __functionAddress = ALC.getICD().alcCaptureCloseDevice;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(device);
      }

      return JNI.invokePZ(device, __functionAddress);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureStart(@NativeType("ALCdevice *") long device) {
      long __functionAddress = ALC.getICD().alcCaptureStart;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(device);
      }

      JNI.invokePV(device, __functionAddress);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureStop(@NativeType("ALCdevice *") long device) {
      long __functionAddress = ALC.getICD().alcCaptureStop;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(device);
      }

      JNI.invokePV(device, __functionAddress);
   }

   public static void nalcCaptureSamples(long device, long buffer, int samples) {
      long __functionAddress = ALC.getICD().alcCaptureSamples;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(device);
      }

      JNI.invokePPV(device, buffer, samples, __functionAddress);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureSamples(@NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") ByteBuffer buffer, @NativeType("ALCsizei") int samples) {
      nalcCaptureSamples(device, MemoryUtil.memAddress(buffer), samples);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureSamples(
      @NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") ShortBuffer buffer, @NativeType("ALCsizei") int samples
   ) {
      nalcCaptureSamples(device, MemoryUtil.memAddress(buffer), samples);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureSamples(@NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") IntBuffer buffer, @NativeType("ALCsizei") int samples) {
      nalcCaptureSamples(device, MemoryUtil.memAddress(buffer), samples);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureSamples(
      @NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") FloatBuffer buffer, @NativeType("ALCsizei") int samples
   ) {
      nalcCaptureSamples(device, MemoryUtil.memAddress(buffer), samples);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureSamples(@NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") short[] buffer, @NativeType("ALCsizei") int samples) {
      long __functionAddress = ALC.getICD().alcCaptureSamples;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(device);
      }

      JNI.invokePPV(device, buffer, samples, __functionAddress);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureSamples(@NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") int[] buffer, @NativeType("ALCsizei") int samples) {
      long __functionAddress = ALC.getICD().alcCaptureSamples;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(device);
      }

      JNI.invokePPV(device, buffer, samples, __functionAddress);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureSamples(@NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") float[] buffer, @NativeType("ALCsizei") int samples) {
      long __functionAddress = ALC.getICD().alcCaptureSamples;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(device);
      }

      JNI.invokePPV(device, buffer, samples, __functionAddress);
   }
}
