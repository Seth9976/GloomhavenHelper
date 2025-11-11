package com.badlogic.gdx.utils;

public class PauseableThread extends Thread {
    boolean exit;
    boolean paused;
    final Runnable runnable;

    public PauseableThread(Runnable runnable0) {
        this.paused = false;
        this.exit = false;
        this.runnable = runnable0;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void onPause() {
        this.paused = true;
    }

    public void onResume() {
        synchronized(this) {
            this.paused = false;
            this.notifyAll();
        }
    }

    @Override
    public void run() {
        while(true) {
            synchronized(this) {
                try {
                label_2:
                    while(this.paused) {
                        this.wait();
                    }
                }
                catch(InterruptedException interruptedException0) {
                    interruptedException0.printStackTrace();
                    if(true) {
                        goto label_8;
                    }
                    goto label_2;
                }
            }
        label_8:
            if(this.exit) {
                break;
            }
            this.runnable.run();
        }
    }

    public void stopThread() {
        this.exit = true;
        if(this.paused) {
            this.onResume();
        }
    }
}

