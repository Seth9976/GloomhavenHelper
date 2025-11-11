package org.lwjgl.system.macosx;

import javax.annotation.Nullable;
import org.lwjgl.system.Callback;

public abstract class EnumerationMutationHandler extends Callback implements EnumerationMutationHandlerI {
   public static EnumerationMutationHandler create(long functionPointer) {
      EnumerationMutationHandlerI instance = (EnumerationMutationHandlerI)Callback.get(functionPointer);
      return (EnumerationMutationHandler)(instance instanceof EnumerationMutationHandler
         ? (EnumerationMutationHandler)instance
         : new EnumerationMutationHandler.Container(functionPointer, instance));
   }

   @Nullable
   public static EnumerationMutationHandler createSafe(long functionPointer) {
      return functionPointer == 0L ? null : create(functionPointer);
   }

   public static EnumerationMutationHandler create(EnumerationMutationHandlerI instance) {
      return (EnumerationMutationHandler)(instance instanceof EnumerationMutationHandler
         ? (EnumerationMutationHandler)instance
         : new EnumerationMutationHandler.Container(instance.address(), instance));
   }

   protected EnumerationMutationHandler() {
      super("(p)v");
   }

   EnumerationMutationHandler(long functionPointer) {
      super(functionPointer);
   }

   private static final class Container extends EnumerationMutationHandler {
      private final EnumerationMutationHandlerI delegate;

      Container(long functionPointer, EnumerationMutationHandlerI delegate) {
         super(functionPointer);
         this.delegate = delegate;
      }

      @Override
      public void invoke(long id) {
         this.delegate.invoke(id);
      }
   }
}
