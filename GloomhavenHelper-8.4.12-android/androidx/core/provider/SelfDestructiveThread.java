package androidx.core.provider;

import android.os.Handler.Callback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.GuardedBy;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SelfDestructiveThread {
    public interface ReplyCallback {
        void onReply(Object arg1);
    }

    private static final int MSG_DESTRUCTION = 0;
    private static final int MSG_INVOKE_RUNNABLE = 1;
    private Handler.Callback mCallback;
    private final int mDestructAfterMillisec;
    @GuardedBy("mLock")
    private int mGeneration;
    @GuardedBy("mLock")
    private Handler mHandler;
    private final Object mLock;
    private final int mPriority;
    @GuardedBy("mLock")
    private HandlerThread mThread;
    private final String mThreadName;

    public SelfDestructiveThread(String s, int v, int v1) {
        this.mLock = new Object();
        this.mCallback = new Handler.Callback() {
            @Override  // android.os.Handler$Callback
            public boolean handleMessage(Message message0) {
                switch(message0.what) {
                    case 0: {
                        SelfDestructiveThread.this.onDestruction();
                        return true;
                    }
                    case 1: {
                        SelfDestructiveThread.this.onInvokeRunnable(((Runnable)message0.obj));
                        return true;
                    }
                    default: {
                        return true;
                    }
                }
            }
        };
        this.mThreadName = s;
        this.mPriority = v;
        this.mDestructAfterMillisec = v1;
        this.mGeneration = 0;
    }

    @VisibleForTesting
    public int getGeneration() {
        synchronized(this.mLock) {
        }
        return this.mGeneration;
    }

    @VisibleForTesting
    public boolean isRunning() {
        synchronized(this.mLock) {
        }
        return this.mThread != null;
    }

    void onDestruction() {
        synchronized(this.mLock) {
            if(this.mHandler.hasMessages(1)) {
                return;
            }
            this.mThread.quit();
            this.mThread = null;
            this.mHandler = null;
        }
    }

    void onInvokeRunnable(Runnable runnable0) {
        runnable0.run();
        synchronized(this.mLock) {
            this.mHandler.removeMessages(0);
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0), ((long)this.mDestructAfterMillisec));
        }
    }

    private void post(Runnable runnable0) {
        synchronized(this.mLock) {
            if(this.mThread == null) {
                this.mThread = new HandlerThread(this.mThreadName, this.mPriority);
                this.mThread.start();
                this.mHandler = new Handler(this.mThread.getLooper(), this.mCallback);
                ++this.mGeneration;
            }
            this.mHandler.removeMessages(0);
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, runnable0));
        }
    }

    public void postAndReply(Callable callable0, ReplyCallback selfDestructiveThread$ReplyCallback0) {
        this.post(new Runnable() {
            @Override
            public void run() {
                Object object0 = null;
                try {
                    object0 = callable0.call();
                }
                catch(Exception unused_ex) {
                }
                androidx.core.provider.SelfDestructiveThread.2.1 selfDestructiveThread$2$10 = new Runnable() {
                    @Override
                    public void run() {
                        androidx.core.provider.SelfDestructiveThread.2.this.val$reply.onReply(object0);
                    }
                };
                new Handler().post(selfDestructiveThread$2$10);
            }
        });
    }

    public Object postAndWait(Callable callable0, int v) throws InterruptedException {
        ReentrantLock reentrantLock0 = new ReentrantLock();
        Condition condition0 = reentrantLock0.newCondition();
        AtomicReference atomicReference0 = new AtomicReference();
        AtomicBoolean atomicBoolean0 = new AtomicBoolean(true);
        this.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Object object0 = callable0.call();
                    atomicReference0.set(object0);
                }
                catch(Exception unused_ex) {
                }
                reentrantLock0.lock();
                try {
                    atomicBoolean0.set(false);
                    condition0.signal();
                }
                finally {
                    reentrantLock0.unlock();
                }
            }
        });
        reentrantLock0.lock();
        try {
            if(!atomicBoolean0.get()) {
                return atomicReference0.get();
            }
            long v2 = TimeUnit.MILLISECONDS.toNanos(((long)v));
            do {
                try {
                    v2 = condition0.awaitNanos(v2);
                }
                catch(InterruptedException unused_ex) {
                }
                if(!atomicBoolean0.get()) {
                    return atomicReference0.get();
                }
            }
            while(v2 > 0L);
        }
        finally {
            reentrantLock0.unlock();
        }
        throw new InterruptedException("timeout");
    }
}

