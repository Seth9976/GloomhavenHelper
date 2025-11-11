package org.lwjgl.glfw;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("GLFWwindowcontentscalefun")
public interface GLFWWindowContentScaleCallbackI extends CallbackI.V {
   String SIGNATURE = "(pff)v";

   @Override
   default String getSignature() {
      return "(pff)v";
   }

   @Override
   default void callback(long args) {
      this.invoke(DynCallback.dcbArgPointer(args), DynCallback.dcbArgFloat(args), DynCallback.dcbArgFloat(args));
   }

   void invoke(@NativeType("GLFWwindow *") long var1, float var3, float var4);
}
