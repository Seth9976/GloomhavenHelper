package com.badlogic.gdx.utils.async;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class AsyncExecutor implements Disposable {
   private final ExecutorService executor;

   public AsyncExecutor(int maxConcurrent) {
      this(maxConcurrent, "AsynchExecutor-Thread");
   }

   public AsyncExecutor(int maxConcurrent, final String name) {
      this.executor = Executors.newFixedThreadPool(maxConcurrent, new ThreadFactory() {
         @Override
         public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, name);
            thread.setDaemon(true);
            return thread;
         }
      });
   }

   public AsyncResult submit(final AsyncTask task) {
      if (this.executor.isShutdown()) {
         throw new GdxRuntimeException("Cannot run tasks on an executor that has been shutdown (disposed)");
      } else {
         return new AsyncResult(this.executor.submit(new Callable() {
            @Override
            public Object call() throws Exception {
               return task.call();
            }
         }));
      }
   }

   @Override
   public void dispose() {
      this.executor.shutdown();

      try {
         this.executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
      } catch (InterruptedException var2) {
         throw new GdxRuntimeException("Couldn't shutdown loading thread", var2);
      }
   }
}
