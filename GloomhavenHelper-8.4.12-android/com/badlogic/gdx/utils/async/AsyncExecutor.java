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

    public AsyncExecutor(int v) {
        this(v, "AsynchExecutor-Thread");
    }

    public AsyncExecutor(int v, String s) {
        this.executor = Executors.newFixedThreadPool(v, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable0) {
                Thread thread0 = new Thread(runnable0, s);
                thread0.setDaemon(true);
                return thread0;
            }
        });
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.executor.shutdown();
        try {
            this.executor.awaitTermination(0x7FFFFFFFFFFFFFFFL, TimeUnit.SECONDS);
        }
        catch(InterruptedException interruptedException0) {
            throw new GdxRuntimeException("Couldn\'t shutdown loading thread", interruptedException0);
        }
    }

    public AsyncResult submit(AsyncTask asyncTask0) {
        if(this.executor.isShutdown()) {
            throw new GdxRuntimeException("Cannot run tasks on an executor that has been shutdown (disposed)");
        }
        com.badlogic.gdx.utils.async.AsyncExecutor.2 asyncExecutor$20 = new Callable() {
            @Override
            public Object call() throws Exception {
                return asyncTask0.call();
            }
        };
        return new AsyncResult(this.executor.submit(asyncExecutor$20));
    }
}

