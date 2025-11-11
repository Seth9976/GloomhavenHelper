package org.lwjgl.glfw;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("GLFWmousebuttonfun")
public interface GLFWMouseButtonCallbackI extends CallbackI.V {
   String SIGNATURE = "(piii)v";

   @Override
   default String getSignature() {
      return "(piii)v";
   }

   @Override
   default void callback(long args) {
      this.invoke(DynCallback.dcbArgPointer(args), DynCallback.dcbArgInt(args), DynCallback.dcbArgInt(args), DynCallback.dcbArgInt(args));
   }

   void invoke(@NativeType("GLFWwindow *") long var1, int var3, int var4, int var5);
}
