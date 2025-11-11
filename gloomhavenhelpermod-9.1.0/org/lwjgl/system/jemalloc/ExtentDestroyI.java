package org.lwjgl.system.jemalloc;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("extent_destroy_t")
public interface ExtentDestroyI extends CallbackI.Z {
   String SIGNATURE = "(pppBi)B";

   @Override
   default String getSignature() {
      return "(pppBi)B";
   }

   @Override
   default boolean callback(long args) {
      return this.invoke(
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgBool(args),
         DynCallback.dcbArgInt(args)
      );
   }

   @NativeType("bool")
   boolean invoke(
      @NativeType("extent_hooks_t *") long var1,
      @NativeType("void *") long var3,
      @NativeType("size_t") long var5,
      @NativeType("bool") boolean var7,
      @NativeType("unsigned int") int var8
   );
}
