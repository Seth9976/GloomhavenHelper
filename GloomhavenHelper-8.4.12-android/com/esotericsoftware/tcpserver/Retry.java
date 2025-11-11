package com.esotericsoftware.tcpserver;

import com.esotericsoftware.minlog.Log;

public abstract class Retry {
    protected final String category;
    boolean daemon;
    protected final String name;
    int retryCount;
    int[] retryDelays;
    volatile Thread retryThread;
    final Object runLock;
    protected volatile boolean running;

    public Retry(String s, String s1) {
        this.runLock = new Object();
        this.retryDelays = new int[]{1000, 3000, 5000, 8000, 13000};
        this.category = s;
        this.name = s1;
    }

    protected void failed() {
        int v = this.retryDelays[this.retryCount % this.retryDelays.length];
        if(v == 0) {
            throw new RuntimeException("Retry thread failed: " + this.name);
        }
        try {
            Thread.sleep(v);
        }
        catch(InterruptedException unused_ex) {
        }
        ++this.retryCount;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    protected void initialize() {
    }

    public boolean isFirstTry() {
        return this.retryCount == 0;
    }

    public boolean isRunning() {
        return this.running;
    }

    protected abstract void retry();

    public void setDaemon(boolean z) {
        this.daemon = z;
    }

    public void setRetryDelays(int[] arr_v) {
        this.retryDelays = arr_v;
    }

    public void start() {
        synchronized(this.runLock) {
            this.stop();
            if(Log.TRACE) {
                Log.trace(this.category, "Started retry thread: " + this.name);
            }
            this.retryCount = 0;
            this.running = true;
            this.retryThread = new Thread(this.name) {
                @Override
                public void run() {
                    while(Retry.this.running) {
                        try {
                            Retry.this.retry();
                        }
                        catch(Throwable throwable0) {
                            try {
                                throw new RuntimeException("Retry error: " + Retry.this.name, throwable0);
                            }
                            catch(Throwable throwable1) {
                                Object object0 = Retry.this.runLock;
                                synchronized(object0) {
                                    Retry.this.stop();
                                    if(Log.TRACE) {
                                        Log.trace(Retry.this.category, "Stopped retry thread: " + Retry.this.name);
                                    }
                                    Retry.this.stopped();
                                    Retry.this.retryThread = null;
                                    Retry.this.runLock.notifyAll();
                                }
                                throw throwable1;
                            }
                        }
                    }
                    synchronized(Retry.this.runLock) {
                        Retry.this.stop();
                        if(Log.TRACE) {
                            Log.trace(Retry.this.category, "Stopped retry thread: " + Retry.this.name);
                        }
                        Retry.this.stopped();
                        Retry.this.retryThread = null;
                        Retry.this.runLock.notifyAll();
                    }
                }
            };
            this.retryThread.setDaemon(this.daemon);
            this.retryThread.start();
        }
    }

    public boolean stop() {
        synchronized(this.runLock) {
            if(!this.running) {
                return false;
            }
            this.running = false;
            Thread thread0 = this.retryThread;
            if(thread0 == Thread.currentThread()) {
                return true;
            }
            if(Log.TRACE) {
                Log.trace(this.category, "Waiting for retry thread to stop: " + this.name);
            }
            thread0.interrupt();
            this.stopped();
            while(this.retryThread == thread0) {
                try {
                    this.runLock.wait();
                }
                catch(InterruptedException unused_ex) {
                }
            }
            return true;
        }
    }

    protected void stopped() {
    }

    protected void success() {
        this.retryCount = 0;
    }
}

