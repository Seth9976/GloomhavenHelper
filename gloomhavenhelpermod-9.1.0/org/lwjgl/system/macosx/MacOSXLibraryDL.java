package org.lwjgl.system.macosx;

import java.nio.ByteBuffer;
import org.lwjgl.system.MemoryStack;

public class MacOSXLibraryDL extends MacOSXLibrary {
   public MacOSXLibraryDL(String name) {
      this(name, loadLibrary(name));
   }

   public MacOSXLibraryDL(String name, long handle) {
      super(name, handle);
   }

   private static long loadLibrary(String name) {
      long handle;
      try (MemoryStack stack = MemoryStack.stackPush()) {
         handle = DynamicLinkLoader.dlopen(stack.ASCII(name), 5);
      }

      if (handle == 0L) {
         throw new UnsatisfiedLinkError("Failed to dynamically load library: " + name + "(error = " + DynamicLinkLoader.dlerror() + ")");
      } else {
         return handle;
      }
   }

   @Override
   public long getFunctionAddress(ByteBuffer functionName) {
      return DynamicLinkLoader.dlsym(this.address(), functionName);
   }

   @Override
   public void free() {
      DynamicLinkLoader.dlclose(this.address());
   }
}
