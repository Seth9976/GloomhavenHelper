package org.lwjgl.system.jemalloc;

import javax.annotation.Nullable;
import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryUtil;

public abstract class MallocMessageCallback extends Callback implements MallocMessageCallbackI {
   public static MallocMessageCallback create(long functionPointer) {
      MallocMessageCallbackI instance = (MallocMessageCallbackI)Callback.get(functionPointer);
      return (MallocMessageCallback)(instance instanceof MallocMessageCallback
         ? (MallocMessageCallback)instance
         : new MallocMessageCallback.Container(functionPointer, instance));
   }

   @Nullable
   public static MallocMessageCallback createSafe(long functionPointer) {
      return functionPointer == 0L ? null : create(functionPointer);
   }

   public static MallocMessageCallback create(MallocMessageCallbackI instance) {
      return (MallocMessageCallback)(instance instanceof MallocMessageCallback
         ? (MallocMessageCallback)instance
         : new MallocMessageCallback.Container(instance.address(), instance));
   }

   protected MallocMessageCallback() {
      super("(pp)v");
   }

   MallocMessageCallback(long functionPointer) {
      super(functionPointer);
   }

   public static String getMessage(long s) {
      return MemoryUtil.memASCII(s);
   }

   private static final class Container extends MallocMessageCallback {
      private final MallocMessageCallbackI delegate;

      Container(long functionPointer, MallocMessageCallbackI delegate) {
         super(functionPointer);
         this.delegate = delegate;
      }

      @Override
      public void invoke(long cbopaque, long s) {
         this.delegate.invoke(cbopaque, s);
      }
   }
}
