package org.lwjgl.glfw;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("GLFWcharfun")
public interface GLFWCharCallbackI extends CallbackI.V {
   String SIGNATURE = "(pi)v";

   @Override
   default String getSignature() {
      return "(pi)v";
   }

   @Override
   default void callback(long args) {
      this.invoke(DynCallback.dcbArgPointer(args), DynCallback.dcbArgInt(args));
   }

   void invoke(@NativeType("GLFWwindow *") long var1, @NativeType("unsigned int") int var3);
}
