package org.lwjgl.system.dyncall;

import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

public class DynCall {
   public static final int DC_CALL_C_DEFAULT = 0;
   public static final int DC_CALL_C_ELLIPSIS = 100;
   public static final int DC_CALL_C_ELLIPSIS_VARARGS = 101;
   public static final int DC_CALL_C_X86_CDECL = 1;
   public static final int DC_CALL_C_X86_WIN32_STD = 2;
   public static final int DC_CALL_C_X86_WIN32_FAST_MS = 3;
   public static final int DC_CALL_C_X86_WIN32_FAST_GNU = 4;
   public static final int DC_CALL_C_X86_WIN32_THIS_MS = 5;
   public static final int DC_CALL_C_X86_WIN32_THIS_GNU = 6;
   public static final int DC_CALL_C_X64_WIN64 = 7;
   public static final int DC_CALL_C_X64_SYSV = 8;
   public static final int DC_CALL_C_PPC32_DARWIN = 9;
   public static final int DC_CALL_C_PPC32_OSX = 9;
   public static final int DC_CALL_C_ARM_ARM_EABI = 10;
   public static final int DC_CALL_C_ARM_THUMB_EABI = 11;
   public static final int DC_CALL_C_ARM_ARMHF = 30;
   public static final int DC_CALL_C_MIPS32_EABI = 12;
   public static final int DC_CALL_C_PPC32_SYSV = 13;
   public static final int DC_CALL_C_PPC32_LINUX = 13;
   public static final int DC_CALL_C_ARM_ARM = 14;
   public static final int DC_CALL_C_ARM_THUMB = 15;
   public static final int DC_CALL_C_MIPS32_O32 = 16;
   public static final int DC_CALL_C_MIPS64_N32 = 17;
   public static final int DC_CALL_C_MIPS64_N64 = 18;
   public static final int DC_CALL_C_X86_PLAN9 = 19;
   public static final int DC_CALL_C_SPARC32 = 20;
   public static final int DC_CALL_C_SPARC64 = 21;
   public static final int DC_CALL_C_ARM64 = 22;
   public static final int DC_CALL_C_PPC64 = 23;
   public static final int DC_CALL_C_PPC64_LINUX = 23;
   public static final int DC_CALL_SYS_DEFAULT = 200;
   public static final int DC_CALL_SYS_X86_INT80H_LINUX = 201;
   public static final int DC_CALL_SYS_X86_INT80H_BSD = 202;
   public static final int DC_CALL_SYS_PPC32 = 210;
   public static final int DC_CALL_SYS_PPC64 = 211;
   public static final int DC_ERROR_NONE = 0;
   public static final int DC_ERROR_UNSUPPORTED_MODE = -1;
   public static final int DC_TRUE = 1;
   public static final int DC_FALSE = 0;
   public static final char DC_SIGCHAR_VOID = 'v';
   public static final char DC_SIGCHAR_BOOL = 'B';
   public static final char DC_SIGCHAR_CHAR = 'c';
   public static final char DC_SIGCHAR_UCHAR = 'C';
   public static final char DC_SIGCHAR_SHORT = 's';
   public static final char DC_SIGCHAR_USHORT = 'S';
   public static final char DC_SIGCHAR_INT = 'i';
   public static final char DC_SIGCHAR_UINT = 'I';
   public static final char DC_SIGCHAR_LONG = 'j';
   public static final char DC_SIGCHAR_ULONG = 'J';
   public static final char DC_SIGCHAR_LONGLONG = 'l';
   public static final char DC_SIGCHAR_ULONGLONG = 'L';
   public static final char DC_SIGCHAR_FLOAT = 'f';
   public static final char DC_SIGCHAR_DOUBLE = 'd';
   public static final char DC_SIGCHAR_POINTER = 'p';
   public static final char DC_SIGCHAR_STRING = 'Z';
   public static final char DC_SIGCHAR_STRUCT = 'T';
   public static final char DC_SIGCHAR_ENDARG = ')';

   protected DynCall() {
      throw new UnsupportedOperationException();
   }

   @NativeType("DCCallVM *")
   public static native long dcNewCallVM(@NativeType("DCsize") long var0);

   public static native void ndcFree(long var0);

   public static void dcFree(@NativeType("DCCallVM *") long vm) {
      if (Checks.CHECKS) {
         Checks.check(vm);
      }

      ndcFree(vm);
   }

   public static native void ndcReset(long var0);

   public static void dcReset(@NativeType("DCCallVM *") long vm) {
      if (Checks.CHECKS) {
         Checks.check(vm);
      }

      ndcReset(vm);
   }

   public static native void ndcMode(long var0, int var2);

   public static void dcMode(@NativeType("DCCallVM *") long vm, @NativeType("DCint") int mode) {
      if (Checks.CHECKS) {
         Checks.check(vm);
      }

      ndcMode(vm, mode);
   }

   public static native void ndcArgBool(long var0, int var2);

   public static void dcArgBool(@NativeType("DCCallVM *") long vm, @NativeType("DCbool") boolean value) {
      if (Checks.CHECKS) {
         Checks.check(vm);
      }

      ndcArgBool(vm, value ? 1 : 0);
   }

   public static native void ndcArgChar(long var0, byte var2);

   public static void dcArgChar(@NativeType("DCCallVM *") long vm, @NativeType("DCchar") byte value) {
      if (Checks.CHECKS) {
         Checks.check(vm);
      }

      ndcArgChar(vm, value);
   }

   public static native void ndcArgShort(long var0, short var2);

   public static void dcArgShort(@NativeType("DCCallVM *") long vm, @NativeType("DCshort") short value) {
      if (Checks.CHECKS) {
         Checks.check(vm);
      }

      ndcArgShort(vm, value);
   }

   public static native void ndcArgInt(long var0, int var2);

   public static void dcArgInt(@NativeType("DCCallVM *") long vm, @NativeType("DCint") int value) {
      if (Checks.CHECKS) {
         Checks.check(vm);
      }

      ndcArgInt(vm, value);
   }

   public static native void ndcArgLong(long var0, long var2);

   public static void dcArgLong(@NativeType("DCCallVM *") long vm, @NativeType("DClong") long value) {
      if (Checks.CHECKS) {
         Checks.check(vm);
      }

      ndcArgLong(vm, value);
   }

   public static native void ndcArgLongLong(long var0, long var2);

   public static void dcArgLongLong(@NativeType("DCCallVM *") long vm, @NativeType("DClonglong") long value) {
      if (Checks.CHECKS) {
         Checks.check(vm);
      }

      ndcArgLongLong(vm, value);
   }

   public static native void ndcArgFloat(long var0, float var2);

   public static void dcArgFloat(@NativeType("DCCallVM *") long vm, @NativeType("DCfloat") float value) {
      if (Checks.CHECKS) {
         Checks.check(vm);
      }

      ndcArgFloat(vm, value);
   }

   public static native void ndcArgDouble(long var0, double var2);

   public static void dcArgDouble(@NativeType("DCCallVM *") long vm, @NativeType("DCdouble") double value) {
      if (Checks.CHECKS) {
         Checks.check(vm);
      }

      ndcArgDouble(vm, value);
   }

   public static native void ndcArgPointer(long var0, long var2);

   public static void dcArgPointer(@NativeType("DCCallVM *") long vm, @NativeType("DCpointer") long value) {
      if (Checks.CHECKS) {
         Checks.check(vm);
      }

      ndcArgPointer(vm, value);
   }

   public static native void ndcArgStruct(long var0, long var2, long var4);

   public static void dcArgStruct(@NativeType("DCCallVM *") long vm, @NativeType("DCstruct *") long s, @NativeType("DCpointer") long value) {
      if (Checks.CHECKS) {
         Checks.check(vm);
         Checks.check(s);
         Checks.check(value);
      }

      ndcArgStruct(vm, s, value);
   }

   public static native void ndcCallVoid(long var0, long var2);

   @NativeType("DCvoid")
   public static void dcCallVoid(@NativeType("DCCallVM *") long vm, @NativeType("DCpointer") long funcptr) {
      if (Checks.CHECKS) {
         Checks.check(vm);
         Checks.check(funcptr);
      }

      ndcCallVoid(vm, funcptr);
   }

   public static native int ndcCallBool(long var0, long var2);

   @NativeType("DCbool")
   public static boolean dcCallBool(@NativeType("DCCallVM *") long vm, @NativeType("DCpointer") long funcptr) {
      if (Checks.CHECKS) {
         Checks.check(vm);
         Checks.check(funcptr);
      }

      return ndcCallBool(vm, funcptr) != 0;
   }

   public static native byte ndcCallChar(long var0, long var2);

   @NativeType("DCchar")
   public static byte dcCallChar(@NativeType("DCCallVM *") long vm, @NativeType("DCpointer") long funcptr) {
      if (Checks.CHECKS) {
         Checks.check(vm);
         Checks.check(funcptr);
      }

      return ndcCallChar(vm, funcptr);
   }

   public static native short ndcCallShort(long var0, long var2);

   @NativeType("DCshort")
   public static short dcCallShort(@NativeType("DCCallVM *") long vm, @NativeType("DCpointer") long funcptr) {
      if (Checks.CHECKS) {
         Checks.check(vm);
         Checks.check(funcptr);
      }

      return ndcCallShort(vm, funcptr);
   }

   public static native int ndcCallInt(long var0, long var2);

   @NativeType("DCint")
   public static int dcCallInt(@NativeType("DCCallVM *") long vm, @NativeType("DCpointer") long funcptr) {
      if (Checks.CHECKS) {
         Checks.check(vm);
         Checks.check(funcptr);
      }

      return ndcCallInt(vm, funcptr);
   }

   public static native long ndcCallLong(long var0, long var2);

   @NativeType("DClong")
   public static long dcCallLong(@NativeType("DCCallVM *") long vm, @NativeType("DCpointer") long funcptr) {
      if (Checks.CHECKS) {
         Checks.check(vm);
         Checks.check(funcptr);
      }

      return ndcCallLong(vm, funcptr);
   }

   public static native long ndcCallLongLong(long var0, long var2);

   @NativeType("DClonglong")
   public static long dcCallLongLong(@NativeType("DCCallVM *") long vm, @NativeType("DCpointer") long funcptr) {
      if (Checks.CHECKS) {
         Checks.check(vm);
         Checks.check(funcptr);
      }

      return ndcCallLongLong(vm, funcptr);
   }

   public static native float ndcCallFloat(long var0, long var2);

   @NativeType("DCfloat")
   public static float dcCallFloat(@NativeType("DCCallVM *") long vm, @NativeType("DCpointer") long funcptr) {
      if (Checks.CHECKS) {
         Checks.check(vm);
         Checks.check(funcptr);
      }

      return ndcCallFloat(vm, funcptr);
   }

   public static native double ndcCallDouble(long var0, long var2);

   @NativeType("DCdouble")
   public static double dcCallDouble(@NativeType("DCCallVM *") long vm, @NativeType("DCpointer") long funcptr) {
      if (Checks.CHECKS) {
         Checks.check(vm);
         Checks.check(funcptr);
      }

      return ndcCallDouble(vm, funcptr);
   }

   public static native long ndcCallPointer(long var0, long var2);

   @NativeType("DCpointer")
   public static long dcCallPointer(@NativeType("DCCallVM *") long vm, @NativeType("DCpointer") long funcptr) {
      if (Checks.CHECKS) {
         Checks.check(vm);
         Checks.check(funcptr);
      }

      return ndcCallPointer(vm, funcptr);
   }

   public static native void ndcCallStruct(long var0, long var2, long var4, long var6);

   public static void dcCallStruct(
      @NativeType("DCCallVM *") long vm, @NativeType("DCpointer") long funcptr, @NativeType("DCstruct *") long s, @NativeType("DCpointer") long returnValue
   ) {
      if (Checks.CHECKS) {
         Checks.check(vm);
         Checks.check(funcptr);
         Checks.check(s);
         Checks.check(returnValue);
      }

      ndcCallStruct(vm, funcptr, s, returnValue);
   }

   public static native int ndcGetError(long var0);

   @NativeType("DCint")
   public static int dcGetError(@NativeType("DCCallVM *") long vm) {
      if (Checks.CHECKS) {
         Checks.check(vm);
      }

      return ndcGetError(vm);
   }

   @NativeType("DCstruct *")
   public static native long dcNewStruct(@NativeType("DCsize") long var0, @NativeType("DCint") int var2);

   public static native void ndcStructField(long var0, int var2, int var3, long var4);

   public static void dcStructField(
      @NativeType("DCstruct *") long s, @NativeType("DCint") int type, @NativeType("DCint") int alignment, @NativeType("DCsize") long arrayLength
   ) {
      if (Checks.CHECKS) {
         Checks.check(s);
      }

      ndcStructField(s, type, alignment, arrayLength);
   }

   public static native void ndcSubStruct(long var0, long var2, int var4, long var5);

   public static void dcSubStruct(
      @NativeType("DCstruct *") long s, @NativeType("DCsize") long fieldCount, @NativeType("DCint") int alignment, @NativeType("DCsize") long arrayLength
   ) {
      if (Checks.CHECKS) {
         Checks.check(s);
      }

      ndcSubStruct(s, fieldCount, alignment, arrayLength);
   }

   public static native void ndcCloseStruct(long var0);

   public static void dcCloseStruct(@NativeType("DCstruct *") long s) {
      if (Checks.CHECKS) {
         Checks.check(s);
      }

      ndcCloseStruct(s);
   }

   public static native long ndcStructSize(long var0);

   @NativeType("DCsize")
   public static long dcStructSize(@NativeType("DCstruct *") long s) {
      if (Checks.CHECKS) {
         Checks.check(s);
      }

      return ndcStructSize(s);
   }

   public static native long ndcStructAlignment(long var0);

   @NativeType("DCsize")
   public static long dcStructAlignment(@NativeType("DCstruct *") long s) {
      if (Checks.CHECKS) {
         Checks.check(s);
      }

      return ndcStructAlignment(s);
   }

   public static native void ndcFreeStruct(long var0);

   public static void dcFreeStruct(@NativeType("DCstruct *") long s) {
      if (Checks.CHECKS) {
         Checks.check(s);
      }

      ndcFreeStruct(s);
   }

   public static native long ndcDefineStruct(long var0);

   @NativeType("DCstruct *")
   public static long dcDefineStruct(@NativeType("char const *") ByteBuffer signature) {
      if (Checks.CHECKS) {
         Checks.checkNT1(signature);
      }

      return ndcDefineStruct(MemoryUtil.memAddress(signature));
   }

   @NativeType("DCstruct *")
   public static long dcDefineStruct(@NativeType("char const *") CharSequence signature) {
      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      long var5;
      try {
         stack.nASCII(signature, true);
         long signatureEncoded = stack.getPointerAddress();
         var5 = ndcDefineStruct(signatureEncoded);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var5;
   }

   static {
      Library.initialize();
   }
}
