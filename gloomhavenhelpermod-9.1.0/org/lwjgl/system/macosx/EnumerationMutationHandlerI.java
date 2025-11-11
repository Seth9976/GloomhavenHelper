package org.lwjgl.system.macosx;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("EnumerationMutationHandler")
public interface EnumerationMutationHandlerI extends CallbackI.V {
   String SIGNATURE = "(p)v";

   @Override
   default String getSignature() {
      return "(p)v";
   }

   @Override
   default void callback(long args) {
      this.invoke(DynCallback.dcbArgPointer(args));
   }

   void invoke(@NativeType("id") long var1);
}
