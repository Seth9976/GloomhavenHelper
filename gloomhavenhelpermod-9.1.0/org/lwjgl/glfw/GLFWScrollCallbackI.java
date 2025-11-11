package org.lwjgl.glfw;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("GLFWscrollfun")
public interface GLFWScrollCallbackI extends CallbackI.V {
   String SIGNATURE = "(pdd)v";

   @Override
   default String getSignature() {
      return "(pdd)v";
   }

   @Override
   default void callback(long args) {
      this.invoke(DynCallback.dcbArgPointer(args), DynCallback.dcbArgDouble(args), DynCallback.dcbArgDouble(args));
   }

   void invoke(@NativeType("GLFWwindow *") long var1, double var3, double var5);
}
