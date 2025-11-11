package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import javax.annotation.Nullable;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

public class ALC10 {
   public static final int ALC_INVALID = -1;
   public static final int ALC_FALSE = 0;
   public static final int ALC_TRUE = 1;
   public static final int ALC_FREQUENCY = 4103;
   public static final int ALC_REFRESH = 4104;
   public static final int ALC_SYNC = 4105;
   public static final int ALC_NO_ERROR = 0;
   public static final int ALC_INVALID_DEVICE = 40961;
   public static final int ALC_INVALID_CONTEXT = 40962;
   public static final int ALC_INVALID_ENUM = 40963;
   public static final int ALC_INVALID_VALUE = 40964;
   public static final int ALC_OUT_OF_MEMORY = 40965;
   public static final int ALC_DEFAULT_DEVICE_SPECIFIER = 4100;
   public static final int ALC_DEVICE_SPECIFIER = 4101;
   public static final int ALC_EXTENSIONS = 4102;
   public static final int ALC_MAJOR_VERSION = 4096;
   public static final int ALC_MINOR_VERSION = 4097;
   public static final int ALC_ATTRIBUTES_SIZE = 4098;
   public static final int ALC_ALL_ATTRIBUTES = 4099;

   protected ALC10() {
      throw new UnsupportedOperationException();
   }

   static boolean isAvailable(ALCCapabilities caps) {
      return Checks.checkFunctions(
         caps.alcOpenDevice,
         caps.alcCloseDevice,
         caps.alcCreateContext,
         caps.alcMakeContextCurrent,
         caps.alcProcessContext,
         caps.alcSuspendContext,
         caps.alcDestroyContext,
         caps.alcGetCurrentContext,
         caps.alcGetContextsDevice,
         caps.alcIsExtensionPresent,
         caps.alcGetProcAddress,
         caps.alcGetEnumValue,
         caps.alcGetError,
         caps.alcGetString,
         caps.alcGetIntegerv
      );
   }

   public static long nalcOpenDevice(long deviceSpecifier) {
      long __functionAddress = ALC.getICD().alcOpenDevice;
      return JNI.invokePP(deviceSpecifier, __functionAddress);
   }

   @NativeType("ALCdevice *")
   public static long alcOpenDevice(@Nullable @NativeType("ALCchar const *") ByteBuffer deviceSpecifier) {
      if (Checks.CHECKS) {
         Checks.checkNT1Safe(deviceSpecifier);
      }

      return nalcOpenDevice(MemoryUtil.memAddressSafe(deviceSpecifier));
   }

   @NativeType("ALCdevice *")
   public static long alcOpenDevice(@Nullable @NativeType("ALCchar const *") CharSequence deviceSpecifier) {
      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      long var5;
      try {
         stack.nUTF8Safe(deviceSpecifier, true);
         long deviceSpecifierEncoded = deviceSpecifier == null ? 0L : stack.getPointerAddress();
         var5 = nalcOpenDevice(deviceSpecifierEncoded);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var5;
   }

   @NativeType("ALCboolean")
   public static boolean alcCloseDevice(@NativeType("ALCdevice const *") long deviceHandle) {
      long __functionAddress = ALC.getICD().alcCloseDevice;
      if (Checks.CHECKS) {
         Checks.check(deviceHandle);
      }

      return JNI.invokePZ(deviceHandle, __functionAddress);
   }

   public static long nalcCreateContext(long deviceHandle, long attrList) {
      long __functionAddress = ALC.getICD().alcCreateContext;
      if (Checks.CHECKS) {
         Checks.check(deviceHandle);
      }

      return JNI.invokePPP(deviceHandle, attrList, __functionAddress);
   }

   @NativeType("ALCcontext *")
   public static long alcCreateContext(@NativeType("ALCdevice const *") long deviceHandle, @Nullable @NativeType("ALCint const *") IntBuffer attrList) {
      if (Checks.CHECKS) {
         Checks.checkNTSafe(attrList);
      }

      return nalcCreateContext(deviceHandle, MemoryUtil.memAddressSafe(attrList));
   }

   @NativeType("ALCboolean")
   public static boolean alcMakeContextCurrent(@NativeType("ALCcontext *") long context) {
      long __functionAddress = ALC.getICD().alcMakeContextCurrent;
      return JNI.invokePZ(context, __functionAddress);
   }

   @NativeType("ALCvoid")
   public static void alcProcessContext(@NativeType("ALCcontext *") long context) {
      long __functionAddress = ALC.getICD().alcProcessContext;
      if (Checks.CHECKS) {
         Checks.check(context);
      }

      JNI.invokePV(context, __functionAddress);
   }

   @NativeType("ALCvoid")
   public static void alcSuspendContext(@NativeType("ALCcontext *") long context) {
      long __functionAddress = ALC.getICD().alcSuspendContext;
      if (Checks.CHECKS) {
         Checks.check(context);
      }

      JNI.invokePV(context, __functionAddress);
   }

   @NativeType("ALCvoid")
   public static void alcDestroyContext(@NativeType("ALCcontext *") long context) {
      long __functionAddress = ALC.getICD().alcDestroyContext;
      if (Checks.CHECKS) {
         Checks.check(context);
      }

      JNI.invokePV(context, __functionAddress);
   }

   @NativeType("ALCcontext *")
   public static long alcGetCurrentContext() {
      long __functionAddress = ALC.getICD().alcGetCurrentContext;
      return JNI.invokeP(__functionAddress);
   }

   @NativeType("ALCdevice *")
   public static long alcGetContextsDevice(@NativeType("ALCcontext *") long context) {
      long __functionAddress = ALC.getICD().alcGetContextsDevice;
      if (Checks.CHECKS) {
         Checks.check(context);
      }

      return JNI.invokePP(context, __functionAddress);
   }

   public static boolean nalcIsExtensionPresent(long deviceHandle, long extName) {
      long __functionAddress = ALC.getICD().alcIsExtensionPresent;
      return JNI.invokePPZ(deviceHandle, extName, __functionAddress);
   }

   @NativeType("ALCboolean")
   public static boolean alcIsExtensionPresent(@NativeType("ALCdevice const *") long deviceHandle, @NativeType("ALCchar const *") ByteBuffer extName) {
      if (Checks.CHECKS) {
         Checks.checkNT1(extName);
      }

      return nalcIsExtensionPresent(deviceHandle, MemoryUtil.memAddress(extName));
   }

   @NativeType("ALCboolean")
   public static boolean alcIsExtensionPresent(@NativeType("ALCdevice const *") long deviceHandle, @NativeType("ALCchar const *") CharSequence extName) {
      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      boolean var7;
      try {
         stack.nASCII(extName, true);
         long extNameEncoded = stack.getPointerAddress();
         var7 = nalcIsExtensionPresent(deviceHandle, extNameEncoded);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var7;
   }

   public static long nalcGetProcAddress(long deviceHandle, long funcName) {
      long __functionAddress = ALC.getICD().alcGetProcAddress;
      return JNI.invokePPP(deviceHandle, funcName, __functionAddress);
   }

   @NativeType("void *")
   public static long alcGetProcAddress(@NativeType("ALCdevice const *") long deviceHandle, @NativeType("ALchar const *") ByteBuffer funcName) {
      if (Checks.CHECKS) {
         Checks.checkNT1(funcName);
      }

      return nalcGetProcAddress(deviceHandle, MemoryUtil.memAddress(funcName));
   }

   @NativeType("void *")
   public static long alcGetProcAddress(@NativeType("ALCdevice const *") long deviceHandle, @NativeType("ALchar const *") CharSequence funcName) {
      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      long var7;
      try {
         stack.nASCII(funcName, true);
         long funcNameEncoded = stack.getPointerAddress();
         var7 = nalcGetProcAddress(deviceHandle, funcNameEncoded);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var7;
   }

   public static int nalcGetEnumValue(long deviceHandle, long enumName) {
      long __functionAddress = ALC.getICD().alcGetEnumValue;
      return JNI.invokePPI(deviceHandle, enumName, __functionAddress);
   }

   @NativeType("ALCenum")
   public static int alcGetEnumValue(@NativeType("ALCdevice const *") long deviceHandle, @NativeType("ALCchar const *") ByteBuffer enumName) {
      if (Checks.CHECKS) {
         Checks.checkNT1(enumName);
      }

      return nalcGetEnumValue(deviceHandle, MemoryUtil.memAddress(enumName));
   }

   @NativeType("ALCenum")
   public static int alcGetEnumValue(@NativeType("ALCdevice const *") long deviceHandle, @NativeType("ALCchar const *") CharSequence enumName) {
      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      int var7;
      try {
         stack.nASCII(enumName, true);
         long enumNameEncoded = stack.getPointerAddress();
         var7 = nalcGetEnumValue(deviceHandle, enumNameEncoded);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var7;
   }

   @NativeType("ALCenum")
   public static int alcGetError(@NativeType("ALCdevice *") long deviceHandle) {
      long __functionAddress = ALC.getICD().alcGetError;
      return JNI.invokePI(deviceHandle, __functionAddress);
   }

   public static long nalcGetString(long deviceHandle, int token) {
      long __functionAddress = ALC.getICD().alcGetString;
      return JNI.invokePP(deviceHandle, token, __functionAddress);
   }

   @Nullable
   @NativeType("ALCchar const *")
   public static String alcGetString(@NativeType("ALCdevice *") long deviceHandle, @NativeType("ALCenum") int token) {
      long __result = nalcGetString(deviceHandle, token);
      return MemoryUtil.memUTF8Safe(__result);
   }

   public static void nalcGetIntegerv(long deviceHandle, int token, int size, long dest) {
      long __functionAddress = ALC.getICD().alcGetIntegerv;
      JNI.invokePPV(deviceHandle, token, size, dest, __functionAddress);
   }

   @NativeType("ALCvoid")
   public static void alcGetIntegerv(@NativeType("ALCdevice *") long deviceHandle, @NativeType("ALCenum") int token, @NativeType("ALCint *") IntBuffer dest) {
      nalcGetIntegerv(deviceHandle, token, dest.remaining(), MemoryUtil.memAddress(dest));
   }

   @NativeType("ALCvoid")
   public static int alcGetInteger(@NativeType("ALCdevice *") long deviceHandle, @NativeType("ALCenum") int token) {
      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      int var6;
      try {
         IntBuffer dest = stack.callocInt(1);
         nalcGetIntegerv(deviceHandle, token, 1, MemoryUtil.memAddress(dest));
         var6 = dest.get(0);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var6;
   }

   @NativeType("ALCcontext *")
   public static long alcCreateContext(@NativeType("ALCdevice const *") long deviceHandle, @Nullable @NativeType("ALCint const *") int[] attrList) {
      long __functionAddress = ALC.getICD().alcCreateContext;
      if (Checks.CHECKS) {
         Checks.check(deviceHandle);
         Checks.checkNTSafe(attrList);
      }

      return JNI.invokePPP(deviceHandle, attrList, __functionAddress);
   }

   @NativeType("ALCvoid")
   public static void alcGetIntegerv(@NativeType("ALCdevice *") long deviceHandle, @NativeType("ALCenum") int token, @NativeType("ALCint *") int[] dest) {
      long __functionAddress = ALC.getICD().alcGetIntegerv;
      JNI.invokePPV(deviceHandle, token, dest.length, dest, __functionAddress);
   }
}
