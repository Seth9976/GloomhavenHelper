package org.lwjgl.system.jemalloc;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("void (*) (void *, char const *)")
public interface MallocMessageCallbackI extends CallbackI.V {
   String SIGNATURE = "(pp)v";

   @Override
   default String getSignature() {
      return "(pp)v";
   }

   @Override
   default void callback(long args) {
      this.invoke(DynCallback.dcbArgPointer(args), DynCallback.dcbArgPointer(args));
   }

   void invoke(@NativeType("void *") long var1, @NativeType("char const *") long var3);
}
