package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import java.io.IOException;

@SuppressLint({"HandlerLeak"})
final class zznx extends zzdkp implements Runnable {
    private volatile boolean zzaee;
    private final zzoa zzbga;
    private final zzny zzbgb;
    public final int zzbgc;
    private final long zzbgd;
    private IOException zzbge;
    private int zzbgf;
    private volatile Thread zzbgg;
    private final zznv zzbgh;

    public zznx(zznv zznv0, Looper looper0, zzoa zzoa0, zzny zzny0, int v, long v1) {
        this.zzbgh = zznv0;
        super(looper0);
        this.zzbga = zzoa0;
        this.zzbgb = zzny0;
        this.zzbgc = v;
        this.zzbgd = v1;
    }

    private final void execute() {
        this.zzbge = null;
        this.zzbgh.zzbfv.execute(this.zzbgh.zzbfw);
    }

    private final void finish() {
        this.zzbgh.zzbfw = null;
    }

    @Override  // android.os.Handler
    public final void handleMessage(Message message0) {
        int v3;
        if(this.zzaee) {
            return;
        }
        switch(message0.what) {
            case 0: {
                this.execute();
                return;
            }
            case 4: {
                throw (Error)message0.obj;
            }
            default: {
                this.finish();
                long v = SystemClock.elapsedRealtime();
                long v1 = v - this.zzbgd;
                if(this.zzbga.zzhu()) {
                    this.zzbgb.zza(this.zzbga, v, v1, false);
                    return;
                }
                switch(message0.what) {
                    case 1: {
                        this.zzbgb.zza(this.zzbga, v, v1, false);
                        return;
                    }
                    case 2: {
                        this.zzbgb.zza(this.zzbga, v, v1);
                        return;
                    }
                    case 3: {
                        this.zzbge = (IOException)message0.obj;
                        int v2 = this.zzbgb.zza(this.zzbga, v, v1, this.zzbge);
                        if(v2 == 3) {
                            this.zzbgh.zzbfx = this.zzbge;
                            return;
                        }
                        switch(v2) {
                            case 1: {
                                v3 = 1;
                                break;
                            }
                            case 2: {
                                return;
                            }
                            default: {
                                v3 = this.zzbgf + 1;
                                break;
                            }
                        }
                        this.zzbgf = v3;
                        this.zzek(((long)Math.min((this.zzbgf - 1) * 1000, 5000)));
                    }
                }
            }
        }
    }

    @Override
    public final void run() {
        try {
            this.zzbgg = Thread.currentThread();
            if(!this.zzbga.zzhu()) {
                String s = this.zzbga.getClass().getSimpleName();
                zzoq.beginSection((s.length() == 0 ? new String("load:") : "load:" + s));
                try {
                    this.zzbga.zzhv();
                }
                finally {
                    zzoq.endSection();
                }
            }
            if(!this.zzaee) {
                this.sendEmptyMessage(2);
            }
        }
        catch(IOException iOException0) {
            if(!this.zzaee) {
                this.obtainMessage(3, iOException0).sendToTarget();
            }
        }
        catch(InterruptedException unused_ex) {
            zzob.checkState(this.zzbga.zzhu());
            if(!this.zzaee) {
                this.sendEmptyMessage(2);
            }
        }
        catch(Exception exception0) {
            Log.e("LoadTask", "Unexpected exception loading stream", exception0);
            if(!this.zzaee) {
                this.obtainMessage(3, new zznz(exception0)).sendToTarget();
            }
        }
        catch(OutOfMemoryError outOfMemoryError0) {
            Log.e("LoadTask", "OutOfMemory error loading stream", outOfMemoryError0);
            if(!this.zzaee) {
                this.obtainMessage(3, new zznz(outOfMemoryError0)).sendToTarget();
            }
        }
        catch(Error error0) {
            Log.e("LoadTask", "Unexpected error loading stream", error0);
            if(!this.zzaee) {
                this.obtainMessage(4, error0).sendToTarget();
            }
            throw error0;
        }
    }

    public final void zzbc(int v) throws IOException {
        IOException iOException0 = this.zzbge;
        if(iOException0 != null && this.zzbgf > v) {
            throw iOException0;
        }
    }

    public final void zzek(long v) {
        zzob.checkState(this.zzbgh.zzbfw == null);
        this.zzbgh.zzbfw = this;
        if(v > 0L) {
            this.sendEmptyMessageDelayed(0, v);
            return;
        }
        this.execute();
    }

    public final void zzl(boolean z) {
        this.zzaee = z;
        this.zzbge = null;
        if(this.hasMessages(0)) {
            this.removeMessages(0);
            if(!z) {
                this.sendEmptyMessage(1);
            }
        }
        else {
            this.zzbga.cancelLoad();
            if(this.zzbgg != null) {
                this.zzbgg.interrupt();
            }
        }
        if(z) {
            this.finish();
            long v = SystemClock.elapsedRealtime();
            this.zzbgb.zza(this.zzbga, v, v - this.zzbgd, true);
        }
    }
}

