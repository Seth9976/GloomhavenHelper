package androidx.asynclayoutinflater.view;

import android.content.Context;
import android.os.Handler.Callback;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.core.util.Pools.SynchronizedPool;
import java.util.concurrent.ArrayBlockingQueue;

public final class AsyncLayoutInflater {
    static class BasicInflater extends LayoutInflater {
        private static final String[] sClassPrefixList;

        static {
            BasicInflater.sClassPrefixList = new String[]{"android.widget.", "android.webkit.", "android.app."};
        }

        BasicInflater(Context context0) {
            super(context0);
        }

        @Override  // android.view.LayoutInflater
        public LayoutInflater cloneInContext(Context context0) {
            return new BasicInflater(context0);
        }

        @Override  // android.view.LayoutInflater
        protected View onCreateView(String s, AttributeSet attributeSet0) throws ClassNotFoundException {
            String[] arr_s = BasicInflater.sClassPrefixList;
            for(int v = 0; v < arr_s.length; ++v) {
                String s1 = arr_s[v];
                try {
                    View view0 = this.createView(s, s1, attributeSet0);
                    if(view0 != null) {
                        return view0;
                    }
                }
                catch(ClassNotFoundException unused_ex) {
                }
            }
            return super.onCreateView(s, attributeSet0);
        }
    }

    static class InflateRequest {
        OnInflateFinishedListener callback;
        AsyncLayoutInflater inflater;
        ViewGroup parent;
        int resid;
        View view;

    }

    static class InflateThread extends Thread {
        private ArrayBlockingQueue mQueue;
        private SynchronizedPool mRequestPool;
        private static final InflateThread sInstance;

        static {
            InflateThread.sInstance = new InflateThread();
            InflateThread.sInstance.start();
        }

        private InflateThread() {
            this.mQueue = new ArrayBlockingQueue(10);
            this.mRequestPool = new SynchronizedPool(10);
        }

        public void enqueue(InflateRequest asyncLayoutInflater$InflateRequest0) {
            try {
                this.mQueue.put(asyncLayoutInflater$InflateRequest0);
            }
            catch(InterruptedException interruptedException0) {
                throw new RuntimeException("Failed to enqueue async inflate request", interruptedException0);
            }
        }

        public static InflateThread getInstance() {
            return InflateThread.sInstance;
        }

        public InflateRequest obtainRequest() {
            InflateRequest asyncLayoutInflater$InflateRequest0 = (InflateRequest)this.mRequestPool.acquire();
            return asyncLayoutInflater$InflateRequest0 == null ? new InflateRequest() : asyncLayoutInflater$InflateRequest0;
        }

        public void releaseRequest(InflateRequest asyncLayoutInflater$InflateRequest0) {
            asyncLayoutInflater$InflateRequest0.callback = null;
            asyncLayoutInflater$InflateRequest0.inflater = null;
            asyncLayoutInflater$InflateRequest0.parent = null;
            asyncLayoutInflater$InflateRequest0.resid = 0;
            asyncLayoutInflater$InflateRequest0.view = null;
            this.mRequestPool.release(asyncLayoutInflater$InflateRequest0);
        }

        @Override
        public void run() {
            while(true) {
                this.runInner();
            }
        }

        public void runInner() {
            InflateRequest asyncLayoutInflater$InflateRequest0;
            try {
                asyncLayoutInflater$InflateRequest0 = (InflateRequest)this.mQueue.take();
            }
            catch(InterruptedException interruptedException0) {
                Log.w("AsyncLayoutInflater", interruptedException0);
                return;
            }
            try {
                asyncLayoutInflater$InflateRequest0.view = asyncLayoutInflater$InflateRequest0.inflater.mInflater.inflate(asyncLayoutInflater$InflateRequest0.resid, asyncLayoutInflater$InflateRequest0.parent, false);
            }
            catch(RuntimeException runtimeException0) {
                Log.w("AsyncLayoutInflater", "Failed to inflate resource in the background! Retrying on the UI thread", runtimeException0);
            }
            Message.obtain(asyncLayoutInflater$InflateRequest0.inflater.mHandler, 0, asyncLayoutInflater$InflateRequest0).sendToTarget();
        }
    }

    public interface OnInflateFinishedListener {
        void onInflateFinished(@NonNull View arg1, @LayoutRes int arg2, @Nullable ViewGroup arg3);
    }

    private static final String TAG = "AsyncLayoutInflater";
    Handler mHandler;
    private Handler.Callback mHandlerCallback;
    InflateThread mInflateThread;
    LayoutInflater mInflater;

    public AsyncLayoutInflater(@NonNull Context context0) {
        this.mHandlerCallback = new Handler.Callback() {
            @Override  // android.os.Handler$Callback
            public boolean handleMessage(Message message0) {
                InflateRequest asyncLayoutInflater$InflateRequest0 = (InflateRequest)message0.obj;
                if(asyncLayoutInflater$InflateRequest0.view == null) {
                    asyncLayoutInflater$InflateRequest0.view = AsyncLayoutInflater.this.mInflater.inflate(asyncLayoutInflater$InflateRequest0.resid, asyncLayoutInflater$InflateRequest0.parent, false);
                }
                asyncLayoutInflater$InflateRequest0.callback.onInflateFinished(asyncLayoutInflater$InflateRequest0.view, asyncLayoutInflater$InflateRequest0.resid, asyncLayoutInflater$InflateRequest0.parent);
                AsyncLayoutInflater.this.mInflateThread.releaseRequest(asyncLayoutInflater$InflateRequest0);
                return true;
            }
        };
        this.mInflater = new BasicInflater(context0);
        this.mHandler = new Handler(this.mHandlerCallback);
        this.mInflateThread = InflateThread.getInstance();
    }

    @UiThread
    public void inflate(@LayoutRes int v, @Nullable ViewGroup viewGroup0, @NonNull OnInflateFinishedListener asyncLayoutInflater$OnInflateFinishedListener0) {
        if(asyncLayoutInflater$OnInflateFinishedListener0 == null) {
            throw new NullPointerException("callback argument may not be null!");
        }
        InflateRequest asyncLayoutInflater$InflateRequest0 = this.mInflateThread.obtainRequest();
        asyncLayoutInflater$InflateRequest0.inflater = this;
        asyncLayoutInflater$InflateRequest0.resid = v;
        asyncLayoutInflater$InflateRequest0.parent = viewGroup0;
        asyncLayoutInflater$InflateRequest0.callback = asyncLayoutInflater$OnInflateFinishedListener0;
        this.mInflateThread.enqueue(asyncLayoutInflater$InflateRequest0);
    }
}

