package org.lwjgl.system.macosx;

import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("CGEventRef (*) (CGEventTapProxy, CGEventType, CGEventRef, void *)")
public interface CGEventTapCallBackI extends CallbackI.P {
   String SIGNATURE = "(pipp)p";

   @Override
   default String getSignature() {
      return "(pipp)p";
   }

   @Override
   default long callback(long args) {
      return this.invoke(DynCallback.dcbArgPointer(args), DynCallback.dcbArgInt(args), DynCallback.dcbArgPointer(args), DynCallback.dcbArgPointer(args));
   }

   @NativeType("CGEventRef")
   long invoke(
      @NativeType("CGEventTapProxy") long var1, @NativeType("CGEventType") int var3, @NativeType("CGEventRef") long var4, @NativeType("void *") long var6
   );
}
