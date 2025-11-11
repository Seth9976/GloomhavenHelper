package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import javax.annotation.Nullable;
import org.lwjgl.system.Checks;
import org.lwjgl.system.NativeType;

public class EXTCapture {
   public static final int ALC_CAPTURE_DEVICE_SPECIFIER = 784;
   public static final int ALC_CAPTURE_DEFAULT_DEVICE_SPECIFIER = 785;
   public static final int ALC_CAPTURE_SAMPLES = 786;

   protected EXTCapture() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(ALCCapabilities caps) {
      return Checks.checkFunctions(caps.alcCaptureOpenDevice, caps.alcCaptureCloseDevice, caps.alcCaptureStart, caps.alcCaptureStop, caps.alcCaptureSamples);
   }

   public static long nalcCaptureOpenDevice(long deviceName, int frequency, int format, int samples) {
      return ALC11.nalcCaptureOpenDevice(deviceName, frequency, format, samples);
   }

   @NativeType("ALCdevice *")
   public static long alcCaptureOpenDevice(
      @Nullable @NativeType("ALCchar const *") ByteBuffer deviceName,
      @NativeType("ALCuint") int frequency,
      @NativeType("ALCenum") int format,
      @NativeType("ALCsizei") int samples
   ) {
      return ALC11.alcCaptureOpenDevice(deviceName, frequency, format, samples);
   }

   @NativeType("ALCdevice *")
   public static long alcCaptureOpenDevice(
      @Nullable @NativeType("ALCchar const *") CharSequence deviceName,
      @NativeType("ALCuint") int frequency,
      @NativeType("ALCenum") int format,
      @NativeType("ALCsizei") int samples
   ) {
      return ALC11.alcCaptureOpenDevice(deviceName, frequency, format, samples);
   }

   @NativeType("ALCboolean")
   public static boolean alcCaptureCloseDevice(@NativeType("ALCdevice *") long device) {
      return ALC11.alcCaptureCloseDevice(device);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureStart(@NativeType("ALCdevice *") long device) {
      ALC11.alcCaptureStart(device);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureStop(@NativeType("ALCdevice *") long device) {
      ALC11.alcCaptureStop(device);
   }

   public static void nalcCaptureSamples(long device, long buffer, int samples) {
      ALC11.nalcCaptureSamples(device, buffer, samples);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureSamples(@NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") ByteBuffer buffer, @NativeType("ALCsizei") int samples) {
      ALC11.alcCaptureSamples(device, buffer, samples);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureSamples(
      @NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") ShortBuffer buffer, @NativeType("ALCsizei") int samples
   ) {
      ALC11.alcCaptureSamples(device, buffer, samples);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureSamples(@NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") IntBuffer buffer, @NativeType("ALCsizei") int samples) {
      ALC11.alcCaptureSamples(device, buffer, samples);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureSamples(
      @NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") FloatBuffer buffer, @NativeType("ALCsizei") int samples
   ) {
      ALC11.alcCaptureSamples(device, buffer, samples);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureSamples(@NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") short[] buffer, @NativeType("ALCsizei") int samples) {
      ALC11.alcCaptureSamples(device, buffer, samples);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureSamples(@NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") int[] buffer, @NativeType("ALCsizei") int samples) {
      ALC11.alcCaptureSamples(device, buffer, samples);
   }

   @NativeType("ALCvoid")
   public static void alcCaptureSamples(@NativeType("ALCdevice *") long device, @NativeType("ALCvoid *") float[] buffer, @NativeType("ALCsizei") int samples) {
      ALC11.alcCaptureSamples(device, buffer, samples);
   }
}
