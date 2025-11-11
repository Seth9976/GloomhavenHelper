package org.lwjgl.system;

import java.nio.ByteBuffer;

public interface FunctionProviderLocal extends FunctionProvider {
   default long getFunctionAddress(long handle, CharSequence functionName) {
      long var6;
      try (MemoryStack stack = MemoryStack.stackPush()) {
         var6 = this.getFunctionAddress(handle, stack.ASCII(functionName));
      }

      return var6;
   }

   long getFunctionAddress(long var1, ByteBuffer var3);
}
