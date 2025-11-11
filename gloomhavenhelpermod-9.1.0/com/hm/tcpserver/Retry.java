package com.hm.tcpserver;

import com.hm.minlog.Log;

public abstract class Retry {
   protected final String category;
   protected final String name;
   protected volatile boolean running;
   boolean daemon;
   final Object runLock = new Object();
   volatile Thread retryThread;
   int retryCount;
   int[] retryDelays = new int[]{1000, 3000, 5000, 8000, 13000};

   public Retry(String category, String name) {
      this.category = category;
      this.name = name;
   }

   public void start() {
      synchronized (this.runLock) {
         this.stop();
         if (Log.TRACE) {
            Log.trace(this.category, "Started retry thread: " + this.name);
         }

         this.retryCount = 0;
         this.running = true;
         this.retryThread = new Thread(this.name) {
            @Override
            public void run() {
               try {
                  Retry.this.initialize();

                  while (Retry.this.running) {
                     Retry.this.retry();
                  }
               } catch (Throwable var11) {
                  throw new RuntimeException("Retry error: " + Retry.this.name, var11);
               } finally {
                  synchronized (Retry.this.runLock) {
                     Retry.this.stop();
                     if (Log.TRACE) {
                        Log.trace(Retry.this.category, "Stopped retry thread: " + Retry.this.name);
                     }

                     Retry.this.stopped();
                     Retry.this.retryThread = null;
                     Retry.this.runLock.notifyAll();
                  }
               }
            }
         };
         this.retryThread.setDaemon(this.daemon);
         this.retryThread.start();
      }
   }

   public boolean stop() {
      synchronized (this.runLock) {
         if (!this.running) {
            return false;
         } else {
            this.running = false;
            Thread retryThread = this.retryThread;
            if (retryThread == Thread.currentThread()) {
               return true;
            } else {
               if (Log.TRACE) {
                  Log.trace(this.category, "Waiting for retry thread to stop: " + this.name);
               }

               retryThread.interrupt();
               this.stopped();

               while (this.retryThread == retryThread) {
                  try {
                     this.runLock.wait();
                  } catch (InterruptedException var5) {
                  }
               }

               return true;
            }
         }
      }
   }

   protected void initialize() {
   }

   protected abstract void retry();

   protected void stopped() {
   }

   protected void success() {
      this.retryCount = 0;
   }

   protected void failed() {
      int delay = this.retryDelays[this.retryCount % this.retryDelays.length];
      if (delay == 0) {
         throw new RuntimeException("Retry thread failed: " + this.name);
      } else {
         try {
            Thread.sleep(delay);
         } catch (InterruptedException var3) {
         }

         this.retryCount++;
      }
   }

   public void setRetryDelays(int... retryDelays) {
      this.retryDelays = retryDelays;
   }

   public int getRetryCount() {
      return this.retryCount;
   }

   public boolean isFirstTry() {
      return this.retryCount == 0;
   }

   public boolean isRunning() {
      return this.running;
   }

   public void setDaemon(boolean daemon) {
      this.daemon = daemon;
   }
}
