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

public class SOFTLoopback {
   public static final int ALC_BYTE_SOFT = 5120;
   public static final int ALC_UNSIGNED_BYTE_SOFT = 5121;
   public static final int ALC_SHORT_SOFT = 5122;
   public static final int ALC_UNSIGNED_SHORT_SOFT = 5123;
   public static final int ALC_INT_SOFT = 5124;
   public static final int ALC_UNSIGNED_INT_SOFT = 5125;
   public static final int ALC_FLOAT_SOFT = 5126;
   public static final int ALC_MONO_SOFT = 5376;
   public static final int ALC_STEREO_SOFT = 5377;
   public static final int ALC_QUAD_SOFT = 5379;
   public static final int ALC_5POINT1_SOFT = 5380;
   public static final int ALC_6POINT1_SOFT = 5381;
   public static final int ALC_7POINT1_SOFT = 5382;
   public static final int ALC_FORMAT_CHANNELS_SOFT = 6544;
   public static final int ALC_FORMAT_TYPE_SOFT = 6545;

   protected SOFTLoopback() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(ALCCapabilities caps) {
      return Checks.checkFunctions(caps.alcLoopbackOpenDeviceSOFT, caps.alcIsRenderFormatSupportedSOFT, caps.alcRenderSamplesSOFT);
   }

   public static long nalcLoopbackOpenDeviceSOFT(long deviceName) {
      long __functionAddress = ALC.getICD().alcLoopbackOpenDeviceSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
      }

      return JNI.invokePP(deviceName, __functionAddress);
   }

   @NativeType("ALCdevice *")
   public static long alcLoopbackOpenDeviceSOFT(@Nullable @NativeType("ALCchar const *") ByteBuffer deviceName) {
      if (Checks.CHECKS) {
         Checks.checkNT1Safe(deviceName);
      }

      return nalcLoopbackOpenDeviceSOFT(MemoryUtil.memAddressSafe(deviceName));
   }

   @NativeType("ALCdevice *")
   public static long alcLoopbackOpenDeviceSOFT(@Nullable @NativeType("ALCchar const *") CharSequence deviceName) {
      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      long var5;
      try {
         stack.nUTF8Safe(deviceName, true);
         long deviceNameEncoded = deviceName == null ? 0L : stack.getPointerAddress();
         var5 = nalcLoopbackOpenDeviceSOFT(deviceNameEncoded);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var5;
   }

   @NativeType("ALCboolean")
   public static boolean alcIsRenderFormatSupportedSOFT(
      @NativeType("ALCdevice *") long device, @NativeType("ALCsizei") int frequency, @NativeType("ALCenum") int channels, @NativeType("ALCenum") int type
   ) {
      long __functionAddress = ALC.getICD().alcIsRenderFormatSupportedSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(device);
      }

      return JNI.invokePZ(device, frequency, channels, type, __functionAddress);
   }

   public static void nalcRenderSamplesSOFT(long device, long buffer, int samples) {
      long __functionAddress = ALC.getICD().alcRenderSamplesSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(device);
      }

      JNI.invokePPV(device, buffer, samples, __functionAddress);
   }

   @NativeType("ALCvoid")
   public static void alcRenderSamplesSOFT(
      @NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") ByteBuffer buffer, @NativeType("ALCsizei") int samples
   ) {
      nalcRenderSamplesSOFT(device, MemoryUtil.memAddress(buffer), samples);
   }

   @NativeType("ALCvoid")
   public static void alcRenderSamplesSOFT(
      @NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") ShortBuffer buffer, @NativeType("ALCsizei") int samples
   ) {
      nalcRenderSamplesSOFT(device, MemoryUtil.memAddress(buffer), samples);
   }

   @NativeType("ALCvoid")
   public static void alcRenderSamplesSOFT(
      @NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") IntBuffer buffer, @NativeType("ALCsizei") int samples
   ) {
      nalcRenderSamplesSOFT(device, MemoryUtil.memAddress(buffer), samples);
   }

   @NativeType("ALCvoid")
   public static void alcRenderSamplesSOFT(
      @NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") FloatBuffer buffer, @NativeType("ALCsizei") int samples
   ) {
      nalcRenderSamplesSOFT(device, MemoryUtil.memAddress(buffer), samples);
   }

   @NativeType("ALCvoid")
   public static void alcRenderSamplesSOFT(@NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") short[] buffer, @NativeType("ALCsizei") int samples) {
      long __functionAddress = ALC.getICD().alcRenderSamplesSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(device);
      }

      JNI.invokePPV(device, buffer, samples, __functionAddress);
   }

   @NativeType("ALCvoid")
   public static void alcRenderSamplesSOFT(@NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") int[] buffer, @NativeType("ALCsizei") int samples) {
      long __functionAddress = ALC.getICD().alcRenderSamplesSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(device);
      }

      JNI.invokePPV(device, buffer, samples, __functionAddress);
   }

   @NativeType("ALCvoid")
   public static void alcRenderSamplesSOFT(@NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") float[] buffer, @NativeType("ALCsizei") int samples) {
      long __functionAddress = ALC.getICD().alcRenderSamplesSOFT;
      if (Checks.CHECKS) {
         Checks.check(__functionAddress);
         Checks.check(device);
      }

      JNI.invokePPV(device, buffer, samples, __functionAddress);
   }
}
