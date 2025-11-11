package org.lwjgl.system.windows;

import org.lwjgl.system.Callback;
import org.lwjgl.system.CallbackI;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.dyncall.DynCallback;

@FunctionalInterface
@NativeType("WNDPROC")
public interface WindowProcI extends CallbackI.P {
   String SIGNATURE = Callback.__stdcall("(pipp)p");

   @Override
   default String getSignature() {
      return SIGNATURE;
   }

   @Override
   default long callback(long args) {
      return this.invoke(DynCallback.dcbArgPointer(args), DynCallback.dcbArgInt(args), DynCallback.dcbArgPointer(args), DynCallback.dcbArgPointer(args));
   }

   @NativeType("LRESULT")
   long invoke(@NativeType("HWND") long var1, @NativeType("UINT") int var3, @NativeType("WPARAM") long var4, @NativeType("LPARAM") long var6);
}
