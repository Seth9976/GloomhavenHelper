package com.badlogic.gdx.backends.lwjgl3;

import org.lwjgl.glfw.GLFW;

class Sync {
   private static final long NANOS_IN_SECOND = 1000000000L;
   private long nextFrame = 0L;
   private boolean initialised = false;
   private Sync.RunningAvg sleepDurations = new Sync.RunningAvg(10);
   private Sync.RunningAvg yieldDurations = new Sync.RunningAvg(10);

   public Sync() {
   }

   public void sync(int fps) {
      if (fps > 0) {
         if (!this.initialised) {
            this.initialise();
         }

         try {
            long t0 = this.getTime();

            while (this.nextFrame - t0 > this.sleepDurations.avg()) {
               Thread.sleep(1L);
               long t1;
               this.sleepDurations.add((t1 = this.getTime()) - t0);
               t0 = t1;
            }

            this.sleepDurations.dampenForLowResTicker();
            t0 = this.getTime();

            while (this.nextFrame - t0 > this.yieldDurations.avg()) {
               Thread.yield();
               long t1;
               this.yieldDurations.add((t1 = this.getTime()) - t0);
               t0 = t1;
            }
         } catch (InterruptedException var6) {
         }

         this.nextFrame = Math.max(this.nextFrame + 1000000000L / fps, this.getTime());
      }
   }

   private void initialise() {
      this.initialised = true;
      this.sleepDurations.init(1000000L);
      this.yieldDurations.init((int)(-(this.getTime() - this.getTime()) * 1.333));
      this.nextFrame = this.getTime();
      String osName = System.getProperty("os.name");
      if (osName.startsWith("Win")) {
         Thread timerAccuracyThread = new Thread(new Runnable() {
            @Override
            public void run() {
               try {
                  Thread.sleep(Long.MAX_VALUE);
               } catch (Exception var2) {
               }
            }
         });
         timerAccuracyThread.setName("LWJGL3 Timer");
         timerAccuracyThread.setDaemon(true);
         timerAccuracyThread.start();
      }
   }

   private long getTime() {
      return (long)(GLFW.glfwGetTime() * 1.0E9);
   }

   private class RunningAvg {
      private final long[] slots;
      private int offset;
      private static final long DAMPEN_THRESHOLD = 10000000L;
      private static final float DAMPEN_FACTOR = 0.9F;

      public RunningAvg(int slotCount) {
         this.slots = new long[slotCount];
         this.offset = 0;
      }

      public void init(long value) {
         while (this.offset < this.slots.length) {
            this.slots[this.offset++] = value;
         }
      }

      public void add(long value) {
         this.slots[this.offset++ % this.slots.length] = value;
         this.offset = this.offset % this.slots.length;
      }

      public long avg() {
         long sum = 0L;

         for (int i = 0; i < this.slots.length; i++) {
            sum += this.slots[i];
         }

         return sum / this.slots.length;
      }

      public void dampenForLowResTicker() {
         if (this.avg() > 10000000L) {
            for (int i = 0; i < this.slots.length; i++) {
               this.slots[i] = (long)((float)this.slots[i] * 0.9F);
            }
         }
      }
   }
}
