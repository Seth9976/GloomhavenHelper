package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@KeepForSdk
public class BlockingServiceConnection implements ServiceConnection {
    private boolean zze;
    private final BlockingQueue zzf;

    public BlockingServiceConnection() {
        this.zze = false;
        this.zzf = new LinkedBlockingQueue();
    }

    @KeepForSdk
    public IBinder getService() throws InterruptedException {
        Preconditions.checkNotMainThread("BlockingServiceConnection.getService() called on main thread");
        if(this.zze) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.zze = true;
        return (IBinder)this.zzf.take();
    }

    @KeepForSdk
    public IBinder getServiceWithTimeout(long v, TimeUnit timeUnit0) throws InterruptedException, TimeoutException {
        Preconditions.checkNotMainThread("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
        if(this.zze) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.zze = true;
        IBinder iBinder0 = (IBinder)this.zzf.poll(v, timeUnit0);
        if(iBinder0 == null) {
            throw new TimeoutException("Timed out waiting for the service connection");
        }
        return iBinder0;
    }

    @Override  // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
        this.zzf.add(iBinder0);
    }

    @Override  // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName0) {
    }
}

