package org.lwjgl.system;

public interface CallbackI extends Pointer {
   String getSignature();

   @Override
   default long address() {
      return Callback.create(this.getSignature(), this);
   }

   public interface B extends CallbackI {
      byte callback(long var1);
   }

   public interface D extends CallbackI {
      double callback(long var1);
   }

   public interface F extends CallbackI {
      float callback(long var1);
   }

   public interface I extends CallbackI {
      int callback(long var1);
   }

   public interface J extends CallbackI {
      long callback(long var1);
   }

   public interface N extends CallbackI {
      long callback(long var1);
   }

   public interface P extends CallbackI {
      long callback(long var1);
   }

   public interface S extends CallbackI {
      short callback(long var1);
   }

   public interface V extends CallbackI {
      void callback(long var1);
   }

   public interface Z extends CallbackI {
      boolean callback(long var1);
   }
}
