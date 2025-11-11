package org.lwjgl.system.windows;

import javax.annotation.Nullable;
import org.lwjgl.system.Callback;

public abstract class WindowProc extends Callback implements WindowProcI {
   public static WindowProc create(long functionPointer) {
      WindowProcI instance = (WindowProcI)Callback.get(functionPointer);
      return (WindowProc)(instance instanceof WindowProc ? (WindowProc)instance : new WindowProc.Container(functionPointer, instance));
   }

   @Nullable
   public static WindowProc createSafe(long functionPointer) {
      return functionPointer == 0L ? null : create(functionPointer);
   }

   public static WindowProc create(WindowProcI instance) {
      return (WindowProc)(instance instanceof WindowProc ? (WindowProc)instance : new WindowProc.Container(instance.address(), instance));
   }

   protected WindowProc() {
      super(SIGNATURE);
   }

   WindowProc(long functionPointer) {
      super(functionPointer);
   }

   private static final class Container extends WindowProc {
      private final WindowProcI delegate;

      Container(long functionPointer, WindowProcI delegate) {
         super(functionPointer);
         this.delegate = delegate;
      }

      @Override
      public long invoke(long hwnd, int uMsg, long wParam, long lParam) {
         return this.delegate.invoke(hwnd, uMsg, wParam, lParam);
      }
   }
}
