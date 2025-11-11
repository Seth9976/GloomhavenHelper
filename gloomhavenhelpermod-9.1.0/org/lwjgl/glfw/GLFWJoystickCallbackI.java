package org.lwjgl.glfw;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("GLFWjoystickfun")
public interface GLFWJoystickCallbackI extends CallbackI.V {
   String SIGNATURE = "(ii)v";

   @Override
   default String getSignature() {
      return "(ii)v";
   }

   @Override
   default void callback(long args) {
      this.invoke(DynCallback.dcbArgInt(args), DynCallback.dcbArgInt(args));
   }

   void invoke(int var1, int var2);
}
