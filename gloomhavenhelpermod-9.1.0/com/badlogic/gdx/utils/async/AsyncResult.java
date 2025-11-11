package com.badlogic.gdx.utils.async;

import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncResult {
   private final Future future;

   AsyncResult(Future future) {
      this.future = future;
   }

   public boolean isDone() {
      return this.future.isDone();
   }

   public Object get() {
      try {
         return this.future.get();
      } catch (InterruptedException var2) {
         return null;
      } catch (ExecutionException var3) {
         throw new GdxRuntimeException(var3.getCause());
      }
   }
}
