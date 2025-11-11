package com.badlogic.gdx.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.LifecycleListener;
import jeb.synthetic.FIN;

public class Timer {
    public static abstract class Task implements Runnable {
        final Application app;
        long executeTimeMillis;
        long intervalMillis;
        int repeatCount;
        volatile Timer timer;

        public Task() {
            this.app = Gdx.app;
            if(this.app == null) {
                throw new IllegalStateException("Gdx.app not available.");
            }
        }

        public void cancel() {
            Timer timer0 = this.timer;
            if(timer0 != null) {
                synchronized(timer0) {
                    synchronized(this) {
                        this.executeTimeMillis = 0L;
                        this.timer = null;
                        timer0.tasks.removeValue(this, true);
                    }
                }
                return;
            }
            synchronized(this) {
                this.executeTimeMillis = 0L;
                this.timer = null;
            }
        }

        public long getExecuteTimeMillis() {
            synchronized(this) {
            }
            return this.executeTimeMillis;
        }

        public boolean isScheduled() {
            return this.timer != null;
        }

        @Override
        public abstract void run();
    }

    static class TimerThread implements LifecycleListener, Runnable {
        final Application app;
        final Files files;
        Timer instance;
        final Array instances;
        long pauseTimeMillis;

        public TimerThread() {
            this.instances = new Array(1);
            this.files = Gdx.files;
            this.app = Gdx.app;
            this.app.addLifecycleListener(this);
            this.resume();
            Thread thread0 = new Thread(this, "Timer");
            thread0.setDaemon(true);
            thread0.start();
        }

        @Override  // com.badlogic.gdx.LifecycleListener
        public void dispose() {
            synchronized(Timer.threadLock) {
                if(Timer.thread == this) {
                    Timer.thread = null;
                }
                this.instances.clear();
                Timer.threadLock.notifyAll();
            }
            this.app.removeLifecycleListener(this);
        }

        @Override  // com.badlogic.gdx.LifecycleListener
        public void pause() {
            synchronized(Timer.threadLock) {
                this.pauseTimeMillis = System.nanoTime() / 1000000L;
                Timer.threadLock.notifyAll();
            }
        }

        @Override  // com.badlogic.gdx.LifecycleListener
        public void resume() {
            synchronized(Timer.threadLock) {
                long v1 = System.nanoTime() / 1000000L - this.pauseTimeMillis;
                int v3 = this.instances.size;
                for(int v2 = 0; v2 < v3; ++v2) {
                    ((Timer)this.instances.get(v2)).delay(v1);
                }
                this.pauseTimeMillis = 0L;
                Timer.threadLock.notifyAll();
            }
        }

        @Override
        public void run() {
            int v1;
            Object object0;
            while(true) {
                long v = 5000L;
                object0 = Timer.threadLock;
                __monitor_enter(object0);
                v1 = FIN.finallyOpen$NT();
                if(Timer.thread != this || this.files != Gdx.files) {
                    goto label_24;
                }
                if(this.pauseTimeMillis == 0L) {
                    long v2 = System.nanoTime() / 1000000L;
                    int v4 = this.instances.size;
                    for(int v3 = 0; v3 < v4; ++v3) {
                        try {
                            v = ((Timer)this.instances.get(v3)).update(v2, v);
                        }
                        catch(Throwable throwable0) {
                            FIN.finallyExec$NT(v1);
                            throw new GdxRuntimeException("Task failed: " + ((Timer)this.instances.get(v3)).getClass().getName(), throwable0);
                        }
                    }
                }
                if(Timer.thread != this || this.files != Gdx.files) {
                    break;
                }
                if(v > 0L) {
                    try {
                        Timer.threadLock.wait(v);
                    }
                    catch(InterruptedException unused_ex) {
                    }
                }
                FIN.finallyExec$NT(v1);
            }
            FIN.finallyExec$NT(v1);
            goto label_27;
        label_24:
            FIN.finallyCodeBegin$NT(v1);
            __monitor_exit(object0);
            FIN.finallyCodeEnd$NT(v1);
        label_27:
            this.dispose();
        }
    }

    final Array tasks;
    static TimerThread thread;
    static final Object threadLock;

    static {
        Timer.threadLock = new Object();
    }

    public Timer() {
        this.tasks = new Array(false, 8);
        this.start();
    }

    public void clear() {
        synchronized(this) {
            int v2 = this.tasks.size;
            for(int v1 = 0; v1 < v2; ++v1) {
                Task timer$Task0 = (Task)this.tasks.get(v1);
                synchronized(timer$Task0) {
                    timer$Task0.executeTimeMillis = 0L;
                    timer$Task0.timer = null;
                }
            }
            this.tasks.clear();
        }
    }

    public void delay(long v) {
        synchronized(this) {
            int v3 = this.tasks.size;
            for(int v2 = 0; v2 < v3; ++v2) {
                Task timer$Task0 = (Task)this.tasks.get(v2);
                synchronized(timer$Task0) {
                    timer$Task0.executeTimeMillis += v;
                }
            }
        }
    }

    public static Timer instance() {
        synchronized(Timer.threadLock) {
            TimerThread timer$TimerThread0 = Timer.thread();
            if(timer$TimerThread0.instance == null) {
                timer$TimerThread0.instance = new Timer();
            }
            return timer$TimerThread0.instance;
        }
    }

    public boolean isEmpty() {
        synchronized(this) {
        }
        return this.tasks.size == 0;
    }

    public static Task post(Task timer$Task0) {
        return Timer.instance().postTask(timer$Task0);
    }

    public Task postTask(Task timer$Task0) {
        return this.scheduleTask(timer$Task0, 0.0f, 0.0f, 0);
    }

    public static Task schedule(Task timer$Task0, float f) {
        return Timer.instance().scheduleTask(timer$Task0, f);
    }

    public static Task schedule(Task timer$Task0, float f, float f1) {
        return Timer.instance().scheduleTask(timer$Task0, f, f1);
    }

    public static Task schedule(Task timer$Task0, float f, float f1, int v) {
        return Timer.instance().scheduleTask(timer$Task0, f, f1, v);
    }

    public Task scheduleTask(Task timer$Task0, float f) {
        return this.scheduleTask(timer$Task0, f, 0.0f, 0);
    }

    public Task scheduleTask(Task timer$Task0, float f, float f1) {
        return this.scheduleTask(timer$Task0, f, f1, -1);
    }

    public Task scheduleTask(Task timer$Task0, float f, float f1, int v) {
        Object object0 = Timer.threadLock;
        __monitor_enter(object0);
        int v1 = FIN.finallyOpen$NT();
        __monitor_enter(this);
        int v2 = FIN.finallyOpen$NT();
        synchronized(timer$Task0) {
            if(timer$Task0.timer == null) {
                timer$Task0.timer = this;
                long v4 = System.nanoTime();
                timer$Task0.executeTimeMillis = Timer.thread.pauseTimeMillis <= 0L ? ((long)(f * 1000.0f)) + v4 / 1000000L : ((long)(f * 1000.0f)) + v4 / 1000000L - (v4 / 1000000L - Timer.thread.pauseTimeMillis);
                timer$Task0.intervalMillis = (long)(f1 * 1000.0f);
                timer$Task0.repeatCount = v;
                this.tasks.add(timer$Task0);
                FIN.finallyCodeBegin$NT(v2);
                __monitor_exit(this);
                FIN.finallyCodeEnd$NT(v2);
                Timer.threadLock.notifyAll();
                FIN.finallyCodeBegin$NT(v1);
                __monitor_exit(object0);
                FIN.finallyCodeEnd$NT(v1);
                return timer$Task0;
            }
        }
        throw new IllegalArgumentException("The same task may not be scheduled twice.");
    }

    public void start() {
        synchronized(Timer.threadLock) {
            Array array0 = Timer.thread().instances;
            if(array0.contains(this, true)) {
                return;
            }
            array0.add(this);
            Timer.threadLock.notifyAll();
        }
    }

    public void stop() {
        synchronized(Timer.threadLock) {
            Timer.thread().instances.removeValue(this, true);
        }
    }

    private static TimerThread thread() {
        synchronized(Timer.threadLock) {
            if(Timer.thread == null || Timer.thread.files != Gdx.files) {
                if(Timer.thread != null) {
                    Timer.thread.dispose();
                }
                Timer.thread = new TimerThread();
            }
            return Timer.thread;
        }
    }

    long update(long v, long v1) {
        synchronized(this) {
            int v4 = this.tasks.size;
            for(int v3 = 0; v3 < v4; ++v3) {
                Task timer$Task0 = (Task)this.tasks.get(v3);
                synchronized(timer$Task0) {
                    if(timer$Task0.executeTimeMillis <= v) {
                        if(timer$Task0.repeatCount == 0) {
                            timer$Task0.timer = null;
                            this.tasks.removeIndex(v3);
                            --v3;
                            --v4;
                        }
                        else {
                            timer$Task0.executeTimeMillis = timer$Task0.intervalMillis + v;
                            v1 = Math.min(v1, timer$Task0.intervalMillis);
                            if(timer$Task0.repeatCount > 0) {
                                --timer$Task0.repeatCount;
                            }
                        }
                        timer$Task0.app.postRunnable(timer$Task0);
                    }
                    else {
                        v1 = Math.min(v1, timer$Task0.executeTimeMillis - v);
                    }
                }
            }
            return v1;
        }
    }
}

