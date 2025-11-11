package org.lwjgl.system;

import java.nio.ByteBuffer;

@FunctionalInterface
public interface FunctionProvider {
   default long getFunctionAddress(CharSequence functionName) {
      long var4;
      try (MemoryStack stack = MemoryStack.stackPush()) {
         var4 = this.getFunctionAddress(stack.ASCII(functionName));
      }

      return var4;
   }

   long getFunctionAddress(ByteBuffer var1);
}
