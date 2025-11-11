package org.lwjgl.system.macosx;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;

public class MacOSXLibraryBundle extends MacOSXLibrary {
   public MacOSXLibraryBundle(String name, long bundleRef) {
      super(name, bundleRef);
   }

   public static MacOSXLibraryBundle getWithIdentifier(String bundleID) {
      long filePath = 0L;

      MacOSXLibraryBundle var7;
      try (MemoryStack stack = MemoryStack.stackPush()) {
         filePath = CString2CFString(stack.ASCII(bundleID), 1536);
         long bundleRef = CoreFoundation.CFBundleGetBundleWithIdentifier(filePath);
         if (bundleRef == 0L) {
            throw new UnsatisfiedLinkError("Failed to retrieve bundle with identifier: " + bundleID);
         }

         CoreFoundation.CFRetain(bundleRef);
         var7 = new MacOSXLibraryBundle(bundleID, bundleRef);
      } finally {
         if (filePath != 0L) {
            CoreFoundation.CFRelease(filePath);
         }
      }

      return var7;
   }

   public static MacOSXLibraryBundle create(String path) {
      long filePath = 0L;
      long url = 0L;

      MacOSXLibraryBundle var9;
      try (MemoryStack stack = MemoryStack.stackPush()) {
         filePath = CString2CFString(stack.UTF8(path), 134217984);
         url = Checks.check(CoreFoundation.CFURLCreateWithFileSystemPath(0L, filePath, 0L, true));
         long bundleRef = CoreFoundation.CFBundleCreate(0L, url);
         if (bundleRef == 0L) {
            throw new UnsatisfiedLinkError("Failed to create bundle: " + path);
         }

         var9 = new MacOSXLibraryBundle(path, bundleRef);
      } finally {
         if (url != 0L) {
            CoreFoundation.CFRelease(url);
         }

         if (filePath != 0L) {
            CoreFoundation.CFRelease(filePath);
         }
      }

      return var9;
   }

   @Nullable
   @Override
   public String getPath() {
      return null;
   }

   @Override
   public long getFunctionAddress(ByteBuffer functionName) {
      long nameRef = CString2CFString(functionName, 1536);

      long var4;
      try {
         var4 = CoreFoundation.CFBundleGetFunctionPointerForName(this.address(), nameRef);
      } finally {
         CoreFoundation.CFRelease(nameRef);
      }

      return var4;
   }

   private static long CString2CFString(ByteBuffer name, int encoding) {
      return Checks.check(CoreFoundation.CFStringCreateWithCStringNoCopy(0L, name, encoding, CoreFoundation.kCFAllocatorNull));
   }

   @Override
   public void free() {
      CoreFoundation.CFRelease(this.address());
   }
}
