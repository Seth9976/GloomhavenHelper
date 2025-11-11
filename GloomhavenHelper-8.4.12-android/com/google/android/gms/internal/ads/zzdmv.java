package com.google.android.gms.internal.ads;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class zzdmv extends zzdnn implements Runnable {
    @NullableDecl
    private zzdof zzhbj;
    @NullableDecl
    private Object zzhca;

    zzdmv(zzdof zzdof0, Object object0) {
        this.zzhbj = (zzdof)zzdlg.checkNotNull(zzdof0);
        this.zzhca = zzdlg.checkNotNull(object0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    protected final void afterDone() {
        this.maybePropagateCancellationTo(this.zzhbj);
        this.zzhbj = null;
        this.zzhca = null;
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    protected final String pendingToString() {
        zzdof zzdof0 = this.zzhbj;
        Object object0 = this.zzhca;
        String s = super.pendingToString();
        String s1 = zzdof0 == null ? "" : "inputFuture=[" + zzdof0 + "], ";
        if(object0 != null) {
            return s1 + "function=[" + object0 + "]";
        }
        if(s != null) {
            String s2 = String.valueOf(s1);
            String s3 = String.valueOf(s);
            return s3.length() == 0 ? new String(s2) : s2 + s3;
        }
        return null;
    }

    @Override
    public final void run() {
        Object object2;
        Object object1;
        zzdof zzdof0 = this.zzhbj;
        Object object0 = this.zzhca;
        if((this.isCancelled() | zzdof0 == null | (object0 == null ? 1 : 0)) != 0) {
            return;
        }
        this.zzhbj = null;
        if(zzdof0.isCancelled()) {
            this.setFuture(zzdof0);
            return;
        }
        try {
            object1 = zzdnt.zza(zzdof0);
        }
        catch(CancellationException unused_ex) {
            this.cancel(false);
            return;
        }
        catch(ExecutionException executionException0) {
            this.setException(executionException0.getCause());
            return;
        }
        catch(RuntimeException runtimeException0) {
            this.setException(runtimeException0);
            return;
        }
        catch(Error error0) {
            this.setException(error0);
            return;
        }
        try {
            object2 = this.zzc(object0, object1);
        }
        catch(Throwable throwable0) {
            try {
                this.setException(throwable0);
                this.zzhca = null;
                return;
            }
            catch(Throwable throwable1) {
                this.zzhca = null;
                throw throwable1;
            }
        }
        this.zzhca = null;
        this.setResult(object2);
    }

    abstract void setResult(@NullableDecl Object arg1);

    static zzdof zza(zzdof zzdof0, zzdku zzdku0, Executor executor0) {
        zzdlg.checkNotNull(zzdku0);
        zzdof zzdof1 = new zzdmx(zzdof0, zzdku0);
        zzdof0.addListener(((Runnable)zzdof1), zzdoh.zza(executor0, ((zzdmt)zzdof1)));
        return zzdof1;
    }

    static zzdof zza(zzdof zzdof0, zzdng zzdng0, Executor executor0) {
        zzdlg.checkNotNull(executor0);
        zzdof zzdof1 = new zzdmy(zzdof0, zzdng0);
        zzdof0.addListener(((Runnable)zzdof1), zzdoh.zza(executor0, ((zzdmt)zzdof1)));
        return zzdof1;
    }

    @NullableDecl
    abstract Object zzc(Object arg1, @NullableDecl Object arg2) throws Exception;
}

