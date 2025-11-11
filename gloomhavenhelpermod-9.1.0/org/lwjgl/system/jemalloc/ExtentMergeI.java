package org.lwjgl.system.jemalloc;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("extent_merge_t")
public interface ExtentMergeI extends CallbackI.Z {
   String SIGNATURE = "(pppppBi)B";

   @Override
   default String getSignature() {
      return "(pppppBi)B";
   }

   @Override
   default boolean callback(long args) {
      return this.invoke(
         DynCallback.dcbArgPointer(args),
         DynCallback.dcbArgPointer(args),
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
      @NativeType("void *") long var7,
      @NativeType("size_t") long var9,
      @NativeType("bool") boolean var11,
      @NativeType("unsigned int") int var12
   );
}
