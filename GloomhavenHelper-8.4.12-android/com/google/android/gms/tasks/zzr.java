package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;
import jeb.synthetic.FIN;

final class zzr {
    private final Object mLock;
    @GuardedBy("mLock")
    private Queue zzt;
    @GuardedBy("mLock")
    private boolean zzu;

    zzr() {
        this.mLock = new Object();
    }

    public final void zza(@NonNull Task task0) {
        zzq zzq0;
        int v;
        synchronized(this.mLock) {
            if(this.zzt != null && !this.zzu) {
                this.zzu = true;
                while(true) {
                    Object object1 = this.mLock;
                    synchronized(object1) {
                        v = FIN.finallyOpen$NT();
                        zzq0 = (zzq)this.zzt.poll();
                        if(zzq0 == null) {
                            break;
                        }
                        FIN.finallyCodeBegin$NT(v);
                    }
                    FIN.finallyCodeEnd$NT(v);
                    zzq0.onComplete(task0);
                }
                this.zzu = false;
                FIN.finallyExec$NT(v);
            }
        }
    }

    public final void zza(@NonNull zzq zzq0) {
        synchronized(this.mLock) {
            if(this.zzt == null) {
                this.zzt = new ArrayDeque();
            }
            this.zzt.add(zzq0);
        }
    }
}

