package com.badlogic.gdx.utils.async;

import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncResult {
    private final Future future;

    AsyncResult(Future future0) {
        this.future = future0;
    }

    public Object get() {
        try {
            return this.future.get();
        }
        catch(InterruptedException unused_ex) {
            return null;
        }
        catch(ExecutionException executionException0) {
            throw new GdxRuntimeException(executionException0.getCause());
        }
    }

    public boolean isDone() {
        return this.future.isDone();
    }
}

