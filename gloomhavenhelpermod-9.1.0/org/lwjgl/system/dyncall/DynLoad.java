package org.lwjgl.system.dyncall;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import org.lwjgl.system.Checks;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

public class DynLoad {
   protected DynLoad() {
      throw new UnsupportedOperationException();
   }

   public static native long ndlLoadLibrary(long var0);

   @NativeType("DLLib *")
   public static long dlLoadLibrary(@NativeType("char const *") ByteBuffer libpath) {
      if (Checks.CHECKS) {
         Checks.checkNT1(libpath);
      }

      return ndlLoadLibrary(MemoryUtil.memAddress(libpath));
   }

   @NativeType("DLLib *")
   public static long dlLoadLibrary(@NativeType("char const *") CharSequence libpath) {
      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      long var5;
      try {
         stack.nASCII(libpath, true);
         long libpathEncoded = stack.getPointerAddress();
         var5 = ndlLoadLibrary(libpathEncoded);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var5;
   }

   public static native void ndlFreeLibrary(long var0);

   public static void dlFreeLibrary(@NativeType("DLLib *") long pLib) {
      if (Checks.CHECKS) {
         Checks.check(pLib);
      }

      ndlFreeLibrary(pLib);
   }

   public static native long ndlFindSymbol(long var0, long var2);

   @NativeType("void *")
   public static long dlFindSymbol(@NativeType("DLLib *") long pLib, @NativeType("char const *") ByteBuffer pSymbolName) {
      if (Checks.CHECKS) {
         Checks.check(pLib);
         Checks.checkNT1(pSymbolName);
      }

      return ndlFindSymbol(pLib, MemoryUtil.memAddress(pSymbolName));
   }

   @NativeType("void *")
   public static long dlFindSymbol(@NativeType("DLLib *") long pLib, @NativeType("char const *") CharSequence pSymbolName) {
      if (Checks.CHECKS) {
         Checks.check(pLib);
      }

      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      long var7;
      try {
         stack.nASCII(pSymbolName, true);
         long pSymbolNameEncoded = stack.getPointerAddress();
         var7 = ndlFindSymbol(pLib, pSymbolNameEncoded);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var7;
   }

   public static native int ndlGetLibraryPath(long var0, long var2, int var4);

   public static int dlGetLibraryPath(@NativeType("DLLib *") long pLib, @NativeType("char *") ByteBuffer sOut) {
      if (Checks.CHECKS) {
         Checks.check(pLib);
      }

      return ndlGetLibraryPath(pLib, MemoryUtil.memAddress(sOut), sOut.remaining());
   }

   @NativeType("int")
   public static String dlGetLibraryPath(@NativeType("DLLib *") long pLib, int bufSize) {
      if (Checks.CHECKS) {
         Checks.check(pLib);
      }

      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      String var7;
      try {
         ByteBuffer sOut = stack.malloc(bufSize);
         int __result = ndlGetLibraryPath(pLib, MemoryUtil.memAddress(sOut), bufSize);
         var7 = MemoryUtil.memASCII(sOut, __result - 1);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var7;
   }

   public static native long ndlSymsInit(long var0);

   @NativeType("DLSyms *")
   public static long dlSymsInit(@NativeType("char const *") ByteBuffer libPath) {
      if (Checks.CHECKS) {
         Checks.checkNT1(libPath);
      }

      return ndlSymsInit(MemoryUtil.memAddress(libPath));
   }

   @NativeType("DLSyms *")
   public static long dlSymsInit(@NativeType("char const *") CharSequence libPath) {
      MemoryStack stack = MemoryStack.stackGet();
      int stackPointer = stack.getPointer();

      long var5;
      try {
         stack.nASCII(libPath, true);
         long libPathEncoded = stack.getPointerAddress();
         var5 = ndlSymsInit(libPathEncoded);
      } finally {
         stack.setPointer(stackPointer);
      }

      return var5;
   }

   public static native void ndlSymsCleanup(long var0);

   public static void dlSymsCleanup(@NativeType("DLSyms *") long pSyms) {
      if (Checks.CHECKS) {
         Checks.check(pSyms);
      }

      ndlSymsCleanup(pSyms);
   }

   public static native int ndlSymsCount(long var0);

   public static int dlSymsCount(@NativeType("DLSyms *") long pSyms) {
      if (Checks.CHECKS) {
         Checks.check(pSyms);
      }

      return ndlSymsCount(pSyms);
   }

   public static native long ndlSymsName(long var0, int var2);

   @Nullable
   @NativeType("char const *")
   public static String dlSymsName(@NativeType("DLSyms *") long pSyms, int index) {
      if (Checks.CHECKS) {
         Checks.check(pSyms);
      }

      long __result = ndlSymsName(pSyms, index);
      return MemoryUtil.memASCIISafe(__result);
   }

   public static native long ndlSymsNameFromValue(long var0, long var2);

   @Nullable
   @NativeType("char const *")
   public static String dlSymsNameFromValue(@NativeType("DLSyms *") long pSyms, @NativeType("void *") long value) {
      if (Checks.CHECKS) {
         Checks.check(pSyms);
         Checks.check(value);
      }

      long __result = ndlSymsNameFromValue(pSyms, value);
      return MemoryUtil.memASCIISafe(__result);
   }

   static {
      Library.initialize();
   }
}
