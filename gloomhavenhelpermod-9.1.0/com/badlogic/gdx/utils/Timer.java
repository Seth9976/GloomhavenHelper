package com.badlogic.gdx.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.LifecycleListener;

public class Timer {
   static final Object threadLock = new Object();
   static Timer.TimerThread thread;
   final Array tasks = new Array(false, 8);

   public static Timer instance() {
      synchronized (threadLock) {
         Timer.TimerThread thread = thread();
         if (thread.instance == null) {
            thread.instance = new Timer();
         }

         return thread.instance;
      }
   }

   private static Timer.TimerThread thread() {
      synchronized (threadLock) {
         if (thread == null || thread.files != Gdx.files) {
            if (thread != null) {
               thread.dispose();
            }

            thread = new Timer.TimerThread();
         }

         return thread;
      }
   }

   public Timer() {
      this.start();
   }

   public Timer.Task postTask(Timer.Task task) {
      return this.scheduleTask(task, 0.0F, 0.0F, 0);
   }

   public Timer.Task scheduleTask(Timer.Task task, float delaySeconds) {
      return this.scheduleTask(task, delaySeconds, 0.0F, 0);
   }

   public Timer.Task scheduleTask(Timer.Task task, float delaySeconds, float intervalSeconds) {
      return this.scheduleTask(task, delaySeconds, intervalSeconds, -1);
   }

   public Timer.Task scheduleTask(Timer.Task task, float delaySeconds, float intervalSeconds, int repeatCount) {
      synchronized (threadLock) {
         synchronized (this) {
            synchronized (task) {
               if (task.timer != null) {
                  throw new IllegalArgumentException("The same task may not be scheduled twice.");
               }

               task.timer = this;
               long timeMillis = System.nanoTime() / 1000000L;
               long executeTimeMillis = timeMillis + (long)(delaySeconds * 1000.0F);
               if (thread.pauseTimeMillis > 0L) {
                  executeTimeMillis -= timeMillis - thread.pauseTimeMillis;
               }

               task.executeTimeMillis = executeTimeMillis;
               task.intervalMillis = (long)(intervalSeconds * 1000.0F);
               task.repeatCount = repeatCount;
               this.tasks.add(task);
            }
         }

         threadLock.notifyAll();
         return task;
      }
   }

   public void stop() {
      synchronized (threadLock) {
         thread().instances.removeValue(this, true);
      }
   }

   public void start() {
      synchronized (threadLock) {
         Timer.TimerThread thread = thread();
         Array instances = thread.instances;
         if (!instances.contains(this, true)) {
            instances.add(this);
            threadLock.notifyAll();
         }
      }
   }

   public synchronized void clear() {
      int i = 0;

      for (int n = this.tasks.size; i < n; i++) {
         Timer.Task task = (Timer.Task)this.tasks.get(i);
         synchronized (task) {
            task.executeTimeMillis = 0L;
            task.timer = null;
         }
      }

      this.tasks.clear();
   }

   public synchronized boolean isEmpty() {
      return this.tasks.size == 0;
   }

   synchronized long update(long timeMillis, long waitMillis) {
      int i = 0;

      for (int n = this.tasks.size; i < n; i++) {
         Timer.Task task = (Timer.Task)this.tasks.get(i);
         synchronized (task) {
            if (task.executeTimeMillis > timeMillis) {
               waitMillis = Math.min(waitMillis, task.executeTimeMillis - timeMillis);
            } else {
               if (task.repeatCount == 0) {
                  task.timer = null;
                  this.tasks.removeIndex(i);
                  i--;
                  n--;
               } else {
                  task.executeTimeMillis = timeMillis + task.intervalMillis;
                  waitMillis = Math.min(waitMillis, task.intervalMillis);
                  if (task.repeatCount > 0) {
                     task.repeatCount--;
                  }
               }

               task.app.postRunnable(task);
            }
         }
      }

      return waitMillis;
   }

   public synchronized void delay(long delayMillis) {
      int i = 0;

      for (int n = this.tasks.size; i < n; i++) {
         Timer.Task task = (Timer.Task)this.tasks.get(i);
         synchronized (task) {
            task.executeTimeMillis += delayMillis;
         }
      }
   }

   public static Timer.Task post(Timer.Task task) {
      return instance().postTask(task);
   }

   public static Timer.Task schedule(Timer.Task task, float delaySeconds) {
      return instance().scheduleTask(task, delaySeconds);
   }

   public static Timer.Task schedule(Timer.Task task, float delaySeconds, float intervalSeconds) {
      return instance().scheduleTask(task, delaySeconds, intervalSeconds);
   }

   public static Timer.Task schedule(Timer.Task task, float delaySeconds, float intervalSeconds, int repeatCount) {
      return instance().scheduleTask(task, delaySeconds, intervalSeconds, repeatCount);
   }

   public abstract static class Task implements Runnable {
      final Application app;
      long executeTimeMillis;
      long intervalMillis;
      int repeatCount;
      volatile Timer timer;

      public Task() {
         this.app = Gdx.app;
         if (this.app == null) {
            throw new IllegalStateException("Gdx.app not available.");
         }
      }

      @Override
      public abstract void run();

      public void cancel() {
         Timer timer = this.timer;
         if (timer != null) {
            synchronized (timer) {
               synchronized (this) {
                  this.executeTimeMillis = 0L;
                  this.timer = null;
                  timer.tasks.removeValue(this, true);
               }
            }
         } else {
            synchronized (this) {
               this.executeTimeMillis = 0L;
               this.timer = null;
            }
         }
      }

      public boolean isScheduled() {
         return this.timer != null;
      }

      public synchronized long getExecuteTimeMillis() {
         return this.executeTimeMillis;
      }
   }

   static class TimerThread implements Runnable, LifecycleListener {
      final Files files;
      final Application app;
      final Array instances = new Array(1);
      Timer instance;
      long pauseTimeMillis;

      public TimerThread() {
         this.files = Gdx.files;
         this.app = Gdx.app;
         this.app.addLifecycleListener(this);
         this.resume();
         Thread thread = new Thread(this, "Timer");
         thread.setDaemon(true);
         thread.start();
      }

      @Override
      public void run() {
         while (true) {
            synchronized (Timer.threadLock) {
               if (Timer.thread == this && this.files == Gdx.files) {
                  long waitMillis = 5000L;
                  if (this.pauseTimeMillis == 0L) {
                     long timeMillis = System.nanoTime() / 1000000L;
                     int i = 0;

                     for (int n = this.instances.size; i < n; i++) {
                        try {
                           waitMillis = ((Timer)this.instances.get(i)).update(timeMillis, waitMillis);
                        } catch (Throwable var11) {
                           throw new GdxRuntimeException("Task failed: " + ((Timer)this.instances.get(i)).getClass().getName(), var11);
                        }
                     }
                  }

                  if (Timer.thread == this && this.files == Gdx.files) {
                     try {
                        if (waitMillis > 0L) {
                           Timer.threadLock.wait(waitMillis);
                        }
                     } catch (InterruptedException var10) {
                     }
                     continue;
                  }
               }
            }

            this.dispose();
            return;
         }
      }

      @Override
      public void resume() {
         synchronized (Timer.threadLock) {
            long delayMillis = System.nanoTime() / 1000000L - this.pauseTimeMillis;
            int i = 0;

            for (int n = this.instances.size; i < n; i++) {
               ((Timer)this.instances.get(i)).delay(delayMillis);
            }

            this.pauseTimeMillis = 0L;
            Timer.threadLock.notifyAll();
         }
      }

      @Override
      public void pause() {
         synchronized (Timer.threadLock) {
            this.pauseTimeMillis = System.nanoTime() / 1000000L;
            Timer.threadLock.notifyAll();
         }
      }

      @Override
      public void dispose() {
         synchronized (Timer.threadLock) {
            if (Timer.thread == this) {
               Timer.thread = null;
            }

            this.instances.clear();
            Timer.threadLock.notifyAll();
         }

         this.app.removeLifecycleListener(this);
      }
   }
}
